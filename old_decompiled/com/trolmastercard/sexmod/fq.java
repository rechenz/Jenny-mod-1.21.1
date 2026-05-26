/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.WorldSavedData
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class fq
extends WorldSavedData {
    public static final List<BlockPos> c = new ArrayList<BlockPos>();
    public static final List<BlockPos> b = new ArrayList<BlockPos>();
    static final String d = "sexmod:galath_spawn_list";
    static final String a = "sexmod:galath_spawn_list";

    public fq() {
        super("sexmod:galath_spawn_list");
    }

    public fq(String string) {
        super("sexmod:galath_spawn_list");
    }

    public static void a(BlockPos blockPos, List<BlockPos> list) {
        list.add(blockPos);
    }

    @SubscribeEvent
    public void a(WorldEvent.Save save) {
        World world = save.getWorld();
        world.func_175693_T().func_75745_a("sexmod:galath_spawn_list", (WorldSavedData)this);
        this.func_76185_a();
    }

    @SubscribeEvent
    public void a(WorldEvent.Load load) {
        World world = load.getWorld();
        world.func_175693_T().func_75742_a(fq.class, "sexmod:galath_spawn_list");
    }

    public void func_76184_a(NBTTagCompound nBTTagCompound) {
        NBTTagCompound nBTTagCompound2 = nBTTagCompound.func_74775_l("sexmod:galath_spawn_list");
        this.b(nBTTagCompound2, "", c);
        this.b(nBTTagCompound2, "mang", b);
    }

    public NBTTagCompound func_189551_b(NBTTagCompound nBTTagCompound) {
        NBTTagCompound nBTTagCompound2 = new NBTTagCompound();
        this.a(nBTTagCompound2, "", c);
        this.a(nBTTagCompound2, "mang", b);
        nBTTagCompound.func_74782_a("sexmod:galath_spawn_list", (NBTBase)nBTTagCompound2);
        return nBTTagCompound;
    }

    void a(NBTTagCompound nBTTagCompound, String string, List<BlockPos> list) {
        nBTTagCompound.func_74768_a("sexmod:pos_amount" + string, list.size());
        int n2 = 0;
        for (BlockPos blockPos : list) {
            nBTTagCompound.func_74768_a("sexmod:x" + string + n2, blockPos.func_177958_n());
            nBTTagCompound.func_74768_a("sexmod:y" + string + n2, blockPos.func_177956_o());
            nBTTagCompound.func_74768_a("sexmod:z" + string + n2, blockPos.func_177952_p());
            ++n2;
        }
    }

    void b(NBTTagCompound nBTTagCompound, String string, List<BlockPos> list) {
        list.clear();
        int n2 = nBTTagCompound.func_74762_e("sexmod:pos_amount" + string);
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                list.add(new BlockPos(nBTTagCompound.func_74762_e("sexmod:x" + string + i2), nBTTagCompound.func_74762_e("sexmod:y" + string + i2), nBTTagCompound.func_74762_e("sexmod:z" + string + i2)));
            }
        }
        catch (RuntimeException runtimeException) {
            throw fq.a(runtimeException);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

