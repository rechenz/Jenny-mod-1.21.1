package com.schnurritv.sexmod.worldgen;

import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

/**
 * World generation handler using chunk-load events.
 * Per-character houses placed at world generation time via NBT structure templates.
 * Source: adapted from g3_class344 (original mod's IWorldGenerator).
 *
 * Tracks placed structures via a static per-dimension HashMap
 * (avoids SavedData complexity).
 */
@Mod.EventBusSubscriber(modid = Main.MODID)
public class WorldGenHandler {

    private static final List<CharacterGenConfig> CONFIGS = new ArrayList<>();
    // Per-dimension trackers: dimension name -> Set<"chunkX,chunkZ">
    private static final Map<String, Set<String>> placedStructures = new HashMap<>();

    static {
        // Each character gets a random house from a pool of vanilla 1.21.1 structure templates
        CONFIGS.add(new CharacterGenConfig("ellie",
            Set.of(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:old_growth_pine_taiga")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:old_growth_spruce_taiga")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:taiga")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:dark_forest"))),
            new BlockPos(18, 8, 18), 5, true,
            "taiga_fisher_cottage_1", "taiga_medium_house_1", "taiga_small_house_1"));
        CONFIGS.add(new CharacterGenConfig("jenny",
            Set.of(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:plains")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:flower_forest")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:forest"))),
            new BlockPos(12, 8, 12), 3, true,
            "plains_small_house_5", "plains_fisher_cottage_1", "plains_small_house_1"));
        CONFIGS.add(new CharacterGenConfig("allie",
            Set.of(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:cherry_grove")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:flower_forest")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:forest"))),
            new BlockPos(12, 8, 12), 3, true,
            "plains_small_house_2", "plains_small_house_3", "plains_small_house_4"));
        CONFIGS.add(new CharacterGenConfig("bia",
            Set.of(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:birch_forest")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:old_growth_birch_forest"))),
            new BlockPos(12, 8, 12), 3, true,
            "plains_medium_house_1", "plains_medium_house_2", "plains_shepherds_house_1"));
        CONFIGS.add(new CharacterGenConfig("luna",
            Set.of(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:ocean")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:deep_ocean")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:beach"))),
            new BlockPos(10, 7, 10), 2, true,
            "plains_fisher_cottage_1"));
        CONFIGS.add(new CharacterGenConfig("goblin",
            Set.of(), new BlockPos(8, 5, 10), 3, true,
            "plains_small_house_6", "plains_small_house_7", "plains_small_house_8"));
        CONFIGS.add(new CharacterGenConfig("alex",
            Set.of(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:plains")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:sunflower_plains")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:meadow"))),
            new BlockPos(14, 8, 14), 3, true,
            "plains_big_house_1", "plains_medium_house_1", "plains_medium_house_2"));
        CONFIGS.add(new CharacterGenConfig("ssa",
            Set.of(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:desert")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:badlands")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:wooded_badlands"))),
            new BlockPos(10, 6, 10), 3, true,
            "desert_small_house_1", "desert_medium_house_1"));
        CONFIGS.add(new CharacterGenConfig("cat",
            Set.of(), new BlockPos(12, 8, 12), 3, true,
            "plains_library_2", "plains_masons_house_1", "taiga_small_house_2"));
        CONFIGS.add(new CharacterGenConfig("bee",
            Set.of(), new BlockPos(12, 8, 12), 3, true,
            "savanna_small_house_1", "savanna_small_house_2", "savanna_small_house_3"));
        CONFIGS.add(new CharacterGenConfig("kobold",
            Set.of(), new BlockPos(12, 8, 12), 3, true,
            "snowy_small_house_1", "snowy_small_house_2", "snowy_small_house_3"));
        CONFIGS.add(new CharacterGenConfig("mika",
            Set.of(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:snowy_plains")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:snowy_taiga")),
                   ResourceKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:ice_spikes"))),
            new BlockPos(12, 8, 12), 3, true,
            "snowy_fisher_cottage", "snowy_small_house_2", "snowy_small_house_3"));
    }

    private static String dimKey(ServerLevel level) {
        return level.dimension().location().toString();
    }

    // ── Event: chunk load → try placing structures ──
    // Uses 1.21.1 vanilla structure NBT files extracted from server jar
    // Files are in data/sexmod/structures/*.nbt
    // DISABLED: Worldgen NBT structures cause chunk-load hang at 51%.
    // The custom character NBTs (jenny.nbt, ellie.nbt, etc.) are 1.12.2 G3 format,
    // incompatible with 1.21.1 StructureTemplateManager. vanilla village NBTs work
    // but lack character spawn offsets.
    // Characters spawn via BaseGirlEntity.tick() -> GirlHouseGenerator.generateCottage() instead.
    //@SubscribeEvent
    //public static void onChunkLoad(ChunkEvent.Load event) { }

    private static boolean tryGenerate(ServerLevel level, int cx, int cz, CharacterGenConfig cfg) {
        RandomSource random = level.getRandom();

        // 0.4% chance per chunk (same as old_ref)
        if (random.nextFloat() > 0.004f) return false;

        int wx = cx * 16 + 8;
        int wz = cz * 16 + 8;

        // Check biome
        if (!cfg.biomes.isEmpty()) {
            ResourceKey<Biome> biomeKey = level.getBiome(new BlockPos(wx, 80, wz)).unwrapKey().orElse(null);
            if (biomeKey == null || !cfg.biomes.contains(biomeKey)) return false;
        }

        int groundY = findGroundY(level, wx, wz);
        if (groundY <= level.getMinBuildHeight()) return false;

        if (!cfg.needsFlatGround) {
            BlockState groundState = level.getBlockState(new BlockPos(wx, groundY, wz));
            if (groundState.liquid()) return false;
            BlockPos placePos = new BlockPos(wx, groundY + 1, wz);
            clearAndPlaceStructure(level, placePos, cfg);
            spawnCharacter(level, new BlockPos(wx, groundY + 1, wz), cfg.name);
            return true;
        }

        // Surface type: check height variance
        int sizeX = cfg.structureSize.getX();
        int sizeZ = cfg.structureSize.getZ();
        int startX = wx - sizeX / 2;
        int startZ = wz - sizeZ / 2;

        int lowest = Integer.MAX_VALUE;
        int highest = Integer.MIN_VALUE;

        for (int x = startX; x < startX + sizeX; x++) {
            for (int z = startZ; z < startZ + sizeZ; z++) {
                int gy = findGroundY(level, x, z);
                if (gy < lowest) lowest = gy;
                if (gy > highest) highest = gy;
                if (level.getBlockState(new BlockPos(x, gy, z)).liquid()) return false;
            }
        }

        if (highest - lowest > cfg.maxHeightVariance) return false;

        int buildY = highest;
        BlockPos origin = new BlockPos(startX, buildY, startZ);
        fillUnderPlatform(level, origin, sizeX, sizeZ, buildY, cfg.needsFlatGround);
        clearAndPlaceStructure(level, origin, cfg);
        spawnCharacter(level, new BlockPos(wx, buildY + 1, wz), cfg.name);
        return true;
    }

    private static void clearAndPlaceStructure(ServerLevel level, BlockPos origin, CharacterGenConfig cfg) {
        StructureTemplateManager manager = level.getStructureManager();

        // Pick a random structure template from the character's pool
        String[] pool = cfg.structureNames();
        if (pool == null || pool.length == 0) return;
        String selectedName = pool[level.random.nextInt(pool.length)];

        // Find the NBT file in our mod: data/sexmod/structures/<name>.nbt
        // In 1.21.1, StructureTemplateManager looks in data/<namespace>/structures/<path>.nbt
        ResourceLocation loc = ResourceLocation.fromNamespaceAndPath("sexmod", selectedName);
        Optional<StructureTemplate> opt = manager.get(loc);
        // Fallback: try sexmod:structures/name (old-style explicit path)
        if (opt.isEmpty()) {
            loc = ResourceLocation.fromNamespaceAndPath("sexmod", "structures/" + selectedName);
            opt = manager.get(loc);
        }

        if (opt.isPresent()) {
            StructureTemplate template = opt.get();
            int sx = template.getSize().getX();
            int sz = template.getSize().getZ();
            // Clear the area before placing
            for (int x = -1; x <= sx + 1; x++) {
                for (int z = -1; z <= sz + 1; z++) {
                    for (int y = -1; y <= 6; y++) {
                        BlockPos bp = origin.offset(x, y, z);
                        BlockState st = level.getBlockState(bp);
                        if (!st.isAir() && !st.liquid()) {
                            level.setBlock(bp, Blocks.AIR.defaultBlockState(), 3);
                        }
                    }
                }
            }
            StructurePlaceSettings settings = new StructurePlaceSettings();
            settings.setIgnoreEntities(false);
            template.placeInWorld(level, origin, origin, settings, level.random, 3);
        }
    }

    private static void fillUnderPlatform(ServerLevel level, BlockPos origin, int sizeX, int sizeZ, int buildY, boolean fillHoles) {
        if (!fillHoles) return;
        int y = buildY - 1;
        boolean foundAir = true;
        while (foundAir) {
            foundAir = false;
            for (int x = -1; x <= sizeX; x++) {
                for (int z = -1; z <= sizeZ; z++) {
                    BlockPos bp = origin.offset(x, y, z);
                    BlockState state = level.getBlockState(bp);
                    if (state.isAir() || state.canBeReplaced()) {
                        level.setBlock(bp, level.canSeeSky(bp) ? Blocks.GRASS_BLOCK.defaultBlockState() : Blocks.DIRT.defaultBlockState(), 3);
                        foundAir = true;
                    }
                }
            }
            y--;
        }
    }

    private static void spawnCharacter(ServerLevel level, BlockPos pos, String name) {
        var type = switch (name.toLowerCase()) {
            case "jenny" -> EntityRegistry.JENNY.get();
            case "ellie" -> EntityRegistry.ELLIE.get();
            case "allie", "alex" -> EntityRegistry.ALLIE.get();
            case "bia" -> EntityRegistry.BIA.get();
            case "bee" -> EntityRegistry.BEE.get();
            case "cat" -> EntityRegistry.CAT.get();
            case "goblin" -> EntityRegistry.GOBLIN.get();
            case "kobold" -> EntityRegistry.KOBOLD.get();
            case "slime" -> EntityRegistry.SLIME.get();
            case "galath" -> EntityRegistry.GALATH.get();
            case "manglelie" -> EntityRegistry.MANGLELIE.get();
            case "lucy" -> EntityRegistry.LUCY.get();
            case "mika" -> EntityRegistry.MIKA.get();
            case "momo" -> EntityRegistry.MOMO.get();
            default -> null;
        };
        if (type == null) return;

        var entity = type.create(level);
        if (entity == null) return;

        entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        // Mark as having a house so BaseGirlEntity.tick() won't generate a second one
        entity.setHasHouse(true);
        level.addFreshEntity(entity);
    }

    private static int findGroundY(ServerLevel level, int x, int z) {
        Set<net.minecraft.world.level.block.Block> surfaceBlocks = Set.of(
            Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.RED_SANDSTONE, Blocks.WATER,
            Blocks.STONE, Blocks.COBBLESTONE, Blocks.DIRT, Blocks.SNOW_BLOCK,
            Blocks.GRAVEL, Blocks.MOSS_BLOCK
        );
        int y = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z)).getY();
        for (int scanY = y; scanY >= y - 20; scanY--) {
            BlockPos check = new BlockPos(x, scanY, z);
            net.minecraft.world.level.block.Block block = level.getBlockState(check).getBlock();
            if (surfaceBlocks.contains(block)) return scanY;
        }
        return y - 1;
    }

    private record CharacterGenConfig(String name, Set<ResourceKey<Biome>> biomes,
                                      BlockPos structureSize, int maxHeightVariance,
                                      boolean needsFlatGround, String... structureNames) {}
}
