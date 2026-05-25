package com.schnurritv.sexmod.entity.jenny;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class JennyEntity extends BaseGirlEntity {
    public JennyEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "jenny"; }
    @Override public String getGeoFileName() { return "jennydressed"; }
    @Override public String getNudeGeoFileName() { return "jennynude"; }

    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START, MISSIONARY_SLOW  -> "animation.ellie.missionary_slow";
            case MISSIONARY_FAST                    -> "animation." + p + ".fastwalk";
            case MISSIONARY_CUM                     -> "animation." + p + ".doggycum";
            // blowjob* names match exactly (lowercase)
            case BLOWJOBINTRO   -> "animation." + p + ".blowjobintro";
            case BLOWJOBSUCK    -> "animation." + p + ".blowjobsuck";
            case BLOWJOBTHRUST  -> "animation." + p + ".blowjobthrust";
            case BLOWJOBCUM     -> "animation." + p + ".blowjobcum";
            // paizuri* names match
            case PAIZURI_START  -> "animation." + p + ".paizuri_start";
            case PAIZURI_SLOW   -> "animation." + p + ".paizuri_slow";
            case PAIZURI_FAST   -> "animation." + p + ".paizuri_fast";
            case PAIZURI_CUM    -> "animation." + p + ".paizuri_cum";
            // doggy*
            case DOGGYSTART, DOGGYSLOW     -> "animation." + p + ".doggyslow";
            case DOGGYGOONBED              -> "animation." + p + ".doggygoonbed";
            case DOGGYWAIT                 -> "animation." + p + ".doggywait";
            case DOGGYFAST                 -> "animation." + p + ".doggyfast_hard";
            case DOGGYCUM                  -> "animation." + p + ".doggycum";
            default -> "animation." + p + ".idle";
        };
    }
}
