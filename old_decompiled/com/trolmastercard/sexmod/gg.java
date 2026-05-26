/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ho;
import com.trolmastercard.sexmod.r;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class gg
implements IMessage {
    boolean b;
    UUID a;

    public gg() {
    }

    public gg(UUID uUID) {
        this.a = uUID;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.a = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.b = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.a.toString());
    }

    public static class a
    implements IMessageHandler<gg, IMessage> {
        public IMessage a(gg gg2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (gg2.b && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gg$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @SendCompanionHome :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gg$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                ArrayList<em> arrayList = em.g(gg2.a);
                for (em em2 : arrayList) {
                    try {
                        if (em2.field_70170_p.field_72995_K) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gg$a.a(runtimeException);
                    }
                    try {
                        if (em2.y() != fp.THROW_PEARL) {
                            em2.b(fp.THROW_PEARL);
                            em2.b((float)Math.atan2(em2.field_70161_v - em2.l.field_72449_c, em2.field_70165_t - em2.l.field_72450_a) * 57.29578f + 90.0f);
                            em2.c(em2.func_174791_d());
                            em2.func_184212_Q().func_187227_b(em.G, (Object)true);
                            em2.q = null;
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gg$a.a(runtimeException);
                    }
                    if (em2.q == null) {
                        float f10 = (float)em2.func_174791_d().func_72438_d(em2.l);
                        em2.q = new ho(em2.field_70170_p, (EntityLivingBase)em2);
                        em2.q.func_70186_c(em2.l.field_72450_a - em2.field_70165_t, em2.l.field_72448_b - em2.field_70163_u, em2.l.field_72449_c - em2.field_70161_v, Math.min(4.0f, f10 * 0.1f), 0.0f);
                        em2.field_70170_p.func_72838_d((Entity)em2.q);
                        continue;
                    }
                    WorldServer worldServer = (WorldServer)em2.field_70170_p;
                    try {
                        for (int i2 = 0; i2 < 32; ++i2) {
                            worldServer.func_180505_a(EnumParticleTypes.PORTAL, false, em2.field_70165_t, em2.field_70163_u + r.f.nextDouble() * 2.0, em2.field_70161_v, 32, 0.2, 0.2, 0.2, r.f.nextGaussian(), new int[0]);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.gg$a.a(runtimeException);
                    }
                    em2.func_70107_b(em2.l.field_72450_a, em2.l.field_72448_b, em2.l.field_72449_c);
                    em2.q = null;
                    em2.b(fp.NULL);
                    em2.func_184212_Q().func_187227_b(em.G, (Object)false);
                    em2.x();
                }
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

