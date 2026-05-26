/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ge;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class gh
implements IMessage {
    boolean b;
    String a;
    int d;
    UUID c;

    public gh(String string, int n2, UUID uUID) {
        this.a = string;
        this.d = n2;
        this.c = uUID;
        this.b = true;
    }

    public gh() {
        this.b = false;
    }

    public void fromBytes(ByteBuf byteBuf) {
        try {
            int n2 = byteBuf.readInt();
            byte[] byArray = new byte[n2];
            try {
                for (int i2 = 0; i2 < n2; ++i2) {
                    byArray[i2] = byteBuf.readByte();
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                throw gh.a(indexOutOfBoundsException);
            }
            this.a = new String(byArray);
            this.d = byteBuf.readInt();
            this.c = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
            this.b = true;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            this.b = false;
            System.out.println("couldn't read bytes @SendChatMessage :(");
            return;
        }
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(this.a.getBytes().length);
        byteBuf.writeBytes(this.a.getBytes());
        byteBuf.writeInt(this.d);
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.c.toString());
    }

    private static IndexOutOfBoundsException a(IndexOutOfBoundsException indexOutOfBoundsException) {
        return indexOutOfBoundsException;
    }

    public static class a
    implements IMessageHandler<gh, IMessage> {
        public IMessage a(gh gh2, MessageContext messageContext) {
            block6: {
                block5: {
                    try {
                        if (!gh2.b) {
                            System.out.println("recieved an unvalid message @SendChatMessage :(");
                            return null;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gh$a.a(runtimeException);
                    }
                    try {
                        if (!messageContext.side.isClient()) break block5;
                        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(gh2.a));
                        break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gh$a.a(runtimeException);
                    }
                }
                FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                    Vec3d vec3d = em.g(gh2.c).get(0).M();
                    ge.b.sendToAllAround((IMessage)new gh(gh2.a, gh2.d, gh2.c), new NetworkRegistry.TargetPoint(gh2.d, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 40.0));
                });
            }
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

