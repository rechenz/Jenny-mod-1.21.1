/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.eu;
import com.trolmastercard.sexmod.ge;
import java.util.UUID;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class w {
    public static w a;
    private a b;

    public void a() {
        try {
            if (w.a.b == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw w.a(runtimeException);
        }
        try {
            float f10;
            w.a.b.e -= 1.0f;
            if (f10 <= 0.0f) {
                Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_135052_a((String)"genderswap.sexpromt.timeout", (Object[])new Object[0])));
                this.c();
            }
        }
        catch (RuntimeException runtimeException) {
            throw w.a(runtimeException);
        }
    }

    public a b() {
        return w.a.b;
    }

    void c() {
        w.a.b = null;
    }

    public void a(@Nonnull a a10) {
        String string;
        StringBuilder stringBuilder;
        TextComponentString textComponentString;
        TextComponentString textComponentString2;
        EntityPlayer entityPlayer;
        EntityPlayer entityPlayer2;
        block6: {
            World world = Minecraft.func_71410_x().field_71439_g.field_70170_p;
            entityPlayer2 = world.func_152378_a(a10.d);
            entityPlayer = world.func_152378_a(a10.c);
            try {
                try {
                    if (entityPlayer != null && entityPlayer2 != null) break block6;
                }
                catch (RuntimeException runtimeException) {
                    throw w.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw w.a(runtimeException);
            }
        }
        try {
            TextComponentString textComponentString3;
            textComponentString2 = textComponentString3;
            textComponentString = textComponentString3;
            stringBuilder = new StringBuilder().append(TextFormatting.LIGHT_PURPLE);
            string = a10.b ? entityPlayer.func_70005_c_() : entityPlayer2.func_70005_c_();
        }
        catch (RuntimeException runtimeException) {
            throw w.a(runtimeException);
        }
        textComponentString2(stringBuilder.append(string).append(" ").append(TextFormatting.DARK_PURPLE).append(I18n.func_135052_a((String)"genderswap.sexpromt.playerxaskedfory", (Object[])new Object[0])).append(" ").append(TextFormatting.LIGHT_PURPLE).append(I18n.func_135052_a((String)a10.a, (Object[])new Object[0])).toString());
        TextComponentString textComponentString4 = textComponentString;
        TextComponentString textComponentString5 = new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_135052_a((String)"genderswap.sexpromt.autodeletion", (Object[])new Object[0]));
        TextComponentString textComponentString6 = new TextComponentString(TextFormatting.DARK_PURPLE + "[ " + TextFormatting.LIGHT_PURPLE + I18n.func_135052_a((String)"genderswap.sexpromt.accept", (Object[])new Object[0]) + TextFormatting.DARK_PURPLE + " | " + TextFormatting.LIGHT_PURPLE + I18n.func_135052_a((String)"genderswap.sexpromt.decline", (Object[])new Object[0]) + TextFormatting.DARK_PURPLE + " ]");
        entityPlayer2.func_145747_a((ITextComponent)textComponentString4);
        entityPlayer2.func_145747_a((ITextComponent)textComponentString5);
        entityPlayer2.func_145747_a((ITextComponent)textComponentString6);
        this.b = a10;
    }

    @SubscribeEvent
    public void a(ClientChatEvent clientChatEvent) {
        try {
            if (a.b() == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw w.a(runtimeException);
        }
        String string = clientChatEvent.getMessage().toLowerCase();
        if (string.equals(I18n.func_135052_a((String)"genderswap.sexpromt.accept", (Object[])new Object[0]).toLowerCase())) {
            a a10 = a.b();
            this.a(a10.a, a10.d, a10.c);
            this.c();
            clientChatEvent.setCanceled(true);
        }
        try {
            if (string.equals(I18n.func_135052_a((String)"genderswap.sexpromt.decline", (Object[])new Object[0]).toLowerCase())) {
                Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_135052_a((String)"genderswap.sexpromt.declineconformation", (Object[])new Object[0])));
                this.c();
                clientChatEvent.setCanceled(true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw w.a(runtimeException);
        }
    }

    void a(String string, UUID uUID, UUID uUID2) {
        ge.b.sendToServer((IMessage)new eu(uUID, uUID2, string));
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a {
        public String a;
        public UUID c;
        public UUID d;
        public float e;
        boolean b;

        public a(String string, UUID uUID, UUID uUID2, boolean bl2) {
            this.a = string;
            this.c = uUID;
            this.d = uUID2;
            this.e = 1200.0f;
            this.b = bl2;
        }
    }
}

