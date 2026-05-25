package com.schnurritv.sexmod.entity.ellie;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
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
    @Override public String getGeoFileName() { return "dressed"; }
    @Override public String getNudeGeoFileName() { return "nude"; }

    // Ellie: missionary_* + carry_*/cowgirl_* (no blowjob/paizuri/doggy standard names)
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START  -> "animation." + p + ".missionary_start";
            case MISSIONARY_SLOW   -> "animation." + p + ".missionary_slow";
            case MISSIONARY_FAST   -> "animation." + p + ".missionary_fast";
            case MISSIONARY_CUM    -> "animation." + p + ".missionary_cum";
            case BLOWJOBINTRO      -> "animation." + p + ".carry_intro";
            case BLOWJOBSUCK       -> "animation." + p + ".carry_slow1";
            case BLOWJOBTHRUST     -> "animation." + p + ".carry_fast";
            case BLOWJOBCUM        -> "animation." + p + ".carry_cum";
            case PAIZURI_START     -> "animation." + p + ".cowgirlstart";
            case PAIZURI_SLOW      -> "animation." + p + ".cowgirlslow2";
            case PAIZURI_FAST      -> "animation." + p + ".cowgirlfast";
            case PAIZURI_CUM       -> "animation." + p + ".cowgirlcum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".carry_intro";
            case DOGGYSLOW         -> "animation." + p + ".carry_slow2";
            case DOGGYFAST         -> "animation." + p + ".carry_fast";
            case DOGGYCUM          -> "animation." + p + ".carry_cum";
            default -> "animation." + p + ".idle";
        };
    }

    private int combatCooldown = 0;
    @Override public void tick() {
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
