/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.items.CapabilityItemHandler
 *  net.minecraftforge.items.IItemHandler
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.fe;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ca
extends Container {
    eb d;
    public Slot[] b;
    public UUID a;
    public static List<ca> c = new ArrayList<ca>();

    public ca(eb eb2, InventoryPlayer inventoryPlayer, UUID uUID) {
        this.a = uUID;
        c.add(this);
        if (eb2.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
            int n2;
            IItemHandler iItemHandler = (IItemHandler)eb2.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
            this.d = eb2;
            this.b = new Slot[]{new fe(fe.b.WEAPON, iItemHandler, fe.b.WEAPON.id, 41, 60), new fe(fe.b.BOW, iItemHandler, fe.b.BOW.id, 59, 60), new fe(fe.b.HELMET, iItemHandler, fe.b.HELMET.id, 81, 60), new fe(fe.b.CHEST_PLATE, iItemHandler, fe.b.CHEST_PLATE.id, 100, 60), new fe(fe.b.PANTS, iItemHandler, fe.b.PANTS.id, 119, 60), new fe(fe.b.SHOES, iItemHandler, fe.b.SHOES.id, 138, 60), new fe(fe.b.ROD, iItemHandler, fe.b.ROD.id, 22, 60)};
            ArrayList<Slot> arrayList = new ArrayList<Slot>();
            for (n2 = 0; n2 < 3; ++n2) {
                try {
                    for (int i2 = 0; i2 < 9; ++i2) {
                        arrayList.add(new Slot((IInventory)inventoryPlayer, i2 + n2 * 9 + 9, 8 + i2 * 18, 84 + n2 * 18));
                    }
                    continue;
                }
                catch (RuntimeException runtimeException) {
                    throw ca.a(runtimeException);
                }
            }
            try {
                for (n2 = 0; n2 < 9; ++n2) {
                    arrayList.add(new Slot((IInventory)inventoryPlayer, n2, 8 + n2 * 18, 142));
                }
            }
            catch (RuntimeException runtimeException) {
                throw ca.a(runtimeException);
            }
            for (Slot slot : this.b) {
                this.func_75146_a(slot);
            }
            for (Slot slot : arrayList) {
                this.func_75146_a(slot);
            }
        }
    }

    public ItemStack func_82846_b(EntityPlayer entityPlayer, int n2) {
        ItemStack itemStack;
        block11: {
            ItemStack itemStack2;
            Slot slot;
            block15: {
                block14: {
                    block13: {
                        int n3;
                        block12: {
                            itemStack = ItemStack.field_190927_a;
                            slot = (Slot)this.field_75151_b.get(n2);
                            try {
                                if (slot == null || !slot.func_75216_d()) break block11;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ca.a(runtimeException);
                            }
                            itemStack2 = slot.func_75211_c();
                            itemStack = itemStack2.func_77946_l();
                            n3 = this.field_75151_b.size() - entityPlayer.field_71071_by.field_70462_a.size();
                            try {
                                try {
                                    if (n2 >= n3) break block12;
                                    if (this.func_75135_a(itemStack2, n3, this.field_75151_b.size(), true)) break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ca.a(runtimeException);
                                }
                                return ItemStack.field_190927_a;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ca.a(runtimeException);
                            }
                        }
                        try {
                            if (!this.func_75135_a(itemStack2, 0, n3, false)) {
                                return ItemStack.field_190927_a;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ca.a(runtimeException);
                        }
                    }
                    try {
                        if (itemStack2.func_190916_E() != 0) break block14;
                        slot.func_75215_d(ItemStack.field_190927_a);
                        break block15;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ca.a(runtimeException);
                    }
                }
                slot.func_75218_e();
            }
            slot.func_190901_a(entityPlayer, itemStack2);
        }
        return itemStack;
    }

    public void func_75141_a(int n2, ItemStack itemStack) {
        super.func_75141_a(n2, itemStack);
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return true;
    }

    public void func_75134_a(EntityPlayer entityPlayer) {
        super.func_75134_a(entityPlayer);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

