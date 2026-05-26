package com.schnurritv.sexmod.entity.jenny;
import com.schnurritv.sexmod.entity.SexFighterEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class JennyEntity extends SexFighterEntity {
    public JennyEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "jenny"; }
    @Override public String getGeoFileName() { return "jennydressed"; }
    @Override public String getNudeGeoFileName() { return "jennynude"; }

    // Jenny animations: blowjob*, paizuri_*, doggy*, fastwalk, walk, idle, etc.
    // Note: Jenny has no missionary_* animations; uses doggy variations as fallback
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            // Missionary → reuse doggy animations (Jenny has no missionary_*)
            case MISSIONARY_START  -> "animation." + p + ".doggystart";
            case MISSIONARY_SLOW   -> "animation." + p + ".doggyslow";
            case MISSIONARY_FAST   -> "animation." + p + ".doggyfast_soft";
            case MISSIONARY_CUM    -> "animation." + p + ".doggycum";
            // Blowjob
            case BLOWJOBINTRO   -> "animation." + p + ".blowjobintro";
            case BLOWJOBSUCK    -> "animation." + p + ".blowjobsuck";
            case BLOWJOBTHRUST  -> "animation." + p + ".blowjobthrust";
            case BLOWJOBCUM     -> "animation." + p + ".blowjobcum";
            // Paizuri
            case PAIZURI_START  -> "animation." + p + ".paizuri_start";
            case PAIZURI_SLOW   -> "animation." + p + ".paizuri_slow";
            case PAIZURI_FAST   -> "animation." + p + ".paizuri_fast";
            case PAIZURI_CUM    -> "animation." + p + ".paizuri_cum";
            // Doggy
            case DOGGYSTART     -> "animation." + p + ".doggystart";
            case DOGGYGOONBED   -> "animation." + p + ".doggygoonbed";
            case DOGGYWAIT      -> "animation." + p + ".doggywait";
            case DOGGYSLOW      -> "animation." + p + ".doggyslow";
            case DOGGYFAST      -> "animation." + p + ".doggyfast_hard";
            case DOGGYCUM       -> "animation." + p + ".doggycum";
            default -> "animation." + p + ".idle";
        };
    }

    // Jenny animations are much longer; use correct durations
    @Override
    public int getAnimationTickLength(SexModAnimation anim) {
        return switch (anim) {
            case MISSIONARY_START -> 198; // doggystart
            case MISSIONARY_SLOW  -> 46;  // doggyslow
            case MISSIONARY_FAST  -> 22;  // doggyfast_soft
            case MISSIONARY_CUM   -> 134; // doggycum
            case BLOWJOBINTRO     -> 986;
            case BLOWJOBSUCK      -> 121;
            case BLOWJOBTHRUST    -> 35;
            case BLOWJOBCUM       -> 236;
            case PAIZURI_START    -> 173;
            case PAIZURI_SLOW     -> 68;
            case PAIZURI_FAST     -> 22;
            case PAIZURI_CUM      -> 230;
            case DOGGYSTART, DOGGYGOONBED -> 198;
            case DOGGYWAIT        -> 129;
            case DOGGYSLOW        -> 46;
            case DOGGYFAST        -> 22;
            case DOGGYCUM         -> 134;
            default -> super.getAnimationTickLength(anim);
        };
    }
}
