/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3i;

public enum by {
    LIGHT_GREEN(213, 239, 150),
    MEDIUM_GREEN(189, 165, 91),
    DARK_GREEN(160, 183, 135),
    LIGHT_YELLOW(234, 176, 102),
    LIGHT_BLUE(187, 203, 252);

    private final Vec3i a;

    private by(int n3, int n4, int n5) {
        this.a = new Vec3i(n3, n4, n5);
    }

    public Vec3i a() {
        return this.a;
    }

    public static int a(by by2) {
        int n2 = 0;
        for (by by3 : by.values()) {
            try {
                if (by2 == by3) {
                    return n2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw by.a(runtimeException);
            }
            ++n2;
        }
        return n2;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

