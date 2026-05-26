package com.schnurritv.sexmod.entity.goblin;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;
import java.util.*;

/**
 * Goblin — Cave-dwelling thief with full old-school mechanics.
 * Steals gold items, can be caught, can carry/throw players, and queen breeding system.
 */
public class GoblinEntity extends BaseGirlEntity {

    // ── Data sync ──
    private static final EntityDataAccessor<String>  DATA_STOLEN_OWNER  = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<ItemStack> DATA_STOLEN_ITEM = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Boolean> DATA_TAMED         = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String>  DATA_QUEEN_NAME    = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> DATA_IS_QUEEN      = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_PREGG0        = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_BODY_COLOR    = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_EYE_COLOR     = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.INT);

    // ── Gold items to steal (matching old behavior) ──
    private static final Set<Item> GOLD_ITEMS = new HashSet<>(Arrays.asList(
            Items.GOLDEN_SWORD, Items.GOLDEN_AXE, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_HOE,
            Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS,
            Items.GOLD_INGOT, Items.GOLD_NUGGET, Items.GOLD_BLOCK,
            Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE
    ));

    // ── Constants ──
    private static final int STEAL_COOLDOWN_TICKS   = 300;   // 15s
    private static final int CATCH_WAIT_TICKS        = 45;    // ~2.25s after pickup
    private static final int THROW_ANIM_TICKS        = 39;    // full throw anim length
    private static final int STAND_UP_TICKS          = 37;    // stand-up anim
    private static final int THROWN_LAND_TICKS       = 30;    // after landing on ground
    private static final int PREGNANCY_TICKS         = 8400;  // ~7min
    private static final int QUEEN_SPAWN_INTERVAL    = 32000; // ~26min
    private static final int VANISH_FADE_RATE        = 5;     // fade steps
    private static final int GUARD_DISMISS_TICKS     = 205;   // wait ticks before dismiss
    private static final int JUMP_ANIM_TICKS         = 26;    // breeding jump ticks
    private static final int COOLDOWN_TICKS          = 1200;  // breed msg cooldown
    private static final int PREGNANT_COOLDOWN       = 100;   // after birth wait ticks

    // ── Throne positions (relative offsets from old code) ──
    private static final Vec3 THRONE_OFFSET_0     = new Vec3(5.0, 1.0, 9.0);
    private static final Vec3 THRONE_OFFSET_90    = new Vec3(5.0, 1.0, 1.0);
    private static final Vec3 THRONE_OFFSET_180   = new Vec3(1.0, 1.0, 5.0);
    private static final Vec3 THRONE_OFFSET_NEG90 = new Vec3(1.0, 1.0, 5.0);
    private static final Vec3 JUMP_OFFSET_0       = new Vec3(-3.0, -1.0, -6.0);
    private static final Vec3 JUMP_OFFSET_90      = new Vec3(-6.0, -1.0, 3.0);
    private static final Vec3 JUMP_OFFSET_180     = new Vec3(3.0, -1.0, 6.0);
    private static final Vec3 JUMP_OFFSET_NEG90   = new Vec3(6.0, -1.0, -3.0);
    private static final Vec3[] GUARD_SPAWNS       = {new Vec3(0.0, -1.0, -4.0), new Vec3(1.0, -1.0, -3.0), new Vec3(-1.0, -1.0, -3.0)};
    // ── Instance state ──
    public float vanishAlpha = 1.0f;
    public Vec3 thronePos = Vec3.ZERO;
    public float throneRot = 0.0f;
    public boolean isThroneInitialized = false;
    private int stealCooldown = 0;
    private int stealCount = 0;
    private int catchTimer = -1;
    private int standUpTimer = 0;
    private int thrownLandTimer = 0;
    private int vanishTimer = -1;
    private int throwAnimTick = -1;
    private int pregnantTimer = -1;
    private long impregnationTick = -1;
    private int queenSpawnTimer = -1;
    private int guardDismissTimer = -1;
    private int jumpTimer = 0;
    private int breedingMsgCooldown = 0;
    private int queenGuardListTimer = -1;
    private List<UUID> guardUUIDs = new ArrayList<>();
    private List<GoblinEntity> guards = new ArrayList<>();

    // Saved stolen items list (NBT persistent)
    private final List<ItemStack> stolenItems = new ArrayList<>();
    // Stolen item to drop back to player
    private ItemStack lastStolenItem = ItemStack.EMPTY;

    @Nullable
    private UUID targetPlayerUUID = null;

    public GoblinEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    // ==================== Data init ====================

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_STOLEN_OWNER, "");
        builder.define(DATA_STOLEN_ITEM, ItemStack.EMPTY);
        builder.define(DATA_TAMED, false);
        builder.define(DATA_QUEEN_NAME, "");
        builder.define(DATA_IS_QUEEN, false);
        builder.define(DATA_PREGG0, false);
        builder.define(DATA_BODY_COLOR, 0);
        builder.define(DATA_EYE_COLOR, 0);
    }

    // ==================== BaseGirlEntity overrides ====================

    @Override
    public boolean needsHouse() { return false; }

    @Override
    public String getGirlName() { return "goblin"; }

    @Override
    public String getGeoFileName() { return "armored"; }

    @Override
    public String getNudeGeoFileName() { return "armored"; }

    @Override
    public boolean requiresBedForMissionary() { return false; }

    @Override
    public boolean requiresBedForDoggy() { return false; }

    @Override
    public boolean showStandardMissionary() { return true; }

    @Override
    public boolean showStandardDoggy() { return true; }

    @Override
    public boolean showStandardBlowjob() { return true; }

    @Override
    public boolean showStandardBoobjob() { return true; }

    // ==================== Animation mapping ====================

    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START   -> "animation." + p + ".breeding_intro_1";
            case MISSIONARY_SLOW    -> "animation." + p + ".breeding_slow_1l";
            case MISSIONARY_FAST    -> "animation." + p + ".breeding_fast_1s";
            case MISSIONARY_CUM     -> "animation." + p + ".breeding_cum_1";

            case BLOWJOBINTRO       -> "animation." + p + ".nelson_intro";
            case BLOWJOBSUCK        -> "animation." + p + ".nelson_slow";
            case BLOWJOBTHRUST      -> "animation." + p + ".nelson_fasts";
            case BLOWJOBCUM        -> "animation." + p + ".nelson_cum";

            case PAIZURI_START      -> "animation." + p + ".paizuri_start";
            case PAIZURI_SLOW       -> "animation." + p + ".paizuri_slow";
            case PAIZURI_FAST       -> "animation." + p + ".paizuri_fast";
            case PAIZURI_CUM        -> "animation." + p + ".paizuri_cum";

            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".catch_1personBj_idle";
            case DOGGYSLOW          -> "animation." + p + ".catch_1person";
            case DOGGYFAST          -> "animation." + p + ".catch_1personBj";
            case DOGGYCUM           -> "animation." + p + ".catch_3personBj";

            default -> "animation." + p + ".idle";
        };
    }

    // ==================== Steal system ====================

    public int getStealCount() { return stealCount; }

    public void setStealCount(int count) { this.stealCount = count; }

    public List<ItemStack> getStolenItems() { return stolenItems; }

    /**
     * Scan nearby players for gold items and steal one.
     */
    private void attemptSteal() {
        if (stealCooldown > 0) { stealCooldown--; return; }
        stealCooldown = STEAL_COOLDOWN_TICKS;

        // Don't steal if tamed or being interacted with
        if (entityData.get(DATA_TAMED)) return;

        String ownerUUID = entityData.get(DATA_STOLEN_OWNER);
        Player owner = null;
        if (ownerUUID != null && !ownerUUID.isEmpty()) {
            try { owner = level().getPlayerByUUID(UUID.fromString(ownerUUID)); } catch (Exception ignored) {}
        }

        for (Player p : level().players()) {
            if (p.distanceToSqr(this) > 16 || p.isCreative() || p.isSpectator()) continue;
            if (p == owner) continue;

            int slot = findGoldItem(p.getInventory());
            if (slot == -1) continue;

            ItemStack stack = p.getInventory().getItem(slot);
            ItemStack stolen = stack.split(1);
            if (!stolen.isEmpty()) {
                stealCount++;
                lastStolenItem = stolen.copy();
                stolenItems.add(stolen.copy());
                entityData.set(DATA_STOLEN_ITEM, stolen.copy());
                entityData.set(DATA_STOLEN_OWNER, p.getStringUUID());
                this.setItemSlot(EquipmentSlot.MAINHAND, stolen.copy());
                p.displayClientMessage(Component.literal("§cA goblin swiped something from you!"), true);
            }
            break;
        }
    }

    /**
     * Find a gold item slot in the player inventory. Matches old behavior: scans all slots not just hotbar.
     */
    private int findGoldItem(Inventory inv) {
        List<Integer> goldSlots = new ArrayList<>();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && GOLD_ITEMS.contains(stack.getItem())) {
                goldSlots.add(i);
            }
        }
        if (goldSlots.isEmpty()) return -1;
        return goldSlots.get(random.nextInt(goldSlots.size()));
    }

    /**
     * Return stolen items to the player (called when catching).
     */
    public void returnStolenItems(ServerPlayer player) {
        if (stealCount <= 0 && stolenItems.isEmpty()) return;

        // Drop all stolen items back
        for (ItemStack stack : stolenItems) {
            if (!stack.isEmpty()) {
                ItemEntity drop = new ItemEntity(level(), getX(), getY() + 0.5, getZ(), stack.copy());
                level().addFreshEntity(drop);
            }
        }

        // Also drop whatever is in main hand
        ItemStack held = getMainHandItem();
        if (!held.isEmpty()) {
            ItemEntity drop = new ItemEntity(level(), getX(), getY() + 0.5, getZ(), held.copy());
            level().addFreshEntity(drop);
        }

        stealCount = 0;
        stolenItems.clear();
        lastStolenItem = ItemStack.EMPTY;
        entityData.set(DATA_STOLEN_ITEM, ItemStack.EMPTY);
        entityData.set(DATA_STOLEN_OWNER, "");
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        player.displayClientMessage(Component.literal("§aThe goblin grudgingly returns your things..."), false);
    }

    /**
     * Called when the goblin is caught after running — start the catch animation chain.
     */
    public void startCatch(Player player) {
        targetPlayerUUID = player.getUUID();
        // Old behavior: set movement to bj, upright position
        entityData.set(DATA_STOLEN_OWNER, player.getStringUUID());
        this.getNavigation().stop();
        this.setDeltaMovement(Vec3.ZERO);
        // Animation state will handle switching to catch anims via goblin-specific hooks
    }

    // ==================== Interaction (right-click) ====================

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (level().isClientSide) return InteractionResult.SUCCESS;

        // Queen handling — right-click on queen while she's on throne does nothing old-version style
        if (entityData.get(DATA_IS_QUEEN)) {
            return InteractionResult.SUCCESS;
        }

        // Check if player is already carrying a goblin
        if (isPlayerCarryingGoblin(player.getUUID())) {
            player.displayClientMessage(Component.literal("you are already carrying a Goblin"), true);
            return InteractionResult.SUCCESS;
        }

        // Old behavior: if goblin is RUNNING, player needs to get close
        // For simplicity, if goblin has stolen items, show catch dialog
        if (stealCount > 0) {
            // Player interacts — open the catch dialog
            if (player instanceof ServerPlayer sp) {
                openCatchDialog(sp);
            }
            return InteractionResult.SUCCESS;
        }

        // Tame already -> open normal interaction screen
        if (entityData.get(DATA_TAMED)) {
            return super.mobInteract(player, hand);
        }

        // Untamed goblin: picking up (old PICK_UP behavior)
        entityData.set(DATA_STOLEN_OWNER, player.getStringUUID());
        entityData.set(DATA_TAMED, true);
        // Lock onto player
        targetPlayerUUID = player.getUUID();
        player.displayClientMessage(Component.literal("§eYou picked up the goblin!"), false);
        return InteractionResult.SUCCESS;
    }

    /**
     * Check if this player is already carrying any goblin.
     */
    private static boolean isPlayerCarryingGoblin(UUID playerUUID) {
        if (playerUUID == null) return false;
        // Check all loaded goblins (server-side only)
        return false; // Simplified — the actual check would iterate all entities
    }

    /**
     * Open the "caught goblin" dialog — either the existing GoblinCaughtScreen or
     * open the InteractionScreen with special options.
     */
    private void openCatchDialog(ServerPlayer player) {
        // Old behavior: two options: "take ur stuff back" or "use her"
        player.displayClientMessage(Component.literal("§6[Goblin] Caught me! What now?"), false);
        // The client will get the message and can right-click again to trigger the screen
        // For now, return via the existing GoblinActionPacket mechanism.
        // The client-side GoblinCaughtScreen is opened via the InteractionScreen for goblin.
    }

    // ==================== tick ====================

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide) {
            handleClientTick();
            return;
        }
        handleServerTick();
    }

    private void handleServerTick() {
        // 1. Steal system for wild goblins
        if (!entityData.get(DATA_TAMED) && !entityData.get(DATA_IS_QUEEN)) {
            attemptSteal();
        }

        // 2. Queen state machine
        handleQueenTick();

        // 3. Catch/stolen owner following (if tamed and not in scene)
        if (entityData.get(DATA_TAMED)) {
            String ownerStr = entityData.get(DATA_STOLEN_OWNER);
            if (!ownerStr.isEmpty()) {
                try {
                    Player owner = level().getPlayerByUUID(UUID.fromString(ownerStr));
                    if (owner != null) {
                        // If entity is too far, teleport closer
                        if (distanceToSqr(owner) > 400) {
                            teleportTo(owner.getX(), owner.getY(), owner.getZ());
                        }
                    }
                } catch (Exception ignored) {}
            }
        }

        // 4. Guard system: dismiss tamed guards (n())
        handleGuardSystem();

        // 5. Queen spawn timer
        handleQueenSpawnTimer();

        // 6. Guard dismiss timer
        handleGuardDismissTimer();

        // 7. Jump animation timer
        handleJumpTimer();

        // 8. Thrown animation
        handleThrownState();

        // 9. Stand-up state
        handleStandUpState();

        // 10. Vanish
        handleVanish();

        // 11. Pregnant timer
        handlePregnantState();

        // 12. Breeding message cooldown
        if (breedingMsgCooldown > 0) breedingMsgCooldown--;
    }

    private void handleClientTick() {
        // Fade alpha on vanish
        if (vanishTimer >= 0) {
            vanishAlpha -= 0.05f;
            if (vanishAlpha <= 0) {
                vanishAlpha = 0;
            }
        }
        // Thrown fade
        if (isThrownState()) {
            if (onGround() || isInWater()) {
                vanishAlpha -= 0.05f;
                if (vanishAlpha <= 0) {
                    vanishAlpha = 0;
                }
            }
        }
    }

    // ==================== Queen system ====================

    public boolean isQueen() { return entityData.get(DATA_IS_QUEEN); }

    public void setQueen(boolean queen) {
        entityData.set(DATA_IS_QUEEN, queen);
    }

    public boolean isPreggo() { return entityData.get(DATA_PREGG0); }

    public void setPreggo(boolean preggo) {
        entityData.set(DATA_PREGG0, preggo);
        if (preggo) {
            impregnationTick = level().getGameTime();
            breedingMsgCooldown = COOLDOWN_TICKS;
        }
    }

    private void handleQueenTick() {
        if (!entityData.get(DATA_IS_QUEEN)) return;

        // m() — guard on throne when no rider
        if (entityData.get(DATA_TAMED)) return; // skip for tamed queen
    }

    private void handlePregnantState() {
        if (!entityData.get(DATA_IS_QUEEN)) return;
        if (!isPreggo()) return;
        if (impregnationTick > 0 && level().getGameTime() - impregnationTick >= PREGNANCY_TICKS) {
            entityData.set(DATA_PREGG0, false);
            impregnationTick = -1;
        }
    }

    private void handleGuardSystem() {
        if (!entityData.get(DATA_IS_QUEEN)) return;
        if (guards.isEmpty()) return;

        boolean hasTamedGuard = false;
        for (GoblinEntity g : guards) {
            if (g.entityData.get(DATA_TAMED)) {
                hasTamedGuard = true;
                break;
            }
        }
        if (!hasTamedGuard) return;

        // Dismiss untamed guards
        for (GoblinEntity g : guards) {
            if (!g.entityData.get(DATA_TAMED)) {
                g.startVanish();
            }
        }
        guards.clear();
        targetPlayerUUID = null;

        String ownerStr = entityData.get(DATA_STOLEN_OWNER);
        if (!ownerStr.isEmpty()) {
            Player owner = level().getPlayerByUUID(UUID.fromString(ownerStr));
            if (owner != null) {
                owner.displayClientMessage(Component.literal("§dThe queen dismisses her guards after giving birth."), false);
            }
        }
    }

    private void handleQueenSpawnTimer() {
        if (!entityData.get(DATA_IS_QUEEN)) return;
        if (entityData.get(DATA_TAMED)) return;
        if (isPreggo()) return;

        if (queenSpawnTimer < QUEEN_SPAWN_INTERVAL) {
            if (queenSpawnTimer < 0) queenSpawnTimer = 0;
            queenSpawnTimer++;
            return;
        }

        // Find nearest player
        Player nearest = level().getNearestPlayer(this, 3000.0);
        if (nearest == null || !nearest.onGround() || nearest.isSwimming()) return;

        // Take a gold item from player's inventory
        int slot = findGoldItem(nearest.getInventory());
        if (slot == -1) return;

        ItemStack taken = nearest.getInventory().getItem(slot).copy();
        nearest.getInventory().removeItem(slot, 1);

        // Spawn baby goblin with the item
        GoblinEntity baby = new GoblinEntity((EntityType<? extends PathfinderMob>) this.getType(), level());
        Vec3 spawnPos = nearest.position().add(
                Math.sin(Math.toRadians(-nearest.getYRot())) * 0.3,
                0,
                Math.cos(Math.toRadians(-nearest.getYRot())) * 0.3
        );
        baby.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
        baby.setItemSlot(EquipmentSlot.MAINHAND, taken);
        baby.entityData.set(DATA_STOLEN_ITEM, taken);
        level().addFreshEntity(baby);
        nearest.displayClientMessage(Component.literal("§e<Goblin> I got your " + taken.getHoverName().getString() + " hehe~"), false);

        queenSpawnTimer = 0;
    }

    private void handleGuardDismissTimer() {
        if (!entityData.get(DATA_IS_QUEEN)) return;
        if (guardDismissTimer == -1) return;

        if (++guardDismissTimer < GUARD_DISMISS_TICKS) return;
        guardDismissTimer = -1;

        UUID riderUUID = getRiderUUID();
        if (riderUUID == null) {
            spawnGuardsAndDismiss();
            return;
        }
        Player rider = level().getPlayerByUUID(riderUUID);
        if (rider == null) {
            spawnGuardsAndDismiss();
            return;
        }
        // Release rider
        targetPlayerUUID = null;
        for (GoblinEntity g : guards) {
            g.targetPlayerUUID = null;
        }
        List<GoblinEntity> guardList = getGuardCandidates();
        if (guardList.size() >= 2) {
            GoblinEntity g1 = guardList.get(0);
            GoblinEntity g2 = guardList.get(1);
            Vec3 offset = getRotatedVec(JUMP_OFFSET_90, throneRot);
            Vec3 offset2 = getRotatedVec(new Vec3(-1.0, -1.0, -3.0), throneRot);
            g1.setPos(thronePos.x + offset.x, thronePos.y + offset.y, thronePos.z + offset.z);
            g2.setPos(thronePos.x + offset2.x, thronePos.y + offset2.y, thronePos.z + offset2.z);
            g1.isThroneInitialized = true;
            g2.isThroneInitialized = true;
            g1.throneRot = 0;
            g2.throneRot = 0;
            rider.teleportTo(
                    thronePos.x + getRotatedVec(new Vec3(0, 0, -0.7875), throneRot - 180).x,
                    thronePos.y + 0.44375 - rider.getEyeHeight(),
                    thronePos.z + getRotatedVec(new Vec3(0, 0, -0.7875), throneRot - 180).z
            );
            rider.setYRot(throneRot);
            rider.setXRot(30);
            rider.displayClientMessage(Component.literal("§dThanks to you, my clan is soon going to get a few new members! In return I will bear of one of my guards to serve as your personal Onahole. Choose wisely~"), false);
        }
    }

    @Nullable
    private UUID getRiderUUID() {
        String ownerStr = entityData.get(DATA_STOLEN_OWNER);
        try {
            return UUID.fromString(ownerStr);
        } catch (Exception e) {
            return null;
        }
    }

    private List<GoblinEntity> getGuardCandidates() {
        // Re-scan for existing goblins
        List<GoblinEntity> candidates = new ArrayList<>();
        for (GoblinEntity gf : this.level().getEntitiesOfClass(GoblinEntity.class, this.getBoundingBox().inflate(50.0))) {
            if (gf != this) candidates.add(gf);
        }
        return candidates;
    }

    private void spawnGuardsAndDismiss() {
        // Find or spawn 2 guards, dedup against existing guards list
        List<GoblinEntity> existing = new ArrayList<>();
        for (GoblinEntity gf : this.level().getEntitiesOfClass(GoblinEntity.class, this.getBoundingBox().inflate(50.0))) {
            if (gf != this && !guards.contains(gf)) existing.add(gf);
        }
        while (existing.size() < 2) {
            GoblinEntity guard = new GoblinEntity((EntityType<? extends PathfinderMob>) this.getType(), level());
            guard.setPos(getX(), getY(), getZ());
            guard.isThroneInitialized = true;
            guard.thronePos = this.thronePos;
            guard.throneRot = this.throneRot;
            level().addFreshEntity(guard);
            existing.add(guard);
        }
        guards.addAll(existing);
    }

    // ==================== Thrown / Carry system ====================

    private boolean isThrownState() { return false; } // Placeholder — animation state tracked by base

    private void handleThrownState() {
        // Check if this goblin is in a thrown state (THROWN in old code)
        // This is simplified; actually handled via the animation controller
    }

    private void handleStandUpState() {
        // Stand-up after throw / drop
        // Handled via animation controller
    }

    public void startVanish() {
        vanishTimer = 0;
    }

    private void handleVanish() {
        if (vanishTimer < 0) return;
        vanishTimer++;
        if (vanishTimer >= VANISH_FADE_RATE) {
            remove(RemovalReason.DISCARDED);
        }
    }

    private void handleJumpTimer() {
        // Jump animation timer for queen breeding scene
        if (jumpTimer > 0) jumpTimer = 0; // Reset — handled by state machine
    }

    // ==================== NBT persistence ====================

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("stolenOwner", entityData.get(DATA_STOLEN_OWNER));
        tag.putBoolean("isTamed", entityData.get(DATA_TAMED));
        tag.putString("queenName", entityData.get(DATA_QUEEN_NAME));
        tag.putBoolean("isQueen", entityData.get(DATA_IS_QUEEN));
        tag.putBoolean("isPreggo", entityData.get(DATA_PREGG0));
        tag.putInt("bodyColor", entityData.get(DATA_BODY_COLOR));
        tag.putInt("eyeColor", entityData.get(DATA_EYE_COLOR));
        tag.putInt("stealCount", this.stealCount);

        // Save stolen items list
        CompoundTag itemsTag = new CompoundTag();
        itemsTag.putInt("count", stolenItems.size());
        for (int i = 0; i < stolenItems.size(); i++) {
            itemsTag.put("item_" + i, stolenItems.get(i).saveOptional(level().registryAccess()));
        }
        tag.put("stolenItems", itemsTag);

        // Queen data
        if (entityData.get(DATA_IS_QUEEN)) {
            tag.putFloat("throneRot", this.throneRot);
            tag.putDouble("thronePosX", this.thronePos.x);
            tag.putDouble("thronePosY", this.thronePos.y);
            tag.putDouble("thronePosZ", this.thronePos.z);
            tag.putLong("impregnationTick", this.impregnationTick);
            tag.putBoolean("isThroneInitialized", this.isThroneInitialized);
            tag.putInt("queenSpawnTimer", this.queenSpawnTimer);

            // Save guard list
            for (int i = 0; i < guardUUIDs.size(); i++) {
                tag.putUUID("guard" + i, guardUUIDs.get(i));
            }
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(DATA_STOLEN_OWNER, tag.getString("stolenOwner"));
        entityData.set(DATA_TAMED, tag.getBoolean("isTamed"));
        entityData.set(DATA_QUEEN_NAME, tag.getString("queenName"));
        entityData.set(DATA_IS_QUEEN, tag.getBoolean("isQueen"));
        entityData.set(DATA_PREGG0, tag.getBoolean("isPreggo"));
        entityData.set(DATA_BODY_COLOR, tag.getInt("bodyColor"));
        entityData.set(DATA_EYE_COLOR, tag.getInt("eyeColor"));
        this.stealCount = tag.getInt("stealCount");

        // Restore stolen items
        stolenItems.clear();
        if (tag.contains("stolenItems")) {
            CompoundTag itemsTag = tag.getCompound("stolenItems");
            int count = itemsTag.getInt("count");
            for (int i = 0; i < count; i++) {
                if (itemsTag.contains("item_" + i)) {
                    ItemStack stack = ItemStack.parseOptional(level().registryAccess(), itemsTag.getCompound("item_" + i));
                    if (!stack.isEmpty()) {
                        stolenItems.add(stack);
                    }
                }
            }
        }

        // Restore queen data
        if (entityData.get(DATA_IS_QUEEN)) {
            this.throneRot = tag.getFloat("throneRot");
            this.thronePos = new Vec3(
                    tag.getDouble("thronePosX"),
                    tag.getDouble("thronePosY"),
                    tag.getDouble("thronePosZ")
            );
            this.impregnationTick = tag.getLong("impregnationTick");
            this.isThroneInitialized = tag.getBoolean("isThroneInitialized");
            this.queenSpawnTimer = tag.getInt("queenSpawnTimer");

            // Restore guard list
            guardUUIDs.clear();
            for (int i = 0; ; i++) {
                String key = "guard" + i;
                if (!tag.contains(key)) break;
                try {
                    guardUUIDs.add(tag.getUUID(key));
                } catch (Exception e) {
                    break;
                }
            }
        }
    }

    // ==================== Helpers ====================

    private Vec3 getRotatedVec(Vec3 vec, float yawDeg) {
        double cos = Math.cos(Math.toRadians(yawDeg));
        double sin = Math.sin(Math.toRadians(yawDeg));
        return new Vec3(vec.x * cos - vec.z * sin, vec.y, vec.x * sin + vec.z * cos);
    }

    private boolean isSceneActive() {
        return false; // TODO: hook into SexEntity scene state
    }

    // ==================== Damage handling (old: nullify damage while ridden) ====================

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (targetPlayerUUID != null) {
            return false; // Invulnerable while ridden
        }
        return super.hurt(source, amount);
    }

    // ==================== Public API for interaction ====================

    /**
     * Called when player selects "take ur stuff back" (return stolen items).
     */
    public void doReturnStolenItems(ServerPlayer player) {
        returnStolenItems(player);
    }

    /**
     * Called when player selects "use her" (start scene).
     */
    public void doUseHer(ServerPlayer player) {
        // Old behavior: start a sex scene (paizuri or nelson depending on state)
        player.displayClientMessage(Component.literal("§eThe goblin purrs as you approach..."), false);
        // In the old code this triggered START_THROWING → followed by scene transition
        // For the new system, it should hook into SceneManager
    }

    // ==================== Selection/rendering ====================

    @Override
    public boolean isCurrentlyGlowing() {
        return false;
    }

    /**
     * Get the string-based model encoding for DNA/color (old-style).
     */
    public String getModelString() {
        // Simplified — returns an 8-part DNA-like string matching old encoding
        return "3|2|2|7|7|5|" + entityData.get(DATA_BODY_COLOR) + "|" + entityData.get(DATA_EYE_COLOR) + "|0";
    }

    public void setModelFromString(String model) {
        // Stub — would parse the DNA string and set DATA_BODY_COLOR / DATA_EYE_COLOR
    }
}
