/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.trolmastercard.sexmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class dw
extends BlockFire {
    public static final Block a = new dw();

    public void func_180650_b(World world, BlockPos blockPos, IBlockState iBlockState, Random random) {
    }

    public static void a() {
        a.setRegistryName("sexmod", "fire");
        a.func_149663_c("fire");
        MinecraftForge.EVENT_BUS.register(dw.class);
    }

    @SubscribeEvent
    public static void a(RegistryEvent.Register<Block> register) {
        register.getRegistry().register((IForgeRegistryEntry)a);
    }
}

