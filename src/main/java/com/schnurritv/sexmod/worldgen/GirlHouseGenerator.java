package com.schnurritv.sexmod.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Generates a small wooden cabin as a character's home.
 * 6×8 wooden cabin with a door, window, and interior floor.
 * No NBT dependency — all hardcoded block placement.
 */
public class GirlHouseGenerator {

    private static final int W = 6;  // x
    private static final int D = 8;  // z
    private static final int H = 4;  // height (wall)

    public static BlockPos generateCottage(Level level, BlockPos center) {
        if (level.isClientSide()) return center;
        if (!(level instanceof ServerLevel serverLevel)) return center;

        int halfX = W / 2;
        int halfZ = D / 2;

        // Find lowest ground in the footprint
        int baseY = findBaseY(serverLevel, center, halfX, halfZ);

        BlockPos origin = new BlockPos(center.getX() - halfX, baseY, center.getZ() - halfZ);

        // Flatten & clear area
        flattenAndClear(serverLevel, origin, W + 2, D + 2, baseY);

        // Build house
        buildWalls(serverLevel, origin);
        buildFloor(serverLevel, origin);
        buildRoof(serverLevel, origin);
        buildDoor(serverLevel, origin);
        buildWindow(serverLevel, origin);
        buildTorches(serverLevel, origin);

        return new BlockPos(center.getX(), baseY + 1, center.getZ() + 3);
    }

    private static void buildWalls(ServerLevel level, BlockPos o) {
        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                // Front wall (z=0) — door gap at x=2,3
                if (x < 2 || x > 3) {
                    level.setBlock(o.offset(x, y, 0), Blocks.OAK_PLANKS.defaultBlockState(), 2);
                }
                // Back wall (z=D-1)
                level.setBlock(o.offset(x, y, D - 1), Blocks.OAK_PLANKS.defaultBlockState(), 2);
            }
        }
        for (int z = 0; z < D; z++) {
            for (int y = 0; y < H; y++) {
                // Left wall (x=0)
                level.setBlock(o.offset(0, y, z), Blocks.OAK_PLANKS.defaultBlockState(), 2);
                // Right wall (x=W-1)
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
        // Simple flat roof one block above wall top
        for (int x = -1; x <= W; x++) {
            for (int z = -1; z <= D; z++) {
                level.setBlock(o.offset(x, H, z), Blocks.OAK_SLAB.defaultBlockState(), 2);
            }
        }
    }

    private static void buildDoor(ServerLevel level, BlockPos o) {
        // Door at front center (x=2,3, z=0, y=0,1)
        level.setBlock(o.offset(2, 0, 0), Blocks.AIR.defaultBlockState(), 2);
        level.setBlock(o.offset(2, 1, 0), Blocks.AIR.defaultBlockState(), 2);
        level.setBlock(o.offset(3, 0, 0), Blocks.AIR.defaultBlockState(), 2);
        level.setBlock(o.offset(3, 1, 0), Blocks.AIR.defaultBlockState(), 2);
        // Door frame
        level.setBlock(o.offset(1, 0, 0), Blocks.OAK_FENCE.defaultBlockState(), 2);
        level.setBlock(o.offset(1, 1, 0), Blocks.OAK_FENCE.defaultBlockState(), 2);
        level.setBlock(o.offset(4, 0, 0), Blocks.OAK_FENCE.defaultBlockState(), 2);
        level.setBlock(o.offset(4, 1, 0), Blocks.OAK_FENCE.defaultBlockState(), 2);
    }

    private static void buildWindow(ServerLevel level, BlockPos o) {
        // Small window on front wall: one block above door
        level.setBlock(o.offset(2, 2, 0), Blocks.GLASS_PANE.defaultBlockState(), 2);
        level.setBlock(o.offset(3, 2, 0), Blocks.GLASS_PANE.defaultBlockState(), 2);
    }

    private static void buildTorches(ServerLevel level, BlockPos o) {
        // Torches inside on walls
        level.setBlock(o.offset(0, 2, 2), Blocks.TORCH.defaultBlockState(), 2);
        level.setBlock(o.offset(W - 1, 2, D - 3), Blocks.TORCH.defaultBlockState(), 2);
    }

    // ── Terrain preparation ──

    private static void flattenAndClear(ServerLevel level, BlockPos o, int areaW, int areaZ, int baseY) {
        for (int x = -1; x < areaW - 1; x++) {
            for (int z = -1; z < areaZ - 1; z++) {
                BlockPos p = o.offset(x, 0, z);
                int groundY = solidGroundLevel(level, p);
                // Clear everything above baseY
                for (int y = baseY; y <= groundY + 6; y++) {
                    BlockPos bp = new BlockPos(p.getX(), y, p.getZ());
                    BlockState state = level.getBlockState(bp);
                    if (!state.isAir() && !state.liquid()) {
                        level.setBlock(bp, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
                // Fill low spots up to baseY
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
