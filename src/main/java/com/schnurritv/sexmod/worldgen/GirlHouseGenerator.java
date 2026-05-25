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
 * Generates small themed cottages for human characters on first spawn.
 */
public class GirlHouseGenerator {

    /**
     * Generate a small cottage at the given position.
     * House is 5x4x5 (w x h x d), wood frame + cobblestone base.
     * @return true if house was generated
     */
    public static boolean generateCottage(Level level, BlockPos center) {
        if (level.isClientSide()) return false;

        // Find ground level: search downward from 2 blocks above center
        int baseY = center.getY();
        if (baseY < 1) baseY = 1;

        // Level the ground: fill uneven terrain under the house
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                BlockPos check = center.offset(x, 0, z);
                // Find the topmost solid block below the house
                int topY = level.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, check).getY();
                if (topY > baseY + 3) {
                    // On a hill — clear trees above
                    for (int y = baseY + 5; y <= topY; y++) {
                        level.setBlock(new BlockPos(check.getX(), y, check.getZ()), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
                // Fill ground up to baseY-1
                int groundFill = Math.min(topY, baseY - 1);
                for (int y = groundFill; y < baseY; y++) {
                    BlockPos fill = new BlockPos(check.getX(), y, check.getZ());
                    level.setBlock(fill, Blocks.DIRT.defaultBlockState(), 3);
                }
            }
        }

        // Floor: oak planks at baseY
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                level.setBlock(center.offset(x, 0, z), Blocks.OAK_PLANKS.defaultBlockState(), 3);
            }
        }

        // Walls
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                // Skip interior (3x3 area)
                if (x >= -1 && x <= 1 && z >= -1 && z <= 1) continue;
                // Skip door position on south wall (center of south)
                if (x == 0 && z == 2) continue;
                // Skip window position on east wall
                if (x == 2 && z == 0) continue;

                for (int y = 1; y <= 3; y++) {
                    BlockPos wall = center.offset(x, y, z);
                    if (y == 3) {
                        // Top row: logs
                        level.setBlock(wall, Blocks.OAK_LOG.defaultBlockState(), 3);
                    } else if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                        // Corners: cobblestone base (y=1, y=2)
                        level.setBlock(wall, Blocks.COBBLESTONE.defaultBlockState(), 3);
                    } else {
                        level.setBlock(wall, Blocks.OAK_PLANKS.defaultBlockState(), 3);
                    }
                }
            }
        }

        // Roof (A-shape: stairs on rim + slabs in the middle, at baseY+4)
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                BlockPos roof = center.offset(x, 4, z);
                boolean isEdge = Math.abs(x) == 2 || Math.abs(z) == 2;
                if (isEdge) {
                    Direction facing = getInwardDirection(x, z);
                    BlockState stairs = Blocks.OAK_STAIRS.defaultBlockState()
                        .setValue(StairBlock.FACING, facing)
                        .setValue(StairBlock.SHAPE, net.minecraft.world.level.block.state.properties.StairsShape.STRAIGHT);
                    level.setBlock(roof, stairs, 3);
                } else {
                    // Slab in the center
                    level.setBlock(roof, Blocks.OAK_SLAB.defaultBlockState(), 3);
                }
            }
        }

        // Door (south side)
        BlockPos door = center.offset(0, 1, 2);
        BlockState doorLower = Blocks.OAK_DOOR.defaultBlockState()
            .setValue(DoorBlock.FACING, Direction.SOUTH)
            .setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
        BlockState doorUpper = Blocks.OAK_DOOR.defaultBlockState()
            .setValue(DoorBlock.FACING, Direction.SOUTH)
            .setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
        level.setBlock(door, doorLower, 3);
        level.setBlock(door.above(), doorUpper, 3);

        // Window (east wall)
        BlockPos window = center.offset(2, 2, 0);
        level.setBlock(window, Blocks.GLASS_PANE.defaultBlockState(), 3);

        // Torches (interior)
        level.setBlock(center.offset(-1, 2, 1), Blocks.TORCH.defaultBlockState(), 3);
        level.setBlock(center.offset(1, 2, -1), Blocks.TORCH.defaultBlockState(), 3);

        // Bed (inside, against north wall, head at north)
        BlockPos bedHead = center.offset(0, 1, -1);
        BlockPos bedFoot = bedHead.south();
        level.setBlock(bedHead, Blocks.RED_BED.defaultBlockState()
            .setValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING, Direction.SOUTH)
            .setValue(BedBlock.PART, BedPart.HEAD), 3);
        level.setBlock(bedFoot, Blocks.RED_BED.defaultBlockState()
            .setValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING, Direction.SOUTH)
            .setValue(BedBlock.PART, BedPart.FOOT), 3);

        // Crafting table (center of room)
        level.setBlock(center.offset(0, 1, 0), Blocks.CRAFTING_TABLE.defaultBlockState(), 3);

        // Chest (west wall)
        level.setBlock(center.offset(-1, 1, 0), Blocks.CHEST.defaultBlockState()
            .setValue(net.minecraft.world.level.block.ChestBlock.FACING, Direction.WEST), 3);

        // Small flower garden in front
        BlockPos garden = center.offset(0, 0, 3);
        if (level.getBlockState(garden).isAir() || level.getBlockState(garden).canBeReplaced()) {
            level.setBlock(garden, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
            level.setBlock(garden.above(), Blocks.POPPY.defaultBlockState(), 3);
        }

        return true;
    }

    private static Direction getInwardDirection(int dx, int dz) {
        if (dx == -2) return Direction.EAST;
        if (dx == 2) return Direction.WEST;
        if (dz == -2) return Direction.SOUTH;
        return Direction.NORTH;
    }
}
