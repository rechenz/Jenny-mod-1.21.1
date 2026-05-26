/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.ai.EntityMoveHelper$Action
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigateGround
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.datafix.DataFixer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.loot.LootTableList
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.fn;
import com.trolmastercard.sexmod.r;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class ay
extends EntityLiving {
    public static int b = 8400;
    public static List<ay> g = new ArrayList<ay>();
    private static final DataParameter<Integer> d = EntityDataManager.func_187226_a(ay.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(111);
    private static final DataParameter<Integer> c = EntityDataManager.func_187226_a(ay.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(110);
    public float a;
    public float e;
    public float h;
    private boolean f;

    public ay(World world) {
        super(world);
        this.field_70765_h = new b(this);
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new d(this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new c(this));
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(c, (Object)1);
        this.field_70180_af.func_187214_a(d, (Object)0);
    }

    public void func_180430_e(float f10, float f11) {
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected void a(int n2, boolean bl2) {
        try {
            this.field_70180_af.func_187227_b(c, (Object)n2);
            this.func_70105_a(0.51000005f * (float)n2, 0.51000005f * (float)n2);
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(n2 * n2));
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)(0.2f + 0.1f * (float)n2));
            if (bl2) {
                this.func_70606_j(this.func_110138_aP());
            }
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        this.field_70728_aV = n2;
    }

    public int h() {
        return (Integer)this.field_70180_af.func_187225_a(c);
    }

    public static void a(DataFixer dataFixer) {
        EntityLiving.func_189752_a((DataFixer)dataFixer, ay.class);
    }

    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        super.func_70014_b(nBTTagCompound);
        nBTTagCompound.func_74768_a("Size", this.h() - 1);
        nBTTagCompound.func_74757_a("wasOnGround", this.f);
        nBTTagCompound.func_74768_a("ageInTicks", ((Integer)this.field_70180_af.func_187225_a(d)).intValue());
    }

    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        super.func_70037_a(nBTTagCompound);
        int n2 = nBTTagCompound.func_74762_e("Size");
        if (n2 < 0) {
            n2 = 0;
        }
        this.a(n2 + 1, false);
        this.f = nBTTagCompound.func_74767_n("wasOnGround");
        this.field_70180_af.func_187227_b(d, (Object)nBTTagCompound.func_74762_e("ageInTicks"));
    }

    public boolean j() {
        boolean bl2;
        try {
            bl2 = this.h() <= 1;
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        return bl2;
    }

    protected EnumParticleTypes g() {
        return EnumParticleTypes.SLIME;
    }

    public static ArrayList<ay> a(Vec3d vec3d) {
        ArrayList<ay> arrayList = ay.a(vec3d, 0.1);
        if (arrayList.isEmpty()) {
            arrayList = ay.a(vec3d, 0.5);
        }
        return arrayList;
    }

    /*
     * Loose catch block
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static ArrayList<ay> a(Vec3d vec3d, double d10) {
        ArrayList<ay> arrayList = new ArrayList<ay>();
        try {
            for (ay ay2 : g) {
                try {
                    if (ay2 == null) {
                        continue;
                    }
                }
                catch (Exception exception) {
                    throw ay.a(exception);
                }
                double d11 = Math.abs(ay2.field_70169_q - vec3d.field_72450_a) + Math.abs(ay2.field_70167_r - vec3d.field_72448_b) + Math.abs(ay2.field_70166_s - vec3d.field_72449_c);
                if (ay2.field_70170_p == null) continue;
                try {
                    block10: {
                        if (!(d11 < d10)) continue;
                        break block10;
                        catch (Exception exception) {
                            throw ay.a(exception);
                        }
                    }
                    arrayList.add(ay2);
                }
                catch (Exception exception) {
                    throw ay.a(exception);
                    return arrayList;
                }
            }
        }
        catch (Exception exception) {
            System.out.println("couldnt find slimes at distance " + d10);
        }
        return arrayList;
    }

    public Vec3d e() {
        return new Vec3d(this.field_70169_q, this.field_70167_r, this.field_70166_s);
    }

    void a(EnumParticleTypes enumParticleTypes) {
        double d10 = r.f.nextGaussian() * 0.02;
        double d11 = r.f.nextGaussian() * 0.02;
        double d12 = r.f.nextGaussian() * 0.02;
        this.field_70170_p.func_175688_a(enumParticleTypes, this.field_70165_t + (double)(r.f.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.15 + (double)(r.f.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(r.f.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d10, d11, d12, new int[0]);
    }

    /*
     * Unable to fully structure code
     */
    public void func_70071_h_() {
        block21: {
            block20: {
                block19: {
                    block17: {
                        block18: {
                            try {
                                try {
                                    this.field_70180_af.func_187227_b(ay.d, (Object)((Integer)this.field_70180_af.func_187225_a(ay.d) + 1));
                                    if (!this.field_70170_p.field_72995_K) break block17;
                                    if (!((double)((Integer)this.field_70180_af.func_187225_a(ay.d)).intValue() > (double)ay.b * 0.95)) break block18;
                                }
                                catch (RuntimeException v0) {
                                    throw ay.a(v0);
                                }
                                this.a(EnumParticleTypes.CLOUD);
                                break block19;
                            }
                            catch (RuntimeException v1) {
                                throw ay.a(v1);
                            }
                        }
                        try {
                            try {
                                if (!((double)((Integer)this.field_70180_af.func_187225_a(ay.d)).intValue() > (double)ay.b * 0.7) || this.field_70173_aa % 10 != 0) ** GOTO lbl32
                            }
                            catch (RuntimeException v2) {
                                throw ay.a(v2);
                            }
                            this.a(EnumParticleTypes.VILLAGER_HAPPY);
                        }
                        catch (RuntimeException v3) {
                            throw ay.a(v3);
                        }
                    }
                    if ((Integer)this.field_70180_af.func_187225_a(ay.d) > ay.b) {
                        var1_1 = new fn(this.field_70170_p);
                        var1_1.func_70080_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
                        this.field_70170_p.func_72838_d((Entity)var1_1);
                        var1_1.a(SoundEvents.field_187604_bf);
                        this.field_70170_p.func_72900_e((Entity)this);
                    }
                }
                try {
                    this.e += (this.a - this.e) * 0.5f;
                    this.h = this.e;
                    super.func_70071_h_();
                    if (!this.field_70122_E || this.f) break block20;
                }
                catch (RuntimeException v4) {
                    throw ay.a(v4);
                }
                var1_2 = this.h();
                if (this.k()) {
                    var1_2 = 0;
                }
                for (var2_3 = 0; var2_3 < var1_2 * 8; ++var2_3) {
                    var3_4 = this.field_70146_Z.nextFloat() * 6.2831855f;
                    var4_5 = this.field_70146_Z.nextFloat() * 0.5f + 0.5f;
                    var5_6 = MathHelper.func_76126_a((float)var3_4) * (float)var1_2 * 0.5f * var4_5;
                    var6_7 = MathHelper.func_76134_b((float)var3_4) * (float)var1_2 * 0.5f * var4_5;
                    var7_8 = this.field_70170_p;
                    var8_9 = this.g();
                    var9_10 = this.field_70165_t + (double)var5_6;
                    var11_11 = this.field_70161_v + (double)var6_7;
                    var7_8.func_175688_a(var8_9, var9_10, this.func_174813_aQ().field_72338_b, var11_11, 0.0, 0.0, 0.0, new int[0]);
                }
                this.func_184185_a(this.f(), this.func_70599_aP(), ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f) / 0.8f);
                this.a = -0.5f;
                break block21;
            }
            try {
                try {
                    if (this.field_70122_E || !this.f) break block21;
                }
                catch (RuntimeException v5) {
                    throw ay.a(v5);
                }
                this.a = 1.0f;
            }
            catch (RuntimeException v6) {
                throw ay.a(v6);
            }
        }
        this.f = this.field_70122_E;
        this.b();
    }

    protected void b() {
        this.a *= 0.6f;
    }

    protected int a() {
        return this.field_70146_Z.nextInt(100) + 50;
    }

    protected ay d() {
        return new ay(this.field_70170_p);
    }

    public void func_184206_a(DataParameter<?> dataParameter) {
        block5: {
            if (c.equals(dataParameter)) {
                int n2 = this.h();
                try {
                    try {
                        this.func_70105_a(0.51000005f * (float)n2, 0.51000005f * (float)n2);
                        this.field_70177_z = this.field_70759_as;
                        this.field_70761_aq = this.field_70759_as;
                        if (!this.func_70090_H() || this.field_70146_Z.nextInt(20) != 0) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ay.a(runtimeException);
                    }
                    this.func_71061_d_();
                }
                catch (RuntimeException runtimeException) {
                    throw ay.a(runtimeException);
                }
            }
        }
        super.func_184206_a(dataParameter);
    }

    public void func_70106_y() {
        block11: {
            int n2 = this.h();
            try {
                try {
                    if (this.field_70170_p.field_72995_K || n2 <= 1) break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw ay.a(runtimeException);
                }
                if (!(this.func_110143_aJ() <= 0.0f)) break block11;
            }
            catch (RuntimeException runtimeException) {
                throw ay.a(runtimeException);
            }
            int n3 = 2 + this.field_70146_Z.nextInt(3);
            for (int i2 = 0; i2 < n3; ++i2) {
                float f10 = ((float)(i2 % 2) - 0.5f) * (float)n2 / 4.0f;
                float f11 = ((float)(i2 / 2) - 0.5f) * (float)n2 / 4.0f;
                ay ay2 = this.d();
                try {
                    if (this.func_145818_k_()) {
                        ay2.func_96094_a(this.func_95999_t());
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ay.a(runtimeException);
                }
                try {
                    if (this.func_104002_bU()) {
                        ay2.func_110163_bv();
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ay.a(runtimeException);
                }
                ay2.a(n2 / 2, true);
                ay2.func_70012_b(this.field_70165_t + (double)f10, this.field_70163_u + 0.5, this.field_70161_v + (double)f11, this.field_70146_Z.nextFloat() * 360.0f, 0.0f);
                this.field_70170_p.func_72838_d((Entity)ay2);
            }
        }
        super.func_70106_y();
    }

    public float func_70047_e() {
        return 0.625f * this.field_70131_O;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSource) {
        SoundEvent soundEvent;
        try {
            soundEvent = this.j() ? SoundEvents.field_187898_fy : SoundEvents.field_187880_fp;
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        return soundEvent;
    }

    protected SoundEvent func_184615_bR() {
        SoundEvent soundEvent;
        try {
            soundEvent = this.j() ? SoundEvents.field_187896_fx : SoundEvents.field_187874_fm;
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        return soundEvent;
    }

    protected SoundEvent f() {
        SoundEvent soundEvent;
        try {
            soundEvent = this.j() ? SoundEvents.field_187900_fz : SoundEvents.field_187886_fs;
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        return soundEvent;
    }

    protected Item func_146068_u() {
        Object object;
        try {
            object = this.h() == 1 ? Items.field_151123_aH : null;
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        return object;
    }

    @Nullable
    protected ResourceLocation func_184647_J() {
        ResourceLocation resourceLocation;
        try {
            resourceLocation = this.h() == 1 ? LootTableList.field_186378_ac : LootTableList.field_186419_a;
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        return resourceLocation;
    }

    protected float func_70599_aP() {
        return 0.4f * (float)this.h();
    }

    public int func_70646_bf() {
        return 0;
    }

    protected boolean i() {
        boolean bl2;
        try {
            bl2 = this.h() > 0;
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        return bl2;
    }

    protected void func_70664_aZ() {
        this.field_70181_x = 0.42f;
        this.field_70160_al = true;
    }

    @Nullable
    public IEntityLivingData func_180482_a(DifficultyInstance difficultyInstance, @Nullable IEntityLivingData iEntityLivingData) {
        this.a(1, true);
        return super.func_180482_a(difficultyInstance, iEntityLivingData);
    }

    protected SoundEvent c() {
        SoundEvent soundEvent;
        try {
            soundEvent = this.j() ? SoundEvents.field_189110_fE : SoundEvents.field_187882_fq;
        }
        catch (RuntimeException runtimeException) {
            throw ay.a(runtimeException);
        }
        return soundEvent;
    }

    protected boolean k() {
        return false;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    static class b
    extends EntityMoveHelper {
        private float b;
        private int c;
        private final ay d;
        private boolean a;

        public b(ay ay2) {
            super((EntityLiving)ay2);
            this.d = ay2;
            this.b = 180.0f * ay2.field_70177_z / (float)Math.PI;
        }

        public void a(float f10, boolean bl2) {
            this.b = f10;
            this.a = bl2;
        }

        public void a(double d10) {
            this.field_75645_e = d10;
            this.field_188491_h = EntityMoveHelper.Action.MOVE_TO;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public void func_75641_c() {
            block11: {
                block12: {
                    block13: {
                        block10: {
                            try {
                                this.field_75648_a.field_70759_as = this.field_75648_a.field_70177_z = this.func_75639_a(this.field_75648_a.field_70177_z, this.b, 90.0f);
                                this.field_75648_a.field_70761_aq = this.field_75648_a.field_70177_z;
                                if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) break block10;
                                this.field_75648_a.func_191989_p(0.0f);
                                return;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.ay$b.a(runtimeException);
                            }
                        }
                        try {
                            try {
                                try {
                                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                                    if (!this.field_75648_a.field_70122_E) break block11;
                                    this.field_75648_a.func_70659_e((float)(this.field_75645_e * this.field_75648_a.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e()));
                                    if (this.c-- > 0) break block12;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.ay$b.a(runtimeException);
                                }
                                this.c = this.d.a();
                                if (!this.a) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.ay$b.a(runtimeException);
                            }
                            this.c /= 3;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.ay$b.a(runtimeException);
                        }
                    }
                    float f10 = r.f.nextInt(360);
                    try {
                        ((b)this.d.func_70605_aq()).a(f10, false);
                        this.d.func_70683_ar().func_75660_a();
                        if (!this.d.i()) return;
                        this.d.func_184185_a(this.d.c(), this.d.func_70599_aP(), ((this.d.func_70681_au().nextFloat() - this.d.func_70681_au().nextFloat()) * 0.2f + 1.0f) * 0.8f);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ay$b.a(runtimeException);
                    }
                }
                this.d.field_70702_br = 0.0f;
                this.d.field_191988_bg = 0.0f;
                this.field_75648_a.func_70659_e(0.0f);
                return;
            }
            this.field_75648_a.func_70659_e((float)(this.field_75645_e * this.field_75648_a.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e()));
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }

    static class c
    extends EntityAIBase {
        private final ay a;

        public c(ay ay2) {
            this.a = ay2;
            this.func_75248_a(5);
        }

        public boolean func_75250_a() {
            return true;
        }

        public void func_75246_d() {
            ((b)this.a.func_70605_aq()).a(1.0);
        }
    }

    static class d
    extends EntityAIBase {
        private final ay a;

        public d(ay ay2) {
            this.a = ay2;
            this.func_75248_a(5);
            ((PathNavigateGround)ay2.func_70661_as()).func_179693_d(true);
        }

        public boolean func_75250_a() {
            boolean bl2;
            block5: {
                block4: {
                    try {
                        try {
                            if (!this.a.func_70090_H() && !this.a.func_180799_ab()) break block4;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.ay$d.a(runtimeException);
                        }
                        bl2 = true;
                        break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ay$d.a(runtimeException);
                    }
                }
                bl2 = false;
            }
            return bl2;
        }

        public void func_75246_d() {
            try {
                if (this.a.func_70681_au().nextFloat() < 0.8f) {
                    this.a.func_70683_ar().func_75660_a();
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ay$d.a(runtimeException);
            }
            ((b)this.a.func_70605_aq()).a(1.2);
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }

    static class a
    extends EntityAIBase {
        private final ay b;
        private float a;
        private int c;

        public a(ay ay2) {
            this.b = ay2;
            this.func_75248_a(2);
        }

        public boolean func_75250_a() {
            boolean bl2;
            block12: {
                block10: {
                    try {
                        block11: {
                            try {
                                try {
                                    try {
                                        try {
                                            if (this.b.func_70638_az() != null) break block10;
                                            if (this.b.field_70122_E) break block11;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw com.trolmastercard.sexmod.ay$a.a(runtimeException);
                                        }
                                        if (this.b.func_70090_H()) break block11;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw com.trolmastercard.sexmod.ay$a.a(runtimeException);
                                    }
                                    if (this.b.func_180799_ab()) break block11;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.ay$a.a(runtimeException);
                                }
                                if (!this.b.func_70644_a(MobEffects.field_188424_y)) break block10;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.ay$a.a(runtimeException);
                            }
                        }
                        bl2 = true;
                        break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ay$a.a(runtimeException);
                    }
                }
                bl2 = false;
            }
            return bl2;
        }

        public void func_75246_d() {
            try {
                if (--this.c <= 0) {
                    this.c = 40 + this.b.func_70681_au().nextInt(60);
                    this.a = this.b.func_70681_au().nextInt(360);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ay$a.a(runtimeException);
            }
            ((b)this.b.func_70605_aq()).a(this.a, false);
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

