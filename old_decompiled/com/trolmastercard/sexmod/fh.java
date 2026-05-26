/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.ds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class fh
extends GuiScreen {
    public static final int d = 1200;
    private static boolean b = false;
    private static double e = 0.0;
    static ResourceLocation c = new ResourceLocation("sexmod", "textures/gui/transitionscreen.png");
    static ResourceLocation f = new ResourceLocation("sexmod", "textures/gui/mirroredtransitionscreen.png");
    static ResourceLocation a = new ResourceLocation("sexmod", "textures/gui/blackscreen.png");

    public static boolean a() {
        return b;
    }

    public static void b() {
        b = true;
    }

    public static void a(Runnable runnable) {
        b = true;
        be.a(1200, runnable);
    }

    public boolean func_73868_f() {
        return false;
    }

    @SubscribeEvent
    public void a(RenderGameOverlayEvent renderGameOverlayEvent) {
        try {
            if (!b) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fh.a(runtimeException);
        }
        try {
            if (renderGameOverlayEvent.getType() != RenderGameOverlayEvent.ElementType.TEXT) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fh.a(runtimeException);
        }
        Minecraft minecraft = Minecraft.func_71410_x();
        int n2 = minecraft.field_71474_y.field_74335_Z;
        float f10 = n2 == 1 ? (float)b6.b(-1800.0, 1000.0, 0.5 * Math.cos(e / 25.0) + 0.5) : (n2 == 2 ? (float)b6.b(-900.0, 750.0, 0.5 * Math.cos(e / 25.0) + 0.5) : (float)b6.b(-900.0, 600.0, 0.5 * Math.cos((e += (double)(minecraft.func_193989_ak() * 0.75f)) / 25.0) + 0.5));
        try {
            GlStateManager.func_179094_E();
            if (n2 == 1) {
                GlStateManager.func_179152_a((float)2.0f, (float)2.0f, (float)2.0f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fh.a(runtimeException);
        }
        try {
            if (n2 == 2) {
                GlStateManager.func_179139_a((double)1.5, (double)1.5, (double)1.5);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fh.a(runtimeException);
        }
        try {
            minecraft.field_71446_o.func_110577_a(c);
            this.func_175174_a(f10, 0.0f, 0, (int)(e * 1.5), 256, 256);
            this.func_175174_a(f10, 256.0f, 0, (int)(e * 1.5), 256, 256);
            this.func_175174_a(f10, 512.0f, 0, (int)(e * 1.5), 256, 256);
            minecraft.field_71446_o.func_110577_a(f);
            this.func_175174_a(f10 + 600.0f, 0.0f, 0, (int)(e * 1.5), 256, 256);
            this.func_175174_a(f10 + 600.0f, 256.0f, 0, (int)(e * 1.5), 256, 256);
            this.func_175174_a(f10 + 600.0f, 512.0f, 0, (int)(e * 1.5), 256, 256);
            minecraft.field_71446_o.func_110577_a(a);
            this.func_175174_a(f10 + 200.0f, 0.0f, 0, 0, 400, 256);
            this.func_175174_a(f10 + 200.0f, 256.0f, 0, 0, 400, 256);
            this.func_175174_a(f10 + 200.0f, 512.0f, 0, 0, 400, 256);
            if (e > 30.0) {
                ds.c();
            }
        }
        catch (RuntimeException runtimeException) {
            throw fh.a(runtimeException);
        }
        try {
            if (e > 69.0) {
                e = 0.0;
                b = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fh.a(runtimeException);
        }
        GlStateManager.func_179121_F();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

