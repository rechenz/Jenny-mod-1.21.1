/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
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

import com.trolmastercard.sexmod.al;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.c;
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

public class e
extends Render<al> {
    static final ResourceLocation g = new ResourceLocation("sexmod", "textures/entity/pyrocinical/standing.png");
    static final ResourceLocation f = new ResourceLocation("sexmod", "textures/entity/pyrocinical/praising.png");
    static final ResourceLocation a = new ResourceLocation("sexmod", "textures/entity/pyrocinical/walking1.png");
    static final ResourceLocation b = new ResourceLocation("sexmod", "textures/entity/pyrocinical/walking2.png");
    static final String e = "textures/entity/pyrocinical/fat/";
    static final int j = 30;
    static final float c = 1.4f;
    static final float h = 0.75f;
    Minecraft d = Minecraft.func_71410_x();
    ResourceLocation k = null;
    long i = 0L;

    public e(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    protected ResourceLocation a(al al2) {
        return null;
    }

    public void a(al al2, double d10, double d11, double d12, float f10, float f11) {
        ResourceLocation resourceLocation;
        block6: {
            GL11.glDisable((int)2896);
            GlStateManager.func_179141_d();
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
            EntityPlayerSP entityPlayerSP = this.d.field_71439_g;
            Vec3d vec3d = b6.a(new Vec3d(al2.field_70142_S, al2.field_70137_T, al2.field_70136_U), al2.func_174791_d(), (double)f11);
            Vec3d vec3d2 = b6.a(new Vec3d(entityPlayerSP.field_70142_S, entityPlayerSP.field_70137_T, entityPlayerSP.field_70136_U), entityPlayerSP.func_174791_d(), (double)f11);
            Vec3d vec3d3 = vec3d.func_178788_d(vec3d2);
            resourceLocation = this.a(al2, Math.abs(vec3d3.field_72450_a) + Math.abs(vec3d3.field_72448_b) + Math.abs(vec3d3.field_72449_c));
            this.d.field_71446_o.func_110577_a(resourceLocation);
            GlStateManager.func_179094_E();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)this.b(al2, f11));
            GlStateManager.func_179137_b((double)vec3d3.field_72450_a, (double)(vec3d3.field_72448_b + this.a(resourceLocation)), (double)vec3d3.field_72449_c);
            GlStateManager.func_179114_b((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            float f12 = 1.4f + this.a(al2, f11);
            GlStateManager.func_179152_a((float)f12, (float)f12, (float)f12);
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferBuilder = tessellator.func_178180_c();
            bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            bufferBuilder.func_181662_b(-1.0, 0.0, 0.0).func_187315_a(0.0, 1.0).func_181675_d();
            bufferBuilder.func_181662_b(1.0, 0.0, 0.0).func_187315_a(1.0, 1.0).func_181675_d();
            bufferBuilder.func_181662_b(1.0, 2.0, 0.0).func_187315_a(1.0, 0.0).func_181675_d();
            bufferBuilder.func_181662_b(-1.0, 2.0, 0.0).func_187315_a(0.0, 0.0).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179121_F();
            GL11.glEnable((int)2896);
            GlStateManager.func_179118_c();
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)OpenGlHelper.lastBrightnessX, (float)OpenGlHelper.lastBrightnessY);
            long l2 = System.currentTimeMillis();
            try {
                try {
                    try {
                        if (this.k == f || resourceLocation != f) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.e.a(runtimeException);
                    }
                    if (l2 <= this.i + 60000L) break block6;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.e.a(runtimeException);
                }
                this.d.field_71439_g.func_184185_a(com.trolmastercard.sexmod.c.MISC_PYRO[0], 1.0f, 1.0f);
                this.i = l2;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.e.a(runtimeException);
            }
        }
        this.k = resourceLocation;
    }

    ResourceLocation a(al al2, double d10) {
        ResourceLocation resourceLocation;
        try {
            if (al2.a != -1) {
                return new ResourceLocation("sexmod", String.format("%s%s.png", e, this.b(al2)));
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        try {
            if (d10 < 3.0) {
                return f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        Vec3d vec3d = new Vec3d(al2.field_70142_S, al2.field_70137_T, al2.field_70136_U).func_178788_d(al2.func_174791_d());
        try {
            if (Math.abs(vec3d.field_72450_a) + Math.abs(vec3d.field_72448_b) + Math.abs(vec3d.field_72449_c) == 0.0) {
                return g;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        try {
            resourceLocation = Math.sin((float)this.d.field_71439_g.field_70173_aa * 0.75f) > 0.0 ? a : b;
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        return resourceLocation;
    }

    double a(ResourceLocation resourceLocation) {
        block4: {
            try {
                try {
                    if (a.equals((Object)resourceLocation) || b.equals((Object)resourceLocation)) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.e.a(runtimeException);
                }
                return 0.0;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.e.a(runtimeException);
            }
        }
        return Math.sin((float)this.d.field_71439_g.field_70173_aa * 0.75f) * (double)0.1f;
    }

    int b(al al2) {
        try {
            if (al2.a == -1) {
                return 0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        return (int)be.b(this.d.field_71439_g.field_70173_aa - al2.a, 1.0f, 30.0f);
    }

    float a(al al2, float f10) {
        try {
            if (al2.a == -1) {
                return 0.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        int n2 = this.b(al2);
        try {
            if (n2 == 30) {
                return 1.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        return ((float)n2 + f10) / 30.0f;
    }

    float b(al al2, float f10) {
        try {
            if (al2.a == -1) {
                return 1.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        try {
            if (this.d.field_71439_g.field_70173_aa - al2.a > 120) {
                return 0.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.e.a(runtimeException);
        }
        int n2 = 90;
        float f11 = be.b(this.d.field_71439_g.field_70173_aa - al2.a, n2, 120.0f) - (float)n2;
        float f12 = (f11 + f10) / 30.0f;
        return 1.0f - f12;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

