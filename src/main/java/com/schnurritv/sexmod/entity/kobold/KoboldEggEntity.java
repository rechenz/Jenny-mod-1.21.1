package com.schnurritv.sexmod.entity.kobold;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;

/**
 * Kobold egg entity. After 12000 ticks (~10 min), hatches into a Kobold.
 */
public class KoboldEggEntity extends LivingEntity implements GeoEntity {

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final int HATCH_TIME = 12000; // ticks
    public static final EntityDataAccessor<String> COLOR = SynchedEntityData.defineId(KoboldEggEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(KoboldEggEntity.class, EntityDataSerializers.INT);

    /** Tribe ID this egg belongs to */
    @Nullable public UUID tribeId = null;

    public KoboldEggEntity(EntityType<? extends KoboldEggEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(COLOR, EyeAndKoboldColor.PURPLE.name());
        builder.define(AGE, 0);
    }

    @Override
    public void tick() {
        super.tick();

        int age = this.entityData.get(AGE);
        if (!this.level().isClientSide) {
            if (age >= HATCH_TIME) {
                hatch();
                return;
            }
            this.entityData.set(AGE, age + 1);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean hurt = super.hurt(source, amount);
        if (hurt) {
            this.remove(RemovalReason.KILLED);
            if (this.level() instanceof ServerLevel sl) {
                sl.sendParticles(ParticleTypes.EXPLOSION,
                    this.getX(), this.getY() + 0.5, this.getZ(),
                    10, 0.3, 0.3, 0.3, 0.1);
            }
        }
        return hurt;
    }

    private void hatch() {
        if (this.level().isClientSide) return;

        if (this.tribeId == null) {
            this.tribeId = UUID.randomUUID();
        }

        EyeAndKoboldColor color = EyeAndKoboldColor.safeValueOf(this.entityData.get(COLOR));
        KoboldEntity kobold = new KoboldEntity(com.schnurritv.sexmod.entity.EntityRegistry.KOBOLD.get(), this.level());
        kobold.setPos(this.getX(), this.getY(), this.getZ());

        // Ensure tribe exists
        if (!KoboldManager.tribeExists(this.tribeId)) {
            KoboldManager.createTribe(this.tribeId, color);
        }
        KoboldManager.addMember(this.tribeId, kobold);

        // Inherit owner from tribe
        UUID ownerId = KoboldManager.getOwner(this.tribeId);
        if (ownerId != null) {
            kobold.setMasterUUID(ownerId.toString());
            kobold.setTame(true);
        }

        this.level().addFreshEntity(kobold);

        // Sound & particles
        this.level().playSound(null, this.blockPosition(),
            SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 0.5f, 1.0f);
        if (this.level() instanceof ServerLevel sl) {
            sl.sendParticles(ParticleTypes.EXPLOSION_EMITTER,
                this.getX(), this.getY() + 0.5, this.getZ(),
                1, 0, 0, 0, 0);
        }

        this.remove(RemovalReason.KILLED);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("EggColor", this.entityData.get(COLOR));
        compound.putInt("EggAge", this.entityData.get(AGE));
        if (tribeId != null) {
            compound.putUUID("TribeId", tribeId);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("EggColor")) {
            this.entityData.set(COLOR, compound.getString("EggColor"));
        }
        if (compound.contains("EggAge")) {
            this.entityData.set(AGE, compound.getInt("EggAge"));
        }
        if (compound.hasUUID("TribeId")) {
            this.tribeId = compound.getUUID("TribeId");
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new software.bernie.geckolib.animation.AnimationController<>(this, "base", 0, state -> {
            // Play wobble animation based on age
            int age = this.entityData.get(AGE);
            String anim;
            if (age < 3000) {
                anim = "animation.model.null";
            } else if (age < 6000) {
                anim = "animation.model.slow";
            } else if (age < 9000) {
                anim = "animation.model.medium";
            } else if (age < 12000) {
                anim = "animation.model.fast";
            } else {
                anim = "animation.model.veryfast";
            }
            return state.setAndContinue(software.bernie.geckolib.animation.RawAnimation.begin().thenLoop(anim));
        }));
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return java.util.Collections.emptyList();
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }
}
