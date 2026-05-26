/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.cv;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ff;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class c9
extends cv {
    static final float g = 1.2f;
    static final float f = 1.0f;

    @Override
    protected ResourceLocation[] a() {
        return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/kobold/kobold.geo.json"), new ResourceLocation("sexmod", "geo/kobold/armored.geo.json")};
    }

    @Override
    public ResourceLocation b() {
        return new ResourceLocation("sexmod", "textures/entity/kobold/kobold.png");
    }

    @Override
    public ResourceLocation b(em em2) {
        return new ResourceLocation("sexmod", "animations/kobold/kobold.animation.json");
    }

    /*
     * Exception decompiling
     */
    public void a(em var1_1, Integer var2_2, AnimationEvent var3_3) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[TRYBLOCK]], but top level block is 6[SWITCH]
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

    void b(em em2, AnimationProcessor animationProcessor) {
        try {
            if (em2.C.getAnimationState() != AnimationState.Transitioning) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        float f10 = ((Float)em2.func_184212_Q().func_187225_a(ff.aE)).floatValue();
        f10 = 0.25f - f10;
        switch (em2.y()) {
            case SUCKBLOWJOB_BLINK: 
            case THRUSTBLOWJOB: 
            case CUMBLOWJOB: {
                IBone iBone = animationProcessor.getBone("body");
                iBone.setPositionZ(11.43f + f10 * -7.0f);
                return;
            }
            case KOBOLD_ANAL_SLOW: 
            case ANAL_FAST: 
            case ANAL_CUM: 
            case ANAL_START: {
                IBone iBone = animationProcessor.getBone("body");
                iBone.setPositionX(1.78f + f10 * -1.5f);
                iBone.setPositionY(13.07f + f10 * -11.0f);
                iBone.setPositionZ(2.05f + f10 * -8.0f);
                return;
            }
            case MATING_PRESS_CUM: 
            case MATING_PRESS_HARD: 
            case MATING_PRESS_SOFT: 
            case MATING_PRESS_START: {
                IBone iBone = animationProcessor.getBone("body");
                iBone.setPositionX(0.0f);
                iBone.setPositionY(2.85f);
                iBone.setPositionZ(-7.0f + f10 * 4.7f);
                return;
            }
        }
    }

    /*
     * Exception decompiling
     */
    void a(em var1_1, AnimationProcessor var2_2, String var3_3) {
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

    void d(AnimationProcessor animationProcessor, String string) {
        boolean bl2;
        IBone iBone;
        boolean bl3;
        IBone iBone2;
        boolean bl4;
        IBone iBone3;
        boolean bl5;
        IBone iBone4;
        int n2 = Integer.parseInt(string);
        IBone iBone5 = animationProcessor.getBone("frecklesHR1");
        IBone iBone6 = animationProcessor.getBone("frecklesHR2");
        IBone iBone7 = animationProcessor.getBone("frecklesHL1");
        IBone iBone8 = animationProcessor.getBone("frecklesHL2");
        try {
            iBone4 = iBone7;
            bl5 = n2 != 1;
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        try {
            iBone4.setHidden(bl5);
            iBone3 = iBone5;
            bl4 = n2 != 1;
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        try {
            iBone3.setHidden(bl4);
            iBone2 = iBone8;
            bl3 = n2 != 2;
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        try {
            iBone2.setHidden(bl3);
            iBone = iBone6;
            bl2 = n2 != 2;
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        iBone.setHidden(bl2);
    }

    void a(AnimationProcessor animationProcessor, String string) {
        boolean bl2;
        IBone iBone;
        boolean bl3;
        IBone iBone2;
        boolean bl4;
        IBone iBone3;
        boolean bl5;
        IBone iBone4;
        int n2 = Integer.parseInt(string);
        IBone iBone5 = animationProcessor.getBone("frecklesAR1");
        IBone iBone6 = animationProcessor.getBone("frecklesAR2");
        IBone iBone7 = animationProcessor.getBone("frecklesAL1");
        IBone iBone8 = animationProcessor.getBone("frecklesAL2");
        try {
            iBone4 = iBone7;
            bl5 = n2 != 1;
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        try {
            iBone4.setHidden(bl5);
            iBone3 = iBone5;
            bl4 = n2 != 1;
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        try {
            iBone3.setHidden(bl4);
            iBone2 = iBone8;
            bl3 = n2 != 2;
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        try {
            iBone2.setHidden(bl3);
            iBone = iBone6;
            bl2 = n2 != 2;
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        iBone.setHidden(bl2);
    }

    void a(AnimationProcessor animationProcessor, String string, float f10, float f11) {
        try {
            if (Minecraft.func_71410_x().func_147113_T()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c9.a(runtimeException);
        }
        float f12 = Float.parseFloat(string);
        f12 /= 100.0f;
        f12 = f10 + (f11 - f10) * f12 - 1.0f;
        IBone iBone = animationProcessor.getBone("eyeL");
        iBone.setPositionX(iBone.getPositionX() + f12);
        IBone iBone2 = animationProcessor.getBone("eyeR");
        iBone2.setPositionX(iBone2.getPositionX() - f12);
    }

    void a(AnimationProcessor animationProcessor, String string, float f10, float f11, String ... stringArray) {
        float f12 = Float.parseFloat(string);
        f12 /= 100.0f;
        f12 = f10 + (f11 - f10) * f12;
        for (String string2 : stringArray) {
            IBone iBone = animationProcessor.getBone(string2);
            try {
                if (iBone == null) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw c9.a(runtimeException);
            }
            iBone.setScaleX(f12);
            iBone.setScaleY(f12);
            iBone.setScaleZ(f12);
        }
    }

    void e(AnimationProcessor animationProcessor, String string) {
        List<IBone> list = this.c(animationProcessor, "hornDL");
        List<IBone> list2 = this.c(animationProcessor, "hornDR");
        this.a(list);
        this.a(list2);
        int n2 = new Integer(string);
        animationProcessor.getBone("hornDL" + n2).setHidden(false);
        animationProcessor.getBone("hornDR" + n2).setHidden(false);
    }

    void b(AnimationProcessor animationProcessor, String string) {
        List<IBone> list = this.c(animationProcessor, "hornUL");
        List<IBone> list2 = this.c(animationProcessor, "hornUR");
        this.a(list);
        this.a(list2);
        int n2 = new Integer(string);
        animationProcessor.getBone("hornUL" + n2).setHidden(false);
        animationProcessor.getBone("hornUR" + n2).setHidden(false);
    }

    List<IBone> c(AnimationProcessor animationProcessor, String string) {
        ArrayList<IBone> arrayList = new ArrayList<IBone>();
        int n2 = 0;
        while (true) {
            IBone iBone = animationProcessor.getBone(string + n2);
            try {
                if (iBone == null) {
                    break;
                }
            }
            catch (RuntimeException runtimeException) {
                throw c9.a(runtimeException);
            }
            arrayList.add(iBone);
            ++n2;
        }
        return arrayList;
    }

    void a(List<IBone> list) {
        for (IBone iBone : list) {
            iBone.setHidden(true);
        }
    }

    /*
     * Exception decompiling
     */
    protected void a(em var1_1, AnimationProcessor var2_2, AnimationEvent var3_3) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 7[SWITCH]
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
    public String[] c() {
        return new String[]{"armorHelmet"};
    }

    @Override
    public String[] f() {
        return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
    }

    @Override
    public String[] a() {
        return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR"};
    }

    @Override
    public String[] h() {
        return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip", "armorKneeR", "armorKneeL"};
    }

    @Override
    public String[] e() {
        return new String[]{"fleshL", "fleshR", "vagina", "fuckhole", "curvesL", "curvesR", "kneeL", "kneeR"};
    }

    @Override
    public String[] b() {
        return new String[]{"armorShoesL", "armorShoesR"};
    }

    @Override
    public String[] d() {
        return new String[]{"toesR", "toesL"};
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

