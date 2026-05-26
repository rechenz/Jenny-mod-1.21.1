/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.af;
import com.trolmastercard.sexmod.ak;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.ce;
import com.trolmastercard.sexmod.d_;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f7;
import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.gc;
import com.trolmastercard.sexmod.gv;
import com.trolmastercard.sexmod.gx;
import com.trolmastercard.sexmod.p;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class dh
extends d_<f8> {
    static final gv C = new gv(115, 108, 188, 255);
    static final f7 D = new f7(0.05f, 0.04f, 0.0f);
    static final f7 v = new f7(0.0f, 0.065f, 0.0f);
    static final f7 z = new f7(0.0f, 0.03f, 0.03f);
    static final gv r = new gv(63, 59, 150, 255);
    static final gv x = new gv(79, 74, 188, 255);
    static final float A = 0.5f;
    static final float w = 0.5f;
    static final int s = 40;
    static final float y = 0.01f;
    static final float t = 0.03f;
    public static final HashSet<String> B = new HashSet<String>(){
        {
            this.add("boobs2");
            this.add("booty2");
            this.add("vagina2");
            this.add("fuckhole2");
        }
    };
    boolean u = false;

    public dh(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d10) {
        super(renderManager, animatedGeoModel, d10);
    }

    @Override
    public HashSet<String> a() {
        try {
            if (!this.u) {
                B.addAll(gx.a);
                this.u = true;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        return B;
    }

    @Override
    public void a(f8 f82, double d10, double d11, double d12, float f10, float f11) {
        try {
            if (this.d(f82)) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            if (this.a(f82)) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            if (dh.c(f82, 0.5f)) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            if (this.c(f82)) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        super.a(f82, d10, d11, d12, f10, f11);
        dh.a(f82, f11);
    }

    boolean c(f8 f82) {
        f_ f_2 = f82.a(false);
        try {
            if (f_2 == null) {
                return false;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            switch (f_2.y()) {
                case CONTROLLED_FLIGHT: 
                case BOOST: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
    }

    boolean a(f8 f82) {
        boolean bl2;
        try {
            if (f82.y() != fp.RIDE_MOMMY_HEAD) {
                return false;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            bl2 = f82.a(false) == null;
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        return bl2;
    }

    boolean d(f8 f82) {
        f_ f_2 = f82.a(false);
        try {
            if (f_2 == null) {
                return false;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            if (f_2.field_70128_L) {
                f82.a((UUID)null);
                return false;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        return f_2.b();
    }

    public void func_76979_b(Entity entity, double d10, double d11, double d12, float f10, float f11) {
        try {
            if (!(entity instanceof f8)) {
                super.func_76979_b(entity, d10, d11, d12, f10, f11);
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        f8 f82 = (f8)entity;
        try {
            if (this.d(f82)) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            if (f82.r()) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        super.func_76979_b(entity, d10, d11, d12, f10, f11);
    }

    static boolean c(em em2, float f10) {
        boolean bl2;
        try {
            if (!(em2 instanceof f8)) {
                return false;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        f_ f_2 = ((f8)em2).a(false);
        try {
            if (f_2 == null) {
                return false;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            bl2 = f_2.bm < f10;
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        return bl2;
    }

    public static void a(em em2, float f10) {
        BufferBuilder bufferBuilder;
        Tessellator tessellator;
        block9: {
            block8: {
                EntityPlayerSP entityPlayerSP = dh.i.field_71439_g;
                try {
                    if (entityPlayerSP == null) {
                        return;
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
                try {
                    if (dh.c(em2, 0.5f)) {
                        return;
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
                tessellator = Tessellator.func_178181_a();
                bufferBuilder = tessellator.func_178180_c();
                try {
                    GlStateManager.func_179094_E();
                    if (!em2.h()) break block8;
                    GlStateManager.func_179137_b((double)0.0, (double)0.01, (double)0.0);
                    break block9;
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
            }
            af.a(i, em2, f10);
            dh.b(em2, f10);
        }
        i.func_110434_K().func_110577_a(e);
        GlStateManager.func_179129_p();
        GlStateManager.func_179140_f();
        dh.a(em2, bufferBuilder, tessellator, dh.a(em2, f10));
        dh.a(em2, bufferBuilder, tessellator);
        GlStateManager.func_179121_F();
        GlStateManager.func_179089_o();
        GlStateManager.func_179145_e();
    }

    static void b(em em2, float f10) {
        try {
            if (!(em2 instanceof f8)) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        f8 f82 = (f8)em2;
        try {
            if (!f82.r()) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            if (ce.c(f82)) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        f_ f_2 = f82.a(false);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        GlStateManager.func_179114_b((float)(-b6.b(em2.field_70760_ar, em2.field_70761_aq, (double)f10)), (float)0.0f, (float)1.0f, (float)0.0f);
    }

    static boolean a(em em2) {
        boolean bl2;
        if (em2 instanceof f_) {
            em2 = ((f_)em2).a(false);
        }
        try {
            if (em2 == null) {
                return false;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        try {
            bl2 = !fp.a(em2, fp.THREESOME_SLOW, fp.THREESOME_FAST, fp.THREESOME_CUM);
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        return bl2;
    }

    static void a(em em2, BufferBuilder bufferBuilder, Tessellator tessellator) {
        try {
            if (!dh.a(em2)) {
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        try {
            for (int i2 = 0; i2 < 39; ++i2) {
                dh.a(em2, bufferBuilder, i2, i2 + 1);
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        dh.a(em2, bufferBuilder, 39, 0);
        tessellator.func_78381_a();
    }

    static void a(em em2, BufferBuilder bufferBuilder, int n2, int n3) {
        gv gv2;
        Vec3d vec3d = em2.b("skirt_" + n2 + "_0");
        Vec3d vec3d2 = em2.b("skirt_" + n2 + "_1");
        Vec3d vec3d3 = em2.b("skirt_" + n2 + "_2");
        Vec3d vec3d4 = em2.b("skirt_" + n3 + "_0");
        Vec3d vec3d5 = em2.b("skirt_" + n3 + "_1");
        Vec3d vec3d6 = em2.b("skirt_" + n3 + "_2");
        try {
            gv2 = n2 % 2 == 0 ? x : r;
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        gv gv3 = gv2;
        bufferBuilder.func_181662_b(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c).func_181669_b(gv3.a, gv3.d, gv3.c, gv3.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c).func_181669_b(gv3.a, gv3.d, gv3.c, gv3.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3d5.field_72450_a, vec3d5.field_72448_b, vec3d5.field_72449_c).func_181669_b(gv3.a, gv3.d, gv3.c, gv3.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3d4.field_72450_a, vec3d4.field_72448_b, vec3d4.field_72449_c).func_181669_b(gv3.a, gv3.d, gv3.c, gv3.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c).func_181669_b(gv3.a, gv3.d, gv3.c, gv3.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3d5.field_72450_a, vec3d5.field_72448_b, vec3d5.field_72449_c).func_181669_b(gv3.a, gv3.d, gv3.c, gv3.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3d6.field_72450_a, vec3d6.field_72448_b, vec3d6.field_72449_c).func_181669_b(gv3.a, gv3.d, gv3.c, gv3.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c).func_181669_b(gv3.a, gv3.d, gv3.c, gv3.b).func_181675_d();
    }

    @Override
    protected void a(BufferBuilder bufferBuilder, String string, GeoBone geoBone) {
        block12: {
            Entity entity;
            block11: {
                dh.a(this.j, string, geoBone, false);
                entity = ((f8)this.j).b();
                try {
                    if (entity == null) {
                        return;
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
                try {
                    try {
                        if (!"weapon".equals(string) || !((f8)this.j).a(entity, i.func_184121_ak())) break block11;
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw dh.a(numberFormatException);
                    }
                    this.a(bufferBuilder, geoBone, true);
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
            }
            try {
                try {
                    if (!"offhand".equals(string) || ((f8)this.j).a(entity, i.func_184121_ak())) break block12;
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
                this.a(bufferBuilder, geoBone, false);
            }
            catch (NumberFormatException numberFormatException) {
                throw dh.a(numberFormatException);
            }
        }
    }

    void a(BufferBuilder bufferBuilder, GeoBone geoBone, boolean bl2) {
        ItemRenderer itemRenderer;
        block5: {
            block4: {
                itemRenderer = Minecraft.func_71410_x().func_175597_ag();
                try {
                    GlStateManager.func_179094_E();
                    Tessellator.func_178181_a().func_78381_a();
                    com.trolmastercard.sexmod.p.a(IGeoRenderer.MATRIX_STACK, geoBone);
                    GL11.glEnable((int)2896);
                    GlStateManager.func_179147_l();
                    GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                    if (!bl2) break block4;
                    GlStateManager.func_179137_b((double)-0.01, (double)0.0, (double)0.0);
                    GlStateManager.func_179114_b((float)120.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    break block5;
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
            }
            GlStateManager.func_179137_b((double)0.15, (double)0.0, (double)-0.05);
            GlStateManager.func_179114_b((float)-140.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GlStateManager.func_179139_a((double)0.7, (double)0.7, (double)0.7);
        ItemStack itemStack = new ItemStack((Item)Items.field_151031_f);
        float f10 = ((f8)this.j).b(i.func_184121_ak());
        if (f10 < 1.0f) {
            float f11 = (float)b6.e(f10);
            ((f8)this.j).d((int)(11.0f * (1.0f - f11) + 71980.0f));
            ((f8)this.j).a(itemStack);
            ((f8)this.j).func_184598_c(EnumHand.MAIN_HAND);
            ((f8)this.j).W();
        } else {
            ((f8)this.j).a(ItemStack.field_190927_a);
            ((f8)this.j).K();
        }
        itemRenderer.func_178099_a((EntityLivingBase)this.j, itemStack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
        this.func_110776_a(Objects.requireNonNull(this.getEntityTexture(this.j)));
        GL11.glDisable((int)2896);
        GlStateManager.func_179121_F();
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }

    public static void a(em em2, String string, GeoBone geoBone, boolean bl2) {
        block25: {
            float f10;
            String string2;
            int n2;
            block26: {
                block23: {
                    block24: {
                        try {
                            if (!string.contains("skirt_")) {
                                return;
                            }
                        }
                        catch (NumberFormatException numberFormatException) {
                            throw dh.a(numberFormatException);
                        }
                        n2 = dh.a(string);
                        try {
                            try {
                                if (!be.a((double)n2, 17.0, 35.0)) break block23;
                                if (!i.func_147113_T()) break block24;
                            }
                            catch (NumberFormatException numberFormatException) {
                                throw dh.a(numberFormatException);
                            }
                            return;
                        }
                        catch (NumberFormatException numberFormatException) {
                            throw dh.a(numberFormatException);
                        }
                    }
                    try {
                        String string3 = string2 = n2 < 26 ? "cheekL" : "cheekR";
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw dh.a(numberFormatException);
                    }
                    if (bl2) {
                        string2 = string2 + "2";
                    }
                    f10 = gc.d(em2.b().getBone(string2).getRotationX());
                    try {
                        if (f10 < 0.0f) {
                            return;
                        }
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw dh.a(numberFormatException);
                    }
                    geoBone.setPositionY(geoBone.getPositionY() + f10 * 0.01f);
                }
                try {
                    try {
                        if (!be.a((double)n2, 1.0, 11.0)) break block25;
                        if (string.endsWith("1")) break block26;
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw dh.a(numberFormatException);
                    }
                    return;
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
            }
            try {
                String string4 = string2 = n2 < 6 ? "legR" : "legL";
            }
            catch (NumberFormatException numberFormatException) {
                throw dh.a(numberFormatException);
            }
            if (bl2) {
                string2 = string2 + "2";
            }
            f10 = gc.d(em2.b().getBone(string2).getRotationX());
            try {
                if (f10 < 0.0f) {
                    return;
                }
            }
            catch (NumberFormatException numberFormatException) {
                throw dh.a(numberFormatException);
            }
            geoBone.setRotationX(gc.c(f10));
            geoBone.setPositionY(gc.c(f10 * 0.03f));
        }
    }

    static int a(String string) {
        block4: {
            int n2 = string.indexOf(95);
            int n3 = string.indexOf(95, n2 + 1);
            try {
                if (n2 == -1 || n3 == -1) break block4;
            }
            catch (NumberFormatException numberFormatException) {
                throw dh.a(numberFormatException);
            }
            String string2 = string.substring(n2 + 1, n3);
            try {
                return Integer.parseInt(string2);
            }
            catch (NumberFormatException numberFormatException) {
                return -1;
            }
        }
        return -1;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void a(GeoModel var1_1, BufferBuilder var2_2, f8 var3_3, float var4_4, float var5_5, float var6_6, float var7_7, float var8_8) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 3[SWITCH]
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

    static void a(em em2, BufferBuilder bufferBuilder, Tessellator tessellator, float f10) {
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        Vec3d[][] vec3dArray = af.a(em2, f10, "clothBoobLconStart", "clothBoobLconEnd", D, v);
        Vec3d[][] vec3dArray2 = af.a(em2, f10, "clothBoobRconStart", "clothBoobRconEnd", D, v);
        Vec3d[][] vec3dArray3 = af.a(em2, f10, "clothBoobMidconStart", "clothBoobMidconEnd", z, z);
        af.a(bufferBuilder, vec3dArray, C);
        af.a(bufferBuilder, vec3dArray2, C);
        af.a(bufferBuilder, vec3dArray3, C);
        tessellator.func_78381_a();
    }

    public boolean a(HashSet hashSet, GeoBone geoBone) {
        while (geoBone.parent != null) {
            String string = geoBone.getName();
            try {
                if (string.contains("clothBoob")) {
                    return true;
                }
            }
            catch (NumberFormatException numberFormatException) {
                throw dh.a(numberFormatException);
            }
            try {
                if (hashSet.contains(string)) {
                    return false;
                }
            }
            catch (NumberFormatException numberFormatException) {
                throw dh.a(numberFormatException);
            }
            try {
                if (string.startsWith("armor")) {
                    return false;
                }
            }
            catch (NumberFormatException numberFormatException) {
                throw dh.a(numberFormatException);
            }
            geoBone = geoBone.parent;
        }
        return true;
    }

    @Override
    protected Vec3d a(f8 f82, float f10, Vec3d vec3d) {
        if (f82.y() == fp.RUN) {
            float f11;
            f82.field_70177_z = f11 = f82.I().floatValue();
            f82.field_70760_ar = f11;
            f82.field_70761_aq = f11;
            f82.field_70758_at = f11;
            f82.field_70759_as = f11;
            return vec3d;
        }
        if (dh.b(f82)) {
            f_ f_2 = f82.a(false);
            try {
                if (f_2 != null) {
                    dh.a(f_2, f10, (EntityLivingBase)f82);
                    return dh.b(f_2, f10);
                }
            }
            catch (NumberFormatException numberFormatException) {
                throw dh.a(numberFormatException);
            }
        }
        return vec3d;
    }

    public static void a(f_ f_2, float f10, EntityLivingBase entityLivingBase) {
        float f11;
        float f12;
        boolean bl2 = f_2.Q();
        try {
            f12 = bl2 ? f_2.I().floatValue() : f_2.field_70759_as;
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        float f13 = f12;
        try {
            f11 = bl2 ? f_2.I().floatValue() : f_2.field_70758_at;
        }
        catch (NumberFormatException numberFormatException) {
            throw dh.a(numberFormatException);
        }
        float f14 = f11;
        Float f15 = f_.a(f_2, f10);
        if (f15 != null) {
            f13 = f15.floatValue();
            f14 = f15.floatValue();
        }
        entityLivingBase.field_70177_z = f13;
        entityLivingBase.field_70760_ar = f14;
        entityLivingBase.field_70761_aq = f13;
        entityLivingBase.field_70758_at = f14;
        entityLivingBase.field_70759_as = f13;
    }

    public static boolean b(f8 f82) {
        boolean bl2;
        block5: {
            block4: {
                try {
                    try {
                        if (!f82.r() || ce.c(f82)) break block4;
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw dh.a(numberFormatException);
                    }
                    bl2 = true;
                    break block5;
                }
                catch (NumberFormatException numberFormatException) {
                    throw dh.a(numberFormatException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    public static Vec3d b(f_ f_2, float f10) {
        return ak.a((Entity)f_2, (EntityPlayer)dh.i.field_71439_g, f10).func_178787_e(f_2.b("mangPos"));
    }

    public static Vec3d a(f_ f_2, float f10) {
        return ak.a((Entity)f_2, f10).func_178787_e(f_2.b("mangPos"));
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

