/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f_;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class g_
implements IMessage {
    boolean a = false;
    boolean b;

    public g_() {
    }

    public g_(boolean bl2) {
        this.b = bl2;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.b = byteBuf.readBoolean();
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(this.b);
    }

    public static class a
    implements IMessageHandler<g_, IMessage> {
        public IMessage a(g_ g_2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (g_2.a && messageContext.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g_$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @GalathRapePounce :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.g_$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                em em2 = em.c(messageContext.getServerHandler().field_147369_b.getPersistentID());
                try {
                    if (em2 instanceof f_) {
                        ((f_)em2).c(g_2.b);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.g_$a.a(runtimeException);
                }
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

