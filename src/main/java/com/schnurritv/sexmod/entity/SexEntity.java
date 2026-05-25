package com.schnurritv.sexmod.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.animation.keyframe.event.SoundKeyframeEvent;

import java.util.UUID;
import java.util.Random;

public abstract class SexEntity extends PathfinderMob implements GeoEntity {

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityDataAccessor<String> MASTER_UUID = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Boolean> IS_LOCKED = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> TARGET_POS = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Float> TARGET_ROTATION = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<String> OWN_UUID = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Integer> CURRENT_MODEL = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<String> CURRENT_ACTION = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> PARTNER_UUID = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> MOVEMENT_STATE = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> HOME_POS = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> ANIMATION_FOLLOW_UP = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Integer> ANIMATION_TICKS = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> CLOTHING_STATE = SynchedEntityData.defineId(SexEntity.class, EntityDataSerializers.INT);

    protected SexEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
        return net.minecraft.world.entity.Mob.createMobAttributes()
                .add(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH, 20.0D)
                .add(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(MASTER_UUID, "");
        builder.define(IS_LOCKED, false);
        builder.define(TARGET_POS, "0|0|0");
        builder.define(TARGET_ROTATION, 0.0f);
        builder.define(OWN_UUID, UUID.randomUUID().toString());
        builder.define(CURRENT_MODEL, 0);
        builder.define(CURRENT_ACTION, SexModAnimation.NULL.name());
        builder.define(PARTNER_UUID, "null");
        builder.define(MOVEMENT_STATE, "WALK");
        builder.define(HOME_POS, "0|0|0");
        builder.define(ANIMATION_FOLLOW_UP, "null");
        builder.define(ANIMATION_TICKS, 0);
        builder.define(CLOTHING_STATE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("MasterUUID", this.entityData.get(MASTER_UUID));
        compound.putBoolean("IsLocked", this.entityData.get(IS_LOCKED));
        compound.putString("TargetPos", this.entityData.get(TARGET_POS));
        compound.putFloat("TargetRotation", this.entityData.get(TARGET_ROTATION));
        compound.putString("OwnUUID", this.entityData.get(OWN_UUID));
        compound.putInt("CurrentModel", this.entityData.get(CURRENT_MODEL));
        compound.putString("CurrentAction", this.entityData.get(CURRENT_ACTION));
        compound.putString("PartnerUUID", this.entityData.get(PARTNER_UUID));
        compound.putString("MovementState", this.entityData.get(MOVEMENT_STATE));
        compound.putString("HomePos", this.entityData.get(HOME_POS));
        compound.putString("AnimationFollowUp", this.entityData.get(ANIMATION_FOLLOW_UP));
        compound.putInt("AnimationTicks", this.entityData.get(ANIMATION_TICKS));
        compound.putInt("ClothingState", this.entityData.get(CLOTHING_STATE));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("MasterUUID")) this.entityData.set(MASTER_UUID, compound.getString("MasterUUID"));
        if (compound.contains("IsLocked")) this.entityData.set(IS_LOCKED, compound.getBoolean("IsLocked"));
        if (compound.contains("TargetPos")) this.entityData.set(TARGET_POS, compound.getString("TargetPos"));
        if (compound.contains("TargetRotation")) this.entityData.set(TARGET_ROTATION, compound.getFloat("TargetRotation"));
        if (compound.contains("OwnUUID")) this.entityData.set(OWN_UUID, compound.getString("OwnUUID"));
        if (compound.contains("CurrentModel")) this.entityData.set(CURRENT_MODEL, compound.getInt("CurrentModel"));
        if (compound.contains("CurrentAction")) this.entityData.set(CURRENT_ACTION, compound.getString("CurrentAction"));
        if (compound.contains("PartnerUUID")) this.entityData.set(PARTNER_UUID, compound.getString("PartnerUUID"));
        if (compound.contains("MovementState")) this.entityData.set(MOVEMENT_STATE, compound.getString("MovementState"));
        if (compound.contains("HomePos")) this.entityData.set(HOME_POS, compound.getString("HomePos"));
        if (compound.contains("AnimationFollowUp")) this.entityData.set(ANIMATION_FOLLOW_UP, compound.getString("AnimationFollowUp"));
        if (compound.contains("AnimationTicks")) this.entityData.set(ANIMATION_TICKS, compound.getInt("AnimationTicks"));
        if (compound.contains("ClothingState")) this.entityData.set(CLOTHING_STATE, compound.getInt("ClothingState"));
    }

