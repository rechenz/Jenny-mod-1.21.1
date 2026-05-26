/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.GameType
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.biome.Biome$BiomeProperties
 *  net.minecraft.world.biome.BiomePlains
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.f5;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class gj
extends WorldClient {
    public Biome getBiomeForCoordsBody(BlockPos blockPos) {
        return new BiomePlains(false, new Biome.BiomeProperties("Plains").func_185398_c(0.125f).func_185400_d(0.05f).func_185400_d(0.8f).func_185395_b(0.4f));
    }

    public void func_175685_c(BlockPos blockPos, Block block, boolean bl2) {
        super.func_175685_c(blockPos, block, bl2);
    }

    public void markAndNotifyBlock(BlockPos blockPos, Chunk chunk, IBlockState iBlockState, IBlockState iBlockState2, int n2) {
    }

    public float getSunBrightnessFactor(float f10) {
        return 1.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public float getSunBrightnessBody(float f10) {
        return 1.0f;
    }

    public void updateWeatherBody() {
    }

    public boolean canBlockFreezeBody(BlockPos blockPos, boolean bl2) {
        return false;
    }

    public boolean canSnowAtBody(BlockPos blockPos, boolean bl2) {
        return false;
    }

    public gj() {
        super((NetHandlerPlayClient)new f5(Minecraft.func_71410_x()), new WorldSettings(0L, GameType.SURVIVAL, false, false, WorldType.field_77138_c), 0, EnumDifficulty.HARD, new Profiler());
        this.field_73011_w.func_76558_a((World)this);
    }

    public boolean canMineBlockBody(EntityPlayer entityPlayer, BlockPos blockPos) {
        return false;
    }

    public boolean isSideSolid(BlockPos blockPos, EnumFacing enumFacing) {
        boolean bl2;
        try {
            bl2 = blockPos.func_177956_o() <= 63;
        }
        catch (RuntimeException runtimeException) {
            throw gj.a(runtimeException);
        }
        return bl2;
    }

    public boolean isSideSolid(BlockPos blockPos, EnumFacing enumFacing, boolean bl2) {
        boolean bl3;
        try {
            bl3 = blockPos.func_177956_o() <= 63;
        }
        catch (RuntimeException runtimeException) {
            throw gj.a(runtimeException);
        }
        return bl3;
    }

    public int countEntities(EnumCreatureType enumCreatureType, boolean bl2) {
        return 0;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

