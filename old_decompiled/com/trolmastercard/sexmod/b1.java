/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fo;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class b1
implements IMessage {
    boolean b = false;
    ItemStack[] d;
    UUID a;
    UUID c;

    public b1() {
    }

    public b1(UUID uUID, UUID uUID2, ItemStack[] itemStackArray) {
        this.a = uUID;
        this.d = itemStackArray;
        this.c = uUID2;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.a = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.c = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        int n2 = byteBuf.readInt();
        this.d = new ItemStack[n2];
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                this.d[i2] = ByteBufUtils.readItemStack((ByteBuf)byteBuf);
            }
        }
        catch (RuntimeException runtimeException) {
            throw b1.a(runtimeException);
        }
        this.b = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.a.toString());
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.c.toString());
        byteBuf.writeInt(this.d.length);
        for (ItemStack itemStack : this.d) {
            ByteBufUtils.writeItemStack((ByteBuf)byteBuf, (ItemStack)itemStack);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a
    implements IMessageHandler<b1, IMessage> {
        public IMessage a(b1 b12, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (b12.b && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.b1$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @UploadInventoryToServer :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.b1$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                ArrayList<em> arrayList = em.g(b12.a);
                for (em em2 : arrayList) {
                    try {
                        if (em2.field_70170_p.field_72995_K) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.b1$a.a(runtimeException);
                    }
                    EntityPlayer entityPlayer = em2.field_70170_p.func_152378_a(b12.c);
                    try {
                        if (entityPlayer == null) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.b1$a.a(runtimeException);
                    }
                    InventoryPlayer inventoryPlayer = entityPlayer.field_71071_by;
                    try {
                        for (int i2 = 0; i2 < 36; ++i2) {
                            inventoryPlayer.func_70299_a(i2, b12.d[i2]);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.b1$a.a(runtimeException);
                    }
                    if (em2 instanceof eb) {
                        e2 e22 = (e2)em2;
                        e22.Q.setStackInSlot(0, b12.d[36]);
                        e22.Q.setStackInSlot(1, b12.d[37]);
                        e22.Q.setStackInSlot(2, b12.d[38]);
                        e22.Q.setStackInSlot(3, b12.d[39]);
                        e22.Q.setStackInSlot(4, b12.d[40]);
                        e22.Q.setStackInSlot(5, b12.d[41]);
                        e22.Q.setStackInSlot(6, b12.d[42]);
                    } else if (em2 instanceof e2) {
                        e2 e23 = (e2)em2;
                        e23.Q.setStackInSlot(0, b12.d[36]);
                        e23.Q.setStackInSlot(1, b12.d[37]);
                        e23.Q.setStackInSlot(2, b12.d[38]);
                        e23.Q.setStackInSlot(3, b12.d[39]);
                        e23.Q.setStackInSlot(4, b12.d[40]);
                        e23.Q.setStackInSlot(5, b12.d[41]);
                    }
                    if (!(em2 instanceof fo)) continue;
                    fo fo2 = (fo)em2;
                    try {
                        for (int i3 = 0; i3 < 27; ++i3) {
                            fo2.L.setStackInSlot(i3, b12.d[i3 + 36]);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.b1$a.a(runtimeException);
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

