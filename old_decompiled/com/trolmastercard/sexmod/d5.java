/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector3f
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.item.ItemStack
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.dm;
import java.util.HashSet;
import javax.vecmath.Vector3f;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d5
extends dm {
    Vector3f A = new Vector3f(0.0f, 0.0f, 0.0f);
    Vector3f D = new Vector3f(0.0f, 0.0f, 0.0f);
    Vector3f F = new Vector3f(0.0f, 0.0f, 0.0f);
    Vector3f E = new Vector3f(0.0f, 0.0f, 0.0f);
    Vector3f z = new Vector3f(0.0f, 0.0f, 0.0f);
    Vector3f B = new Vector3f(0.0f, 0.0f, 0.0f);
    Vector3f C = new Vector3f(0.0f, 0.0f, 0.0f);

    public d5(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    @Override
    protected void c() {
        GlStateManager.func_179109_b((float)0.0f, (float)-1.25f, (float)0.0f);
        GlStateManager.func_179152_a((float)0.8f, (float)0.8f, (float)0.8f);
    }

    @Override
    protected void a(String string, GeoBone geoBone) {
        try {
            if ("slime".equals(string)) {
                this.F = new Vector3f(geoBone.getRotationX(), geoBone.getRotationY(), geoBone.getRotationZ());
                this.A = new Vector3f(geoBone.getScaleX(), geoBone.getScaleY(), geoBone.getScaleZ());
                this.D = new Vector3f(geoBone.getPositionX(), geoBone.getPositionY(), geoBone.getPositionZ());
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
        try {
            if ("upperBody".equals(string)) {
                this.B = new Vector3f(geoBone.getRotationX(), geoBone.getRotationY(), geoBone.getRotationZ());
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
        try {
            if ("torso".equals(string)) {
                this.E = new Vector3f(geoBone.getRotationX(), geoBone.getRotationY(), geoBone.getRotationZ());
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
        try {
            if ("head".equals(string)) {
                this.C = new Vector3f(geoBone.getRotationX(), geoBone.getRotationY(), geoBone.getRotationZ());
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
        try {
            if ("boobs".equals(string)) {
                this.z = new Vector3f(geoBone.getRotationX(), geoBone.getRotationY(), geoBone.getRotationZ());
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
        try {
            if ("figure".equals(string)) {
                geoBone.setRotationX(this.F.x);
                geoBone.setRotationY(this.F.y);
                geoBone.setRotationZ(this.F.z);
                geoBone.setScaleX(this.A.x);
                geoBone.setScaleY(this.A.y);
                geoBone.setScaleZ(this.A.z);
                geoBone.setPositionX(this.D.x);
                geoBone.setPositionY(this.D.y);
                geoBone.setPositionZ(this.D.z);
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
        try {
            if ("dress".equals(string)) {
                geoBone.setRotationX(this.B.x);
                geoBone.setRotationY(this.B.y);
                geoBone.setRotationZ(this.B.z);
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
        try {
            if ("hat".equals(string)) {
                geoBone.setRotationX(this.C.x);
                geoBone.setRotationY(this.C.y);
                geoBone.setRotationZ(this.C.z);
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
        try {
            if ("boobsSlime".equals(string)) {
                geoBone.setRotationX(this.z.x);
                geoBone.setRotationY(this.z.y);
                geoBone.setRotationZ(this.z.z);
            }
        }
        catch (RuntimeException runtimeException) {
            throw d5.a(runtimeException);
        }
    }

    @Override
    protected void a(boolean bl2) {
        block3: {
            block2: {
                try {
                    super.a(bl2);
                    if (!bl2) break block2;
                    GlStateManager.func_179109_b((float)0.15f, (float)0.0f, (float)0.0f);
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw d5.a(runtimeException);
                }
            }
            GlStateManager.func_179137_b((double)-0.02, (double)0.0, (double)0.0);
            GlStateManager.func_179114_b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    @Override
    public HashSet<String> a() {
        HashSet hashSet = super.a();
        hashSet.add("figure");
        return hashSet;
    }

    @Override
    protected void a(boolean bl2, boolean bl3) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            super.a(bl2, bl3);
                            if (!bl2 || bl3) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw d5.a(runtimeException);
                        }
                        GlStateManager.func_179137_b((double)-0.025, (double)-0.025, (double)0.0);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw d5.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (bl2 || !bl3) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw d5.a(runtimeException);
                    }
                    GlStateManager.func_179114_b((float)120.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw d5.a(runtimeException);
                }
            }
            try {
                try {
                    if (bl2 || bl3) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw d5.a(runtimeException);
                }
                GlStateManager.func_179137_b((double)0.0, (double)0.4, (double)-0.1);
                GlStateManager.func_179114_b((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            }
            catch (RuntimeException runtimeException) {
                throw d5.a(runtimeException);
            }
        }
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void a(boolean var1_1, ItemStack var2_2) {
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

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

