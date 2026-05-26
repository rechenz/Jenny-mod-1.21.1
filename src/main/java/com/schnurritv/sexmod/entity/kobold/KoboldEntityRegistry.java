package com.schnurritv.sexmod.entity.kobold;

import com.schnurritv.sexmod.Main;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registry for Kobold-specific entities and items.
 */
public class KoboldEntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Main.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    // Kobold entity (already registered in EntityRegistry, but we need it here too for egg creation)
    // We reference the existing one by name — no duplicate registration

    // Kobold Egg Entity
    public static final RegistryObject<EntityType<KoboldEggEntity>> KOBOLD_EGG = ENTITIES.register("kobold_egg",
            () -> EntityType.Builder.<KoboldEggEntity>of(KoboldEggEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("kobold_egg"));

    // Kobold Egg Item
    public static final RegistryObject<Item> KOBOLD_EGG_ITEM = ITEMS.register("kobold_egg_item",
            () -> new KoboldEggItem(new Item.Properties().stacksTo(1)));
}
