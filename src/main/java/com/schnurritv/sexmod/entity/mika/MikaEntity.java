package com.schnurritv.sexmod.entity.mika;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class MikaEntity extends BaseGirlEntity {
    public MikaEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public boolean needsHouse() { return false; }
    @Override public String getGirlName() { return "mika"; }
    @Override public String getGeoFileName() { return "dressed"; }
    @Override public String getNudeGeoFileName() { return "nude"; }

    // Same animation structure as Lucy: animation.default.* prefix
    @Override public String getAnimationPrefix() { return "default"; }

    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START                     -> "animation." + p + ".missionary_start";
            case MISSIONARY_SLOW                      -> "animation." + p + ".missionary_slow";
            case MISSIONARY_FAST                      -> "animation." + p + ".missionary_fast";
            case MISSIONARY_CUM                       -> "animation." + p + ".missionary_cum";
            case BLOWJOBINTRO                   -> "animation." + p + ".blowjob_intro";
            case BLOWJOBSUCK                    -> "animation." + p + ".blowjob_slow";
            case BLOWJOBTHRUST                  -> "animation." + p + ".blowjob_fast";
            case BLOWJOBCUM                     -> "animation." + p + ".blowjob_cum";
            case PAIZURI_START                  -> "animation." + p + ".paizuri_intro";
            case PAIZURI_SLOW                   -> "animation." + p + ".paizuri_slow";
            case PAIZURI_FAST                   -> "animation." + p + ".paizuri_fast";
            case PAIZURI_CUM                    -> "animation." + p + ".paizuri_cum";
            case DOGGYSTART                     -> "animation." + p + ".doggy_intro";
            case DOGGYGOONBED                   -> "animation." + p + ".doggy_lay_on_bed";
            case DOGGYWAIT                      -> "animation." + p + ".doggy_bed_idle";
            case DOGGYSLOW                      -> "animation." + p + ".doggy_slow";
            case DOGGYFAST                      -> "animation." + p + ".doggy_fast1";
            case DOGGYCUM                       -> "animation." + p + ".doggy_cum";
            default -> "animation." + p + ".idle";
        };
    }

    @Override
    public int getAnimationTickLength(SexModAnimation anim) {
        return switch (anim) {
            case MISSIONARY_START -> 65;
            case MISSIONARY_SLOW  -> 58;
            case MISSIONARY_FAST  -> 38;
            case MISSIONARY_CUM   -> 289;
            case BLOWJOBINTRO     -> 200;
            case BLOWJOBSUCK      -> 60;
            case BLOWJOBTHRUST    -> 40;
            case BLOWJOBCUM       -> 240;
            case PAIZURI_START    -> 200;
            case PAIZURI_SLOW     -> 70;
            case PAIZURI_FAST     -> 40;
            case PAIZURI_CUM      -> 240;
            case DOGGYSTART, DOGGYSLOW -> 50;
            case DOGGYFAST        -> 30;
            case DOGGYCUM         -> 140;
            default -> super.getAnimationTickLength(anim);
        };
    }

    private int luckCooldown = 0;
    @Override public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (luckCooldown > 0) { luckCooldown--; return; }
        luckCooldown = 300;
        for (Player p : this.level().players()) {
            if (p.distanceToSqr(this) < 81) {
                p.addEffect(new MobEffectInstance(MobEffects.LUCK, 400, 0, false, false));
            }
        }
    }
}
