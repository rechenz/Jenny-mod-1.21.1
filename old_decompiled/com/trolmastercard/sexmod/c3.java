/*
 * Decompiled with CFR 0.152.
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.gx;
import java.util.HashSet;
import software.bernie.geckolib3.geo.render.built.GeoBone;

public interface c3 {
    default public HashSet<String> a() {
        return gx.a;
    }

    default public boolean a(HashSet<String> hashSet, GeoBone geoBone) {
        while (geoBone.parent != null) {
            String string = geoBone.getName();
            try {
                if (hashSet.contains(string)) {
                    return false;
                }
            }
            catch (RuntimeException runtimeException) {
                throw c3.b(runtimeException);
            }
            try {
                if (string.startsWith("armor")) {
                    return false;
                }
            }
            catch (RuntimeException runtimeException) {
                throw c3.b(runtimeException);
            }
            geoBone = geoBone.parent;
        }
        return true;
    }

    private static RuntimeException b(RuntimeException runtimeException) {
        return runtimeException;
    }
}

