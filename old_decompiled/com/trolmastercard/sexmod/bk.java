/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.v;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class bk
implements IMessage {
    boolean a = false;

    public void fromBytes(ByteBuf byteBuf) {
        this.a = true;
    }

    public void toBytes(ByteBuf byteBuf) {
    }

    public static class a
    implements IMessageHandler<bk, IMessage> {
        public IMessage a(bk bk2, MessageContext messageContext) {
            block7: {
                try {
                    try {
                        if (bk2.a && messageContext.side.equals((Object)Side.SERVER)) break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.bk$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @RequestRiding :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.bk$a.a(runtimeException);
                }
            }
            EntityPlayerMP entityPlayerMP = messageContext.getServerHandler().field_147369_b;
            UUID uUID = v.b((EntityPlayer)entityPlayerMP);
            em em2 = em.a(uUID);
            try {
                if (em2 == null) {
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.bk$a.a(runtimeException);
            }
            entityPlayerMP.func_184205_a((Entity)em2, true);
            em2.b(fp.CONTROLLED_FLIGHT);
            em2.a((EntityPlayer)entityPlayerMP);
            em2.field_70181_x = 0.25;
            entityPlayerMP.field_70170_p.func_175726_f(em2.func_180425_c()).func_76622_b((Entity)em2);
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

