/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.d9;
import com.trolmastercard.sexmod.dy;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.e4;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.eq;
import com.trolmastercard.sexmod.f7;
import com.trolmastercard.sexmod.fp;
import java.util.HashSet;
import javax.annotation.Nullable;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dg
extends d9 {
    eq B = null;
    boolean C = false;
    boolean E = false;
    boolean D = false;

    public dg(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    @Override
    protected Vec3i a(String string) {
        String[] stringArray;
        block24: {
            block23: {
                stringArray = e4.a(this.j);
                try {
                    if (stringArray.length < 8) {
                        return z;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw dg.a(runtimeException);
                }
                try {
                    if (string.contains("band")) {
                        return dy.w;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw dg.a(runtimeException);
                }
                try {
                    try {
                        if (!string.contains("eyeColor") && !string.contains("eyeColor2")) break block23;
                    }
                    catch (RuntimeException runtimeException) {
                        throw dg.a(runtimeException);
                    }
                    return dy.b(stringArray[8]);
                }
                catch (RuntimeException runtimeException) {
                    throw dg.a(runtimeException);
                }
            }
            try {
                try {
                    if (!string.contains("variant") && !string.contains("boob")) break block24;
                }
                catch (RuntimeException runtimeException) {
                    throw dg.a(runtimeException);
                }
                return dy.c(stringArray[7]);
            }
            catch (RuntimeException runtimeException) {
                throw dg.a(runtimeException);
            }
        }
        try {
            if (string.contains("hair")) {
                return dy.d(stringArray[6]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw dg.a(runtimeException);
        }
        try {
            if (dy.D.contains(string)) {
                return dy.c(stringArray[7]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw dg.a(runtimeException);
        }
        try {
            if (dy.M.contains(string)) {
                return dy.d(stringArray[6]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw dg.a(runtimeException);
        }
        return z;
    }

    @Override
    protected Vector4f a(String string, float f10, float f11, float f12) {
        if (string.startsWith("crown")) {
            ItemStack itemStack = (ItemStack)this.j.func_184212_Q().func_187225_a(e2.X);
            try {
                if (itemStack.func_190926_b()) {
                    return super.a(string, f10, f11, f12);
                }
            }
            catch (RuntimeException runtimeException) {
                throw dg.a(runtimeException);
            }
            ItemArmor itemArmor = (ItemArmor)itemStack.func_77973_b();
            ItemArmor.ArmorMaterial armorMaterial = itemArmor.func_82812_d();
            float f13 = 0.0f;
            switch (armorMaterial) {
                case GOLD: {
                    f13 = 1.0f;
                    break;
                }
                case CHAIN: 
                case IRON: {
                    f13 = 2.0f;
                    break;
                }
                case LEATHER: {
                    f13 = 4.0f;
                    int n2 = itemArmor.func_82814_b(itemStack);
                    float f14 = (float)(n2 >> 16 & 0xFF) / 255.0f;
                    float f15 = (float)(n2 >> 8 & 0xFF) / 255.0f;
                    float f16 = (float)(n2 & 0xFF) / 255.0f;
                    f10 = f14;
                    f11 = f15;
                    f12 = f16;
                }
            }
            return new Vector4f(f10, f11, f12, 72.0f * f13 / 4096.0f);
        }
        return super.a(string, f10, f11, f12);
    }

    @Override
    protected boolean c(String string) {
        try {
            if (string.startsWith("crown")) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw dg.a(runtimeException);
        }
        return super.c(string);
    }

    @Override
    public HashSet<String> a() {
        return new HashSet<String>(){
            {
                this.add("boobs");
                this.add("booty");
                this.add("vagina");
                this.add("fuckhole");
                this.add("preggy");
                this.add("LegL");
                this.add("LegR");
                this.add("cheekR");
                this.add("cheekL");
            }
        };
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void a(String var1_1, GeoBone var2_2) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 4[SWITCH]
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
     * Unable to fully structure code
     */
    @Override
    public void a(em var1_1, double var2_2, double var4_3, double var6_4, float var8_5, float var9_6) {
        block80: {
            block73: {
                block77: {
                    block70: {
                        block75: {
                            block76: {
                                block71: {
                                    block72: {
                                        block68: {
                                            block69: {
                                                block67: {
                                                    block66: {
                                                        block65: {
                                                            block64: {
                                                                try {
                                                                    try {
                                                                        this.D = dg.v;
                                                                        this.B = (eq)var1_1;
                                                                        v0 = this;
                                                                        if (-420.69f != var8_5 || var1_1.y() != fp.SHOULDER_IDLE) break block64;
                                                                    }
                                                                    catch (RuntimeException v1) {
                                                                        throw dg.a(v1);
                                                                    }
                                                                    v2 = true;
                                                                    break block65;
                                                                }
                                                                catch (RuntimeException v3) {
                                                                    throw dg.a(v3);
                                                                }
                                                            }
                                                            v2 = false;
                                                        }
                                                        try {
                                                            try {
                                                                v0.C = v2;
                                                                v4 = this;
                                                                if (-420.69f != var8_5 || var1_1.y() != fp.PICK_UP) break block66;
                                                            }
                                                            catch (RuntimeException v5) {
                                                                throw dg.a(v5);
                                                            }
                                                            v6 = true;
                                                            break block67;
                                                        }
                                                        catch (RuntimeException v7) {
                                                            throw dg.a(v7);
                                                        }
                                                    }
                                                    v6 = false;
                                                }
                                                v4.E = v6;
                                                this.y = var9_6;
                                                dy.B = var8_5;
                                                var10_7 = var1_1.y();
                                                var11_8 = this.B.e();
                                                if (var1_1.h()) {
                                                    var12_9 = dy.a(var1_1.field_70170_p, var1_1, var11_8, var2_2, var4_3, var6_4);
                                                    var2_2 = var12_9.field_72450_a;
                                                    var4_3 = var12_9.field_72448_b;
                                                    var6_4 = var12_9.field_72449_c;
                                                }
                                                try {
                                                    try {
                                                        try {
                                                            try {
                                                                try {
                                                                    if (var10_7 != fp.THROWN && var10_7 != fp.START_THROWING) break block68;
                                                                }
                                                                catch (RuntimeException v8) {
                                                                    throw dg.a(v8);
                                                                }
                                                                if (dg.i.field_71474_y.field_74320_O != 0) break block69;
                                                            }
                                                            catch (RuntimeException v9) {
                                                                throw dg.a(v9);
                                                            }
                                                            if (var8_5 != -420.69f) break block69;
                                                        }
                                                        catch (RuntimeException v10) {
                                                            throw dg.a(v10);
                                                        }
                                                        if (var1_1.h()) break block69;
                                                    }
                                                    catch (RuntimeException v11) {
                                                        throw dg.a(v11);
                                                    }
                                                    return;
                                                }
                                                catch (RuntimeException v12) {
                                                    throw dg.a(v12);
                                                }
                                            }
                                            if (!var1_1.h()) {
                                                var1_1.field_70760_ar = var12_10 = var1_1.I().floatValue();
                                                var1_1.field_70761_aq = var12_10;
                                            }
                                        }
                                        try {
                                            try {
                                                try {
                                                    if (!dy.a(var1_1, var10_7)) break block70;
                                                    if (!dg.i.field_71439_g.getPersistentID().equals(var11_8)) break block71;
                                                }
                                                catch (RuntimeException v13) {
                                                    throw dg.a(v13);
                                                }
                                                if (-420.69f == var8_5) break block72;
                                            }
                                            catch (RuntimeException v14) {
                                                throw dg.a(v14);
                                            }
                                            return;
                                        }
                                        catch (RuntimeException v15) {
                                            throw dg.a(v15);
                                        }
                                    }
                                    var1_1.field_70761_aq = dg.i.field_71439_g.field_70177_z + 180.0f;
                                    var1_1.field_70760_ar = dg.i.field_71439_g.field_70177_z + 180.0f;
                                    var12_11 = dg.i.field_71439_g.func_70040_Z();
                                    GlStateManager.func_179094_E();
                                    GlStateManager.func_179137_b((double)var12_11.field_72450_a, (double)(var12_11.field_72448_b + (double)dg.i.field_71439_g.func_70047_e()), (double)var12_11.field_72449_c);
                                    var13_17 = e3.b(new Vec3d((double)(-Math.abs(dg.i.field_71439_g.field_70125_A)), 0.0, 0.0), dg.i.field_71439_g.field_70177_z);
                                    GlStateManager.func_179114_b((float)dg.i.field_71439_g.field_70125_A, (float)((float)var13_17.field_72450_a), (float)0.0f, (float)((float)var13_17.field_72449_c));
                                    var2_2 = 0.0;
                                    var4_3 = 0.0;
                                    var6_4 = 0.0;
                                    break block73;
                                }
                                try {
                                    try {
                                        block74: {
                                            try {
                                                try {
                                                    try {
                                                        if (this.B.m().equals(dg.i.field_71439_g.getPersistentID())) break block73;
                                                        if (!var1_1.h()) break block74;
                                                    }
                                                    catch (RuntimeException v16) {
                                                        throw dg.a(v16);
                                                    }
                                                    if (var11_8 == null) break block74;
                                                }
                                                catch (RuntimeException v17) {
                                                    throw dg.a(v17);
                                                }
                                                if (!dg.i.field_71439_g.getPersistentID().equals(var11_8)) break block75;
                                            }
                                            catch (RuntimeException v18) {
                                                throw dg.a(v18);
                                            }
                                        }
                                        if (var11_8 == null) break block76;
                                    }
                                    catch (RuntimeException v19) {
                                        throw dg.a(v19);
                                    }
                                    if (dg.i.field_71439_g.getPersistentID().equals(var11_8)) break block76;
                                }
                                catch (RuntimeException v20) {
                                    throw dg.a(v20);
                                }
                                var12_12 = var1_1.field_70170_p.func_152378_a(var11_8);
                                try {
                                    if (var12_12 == null) ** GOTO lbl137
                                    var1_1.field_70761_aq = var12_12.field_70177_z;
                                    var1_1.field_70760_ar = var12_12.field_70177_z;
                                }
                                catch (RuntimeException v21) {
                                    throw dg.a(v21);
                                }
                            }
                            var1_1.field_70761_aq = dg.i.field_71439_g.field_70177_z;
                            var1_1.field_70760_ar = dg.i.field_71439_g.field_70177_z;
                        }
                        var12_13 = dy.a(var1_1, this.B.e(), var9_6);
                        var2_2 = var12_13.field_72450_a;
                        var4_3 = var12_13.field_72448_b;
                        var6_4 = var12_13.field_72449_c;
                        break block73;
                    }
                    if (this.C) {
                        dy.a(var9_6);
                        var12_14 = new Vec3d((double)b6.a(-0.1f, 0.2f, dg.i.field_71474_y.field_74334_X / 110.0f), 0.0, 0.0);
                        var12_14 = e3.b(var12_14, dg.i.field_71439_g.field_70177_z);
                        var2_2 = var12_14.field_72450_a;
                        var4_3 = var12_14.field_72448_b;
                        var6_4 = var12_14.field_72449_c;
                        var1_1.field_70761_aq = dg.i.field_71439_g.field_70177_z;
                        var1_1.field_70760_ar = dg.i.field_71439_g.field_70126_B;
                        if (dg.i.field_71439_g.func_70093_af()) {
                            var4_3 -= 0.075;
                        }
                    } else {
                        block79: {
                            block78: {
                                try {
                                    try {
                                        if (var10_7 != fp.SHOULDER_IDLE) break block77;
                                        if (var11_8 != null) break block78;
                                    }
                                    catch (RuntimeException v22) {
                                        throw dg.a(v22);
                                    }
                                    return;
                                }
                                catch (RuntimeException v23) {
                                    throw dg.a(v23);
                                }
                            }
                            try {
                                try {
                                    if (!dg.i.field_71439_g.getPersistentID().equals(var11_8) || dg.i.field_71474_y.field_74320_O != 0) break block79;
                                }
                                catch (RuntimeException v24) {
                                    throw dg.a(v24);
                                }
                                return;
                            }
                            catch (RuntimeException v25) {
                                throw dg.a(v25);
                            }
                        }
                        var12_15 = var1_1.field_70170_p.func_152378_a(var11_8);
                        try {
                            if (var12_15 == null) {
                                return;
                            }
                        }
                        catch (RuntimeException v26) {
                            throw dg.a(v26);
                        }
                        var13_18 = dy.a(var12_15, var9_6);
                        var2_2 = var13_18.x;
                        var4_3 = var13_18.y;
                        var6_4 = var13_18.z;
                        var1_1.field_70761_aq = var13_18.w;
                        if (var12_15.func_70093_af()) {
                            var4_3 -= 0.32;
                        }
                    }
                    break block73;
                }
                try {
                    if (var10_7 != fp.PICK_UP || var11_8 == null) break block73;
                }
                catch (RuntimeException v27) {
                    throw dg.a(v27);
                }
                var12_16 = var1_1.field_70170_p.func_152378_a(var11_8);
                try {
                    if (var12_16 != null) {
                        var1_1.field_70760_ar = var12_16.field_70758_at;
                        var1_1.field_70761_aq = var12_16.field_70759_as;
                    }
                }
                catch (RuntimeException v28) {
                    throw dg.a(v28);
                }
            }
            try {
                try {
                    try {
                        super.a(var1_1, var2_2, var4_3, var6_4, var8_5, var9_6);
                        if (!dy.a(var1_1, var10_7) || dg.i.field_71474_y.field_74320_O != 0) break block80;
                    }
                    catch (RuntimeException v29) {
                        throw dg.a(v29);
                    }
                    if (!dg.i.field_71439_g.getPersistentID().equals(var11_8)) break block80;
                }
                catch (RuntimeException v30) {
                    throw dg.a(v30);
                }
                GlStateManager.func_179121_F();
            }
            catch (RuntimeException v31) {
                throw dg.a(v31);
            }
        }
    }

    @Override
    protected void b(Tessellator tessellator, BufferBuilder bufferBuilder, em em2, f7 f72, float f10) {
        dg.a(tessellator, bufferBuilder, em2, f72, f10);
    }

    /*
     * Exception decompiling
     */
    @Nullable
    protected f7 e(em var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [8[TRYBLOCK]], but top level block is 9[SWITCH]
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
    protected void c() {
        GlStateManager.func_179137_b((double)0.0, (double)-0.77, (double)-0.05);
        GlStateManager.func_179139_a((double)0.5, (double)0.5, (double)0.5);
    }

    @Override
    protected void a(boolean bl2, ItemStack itemStack) {
        float f10;
        block6: {
            block8: {
                block7: {
                    try {
                        try {
                            super.a(bl2, itemStack);
                            if (itemStack.func_77973_b().func_77661_b(itemStack) != EnumAction.BOW) break block6;
                            if (!bl2) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw dg.a(runtimeException);
                        }
                        GlStateManager.func_179109_b((float)0.1f, (float)0.0f, (float)0.0f);
                        GlStateManager.func_179114_b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw dg.a(runtimeException);
                    }
                }
                GlStateManager.func_179114_b((float)170.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            }
            return;
        }
        try {
            f10 = bl2 ? 70.0f : 180.0f;
        }
        catch (RuntimeException runtimeException) {
            throw dg.a(runtimeException);
        }
        GlStateManager.func_179114_b((float)f10, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179137_b((double)0.0, (double)0.05, (double)-0.03);
    }

    @Override
    protected void a(boolean bl2) {
    }

    @Override
    protected void a(boolean bl2, boolean bl3) {
        block8: {
            block9: {
                block6: {
                    block7: {
                        try {
                            try {
                                super.a(bl2, bl3);
                                if (!bl2) break block6;
                                if (!bl3) break block7;
                            }
                            catch (RuntimeException runtimeException) {
                                throw dg.a(runtimeException);
                            }
                            GlStateManager.func_179137_b((double)0.0, (double)0.2, (double)-0.25);
                            GlStateManager.func_179114_b((float)85.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                            GlStateManager.func_179114_b((float)38.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                            GlStateManager.func_179114_b((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                            break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw dg.a(runtimeException);
                        }
                    }
                    GlStateManager.func_179114_b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179137_b((double)0.0, (double)-0.265, (double)-0.04);
                    break block8;
                }
                try {
                    if (!bl3) break block9;
                    GlStateManager.func_179114_b((float)0.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)150.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.func_179137_b((double)0.0, (double)-0.33, (double)-0.1);
                    break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw dg.a(runtimeException);
                }
            }
            GlStateManager.func_179137_b((double)-0.02, (double)-0.05, (double)-0.05);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

