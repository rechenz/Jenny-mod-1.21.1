/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.em;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class bo
implements IMessage {
    boolean c;
    UUID a;
    UUID b;

    public bo() {
    }

    public bo(UUID uUID, UUID uUID2) {
        this.a = uUID;
        this.b = uUID2;
        this.c = true;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.a = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.b = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.c = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.a.toString());
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.b.toString());
    }

    public static class a
    implements IMessageHandler<bo, IMessage> {
        public IMessage a(bo bo2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (bo2.c && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.bo$a.a(runtimeException);
                    }
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.bo$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                for (em em2 : em.ad()) {
                    try {
                        try {
                            if (em2.field_70170_p.field_72995_K || !em2.f().equals(bo2.a)) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.bo$a.a(runtimeException);
                        }
                        ((EntityPlayerMP)em2.field_70170_p.func_152378_a(bo2.b)).openGui((Object)Main.instance, 0, em2.field_70170_p, em2.func_180425_c().func_177958_n(), em2.func_180425_c().func_177956_o(), em2.func_180425_c().func_177952_p());
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.bo$a.a(runtimeException);
                    }
                }
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

