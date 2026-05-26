/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3i;

public enum eh {
    RED(255, 0, 0),
    VIOLET(132, 30, 156),
    YELLOW(243, 247, 0),
    BROWN(105, 60, 9),
    TURKEY(0, 206, 217),
    BLUE(0, 0, 255);

    private final Vec3i b;

    private eh(int n3, int n4, int n5) {
        this.b = new Vec3i(n3, n4, n5);
    }

    public Vec3i a() {
        return this.b;
    }

    public static eh a(Vec3i vec3i) {
        for (eh eh2 : eh.values()) {
            try {
                if (!vec3i.equals((Object)eh2.a())) continue;
                return eh2;
            }
            catch (RuntimeException runtimeException) {
                throw eh.a(runtimeException);
            }
        }
        return RED;
    }

    public static int a(eh eh2) {
        int n2 = 0;
        for (eh eh3 : eh.values()) {
            try {
                if (eh2 == eh3) {
                    return n2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eh.a(runtimeException);
            }
            ++n2;
        }
        return n2;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

