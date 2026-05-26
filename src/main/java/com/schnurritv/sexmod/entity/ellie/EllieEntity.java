package com.schnurritv.sexmod.entity.ellie;
import com.schnurritv.sexmod.entity.SexFighterEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class EllieEntity extends SexFighterEntity {
    public EllieEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "ellie"; }
    @Override public String getGeoFileName() { return "dressed"; }
    @Override public String getNudeGeoFileName() { return "nude"; }

    // Ellie: missionary_* + carry_*/cowgirl_* (no blowjob/paizuri/doggy standard names)
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START  -> "animation." + p + ".missionary_start";
            case MISSIONARY_SLOW   -> "animation." + p + ".missionary_slow";
            case MISSIONARY_FAST   -> "animation." + p + ".missionary_fast";
            case MISSIONARY_CUM    -> "animation." + p + ".missionary_cum";
            case BLOWJOBINTRO      -> "animation." + p + ".carry_intro";
            case BLOWJOBSUCK       -> "animation." + p + ".carry_slow1";
            case BLOWJOBTHRUST     -> "animation." + p + ".carry_fast";
            case BLOWJOBCUM        -> "animation." + p + ".carry_cum";
            case PAIZURI_START     -> "animation." + p + ".cowgirlstart";
            case PAIZURI_SLOW      -> "animation." + p + ".cowgirlslow2";
            case PAIZURI_FAST      -> "animation." + p + ".cowgirlfast";
            case PAIZURI_CUM       -> "animation." + p + ".cowgirlcum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".carry_intro";
            case DOGGYSLOW         -> "animation." + p + ".carry_slow2";
            case DOGGYFAST         -> "animation." + p + ".carry_fast";
            case DOGGYCUM          -> "animation." + p + ".carry_cum";
            default -> "animation." + p + ".idle";
        };
    }
}
