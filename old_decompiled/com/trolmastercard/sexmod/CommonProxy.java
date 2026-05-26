/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.network.IGuiHandler
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.registry.GameRegistry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.bi;
import com.trolmastercard.sexmod.bn;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.et;
import com.trolmastercard.sexmod.f9;
import com.trolmastercard.sexmod.g3;
import com.trolmastercard.sexmod.ge;
import java.io.IOException;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInitRegistries(FMLPreInitializationEvent fMLPreInitializationEvent) {
        GameRegistry.registerWorldGenerator((IWorldGenerator)g3.b(), (int)0);
        bi.a();
        f9.a();
    }

    public void initRegistries(FMLInitializationEvent fMLInitializationEvent) throws IOException {
        Main.setConfigs();
        c.a();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)Main.instance, (IGuiHandler)new et());
        bn.a(false);
        ge.a();
    }

    public void postInit(FMLPostInitializationEvent fMLPostInitializationEvent) throws IOException {
        this.setUpCustomModelsOnServer();
    }

    void setUpCustomModelsOnServer() {
        try {
            if (!FMLCommonHandler.instance().getMinecraftServerInstance().func_71262_S()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw CommonProxy.a(runtimeException);
        }
        br.c(false);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

