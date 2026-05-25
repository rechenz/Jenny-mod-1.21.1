package com.schnurritv.sexmod.entity.allie;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class AllieEntity extends BaseGirlEntity {
    public AllieEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "allie"; }
    @Override public String getNudeGeoFileName() { return "armored"; }

    // Allie: deepthroat_* + reverse_cowgirl_*
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START  -> "animation." + p + ".reverse_cowgirl_start";
            case MISSIONARY_SLOW   -> "animation." + p + ".reverse_cowgirl_slow1";
            case MISSIONARY_FAST   -> "animation." + p + ".reverse_cowgirl_fasts";
            case MISSIONARY_CUM    -> "animation." + p + ".reverse_cowgirl_cum";
            case BLOWJOBINTRO      -> "animation." + p + ".deepthroat_start";
            case BLOWJOBSUCK       -> "animation." + p + ".deepthroat_slow";
            case BLOWJOBTHRUST     -> "animation." + p + ".deepthroat_fast";
            case BLOWJOBCUM        -> "animation." + p + ".deepthroat_cum";
            case PAIZURI_START     -> "animation." + p + ".deepthroat_prepare";
            case PAIZURI_SLOW      -> "animation." + p + ".deepthroat_slow";
            case PAIZURI_FAST      -> "animation." + p + ".deepthroat_fast";
            case PAIZURI_CUM       -> "animation." + p + ".deepthroat_cum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".reverse_cowgirl_start";
            case DOGGYSLOW         -> "animation." + p + ".reverse_cowgirl_slow2";
            case DOGGYFAST         -> "animation." + p + ".reverse_cowgirl_fastc1";
            case DOGGYCUM          -> "animation." + p + ".reverse_cowgirl_cum";
            default -> "animation." + p + ".idle";
        };
    }

    private int healCooldown = 0;
    @Override public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (healCooldown > 0) { healCooldown--; return; }
        healCooldown = 100;
        for (Player p : this.level().players()) {
            if (p.distanceToSqr(this) < 64) {
                if (p.getHealth() < p.getMaxHealth()) {
                    p.heal(1.0f);
                    p.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0, false, false));
                }
            }
        }
    }
}
