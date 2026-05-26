/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.storage.loot.LootContext$Builder
 *  net.minecraft.world.storage.loot.LootTableList
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.em;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class gi
extends Entity {
    public static final int m = 15;
    private static final DataParameter<Integer> g = EntityDataManager.func_187226_a(gi.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(111);
    private static final DataParameter<Optional<UUID>> f = EntityDataManager.func_187226_a(gi.class, (DataSerializer)DataSerializers.field_187203_m).func_187156_b().func_187161_a(110);
    private boolean k;
    private int l;
    private int h;
    public int d;
    private int c;
    private int j;
    private float e;
    public Entity i;
    private a n = com.trolmastercard.sexmod.gi$a.FLYING;
    private int a;
    private int o;
    public static eb b = null;

    public gi(World world, eb eb2, double d10) {
        super(world);
        this.a(eb2);
        this.a(d10);
    }

    public gi(World world) {
        super(world);
    }

    private void a(eb eb2) {
        this.func_70105_a(0.25f, 0.25f);
        this.field_70158_ak = true;
        eb2.av = this;
    }

    protected void func_70088_a() {
        this.func_184212_Q().func_187214_a(g, (Object)0);
        this.func_184212_Q().func_187214_a(f, (Object)Optional.of((Object)b.f()));
    }

    public AxisAlignedBB func_184177_bl() {
        return this.func_174813_aQ().func_186662_g(10.0);
    }

    eb b() {
        Optional optional = (Optional)this.field_70180_af.func_187225_a(f);
        try {
            if (!optional.isPresent()) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        em em2 = em.a((UUID)optional.get());
        try {
            if (em2 == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        try {
            if (!(em2 instanceof eb)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        return (eb)em2;
    }

    eb g() {
        Optional optional = (Optional)this.field_70180_af.func_187225_a(f);
        try {
            if (!optional.isPresent()) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        em em2 = em.b((UUID)optional.get());
        try {
            if (!(em2 instanceof eb)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        return (eb)em2;
    }

    public void b(int n2) {
        this.o = n2;
    }

    public void a(int n2) {
        this.a = n2;
    }

    public void func_70030_z() {
        block9: {
            try {
                super.func_70030_z();
                if (this.field_70170_p.field_72995_K) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw gi.a(runtimeException);
            }
            try {
                try {
                    try {
                        if (this.i == null && !this.field_70122_E) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw gi.a(runtimeException);
                    }
                    if (this.d != 0) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw gi.a(runtimeException);
                }
                this.b().o();
            }
            catch (RuntimeException runtimeException) {
                throw gi.a(runtimeException);
            }
        }
    }

    public void a(double d10) {
        eb eb2 = this.b();
        try {
            if (eb2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        BlockPos blockPos = eb2.ai;
        float f10 = (float)Math.sqrt(eb2.func_174791_d().func_186679_c((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()));
        float f11 = -22.5f + 45.0f * (f10 / 7.0f);
        float f12 = eb2.I().floatValue();
        float f13 = MathHelper.func_76134_b((float)(-f12 * ((float)Math.PI / 180) - (float)Math.PI));
        float f14 = MathHelper.func_76126_a((float)(-f12 * ((float)Math.PI / 180) - (float)Math.PI));
        float f15 = -MathHelper.func_76134_b((float)(-f11 * ((float)Math.PI / 180)));
        float f16 = MathHelper.func_76126_a((float)(-f11 * ((float)Math.PI / 180)));
        double d11 = eb2.field_70169_q + (eb2.field_70165_t - eb2.field_70169_q) - (double)f14 * 0.3;
        double d12 = eb2.field_70167_r + (eb2.field_70163_u - eb2.field_70167_r) + (double)eb2.func_70047_e();
        double d13 = eb2.field_70166_s + (eb2.field_70161_v - eb2.field_70166_s) - (double)f13 * 0.3;
        this.func_70012_b(d11, d12, d13, f12, f11);
        this.field_70159_w = d10 * (double)(-f14);
        this.field_70181_x = d10 * (double)MathHelper.func_76131_a((float)(-(f16 / f15)), (float)-5.0f, (float)5.0f);
        this.field_70179_y = d10 * (double)(-f13);
        float f17 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
        this.field_70159_w *= 0.6 / (double)f17 + 0.5 + this.field_70146_Z.nextGaussian() * 0.0045;
        this.field_70181_x *= 0.6 / (double)f17 + 0.5 + this.field_70146_Z.nextGaussian() * 0.0045;
        this.field_70179_y *= 0.6 / (double)f17 + 0.5 + this.field_70146_Z.nextGaussian() * 0.0045;
        float f18 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
        this.field_70177_z = (float)(MathHelper.func_181159_b((double)this.field_70159_w, (double)this.field_70179_y) * 57.29577951308232);
        this.field_70125_A = (float)(MathHelper.func_181159_b((double)this.field_70181_x, (double)f18) * 57.29577951308232);
        this.field_70126_B = this.field_70177_z;
        this.field_70127_C = this.field_70125_A;
    }

    public void func_184206_a(DataParameter<?> dataParameter) {
        if (g.equals(dataParameter)) {
            Entity entity;
            int n2 = (Integer)this.func_184212_Q().func_187225_a(g);
            try {
                gi gi2 = this;
                entity = n2 > 0 ? this.field_70170_p.func_73045_a(n2 - 1) : null;
            }
            catch (RuntimeException runtimeException) {
                throw gi.a(runtimeException);
            }
            gi2.i = entity;
        }
        super.func_184206_a(dataParameter);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double d10) {
        boolean bl2;
        double d11 = 64.0;
        try {
            bl2 = d10 < 4096.0;
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        return bl2;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_180426_a(double d10, double d11, double d12, float f10, float f11, int n2, boolean bl2) {
    }

    public void func_70071_h_() {
        block43: {
            double d10;
            IBlockState iBlockState;
            block48: {
                BlockPos blockPos;
                float f10;
                block49: {
                    block50: {
                        block51: {
                            block45: {
                                block47: {
                                    block46: {
                                        block44: {
                                            block42: {
                                                try {
                                                    super.func_70071_h_();
                                                    if (this.b() != null) break block42;
                                                    this.func_70106_y();
                                                    break block43;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw gi.a(runtimeException);
                                                }
                                            }
                                            try {
                                                try {
                                                    try {
                                                        try {
                                                            if (!this.field_70170_p.field_72995_K && this.f()) break block43;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw gi.a(runtimeException);
                                                        }
                                                        if (!this.k) break block44;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw gi.a(runtimeException);
                                                    }
                                                    ++this.l;
                                                    if (this.l < 1200) break block44;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw gi.a(runtimeException);
                                                }
                                                this.func_70106_y();
                                                return;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw gi.a(runtimeException);
                                            }
                                        }
                                        f10 = 0.0f;
                                        blockPos = new BlockPos((Entity)this);
                                        iBlockState = this.field_70170_p.func_180495_p(blockPos);
                                        if (iBlockState.func_185904_a() == Material.field_151586_h) {
                                            f10 = BlockLiquid.func_190973_f((IBlockState)iBlockState, (IBlockAccess)this.field_70170_p, (BlockPos)blockPos);
                                        }
                                        try {
                                            try {
                                                if (this.n != com.trolmastercard.sexmod.gi$a.FLYING) break block45;
                                                if (this.i == null) break block46;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw gi.a(runtimeException);
                                            }
                                            this.field_70159_w = 0.0;
                                            this.field_70181_x = 0.0;
                                            this.field_70179_y = 0.0;
                                            this.n = com.trolmastercard.sexmod.gi$a.HOOKED_IN_ENTITY;
                                            return;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw gi.a(runtimeException);
                                        }
                                    }
                                    try {
                                        if (f10 > 0.0f) {
                                            this.field_70159_w *= 0.3;
                                            this.field_70181_x *= 0.2;
                                            this.field_70179_y *= 0.3;
                                            this.n = com.trolmastercard.sexmod.gi$a.BOBBING;
                                            return;
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gi.a(runtimeException);
                                    }
                                    try {
                                        if (!this.field_70170_p.field_72995_K) {
                                            this.e();
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gi.a(runtimeException);
                                    }
                                    try {
                                        try {
                                            try {
                                                if (this.k || this.field_70122_E) break block47;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw gi.a(runtimeException);
                                            }
                                            if (this.field_70123_F) break block47;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw gi.a(runtimeException);
                                        }
                                        ++this.h;
                                        break block48;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gi.a(runtimeException);
                                    }
                                }
                                this.h = 0;
                                this.field_70159_w = 0.0;
                                this.field_70181_x = 0.0;
                                this.field_70179_y = 0.0;
                                break block48;
                            }
                            try {
                                try {
                                    try {
                                        if (this.n != com.trolmastercard.sexmod.gi$a.HOOKED_IN_ENTITY) break block49;
                                        if (this.i == null) break block50;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gi.a(runtimeException);
                                    }
                                    if (!this.i.field_70128_L) break block51;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gi.a(runtimeException);
                                }
                                this.i = null;
                                this.n = com.trolmastercard.sexmod.gi$a.FLYING;
                                break block50;
                            }
                            catch (RuntimeException runtimeException) {
                                throw gi.a(runtimeException);
                            }
                        }
                        this.field_70165_t = this.i.field_70165_t;
                        double d11 = this.i.field_70131_O;
                        this.field_70163_u = this.i.func_174813_aQ().field_72338_b + d11 * 0.8;
                        this.field_70161_v = this.i.field_70161_v;
                        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    }
                    return;
                }
                if (this.n == com.trolmastercard.sexmod.gi$a.BOBBING) {
                    this.field_70159_w *= 0.9;
                    this.field_70179_y *= 0.9;
                    d10 = this.field_70163_u + this.field_70181_x - (double)blockPos.func_177956_o() - (double)f10;
                    if (Math.abs(d10) < 0.01) {
                        d10 += Math.signum(d10) * 0.1;
                    }
                    try {
                        try {
                            this.field_70181_x -= d10 * (double)this.field_70146_Z.nextFloat() * 0.2;
                            if (this.field_70170_p.field_72995_K || !(f10 > 0.0f)) break block48;
                        }
                        catch (RuntimeException runtimeException) {
                            throw gi.a(runtimeException);
                        }
                        this.a(blockPos);
                    }
                    catch (RuntimeException runtimeException) {
                        throw gi.a(runtimeException);
                    }
                }
            }
            try {
                if (iBlockState.func_185904_a() != Material.field_151586_h) {
                    this.field_70181_x -= 0.03;
                }
            }
            catch (RuntimeException runtimeException) {
                throw gi.a(runtimeException);
            }
            this.func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.h();
            d10 = 0.92;
            this.field_70159_w *= 0.92;
            this.field_70181_x *= 0.92;
            this.field_70179_y *= 0.92;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
    }

    private boolean f() {
        return false;
    }

    private void h() {
        float f10 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
        this.field_70177_z = (float)(MathHelper.func_181159_b((double)this.field_70159_w, (double)this.field_70179_y) * 57.29577951308232);
        this.field_70125_A = (float)(MathHelper.func_181159_b((double)this.field_70181_x, (double)f10) * 57.29577951308232);
        try {
            while (this.field_70125_A - this.field_70127_C < -180.0f) {
                this.field_70127_C -= 360.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        try {
            while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                this.field_70127_C += 360.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        try {
            while (this.field_70177_z - this.field_70126_B < -180.0f) {
                this.field_70126_B -= 360.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        try {
            while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                this.field_70126_B += 360.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gi.a(runtimeException);
        }
        this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2f;
        this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2f;
    }

    private void e() {
        block16: {
            block17: {
                Vec3d vec3d = new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                Vec3d vec3d2 = new Vec3d(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
                RayTraceResult rayTraceResult = this.field_70170_p.func_147447_a(vec3d, vec3d2, false, true, false);
                vec3d = new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                vec3d2 = new Vec3d(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
                if (rayTraceResult != null) {
                    vec3d2 = new Vec3d(rayTraceResult.field_72307_f.field_72450_a, rayTraceResult.field_72307_f.field_72448_b, rayTraceResult.field_72307_f.field_72449_c);
                }
                Entity entity = null;
                List list = this.field_70170_p.func_72839_b((Entity)this, this.func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_186662_g(1.0));
                double d10 = 0.0;
                for (Entity entity2 : list) {
                    AxisAlignedBB axisAlignedBB;
                    RayTraceResult rayTraceResult2;
                    block15: {
                        try {
                            try {
                                if (!this.a(entity2)) continue;
                                if (entity2 != this.b()) break block15;
                            }
                            catch (RuntimeException runtimeException) {
                                throw gi.a(runtimeException);
                            }
                            if (this.h < 5) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw gi.a(runtimeException);
                        }
                    }
                    if ((rayTraceResult2 = (axisAlignedBB = entity2.func_174813_aQ().func_186662_g((double)0.3f)).func_72327_a(vec3d, vec3d2)) == null) continue;
                    double d11 = vec3d.func_72436_e(rayTraceResult2.field_72307_f);
                    try {
                        if (!(d11 < d10) && d10 != 0.0) continue;
                    }
                    catch (RuntimeException runtimeException) {
                        throw gi.a(runtimeException);
                    }
                    entity = entity2;
                    d10 = d11;
                }
                if (entity != null) {
                    rayTraceResult = new RayTraceResult(entity);
                }
                try {
                    try {
                        try {
                            if (rayTraceResult == null || rayTraceResult.field_72313_a == RayTraceResult.Type.MISS) break block16;
                        }
                        catch (RuntimeException runtimeException) {
                            throw gi.a(runtimeException);
                        }
                        if (rayTraceResult.field_72313_a != RayTraceResult.Type.ENTITY) break block17;
                    }
                    catch (RuntimeException runtimeException) {
                        throw gi.a(runtimeException);
                    }
                    this.i = rayTraceResult.field_72308_g;
                    this.a();
                    break block16;
                }
                catch (RuntimeException runtimeException) {
                    throw gi.a(runtimeException);
                }
            }
            this.k = true;
        }
    }

    private void a() {
        this.func_184212_Q().func_187227_b(g, (Object)(this.i.func_145782_y() + 1));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a(BlockPos blockPos) {
        int n2;
        WorldServer worldServer;
        block34: {
            block35: {
                double d10;
                double d11;
                double d12;
                float f10;
                float f11;
                block36: {
                    block32: {
                        block33: {
                            block31: {
                                BlockPos blockPos2;
                                block30: {
                                    worldServer = (WorldServer)this.field_70170_p;
                                    n2 = 1;
                                    blockPos2 = blockPos.func_177984_a();
                                    try {
                                        try {
                                            if (!(this.field_70146_Z.nextFloat() < 0.25f) || !this.field_70170_p.func_175727_C(blockPos2)) break block30;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw gi.a(runtimeException);
                                        }
                                        ++n2;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gi.a(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (!(this.field_70146_Z.nextFloat() < 0.5f) || this.field_70170_p.func_175678_i(blockPos2)) break block31;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gi.a(runtimeException);
                                    }
                                    --n2;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gi.a(runtimeException);
                                }
                            }
                            try {
                                try {
                                    if (this.d <= 0) break block32;
                                    --this.d;
                                    if (this.d > 0) break block33;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gi.a(runtimeException);
                                }
                                this.c = 0;
                                this.j = 0;
                                return;
                            }
                            catch (RuntimeException runtimeException) {
                                throw gi.a(runtimeException);
                            }
                        }
                        this.field_70181_x -= 0.2 * (double)this.field_70146_Z.nextFloat() * (double)this.field_70146_Z.nextFloat();
                        return;
                    }
                    try {
                        if (this.j <= 0) break block34;
                        this.j -= n2;
                        if (this.j <= 0) break block35;
                    }
                    catch (RuntimeException runtimeException) {
                        throw gi.a(runtimeException);
                    }
                    this.e = (float)((double)this.e + this.field_70146_Z.nextGaussian() * 4.0);
                    float f12 = this.e * ((float)Math.PI / 180);
                    f11 = MathHelper.func_76126_a((float)f12);
                    f10 = MathHelper.func_76134_b((float)f12);
                    d12 = this.field_70165_t + (double)(f11 * (float)this.j * 0.1f);
                    d11 = (float)MathHelper.func_76128_c((double)this.func_174813_aQ().field_72338_b) + 1.0f;
                    d10 = this.field_70161_v + (double)(f10 * (float)this.j * 0.1f);
                    IBlockState iBlockState = worldServer.func_180495_p(new BlockPos(d12, d11 - 1.0, d10));
                    try {
                        try {
                            if (iBlockState.func_185904_a() != Material.field_151586_h) return;
                            if (!(this.field_70146_Z.nextFloat() < 0.15f)) break block36;
                        }
                        catch (RuntimeException runtimeException) {
                            throw gi.a(runtimeException);
                        }
                        worldServer.func_175739_a(EnumParticleTypes.WATER_BUBBLE, d12, d11 - (double)0.1f, d10, 1, (double)f11, 0.1, (double)f10, 0.0, new int[0]);
                    }
                    catch (RuntimeException runtimeException) {
                        throw gi.a(runtimeException);
                    }
                }
                float f13 = f11 * 0.04f;
                float f14 = f10 * 0.04f;
                worldServer.func_175739_a(EnumParticleTypes.WATER_WAKE, d12, d11, d10, 0, (double)f14, 0.01, (double)(-f13), 1.0, new int[0]);
                worldServer.func_175739_a(EnumParticleTypes.WATER_WAKE, d12, d11, d10, 0, (double)(-f14), 0.01, (double)f13, 1.0, new int[0]);
                return;
            }
            this.field_70181_x = -0.4f * MathHelper.func_151240_a((Random)this.field_70146_Z, (float)0.6f, (float)1.0f);
            this.func_184185_a(SoundEvents.field_187609_F, 0.25f, 1.0f + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4f);
            double d13 = this.func_174813_aQ().field_72338_b + 0.5;
            worldServer.func_175739_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t, d13, this.field_70161_v, (int)(1.0f + this.field_70130_N * 20.0f), (double)this.field_70130_N, 0.0, (double)this.field_70130_N, (double)0.2f, new int[0]);
            worldServer.func_175739_a(EnumParticleTypes.WATER_WAKE, this.field_70165_t, d13, this.field_70161_v, (int)(1.0f + this.field_70130_N * 20.0f), (double)this.field_70130_N, 0.0, (double)this.field_70130_N, (double)0.2f, new int[0]);
            this.d = MathHelper.func_76136_a((Random)this.field_70146_Z, (int)20, (int)40);
            return;
        }
        if (this.c > 0) {
            this.c -= n2;
            float f15 = 0.15f;
            if (this.c < 20) {
                f15 = (float)((double)f15 + (double)(20 - this.c) * 0.05);
            } else if (this.c < 40) {
                f15 = (float)((double)f15 + (double)(40 - this.c) * 0.02);
            } else if (this.c < 60) {
                f15 = (float)((double)f15 + (double)(60 - this.c) * 0.01);
            }
            if (this.field_70146_Z.nextFloat() < f15) {
                float f16 = MathHelper.func_151240_a((Random)this.field_70146_Z, (float)0.0f, (float)360.0f) * ((float)Math.PI / 180);
                float f17 = MathHelper.func_151240_a((Random)this.field_70146_Z, (float)25.0f, (float)60.0f);
                double d14 = this.field_70165_t + (double)(MathHelper.func_76126_a((float)f16) * f17 * 0.1f);
                double d15 = (float)MathHelper.func_76128_c((double)this.func_174813_aQ().field_72338_b) + 1.0f;
                double d16 = this.field_70161_v + (double)(MathHelper.func_76134_b((float)f16) * f17 * 0.1f);
                IBlockState iBlockState = worldServer.func_180495_p(new BlockPos((int)d14, (int)d15 - 1, (int)d16));
                try {
                    if (iBlockState.func_185904_a() == Material.field_151586_h) {
                        worldServer.func_175739_a(EnumParticleTypes.WATER_SPLASH, d14, d15, d16, 2 + this.field_70146_Z.nextInt(2), (double)0.1f, 0.0, (double)0.1f, 0.0, new int[0]);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw gi.a(runtimeException);
                }
            }
            try {
                if (this.c > 0) return;
                this.e = MathHelper.func_151240_a((Random)this.field_70146_Z, (float)0.0f, (float)360.0f);
                this.j = MathHelper.func_76136_a((Random)this.field_70146_Z, (int)20, (int)80);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw gi.a(runtimeException);
            }
        }
        this.c = MathHelper.func_76136_a((Random)this.field_70146_Z, (int)100, (int)600);
        this.c -= this.o * 20 * 5;
    }

    protected boolean a(Entity entity) {
        boolean bl2;
        block5: {
            block4: {
                try {
                    try {
                        if (!entity.func_70067_L() && !(entity instanceof EntityItem)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw gi.a(runtimeException);
                    }
                    bl2 = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw gi.a(runtimeException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    public void func_70014_b(NBTTagCompound nBTTagCompound) {
    }

    public void func_70037_a(NBTTagCompound nBTTagCompound) {
    }

    public int c() {
        block11: {
            int n2;
            Object var2_2;
            int n3;
            block15: {
                block12: {
                    int n4;
                    block14: {
                        block13: {
                            try {
                                if (this.field_70170_p.field_72995_K || this.b() == null) break block11;
                            }
                            catch (RuntimeException runtimeException) {
                                throw gi.a(runtimeException);
                            }
                            n3 = 0;
                            var2_2 = null;
                            try {
                                try {
                                    if (this.i == null) break block12;
                                    this.d();
                                    this.field_70170_p.func_72960_a((Entity)this, (byte)31);
                                    if (!(this.i instanceof EntityItem)) break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gi.a(runtimeException);
                                }
                                n4 = 3;
                                break block14;
                            }
                            catch (RuntimeException runtimeException) {
                                throw gi.a(runtimeException);
                            }
                        }
                        n4 = 5;
                    }
                    n3 = n4;
                    break block15;
                }
                if (this.d > 0) {
                    LootContext.Builder builder = new LootContext.Builder((WorldServer)this.field_70170_p);
                    List list = this.field_70170_p.func_184146_ak().func_186521_a(LootTableList.field_186387_al).func_186462_a(this.field_70146_Z, builder.func_186471_a());
                    for (ItemStack itemStack : list) {
                        eb eb2 = this.b();
                        eb2.b(itemStack);
                    }
                    this.d = 9999;
                    n3 = 1;
                }
            }
            if (this.k) {
                n3 = 2;
            }
            try {
                n2 = var2_2 == null ? n3 : var2_2.getRodDamage();
            }
            catch (RuntimeException runtimeException) {
                throw gi.a(runtimeException);
            }
            return n2;
        }
        return 0;
    }

    protected void d() {
        eb eb2 = this.b();
        if (eb2 != null) {
            double d10 = eb2.field_70165_t - this.field_70165_t;
            double d11 = eb2.field_70163_u - this.field_70163_u;
            double d12 = eb2.field_70161_v - this.field_70161_v;
            double d13 = 0.1;
            this.i.field_70159_w += d10 * 0.1;
            this.i.field_70181_x += d11 * 0.1;
            this.i.field_70179_y += d12 * 0.1;
        }
    }

    protected boolean func_70041_e_() {
        return false;
    }

    public void func_70020_e(NBTTagCompound nBTTagCompound) {
    }

    public NBTTagCompound func_189511_e(NBTTagCompound nBTTagCompound) {
        return null;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    static enum a {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING;

    }
}