    public void setSexModAnimation(SexModAnimation animation) {
        this.entityData.set(CURRENT_ACTION, animation.name());
        if (!this.level().isClientSide) {
            com.schnurritv.sexmod.networking.NetworkHandler.broadcastAnimationSync(this, animation);
        }
    }

    public SexModAnimation getSexModAnimation() {
        try {
            return SexModAnimation.valueOf(this.entityData.get(CURRENT_ACTION));
        } catch (IllegalArgumentException e) {
            return SexModAnimation.NULL;
        }
    }

    @Override
    public void tick() {
        super.tick();
        
        if (this.getEntityData().get(IS_LOCKED)) {
            float rot = this.getEntityData().get(TARGET_ROTATION);
            this.setYRot(rot);
            this.yRotO = rot;
            this.yBodyRot = rot;
            this.yBodyRotO = rot;
            this.yHeadRot = rot;
            this.yHeadRotO = rot;
            this.setXRot(0);
            this.getNavigation().stop();
        }

        if (this.level().isClientSide) {
            for (net.minecraft.world.entity.EquipmentSlot slot : net.minecraft.world.entity.EquipmentSlot.values()) {
                super.setItemSlot(slot, net.minecraft.world.item.ItemStack.EMPTY);
            }
        } else {
            handleAnimationSequencing();
        }
    }

    @Override
    public float getScale() {
        return 1.0f;
    }

    @Override
    public void travel(net.minecraft.world.phys.Vec3 pos) {
        if (this.getEntityData().get(IS_LOCKED)) {
            this.setDeltaMovement(0, 0, 0);
            return;
        }
        super.travel(pos);
    }

    protected void handleAnimationSequencing() {
        SexModAnimation current = getSexModAnimation();
        String followUpName = this.entityData.get(ANIMATION_FOLLOW_UP);

        if (current == SexModAnimation.DOGGYSTART && ("null".equals(followUpName) || followUpName.isEmpty())) {
            followUpName = SexModAnimation.DOGGYWAIT.name();
        }

        if ("null".equals(followUpName) || followUpName.isEmpty()) return;

        int length = current.getLength();
        if (length <= 0) return;

        int ticks = this.entityData.get(ANIMATION_TICKS) + 1;
        this.entityData.set(ANIMATION_TICKS, ticks);

        if (ticks >= length) {
            try {
                SexModAnimation followUp = SexModAnimation.valueOf(followUpName);
                setSexModAnimation(followUp);
                this.entityData.set(ANIMATION_TICKS, 0);
                this.entityData.set(ANIMATION_FOLLOW_UP, "null");
            } catch (IllegalArgumentException e) {
                this.entityData.set(ANIMATION_FOLLOW_UP, "null");
            }
        }
    }

    @Override
    public net.minecraft.world.item.ItemStack getItemBySlot(net.minecraft.world.entity.EquipmentSlot slot) {
        if (this.level().isClientSide) {
            return net.minecraft.world.item.ItemStack.EMPTY;
        }
        return super.getItemBySlot(slot);
    }

    @Override
    public void setItemSlot(net.minecraft.world.entity.EquipmentSlot slot, net.minecraft.world.item.ItemStack stack) {
        if (this.level().isClientSide) {
             super.setItemSlot(slot, net.minecraft.world.item.ItemStack.EMPTY);
             return;
        }
        super.setItemSlot(slot, stack);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public abstract String getModelName();

    public String getGeoFileName() {
        return getModelName();
    }

    public String getNudeGeoFileName() {
        return getGeoFileName();
    }

    // ═══════════════════════════════════════════════════════════════
    //  Animation path methods — override for character-specific naming
    // ═══════════════════════════════════════════════════════════════

    /** Animation file prefix. Default: modelName. Override for Lucy/Mika/Momo (→ "default"). */
    public String getAnimationPrefix() {
        return getModelName().toLowerCase();
    }

    /** Map SexModAnimation enum → character-specific animation name in JSON. */
    public String getSceneAnimationPath(SexModAnimation animation) {
        return "animation." + getAnimationPrefix() + "." + animation.name().toLowerCase();
    }

    /** Movement animation path. Handles FOLLOW→"walk" so follow mode plays walk anim. */
    public String getMovementAnimationPath() {
        String state = this.entityData.get(MOVEMENT_STATE);
        String prefix = getAnimationPrefix();
        // FOLLOW state means we're walking to follow — play walk animation
        if ("FOLLOW".equals(state) || "STAY".equals(state)) {
            return "animation." + prefix + ".walk";
        }
        return "animation." + prefix + "." + state.toLowerCase();
    }

    public String getIdleAnimationPath() {
        return "animation." + getAnimationPrefix() + ".idle";
    }

    public String getBlinkAnimationPath() {
        return "animation." + getAnimationPrefix() + ".blink";
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        AnimationController<SexEntity> baseController = new AnimationController<>(this, "base", 5, state -> {
            SexModAnimation current = getSexModAnimation();

            // --- Scene Animation priority ---
            if (current != SexModAnimation.NULL) {
                return state.setAndContinue(RawAnimation.begin().thenLoop(getSceneAnimationPath(current)));
            }

            // --- Movement Animation ---
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop(getMovementAnimationPath()));
            }

            // --- Idle ---
            return state.setAndContinue(RawAnimation.begin().thenLoop(getIdleAnimationPath()));
        });

