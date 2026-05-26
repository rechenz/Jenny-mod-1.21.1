/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.bs;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.h6;
import io.netty.buffer.ByteBuf;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class e6
implements IMessage {
    boolean c = false;
    BlockPos a;
    EnumFacing b;

    public e6() {
    }

    public e6(BlockPos blockPos, EnumFacing enumFacing) {
        this.a = blockPos;
        this.b = enumFacing;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.a = new BlockPos(byteBuf.readInt(), byteBuf.readInt(), byteBuf.readInt());
        this.b = EnumFacing.func_176739_a((String)ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.c = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(this.a.func_177958_n());
        byteBuf.writeInt(this.a.func_177956_o());
        byteBuf.writeInt(this.a.func_177952_p());
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.b.func_176610_l());
    }

    public static class a
    implements IMessageHandler<e6, IMessage> {
        public IMessage a(e6 e62, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (e62.c && messageContext.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.e6$a.a(runtimeException);
                    }
                    System.out.println("received an invalid Message @Mine :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.e6$a.a(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                EntityPlayerMP entityPlayerMP = messageContext.getServerHandler().field_147369_b;
                UUID uUID = ax.a(entityPlayerMP.getPersistentID());
                try {
                    if (uUID == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.e6$a.a(runtimeException);
                }
                int n2 = ax.h(uUID);
                int n3 = (int)Math.floor((double)ax.j(uUID).size() / 2.0);
                try {
                    if (n2 > n3) {
                        entityPlayerMP.func_145747_a((ITextComponent)new TextComponentString(String.format("sUr Tribe will only work for you, if %severyone%s of them has a %sbed", TextFormatting.RED, TextFormatting.WHITE, TextFormatting.RED)));
                        entityPlayerMP.func_145747_a((ITextComponent)new TextComponentString(String.format("%s%d/%d Beds", TextFormatting.YELLOW, n3, n2)));
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.e6$a.a(runtimeException);
                }
                HashSet<BlockPos> hashSet = this.a(e62.a, e62.b);
                World world = messageContext.getServerHandler().field_147369_b.field_70170_p;
                for (BlockPos blockPos : hashSet) {
                    IBlockState iBlockState = world.func_180495_p(blockPos);
                    try {
                        if (!(iBlockState.func_177230_c().func_176195_g(iBlockState, world, blockPos) < 0.0f)) continue;
                        entityPlayerMP.func_146105_b((ITextComponent)new TextComponentString("This area contains Bedrock and cannot be mined"), true);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.e6$a.a(runtimeException);
                    }
                }
                bs bs2 = new bs(e62.a, bs.a.MINE, hashSet, e62.b);
                ax.b(uUID, bs2);
                ge.b.sendTo((IMessage)new h6(hashSet, true), messageContext.getServerHandler().field_147369_b);
            });
            return null;
        }

        HashSet<BlockPos> a(BlockPos blockPos, EnumFacing enumFacing) {
            HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
            BlockPos blockPos2 = blockPos;
            for (int i2 = 0; i2 < 30; ++i2) {
                hashSet.add(blockPos2.func_177973_b((Vec3i)this.a(enumFacing)));
                hashSet.add(blockPos2.func_177973_b((Vec3i)this.a(enumFacing)).func_177984_a());
                hashSet.add(blockPos2.func_177973_b((Vec3i)this.a(enumFacing)).func_177984_a().func_177984_a());
                hashSet.add(blockPos2);
                hashSet.add(blockPos2.func_177984_a());
                hashSet.add(blockPos2.func_177984_a().func_177984_a());
                hashSet.add(blockPos2.func_177971_a((Vec3i)this.a(enumFacing)));
                hashSet.add(blockPos2.func_177971_a((Vec3i)this.a(enumFacing)).func_177984_a());
                hashSet.add(blockPos2.func_177971_a((Vec3i)this.a(enumFacing)).func_177984_a().func_177984_a());
                blockPos2 = blockPos2.func_177971_a(enumFacing.func_176730_m());
            }
            return hashSet;
        }

        BlockPos a(EnumFacing enumFacing) {
            Vec3i vec3i = enumFacing.func_176730_m();
            return new BlockPos(vec3i.func_177952_p(), vec3i.func_177956_o(), -vec3i.func_177958_n());
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

