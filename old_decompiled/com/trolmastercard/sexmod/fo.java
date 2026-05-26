/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.world.World
 *  net.minecraftforge.items.ItemStackHandler
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public abstract class fo
extends em
implements IInventory {
    public static final DataParameter<Boolean> K = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(111);
    public ItemStackHandler L = new ItemStackHandler(27);

    protected fo(World world) {
        super(world);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.m.func_187214_a(K, (Object)false);
    }

    public int func_70302_i_() {
        return 27;
    }

    public boolean func_191420_l() {
        return false;
    }

    public ItemStack func_70301_a(int n2) {
        try {
            if (n2 >= this.L.getSlots()) {
                return ItemStack.field_190927_a;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fo.a(runtimeException);
        }
        return this.L.getStackInSlot(n2);
    }

    public ItemStack func_70298_a(int n2, int n3) {
        return this.L.extractItem(n2, n3, false);
    }

    public ItemStack func_70304_b(int n2) {
        return this.L.extractItem(n2, this.L.getStackInSlot(n2).func_190916_E(), false);
    }

    public void func_70299_a(int n2, ItemStack itemStack) {
        this.L.setStackInSlot(n2, itemStack);
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer entityPlayer) {
        return true;
    }

    public void func_174889_b(EntityPlayer entityPlayer) {
    }

    public void func_174886_c(EntityPlayer entityPlayer) {
    }

    public boolean func_94041_b(int n2, ItemStack itemStack) {
        return true;
    }

    public int func_174887_a_(int n2) {
        return n2;
    }

    public void func_174885_b(int n2, int n3) {
    }

    public int func_174890_g() {
        return 0;
    }

    public void func_174888_l() {
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

