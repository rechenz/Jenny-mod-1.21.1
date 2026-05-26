/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ClientProxy;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class ea
extends GuiScreen {
    static final float j = 100.0f;
    static final float c = 15.0f;
    static final float k = 5.0f;
    static final float l = 0.5f;
    static final float b = 0.5f;
    static final ResourceLocation i = new ResourceLocation("sexmod", "textures/gui/command.png");
    float a = 0.0f;
    float g = 0.0f;
    float e = 0.0f;
    float d = 0.0f;
    float m = 0.0f;
    em f;
    boolean h = false;

    public ea(em em2) {
        this.f = em2;
        this.h = em2 instanceof e3;
    }

    public void func_146281_b() {
        block16: {
            block15: {
                block14: {
                    try {
                        try {
                            try {
                                super.func_146281_b();
                                if (this.d != 0.0f || this.m != 0.0f) break block14;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw ea.a(nullPointerException);
                            }
                            if (this.g != 0.0f) break block14;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw ea.a(nullPointerException);
                        }
                        return;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw ea.a(nullPointerException);
                    }
                }
                try {
                    if (this.g > 0.0f) {
                        this.c();
                        return;
                    }
                }
                catch (NullPointerException nullPointerException) {
                    throw ea.a(nullPointerException);
                }
                try {
                    if (!this.h) {
                        return;
                    }
                }
                catch (NullPointerException nullPointerException) {
                    throw ea.a(nullPointerException);
                }
                try {
                    if (!(this.d > this.m)) break block15;
                    this.a();
                    break block16;
                }
                catch (NullPointerException nullPointerException) {
                    throw ea.a(nullPointerException);
                }
            }
            this.b();
        }
    }

    void a() {
        try {
            if (this.h) {
                ((e3)this.f).c(Minecraft.func_71410_x().field_71439_g.getPersistentID());
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ea.a(nullPointerException);
        }
    }

    void b() {
        ((e3)this.f).b(Minecraft.func_71410_x().field_71439_g.getPersistentID());
    }

    void c() {
        try {
            if (this.f.ae() != null) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ea.a(nullPointerException);
        }
        this.f.b(fp.START_THROWING);
    }

    public void func_146282_l() throws IOException {
        block4: {
            try {
                try {
                    if (ClientProxy.keyBindings[0].func_151463_i() != Keyboard.getEventKey() || Keyboard.getEventKeyState()) break block4;
                }
                catch (IOException iOException) {
                    throw ea.a(iOException);
                }
                Minecraft.func_71410_x().field_71439_g.func_71053_j();
                return;
            }
            catch (IOException iOException) {
                throw ea.a(iOException);
            }
        }
        super.func_146282_l();
    }

    public void func_73863_a(int n2, int n3, float f10) {
        int n4;
        float f11;
        int n5;
        float f12;
        int n6;
        float f13;
        int n7;
        float f14;
        super.func_73863_a(n2, n3, f10);
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glBlendFunc((int)770, (int)771);
        try {
            this.a = Math.min(1.0f, this.a + this.field_146297_k.func_193989_ak() / 5.0f);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        float f15 = (float)this.a(this.a);
        float f16 = (1.0f - f15) * 100.0f;
        try {
            ea ea2 = this;
            ea ea3 = ea2;
            f14 = ea2.g;
            n7 = n2 < this.field_146294_l / 2 ? 1 : -1;
        }
        catch (NullPointerException nullPointerException) {
            throw ea.a(nullPointerException);
        }
        try {
            ea3.g = f14 + (float)n7 * this.field_146297_k.func_193989_ak();
            ea ea4 = this;
            ea ea5 = ea4;
            f13 = ea4.e;
            n6 = n2 > this.field_146294_l / 2 ? 1 : -1;
        }
        catch (NullPointerException nullPointerException) {
            throw ea.a(nullPointerException);
        }
        try {
            ea5.e = f13 + (float)n6 * this.field_146297_k.func_193989_ak();
            ea ea6 = this;
            ea ea7 = ea6;
            f12 = ea6.d;
            n5 = n3 < this.field_146295_m / 2 - 1 ? 1 : -1;
        }
        catch (NullPointerException nullPointerException) {
            throw ea.a(nullPointerException);
        }
        try {
            ea7.d = f12 + (float)n5 * this.field_146297_k.func_193989_ak();
            ea ea8 = this;
            ea ea9 = ea8;
            f11 = ea8.m;
            n4 = n3 > this.field_146295_m / 2 ? 1 : -1;
        }
        catch (NullPointerException nullPointerException) {
            throw ea.a(nullPointerException);
        }
        try {
            ea9.m = f11 + (float)n4 * this.field_146297_k.func_193989_ak();
            this.g = be.b(this.g, 0.0f, 1.0f);
            this.e = be.b(this.e, 0.0f, 1.0f);
            this.d = be.b(this.d, 0.0f, 1.0f);
            this.m = be.b(this.m, 0.0f, 1.0f);
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)((float)this.field_146294_l / 2.0f), (float)((float)this.field_146295_m / 2.0f), (float)0.0f);
            GlStateManager.func_179152_a((float)f15, (float)f15, (float)f15);
            this.field_146297_k.field_71446_o.func_110577_a(i);
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a((float)(1.0f + this.g * 0.5f), (float)(1.0f + this.g * 0.5f), (float)1.0f);
            this.func_175174_a(-62.0f + f16 - this.g * 15.0f, f16 - 32.0f, 0, 0, 64, 64);
            this.func_175174_a(-62.0f + f16 - this.g * 15.0f, f16 - 32.0f, 64, 128, 64, 64);
            GlStateManager.func_179121_F();
            if (!this.h) {
                GlStateManager.func_179121_F();
                GL11.glDisable((int)3042);
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ea.a(nullPointerException);
        }
        try {
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a((float)(1.0f - this.e), (float)(1.0f - this.e), (float)1.0f);
            this.func_175174_a(-2.0f - f16 + this.e * 32.0f, -f16 - 32.0f, 0, 0, 64, 64);
            this.func_175174_a(-2.0f - f16 + this.e * 32.0f, -f16 - 32.0f, 0, 128, 64, 64);
            GlStateManager.func_179121_F();
            if (this.e > 0.0f) {
                GlStateManager.func_179094_E();
                GlStateManager.func_179152_a((float)(-1.0f + this.e + 1.0f + this.d * 0.5f), (float)(-1.0f + this.e + 1.0f + this.d * 0.5f), (float)1.0f);
                this.func_175174_a(-2.0f - f16 + this.d * 5.0f, -f16 - 64.0f - this.d * 5.0f / 2.0f, 0, 0, 64, 64);
                this.func_175174_a(-2.0f - f16 + this.d * 5.0f, -f16 - 64.0f - this.d * 5.0f / 2.0f, 128, 128, 64, 64);
                GlStateManager.func_179121_F();
                GlStateManager.func_179094_E();
                GlStateManager.func_179152_a((float)(-1.0f + this.e + 1.0f + this.m * 0.5f), (float)(-1.0f + this.e + 1.0f + this.m * 0.5f), (float)1.0f);
                this.func_175174_a(-2.0f - f16 + this.m * 5.0f, -f16 + this.m * 5.0f / 2.0f, 0, 0, 64, 64);
                this.func_175174_a(-2.0f - f16 + this.m * 5.0f, -f16 + this.m * 5.0f / 2.0f, 192, 128, 64, 64);
                GlStateManager.func_179121_F();
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ea.a(nullPointerException);
        }
        GlStateManager.func_179121_F();
        GL11.glDisable((int)3042);
    }

    double a(double d10) {
        double d11 = 1.70158;
        double d12 = d11 + 1.0;
        return 1.0 + d12 * Math.pow(d10 - 1.0, 3.0) + d11 * Math.pow(d10 - 1.0, 2.0);
    }

    public boolean func_73868_f() {
        return false;
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

