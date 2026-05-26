/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  javax.vecmath.Vector4d
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.e4;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.j;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.vecmath.Vector4d;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class b3
implements IMessage {
    boolean a = false;
    boolean b;
    List<Vector4d> c;

    public b3() {
        this.b = false;
        this.c = new ArrayList<Vector4d>();
    }

    public b3(boolean bl2, List<Vector4d> list) {
        this.b = bl2;
        this.c = list;
    }

    static b3 a() {
        return new b3(false, new ArrayList<Vector4d>());
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.b = byteBuf.readBoolean();
        int n2 = byteBuf.readInt();
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                this.c.add(new Vector4d((double)byteBuf.readInt(), (double)byteBuf.readInt(), (double)byteBuf.readInt(), (double)byteBuf.readInt()));
            }
        }
        catch (RuntimeException runtimeException) {
            throw b3.a(runtimeException);
        }
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(this.b);
        byteBuf.writeInt(this.c.size());
        for (Vector4d vector4d : this.c) {
            byteBuf.writeInt((int)vector4d.getX());
            byteBuf.writeInt((int)vector4d.getY());
            byteBuf.writeInt((int)vector4d.getZ());
            byteBuf.writeInt((int)vector4d.getW());
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a
    implements IMessageHandler<b3, IMessage> {
        public IMessage a(b3 b32, MessageContext messageContext) {
            try {
                if (!b32.a) {
                    System.out.println("received an invalid message @GetTribeUIValues :(");
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.b3$a.a(runtimeException);
            }
            try {
                if (messageContext.side.isClient()) {
                    j.d = b32.b;
                    ff.aY = b32.c;
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.b3$a.a(runtimeException);
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                UUID uUID;
                UUID uUID2 = ax.a(messageContext.getServerHandler().field_147369_b.getPersistentID());
                try {
                    if (uUID2 == null) {
                        ge.b.sendTo((IMessage)b3.a(), messageContext.getServerHandler().field_147369_b);
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.b3$a.a(runtimeException);
                }
                boolean bl2 = ax.c(uUID2);
                EntityPlayerMP entityPlayerMP = messageContext.getServerHandler().field_147369_b;
                HashMap<UUID, BlockPos> hashMap = ax.a(uUID2, entityPlayerMP.field_70170_p);
                List<ff> list = ax.n(uUID2);
                ArrayList<Vector4d> arrayList = new ArrayList<Vector4d>();
                int n2 = ax.l(uUID2).getWoolMeta();
                HashSet<UUID> hashSet = new HashSet<UUID>();
                for (ff object : list) {
                    try {
                        if (object.field_70128_L) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.b3$a.a(runtimeException);
                    }
                    uUID = object.f();
                    try {
                        if (hashSet.contains(uUID)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.b3$a.a(runtimeException);
                    }
                    if (object.aA) {
                        n2 = EyeAndKoboldColor.safeValueOf((String)object.func_184212_Q().func_187225_a(e4.N)).getWoolMeta();
                    }
                    arrayList.add(new Vector4d(object.field_70165_t, object.field_70163_u, object.field_70161_v, (double)n2));
                    hashSet.add(uUID);
                }
                for (Map.Entry entry : hashMap.entrySet()) {
                    try {
                        if (hashSet.contains(entry.getKey())) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.b3$a.a(runtimeException);
                    }
                    uUID = (BlockPos)entry.getValue();
                    arrayList.add(new Vector4d((double)uUID.func_177958_n(), (double)uUID.func_177956_o(), (double)uUID.func_177952_p(), (double)n2));
                }
                ge.b.sendTo((IMessage)new b3(bl2, arrayList), entityPlayerMP);
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

