/*
 * Decompiled with CFR 0.152.
 */
package com.trolmastercard.sexmod;

public class g8 {
    public double b;
    public double a;

    public g8(double d10, double d11) {
        this.b = d10;
        this.a = d11;
    }

    public g8 a(g8 g82) {
        return new g8(this.b - g82.b, this.a - g82.a);
    }
}

