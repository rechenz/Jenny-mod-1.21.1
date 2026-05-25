package com.schnurritv.sexmod.entity.cat;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

/**
 * Cat aka Luna — Ship-dwelling feline.
 * Loves fish, spawns near oceans, has unique sitting/touch animation set.
 */
public class CatEntity extends BaseGirlEntity {
    public CatEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "cat"; }

    @Override
    public String getGeoFileName() { return "cat"; }
    @Override public String getNudeGeoFileName() { return "cat"; }

    // Cat uses "animation.cat.*" prefix (same as modelName) — OK

    @Override public boolean supportsScene(String name) {
        return name.equals("Missionary") || name.equals("Blowjob");
    }

    @Override public boolean hasSingleUnifiedScene() { return false; }

    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String prefix = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START, MISSIONARY_SLOW   -> "animation." + prefix + ".sitting_slow";
            case MISSIONARY_FAST                      -> "animation." + prefix + ".sitting_fast";
            case MISSIONARY_CUM                       -> "animation." + prefix + ".sitting_cum";
            case BLOWJOBINTRO                        -> "animation." + prefix + ".touch_boobs_intro";
            case BLOWJOBSUCK                         -> "animation." + prefix + ".touch_boobs_slow";
            case BLOWJOBTHRUST                       -> "animation." + prefix + ".touch_boobs_fast";
            case BLOWJOBCUM                          -> "animation." + prefix + ".touch_boobs_cum";
            case PAIZURI_START, DOGGYSTART, DOGGYGOONBED, DOGGYWAIT ->
                "animation." + prefix + ".idle";
            default -> "animation." + prefix + ".idle";
        };
    }

    @Override
    public String getIdleAnimationPath() {
        return "animation.cat.idle2";
    }
}
