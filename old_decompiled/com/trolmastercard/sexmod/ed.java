/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.bf;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.fh;
import com.trolmastercard.sexmod.fp;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class ed
extends ei {
    int ar = 0;
    boolean aq = false;
    boolean ap = false;
    boolean as = false;

    protected ed(World world) {
        super(world);
    }

    public ed(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float i() {
        return 1.6f;
    }

    public float func_70047_e() {
        return 1.34f;
    }

    @Override
    public at a(int n2) {
        return new bf();
    }

    @Override
    public String c(int n2) {
        return "textures/entity/cat/hand.png";
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("action.names.touchboobs".equals(string)) {
                this.a(0, fp.TOUCH_BOOBS_INTRO);
                this.b(fp.TOUCH_BOOBS_INTRO);
                this.m.func_187227_b(D, (Object)0);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ed.a(runtimeException);
        }
        try {
            if ("action.names.headpat".equals(string)) {
                this.b(fp.HEAD_PAT);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ed.a(runtimeException);
        }
    }

    @Override
    public void u() {
        this.b(fp.WAIT_CAT);
    }

    @Override
    public boolean v() {
        return true;
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        ed.a(entityPlayer, this, new String[]{"action.names.touchboobs", "action.names.headpat"}, false);
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
                                if (this.y() != fp.COWGIRL_SITTING_CUM) break block12;
                                if (fp2 == fp.COWGIRL_SITTING_SLOW) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ed.a(runtimeException);
                            }
                            if (fp2 != fp.COWGIRL_SITTING_FAST) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ed.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ed.a(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.y() != fp.TOUCH_BOOBS_CUM) break block14;
                            if (fp2 == fp.TOUCH_BOOBS_FAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ed.a(runtimeException);
                        }
                        if (fp2 != fp.TOUCH_BOOBS_SLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ed.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ed.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    @Override
    public void func_70071_h_() {
        block3: {
            block2: {
                try {
                    super.func_70071_h_();
                    if (!fp.WAIT_CAT.equals((Object)this.y())) break block2;
                    this.a();
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw ed.a(runtimeException);
                }
            }
            this.ar = 0;
        }
    }

    void a() {
        block12: {
            EntityPlayer entityPlayer;
            block11: {
                entityPlayer = this.j();
                try {
                    if (entityPlayer == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ed.a(runtimeException);
                }
                try {
                    if (entityPlayer.func_70011_f(this.field_70165_t, this.w().field_72448_b, this.field_70161_v) > 1.25) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ed.a(runtimeException);
                }
                try {
                    if (!this.field_70170_p.field_72995_K) break block11;
                    this.a(entityPlayer, this.ar);
                    break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw ed.a(runtimeException);
                }
            }
            try {
                if (this.ar == 25) {
                    this.e(entityPlayer.getPersistentID());
                    entityPlayer.func_191958_b(0.0f, 0.0f, 0.0f, 0.0f);
                    entityPlayer.func_70634_a(this.func_174791_d().field_72450_a, this.w().field_72448_b, this.func_174791_d().field_72449_c);
                    this.b(fp.COWGIRL_SITTING_INTRO);
                    entityPlayer.func_70034_d(this.I().floatValue() + 180.0f);
                    entityPlayer.field_70177_z = this.I().floatValue() + 180.0f;
                    entityPlayer.field_70126_B = this.I().floatValue() + 180.0f;
                    this.r = this.I().floatValue() + 180.0f;
                    this.a(0.0, -0.075f, -0.7109375, 0.0f, 0.0f);
                    this.m.func_187227_b(D, (Object)0);
                }
            }
            catch (RuntimeException runtimeException) {
                throw ed.a(runtimeException);
            }
        }
        ++this.ar;
    }

    @SideOnly(value=Side.CLIENT)
    void a(EntityPlayer entityPlayer, int n2) {
        EntityPlayerSP entityPlayerSP;
        if (n2 == 0) {
            entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    fh.b();
                    entityPlayerSP.func_70016_h(0.0, 0.0, 0.0);
                    d3.a(false);
                }
            }
            catch (RuntimeException runtimeException) {
                throw ed.a(runtimeException);
            }
        }
        if (n2 == 25) {
            entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    Minecraft.func_71410_x().field_71474_y.field_74320_O = 2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ed.a(runtimeException);
            }
        }
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.TOUCH_BOOBS_SLOW) {
                return fp.TOUCH_BOOBS_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ed.a(runtimeException);
        }
        try {
            if (fp2 == fp.COWGIRL_SITTING_SLOW) {
                return fp.COWGIRL_SITTING_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ed.a(runtimeException);
        }
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block9: {
            block8: {
                try {
                    try {
                        if (fp2 != fp.TOUCH_BOOBS_SLOW && fp2 != fp.TOUCH_BOOBS_FAST) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ed.a(runtimeException);
                    }
                    return fp.TOUCH_BOOBS_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw ed.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.COWGIRL_SITTING_FAST && fp2 != fp.COWGIRL_SITTING_SLOW) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw ed.a(runtimeException);
                }
                return fp.COWGIRL_SITTING_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw ed.a(runtimeException);
            }
        }
        return null;
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
            throw ed.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 23[SWITCH]
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
        this.E.transitionLengthTicks = 10.0;
        this.C.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.C);
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

