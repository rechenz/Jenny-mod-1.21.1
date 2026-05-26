/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b5;
import com.trolmastercard.sexmod.fy;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class bd
implements IMessage {
    boolean c = false;
    EntityPlayer b;
    HashMap<fy, String> a = new HashMap();

    public bd() {
    }

    public bd(EntityPlayer entityPlayer) {
        this.b = entityPlayer;
    }

    public void fromBytes(ByteBuf byteBuf) {
        int n2 = byteBuf.readInt();
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                this.a.put(fy.valueOf(ByteBufUtils.readUTF8String((ByteBuf)byteBuf)), ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
            }
        }
        catch (RuntimeException runtimeException) {
            throw bd.a(runtimeException);
        }
        this.c = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        for (fy fy2 : fy.values()) {
            try {
                if (!fy2.hasSpecifics) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw bd.a(runtimeException);
            }
            String string = this.b.getEntityData().func_74779_i("sexmod:GirlSpecific" + (Object)((Object)fy2));
            try {
                if ("".equals(string)) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw bd.a(runtimeException);
            }
            this.a.put(fy2, string);
        }
        byteBuf.writeInt(this.a.size());
        for (Map.Entry entry : this.a.entrySet()) {
            ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)((fy)((Object)entry.getKey())).toString());
            ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)((String)entry.getValue()));
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a
    implements IMessageHandler<bd, IMessage> {
        public IMessage a(bd bd2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (bd2.c && messageContext.side == Side.CLIENT) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.bd$a.a(runtimeException);
                    }
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.bd$a.a(runtimeException);
                }
            }
            this.a(bd2.a);
            return null;
        }

        @SideOnly(value=Side.CLIENT)
        public void a(HashMap<fy, String> hashMap) {
            Minecraft minecraft = Minecraft.func_71410_x();
            minecraft.func_152344_a(() -> minecraft.func_147108_a((GuiScreen)new b5(hashMap)));
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

