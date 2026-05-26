/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class bx
extends Container {
    private final IInventory a;
    private final int d;
    public static List<bx> b = new ArrayList<bx>();
    public UUID c;

    public bx(IInventory iInventory, IInventory iInventory2, EntityPlayer entityPlayer, UUID uUID) {
        int n2;
        int n3;
        this.c = uUID;
        b.add(this);
        this.a = iInventory2;
        iInventory2.func_174889_b(entityPlayer);
        this.d = 3;
        int n4 = -18;
        for (n3 = 0; n3 < 3; ++n3) {
            try {
                for (n2 = 0; n2 < 9; ++n2) {
                    this.func_75146_a(new Slot(iInventory2, n2 + n3 * 9, 8 + n2 * 18, 18 + n3 * 18));
                }
                continue;
            }
            catch (RuntimeException runtimeException) {
                throw bx.a(runtimeException);
            }
        }
        for (n3 = 0; n3 < 3; ++n3) {
            try {
                for (n2 = 0; n2 < 9; ++n2) {
                    this.func_75146_a(new Slot(iInventory, n2 + n3 * 9 + 9, 8 + n2 * 18, 103 + n3 * 18 + n4));
                }
                continue;
            }
            catch (RuntimeException runtimeException) {
                throw bx.a(runtimeException);
            }
        }
        try {
            for (n3 = 0; n3 < 9; ++n3) {
                this.func_75146_a(new Slot(iInventory, n3, 8 + n3 * 18, 161 + n4));
            }
        }
        catch (RuntimeException runtimeException) {
            throw bx.a(runtimeException);
        }
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.a.func_70300_a(entityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer entityPlayer, int n2) {
        ItemStack itemStack;
        block11: {
            Slot slot;
            block14: {
                ItemStack itemStack2;
                block13: {
                    block12: {
                        itemStack = ItemStack.field_190927_a;
                        slot = (Slot)this.field_75151_b.get(n2);
                        try {
                            if (slot == null || !slot.func_75216_d()) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw bx.a(runtimeException);
                        }
                        itemStack2 = slot.func_75211_c();
                        itemStack = itemStack2.func_77946_l();
                        try {
                            try {
                                if (n2 >= this.d * 9) break block12;
                                if (this.func_75135_a(itemStack2, this.d * 9, this.field_75151_b.size(), true)) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw bx.a(runtimeException);
                            }
                            return ItemStack.field_190927_a;
                        }
                        catch (RuntimeException runtimeException) {
                            throw bx.a(runtimeException);
                        }
                    }
                    try {
                        if (!this.func_75135_a(itemStack2, 0, this.d * 9, false)) {
                            return ItemStack.field_190927_a;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw bx.a(runtimeException);
                    }
                }
                try {
                    if (!itemStack2.func_190926_b()) break block14;
                    slot.func_75215_d(ItemStack.field_190927_a);
                    break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw bx.a(runtimeException);
                }
            }
            slot.func_75218_e();
        }
        return itemStack;
    }

    public void func_75134_a(EntityPlayer entityPlayer) {
        super.func_75134_a(entityPlayer);
        this.a.func_174886_c(entityPlayer);
    }

    public IInventory a() {
        return this.a;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

