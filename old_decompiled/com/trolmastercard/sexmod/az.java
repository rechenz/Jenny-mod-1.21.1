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
import com.trolmastercard.sexmod.ca;
import com.trolmastercard.sexmod.eb;
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

public class az
extends GuiContainer {
    static final ResourceLocation b = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
    UUID c;
    eb d;
    UUID a;

    public az(eb eb2, InventoryPlayer inventoryPlayer, UUID uUID) {
        super((Container)new ca(eb2, inventoryPlayer, uUID));
        this.c = uUID;
        this.d = eb2;
        this.a = inventoryPlayer.field_70458_d.getPersistentID();
    }

    public void func_73863_a(int n2, int n3, float f10) {
        this.func_146276_q_();
        super.func_73863_a(n2, n3, f10);
        this.func_191948_b(n2, n3);
    }

    public void func_146281_b() {
        super.func_146281_b();
        for (ca ca2 : ca.c) {
            if (!ca2.a.equals(this.c)) continue;
            Object[] objectArray = new ItemStack[43];
            Minecraft.func_71410_x().field_71439_g.field_71071_by.field_70462_a.toArray(objectArray);
            objectArray[36] = ca2.func_75139_a(0).func_75211_c();
            objectArray[37] = ca2.func_75139_a(1).func_75211_c();
            objectArray[38] = ca2.func_75139_a(2).func_75211_c();
            objectArray[39] = ca2.func_75139_a(3).func_75211_c();
            objectArray[40] = ca2.func_75139_a(4).func_75211_c();
            objectArray[41] = ca2.func_75139_a(5).func_75211_c();
            objectArray[42] = ca2.func_75139_a(6).func_75211_c();
            ge.b.sendToServer((IMessage)new b1(this.d.f(), this.a, (ItemStack[])objectArray));
        }
    }

    protected void func_146976_a(float f10, int n2, int n3) {
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(b);
        this.func_73729_b(this.field_146294_l / 2 - 88, this.field_146295_m / 2 - 7 - 24, 80, 142, 176, 114);
    }
}

