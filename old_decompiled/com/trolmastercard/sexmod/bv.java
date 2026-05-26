/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.c4;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class bv
implements IMessage {
    Vec3d a;
    boolean c;
    boolean b = false;

    public bv() {
    }

    public bv(Vec3d vec3d, boolean bl2) {
        this.a = vec3d;
        this.c = bl2;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.a = new Vec3d(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
        this.c = byteBuf.readBoolean();
        this.b = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeDouble(this.a.field_72450_a);
        byteBuf.writeDouble(this.a.field_72448_b);
        byteBuf.writeDouble(this.a.field_72449_c);
        byteBuf.writeBoolean(this.c);
    }

    public static class a
    implements IMessageHandler<bv, IMessage> {
        public IMessage a(bv bv2, MessageContext messageContext) {
            block8: {
                block7: {
                    block6: {
                        try {
                            try {
                                if (bv2.b && messageContext.side.equals((Object)Side.CLIENT)) break block6;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.bv$a.a(runtimeException);
                            }
                            System.out.println("received an invalid message @SpawnEnergyBallParticles :(");
                            return null;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.bv$a.a(runtimeException);
                        }
                    }
                    try {
                        if (!bv2.c) break block7;
                        c4.a(bv2.a);
                        break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.bv$a.a(runtimeException);
                    }
                }
                c4.c(bv2.a);
            }
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

