/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.cd;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gr;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class gb
extends Gui {
    static final ResourceLocation l = new ResourceLocation("sexmod", "textures/gui/escape_minigame_ui.png");
    static final int f = 52;
    static final float a = 20.0f;
    static final int p = 35;
    static final float n = 0.08f;
    static final float h = 0.006f;
    static final int m = 2;
    static final float i = 0.33f;
    static boolean g = false;
    static gr q = null;
    static float k = 0.0f;
    static float j = 0.0f;
    static boolean b = true;
    static float d = 0.0f;
    static boolean c = false;
    static Minecraft e = Minecraft.func_71410_x();
    static boolean o = false;

    public static void e() {
        block26: {
            block23: {
                boolean bl2;
                block25: {
                    block24: {
                        block21: {
                            block22: {
                                try {
                                    if (!g) {
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gb.a(runtimeException);
                                }
                                try {
                                    if (gb.e.field_71441_e == null) {
                                        g = false;
                                        o = false;
                                        j = 0.0f;
                                        k = 0.0f;
                                        d = 0.0f;
                                        c = false;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gb.a(runtimeException);
                                }
                                try {
                                    try {
                                        if (!c) break block21;
                                        b = false;
                                        if (!((d += 1.0f) >= 20.0f)) break block22;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gb.a(runtimeException);
                                    }
                                    g = false;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gb.a(runtimeException);
                                }
                            }
                            return;
                        }
                        try {
                            try {
                                if ((j += 1.0f) % (float)Math.max(1, 2) != 0.0f) break block23;
                                if (b) break block24;
                            }
                            catch (RuntimeException runtimeException) {
                                throw gb.a(runtimeException);
                            }
                            bl2 = true;
                            break block25;
                        }
                        catch (RuntimeException runtimeException) {
                            throw gb.a(runtimeException);
                        }
                    }
                    bl2 = false;
                }
                b = bl2;
            }
            try {
                k = Math.max(0.0f, k - 0.006f);
                if (j < 20.0f) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw gb.a(runtimeException);
            }
            try {
                try {
                    if (j % 35.0f != 0.0f && q != null) break block26;
                }
                catch (RuntimeException runtimeException) {
                    throw gb.a(runtimeException);
                }
                gb.b();
            }
            catch (RuntimeException runtimeException) {
                throw gb.a(runtimeException);
            }
        }
    }

    static void b() {
        gr gr2 = q;
        Random random = new Random();
        try {
            while (gr2 == (q = gr.values()[random.nextInt(gr.values().length)])) {
            }
            return;
        }
        catch (RuntimeException runtimeException) {
            throw gb.a(runtimeException);
        }
    }

    static void c() {
        try {
            if (!g) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gb.a(runtimeException);
        }
        try {
            if (o) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gb.a(runtimeException);
        }
        o = true;
        ge.b.sendToServer((IMessage)new cd());
        gb.d();
    }

    public static void a() {
        g = true;
        o = false;
        j = 0.0f;
        k = 0.0f;
        d = 0.0f;
        c = false;
    }

    public static void d() {
        c = true;
        d = 0.0f;
    }

    @SubscribeEvent
    public void a(RenderGameOverlayEvent renderGameOverlayEvent) {
        int n2;
        int n3;
        int n4;
        int n5;
        gb gb2;
        int n6;
        int n7;
        double d10;
        int n8;
        block29: {
            block28: {
                int n9;
                int n10;
                int n11;
                int n12;
                gb gb3;
                block27: {
                    block26: {
                        int n13;
                        int n14;
                        int n15;
                        int n16;
                        gb gb4;
                        block25: {
                            block24: {
                                int n17;
                                int n18;
                                int n19;
                                int n20;
                                gb gb5;
                                block23: {
                                    block22: {
                                        try {
                                            if (!g) {
                                                return;
                                            }
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw gb.a(runtimeException);
                                        }
                                        try {
                                            if (renderGameOverlayEvent.getType() != RenderGameOverlayEvent.ElementType.TEXT) {
                                                return;
                                            }
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw gb.a(runtimeException);
                                        }
                                        n8 = renderGameOverlayEvent.getResolution().func_78326_a();
                                        int n21 = renderGameOverlayEvent.getResolution().func_78328_b();
                                        float f10 = renderGameOverlayEvent.getPartialTicks();
                                        e.func_110434_K().func_110577_a(l);
                                        d10 = c ? 1.0 - b6.d((d + f10) / 20.0f) : Math.min(1.0, b6.c((j + f10) / 20.0f));
                                        n7 = n21 + 385;
                                        GlStateManager.func_179094_E();
                                        GlStateManager.func_179152_a((float)0.33f, (float)0.33f, (float)0.33f);
                                        GlStateManager.func_179109_b((float)485.0f, (float)0.0f, (float)0.0f);
                                        n6 = 4 * n21;
                                        try {
                                            try {
                                                this.func_73729_b(n8 / 2 - 87, (int)b6.b((double)n6, (double)n7, d10), 0, 104, 174, 48);
                                                gb5 = this;
                                                n20 = (int)((float)n8 / 2.0f - 78.0f);
                                                n19 = (int)b6.b((double)n6, (double)(n7 - 52), d10);
                                                n18 = 52;
                                                if (!b || q != gr.A) break block22;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw gb.a(runtimeException);
                                            }
                                            n17 = 52;
                                            break block23;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw gb.a(runtimeException);
                                        }
                                    }
                                    n17 = 0;
                                }
                                try {
                                    try {
                                        gb5.func_73729_b(n20, n19, n18, n17, 52, 52);
                                        gb4 = this;
                                        n16 = (int)((float)n8 / 2.0f - 26.0f);
                                        n15 = (int)b6.b((double)n6, (double)(n7 - 52), d10);
                                        n14 = 104;
                                        if (!b || q != gr.S) break block24;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gb.a(runtimeException);
                                    }
                                    n13 = 52;
                                    break block25;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gb.a(runtimeException);
                                }
                            }
                            n13 = 0;
                        }
                        try {
                            try {
                                gb4.func_73729_b(n16, n15, n14, n13, 52, 52);
                                gb3 = this;
                                n12 = (int)((float)n8 / 2.0f + 26.0f);
                                n11 = (int)b6.b((double)n6, (double)(n7 - 52), d10);
                                n10 = 156;
                                if (!b || q != gr.D) break block26;
                            }
                            catch (RuntimeException runtimeException) {
                                throw gb.a(runtimeException);
                            }
                            n9 = 52;
                            break block27;
                        }
                        catch (RuntimeException runtimeException) {
                            throw gb.a(runtimeException);
                        }
                    }
                    n9 = 0;
                }
                try {
                    try {
                        gb3.func_73729_b(n12, n11, n10, n9, 52, 52);
                        gb2 = this;
                        n5 = (int)((float)n8 / 2.0f - 26.0f);
                        n4 = (int)b6.b((double)n6, (double)(n7 - 104), d10);
                        n3 = 0;
                        if (!b || q != gr.W) break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw gb.a(runtimeException);
                    }
                    n2 = 52;
                    break block29;
                }
                catch (RuntimeException runtimeException) {
                    throw gb.a(runtimeException);
                }
            }
            n2 = 0;
        }
        gb2.func_73729_b(n5, n4, n3, n2, 52, 52);
        this.func_73729_b(n8 / 2 - 87 + 8, (int)b6.b((double)(n6 - 8), (double)(n7 + 8), d10), 8, 152, (int)(158.0f * k), 32);
        GlStateManager.func_179121_F();
    }

    @SubscribeEvent
    public void a(TickEvent.ClientTickEvent clientTickEvent) {
        try {
            if (clientTickEvent.phase == TickEvent.Phase.END) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gb.a(runtimeException);
        }
        gb.e();
    }

    @SubscribeEvent
    public void a(InputEvent.KeyInputEvent keyInputEvent) {
        block28: {
            block30: {
                block29: {
                    GameSettings gameSettings;
                    block25: {
                        block27: {
                            block26: {
                                block22: {
                                    block24: {
                                        block23: {
                                            block19: {
                                                block21: {
                                                    block20: {
                                                        gameSettings = Minecraft.func_71410_x().field_71474_y;
                                                        try {
                                                            try {
                                                                if (!GameSettings.func_100015_a((KeyBinding)gameSettings.field_74370_x)) break block19;
                                                                if (q != gr.A) break block20;
                                                            }
                                                            catch (RuntimeException runtimeException) {
                                                                throw gb.a(runtimeException);
                                                            }
                                                            k += 0.08f;
                                                            break block21;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw gb.a(runtimeException);
                                                        }
                                                    }
                                                    k -= 0.04f;
                                                }
                                                return;
                                            }
                                            try {
                                                try {
                                                    if (!GameSettings.func_100015_a((KeyBinding)gameSettings.field_74366_z)) break block22;
                                                    if (q != gr.D) break block23;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw gb.a(runtimeException);
                                                }
                                                k += 0.08f;
                                                break block24;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw gb.a(runtimeException);
                                            }
                                        }
                                        k -= 0.04f;
                                    }
                                    return;
                                }
                                try {
                                    try {
                                        if (!GameSettings.func_100015_a((KeyBinding)gameSettings.field_74351_w)) break block25;
                                        if (q != gr.W) break block26;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw gb.a(runtimeException);
                                    }
                                    k += 0.08f;
                                    break block27;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw gb.a(runtimeException);
                                }
                            }
                            k -= 0.04f;
                        }
                        return;
                    }
                    try {
                        try {
                            if (!GameSettings.func_100015_a((KeyBinding)gameSettings.field_74368_y)) break block28;
                            if (q != gr.S) break block29;
                        }
                        catch (RuntimeException runtimeException) {
                            throw gb.a(runtimeException);
                        }
                        k += 0.08f;
                        break block30;
                    }
                    catch (RuntimeException runtimeException) {
                        throw gb.a(runtimeException);
                    }
                }
                k -= 0.04f;
            }
            return;
        }
        try {
            if (k >= 1.0f) {
                gb.c();
            }
        }
        catch (RuntimeException runtimeException) {
            throw gb.a(runtimeException);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

