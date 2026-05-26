/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ClientProxy;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.cu;
import com.trolmastercard.sexmod.ge;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class g6
implements IMessage {
    boolean a = false;
    HashMap<String, Float> b = new HashMap();

    public g6() {
    }

    public g6(HashMap<String, Float> hashMap) {
        this.b = hashMap;
    }

    public void fromBytes(ByteBuf byteBuf) {
        int n2;
        try {
            if (!(Main.proxy instanceof ClientProxy)) {
                this.a = true;
                return;
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw g6.a(indexOutOfBoundsException);
        }
        try {
            if (!br.b()) {
                return;
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw g6.a(indexOutOfBoundsException);
        }
        try {
            n2 = byteBuf.readInt();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            this.a = true;
            return;
        }
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                this.b.put(ByteBufUtils.readUTF8String((ByteBuf)byteBuf), Float.valueOf(byteBuf.readFloat()));
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw g6.a(indexOutOfBoundsException);
        }
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        try {
            if (Main.proxy instanceof ClientProxy) {
                return;
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw g6.a(indexOutOfBoundsException);
        }
        byteBuf.writeInt(this.b.size());
        for (Map.Entry<String, Float> entry : this.b.entrySet()) {
            ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)entry.getKey());
            byteBuf.writeFloat(entry.getValue().floatValue());
        }
    }

    private static IndexOutOfBoundsException a(IndexOutOfBoundsException indexOutOfBoundsException) {
        return indexOutOfBoundsException;
    }

    public static class a
    implements IMessageHandler<g6, IMessage> {
        public IMessage a(g6 g62, MessageContext messageContext) {
            block13: {
                block14: {
                    try {
                        if (!g62.a) {
                            System.out.println("received an invalid Message @RequestServerModelAvailability :(");
                            return null;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g6$a.a(runtimeException);
                    }
                    try {
                        try {
                            if (!messageContext.side.isClient()) break block13;
                            if (br.b()) break block14;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g6$a.a(runtimeException);
                        }
                        return null;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g6$a.a(runtimeException);
                    }
                }
                ArrayList<String> arrayList = new ArrayList<String>();
                for (Map.Entry<String, Float> entry : g62.b.entrySet()) {
                    String string = entry.getKey();
                    try {
                        if (!br.f(string)) {
                            arrayList.add(string);
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g6$a.a(runtimeException);
                    }
                    float f10 = br.i(string);
                    float f11 = entry.getValue().floatValue();
                    try {
                        if (!(f11 > f10)) continue;
                        arrayList.add(string);
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g6$a.a(runtimeException);
                    }
                }
                return new cu(arrayList);
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> ge.b.sendTo((IMessage)new g6(br.e()), messageContext.getServerHandler().field_147369_b));
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

