package com.schnurritv.sexmod.entity.allie;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class AllieEntity extends BaseGirlEntity {
    public AllieEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "allie"; }
    @Override public String getNudeGeoFileName() { return "armored"; }

    private int healCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (healCooldown > 0) { healCooldown--; return; }
        healCooldown = 100;
        for (Player p : this.level().players()) {
            if (p.distanceToSqr(this) < 64) {
                if (p.getHealth() < p.getMaxHealth()) {
                    p.heal(1.0f);
                    p.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0, false, false));
                }
            }
        }
    }
}
