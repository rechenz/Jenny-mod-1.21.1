package com.schnurritv.sexmod.entity.lucy;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class LucyEntity extends BaseGirlEntity {
    public LucyEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "lucy"; }

    private int careCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (careCooldown > 0) { careCooldown--; return; }
        careCooldown = 80; // Every 4 seconds

        // Lucy's Caretaker: gives saturation to nearby players
        for (Player p : this.level().players()) {
            if (p.distanceToSqr(this) < 100) {
                p.addEffect(new MobEffectInstance(MobEffects.SATURATION, 20, 0, false, false));
                p.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0, false, false));
            }
        }
    }
}
