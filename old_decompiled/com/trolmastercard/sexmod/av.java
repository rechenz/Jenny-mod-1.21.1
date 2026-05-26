/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.as;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.cc;
import com.trolmastercard.sexmod.f7;
import com.trolmastercard.sexmod.v;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class av
extends GeoItemRenderer<cc> {
    public static final f7 e = new f7(0.84705883f, 0.11764706f, 0.35686275f);
    public static final f7 f = new f7(0.44705883f, 0.44705883f, 0.44705883f);
    public static final float b = 240.0f;
    public static final float g = 120.0f;
    static final float h = 0.05f;
    static final Minecraft a = Minecraft.func_71410_x();
    boolean c = false;
    f7 d;

    public av() {
        super(new as());
    }

    public void a(GeoModel geoModel, cc cc2, float f10, float f11, float f12, float f13, float f14) {
        GlStateManager.func_179129_p();
        GlStateManager.func_179091_B();
        BufferBuilder bufferBuilder = Tessellator.func_178181_a().func_178180_c();
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
        GeoBone geoBone = null;
        this.c = false;
        GeoBone geoBone2 = geoModel.topLevelBones.get(0);
        MATRIX_STACK.push();
        MATRIX_STACK.translate(geoBone2);
        MATRIX_STACK.moveToPivot(geoBone2);
        MATRIX_STACK.rotate(geoBone2);
        MATRIX_STACK.scale(geoBone2);
        MATRIX_STACK.moveBackFromPivot(geoBone2);
        for (GeoBone geoBone3 : geoBone2.childBones) {
            if ("pentagram".equals(geoBone3.getName())) {
                geoBone = geoBone3;
                continue;
            }
            this.renderRecursively(bufferBuilder, geoBone3, f11, f12, f13, f14);
        }
        Tessellator.func_178181_a().func_78381_a();
        float f15 = this.a(f10);
        try {
            this.d = this.a();
            if (!v.f) {
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)f15, (float)f15);
                GL11.glDisable((int)2896);
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        this.c = true;
        this.renderRecursively(bufferBuilder, geoBone, f11, f12, f13, f14);
        Tessellator.func_178181_a().func_78381_a();
        GL11.glEnable((int)2896);
        MATRIX_STACK.pop();
        GlStateManager.func_179101_C();
        GlStateManager.func_179089_o();
        GlStateManager.func_179117_G();
    }

    float a(float f10) {
        block13: {
            try {
                try {
                    if (av.a.field_71439_g.func_184614_ca() == this.currentItemStack || av.a.field_71439_g.func_184592_cb() == this.currentItemStack) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw av.a(runtimeException);
                }
                return this.b(f10);
            }
            catch (RuntimeException runtimeException) {
                throw av.a(runtimeException);
            }
        }
        long l2 = System.currentTimeMillis();
        NBTTagCompound nBTTagCompound = av.a.field_71439_g.getEntityData();
        long l3 = nBTTagCompound.func_74763_f("sexmod:galath_coin_activation_time");
        long l4 = nBTTagCompound.func_74763_f("sexmod:galath_coin_deactivation_time");
        try {
            if (l3 != 0L) {
                return this.a(l2, l3, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        try {
            if (l4 != 0L) {
                return this.b(l2, l4, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        try {
            if (v.f) {
                return 120.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        return this.b(f10);
    }

    float b(long l2, long l3, float f10) {
        float f11 = l2 - l3;
        try {
            if (f11 < 1000.0f) {
                return 120.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        try {
            if (f11 <= 3000.0f) {
                return b6.a(120.0f, 240.0f, (f11 - 1000.0f) / 2000.0f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        return 240.0f;
    }

    float a(long l2, long l3, float f10) {
        float f11 = l2 - l3;
        try {
            if (f11 < 1000.0f) {
                return 240.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        try {
            if (f11 <= 3000.0f) {
                return b6.a(240.0f, 120.0f, (f11 - 1000.0f) / 2000.0f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        return 120.0f;
    }

    f7 a() {
        block13: {
            try {
                try {
                    if (av.a.field_71439_g.func_184614_ca() == this.currentItemStack || av.a.field_71439_g.func_184592_cb() == this.currentItemStack) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw av.a(runtimeException);
                }
                return e;
            }
            catch (RuntimeException runtimeException) {
                throw av.a(runtimeException);
            }
        }
        long l2 = System.currentTimeMillis();
        NBTTagCompound nBTTagCompound = av.a.field_71439_g.getEntityData();
        long l3 = nBTTagCompound.func_74763_f("sexmod:galath_coin_activation_time");
        long l4 = nBTTagCompound.func_74763_f("sexmod:galath_coin_deactivation_time");
        try {
            if (l3 != 0L) {
                return this.b(l3, l2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        try {
            if (l4 != 0L) {
                return this.a(l4, l2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        try {
            if (v.f) {
                return f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        return e;
    }

    f7 a(long l2, long l3) {
        float f10 = l3 - l2;
        try {
            if (f10 < 1000.0f) {
                return f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        try {
            if (f10 <= 3000.0f) {
                return b6.a(f, e, (double)((f10 - 1000.0f) / 2000.0f));
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        return e;
    }

    f7 b(long l2, long l3) {
        float f10 = l3 - l2;
        try {
            if (f10 < 1000.0f) {
                return e;
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        try {
            if (f10 <= 3000.0f) {
                return b6.a(e, f, (double)((f10 - 1000.0f) / 2000.0f));
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        return f;
    }

    float b(float f10) {
        return (float)(60.0 * Math.sin(((float)av.a.field_71439_g.field_70173_aa + f10) * 0.05f) + 180.0);
    }

    void a(BufferBuilder bufferBuilder, GeoCube geoCube) {
        for (GeoQuad geoQuad : geoCube.quads) {
            try {
                if (geoQuad == null) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw av.a(runtimeException);
            }
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.func_181662_b((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).func_187315_a((double)geoVertex.textureU, (double)geoVertex.textureV).func_181666_a(this.d.a, this.d.c, this.d.b, 1.0f).func_181675_d();
            }
        }
    }

    @Override
    public void renderCube(BufferBuilder bufferBuilder, GeoCube geoCube, float f10, float f11, float f12, float f13) {
        try {
            MATRIX_STACK.moveToPivot(geoCube);
            MATRIX_STACK.rotate(geoCube);
            MATRIX_STACK.moveBackFromPivot(geoCube);
            if (this.c) {
                this.a(bufferBuilder, geoCube);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw av.a(runtimeException);
        }
        for (GeoQuad geoQuad : geoCube.quads) {
            Vector3f vector3f;
            block28: {
                block27: {
                    block26: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw av.a(runtimeException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.func_177958_n(), (float)geoQuad.normal.func_177956_o(), (float)geoQuad.normal.func_177952_p());
                        try {
                            try {
                                try {
                                    MATRIX_STACK.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block26;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw av.a(runtimeException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block26;
                            }
                            catch (RuntimeException runtimeException) {
                                throw av.a(runtimeException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (RuntimeException runtimeException) {
                            throw av.a(runtimeException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block27;
                            }
                            catch (RuntimeException runtimeException) {
                                throw av.a(runtimeException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block27;
                        }
                        catch (RuntimeException runtimeException) {
                            throw av.a(runtimeException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (RuntimeException runtimeException) {
                        throw av.a(runtimeException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block28;
                        }
                        catch (RuntimeException runtimeException) {
                            throw av.a(runtimeException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw av.a(runtimeException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw av.a(runtimeException);
                }
            }
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.func_181662_b((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).func_187315_a((double)geoVertex.textureU, (double)geoVertex.textureV).func_181666_a(f10, f11, f12, f13).func_181663_c(vector3f.getX(), vector3f.getY(), vector3f.getZ()).func_181675_d();
            }
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