        // Sound keyframe handler: play sounds defined in animation JSON sound_effects
        baseController.setSoundKeyframeHandler(event -> {
            if (!this.level().isClientSide) return;
            software.bernie.geckolib.animation.keyframe.event.data.SoundKeyframeData data = event.getKeyframeData();
            if (data == null) return;
            String instruction = data.getSound();
            if (instruction == null || instruction.isEmpty()) return;

            onCustomInstruction(getAnimationPrefix(), instruction);
        });

        controllers.add(baseController);

        controllers.add(new AnimationController<>(this, "eyes", 0, state -> {
            SexModAnimation current = getSexModAnimation();
            if (current != null && current.autoBlink) {
                return state.setAndContinue(RawAnimation.begin().thenLoop(getBlinkAnimationPath()));
            }
            return state.setAndContinue(RawAnimation.begin());
        }));
    }

    /**
     * Handle custom sound instructions from animation.json sound_effects.
     * Each instruction tag is mapped to specific sound categories and variants.
     */
    private void onCustomInstruction(String modelName, String instruction) {
        if (!this.level().isClientSide) return;
        Random rand = new Random();

        switch (instruction) {
            // === BLOWJOB INTRO (bji) ===
            case "bjiMSG1":  playSound(modelName, "giggle", 3); break;
            case "bjiMSG2":  playSound(modelName, "lightbreathing", 8); break;
            case "bjiMSG3":  playSound(modelName, "aftersessionmoan", 0); break;
            case "bjiMSG4":  playMiscSound("belljingle", 0); break;
            case "bjiMSG5":  playSound(modelName, "hmph", 1); break;
            case "bjiMSG6":
            case "bjiMSG7":
            case "bjiMSG8":
            case "bjiMSG9":  playSound(modelName, "giggle", rand.nextInt(5)); break;
            case "bjiMSG10": break; // camera cue
            case "bjiMSG11":
            case "bjiMSG12": playSound(modelName, "lipsound", rand.nextInt(getVariantCount(modelName, "lipsound"))); break;
            case "bjiDone":   break;

            // === BLOWJOB THRUST (bjt) ===
            case "bjtMSG1":   playSound(modelName, "mmm", rand.nextInt(Math.max(1, getVariantCount(modelName, "mmm"))));
                               playCoSound(modelName, "lipsound", rand.nextInt(Math.max(1, getVariantCount(modelName, "lipsound")))); break;
            case "bjtReady":
            case "bjtDone":   break;

            // === BLOWJOB CUM (bjc) ===
            case "bjcMSG1":   playSound(modelName, "bjmoan", 1); break;
            case "bjcMSG2":   playSound(modelName, "bjmoan", 7); break;
            case "bjcMSG3":   playSound(modelName, "aftersessionmoan", 1); break;
            case "bjcMSG4":
            case "bjcMSG5":
            case "bjcMSG6":
            case "bjcMSG7":   playSound(modelName, "lightbreathing", rand.nextInt(12)); break;
            case "bjcBlackScreen": break;
            case "bjcDone":    break;

            // === DOGGY START ===
            case "doggystartMSG1":
            case "doggystartMSG2":
            case "doggystartMSG3":
            case "doggystartMSG4":
            case "doggystartMSG5": playSound(modelName, "giggle", rand.nextInt(5)); break;
            case "doggystartDone": break;

            // === DOGGY GO ON BED ===
            case "doggyGoOnBedMSG1": playMiscSound("plob", 0);
                                       playCoSound(modelName, "bjmoan", rand.nextInt(Math.max(1, getVariantCount(modelName, "bjmoan")))); break;
            case "doggyGoOnBedMSG2":
            case "doggyGoOnBedMSG3":
            case "doggyGoOnBedMSG4": break;
            case "doggyGoOnBedDone": break;

            // === DOGGY (thrusting) ===
            case "doggyMSG1":  playSound(modelName, "giggle", rand.nextInt(5)); break;
            case "doggyslowMSG1":
            case "doggyslowMSG2": playSound(modelName, "moan", rand.nextInt(8));
                                  playCoSound(modelName, "heavybreathing", rand.nextInt(8)); break;
            case "doggyfastMSG1": playSound(modelName, "moan", rand.nextInt(8));
                                  playCoSound(modelName, "heavybreathing", rand.nextInt(8)); break;
            case "doggyfastReady":
            case "doggyfastDone":  break;

            // === DOGGY CUM ===
            case "doggycumMSG1": playMiscSound("pounding", rand.nextInt(36));
                                 playSound(modelName, "moan", rand.nextInt(8));
                                 playCoSound(modelName, "heavybreathing", rand.nextInt(8)); break;
            case "doggycumMSG2":
            case "doggycumMSG3":
            case "doggycumMSG4":
            case "doggycumMSG5": playSound(modelName, "heavybreathing", rand.nextInt(8)); break;
            case "doggyCumDone": break;

            // === PAIZURI / BOOBJOB START ===
            case "paizuriStartMSG1":  playSound(modelName, "giggle", rand.nextInt(5)); break;
            case "paizuri_startStep": playSound(modelName, "lipsound", rand.nextInt(Math.max(1, getVariantCount(modelName, "lipsound")))); break;
            case "paizuri_startDone": break;

            // === PAIZURI SLOW/FAST ===
            case "paizuriSlowMSG1": playSound(modelName, "lipsound", rand.nextInt(Math.max(1, getVariantCount(modelName, "lipsound")))); break;
            case "paizuriFastMSG1": playSound(modelName, "moan", rand.nextInt(8)); break;
            case "paizuriReady":
            case "paizuri_fastDone": break;

            // === PAIZURI CUM ===
            case "paizuri_cumStart": break;
            case "paizuri_cumDone":  break;

            // === STRIP ===
            case "stripMSG1":    playSound(modelName, "giggle", rand.nextInt(5)); break;
            case "startStrip":
            case "becomeNude":
            case "stripDone":    break;

            // === PAYMENT ===
            case "paymentMSG1": playSound(modelName, "giggle", rand.nextInt(5)); break;
            case "paymentMSG2": playSound(modelName, "huh", rand.nextInt(2)); break;
            case "paymentMSG3":
            case "paymentMSG4": playSound(modelName, "giggle", rand.nextInt(5)); break;
            case "drawItems":
            case "removeItems":
            case "paymentDone":  break;

            // === MISSIONARY ===
            case "missionary_slowMSG1":  playSound(modelName, "moan", rand.nextInt(8)); break;
            case "missionary_fastMSG1":  playSound(modelName, "moan", rand.nextInt(8)); break;
            case "missionary_fastReady":
            case "missionary_fastDone":
            case "missionary_startDone": break;
            case "missionary_cumMSG1":
            case "missionary_cumMSG2":   playSound(modelName, "moan", rand.nextInt(8)); break;
            case "missionary_cumDone":   break;

            // === IDLE / AMBIENT ===
            case "idle":         playSound(modelName, "giggle", rand.nextInt(5)); break;

            // === MISC / UI / SFX ===
            case "bedRustle":    playMiscSound("bedrustle", rand.nextInt(2)); break;
            case "attackSound":  playMiscSound("slap", rand.nextInt(2)); break;
            case "attackDone":
            case "openSexUi":
            case "sexUiOn":
            case "cowgirlcumMSG6":
            case "boobjob_camera":
            case "pearl":    break; // UI/visual/meta cues, no vocal sound

            default:
                // Unknown instruction — try as a generic category name
                if (getVariantCount(modelName, instruction) > 0) {
                    playSound(modelName, instruction, rand.nextInt(getVariantCount(modelName, instruction)));
                }
                break;
        }
    }

    /** Play a specific sound variant by index */
    private void playSound(String modelName, String category, int variant) {
        if (!this.level().isClientSide) return;
        int count = getVariantCount(modelName, category);
        if (count <= 0) return;
        int safeVariant = Math.max(0, Math.min(variant, count - 1));
        String soundPath = "girls." + modelName + "." + category + "." + category + safeVariant;
        net.minecraft.resources.ResourceLocation loc = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("sexmod", soundPath);
        net.minecraft.sounds.SoundEvent ev = net.minecraft.sounds.SoundEvent.createVariableRangeEvent(loc);
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(),
                ev, net.minecraft.sounds.SoundSource.VOICE, 1.0f, 1.0f, false);
    }

    /** Play a co-sound (layered, slightly lower volume) for multi-sound instructions */
    private void playCoSound(String modelName, String category, int variant) {
        if (!this.level().isClientSide) return;
        int count = getVariantCount(modelName, category);
        if (count <= 0) return;
        int safeVariant = Math.max(0, Math.min(variant, count - 1));
        String soundPath = "girls." + modelName + "." + category + "." + category + safeVariant;
        net.minecraft.resources.ResourceLocation loc = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("sexmod", soundPath);
        net.minecraft.sounds.SoundEvent ev = net.minecraft.sounds.SoundEvent.createVariableRangeEvent(loc);
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(),
                ev, net.minecraft.sounds.SoundSource.VOICE, 0.8f, 1.0f, false);
    }

    /** Play a misc sound (global SFX: plob, belljingle, pounding, bedrustle, slap, etc.) */
    private void playMiscSound(String category, int variant) {
        if (!this.level().isClientSide) return;
        String soundPath = "misc." + category + "." + category + variant;
        net.minecraft.resources.ResourceLocation loc = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("sexmod", soundPath);
        net.minecraft.sounds.SoundEvent ev = net.minecraft.sounds.SoundEvent.createVariableRangeEvent(loc);
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(),
                ev, net.minecraft.sounds.SoundSource.VOICE, 1.0f, 1.0f, false);
    }

    /** Get number of sound variants for a model+category combination */
    private int getVariantCount(String modelName, String category) {
        switch (modelName) {
            case "jenny":
                switch (category) {
                    case "giggle": return 5;
                    case "moan": return 8;
                    case "bjmoan": return 13;
                    case "lipsound": return 10;
                    case "ahh": return 10;
                    case "mmm": return 9;
                    case "lightbreathing": return 12;
                    case "heavybreathing": return 8;
                    case "happyoh": return 3;
                    case "hmph": return 4;
                    case "sigh": return 2;
                    case "huh": return 2;
                    case "sadoh": return 2;
                    case "aftersessionmoan": return 5;
                    default: return 0;
                }
            case "allie":
                switch (category) {
                    case "giggle": return 5;
                    case "moan": return 8;
                    case "bjmoan": return 14;
                    case "lipsound": return 14;
                    case "ahh": return 10;
                    case "mmm": return 10;
                    case "lightbreathing": return 11;
                    case "heavybreathing": return 8;
                    case "happyoh": return 3;
                    case "hmph": return 5;
                    case "sigh": return 2;
                    case "huh": return 2;
                    case "sadoh": return 2;
                    case "scawy": return 3;
                    case "aftersessionmoan": return 4;
                    default: return 0;
                }
            case "ellie":
                switch (category) {
                    case "giggle": return 5;
                    case "moan": return 9;
                    case "bjmoan": return 13;
                    case "lipsound": return 10;
                    case "ahh": return 10;
                    case "mmm": return 9;
                    case "lightbreathing": return 8;
                    case "heavybreathing": return 9;
                    case "happyoh": return 3;
                    case "hmph": return 4;
                    case "sigh": return 2;
                    case "huh": return 2;
                    case "sadoh": return 2;
                    case "cometomommy": return 2;
                    case "goodboy": return 2;
                    case "mommyhorny": return 2;
                    case "aftersessionmoan": return 5;
                    default: return 0;
                }
            case "kobold":
                switch (category) {
                    case "giggle": return 4;
                    case "bjmoan": return 10;
                    case "moan": return 11;
                    case "lightbreathing": return 12;
                    case "haa": return 7;
                    case "heymaster": return 6;
                    case "master": return 6;
                    case "orgasm": return 4;
                    case "sad": return 3;
                    case "yep": return 7;
                    case "interested": return 3;
                    default: return 0;
                }
            case "bia":
                switch (category) {
                    case "giggle": return 3;
                    case "ahh": return 8;
                    case "bjmoan": return 5;
                    case "breath": return 4;
                    case "hey": return 4;
                    case "huh": return 3;
                    case "mmm": return 8;
                    default: return 0;
                }
            default:
                return 0;
        }
    }
}
