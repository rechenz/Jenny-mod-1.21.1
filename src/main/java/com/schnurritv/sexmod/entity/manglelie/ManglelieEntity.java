package com.schnurritv.sexmod.entity.manglelie;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.entity.galath.GalathEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class ManglelieEntity extends BaseGirlEntity {
    private int corruptionCooldown = 0;
    private int bondParticleCooldown = 0;
    // ── Threesome with Galath ──
    public boolean isInThreesome = false;
    public String threesomePartnerUUID = "";
    public int threesomeTicks = 0;
    // ── Ride on head ──
    private static final int RIDE_COOLDOWN = 100;
    private int rideCooldown = 0;

    public ManglelieEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public boolean needsHouse() { return false; }
    @Override public String getGirlName() { return "manglelie"; }
    @Override public String getGeoFileName() { return "manglelie"; }
    @Override public String getNudeGeoFileName() { return "manglelie"; }

    // Manglelie only has solo: bed_slow. Everything else is shared (requires Galath).
    // Only expose Missionary in UI since other scenes lack solo animations.
    @Override public boolean showStandardMissionary() { return true; }
    @Override public boolean showStandardBlowjob() { return false; }
    @Override public boolean showStandardDoggy() { return false; }
    @Override public boolean showStandardBoobjob() { return false; }
    @Override public boolean supportsScene(String name) { return "Missionary".equals(name); }

    // Manglelie: own bed_slow + shared.bed_* exist in manglelie.animation.json
    // Expose only Missionary in UI. Use shared.bed_* for distinct phases.
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        return switch (animation) {
            case MISSIONARY_START -> "animation.manglelie.bed_slow";
            case MISSIONARY_SLOW  -> "animation.shared.bed_slow";
            case MISSIONARY_FAST  -> "animation.shared.bed_fast";
            case MISSIONARY_CUM   -> "animation.shared.bed_cum";
            default -> "animation.manglelie.idle";
        };
    }

    /** Find a Galath entity by its UUID string */
    private GalathEntity findGalathByUUID(String uuid) {
        if (uuid == null || uuid.isEmpty()) return null;
        java.util.UUID target;
        try { target = java.util.UUID.fromString(uuid); }
        catch (IllegalArgumentException e) { return null; }
        for (GalathEntity galath : this.level().getEntitiesOfClass(GalathEntity.class,
                this.getBoundingBox().inflate(50))) {
            if (galath.getUUID().equals(target) && galath.isAlive()) {
                return galath;
            }
        }
        return null;
    }

    @Override
    public boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty() && passenger instanceof Player;
    }

    @Override
    public void travel(Vec3 pos) {
        if (this.getEntityData().get(IS_LOCKED)) { this.setDeltaMovement(0, 0, 0); return; }
        Player rider = this.getControllingPassenger() instanceof Player p ? p : null;
        if (rider != null) {
            float forward = rider.zza;
            float strafe = rider.xxa;
            this.setYRot(rider.getYRot());
            this.yRotO = this.getYRot();
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.getYRot();
            Vec3 lookVec = rider.getLookAngle();
            double hSpeed = 0.4D, vSpeed = 0.25D;
            double mx = 0, my = 0, mz = 0;
            if (strafe != 0) { mx = -lookVec.z * hSpeed * strafe; mz = lookVec.x * hSpeed * strafe; }
            if (forward != 0) { mx += lookVec.x * hSpeed * forward; mz += lookVec.z * hSpeed * forward; }
            if (rider.isShiftKeyDown()) { my = -vSpeed; }
            this.setDeltaMovement(mx, my, mz);
            this.hasImpulse = true;
            this.fallDistance = 0;
            super.travel(Vec3.ZERO);
        } else {
            super.travel(pos);
        }
    }

    @Override
    public Vec3 getPassengerRidingPosition(Entity passenger) {
        return new Vec3(0.0, 1.6D, 0.0); // Ride on "head" position
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof Player player ? player : null;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.level().isClientSide) return InteractionResult.SUCCESS;

        // If player is sneaking, let them mount
        if (player.isShiftKeyDown()) {
            if (this.getPassengers().isEmpty() && rideCooldown <= 0) {
                // Mount check
                if (this.getEntityData().get(MASTER_UUID).equals(player.getUUID().toString()) || true) { // Allow anyone for now
                    this.getEntityData().set(IS_LOCKED, false);
                    player.startRiding(this);
                    player.displayClientMessage(Component.literal("§5You climb onto Manglelie's shoulders!"), true);
                    rideCooldown = RIDE_COOLDOWN;
                    return InteractionResult.CONSUME;
                }
            } else if (!this.getPassengers().isEmpty() && this.getFirstPassenger() == player) {
                player.stopRiding();
                player.displayClientMessage(Component.literal("§7You hop off Manglelie."), true);
                return InteractionResult.CONSUME;
            }
        }

        // Otherwise open standard interaction screen
        return super.mobInteract(player, hand);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;

        // Cooldown tick
        if (rideCooldown > 0) rideCooldown--;

        // Threesome upkeep
        if (isInThreesome && !threesomePartnerUUID.isEmpty()) {
            threesomeTicks++;
            GalathEntity galath = findGalathByUUID(threesomePartnerUUID);
            if (galath == null || !galath.isAlive() || !galath.isInThreesome) {
                isInThreesome = false;
                threesomePartnerUUID = "";
                this.getEntityData().set(IS_LOCKED, false);
                this.getEntityData().set(PARTNER_UUID, "null");
                this.setSexModAnimation(SexModAnimation.NULL);
            } else {
                this.getEntityData().set(IS_LOCKED, true);
                if (threesomeTicks % 20 == 0 && galath != null) {
                    this.lookAt(galath, 360f, 360f);
                }
                // Emit particles around both
                if (threesomeTicks % 10 == 0 && this.level() instanceof ServerLevel srv) {
                    srv.sendParticles(ParticleTypes.WITCH,
                        this.getX(), this.getY() + 1.2, this.getZ(),
                        3, 0.3, 0.3, 0.3, 0.02);
                }
            }
        }

        // Corruption particles
        if (corruptionCooldown <= 0) {
            corruptionCooldown = 80;
            int orbCount = this.random.nextInt(4);
            for (int i = 0; i < orbCount; i++) {
                double x = this.getX() + (this.random.nextDouble() - 0.5) * 4;
                double y = this.getY() + 1.0;
                double z = this.getZ() + (this.random.nextDouble() - 0.5) * 4;
                this.level().addFreshEntity(new ExperienceOrb(this.level(), x, y, z, 1 + this.random.nextInt(3)));
            }
        } else { corruptionCooldown--; }

        // Owner buff
        String uuid = this.entityData.get(MASTER_UUID);
        if (!uuid.isEmpty()) {
            try {
                Player owner = this.level().getPlayerByUUID(java.util.UUID.fromString(uuid));
                if (owner != null && owner.distanceToSqr(this) < 64.0D) {
                    owner.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 0, false, false));
                }
            } catch (Exception ignored) {}
        }

        // Mother-Daughter Bond: near Galath → mutual buffs
        boolean bondActive = false;
        for (GalathEntity galath : this.level().getEntitiesOfClass(GalathEntity.class,
                this.getBoundingBox().inflate(10.0D))) {
            if (galath.isAlive() && galath.distanceToSqr(this) < 100.0D) {
                galath.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1, false, false));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0, false, false));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0, false, false));
                bondActive = true;
                break;
            }
        }

        if (bondActive && this.level() instanceof ServerLevel serverLevel) {
            if (bondParticleCooldown <= 0) {
                bondParticleCooldown = 20;
                double px = this.getX() + (this.random.nextDouble() - 0.5) * 2.0;
                double py = this.getY() + 1.0 + this.random.nextDouble() * 1.5;
                double pz = this.getZ() + (this.random.nextDouble() - 0.5) * 2.0;
                serverLevel.sendParticles(ParticleTypes.PORTAL, px, py, pz, 5, 0.3, 0.3, 0.3, 0.1);
            } else { bondParticleCooldown--; }
        }
    }

    @Override
    public boolean isNoGravity() {
        return this.getControllingPassenger() != null || super.isNoGravity();
    }
}
