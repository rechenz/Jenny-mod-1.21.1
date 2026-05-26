/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.commons.io.FileUtils
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ClientProxy;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.ge;
import io.netty.buffer.ByteBuf;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.FileUtils;

public class cu
implements IMessage {
    boolean d;
    List<String> c = new ArrayList<String>();
    byte[] b;
    b f;
    String e;
    int a = 0;

    public cu() {
    }

    public cu(List<String> list) {
        this.c = list;
    }

    public cu(byte[] byArray, b b10, String string) {
        this.b = byArray;
        this.f = b10;
        this.e = string;
    }

    public int a() {
        return this.a;
    }

    public void a(int n2) {
        this.a = n2;
    }

    public void fromBytes(ByteBuf byteBuf) {
        block10: {
            block11: {
                try {
                    try {
                        if (!(Main.proxy instanceof ClientProxy)) break block10;
                        if (br.b()) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cu.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw cu.a(runtimeException);
                }
            }
            this.e = ByteBufUtils.readUTF8String((ByteBuf)byteBuf);
            this.f = com.trolmastercard.sexmod.cu$b.valueOf(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
            this.a = byteBuf.readInt();
            int n2 = byteBuf.readInt();
            this.b = new byte[n2];
            try {
                for (int i2 = 0; i2 < n2; ++i2) {
                    this.b[i2] = byteBuf.readByte();
                }
            }
            catch (RuntimeException runtimeException) {
                throw cu.a(runtimeException);
            }
            this.d = true;
            return;
        }
        int n3 = byteBuf.readInt();
        try {
            for (int i3 = 0; i3 < n3; ++i3) {
                this.c.add(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
            }
        }
        catch (RuntimeException runtimeException) {
            throw cu.a(runtimeException);
        }
        this.d = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        if (Main.proxy instanceof ClientProxy) {
            byteBuf.writeInt(this.c.size());
            for (String string : this.c) {
                ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)string);
            }
            return;
        }
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.e);
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.f.toString());
        byteBuf.writeInt(this.a);
        byteBuf.writeInt(this.b.length);
        for (byte by2 : this.b) {
            byteBuf.writeByte((int)by2);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static enum b {
        CFG(".cfg"),
        PNG(".png"),
        GEO(".geo.json");

        public String ending;

        private b(String string2) {
            this.ending = string2;
        }
    }

    public static class a
    implements IMessageHandler<cu, IMessage> {
        static int a = 0;

        @SideOnly(value=Side.CLIENT)
        void a(String string) {
            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(string));
        }

        @SideOnly(value=Side.CLIENT)
        void a() {
            Minecraft.func_71410_x().func_152343_a(() -> br.b(true));
        }

        /*
         * Loose catch block
         */
        public IMessage a(cu cu2, MessageContext messageContext) {
            block27: {
                block34: {
                    int n2;
                    int n3;
                    String string;
                    block33: {
                        String string2;
                        block29: {
                            block28: {
                                try {
                                    if (!cu2.d) {
                                        System.out.println("received an invalid Message @DownloadServerModel :(");
                                        return null;
                                    }
                                }
                                catch (Throwable throwable) {
                                    throw com.trolmastercard.sexmod.cu$a.a(throwable);
                                }
                                if (!messageContext.side.isClient()) break block27;
                                try {
                                    block35: {
                                        if (br.b()) break block28;
                                        break block35;
                                        catch (Throwable throwable) {
                                            throw com.trolmastercard.sexmod.cu$a.a(throwable);
                                        }
                                    }
                                    return null;
                                }
                                catch (Throwable throwable) {
                                    throw com.trolmastercard.sexmod.cu$a.a(throwable);
                                }
                            }
                            string = cu2.e;
                            b b10 = cu2.f;
                            byte[] byArray = cu2.b;
                            string2 = br.h() + "/" + string;
                            File file = new File(string2);
                            file.mkdirs();
                            File file2 = new File(string2 + "/" + string + b10.ending);
                            try {
                                Throwable throwable;
                                FileOutputStream fileOutputStream;
                                block30: {
                                    fileOutputStream = new FileOutputStream(file2);
                                    throwable = null;
                                    fileOutputStream.write(byArray);
                                    if (fileOutputStream == null) break block29;
                                    if (throwable == null) break block30;
                                    try {
                                        fileOutputStream.close();
                                    }
                                    catch (Throwable throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                    break block29;
                                }
                                fileOutputStream.close();
                                break block29;
                                catch (Throwable throwable3) {
                                    try {
                                        throwable = throwable3;
                                        throw throwable3;
                                    }
                                    catch (Throwable throwable4) {
                                        block31: {
                                            block32: {
                                                try {
                                                    if (fileOutputStream == null) break block31;
                                                    if (throwable == null) break block32;
                                                }
                                                catch (Throwable throwable5) {
                                                    throw com.trolmastercard.sexmod.cu$a.a(throwable5);
                                                }
                                                try {
                                                    fileOutputStream.close();
                                                }
                                                catch (Throwable throwable6) {
                                                    throwable.addSuppressed(throwable6);
                                                }
                                                break block31;
                                            }
                                            fileOutputStream.close();
                                        }
                                        throw throwable4;
                                    }
                                }
                            }
                            catch (IOException iOException) {
                                iOException.printStackTrace();
                            }
                        }
                        n3 = 0;
                        n2 = com.trolmastercard.sexmod.cu$b.values().length;
                        for (b b11 : com.trolmastercard.sexmod.cu$b.values()) {
                            try {
                                if (!new File(string2 + "/" + string + b11.ending).exists()) continue;
                                ++n3;
                            }
                            catch (Throwable throwable) {
                                throw com.trolmastercard.sexmod.cu$a.a(throwable);
                            }
                        }
                        try {
                            if (n3 != n2) break block33;
                            this.a(String.format("%sSuccessfully downloaded the custom model '%s%s%s'!", TextFormatting.GREEN, TextFormatting.YELLOW, string, TextFormatting.GREEN));
                            break block34;
                        }
                        catch (Throwable throwable) {
                            throw com.trolmastercard.sexmod.cu$a.a(throwable);
                        }
                    }
                    this.a(String.format("%sdownloading custom model '%s%s%s' (%s/%s)...", TextFormatting.GRAY, TextFormatting.YELLOW, string, TextFormatting.GRAY, n3, n2));
                }
                try {
                    if (++a < cu2.a) {
                        return null;
                    }
                }
                catch (Throwable throwable) {
                    throw com.trolmastercard.sexmod.cu$a.a(throwable);
                }
                a = 0;
                this.a();
                return null;
            }
            MinecraftServer minecraftServer = FMLCommonHandler.instance().getMinecraftServerInstance();
            minecraftServer.func_152344_a(() -> {
                List<String> list = cu2.c;
                ArrayList<cu> arrayList = new ArrayList<cu>();
                for (String object : list) {
                    String string = "sexmod_custom_models/" + object;
                    for (b b10 : com.trolmastercard.sexmod.cu$b.values()) {
                        File file = new File(string + "/" + object + b10.ending);
                        try {
                            if (!file.exists()) {
                                System.out.println(file.getAbsolutePath() + " doesnt exist lol");
                                continue;
                            }
                        }
                        catch (IOException iOException) {
                            throw com.trolmastercard.sexmod.cu$a.a(iOException);
                        }
                        byte[] byArray = null;
                        try {
                            byArray = FileUtils.readFileToByteArray((File)file);
                        }
                        catch (IOException iOException) {
                            throw new RuntimeException(iOException);
                        }
                        try {
                            if (byArray == null) {
                                continue;
                            }
                        }
                        catch (IOException iOException) {
                            throw com.trolmastercard.sexmod.cu$a.a(iOException);
                        }
                        arrayList.add(new cu(byArray, b10, object));
                    }
                }
                int n2 = arrayList.size();
                for (cu cu3 : arrayList) {
                    cu3.a(n2);
                    minecraftServer.func_152344_a(() -> ge.b.sendTo((IMessage)cu3, messageContext.getServerHandler().field_147369_b));
                }
            });
            return null;
        }

        private static Throwable a(Throwable throwable) {
            return throwable;
        }
    }
}

