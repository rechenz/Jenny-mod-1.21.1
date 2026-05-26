/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.c7;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class z
implements IMessage {
    boolean a;

    public void fromBytes(ByteBuf byteBuf) {
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
    }

    public static class a
    implements IMessageHandler<z, IMessage> {
        public IMessage a(z z2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (z2.a && messageContext.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.z$a.a(runtimeException);
                    }
                    System.out.println("received an invalid Message @SendEgg :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.z$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                EntityPlayerMP entityPlayerMP = messageContext.getServerHandler().field_147369_b;
                UUID uUID = ax.a(entityPlayerMP.getPersistentID());
                try {
                    if (uUID == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.z$a.a(runtimeException);
                }
                EyeAndKoboldColor eyeAndKoboldColor = ax.l(uUID);
                ItemStack itemStack = new ItemStack((Item)c7.a, 1, eyeAndKoboldColor.getWoolMeta());
                NBTTagCompound nBTTagCompound = itemStack.func_77978_p();
                if (nBTTagCompound == null) {
                    nBTTagCompound = new NBTTagCompound();
                }
                nBTTagCompound.func_74778_a("tribeID", uUID.toString());
                itemStack.func_77982_d(nBTTagCompound);
                entityPlayerMP.field_71071_by.func_70441_a(itemStack);
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

