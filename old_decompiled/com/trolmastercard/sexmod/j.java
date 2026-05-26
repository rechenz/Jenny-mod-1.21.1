/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockLog
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.au;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.e6;
import com.trolmastercard.sexmod.fa;
import com.trolmastercard.sexmod.fc;
import com.trolmastercard.sexmod.fj;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gm;
import com.trolmastercard.sexmod.h6;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.lwjgl.opengl.GL11;

public class j
extends GuiScreen {
    static final float f = 100.0f;
    static final float g = 15.0f;
    static final float j = 0.5f;
    static final ResourceLocation h = new ResourceLocation("sexmod", "textures/gui/command.png");
    static final HashSet<Material> l = new HashSet<Material>(Arrays.asList(Material.field_151571_B, Material.field_151576_e, Material.field_151595_p, Material.field_151578_c));
    public static boolean d = false;
    float m = 0.0f;
    float a = 0.0f;
    float k = 0.0f;
    float n = 0.0f;
    float i = 0.0f;
    IBlockState e;
    BlockPos c;
    EnumFacing b;

    public j() {
        Minecraft minecraft = Minecraft.func_71410_x();
        try {
            this.c = minecraft.field_71476_x.func_178782_a();
            this.b = minecraft.field_71476_x.field_178784_b == null ? EnumFacing.NORTH : minecraft.field_71476_x.field_178784_b.func_176734_d();
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        try {
            if (this.c == null) {
                this.c = BlockPos.field_177992_a;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        this.e = minecraft.field_71441_e.func_180495_p(this.c);
    }

    public void func_146281_b() {
        super.func_146281_b();
        List<Float> list = Arrays.asList(Float.valueOf(this.a), Float.valueOf(this.k), Float.valueOf(this.n), Float.valueOf(this.i));
        float f10 = Collections.max(list).floatValue();
        try {
            if (f10 == 0.0f) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        try {
            if (this.a == f10) {
                this.b();
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        try {
            if (this.k == f10) {
                this.d();
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        try {
            if (this.n == f10) {
                this.c();
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        try {
            if (this.i == f10) {
                this.a();
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
    }

    void b() {
        block6: {
            boolean bl2;
            BlockPos blockPos;
            h6 h62;
            h6 h63;
            SimpleNetworkWrapper simpleNetworkWrapper;
            block8: {
                block7: {
                    IBlockState iBlockState = this.field_146297_k.field_71441_e.func_180495_p(this.c);
                    try {
                        try {
                            h6 h64;
                            try {
                                if (!(iBlockState.func_177230_c() instanceof BlockBed) && !(iBlockState.func_177230_c() instanceof BlockChest)) break block6;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw com.trolmastercard.sexmod.j.a(nullPointerException);
                            }
                            simpleNetworkWrapper = ge.b;
                            h63 = h64;
                            h62 = h64;
                            blockPos = this.c;
                            if (gm.a(this.c)) break block7;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw com.trolmastercard.sexmod.j.a(nullPointerException);
                        }
                        bl2 = true;
                        break block8;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw com.trolmastercard.sexmod.j.a(nullPointerException);
                    }
                }
                bl2 = false;
            }
            h63(blockPos, bl2);
            simpleNetworkWrapper.sendToServer((IMessage)h62);
        }
    }

    void d() {
        boolean bl2;
        fj fj2;
        fj fj3;
        SimpleNetworkWrapper simpleNetworkWrapper;
        try {
            fj fj4;
            simpleNetworkWrapper = ge.b;
            fj3 = fj4;
            fj2 = fj4;
            bl2 = !d;
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        fj3(bl2);
        simpleNetworkWrapper.sendToServer((IMessage)fj2);
    }

    void c() {
        fa.a();
    }

    void a() {
        block10: {
            Object[] objectArray;
            block11: {
                block8: {
                    block9: {
                        Block block = this.e.func_177230_c();
                        try {
                            try {
                                if (!(block instanceof BlockLog)) break block8;
                                if (!gm.a(this.c)) break block9;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw com.trolmastercard.sexmod.j.a(nullPointerException);
                            }
                            ge.b.sendToServer((IMessage)new au(this.c));
                            return;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw com.trolmastercard.sexmod.j.a(nullPointerException);
                        }
                    }
                    ge.b.sendToServer((IMessage)new fc(this.c));
                }
                objectArray = this.e();
                try {
                    try {
                        if (objectArray == null) break block10;
                        if (!gm.a(this.c)) break block11;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw com.trolmastercard.sexmod.j.a(nullPointerException);
                    }
                    ge.b.sendToServer((IMessage)new au(this.c));
                    return;
                }
                catch (NullPointerException nullPointerException) {
                    throw com.trolmastercard.sexmod.j.a(nullPointerException);
                }
            }
            ge.b.sendToServer((IMessage)new e6((BlockPos)objectArray[0], (EnumFacing)objectArray[1]));
        }
    }

    @Nullable
    Object[] e() {
        Material material = this.field_146297_k.field_71441_e.func_180495_p(this.c).func_185904_a();
        EntityPlayerSP entityPlayerSP = this.field_146297_k.field_71439_g;
        try {
            if (!l.contains(material)) {
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        try {
            if (entityPlayerSP.func_180425_c().func_177956_o() > this.c.func_177956_o()) {
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        BlockPos blockPos = this.c;
        while (this.field_146297_k.field_71441_e.func_180495_p(blockPos.func_177977_b().func_177971_a(this.b.func_176734_d().func_176730_m())).func_177230_c() == Blocks.field_150350_a) {
            blockPos = blockPos.func_177977_b();
        }
        try {
            if (this.c.func_177956_o() - blockPos.func_177956_o() > 3) {
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw com.trolmastercard.sexmod.j.a(nullPointerException);
        }
        return new Object[]{blockPos, this.b};
    }

    public void func_73863_a(int n2, int n3, float f10) {
        block60: {
            boolean bl2;
            float f11;
            block61: {
                boolean bl3;
                Block block;
                block58: {
                    boolean bl4;
                    block59: {
                        int n4;
                        float f12;
                        float f13;
                        block57: {
                            block56: {
                                int n5;
                                float f14;
                                block55: {
                                    block54: {
                                        int n6;
                                        float f15;
                                        block53: {
                                            block52: {
                                                int n7;
                                                float f16;
                                                block51: {
                                                    block50: {
                                                        super.func_73863_a(n2, n3, f10);
                                                        GL11.glEnable((int)3042);
                                                        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                                                        GL11.glBlendFunc((int)770, (int)771);
                                                        try {
                                                            this.m = Math.min(1.0f, this.m + this.field_146297_k.func_193989_ak() / 5.0f);
                                                        }
                                                        catch (NullPointerException nullPointerException) {
                                                            // empty catch block
                                                        }
                                                        f13 = (float)this.a((double)this.m);
                                                        f11 = (1.0f - f13) * 100.0f;
                                                        try {
                                                            try {
                                                                j j2 = this;
                                                                j j3 = j2;
                                                                f16 = j2.a;
                                                                if (n2 >= this.field_146294_l / 2 || n3 <= this.field_146295_m / 2) break block50;
                                                            }
                                                            catch (NullPointerException nullPointerException) {
                                                                throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                                            }
                                                            n7 = 1;
                                                            break block51;
                                                        }
                                                        catch (NullPointerException nullPointerException) {
                                                            throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                                        }
                                                    }
                                                    n7 = -1;
                                                }
                                                try {
                                                    try {
                                                        j3.a = f16 + (float)n7 * this.field_146297_k.func_193989_ak();
                                                        j j4 = this;
                                                        j j5 = j4;
                                                        f15 = j4.k;
                                                        if (n2 >= this.field_146294_l / 2 || n3 >= this.field_146295_m / 2) break block52;
                                                    }
                                                    catch (NullPointerException nullPointerException) {
                                                        throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                                    }
                                                    n6 = 1;
                                                    break block53;
                                                }
                                                catch (NullPointerException nullPointerException) {
                                                    throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                                }
                                            }
                                            n6 = -1;
                                        }
                                        try {
                                            try {
                                                j5.k = f15 + (float)n6 * this.field_146297_k.func_193989_ak();
                                                j j6 = this;
                                                j j7 = j6;
                                                f14 = j6.n;
                                                if (n2 <= this.field_146294_l / 2 || n3 <= this.field_146295_m / 2) break block54;
                                            }
                                            catch (NullPointerException nullPointerException) {
                                                throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                            }
                                            n5 = 1;
                                            break block55;
                                        }
                                        catch (NullPointerException nullPointerException) {
                                            throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                        }
                                    }
                                    n5 = -1;
                                }
                                try {
                                    try {
                                        j7.n = f14 + (float)n5 * this.field_146297_k.func_193989_ak();
                                        j j8 = this;
                                        j j9 = j8;
                                        f12 = j8.i;
                                        if (n2 <= this.field_146294_l / 2 || n3 >= this.field_146295_m / 2) break block56;
                                    }
                                    catch (NullPointerException nullPointerException) {
                                        throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                    }
                                    n4 = 1;
                                    break block57;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                }
                            }
                            n4 = -1;
                        }
                        try {
                            j9.i = f12 + (float)n4 * this.field_146297_k.func_193989_ak();
                            this.a = be.b(this.a, 0.0f, 1.0f);
                            this.k = be.b(this.k, 0.0f, 1.0f);
                            this.n = be.b(this.n, 0.0f, 1.0f);
                            this.i = be.b(this.i, 0.0f, 1.0f);
                            GlStateManager.func_179094_E();
                            GlStateManager.func_179109_b((float)((float)this.field_146294_l / 2.0f), (float)((float)this.field_146295_m / 2.0f), (float)0.0f);
                            GlStateManager.func_179152_a((float)f13, (float)f13, (float)f13);
                            this.field_146297_k.field_71446_o.func_110577_a(h);
                            GlStateManager.func_179094_E();
                            GlStateManager.func_179152_a((float)(1.0f + this.k * 0.5f), (float)(1.0f + this.k * 0.5f), (float)1.0f);
                            this.func_175174_a(-62.0f + f11 - this.k * 15.0f, -62.0f + f11 - this.k * 15.0f, 0, 0, 64, 64);
                            this.c(f11);
                            if (d) {
                                this.func_175174_a(-62.0f + f11 - this.k * 15.0f, -62.0f + f11 - this.k * 15.0f, 128, 64, 64, 64);
                            }
                        }
                        catch (NullPointerException nullPointerException) {
                            throw com.trolmastercard.sexmod.j.a(nullPointerException);
                        }
                        try {
                            GlStateManager.func_179121_F();
                            GlStateManager.func_179094_E();
                            GlStateManager.func_179152_a((float)(1.0f + this.n * 0.5f), (float)(1.0f + this.n * 0.5f), (float)1.0f);
                            this.func_175174_a(-2.0f - f11 + this.n * 15.0f, -2.0f - f11 + this.n * 15.0f, 0, 0, 64, 64);
                            this.a(f11);
                            if (fa.b()) {
                                this.func_175174_a(-2.0f - f11 + this.n * 15.0f, -2.0f - f11 + this.n * 15.0f, 128, 64, 64, 64);
                            }
                        }
                        catch (NullPointerException nullPointerException) {
                            throw com.trolmastercard.sexmod.j.a(nullPointerException);
                        }
                        GlStateManager.func_179121_F();
                        block = this.e.func_177230_c();
                        boolean bl5 = block instanceof BlockChest;
                        bl4 = block instanceof BlockBed;
                        try {
                            try {
                                try {
                                    if (!bl5 && !bl4) break block58;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw com.trolmastercard.sexmod.j.a(nullPointerException);
                                }
                                GlStateManager.func_179094_E();
                                GlStateManager.func_179152_a((float)(1.0f + this.a * 0.5f), (float)(1.0f + this.a * 0.5f), (float)1.0f);
                                this.func_175174_a(-62.0f + f11 - this.a * 15.0f, -2.0f - f11 + this.a * 15.0f, 0, 0, 64, 64);
                                if (!bl5) break block59;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw com.trolmastercard.sexmod.j.a(nullPointerException);
                            }
                            this.d(f11);
                        }
                        catch (NullPointerException nullPointerException) {
                            throw com.trolmastercard.sexmod.j.a(nullPointerException);
                        }
                    }
                    try {
                        if (bl4) {
                            this.f(f11);
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        throw com.trolmastercard.sexmod.j.a(nullPointerException);
                    }
                    try {
                        if (gm.a(this.c)) {
                            this.func_175174_a(-62.0f + f11 - this.a * 15.0f, -2.0f - f11 + this.a * 15.0f, 128, 64, 64, 64);
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        throw com.trolmastercard.sexmod.j.a(nullPointerException);
                    }
                    GlStateManager.func_179121_F();
                }
                boolean bl6 = block instanceof BlockLog;
                try {
                    bl3 = this.e() != null;
                }
                catch (NullPointerException nullPointerException) {
                    throw com.trolmastercard.sexmod.j.a(nullPointerException);
                }
                bl2 = bl3;
                try {
                    try {
                        try {
                            if (!bl6 && !bl2) break block60;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw com.trolmastercard.sexmod.j.a(nullPointerException);
                        }
                        GlStateManager.func_179094_E();
                        GlStateManager.func_179152_a((float)(1.0f + this.i * 0.5f), (float)(1.0f + this.i * 0.5f), (float)1.0f);
                        this.func_175174_a(-2.0f - f11 + this.i * 15.0f, -62.0f + f11 - this.i * 15.0f, 0, 0, 64, 64);
                        if (!bl6) break block61;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw com.trolmastercard.sexmod.j.a(nullPointerException);
                    }
                    this.e(f11);
                }
                catch (NullPointerException nullPointerException) {
                    throw com.trolmastercard.sexmod.j.a(nullPointerException);
                }
            }
            try {
                if (bl2) {
                    this.b(f11);
                }
            }
            catch (NullPointerException nullPointerException) {
                throw com.trolmastercard.sexmod.j.a(nullPointerException);
            }
            try {
                if (gm.a(this.c)) {
                    this.func_175174_a(-2.0f - f11 + this.i * 15.0f, -62.0f + f11 - this.i * 15.0f, 128, 64, 64, 64);
                }
            }
            catch (NullPointerException nullPointerException) {
                throw com.trolmastercard.sexmod.j.a(nullPointerException);
            }
            GlStateManager.func_179121_F();
        }
        GlStateManager.func_179121_F();
        GL11.glDisable((int)3042);
    }

    void a(float f10) {
        this.func_175174_a(-2.0f - f10 + this.n * 15.0f, -2.0f - f10 + this.n * 15.0f, 192, 64, 64, 64);
    }

    void c(float f10) {
        this.func_175174_a(-62.0f + f10 - this.k * 15.0f, -62.0f + f10 - this.k * 15.0f, 64, 64, 64, 64);
    }

    void e(float f10) {
        this.func_175174_a(-2.0f - f10 + this.i * 15.0f, -62.0f + f10 - this.i * 15.0f, 64, 0, 64, 64);
    }

    void b(float f10) {
        this.func_175174_a(-2.0f - f10 + this.i * 15.0f, -62.0f + f10 - this.i * 15.0f, 128, 0, 64, 64);
    }

    void f(float f10) {
        this.func_175174_a(-62.0f + f10 - this.a * 15.0f, -2.0f - f10 + this.a * 15.0f, 0, 64, 64, 64);
    }

    void d(float f10) {
        this.func_175174_a(-62.0f + f10 - this.a * 15.0f, -2.0f - f10 + this.a * 15.0f, 192, 0, 64, 64);
    }

    double a(double d10) {
        double d11 = 1.70158;
        double d12 = d11 + 1.0;
        return 1.0 + d12 * Math.pow(d10 - 1.0, 3.0) + d11 * Math.pow(d10 - 1.0, 2.0);
    }

    protected void func_146286_b(int n2, int n3, int n4) {
        this.field_146297_k.field_71439_g.func_71053_j();
        super.func_146286_b(n2, n3, n4);
    }

    public boolean func_73868_f() {
        return false;
    }

    private static NullPointerException a(NullPointerException nullPointerException) {
        return nullPointerException;
    }
}

