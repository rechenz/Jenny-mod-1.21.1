/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.g9;
import com.trolmastercard.sexmod.ge;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class g7
extends GuiScreen {
    static final int b = 15;
    static final int a = 100;
    static final int c = 20;
    UUID e;
    GuiTextField d;

    public g7(UUID uUID) {
        this.e = uUID;
    }

    public boolean func_73868_f() {
        return false;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.d = new GuiTextField(0, this.field_146297_k.field_71466_p, this.field_146294_l / 2 - 50, this.field_146295_m / 2 - 10, 100, 20);
        this.d.func_146195_b(true);
        this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 25, this.field_146295_m / 2 + 20, 50, 20, "set"));
    }

    public void func_73876_c() {
        this.d.func_146178_a();
        super.func_73876_c();
    }

    public void func_73863_a(int n2, int n3, float f10) {
        this.func_146279_a("Name Tribe", this.field_146294_l / 2 - 39, this.field_146295_m / 2 - 10);
        this.d.func_146194_f();
        super.func_73863_a(n2, n3, f10);
    }

    protected void func_73869_a(char c10, int n2) throws IOException {
        this.d.func_146201_a(c10, n2);
        String string = this.d.func_146179_b();
        try {
            if (string.length() > 15) {
                this.d.func_146180_a(string.substring(0, 15));
            }
        }
        catch (IOException iOException) {
            throw g7.a(iOException);
        }
        super.func_73869_a(c10, n2);
    }

    protected void func_146284_a(GuiButton guiButton) throws IOException {
        super.func_146284_a(guiButton);
        String string = this.d.func_146179_b().trim();
        try {
            if (string.length() == 0) {
                return;
            }
        }
        catch (IOException iOException) {
            throw g7.a(iOException);
        }
        ge.b.sendToServer((IMessage)new g9(this.e, Minecraft.func_71410_x().field_71439_g.getPersistentID(), string));
        Minecraft.func_71410_x().field_71439_g.func_71053_j();
    }

    private static IOException a(IOException iOException) {
        return iOException;
    }
}

