/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a6;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f3;
import com.trolmastercard.sexmod.fo;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gg;
import com.trolmastercard.sexmod.n;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ch
extends GuiScreen {
    fo c;
    EntityPlayer a;
    boolean e;
    static final ResourceLocation b = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
    double d = 0.0;

    public ch(fo fo2, EntityPlayer entityPlayer) {
        this.c = fo2;
        this.a = entityPlayer;
        this.e = !"".equals(fo2.func_184212_Q().func_187225_a(em.v));
    }

    public boolean func_73868_f() {
        return false;
    }

    public void func_73863_a(int n2, int n3, float f10) {
        String string;
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        GuiButton guiButton;
        GuiButton guiButton2;
        List list;
        super.func_73863_a(n2, n3, f10);
        this.field_146292_n.clear();
        ScaledResolution scaledResolution = new ScaledResolution(this.field_146297_k);
        int n9 = scaledResolution.func_78326_a();
        try {
            GuiButton guiButton3;
            this.d = Math.min(1.0, this.d + (double)(this.field_146297_k.func_193989_ak() / 5.0f));
            list = this.field_146292_n;
            guiButton2 = guiButton3;
            guiButton = guiButton3;
            n8 = 0;
            n7 = n9 / 2 - 119 + (int)(100.0 - 100.0 * this.d);
            n6 = 30;
            n5 = (int)(this.d * 100.0);
            n4 = 20;
            string = this.e ? I18n.func_135052_a((String)"action.names.stopfollowme", (Object[])new Object[0]) : I18n.func_135052_a((String)"action.names.followme", (Object[])new Object[0]);
        }
        catch (RuntimeException runtimeException) {
            throw ch.a(runtimeException);
        }
        guiButton2(n8, n7, n6, n5, n4, string);
        list.add(guiButton);
        this.field_146292_n.add(new GuiButton(1, n9 / 2 + 19, 30, (int)(this.d * 100.0), 20, I18n.func_135052_a((String)"action.names.gohome", (Object[])new Object[0])));
        this.field_146297_k.field_71446_o.func_110577_a(b);
        this.func_73729_b(n9 / 2 - 7, 61 - (int)(15.0 - this.d * 15.0), 32, 0, 15, 15);
        this.field_146292_n.add(new GuiButton(2, n9 / 2 - 10, 59 - (int)(15.0 - this.d * 15.0), 20, 20, ""));
        this.func_73729_b(n9 / 2 - 20, 20, (Boolean)this.c.func_184212_Q().func_187225_a(fo.K) != false ? 0 : 40, 130, 40, 40);
    }

    protected void func_73864_a(int n2, int n3, int n4) throws IOException {
        block10: {
            ScaledResolution scaledResolution = new ScaledResolution(this.field_146297_k);
            int n5 = scaledResolution.func_78326_a();
            try {
                try {
                    try {
                        try {
                            try {
                                if (!((Boolean)this.c.func_184212_Q().func_187225_a(fo.K)).booleanValue() || n2 < n5 / 2 - 20) break block10;
                            }
                            catch (IOException iOException) {
                                throw ch.a(iOException);
                            }
                            if (n2 > n5 / 2 + 20) break block10;
                        }
                        catch (IOException iOException) {
                            throw ch.a(iOException);
                        }
                        if (n3 < 20) break block10;
                    }
                    catch (IOException iOException) {
                        throw ch.a(iOException);
                    }
                    if (n3 > 60) break block10;
                }
                catch (IOException iOException) {
                    throw ch.a(iOException);
                }
                ge.b.sendToServer((IMessage)new f3(this.c.f(), this.a.getPersistentID()));
                this.func_146281_b();
            }
            catch (IOException iOException) {
                throw ch.a(iOException);
            }
        }
        super.func_73864_a(n2, n3, n4);
    }

    protected void func_146284_a(GuiButton guiButton) throws IOException {
        block12: {
            boolean bl2;
            block14: {
                block13: {
                    try {
                        try {
                            super.func_146284_a(guiButton);
                            if (guiButton.field_146127_k != 0) break block12;
                            if (!this.e) break block13;
                        }
                        catch (IOException iOException) {
                            throw ch.a(iOException);
                        }
                        ge.b.sendToServer((IMessage)new n(this.c.f(), "master", ""));
                        this.a.func_145747_a((ITextComponent)new TextComponentString(I18n.func_135052_a((String)"bee.dialogue.sad", (Object[])new Object[0])));
                        break block14;
                    }
                    catch (IOException iOException) {
                        throw ch.a(iOException);
                    }
                }
                ge.b.sendToServer((IMessage)new n(this.c.f(), "master", this.a.getPersistentID().toString()));
                this.a.func_145747_a((ITextComponent)new TextComponentString(I18n.func_135052_a((String)"bee.dialogue.exited", (Object[])new Object[0])));
            }
            try {
                ch ch2 = this;
                bl2 = !this.e;
            }
            catch (IOException iOException) {
                throw ch.a(iOException);
            }
            ch2.e = bl2;
            this.a.func_71053_j();
        }
        try {
            if (guiButton.field_146127_k == 1) {
                ge.b.sendToServer((IMessage)new gg(this.c.f()));
                this.a.func_71053_j();
            }
        }
        catch (IOException iOException) {
            throw ch.a(iOException);
        }
        try {
            if (guiButton.field_146127_k == 2) {
                ge.b.sendToServer((IMessage)new a6(this.c.f(), new Vec3d(this.c.field_70165_t, this.c.field_70163_u, this.c.field_70161_v)));
                this.a.func_71053_j();
                this.a.func_145747_a((ITextComponent)new TextComponentString(I18n.func_135052_a((String)"bee.dialogue.home", (Object[])new Object[0])));
            }
        }
        catch (IOException iOException) {
            throw ch.a(iOException);
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

