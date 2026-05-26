/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ay;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.co;
import com.trolmastercard.sexmod.dz;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import com.trolmastercard.sexmod.r;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fn
extends em {
    static final double Q = (double)0.7f;
    static final float W = 0.9f;
    static final double M = 100.0;
    static final float L = 0.1f;
    static final int O = 2400;
    a S = com.trolmastercard.sexmod.fn$a.IDLE;
    public static DataParameter<Integer> U = EntityDataManager.func_187226_a(fn.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(113);
    public static DataParameter<Float> R = EntityDataManager.func_187226_a(fn.class, (DataSerializer)DataSerializers.field_187193_c).func_187156_b().func_187161_a(112);
    public static DataParameter<Integer> T = EntityDataManager.func_187226_a(fn.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(111);
    int N = 0;
    boolean K = true;
    boolean V = false;
    int P = 0;

    public fn(World world) {
        super(world);
    }

    @Override
    public String c() {
        return "Slime";
    }

    @Override
    public float i() {
        return 1.6f;
    }

    @Override
    public void b(fp fp2) {
        block14: {
            block12: {
                try {
                    block13: {
                        try {
                            try {
                                if (this.y() != fp.CUMBLOWJOB) break block12;
                                if (fp2 == fp.THRUSTBLOWJOB) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw fn.a(runtimeException);
                            }
                            if (fp2 != fp.SUCKBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw fn.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw fn.a(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.y() != fp.DOGGYCUM) break block14;
                            if (fp2 == fp.DOGGYFAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw fn.a(runtimeException);
                        }
                        if (fp2 != fp.DOGGYSLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fn.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw fn.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean t() {
        return false;
    }

    @Override
    protected void func_184651_r() {
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.func_184212_Q().func_187214_a(T, (Object)0);
        this.func_184212_Q().func_187214_a(R, (Object)Float.valueOf(0.0f));
        this.func_184212_Q().func_187214_a(U, (Object)-1);
    }

    @Override
    protected fp a(fp fp2) {
        block9: {
            block8: {
                try {
                    try {
                        if (fp2 != fp.SUCKBLOWJOB && fp2 != fp.THRUSTBLOWJOB) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fn.a(runtimeException);
                    }
                    return fp.CUMBLOWJOB;
                }
                catch (RuntimeException runtimeException) {
                    throw fn.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.DOGGYSLOW && fp2 != fp.DOGGYFAST) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw fn.a(runtimeException);
                }
                return fp.DOGGYCUM;
            }
            catch (RuntimeException runtimeException) {
                throw fn.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.SUCKBLOWJOB) {
                return fp.THRUSTBLOWJOB;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        try {
            if (fp2 == fp.DOGGYSLOW) {
                return fp.DOGGYFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        return null;
    }

    protected float func_175134_bD() {
        return 0.9f;
    }

    @Override
    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        super.func_70014_b(nBTTagCompound);
        nBTTagCompound.func_74768_a("hornyLevel", ((Integer)this.m.func_187225_a(T)).intValue());
        nBTTagCompound.func_74768_a("ticksUntilBirth", ((Integer)this.m.func_187225_a(U)).intValue());
    }

    @Override
    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        try {
            super.func_70037_a(nBTTagCompound);
            this.m.func_187227_b(T, (Object)nBTTagCompound.func_74762_e("hornyLevel"));
            this.m.func_187227_b(U, (Object)nBTTagCompound.func_74762_e("ticksUntilBirth"));
            if ((Integer)this.m.func_187225_a(T) != 0) {
                this.m.func_187227_b(D, (Object)0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        this.field_70145_X = false;
        this.func_189654_d(false);
    }

    @Override
    protected ResourceLocation func_184647_J() {
        return dz.b;
    }

    @Override
    public void g() {
        this.m.func_187227_b(T, (Object)0);
        this.m.func_187227_b(D, (Object)1);
    }

    @Override
    public void func_70619_bc() {
        block8: {
            block9: {
                try {
                    try {
                        try {
                            try {
                                super.func_70619_bc();
                                this.a();
                                this.c();
                                if (!this.func_70644_a(co.b) || this.S != com.trolmastercard.sexmod.fn$a.IDLE) break block8;
                            }
                            catch (RuntimeException runtimeException) {
                                throw fn.a(runtimeException);
                            }
                            if ((Integer)this.m.func_187225_a(U) != -1) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw fn.a(runtimeException);
                        }
                        this.m.func_187227_b(T, (Object)2);
                        if ((Integer)this.m.func_187225_a(D) != 1) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fn.a(runtimeException);
                    }
                    this.b(fp.UNDRESS);
                }
                catch (RuntimeException runtimeException) {
                    throw fn.a(runtimeException);
                }
            }
            this.func_184589_d(co.b);
        }
    }

    @Override
    public void func_70071_h_() {
        block10: {
            try {
                super.func_70071_h_();
                if (this.y() == fp.NULL) {
                    this.b();
                }
            }
            catch (RuntimeException runtimeException) {
                throw fn.a(runtimeException);
            }
            try {
                try {
                    if ((Integer)this.m.func_187225_a(T) < 2 || this.field_70173_aa % 10 != 0) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw fn.a(runtimeException);
                }
                fn.a(EnumParticleTypes.HEART, (em)this);
            }
            catch (RuntimeException runtimeException) {
                throw fn.a(runtimeException);
            }
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                this.d();
                this.i();
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void i() {
        try {
            if (this.ae() == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        try {
            if (!this.ae().equals(entityPlayerSP.getPersistentID())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        Vec3d vec3d = this.func_174791_d();
        Vec3d vec3d2 = ck.a(new Vec3d(0.0, 0.0, (double)0.65f), this.I().floatValue());
        vec3d = vec3d.func_178787_e(vec3d2);
        entityPlayerSP.func_70107_b(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        entityPlayerSP.func_70016_h(0.0, 0.0, 0.0);
    }

    void d() {
        int n2 = (Integer)this.m.func_187225_a(U);
        try {
            if (n2 == -1) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        try {
            fn.a(EnumParticleTypes.SPELL_WITCH, (em)this);
            if (n2 == 0) {
                this.a(com.trolmastercard.sexmod.c.MISC_PLOB[0]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
    }

    void c() {
        int n2 = (Integer)this.m.func_187225_a(U);
        try {
            if (n2 == -1) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        try {
            this.m.func_187227_b(U, (Object)(n2 - 1));
            if (--n2 >= 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        ay ay2 = new ay(this.field_70170_p);
        ay2.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70170_p.func_72838_d((Entity)ay2);
        this.m.func_187227_b(U, (Object)-1);
    }

    void a() {
        block21: {
            block20: {
                EntityPlayer entityPlayer;
                block19: {
                    block17: {
                        int n2 = (Integer)this.m.func_187225_a(T);
                        try {
                            if (n2 < 2) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw fn.a(runtimeException);
                        }
                        try {
                            try {
                                try {
                                    if (n2 < 4 || !this.field_70122_E) break block17;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw fn.a(runtimeException);
                                }
                                if (this.y() != fp.NULL) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw fn.a(runtimeException);
                            }
                            this.c(this.func_174791_d());
                            this.b(this.field_70177_z);
                            this.m.func_187227_b(G, (Object)true);
                            this.func_189654_d(true);
                            this.field_70145_X = true;
                            this.b(fp.STARTDOGGY);
                            return;
                        }
                        catch (RuntimeException runtimeException) {
                            throw fn.a(runtimeException);
                        }
                    }
                    entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 1.0);
                    try {
                        block18: {
                            try {
                                try {
                                    if (entityPlayer == null || !entityPlayer.field_70122_E) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw fn.a(runtimeException);
                                }
                                if (fn.d(entityPlayer) == null) break block19;
                            }
                            catch (RuntimeException runtimeException) {
                                throw fn.a(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fn.a(runtimeException);
                    }
                }
                this.c(this.func_174791_d());
                this.b(this.field_70177_z);
                this.m.func_187227_b(G, (Object)true);
                this.func_189654_d(true);
                this.field_70145_X = true;
                entityPlayer.func_189654_d(true);
                entityPlayer.field_70145_X = true;
                ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
                this.e(entityPlayer.getPersistentID());
                entityPlayer.field_70177_z = this.I().floatValue();
                Vec3d vec3d = ck.a(new Vec3d(0.0, 0.0, (double)0.65f), this.I().floatValue());
                try {
                    entityPlayer.func_70107_b(this.field_70165_t + vec3d.field_72450_a, this.field_70163_u, this.field_70161_v + vec3d.field_72449_c);
                    if (this.y() != fp.WAITDOGGY) break block20;
                    this.b(fp.DOGGYSTART);
                    break block21;
                }
                catch (RuntimeException runtimeException) {
                    throw fn.a(runtimeException);
                }
            }
            this.b(fp.SUCKBLOWJOB);
        }
    }

    void b() {
        block36: {
            block33: {
                boolean bl2;
                block35: {
                    block34: {
                        block30: {
                            float f10;
                            block32: {
                                block31: {
                                    try {
                                        try {
                                            if (!this.field_70170_p.field_72995_K) break block30;
                                            if ((double)this.N != 90.0) break block31;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw fn.a(runtimeException);
                                        }
                                        this.S = com.trolmastercard.sexmod.fn$a.JUMP_START;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw fn.a(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (this.K || !this.field_70122_E) break block32;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw fn.a(runtimeException);
                                    }
                                    this.S = com.trolmastercard.sexmod.fn$a.JUMP_END;
                                    this.N = 0;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw fn.a(runtimeException);
                                }
                            }
                            this.field_70177_z = f10 = ((Float)this.m.func_187225_a(R)).floatValue();
                            this.field_70759_as = f10;
                            this.field_70761_aq = f10;
                            break block36;
                        }
                        try {
                            if ((double)this.N == 85.0) {
                                this.m.func_187227_b(R, (Object)Float.valueOf(this.e()));
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw fn.a(runtimeException);
                        }
                        try {
                            if ((double)this.N == 100.0) {
                                this.h();
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw fn.a(runtimeException);
                        }
                        try {
                            try {
                                try {
                                    try {
                                        if (this.K || !this.field_70122_E) break block33;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw fn.a(runtimeException);
                                    }
                                    fn fn2 = this;
                                    if ((Integer)this.m.func_187225_a(U) != -1) break block34;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw fn.a(runtimeException);
                                }
                                if (!(this.func_70681_au().nextFloat() < 0.1f)) break block34;
                            }
                            catch (RuntimeException runtimeException) {
                                throw fn.a(runtimeException);
                            }
                            bl2 = true;
                            break block35;
                        }
                        catch (RuntimeException runtimeException) {
                            throw fn.a(runtimeException);
                        }
                    }
                    bl2 = false;
                }
                fn2.V = bl2;
            }
            try {
                if (!this.V || this.N != 50) break block36;
            }
            catch (RuntimeException runtimeException) {
                throw fn.a(runtimeException);
            }
            int n2 = (Integer)this.m.func_187225_a(T);
            int n3 = n2 + 1;
            try {
                this.m.func_187227_b(T, (Object)n3);
                if (n3 == 1) {
                    this.b(fp.UNDRESS);
                }
            }
            catch (RuntimeException runtimeException) {
                throw fn.a(runtimeException);
            }
        }
        try {
            if (this.field_70122_E) {
                ++this.N;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        this.K = this.field_70122_E;
    }

    void h() {
        float f10;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.func_70664_aZ();
        this.field_70177_z = f10 = ((Float)this.m.func_187225_a(R)).floatValue();
        this.field_70126_B = f10;
        Vec3d vec3d = new Vec3d(0.0, 0.0, (double)0.7f);
        vec3d = ck.a(vec3d, f10);
        this.field_70159_w = vec3d.field_72450_a;
        this.field_70179_y = vec3d.field_72449_c;
        this.N = 0;
    }

    float e() {
        int n2 = (Integer)this.m.func_187225_a(T);
        try {
            if ((Integer)this.m.func_187225_a(U) != -1) {
                return this.f();
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        try {
            if (n2 < 2) {
                return this.f();
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 30.0);
        try {
            if (entityPlayer == null) {
                return this.f();
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        try {
            if (fn.d(entityPlayer) != null) {
                return this.f();
            }
        }
        catch (RuntimeException runtimeException) {
            throw fn.a(runtimeException);
        }
        return (float)Math.atan2(this.field_70161_v - entityPlayer.field_70161_v, this.field_70165_t - entityPlayer.field_70165_t) * 57.29578f + 90.0f;
    }

    float f() {
        return com.trolmastercard.sexmod.r.f.nextFloat() * 360.0f;
    }

    public void func_180430_e(float f10, float f11) {
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 7[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 22[SWITCH]
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        };
        this.C.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.C);
        animationData.addAnimationController(this.s);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    static enum a {
        IDLE("animation.slime.idle"),
        JUMP_START("animation.slime.jumpstart"),
        JUMP_AIR("animation.slime.jumpair"),
        JUMP_END("animation.slime.jumpend");

        String a;

        public String a() {
            return this.a;
        }

        private a(String string2) {
            this.a = string2;
        }
    }
}

