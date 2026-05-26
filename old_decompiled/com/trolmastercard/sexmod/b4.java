/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Rotation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraft.world.gen.structure.template.Template
 *  net.minecraft.world.gen.structure.template.TemplateManager
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.d7;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class b4
extends WorldGenerator
implements d7 {
    public String c;

    public b4(String string) {
        this.c = string;
    }

    public void a(World world, BlockPos blockPos) {
        ResourceLocation resourceLocation;
        MinecraftServer minecraftServer = world.func_73046_m();
        TemplateManager templateManager = b.func_184163_y();
        Template template = templateManager.func_189942_b(minecraftServer, resourceLocation = new ResourceLocation("sexmod", this.c));
        if (template != null) {
            IBlockState iBlockState = world.func_180495_p(blockPos);
            world.func_184138_a(blockPos, iBlockState, iBlockState, 3);
            template.func_186253_b(world, blockPos, a);
        }
    }

    public void a(World world, BlockPos blockPos, Rotation rotation) {
        ResourceLocation resourceLocation;
        MinecraftServer minecraftServer = world.func_73046_m();
        TemplateManager templateManager = b.func_184163_y();
        Template template = templateManager.func_189942_b(minecraftServer, resourceLocation = new ResourceLocation("sexmod", this.c));
        if (template != null) {
            IBlockState iBlockState = world.func_180495_p(blockPos);
            world.func_184138_a(blockPos, iBlockState, iBlockState, 2);
            template.func_186253_b(world, blockPos, a.func_186220_a(rotation));
        }
    }

    public boolean func_180709_b(World world, Random random, BlockPos blockPos) {
        this.a(world, blockPos);
        return true;
    }
}

