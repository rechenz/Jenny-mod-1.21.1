/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fy;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class fw
implements IMessage {
    boolean a = false;
    String c;
    List<Integer> d = new ArrayList<Integer>();
    UUID b;

    public fw() {
    }

    public fw(String string, UUID uUID) {
        this.c = string;
        this.b = uUID;
    }

    public fw(String string, UUID uUID, List<Integer> list) {
        this.c = string;
        this.b = uUID;
        this.d = list;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.c = ByteBufUtils.readUTF8String((ByteBuf)byteBuf);
        this.b = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        int n2 = byteBuf.readInt();
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                this.d.add(byteBuf.readInt());
            }
        }
        catch (RuntimeException runtimeException) {
            throw fw.a(runtimeException);
        }
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.c);
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.b.toString());
        byteBuf.writeInt(this.d.size());
        for (int n2 : this.d) {
            byteBuf.writeInt(n2);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a
    implements IMessageHandler<fw, IMessage> {
        public IMessage a(fw fw2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (fw2.a && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                    }
                    System.out.println("received an invalid message @UploadModelString :(");
                    return null;
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                block16: {
                    boolean bl2;
                    em em2 = em.a(fw2.b);
                    try {
                        bl2 = fw2.d.size() > 0;
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                    }
                    boolean bl3 = bl2;
                    boolean bl4 = false;
                    if (bl3) {
                        bl4 = this.a(em2, fw2.d);
                        try {
                            if (bl4) {
                                em2.a(fw2.d);
                            }
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                        }
                    }
                    try {
                        if (!(em2 instanceof ei)) {
                            em2.f(fw2.c);
                            return;
                        }
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                    }
                    EntityPlayerMP entityPlayerMP = messageContext.getServerHandler().field_147369_b;
                    NBTTagCompound nBTTagCompound = entityPlayerMP.getEntityData();
                    ei ei2 = ei.g((EntityPlayer)entityPlayerMP);
                    try {
                        if (ei2 == null) {
                            return;
                        }
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                    }
                    fy fy2 = fy.a((Entity)ei2);
                    try {
                        try {
                            nBTTagCompound.func_74778_a("sexmod:CustomModel" + fy2.toString(), fw2.c);
                            if (!bl3 || !bl4) break block16;
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                        }
                        nBTTagCompound.func_74778_a("sexmod:GirlSpecific" + fy2.toString(), em.c(fw2.d));
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                    }
                }
            });
            return null;
        }

        boolean a(em em2, List<Integer> list) {
            block5: {
                ArrayList<Integer> arrayList = em2.D();
                try {
                    int n2 = 0;
                    while (true) {
                        block6: {
                            try {
                                if (n2 >= arrayList.size()) break block5;
                                if (arrayList.get(n2) > list.get(n2)) break block6;
                            }
                            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                                throw com.trolmastercard.sexmod.fw$a.a(indexOutOfBoundsException);
                            }
                            return false;
                        }
                        ++n2;
                    }
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    return false;
                }
            }
            return true;
        }

        private static IndexOutOfBoundsException a(IndexOutOfBoundsException indexOutOfBoundsException) {
            return indexOutOfBoundsException;
        }
    }
}

