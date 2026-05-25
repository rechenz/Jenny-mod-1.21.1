package com.schnurritv.sexmod.entity.manglelie;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;

public class ManglelieEntity extends BaseGirlEntity {
    public ManglelieEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "manglelie"; }
    @Override public String getGeoFileName() { return "manglelie"; }
    @Override public String getNudeGeoFileName() { return "manglelie"; }

    private int corruptionCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (corruptionCooldown > 0) { corruptionCooldown--; return; }
        corruptionCooldown = 200; // Every 10 seconds

        // Manglelie's Corrupted Presence: drops bonus XP orbs nearby
        if (this.random.nextFloat() < 0.3f) {
            double x = this.getX() + (this.random.nextDouble() - 0.5) * 4;
            double y = this.getY() + 1.0;
            double z = this.getZ() + (this.random.nextDouble() - 0.5) * 4;
            this.level().addFreshEntity(new ExperienceOrb(this.level(), x, y, z, 3 + this.random.nextInt(5)));
        }

        // Also slow enemies near her
        for (Player p : this.level().players()) {
            if (p.distanceToSqr(this) < 64) {
                p.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 0, false, false));
            }
        }
    }
}
