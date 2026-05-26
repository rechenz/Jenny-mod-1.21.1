/*
 * Decompiled with CFR 0.152.
 */
package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class gl<K, V> {
    private final HashMap<K, V> b = new HashMap();
    private final HashMap<V, K> a = new HashMap();

    public void a(K k2, V v2) {
        V v3 = this.b.put(k2, v2);
        this.a.remove(v3);
        this.a.put(v2, k2);
    }

    public V c(K k2) {
        return this.b.get(k2);
    }

    public K b(V v2) {
        return this.a.get(v2);
    }

    public int e() {
        return this.b.size();
    }

    public void a(K k2) {
        V v2 = this.b.get(k2);
        try {
            if (v2 != null) {
                this.b.remove(k2);
                this.a.remove(v2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw gl.a(runtimeException);
        }
    }

    public Set<Map.Entry<K, V>> c() {
        return this.b.entrySet();
    }

    public Set<K> a() {
        return this.b.keySet();
    }

    public Set<V> d() {
        return this.a.keySet();
    }

    public void b() {
        this.a.clear();
        this.b.clear();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

