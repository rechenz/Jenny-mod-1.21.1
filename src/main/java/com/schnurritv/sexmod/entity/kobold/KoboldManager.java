package com.schnurritv.sexmod.entity.kobold;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * KoboldManager: Manages tribes, tasks, and territories.
 * Uses SavedData for persistence (replaces old WorldSavedData).
 */
public class KoboldManager {

    // ── Tribe Data ──
    static final Map<UUID, TribeData> TRIBES = new HashMap<>();

    // ── Bed tracking (KoboldEntity → bed positions) ──
    static final Map<Integer, BlockPos[]> BED_MAP = new HashMap<>();

    // ── SavedData wrapper (stub — in-memory only for now) ──
    public static void init(ServerLevel level) {
        // Future: persist tribe data via SavedData
    }

    // ── Tribe Data Inner Class ──
    public static class TribeData {
        public final UUID tribeId;
        public EyeAndKoboldColor color;
        public UUID ownerId; // owner Player UUID
        public KoboldEntity leader; // tribe leader
        public final List<KoboldEntity> members = new ArrayList<>();
        public final Set<BlockPos> beds = new HashSet<>(); // bed positions
        public final Set<BlockPos> claimedBlocks = new HashSet<>(); // territory/chest positions
        public final List<KoboldTask> tasks = new ArrayList<>();
        public KoboldWorkMode workMode = KoboldWorkMode.ACTIVE;

        public TribeData(UUID tribeId, EyeAndKoboldColor color) {
            this.tribeId = tribeId;
            this.color = color;
        }

        public void addMember(KoboldEntity kobold) {
            if (!members.contains(kobold)) {
                members.add(kobold);
            }
        }

        public void removeMember(KoboldEntity kobold) {
            members.remove(kobold);
            if (leader == kobold) {
                leader = members.isEmpty() ? null : members.get(0);
            }
        }

        public boolean isTame() {
            return ownerId != null;
        }

        @Nullable
        public Player getOwner(Level level) {
            if (ownerId == null) return null;
            return level.getPlayerByUUID(ownerId);
        }

        public KoboldWorkMode determineWorkMode(Level level) {
            long time = level.getDayTime() % 24000;
            workMode = time < 12000 ? KoboldWorkMode.ACTIVE : KoboldWorkMode.REST;
            return workMode;
        }

        /** Find claims of a specific task type */
        @Nullable
        public KoboldTask findTaskByBlock(BlockPos pos) {
            for (KoboldTask t : tasks) {
                if (t.workBlocks.contains(pos)) return t;
            }
            return null;
        }

        public void removeCompletedTasks() {
            tasks.removeIf(KoboldTask::isComplete);
        }
    }

    // ── Tribe CRUD ──

    public static TribeData createTribe(UUID tribeId, EyeAndKoboldColor color) {
        TribeData tribe = new TribeData(tribeId, color);
        TRIBES.put(tribeId, tribe);
        return tribe;
    }

    @Nullable
    public static TribeData getTribe(UUID tribeId) {
        return TRIBES.get(tribeId);
    }

    public static boolean tribeExists(UUID tribeId) {
        return TRIBES.containsKey(tribeId);
    }

