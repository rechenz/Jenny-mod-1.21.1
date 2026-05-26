/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.server.management.PlayerList
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ff;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class g9
implements IMessage {
    boolean c = false;
    UUID d;
    UUID a;
    String b;

    public g9() {
    }

    public g9(UUID uUID, UUID uUID2, String string) {
        this.d = uUID;
        this.a = uUID2;
        this.b = string;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.d = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.a = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.b = ByteBufUtils.readUTF8String((ByteBuf)byteBuf);
        this.c = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.d.toString());
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.a.toString());
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.b);
    }

    public static class a
    implements IMessageHandler<g9, IMessage> {
        public IMessage a(g9 g92, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (g92.c && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g9$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @ClaimTribe :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.g9$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                List<ff> list = ax.n(g92.d);
                EyeAndKoboldColor eyeAndKoboldColor = null;
                for (ff object2 : list) {
                    try {
                        if (object2.J()) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g9$a.a(runtimeException);
                    }
                    EntityDataManager entityDataManager = object2.func_184212_Q();
                    entityDataManager.func_187227_b(em.v, (Object)g92.a.toString());
                    entityDataManager.func_187227_b(ff.aU, (Object)g92.b);
                    eyeAndKoboldColor = EyeAndKoboldColor.valueOf((String)entityDataManager.func_187225_a(ff.N));
                }
                try {
                    if (eyeAndKoboldColor == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.g9$a.a(runtimeException);
                }
                PlayerList playerList = FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al();
                String string = messageContext.getServerHandler().field_147369_b.func_70005_c_();
                for (EntityPlayer entityPlayer : playerList.func_181057_v()) {
                    entityPlayer.func_145747_a((ITextComponent)new TextComponentString(String.format("%s formed the " + eyeAndKoboldColor.getTextColor() + "%s " + TextFormatting.WHITE + "Tribe", string, g92.b)));
                }
                ax.a(g92.d, true);
                ax.a(g92.d, messageContext.getServerHandler().field_147369_b.getPersistentID());
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

