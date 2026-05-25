package com.schnurritv.sexmod.entity.bee;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class BeeEntity extends BaseGirlEntity {
    public BeeEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "bee"; }
    @Override public String getNudeGeoFileName() { return "armored"; }

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
            if (owner != null && owner.distanceToSqr(this) < 64) {
                owner.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, false, false));
                owner.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0, false, false));
            }
        } catch (Exception e) {}
    }
}
