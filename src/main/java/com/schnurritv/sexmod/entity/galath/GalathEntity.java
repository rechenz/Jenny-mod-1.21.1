package com.schnurritv.sexmod.entity.galath;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class GalathEntity extends BaseGirlEntity implements PlayerRideableJumping {

    private int energyCooldown = 0;

    public GalathEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "galath"; }
    @Override public String getGeoFileName() { return "galath"; }
    @Override public String getNudeGeoFileName() { return "galath_con_mang"; }

    // Galath: corrupt_*, rape_*, masterbating_*, shared.bed_*
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START  -> "animation.shared.bed_slow";
            case MISSIONARY_SLOW   -> "animation.shared.bed_slow";
            case MISSIONARY_FAST   -> "animation.shared.bed_fast";
            case MISSIONARY_CUM    -> "animation.shared.bed_cum";
            case BLOWJOBINTRO      -> "animation." + p + ".rape_intro";
            case BLOWJOBSUCK       -> "animation." + p + ".rape1";
            case BLOWJOBTHRUST     -> "animation." + p + ".rape2";
            case BLOWJOBCUM        -> "animation." + p + ".rape_cum";
            case PAIZURI_START     -> "animation." + p + ".corrupt_intro";
            case PAIZURI_SLOW      -> "animation." + p + ".corrupt_slow";
            case PAIZURI_FAST      -> "animation." + p + ".corrupt_hard";
            case PAIZURI_CUM       -> "animation." + p + ".corrupt_cum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation.shared.bed_slow";
            case DOGGYSLOW         -> "animation.shared.bed_slow";
            case DOGGYFAST         -> "animation.shared.bed_fast1";
            case DOGGYCUM          -> "animation.shared.bed_cum";
            default -> "animation." + p + ".idle";
        };
    }

    // ── Boss Attributes ──
    public static AttributeSupplier.Builder createBossAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }
    public boolean removeWhenFarAway(double distance) { return false; }
    @Override public int getBaseExperienceReward() { return 50; }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void die(DamageSource source) {
        if (!this.level().isClientSide) {
            this.spawnAtLocation(new ItemStack(ModItems.GALATH_COIN.get(), 1));
            int netherStars = this.random.nextInt(3);
            if (netherStars > 0) this.spawnAtLocation(new ItemStack(Items.NETHER_STAR, netherStars));
        }
        super.die(source);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (energyCooldown > 0) { energyCooldown--; return; }
        energyCooldown = 200;
        LivingEntity target = this.getTarget();
        if (target == null) return;
        AABB area = this.getBoundingBox().inflate(6.0);
        for (LivingEntity e : this.level().getEntitiesOfClass(LivingEntity.class, area,
                e -> e != this && !(e instanceof BaseGirlEntity))) {
            if (e.getType().getCategory().isFriendly()) continue;
            e.hurt(this.damageSources().magic(), 4.0f);
            e.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1, false, true));
            e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, true));
        }
    }

    // ── Rideable ──
    @Nullable @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof Player player ? player : null;
    }
    @Override public boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty() && passenger instanceof Player;
    }

    @Override
    public void travel(Vec3 pos) {
        if (this.getEntityData().get(IS_LOCKED)) { this.setDeltaMovement(0, 0, 0); return; }
        LivingEntity rider = getControllingPassenger();
        if (rider != null && this.isVehicle()) {
            float forward = rider.zza;
            float strafe = rider.xxa;
            boolean sneak = rider.isShiftKeyDown();
            this.setYRot(rider.getYRot());
            this.yRotO = this.getYRot(); this.yBodyRot = this.getYRot(); this.yHeadRot = this.getYRot();
            Vec3 lookVec = rider.getLookAngle();
            double hSpeed = 0.5D, vSpeed = 0.3D;
            double mx = 0, my = 0, mz = 0;
            if (strafe != 0) { mx = -lookVec.z * hSpeed * strafe; mz = lookVec.x * hSpeed * strafe; }
            if (forward != 0) { mx += lookVec.x * hSpeed * forward; mz += lookVec.z * hSpeed * forward; }
            if (this.jumping) { my = vSpeed; this.jumping = false; }
            else if (sneak) { my = -vSpeed; }
            this.setDeltaMovement(mx, my, mz);
            this.hasImpulse = true; this.fallDistance = 0;
            super.travel(Vec3.ZERO);
        } else { super.travel(pos); }
    }
    @Override public boolean isNoGravity() { return this.getControllingPassenger() != null || super.isNoGravity(); }
    @Override protected float getRiddenSpeed(Player player) { return 1.0f; }
    @Override public Vec3 getPassengerRidingPosition(Entity passenger) { return new Vec3(0.0, 0.8D, 0.0); }
    @Override public boolean canJump() { return true; }
    @Override public void onPlayerJump(int jumpPower) { this.jumping = true; }
    @Override public void handleStartJump(int jumpPower) { this.jumping = true; }
    @Override public void handleStopJump() {}
    @Override public int getJumpCooldown() { return 0; }
}
