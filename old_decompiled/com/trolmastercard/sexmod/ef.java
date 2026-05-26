/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.math.Vec3d
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.gv;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class ef {
    public static void a(BufferBuilder bufferBuilder, Tessellator tessellator, Minecraft minecraft, b b10) {
        Vec3d[] vec3dArray;
        int n2;
        Vec3d[] vec3dArray2 = new Vec3d[]{new Vec3d((double)(-b10.f), (double)(-b10.a), 0.0), new Vec3d((double)(-b10.f), (double)b10.a, 0.0), new Vec3d((double)b10.f, (double)b10.a, 0.0), new Vec3d((double)b10.f, (double)(-b10.a), 0.0)};
        Vec3d vec3d = new Vec3d(0.0, 0.0, (double)(-b10.g));
        Vec3d vec3d2 = ck.a(vec3d.func_72432_b(), (double)b10.e);
        Vec3d[] vec3dArray3 = new Vec3d[4];
        System.arraycopy(vec3dArray2, 0, vec3dArray3, 0, 4);
        ArrayList<Vec3d[]> arrayList = new ArrayList<Vec3d[]>();
        float f10 = (float)minecraft.field_71439_g.field_70173_aa + minecraft.func_184121_ak();
        for (n2 = 0; n2 <= b10.c; ++n2) {
            vec3dArray = new Vec3d[4];
            float f11 = 1.0f - (float)n2 / (float)b10.c;
            for (int i2 = 0; i2 < 4; ++i2) {
                Vec3d vec3d3 = vec3dArray2[i2];
                vec3dArray[i2] = new Vec3d(vec3d3.field_72450_a * (double)f11, vec3d3.field_72448_b, vec3d3.field_72449_c).func_178787_e(vec3d2);
            }
            arrayList.add(vec3dArray);
            vec3d = ck.a(vec3d, b10.i.a(n2, f10), b10.b.a(n2, f10), b10.d.a(n2, f10));
            vec3d2 = vec3d2.func_178787_e(vec3d);
        }
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        ef.a(bufferBuilder, vec3dArray3, (Vec3d[])arrayList.get(0), b10.h);
        for (n2 = 0; n2 < b10.c - 1; ++n2) {
            vec3dArray = (Vec3d[])arrayList.get(n2);
            Vec3d[] vec3dArray4 = (Vec3d[])arrayList.get(n2 + 1);
            ef.a(bufferBuilder, vec3dArray, vec3dArray4, b10.h);
        }
        tessellator.func_78381_a();
    }

    static float a(float f10, float f11, float f12, int n2, float f13) {
        return (float)(Math.sin(f10 * f11 + f12 * (float)n2) * (double)f13);
    }

    static void a(BufferBuilder bufferBuilder, Vec3d[] vec3dArray, Vec3d[] vec3dArray2, gv gv2) {
        bufferBuilder.func_181662_b(vec3dArray[1].field_72450_a, vec3dArray[1].field_72448_b, vec3dArray[1].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray[2].field_72450_a, vec3dArray[2].field_72448_b, vec3dArray[2].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray2[2].field_72450_a, vec3dArray2[2].field_72448_b, vec3dArray2[2].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray2[1].field_72450_a, vec3dArray2[1].field_72448_b, vec3dArray2[1].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray[0].field_72450_a, vec3dArray[0].field_72448_b, vec3dArray[0].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray[1].field_72450_a, vec3dArray[1].field_72448_b, vec3dArray[1].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray2[1].field_72450_a, vec3dArray2[1].field_72448_b, vec3dArray2[1].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray2[0].field_72450_a, vec3dArray2[0].field_72448_b, vec3dArray2[0].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray[2].field_72450_a, vec3dArray[2].field_72448_b, vec3dArray[2].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray[3].field_72450_a, vec3dArray[3].field_72448_b, vec3dArray[3].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray2[3].field_72450_a, vec3dArray2[3].field_72448_b, vec3dArray2[3].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray2[2].field_72450_a, vec3dArray2[2].field_72448_b, vec3dArray2[2].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray[0].field_72450_a, vec3dArray[0].field_72448_b, vec3dArray[0].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray[3].field_72450_a, vec3dArray[3].field_72448_b, vec3dArray[3].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray2[3].field_72450_a, vec3dArray2[3].field_72448_b, vec3dArray2[3].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
        bufferBuilder.func_181662_b(vec3dArray2[0].field_72450_a, vec3dArray2[0].field_72448_b, vec3dArray2[0].field_72449_c).func_181669_b(gv2.a, gv2.d, gv2.c, gv2.b).func_181675_d();
    }

    public static class b {
        public gv h;
        public float e;
        public int c;
        public float g;
        public a i;
        public a b;
        public a d;
        public float f;
        public float a;

        public b(gv gv2, float f10, int n2, float f11, a a10, a a11, a a12, float f12, float f13) {
            this.h = gv2;
            this.e = f10;
            this.c = n2;
            this.g = f11;
            this.i = a10;
            this.b = a11;
            this.d = a12;
            this.f = f12;
            this.a = f13;
        }

        public b a() {
            return new b(this.h, this.e, this.c, this.g, this.i, this.b, this.d, this.f, this.a);
        }
    }

    @FunctionalInterface
    public static interface a {
        public float a(int var1, float var2);
    }
}

