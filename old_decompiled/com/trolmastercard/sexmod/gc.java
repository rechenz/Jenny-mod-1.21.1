/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3d
 */
package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3d;

public class gc {
    public static double a(Vec3d vec3d, Vec3d vec3d2) {
        double d10 = vec3d2.field_72450_a - vec3d.field_72450_a;
        double d11 = vec3d2.field_72448_b - vec3d.field_72448_b;
        double d12 = vec3d2.field_72449_c - vec3d.field_72449_c;
        return Math.atan2(d12, Math.sqrt(d10 * d10 + d11 * d11));
    }

    public static float b(float f10) {
        if ((f10 %= 360.0f) < 0.0f) {
            f10 += 360.0f;
        }
        return f10;
    }

    public static float a(float f10) {
        float f11;
        f10 %= 360.0f;
        try {
            float f12;
            f11 = f12 >= 0.0f ? f10 : f10 + 360.0f;
        }
        catch (RuntimeException runtimeException) {
            throw gc.a(runtimeException);
        }
        return f11;
    }

    public static double a(double d10) {
        double d11;
        d10 %= 360.0;
        try {
            double d12;
            d11 = d12 >= 0.0 ? d10 : d10 + 360.0;
        }
        catch (RuntimeException runtimeException) {
            throw gc.a(runtimeException);
        }
        return d11;
    }

    public static float c(float f10) {
        return (float)(Math.PI * 2 / (360.0 / (double)f10));
    }

    public static float c(double d10) {
        return (float)(Math.PI * 2 / (360.0 / d10));
    }

    public static float d(float f10) {
        return (float)(57.29577951308232 * (double)f10);
    }

    public static double b(double d10) {
        return 57.29577951308232 * d10;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

