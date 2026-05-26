package com.schnurritv.sexmod.entity.galath;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class GalathEntity extends BaseGirlEntity implements PlayerRideableJumping {

    private int energyCooldown = 0;
    // ── Combat grab mechanic ──
    private int grabCooldown = 0;
    private int grabPhaseTicks = 0;
    private SexModAnimation lastGrabAnim = SexModAnimation.NULL;
    private String grabbedPlayerUUID = "";
    private int escapeTaps = 0;
    private static final int GRAB_INTERVAL = 300; // 15 seconds between grab attempts
    private static final int GRAB_RANGE = 3;      // 3 block range for grab
    private static final int ESCAPE_THRESHOLD = 60;
    private static final int ESCAPE_DURATION = 160; // 8 seconds max before forced cum
    private static final int GRAB_CUM_DAMAGE = 8;
    // ── Coin summon/despawn ──
    public int despawnTimer = -1;
    // ── Skeleton summon ──
    private int skeletonSummonCooldown = 0;
    private static final int SKELETON_SUMMON_INTERVAL = 600; // 30 seconds
    // ── Manglelie threesome ──
    public boolean isInThreesome = false;          // part of threesome with Manglelie
    public String threesomePartnerUUID = "";
    private int threesomeTicks = 0;
    private static final int THREESOME_TICK_DELAY = 60;
    // ── Damage tracking for skeleton summon ──
    private int damageAccumulated = 0;
    private static final int DAMAGE_FOR_SKELETONS = 30; // summon skeletons every 30 HP lost

    public GalathEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public boolean needsHouse() { return false; }
    @Override public String getGirlName() { return "galath"; }
    @Override public String getGeoFileName() { return "galath"; }
    @Override public String getNudeGeoFileName() { return "galath_con_mang"; }

    // ── UI scene filtering: Blowjob not in UI (it's a combat grab) ──
    @Override public boolean showStandardBlowjob() { return false; }
    @Override public boolean supportsScene(String name) { return !"Blowjob".equals(name); }

    // Galath: corrupt_* = missionary, shared.bed_* = doggy, pussy_licking_* = boobjob
    // rape_* = combat grab (blowjob slot)
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            // Missionary → corrupt_* (corruption animations)
            case MISSIONARY_START  -> "animation." + p + ".corrupt_intro";
            case MISSIONARY_SLOW   -> "animation." + p + ".corrupt_slow";
            case MISSIONARY_FAST   -> "animation." + p + ".corrupt_hard";
            case MISSIONARY_CUM    -> "animation." + p + ".corrupt_cum";

            // Doggy → shared.bed_* (bed scenes)
            case DOGGYSTART, DOGGYWAIT -> "animation.shared.bed_soft";
            case DOGGYSLOW  -> "animation.shared.bed_slow";
            case DOGGYFAST  -> "animation.shared.bed_fast";
            case DOGGYCUM   -> "animation.shared.bed_cum";

            // Boobjob → pussy_licking_* (face-sitting type)
            case PAIZURI_START -> "animation." + p + ".pussy_licking";
            case PAIZURI_SLOW  -> "animation." + p + ".pussy_licking_forward";
            case PAIZURI_FAST  -> "animation." + p + ".pussy_licking_back";
            case PAIZURI_CUM   -> "animation." + p + ".masterbating_sitting_cum";

            // Combat grab sequences (not shown in UI)
            case BLOWJOBINTRO  -> "animation." + p + ".rape_intro";
            case BLOWJOBSUCK   -> "animation." + p + ".rape1";
            case BLOWJOBTHRUST -> "animation." + p + ".rape2";
            case BLOWJOBCUM    -> "animation." + p + ".rape_cum";

            default -> "animation." + p + ".idle";
        };
    }

    // ── Boss Attributes ──
    public static AttributeSupplier.Builder createBossAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }
    public boolean removeWhenFarAway(double distance) { return false; }
    @Override public int getBaseExperienceReward() { return 50; }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && !this.level().isClientSide) {
            damageAccumulated += (int)amount;
        }
        return result;
    }

    @Override
    public void die(DamageSource source) {
        if (!this.level().isClientSide) {
            this.spawnAtLocation(new ItemStack(ModItems.GALATH_COIN.get(), 1));
            int netherStars = this.random.nextInt(3);
            if (netherStars > 0) this.spawnAtLocation(new ItemStack(Items.NETHER_STAR, netherStars));
        }
        super.die(source);
    }

    @Override
    public boolean showStandardMissionary() { return true; }
    @Override
    public boolean showStandardDoggy() { return true; }
    @Override
    public boolean showStandardBoobjob() { return true; }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;

        // ── Coin despawn timer ──
        if (despawnTimer >= 0) {
            despawnTimer--;
            if (despawnTimer <= 0) {
                // Drop coin if the player didn't have one
                String masterUUID = this.getEntityData().get(MASTER_UUID);
                if (masterUUID == null || masterUUID.isEmpty()) {
                    this.spawnAtLocation(new ItemStack(ModItems.GALATH_COIN.get(), 1));
                }
                this.discard();
                return;
            }
            // Fade out effect: speed up despawn
            if (despawnTimer < 20 && despawnTimer % 4 == 0) {
                for (int i = 0; i < 3; i++) {
                    ((net.minecraft.server.level.ServerLevel)this.level()).sendParticles(
                        net.minecraft.core.particles.ParticleTypes.SMOKE,
                        this.getX() + (this.random.nextDouble() - 0.5) * 1.5,
                        this.getY() + this.random.nextDouble() * 2.0,
                        this.getZ() + (this.random.nextDouble() - 0.5) * 1.5,
                        1, 0, 0, 0, 0);
                }
            }
        }

        // ── Threesome (Manglelie + Galath) ──
        if (isInThreesome && !threesomePartnerUUID.isEmpty()) {
            threesomeTicks++;
            com.schnurritv.sexmod.entity.manglelie.ManglelieEntity mang = findManglelieByUUID(threesomePartnerUUID);
            if (mang == null || !mang.isAlive()) {
                // Partner lost — exit threesome
                isInThreesome = false;
                threesomePartnerUUID = "";
                this.getEntityData().set(IS_LOCKED, false);
                this.getEntityData().set(PARTNER_UUID, "null");
                this.setSexModAnimation(SexModAnimation.NULL);
            } else {
                // Ensure both are locked and facing each other
                this.getEntityData().set(IS_LOCKED, true);
                if (threesomeTicks % 10 == 0 && mang != null) {
                    this.lookAt(mang, 360f, 360f);
                }
            }
        }

        // ── Skeleton summon (triggered by damage taken) ──
        if (damageAccumulated >= DAMAGE_FOR_SKELETONS && skeletonSummonCooldown <= 0) {
            damageAccumulated = 0;
            skeletonSummonCooldown = SKELETON_SUMMON_INTERVAL;
            summonSkeletons();
        }

        // ── Handle active combat grab ──
        if (!grabbedPlayerUUID.isEmpty()) {
            Player grabbed = this.level().getPlayerByUUID(java.util.UUID.fromString(grabbedPlayerUUID));
            if (grabbed == null || !grabbed.isAlive() || grabbed.isCreative() || grabbed.isSpectator()) {
                releaseGrab(null);
                return;
            }

            grabPhaseTicks++;

            if (grabPhaseTicks >= ESCAPE_DURATION) {
                this.setSexModAnimation(SexModAnimation.BLOWJOBCUM);
                grabbed.hurt(this.damageSources().mobAttack(this), GRAB_CUM_DAMAGE);
                grabbed.hurt(this.damageSources().magic(), 4.0f);
                Vec3 kb = grabbed.position().subtract(this.position()).normalize().scale(2.0);
                grabbed.setDeltaMovement(kb.x, 0.5, kb.z);
                grabbed.hurtMarked = true;
                releaseGrab(null);
                return;
            }

            SexModAnimation targetAnim;
            if (grabPhaseTicks < 40) {
                targetAnim = SexModAnimation.BLOWJOBINTRO;
            } else if (grabPhaseTicks < 80) {
                targetAnim = SexModAnimation.BLOWJOBSUCK;
            } else {
                targetAnim = SexModAnimation.BLOWJOBTHRUST;
            }
            if (targetAnim != lastGrabAnim) {
                this.setSexModAnimation(targetAnim);
                lastGrabAnim = targetAnim;
            }

            if (grabPhaseTicks % 20 == 0) {
                grabbed.hurt(this.damageSources().mobAttack(this), 1.0f + this.random.nextFloat());
            }

            Vec3 lockPos = this.position().add(this.getLookAngle().scale(1.0));
            grabbed.teleportTo(lockPos.x, this.getY(), lockPos.z);
            grabbed.setYRot(this.getYRot());
            grabbed.hurtMarked = true;
            return;
        }

        // ── Passive energy wave ──
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (energyCooldown > 0) { energyCooldown--; return; }
        energyCooldown = 200;
        LivingEntity target = this.getTarget();
        if (target == null) return;
        AABB area = this.getBoundingBox().inflate(6.0);
        for (LivingEntity e : this.level().getEntitiesOfClass(LivingEntity.class, area,
                e -> e != this && !(e instanceof BaseGirlEntity))) {
            if (e.getType().getCategory().isFriendly()) continue;
            e.hurt(this.damageSources().magic(), 4.0f);
            e.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1, false, true));
            e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, true));
        }

        // ── Combat grab attempt ──
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (grabCooldown > 0) { grabCooldown--; return; }
        if (this.getTarget() == null) return;

        grabCooldown = GRAB_INTERVAL;
        Player nearestPlayer = null;
        double nearestDist = GRAB_RANGE * GRAB_RANGE;
        for (Player p : this.level().getEntitiesOfClass(Player.class,
                this.getBoundingBox().inflate(GRAB_RANGE))) {
            if (p.isCreative() || p.isSpectator()) continue;
            double d = p.distanceToSqr(this);
            if (d < nearestDist) {
                nearestDist = d;
                nearestPlayer = p;
            }
        }
        if (nearestPlayer != null) {
            startGrab(nearestPlayer);
        }

        // ── Skeleton cooldown tick ──
        if (skeletonSummonCooldown > 0) skeletonSummonCooldown--;

        // ── Auto-summon skeletons when actively fighting (non-player-damage version) ──
        if (this.getTarget() != null && skeletonSummonCooldown <= 0 && this.random.nextFloat() < 0.01f) {
            skeletonSummonCooldown = SKELETON_SUMMON_INTERVAL;
            summonSkeletons();
        }
    }

    /** Summon 2-3 skeletons to fight for Galath */
    private void summonSkeletons() {
        if (this.level().isClientSide) return;
        int count = 2 + this.random.nextInt(2); // 2-3
        for (int i = 0; i < count; i++) {
            net.minecraft.world.entity.monster.Skeleton skeleton =
                net.minecraft.world.entity.EntityType.SKELETON.create(this.level());
            if (skeleton != null) {
                double angle = this.random.nextDouble() * Math.PI * 2;
                double dist = 2.0 + this.random.nextDouble() * 3.0;
                double sx = this.getX() + Math.cos(angle) * dist;
                double sz = this.getZ() + Math.sin(angle) * dist;
                skeleton.setPos(sx, this.getY(), sz);
                // Skeletons attack Galath's target
                LivingEntity target = this.getTarget();
                if (target != null) {
                    skeleton.setTarget(target);
                }
                // Make them not despawn naturally
                skeleton.setPersistenceRequired();
                this.level().addFreshEntity(skeleton);

                // Spawn smoke particles
                if (this.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                    serverLevel.sendParticles(net.minecraft.core.particles.ParticleTypes.SMOKE,
                        sx, this.getY(), sz, 8, 0.3, 0.5, 0.3, 0.05);
                }
            }
        }
        // Visual feedback
        if (!this.level().isClientSide) {
            net.minecraft.server.level.ServerLevel serverLevel = (net.minecraft.server.level.ServerLevel)this.level();
            serverLevel.sendParticles(net.minecraft.core.particles.ParticleTypes.SOUL_FIRE_FLAME,
                this.getX(), this.getY() + 1.5, this.getZ(), 10, 1.0, 0.5, 1.0, 0.1);
        }
    }

    // ── Combat Grab Logic ──
    private void startGrab(Player player) {
        grabbedPlayerUUID = player.getUUID().toString();
        grabPhaseTicks = 0;
        escapeTaps = 0;
        this.getEntityData().set(IS_LOCKED, true);
        this.getEntityData().set(PARTNER_UUID, player.getUUID().toString());
        this.setSexModAnimation(SexModAnimation.BLOWJOBINTRO);
        // Lock player position
        Vec3 lockPos = this.position().add(this.getLookAngle().scale(1.0));
        player.teleportTo(lockPos.x, this.getY(), lockPos.z);
        player.setYRot(this.getYRot());
        player.displayClientMessage(
            net.minecraft.network.chat.Component.literal("§4Galath grabs you! MASH A/D TO ESCAPE!"), true);

        // Open grab escape screen on client
        if (player instanceof ServerPlayer sp) {
            com.schnurritv.sexmod.networking.NetworkHandler.sendToPlayer(
                new com.schnurritv.sexmod.networking.GalathGrabPacket(this.getId(), true), sp);
        }
    }

    /** Called from GalathGrabPacket handler on server when player sends escape taps */
    public void onEscapeTap() {
        if (grabbedPlayerUUID.isEmpty()) return;
        escapeTaps++;
        if (escapeTaps >= ESCAPE_THRESHOLD) {
            Player grabbed = this.level().getPlayerByUUID(java.util.UUID.fromString(grabbedPlayerUUID));
            releaseGrab(grabbed);
        }
    }

    private void releaseGrab(@Nullable Player player) {
        if (player != null) {
            player.displayClientMessage(
                net.minecraft.network.chat.Component.literal("§aYou break free from Galath's grasp!"), true);
            // Light knockback on escape
            Vec3 kb = player.position().subtract(this.position()).normalize().scale(1.5);
            player.setDeltaMovement(kb.x, 0.3, kb.z);
            player.hurtMarked = true;
        }
        grabbedPlayerUUID = "";
        grabPhaseTicks = 0;
        escapeTaps = 0;
        lastGrabAnim = SexModAnimation.NULL;
        this.getEntityData().set(IS_LOCKED, false);
        this.getEntityData().set(PARTNER_UUID, "null");
        this.setSexModAnimation(SexModAnimation.NULL);
        grabCooldown = GRAB_INTERVAL / 2; // Short cooldown after release

        // Close grab screen on client
        if (player instanceof ServerPlayer sp) {
            com.schnurritv.sexmod.networking.NetworkHandler.sendToPlayer(
                new com.schnurritv.sexmod.networking.GalathGrabPacket(this.getId(), false), sp);
        }
    }

    public boolean isGrabbingPlayer() { return !grabbedPlayerUUID.isEmpty(); }

    /** Find a Manglelie entity by its UUID string */
    private com.schnurritv.sexmod.entity.manglelie.ManglelieEntity findManglelieByUUID(String uuid) {
        if (uuid == null || uuid.isEmpty()) return null;
        java.util.UUID target;
        try { target = java.util.UUID.fromString(uuid); }
        catch (IllegalArgumentException e) { return null; }
        for (com.schnurritv.sexmod.entity.manglelie.ManglelieEntity mang :
                this.level().getEntitiesOfClass(com.schnurritv.sexmod.entity.manglelie.ManglelieEntity.class,
                    this.getBoundingBox().inflate(50))) {
            if (mang.getUUID().equals(target) && mang.isAlive()) {
                return mang;
            }
        }
        return null;
    }

    /** Initiate a threesome with a Manglelie partner */
    public void startThreesome(com.schnurritv.sexmod.entity.manglelie.ManglelieEntity mang, Player player) {
        if (this.level().isClientSide) return;
        isInThreesome = true;
        threesomePartnerUUID = mang.getUUID().toString();
        threesomeTicks = 0;

        mang.threesomePartnerUUID = this.getUUID().toString();
        mang.isInThreesome = true;
        mang.threesomeTicks = 0;

        // Lock both entities
        this.getEntityData().set(IS_LOCKED, true);
        this.getEntityData().set(PARTNER_UUID, player.getUUID().toString());
        mang.getEntityData().set(IS_LOCKED, true);
        mang.getEntityData().set(PARTNER_UUID, player.getUUID().toString());

        // Position entities around the player
        Vec3 playerPos = player.position();
        this.teleportTo(playerPos.x - 1, playerPos.y, playerPos.z);
        mang.teleportTo(playerPos.x + 1, playerPos.y, playerPos.z);

        // Face each other
        this.lookAt(mang, 360f, 360f);
        mang.lookAt(this, 360f, 360f);

        // Play animations
        this.setSexModAnimation(SexModAnimation.PAIZURI_SLOW);

        player.displayClientMessage(
            net.minecraft.network.chat.Component.literal("§5Galath and Manglelie begin their dark ritual..."), true);
    }

    // ── Rideable ──
    @Nullable @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof Player player ? player : null;
    }
    @Override public boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty() && passenger instanceof Player;
    }

    @Override
    public void travel(Vec3 pos) {
        if (this.getEntityData().get(IS_LOCKED)) { this.setDeltaMovement(0, 0, 0); return; }
        LivingEntity rider = getControllingPassenger();
        if (rider != null && this.isVehicle()) {
            float forward = rider.zza;
            float strafe = rider.xxa;
            boolean sneak = rider.isShiftKeyDown();
            this.setYRot(rider.getYRot());
            this.yRotO = this.getYRot(); this.yBodyRot = this.getYRot(); this.yHeadRot = this.getYRot();
            Vec3 lookVec = rider.getLookAngle();
            double hSpeed = 0.5D, vSpeed = 0.3D;
            double mx = 0, my = 0, mz = 0;
            if (strafe != 0) { mx = -lookVec.z * hSpeed * strafe; mz = lookVec.x * hSpeed * strafe; }
            if (forward != 0) { mx += lookVec.x * hSpeed * forward; mz += lookVec.z * hSpeed * forward; }
            if (this.jumping) { my = vSpeed; this.jumping = false; }
            else if (sneak) { my = -vSpeed; }
            this.setDeltaMovement(mx, my, mz);
            this.hasImpulse = true; this.fallDistance = 0;
            super.travel(Vec3.ZERO);
        } else { super.travel(pos); }
    }
    @Override public boolean isNoGravity() { return this.getControllingPassenger() != null || super.isNoGravity(); }
    @Override protected float getRiddenSpeed(Player player) { return 1.0f; }
    @Override public Vec3 getPassengerRidingPosition(Entity passenger) { return new Vec3(0.0, 0.8D, 0.0); }
    @Override public boolean canJump() { return true; }
    @Override public void onPlayerJump(int jumpPower) { this.jumping = true; }
    @Override public void handleStartJump(int jumpPower) { this.jumping = true; }
    @Override public void handleStopJump() {}
    @Override public int getJumpCooldown() { return 0; }
}
