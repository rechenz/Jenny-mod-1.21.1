package com.schnurritv.sexmod.entity.momo;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MomoEntity extends BaseGirlEntity {
    public MomoEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "momo"; }
    @Override public String getGeoFileName() { return "dressed"; }
    @Override public String getNudeGeoFileName() { return "nude"; }

    // Same animation structure as Lucy/Mika: animation.default.* prefix
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

    private int scholarCooldown = 0;
    @Override public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (scholarCooldown > 0) { scholarCooldown--; return; }
        scholarCooldown = 100;
        String uuid = this.entityData.get(MASTER_UUID);
        if (uuid.isEmpty()) return;
        try {
            Player owner = this.level().getPlayerByUUID(java.util.UUID.fromString(uuid));
            if (owner != null && owner.distanceToSqr(this) < 64) {
                this.level().addFreshEntity(new ExperienceOrb(this.level(),
                    this.getX(), this.getY() + 1.5, this.getZ(), 1));
            }
        } catch (Exception e) {}
    }
}
