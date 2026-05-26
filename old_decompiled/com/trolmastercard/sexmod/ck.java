/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector3f
 *  net.minecraft.util.math.Vec3d
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.gc;
import javax.vecmath.Vector3f;
import net.minecraft.util.math.Vec3d;

public class ck {
    public static Vec3d a(Vec3d vec3d, double d10) {
        return new Vec3d(vec3d.field_72450_a * d10, vec3d.field_72448_b * d10, vec3d.field_72449_c * d10);
    }

    public static double a(Vector3f vector3f, Vec3d vec3d) {
        return (double)vector3f.x * vec3d.field_72450_a + (double)vector3f.y * vec3d.field_72448_b + (double)vector3f.z * vec3d.field_72449_c;
    }

    public static double a(Vec3d vec3d, Vec3d vec3d2) {
        return vec3d.field_72450_a * vec3d2.field_72450_a + vec3d.field_72448_b * vec3d2.field_72448_b + vec3d.field_72449_c * vec3d2.field_72449_c;
    }

    public static Vec3d b(Vec3d vec3d, Vec3d vec3d2) {
        return new Vec3d(vec3d.field_72448_b * vec3d2.field_72449_c - vec3d.field_72449_c * vec3d2.field_72448_b, vec3d.field_72449_c * vec3d2.field_72450_a - vec3d.field_72450_a * vec3d2.field_72449_c, vec3d.field_72450_a * vec3d2.field_72448_b - vec3d.field_72448_b * vec3d2.field_72450_a);
    }

    public static Vec3d a(double d10, double d11, double d12, float f10) {
        return ck.a(new Vec3d(d10, d11, d12), f10);
    }

    public static Vec3d a(Vec3d vec3d, float f10) {
        return ck.a(vec3d, 0.0f, f10);
    }

    public static Vec3d a(Vec3d vec3d, float f10, float f11) {
        Vec3d vec3d2 = new Vec3d(vec3d.field_72450_a, vec3d.field_72448_b * Math.cos((double)f10 * (Math.PI / 180)) - vec3d.field_72449_c * Math.sin((double)f10 * (Math.PI / 180)), vec3d.field_72448_b * Math.sin((double)f10 * (Math.PI / 180)) + vec3d.field_72449_c * Math.cos((double)f10 * (Math.PI / 180)));
        Vec3d vec3d3 = new Vec3d(-Math.sin((double)(f11 + 90.0f) * (Math.PI / 180)) * vec3d2.field_72450_a - Math.sin((double)f11 * (Math.PI / 180)) * vec3d2.field_72449_c, vec3d2.field_72448_b, Math.cos((double)(f11 + 90.0f) * (Math.PI / 180)) * vec3d2.field_72450_a + Math.cos((double)f11 * (Math.PI / 180)) * vec3d2.field_72449_c);
        return vec3d3;
    }

    public static Vec3d a(double d10, double d11, double d12, float f10, float f11) {
        return ck.a(new Vec3d(d10, d11, d12), f10, f11);
    }

    public static Vec3d a(Vec3d vec3d, float f10, float f11, float f12) {
        f10 = gc.c(f10);
        f11 = gc.c(f11);
        f12 = gc.c(f12);
        double d10 = (float)Math.sin(f10);
        double d11 = (float)Math.cos(f10);
        double d12 = (float)Math.sin(f11);
        double d13 = (float)Math.cos(f11);
        double d14 = (float)Math.sin(f12);
        double d15 = (float)Math.cos(f12);
        double d16 = vec3d.field_72448_b * d11 - vec3d.field_72449_c * d10;
        double d17 = vec3d.field_72448_b * d10 + vec3d.field_72449_c * d11;
        vec3d = new Vec3d(vec3d.field_72450_a, d16, d17);
        double d18 = vec3d.field_72450_a * d13 + vec3d.field_72449_c * d12;
        d17 = -vec3d.field_72450_a * d12 + vec3d.field_72449_c * d13;
        vec3d = new Vec3d(d18, vec3d.field_72448_b, d17);
        d18 = vec3d.field_72450_a * d15 - vec3d.field_72448_b * d14;
        d16 = vec3d.field_72450_a * d14 + vec3d.field_72448_b * d15;
        return new Vec3d(d18, d16, vec3d.field_72449_c);
    }

    public static Vec3d c(Vec3d vec3d) {
        return new Vec3d(-vec3d.field_72450_a, vec3d.field_72448_b, -vec3d.field_72449_c);
    }

    public static Vec3d a(Vec3d vec3d) {
        return new Vec3d(-vec3d.field_72450_a, -vec3d.field_72448_b, vec3d.field_72449_c);
    }

    public static Vec3d b(Vec3d vec3d) {
        return new Vec3d(vec3d.field_72450_a, -vec3d.field_72448_b, -vec3d.field_72449_c);
    }

    static double a(double d10, double d11, double d12) {
        return (d12 - d10) / (d11 - d10);
    }

    public static double a(Vec3d vec3d, Vec3d vec3d2, Vec3d vec3d3) {
        return ck.a(vec3d.field_72450_a, vec3d2.field_72450_a, vec3d3.field_72450_a);
    }
}