    public static void setOwner(UUID tribeId, UUID ownerUUID) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) tribe.ownerId = ownerUUID;
    }

    @Nullable
    public static UUID getOwner(UUID tribeId) {
        TribeData tribe = getTribe(tribeId);
        return tribe != null ? tribe.ownerId : null;
    }

    @Nullable
    public static Player getOwnerPlayer(UUID tribeId, Level level) {
        TribeData tribe = getTribe(tribeId);
        return tribe != null ? tribe.getOwner(level) : null;
    }

    @Nullable
    public static KoboldEntity getLeader(UUID tribeId) {
        TribeData tribe = getTribe(tribeId);
        if (tribe == null) return null;
        if (tribe.leader == null || !tribe.leader.isAlive()) {
            // Reassign
            for (KoboldEntity k : tribe.members) {
                if (k.isAlive()) {
                    tribe.leader = k;
                    break;
                }
            }
        }
        return tribe.leader;
    }

    public static void setLeader(UUID tribeId, KoboldEntity kobold) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) tribe.leader = kobold;
    }

    public static void addMember(UUID tribeId, KoboldEntity kobold) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) {
            tribe.addMember(kobold);
            kobold.tribeId = tribeId;
            kobold.tribeColor = tribe.color;
        } else {
            // Tribe no longer exists — clear kobold's tribeId to avoid stale state
            kobold.tribeId = null;
            kobold.tribeColor = EyeAndKoboldColor.PURPLE;
        }
    }

    public static void removeMember(UUID tribeId, KoboldEntity kobold) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) {
            tribe.removeMember(kobold);
        }
    }

    @Nullable
    public static List<KoboldEntity> getMembers(UUID tribeId) {
        TribeData tribe = getTribe(tribeId);
        return tribe != null ? tribe.members : null;
    }

    public static EyeAndKoboldColor getColor(UUID tribeId) {
        TribeData tribe = getTribe(tribeId);
        return tribe != null ? tribe.color : EyeAndKoboldColor.PURPLE;
    }

    public static void setColor(UUID tribeId, EyeAndKoboldColor color) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) tribe.color = color;
    }

    public static KoboldWorkMode getWorkMode(UUID tribeId) {
        TribeData tribe = getTribe(tribeId);
        return tribe != null ? tribe.workMode : KoboldWorkMode.REST;
    }

    public static void setWorkMode(UUID tribeId, KoboldWorkMode mode) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) tribe.workMode = mode;
    }

    public static boolean isTame(UUID tribeId) {
        TribeData tribe = getTribe(tribeId);
        return tribe != null && tribe.isTame();
    }

    public static boolean isLeader(UUID tribeId, KoboldEntity kobold) {
        KoboldEntity leader = getLeader(tribeId);
        return leader != null && leader.getId() == kobold.getId();
    }

    // ── Bed tracking ──

    public static void registerBed(KoboldEntity kobold, BlockPos front, BlockPos back) {
        BED_MAP.put(kobold.getId(), new BlockPos[]{front, back});
    }

    @Nullable
    public static BlockPos[] getBed(KoboldEntity kobold) {
        return BED_MAP.get(kobold.getId());
    }

    public static void removeBed(KoboldEntity kobold) {
        BED_MAP.remove(kobold.getId());
    }

    public static boolean isBedOccupied(BlockPos pos) {
        for (BlockPos[] bed : BED_MAP.values()) {
            if (bed[0].equals(pos) || bed[1].equals(pos)) return true;
        }
        return false;
    }

    // ── Territory / Claimed blocks ──

    public static Set<BlockPos> getClaimedBlocks(UUID tribeId) {
        TribeData tribe = getTribe(tribeId);
        return tribe != null ? tribe.claimedBlocks : new HashSet<>();
    }

    public static void claimBlock(UUID tribeId, BlockPos pos) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) tribe.claimedBlocks.add(pos);
    }

    public static void unclaimBlock(UUID tribeId, BlockPos pos) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) tribe.claimedBlocks.remove(pos);
    }

    public static Set<BlockPos> getBeds(UUID tribeId) {
        TribeData tribe = getTribe(tribeId);
        return tribe != null ? tribe.beds : new HashSet<>();
    }

    public static void addBed(UUID tribeId, BlockPos pos) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) tribe.beds.add(pos);
    }

    // ── Task management ──

    public static void addTask(UUID tribeId, KoboldTask task) {
        TribeData tribe = getTribe(tribeId);
        if (tribe != null) tribe.tasks.add(task);
    }

    @Nullable
    public static KoboldTask findAvailableTask(UUID tribeId, KoboldEntity kobold) {
        TribeData tribe = getTribe(tribeId);
        if (tribe == null) return null;
        tribe.removeCompletedTasks();
        for (KoboldTask task : tribe.tasks) {
            if (task.canAssign() && !task.hasWorker(kobold)) {
                return task;
            }
        }
        return null;
    }

    @Nullable
    public static KoboldTask findKoboldTask(UUID tribeId, KoboldEntity kobold) {
        TribeData tribe = getTribe(tribeId);
        if (tribe == null) return null;
        for (KoboldTask task : tribe.tasks) {
            if (task.hasWorker(kobold)) return task;
        }
        return null;
    }

    public static void removeWorkerFromTask(UUID tribeId, KoboldEntity kobold) {
        TribeData tribe = getTribe(tribeId);
        if (tribe == null) return;
        for (KoboldTask task : tribe.tasks) {
            if (task.hasWorker(kobold)) {
                task.removeWorker(kobold);
                return;
            }
        }
    }

    // ── Combat helpers ──

    /** Get all hostile entities within range of the tribe leader */
    public static Set<LivingEntity> getNearbyHostiles(UUID tribeId, Level level) {
        Set<LivingEntity> hostiles = new HashSet<>();
        KoboldEntity leader = getLeader(tribeId);
        if (leader == null) return hostiles;

        double range = 30.0;
        for (var entity : level.getEntitiesOfClass(LivingEntity.class,
                leader.getBoundingBox().inflate(range))) {
            if (entity instanceof Monster && entity.isAlive() && entity != leader) {
                hostiles.add(entity);
            }
        }
        return hostiles;
    }

    /** Clear all tribe data (for world unload) */
    public static void clear() {
        TRIBES.clear();
        BED_MAP.clear();
    }
}
