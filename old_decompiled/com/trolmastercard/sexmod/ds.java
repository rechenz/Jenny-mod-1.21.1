/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.d3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ds
extends Gui {
    static ResourceLocation e = new ResourceLocation("sexmod", "textures/gui/buttons.png");
    static ResourceLocation b = new ResourceLocation("sexmod", "textures/gui/hornymeter.png");
    public static boolean d = false;
    public static double c;
    static double a;
    static float f;
    static float g;
    static boolean i;
    static boolean h;

    public static void d() {
        try {
            if (d) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ds.a(runtimeException);
        }
        ds.b();
        d = true;
        h = true;
    }

    public static void a(boolean bl2) {
        try {
            if (d) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ds.a(runtimeException);
        }
        ds.b();
        d = true;
        h = bl2;
    }

    public static void c() {
        ds.b();
        d = false;
        h = true;
    }

    public static boolean a() {
        return d;
    }

    @SubscribeEvent
    public void a(RenderGameOverlayEvent renderGameOverlayEvent) {
        block18: {
            block27: {
                Minecraft minecraft;
                block26: {
                    int n2;
                    block23: {
                        int n3;
                        block25: {
                            block24: {
                                block21: {
                                    int n4;
                                    block22: {
                                        block20: {
                                            block19: {
                                                try {
                                                    if (!d || renderGameOverlayEvent.getType() != RenderGameOverlayEvent.ElementType.TEXT) break block18;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw ds.a(runtimeException);
                                                }
                                                minecraft = Minecraft.func_71410_x();
                                                try {
                                                    if (!(f < 1.0f)) break block19;
                                                    f += minecraft.func_193989_ak() / 25.0f;
                                                    break block20;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw ds.a(runtimeException);
                                                }
                                            }
                                            f = 1.0f;
                                        }
                                        try {
                                            try {
                                                GL11.glPushMatrix();
                                                minecraft.field_71446_o.func_110577_a(e);
                                                GL11.glScalef((float)0.35f, (float)0.35f, (float)0.35f);
                                                if (!(c >= 1.0)) break block21;
                                                if (!d3.a) break block22;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw ds.a(runtimeException);
                                            }
                                            i = true;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw ds.a(runtimeException);
                                        }
                                    }
                                    try {
                                        n4 = i ? 54 : 0;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ds.a(runtimeException);
                                    }
                                    n2 = n4;
                                    this.func_73729_b(240, 160, 0, 108 + n2, 256, 52);
                                }
                                try {
                                    try {
                                        try {
                                            if (!h || i) break block23;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw ds.a(runtimeException);
                                        }
                                        if (!d3.d) break block24;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ds.a(runtimeException);
                                    }
                                    n3 = 54;
                                    break block25;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ds.a(runtimeException);
                                }
                            }
                            n3 = 0;
                        }
                        n2 = n3;
                        this.func_73729_b((int)b6.a(-200.0f, 98.0f, f), 405, 0, n2, 158, 54);
                    }
                    GL11.glScalef((float)2.857143f, (float)2.857143f, (float)2.857143f);
                    minecraft.field_71446_o.func_110577_a(b);
                    GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
                    this.func_73729_b(10, (int)b6.a(-200.0f, 10.0f, f), 0, 0, 146, 175);
                    a = b6.b(a, c, (double)minecraft.func_193989_ak());
                    n2 = (int)b6.b(0.0, 160.0, a);
                    int n5 = (int)b6.b(167.0, 8.0, a);
                    double d10 = b6.b(178.0, 18.0, a);
                    try {
                        if (i) break block26;
                        this.func_73729_b(67, (int)b6.b(-45.0, d10, (double)f), 159, n5, 32, n2);
                        this.func_73729_b(120, (int)b6.b(-58.0, b6.b(178.0, 149.0, 1.0 - a), (double)f), 212, (int)b6.b(169.0, 141.0, 1.0 - a), 28, (int)b6.b(1.0, 29.0, 1.0 - a));
                        this.func_73729_b(18, (int)b6.b(-58.0, b6.b(178.0, 149.0, 1.0 - a), (double)f), 212, (int)b6.b(169.0, 141.0, 1.0 - a), 28, (int)b6.b(1.0, 29.0, 1.0 - a));
                        break block27;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ds.a(runtimeException);
                    }
                }
                this.func_73729_b(67, (int)b6.a(18.0f, -300.0f, g += minecraft.func_193989_ak() / 15.0f), 159, 8, 32, 160);
            }
            GL11.glPopMatrix();
        }
    }

    public static void a(double d10) {
        double d11;
        try {
            d11 = c > 1.0 ? 1.0 : (c += d10);
        }
        catch (RuntimeException runtimeException) {
            throw ds.a(runtimeException);
        }
        c = d11;
    }

    public static void b() {
        c = 0.0;
        i = false;
    }

    static {
        a = c = 0.0;
        f = 0.0f;
        g = 0.0f;
        i = false;
        h = true;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

