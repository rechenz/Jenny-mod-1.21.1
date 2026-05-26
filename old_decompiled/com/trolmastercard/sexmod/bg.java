/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ev;
import com.trolmastercard.sexmod.fp;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class bg
implements IMessage {
    boolean a = false;

    public void fromBytes(ByteBuf byteBuf) {
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
    }

    public static class a
    implements IMessageHandler<bg, IMessage> {
        public IMessage a(bg bg2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (bg2.a && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.bg$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @SummonAllie :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.bg$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                block5: {
                    fp fp2;
                    ev ev2;
                    ev ev3;
                    block4: {
                        EntityPlayerMP entityPlayerMP = messageContext.getServerHandler().field_147369_b;
                        Vec3d vec3d = entityPlayerMP.func_174791_d().func_72441_c(-Math.sin((double)entityPlayerMP.field_70759_as * (Math.PI / 180)) * 2.0, 0.0, Math.cos((double)entityPlayerMP.field_70759_as * (Math.PI / 180)) * 2.0);
                        ev3 = new ev(entityPlayerMP.field_70170_p, entityPlayerMP.func_184614_ca());
                        ev3.e(entityPlayerMP.getPersistentID());
                        ev3.func_70080_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, entityPlayerMP.field_70759_as + 180.0f, entityPlayerMP.field_70125_A);
                        ev3.c(ev3.func_174791_d());
                        ev3.b(entityPlayerMP.field_70759_as + 180.0f);
                        ev3.func_189654_d(true);
                        ev3.field_70145_X = true;
                        entityPlayerMP.field_70170_p.func_72838_d((Entity)ev3);
                        BlockPos blockPos = ev3.func_180425_c().func_177982_a(0, -1, 0);
                        try {
                            if (!ev3.field_70170_p.func_180495_p(blockPos).func_177230_c().equals(Blocks.field_150354_m)) break block4;
                            ev3.b(fp.SUMMON_SAND);
                            break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.bg$a.a(runtimeException);
                        }
                    }
                    try {
                        ev2 = ev3;
                        fp2 = ev3.f() ? fp.SUMMON : fp.SUMMON_NORMAL;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.bg$a.a(runtimeException);
                    }
                    ev2.b(fp2);
                }
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

