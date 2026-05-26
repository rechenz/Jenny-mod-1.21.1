package com.schnurritv.sexmod.entity.allie;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.Minecraft;

import java.util.Random;
import java.util.UUID;

public class AllieEntity extends BaseGirlEntity {
    private static final Random RAND = new Random();

    // ── Data syncer: stores the summoning item (or UUID reference) ──
    protected static final EntityDataAccessor<String> SUMMON_OWNER = SynchedEntityData.defineId(AllieEntity.class, EntityDataSerializers.STRING);
    // sexmodUses field from the summoning item (synced once to client)
    protected static final EntityDataAccessor<Integer> SEXMOD_USES = SynchedEntityData.defineId(AllieEntity.class, EntityDataSerializers.INT);
    // Sync flag: rich animation finished on server → signal client to play effects
    protected static final EntityDataAccessor<Boolean> RICH_ANIM_FINISHED = SynchedEntityData.defineId(AllieEntity.class, EntityDataSerializers.BOOLEAN);

    // ── Client-side state flags ──
    /** True while the summon dialogue chain is still running (right-click should not re-open) */
    private boolean summonedByPlayer = false;
    /** True for the first summon tick – triggers portal burst */
    private boolean firstSummonTick = true;

    // ── Summon dialogue chain ──
    private static final String[] SUMMON_LINES = {
        "§d<Allie> Huh? Where... where am I?",
        "§d<Allie> Oh! You must be the one who called me!",
        "§d<Allie> My name is Allie. I'm a summon, but... I don't really get the rules.",
        "§d<Allie> Soooo... what's up? You need me for something?",
        "§d<Allie> I can help out, if you want.",
        "§d<Allie> Just don't expect me to know what I'm doing, hehe~",
        "§d<Allie> Alright, let's see what you got~",
        "§d<Allie> So, what'll it be?"
    };
    private int summonLineIndex = 0;
    private int summonLineTick = 0;

    // ── Tail portal particle timer ──
    private int tailParticleTick = 0;

