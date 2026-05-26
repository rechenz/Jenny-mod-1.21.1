/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.f_;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class bt
extends EntityAIAvoidEntity<EntityPlayer> {
    final f8 a;
    final float b;

    public bt(f8 f82, float f10, double d10, double d11) {
        super((EntityCreature)f82, EntityPlayer.class, f10, d10, d11);
        this.a = f82;
        this.b = f10;
    }

    boolean a() {
        try {
            if (this.a.v() != null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw bt.a(runtimeException);
        }
        BlockPos blockPos = this.a.func_180425_c();
        BlockPos blockPos2 = new BlockPos((double)this.b, (double)this.b, (double)this.b);
        List list = this.a.field_70170_p.func_72872_a(f_.class, new AxisAlignedBB(blockPos.func_177971_a((Vec3i)blockPos2), blockPos.func_177973_b((Vec3i)blockPos2)));
        for (f_ f_2 : list) {
            try {
                if (f_2.field_70170_p.field_72995_K) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw bt.a(runtimeException);
            }
            try {
                if (f_2.field_70128_L) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw bt.a(runtimeException);
            }
            try {
                if (!f_2.k()) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw bt.a(runtimeException);
            }
            return true;
        }
        return false;
    }

    public boolean func_75250_a() {
        try {
            if (this.a()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw bt.a(runtimeException);
        }
        return super.func_75250_a();
    }

    public boolean func_75253_b() {
        try {
            if (this.a()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw bt.a(runtimeException);
        }
        return super.func_75253_b();
    }

    public void func_75249_e() {
        this.a.func_184212_Q().func_187227_b(f8.ar, (Object)true);
        super.func_75249_e();
    }

    public void func_75251_c() {
        this.a.func_184212_Q().func_187227_b(f8.ar, (Object)false);
        super.func_75251_c();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

