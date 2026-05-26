/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.command.ICommand
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.network.IGuiHandler
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.CommonProxy;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.a_;
import com.trolmastercard.sexmod.bn;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.et;
import com.trolmastercard.sexmod.ez;
import com.trolmastercard.sexmod.fd;
import com.trolmastercard.sexmod.fk;
import com.trolmastercard.sexmod.fx;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gj;
import com.trolmastercard.sexmod.w;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy
extends CommonProxy {
    public static boolean IS_PRELOADING = false;
    public static KeyBinding[] keyBindings;

    @Override
    public void postInit(FMLPostInitializationEvent fMLPostInitializationEvent) throws IOException {
    }

    @Override
    public void preInitRegistries(FMLPreInitializationEvent fMLPreInitializationEvent) {
        super.preInitRegistries(fMLPreInitializationEvent);
        fk.a();
    }

    @Override
    public void initRegistries(FMLInitializationEvent fMLInitializationEvent) throws IOException {
        keyBindings = new KeyBinding[2];
        ClientProxy.keyBindings[0] = new KeyBinding("Interact with your goblin", 34, "Sex mod");
        ClientProxy.keyBindings[1] = new KeyBinding("open character customisation menu", 76, "Sex mod");
        for (fy[] fyArray : keyBindings) {
            ClientRegistry.registerKeyBinding((KeyBinding)fyArray);
        }
        Main.setConfigs();
        c.a();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)Main.instance, (IGuiHandler)new et(true));
        bn.a(true);
        ge.a();
        Minecraft minecraft = Minecraft.func_71410_x();
        RenderManager renderManager = minecraft.func_175598_ae();
        gj gj2 = new gj();
        IS_PRELOADING = true;
        try {
            for (fy fy2 : fy.values()) {
                renderManager.func_188391_a((Entity)fy2.npcClass.getDeclaredConstructor(World.class).newInstance(new Object[]{gj2}), 0.0, 0.0, 0.0, 0.0f, 0.0f, false);
            }
        }
        catch (Exception exception) {
            System.out.println("error while preloading:");
            exception.printStackTrace();
        }
        IS_PRELOADING = false;
        w.a = new w();
        ClientCommandHandler.instance.func_71560_a((ICommand)fd.a);
        ClientCommandHandler.instance.func_71560_a((ICommand)fx.a);
        ClientCommandHandler.instance.func_71560_a((ICommand)a_.b);
        Minecraft.func_71410_x().field_71452_i.func_178929_a(625115, (n2, world, d10, d11, d12, d13, d14, d15, nArray) -> new ez(world, d10, d11, d12));
    }
}

