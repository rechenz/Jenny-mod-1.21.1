package com.schnurritv.sexmod.entity.mika;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class MikaEntity extends BaseGirlEntity {
    public MikaEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "mika"; }

    private int luckCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (luckCooldown > 0) { luckCooldown--; return; }
        luckCooldown = 300; // Every 15 seconds

        // Mika's Fortune Blessing: gives Luck to nearby players
        for (Player p : this.level().players()) {
            if (p.distanceToSqr(this) < 81) {
                p.addEffect(new MobEffectInstance(MobEffects.LUCK, 400, 0, false, false));
            }
        }
    }
}
