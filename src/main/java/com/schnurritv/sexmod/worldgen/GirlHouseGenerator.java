package com.schnurritv.sexmod.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;

/**
 * Generates a character's home.
 *
 * Priority 1: Load the character's own NBT structure from
 *             data/sexmod/structures/<girl>.nbt (1.12.2-era templates,
 *             auto-upgraded to 1.21.1 by the DataFixer on load).
 * Priority 2: Pick a vanilla house template from the character's pool
 *             (data/sexmod/structures/<vanilla_house>.nbt).
 * Fallback:   Hardcoded 6×8 wooden cabin (no NBT dependency).
 *
 * The structure is placed at the character's spawn position, so we avoid
 * running during chunk generation (no 51% hang) — it happens lazily when
 * the character first ticks in BaseGirlEntity.
 */
public class GirlHouseGenerator {

    // Hardcoded fallback cabin dimensions (only used when no NBT is available)
    private static final int W = 6;  // x
    private static final int D = 8;  // z
    private static final int H = 4;  // height (wall)

    /** Character → NBT structure name (data/sexmod/structures/<name>.nbt) */
    private static String structureForGirl(String girlName) {
        return switch (girlName) {
            case "jenny"    -> "jenny";
            case "ellie"    -> "ellie";
            case "alex"     -> "alex";
            case "bia"      -> "bia";
            case "goblin"   -> "goblin";
            case "luna"     -> "luna";
            case "ssa"      -> "ssa";
            default         -> null; // no character-specific structure
        };
    }

    /** Vanilla house pool per character (from old WorldGenHandler configs) */
    private static String[] vanillaPoolForGirl(String girlName) {
        return switch (girlName) {
            case "ellie"    -> new String[]{"taiga_fisher_cottage_1", "taiga_medium_house_1", "taiga_small_house_1"};
            case "jenny"    -> new String[]{"plains_small_house_5", "plains_fisher_cottage_1", "plains_small_house_1"};
            case "allie"    -> new String[]{"plains_small_house_2", "plains_small_house_3", "plains_small_house_4"};
            case "bia"      -> new String[]{"plains_medium_house_1", "plains_medium_house_2", "plains_shepherds_house_1"};
            case "luna"     -> new String[]{"plains_fisher_cottage_1"};
            case "goblin"   -> new String[]{"plains_small_house_6", "plains_small_house_7", "plains_small_house_8"};
            case "alex"     -> new String[]{"plains_big_house_1", "plains_medium_house_1", "plains_medium_house_2"};
            case "ssa"      -> new String[]{"desert_small_house_1", "desert_medium_house_1"};
            case "cat"      -> new String[]{"plains_library_2", "plains_masons_house_1", "taiga_small_house_2"};
            case "bee"      -> new String[]{"savanna_small_house_1", "savanna_small_house_2", "savanna_small_house_3"};
            case "kobold"   -> new String[]{"snowy_small_house_1", "snowy_small_house_2", "snowy_small_house_3"};
            case "mika"     -> new String[]{"snowy_fisher_cottage", "snowy_small_house_2", "snowy_small_house_3"};
            default         -> new String[]{"plains_small_house_1"};
        };
    }

    /**
     * Generate a house for a character. Returns the door position
     * (where the character should stand), or the center if nothing was placed.
     */
    public static BlockPos generateCottage(Level level, BlockPos center) {
        if (level.isClientSide()) return center;
        if (!(level instanceof ServerLevel serverLevel)) return center;

        String girlName = pendingGirlName != null ? pendingGirlName : "";
        StructureTemplateManager manager = serverLevel.getStructureManager();

        // ── Priority 1: character-specific NBT structure ──
        String own = structureForGirl(girlName);
        if (own != null) {
            BlockPos result = tryPlaceStructure(serverLevel, manager, center, own, girlName);
            if (result != null) return result;
        }

        // ── Priority 2: vanilla house pool ──
        String[] pool = vanillaPoolForGirl(girlName);
        String picked = pool[serverLevel.random.nextInt(pool.length)];
        BlockPos result = tryPlaceStructure(serverLevel, manager, center, picked, girlName);
        if (result != null) return result;

        // ── Fallback: hardcoded cabin ──
        return buildHardcodedCabin(serverLevel, center);
    }

