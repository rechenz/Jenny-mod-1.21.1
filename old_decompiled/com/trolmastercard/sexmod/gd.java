/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class gd
implements IMessage {
    boolean d = false;
    UUID c;
    int b;
    fp a;

    public gd() {
    }

    public gd(UUID uUID, int n2, fp fp2) {
        this.c = uUID;
        this.b = n2;
        this.a = fp2;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.c = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.b = byteBuf.readInt();
        this.a = fp.valueOf(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.d = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.c.toString());
        byteBuf.writeInt(this.b);
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.a.toString());
    }

    public static class a
    implements IMessageHandler<gd, IMessage> {
        public IMessage a(gd gd2, MessageContext messageContext) {
            block7: {
                try {
                    try {
                        if (gd2.d && messageContext.side.equals((Object)Side.CLIENT)) break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gd$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @ForcePlayerGirlUpdate :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gd$a.a(runtimeException);
                }
            }
            ei ei2 = ei.d(gd2.c);
            try {
                if (ei2 == null) {
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.gd$a.a(runtimeException);
            }
            ei2.func_184212_Q().func_187227_b(em.J, (Object)gd2.a.toString());
            ei2.func_184212_Q().func_187227_b(em.D, (Object)gd2.b);
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

