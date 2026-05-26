/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.ParticleDragonBreath
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import net.minecraft.client.particle.ParticleDragonBreath;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ez
extends ParticleDragonBreath {
    public static final float a = 0.2f;
    public static final float c = 0.5f;
    public static float b = 0.2f;

    public ez(World world, double d10, double d11, double d12) {
        super(world, d10, d11, d12, 0.0, 0.0, 0.0);
    }

    public void func_180434_a(BufferBuilder bufferBuilder, Entity entity, float f10, float f11, float f12, float f13, float f14, float f15) {
        this.field_70544_f = b;
        float f16 = (float)this.field_94054_b / 16.0f;
        float f17 = f16 + 0.0624375f;
        float f18 = (float)this.field_94055_c / 16.0f;
        float f19 = f18 + 0.0624375f;
        float f20 = 0.1f * this.field_70544_f;
        if (this.field_187119_C != null) {
            f16 = this.field_187119_C.func_94209_e();
            f17 = this.field_187119_C.func_94212_f();
            f18 = this.field_187119_C.func_94206_g();
            f19 = this.field_187119_C.func_94210_h();
        }
        float f21 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * (double)f10 - field_70556_an);
        float f22 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * (double)f10 - field_70554_ao);
        float f23 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * (double)f10 - field_70555_ap);
        int n2 = this.func_189214_a(f10);
        int n3 = n2 >> 16 & 0xFFFF;
        int n4 = n2 & 0xFFFF;
        Vec3d[] vec3dArray = new Vec3d[]{new Vec3d((double)(-f11 * f20 - f14 * f20), (double)(-f12 * f20), (double)(-f13 * f20 - f15 * f20)), new Vec3d((double)(-f11 * f20 + f14 * f20), (double)(f12 * f20), (double)(-f13 * f20 + f15 * f20)), new Vec3d((double)(f11 * f20 + f14 * f20), (double)(f12 * f20), (double)(f13 * f20 + f15 * f20)), new Vec3d((double)(f11 * f20 - f14 * f20), (double)(-f12 * f20), (double)(f13 * f20 - f15 * f20))};
        if (this.field_190014_F != 0.0f) {
            float f24 = this.field_190014_F + (this.field_190014_F - this.field_190015_G) * f10;
            float f25 = MathHelper.func_76134_b((float)(f24 * 0.5f));
            float f26 = MathHelper.func_76126_a((float)(f24 * 0.5f)) * (float)ez.field_190016_K.field_72450_a;
            float f27 = MathHelper.func_76126_a((float)(f24 * 0.5f)) * (float)ez.field_190016_K.field_72448_b;
            float f28 = MathHelper.func_76126_a((float)(f24 * 0.5f)) * (float)ez.field_190016_K.field_72449_c;
            Vec3d vec3d = new Vec3d((double)f26, (double)f27, (double)f28);
            try {
                for (int i2 = 0; i2 < 4; ++i2) {
                    vec3dArray[i2] = vec3d.func_186678_a(2.0 * vec3dArray[i2].func_72430_b(vec3d)).func_178787_e(vec3dArray[i2].func_186678_a((double)(f25 * f25) - vec3d.func_72430_b(vec3d))).func_178787_e(vec3d.func_72431_c(vec3dArray[i2]).func_186678_a((double)(2.0f * f25)));
                }
            }
            catch (RuntimeException runtimeException) {
                throw ez.a(runtimeException);
            }
        }
        bufferBuilder.func_181662_b((double)f21 + vec3dArray[0].field_72450_a, (double)f22 + vec3dArray[0].field_72448_b, (double)f23 + vec3dArray[0].field_72449_c).func_187315_a((double)f17, (double)f19).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(n3, n4).func_181675_d();
        bufferBuilder.func_181662_b((double)f21 + vec3dArray[1].field_72450_a, (double)f22 + vec3dArray[1].field_72448_b, (double)f23 + vec3dArray[1].field_72449_c).func_187315_a((double)f17, (double)f18).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(n3, n4).func_181675_d();
        bufferBuilder.func_181662_b((double)f21 + vec3dArray[2].field_72450_a, (double)f22 + vec3dArray[2].field_72448_b, (double)f23 + vec3dArray[2].field_72449_c).func_187315_a((double)f16, (double)f18).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(n3, n4).func_181675_d();
        bufferBuilder.func_181662_b((double)f21 + vec3dArray[3].field_72450_a, (double)f22 + vec3dArray[3].field_72448_b, (double)f23 + vec3dArray[3].field_72449_c).func_187315_a((double)f16, (double)f19).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(n3, n4).func_181675_d();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

