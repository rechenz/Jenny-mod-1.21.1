package com.schnurritv.sexmod.entity;

import com.schnurritv.sexmod.SexModConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.UUID;

/**
 * Base class for fighter characters with equipment and auto-combat.
 * <p>
 * Adds 6 equipment slots (0=melee, 1=bow, 2=helmet, 3=chestplate, 4=leggings, 5=boots),
 * auto-targeting of hostile mobs, melee combos, bow ranged attacks,
 * and a DOWNED state when health reaches zero.
 */
public abstract class SexFighterEntity extends BaseGirlEntity {

    // ── Synced equipment data accessors ──
    private static final EntityDataAccessor<ItemStack> EQUIP_SLOT_0 = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> EQUIP_SLOT_1 = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> EQUIP_SLOT_2 = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> EQUIP_SLOT_3 = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> EQUIP_SLOT_4 = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> EQUIP_SLOT_5 = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> GIRL_MODE = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_DOWNED = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ATTACK_STAGE = SynchedEntityData.defineId(SexFighterEntity.class, EntityDataSerializers.INT);

    // 6 equipment slots: 0=melee weapon, 1=bow, 2=helmet, 3=chestplate, 4=leggings, 5=boots
    private final NonNullList<ItemStack> equipment = NonNullList.withSize(6, ItemStack.EMPTY);

    // Combat state (server-side)
    private int attackCombo = 1;
    private int attackCooldown = 0;
    private int bowChargeTimer = 0;
    private int downedRegenTimer = 0;
    private int targetSearchTimer = 0;
    private int pathRecalcTimer = 0;
    private LivingEntity combatTarget = null;
    // Attack animation reset timer (client tracks via data sync)
    private int attackStageResetTimer = 0;

