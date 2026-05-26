/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.item.ItemStack
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.dm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dl
extends dm {
    public dl(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    @Override
    protected void c() {
        GlStateManager.func_179109_b((float)0.0f, (float)-1.5f, (float)0.0f);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void a(boolean var1_1, ItemStack var2_2) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 3[SWITCH]
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
            f10 = bl2 ? 90.0f : 180.0f;
        }
        catch (RuntimeException runtimeException) {
            throw dl.a(runtimeException);
        }
        try {
            GlStateManager.func_179114_b((float)f10, (float)1.0f, (float)0.0f, (float)0.0f);
            if (bl2) {
                GlStateManager.func_179137_b((double)0.2, (double)-0.2, (double)0.0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw dl.a(runtimeException);
        }
    }

    @Override
    protected void a(boolean bl2, boolean bl3) {
        block7: {
            block8: {
                block6: {
                    try {
                        try {
                            if (!bl2) break block6;
                            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                            GlStateManager.func_179114_b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                            if (!bl3) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw dl.a(runtimeException);
                        }
                        GlStateManager.func_179114_b((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        GlStateManager.func_179114_b((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                        GlStateManager.func_179114_b((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        GlStateManager.func_179109_b((float)0.4f, (float)0.0f, (float)0.228f);
                        break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw dl.a(runtimeException);
                    }
                }
                try {
                    GlStateManager.func_179109_b((float)0.0f, (float)0.282f, (float)0.141f);
                    if (!bl3) break block8;
                    GlStateManager.func_179137_b((double)0.165, (double)-0.45f, (double)0.0);
                    GlStateManager.func_179114_b((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)-27.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw dl.a(runtimeException);
                }
            }
            GlStateManager.func_179137_b((double)0.0, (double)0.0, (double)-0.05);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