    /**
     * Try to load and place a structure template. Returns the door position
     * (center + 3 z-offset, matching old behavior) on success, null on failure.
     */
    private static BlockPos tryPlaceStructure(ServerLevel level, StructureTemplateManager manager,
                                              BlockPos center, String structureName, String girlName) {
        ResourceLocation loc = ResourceLocation.fromNamespaceAndPath("sexmod", structureName);
        Optional<StructureTemplate> opt = manager.get(loc);
        if (opt.isEmpty()) {
            // Fallback: explicit structures/ path (old-style)
            opt = manager.get(ResourceLocation.fromNamespaceAndPath("sexmod", "structures/" + structureName));
        }
        if (opt.isEmpty()) {
            // Fallback 2: manual load via ResourceManager + DataFixer (bypasses manager quirks)
            opt = manualLoadStructure(level, structureName);
        }
        if (opt.isEmpty()) return null;

        StructureTemplate template = opt.get();
        int sx = template.getSize().getX();
        int sz = template.getSize().getZ();

        // Find the lowest ground in the footprint
        int halfX = sx / 2;
        int halfZ = sz / 2;
        int baseY = findBaseY(level, center, halfX, halfZ);

        // Origin: center footprint so the house is centered on the character
        BlockPos origin = new BlockPos(center.getX() - halfX, baseY, center.getZ() - halfZ);

        // Clear the area (remove trees/overhangs above ground level)
        clearArea(level, origin, sx, sz, baseY);

        // Place the structure — ignore entities from the template (we spawn
        // the character ourselves to avoid 1.12.2 entity remap issues).
        StructurePlaceSettings settings = new StructurePlaceSettings()
                .setIgnoreEntities(true);
        template.placeInWorld(level, origin, origin, settings, level.random, 3);

        return new BlockPos(center.getX(), baseY + 1, center.getZ() + (sz / 2) + 1);
    }

