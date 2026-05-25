package com.schnurritv.sexmod.entity.bee;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class BeeEntity extends BaseGirlEntity {
    public BeeEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "bee"; }
    @Override public String getNudeGeoFileName() { return "armored"; }

    // Bee: sex_* (single scene, all phases map to the same 4 states)
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START, BLOWJOBINTRO, PAIZURI_START, DOGGYSTART, DOGGYGOONBED, DOGGYWAIT
                -> "animation." + p + ".sex_start";
            case MISSIONARY_SLOW, BLOWJOBSUCK, PAIZURI_SLOW, DOGGYSLOW
                -> "animation." + p + ".sex_slow";
            case MISSIONARY_FAST, BLOWJOBTHRUST, PAIZURI_FAST, DOGGYFAST
                -> "animation." + p + ".sex_fast";
            case MISSIONARY_CUM, BLOWJOBCUM, PAIZURI_CUM, DOGGYCUM
                -> "animation." + p + ".sex_cum";
            default -> "animation." + p + ".idle";
        };
    }

    private int buffCooldown = 0;
    @Override public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (buffCooldown > 0) { buffCooldown--; return; }
        buffCooldown = 200;
        String uuid = this.entityData.get(MASTER_UUID);
        if (uuid.isEmpty()) return;
        try {
            Player owner = this.level().getPlayerByUUID(java.util.UUID.fromString(uuid));
            if (owner != null && owner.distanceToSqr(this) < 64) {
                owner.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, false, false));
                owner.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0, false, false));
            }
        } catch (Exception e) {}
    }
}
