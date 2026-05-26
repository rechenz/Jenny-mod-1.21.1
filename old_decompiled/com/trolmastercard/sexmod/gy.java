/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.WorldSavedData
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.fy;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class gy
extends WorldSavedData {
    static final String a = "sexmod:customstaticgirlnames";
    static final HashMap<UUID, HashMap<fy, String>> b = new HashMap();

    public gy() {
        super(a);
    }

    public gy(String string) {
        super(a);
    }

    @SubscribeEvent
    public void a(WorldEvent.Save save) {
        World world = save.getWorld();
        world.func_175693_T().func_75745_a(a, (WorldSavedData)this);
        this.func_76185_a();
    }

    @SubscribeEvent
    public void a(WorldEvent.Load load) {
        World world = load.getWorld();
        world.func_175693_T().func_75742_a(gy.class, a);
    }

    public static void a(UUID uUID, fy fy2, String string) {
        HashMap<fy, String> hashMap = b.get(uUID);
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put(fy2, string);
        b.put(uUID, hashMap);
    }

    @Nullable
    public static String a(UUID uUID, fy fy2) {
        HashMap<fy, String> hashMap = b.get(uUID);
        try {
            if (hashMap == null) {
                return null;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw gy.a(illegalArgumentException);
        }
        return hashMap.get((Object)fy2);
    }

    public void func_76184_a(NBTTagCompound nBTTagCompound) {
        for (String string : nBTTagCompound.func_150296_c()) {
            UUID uUID;
            try {
                uUID = UUID.fromString(string);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                continue;
            }
            b.put(uUID, this.a(nBTTagCompound.func_74775_l(string)));
        }
    }

    public NBTTagCompound func_189551_b(NBTTagCompound nBTTagCompound) {
        for (Map.Entry<UUID, HashMap<fy, String>> entry : b.entrySet()) {
            UUID uUID = entry.getKey();
            nBTTagCompound.func_74782_a(uUID.toString(), (NBTBase)this.a(entry.getValue()));
        }
        return nBTTagCompound;
    }

    private NBTTagCompound a(HashMap<fy, String> hashMap) {
        NBTTagCompound nBTTagCompound = new NBTTagCompound();
        for (Map.Entry<fy, String> entry : hashMap.entrySet()) {
            nBTTagCompound.func_74778_a(entry.getKey().name(), entry.getValue());
        }
        return nBTTagCompound;
    }

    private HashMap<fy, String> a(NBTTagCompound nBTTagCompound) {
        HashMap<fy, String> hashMap = new HashMap<fy, String>();
        for (fy fy2 : fy.values()) {
            String string = nBTTagCompound.func_74779_i(fy2.name());
            try {
                if ("".equals(string)) continue;
                hashMap.put(fy2, string);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw gy.a(illegalArgumentException);
            }
        }
        return hashMap;
    }

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }
}

