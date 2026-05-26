/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.t;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class m
extends GuiScreen {
    final em g;
    final EntityPlayer i;
    final String[] h;
    @Nullable
    final ItemStack[] f;
    static final ResourceLocation c = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
    EntityDataManager l;
    final boolean k;
    float m = 0.0f;
    float n = 0.0f;
    String[] a = new String[]{"action.names.followme", "action.names.stopfollowme", "action.names.gohome", "action.names.setnewhome", "action.names.equipment"};
    int[] d = new int[]{0, 0, 0, 0, 0};
    int[] j = new int[]{64, 80, 47, 32, 96};
    int[] b = new int[]{4, 4, 5, 5, 4};
    int[] e = new int[]{50, 90, 50, 80, 60};

    public m(em em2, EntityPlayer entityPlayer) {
        this.g = em2;
        this.i = entityPlayer;
        this.h = new String[0];
        this.f = new ItemStack[0];
        this.k = true;
        this.l = em2.func_184212_Q();
    }

    public m(em em2, EntityPlayer entityPlayer, String[] stringArray, @Nullable ItemStack[] itemStackArray, boolean bl2) {
        this.g = em2;
        this.i = entityPlayer;
        this.h = stringArray;
        this.f = itemStackArray;
        this.k = bl2;
        this.l = em2.func_184212_Q();
    }

    public boolean func_73868_f() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_146281_b() {
        super.func_146281_b();
        this.g.ac();
    }

    protected void func_146284_a(GuiButton guiButton) {
        block16: {
            try {
                block15: {
                    try {
                        try {
                            try {
                                if (guiButton.field_146127_k < 5 || this.f == null) break block15;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.m.a(runtimeException);
                            }
                            if (this.f[guiButton.field_146127_k - 5] == null) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.m.a(runtimeException);
                        }
                        if (!this.i.field_71075_bZ.field_75098_d) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.m.a(runtimeException);
                    }
                }
                this.a(guiButton);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.m.a(runtimeException);
            }
        }
        for (ItemStack itemStack : this.i.field_71071_by.field_70462_a) {
            try {
                try {
                    try {
                        if (!itemStack.func_77973_b().equals(this.f[guiButton.field_146127_k - 5].func_77973_b()) || itemStack.func_190916_E() < this.f[guiButton.field_146127_k - 5].func_190916_E()) continue;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.m.a(runtimeException);
                    }
                    if (itemStack.func_77960_j() != this.f[guiButton.field_146127_k - 5].func_77960_j()) continue;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.m.a(runtimeException);
                }
                ge.b.sendToServer((IMessage)new t(this.i.getPersistentID(), this.f[guiButton.field_146127_k - 5]));
                this.a(guiButton);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.m.a(runtimeException);
            }
        }
        this.i.func_145747_a((ITextComponent)new TextComponentString("<" + this.g.func_70005_c_() + "> you cannot afford that..."));
        this.g.a(com.trolmastercard.sexmod.c.GIRLS_JENNY_SADOH[1]);
    }

    void a(GuiButton guiButton) {
        String string = guiButton.field_146127_k < 5 ? this.a[guiButton.field_146127_k] : this.h[guiButton.field_146127_k - 5];
        this.g.a(string, this.i.getPersistentID());
        Minecraft.func_71410_x().field_71439_g.func_71053_j();
    }

    public void func_73863_a(int n2, int n3, float f10) {
        super.func_73863_a(n2, n3, f10);
        this.field_146292_n.clear();
        ScaledResolution scaledResolution = new ScaledResolution(this.field_146297_k);
        int n4 = scaledResolution.func_78326_a();
        int n5 = scaledResolution.func_78328_b();
        try {
            this.m = Math.min(1.0f, this.m + this.field_146297_k.func_193989_ak() / 5.0f);
            if (this.m == 1.0f) {
                this.n = Math.min(1.0f, this.n + this.field_146297_k.func_193989_ak() / 5.0f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.m.a(runtimeException);
        }
        int n6 = (int)b6.a(115.0f, 161.0f, this.n);
        int n7 = (int)b6.a(91.0f, 137.0f, this.n);
        int n8 = (int)b6.a(-30.0f, 120.0f, this.m);
        int n9 = 70;
        int n10 = 52;
        int n11 = 68;
        int n12 = 5;
        while (true) {
            block17: {
                try {
                    try {
                        try {
                            try {
                                try {
                                    if (n12 >= this.h.length + 5) break;
                                    if (!(this.n > 0.0f)) break block17;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.m.a(runtimeException);
                                }
                                if (this.f == null) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.m.a(runtimeException);
                            }
                            if (this.f[n12 - 5] == null) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.m.a(runtimeException);
                        }
                        if (this.f[n12 - 5].func_190916_E() == 0) break block17;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.m.a(runtimeException);
                    }
                    this.field_73735_i = -300.0f;
                    this.field_146296_j.field_77023_b = -300.0f;
                    this.a(Arrays.asList(this.f[n12 - 5].func_190916_E() + "x    "), n4 - n6, n5 - n10, this.field_146289_q);
                    this.field_146296_j.func_175042_a(this.f[n12 - 5], n4 - n7, n5 - n11);
                    this.field_73735_i = 0.0f;
                    this.field_146296_j.field_77023_b = 0.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.m.a(runtimeException);
                }
            }
            this.field_146292_n.add(new GuiButton(n12, n4 - n8, n5 - n9, 100, 20, I18n.func_135052_a((String)this.h[n12 - 5], (Object[])new Object[0])));
            n9 += 30;
            n10 += 30;
            n11 += 30;
            ++n12;
        }
        try {
            if (this.k) {
                this.a(n2, n3);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.m.a(runtimeException);
        }
    }

    void a(int n2, int n3) {
        boolean bl2;
        int n4 = (int)b6.a(-30.0f, 120.0f, this.m);
        try {
            this.field_146296_j.func_175042_a((ItemStack)this.l.func_187225_a(e2.L), n4 - 105, 68);
            this.field_146296_j.func_175042_a((ItemStack)this.l.func_187225_a(e2.R), n4 - 105, 87);
            this.field_146296_j.func_175042_a((ItemStack)this.l.func_187225_a(e2.X), n4 - 105, 109);
            this.field_146296_j.func_175042_a((ItemStack)this.l.func_187225_a(e2.T), n4 - 105, 127);
            this.field_146296_j.func_175042_a((ItemStack)this.l.func_187225_a(e2.U), n4 - 105, 146);
            this.field_146296_j.func_175042_a((ItemStack)this.l.func_187225_a(e2.W), n4 - 105, 166);
            if (this.n == 0.0f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.m.a(runtimeException);
        }
        try {
            bl2 = !((String)this.l.func_187225_a(em.v)).equals("");
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.m.a(runtimeException);
        }
        boolean bl3 = bl2;
        int n5 = 35;
        int n6 = 70;
        int n7 = 0;
        while (true) {
            String string;
            int n8;
            int n9;
            int n10;
            int n11;
            int n12;
            GuiButton guiButton;
            GuiButton guiButton2;
            List list;
            block28: {
                block27: {
                    block26: {
                        block25: {
                            try {
                                try {
                                    if (n7 >= 5) break;
                                    if (n7 != 0) break block25;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.m.a(runtimeException);
                                }
                                if (!bl3) break block25;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.m.a(runtimeException);
                            }
                            n7 = 1;
                            break block26;
                        }
                        try {
                            if (n7 != 1 || bl3) break block26;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.m.a(runtimeException);
                        }
                        n7 = 2;
                    }
                    try {
                        try {
                            try {
                                try {
                                    if (n2 < n5 || n2 > n5 + 23 + this.d[n7]) break block27;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.m.a(runtimeException);
                                }
                                if (n3 < n6) break block27;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.m.a(runtimeException);
                            }
                            if (n3 > n6 + 20) break block27;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.m.a(runtimeException);
                        }
                        this.d[n7] = Math.min(this.e[n7], this.d[n7] + 7);
                        break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.m.a(runtimeException);
                    }
                }
                this.d[n7] = Math.max(0, this.d[n7] - 7);
            }
            StringBuilder stringBuilder = new StringBuilder(I18n.func_135052_a((String)this.a[n7], (Object[])new Object[0]));
            try {
                for (int i2 = 0; i2 < this.b[n7]; ++i2) {
                    stringBuilder.append(" ");
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.m.a(runtimeException);
            }
            try {
                GuiButton guiButton3;
                this.field_146297_k.field_71446_o.func_110577_a(c);
                this.func_73729_b(this.d[n7] + n5 - 18 + (int)b6.a(0.0f, 23.0f, this.n), n6 + 2, this.j[n7], 0, 16, 16);
                list = this.field_146292_n;
                guiButton2 = guiButton3;
                guiButton = guiButton3;
                n12 = n7;
                n11 = n5 + 1;
                n10 = n6;
                n9 = (int)(b6.a(0.0f, 23.0f, this.n) + (float)this.d[n7]);
                n8 = 20;
                string = this.d[n7] <= 14 ? "" : stringBuilder.toString();
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.m.a(runtimeException);
            }
            guiButton2(n12, n11, n10, n9, n8, string);
            list.add(guiButton);
            n6 += 30;
            ++n7;
        }
        this.field_146297_k.field_71446_o.func_110577_a(c);
        this.func_73729_b(n4 - 113, 60, 0, 0, 32, 130);
    }

    void a(List<String> list, int n2, int n3, FontRenderer fontRenderer) {
        int n4;
        GlStateManager.func_179101_C();
        RenderHelper.func_74518_a();
        GlStateManager.func_179140_f();
        int n5 = 0;
        for (String string : list) {
            n4 = this.field_146289_q.func_78256_a(string);
            if (n4 <= n5) continue;
            n5 = n4;
        }
        int n6 = n2 + 12;
        int n7 = n3 - 12;
        n4 = 8;
        if (list.size() > 1) {
            n4 += 2 + (list.size() - 1) * 10;
        }
        if (n6 + n5 > this.field_146294_l) {
            n6 -= 28 + n5;
        }
        if (n7 + n4 + 6 > this.field_146295_m) {
            n7 = this.field_146295_m - n4 - 6;
        }
        this.func_73733_a(n6 - 3, n7 - 4, n6 + n5 + 3, n7 - 3, -267386864, -267386864);
        this.func_73733_a(n6 - 3, n7 + n4 + 3, n6 + n5 + 3, n7 + n4 + 4, -267386864, -267386864);
        this.func_73733_a(n6 - 3, n7 - 3, n6 + n5 + 3, n7 + n4 + 3, -267386864, -267386864);
        this.func_73733_a(n6 - 4, n7 - 3, n6 - 3, n7 + n4 + 3, -267386864, -267386864);
        this.func_73733_a(n6 + n5 + 3, n7 - 3, n6 + n5 + 4, n7 + n4 + 3, -267386864, -267386864);
        this.func_73733_a(n6 - 3, n7 - 3 + 1, n6 - 3 + 1, n7 + n4 + 3 - 1, 0x505000FF, 1344798847);
        this.func_73733_a(n6 + n5 + 2, n7 - 3 + 1, n6 + n5 + 3, n7 + n4 + 3 - 1, 0x505000FF, 1344798847);
        this.func_73733_a(n6 - 3, n7 - 3, n6 + n5 + 3, n7 - 3 + 1, 0x505000FF, 0x505000FF);
        this.func_73733_a(n6 - 3, n7 + n4 + 2, n6 + n5 + 3, n7 + n4 + 3, 1344798847, 1344798847);
        for (int i2 = 0; i2 < list.size(); ++i2) {
            String string = list.get(i2);
            try {
                this.field_146289_q.func_175063_a(string, (float)n6, (float)n7, -1);
                if (i2 == 0) {
                    n7 += 2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.m.a(runtimeException);
            }
            n7 += 10;
        }
        GlStateManager.func_179145_e();
        RenderHelper.func_74519_b();
        GlStateManager.func_179091_B();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

