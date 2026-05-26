/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.WorldSavedData
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ServerTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.apache.logging.log4j.Level
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gf;
import com.trolmastercard.sexmod.gl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.apache.logging.log4j.Level;

public class v
extends WorldSavedData {
    public static boolean f = true;
    public static final float c = 60.0f;
    public static final String e = "sexmod:galath_owner_ship";
    public static final String d = "sexmod:ownershipdata";
    public static final String g = "sexmod:mangownershipdata";
    static final long a = 0L;
    static gl<UUID, UUID> h = new gl();
    static HashMap<UUID, Long> b = new HashMap();
    static HashSet<UUID> i = new HashSet();

    public v() {
        super(e);
    }

    public v(String string) {
        super(e);
    }

    public static void a() {
        i.clear();
        h.b();
    }

    public static void e(UUID uUID) {
        UUID uUID2 = v.f(uUID);
        try {
            if (uUID2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        i.add(uUID2);
    }

    public static boolean b(UUID uUID) {
        return i.contains(uUID);
    }

    public static boolean c(f_ f_2) {
        UUID uUID = h.b(f_2.f());
        try {
            if (uUID == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        World world = f_2.field_70170_p;
        EntityPlayer entityPlayer = world.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        try {
            if (entityPlayer.field_71093_bK != f_2.field_71093_bK) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        try {
            if (entityPlayer.func_70032_d((Entity)f_2) > 60.0f) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        return true;
    }

    public static boolean b(EntityPlayer entityPlayer, f_ f_2) {
        return f_2.f().equals(h.c(entityPlayer.getPersistentID()));
    }

    public static void a(f_ f_2) {
        f8 f82 = f_2.a(true);
        try {
            if (f82 != null) {
                f_2.field_70170_p.func_72900_e((Entity)f82);
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        UUID uUID = h.b(f_2.f());
        try {
            if (uUID == null) {
                f_2.field_70170_p.func_72900_e((Entity)f_2);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        World world = f_2.field_70170_p;
        EntityPlayer entityPlayer = world.func_152378_a(uUID);
        try {
            f_2.field_70170_p.func_72900_e((Entity)f_2);
            h.a(uUID);
            if (entityPlayer != null) {
                ge.b.sendTo((IMessage)new gf(false), (EntityPlayerMP)entityPlayer);
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
    }

    public static boolean c(UUID uUID) {
        boolean bl2;
        try {
            bl2 = h.c(uUID) != null;
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        return bl2;
    }

    public static UUID f(UUID uUID) {
        return h.b(uUID);
    }

    public static UUID b(f_ f_2) {
        try {
            if (f_2 == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        return v.f(f_2.f());
    }

    public static UUID a(UUID uUID) {
        return h.c(uUID);
    }

    public static UUID b(EntityPlayer entityPlayer) {
        try {
            if (entityPlayer == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        return v.a(entityPlayer.getPersistentID());
    }

    public static void a(UUID uUID, UUID uUID2) {
        h.a(uUID, uUID2);
    }

    public static void a(EntityPlayer entityPlayer, f_ f_2) {
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        v.a(entityPlayer.getPersistentID(), f_2.f());
    }

    public static void d(UUID uUID) {
        h.a(uUID);
    }

    public static void a(EntityPlayer entityPlayer) {
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        v.d(entityPlayer.getPersistentID());
    }

    public static boolean a(UUID uUID, World world) {
        boolean bl2;
        Long l2 = b.get(uUID);
        try {
            if (!v.b(uUID)) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        try {
            if (l2 == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        try {
            bl2 = world.func_82737_E() - l2 > 0L;
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        return bl2;
    }

    public static void a(UUID uUID, Long l2) {
        try {
            if (uUID == null) {
                Main.LOGGER.log(Level.WARN, "tried to save last cum dosage time on NULL player");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        b.put(uUID, l2);
    }

    @SubscribeEvent
    public void a(TickEvent.ServerTickEvent serverTickEvent) {
        try {
            if (serverTickEvent.phase != TickEvent.Phase.END) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw v.a(runtimeException);
        }
        World world = FMLCommonHandler.instance().getMinecraftServerInstance().func_130014_f_();
        ArrayList<EntityPlayer> arrayList = new ArrayList<EntityPlayer>();
        for (Map.Entry<UUID, UUID> entityPlayer : h.c()) {
            UUID uUID = entityPlayer.getKey();
            UUID uUID2 = entityPlayer.getValue();
            EntityPlayer entityPlayer2 = world.func_152378_a(uUID);
            try {
                if (entityPlayer2 == null) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw v.a(runtimeException);
            }
            try {
                if (em.a(uUID2) != null) continue;
                arrayList.add(entityPlayer2);
            }
            catch (RuntimeException runtimeException) {
                throw v.a(runtimeException);
            }
        }
        for (EntityPlayer entityPlayer : arrayList) {
            h.a(entityPlayer.getPersistentID());
            ge.b.sendTo((IMessage)new gf(false), (EntityPlayerMP)entityPlayer);
        }
    }

    @SubscribeEvent
    public void a(WorldEvent.Save save) {
        World world = save.getWorld();
        world.func_175693_T().func_75745_a(e, (WorldSavedData)this);
        this.func_76185_a();
    }

    @SubscribeEvent
    public void a(WorldEvent.Load load) {
        World world = load.getWorld();
        world.func_175693_T().func_75742_a(v.class, e);
    }

    public void func_76184_a(NBTTagCompound nBTTagCompound) {
        NBTTagCompound nBTTagCompound2 = nBTTagCompound.func_74775_l(d);
        int n2 = nBTTagCompound2.func_74762_e("amount");
        for (int i2 = 0; i2 < n2; ++i2) {
            long l2;
            UUID uUID;
            UUID uUID2;
            block9: {
                uUID2 = nBTTagCompound2.func_186857_a("master" + i2);
                uUID = nBTTagCompound2.func_186857_a("galath" + i2);
                l2 = nBTTagCompound2.func_74763_f("lastcumdosage" + i2);
                try {
                    try {
                        if (uUID2 != null && uUID != null) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw v.a(runtimeException);
                    }
                    Main.LOGGER.fatal("OMFG WHOOP WHOOP SAVING DIDNT WORK CORRECTLY AAAAAAAAAAA");
                    continue;
                }
                catch (RuntimeException runtimeException) {
                    throw v.a(runtimeException);
                }
            }
            h.a(uUID2, uUID);
            b.put(uUID2, l2);
        }
        NBTTagCompound nBTTagCompound3 = nBTTagCompound.func_74775_l(g);
        int n3 = 0;
        while (true) {
            try {
                if (!nBTTagCompound3.func_186855_b("mang" + n3)) {
                    break;
                }
            }
            catch (RuntimeException runtimeException) {
                throw v.a(runtimeException);
            }
            i.add(nBTTagCompound3.func_186857_a("mang" + n3));
            ++n3;
        }
        nBTTagCompound.func_74782_a(g, (NBTBase)new NBTTagCompound());
        nBTTagCompound.func_74782_a(d, (NBTBase)new NBTTagCompound());
    }

    public NBTTagCompound func_189551_b(NBTTagCompound nBTTagCompound) {
        NBTTagCompound nBTTagCompound2 = new NBTTagCompound();
        nBTTagCompound2.func_74768_a("amount", h.e());
        int n2 = 0;
        for (Map.Entry<UUID, UUID> object : h.c()) {
            UUID uUID = object.getKey();
            UUID uUID2 = object.getValue();
            Long l2 = b.get(uUID);
            if (l2 == null) {
                l2 = 0L;
            }
            nBTTagCompound2.func_186854_a("galath" + n2, uUID2);
            nBTTagCompound2.func_186854_a("master" + n2, uUID);
            nBTTagCompound2.func_74772_a("lastcumdosage" + n2, l2.longValue());
            ++n2;
        }
        NBTTagCompound nBTTagCompound3 = new NBTTagCompound();
        n2 = 0;
        for (UUID uUID : i) {
            nBTTagCompound3.func_186854_a("mang" + n2++, uUID);
        }
        nBTTagCompound.func_74782_a(d, (NBTBase)nBTTagCompound2);
        nBTTagCompound.func_74782_a(g, (NBTBase)nBTTagCompound3);
        return nBTTagCompound;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

