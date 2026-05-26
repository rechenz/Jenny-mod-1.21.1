/*
 * Decompiled with CFR 0.152.
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import java.util.HashMap;
import java.util.UUID;

public class fs {
    static HashMap<UUID, em> a = new HashMap();

    public static void b(em em2) {
        a.put(em2.f(), em2);
    }

    public static void a(em em2) {
        a.remove(em2.f());
    }

    public static void a() {
        a.clear();
    }

    public static em a(UUID uUID) {
        return a.get(uUID);
    }
}

