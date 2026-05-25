package com.schnurritv.sexmod.entity.manglelie;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.entity.galath.GalathEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

public class ManglelieEntity extends BaseGirlEntity {
    private int corruptionCooldown = 0;
    private int bondParticleCooldown = 0;

    public ManglelieEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "manglelie"; }
    @Override public String getGeoFileName() { return "manglelie"; }
    @Override public String getNudeGeoFileName() { return "manglelie"; }

    // Manglelie: bed_slow + shared.* (double holding, bed)
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START, MISSIONARY_SLOW  -> "animation." + p + ".bed_slow";
            case MISSIONARY_FAST                    -> "animation.shared.bed_fast";
            case MISSIONARY_CUM                     -> "animation.shared.bed_cum";
            case BLOWJOBINTRO, PAIZURI_START        -> "animation.shared.double_holding_slow";
            case BLOWJOBSUCK, PAIZURI_SLOW          -> "animation.shared.double_holding_slow1";
            case BLOWJOBTHRUST, PAIZURI_FAST        -> "animation.shared.double_holding_slow2";
            case BLOWJOBCUM, PAIZURI_CUM            -> "animation.shared.double_holding_cum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".bed_slow";
            case DOGGYSLOW                           -> "animation.shared.bed_slow";
            case DOGGYFAST                           -> "animation.shared.bed_fast";
            case DOGGYCUM                            -> "animation.shared.bed_cum";
            default -> "animation." + p + ".idle";
        };
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
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
}
