package com.schnurritv.sexmod.entity.kobold;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class KoboldEntity extends BaseGirlEntity {
    public KoboldEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "kobold"; }
    @Override public String getNudeGeoFileName() { return "armored"; }

    // Kobold: blowjob* + mating_press* (capitalized suffixes)
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START  -> "animation." + p + ".mating_press_start";
            case MISSIONARY_SLOW   -> "animation." + p + ".mating_press_slow";
            case MISSIONARY_FAST   -> "animation." + p + ".mating_press_hard";
            case MISSIONARY_CUM    -> "animation." + p + ".mating_press_cum";
            case BLOWJOBINTRO      -> "animation." + p + ".blowjobStart";
            case BLOWJOBSUCK       -> "animation." + p + ".blowjobSlowL";
            case BLOWJOBTHRUST     -> "animation." + p + ".blowjobFast";
            case BLOWJOBCUM        -> "animation." + p + ".blowjobCum";
            case PAIZURI_START     -> "animation." + p + ".analStart";
            case PAIZURI_SLOW      -> "animation." + p + ".analSoft";
            case PAIZURI_FAST      -> "animation." + p + ".analHard";
            case PAIZURI_CUM       -> "animation." + p + ".analCum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".mating_press_start";
            case DOGGYSLOW         -> "animation." + p + ".mating_press_slow";
            case DOGGYFAST         -> "animation." + p + ".mating_press_hard";
            case DOGGYCUM          -> "animation." + p + ".mating_press_cum";
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
                owner.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1, false, false));
            }
        } catch (Exception e) {}
    }
}
