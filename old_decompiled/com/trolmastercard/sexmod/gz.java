/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.ds;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class gz
implements IMessage {
    boolean a;
    boolean b;

    public gz(boolean bl2) {
        this.b = bl2;
        this.a = true;
    }

    public gz() {
        this.a = false;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.b = byteBuf.readBoolean();
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(this.b);
        this.a = true;
    }

    public static class a
    implements IMessageHandler<gz, IMessage> {
        /*
         * Unable to fully structure code
         */
        public IMessage a(gz var1_1, MessageContext var2_2) {
            block9: {
                if (!var1_1.a) ** GOTO lbl8
                try {
                    block10: {
                        if (var2_2.side == Side.CLIENT) break block9;
                        break block10;
                        catch (Exception v0) {
                            throw com.trolmastercard.sexmod.gz$a.a(v0);
                        }
                    }
                    System.out.println("received an invalid message @SetPlayerMovement :(");
                    return null;
                }
                catch (Exception v1) {
                    throw com.trolmastercard.sexmod.gz$a.a(v1);
                }
            }
            d3.a(var1_1.b);
            try {
                Minecraft.func_71410_x().field_71439_g.func_70016_h(0.0, 0.0, 0.0);
            }
            catch (Exception var3_3) {
                // empty catch block
            }
            try {
                if (var1_1.b) {
                    ds.c();
                }
            }
            catch (Exception v2) {
                throw com.trolmastercard.sexmod.gz$a.a(v2);
            }
            return null;
        }

        private static Exception a(Exception exception) {
            return exception;
        }
    }
}

