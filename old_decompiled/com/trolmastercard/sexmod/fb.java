/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b1;
import com.trolmastercard.sexmod.d4;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ge;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class fb
extends GuiContainer {
    static final ResourceLocation c = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
    UUID a;
    em b;
    UUID d;

    public fb(em em2, InventoryPlayer inventoryPlayer, UUID uUID) {
        super((Container)new d4(em2, inventoryPlayer, uUID));
        this.a = uUID;
        this.b = em2;
        this.d = inventoryPlayer.field_70458_d.getPersistentID();
    }

    public void func_73863_a(int n2, int n3, float f10) {
        this.func_146276_q_();
        super.func_73863_a(n2, n3, f10);
        this.func_191948_b(n2, n3);
    }

    public void func_146281_b() {
        super.func_146281_b();
        for (d4 d42 : d4.c) {
            if (!d42.a.equals(this.a)) continue;
            Object[] objectArray = new ItemStack[42];
            Minecraft.func_71410_x().field_71439_g.field_71071_by.field_70462_a.toArray(objectArray);
            objectArray[36] = d42.func_75139_a(0).func_75211_c();
            objectArray[37] = d42.func_75139_a(1).func_75211_c();
            objectArray[38] = d42.func_75139_a(2).func_75211_c();
            objectArray[39] = d42.func_75139_a(3).func_75211_c();
            objectArray[40] = d42.func_75139_a(4).func_75211_c();
            objectArray[41] = d42.func_75139_a(5).func_75211_c();
            ge.b.sendToServer((IMessage)new b1(this.b.f(), this.d, (ItemStack[])objectArray));
        }
    }

    protected void func_146976_a(float f10, int n2, int n3) {
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(c);
        this.func_73729_b(this.field_146294_l / 2 - 88, this.field_146295_m / 2 - 7 - 24, 33, 16, 176, 114);
    }
}

