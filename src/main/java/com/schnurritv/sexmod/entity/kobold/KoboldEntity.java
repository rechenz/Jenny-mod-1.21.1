package com.schnurritv.sexmod.entity.kobold;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import com.schnurritv.sexmod.entity.kobold.KoboldManager.TribeData;

import java.util.*;

/**
 * KoboldEntity — fully featured with tribe, work, sleep, breeding, taming, and combat.
 * P0 port from old KoboldEntity (class ff / KoboldEntity in old_ref).
 */
public class KoboldEntity extends BaseGirlEntity {

    // ── Sync Data ──
    private static final EntityDataAccessor<Float> SCALE_DATA = SynchedEntityData.defineId(KoboldEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<String> KOBOLD_NAME = SynchedEntityData.defineId(KoboldEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> IS_TAME = SynchedEntityData.defineId(KoboldEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> TRIBE_COLOR_NAME = SynchedEntityData.defineId(KoboldEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(KoboldEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(KoboldEntity.class, EntityDataSerializers.BOOLEAN);

    // ── Tribe data (stored on entity for NBT persistence) ──
    @Nullable public UUID tribeId = null;
    public EyeAndKoboldColor tribeColor = EyeAndKoboldColor.PURPLE;
    public boolean isTamed = false;

    // ── State ──
    private int buffCooldown = 0;
    private int regenTimer = 0;
    private int matingPressTimer = -1; // for egg laying after MATING_PRESS_CUM
    private int idleSpeechTimer = 0;
    private int findBedCooldown = 0;
    private @Nullable BlockPos targetBedPos = null;

    // ── Work state ──
    private @Nullable BlockPos mineTarget = null;
    private int mineProgress = 0;
    private int mineBreakTimer = 24;
    private int stuckCheckTimer = 0;
    private Vec3 lastPos = Vec3.ZERO;

    public KoboldEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new com.schnurritv.sexmod.entity.ai.SexModMoveToTargetGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new com.schnurritv.sexmod.entity.ai.SexModFollowGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(3, new KoboldWorkGoal());
        this.goalSelector.addGoal(4, new KoboldFollowLeaderGoal());
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        // Combat — attack monsters near territory
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SCALE_DATA, 0.0f);
        builder.define(KOBOLD_NAME, KoboldName.values()[this.random.nextInt(KoboldName.values().length)].name());
        builder.define(IS_TAME, false);
        builder.define(TRIBE_COLOR_NAME, EyeAndKoboldColor.PURPLE.name());
        builder.define(IS_SLEEPING, false);
        builder.define(IS_LEADER, false);
    }

    // Attributes set via createAttributes() in EntityRegistry

    @Override
    public boolean needsHouse() { return false; }

    @Override
    public String getGirlName() { return "kobold"; }

    @Override
    public String getGeoFileName() { return "armored"; }

    @Override
    public String getNudeGeoFileName() { return "armored"; }

    @Override
    public boolean requiresBedForMissionary() { return false; }
    @Override
    public boolean requiresBedForDoggy() { return false; }

    // ── Getters / Setters ──

    public float getKoboldScale() {
        float raw = this.entityData.get(SCALE_DATA);
        if (raw <= 0) return 0.25f;
        return raw;
    }

    public void setKoboldScale(float scale) {
        this.entityData.set(SCALE_DATA, Math.max(0.01f, Math.min(0.25f, scale)));
    }

    public String getKoboldName() {
        return this.entityData.get(KOBOLD_NAME);
    }

    public void setKoboldName(String name) {
        this.entityData.set(KOBOLD_NAME, name);
    }

    public boolean isTame() {
        return this.entityData.get(IS_TAME);
    }

    public void setTame(boolean tame) {
        this.entityData.set(IS_TAME, tame);
        this.isTamed = tame;
    }

    public void setTribeColor(EyeAndKoboldColor color) {
        this.tribeColor = color;
        this.entityData.set(TRIBE_COLOR_NAME, color.name());
    }

    public EyeAndKoboldColor getTribeColor() {
        return EyeAndKoboldColor.safeValueOf(this.entityData.get(TRIBE_COLOR_NAME));
    }

    public boolean isSleeping() {
        return this.entityData.get(IS_SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.entityData.set(IS_SLEEPING, sleeping);
    }

    public boolean isLeader() {
        return this.entityData.get(IS_LEADER);
    }

    public void setLeader(boolean leader) {
        this.entityData.set(IS_LEADER, leader);
    }

    public void setMasterUUID(String uuid) {
        this.entityData.set(MASTER_UUID, uuid);
    }

    public String getMasterUUID() {
        return this.entityData.get(MASTER_UUID);
    }

    // ── NBT ──

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putFloat("KoboldScale", getKoboldScale());
        compound.putString("KoboldName", getKoboldName());
        compound.putBoolean("KoboldTame", isTame());
        compound.putString("KoboldColor", tribeColor.name());
        compound.putBoolean("KoboldSleeping", isSleeping());
        compound.putBoolean("KoboldLeader", isLeader());
        if (tribeId != null) {
            compound.putUUID("TribeId", tribeId);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("KoboldScale")) setKoboldScale(compound.getFloat("KoboldScale"));
        if (compound.contains("KoboldName")) setKoboldName(compound.getString("KoboldName"));
        if (compound.contains("KoboldTame")) {
            boolean t = compound.getBoolean("KoboldTame");
            setTame(t);
            this.isTamed = t;
        }
        if (compound.contains("KoboldColor")) {
            tribeColor = EyeAndKoboldColor.safeValueOf(compound.getString("KoboldColor"));
            this.entityData.set(TRIBE_COLOR_NAME, tribeColor.name());
        }
        if (compound.contains("KoboldSleeping")) setSleeping(compound.getBoolean("KoboldSleeping"));
        if (compound.contains("KoboldLeader")) setLeader(compound.getBoolean("KoboldLeader"));
        if (compound.hasUUID("TribeId")) {
            this.tribeId = compound.getUUID("TribeId");
            // Re-register with manager on load
            if (this.tribeId != null && !this.level().isClientSide) {
                KoboldManager.addMember(this.tribeId, this);
            }
        }
    }

    // ── Animation mapping ──

    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            // Missionary → mating_press (on-back breeding)
            case MISSIONARY_START  -> "animation." + p + ".mating_press_start";
            case MISSIONARY_SLOW   -> "animation." + p + ".mating_press_soft";
            case MISSIONARY_FAST   -> "animation." + p + ".mating_press_hard";
            case MISSIONARY_CUM    -> "animation." + p + ".mating_press_cum";
            // Blowjob
            case BLOWJOBINTRO      -> "animation." + p + ".blowjobStart";
            case BLOWJOBSUCK       -> "animation." + p + ".blowjobSlowL";
            case BLOWJOBTHRUST     -> "animation." + p + ".blowjobFast";
            case BLOWJOBCUM        -> "animation." + p + ".blowjobCum";
            // Doggy → anal
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".analStart";
            case DOGGYSLOW         -> "animation." + p + ".analSoft";
            case DOGGYFAST         -> "animation." + p + ".analHard";
            case DOGGYCUM          -> "animation." + p + ".analCum";
            // Boobjob → mating_press reuse
            case PAIZURI_START     -> "animation." + p + ".mating_press_start";
            case PAIZURI_SLOW      -> "animation." + p + ".mating_press_soft";
            case PAIZURI_FAST      -> "animation." + p + ".mating_press_hard";
            case PAIZURI_CUM       -> "animation." + p + ".mating_press_cum";
            default -> "animation." + p + ".idle";
        };
    }

    // ── Interaction ──

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);

