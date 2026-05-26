/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.r;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class i
extends EntityLivingBase
implements IAnimatable {
    static final int e = 12000;
    private final AnimationFactory d = new AnimationFactory(this);
    public UUID f = null;
    static AnimationController<i> a;
    public static final DataParameter<String> b;
    public static final DataParameter<Integer> c;

    public i(World world) {
        super(world);
        this.func_70105_a(0.5f, 0.5f);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(b, (Object)ff.aJ.toString());
        this.field_70180_af.func_187214_a(c, (Object)0);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        int n2 = (Integer)this.field_70180_af.func_187225_a(c);
        try {
            if (n2 >= 12000) {
                this.a();
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        try {
            if (!this.field_70170_p.field_72995_K) {
                this.field_70180_af.func_187227_b(c, (Object)(n2 + 1));
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
    }

    public boolean canTrample(World world, Block block, BlockPos blockPos, float f10) {
        return false;
    }

    public boolean func_70097_a(DamageSource damageSource, float f10) {
        boolean bl2 = super.func_70097_a(damageSource, f10);
        try {
            if (!bl2) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        this.func_70106_y();
        return true;
    }

    void a() {
        int n2 = 0;
        while (true) {
            int n3;
            int n4;
            int n5;
            block23: {
                block22: {
                    try {
                        try {
                            if (n2 >= 30) break;
                            if (!r.f.nextBoolean()) break block22;
                        }
                        catch (RuntimeException runtimeException) {
                            throw i.a(runtimeException);
                        }
                        n5 = 1;
                        break block23;
                    }
                    catch (RuntimeException runtimeException) {
                        throw i.a(runtimeException);
                    }
                }
                n5 = -1;
            }
            float f10 = (float)n5 * r.f.nextFloat();
            try {
                n4 = r.f.nextBoolean() ? 1 : -1;
            }
            catch (RuntimeException runtimeException) {
                throw i.a(runtimeException);
            }
            float f11 = (float)n4 * r.f.nextFloat();
            try {
                n3 = r.f.nextBoolean() ? 1 : -1;
            }
            catch (RuntimeException runtimeException) {
                throw i.a(runtimeException);
            }
            float f12 = (float)n3 * r.f.nextFloat();
            this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, 0.5 + this.field_70165_t, 0.5 + this.field_70163_u, 0.5 + this.field_70161_v, (double)f10, (double)f11, (double)f12, new int[0]);
            ++n2;
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        try {
            if (this.f == null) {
                this.f = UUID.randomUUID();
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        ff ff2 = ff.a(this.field_70170_p, this.f);
        ax.c(this.f, ff2);
        UUID uUID = ax.b(this.f);
        try {
            if (uUID != null) {
                ff2.func_184212_Q().func_187227_b(em.v, (Object)uUID.toString());
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        List<ff> list = ax.n(this.f);
        String string = null;
        for (ff ff3 : list) {
            String string2 = (String)ff3.func_184212_Q().func_187225_a(ff.aU);
            if ("".equals(string2)) continue;
            string = string2;
            break;
        }
        try {
            if (string != null) {
                ff2.func_184212_Q().func_187227_b(ff.aU, string);
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        ff2.func_70107_b(0.5 + this.field_70165_t, this.field_70163_u, 0.5 + this.field_70161_v);
        this.field_70170_p.func_72838_d((Entity)ff2);
        this.a(ff2);
        this.field_70170_p.func_184133_a(null, this.func_180425_c(), SoundEvents.field_187539_bB, SoundCategory.BLOCKS, 0.5f, 1.0f);
        this.field_70170_p.func_72900_e((Entity)this);
    }

    void a(ff ff2) {
        EntityPlayer entityPlayer = ff2.z();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entityPlayer;
        EyeAndKoboldColor eyeAndKoboldColor = ax.l(this.f);
        entityPlayer.func_145747_a((ITextComponent)new TextComponentString(String.format("%s%s %shas become a %snew tribe member%s!", eyeAndKoboldColor.getTextColor(), ff2.c(), TextFormatting.WHITE, TextFormatting.RED, TextFormatting.WHITE)));
        entityPlayerMP.field_71135_a.func_147359_a((Packet)new SPacketSoundEffect(SoundEvents.field_187734_u, SoundCategory.NEUTRAL, entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v, 1.0f, 1.0f));
        entityPlayerMP.field_71135_a.func_147359_a((Packet)new SPacketSoundEffect(SoundEvents.field_187640_br, SoundCategory.NEUTRAL, entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v, 1.0f, 1.0f));
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        a = new AnimationController<i>(this, "controller", 5.0f, this::a);
        animationData.addAnimationController(a);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.d;
    }

    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        try {
            if (this.f != null) {
                nBTTagCompound.func_74778_a("tribeID", this.f.toString());
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        nBTTagCompound.func_74778_a("egg_color", (String)this.field_70180_af.func_187225_a(b));
        nBTTagCompound.func_74768_a("eggAge", ((Integer)this.field_70180_af.func_187225_a(c)).intValue());
        super.func_70014_b(nBTTagCompound);
    }

    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        super.func_70037_a(nBTTagCompound);
        String string = nBTTagCompound.func_74779_i("tribeID");
        try {
            if (!"".equals(string)) {
                this.f = UUID.fromString(string);
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        this.field_70180_af.func_187227_b(b, (Object)nBTTagCompound.func_74779_i("egg_color"));
        this.field_70180_af.func_187227_b(c, (Object)nBTTagCompound.func_74762_e("eggAge"));
    }

    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animationEvent) {
        int n2 = (Integer)this.field_70180_af.func_187225_a(c);
        try {
            if (12000 - n2 < 20) {
                animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.hatch", true));
                return PlayState.CONTINUE;
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        float f10 = (float)n2 / 12000.0f;
        try {
            if ((double)f10 > 0.98) {
                animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.veryfast", true));
                return PlayState.CONTINUE;
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        try {
            if ((double)f10 > 0.85) {
                animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.fast", true));
                return PlayState.CONTINUE;
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        try {
            if ((double)f10 > 0.75) {
                animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.medium", true));
                return PlayState.CONTINUE;
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        try {
            if ((double)f10 > 0.5) {
                animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.slow", true));
                return PlayState.CONTINUE;
            }
        }
        catch (RuntimeException runtimeException) {
            throw i.a(runtimeException);
        }
        return PlayState.CONTINUE;
    }

    public Iterable<ItemStack> func_184193_aE() {
        return new ArrayList<ItemStack>();
    }

    public ItemStack func_184582_a(EntityEquipmentSlot entityEquipmentSlot) {
        return ItemStack.field_190927_a;
    }

    public void func_184201_a(EntityEquipmentSlot entityEquipmentSlot, ItemStack itemStack) {
    }

    public EnumHandSide func_184591_cq() {
        return EnumHandSide.LEFT;
    }

    static {
        b = EntityDataManager.func_187226_a(i.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(115);
        c = EntityDataManager.func_187226_a(i.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(116);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

