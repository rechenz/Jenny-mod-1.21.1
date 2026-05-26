/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.WorldSavedData
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.v;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class bj
extends WorldSavedData {
    static final String a = "sexmod:static_custom_model_manager";
    static final String d = "sexmod:static_custom_model_manager";
    public static HashMap<UUID, String> c = new HashMap();
    public static HashMap<UUID, String> b = new HashMap();

    public bj() {
        super("sexmod:static_custom_model_manager");
    }

    public bj(String string) {
        super("sexmod:static_custom_model_manager");
    }

    public static String c(em em2) {
        String string = bj.b(em2);
        try {
            if (string == null) {
                return "";
            }
        }
        catch (RuntimeException runtimeException) {
            throw bj.a(runtimeException);
        }
        return string;
    }

    private static String b(em em2) {
        if (em2 instanceof f_) {
            UUID uUID = em2.f();
            UUID uUID2 = v.f(uUID);
            if (uUID2 == null) {
                uUID2 = uUID;
            }
            return c.get(uUID2);
        }
        if (em2 instanceof f8) {
            UUID uUID;
            HashMap<UUID, String> hashMap;
            UUID uUID3 = v.f(((f8)em2).v());
            try {
                hashMap = b;
                uUID = uUID3 == null ? em2.f() : uUID3;
            }
            catch (RuntimeException runtimeException) {
                throw bj.a(runtimeException);
            }
            return hashMap.get(uUID);
        }
        return null;
    }

    public static void a(em em2) {
        if (em2 instanceof f_) {
            UUID uUID = em2.f();
            UUID uUID2 = v.f(uUID);
            if (uUID2 == null) {
                uUID2 = uUID;
            }
            c.put(uUID2, em2.C());
            return;
        }
        if (em2 instanceof f8) {
            UUID uUID;
            HashMap<UUID, String> hashMap;
            UUID uUID3 = v.f(((f8)em2).v());
            try {
                hashMap = b;
                uUID = uUID3 == null ? em2.f() : uUID3;
            }
            catch (RuntimeException runtimeException) {
                throw bj.a(runtimeException);
            }
            hashMap.put(uUID, em2.C());
        }
    }

    @SubscribeEvent
    public void a(WorldEvent.Save save) {
        World world = save.getWorld();
        world.func_175693_T().func_75745_a("sexmod:static_custom_model_manager", (WorldSavedData)this);
        this.func_76185_a();
    }

    @SubscribeEvent
    public void a(WorldEvent.Load load) {
        World world = load.getWorld();
        world.func_175693_T().func_75742_a(bj.class, "sexmod:static_custom_model_manager");
    }

    public void func_76184_a(NBTTagCompound nBTTagCompound) {
        NBTTagCompound nBTTagCompound2 = nBTTagCompound.func_74775_l("sexmod:static_custom_model_manager");
        this.a(nBTTagCompound2.func_74775_l("galath"), c);
        this.a(nBTTagCompound2.func_74775_l("mang"), b);
    }

    public NBTTagCompound func_189551_b(NBTTagCompound nBTTagCompound) {
        NBTTagCompound nBTTagCompound2 = new NBTTagCompound();
        nBTTagCompound2.func_74782_a("galath", (NBTBase)this.a(c));
        nBTTagCompound2.func_74782_a("mang", (NBTBase)this.a(b));
        nBTTagCompound.func_74782_a("sexmod:static_custom_model_manager", (NBTBase)nBTTagCompound2);
        return nBTTagCompound;
    }

    NBTTagCompound a(HashMap<UUID, String> hashMap) {
        NBTTagCompound nBTTagCompound = new NBTTagCompound();
        int n2 = 0;
        for (Map.Entry<UUID, String> entry : hashMap.entrySet()) {
            UUID uUID = entry.getKey();
            nBTTagCompound.func_74778_a("UUID" + n2, uUID.toString());
            nBTTagCompound.func_74778_a("MODEL" + n2, entry.getValue());
            ++n2;
        }
        return nBTTagCompound;
    }

    void a(NBTTagCompound nBTTagCompound, HashMap<UUID, String> hashMap) {
        int n2 = 0;
        while (true) {
            String string = nBTTagCompound.func_74779_i("UUID" + n2);
            try {
                if ("".equals(string)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw bj.a(runtimeException);
            }
            hashMap.put(UUID.fromString(string), nBTTagCompound.func_74779_i("MODEL" + n2));
            ++n2;
        }
    }

    public static void a() {
        c.clear();
        b.clear();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

