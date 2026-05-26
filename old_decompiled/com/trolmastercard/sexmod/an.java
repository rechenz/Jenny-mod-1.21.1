/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class an {
    public static final float a = 9.81f;
    public static final float g = 0.05f;
    public static final float b = 0.05f;
    public static final float c = 0.03f;
    World h;
    Vec3d d;
    Vec3d f;
    Vec3d e;

    public an(World world, Vec3d vec3d, Vec3d vec3d2) {
        this.h = world;
        this.f = vec3d;
        this.d = vec3d;
        this.e = vec3d2;
    }

    public void a() {
        int n2;
        int n3;
        try {
            if (Vec3d.field_186680_a.equals((Object)this.e)) {
                this.d = this.f;
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw an.a(runtimeException);
        }
        this.e = new Vec3d(this.e.field_72450_a * (double)0.95f, (this.e.field_72448_b - 0.4905000329017639) * (double)0.95f, this.e.field_72449_c * (double)0.95f);
        this.d = this.f;
        this.f = new Vec3d(this.f.field_72450_a + this.e.field_72450_a * (double)0.05f, this.f.field_72448_b + this.e.field_72448_b * (double)0.05f, this.f.field_72449_c + this.e.field_72449_c * (double)0.05f);
        BlockPos blockPos = new BlockPos(this.d);
        BlockPos blockPos2 = null;
        for (BlockPos blockPos3 : an.a(new BlockPos(this.d), new BlockPos(this.f))) {
            if (this.h.func_180495_p(blockPos3).func_177230_c() == Blocks.field_150350_a) {
                blockPos = blockPos3;
                continue;
            }
            blockPos2 = blockPos3;
            break;
        }
        try {
            if (blockPos2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw an.a(runtimeException);
        }
        int n4 = blockPos2.func_177958_n();
        int n5 = blockPos.func_177958_n();
        if (n4 - n5 != 0) {
            int n6;
            float f10;
            double d10;
            Vec3d vec3d;
            Vec3d vec3d2;
            double d11 = Math.max(n4, n5);
            double d12 = (this.d.field_72448_b - this.f.field_72448_b) / (this.d.field_72450_a - this.f.field_72450_a);
            double d13 = this.f.field_72448_b - d12 * this.f.field_72450_a;
            double d14 = d12 * d11 + d13;
            double d15 = (this.d.field_72449_c - this.f.field_72449_c) / (this.d.field_72450_a - this.f.field_72450_a);
            double d16 = this.f.field_72449_c - d15 * this.f.field_72450_a;
            double d17 = d15 * d11 + d16;
            try {
                Vec3d vec3d3;
                an an2 = this;
                vec3d2 = vec3d3;
                vec3d = vec3d3;
                d10 = d11;
                f10 = 0.03f;
                n6 = n4 > n5 ? -1 : 1;
            }
            catch (RuntimeException runtimeException) {
                throw an.a(runtimeException);
            }
            vec3d2(d10 + (double)(f10 * (float)n6), d14, d17);
            an2.f = vec3d;
            this.e = new Vec3d(0.0, 0.0, 0.0);
            return;
        }
        int n7 = blockPos2.func_177956_o();
        if (n7 - (n3 = blockPos.func_177956_o()) != 0) {
            int n8;
            float f11;
            double d18;
            double d19;
            Vec3d vec3d;
            Vec3d vec3d4;
            double d20 = Math.max(n7, n3);
            double d21 = (this.d.field_72450_a - this.f.field_72450_a) / (this.d.field_72448_b - this.f.field_72448_b);
            double d22 = this.f.field_72450_a - d21 * this.f.field_72448_b;
            double d23 = d21 * d20 + d22;
            double d24 = (this.d.field_72449_c - this.f.field_72449_c) / (this.d.field_72448_b - this.f.field_72448_b);
            double d25 = this.f.field_72449_c - d24 * this.f.field_72448_b;
            double d26 = d24 * d20 + d25;
            try {
                Vec3d vec3d5;
                an an3 = this;
                vec3d4 = vec3d5;
                vec3d = vec3d5;
                d19 = d23;
                d18 = d20;
                f11 = 0.03f;
                n8 = n7 > n3 ? -1 : 1;
            }
            catch (RuntimeException runtimeException) {
                throw an.a(runtimeException);
            }
            vec3d4(d19, d18 + (double)(f11 * (float)n8), d26);
            an3.f = vec3d;
            this.e = new Vec3d(0.0, 0.0, 0.0);
            return;
        }
        int n9 = blockPos2.func_177952_p();
        if (n9 - (n2 = blockPos.func_177952_p()) != 0) {
            int n10;
            float f12;
            double d27;
            double d28;
            double d29;
            Vec3d vec3d;
            Vec3d vec3d6;
            double d30 = Math.max(n9, n2);
            double d31 = (this.d.field_72448_b - this.f.field_72448_b) / (this.d.field_72449_c - this.f.field_72449_c);
            double d32 = this.f.field_72448_b - d31 * this.f.field_72449_c;
            double d33 = d31 * d30 + d32;
            double d34 = (this.d.field_72450_a - this.f.field_72450_a) / (this.d.field_72449_c - this.f.field_72449_c);
            double d35 = this.f.field_72450_a - d34 * this.f.field_72449_c;
            double d36 = d34 * d30 + d35;
            try {
                Vec3d vec3d7;
                an an4 = this;
                vec3d6 = vec3d7;
                vec3d = vec3d7;
                d29 = d36;
                d28 = d33;
                d27 = d30;
                f12 = 0.03f;
                n10 = n9 > n2 ? -1 : 1;
            }
            catch (RuntimeException runtimeException) {
                throw an.a(runtimeException);
            }
            vec3d6(d29, d28, d27 + (double)(f12 * (float)n10));
            an4.f = vec3d;
            this.e = new Vec3d(0.0, 0.0, 0.0);
            return;
        }
    }

    static List<BlockPos> a(BlockPos blockPos, BlockPos blockPos2) {
        int n2;
        int n3;
        int n4;
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        arrayList.add(blockPos);
        int n5 = blockPos.func_177958_n();
        int n6 = blockPos.func_177956_o();
        int n7 = blockPos.func_177952_p();
        int n8 = blockPos2.func_177958_n();
        int n9 = blockPos2.func_177956_o();
        int n10 = blockPos2.func_177952_p();
        int n11 = Math.abs(n8 - n5);
        int n12 = Math.abs(n9 - n6);
        int n13 = Math.abs(n10 - n7);
        try {
            n4 = n5 < n8 ? 1 : -1;
        }
        catch (RuntimeException runtimeException) {
            throw an.a(runtimeException);
        }
        int n14 = n4;
        try {
            n3 = n6 < n9 ? 1 : -1;
        }
        catch (RuntimeException runtimeException) {
            throw an.a(runtimeException);
        }
        int n15 = n3;
        try {
            n2 = n7 < n10 ? 1 : -1;
        }
        catch (RuntimeException runtimeException) {
            throw an.a(runtimeException);
        }
        int n16 = n2;
        int n17 = Math.max(n11, Math.max(n12, n13));
        int n18 = n5;
        int n19 = n6;
        int n20 = n7;
        int n21 = n17 / 2;
        int n22 = n17 / 2;
        int n23 = n17 / 2;
        for (int i2 = 0; i2 < n17; ++i2) {
            arrayList.add(new BlockPos(n18, n19, n20));
            n22 -= n12;
            n23 -= n13;
            if ((n21 -= n11) < 0) {
                n18 += n14;
                n21 += n17;
                continue;
            }
            if (n22 < 0) {
                n19 += n15;
                n22 += n17;
                continue;
            }
            if (n23 >= 0) continue;
            n20 += n16;
            n23 += n17;
        }
        arrayList.add(blockPos2);
        return arrayList;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

