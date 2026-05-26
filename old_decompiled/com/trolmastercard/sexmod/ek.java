/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b1;
import com.trolmastercard.sexmod.bx;
import com.trolmastercard.sexmod.d4;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ge;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ek
extends GuiContainer {
    private static final ResourceLocation f = new ResourceLocation("textures/gui/container/generic_54.png");
    private final IInventory e;
    private final IInventory d;
    private final int g;
    UUID c;
    em b;
    UUID a;

    public ek(EntityPlayer entityPlayer, em em2, UUID uUID) {
        super((Container)new bx((IInventory)entityPlayer.field_71071_by, (IInventory)em2, entityPlayer, uUID));
        this.c = uUID;
        this.b = em2;
        this.a = entityPlayer.getPersistentID();
        this.e = entityPlayer.field_71071_by;
        this.d = (IInventory)em2;
        this.field_146291_p = false;
        this.g = ((IInventory)em2).func_70302_i_() / 9;
        this.field_147000_g = 114 + this.g * 18;
    }

    public void func_73863_a(int n2, int n3, float f10) {
        this.func_146276_q_();
        super.func_73863_a(n2, n3, f10);
        this.func_191948_b(n2, n3);
    }

    protected void func_146979_b(int n2, int n3) {
        this.field_146289_q.func_78276_b(this.b.c(), 8, 6, 0x404040);
        this.field_146289_q.func_78276_b(this.e.func_145748_c_().func_150260_c(), 8, this.field_147000_g - 96 + 2, 0x404040);
    }

    protected void func_146976_a(float f10, int n2, int n3) {
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(f);
        int n4 = (this.field_146294_l - this.field_146999_f) / 2;
        int n5 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(n4, n5, 0, 0, this.field_146999_f, this.g * 18 + 17);
        this.func_73729_b(n4, n5 + this.g * 18 + 17, 0, 126, this.field_146999_f, 96);
    }

    public void func_146281_b() {
        super.func_146281_b();
        for (d4 d42 : d4.c) {
            if (!d42.a.equals(this.c)) continue;
            Object[] objectArray = new ItemStack[63];
            Minecraft.func_71410_x().field_71439_g.field_71071_by.field_70462_a.toArray(objectArray);
            try {
                for (int i2 = 0; i2 < 27; ++i2) {
                    objectArray[i2 + 36] = d42.func_75139_a(i2).func_75211_c();
                }
            }
            catch (RuntimeException runtimeException) {
                throw ek.a(runtimeException);
            }
            ge.b.sendToServer((IMessage)new b1(this.b.f(), this.a, (ItemStack[])objectArray));
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

