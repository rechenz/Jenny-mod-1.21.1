package com.schnurritv.sexmod.entity.kobold;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;

import java.util.*;

/**
 * A task assigned to one or more Kobolds.
 * Supports FALL_TREE and MINE task types.
 */
public class KoboldTask {
    public enum TaskType {
        FALL_TREE(1),  // 1 kobold per tree
        MINE(3);       // 3 kobolds per mining task

        final int maxWorkers;
        TaskType(int max) { this.maxWorkers = max; }
        public int getMaxWorkers() { return maxWorkers; }
    }

    /** Origin block (root of the work area) */
    BlockPos origin;
    /** Task type */
    TaskType type;
    /** Blocks to be processed (ore/log positions) */
    final Set<BlockPos> workBlocks = new HashSet<>();
    /** Workers assigned to this task */
    final List<KoboldEntity> workers = new ArrayList<>();

    public KoboldTask(BlockPos origin, TaskType type, Set<BlockPos> workBlocks) {
        this.origin = origin;
        this.type = type;
        if (workBlocks != null) this.workBlocks.addAll(workBlocks);
    }

    public BlockPos getOrigin() { return origin; }
    public TaskType getTaskType() { return type; }
    public Set<BlockPos> getWorkBlocks() { return workBlocks; }
    public List<KoboldEntity> getWorkers() { return workers; }

    public boolean canAssign() {
        return workers.size() < type.maxWorkers;
    }

    public boolean assignWorker(KoboldEntity kobold) {
        if (!canAssign()) return false;
        workers.add(kobold);
        return true;
    }

    public void removeWorker(KoboldEntity kobold) {
        workers.remove(kobold);
    }

    public boolean hasWorker(KoboldEntity kobold) {
        return workers.contains(kobold);
    }

    public boolean isComplete() {
        return workBlocks.isEmpty();
    }

    public void clearWorkers() {
        workers.clear();
    }

    // ── Static helpers (transplanted from old bs_class97) ──

    /** Detect a tree at given log position, create FALL_TREE task */
    public static KoboldTask detectAndCreateTreeTask(Level world, BlockPos logPos, UUID tribeID) {
        // Find bottom of tree
        BlockPos bottom = logPos;
        while (!isSolidBelow(world, bottom)) {
            bottom = bottom.below();
        }
        // Find top of tree
        BlockPos top = logPos;
        while (!isAirAbove(world, top)) {
            top = top.above();
        }

        Set<BlockPos> treeBlocks = new HashSet<>();
        int height = top.getY() - bottom.getY();
        for (int i = 0; i <= height; i++) {
            treeBlocks.add(bottom.offset(0, i, 0));
        }

        // Find surrounding logs (breadth-first)
        Set<BlockPos> surrounding = findSurroundingLogs(world, bottom, new HashSet<>());
        for (BlockPos bp : surrounding) {
            if (bp.getX() == bottom.getX() && bp.getZ() == bottom.getZ()) continue;
            treeBlocks.add(bp);
        }

        // Remove blocks already claimed by other tasks of this tribe
        Set<BlockPos> existing = KoboldManager.getClaimedBlocks(tribeID);
        treeBlocks.removeAll(existing);

        KoboldTask task = new KoboldTask(bottom, TaskType.FALL_TREE, treeBlocks);
        return task;
    }

    private static boolean isSolidBelow(Level world, BlockPos pos) {
        BlockState state = world.getBlockState(pos.below());
        return !(state.isAir()) && state.getBlock() != net.minecraft.world.level.block.Blocks.OAK_LOG;
    }

    private static boolean isAirAbove(Level world, BlockPos pos) {
        BlockState state = world.getBlockState(pos.above());
        return state.isAir();
    }

    private static Set<BlockPos> findSurroundingLogs(Level world, BlockPos pos, Set<BlockPos> visited) {
        if (visited.contains(pos)) return new HashSet<>();
        visited.add(pos);

        Set<BlockPos> result = new HashSet<>();
        if (world.getBlockState(pos).getBlock() != net.minecraft.world.level.block.Blocks.OAK_LOG
            && world.getBlockState(pos).getBlock() != net.minecraft.world.level.block.Blocks.SPRUCE_LOG
            && world.getBlockState(pos).getBlock() != net.minecraft.world.level.block.Blocks.BIRCH_LOG
            && world.getBlockState(pos).getBlock() != net.minecraft.world.level.block.Blocks.JUNGLE_LOG
            && world.getBlockState(pos).getBlock() != net.minecraft.world.level.block.Blocks.ACACIA_LOG
            && world.getBlockState(pos).getBlock() != net.minecraft.world.level.block.Blocks.DARK_OAK_LOG
            && world.getBlockState(pos).getBlock() != net.minecraft.world.level.block.Blocks.MANGROVE_LOG
            && world.getBlockState(pos).getBlock() != net.minecraft.world.level.block.Blocks.CHERRY_LOG
        ) {
            return result;
        }

        result.add(pos);
        // Check neighbors
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue;
                    BlockPos neighbor = pos.offset(dx, dy, dz);
                    if (!visited.contains(neighbor)) {
                        result.addAll(findSurroundingLogs(world, neighbor, visited));
                    }
                }
            }
        }
        return result;
    }
}
