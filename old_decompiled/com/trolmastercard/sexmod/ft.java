/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.em;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ft
implements IMessage {
    boolean a;
    UUID c;
    NBTTagCompound b;

    public ft() {
    }

    public ft(UUID uUID, NBTTagCompound nBTTagCompound) {
        this.c = uUID;
        this.b = nBTTagCompound;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.c = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.b = ByteBufUtils.readTag((ByteBuf)byteBuf);
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.c.toString());
        ByteBufUtils.writeTag((ByteBuf)byteBuf, (NBTTagCompound)this.b);
    }

    public static class a
    implements IMessageHandler<ft, IMessage> {
        public IMessage a(ft ft2, MessageContext messageContext) {
            try {
                if (!ft2.a) {
                    System.out.println("received an invalid message @UpdateEquipment :(");
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ft$a.a(runtimeException);
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                ArrayList<em> arrayList = em.g(ft2.c);
                for (em em2 : arrayList) {
                    try {
                        if (!(em2 instanceof e2)) continue;
                        ((e2)em2).Q.deserializeNBT(ft2.b);
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ft$a.a(runtimeException);
                    }
                }
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

