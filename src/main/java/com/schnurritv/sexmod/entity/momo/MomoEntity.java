package com.schnurritv.sexmod.entity.momo;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MomoEntity extends BaseGirlEntity {
    public MomoEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "momo"; }

    private int scholarCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (scholarCooldown > 0) { scholarCooldown--; return; }
        scholarCooldown = 100; // Every 5 seconds

        // Momo's Scholar Aura: generates XP for owner
        String uuid = this.entityData.get(MASTER_UUID);
        if (uuid.isEmpty()) return;
        try {
            Player owner = this.level().getPlayerByUUID(java.util.UUID.fromString(uuid));
            if (owner != null && owner.distanceToSqr(this) < 64) {
                this.level().addFreshEntity(new ExperienceOrb(this.level(),
                    this.getX(), this.getY() + 1.5, this.getZ(), 1));
            }
        } catch (Exception e) {}
    }
}
