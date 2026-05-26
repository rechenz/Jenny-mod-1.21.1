/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class aq
implements IMessage {
    boolean d = false;
    float a;
    float b;
    int c;

    public aq() {
    }

    public aq(float f10, float f11, int n2) {
        this.a = f10;
        this.b = f11;
        this.c = n2;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.a = byteBuf.readFloat();
        this.b = byteBuf.readFloat();
        this.c = byteBuf.readInt();
        this.d = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeFloat(this.a);
        byteBuf.writeFloat(this.b);
        byteBuf.writeInt(this.c);
    }

    public static class a
    implements IMessageHandler<aq, IMessage> {
        public IMessage a(aq aq2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (aq2.d && messageContext.side == Side.CLIENT) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.aq$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @SetPlayerCam :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.aq$a.a(runtimeException);
                }
            }
            System.out.println(Thread.currentThread().getName());
            Minecraft minecraft = Minecraft.func_71410_x();
            minecraft.func_152344_a(() -> {
                minecraft.field_71474_y.field_74320_O = aq2.c;
                EntityPlayerSP entityPlayerSP = minecraft.field_71439_g;
                entityPlayerSP.field_70177_z = aq2.b;
                entityPlayerSP.field_70126_B = aq2.b;
                entityPlayerSP.field_70758_at = aq2.b;
                entityPlayerSP.field_70759_as = aq2.b;
                entityPlayerSP.field_70761_aq = aq2.b;
                entityPlayerSP.field_70125_A = aq2.a;
                entityPlayerSP.field_70127_C = aq2.a;
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

