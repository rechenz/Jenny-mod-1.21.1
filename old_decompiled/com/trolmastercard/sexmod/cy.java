/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.gw;
import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.MatrixStack;

public class cy
extends EntityLivingBase
implements IAnimatable {
    static final float e = 11000.0f;
    public static final DataParameter<String> a = EntityDataManager.func_187226_a(cy.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(101);
    public static final DataParameter<String> b = EntityDataManager.func_187226_a(cy.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(102);
    AnimationFactory g = new AnimationFactory(this);
    public boolean f = false;
    public MatrixStack c = new MatrixStack();
    gw d = null;

    public cy(World world) {
        super(world);
        this.field_70130_N = 0.1f;
        this.field_70131_O = 0.1f;
    }

    public cy(World world, UUID uUID, String string) {
        this(world);
        this.field_70180_af.func_187227_b(a, (Object)uUID.toString());
        this.field_70180_af.func_187227_b(b, (Object)string);
    }

    public static cy a(World world, UUID uUID, gw gw2) {
        cy cy2 = new cy(world);
        cy2.func_184212_Q().func_187227_b(a, (Object)uUID.toString());
        cy2.f = true;
        cy2.d = gw2;
        return cy2;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(a, (Object)"");
        this.field_70180_af.func_187214_a(b, (Object)"");
    }

    public AxisAlignedBB func_184177_bl() {
        BlockPos blockPos = this.func_180425_c();
        Vec3i vec3i = new Vec3i(0.5, 0.5, 0.5);
        return new AxisAlignedBB(blockPos.func_177973_b(vec3i), blockPos.func_177971_a(vec3i));
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_145770_h(double d10, double d11, double d12) {
        double d13 = this.field_70165_t - d10;
        double d14 = this.field_70163_u - d11;
        double d15 = this.field_70161_v - d12;
        double d16 = d13 * d13 + d14 * d14 + d15 * d15;
        return this.func_70112_a(d16);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double d10) {
        boolean bl2;
        try {
            bl2 = d10 < 11000.0;
        }
        catch (RuntimeException runtimeException) {
            throw cy.a(runtimeException);
        }
        return bl2;
    }

    @Nullable
    public UUID b() {
        String string = (String)this.field_70180_af.func_187225_a(a);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cy.a(runtimeException);
        }
        return UUID.fromString(string);
    }

    public boolean func_70097_a(DamageSource damageSource, float f10) {
        try {
            if (damageSource != DamageSource.field_76380_i) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cy.a(runtimeException);
        }
        return super.func_70097_a(damageSource, f10);
    }

    @Nullable
    public String a() {
        String string = (String)this.field_70180_af.func_187225_a(b);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cy.a(runtimeException);
        }
        return string;
    }

    public boolean func_70104_M() {
        return false;
    }

    public boolean func_70067_L() {
        return false;
    }

    public void func_70645_a(DamageSource damageSource) {
        super.func_70645_a(damageSource);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.g;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
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

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

