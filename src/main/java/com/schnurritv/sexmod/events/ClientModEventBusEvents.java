package com.schnurritv.sexmod.events;

import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.entity.EntityRegistry;
import com.schnurritv.sexmod.client.renderer.GirlRenderer;
import com.schnurritv.sexmod.client.renderer.KoboldEggRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Client-only renderer registration. Kept in its own file so the common
 * ModEventBusEvents class never references client classes (dedicated servers
 * would fail verification otherwise).
 */
@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventBusEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.JENNY.get(), ctx -> new GirlRenderer<>(ctx, "jenny"));
        event.registerEntityRenderer(EntityRegistry.ELLIE.get(), ctx -> new GirlRenderer<>(ctx, "ellie"));
        event.registerEntityRenderer(EntityRegistry.SLIME.get(), ctx -> new GirlRenderer<>(ctx, "slime"));
        event.registerEntityRenderer(EntityRegistry.BEE.get(), ctx -> new GirlRenderer<>(ctx, "bee"));
        event.registerEntityRenderer(EntityRegistry.BIA.get(), ctx -> new GirlRenderer<>(ctx, "bia"));
        event.registerEntityRenderer(EntityRegistry.ALLIE.get(), ctx -> new GirlRenderer<>(ctx, "allie"));
        event.registerEntityRenderer(EntityRegistry.GOBLIN.get(), ctx -> new GirlRenderer<>(ctx, "goblin"));
        event.registerEntityRenderer(EntityRegistry.KOBOLD.get(), ctx -> new GirlRenderer<>(ctx, "kobold"));
        event.registerEntityRenderer(EntityRegistry.CAT.get(), ctx -> new GirlRenderer<>(ctx, "cat"));
        event.registerEntityRenderer(EntityRegistry.GALATH.get(), ctx -> new GirlRenderer<>(ctx, "galath"));
        event.registerEntityRenderer(EntityRegistry.MANGLELIE.get(), ctx -> new GirlRenderer<>(ctx, "manglelie"));
        event.registerEntityRenderer(EntityRegistry.LUCY.get(), ctx -> new GirlRenderer<>(ctx, "lucy"));
        event.registerEntityRenderer(EntityRegistry.MIKA.get(), ctx -> new GirlRenderer<>(ctx, "mika"));
        event.registerEntityRenderer(EntityRegistry.MOMO.get(), ctx -> new GirlRenderer<>(ctx, "momo"));
        // Kobold Egg renderer
        event.registerEntityRenderer(com.schnurritv.sexmod.entity.kobold.KoboldEntityRegistry.KOBOLD_EGG.get(),
                KoboldEggRenderer::new);
    }
}
