/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MovementInput
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.client.event.InputUpdateEvent
 *  net.minecraftforge.client.event.MouseEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ds;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class d3 {
    private static boolean c = true;
    public static boolean d = false;
    public static boolean a = false;
    public static MovementInput b;

    @SubscribeEvent
    public void a(InputUpdateEvent inputUpdateEvent) {
        block13: {
            try {
                b = inputUpdateEvent.getMovementInput();
                d = d3.b.field_78899_d;
                a = d3.b.field_78901_c;
                if (c) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw d3.a(runtimeException);
            }
            try {
                if (d3.b.field_78901_c) {
                    ei.i();
                }
            }
            catch (RuntimeException runtimeException) {
                throw d3.a(runtimeException);
            }
            try {
                if (d3.b.field_78899_d) {
                    em.k(Minecraft.func_71410_x().field_71439_g.getPersistentID());
                }
            }
            catch (RuntimeException runtimeException) {
                throw d3.a(runtimeException);
            }
            try {
                try {
                    if (!d3.b.field_78901_c || !(ds.c >= 1.0)) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw d3.a(runtimeException);
                }
                em.f(Minecraft.func_71410_x().field_71439_g.getPersistentID());
            }
            catch (RuntimeException runtimeException) {
                throw d3.a(runtimeException);
            }
        }
        d3.b.field_187256_d = false;
        d3.b.field_187255_c = false;
        d3.b.field_187257_e = false;
        d3.b.field_187258_f = false;
        d3.b.field_78899_d = false;
        d3.b.field_78901_c = false;
        d3.b.field_192832_b = 0.0f;
        d3.b.field_78902_a = 0.0f;
        Minecraft.func_71410_x().field_71439_g.func_70016_h(0.0, 0.0, 0.0);
    }

    public static boolean b() {
        return c;
    }

    public static void a(boolean bl2) {
        try {
            c = bl2;
            if (!bl2) {
                d3.a();
            }
        }
        catch (RuntimeException runtimeException) {
            throw d3.a(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    static void a() {
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        try {
            if (!ei.e((EntityPlayer)entityPlayerSP)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw d3.a(runtimeException);
        }
        entityPlayerSP.func_146105_b((ITextComponent)new TextComponentString("Jump to get out of the animation"), true);
    }

    @SubscribeEvent
    public void a(MouseEvent mouseEvent) {
        block4: {
            try {
                try {
                    if (c || !mouseEvent.isButtonstate()) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw d3.a(runtimeException);
                }
                mouseEvent.setCanceled(true);
            }
            catch (RuntimeException runtimeException) {
                throw d3.a(runtimeException);
            }
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

