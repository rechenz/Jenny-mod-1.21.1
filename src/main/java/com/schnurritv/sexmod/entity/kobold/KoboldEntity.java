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
    @Override public String getGeoFileName() { return "armored"; }
    @Override public String getNudeGeoFileName() { return "armored"; }

    // Kobold: blowjob* + mating_press_* + anal* animations
    // Scene mapping:
    //   Blowjob → blowjobStart/SlowL/Fast/Cum
    //   Missionary → mating_press_start/soft/hard/cum
    //   Doggy → analStart/Soft/Hard/Cum
    //   Boobjob → ??? reuse mating_press variants (kobold has no dedicated paizuri)
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            // Missionary → mating_press (on-back breeding)
            case MISSIONARY_START  -> "animation." + p + ".mating_press_start";
            case MISSIONARY_SLOW   -> "animation." + p + ".mating_press_soft";
            case MISSIONARY_FAST   -> "animation." + p + ".mating_press_hard";
            case MISSIONARY_CUM    -> "animation." + p + ".mating_press_cum";

            // Blowjob → blowjobStart/SlowL/Fast/Cum
            case BLOWJOBINTRO      -> "animation." + p + ".blowjobStart";
            case BLOWJOBSUCK       -> "animation." + p + ".blowjobSlowL";
            case BLOWJOBTHRUST     -> "animation." + p + ".blowjobFast";
            case BLOWJOBCUM        -> "animation." + p + ".blowjobCum";

            // Doggy → analStart/Soft/Hard/Cum
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".analStart";
            case DOGGYSLOW         -> "animation." + p + ".analSoft";
            case DOGGYFAST         -> "animation." + p + ".analHard";
            case DOGGYCUM          -> "animation." + p + ".analCum";

            // Boobjob → mating_press (kobold has no dedicated paizuri, reuse mating_press)
            case PAIZURI_START     -> "animation." + p + ".mating_press_start";
            case PAIZURI_SLOW      -> "animation." + p + ".mating_press_soft";
            case PAIZURI_FAST      -> "animation." + p + ".mating_press_hard";
            case PAIZURI_CUM       -> "animation." + p + ".mating_press_cum";

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
