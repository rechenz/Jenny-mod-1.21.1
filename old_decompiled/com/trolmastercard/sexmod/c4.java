/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityWitherSkeleton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.bv;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.gc;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.r;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class c4
extends EntityLiving {
    public static final float d = 0.4f;
    public static final float e = 0.3f;
    static final int b = 200;
    static final int k = 100;
    static final float a = 0.5f;
    static final float l = 0.15f;
    public static final float j = 0.75f;
    public double g = 1.0;
    Vec3d h = Vec3d.field_186680_a;
    boolean c = false;
    boolean i = true;
    f_ f;

    public c4(World world) {
        super(world);
        this.func_70105_a(0.5f, 0.5f);
    }

    public c4(World world, f_ f_2) {
        super(world);
        this.func_70105_a(0.5f, 0.5f);
        this.f = f_2;
    }

    public c4(World world, f_ f_2, Vec3d vec3d) {
        this(world);
        this.h = vec3d;
        this.f = f_2;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_82167_n(Entity entity) {
    }

    public void func_70071_h_() {
        try {
            if (this.field_70128_L) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        try {
            this.field_70145_X = true;
            this.func_189654_d(true);
            this.field_70159_w = this.h.field_72450_a;
            this.field_70181_x = this.h.field_72448_b;
            this.field_70179_y = this.h.field_72449_c;
            super.func_70071_h_();
            if (this.field_70170_p.field_72995_K) {
                this.a();
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        try {
            this.c();
            if (this.field_70170_p.func_175623_d(this.func_180425_c())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        this.b();
        this.field_70170_p.func_72900_e((Entity)this);
    }

    void c() {
        try {
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        try {
            if (!this.c) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        Vec3d vec3d = this.func_174791_d();
        Vec3d vec3d2 = vec3d.func_178786_a(0.75, 0.75, 0.75);
        Vec3d vec3d3 = vec3d.func_72441_c(0.75, 0.75, 0.75);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c, vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c);
        List list = this.field_70170_p.func_72872_a(f_.class, axisAlignedBB);
        try {
            if (list.isEmpty()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f, true);
        for (f_ f_2 : list) {
            f_2.f(this.func_174791_d());
        }
        this.field_70170_p.func_72900_e((Entity)this);
    }

    void a() {
        this.a(b6.b(this.field_70142_S, this.field_70165_t, 0.5), b6.b(this.field_70137_T, this.field_70163_u, 0.5), b6.b(this.field_70136_U, this.field_70161_v, 0.5));
        this.a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    }

    void a(double d10, double d11, double d12) {
        Random random = this.func_70681_au();
        this.field_70170_p.func_175688_a(EnumParticleTypes.DRAGON_BREATH, d10 + random.nextDouble() * (double)0.3f, d11 + 0.25 + random.nextDouble() * (double)0.3f, d12 + random.nextDouble() * (double)0.3f, 0.0, 0.0, 0.0, new int[0]);
    }

    void b() {
        try {
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        try {
            if (this.field_70128_L) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        try {
            if (!this.i) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        Vec3d vec3d = new Vec3d(this.field_70165_t, (double)(this.func_180425_c().func_177956_o() + 1), this.field_70161_v);
        try {
            if (!this.b(vec3d)) {
                this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0f, true);
                this.i = false;
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        EntityWitherSkeleton entityWitherSkeleton = new EntityWitherSkeleton(this.field_70170_p);
        entityWitherSkeleton.func_184611_a(EnumHand.MAIN_HAND, new ItemStack(Items.field_151052_q));
        entityWitherSkeleton.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        this.field_70170_p.func_72838_d((Entity)entityWitherSkeleton);
        ge.b.sendToAllTracking((IMessage)new bv(vec3d, true), (Entity)this);
        this.f.bI.add(entityWitherSkeleton);
    }

    boolean b(Vec3d vec3d) {
        boolean bl2;
        try {
            if (this.f == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        EntityLivingBase entityLivingBase = this.f.M();
        try {
            if (entityLivingBase == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        try {
            bl2 = entityLivingBase.func_70011_f(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c) < 15.0;
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        return bl2;
    }

    @SideOnly(value=Side.CLIENT)
    public static void a(Vec3d vec3d) {
        WorldClient worldClient = Minecraft.func_71410_x().field_71441_e;
        float f10 = gc.c(1.8f);
        Random random = r.f;
        float f11 = 0.0f;
        while ((double)f11 < Math.PI * 2) {
            double d10 = Math.sin(f11);
            double d11 = Math.cos(f11);
            double d12 = vec3d.field_72450_a + d10 * 0.5;
            double d13 = d10 * (double)0.15f;
            double d14 = vec3d.field_72449_c + d11 * 0.5;
            double d15 = d11 * (double)0.15f;
            double d16 = vec3d.field_72448_b;
            double d17 = random.nextDouble() * (double)0.15f;
            worldClient.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d12, d16, d14, d13, d17, d15, new int[0]);
            f11 += f10;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void c(Vec3d vec3d) {
        WorldClient worldClient = Minecraft.func_71410_x().field_71441_e;
        Random random = r.f;
        try {
            for (int i2 = 0; i2 < 100; ++i2) {
                worldClient.func_175688_a(EnumParticleTypes.DRAGON_BREATH, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, random.nextDouble() * (double)0.15f, random.nextDouble() * (double)0.15f, random.nextDouble() * (double)0.15f, new int[0]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        worldClient.func_184134_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, com.trolmastercard.sexmod.c.MISC_SHATTER[0], SoundCategory.AMBIENT, 0.7f, 1.0f, false);
    }

    public boolean func_70097_a(DamageSource damageSource, float f10) {
        block11: {
            try {
                if (DamageSource.field_76380_i.equals(damageSource)) {
                    this.func_70606_j(0.0f);
                    this.i = false;
                    this.field_70170_p.func_72900_e((Entity)this);
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw c4.a(runtimeException);
            }
            try {
                if (this.field_70170_p.field_72995_K || !"arrow".equals(damageSource.field_76373_n)) break block11;
            }
            catch (RuntimeException runtimeException) {
                throw c4.a(runtimeException);
            }
            this.func_70606_j(0.0f);
            this.i = false;
            ge.b.sendToAllTracking((IMessage)new bv(this.func_174791_d(), false), (Entity)this);
            Entity entity = damageSource.func_76364_f();
            try {
                if (entity != null) {
                    this.field_70170_p.func_72900_e(entity);
                }
            }
            catch (RuntimeException runtimeException) {
                throw c4.a(runtimeException);
            }
            this.field_70170_p.func_72900_e((Entity)this);
            return true;
        }
        Entity entity = damageSource.func_76346_g();
        try {
            if (!(entity instanceof EntityPlayer)) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c4.a(runtimeException);
        }
        this.h = entity.func_70040_Z();
        this.c = true;
        return true;
    }

    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        this.field_70170_p.func_72900_e((Entity)this);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

