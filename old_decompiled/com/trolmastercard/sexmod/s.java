/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
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
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class s
implements IMessage {
    boolean b;
    UUID c;
    boolean a;

    public s() {
        this.b = false;
    }

    public s(UUID uUID) {
        this.c = uUID;
        this.a = false;
        this.b = true;
    }

    public s(UUID uUID, boolean bl2) {
        this.c = uUID;
        this.a = bl2;
        this.b = true;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.c = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.a = byteBuf.readBoolean();
        this.b = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.c.toString());
        byteBuf.writeBoolean(this.a);
        this.b = true;
    }

    public static class a
    implements IMessageHandler<s, IMessage> {
        public static void a(em em2) {
            Vec3d vec3d;
            World world;
            block7: {
                try {
                    em2.g();
                    if (!(em2 instanceof ei) || em2.field_70170_p.func_152378_a(((ei)em2).m()) == null) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.s$a.a(runtimeException);
                }
                ge.b.sendTo((IMessage)new gz(true), (EntityPlayerMP)FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(em2.field_71093_bK).func_152378_a(((ei)em2).m()));
                em2.func_184212_Q().func_187227_b(em.D, (Object)1);
                world = em2.field_70170_p.func_152378_a(((ei)em2).m());
                world.field_71075_bZ.field_75100_b = false;
                world.func_189654_d(false);
                world.field_70145_X = false;
                em2.a(false);
                em2.b(fp.NULL);
                if (em2.ae() != null) {
                    vec3d = em2.field_70170_p.func_152378_a(em2.ae());
                    try {
                        if (vec3d != null) {
                            vec3d.field_71075_bZ.field_75100_b = false;
                            vec3d.func_189654_d(false);
                            vec3d.field_70145_X = false;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.s$a.a(runtimeException);
                    }
                }
            }
            em2.a(false);
            em2.e((UUID)null);
            em2.B = null;
            em2.func_189654_d(false);
            em2.field_70145_X = false;
            world = em2.field_70170_p;
            vec3d = em2.func_174791_d();
            while (world.func_180495_p(new BlockPos(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c)).func_177230_c() != Blocks.field_150350_a) {
                vec3d = vec3d.func_72441_c(0.0, 1.0, 0.0);
            }
            em2.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        }

        public static void a(EntityPlayerMP entityPlayerMP) {
            try {
                if (entityPlayerMP == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.s$a.a(runtimeException);
            }
            World world = entityPlayerMP.field_70170_p;
            Vec3d vec3d = entityPlayerMP.func_174791_d();
            while (world.func_180495_p(new BlockPos(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c)).func_177230_c() != Blocks.field_150350_a) {
                vec3d = vec3d.func_72441_c(0.0, 1.0, 0.0);
            }
            entityPlayerMP.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
            entityPlayerMP.func_82142_c(false);
            entityPlayerMP.field_70145_X = false;
            entityPlayerMP.func_189654_d(false);
            entityPlayerMP.field_71075_bZ.field_75100_b = false;
            ge.b.sendTo((IMessage)new gz(true), entityPlayerMP);
        }

        public IMessage a(s s2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (s2.b && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.s$a.a(runtimeException);
                    }
                    System.out.println("recieved an unvalid message @ResetGirl :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.s$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                ArrayList<em> arrayList = em.g(s2.c);
                for (em em2 : arrayList) {
                    try {
                        if (em2.field_70170_p.field_72995_K) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.s$a.a(runtimeException);
                    }
                    try {
                        if (em2.ae() != null) {
                            com.trolmastercard.sexmod.s$a.a(FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_177451_a(em2.ae()));
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.s$a.a(runtimeException);
                    }
                    try {
                        if (s2.a) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.s$a.a(runtimeException);
                    }
                    com.trolmastercard.sexmod.s$a.a(em2);
                }
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

