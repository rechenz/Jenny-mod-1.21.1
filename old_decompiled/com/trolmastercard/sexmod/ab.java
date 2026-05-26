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

import com.trolmastercard.sexmod.cc;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f_;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ab
implements IMessage {
    boolean c = false;
    UUID a;
    UUID b;

    public ab() {
    }

    public ab(UUID uUID, UUID uUID2) {
        this.a = uUID;
        this.b = uUID2;
    }

    public void fromBytes(ByteBuf byteBuf) {
        try {
            this.a = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        }
        catch (Exception exception) {
            this.a = null;
        }
        try {
            this.b = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        }
        catch (Exception exception) {
            this.b = null;
        }
        this.c = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        String string;
        ByteBuf byteBuf2;
        String string2;
        ByteBuf byteBuf3;
        try {
            byteBuf3 = byteBuf;
            string2 = this.a == null ? "trol was here" : this.a.toString();
        }
        catch (RuntimeException runtimeException) {
            throw ab.a(runtimeException);
        }
        try {
            ByteBufUtils.writeUTF8String((ByteBuf)byteBuf3, (String)string2);
            byteBuf2 = byteBuf;
            string = this.b == null ? "trol was here" : this.b.toString();
        }
        catch (RuntimeException runtimeException) {
            throw ab.a(runtimeException);
        }
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf2, (String)string);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a
    implements IMessageHandler<ab, IMessage> {
        public IMessage a(ab ab2, MessageContext messageContext) {
            block7: {
                try {
                    try {
                        if (ab2.c && messageContext.side.equals((Object)Side.CLIENT)) break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ab$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @SpawnEnergyBallParticles :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ab$a.a(runtimeException);
                }
            }
            em em2 = em.b(ab2.a);
            try {
                if (!(em2 instanceof f_)) {
                    System.out.println("doesnt exit");
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ab$a.a(runtimeException);
            }
            cc.a(ab2.b, (f_)em2);
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

