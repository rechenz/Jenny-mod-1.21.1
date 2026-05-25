package com.schnurritv.sexmod.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Generates small cottages for human characters on first spawn.
 * House: 5×4×5 (x×y×z), door on south, bed against north wall, char spawns 1 block outside south.
 */
public class GirlHouseGenerator {

    /** House size: ±2 from center. Interior: 3×3 at ±1. */
    private static final int RADIUS = 2;
    private static final int WALL_HEIGHT = 4; // floor at Y+0, walls Y+1..Y+3, roof at Y+4

    /**
     * Generate a cottage at center, return spawn position (outside door).
     */
    public static BlockPos generateCottage(Level level, BlockPos center) {
        if (level.isClientSide()) return center;

        int baseY = columnGroundLevel(level, center);

        // ── Foundation: flatten ground ──
        for (int x = -RADIUS; x <= RADIUS; x++) {
            for (int z = -RADIUS; z <= RADIUS; z++) {
                BlockPos p = center.offset(x, 0, z);
                int groundY = level.getHeightmapPos(
                    net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, p).getY();
                // Clear above
                for (int y = baseY + 1; y <= Math.max(groundY, baseY + WALL_HEIGHT); y++) {
                    level.setBlock(new BlockPos(p.getX(), y, p.getZ()), Blocks.AIR.defaultBlockState(), 3);
                }
                // Fill ground to baseY-1, place floor at baseY
                for (int y = groundY; y < baseY; y++) {
                    level.setBlock(new BlockPos(p.getX(), y, p.getZ()), Blocks.DIRT.defaultBlockState(), 3);
                }
            }
        }

        // ── Floor: oak planks ──
        for (int x = -RADIUS; x <= RADIUS; x++) {
            for (int z = -RADIUS; z <= RADIUS; z++) {
                level.setBlock(center.offset(x, 0, z), Blocks.OAK_PLANKS.defaultBlockState(), 3);
            }
        }

        // ── Walls + roof ──
        for (int x = -RADIUS; x <= RADIUS; x++) {
            for (int z = -RADIUS; z <= RADIUS; z++) {
                boolean isEdge = Math.abs(x) == RADIUS || Math.abs(z) == RADIUS;
                if (!isEdge) continue; // interior: skip

                // Door gap on south wall center
                if (x == 0 && z == RADIUS) continue;
                // Window on east wall center
                boolean isWindow = (x == RADIUS && z == 0);

                for (int y = 1; y <= 3; y++) {
                    BlockPos w = center.offset(x, y, z);
                    if (y == 3) {
                        level.setBlock(w, Blocks.OAK_LOG.defaultBlockState(), 3);
                    } else if (y == 2 && isWindow) {
                        level.setBlock(w, Blocks.GLASS_PANE.defaultBlockState(), 3);
                    } else if (Math.abs(x) == RADIUS && Math.abs(z) == RADIUS && y <= 2) {
                        level.setBlock(w, Blocks.COBBLESTONE.defaultBlockState(), 3);
                    } else {
                        level.setBlock(w, Blocks.OAK_PLANKS.defaultBlockState(), 3);
                    }
                }
            }
        }

        // ── Roof: stair rim + slab center ──
        for (int x = -RADIUS; x <= RADIUS; x++) {
            for (int z = -RADIUS; z <= RADIUS; z++) {
                BlockPos r = center.offset(x, 4, z);
                boolean isEdge = Math.abs(x) == RADIUS || Math.abs(z) == RADIUS;
                if (isEdge) {
                    Direction facing = (x == -RADIUS) ? Direction.EAST :
                                       (x == RADIUS) ? Direction.WEST :
                                       (z == -RADIUS) ? Direction.SOUTH : Direction.NORTH;
                    level.setBlock(r, Blocks.OAK_STAIRS.defaultBlockState()
                        .setValue(StairBlock.FACING, facing)
                        .setValue(StairBlock.SHAPE,
                            net.minecraft.world.level.block.state.properties.StairsShape.STRAIGHT), 3);
                } else {
                    level.setBlock(r, Blocks.OAK_SLAB.defaultBlockState(), 3);
                }
            }
        }

        // ── Door: south wall, center ──
        BlockPos door = center.offset(0, 1, RADIUS);
        level.setBlock(door, Blocks.OAK_DOOR.defaultBlockState()
            .setValue(DoorBlock.FACING, Direction.SOUTH)
            .setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER), 3);
        level.setBlock(door.above(), Blocks.OAK_DOOR.defaultBlockState()
            .setValue(DoorBlock.FACING, Direction.SOUTH)
            .setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER), 3);

        // ── Bed: against north wall. Head at north (inside), foot toward center ──
        // Bed occupies 2 blocks: head at north edge (-RADIUS,0,0) at z=-1, foot at z=0  
        // This leaves z=+1 clear for walking
        BlockPos bedHead = center.offset(0, 1, -(RADIUS + 0)); // north wall
        BlockPos bedFoot = bedHead.south();
        level.setBlock(bedHead, Blocks.RED_BED.defaultBlockState()
            .setValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING, Direction.SOUTH)
            .setValue(BedBlock.PART, BedPart.HEAD), 3);
        level.setBlock(bedFoot, Blocks.RED_BED.defaultBlockState()
            .setValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING, Direction.SOUTH)
            .setValue(BedBlock.PART, BedPart.FOOT), 3);

        // ── Furniture ──
        // Chest: west wall
        level.setBlock(center.offset(-(RADIUS + 0), 1, 0), Blocks.CHEST.defaultBlockState()
            .setValue(net.minecraft.world.level.block.ChestBlock.FACING, Direction.EAST), 3);
        // Crafting table: east wall, south of window
        level.setBlock(center.offset(RADIUS, 1, 1), Blocks.CRAFTING_TABLE.defaultBlockState(), 3);
        // Torches
        level.setBlock(center.offset(-1, 2, 1), Blocks.TORCH.defaultBlockState(), 3);
        level.setBlock(center.offset(1, 2, -1), Blocks.TORCH.defaultBlockState(), 3);

        // ── Garden in front of door ──
        BlockPos garden = center.offset(0, 0, RADIUS + 1);
        if (level.getBlockState(garden).isAir() || level.getBlockState(garden).canBeReplaced()) {
            level.setBlock(garden, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
            level.setBlock(garden.above(), Blocks.POPPY.defaultBlockState(), 3);
            level.setBlock(garden.east().above(), Blocks.DANDELION.defaultBlockState(), 3);
        }

        // ── Spawn position: 2 blocks south of door (outside, clear) ──
        BlockPos spawnPos = center.offset(0, 0, RADIUS + 2);
        // Clear any blocks at spawn position
        level.setBlock(spawnPos, Blocks.AIR.defaultBlockState(), 3);
        level.setBlock(spawnPos.above(), Blocks.AIR.defaultBlockState(), 3);

        return spawnPos;
    }

    /** Get consistent ground level using the center column. */
    private static int columnGroundLevel(Level level, BlockPos center) {
        int y = level.getHeightmapPos(
            net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, center).getY();
        // Don't go below sea level (build on surface)
        int seaLevel = level.getSeaLevel();
        if (y < seaLevel) y = seaLevel + 2;
        return y;
    }
}
