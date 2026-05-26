package com.schnurritv.sexmod.entity.bia;
import com.schnurritv.sexmod.entity.SexFighterEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class BiaEntity extends SexFighterEntity {
    public BiaEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "bia"; }
    @Override public String getGeoFileName() { return "biadressed"; }
    @Override public String getNudeGeoFileName() { return "bianude"; }

    // Bia: prone_doggy_* + anal_*
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START  -> "animation." + p + ".prone_doggy_intro";
            case MISSIONARY_SLOW   -> "animation." + p + ".prone_doggy_soft";
            case MISSIONARY_FAST   -> "animation." + p + ".prone_doggy_hard1";
            case MISSIONARY_CUM    -> "animation." + p + ".prone_doggy_cum";
            case BLOWJOBINTRO      -> "animation." + p + ".anal_start";
            case BLOWJOBSUCK       -> "animation." + p + ".anal_slow";
            case BLOWJOBTHRUST     -> "animation." + p + ".anal_fast";
            case BLOWJOBCUM        -> "animation." + p + ".anal_cum";
            case PAIZURI_START     -> "animation." + p + ".anal_prepare";
            case PAIZURI_SLOW      -> "animation." + p + ".anal_slow";
            case PAIZURI_FAST      -> "animation." + p + ".anal_fast";
            case PAIZURI_CUM       -> "animation." + p + ".anal_cum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".prone_doggy_insert";
            case DOGGYSLOW         -> "animation." + p + ".prone_doggy_soft";
            case DOGGYFAST         -> "animation." + p + ".prone_doggy_hard2";
            case DOGGYCUM          -> "animation." + p + ".prone_doggy_cum";
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
            if (owner != null && owner.distanceToSqr(this) < 100) {
                owner.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0, false, false));
            }
        } catch (Exception e) {}
    }
}
