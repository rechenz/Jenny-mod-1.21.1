package com.schnurritv.sexmod.entity.cat;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.entity.ai.ShipSeekGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CatEntity extends BaseGirlEntity {
    // Initialized in registerGoals() because super()→Mob calls registerGoals() before our constructor body
    private ShipSeekGoal shipSeekGoal;

    public CatEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override public boolean needsHouse() { return false; }
    @Override public String getGirlName() { return "cat"; }

    @Override
    public String getGeoFileName() { return "cat"; }
    @Override public String getNudeGeoFileName() { return "cat"; }

    public ShipSeekGoal getShipSeekGoal() { return shipSeekGoal; }

    @Override
    protected void registerGoals() {
        // Must create shipSeekGoal HERE (not in constructor), because super() calls registerGoals() before our fields are set
        this.shipSeekGoal = new ShipSeekGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, shipSeekGoal);
        this.goalSelector.addGoal(2, new com.schnurritv.sexmod.entity.ai.SexModMoveToTargetGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new com.schnurritv.sexmod.entity.ai.SexModFollowGoal(this, 1.25D, 10.0F, 2.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override public boolean supportsScene(String name) {
        return name.equals("Missionary") || name.equals("Blowjob");
    }

    @Override public boolean hasSingleUnifiedScene() { return false; }

    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String prefix = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START, MISSIONARY_SLOW   -> "animation." + prefix + ".sitting_slow";
            case MISSIONARY_FAST                     -> "animation." + prefix + ".sitting_fast";
            case MISSIONARY_CUM                      -> "animation." + prefix + ".sitting_cum";
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

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (shipSeekGoal != null) {
            compound.put("ShipSeekData", shipSeekGoal.saveToNBT());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (shipSeekGoal != null && compound.contains("ShipSeekData")) {
            shipSeekGoal.loadFromNBT(compound.getCompound("ShipSeekData"));
        }
    }
}