    protected SexFighterEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    // ═══════════════════════════════════════════════════════════
    //  Synced data
    // ═══════════════════════════════════════════════════════════

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(EQUIP_SLOT_0, ItemStack.EMPTY);
        builder.define(EQUIP_SLOT_1, ItemStack.EMPTY);
        builder.define(EQUIP_SLOT_2, ItemStack.EMPTY);
        builder.define(EQUIP_SLOT_3, ItemStack.EMPTY);
        builder.define(EQUIP_SLOT_4, ItemStack.EMPTY);
        builder.define(EQUIP_SLOT_5, ItemStack.EMPTY);
        builder.define(GIRL_MODE, 0);
        builder.define(IS_DOWNED, false);
        builder.define(ATTACK_STAGE, 0);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (key == EQUIP_SLOT_0) equipment.set(0, entityData.get(EQUIP_SLOT_0));
        else if (key == EQUIP_SLOT_1) equipment.set(1, entityData.get(EQUIP_SLOT_1));
        else if (key == EQUIP_SLOT_2) equipment.set(2, entityData.get(EQUIP_SLOT_2));
        else if (key == EQUIP_SLOT_3) equipment.set(3, entityData.get(EQUIP_SLOT_3));
        else if (key == EQUIP_SLOT_4) equipment.set(4, entityData.get(EQUIP_SLOT_4));
        else if (key == EQUIP_SLOT_5) equipment.set(5, entityData.get(EQUIP_SLOT_5));
    }

    // ═══════════════════════════════════════════════════════════
    //  Equipment API
    // ═══════════════════════════════════════════════════════════

    public void setEquipmentSlot(int slot, ItemStack stack) {
        if (slot < 0 || slot >= 6) return;
        equipment.set(slot, stack.copy());
        syncEquipSlot(slot);
        if (!level().isClientSide) {
            applyEquipmentVisuals();
        }
    }

    public ItemStack getEquipmentSlot(int slot) {
        if (slot < 0 || slot >= 6) return ItemStack.EMPTY;
        return equipment.get(slot);
    }

    public NonNullList<ItemStack> getEquipment() {
        return equipment;
    }

    private void syncEquipSlot(int slot) {
        switch (slot) {
            case 0 -> entityData.set(EQUIP_SLOT_0, equipment.get(0));
            case 1 -> entityData.set(EQUIP_SLOT_1, equipment.get(1));
            case 2 -> entityData.set(EQUIP_SLOT_2, equipment.get(2));
            case 3 -> entityData.set(EQUIP_SLOT_3, equipment.get(3));
            case 4 -> entityData.set(EQUIP_SLOT_4, equipment.get(4));
            case 5 -> entityData.set(EQUIP_SLOT_5, equipment.get(5));
        }
    }

    private void syncAllEquipment() {
        for (int i = 0; i < 6; i++) syncEquipSlot(i);
    }

    private void applyEquipmentVisuals() {
        setItemSlot(EquipmentSlot.HEAD, equipment.get(2));
        setItemSlot(EquipmentSlot.CHEST, equipment.get(3));
        setItemSlot(EquipmentSlot.LEGS, equipment.get(4));
        setItemSlot(EquipmentSlot.FEET, equipment.get(5));
        setItemSlot(EquipmentSlot.MAINHAND, equipment.get(0));
        setItemSlot(EquipmentSlot.OFFHAND, equipment.get(1));
    }

    public boolean isDowned() {
        return entityData.get(IS_DOWNED);
    }

    public int getComboStage() {
        return attackCombo;
    }

    public int getGirlMode() {
        return entityData.get(GIRL_MODE);
    }

    public int getAttackStage() {
        return entityData.get(ATTACK_STAGE);
    }

    // ═══════════════════════════════════════════════════════════
    //  Combat animation override
    // ═══════════════════════════════════════════════════════════

    /**
     * Override getIdleAnimationPath to return combat-relevant animations when:
     * - ATTACK_STAGE > 0: returns attack animation (animation.&lt;prefix&gt;.attack0/1/2)
     * - isDowned(): returns downed animation (animation.&lt;prefix&gt;.downed)
     * - GIRL_MODE == 2 (bow charge): returns bowcharge animation (animation.&lt;prefix&gt;.bowcharge)
     * Characters without these animations (e.g. Cat) fall back to idle.
     */
    @Override
    public String getIdleAnimationPath() {
        String prefix = getAnimationPrefix();

        // Downed state
        if (isDowned()) {
            // All fighter characters have downed animations
            return "animation." + prefix + ".downed";
        }

        // Bow charge animation
        if (getGirlMode() == 2) {
            // Check if character has bowcharge animation (Jenny, Bia have it; Ellie doesn't, Cat doesn't)
            if ("jenny".equals(prefix) || "bia".equals(prefix)) {
                return "animation." + prefix + ".bowcharge";
            }
        }

        // Attack stage
        int stage = getAttackStage();
        if (stage > 0) {
            // Cat doesn't have attack animations
            if (!"cat".equals(prefix)) {
                return "animation." + prefix + ".attack" + (stage - 1);
            }
        }

        return super.getIdleAnimationPath();
    }

    // ═══════════════════════════════════════════════════════════
    //  Hurt / Downed
    // ═══════════════════════════════════════════════════════════

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isDowned()) {
            if (downedRegenTimer > 0) return false;
            return super.hurt(source, amount * 0.5f);
        }
        boolean wasHurt = super.hurt(source, amount);
        if (wasHurt && getHealth() <= 0.0f) {
            enterDownedState();
        }
        return wasHurt;
    }

    private void enterDownedState() {
        entityData.set(IS_DOWNED, true);
        setHealth(1.0f);
        entityData.set(GIRL_MODE, 0);
        getNavigation().stop();
        setTarget(null);
        combatTarget = null;
        downedRegenTimer = 200;
        entityData.set(IS_LOCKED, false);
    }

    private void exitDownedState() {
        entityData.set(IS_DOWNED, false);
        setHealth(getMaxHealth());
        downedRegenTimer = 0;
    }

    // ═══════════════════════════════════════════════════════════
    //  Melee attack
    // ═══════════════════════════════════════════════════════════

    public void tryAttack(LivingEntity target) {
        if (target == null || !target.isAlive()) return;

        ItemStack weapon = equipment.get(0);
        float baseDamage = 2.0f;

        if (weapon.getItem() instanceof SwordItem) baseDamage = 5.0f;
        else if (weapon.getItem() instanceof AxeItem) baseDamage = 7.0f;
        else if (weapon.getItem() instanceof TridentItem) baseDamage = 8.0f;
        else if (weapon.getItem() instanceof PickaxeItem) baseDamage = 4.0f;


        float comboBonus = (attackCombo - 1) * 0.5f;
        float total = baseDamage + comboBonus;

        boolean hit = target.hurt(damageSources().mobAttack(this), total);

        if (hit) {
            // Basic knockback
            Vec3 dir = position().subtract(target.position()).normalize();
            target.push(dir.x * 0.3, 0.1, dir.z * 0.3);
        }

        // Set attack animation stage (1→2→3→1) and sync to client
        entityData.set(ATTACK_STAGE, attackCombo);
        attackStageResetTimer = 5;

        attackCombo = (attackCombo % 3) + 1;

        if (weapon.getItem() instanceof SwordItem) attackCooldown = 8;
        else if (weapon.getItem() instanceof AxeItem || weapon.getItem() instanceof PickaxeItem) attackCooldown = 12;
        else if (weapon.getItem() instanceof TridentItem) attackCooldown = 15;
        else attackCooldown = 10;
    }

    // ═══════════════════════════════════════════════════════════
    //  Bow attack
    // ═══════════════════════════════════════════════════════════

    public void startBowCharge() {
        if (equipment.get(1).getItem() instanceof BowItem) {
            bowChargeTimer = 0;
            entityData.set(GIRL_MODE, 2);
        }
    }

    public void fireArrow(LivingEntity target) {
        if (target == null || !target.isAlive()) return;
        if (!(equipment.get(1).getItem() instanceof BowItem)) {
            bowChargeTimer = 0;
            entityData.set(GIRL_MODE, 1);
            return;
        }
        if (level().isClientSide) return;

        Arrow arrow = new Arrow(EntityType.ARROW, level());
        arrow.setPos(getX(), getEyeY() - 0.1, getZ());

        float charge = Math.min((float) bowChargeTimer / 20.0f, 1.0f);
        float velocity = 0.5f + charge * 1.5f;

        double dx = target.getX() - getX();
        double dy = target.getEyeY() - getEyeY();
        double dz = target.getZ() - getZ();

        double hDist = Math.sqrt(dx * dx + dz * dz);
        arrow.shoot(dx, dy + hDist * 0.2, dz, velocity, 4.0f);

        equipment.get(1).hurtAndBreak(1, this, EquipmentSlot.MAINHAND);
        level().addFreshEntity(arrow);

        bowChargeTimer = 0;
        entityData.set(GIRL_MODE, 1);
    }

    // ═══════════════════════════════════════════════════════════
    //  Combat AI tick
    // ═══════════════════════════════════════════════════════════

    protected void handleCombatTick() {
        if (level().isClientSide) return;

        if (isDowned()) {
            if (downedRegenTimer > 0) {
                downedRegenTimer--;
                heal(getMaxHealth() / 200.0f);
            } else if (getHealth() >= getMaxHealth() - 0.1f) {
                exitDownedState();
            } else {
                heal(1.0f);
            }
            return;
        }

        if (entityData.get(IS_LOCKED)) return;
        if (attackCooldown > 0) attackCooldown--;

        // Reset attack animation stage after timer expires
        if (attackStageResetTimer > 0) {
            attackStageResetTimer--;
            if (attackStageResetTimer <= 0) {
                entityData.set(ATTACK_STAGE, 0);
            }
        }

        if (bowChargeTimer > 0) {
            if (combatTarget != null && combatTarget.isAlive()) {
                bowChargeTimer++;
                if (bowChargeTimer >= 30) fireArrow(combatTarget);
                lookAt(combatTarget, 30.0f, 30.0f);
            } else {
                bowChargeTimer = 0;
                entityData.set(GIRL_MODE, 0);
            }
            return;
        }

        if (!SexModConfig.GIRLS_FIGHT_HOSTILES.get()) return;

        if (targetSearchTimer > 0) targetSearchTimer--;
        if (targetSearchTimer <= 0) {
            targetSearchTimer = 40;
            combatTarget = findCombatTarget();
            entityData.set(GIRL_MODE, combatTarget != null ? 1 : 0);
        }

        if (combatTarget != null && combatTarget.isAlive()) {
            double distSq = distanceToSqr(combatTarget);
            if (distSq > 64.0) {
                combatTarget = null;
                setTarget(null);
                entityData.set(GIRL_MODE, 0);
                return;
            }
            setTarget(combatTarget);

            if (distSq <= 4.0) {
                if (attackCooldown <= 0) {
                    lookAt(combatTarget, 30.0f, 30.0f);
                    tryAttack(combatTarget);
                }
            } else {
                boolean hasBow = equipment.get(1).getItem() instanceof BowItem;
                if (hasBow && distSq > 9.0) {
                    if (attackCooldown <= 0) {
                        lookAt(combatTarget, 30.0f, 30.0f);
                        startBowCharge();
                    }
                } else {
                    if (pathRecalcTimer > 0) pathRecalcTimer--;
                    if (pathRecalcTimer <= 0) {
                        pathRecalcTimer = 20;
                        getNavigation().moveTo(combatTarget, 1.25D);
                    }
                    lookAt(combatTarget, 30.0f, 30.0f);
                }
            }
        } else {
            combatTarget = null;
            setTarget(null);
            entityData.set(GIRL_MODE, 0);
        }
    }

    private LivingEntity findCombatTarget() {
        Player owner = findOwner();
        AABB area = getBoundingBox().inflate(8.0);

        List<LivingEntity> list = level().getEntitiesOfClass(LivingEntity.class, area,
            e -> e != this && e.isAlive()
                && e instanceof Enemy
                && !(e instanceof Creeper)
                && !(e instanceof BaseGirlEntity));

        if (list.isEmpty()) return null;

        if (owner != null) {
            LivingEntity best = null;
            double bestDist = Double.MAX_VALUE;
            for (LivingEntity e : list) {
                double d = e.distanceToSqr(owner);
                if (d < bestDist) { bestDist = d; best = e; }
            }
            return best;
        }

        LivingEntity closest = null;
        double closestDist = Double.MAX_VALUE;
        for (LivingEntity e : list) {
            double d = distanceToSqr(e);
            if (d < closestDist) { closestDist = d; closest = e; }
        }
        return closest;
    }

    private Player findOwner() {
        String uid = entityData.get(MASTER_UUID);
        if (uid == null || uid.isEmpty()) return null;
        try { return level().getPlayerByUUID(UUID.fromString(uid)); }
        catch (Exception e) { return null; }
    }

    @Override
    public void tick() {
        super.tick();
        handleCombatTick();
    }

    @Override
    public void die(DamageSource source) {
        if (!level().isClientSide) {
            for (int i = 0; i < 6; i++) {
                ItemStack s = equipment.get(i);
                if (!s.isEmpty()) {
                    spawnAtLocation(s);
                    equipment.set(i, ItemStack.EMPTY);
                }
            }
        }
        super.die(source);
    }

    // ═══════════════════════════════════════════════════════════
    //  NBT
    // ═══════════════════════════════════════════════════════════

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        CompoundTag eq = new CompoundTag();
        for (int i = 0; i < 6; i++) {
            eq.put("Slot" + i, equipment.get(i).saveOptional(registryAccess()));
        }
        tag.put("FighterEquipment", eq);
        tag.putInt("AttackCombo", attackCombo);
        tag.putBoolean("IsDowned", isDowned());
        tag.putInt("DownedRegenTimer", downedRegenTimer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("FighterEquipment")) {
            CompoundTag eq = tag.getCompound("FighterEquipment");
            for (int i = 0; i < 6; i++) {
                if (eq.contains("Slot" + i)) {
                    equipment.set(i, ItemStack.parseOptional(registryAccess(), eq.getCompound("Slot" + i)));
                }
            }
            syncAllEquipment();
            if (!level().isClientSide) applyEquipmentVisuals();
        }
        if (tag.contains("AttackCombo")) attackCombo = tag.getInt("AttackCombo");
        if (tag.contains("IsDowned") && tag.getBoolean("IsDowned")) {
            entityData.set(IS_DOWNED, true);
        }
        if (tag.contains("DownedRegenTimer")) downedRegenTimer = tag.getInt("DownedRegenTimer");
    }
}
