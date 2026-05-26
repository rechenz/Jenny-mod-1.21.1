/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.f1;
import com.trolmastercard.sexmod.fp;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class e5
extends ei {
    static final double au = 4.0;
    static final double at = 4.0;
    public float aq = 0.0f;
    EntityPlayer as = null;
    boolean ap = false;
    int ar = 1;
    int av = 1;

    protected e5(World world) {
        super(world);
    }

    public e5(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float i() {
        return 1.9f + this.aq;
    }

    public float func_70047_e() {
        return 1.63f;
    }

    @Override
    public boolean v() {
        return false;
    }

    @Override
    public at a(int n2) {
        return new f1();
    }

    @Override
    public String c(int n2) {
        return "textures/entity/allie/hand.png";
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("action.names.deepthroat".equals(string)) {
                this.b(fp.DEEPTHROAT_START);
                this.a(this.ah(), fp.DEEPTHROAT_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw e5.a(runtimeException);
        }
        try {
            if ("Reverse cowgirl".equals(string)) {
                this.b(fp.REVERSE_COWGIRL_START);
                this.a(0, fp.REVERSE_COWGIRL_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw e5.a(runtimeException);
        }
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        e5.a(entityPlayer, this, new String[]{"action.names.deepthroat", "Reverse cowgirl"}, false);
        return true;
    }

    @Override
    public void b(fp fp2) {
        block16: {
            block14: {
                try {
                    block15: {
                        try {
                            try {
                                if (this.y() != fp.DEEPTHROAT_CUM) break block14;
                                if (fp2 == fp.DEEPTHROAT_FAST) break block15;
                            }
                            catch (RuntimeException runtimeException) {
                                throw e5.a(runtimeException);
                            }
                            if (fp2 != fp.DEEPTHROAT_SLOW) break block14;
                        }
                        catch (RuntimeException runtimeException) {
                            throw e5.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw e5.a(runtimeException);
                }
            }
            try {
                block17: {
                    try {
                        try {
                            try {
                                if (this.y() != fp.REVERSE_COWGIRL_CUM) break block16;
                                if (fp2 == fp.REVERSE_COWGIRL_SLOW) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw e5.a(runtimeException);
                            }
                            if (fp2 == fp.REVERSE_COWGIRL_FAST_START) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw e5.a(runtimeException);
                        }
                        if (fp2 != fp.REVERSE_COWGIRL_FAST_CONTINUES) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw e5.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw e5.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    @Override
    public boolean F() {
        try {
            switch (this.y()) {
                case ALLIE_PREPARE_NORMAL: 
                case DEEPTHROAT_START: 
                case DEEPTHROAT_CUM: 
                case DEEPTHROAT_FAST: 
                case ALLIE_PREPARE_FIRST_TIME: 
                case DEEPTHROAT_SLOW: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw e5.a(runtimeException);
        }
    }

    @Override
    public void func_70619_bc() {
        EntityPlayer entityPlayer;
        block7: {
            try {
                super.func_70619_bc();
                if (this.m() == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw e5.a(runtimeException);
            }
            entityPlayer = this.field_70170_p.func_152378_a(this.m());
            try {
                try {
                    if (entityPlayer == null || this.as != null) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw e5.a(runtimeException);
                }
                this.c(true);
            }
            catch (RuntimeException runtimeException) {
                throw e5.a(runtimeException);
            }
        }
        this.as = entityPlayer;
    }

    @Override
    public void func_70071_h_() {
        try {
            super.func_70071_h_();
            if (this.field_70170_p.field_72995_K) {
                this.a();
            }
        }
        catch (RuntimeException runtimeException) {
            throw e5.a(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void a() {
        try {
            if (this.field_70173_aa % 10 != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw e5.a(runtimeException);
        }
        int n2 = this.func_70681_au().nextInt(8);
        Vec3d vec3d = this.b("tail" + n2).func_178787_e(this.func_174791_d());
        this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, this.func_70681_au().nextGaussian() * (double)0.01f, this.func_70681_au().nextGaussian() * (double)0.01f, this.func_70681_au().nextGaussian() * (double)0.01f, new int[0]);
    }

    @Override
    public void B() {
        this.c(true);
    }

    @Override
    public void y() {
        this.c(false);
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.DEEPTHROAT_SLOW) {
                return fp.DEEPTHROAT_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw e5.a(runtimeException);
        }
        try {
            if (fp2 == fp.REVERSE_COWGIRL_SLOW) {
                return fp.REVERSE_COWGIRL_FAST_START;
            }
        }
        catch (RuntimeException runtimeException) {
            throw e5.a(runtimeException);
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
                        throw e5.a(runtimeException);
                    }
                    return fp.DEEPTHROAT_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw e5.a(runtimeException);
                }
            }
            try {
                block11: {
                    try {
                        try {
                            if (fp2 == fp.REVERSE_COWGIRL_SLOW || fp2 == fp.REVERSE_COWGIRL_FAST_START) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw e5.a(runtimeException);
                        }
                        if (fp2 != fp.REVERSE_COWGIRL_FAST_CONTINUES) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw e5.a(runtimeException);
                    }
                }
                return fp.REVERSE_COWGIRL_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw e5.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.C == null) {
                this.p();
            }
        }
        catch (RuntimeException runtimeException) {
            throw e5.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 20[SWITCH]
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

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

