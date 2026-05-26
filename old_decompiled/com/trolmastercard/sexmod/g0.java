/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.fml.common.FMLCommonHandler
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.Main;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class g0 {
    public static boolean a() {
        String string = Thread.currentThread().getName().toLowerCase();
        try {
            if (string.contains("server")) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g0.a(runtimeException);
        }
        try {
            if (string.contains("client")) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g0.a(runtimeException);
        }
        MinecraftServer minecraftServer = FMLCommonHandler.instance().getMinecraftServerInstance();
        try {
            if (minecraftServer == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw g0.a(runtimeException);
        }
        boolean bl2 = minecraftServer.func_152345_ab();
        Main.LOGGER.warn("couldn't clarify if is running on a server or client thread. Came to the solution onServer=" + bl2);
        return bl2;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