    public AllieEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SUMMON_OWNER, "");
        builder.define(SEXMOD_USES, 0);
        builder.define(RICH_ANIM_FINISHED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("SummonOwner", this.entityData.get(SUMMON_OWNER));
        compound.putInt("SexmodUses", this.entityData.get(SEXMOD_USES));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("SummonOwner"))
            this.entityData.set(SUMMON_OWNER, compound.getString("SummonOwner"));
        if (compound.contains("SexmodUses"))
            this.entityData.set(SEXMOD_USES, compound.getInt("SexmodUses"));
    }

    @Override
    public String getGirlName() { return "allie"; }
    @Override
    public String getGeoFileName() { return "armored"; }
    @Override
    public String getNudeGeoFileName() { return "armored"; }
    @Override
    public boolean requiresBedForMissionary() { return false; }
    @Override
    public boolean requiresBedForDoggy() { return false; }

    // Allie doesn't use the standard scene buttons
    @Override
    public boolean showStandardMissionary() { return false; }
    @Override
    public boolean showStandardBlowjob() { return false; }
    @Override
    public boolean showStandardDoggy() { return false; }
    @Override
    public boolean showStandardBoobjob() { return false; }

    // ─────────────────────────────────────────────────────────────
    //  Animation paths: deepthroat_* + reverse_cowgirl_*
    // ─────────────────────────────────────────────────────────────
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case SUMMON          -> "animation." + p + ".summon";
            case SUMMON_WAIT     -> "animation." + p + ".idle";
            case RICH_FIRST_TIME -> "animation." + p + ".summon";
            case RICH_NORMAL     -> "animation." + p + ".summon";
            case MISSIONARY_START  -> "animation." + p + ".reverse_cowgirl_start";
            case MISSIONARY_SLOW   -> "animation." + p + ".reverse_cowgirl_slow1";
            case MISSIONARY_FAST   -> "animation." + p + ".reverse_cowgirl_fasts";
            case MISSIONARY_CUM    -> "animation." + p + ".reverse_cowgirl_cum";
            case BLOWJOBINTRO      -> "animation." + p + ".deepthroat_start";
            case BLOWJOBSUCK       -> "animation." + p + ".deepthroat_slow";
            case BLOWJOBTHRUST     -> "animation." + p + ".deepthroat_fast";
            case BLOWJOBCUM        -> "animation." + p + ".deepthroat_cum";
            case PAIZURI_START     -> "animation." + p + ".deepthroat_prepare";
            case PAIZURI_SLOW      -> "animation." + p + ".deepthroat_slow";
            case PAIZURI_FAST      -> "animation." + p + ".deepthroat_fast";
            case PAIZURI_CUM       -> "animation." + p + ".deepthroat_cum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".reverse_cowgirl_start";
            case DOGGYSLOW         -> "animation." + p + ".reverse_cowgirl_slow2";
            case DOGGYFAST         -> "animation." + p + ".reverse_cowgirl_fastc1";
            case DOGGYCUM          -> "animation." + p + ".reverse_cowgirl_cum";
            default -> "animation." + p + ".idle";
        };
    }

    // ─────────────────────────────────────────────────────────────
    //  Interaction: show Allie-specific options
    // ─────────────────────────────────────────────────────────────
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        // If in SUMMON_WAIT (idle after summon), open the wish/action dialog
        SexModAnimation current = getSexModAnimation();
        if (current == SexModAnimation.SUMMON_WAIT || current == SexModAnimation.NULL) {
            // Teleport player close to Allie if they're far
            if (this.distanceToSqr(player) > 16) {
                player.teleportTo(this.getX(), this.getY(), this.getZ());
            }
            if (this.level().isClientSide) {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    // Show the allie action screen on client
                    Minecraft.getInstance().setScreen(
                        new com.schnurritv.sexmod.client.gui.AllieActionScreen(this));
                });
            }
            return InteractionResult.SUCCESS;
        }
        // Default behavior for other states
        return super.mobInteract(player, hand);
    }

    // ─────────────────────────────────────────────────────────────
    //  Tick: tail portal particles, summon dialogue, rich animation finish
    // ─────────────────────────────────────────────────────────────
    @Override
    public void tick() {
        super.tick();

        // ── Client-side effects ──
        if (this.level().isClientSide) {
            // First summon tick → 300 portal particle burst
            if (firstSummonTick && getSexModAnimation() == SexModAnimation.SUMMON) {
                firstSummonTick = false;
                spawnPortalBurst(300);
            }

            // Periodic tail portal particles (every 10 ticks)
            if (getSexModAnimation() == SexModAnimation.SUMMON_WAIT || getSexModAnimation() == SexModAnimation.NULL) {
                tailParticleTick++;
                if (tailParticleTick >= 10) {
                    tailParticleTick = 0;
                    spawnTailPortalParticle();
                }
            }

            // RICH animations finished → play "wish granted" sound + gold particles
            if (this.entityData.get(RICH_ANIM_FINISHED)) {
                this.entityData.set(RICH_ANIM_FINISHED, false);
                // Play wish granted sound (misc.allie.wishgranted0)
                playWishGrantedSound();
                // Spawn gold coin particles
                spawnGoldCoinParticles();
            }
        }

        // ── Server-side ──
        if (!this.level().isClientSide) {
            // Track summon dialogue
            if (getSexModAnimation() == SexModAnimation.SUMMON) {
                summonedByPlayer = true;
            }

            // Detect when RICH animation finishes → switch back to SUMMON_WAIT
            SexModAnimation current = getSexModAnimation();
            if ((current == SexModAnimation.RICH_FIRST_TIME || current == SexModAnimation.RICH_NORMAL)
                && this.entityData.get(ANIMATION_TICKS) >= getAnimationTickLength(current)
                && getAnimationTickLength(current) > 0) {
                // Animation finished → reset to SUMMON_WAIT
                setSexModAnimation(SexModAnimation.SUMMON_WAIT);
                this.entityData.set(ANIMATION_TICKS, 0);
                this.entityData.set(ANIMATION_FOLLOW_UP, "null");
                // Signal client to play effects
                this.entityData.set(RICH_ANIM_FINISHED, true);
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  Tail portal particles (client only)
    // ─────────────────────────────────────────────────────────────
    private void spawnTailPortalParticle() {
        if (!this.level().isClientSide) return;
        int tailIndex = RAND.nextInt(8);
        Vec3 tailPos = getTailPosition(tailIndex);
        this.level().addParticle(ParticleTypes.PORTAL,
            tailPos.x, tailPos.y, tailPos.z,
            RAND.nextGaussian() * 0.01, RAND.nextGaussian() * 0.01, RAND.nextGaussian() * 0.01);
    }

    private void spawnPortalBurst(int count) {
        if (!this.level().isClientSide) return;
        for (int i = 0; i < count; i++) {
            double angle = Math.PI * 2 * (double)i / (double)count;
            double radius = 0.75;
            double x = this.getX() + Math.sin(angle) * radius;
            double z = this.getZ() + Math.cos(angle) * radius;
            this.level().addParticle(ParticleTypes.PORTAL,
                x, this.getY(), z,
                0.0, RAND.nextDouble() * 1.5, 0.0);
        }
    }

    private void spawnGoldCoinParticles() {
        if (!this.level().isClientSide) return;
        for (int i = 0; i < 40; i++) {
            this.level().addParticle(ParticleTypes.GLOW,
                this.getX() + (RAND.nextDouble() - 0.5) * 1.5,
                this.getY() + 1.0 + RAND.nextDouble() * 1.5,
                this.getZ() + (RAND.nextDouble() - 0.5) * 1.5,
                RAND.nextGaussian() * 0.1, 0.2 + RAND.nextDouble() * 0.3, RAND.nextGaussian() * 0.1);
        }
    }

    private void playWishGrantedSound() {
        if (!this.level().isClientSide) return;
        net.minecraft.sounds.SoundEvent ev = net.minecraft.sounds.SoundEvent.createVariableRangeEvent(
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("sexmod", "misc.wish.wishgranted0"));
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(),
            ev, net.minecraft.sounds.SoundSource.VOICE, 1.0f, 1.0f, false);
    }

    /**
     * Estimate tail bone position. Uses generic offset since the model
     * is geckolib-driven and bones change per frame. This gives a rough
     * area around the back of the entity at different heights.
     */
    private Vec3 getTailPosition(int index) {
        // 8 tails spread in a fan behind the entity
        float yawRad = (float) Math.toRadians(this.getYRot() + 180);
        float spread = (index - 3.5f) * 0.25f; // -0.875 to 0.875
        float baseY = (float)(this.getY() + 0.3 + index * 0.08f);
        double dx = Math.sin(yawRad + spread) * 0.6;
        double dz = Math.cos(yawRad + spread) * 0.6;
        return new Vec3(this.getX() + dx, baseY, this.getZ() + dz);
    }

    // ─────────────────────────────────────────────────────────────
    //  Handle wish system (called from AllieActionScreen / network)
    // ─────────────────────────────────────────────────────────────
    /**
     * Called server-side when player selects an action from Allie's menu.
     * @param actionKey one of: "makemerichallie", "deepthroat", "reverse_cowgirl"
     */
    public void handleAllieAction(String actionKey, Player player) {
        if (this.level().isClientSide) return;

        if ("makemerichallie".equals(actionKey)) {
            // Check sexmodUses from the entity's data
            int uses = this.entityData.get(SEXMOD_USES);
            SexModAnimation anim = (uses == 1) ? SexModAnimation.RICH_FIRST_TIME : SexModAnimation.RICH_NORMAL;
            setSexModAnimation(anim);
            this.entityData.set(ANIMATION_TICKS, 0);
            this.entityData.set(ANIMATION_FOLLOW_UP, SexModAnimation.SUMMON_WAIT.name());
            player.displayClientMessage(Component.literal("§e<Allie> Your wish is my command~ ✨"), false);
        } else {
            // deepthroat or reverse_cowgirl → start the scene directly
            boolean isDeepThroat = "deepthroat".equals(actionKey);
            int uses = this.entityData.get(SEXMOD_USES);

            if (isDeepThroat) {
                com.schnurritv.sexmod.scene.SceneManager.startBlowjob(this, player);
            } else {
                com.schnurritv.sexmod.scene.SceneManager.startMissionary(this, player);
            }
        }
    }

    /**
     * Called when a scene ends. Returns Allie to SUMMON_WAIT state
     * so the player can interact again.
     */
    public void returnToSummonWait() {
        if (this.level().isClientSide) return;
        this.entityData.set(IS_LOCKED, false);
        this.entityData.set(PARTNER_UUID, "null");
        this.entityData.set(CLOTHING_STATE, 0);
        setSexModAnimation(SexModAnimation.SUMMON_WAIT);
        this.entityData.set(ANIMATION_TICKS, 0);
        this.entityData.set(ANIMATION_FOLLOW_UP, "null");
    }

    // ─────────────────────────────────────────────────────────────
    //  Override scene stop to return to SUMMON_WAIT
    // ─────────────────────────────────────────────────────────────
    @Override
    public void handleAnimationSequencing() {
        SexModAnimation current = getSexModAnimation();
        String followUpName = this.entityData.get(ANIMATION_FOLLOW_UP);

        // Auto-set DOGGYSTART follow-up to DOGGYWAIT (mirrors parent logic)
        if (current == SexModAnimation.DOGGYSTART && ("null".equals(followUpName) || followUpName.isEmpty())) {
            followUpName = SexModAnimation.DOGGYWAIT.name();
            this.entityData.set(ANIMATION_FOLLOW_UP, followUpName);
        }

        int length = getAnimationTickLength(current);
        if (length <= 0) return;

        int ticks = this.entityData.get(ANIMATION_TICKS) + 1;
        this.entityData.set(ANIMATION_TICKS, ticks);

        if (ticks < length) return;

        // Animation finished
        boolean hasFollowUp = followUpName != null && !followUpName.isEmpty() && !"null".equals(followUpName);

        if (hasFollowUp) {
            try {
                SexModAnimation followUp = SexModAnimation.valueOf(followUpName);
                setSexModAnimation(followUp);
                this.entityData.set(ANIMATION_TICKS, 0);
                // Clear follow-up for non-looping transitions
                if (followUp == SexModAnimation.SUMMON_WAIT) {
                    this.entityData.set(ANIMATION_FOLLOW_UP, "null");
                }
            } catch (IllegalArgumentException e) {
                this.entityData.set(ANIMATION_FOLLOW_UP, "");
            }
        } else {
            // No follow-up: handle looping or stop
            if (current.name().contains("CUM")) {
                // CUM finished → return to SUMMON_WAIT
                returnToSummonWait();
            } else {
                // Loop (SLOW/WAIT)
                this.entityData.set(ANIMATION_TICKS, 0);
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  On spawn: trigger SUMMON animation
    // ─────────────────────────────────────────────────────────────
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        // Start with SUMMON animation
        setSexModAnimation(SexModAnimation.SUMMON);
        this.entityData.set(ANIMATION_FOLLOW_UP, SexModAnimation.SUMMON_WAIT.name());
        this.entityData.set(ANIMATION_TICKS, 0);
        firstSummonTick = true;
    }

    @Override
    public String getModelName() {
        return "allie";
    }

    @Override
    public String getAnimationPrefix() {
        return "allie";
    }
}
