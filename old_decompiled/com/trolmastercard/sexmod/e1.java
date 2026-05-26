/*
 * Decompiled with CFR 0.152.
 */
package com.trolmastercard.sexmod;

public class e1 {
    public static final e1 a = new e1(0, 0);
    public int c;
    public int b;

    public e1(int n2, int n3) {
        this.c = n2;
        this.b = n3;
    }

    public float a(int n2, int n3) {
        float f10 = n2 - this.c;
        float f11 = n3 - this.b;
        return (float)Math.sqrt(f10 * f10 + f11 * f11);
    }

    public String toString() {
        return String.format("(%s, %s)", this.c, this.b);
    }
}

