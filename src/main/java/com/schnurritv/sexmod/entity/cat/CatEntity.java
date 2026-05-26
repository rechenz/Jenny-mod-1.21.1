package com.schnurritv.sexmod.entity.cat;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.entity.SexFighterEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.entity.ai.ShipSeekGoal;
import com.schnurritv.sexmod.networking.NetworkHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CatEntity extends SexFighterEntity {
    private static final EntityDataAccessor<String> HEAD_PAT_PLAYER =
        SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.STRING);
    private static final int HEAD_PAT_TICKS = 112;
    private static final int HEAD_PAT_AFFECTION = 3;
    private int headPatTimer = 0;

    private ShipSeekGoal shipSeekGoal;

    public CatEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HEAD_PAT_PLAYER, "");
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

    @Override public boolean showStandardBoobjob() { return false; }
    @Override public boolean showStandardDoggy() { return false; }

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
            case PAIZURI_START, PAIZURI_SLOW -> "animation." + prefix + ".sitting_slow";
            case PAIZURI_FAST -> "animation." + prefix + ".sitting_fast";
            case PAIZURI_CUM -> "animation." + prefix + ".sitting_cum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT ->
                "animation." + prefix + ".idle";
            default -> "animation." + prefix + ".idle";
        };
    }

    // ── Head Pat System ──

    /** Start head pat animation for the given player */
    public void startHeadPat(Player player) {
        if (this.level().isClientSide) return;
        this.entityData.set(HEAD_PAT_PLAYER, player.getUUID().toString());
        this.entityData.set(SexEntity.IS_LOCKED, true);
        this.getNavigation().stop();

        // Face the player
        double dx = player.getX() - this.getX();
        double dz = player.getZ() - this.getZ();
        this.setYRot((float) (Math.atan2(dz, dx) * 180.0 / Math.PI) - 90.0f);
        this.yRotO = this.getYRot();

        headPatTimer = 0;
    }

    public boolean isPlayingHeadPat() {
        return !this.entityData.get(HEAD_PAT_PLAYER).isEmpty();
    }

    public String getHeadPatPlayerUUID() {
        return this.entityData.get(HEAD_PAT_PLAYER);
    }

    private void handleHeadPatTick() {
        if (!isPlayingHeadPat()) return;

        if (headPatTimer >= HEAD_PAT_TICKS) {
            // Animation finished — give affection
            String playerUUID = getHeadPatPlayerUUID();
            if (!playerUUID.isEmpty() && !this.level().isClientSide) {
                Player player = this.level().getPlayerByUUID(java.util.UUID.fromString(playerUUID));
                if (player instanceof ServerPlayer sp) {
                    getAffectionData().addAffection(HEAD_PAT_AFFECTION, 100);
                    sp.displayClientMessage(
                        Component.literal("<" + getGirlName() + "> Purrr~ ♥"), false);
                }
            }
            // Clean up
            this.entityData.set(HEAD_PAT_PLAYER, "");
            this.entityData.set(SexEntity.IS_LOCKED, false);
            headPatTimer = 0;
        } else {
            headPatTimer++;
        }
    }

    @Override
    public void tick() {
        super.tick();
        // Run head pat timer
        if (isPlayingHeadPat()) {
            handleHeadPatTick();
        }
    }

    @Override
    public String getIdleAnimationPath() {
        if (isPlayingHeadPat()) {
            return "animation.cat.head_pat";
        }
        return "animation.cat.idle2";
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("headPatTimer", headPatTimer);
        if (isPlayingHeadPat()) {
            compound.putString("headPatPlayer", getHeadPatPlayerUUID());
        }
        if (shipSeekGoal != null) {
            compound.put("ShipSeekData", shipSeekGoal.saveToNBT());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("headPatTimer")) {
            headPatTimer = compound.getInt("headPatTimer");
        }
        if (compound.contains("headPatPlayer")) {
            this.entityData.set(HEAD_PAT_PLAYER, compound.getString("headPatPlayer"));
        }
        if (shipSeekGoal != null && compound.contains("ShipSeekData")) {
            shipSeekGoal.loadFromNBT(compound.getCompound("ShipSeekData"));
        }
    }
}
