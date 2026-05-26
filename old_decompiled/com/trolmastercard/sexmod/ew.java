/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.gj;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ew
extends ei {
    public static final DataParameter<String> as = EntityDataManager.func_187226_a(ew.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(119);
    public static final DataParameter<BlockPos> au = EntityDataManager.func_187226_a(ew.class, (DataSerializer)DataSerializers.field_187200_j).func_187156_b().func_187161_a(120);
    public static final DataParameter<String> at = EntityDataManager.func_187226_a(ew.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(121);
    boolean ar = true;
    String ap = null;
    String av = null;
    BlockPos aq = null;

    protected ew(World world) {
        super(world);
    }

    protected ew(World world, UUID uUID) {
        super(world, uUID);
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
                    throw ew.d(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ew.d(runtimeException);
            }
        }
        this.m.func_187214_a(at, (Object)this.a(new StringBuilder()));
    }

    protected abstract String a(StringBuilder var1);

    public static String[] a(em em2) {
        return ((String)em2.func_184212_Q().func_187225_a(at)).split("-");
    }

    @Override
    public void func_70071_h_() {
        try {
            super.func_70071_h_();
            this.b();
            if (!this.ar) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ew.d(runtimeException);
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                this.a();
                this.ar = true;
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ew.d(runtimeException);
        }
        EntityPlayer entityPlayer = this.k();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ew.d(runtimeException);
        }
        String string = entityPlayer.getEntityData().func_74779_i("sexmod:GirlSpecific" + (Object)((Object)fy.a((Entity)this)));
        try {
            this.ar = false;
            if (!"".equals(string)) {
                this.a(ew.c(string));
            }
        }
        catch (RuntimeException runtimeException) {
            throw ew.d(runtimeException);
        }
    }

    void b() {
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
                throw ew.d(runtimeException);
            }
            string2 = (String)this.m.func_187225_a(as);
            string = (String)this.m.func_187225_a(at);
            blockPos = (BlockPos)this.m.func_187225_a(au);
            try {
                if (this.ap == null) {
                    this.ap = string2;
                    this.av = string;
                    this.aq = blockPos;
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ew.d(runtimeException);
            }
            try {
                block12: {
                    try {
                        try {
                            if (!this.av.equals(string) || !this.ap.equals(string2)) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ew.d(runtimeException);
                        }
                        if (this.aq.equals((Object)blockPos)) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ew.d(runtimeException);
                    }
                }
                this.a();
            }
            catch (RuntimeException runtimeException) {
                throw ew.d(runtimeException);
            }
        }
        this.ap = string2;
        this.av = string;
        this.aq = blockPos;
    }

    protected abstract void a();

    private static RuntimeException d(RuntimeException runtimeException) {
        return runtimeException;
    }
}

