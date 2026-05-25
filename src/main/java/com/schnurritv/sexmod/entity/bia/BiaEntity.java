package com.schnurritv.sexmod.entity.bia;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class BiaEntity extends BaseGirlEntity {
    public BiaEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "bia"; }
    @Override public String getGeoFileName() { return "biadressed"; }
    @Override public String getNudeGeoFileName() { return "bianude"; }

    private int buffCooldown = 0;

    @Override
    public void tick() {
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
