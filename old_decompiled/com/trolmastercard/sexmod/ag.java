/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.c4;
import com.trolmastercard.sexmod.gv;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class ag
extends Render<c4> {
    public static ag a;
    static final gv e;
    static final gv b;
    static final gv d;
    Minecraft c = Minecraft.func_71410_x();

    public ag(RenderManager renderManager) {
        super(renderManager);
        a = this;
    }

    @Nullable
    protected ResourceLocation a(c4 c42) {
        return new ResourceLocation("sexmod", "textures/entity/galath/energy_ball.png");
    }

    public void a(c4 c42, double d10, double d11, double d12, float f10, float f11) {
        gv gv2;
        gv gv3;
        int n2;
        GL11.glDisable((int)2896);
        GlStateManager.func_179141_d();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
        EntityPlayerSP entityPlayerSP = this.c.field_71439_g;
        Vec3d vec3d = b6.a(new Vec3d(c42.field_70142_S, c42.field_70137_T, c42.field_70136_U), c42.func_174791_d(), (double)f11);
        Vec3d vec3d2 = b6.a(new Vec3d(entityPlayerSP.field_70142_S, entityPlayerSP.field_70137_T, entityPlayerSP.field_70136_U), entityPlayerSP.func_174791_d(), (double)f11);
        Vec3d vec3d3 = vec3d.func_178788_d(vec3d2);
        try {
            GlStateManager.func_179094_E();
            GlStateManager.func_179137_b((double)vec3d3.field_72450_a, (double)vec3d3.field_72448_b, (double)vec3d3.field_72449_c);
            GlStateManager.func_179114_b((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            n2 = this.field_76990_c.field_78733_k.field_74320_O == 2 ? -1 : 1;
        }
        catch (RuntimeException runtimeException) {
            throw ag.a(runtimeException);
        }
        GlStateManager.func_179114_b((float)((float)n2 * -this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179139_a((double)c42.g, (double)c42.g, (double)c42.g);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferBuilder = tessellator.func_178180_c();
        this.c.field_71446_o.func_110577_a(this.a(c42));
        if (c42.g == 1.0) {
            float f12 = (float)this.c.field_71441_e.func_82737_E() + this.c.func_184121_ak();
            double d13 = 0.5 * Math.sin((double)f12 * 0.5) + 0.5;
            gv3 = b6.a(e, b, d13);
            gv2 = b6.a(b, e, d13);
        } else {
            gv3 = b6.a(d, e, c42.g);
            gv2 = b6.a(d, e, c42.g);
        }
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        this.a(bufferBuilder, gv3, 0.0f);
        tessellator.func_78381_a();
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        GlStateManager.func_179152_a((float)0.75f, (float)0.75f, (float)0.75f);
        GlStateManager.func_179109_b((float)0.0f, (float)0.075f, (float)0.0f);
        this.a(bufferBuilder, gv2, 0.001f);
        tessellator.func_78381_a();
        GlStateManager.func_179121_F();
        GlStateManager.func_179118_c();
        GL11.glEnable((int)2896);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)OpenGlHelper.lastBrightnessX, (float)OpenGlHelper.lastBrightnessY);
    }

    void a(BufferBuilder bufferBuilder, gv gv2, float f10) {
        bufferBuilder.func_181662_b(-0.25, 0.0, (double)f10).func_187315_a(0.0, 0.0).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(0.25, 0.0, (double)f10).func_187315_a(1.0, 0.0).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(0.25, 0.5, (double)f10).func_187315_a(1.0, 1.0).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(-0.25, 0.5, (double)f10).func_187315_a(0.0, 1.0).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
    }

    static {
        e = new gv(0, 255, 251, 255);
        b = new gv(255, 0, 236, 255);
        d = new gv(255, 255, 255, 0);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

