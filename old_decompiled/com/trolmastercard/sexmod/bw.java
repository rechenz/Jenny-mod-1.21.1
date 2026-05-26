/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.r;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class bw
implements IMessage {
    boolean b;
    Vec3d a;

    public bw() {
    }

    public bw(Vec3d vec3d) {
        this.a = vec3d;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.a = new Vec3d(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
        this.b = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeDouble(this.a.field_72450_a);
        byteBuf.writeDouble(this.a.field_72448_b);
        byteBuf.writeDouble(this.a.field_72449_c);
    }

    public static class a
    implements IMessageHandler<bw, IMessage> {
        public IMessage a(bw bw2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (bw2.b && messageContext.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.bw$a.a(runtimeException);
                    }
                    System.out.println("received an invalid message @MakeRichWish :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.bw$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                World world = messageContext.getServerHandler().field_147369_b.field_70170_p;
                EntityItem entityItem = new EntityItem(world, bw2.a.field_72450_a, bw2.a.field_72448_b, bw2.a.field_72449_c, new ItemStack(Items.field_151045_i, r.f.nextInt(2) + 1));
                EntityItem entityItem2 = new EntityItem(world, bw2.a.field_72450_a, bw2.a.field_72448_b, bw2.a.field_72449_c, new ItemStack(Items.field_151166_bC, r.f.nextInt(2) + 1));
                EntityItem entityItem3 = new EntityItem(world, bw2.a.field_72450_a, bw2.a.field_72448_b, bw2.a.field_72449_c, new ItemStack(Items.field_151043_k, r.f.nextInt(2) + 1));
                world.func_72838_d((Entity)entityItem);
                world.func_72838_d((Entity)entityItem2);
                world.func_72838_d((Entity)entityItem3);
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

