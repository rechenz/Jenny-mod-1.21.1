/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gm;
import io.netty.buffer.ByteBuf;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class h6
implements IMessage {
    boolean b = false;
    HashSet<BlockPos> c = new HashSet();
    boolean a;

    public h6() {
    }

    public h6(HashSet<BlockPos> hashSet, boolean bl2) {
        this.c = hashSet;
        this.a = bl2;
    }

    public h6(BlockPos blockPos, boolean bl2) {
        this.c.add(blockPos);
        this.a = bl2;
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.a = byteBuf.readBoolean();
        int n2 = byteBuf.readInt();
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                this.c.add(new BlockPos(byteBuf.readInt(), byteBuf.readInt(), byteBuf.readInt()));
            }
        }
        catch (RuntimeException runtimeException) {
            throw h6.a(runtimeException);
        }
        this.b = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(this.a);
        byteBuf.writeInt(this.c.size());
        for (BlockPos blockPos : this.c) {
            byteBuf.writeInt(blockPos.func_177958_n());
            byteBuf.writeInt(blockPos.func_177956_o());
            byteBuf.writeInt(blockPos.func_177952_p());
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a
    implements IMessageHandler<h6, IMessage> {
        public IMessage a(h6 h62, MessageContext messageContext) {
            block7: {
                block9: {
                    block8: {
                        try {
                            if (!h62.b) {
                                System.out.println("received an invalid Message @SendBlocks :(");
                                return null;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                        }
                        try {
                            try {
                                if (!messageContext.side.isClient()) break block7;
                                if (!h62.a) break block8;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                            }
                            gm.a(h62.c);
                            break block9;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                        }
                    }
                    gm.b(h62.c);
                }
                return null;
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
                UUID uUID = messageContext.getServerHandler().field_147369_b.getPersistentID();
                UUID uUID2 = ax.a(uUID);
                try {
                    if (uUID2 == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                }
                try {
                    if (h62.c.size() != 1) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                }
                World world = messageContext.getServerHandler().field_147369_b.field_70170_p;
                for (BlockPos blockPos : h62.c) {
                    Object object;
                    BlockPos blockPos2;
                    block37: {
                        block38: {
                            IBlockState iBlockState;
                            block35: {
                                block36: {
                                    block34: {
                                        block33: {
                                            iBlockState = world.func_180495_p(blockPos);
                                            blockPos2 = null;
                                            if (iBlockState.func_177230_c() instanceof BlockBed) {
                                                blockPos2 = cj.a(blockPos, iBlockState);
                                            }
                                            if (iBlockState.func_177230_c() instanceof BlockChest) {
                                                block32: {
                                                    block31: {
                                                        block30: {
                                                            object = ((BlockChest)iBlockState.func_177230_c()).field_149956_a;
                                                            try {
                                                                if (!(world.func_180495_p(blockPos.func_177978_c()).func_177230_c() instanceof BlockChest) || !object.equals((Object)((BlockChest)world.func_180495_p((BlockPos)blockPos.func_177978_c()).func_177230_c()).field_149956_a)) break block30;
                                                            }
                                                            catch (RuntimeException runtimeException) {
                                                                throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                                                            }
                                                            blockPos2 = blockPos.func_177978_c();
                                                        }
                                                        try {
                                                            if (!(world.func_180495_p(blockPos.func_177974_f()).func_177230_c() instanceof BlockChest) || !object.equals((Object)((BlockChest)world.func_180495_p((BlockPos)blockPos.func_177974_f()).func_177230_c()).field_149956_a)) break block31;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                                                        }
                                                        blockPos2 = blockPos.func_177974_f();
                                                    }
                                                    try {
                                                        if (!(world.func_180495_p(blockPos.func_177968_d()).func_177230_c() instanceof BlockChest) || !object.equals((Object)((BlockChest)world.func_180495_p((BlockPos)blockPos.func_177968_d()).func_177230_c()).field_149956_a)) break block32;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                                                    }
                                                    blockPos2 = blockPos.func_177968_d();
                                                }
                                                try {
                                                    if (!(world.func_180495_p(blockPos.func_177976_e()).func_177230_c() instanceof BlockChest) || !object.equals((Object)((BlockChest)world.func_180495_p((BlockPos)blockPos.func_177976_e()).func_177230_c()).field_149956_a)) break block33;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                                                }
                                                blockPos2 = blockPos.func_177976_e();
                                            }
                                        }
                                        try {
                                            try {
                                                if (blockPos2 != null || !(iBlockState.func_177230_c() instanceof BlockBed)) break block34;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                                            }
                                            return;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                                        }
                                    }
                                    try {
                                        try {
                                            if (!h62.a) break block35;
                                            if (!(iBlockState.func_177230_c() instanceof BlockBed)) break block36;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                                        }
                                        ax.a(uUID2, blockPos);
                                        ax.a(uUID2, blockPos2);
                                        break block37;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                                    }
                                }
                                ax.f(uUID2, blockPos);
                                ax.f(uUID2, blockPos2);
                                break block37;
                            }
                            try {
                                if (!(iBlockState.func_177230_c() instanceof BlockBed)) break block38;
                                ax.e(uUID2, blockPos);
                                ax.e(uUID2, blockPos2);
                                break block37;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                            }
                        }
                        ax.d(uUID2, blockPos);
                        ax.d(uUID2, blockPos2);
                    }
                    object = new HashSet();
                    try {
                        ((HashSet)object).add(blockPos);
                        if (blockPos2 != null) {
                            ((HashSet)object).add(blockPos2);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.h6$a.a(runtimeException);
                    }
                    ge.b.sendTo((IMessage)new h6((HashSet<BlockPos>)object, h62.a), messageContext.getServerHandler().field_147369_b);
                }
            });
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