        // Check if this is a tame staff (Stick)
        if (held.getItem() == Items.STICK) {
            if (this.level().isClientSide) {
                return InteractionResult.SUCCESS;
            }
            UUID ownerId = player.getUUID();
            if (!this.isTame()) {
                // Tame this kobold — create a tribe if doesn't have one
                if (tribeId == null) {
                    // Check if player already has a tribe
                    UUID existingTribe = findPlayerTribe(player);
                    if (existingTribe != null) {
                        tribeId = existingTribe;
                    } else {
                        tribeId = UUID.randomUUID();
                        EyeAndKoboldColor color = EyeAndKoboldColor.values()[random.nextInt(EyeAndKoboldColor.values().length)];
                        KoboldManager.createTribe(tribeId, color);
                        KoboldManager.setOwner(tribeId, ownerId);
                    }
                }
                KoboldManager.addMember(tribeId, this);
                KoboldManager.setOwner(tribeId, ownerId);
                setTame(true);
                setMasterUUID(ownerId.toString());
                player.displayClientMessage(Component.literal("<" + getKoboldName() + "> Tamed!"), false);
                return InteractionResult.CONSUME;
            } else {
                // Already tamed — open GUI
                return super.mobInteract(player, hand);
            }
        }

        // Name tag support
        if (held.getItem() == Items.NAME_TAG && held.has(DataComponents.CUSTOM_NAME)) {
            if (!this.level().isClientSide) {
                setKoboldName(held.getHoverName().getString());
                if (!player.isCreative()) held.shrink(1);
                player.displayClientMessage(Component.literal("Kobold renamed to " + getKoboldName()), false);
            }
            return InteractionResult.SUCCESS;
        }

