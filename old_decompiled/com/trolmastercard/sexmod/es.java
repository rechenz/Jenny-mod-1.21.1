/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a0;
import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class es
extends ei {
    boolean ap = false;
    boolean ar = false;
    int aq = 0;
    boolean as = false;

    protected es(World world) {
        super(world);
    }

    public es(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float i() {
        return 1.75f;
    }

    @Override
    public float T() {
        return 35.0f;
    }

    @Override
    public float ai() {
        return 140.0f;
    }

    public float func_70047_e() {
        return 1.64f;
    }

    @Override
    public void u() {
        this.b(fp.STARTDOGGY);
        this.m.func_187227_b(em.D, (Object)0);
        this.r = ((Float)this.m.func_187225_a(em.w)).floatValue();
    }

    @Override
    public boolean A() {
        return false;
    }

    @Override
    public at a(int n2) {
        return new a0();
    }

    @Override
    public String c(int n2) {
        try {
            if (n2 == 0) {
                return "textures/entity/jenny/hand_nude.png";
            }
        }
        catch (RuntimeException runtimeException) {
            throw es.a(runtimeException);
        }
        return "textures/entity/jenny/hand.png";
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("action.names.boobjob".equals(string)) {
                this.m.func_187227_b(em.D, (Object)0);
                this.b(fp.PAIZURI_START);
                this.a(0, fp.PAIZURI_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw es.a(runtimeException);
        }
        try {
            if ("action.names.blowjob".equals(string)) {
                this.b(fp.STARTBLOWJOB);
                this.a(this.ah(), fp.PAIZURI_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw es.a(runtimeException);
        }
    }

    @Override
    public void func_70619_bc() {
        block7: {
            super.func_70619_bc();
            if (this.y() == fp.WAITDOGGY) {
                EntityPlayer entityPlayer;
                block8: {
                    entityPlayer = this.j();
                    try {
                        try {
                            try {
                                if (entityPlayer == null || !(entityPlayer.func_70011_f(this.w().field_72450_a, this.w().field_72448_b, this.w().field_72449_c) < 1.0)) break block7;
                            }
                            catch (RuntimeException runtimeException) {
                                throw es.a(runtimeException);
                            }
                            if (!this.c(entityPlayer.getPersistentID())) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw es.a(runtimeException);
                        }
                        entityPlayer.func_145747_a((ITextComponent)new TextComponentString(TextFormatting.DARK_PURPLE + "sowy no lesbo action yet uwu"));
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw es.a(runtimeException);
                    }
                }
                this.e(entityPlayer.getPersistentID());
                entityPlayer.func_70634_a(this.func_174791_d().field_72450_a, this.w().field_72448_b, this.func_174791_d().field_72449_c);
                this.a((EntityPlayerMP)entityPlayer, false);
                entityPlayer.func_191958_b(0.0f, 0.0f, 0.0f, 0.0f);
                entityPlayer.field_71075_bZ.field_75100_b = true;
                this.field_70170_p.func_152378_a((UUID)this.m()).field_71075_bZ.field_75100_b = true;
                this.a(0.0, 0.0, 0.4, 0.0f, 60.0f);
                this.B = null;
                this.b(fp.DOGGYSTART);
                ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
            }
        }
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        em.a(entityPlayer, this, new String[]{"action.names.blowjob", "action.names.boobjob"}, false);
        return true;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected fp c(fp var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[SWITCH]
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
    protected fp a(fp fp2) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (fp2 != fp.SUCKBLOWJOB && fp2 != fp.THRUSTBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw es.a(runtimeException);
                        }
                        this.a(0.0, 0.0, 0.0, 0.0f, 70.0f);
                        return fp.CUMBLOWJOB;
                    }
                    catch (RuntimeException runtimeException) {
                        throw es.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (fp2 != fp.DOGGYSLOW && fp2 != fp.DOGGYFAST) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw es.a(runtimeException);
                    }
                    return fp.DOGGYCUM;
                }
                catch (RuntimeException runtimeException) {
                    throw es.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.PAIZURI_FAST && fp2 != fp.PAIZURI_SLOW) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw es.a(runtimeException);
                }
                return fp.PAIZURI_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw es.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void b(fp fp2) {
        block22: {
            fp fp3;
            block20: {
                block18: {
                    fp3 = this.y();
                    try {
                        block19: {
                            try {
                                try {
                                    if (fp3 != fp.DOGGYCUM) break block18;
                                    if (fp2 == fp.DOGGYSLOW) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw es.a(runtimeException);
                                }
                                if (fp2 != fp.DOGGYFAST) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw es.a(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw es.a(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                if (fp3 != fp.CUMBLOWJOB) break block20;
                                if (fp2 == fp.THRUSTBLOWJOB) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw es.a(runtimeException);
                            }
                            if (fp2 != fp.SUCKBLOWJOB) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw es.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw es.a(runtimeException);
                }
            }
            try {
                block23: {
                    try {
                        try {
                            if (fp3 != fp.PAIZURI_CUM) break block22;
                            if (fp2 == fp.PAIZURI_SLOW) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw es.a(runtimeException);
                        }
                        if (fp2 != fp.PAIZURI_FAST) break block22;
                    }
                    catch (RuntimeException runtimeException) {
                        throw es.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw es.a(runtimeException);
            }
        }
        super.b(fp2);
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
            throw es.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 37[SWITCH]
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

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

