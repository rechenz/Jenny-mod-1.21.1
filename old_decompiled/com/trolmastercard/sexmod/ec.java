/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.cq;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class ec
extends ei {
    boolean ap = false;
    int aq = 0;

    protected ec(World world) {
        super(world);
    }

    public ec(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float i() {
        return 1.6f;
    }

    public float func_70047_e() {
        return 1.64f;
    }

    @Override
    public boolean v() {
        return false;
    }

    @Override
    public boolean A() {
        return false;
    }

    @Override
    public at a(int n2) {
        return new cq();
    }

    @Override
    public String c(int n2) {
        return "textures/entity/slime/hand.png";
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("action.names.blowjob".equals(string)) {
                this.a(0, fp.SUCKBLOWJOB);
                this.b(fp.SUCKBLOWJOB);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ec.a(runtimeException);
        }
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        ec.a(entityPlayer, this, new String[]{"action.names.blowjob"}, false);
        return true;
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
                                throw ec.a(runtimeException);
                            }
                            if (fp2 != fp.SUCKBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ec.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ec.a(runtimeException);
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
                            throw ec.a(runtimeException);
                        }
                        if (fp2 != fp.DOGGYSLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ec.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ec.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.SUCKBLOWJOB) {
                return fp.THRUSTBLOWJOB;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ec.a(runtimeException);
        }
        try {
            if (fp2 == fp.DOGGYSLOW) {
                return fp.DOGGYFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ec.a(runtimeException);
        }
        return null;
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
                        throw ec.a(runtimeException);
                    }
                    return fp.CUMBLOWJOB;
                }
                catch (RuntimeException runtimeException) {
                    throw ec.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.DOGGYSLOW && fp2 != fp.DOGGYFAST) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw ec.a(runtimeException);
                }
                return fp.DOGGYCUM;
            }
            catch (RuntimeException runtimeException) {
                throw ec.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void func_70619_bc() {
        try {
            super.func_70619_bc();
            if (this.y() != fp.WAITDOGGY) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ec.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.j();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ec.a(runtimeException);
        }
        try {
            if (entityPlayer.func_174791_d().func_72438_d(this.w()) > 1.0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ec.a(runtimeException);
        }
        ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
        this.e(entityPlayer.getPersistentID());
        entityPlayer.field_70177_z = this.I().floatValue();
        this.r = this.I().floatValue();
        entityPlayer.func_70107_b(this.w().field_72450_a, this.w().field_72448_b, this.w().field_72449_c);
        entityPlayer.func_191958_b(0.0f, 0.0f, 0.0f, 0.0f);
        this.a(0.0, 0.0, 0.4, 0.0f, 60.0f);
        this.b(fp.DOGGYSTART);
        entityPlayer.func_189654_d(true);
        entityPlayer.field_70145_X = true;
        EntityPlayer entityPlayer2 = this.field_70170_p.func_152378_a(this.m());
        entityPlayer2.func_189654_d(true);
        entityPlayer.field_70145_X = true;
        entityPlayer.field_71075_bZ.field_75100_b = true;
        entityPlayer2.field_71075_bZ.field_75100_b = true;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 17[SWITCH]
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
        try {
            if (this.C == null) {
                this.p();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ec.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 25[SWITCH]
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
        animationData.addAnimationController(this.E);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