        // Untamed kobold: require payment (3 gold ingots + iron pickaxe) to interact
        if (!this.isTame()) {
            if (this.level().isClientSide) {
                // Client side: still open interaction but will show payment screen
                return super.mobInteract(player, hand);
            }
            int goldCount = 0;
            boolean hasPickaxe = false;
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack item = player.getInventory().getItem(i);
                if (item.getItem() == Items.GOLD_INGOT) goldCount += item.getCount();
                if (item.getItem() == Items.IRON_PICKAXE) hasPickaxe = true;
            }
            if (goldCount >= 3 && hasPickaxe) {
                // Consume payment
                int toRemove = 3;
                for (int i = 0; i < player.getInventory().getContainerSize() && toRemove > 0; i++) {
                    ItemStack item = player.getInventory().getItem(i);
                    if (item.getItem() == Items.GOLD_INGOT) {
                        int remove = Math.min(toRemove, item.getCount());
                        item.shrink(remove);
                        toRemove -= remove;
                    }
                }
                player.displayClientMessage(Component.literal("<" + getKoboldName() + "> Payment accepted!"), false);
                return InteractionResult.SUCCESS;
            } else {
                player.displayClientMessage(Component.literal("Need 3 Gold Ingots + Iron Pickaxe to interact with this kobold!"), false);
                return InteractionResult.FAIL;
            }
        }

        // Tamed kobold: free interaction
        return super.mobInteract(player, hand);
    }

    // ── Tick ──

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            return;
        }

        ServerLevel serverLevel = (ServerLevel) this.level();

        // ── Tame checks ──
        if (isTame() && tribeId != null) {
            // Ensure registered in manager
            KoboldManager.addMember(tribeId, this);

            // Sync work mode with day/night
            TribeData tribe = KoboldManager.getTribe(tribeId);
            if (tribe != null) {
                tribe.determineWorkMode(level());

                // Active/rest switching
                if (tribe.workMode == KoboldWorkMode.REST && !isSleeping()) {
                    findBedAndSleep();
                } else if (tribe.workMode == KoboldWorkMode.ACTIVE && isSleeping()) {
                    wakeUp();
                }
            }

            // ── Buff aura (dig speed for owner) ──
            if (buffCooldown > 0) {
                buffCooldown--;
            } else {
                buffCooldown = 200;
                String uuid = this.getMasterUUID();
                if (!uuid.isEmpty()) {
                    try {
                        Player owner = this.level().getPlayerByUUID(UUID.fromString(uuid));
                        if (owner != null && owner.distanceToSqr(this) < 64) {
                            owner.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1, false, false));
                        }
                    } catch (Exception ignored) {}
                }
            }

            // ── Idle speech ──
            if (idleSpeechTimer > 0) idleSpeechTimer--;
            if (idleSpeechTimer <= 0 && tribe != null && tribe.workMode == KoboldWorkMode.ACTIVE) {
                Player owner = KoboldManager.getOwnerPlayer(tribeId, level());
                if (owner != null && distanceToSqr(owner) < 100 && !isSleeping()
                    && !this.entityData.get(IS_LOCKED)) {
                    idleSpeechTimer = random.nextInt(600) + 600;
                    owner.displayClientMessage(
                        Component.literal("<" + getKoboldName() + "> Hey master!"), false);
                }
            }

            // ── Combat: attack hostiles near territory ──
            if (tribe != null && tribe.workMode == KoboldWorkMode.ACTIVE && this.tickCount % 40 == 0) {
                Set<LivingEntity> hostiles = KoboldManager.getNearbyHostiles(tribeId, level());
                if (!hostiles.isEmpty()) {
                    LivingEntity target = hostiles.stream()
                        .filter(e -> distanceToSqr(e) < 900)
                        .min(Comparator.comparingDouble(this::distanceToSqr))
                        .orElse(null);
                    if (target != null) {
                        this.setTarget(target);
                    }
                }
            }

            // ── Mating press → egg laying ──
            if (matingPressTimer >= 0) {
                matingPressTimer++;
                if (matingPressTimer >= 132) {
                    layEgg();
                    matingPressTimer = -1;
                }
            }

            // ── Health regen ──
            if (!isSleeping() && this.getHealth() < this.getMaxHealth()) {
                if (regenTimer++ >= 100) {
                    this.heal(2.0f);
                    regenTimer = 0;
                }
            } else {
                regenTimer = 0;
            }
        }
    }

    // ── Egg laying ──

    @Override
    protected void handleAnimationSequencing() {
        // Check if this is a CUM ending that should trigger egg laying
        SexModAnimation current = getSexModAnimation();
        if ((current == SexModAnimation.MISSIONARY_CUM || current == SexModAnimation.PAIZURI_CUM)
            && "mating".equals(getAnimationPrefix())) {
            int length = getAnimationTickLength(current);
            int ticks = this.entityData.get(ANIMATION_TICKS) + 1;
            if (length > 0 && ticks >= length) {
                // CUM just finished → lay egg before parent cleans up scene
                if (tribeId != null && isTame()) {
                    layEgg();
                }
            }
        }
        super.handleAnimationSequencing();
    }

    private void layEgg() {
        if (tribeId == null) return;

        KoboldEggItem eggItem = (KoboldEggItem) KoboldEntityRegistry.KOBOLD_EGG_ITEM.get();
        ItemStack eggStack = new ItemStack(eggItem, 1);
        CompoundTag tag = new CompoundTag();
        tag.putUUID(KoboldEggItem.TRIBE_ID_TAG, tribeId);
        tag.putString(KoboldEggItem.TRIBE_COLOR_TAG, tribeColor.name());
        // Store in item component
        eggStack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        // Give to owner
        UUID ownerUUID = KoboldManager.getOwner(tribeId);
        if (ownerUUID != null) {
            Player owner = level().getPlayerByUUID(ownerUUID);
            if (owner != null) {
                if (!owner.getInventory().add(eggStack)) {
                    spawnAtLocation(eggStack, 1.0f);
                }
            }
        }
    }

    // ── Sleep system ──

    private void findBedAndSleep() {
        if (tribeId == null) return;
        if (findBedCooldown-- > 0) return;
        findBedCooldown = 100;

        TribeData tribe = KoboldManager.getTribe(tribeId);
        if (tribe == null) return;

        // First check known beds
        for (BlockPos bedPos : tribe.beds) {
            BlockState state = level().getBlockState(bedPos);
            if (state.getBlock() instanceof BedBlock && state.getValue(BedBlock.PART) == BedPart.HEAD) {
                if (!KoboldManager.isBedOccupied(bedPos)) {
                    targetBedPos = bedPos;
                    break;
                }
            }
        }

        if (targetBedPos == null) {
            // Scan for beds nearby
            for (BlockPos pos : BlockPos.betweenClosed(
                this.blockPosition().offset(-10, -3, -10),
                this.blockPosition().offset(10, 3, 10))) {
                BlockState state = level().getBlockState(pos);
                if (state.getBlock() instanceof BedBlock && state.getValue(BedBlock.PART) == BedPart.HEAD) {
                    if (!KoboldManager.isBedOccupied(pos)) {
                        targetBedPos = pos.immutable();
                        tribe.beds.add(targetBedPos);
                        break;
                    }
                }
            }
        }

        if (targetBedPos != null) {
            double dist = distanceToSqr(targetBedPos.getX() + 0.5, targetBedPos.getY(), targetBedPos.getZ() + 0.5);
            if (dist < 4.0) {
                // At bed — start sleeping
                setSleeping(true);
                this.getNavigation().stop();
                KoboldManager.registerBed(this, targetBedPos, targetBedPos.relative(
                    level().getBlockState(targetBedPos).getValue(BedBlock.FACING).getOpposite()));
            } else {
                // Move to bed
                this.getNavigation().moveTo(targetBedPos.getX() + 0.5, targetBedPos.getY(),
                    targetBedPos.getZ() + 0.5, 0.35);
            }
        }
    }

    private void wakeUp() {
        setSleeping(false);
        KoboldManager.removeBed(this);
        targetBedPos = null;
    }

    // ── Work system ──

    /** Attempt to find and execute work (tree felling or mining) */
    public boolean tryWork() {
        if (tribeId == null) return false;
        TribeData tribe = KoboldManager.getTribe(tribeId);
        if (tribe == null) return false;
        if (tribe.workMode != KoboldWorkMode.ACTIVE) return false;
        if (isSleeping()) return false;
        if (this.entityData.get(IS_LOCKED)) return false;

        // Check if already assigned a task
        KoboldTask existing = KoboldManager.findKoboldTask(tribeId, this);
        if (existing != null) {
            return executeTask(existing);
        }

        // Find an available task
        KoboldTask task = KoboldManager.findAvailableTask(tribeId, this);
        if (task != null && task.assignWorker(this)) {
            return executeTask(task);
        }

        // Scan for new trees or ores
        if (this.tickCount % 40 == 0) {
            return scanForWork(tribe);
        }

        return false;
    }

    private boolean scanForWork(TribeData tribe) {
        // Scan for logs (trees)
        for (int dx = -15; dx <= 15; dx += 2) {
            for (int dz = -15; dz <= 15; dz += 2) {
                for (int dy = -5; dy <= 5; dy += 1) {
                    BlockPos pos = this.blockPosition().offset(dx, dy, dz);
                    BlockState state = level().getBlockState(pos);
                    if (isLogBlock(state)) {
                        KoboldTask task = KoboldTask.detectAndCreateTreeTask(level(), pos, tribeId);
                        if (task != null && !task.isComplete()) {
                            tribe.tasks.add(task);
                            if (task.assignWorker(this)) {
                                return executeTask(task);
                            }
                            return false;
                        }
                    }
                }
            }
        }

        // Scan for ores (simplified)
        for (int dx = -15; dx <= 15; dx += 2) {
            for (int dz = -15; dz <= 15; dz += 2) {
                for (int dy = -5; dy <= 5; dy += 1) {
                    BlockPos pos = this.blockPosition().offset(dx, dy, dz);
                    BlockState state = level().getBlockState(pos);
                    if (isOreBlock(state)) {
                        Set<BlockPos> oreSet = new HashSet<>();
                        oreSet.add(pos.immutable());
                        KoboldTask mineTask = new KoboldTask(pos.immutable(), KoboldTask.TaskType.MINE, oreSet);
                        tribe.tasks.add(mineTask);
                        mineTask.assignWorker(this);
                        return executeTask(mineTask);
                    }
                }
            }
        }

        return false;
    }

    private boolean executeTask(KoboldTask task) {
        switch (task.getTaskType()) {
            case FALL_TREE:
                return executeTreeTask(task);
            case MINE:
                return executeMineTask(task);
        }
        return false;
    }

    private boolean executeTreeTask(KoboldTask task) {
        if (task.getWorkBlocks().isEmpty()) return false;

        // Find nearest log block
        BlockPos nearest = null;
        double nearestDist = Double.MAX_VALUE;
        for (BlockPos bp : task.getWorkBlocks()) {
            double d = distanceToSqr(bp.getX(), bp.getY(), bp.getZ());
            if (d < nearestDist) {
                nearestDist = d;
                nearest = bp;
            }
        }

        if (nearest == null) return false;

        // Move close to block
        if (nearestDist > 9.0) {
            this.getNavigation().moveTo(nearest.getX(), nearest.getY(), nearest.getZ(), 0.35);
            return true;
        }

        // At block — break it
        if (nearestDist <= 4.0) {
            level().destroyBlock(nearest, true);
            task.getWorkBlocks().remove(nearest);
        }
        return true;
    }

    private boolean executeMineTask(KoboldTask task) {
        if (task.getWorkBlocks().isEmpty()) return false;

        if (mineTarget == null || !task.getWorkBlocks().contains(mineTarget)) {
            mineTarget = task.getWorkBlocks().iterator().next();
            mineProgress = 0;
        }

        double dist = distanceToSqr(mineTarget.getX(), mineTarget.getY(), mineTarget.getZ());
        if (dist > 9.0) {
            this.getNavigation().moveTo(mineTarget.getX(), mineTarget.getY(), mineTarget.getZ(), 0.35);
            return true;
        }

        // Mining in progress
        if (mineBreakTimer <= 0) {
            level().destroyBlock(mineTarget, true);
            task.getWorkBlocks().remove(mineTarget);
            mineTarget = null;
            mineBreakTimer = 24;
            return true;
        }

        // Face the block
        this.getNavigation().stop();
        mineBreakTimer--;
        return true;
    }

    // ── Helpers ──

    private static boolean isLogBlock(BlockState state) {
        Block b = state.getBlock();
        return b == Blocks.OAK_LOG || b == Blocks.SPRUCE_LOG || b == Blocks.BIRCH_LOG
            || b == Blocks.JUNGLE_LOG || b == Blocks.ACACIA_LOG || b == Blocks.DARK_OAK_LOG
            || b == Blocks.MANGROVE_LOG || b == Blocks.CHERRY_LOG;
    }

    private static boolean isOreBlock(BlockState state) {
        Block b = state.getBlock();
        return b == Blocks.COAL_ORE || b == Blocks.IRON_ORE || b == Blocks.COPPER_ORE
            || b == Blocks.GOLD_ORE || b == Blocks.DIAMOND_ORE || b == Blocks.EMERALD_ORE
            || b == Blocks.LAPIS_ORE || b == Blocks.REDSTONE_ORE
            || b == Blocks.DEEPSLATE_COAL_ORE || b == Blocks.DEEPSLATE_IRON_ORE
            || b == Blocks.DEEPSLATE_COPPER_ORE || b == Blocks.DEEPSLATE_GOLD_ORE
            || b == Blocks.DEEPSLATE_DIAMOND_ORE || b == Blocks.DEEPSLATE_EMERALD_ORE
            || b == Blocks.DEEPSLATE_LAPIS_ORE || b == Blocks.DEEPSLATE_REDSTONE_ORE;
    }

    @Nullable
    private UUID findPlayerTribe(Player player) {
        UUID pid = player.getUUID();
        for (var entry : KoboldManager.TRIBES.entrySet()) {
            if (pid.equals(entry.getValue().ownerId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    // ── Custom Goals ──

    class KoboldWorkGoal extends Goal {
        int cooldown = 0;

        @Override
        public boolean canUse() {
            if (cooldown-- > 0) return false;
            cooldown = 20;
            return isTame() && tribeId != null && !isSleeping() && !entityData.get(IS_LOCKED);
        }

        @Override
        public void tick() {
            if (tribeId == null) return;
            TribeData tribe = KoboldManager.getTribe(tribeId);
            if (tribe == null) return;
            if (tribe.workMode != KoboldWorkMode.ACTIVE) return;
            tryWork();
        }
    }

    class KoboldFollowLeaderGoal extends Goal {
        @Override
        public boolean canUse() {
            if (tribeId == null) return false;
            if (isLeader()) return false;
            if (isSleeping()) return false;
            if (entityData.get(IS_LOCKED)) return false;
            KoboldEntity leader = KoboldManager.getLeader(tribeId);
            return leader != null && leader != KoboldEntity.this && distanceToSqr(leader) > 400;
        }

        @Override
        public void tick() {
            KoboldEntity leader = KoboldManager.getLeader(tribeId);
            if (leader == null) return;
            if (distanceToSqr(leader) > 400) {
                getNavigation().moveTo(leader, 0.5);
            }
        }

        @Override
        public boolean canContinueToUse() {
            KoboldEntity leader = KoboldManager.getLeader(tribeId);
            return leader != null && distanceToSqr(leader) > 100;
        }
    }

    // ── Public API ──

    /** Call from scene manager when MATING_PRESS_CUM animation completes */
    public void triggerEggLaying() {
        if (tribeId != null && isTame()) {
            layEgg();
        }
    }

    @Override
    public List<String> getAvailableActions() {
        List<String> actions = super.getAvailableActions();
        return actions;
    }

    @Override
    public boolean supportsScene(String sceneName) {
        return true;
    }

    @Override
    public boolean showStandardMissionary() { return true; }
    @Override
    public boolean showStandardBlowjob() { return true; }
    @Override
    public boolean showStandardDoggy() { return true; }
    @Override
    public boolean showStandardBoobjob() { return true; }
}
