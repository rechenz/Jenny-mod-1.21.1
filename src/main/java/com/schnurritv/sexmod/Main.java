package com.schnurritv.sexmod;

import com.schnurritv.sexmod.command.ModCommands;
import com.schnurritv.sexmod.networking.NetworkHandler;
import com.schnurritv.sexmod.entity.EntityRegistry;
import com.schnurritv.sexmod.entity.kobold.KoboldEntityRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "sexmod";
    public static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        EntityRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
        KoboldEntityRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        KoboldEntityRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        net.minecraftforge.fml.ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, SexModConfig.CLIENT_SPEC);
        net.minecraftforge.fml.ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, SexModConfig.SERVER_SPEC);
        MinecraftForge.EVENT_BUS.register(this);
        // Register debug commands
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(NetworkHandler::register);
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }
}