/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Vector2f
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4d
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.bc;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.e8;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.hy;
import com.trolmastercard.sexmod.p;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class fa
extends GeoItemRenderer<hy> {
    private static final ResourceLocation c = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
    private final e8 q = new e8();
    static final float p = 10.0f;
    static final float f = 1.5f;
    static final float m = 0.175f;
    static final float r = 0.1f;
    static final float g = 0.04f;
    static final float d = 8.0f;
    static final float i = 6.0f;
    static final float a = 1.3f;
    static final Vector2f[] l = new Vector2f[]{new Vector2f(1.0f, 0.0f), new Vector2f(0.0f, 1.0f), new Vector2f(0.0f, 0.0f), new Vector2f(0.5f, 0.5f), new Vector2f(0.75f, 0.25f), new Vector2f(0.25f, 0.75f), new Vector2f(0.25f, 0.75f)};
    static boolean o = false;
    Minecraft e = Minecraft.func_71410_x();
    Vector2f j;
    double b = 0.0;
    EntityPlayer k;
    ItemStack h;
    static HashMap<ItemStack, Vector3f> n = new HashMap();

    public fa() {
        super(new bc());
    }

    public static boolean b() {
        return o;
    }

    public static void a() {
        boolean bl2;
        try {
            bl2 = !o;
        }
        catch (RuntimeException runtimeException) {
            throw fa.a(runtimeException);
        }
        o = bl2;
    }

    public void a(hy hy2, ItemStack itemStack) {
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : this.e.field_71441_e.field_73010_i) {
            if (entityPlayer2.field_71071_by.field_70462_a.contains((Object)itemStack)) {
                entityPlayer = entityPlayer2;
                break;
            }
            if (!entityPlayer2.field_71071_by.field_184439_c.contains((Object)itemStack)) continue;
            entityPlayer = entityPlayer2;
            break;
        }
        if (entityPlayer != null) {
            double d10 = entityPlayer.field_70165_t - entityPlayer.field_70142_S;
            double d11 = entityPlayer.field_70161_v - entityPlayer.field_70136_U;
            double d12 = Math.PI / 180 * (double)entityPlayer.field_70177_z;
            this.j = new Vector2f((float)(d10 * Math.cos(d12) + d11 * Math.sin(d12)), (float)(-d10 * Math.sin(d12) + d11 * Math.cos(d12)));
        } else {
            this.j = new Vector2f(0.0f, 0.0f);
        }
        try {
            if (!Minecraft.func_71410_x().func_147113_T()) {
                this.b = (float)Minecraft.func_71410_x().field_71439_g.field_70173_aa + this.e.func_184121_ak();
            }
        }
        catch (RuntimeException runtimeException) {
            throw fa.a(runtimeException);
        }
        this.h = itemStack;
        this.k = entityPlayer;
        super.render(hy2, itemStack);
    }

    @Override
    public void renderRecursively(BufferBuilder bufferBuilder, GeoBone geoBone, float f10, float f11, float f12, float f13) {
        if ("staff".equals(geoBone.getName())) {
            float f14;
            float f15;
            Vector3f vector3f;
            Vector3f vector3f2;
            Vector3f vector3f3;
            GlStateManager.func_179094_E();
            Tessellator.func_178181_a().func_78381_a();
            com.trolmastercard.sexmod.p.a(IGeoRenderer.MATRIX_STACK, geoBone);
            GlStateManager.func_179137_b((double)0.0, (double)(1.5 + 0.001 * Math.sin(0.005 * this.b) + 0.001), (double)0.0);
            Vector3f vector3f4 = n.get(this.h);
            GlStateManager.func_179139_a((double)this.d(), (double)this.d(), (double)this.d());
            if (vector3f4 == null) {
                vector3f4 = new Vector3f(0.0f, 0.0f, 0.0f);
            }
            try {
                Vector3f vector3f5;
                vector3f3 = vector3f4;
                vector3f2 = vector3f5;
                vector3f = vector3f5;
                f15 = this.j.x;
                f14 = this.k == null ? 0.0f : (float)(this.k.field_70163_u - this.k.field_70137_T);
            }
            catch (RuntimeException runtimeException) {
                throw fa.a(runtimeException);
            }
            vector3f2(f15, f14, this.j.y);
            vector3f3.add((Tuple3f)vector3f);
            GlStateManager.func_179114_b((float)(vector3f4.z * 10.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)(vector3f4.x * 10.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)(-vector3f4.y * 10.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.func_179114_b((float)((float)(this.b * (double)0.1f)), (float)1.0f, (float)1.0f, (float)1.0f);
            n.put(this.h, vector3f4);
            this.e.func_110434_K().func_110577_a(c);
            this.q.func_78088_a((Entity)Minecraft.func_71410_x().field_71439_g, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            GlStateManager.func_179121_F();
            if (this.k != null) {
                this.c();
            }
            this.e.func_110434_K().func_110577_a(new bc().a(null));
            bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
        }
        super.renderRecursively(bufferBuilder, geoBone, f10, f11, f12, f13);
    }

    void c() {
        block7: {
            ArrayList<Integer> arrayList;
            block6: {
                arrayList = new ArrayList<Integer>();
                ArrayList<Vec3d> arrayList2 = new ArrayList<Vec3d>();
                for (Vector4d vector4d : ff.aY) {
                    arrayList.add((int)vector4d.getW());
                    arrayList2.add(new Vec3d(vector4d.getX(), vector4d.getY(), vector4d.getZ()));
                }
                try {
                    if (arrayList.size() == 0) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw fa.a(runtimeException);
                }
                try {
                    if (!o) break block6;
                    this.a(arrayList, arrayList2);
                    break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw fa.a(runtimeException);
                }
            }
            this.a(arrayList);
        }
    }

    void a(List<Integer> list, List<Vec3d> list2) {
        for (int i2 = 0; i2 < list.size(); ++i2) {
            float f10 = b6.a(this.k.field_70758_at, this.k.field_70759_as, this.e.func_184121_ak());
            float f11 = b6.a(this.k.field_70127_C, this.k.field_70125_A, this.e.func_184121_ak());
            Vec3d vec3d = b6.a(new Vec3d(this.k.field_70169_q, this.k.field_70167_r + (double)this.k.func_70047_e(), this.k.field_70166_s), this.k.func_174791_d().func_72441_c(0.0, (double)this.k.func_70047_e(), 0.0), (double)this.e.func_184121_ak());
            Vec3d vec3d2 = vec3d.func_178788_d(list2.get(i2));
            vec3d2 = ck.a(vec3d2, -f11, f10);
            double d10 = Math.abs(vec3d2.field_72450_a) + Math.abs(vec3d2.field_72449_c) + Math.abs(vec3d2.field_72448_b);
            double d11 = -vec3d2.field_72450_a / d10;
            double d12 = -vec3d2.field_72448_b / d10;
            double d13 = vec3d2.field_72449_c / d10;
            d11 = this.a(d11);
            d12 = this.a(d12);
            d13 = this.a(d13);
            this.b(list.get(i2), (float)(d11 *= (double)1.3f), (float)(d12 *= (double)1.3f), (float)(d13 *= (double)1.3f));
        }
    }

    void a(List<Integer> list) {
        float f10 = 1.0f / (float)list.size();
        float f11 = 0.0f;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            this.a(list.get(i2), 1.0f - (f11 += f10), 0.0f + f11, (float)b6.b((double)0.8f, (double)1.2f, (double)i2 / (double)list.size()));
        }
    }

    double a(double d10) {
        return d10 * Math.sqrt(1.0 - d10 * d10 / 2.0);
    }

    double d() {
        return (double)0.175f + 0.025 * Math.sin(0.005 * this.b) + 0.025;
    }

    void a(int n2, float f10, float f11, float f12) {
        this.a(new ItemStack(Blocks.field_150325_L, 1, n2), f10, f11, f12);
    }

    void b(int n2, float f10, float f11, float f12) {
        this.b(new ItemStack(Blocks.field_150325_L, 1, n2), f10, f11, f12);
    }

    void b(ItemStack itemStack, float f10, float f11, float f12) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)0.0, (double)(1.5 + 0.001 * Math.sin(0.005 * this.b) + 0.001), (double)0.0);
        GlStateManager.func_179152_a((float)0.04f, (float)0.04f, (float)0.04f);
        GlStateManager.func_179109_b((float)(f10 * 6.0f), (float)(f11 * 6.0f), (float)(f12 * 6.0f));
        this.e.func_175597_ag().func_178099_a((EntityLivingBase)Minecraft.func_71410_x().field_71439_g, itemStack, ItemCameraTransforms.TransformType.NONE);
        GlStateManager.func_179121_F();
    }

    void a(ItemStack itemStack, float f10, float f11, float f12) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)0.0, (double)(1.5 + 0.001 * Math.sin(0.005 * this.b) + 0.001), (double)0.0);
        GlStateManager.func_179152_a((float)0.04f, (float)0.04f, (float)0.04f);
        GlStateManager.func_179114_b((float)((float)(this.b * 8.0 * (double)f12)), (float)0.0f, (float)f10, (float)f11);
        GlStateManager.func_179109_b((float)6.0f, (float)0.0f, (float)0.0f);
        this.e.func_175597_ag().func_178099_a((EntityLivingBase)Minecraft.func_71410_x().field_71439_g, itemStack, ItemCameraTransforms.TransformType.NONE);
        GlStateManager.func_179121_F();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

