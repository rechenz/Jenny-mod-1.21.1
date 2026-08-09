package com.schnurritv.sexmod.events;

import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.entity.EntityRegistry;
import com.schnurritv.sexmod.entity.SexEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.JENNY.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.ELLIE.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.SLIME.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.BEE.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.BIA.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.ALLIE.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.GOBLIN.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.KOBOLD.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.CAT.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.GALATH.get(), com.schnurritv.sexmod.entity.galath.GalathEntity.createBossAttributes().build());
        event.put(EntityRegistry.MANGLELIE.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.LUCY.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.MIKA.get(), SexEntity.createAttributes().build());
        event.put(EntityRegistry.MOMO.get(), SexEntity.createAttributes().build());
        // Kobold Egg
        event.put(com.schnurritv.sexmod.entity.kobold.KoboldEntityRegistry.KOBOLD_EGG.get(), SexEntity.createAttributes().build());
    }
}
