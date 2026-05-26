/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.management.PlayerList
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ex;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerList;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class b0
implements IMessage {
    boolean a;
    UUID c;
    UUID b;

    public b0() {
        this.a = false;
    }

    public b0(UUID uUID, UUID uUID2) {
        this.c = uUID;
        this.b = uUID2;
        this.a = true;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.c = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.b = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.c.toString());
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.b.toString());
    }

    public static class a
    implements IMessageHandler<b0, IMessage> {
        public IMessage a(b0 b02, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (b02.a && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw com.trolmastercard.sexmod.b0$a.a(nullPointerException);
                    }
                    System.out.println("received an invalid message @SetPlayerForGirl :(");
                    return null;
                }
                catch (NullPointerException nullPointerException) {
                    throw com.trolmastercard.sexmod.b0$a.a(nullPointerException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                ArrayList<em> arrayList = em.g(b02.c);
                for (em em2 : arrayList) {
                    PlayerList playerList = FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al();
                    try {
                        playerList.func_177451_a(b02.b).func_70005_c_();
                    }
                    catch (NullPointerException nullPointerException) {
                        System.out.println("couldn't find player with UUID: " + b02.b);
                        System.out.println("could only find players with thsese UUID's:");
                        for (EntityPlayerMP entityPlayerMP : playerList.func_181057_v()) {
                            System.out.println(entityPlayerMP.func_70005_c_() + " " + entityPlayerMP.func_110124_au());
                        }
                        continue;
                    }
                    try {
                        if (em2 instanceof ex) {
                            ((ex)em2).af = true;
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        throw com.trolmastercard.sexmod.b0$a.a(nullPointerException);
                    }
                    em2.e(b02.b);
                }
            });
            return null;
        }

        private static NullPointerException a(NullPointerException nullPointerException) {
            return nullPointerException;
        }
    }
}

