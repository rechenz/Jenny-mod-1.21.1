/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainerCreative
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemShield
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.bu;
import com.trolmastercard.sexmod.d_;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.p;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class dm
extends d_ {
    public static boolean v = false;
    ItemStack s = ItemStack.field_190927_a;
    ItemStack x = ItemStack.field_190927_a;
    boolean r = false;
    boolean u = false;
    protected ei w;
    protected float y;
    float t = 0.0f;

    public dm(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel, 0.0);
    }

    public void func_76979_b(Entity entity, double d10, double d11, double d12, float f10, float f11) {
    }

    boolean a(em em2) {
        try {
            if (em2.h()) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        boolean bl2 = v;
        v = false;
        return bl2;
    }

    public void a(em em2, double d10, double d11, double d12, float f10, float f11) {
        try {
            if (!this.a(em2)) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        ei ei2 = (ei)em2;
        try {
            if (ei2.m() == null) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        EntityPlayer entityPlayer = Minecraft.func_71410_x().field_71439_g.field_70170_p.func_152378_a(ei2.m());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        try {
            this.s = entityPlayer.func_184614_ca();
            this.x = entityPlayer.func_184592_cb();
            this.u = ei2.ah;
            this.r = ei2.ad;
            this.w = (ei)em2;
            this.y = f11;
            ei2.f(entityPlayer);
            if (this.a(entityPlayer, em2)) {
                this.func_147906_a((Entity)em2, entityPlayer.func_70005_c_(), d10, d11 + (double)ei2.i(), d12, 300);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        super.a(em2, d10, d11, d12, f10, f11);
    }

    @Override
    public Entity c(em em2) {
        try {
            if (!(em2 instanceof ei)) {
                return em2;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        ei ei2 = (ei)em2;
        EntityPlayer entityPlayer = ei2.k();
        try {
            if (entityPlayer == null) {
                return em2;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        return entityPlayer;
    }

    boolean a(EntityPlayer entityPlayer, em em2) {
        boolean bl2;
        try {
            if (entityPlayer.getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID())) {
                return false;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        fp fp2 = em2.y();
        try {
            if (fp2 == null) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        try {
            bl2 = !fp2.hideNameTag;
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        return bl2;
    }

    protected void a(String string, GeoBone geoBone) {
    }

    protected void a(String string, GeoBone geoBone, ei ei2, BufferBuilder bufferBuilder) {
    }

    /*
     * Could not resolve type clashes
     * Unable to fully structure code
     */
    @Override
    public void renderRecursively(BufferBuilder var1_1, GeoBone var2_2, float var3_3, float var4_4, float var5_5, float var6_6) {
        block68: {
            block79: {
                block67: {
                    block66: {
                        block65: {
                            block63: {
                                block64: {
                                    block73: {
                                        block61: {
                                            block62: {
                                                block71: {
                                                    block70: {
                                                        block59: {
                                                            block60: {
                                                                var7_7 = var2_2.getName();
                                                                if (!this.r) break block59;
                                                                try {
                                                                    block69: {
                                                                        if (!var7_7.equals("upperBody")) break block60;
                                                                        break block69;
                                                                        catch (IllegalStateException v0) {
                                                                            throw dm.b(v0);
                                                                        }
                                                                    }
                                                                    var2_2.setRotationX(var2_2.getRotationX() - 0.5f);
                                                                }
                                                                catch (IllegalStateException v1) {
                                                                    throw dm.b(v1);
                                                                }
                                                            }
                                                            try {
                                                                if (var7_7.equals("head")) {
                                                                    var2_2.setRotationX(var2_2.getRotationX() + 0.5f);
                                                                }
                                                            }
                                                            catch (IllegalStateException v2) {
                                                                throw dm.b(v2);
                                                            }
                                                        }
                                                        try {
                                                            if (var7_7.equals("head")) {
                                                                this.a(var1_1, var2_2, Color.ofRGB(var3_3, var4_4, var5_5));
                                                            }
                                                        }
                                                        catch (IllegalStateException v3) {
                                                            throw dm.b(v3);
                                                        }
                                                        this.a(var7_7, var2_2);
                                                        this.a(var7_7, var2_2, this.w, var1_1);
                                                        if (!this.u) break block61;
                                                        if (this.s.func_77973_b() instanceof ItemBow) ** GOTO lbl43
                                                        break block70;
                                                        catch (IllegalStateException v4) {
                                                            throw dm.b(v4);
                                                        }
                                                    }
                                                    if (!(this.x.func_77973_b() instanceof ItemBow)) break block61;
                                                    break block71;
                                                    catch (IllegalStateException v5) {
                                                        throw dm.b(v5);
                                                    }
                                                }
                                                try {
                                                    block72: {
                                                        if (!var7_7.equals("armR")) break block62;
                                                        break block72;
                                                        catch (IllegalStateException v6) {
                                                            throw dm.b(v6);
                                                        }
                                                    }
                                                    var2_2.setRotationX(var2_2.getRotationX() - this.j.field_70125_A / 50.0f);
                                                }
                                                catch (IllegalStateException v7) {
                                                    throw dm.b(v7);
                                                }
                                            }
                                            try {
                                                if (var7_7.equals("armL")) {
                                                    var2_2.setRotationY(var2_2.getRotationY() - this.j.field_70125_A / 50.0f);
                                                }
                                            }
                                            catch (IllegalStateException v8) {
                                                throw dm.b(v8);
                                            }
                                            if (this.x.func_77973_b() instanceof ItemBow) {
                                                var8_8 = this.x;
                                                this.x = this.s;
                                                this.s = var8_8;
                                            }
                                        }
                                        if (!this.u) break block63;
                                        if (!(this.s.func_77973_b() instanceof ItemShield)) break block63;
                                        break block73;
                                        catch (IllegalStateException v9) {
                                            throw dm.b(v9);
                                        }
                                    }
                                    try {
                                        block74: {
                                            if (!var7_7.equals("armR")) break block64;
                                            break block74;
                                            catch (IllegalStateException v10) {
                                                throw dm.b(v10);
                                            }
                                        }
                                        var2_2.setRotationZ(0.0f);
                                        var2_2.setRotationX(0.5f);
                                        break block63;
                                    }
                                    catch (IllegalStateException v11) {
                                        throw dm.b(v11);
                                    }
                                }
                                if (!(this.x.func_77973_b() instanceof ItemShield)) break block63;
                                try {
                                    block75: {
                                        if (!var7_7.equals("armL")) break block63;
                                        break block75;
                                        catch (IllegalStateException v12) {
                                            throw dm.b(v12);
                                        }
                                    }
                                    var2_2.setRotationZ(0.0f);
                                    var2_2.setRotationX(0.5f);
                                }
                                catch (IllegalStateException v13) {
                                    throw dm.b(v13);
                                }
                            }
                            if (!var7_7.equals("weapon")) break block65;
                            try {
                                block76: {
                                    if (this.s.func_190926_b()) break block65;
                                    break block76;
                                    catch (IllegalStateException v14) {
                                        throw dm.b(v14);
                                    }
                                }
                                this.a(var1_1, var2_2, false);
                            }
                            catch (IllegalStateException v15) {
                                throw dm.b(v15);
                            }
                        }
                        if (!var7_7.equals("offhand")) break block66;
                        try {
                            block77: {
                                if (this.x.func_190926_b()) break block66;
                                break block77;
                                catch (IllegalStateException v16) {
                                    throw dm.b(v16);
                                }
                            }
                            this.a(var1_1, var2_2, true);
                        }
                        catch (IllegalStateException v17) {
                            throw dm.b(v17);
                        }
                    }
                    dm.MATRIX_STACK.push();
                    dm.MATRIX_STACK.translate(var2_2);
                    dm.MATRIX_STACK.moveToPivot(var2_2);
                    dm.MATRIX_STACK.rotate(var2_2);
                    dm.MATRIX_STACK.scale(var2_2);
                    dm.MATRIX_STACK.moveBackFromPivot(var2_2);
                    if (!"Head2".equals(var7_7)) break block67;
                    try {
                        block78: {
                            if (this.c()) break block67;
                            break block78;
                            catch (IllegalStateException v18) {
                                throw dm.b(v18);
                            }
                        }
                        dm.MATRIX_STACK.pop();
                        return;
                    }
                    catch (IllegalStateException v19) {
                        throw dm.b(v19);
                    }
                }
                if ("neck".equals(var7_7)) ** GOTO lbl152
                if (!"head".equals(var7_7)) break block68;
                break block79;
                catch (IllegalStateException v20) {
                    throw dm.b(v20);
                }
            }
            try {
                block80: {
                    if (this.a()) break block68;
                    break block80;
                    catch (IllegalStateException v21) {
                        throw dm.b(v21);
                    }
                }
                dm.MATRIX_STACK.pop();
                return;
            }
            catch (IllegalStateException v22) {
                throw dm.b(v22);
            }
        }
        if (!var2_2.isHidden) {
            var8_8 = this.a(var7_7, var3_3, var4_4, var5_5);
            var3_3 = var8_8.x;
            var4_4 = var8_8.y;
            var5_5 = var8_8.z;
            var9_10 = var8_8.w;
            if (!this.p.contains(var7_7)) {
                for (Object var12_12 : var2_2.childCubes) {
                    dm.MATRIX_STACK.push();
                    GlStateManager.func_179094_E();
                    this.q = var2_2;
                    this.a(var1_1, (GeoCube)var12_12, var3_3, var4_4, var5_5, var6_6, var9_10);
                    GlStateManager.func_179121_F();
                    dm.MATRIX_STACK.pop();
                }
            }
            for (Object var12_12 : var2_2.childBones) {
                try {
                    if (var9_10 == 0.0) {
                        this.renderRecursively(var1_1, (GeoBone)var12_12, var3_3, var4_4, var5_5, var6_6);
                        continue;
                    }
                }
                catch (IllegalStateException v23) {
                    throw dm.b(v23);
                }
                this.a(var1_1, (GeoBone)var12_12, var3_3, var4_4, var5_5, var6_6, var9_10);
            }
        }
        try {
            dm.MATRIX_STACK.pop();
        }
        catch (IllegalStateException var8_9) {
            // empty catch block
        }
    }

    boolean a() {
        boolean bl2;
        block11: {
            block10: {
                try {
                    if (!((ei)this.j).f()) {
                        return true;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw dm.b(illegalStateException);
                }
                try {
                    if (dm.i.field_71474_y.field_74320_O != 0) {
                        return true;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw dm.b(illegalStateException);
                }
                try {
                    try {
                        if (!(dm.i.field_71462_r instanceof GuiInventory) && !(dm.i.field_71462_r instanceof GuiContainerCreative)) break block10;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw dm.b(illegalStateException);
                    }
                    bl2 = true;
                    break block11;
                }
                catch (IllegalStateException illegalStateException) {
                    throw dm.b(illegalStateException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    void a(BufferBuilder bufferBuilder, GeoBone geoBone, Color color) {
        GlStateManager.func_179094_E();
        Tessellator.func_178181_a().func_78381_a();
        com.trolmastercard.sexmod.p.a(IGeoRenderer.MATRIX_STACK, geoBone);
        GL11.glEnable((int)2896);
        this.c();
        new bu((IGeoRenderer)this).render((EntityLivingBase)this.j, this.j.field_184619_aG, this.j.field_70721_aZ, this.y, 0.0f, 0.0f, 0.0f, color);
        this.func_110776_a(Objects.requireNonNull(this.getEntityTexture(this.j)));
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glDisable((int)2896);
        GlStateManager.func_179121_F();
    }

    protected void c() {
    }

    /*
     * Exception decompiling
     */
    void a(BufferBuilder var1_1, GeoBone var2_2, boolean var3_3) {
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

    protected void a(boolean bl2, ItemStack itemStack) {
        float f10;
        try {
            f10 = bl2 ? 200.0f : 90.0f;
        }
        catch (IllegalStateException illegalStateException) {
            throw dm.b(illegalStateException);
        }
        GlStateManager.func_179114_b((float)f10, (float)1.0f, (float)0.0f, (float)0.0f);
    }

    protected void a(boolean bl2) {
        GlStateManager.func_179114_b((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
    }

    protected void a(boolean bl2, boolean bl3) {
        block8: {
            block7: {
                try {
                    try {
                        if (!bl2) break block7;
                        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        GlStateManager.func_179114_b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        if (!bl3) break block8;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw dm.b(illegalStateException);
                    }
                    GlStateManager.func_179114_b((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)35.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.func_179114_b((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)0.228f);
                    break block8;
                }
                catch (IllegalStateException illegalStateException) {
                    throw dm.b(illegalStateException);
                }
            }
            try {
                if (bl3) {
                    GlStateManager.func_179114_b((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.func_179109_b((float)0.0f, (float)0.165f, (float)0.0f);
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw dm.b(illegalStateException);
            }
        }
    }

    private static IllegalStateException b(IllegalStateException illegalStateException) {
        return illegalStateException;
    }
}

