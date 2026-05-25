package com.schnurritv.sexmod.entity.ellie;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.SexModConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class EllieEntity extends BaseGirlEntity {
    public EllieEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "ellie"; }

    private int combatCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide || !SexModConfig.GIRLS_FIGHT_HOSTILES.get()) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (combatCooldown > 0) { combatCooldown--; return; }
        combatCooldown = 40;

        Player owner = findOwner();
        if (owner == null) return;

        AABB scanArea = owner.getBoundingBox().inflate(12);
        for (LivingEntity target : this.level().getEntitiesOfClass(LivingEntity.class, scanArea,
                e -> e instanceof Monster && e.isAlive() && e.distanceToSqr(owner) < 144)) {
            this.setTarget(target);
            double dmg = SexModConfig.GIRL_ATTACK_DAMAGE.get();
            target.hurt(this.damageSources().mobAttack(this), (float) dmg);
            break;
        }
    }

    private Player findOwner() {
        String uuid = this.entityData.get(MASTER_UUID);
        if (uuid.isEmpty()) return null;
        try { return this.level().getPlayerByUUID(java.util.UUID.fromString(uuid)); }
        catch (Exception e) { return null; }
    }
}
