/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Sets
 *  com.google.common.collect.UnmodifiableIterator
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBed$EnumPartType
 *  net.minecraft.block.properties.PropertyDirection
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.FMLCommonHandler
 */
package com.trolmastercard.sexmod;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.gc;
import com.trolmastercard.sexmod.gj;
import com.trolmastercard.sexmod.r;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class cj {
    public static float a(float f10, float f11) {
        f10 = gc.b(f10);
        f11 = gc.b(f11);
        float f12 = Math.abs(f10 - f11);
        float f13 = 360.0f - f12;
        float f14 = Math.min(f12, f13);
        try {
            if (f10 > f11) {
                return -f14;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cj.a(runtimeException);
        }
        return f14;
    }

    public static Vec3d a(EntityLivingBase entityLivingBase, float f10) {
        World world = entityLivingBase.field_70170_p;
        try {
            if (world instanceof gj) {
                return new Vec3d(0.0, 1.0, 0.0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cj.a(runtimeException);
        }
        BlockPos blockPos = new BlockPos(Math.floor(entityLivingBase.field_70165_t), Math.floor(entityLivingBase.field_70163_u), Math.floor(entityLivingBase.field_70161_v));
        HashMap<Vec3d, Integer> hashMap = new HashMap<Vec3d, Integer>();
        int n2 = 0;
        for (int i2 = -1; i2 < 2; ++i2) {
            for (int i3 = -1; i3 < 2; ++i3) {
                for (int i4 = -1; i4 < 2; ++i4) {
                    int n3 = world.func_175721_c(blockPos.func_177982_a(i2, i3, i4), false);
                    hashMap.put(new Vec3d((double)i2, (double)i3, (double)i4), n3);
                    if (n3 <= n2) continue;
                    n2 = n3;
                }
            }
        }
        Vec3d vec3d = null;
        for (Map.Entry entry : hashMap.entrySet()) {
            try {
                if ((Integer)entry.getValue() != n2) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw cj.a(runtimeException);
            }
            if (vec3d == null) {
                vec3d = (Vec3d)entry.getKey();
                continue;
            }
            vec3d = null;
            break;
        }
        if (vec3d == null) {
            vec3d = new Vec3d(0.2, 0.8, 0.0);
        } else {
            vec3d = new Vec3d(vec3d.field_72450_a, vec3d.field_72448_b, -vec3d.field_72449_c);
            float f11 = -b6.a(entityLivingBase.field_70760_ar, entityLivingBase.field_70761_aq, f10);
            vec3d = ck.a(vec3d, f11);
        }
        return vec3d.func_72432_b();
    }

    public static int a(World world, int n2, int n3) {
        HashSet hashSet = Sets.newHashSet((Object[])new Block[]{Blocks.field_150349_c, Blocks.field_150354_m, Blocks.field_180395_cM, Blocks.field_150355_j, Blocks.field_150348_b, Blocks.field_150347_e});
        int n4 = world.func_72800_K();
        boolean bl2 = false;
        while (true) {
            try {
                if (bl2 || n4-- < 0) break;
            }
            catch (RuntimeException runtimeException) {
                throw cj.a(runtimeException);
            }
            Block block = world.func_180495_p(new BlockPos(n2, n4, n3)).func_177230_c();
            bl2 = hashSet.contains(block);
        }
        return n4;
    }

    public static BlockPos a(World world, BlockPos blockPos) {
        return new BlockPos(blockPos.func_177958_n(), cj.a(world, blockPos.func_177958_n(), blockPos.func_177952_p()), blockPos.func_177952_p());
    }

    public static boolean b(World world, BlockPos blockPos) {
        return cj.a(world, blockPos, null, null, null);
    }

    public static boolean a(World world, BlockPos blockPos, Vec3d vec3d, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        block21: {
            Object object;
            Block block;
            IBlockState iBlockState;
            block20: {
                block18: {
                    iBlockState = world.func_180495_p(blockPos);
                    block = iBlockState.func_177230_c();
                    try {
                        if (block.isBed(iBlockState, (IBlockAccess)world, blockPos, null)) {
                            return true;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw cj.a(runtimeException);
                    }
                    TileEntity tileEntity = world.func_175625_s(blockPos);
                    if (tileEntity != null) {
                        object = tileEntity.func_145748_c_();
                        try {
                            block19: {
                                try {
                                    try {
                                        if (object == null) break block18;
                                        if (object.toString().contains(" bed")) break block19;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw cj.a(runtimeException);
                                    }
                                    if (!object.toString().contains("bed ")) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw cj.a(runtimeException);
                                }
                            }
                            return true;
                        }
                        catch (RuntimeException runtimeException) {
                            throw cj.a(runtimeException);
                        }
                    }
                }
                try {
                    try {
                        if (enumFacing != null && vec3d != null) break block20;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cj.a(runtimeException);
                    }
                    return false;
                }
                catch (RuntimeException runtimeException) {
                    throw cj.a(runtimeException);
                }
            }
            object = block.getPickBlock(iBlockState, new RayTraceResult(vec3d, enumFacing), world, blockPos, entityPlayer).func_82833_r().toLowerCase();
            try {
                try {
                    if (!((String)object).contains(" bed") && !((String)object).contains("bed ")) break block21;
                }
                catch (RuntimeException runtimeException) {
                    throw cj.a(runtimeException);
                }
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw cj.a(runtimeException);
            }
        }
        return false;
    }

    public static void a(World world, EnumParticleTypes enumParticleTypes, Vec3d vec3d, int n2, double d10, double d11) {
        for (int i2 = 0; i2 < n2; ++i2) {
            float f10 = (float)i2 / (float)n2;
            double d12 = Math.PI * 2 * (double)f10;
            double d13 = Math.sin(d12);
            double d14 = Math.cos(d12);
            world.func_175688_a(enumParticleTypes, vec3d.field_72450_a + (d13 *= d10), vec3d.field_72448_b, vec3d.field_72449_c + (d14 *= d10), 0.0, (double)r.f.nextFloat() * d11, 0.0, new int[0]);
        }
    }

    public static BlockPos a(BlockPos blockPos, IBlockState iBlockState) {
        UnmodifiableIterator unmodifiableIterator;
        block22: {
            EnumFacing enumFacing;
            block20: {
                block21: {
                    ImmutableMap immutableMap = iBlockState.func_177228_b();
                    enumFacing = null;
                    BlockBed.EnumPartType enumPartType = null;
                    for (Map.Entry entry : immutableMap.entrySet()) {
                        if (entry.getKey() instanceof PropertyDirection) {
                            enumFacing = (EnumFacing)entry.getValue();
                            continue;
                        }
                        if (!(entry.getKey() instanceof PropertyEnum)) continue;
                        enumPartType = (BlockBed.EnumPartType)entry.getValue();
                    }
                    try {
                        if (enumFacing == null) {
                            System.out.println("bed is fucked up - it has no facing value");
                            return null;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw cj.a(runtimeException);
                    }
                    try {
                        if (enumPartType == null) {
                            System.out.println("bed is fucked up - it has no partType value");
                            return null;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw cj.a(runtimeException);
                    }
                    unmodifiableIterator = null;
                    try {
                        if (enumPartType != BlockBed.EnumPartType.FOOT) break block20;
                        if (enumFacing != EnumFacing.NORTH) break block21;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cj.a(runtimeException);
                    }
                    unmodifiableIterator = blockPos.func_177978_c();
                }
                if (enumFacing == EnumFacing.EAST) {
                    unmodifiableIterator = blockPos.func_177974_f();
                }
                if (enumFacing == EnumFacing.SOUTH) {
                    unmodifiableIterator = blockPos.func_177968_d();
                }
                if (enumFacing == EnumFacing.WEST) {
                    unmodifiableIterator = blockPos.func_177976_e();
                }
                break block22;
            }
            if (enumFacing == EnumFacing.NORTH) {
                unmodifiableIterator = blockPos.func_177968_d();
            }
            if (enumFacing == EnumFacing.EAST) {
                unmodifiableIterator = blockPos.func_177976_e();
            }
            if (enumFacing == EnumFacing.SOUTH) {
                unmodifiableIterator = blockPos.func_177978_c();
            }
            if (enumFacing == EnumFacing.WEST) {
                unmodifiableIterator = blockPos.func_177974_f();
            }
        }
        try {
            if (unmodifiableIterator == null) {
                System.out.println("bed is fucked up - it appears to be positioned vertically (wtf?)");
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cj.a(runtimeException);
        }
        return unmodifiableIterator;
    }

    public static Set<? extends EntityPlayer> a(Entity entity) {
        try {
            if (entity == null) {
                return Collections.emptySet();
            }
        }
        catch (RuntimeException runtimeException) {
            throw cj.a(runtimeException);
        }
        return FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(entity.field_71093_bK).func_73039_n().getTrackingPlayers(entity);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

