/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.play.server.SPacketPlayerPosLook$EnumFlags
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.EnumSet;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class a8
implements IMessage {
    boolean b;
    String c;
    Vec3d e;
    float a;
    float d;

    public a8() {
        this.b = false;
    }

    public a8(String string, Vec3d vec3d) {
        this.c = string;
        this.e = vec3d;
        this.a = 0.0f;
        this.d = 0.0f;
        this.b = true;
    }

    public a8(String string, Vec3d vec3d, float f10, float f11) {
        this.c = string;
        this.e = vec3d;
        this.a = f10;
        this.d = f11;
        this.b = true;
    }

    public a8(String string, double d10, double d11, double d12, float f10, float f11) {
        this.c = string;
        this.e = new Vec3d(d10, d11, d12);
        this.a = f10;
        this.d = f11;
        this.b = true;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.c = ByteBufUtils.readUTF8String((ByteBuf)byteBuf);
        this.e = new Vec3d(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
        this.a = byteBuf.readFloat();
        this.d = byteBuf.readFloat();
        this.b = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.c);
        byteBuf.writeDouble(this.e.field_72450_a);
        byteBuf.writeDouble(this.e.field_72448_b);
        byteBuf.writeDouble(this.e.field_72449_c);
        byteBuf.writeFloat(this.a);
        byteBuf.writeFloat(this.d);
        this.b = true;
    }

    public static class a
    implements IMessageHandler<a8, IMessage> {
        public IMessage a(a8 a82, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (a82.b && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.a8$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @TeleportPlayer :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.a8$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                try {
                    System.out.println("teleporting player " + a82.c + " to " + a82.e);
                    EntityPlayerMP entityPlayerMP = FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_177451_a(UUID.fromString(a82.c));
                    a82.a = MathHelper.func_76142_g((float)a82.a);
                    a82.d = MathHelper.func_76142_g((float)a82.d);
                    entityPlayerMP.func_70012_b(a82.e.field_72450_a, a82.e.field_72448_b, a82.e.field_72449_c, a82.a, a82.d);
                    entityPlayerMP.func_70034_d(a82.a);
                    entityPlayerMP.field_70159_w = 0.0;
                    entityPlayerMP.field_70181_x = 0.0;
                    entityPlayerMP.field_70179_y = 0.0;
                    entityPlayerMP.field_71135_a.func_175089_a(a82.e.field_72450_a, a82.e.field_72448_b, a82.e.field_72449_c, a82.a, a82.d, EnumSet.noneOf(SPacketPlayerPosLook.EnumFlags.class));
                }
                catch (Exception exception) {
                    System.out.println("couldn't find player with UUID: " + a82.c);
                    System.out.println("could only find the following players:");
                    System.out.println(FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_181058_b(true));
                }
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

