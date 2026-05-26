/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.v;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class gf
implements IMessage {
    boolean a = false;
    boolean b;

    public gf() {
    }

    public gf(boolean bl2) {
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
    implements IMessageHandler<gf, IMessage> {
        public IMessage a(gf gf2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (gf2.a && messageContext.side.equals((Object)Side.CLIENT)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gf$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @InformOfOwnership :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gf$a.a(runtimeException);
                }
            }
            v.f = gf2.b;
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

