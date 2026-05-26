/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.cz;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
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
public class ev
extends em {
    public static final int Q = 300;
    public static final int K = 8;
    public static final Vec3d O = new Vec3d(0.5, 1.0, 0.0);
    float U = 1.0f;
    public boolean P = false;
    public static final DataParameter<ItemStack> N = EntityDataManager.func_187226_a(ev.class, (DataSerializer)DataSerializers.field_187196_f).func_187156_b().func_187161_a(111);
    boolean S = true;
    int T = 1;
    int L = 1;
    boolean M = false;
    boolean R = false;

    public ev(World world) {
        super(world);
        this.func_70105_a((float)ev.O.field_72450_a, (float)ev.O.field_72448_b);
    }

    public ev(World world, ItemStack itemStack) {
        this(world);
        this.m.func_187227_b(N, (Object)itemStack);
    }

    @Override
    public String c() {
        return "Allie";
    }

    @Override
    public float i() {
        return 1.0f;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.m.func_187214_a(N, (Object)ItemStack.field_190927_a);
    }

    public boolean f() {
        boolean bl2;
        NBTTagCompound nBTTagCompound = ((ItemStack)this.m.func_187225_a(N)).func_77978_p();
        try {
            if (nBTTagCompound == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        try {
            bl2 = nBTTagCompound.func_74762_e("sexmodUses") == 1;
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        return bl2;
    }

    @Override
    public void func_70619_bc() {
        try {
            super.func_70619_bc();
            if (this.y() == fp.NULL) {
                this.field_70170_p.func_72900_e((Entity)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                this.field_70170_p.func_72900_e((Entity)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ac() {
        try {
            if (!this.R) {
                this.P = true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
    }

    @Override
    public void func_70071_h_() {
        block17: {
            block18: {
                try {
                    try {
                        try {
                            try {
                                super.func_70071_h_();
                                if (this.U == 1.0f || this.U == -69.0f) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ev.a(runtimeException);
                            }
                            if (!(this.U <= 0.0f)) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ev.a(runtimeException);
                        }
                        if (!this.n()) break block18;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ev.a(runtimeException);
                    }
                    ge.b.sendToServer((IMessage)new cz(this.f()));
                    d3.a(true);
                }
                catch (RuntimeException runtimeException) {
                    throw ev.a(runtimeException);
                }
            }
            this.U = -69.0f;
        }
        try {
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        try {
            if (this.P) {
                this.c();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        try {
            if (this.S) {
                this.d();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        this.b();
    }

    void b() {
        try {
            if (this.field_70173_aa % 10 != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        int n2 = this.func_70681_au().nextInt(8);
        Vec3d vec3d = this.b("tail" + n2).func_178787_e(this.func_174791_d());
        this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, this.func_70681_au().nextGaussian() * (double)0.01f, this.func_70681_au().nextGaussian() * (double)0.01f, this.func_70681_au().nextGaussian() * (double)0.01f, new int[0]);
    }

    @SideOnly(value=Side.CLIENT)
    void d() {
        this.S = false;
        cj.a(this.field_70170_p, EnumParticleTypes.PORTAL, this.func_174791_d(), 300, 0.75, 1.5);
    }

    @SideOnly(value=Side.CLIENT)
    void c() {
        this.b((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        this.P = false;
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        this.R = false;
        String[] stringArray = new String[]{"action.names.makemerichallie", "action.names.deepthroat", "Reverse cowgirl"};
        ev.a(entityPlayer, this, stringArray, false);
        return true;
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.DEEPTHROAT_SLOW) {
                return fp.DEEPTHROAT_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        try {
            if (fp2 == fp.REVERSE_COWGIRL_SLOW) {
                return fp.REVERSE_COWGIRL_FAST_START;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block12: {
            block10: {
                try {
                    try {
                        if (fp2 != fp.DEEPTHROAT_FAST && fp2 != fp.DEEPTHROAT_SLOW) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ev.a(runtimeException);
                    }
                    return fp.DEEPTHROAT_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw ev.a(runtimeException);
                }
            }
            try {
                block11: {
                    try {
                        try {
                            if (fp2 == fp.REVERSE_COWGIRL_SLOW || fp2 == fp.REVERSE_COWGIRL_FAST_START) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ev.a(runtimeException);
                        }
                        if (fp2 != fp.REVERSE_COWGIRL_FAST_CONTINUES) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ev.a(runtimeException);
                    }
                }
                return fp.REVERSE_COWGIRL_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw ev.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void b(fp fp2) {
        block22: {
            block20: {
                block18: {
                    try {
                        block19: {
                            try {
                                try {
                                    if (this.y() != fp.DEEPTHROAT_CUM) break block18;
                                    if (fp2 == fp.DEEPTHROAT_FAST) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ev.a(runtimeException);
                                }
                                if (fp2 != fp.DEEPTHROAT_SLOW) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ev.a(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ev.a(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                try {
                                    if (this.y() != fp.REVERSE_COWGIRL_CUM) break block20;
                                    if (fp2 == fp.REVERSE_COWGIRL_SLOW) break block21;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ev.a(runtimeException);
                                }
                                if (fp2 == fp.REVERSE_COWGIRL_FAST_START) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ev.a(runtimeException);
                            }
                            if (fp2 != fp.REVERSE_COWGIRL_FAST_CONTINUES) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ev.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ev.a(runtimeException);
                }
            }
            try {
                try {
                    if (this.field_70170_p.field_72995_K || fp2 != fp.REVERSE_COWGIRL_START) break block22;
                }
                catch (RuntimeException runtimeException) {
                    throw ev.a(runtimeException);
                }
                this.a();
            }
            catch (RuntimeException runtimeException) {
                throw ev.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    void a() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        Vec3d vec3d = this.o();
        entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 6[SWITCH]
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
    @SideOnly(value=Side.CLIENT)
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.C == null) {
                this.p();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 26[SWITCH]
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
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
    }

    @Override
    public void a(String string, UUID uUID) {
        fp fp2;
        ev ev2;
        String string2;
        String string3;
        ev ev3;
        block8: {
            fp fp3;
            ev ev4;
            block10: {
                block9: {
                    try {
                        try {
                            this.R = true;
                            if (!"action.names.makemerichallie".equals(string)) break block8;
                            ev4 = this;
                            if (!this.f()) break block9;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ev.a(runtimeException);
                        }
                        fp3 = fp.RICH_FIRST_TIME;
                        break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ev.a(runtimeException);
                    }
                }
                fp3 = fp.RICH_NORMAL;
            }
            ev4.b(fp3);
            return;
        }
        try {
            ev3 = this;
            string3 = "animationFollowUp";
            string2 = "action.names.deepthroat".equals(string) ? "deepthroat" : "reverse_cowgirl";
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        try {
            ev3.a(string3, string2);
            ev2 = this;
            fp2 = this.f() ? fp.ALLIE_PREPARE_FIRST_TIME : fp.ALLIE_PREPARE_NORMAL;
        }
        catch (RuntimeException runtimeException) {
            throw ev.a(runtimeException);
        }
        ev2.b(fp2);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

