/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3i;

public enum g5 {
    PURPLE(103, 39, 123),
    ORANGE(251, 153, 56),
    BLACK(30, 33, 38),
    BLUE(88, 83, 186),
    BROWN(63, 35, 34),
    PINK(247, 102, 109),
    RED(241, 69, 49),
    GREEN(75, 143, 106);

    private final Vec3i b;

    private g5(int n3, int n4, int n5) {
        this.b = new Vec3i(n3, n4, n5);
    }

    public Vec3i a() {
        return this.b;
    }

    public static int a(g5 g52) {
        int n2 = 0;
        for (g5 g53 : g5.values()) {
            try {
                if (g52 == g53) {
                    return n2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw g5.a(runtimeException);
            }
            ++n2;
        }
        return n2;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

