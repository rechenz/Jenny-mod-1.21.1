/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Biomes
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.Rotation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.IChunkGenerator
 *  net.minecraft.world.storage.WorldSavedData
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.b4;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.e1;
import com.trolmastercard.sexmod.e3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class g3
extends WorldSavedData
implements IWorldGenerator {
    static final String j = "sexmod:generation";
    static final int h = 156;
    static final int a = 62;
    static final int b = 6;
    final double f = 0.004f;
    public static boolean i = true;
    final List<b> e = new ArrayList<b>();
    final List<a> d = new ArrayList<a>();
    private static g3 g = null;
    static boolean c = true;

    public static g3 b() {
        try {
            if (g == null) {
                g = new g3();
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        return g;
    }

    public g3(String string) {
        this();
    }

    private g3() {
        super(j);
        g = this;
        this.e.add(new b("ellie", new HashSet<Biome>(Arrays.asList(Biomes.field_150578_U, Biomes.field_150584_S, Biomes.field_76768_g, Biomes.field_150585_R)), new Vec3i(30, 27, 26), 9, true));
        this.e.add(new b("jenny", new HashSet<Biome>(Arrays.asList(Biomes.field_76772_c, Biomes.field_76767_f)), new Vec3i(9, 4, 9), 1, true));
        this.e.add(new b("ellie", new HashSet<Biome>(Arrays.asList(Biomes.field_150578_U, Biomes.field_150584_S, Biomes.field_76768_g, Biomes.field_150585_R)), new Vec3i(30, 27, 26), 9, true));
        this.e.add(new b("bia", new HashSet<Biome>(Arrays.asList(Biomes.field_185448_Z, Biomes.field_150583_P)), new Vec3i(11, 9, 15), 2, true));
        this.e.add(new b("luna", new HashSet<Biome>(Arrays.asList(Biomes.field_76771_b, Biomes.field_150575_M)), new Vec3i(3, 7, 10), 0, false));
    }

    public void a() {
        this.d.clear();
    }

    @SubscribeEvent
    public void a(WorldEvent.Save save) {
        World world = save.getWorld();
        world.func_175693_T().func_75745_a(j, (WorldSavedData)this);
        this.func_76185_a();
    }

    @SubscribeEvent
    public void a(WorldEvent.Load load) {
        World world = load.getWorld();
        world.func_175693_T().func_75742_a(g3.class, j);
    }

    public void func_76184_a(NBTTagCompound nBTTagCompound) {
        this.a();
        NBTTagCompound nBTTagCompound2 = nBTTagCompound.func_74775_l(j);
        int n2 = 0;
        while (true) {
            String string = nBTTagCompound2.func_74779_i("sexmod:name" + n2);
            String string2 = nBTTagCompound2.func_74779_i("sexmod:pos" + n2);
            try {
                if ("".equals(string)) {
                    break;
                }
            }
            catch (RuntimeException runtimeException) {
                throw g3.a(runtimeException);
            }
            try {
                if ("".equals(string2)) {
                    break;
                }
            }
            catch (RuntimeException runtimeException) {
                throw g3.a(runtimeException);
            }
            this.d.add(new a(g3.a(string2), string));
            ++n2;
        }
    }

    public NBTTagCompound func_189551_b(NBTTagCompound nBTTagCompound) {
        nBTTagCompound.func_74782_a(j, (NBTBase)new NBTTagCompound());
        NBTTagCompound nBTTagCompound2 = new NBTTagCompound();
        int n2 = 0;
        for (a a10 : this.d) {
            nBTTagCompound2.func_74778_a("sexmod:name" + n2, a10.a);
            nBTTagCompound2.func_74778_a("sexmod:pos" + n2++, g3.a(a10.b));
        }
        nBTTagCompound.func_74782_a(j, (NBTBase)nBTTagCompound2);
        return nBTTagCompound;
    }

    static String a(e1 e12) {
        return e12.c + "|" + e12.b;
    }

    static e1 a(String string) {
        String[] stringArray = string.split("\\|");
        return new e1(Integer.parseInt(stringArray[0]), Integer.parseInt(stringArray[1]));
    }

    public void generate(Random random, int n2, int n3, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider) {
        try {
            if (!i) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        try {
            if (world.func_175624_G() == WorldType.field_77138_c) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        this.b(world, random, n2, n3);
        this.a(world, random, n2, n3);
        this.a(random, n2, n3, world);
    }

    void a(Random random, int n2, int n3, World world) {
        try {
            if (!c) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        c = false;
        for (b b10 : this.e) {
            this.a(b10, random, n2, n3, world);
        }
        c = true;
    }

    void a(b b10, Random random, int n2, int n3, World world) {
        int n4;
        int n5;
        int n6;
        int n7;
        for (a a10 : this.d) {
            int n8;
            try {
                n8 = a10.a.equals(b10.f) ? 156 : 62;
            }
            catch (RuntimeException runtimeException) {
                throw g3.a(runtimeException);
            }
            n7 = n8;
            try {
                if (!(a10.b.a(n2, n3) < (float)n7)) continue;
                return;
            }
            catch (RuntimeException runtimeException) {
                throw g3.a(runtimeException);
            }
        }
        int n9 = b10.c.func_177958_n();
        int n10 = b10.c.func_177952_p();
        n7 = n2 * 16 + (16 - n9) / 2;
        int n11 = n3 * 16 + (16 - n10) / 2;
        Biome biome = world.field_73011_w.getBiomeForCoords(new BlockPos(n7, 80, n11));
        try {
            if (!b10.e.contains(biome)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        int n12 = Integer.MIN_VALUE;
        int n13 = Integer.MAX_VALUE;
        for (n6 = n7; n6 < n7 + n9; ++n6) {
            for (n5 = n11; n5 < n11 + n10; ++n5) {
                block29: {
                    n4 = cj.a(world, n6, n5);
                    try {
                        try {
                            if (!b10.d || world.func_180495_p(new BlockPos(n6, n4, n5)).func_177230_c() != Blocks.field_150355_j) break block29;
                        }
                        catch (RuntimeException runtimeException) {
                            throw g3.a(runtimeException);
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw g3.a(runtimeException);
                    }
                }
                if (n4 > n12) {
                    n12 = n4;
                }
                if (n4 >= n13) continue;
                n13 = n4;
            }
        }
        try {
            if (n12 - n13 > b10.a) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        n6 = n12;
        try {
            this.d.add(new a(new e1(n2, n3), b10.f));
            b10.b.func_180709_b(world, random, new BlockPos(n7, n6, n11));
            if (!b10.d) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        n5 = 1;
        n4 = n6 - 1;
        while (n5 != 0) {
            n5 = 0;
            Vec3i vec3i = new Vec3i(n9 + 2, 0, n10 + 2);
            --n11;
            for (int i2 = --n7; i2 < n7 + vec3i.func_177958_n(); ++i2) {
                for (int i3 = n11; i3 < n11 + vec3i.func_177952_p(); ++i3) {
                    IBlockState iBlockState;
                    BlockPos blockPos = new BlockPos(i2, n4, i3);
                    IBlockState iBlockState2 = world.func_180495_p(blockPos);
                    try {
                        if (!iBlockState2.func_177230_c().func_176205_b((IBlockAccess)world, blockPos)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw g3.a(runtimeException);
                    }
                    try {
                        iBlockState = world.func_175678_i(blockPos) ? Blocks.field_150349_c.func_176223_P() : Blocks.field_150346_d.func_176223_P();
                    }
                    catch (RuntimeException runtimeException) {
                        throw g3.a(runtimeException);
                    }
                    iBlockState2 = iBlockState;
                    world.func_175656_a(blockPos, iBlockState2);
                    n5 = 1;
                }
            }
            --n4;
        }
    }

    void b(World world, Random random, int n2, int n3) {
        try {
            if (random.nextDouble() > (double)0.004f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        int n4 = n2 * 16 + 8;
        int n5 = n3 * 16 + 8;
        int n6 = cj.a(world, n4, n5);
        try {
            if (world.func_180495_p(new BlockPos(n4, n6, n5)).func_185904_a().func_76224_d()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        ax.a(world, new Vec3d((double)n4, (double)n6, (double)n5));
    }

    void a(World world, Random random, int n2, int n3) {
        Vec3d vec3d;
        Material material;
        BlockPos blockPos;
        ArrayList<BlockPos> arrayList;
        BlockPos blockPos2;
        block49: {
            int n4 = 16 * n2 + 3;
            int n5 = 16 * n3 + 3;
            int n6 = random.nextInt(255);
            blockPos2 = new BlockPos(n4, n6, n5);
            arrayList = new ArrayList<BlockPos>();
            for (int i2 = 0; i2 <= e3.ah.func_177958_n(); ++i2) {
                for (int i3 = -1; i3 <= e3.ah.func_177956_o(); ++i3) {
                    for (int i4 = 0; i4 <= e3.ah.func_177952_p(); ++i4) {
                        block46: {
                            blockPos = blockPos2.func_177982_a(i2, i3, i4);
                            material = world.func_180495_p(blockPos).func_185904_a();
                            boolean bl2 = material.func_76220_a();
                            try {
                                block47: {
                                    try {
                                        try {
                                            if (bl2) break block46;
                                            if (i3 == -1) break block47;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw g3.a(runtimeException);
                                        }
                                        if (i3 != e3.ah.func_177956_o()) break block46;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw g3.a(runtimeException);
                                    }
                                }
                                return;
                            }
                            catch (RuntimeException runtimeException) {
                                throw g3.a(runtimeException);
                            }
                        }
                        try {
                            try {
                                try {
                                    try {
                                        block48: {
                                            try {
                                                try {
                                                    try {
                                                        if (i2 == 0 || i2 == e3.ah.func_177958_n()) break block48;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw g3.a(runtimeException);
                                                    }
                                                    if (i4 == 0) break block48;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw g3.a(runtimeException);
                                                }
                                                if (i4 != e3.ah.func_177952_p()) continue;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw g3.a(runtimeException);
                                            }
                                        }
                                        if (i3 != 0) continue;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw g3.a(runtimeException);
                                    }
                                    if (!world.func_175623_d(blockPos)) continue;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw g3.a(runtimeException);
                                }
                                if (!world.func_175623_d(blockPos.func_177984_a())) continue;
                            }
                            catch (RuntimeException runtimeException) {
                                throw g3.a(runtimeException);
                            }
                            arrayList.add(blockPos);
                            continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw g3.a(runtimeException);
                        }
                    }
                }
            }
            try {
                try {
                    if (arrayList.size() != 0 && arrayList.size() <= 4) break block49;
                }
                catch (RuntimeException runtimeException) {
                    throw g3.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw g3.a(runtimeException);
            }
        }
        BlockPos blockPos3 = null;
        Rotation rotation = arrayList.iterator();
        while (rotation.hasNext()) {
            BlockPos blockPos4;
            blockPos = blockPos4 = (BlockPos)rotation.next();
            material = blockPos2.func_177982_a(6, 0, 6);
            blockPos = blockPos.func_177973_b((Vec3i)material);
            try {
                if (Math.abs(blockPos.func_177958_n()) == Math.abs(blockPos.func_177952_p())) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw g3.a(runtimeException);
            }
            try {
                if (Math.abs(blockPos.func_177958_n()) == Math.abs(blockPos.func_177952_p()) - 1) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw g3.a(runtimeException);
            }
            try {
                if (Math.abs(blockPos.func_177958_n()) - 1 == Math.abs(blockPos.func_177952_p())) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw g3.a(runtimeException);
            }
            blockPos3 = blockPos;
            break;
        }
        try {
            if (blockPos3 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g3.a(runtimeException);
        }
        blockPos = new Vec3i(0, 0, 0);
        float f10 = 0.0f;
        if (blockPos3.func_177952_p() == -6) {
            rotation = Rotation.NONE;
            vec3d = e3.aB;
            f10 = 180.0f;
        } else if (blockPos3.func_177958_n() == 5) {
            rotation = Rotation.CLOCKWISE_90;
            vec3d = e3.ao;
            blockPos = new Vec3i(e3.ah.func_177958_n() - 1, 0, 0);
            f10 = -90.0f;
        } else if (blockPos3.func_177952_p() == 5) {
            rotation = Rotation.CLOCKWISE_180;
            vec3d = e3.aM;
            blockPos = new Vec3i(e3.ah.func_177958_n() - 1, 0, e3.ah.func_177952_p() - 1);
        } else {
            rotation = Rotation.COUNTERCLOCKWISE_90;
            vec3d = e3.U;
            blockPos = new Vec3i(0, 0, e3.ah.func_177952_p() - 1);
            f10 = 90.0f;
        }
        new b4("goblin").a(world, blockPos2.func_177982_a(0, -1, 0).func_177971_a((Vec3i)blockPos), rotation);
        vec3d.func_72441_c((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p());
        vec3d = new Vec3d((double)blockPos2.func_177958_n() + vec3d.field_72450_a + 0.5, (double)blockPos2.func_177956_o() + vec3d.field_72448_b, (double)blockPos2.func_177952_p() + vec3d.field_72449_c + 0.5);
        e3 e32 = new e3(world, true, f10, vec3d);
        e32.field_98038_p = true;
        world.func_72838_d((Entity)e32);
        world.func_72964_e(n2, n3).func_76630_e();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    static class a {
        e1 b;
        String a;

        public a(e1 e12, String string) {
            this.b = e12;
            this.a = string;
        }
    }

    static class b {
        public final String f;
        public final b4 b;
        public final HashSet<Biome> e;
        public final Vec3i c;
        public final boolean d;
        public final int a;

        public b(String string, HashSet<Biome> hashSet, Vec3i vec3i, int n2, boolean bl2) {
            this.f = string;
            this.e = hashSet;
            this.c = vec3i;
            this.d = bl2;
            this.a = n2;
            this.b = new b4(string);
        }
    }
}

