/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.gj;
import com.trolmastercard.sexmod.r;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class e4
extends em {
    public static final DataParameter<String> N = EntityDataManager.func_187226_a(e4.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(119);
    public static final DataParameter<BlockPos> K = EntityDataManager.func_187226_a(e4.class, (DataSerializer)DataSerializers.field_187200_j).func_187156_b().func_187161_a(120);
    public static final DataParameter<String> M = EntityDataManager.func_187226_a(e4.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(121);
    String P = null;
    String O = null;
    BlockPos L = null;

    protected e4(World world) {
        super(world);
    }

    @Override
    protected void func_70088_a() {
        block4: {
            try {
                try {
                    super.func_70088_a();
                    if (!this.field_70170_p.field_72995_K || !(this.field_70170_p instanceof gj)) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw e4.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw e4.a(runtimeException);
            }
        }
        this.m.func_187214_a(M, (Object)this.a(new StringBuilder()));
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        this.c();
    }

    void c() {
        BlockPos blockPos;
        String string;
        String string2;
        block13: {
            try {
                if (!this.field_70170_p.field_72995_K) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw e4.a(runtimeException);
            }
            string2 = (String)this.m.func_187225_a(N);
            string = (String)this.m.func_187225_a(M);
            blockPos = (BlockPos)this.m.func_187225_a(K);
            try {
                if (this.P == null) {
                    this.P = string2;
                    this.O = string;
                    this.L = blockPos;
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw e4.a(runtimeException);
            }
            try {
                block12: {
                    try {
                        try {
                            if (!this.O.equals(string) || !this.P.equals(string2)) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw e4.a(runtimeException);
                        }
                        if (this.L.equals((Object)blockPos)) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw e4.a(runtimeException);
                    }
                }
                this.a();
            }
            catch (RuntimeException runtimeException) {
                throw e4.a(runtimeException);
            }
        }
        this.P = string2;
        this.O = string;
        this.L = blockPos;
    }

    protected abstract void a();

    protected abstract String a(StringBuilder var1);

    public static void c(StringBuilder stringBuilder, int n2) {
        try {
            if (n2 < 10) {
                stringBuilder.append(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw e4.a(runtimeException);
        }
        stringBuilder.append(n2);
        stringBuilder.append("-");
    }

    public static void a(StringBuilder stringBuilder, int n2) {
        int n3 = com.trolmastercard.sexmod.r.f.nextInt(n2 + 1);
        try {
            if (n3 < 10) {
                stringBuilder.append(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw e4.a(runtimeException);
        }
        stringBuilder.append(n3);
        stringBuilder.append("-");
    }

    public static void b(StringBuilder stringBuilder) {
        double d10 = com.trolmastercard.sexmod.r.f.nextDouble();
        double d11 = Math.pow(Math.E, -Math.pow(-2.5 + 5.0 * d10, 2.0));
        String string = String.format("%.2f", d11);
        String[] stringArray = string.split("\\.");
        if (stringArray.length < 2) {
            stringArray = string.split(",");
        }
        string = stringArray[1];
        stringBuilder.append(string).append("-");
    }

    public static void b(StringBuilder stringBuilder, int n2) {
        int n3 = com.trolmastercard.sexmod.r.f.nextInt(n2);
        try {
            if (n3 < 10) {
                stringBuilder.append(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw e4.a(runtimeException);
        }
        stringBuilder.append(n3);
        stringBuilder.append("-");
    }

    public static String[] a(em em2) {
        return ((String)em2.func_184212_Q().func_187225_a(M)).split("-");
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

