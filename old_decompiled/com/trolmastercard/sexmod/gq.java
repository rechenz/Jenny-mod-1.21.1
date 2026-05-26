/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiListExtended
 *  net.minecraft.client.gui.GuiListExtended$IGuiListEntry
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.input.Mouse
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.cy;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.gw;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

public class gq
extends GuiListExtended {
    static final int c = 3809871;
    static final List<gw> f = Arrays.asList(gw.values());
    static final String a = "MMMMMMMMMM";
    protected static int i = 5;
    protected static int e = 200;
    private List<a> b = new ArrayList<a>();
    com.trolmastercard.sexmod.a d;
    boolean h = false;
    float g = 0.0f;

    public gq(Minecraft minecraft, com.trolmastercard.sexmod.a a10) {
        super(minecraft, a10.field_146294_l / 2, a10.field_146295_m, 0, a10.field_146295_m, 30);
        e = a10.field_146294_l / 2;
        this.d = a10;
    }

    public GuiListExtended.IGuiListEntry func_148180_b(int n2) {
        return this.b.get(n2);
    }

    protected int func_148127_b() {
        return this.b.size();
    }

    protected int func_148137_d() {
        return 0;
    }

    protected void drawContainerBackground(Tessellator tessellator) {
    }

    public void func_178039_p() {
        try {
            if (!this.func_148141_e(this.field_148162_h)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
        int n2 = Mouse.getEventDWheel();
        try {
            if (n2 == 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
        n2 = n2 > 0 ? -1 : 1;
        this.field_148169_q += (float)(n2 * this.field_148149_f / 2);
    }

    protected void func_148136_c(int n2, int n3, int n4, int n5) {
    }

    void a() {
        int n2 = this.b.size() * this.field_148149_f;
        try {
            if (n2 > this.field_148158_l) {
                this.field_148153_b = 0;
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
        int n3 = this.field_148158_l - n2;
        this.field_148153_b = n3 / 2;
    }

    public void func_148128_a(int n2, int n3, float f10) {
        boolean bl2;
        gq gq2;
        a a11;
        a a12;
        List<a> list;
        this.b.clear();
        int n4 = 0;
        for (Map.Entry<gw, Map.Entry<List<String>, Integer>> entry : com.trolmastercard.sexmod.a.m) {
            gw gw2 = entry.getKey();
            Map.Entry<List<String>, Integer> entry2 = entry.getValue();
            try {
                this.b.add(new a(gw2, entry2.getKey(), entry2.getValue()));
                if (!gw.CUSTOM_BONE.equals((Object)entry.getKey())) continue;
                ++n4;
            }
            catch (RuntimeException runtimeException) {
                throw gq.a(runtimeException);
            }
        }
        this.b.sort(Comparator.comparingInt(a10 -> f.indexOf((Object)a10.d)));
        List<String> list2 = br.a(this.d.c).get((Object)gw.CUSTOM_BONE);
        try {
            a a13;
            list2.add(0, "cross");
            list = this.b;
            a12 = a13;
            a11 = a13;
            gq2 = this;
            bl2 = n4 > 1;
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
        a12(bl2);
        list.add(a11);
        this.a();
        this.a(n2, n3, f10);
        if (!this.h) {
            return;
        }
        this.func_148145_f(999999);
        this.h = false;
    }

    void a(int n2, int n3, float f10) {
        try {
            if (!this.field_178041_q) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
        this.field_148150_g = n2;
        this.field_148162_h = n3;
        this.func_148123_a();
        int n4 = this.func_148137_d();
        int n5 = n4 + 6;
        this.func_148121_k();
        GlStateManager.func_179140_f();
        GlStateManager.func_179106_n();
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferBuilder = tessellator.func_178180_c();
        this.drawContainerBackground(tessellator);
        int n6 = this.field_148152_e + this.field_148155_a / 2 - this.func_148139_c() / 2 + 2;
        int n7 = this.field_148153_b + 4 - (int)this.field_148169_q;
        try {
            if (this.field_148165_u) {
                this.func_148129_a(n6, n7, tessellator);
            }
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
        this.func_192638_a(n6, n7, n2, n3, f10);
        GlStateManager.func_179097_i();
        this.func_148136_c(0, this.field_148153_b, 255, 255);
        this.func_148136_c(this.field_148154_c, this.field_148158_l, 255, 255);
        GlStateManager.func_179147_l();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ZERO, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
        GlStateManager.func_179118_c();
        GlStateManager.func_179103_j((int)7425);
        GlStateManager.func_179090_x();
        int n8 = this.func_148135_f();
        if (n8 > 0) {
            int n9 = (this.field_148154_c - this.field_148153_b) * (this.field_148154_c - this.field_148153_b) / this.func_148138_e();
            int n10 = (int)this.field_148169_q * (this.field_148154_c - this.field_148153_b - (n9 = MathHelper.func_76125_a((int)n9, (int)32, (int)(this.field_148154_c - this.field_148153_b - 8)))) / n8 + this.field_148153_b;
            if (n10 < this.field_148153_b) {
                n10 = this.field_148153_b;
            }
            bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            bufferBuilder.func_181662_b((double)n4, (double)this.field_148154_c, 0.0).func_187315_a(0.0, 1.0).func_181669_b(0, 0, 0, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)n5, (double)this.field_148154_c, 0.0).func_187315_a(1.0, 1.0).func_181669_b(0, 0, 0, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)n5, (double)this.field_148153_b, 0.0).func_187315_a(1.0, 0.0).func_181669_b(0, 0, 0, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)n4, (double)this.field_148153_b, 0.0).func_187315_a(0.0, 0.0).func_181669_b(0, 0, 0, 255).func_181675_d();
            tessellator.func_78381_a();
            bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            bufferBuilder.func_181662_b((double)n4, (double)(n10 + n9), 0.0).func_187315_a(0.0, 1.0).func_181669_b(128, 128, 128, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)n5, (double)(n10 + n9), 0.0).func_187315_a(1.0, 1.0).func_181669_b(128, 128, 128, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)n5, (double)n10, 0.0).func_187315_a(1.0, 0.0).func_181669_b(128, 128, 128, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)n4, (double)n10, 0.0).func_187315_a(0.0, 0.0).func_181669_b(128, 128, 128, 255).func_181675_d();
            tessellator.func_78381_a();
            bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            bufferBuilder.func_181662_b((double)n4, (double)(n10 + n9 - 1), 0.0).func_187315_a(0.0, 1.0).func_181669_b(192, 192, 192, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)(n5 - 1), (double)(n10 + n9 - 1), 0.0).func_187315_a(1.0, 1.0).func_181669_b(192, 192, 192, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)(n5 - 1), (double)n10, 0.0).func_187315_a(1.0, 0.0).func_181669_b(192, 192, 192, 255).func_181675_d();
            bufferBuilder.func_181662_b((double)n4, (double)n10, 0.0).func_187315_a(0.0, 0.0).func_181669_b(192, 192, 192, 255).func_181675_d();
            tessellator.func_78381_a();
        }
        this.func_148142_b(n2, n3);
        GlStateManager.func_179098_w();
        GlStateManager.func_179103_j((int)7424);
        GlStateManager.func_179141_d();
        GlStateManager.func_179084_k();
    }

    public boolean func_148179_a(int n2, int n3, int n4) {
        this.a(n2, n3, n4);
        return super.func_148179_a(n2, n3, n4);
    }

    void a(int n2, int n3, int n4) {
        try {
            if (n2 > this.field_148155_a) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
        int n5 = this.func_148148_g();
        float f10 = n5 + n3 - 5 - this.field_148153_b;
        int n6 = Math.round((float)Math.floor(f10 / (float)this.field_148149_f));
        int n7 = (int)Math.round(((double)(f10 / (float)this.field_148149_f) - Math.floor(f10 / (float)this.field_148149_f)) * (double)this.field_148149_f);
        try {
            if (n6 < 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
        try {
            if (n6 < this.b.size()) {
                this.b.get(n6).a(n2, n7, n4, n6);
            }
        }
        catch (RuntimeException runtimeException) {
            throw gq.a(runtimeException);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    @SideOnly(value=Side.CLIENT)
    public class a
    implements GuiListExtended.IGuiListEntry {
        static final int g = 4;
        public gw d;
        public List<String> b;
        public int f;
        FontRenderer c;
        boolean a = false;
        boolean e = false;

        public a(gw gw2, List<String> list, int n2) {
            this.d = gw2;
            this.b = list;
            this.f = n2;
            this.c = ((gq)gq.this).field_148161_k.field_71466_p;
        }

        public a(boolean bl2) {
            this.e = bl2;
            this.a = true;
        }

        boolean b(int n2, int n3, int n4, int n5, int n6, int n7) {
            try {
                if (n2 < n4) {
                    return false;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            try {
                if (n2 > n6) {
                    return false;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            try {
                if (n3 < n5) {
                    return false;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            try {
                if (n3 > n7) {
                    return false;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            return true;
        }

        void b(int n2, int n3, int n4) {
            int n5;
            int n6;
            int n7;
            int n8;
            com.trolmastercard.sexmod.a a10;
            block9: {
                block8: {
                    int n9;
                    int n10;
                    int n11;
                    int n12;
                    com.trolmastercard.sexmod.a a11;
                    int n13 = 30;
                    try {
                        ((gq)gq.this).field_148161_k.field_71446_o.func_110577_a(com.trolmastercard.sexmod.a.k);
                        a11 = gq.this.d;
                        n12 = n13;
                        n11 = n2 += 5;
                        n10 = 40;
                        n9 = this.b(n3, n4, n13, n2, n13 + 20, n2 + 20) ? 40 : 20;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                    try {
                        a11.func_73729_b(n12, n11, n10, n9, 20, 20);
                        a10 = gq.this.d;
                        n8 = n13 += 40;
                        n7 = n2;
                        n6 = this.e ? 60 : 80;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                    try {
                        try {
                            if (!this.e || !this.b(n3, n4, n13, n2, n13 + 20, n2 + 20)) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                        }
                        n5 = 40;
                        break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                }
                n5 = 20;
            }
            a10.func_73729_b(n8, n7, n6, n5, 20, 20);
        }

        void a(int n2, int n3, int n4) {
            String string;
            String string2;
            int n5;
            int n6;
            int n7;
            com.trolmastercard.sexmod.a a10;
            int n8;
            float f10;
            br.b b10;
            cy cy2;
            int n9;
            block27: {
                block26: {
                    int n10;
                    int n11;
                    int n12;
                    int n13;
                    int n14;
                    com.trolmastercard.sexmod.a a11;
                    try {
                        ((gq)gq.this).field_148161_k.field_71446_o.func_110577_a(com.trolmastercard.sexmod.a.k);
                        a11 = gq.this.d;
                        n14 = i;
                        n13 = n2;
                        n12 = 0;
                        n11 = 60;
                        n10 = this.f == 0 ? 119 : 256;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                    a11.func_73729_b(n14, n13, n12, n11, n10, 30);
                    n9 = i + 10;
                    gq.this.d.a(n9, n2 += 5, this.d.iconXPos);
                    n9 += 25;
                    n9 = this.c(n9, n2, n3, n4);
                    em em2 = gq.this.d.d();
                    cy2 = this.f == 0 ? cy.a((World)((gq)gq.this).field_148161_k.field_71441_e, em2.f(), this.d) : new cy(em2.field_70170_p, em2.f(), this.b.get(this.f));
                    b10 = br.b(cy2.a());
                    try {
                        try {
                            if (!cy2.f && b10 != null) break block26;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                        }
                        f10 = 1.0f;
                        break block27;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                }
                f10 = b10.d();
            }
            float f11 = f10;
            try {
                n8 = b10 == null ? 0 : (int)(-b10.g());
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            int n15 = n8;
            try {
                a10 = gq.this.d;
                n7 = n9;
                n6 = n2 + 10;
                n5 = cy2.f ? 0 : 6;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            try {
                a10.a(n7, n6 + n5 + n15, 30.0f * f11, cy2);
                if (this.f != 0) {
                    gq.this.d.a(cy2);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            ((gq)gq.this).field_148161_k.field_71441_e.func_72973_f((Entity)cy2);
            n9 = (int)((float)n9 + 30.0f);
            try {
                if (this.f == 0) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            int n16 = n9;
            String string3 = this.b.get(this.f);
            try {
                string2 = string3.length() > gq.a.length() ? string3.substring(0, gq.a.length() - 3) + "..." : string3;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            String string4 = string2;
            this.a(string4, n9, n2 + 10);
            int n17 = n9 += this.c.func_78256_a(gq.a);
            int n18 = n9;
            String string5 = br.d(string3);
            try {
                string = string5.length() > gq.a.length() ? string5.substring(0, gq.a.length() - 3) + "..." : string5;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            String string6 = string;
            this.a(string6, n9, n2 + 10);
            int n19 = n9 += this.c.func_78256_a(gq.a);
            try {
                if (this.b(n3, n4, n16, n2 + 10, n17, n2 + 10 + this.c.field_78288_b)) {
                    gq.this.d.a(string3, n3, n4);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            try {
                if (this.b(n3, n4, n18, n2 + 10, n19, n2 + 10 + this.c.field_78288_b)) {
                    gq.this.d.a(string5, n3, n4);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179131_c((float)255.0f, (float)255.0f, (float)255.0f, (float)255.0f);
        }

        int c(int n2, int n3, int n4, int n5) {
            int n6;
            int n7;
            int n8;
            int n9;
            int n10;
            com.trolmastercard.sexmod.a a10;
            int n11;
            int n12;
            int n13;
            int n14;
            int n15;
            com.trolmastercard.sexmod.a a11;
            try {
                a11 = gq.this.d;
                n15 = n2;
                n14 = n3;
                n13 = 0;
                n12 = 20;
                n11 = this.b(n4, n5, n2, n3, n2 + 20, n3 + 20) ? 2 : 1;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            try {
                a11.a(n15, n14, n13, n12 * n11);
                a10 = gq.this.d;
                n10 = n2 += 20;
                n9 = n3;
                n8 = 20;
                n7 = 20;
                n6 = this.b(n4, n5, n2, n3, n2 + 20, n3 + 20) ? 2 : 1;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            a10.a(n10, n9, n8, n7 * n6);
            return n2 + 40;
        }

        void a(int n2, int n3, int n4, int n5, int n6) {
            int n7;
            int n8;
            int n9;
            com.trolmastercard.sexmod.a a10;
            gq.this.d.func_73729_b(n2, n3, 140, 20, 79, 20);
            int n10 = n2 += 4;
            int n11 = n2 + 71 - 4;
            float f10 = this.a(n3, n10, n11, n4, n5, n6);
            int n12 = (int)b6.a((float)n10, (float)n11, f10);
            try {
                a10 = gq.this.d;
                n9 = n12;
                n8 = n3;
                n7 = this.b(n4, n5, n12, n3, n12 + 4, n3 + 20) ? 223 : 219;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            a10.func_73729_b(n9, n8, n7, 20, 4, 20);
            gq.this.d.c.a(n6, (int)(f10 * 100.0f));
        }

        float a(int n2, int n3, int n4, int n5, int n6, int n7) {
            block16: {
                try {
                    if (!gq.this.d.f) {
                        return this.a(n7);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                }
                try {
                    if ((float)n5 > 0.33333334f * (float)gq.this.d.field_146294_l) {
                        return this.a(n7);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                }
                try {
                    try {
                        if (n6 >= n2 && n6 <= n2 + 20) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                    return this.a(n7);
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                }
            }
            try {
                if (n5 < n3) {
                    return 0.0f;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            try {
                if (n5 > n4) {
                    return 1.0f;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
            return (float)(n5 -= n3) / (float)(n4 -= n3);
        }

        float a(int n2) {
            Map.Entry<gw, Map.Entry<List<String>, Integer>> entry = gq.this.d.c.d(gq.this.d.g).get(n2);
            return (float)entry.getValue().getValue().intValue() / 100.0f;
        }

        void b(int n2, int n3, int n4, int n5) {
            block7: {
                int n6;
                block6: {
                    boolean bl2;
                    block5: {
                        block4: {
                            bl2 = gq.this.d.c.h(n5);
                            try {
                                ((gq)gq.this).field_148161_k.field_71446_o.func_110577_a(com.trolmastercard.sexmod.a.k);
                                if (!bl2) break block4;
                                gq.this.d.func_73729_b(i, n2, 0, 60, 119, 30);
                                break block5;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                            }
                        }
                        gq.this.d.func_73729_b(i, n2, 0, 90, 95, 30);
                    }
                    n6 = i + 10;
                    try {
                        gq.this.d.a(n6, n2 += 5, gq.this.d.c.g(n5));
                        n6 += 25;
                        if (!bl2) break block6;
                        this.a(n6, n2, n3, n4, n5);
                        break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                }
                this.c(n6, n2, n3, n4);
            }
        }

        public void func_192634_a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl2, float f10) {
            block5: {
                block6: {
                    block4: {
                        try {
                            if (!this.a) break block4;
                            this.b(n4, n7, n8);
                            break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                        }
                    }
                    try {
                        if (this.d != gw.GIRL_SPECIFIC) break block6;
                        this.b(n4, n7, n8, n2);
                        break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                }
                this.a(n4, n7, n8);
            }
        }

        void a(String string, int n2, int n3) {
            this.c.func_78276_b(string, n2, n3, 3809871);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }

        void b(int n2, int n3) {
            block10: {
                int n4;
                block9: {
                    n4 = 30;
                    try {
                        if (n2 <= n4 || n2 >= n4 + 20) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                    gq.this.h = true;
                    gq.this.field_148161_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187909_gi, (float)1.0f));
                    ArrayList<String> arrayList = new ArrayList<String>();
                    arrayList.add("cross");
                    arrayList.addAll((Collection)br.a(gq.this.d.c).get((Object)gw.CUSTOM_BONE));
                    com.trolmastercard.sexmod.a.m.add(com.trolmastercard.sexmod.a.b(gq.this.d.c));
                }
                try {
                    if (!this.e) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                }
                try {
                    try {
                        if (n2 <= (n4 += 40) || n2 >= n4 + 20) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                    gq.this.field_148161_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187909_gi, (float)1.0f));
                    com.trolmastercard.sexmod.a.m.remove(com.trolmastercard.sexmod.a.m.size() - 1);
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                }
            }
        }

        void a(int n2, int n3) {
            block9: {
                block8: {
                    try {
                        try {
                            if (n2 <= 40 || n2 >= 60) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                        }
                        gq.this.d.a(this.d, false, n3);
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (n2 <= 60 || n2 >= 80) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                    gq.this.d.a(this.d, true, n3);
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                }
            }
        }

        void c(int n2, int n3) {
            try {
                if (!gq.this.d.c.h(n3)) {
                    this.a(n2, n3);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
            }
        }

        public void a(int n2, int n3, int n4, int n5) {
            block14: {
                block15: {
                    block13: {
                        try {
                            if (n4 != 0) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                        }
                        try {
                            if (n3 < 5) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                        }
                        try {
                            if (n3 > 25) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                        }
                        try {
                            if (!this.a) break block13;
                            this.b(n2, n3);
                            break block14;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                        }
                    }
                    try {
                        if (this.d != gw.GIRL_SPECIFIC) break block15;
                        this.c(n2, n5);
                        break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gq$a.a(runtimeException);
                    }
                }
                this.a(n2, n5);
            }
        }

        public void func_192633_a(int n2, int n3, int n4, float f10) {
        }

        public boolean func_148278_a(int n2, int n3, int n4, int n5, int n6, int n7) {
            return false;
        }

        public void func_148277_b(int n2, int n3, int n4, int n5, int n6, int n7) {
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

