package com.schnurritv.sexmod.entity.slime;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class SlimeEntity extends BaseGirlEntity {
    public SlimeEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public boolean needsHouse() { return false; }
    @Override public String getGirlName() { return "slime"; }
    @Override public String getGeoFileName() { return "dressed"; }
    @Override public String getNudeGeoFileName() { return "nude"; }

    // Slime: blowjob* + doggy* (no missionary/paizuri)
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START, BLOWJOBINTRO  -> "animation." + p + ".doggystart";
            case MISSIONARY_SLOW, BLOWJOBSUCK    -> "animation." + p + ".doggyslow";
            case MISSIONARY_FAST, BLOWJOBTHRUST  -> "animation." + p + ".doggyfast";
            case MISSIONARY_CUM, BLOWJOBCUM      -> "animation." + p + ".doggycum";
            case PAIZURI_START                   -> "animation." + p + ".blowjobstart";
            case PAIZURI_SLOW                    -> "animation." + p + ".blowjobsuck";
            case PAIZURI_FAST                    -> "animation." + p + ".blowjobthrust";
            case PAIZURI_CUM                     -> "animation." + p + ".blowjobcum";
            case DOGGYSTART, DOGGYGOONBED        -> "animation." + p + ".doggygoonbed";
            case DOGGYWAIT                       -> "animation." + p + ".doggywait";
            case DOGGYSLOW                       -> "animation." + p + ".doggyslow";
            case DOGGYFAST                       -> "animation." + p + ".doggyfast";
            case DOGGYCUM                        -> "animation." + p + ".doggycum";
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
            if (owner != null && owner.distanceToSqr(this) < 64) {
                owner.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 1, false, false));
            }
        } catch (Exception e) {}
    }
}
