package com.schnurritv.sexmod.entity.galath;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.AABB;

public class GalathEntity extends BaseGirlEntity {
    public GalathEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "galath"; }
    @Override public String getGeoFileName() { return "galath"; }
    @Override public String getNudeGeoFileName() { return "galath_con_mang"; }

    private int energyCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (energyCooldown > 0) { energyCooldown--; return; }
        energyCooldown = 120; // Every 6 seconds

        // Galath's Void Energy: damages nearby hostile mobs
        AABB area = this.getBoundingBox().inflate(8.0);
        for (LivingEntity e : this.level().getEntitiesOfClass(LivingEntity.class, area,
                e -> e != this && !(e instanceof Player) && !(e instanceof BaseGirlEntity))) {
            if (e.getType().getCategory().isFriendly()) continue;
            e.hurt(this.damageSources().magic(), 2.0f);
            e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, false, false));
        }
    }
}
