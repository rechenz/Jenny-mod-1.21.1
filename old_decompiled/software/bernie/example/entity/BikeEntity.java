/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package software.bernie.example.entity;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BikeEntity
extends EntityAnimal
implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bike.idle", true));
        return PlayState.CONTINUE;
    }

    public BikeEntity(World worldIn) {
        super(worldIn);
        this.field_70158_ak = true;
        this.func_70105_a(0.5f, 0.6f);
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.func_184207_aI()) {
            player.func_184220_m((Entity)this);
            return super.func_184645_a(player, hand);
        }
        return super.func_184645_a(player, hand);
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
    }

    public void func_191986_a(float strafe, float vertical, float forward) {
        if (this.func_70089_S() && this.func_184207_aI()) {
            EntityLivingBase livingentity = (EntityLivingBase)this.func_184179_bs();
            this.field_70126_B = this.field_70177_z = livingentity.field_70177_z;
            this.field_70125_A = livingentity.field_70125_A * 0.5f;
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
            this.field_70759_as = this.field_70761_aq = this.field_70177_z;
            float f10 = livingentity.field_70702_br * 0.5f;
            float f12 = livingentity.field_191988_bg;
            if (f12 <= 0.0f) {
                f12 *= 0.25f;
            }
            this.func_70659_e(0.3f);
            super.func_191986_a(f10, vertical, f12);
        }
    }

    @Nullable
    public Entity func_184179_bs() {
        return this.func_184188_bt().isEmpty() ? null : (Entity)this.func_184188_bt().get(0);
    }

    public boolean func_82171_bF() {
        return true;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BikeEntity>(this, "controller", 0.0f, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Nullable
    public EntityAgeable func_90011_a(EntityAgeable ageable) {
        return null;
    }
}

