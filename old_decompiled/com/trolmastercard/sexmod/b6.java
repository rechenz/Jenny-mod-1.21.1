/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.f7;
import com.trolmastercard.sexmod.gv;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class b6 {
    public static Vec3d a(Vec3d vec3d, Vec3d vec3d2, int n2) {
        try {
            if (n2 == 0) {
                return vec3d2;
            }
        }
        catch (RuntimeException runtimeException) {
            throw b6.a(runtimeException);
        }
        Vec3d vec3d3 = vec3d2.func_178788_d(vec3d);
        return vec3d.func_72441_c(vec3d3.field_72450_a / (double)n2, vec3d3.field_72448_b / (double)n2, vec3d3.field_72449_c / (double)n2);
    }

    public static double b(double d10, double d11, double d12) {
        return d10 + (d11 - d10) * d12;
    }

    public static float a(float f10, float f11, float f12) {
        return f10 + (f11 - f10) * f12;
    }

    public static float a(float f10, float f11, double d10) {
        float f12 = f11 - f10;
        while ((double)f12 < -Math.PI) {
            f12 = (float)((double)f12 + Math.PI * 2);
        }
        while ((double)f12 >= Math.PI) {
            f12 = (float)((double)f12 - Math.PI * 2);
        }
        return (float)((double)f10 + (double)f12 * d10);
    }

    public static float b(float f10, float f11, double d10) {
        double d11 = Math.toRadians(f10);
        double d12 = Math.toRadians(f11);
        return (float)Math.toDegrees(b6.a((float)d11, (float)d12, d10));
    }

    public static Vec3d a(Vec3d vec3d, Vec3d vec3d2, double d10) {
        Vec3d vec3d3 = vec3d2.func_178788_d(vec3d);
        return vec3d.func_178787_e(new Vec3d(vec3d3.field_72450_a * d10, vec3d3.field_72448_b * d10, vec3d3.field_72449_c * d10));
    }

    public static f7 a(f7 f72, f7 f73, double d10) {
        f7 f74 = f73.b(f72);
        return f72.a(f74.a((float)d10));
    }

    public static Vec3i a(Vec3i vec3i, Vec3i vec3i2, double d10) {
        Vec3d vec3d = new Vec3d((double)(vec3i2.func_177958_n() - vec3i.func_177958_n()), (double)(vec3i2.func_177956_o() - vec3i.func_177956_o()), (double)(vec3i2.func_177952_p() - vec3i.func_177952_p()));
        return new Vec3i((double)vec3i.func_177958_n() + vec3d.field_72450_a * d10, (double)vec3i.func_177956_o() + vec3d.field_72448_b * d10, (double)vec3i.func_177952_p() + vec3d.field_72449_c * d10);
    }

    public static gv a(gv gv2, gv gv3, double d10) {
        gv gv4 = new gv(gv3.a - gv2.a, gv3.d - gv2.d, gv3.c - gv2.c, gv3.b - gv2.b);
        return new gv((int)((double)gv2.a + (double)gv4.a * d10), (int)((double)gv2.d + (double)gv4.d * d10), (int)((double)gv2.c + (double)gv4.c * d10), (int)((double)gv2.b + (double)gv4.b * d10));
    }

    public static double e(double d10) {
        return 1.0 - Math.pow(1.0 - d10, 4.0);
    }

    public static double g(double d10) {
        return 1.0 - Math.pow(1.0 - d10, 3.0);
    }

    public static double c(double d10) {
        double d11 = 1.70158;
        double d12 = d11 + 1.0;
        return 1.0 + d12 * Math.pow(d10 - 1.0, 3.0) + d11 * Math.pow(d10 - 1.0, 2.0);
    }

    public static double d(double d10) {
        double d11 = 1.70158;
        double d12 = d11 + 1.0;
        return d12 * d10 * d10 * d10 - d11 * d10 * d10;
    }

    public static double b(double d10) {
        return Math.sin(d10 * Math.PI / 2.0);
    }

    public static double a(double d10) {
        return d10 * d10 * d10;
    }

    public static double h(double d10) {
        return -(Math.cos(Math.PI * d10) - 1.0) / 2.0;
    }

    public static double f(double d10) {
        return 1.0 - Math.cos(Math.PI * d10 / 2.0);
    }

    public static double a(double d10, double d11, double d12) {
        double d13 = (1.0 - Math.cos(d12 * Math.PI)) / 2.0;
        return d10 * (1.0 - d13) + d11 * d13;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

