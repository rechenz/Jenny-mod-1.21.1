package com.schnurritv.sexmod.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.nbt.CompoundTag;
import java.util.EnumSet;

public class ShipSeekGoal extends Goal {
    private final PathfinderMob mob;
    private BlockPos homePos = null;
    private boolean foundHome = false;
    private int searchCooldown = 0;
    private int wanderCooldown = 0;
    private static final int SEARCH_RADIUS = 64;
    private static final int STAY_RADIUS = 24;

    public ShipSeekGoal(PathfinderMob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public void setHomePos(BlockPos pos) { this.homePos = pos; this.foundHome = pos != null; }
    public BlockPos getHomePos() { return homePos; }
    public boolean hasFoundHome() { return foundHome; }

    public void loadFromNBT(CompoundTag tag) {
        if (tag.contains("ShipHomeX")) {
            homePos = new BlockPos(tag.getInt("ShipHomeX"), tag.getInt("ShipHomeY"), tag.getInt("ShipHomeZ"));
            foundHome = true;
        }
    }

    public CompoundTag saveToNBT() {
        CompoundTag tag = new CompoundTag();
        if (homePos != null) {
            tag.putInt("ShipHomeX", homePos.getX());
            tag.putInt("ShipHomeY", homePos.getY());
            tag.putInt("ShipHomeZ", homePos.getZ());
        }
        return tag;
    }

    @Override
    public boolean canUse() {
        if (!foundHome) {
            if (searchCooldown > 0) { searchCooldown--; return false; }
            return true;
        }
        double dist = homePos.distSqr(mob.blockPosition());
        // Only pathfind home if more than STAY_RADIUS blocks away
        if (dist <= STAY_RADIUS * STAY_RADIUS) return false;
        if (wanderCooldown > 0) { wanderCooldown--; return false; }
        return true;
    }

    @Override
    public void start() {
        if (!foundHome) {
            findNearbyShip();
            searchCooldown = 200; // retry in 10 seconds
            return;
        }
        // Move toward home position
        BlockPos target = findGroundNear(homePos, 8);
        mob.getNavigation().moveTo(target.getX() + 0.5, target.getY(), target.getZ() + 0.5, 1.0);
        wanderCooldown = 100;
    }

    @Override
    public boolean canContinueToUse() {
        return false; // one-shot per activation, re-check in canUse
    }

    private void findNearbyShip() {
        Level level = mob.level();
        BlockPos mobPos = mob.blockPosition();
        for (int x = -SEARCH_RADIUS; x <= SEARCH_RADIUS; x += 6) {
            for (int z = -SEARCH_RADIUS; z <= SEARCH_RADIUS; z += 6) {
                BlockPos check = mobPos.offset(x, 0, z);
                BlockPos surface = level.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, check);
                if (Math.abs(surface.getY() - mobPos.getY()) > 15) continue;
                if (isWoodBlock(level, surface) && isNearWater(level, surface)) {
                    homePos = surface.immutable();
                    foundHome = true;
                    return;
                }
            }
        }
        // Fallback: stay near current position near water
        BlockPos waterNearby = findWaterNear(mobPos);
        homePos = waterNearby != null ? waterNearby : mobPos;
        foundHome = true;
    }

    private BlockPos findGroundNear(BlockPos center, int radius) {
        Level level = mob.level();
        BlockPos result = center;
        double best = Double.MAX_VALUE;
        for (int x = -radius; x <= radius; x += 2) {
            for (int z = -radius; z <= radius; z += 2) {
                BlockPos check = center.offset(x, 0, z);
                BlockPos surface = level.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING, check);
                double dist = surface.distSqr(center);
                if (dist < best) {
                    best = dist;
                    result = surface;
                }
            }
        }
        return result;
    }

    private boolean isWoodBlock(Level level, BlockPos pos) {
        var state = level.getBlockState(pos);
        return state.is(BlockTags.PLANKS) || state.is(BlockTags.LOGS) || state.is(BlockTags.WOODEN_STAIRS)
            || state.is(BlockTags.WOODEN_SLABS) || state.is(BlockTags.WOODEN_FENCES) || state.is(BlockTags.WOODEN_DOORS)
            || state.is(BlockTags.WOODEN_TRAPDOORS);
    }

    private boolean isNearWater(Level level, BlockPos pos) {
        for (int dx = -4; dx <= 4; dx++) {
            for (int dz = -4; dz <= 4; dz++) {
                for (int dy = -1; dy <= 0; dy++) {
                    if (level.getBlockState(pos.offset(dx, dy, dz)).is(Blocks.WATER)) return true;
                }
            }
        }
        return false;
    }

    private BlockPos findWaterNear(BlockPos center) {
        Level level = mob.level();
        for (int x = -32; x <= 32; x += 4) {
            for (int z = -32; z <= 32; z += 4) {
                BlockPos check = center.offset(x, 0, z);
                BlockPos surface = level.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, check);
                if (isNearWater(level, surface)) return surface;
            }
        }
        return null;
    }
}
