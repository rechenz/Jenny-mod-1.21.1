/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockChest$Type
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.monster.AbstractSkeleton
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer$SleepResult
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.WorldSavedData
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.player.PlayerSleepInBedEvent
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  net.minecraftforge.event.world.BlockEvent$PlaceEvent
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.aa;
import com.trolmastercard.sexmod.bs;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.fm;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.h6;
import com.trolmastercard.sexmod.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ax {
    static final int d = 4;
    private static final HashMap<UUID, a> c = new HashMap();
    static final Vec3d[] b = new Vec3d[]{new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.5, 0.0, 0.0), new Vec3d(-0.5, 0.0, 0.0), new Vec3d(0.0, 0.0, 0.5), new Vec3d(0.0, 0.0, -0.5)};
    static HashMap<ff, BlockPos[]> a = new HashMap();

    public static void a() {
        c.clear();
        a.clear();
    }

    public static void a(World world, Vec3d vec3d) {
        UUID uUID = UUID.randomUUID();
        float[] fArray = new float[4];
        fArray[0] = 0.25f;
        try {
            for (int i2 = 1; i2 < fArray.length; ++i2) {
                fArray[i2] = ff.j();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        ArrayList<ff> arrayList = new ArrayList<ff>();
        for (float f10 : fArray) {
            ff ff2 = ff.a(world, uUID, f10);
            arrayList.add(ff2);
        }
        EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.values()[r.f.nextInt(EyeAndKoboldColor.values().length)];
        a a10 = new a(uUID, eyeAndKoboldColor, (ff)arrayList.get(0), arrayList);
        c.put(uUID, a10);
        int n2 = 0;
        for (ff ff2 : arrayList) {
            ff2.func_70107_b(vec3d.field_72450_a + ax.b[n2].field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c + ax.b[n2].field_72449_c);
            world.func_72838_d((Entity)ff2);
            ++n2;
        }
    }

    public static boolean o(UUID uUID) {
        boolean bl2;
        try {
            bl2 = c.get(uUID) != null;
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return bl2;
    }

    public static void a(UUID uUID, UUID uUID2) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.a(uUID2);
    }

    public static void a(UUID uUID, EyeAndKoboldColor eyeAndKoboldColor) {
        a a10 = c.get(uUID);
        try {
            if (a10 != null) {
                System.out.println("tribe of UUID " + uUID.toString() + " does already exist lol");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        c.put(uUID, new a(uUID, eyeAndKoboldColor));
    }

    public static boolean a(BlockPos blockPos) {
        for (Map.Entry<ff, BlockPos[]> entry : a.entrySet()) {
            BlockPos[] blockPosArray = entry.getValue();
            try {
                if (blockPosArray[0].equals((Object)blockPos)) {
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
            try {
                if (!blockPosArray[1].equals((Object)blockPos)) continue;
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
        }
        return false;
    }

    public static BlockPos[] a(ff ff2) {
        return a.get(ff2);
    }

    public static void a(ff ff2, BlockPos blockPos) {
        World world = ff2.field_70170_p;
        BlockPos blockPos2 = null;
        if (world.func_180495_p(blockPos.func_177978_c()).func_177230_c() instanceof BlockBed) {
            blockPos2 = blockPos.func_177978_c();
        }
        if (world.func_180495_p(blockPos.func_177974_f()).func_177230_c() instanceof BlockBed) {
            blockPos2 = blockPos.func_177974_f();
        }
        if (world.func_180495_p(blockPos.func_177968_d()).func_177230_c() instanceof BlockBed) {
            blockPos2 = blockPos.func_177968_d();
        }
        if (world.func_180495_p(blockPos.func_177976_e()).func_177230_c() instanceof BlockBed) {
            blockPos2 = blockPos.func_177976_e();
        }
        try {
            if (blockPos2 == null) {
                System.out.println("bed @" + blockPos.toString() + " apparently doesn't have another half.. wtf");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a.put(ff2, new BlockPos[]{blockPos, blockPos2});
    }

    public static void b(ff ff2) {
        a.remove(ff2);
    }

    public static void d(UUID uUID, ff ff2) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.g = ff2;
    }

    public static void c(UUID uUID, ff ff2) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        try {
            a10.a(ff2);
            c.replace(uUID, a10);
            ff2.func_184212_Q().func_187227_b(ff.aL, (Object)Optional.of((Object)uUID));
            if (!ff2.aA) {
                ff2.func_184212_Q().func_187227_b(ff.N, (Object)a10.h.toString());
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
    }

    public static void k(UUID uUID) {
        block7: {
            a a10 = c.get(uUID);
            try {
                if (a10 == null) {
                    System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
            ff ff2 = a10.g;
            try {
                try {
                    if (ff2 != null && !ff2.field_70128_L) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw ax.a(runtimeException);
                }
                a10.g = a10.b();
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
        }
    }

    public static void a(UUID uUID, ff ff2) {
        Object object;
        a a10;
        block17: {
            a10 = c.get(uUID);
            try {
                if (a10 == null) {
                    System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
            try {
                a10.b(ff2);
                a10.b(ff2.f());
                if (a10.g == null || a10.g.func_145782_y() != ff2.func_145782_y()) break block17;
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
            object = a10.b();
            try {
                if (object != null) {
                    a10.g = object;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
        }
        for (bs object2 : a10.f) {
            object2.c(ff2);
        }
        try {
            if (!a10.a.isEmpty()) {
                c.replace(uUID, a10);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        try {
            if (!ff2.J()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        object = ff2.z();
        if (object != null) {
            HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
            hashSet.addAll(a10.i);
            hashSet.addAll(a10.b);
            for (bs bs2 : a10.f) {
                hashSet.addAll(bs2.b);
            }
            ge.b.sendTo((IMessage)new h6(hashSet, false), (EntityPlayerMP)object);
            object.func_145747_a((ITextComponent)new TextComponentString(String.format("ur %stribe %shas been %seradicated %suwu", TextFormatting.RED, TextFormatting.WHITE, TextFormatting.RED, TextFormatting.WHITE)));
        }
    }

    @Nullable
    public static ff f(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.g;
    }

    public static boolean e(UUID uUID, ff ff2) {
        boolean bl2;
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        try {
            if (a10.g == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        try {
            bl2 = a10.g.func_145782_y() == ff2.func_145782_y();
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return bl2;
    }

    public static EyeAndKoboldColor l(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return ff.aJ;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.h;
    }

    public static HashSet<BlockPos> j(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return new HashSet<BlockPos>();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.b;
    }

    public static void a(UUID uUID, BlockPos blockPos) {
        try {
            if (blockPos == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.b.add(blockPos);
    }

    public static void e(UUID uUID, BlockPos blockPos) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.b.remove(blockPos);
    }

    public static HashSet<BlockPos> q(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.i;
    }

    public static void f(UUID uUID, BlockPos blockPos) {
        try {
            if (blockPos == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.i.add(blockPos);
    }

    public static void d(UUID uUID, BlockPos blockPos) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.i.remove(blockPos);
    }

    public static HashSet<BlockPos> a(UUID uUID, bs bs2) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return new HashSet<BlockPos>();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        try {
            if (bs2 != null) {
                a10.b(bs2);
                return bs2.b;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return new HashSet<BlockPos>();
    }

    public static HashSet<BlockPos> c(UUID uUID, BlockPos blockPos) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return new HashSet<BlockPos>();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        bs bs2 = null;
        for (bs bs3 : a10.f) {
            if (!bs3.b.contains(blockPos)) continue;
            bs2 = bs3;
            break;
        }
        return ax.a(uUID, bs2);
    }

    public static void b(UUID uUID, bs bs2) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.a(bs2);
    }

    public static void b(UUID uUID, ff ff2) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        bs bs2 = null;
        for (bs bs3 : a10.f) {
            if (!bs3.b(ff2)) continue;
            bs2 = bs3;
        }
        try {
            if (bs2 == null) {
                System.out.println("task of worker " + ff2.f() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.b(bs2);
    }

    @Nullable
    public static Collection<bs> p(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.f;
    }

    public static fm i(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return fm.REST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.e();
    }

    public static void a(UUID uUID, fm fm2) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.a(fm2);
    }

    public static int h(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return 0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.f();
    }

    public static List<ff> n(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return new ArrayList<ff>();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.a;
    }

    public static void b(UUID uUID, BlockPos blockPos) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.a(blockPos);
    }

    @Nullable
    public static BlockPos m(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.g();
    }

    public static HashSet<EntityLivingBase> e(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return new HashSet<EntityLivingBase>();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.c();
    }

    public static void a(UUID uUID, EntityLivingBase entityLivingBase) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.a(entityLivingBase);
    }

    public static void b(UUID uUID, EntityLivingBase entityLivingBase) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.b(entityLivingBase);
    }

    public static boolean g(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        for (ff ff2 : a10.a) {
            try {
                if (ff2.ae() == null) continue;
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
        }
        return false;
    }

    public static boolean c(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        return a10.c;
    }

    public static void a(UUID uUID, boolean bl2) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.c = bl2;
    }

    @Nullable
    public static UUID a(UUID uUID) {
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        for (Map.Entry<UUID, a> entry : c.entrySet()) {
            a a10 = entry.getValue();
            try {
                try {
                    if (a10.d().size() == 0 && a10.f() == 0) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ax.a(runtimeException);
                }
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
            try {
                if (!uUID.equals(entry.getValue().a())) continue;
                return entry.getKey();
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
        }
        return null;
    }

    @Nullable
    public static UUID b(UUID uUID) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        List<ff> list = a10.a;
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        ff ff2 = list.get(0);
        try {
            if (!ff2.J()) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        String string = (String)list.get(0).func_184212_Q().func_187225_a(em.v);
        return UUID.fromString(string);
    }

    public static HashSet<BlockPos> d(UUID uUID) {
        a a10 = c.get(uUID);
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return hashSet;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        for (bs bs2 : a10.f) {
            hashSet.addAll(bs2.b);
        }
        hashSet.addAll(a10.i);
        hashSet.addAll(a10.b);
        return hashSet;
    }

    public static HashMap<UUID, BlockPos> a(UUID uUID, World world) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return new HashMap<UUID, BlockPos>();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        HashMap<UUID, BlockPos> hashMap = a10.k;
        ArrayList<UUID> arrayList = new ArrayList<UUID>();
        for (Map.Entry<UUID, BlockPos> entry : hashMap.entrySet()) {
            BlockPos blockPos = entry.getValue();
            UUID uUID2 = entry.getKey();
            try {
                if (!world.func_175697_a(blockPos, 5)) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.func_177973_b(new Vec3i(-3, -3, -3)), blockPos.func_177982_a(3, 3, 3));
            List list = world.func_72872_a(ff.class, axisAlignedBB);
            boolean bl2 = false;
            for (ff ff2 : list) {
                if (!uUID2.equals(ff2.f())) continue;
                bl2 = true;
                break;
            }
            try {
                if (bl2) continue;
                arrayList.add(uUID2);
            }
            catch (RuntimeException runtimeException) {
                throw ax.a(runtimeException);
            }
        }
        a10.k = hashMap;
        return hashMap;
    }

    public static void a(UUID uUID, UUID uUID2, BlockPos blockPos) {
        a a10 = c.get(uUID);
        try {
            if (a10 == null) {
                System.out.println("tribe of UUID " + uUID.toString() + " not found uwu");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ax.a(runtimeException);
        }
        a10.a(uUID2, blockPos);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class b
    extends WorldSavedData {
        public b(String string) {
            super(string);
        }

        @SubscribeEvent
        public void a(WorldEvent.Save save) {
            World world = save.getWorld();
            world.func_175693_T().func_75745_a("tribes", (WorldSavedData)this);
            this.func_76185_a();
        }

        @SubscribeEvent
        public void a(WorldEvent.Load load) {
            World world = load.getWorld();
            world.func_175693_T().func_75742_a(b.class, "tribes");
        }

        @SubscribeEvent
        public void a(PlayerSleepInBedEvent playerSleepInBedEvent) {
            try {
                if (ax.a(playerSleepInBedEvent.getPos())) {
                    playerSleepInBedEvent.setResult(EntityPlayer.SleepResult.OTHER_PROBLEM);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
            }
        }

        @SubscribeEvent
        public void a(BlockEvent.PlaceEvent placeEvent) {
            BlockPos blockPos;
            World world;
            BlockPos blockPos2;
            block30: {
                BlockChest.Type type;
                block29: {
                    block28: {
                        block27: {
                            blockPos2 = placeEvent.getPos();
                            IBlockState iBlockState = placeEvent.getState();
                            world = placeEvent.getWorld();
                            try {
                                if (world.field_72995_K) {
                                    return;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                            }
                            try {
                                if (!(iBlockState.func_177230_c() instanceof BlockChest)) {
                                    return;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                            }
                            type = ((BlockChest)world.func_180495_p((BlockPos)blockPos2).func_177230_c()).field_149956_a;
                            blockPos = null;
                            try {
                                if (!(world.func_180495_p(blockPos2.func_177978_c()).func_177230_c() instanceof BlockChest) || !type.equals((Object)((BlockChest)world.func_180495_p((BlockPos)blockPos2.func_177978_c()).func_177230_c()).field_149956_a)) break block27;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                            }
                            blockPos = blockPos2.func_177978_c();
                        }
                        try {
                            if (!(world.func_180495_p(blockPos2.func_177974_f()).func_177230_c() instanceof BlockChest) || !type.equals((Object)((BlockChest)world.func_180495_p((BlockPos)blockPos2.func_177974_f()).func_177230_c()).field_149956_a)) break block28;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                        }
                        blockPos = blockPos2.func_177974_f();
                    }
                    try {
                        if (!(world.func_180495_p(blockPos2.func_177968_d()).func_177230_c() instanceof BlockChest) || !type.equals((Object)((BlockChest)world.func_180495_p((BlockPos)blockPos2.func_177968_d()).func_177230_c()).field_149956_a)) break block29;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    blockPos = blockPos2.func_177968_d();
                }
                try {
                    if (!(world.func_180495_p(blockPos2.func_177976_e()).func_177230_c() instanceof BlockChest) || !type.equals((Object)((BlockChest)world.func_180495_p((BlockPos)blockPos2.func_177976_e()).func_177230_c()).field_149956_a)) break block30;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                }
                blockPos = blockPos2.func_177976_e();
            }
            try {
                if (blockPos == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
            }
            for (Map.Entry entry : c.entrySet()) {
                a a10 = (a)entry.getValue();
                try {
                    if (!a10.i.contains(blockPos)) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                }
                a10.i.add(blockPos2);
                UUID uUID = ax.b((UUID)entry.getKey());
                try {
                    if (uUID == null) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                }
                EntityPlayerMP entityPlayerMP = (EntityPlayerMP)world.func_152378_a(uUID);
                try {
                    if (entityPlayerMP == null) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                }
                ge.b.sendTo((IMessage)new h6(blockPos2, true), entityPlayerMP);
            }
        }

        @SubscribeEvent
        public void a(EntityJoinWorldEvent entityJoinWorldEvent) {
            EntityZombie entityZombie;
            Entity entity = entityJoinWorldEvent.getEntity();
            if (entity instanceof EntityZombie) {
                entityZombie = (EntityZombie)entity;
                entityZombie.field_70715_bh.func_75776_a(3, (EntityAIBase)new aa((EntityCreature)entityZombie, true, false));
            }
            if (entity instanceof AbstractSkeleton) {
                entityZombie = (AbstractSkeleton)entity;
                entityZombie.field_70715_bh.func_75776_a(3, (EntityAIBase)new aa((EntityCreature)entityZombie, true, false));
            }
            if (entity instanceof EntitySpider) {
                entityZombie = (EntitySpider)entity;
                entityZombie.field_70715_bh.func_75776_a(3, (EntityAIBase)new aa((EntityCreature)entityZombie, true, true));
            }
        }

        @SubscribeEvent
        public void a(BlockEvent.BreakEvent breakEvent) {
            Object object;
            Object object2;
            a a10;
            BlockPos blockPos = breakEvent.getPos();
            World world = breakEvent.getWorld();
            try {
                if (world.field_72995_K) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
            }
            IBlockState iBlockState = world.func_180495_p(blockPos);
            Block block = iBlockState.func_177230_c();
            if (block instanceof BlockChest) {
                for (Map.Entry entry : c.entrySet()) {
                    a10 = (a)entry.getValue();
                    try {
                        if (!a10.i.contains(blockPos)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    a10.i.remove(blockPos);
                    object2 = ax.b((UUID)entry.getKey());
                    try {
                        if (object2 == null) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    object = (EntityPlayerMP)world.func_152378_a((UUID)object2);
                    try {
                        if (object == null) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    ge.b.sendTo((IMessage)new h6(blockPos, false), object);
                }
            }
            if (block instanceof BlockBed) {
                for (Map.Entry entry : c.entrySet()) {
                    a10 = (a)entry.getValue();
                    try {
                        if (!a10.b.contains(blockPos)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    object2 = cj.a(blockPos, iBlockState);
                    a10.b.remove(blockPos);
                    a10.b.remove(object2);
                    object = ax.b((UUID)entry.getKey());
                    try {
                        if (object == null) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    EntityPlayerMP entityPlayerMP = (EntityPlayerMP)world.func_152378_a((UUID)object);
                    try {
                        if (entityPlayerMP == null) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
                    hashSet.add(blockPos);
                    hashSet.add((BlockPos)object2);
                    ge.b.sendTo((IMessage)new h6(hashSet, false), entityPlayerMP);
                }
            }
        }

        String a(String string, NBTTagCompound nBTTagCompound) {
            String string2 = nBTTagCompound.func_74779_i(string);
            nBTTagCompound.func_74778_a(string, "");
            return string2;
        }

        public void func_76184_a(NBTTagCompound nBTTagCompound) {
            int n2 = 0;
            while (true) {
                Object object;
                Object object2;
                Object object3;
                String string;
                String string2 = this.a("tribeId" + n2, nBTTagCompound);
                try {
                    if ("".equals(string2)) {
                        break;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                }
                UUID uUID = UUID.fromString(string2);
                EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.valueOf(this.a("tribeColor" + n2, nBTTagCompound));
                ax.a(uUID, eyeAndKoboldColor);
                String string3 = this.a("tribeMaster" + n2, nBTTagCompound);
                try {
                    if (!"".equals(string3)) {
                        ax.a(uUID, UUID.fromString(string3));
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                }
                int n3 = 0;
                while (true) {
                    String string4 = this.a(uUID.toString() + "member" + n3 + "pos", nBTTagCompound);
                    try {
                        if ("".equals(string4)) {
                            break;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    string = this.a(uUID.toString() + "member" + n3 + "id", nBTTagCompound);
                    try {
                        if ("".equals(string)) {
                            break;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    object3 = string4.split("\\|");
                    object2 = new BlockPos(Integer.parseInt(object3[0]), Integer.parseInt(object3[1]), Integer.parseInt(object3[2]));
                    object = UUID.fromString(string);
                    ax.a(uUID, (UUID)object, (BlockPos)object2);
                    ++n3;
                }
                int n4 = 0;
                while (true) {
                    string = this.a(uUID.toString() + "bed" + n4, nBTTagCompound);
                    try {
                        if ("".equals(string)) {
                            break;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    object3 = string.split("\\|");
                    object2 = new BlockPos(Integer.parseInt(object3[0]), Integer.parseInt(object3[1]), Integer.parseInt(object3[2]));
                    ax.a(uUID, (BlockPos)object2);
                    ++n4;
                }
                int n5 = 0;
                while (true) {
                    object3 = this.a(uUID.toString() + "chest" + n5, nBTTagCompound);
                    try {
                        if ("".equals(object3)) {
                            break;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    object2 = ((String)object3).split("\\|");
                    object = new BlockPos(Integer.parseInt(object2[0]), Integer.parseInt(object2[1]), Integer.parseInt(object2[2]));
                    ax.f(uUID, (BlockPos)object);
                    ++n5;
                }
                int n6 = 0;
                while (true) {
                    object2 = this.a(uUID.toString() + n6 + "taskKind", nBTTagCompound);
                    try {
                        if ("".equals(object2)) {
                            break;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    object = this.a(uUID.toString() + n6 + "facing", nBTTagCompound);
                    EnumFacing enumFacing = EnumFacing.NORTH;
                    if (!"".equals(object)) {
                        enumFacing = EnumFacing.func_176739_a((String)object);
                    }
                    String string5 = this.a(uUID.toString() + n6 + "pos", nBTTagCompound);
                    String[] stringArray = string5.split("\\|");
                    BlockPos blockPos = new BlockPos(Integer.parseInt(stringArray[0]), Integer.parseInt(stringArray[1]), Integer.parseInt(stringArray[2]));
                    HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
                    int n7 = 0;
                    while (true) {
                        String string6 = this.a(uUID.toString() + n6 + "block" + n7, nBTTagCompound);
                        try {
                            if ("".equals(string6)) {
                                break;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                        }
                        String[] stringArray2 = string6.split("\\|");
                        BlockPos blockPos2 = new BlockPos(Integer.parseInt(stringArray2[0]), Integer.parseInt(stringArray2[1]), Integer.parseInt(stringArray2[2]));
                        hashSet.add(blockPos2);
                        ++n7;
                    }
                    ax.b(uUID, new bs(blockPos, bs.a.valueOf((String)object2), hashSet, enumFacing));
                    ++n6;
                }
                ++n2;
            }
        }

        /*
         * WARNING - void declaration
         */
        public NBTTagCompound func_189551_b(NBTTagCompound nBTTagCompound) {
            int n2 = 0;
            for (Map.Entry entry : c.entrySet()) {
                Object object2;
                a a10 = (a)entry.getValue();
                UUID uUID = (UUID)entry.getKey();
                UUID uUID2 = a10.a();
                try {
                    nBTTagCompound.func_74778_a("tribeId" + n2, uUID.toString());
                    nBTTagCompound.func_74778_a("tribeColor" + n2, a10.h.toString());
                    if (uUID2 != null) {
                        nBTTagCompound.func_74778_a("tribeMaster" + n2, uUID2.toString());
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                }
                int n3 = 0;
                HashSet<Object> hashSet = new HashSet<Object>();
                for (ff iterator2 : a10.a) {
                    try {
                        if (iterator2.field_70128_L) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    object2 = iterator2.func_180425_c();
                    UUID uUID3 = iterator2.f();
                    nBTTagCompound.func_74778_a(uUID.toString() + "member" + n3 + "pos", object2.func_177958_n() + "|" + object2.func_177956_o() + "|" + object2.func_177952_p());
                    nBTTagCompound.func_74778_a(uUID.toString() + "member" + n3 + "id", uUID3.toString());
                    hashSet.add(uUID3);
                    ++n3;
                }
                for (Map.Entry entry2 : a10.k.entrySet()) {
                    object2 = (UUID)entry2.getKey();
                    BlockPos blockPos = (BlockPos)entry2.getValue();
                    try {
                        if (hashSet.contains(object2)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$b.a(runtimeException);
                    }
                    nBTTagCompound.func_74778_a(uUID.toString() + "member" + n3 + "pos", blockPos.func_177958_n() + "|" + blockPos.func_177956_o() + "|" + blockPos.func_177952_p());
                    nBTTagCompound.func_74778_a(uUID.toString() + "member" + n3 + "id", ((UUID)object2).toString());
                    hashSet.add(object2);
                    ++n3;
                }
                int n4 = 0;
                for (Object object2 : a10.b) {
                    nBTTagCompound.func_74778_a(uUID.toString() + "bed" + n4, object2.func_177958_n() + "|" + object2.func_177956_o() + "|" + object2.func_177952_p());
                    ++n4;
                }
                boolean bl2 = false;
                object2 = a10.i.iterator();
                while (object2.hasNext()) {
                    void n5;
                    BlockPos blockPos = (BlockPos)object2.next();
                    nBTTagCompound.func_74778_a(uUID.toString() + "chest" + (int)n5, blockPos.func_177958_n() + "|" + blockPos.func_177956_o() + "|" + blockPos.func_177952_p());
                    ++n5;
                }
                int n5 = 0;
                for (bs bs2 : a10.f) {
                    nBTTagCompound.func_74778_a(uUID.toString() + n5 + "taskKind", bs2.c.toString());
                    nBTTagCompound.func_74778_a(uUID.toString() + n5 + "pos", bs2.a.func_177958_n() + "|" + bs2.a.func_177956_o() + "|" + bs2.a.func_177952_p());
                    nBTTagCompound.func_74778_a(uUID.toString() + n5 + "facing", bs2.e.func_176610_l());
                    int n6 = 0;
                    for (BlockPos blockPos : bs2.b) {
                        nBTTagCompound.func_74778_a(uUID.toString() + n5 + "block" + n6, blockPos.func_177958_n() + "|" + blockPos.func_177956_o() + "|" + blockPos.func_177952_p());
                        ++n6;
                    }
                    ++n5;
                }
                ++n2;
            }
            return nBTTagCompound;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }

    public static class a {
        UUID m;
        UUID e;
        ff g;
        List<ff> a;
        EyeAndKoboldColor h;
        fm d = fm.REST;
        BlockPos l = null;
        Collection<bs> f = new ArrayList<bs>();
        HashSet<EntityLivingBase> j = new HashSet();
        HashSet<BlockPos> i = new HashSet();
        HashSet<BlockPos> b = new HashSet();
        HashMap<UUID, BlockPos> k = new HashMap();
        boolean c = false;

        public a(UUID uUID, EyeAndKoboldColor eyeAndKoboldColor, ff ff2, List<ff> list) {
            this.m = uUID;
            this.h = eyeAndKoboldColor;
            this.g = ff2;
            this.a = list;
        }

        public a(UUID uUID, EyeAndKoboldColor eyeAndKoboldColor) {
            this.m = uUID;
            this.h = eyeAndKoboldColor;
            this.a = new ArrayList<ff>();
        }

        public void a(UUID uUID) {
            this.e = uUID;
        }

        public UUID a() {
            return this.e;
        }

        public void b(bs bs2) {
            block11: {
                try {
                    if (!this.f.contains(bs2)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$a.a(runtimeException);
                }
                for (ff ff2 : bs2.f) {
                    ff2.b(fp.NULL);
                    ff2.func_189654_d(false);
                    ff2.field_70145_X = false;
                    ff2.func_184212_Q().func_187227_b(em.G, (Object)false);
                }
                try {
                    try {
                        this.f.remove(bs2);
                        if (!bs2.b.isEmpty() && this.e != null) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.ax$a.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$a.a(runtimeException);
                }
            }
            EntityPlayerMP entityPlayerMP = FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_177451_a(this.e);
            try {
                if (entityPlayerMP == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ax$a.a(runtimeException);
            }
            ge.b.sendTo((IMessage)new h6(bs2.b, false), entityPlayerMP);
        }

        public HashMap<UUID, BlockPos> d() {
            return this.k;
        }

        public void a(UUID uUID, BlockPos blockPos) {
            this.k.put(uUID, blockPos);
        }

        public void b(UUID uUID) {
            this.k.remove(uUID);
        }

        public void b(EntityLivingBase entityLivingBase) {
            this.j.remove(entityLivingBase);
        }

        public void a(EntityLivingBase entityLivingBase) {
            this.j.add(entityLivingBase);
        }

        public HashSet<EntityLivingBase> c() {
            return this.j;
        }

        public int f() {
            HashSet<UUID> hashSet = new HashSet<UUID>();
            for (ff object : this.a) {
                hashSet.add(object.f());
            }
            for (Map.Entry entry : this.k.entrySet()) {
                hashSet.add((UUID)entry.getKey());
            }
            return hashSet.size();
        }

        public BlockPos g() {
            return this.l;
        }

        public void a(BlockPos blockPos) {
            this.l = blockPos;
        }

        public void a(bs bs2) {
            this.f.add(bs2);
        }

        public fm e() {
            return this.d;
        }

        public void a(fm fm2) {
            this.d = fm2;
        }

        public void a(ff ff2) {
            try {
                if (this.a.contains(ff2)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.ax$a.a(runtimeException);
            }
            UUID uUID = ff2.f();
            ArrayList<ff> arrayList = new ArrayList<ff>();
            for (ff ff3 : this.a) {
                try {
                    if (!ff3.f().equals(uUID)) continue;
                    arrayList.add(ff3);
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$a.a(runtimeException);
                }
            }
            for (ff ff3 : arrayList) {
                Main.LOGGER.warn(String.format("Removed old entry of kobold called %s with UUID %s owned by %s", ff3.c(), ff3.f(), this.e));
                this.b(ff3);
            }
            this.a.add(ff2);
        }

        public void b(ff ff2) {
            this.a.remove(ff2);
        }

        ff b() {
            ff ff2 = null;
            for (ff ff3 : this.a) {
                try {
                    if (ff3.field_70128_L) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.ax$a.a(runtimeException);
                }
                if (ff2 == null) {
                    ff2 = ff3;
                    continue;
                }
                float f10 = ((Float)ff2.func_184212_Q().func_187225_a(ff.aE)).floatValue();
                float f11 = ((Float)ff3.func_184212_Q().func_187225_a(ff.aE)).floatValue();
                if (!(f11 < f10)) continue;
                ff2 = ff3;
            }
            return ff2;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

