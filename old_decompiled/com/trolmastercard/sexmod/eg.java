/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.ds;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fh;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.go;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class eg
extends ei {
    int ar = -1;
    boolean ap = false;
    int aq = 1;

    public eg(World world) {
        super(world);
    }

    public eg(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float i() {
        return 1.5f;
    }

    public float func_70047_e() {
        return 1.5f;
    }

    @Override
    public void u() {
    }

    @Override
    public boolean a(String string) {
        try {
            if ("anal".equals(string)) {
                this.b(fp.ANAL_PREPARE);
                this.f(0);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eg.a(runtimeException);
        }
        try {
            if ("doggy".equals(string)) {
                this.b(fp.SITDOWN);
                this.f(0);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eg.a(runtimeException);
        }
        return false;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void H() {
        em.a((EntityPlayer)Minecraft.func_71410_x().field_71439_g, this, new String[]{"anal", "doggy"}, false);
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("action.names.headpat".equals(string)) {
                this.b(uUID);
                this.b(fp.HEAD_PAT);
                this.a(this.ah(), fp.HEAD_PAT);
            }
        }
        catch (RuntimeException runtimeException) {
            throw eg.a(runtimeException);
        }
    }

    @Override
    public at a(int n2) {
        return new go();
    }

    @Override
    public String c(int n2) {
        return "textures/entity/bia/hand.png";
    }

    @Override
    public float T() {
        return 35.0f;
    }

    @Override
    public float ai() {
        return 140.0f;
    }

    @Override
    public boolean A() {
        return false;
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        em.a(entityPlayer, this, new String[]{"action.names.headpat"}, false);
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
                                if (this.y() != fp.ANAL_CUM) break block12;
                                if (fp2 == fp.ANAL_FAST) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw eg.a(runtimeException);
                            }
                            if (fp2 != fp.ANAL_SLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eg.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw eg.a(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.y() != fp.PRONE_DOGGY_CUM) break block14;
                            if (fp2 == fp.PRONE_DOGGY_HARD) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eg.a(runtimeException);
                        }
                        if (fp2 != fp.PRONE_DOGGY_SOFT) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eg.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw eg.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.ANAL_SLOW) {
                return fp.ANAL_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eg.a(runtimeException);
        }
        try {
            if (fp2 == fp.PRONE_DOGGY_INTRO) {
                return fp.PRONE_DOGGY_INSERT;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eg.a(runtimeException);
        }
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block9: {
            block8: {
                try {
                    try {
                        if (fp2 != fp.ANAL_SLOW && fp2 != fp.ANAL_FAST) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eg.a(runtimeException);
                    }
                    return fp.ANAL_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw eg.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.PRONE_DOGGY_SOFT && fp2 != fp.PRONE_DOGGY_HARD) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw eg.a(runtimeException);
                }
                return fp.PRONE_DOGGY_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw eg.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        this.a();
    }

    @Override
    protected void V() {
        super.V();
        this.ar = -1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean a(EntityPlayer entityPlayer) {
        return Minecraft.func_71410_x().field_71439_g.getPersistentID().equals(entityPlayer.getPersistentID());
    }

    void a() {
        float f10;
        EntityPlayer entityPlayer;
        block35: {
            block37: {
                block36: {
                    fp fp2;
                    block32: {
                        block34: {
                            block33: {
                                block31: {
                                    block30: {
                                        fp2 = this.y();
                                        try {
                                            try {
                                                if (fp2 == fp.ANAL_WAIT || fp2 == fp.SITDOWNIDLE) break block30;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw eg.a(runtimeException);
                                            }
                                            return;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw eg.a(runtimeException);
                                        }
                                    }
                                    entityPlayer = this.j();
                                    try {
                                        if (entityPlayer == null) {
                                            return;
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw eg.a(runtimeException);
                                    }
                                    try {
                                        if (entityPlayer.func_70032_d((Entity)this) > 1.0f) {
                                            return;
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw eg.a(runtimeException);
                                    }
                                    try {
                                        try {
                                            if (!this.field_70170_p.field_72995_K || this.a(entityPlayer)) break block31;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw eg.a(runtimeException);
                                        }
                                        return;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw eg.a(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (this.ar != -1) break block32;
                                        if (!this.field_70170_p.field_72995_K) break block33;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw eg.a(runtimeException);
                                    }
                                    fh.b();
                                    d3.a(false);
                                    break block34;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw eg.a(runtimeException);
                                }
                            }
                            this.e(entityPlayer.getPersistentID());
                        }
                        this.ar = em.j;
                        return;
                    }
                    try {
                        if (--this.ar > 0) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw eg.a(runtimeException);
                    }
                    try {
                        this.ar = -1;
                        entityPlayer.field_70145_X = true;
                        entityPlayer.func_189654_d(true);
                        if (fp2 != fp.ANAL_WAIT) break block35;
                        if (this.field_70170_p.field_72995_K) break block36;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eg.a(runtimeException);
                    }
                    this.b(fp.ANAL_START);
                    Vec3d vec3d = this.o().func_178787_e(ck.a(-0.3, -1.0, -0.5, this.I().floatValue()));
                    entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
                    break block37;
                }
                try {
                    if (this.n()) {
                        ds.d();
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw eg.a(runtimeException);
                }
            }
            return;
        }
        entityPlayer.field_70177_z = f10 = this.I().floatValue();
        entityPlayer.field_70125_A = 60.0f;
        if (!this.field_70170_p.field_72995_K) {
            this.f(0);
            this.b(fp.PRONE_DOGGY_INTRO);
            Vec3d vec3d = this.o();
            Vec3d vec3d2 = vec3d.func_178787_e(ck.a(0.0, 0.0, 1.0, f10));
            this.c(vec3d2);
            EntityPlayer entityPlayer2 = this.k();
            try {
                if (entityPlayer2 != null) {
                    entityPlayer2.func_70634_a(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c);
                }
            }
            catch (RuntimeException runtimeException) {
                throw eg.a(runtimeException);
            }
            Vec3d vec3d3 = vec3d.func_178787_e(ck.a(0.0, 1.1875 - (double)entityPlayer.func_70047_e(), 0.5, f10));
            entityPlayer.func_70634_a(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c);
            this.a(true);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ag() {
        try {
            super.ag();
            if (this.y() != fp.PRONE_DOGGY_HARD) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eg.a(runtimeException);
        }
        int n2 = this.aq;
        try {
            do {
                this.aq = this.func_70681_au().nextInt(3) + 1;
            } while (n2 == this.aq);
            return;
        }
        catch (RuntimeException runtimeException) {
            throw eg.a(runtimeException);
        }
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 16[SWITCH]
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
            throw eg.a(runtimeException);
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
        this.C.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
        animationData.addAnimationController(this.C);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