    /**
     * Manual structure load: read NBT from the mod's datapack resources,
     * run the DataFixer, and parse into a StructureTemplate.
     * Used as a fallback because StructureTemplateManager.get() can silently
     * return empty for some mod-packaged structures.
     */
    public static Optional<StructureTemplate> manualLoadStructure(ServerLevel level, String structureName) {
        try {
            net.minecraft.server.packs.resources.ResourceManager rm = level.getServer().getResourceManager();
            var resOpt = rm.getResource(
                    net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("sexmod", "structures/" + structureName + ".nbt"));
            if (resOpt.isEmpty()) return Optional.empty();
            try (java.io.InputStream in = resOpt.get().open()) {
                var tag = net.minecraft.nbt.NbtIo.readCompressed(in,
                        net.minecraft.nbt.NbtAccounter.create(2000000000L));
                int ver = net.minecraft.nbt.NbtUtils.getDataVersion(tag, 1343);
                var fixed = net.minecraft.util.datafix.DataFixTypes.STRUCTURE.updateToCurrentVersion(
                        net.minecraft.util.datafix.DataFixers.getDataFixer(), tag, ver);
                var st = new StructureTemplate();
                net.minecraft.core.HolderGetter<net.minecraft.world.level.block.Block> blocks =
                        level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.BLOCK);
                st.load(blocks, fixed);
                return Optional.of(st);
            }
        } catch (Exception e) {
            com.schnurritv.sexmod.Main.LOGGER.error("Manual structure load failed for {}", structureName, e);
            return Optional.empty();
        }
    }

    private static void clearArea(ServerLevel level, BlockPos origin, int sx, int sz, int baseY) {
        for (int x = 0; x < sx; x++) {
            for (int z = 0; z < sz; z++) {
                BlockPos p = origin.offset(x, 0, z);
                int groundY = solidGroundLevel(level, p);
                for (int y = baseY; y <= groundY + 6; y++) {
                    BlockPos bp = new BlockPos(p.getX(), y, p.getZ());
                    BlockState state = level.getBlockState(bp);
                    if (!state.isAir() && !state.liquid()) {
                        level.setBlock(bp, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    // ── Hardcoded fallback cabin (original implementation) ──

    private static BlockPos buildHardcodedCabin(ServerLevel level, BlockPos center) {
        int halfX = W / 2;
        int halfZ = D / 2;
        int baseY = findBaseY(level, center, halfX, halfZ);
        BlockPos origin = new BlockPos(center.getX() - halfX, baseY, center.getZ() - halfZ);

        flattenAndClear(level, origin, W + 2, D + 2, baseY);
        buildWalls(level, origin);
        buildFloor(level, origin);
        buildRoof(level, origin);
        buildDoor(level, origin);
        buildWindow(level, origin);
        buildTorches(level, origin);

        return new BlockPos(center.getX(), baseY + 1, center.getZ() + 3);
    }

    private static void buildWalls(ServerLevel level, BlockPos o) {
        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                if (x < 2 || x > 3) {
                    level.setBlock(o.offset(x, y, 0), Blocks.OAK_PLANKS.defaultBlockState(), 2);
                }
                level.setBlock(o.offset(x, y, D - 1), Blocks.OAK_PLANKS.defaultBlockState(), 2);
            }
        }
        for (int z = 0; z < D; z++) {
            for (int y = 0; y < H; y++) {
                level.setBlock(o.offset(0, y, z), Blocks.OAK_PLANKS.defaultBlockState(), 2);
                level.setBlock(o.offset(W - 1, y, z), Blocks.OAK_PLANKS.defaultBlockState(), 2);
            }
        }
    }

    private static void buildFloor(ServerLevel level, BlockPos o) {
        for (int x = 0; x < W; x++) {
            for (int z = 0; z < D; z++) {
                level.setBlock(o.offset(x, 0, z), Blocks.OAK_PLANKS.defaultBlockState(), 2);
            }
        }
    }

    private static void buildRoof(ServerLevel level, BlockPos o) {
        for (int x = -1; x <= W; x++) {
            for (int z = -1; z <= D; z++) {
                level.setBlock(o.offset(x, H, z), Blocks.OAK_SLAB.defaultBlockState(), 2);
            }
        }
    }

    private static void buildDoor(ServerLevel level, BlockPos o) {
        level.setBlock(o.offset(2, 0, 0), Blocks.AIR.defaultBlockState(), 2);
        level.setBlock(o.offset(2, 1, 0), Blocks.AIR.defaultBlockState(), 2);
        level.setBlock(o.offset(3, 0, 0), Blocks.AIR.defaultBlockState(), 2);
        level.setBlock(o.offset(3, 1, 0), Blocks.AIR.defaultBlockState(), 2);
        level.setBlock(o.offset(1, 0, 0), Blocks.OAK_FENCE.defaultBlockState(), 2);
        level.setBlock(o.offset(1, 1, 0), Blocks.OAK_FENCE.defaultBlockState(), 2);
        level.setBlock(o.offset(4, 0, 0), Blocks.OAK_FENCE.defaultBlockState(), 2);
        level.setBlock(o.offset(4, 1, 0), Blocks.OAK_FENCE.defaultBlockState(), 2);
    }

    private static void buildWindow(ServerLevel level, BlockPos o) {
        level.setBlock(o.offset(2, 2, 0), Blocks.GLASS_PANE.defaultBlockState(), 2);
        level.setBlock(o.offset(3, 2, 0), Blocks.GLASS_PANE.defaultBlockState(), 2);
    }

    private static void buildTorches(ServerLevel level, BlockPos o) {
        level.setBlock(o.offset(0, 2, 2), Blocks.TORCH.defaultBlockState(), 2);
        level.setBlock(o.offset(W - 1, 2, D - 3), Blocks.TORCH.defaultBlockState(), 2);
    }

    // ── Terrain preparation ──

    private static void flattenAndClear(ServerLevel level, BlockPos o, int areaW, int areaZ, int baseY) {
        for (int x = -1; x < areaW - 1; x++) {
            for (int z = -1; z < areaZ - 1; z++) {
                BlockPos p = o.offset(x, 0, z);
                int groundY = solidGroundLevel(level, p);
                for (int y = baseY; y <= groundY + 6; y++) {
                    BlockPos bp = new BlockPos(p.getX(), y, p.getZ());
                    BlockState state = level.getBlockState(bp);
                    if (!state.isAir() && !state.liquid()) {
                        level.setBlock(bp, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
                if (groundY < baseY) {
                    for (int y = groundY + 1; y <= baseY; y++) {
                        BlockPos fp = new BlockPos(p.getX(), y, p.getZ());
                        BlockState filler = (y == baseY) ? Blocks.GRASS_BLOCK.defaultBlockState() : Blocks.DIRT.defaultBlockState();
                        level.setBlock(fp, filler, 2);
                    }
                }
            }
        }
    }

    private static int findBaseY(Level level, BlockPos center, int halfX, int halfZ) {
        int lowest = Integer.MAX_VALUE;
        for (int x = -halfX; x <= halfX; x++) {
            for (int z = -halfZ; z <= halfZ; z++) {
                int gy = solidGroundLevel(level, center.offset(x, 0, z));
                if (gy < lowest) lowest = gy;
            }
        }
        return lowest;
    }

    private static int solidGroundLevel(Level level, BlockPos pos) {
        int hint = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, pos).getY();
        for (int y = hint; y >= level.getMinBuildHeight(); y--) {
            BlockPos check = new BlockPos(pos.getX(), y, pos.getZ());
            BlockState state = level.getBlockState(check);
            if (state.isSolid() && !state.liquid()) {
                return y;
            }
        }
        return Math.max(level.getSeaLevel(), hint);
    }

    // ── Context: set before calling generateCottage ──
    public static String pendingGirlName = null;
}
