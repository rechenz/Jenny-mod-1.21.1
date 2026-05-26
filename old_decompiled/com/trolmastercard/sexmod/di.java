/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.dm;
import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.em;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class di
extends dm {
    float z = 0.0f;

    public di(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    @Override
    protected void c() {
        GlStateManager.func_179109_b((float)0.0f, (float)-1.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)0.65f, (float)0.65f, (float)0.65f);
    }

    @Override
    protected ItemStack a(@Nullable ItemStack itemStack) {
        switch (this.j.y()) {
            case FISHING_IDLE: 
            case FISHING_START: {
                ItemStack itemStack2 = ((eb)this.j).ao;
                this.j.func_184611_a(EnumHand.MAIN_HAND, itemStack2);
                return itemStack2;
            }
        }
        return itemStack;
    }

    boolean b() {
        return (Boolean)this.j.func_184212_Q().func_187225_a(em.G);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void a(String var1_1, GeoBone var2_2) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 5[SWITCH]
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

    @Override
    protected void a(boolean bl2) {
        float f10;
        try {
            f10 = bl2 ? 60.0f : 150.0f;
        }
        catch (RuntimeException runtimeException) {
            throw di.a(runtimeException);
        }
        try {
            GlStateManager.func_179114_b((float)f10, (float)1.0f, (float)0.0f, (float)0.0f);
            if (bl2) {
                GlStateManager.func_179137_b((double)0.12, (double)0.0, (double)0.0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw di.a(runtimeException);
        }
    }

    @Override
    protected void a(boolean bl2, boolean bl3) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            super.a(bl2, bl3);
                            if (bl2 || !bl3) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw di.a(runtimeException);
                        }
                        GlStateManager.func_179114_b((float)120.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw di.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (bl2 || bl3) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw di.a(runtimeException);
                    }
                    GlStateManager.func_179137_b((double)0.0, (double)0.3, (double)-0.15);
                    GlStateManager.func_179114_b((float)-45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw di.a(runtimeException);
                }
            }
            try {
                try {
                    if (!bl2 || bl3) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw di.a(runtimeException);
                }
                GlStateManager.func_179137_b((double)-0.025, (double)-0.05, (double)0.0);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw di.a(runtimeException);
            }
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

