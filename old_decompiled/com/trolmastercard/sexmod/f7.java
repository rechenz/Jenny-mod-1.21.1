/*
 * Decompiled with CFR 0.152.
 */
package com.trolmastercard.sexmod;

public class f7 {
    public static final f7 d = new f7(0.0f, 0.0f, 0.0f);
    public float a;
    public float c;
    public float b;

    public f7(float f10, float f11, float f12) {
        this.a = f10;
        this.c = f11;
        this.b = f12;
    }

    public f7 b(f7 f72) {
        return new f7(this.a - f72.a, this.c - f72.c, this.b - f72.b);
    }

    public f7 a(f7 f72) {
        return new f7(this.a + f72.a, this.c + f72.c, this.b + f72.b);
    }

    public f7 a(float f10) {
        return new f7(this.a * f10, this.c * f10, this.b * f10);
    }
}

