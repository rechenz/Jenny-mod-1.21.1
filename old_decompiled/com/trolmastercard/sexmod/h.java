/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f;
import net.minecraft.entity.Entity;

public class h
extends f {
    int j = 0;
    int i = 0;

    public h(em em2) {
        super(em2);
    }

    @Override
    public void func_75251_c() {
        super.func_75251_c();
        this.d.field_70747_aH = 0.02f;
    }

    @Override
    protected f.a a() {
        boolean bl2;
        block11: {
            block12: {
                boolean bl3;
                float f10 = this.d.func_70032_d((Entity)this.a);
                try {
                    bl3 = f10 > 5.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.h.a(runtimeException);
                }
                bl2 = bl3;
                try {
                    try {
                        try {
                            if (this.d.ae() != null || bl2) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.h.a(runtimeException);
                        }
                        if (this.f != f.a.FOLLOW) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.h.a(runtimeException);
                    }
                    if (++this.j <= 60) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.h.a(runtimeException);
                }
                bl2 = false;
                this.j = 0;
                break block11;
            }
            bl2 = true;
        }
        try {
            if (bl2) {
                return f.a.FOLLOW;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.h.a(runtimeException);
        }
        return f.a.IDLE;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    protected void a(f.a var1_1) {
        switch (com.trolmastercard.sexmod.h$a.a[var1_1.ordinal()]) {
            case 1: {
                var2_2 = this.d.func_70032_d((Entity)this.a);
                try {
                    if (!((double)this.c.func_111269_d() > var2_2)) ** GOTO lbl12
                    this.c.func_75499_g();
                    this.c.func_75497_a((Entity)this.a, 0.5);
                    ** GOTO lbl13
                }
                catch (RuntimeException v0) {
                    throw com.trolmastercard.sexmod.h.a(v0);
                }
lbl12:
                // 1 sources

                this.c();
lbl13:
                // 2 sources

                this.i = 300;
                this.b();
                break;
            }
            case 2: {
                this.b();
            }
        }
    }

    @Override
    protected double b() {
        float f10 = this.d.func_70032_d((Entity)this.a);
        float f11 = 0.02f;
        double d10 = Math.min(0.7, Math.floor(f10 / 3.0f) * 0.05);
        this.d.field_70747_aH = f11 = (float)((double)f11 + d10);
        return f11;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

