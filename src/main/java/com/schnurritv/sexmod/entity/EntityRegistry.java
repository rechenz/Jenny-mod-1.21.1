package com.schnurritv.sexmod.entity;

import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Main.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public static final DeferredRegister<Item> MOD_ITEMS = ModItems.ITEMS;
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MODID);

    // Entities
    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.jenny.JennyEntity>> JENNY = ENTITIES.register("jenny", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.jenny.JennyEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("jenny"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.ellie.EllieEntity>> ELLIE = ENTITIES.register("ellie", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.ellie.EllieEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("ellie"));
            
    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.slime.SlimeEntity>> SLIME = ENTITIES.register("slime", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.slime.SlimeEntity::new, MobCategory.CREATURE).sized(0.6f, 0.9f).build("slime"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.bee.BeeEntity>> BEE = ENTITIES.register("bee", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.bee.BeeEntity::new, MobCategory.CREATURE).sized(0.6f, 1.2f).build("bee"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.bia.BiaEntity>> BIA = ENTITIES.register("bia", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.bia.BiaEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("bia"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.allie.AllieEntity>> ALLIE = ENTITIES.register("allie", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.allie.AllieEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("allie"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.goblin.GoblinEntity>> GOBLIN = ENTITIES.register("goblin", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.goblin.GoblinEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("goblin"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.kobold.KoboldEntity>> KOBOLD = ENTITIES.register("kobold", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.kobold.KoboldEntity::new, MobCategory.CREATURE).sized(0.5f, 1.4f).build("kobold"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.cat.CatEntity>> CAT = ENTITIES.register("cat", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.cat.CatEntity::new, MobCategory.CREATURE).sized(0.6f, 0.8f).build("cat"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.galath.GalathEntity>> GALATH = ENTITIES.register("galath", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.galath.GalathEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("galath"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.manglelie.ManglelieEntity>> MANGLELIE = ENTITIES.register("manglelie", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.manglelie.ManglelieEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("manglelie"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.lucy.LucyEntity>> LUCY = ENTITIES.register("lucy", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.lucy.LucyEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("lucy"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.mika.MikaEntity>> MIKA = ENTITIES.register("mika", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.mika.MikaEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("mika"));

    public static final RegistryObject<EntityType<com.schnurritv.sexmod.entity.momo.MomoEntity>> MOMO = ENTITIES.register("momo", 
            () -> EntityType.Builder.of(com.schnurritv.sexmod.entity.momo.MomoEntity::new, MobCategory.CREATURE).sized(0.3f, 0.3f).build("momo"));

    // Items (Spawn Eggs)
    public static final RegistryObject<Item> JENNY_SPAWN_EGG = ITEMS.register("jenny_spawn_egg", 
            () -> new ForgeSpawnEggItem(JENNY, 0xFFA500, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> ELLIE_SPAWN_EGG = ITEMS.register("ellie_spawn_egg", 
            () -> new ForgeSpawnEggItem(ELLIE, 0xFF69B4, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> SLIME_SPAWN_EGG = ITEMS.register("slime_spawn_egg", 
            () -> new ForgeSpawnEggItem(SLIME, 0x00FF00, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> BEE_SPAWN_EGG = ITEMS.register("bee_spawn_egg", 
            () -> new ForgeSpawnEggItem(BEE, 0xFFFF00, 0x000000, new Item.Properties()));

    public static final RegistryObject<Item> BIA_SPAWN_EGG = ITEMS.register("bia_spawn_egg", 
            () -> new ForgeSpawnEggItem(BIA, 0x0000FF, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> ALLIE_SPAWN_EGG = ITEMS.register("allie_spawn_egg", 
            () -> new ForgeSpawnEggItem(ALLIE, 0x800080, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> GOBLIN_SPAWN_EGG = ITEMS.register("goblin_spawn_egg", 
            () -> new ForgeSpawnEggItem(GOBLIN, 0x006400, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> KOBOLD_SPAWN_EGG = ITEMS.register("kobold_spawn_egg", 
            () -> new ForgeSpawnEggItem(KOBOLD, 0x8B4513, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> CAT_SPAWN_EGG = ITEMS.register("cat_spawn_egg", 
            () -> new ForgeSpawnEggItem(CAT, 0xD2B48C, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> GALATH_SPAWN_EGG = ITEMS.register("galath_spawn_egg", 
            () -> new ForgeSpawnEggItem(GALATH, 0x4B0082, 0x00FFFF, new Item.Properties()));

    public static final RegistryObject<Item> MANGLELIE_SPAWN_EGG = ITEMS.register("manglelie_spawn_egg", 
            () -> new ForgeSpawnEggItem(MANGLELIE, 0x8B0000, 0x000000, new Item.Properties()));

    public static final RegistryObject<Item> LUCY_SPAWN_EGG = ITEMS.register("lucy_spawn_egg", 
            () -> new ForgeSpawnEggItem(LUCY, 0xFF1493, 0xFFFACD, new Item.Properties()));

    public static final RegistryObject<Item> MIKA_SPAWN_EGG = ITEMS.register("mika_spawn_egg", 
            () -> new ForgeSpawnEggItem(MIKA, 0x4169E1, 0xFFD700, new Item.Properties()));

    public static final RegistryObject<Item> MOMO_SPAWN_EGG = ITEMS.register("momo_spawn_egg", 
            () -> new ForgeSpawnEggItem(MOMO, 0x2E8B57, 0xFFFFFF, new Item.Properties()));

    // Creative Tab
    public static final RegistryObject<CreativeModeTab> SEXMOD_TAB = CREATIVE_MODE_TABS.register("sexmod_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.sexmod"))
                    .icon(() -> new ItemStack(JENNY_SPAWN_EGG.get()))
                    .displayItems((parameters, output) -> {
                        // Kobold Egg Item
                        output.accept(com.schnurritv.sexmod.entity.kobold.KoboldEntityRegistry.KOBOLD_EGG_ITEM.get());
                        // Spawn eggs
                        output.accept(JENNY_SPAWN_EGG.get());
                        output.accept(ELLIE_SPAWN_EGG.get());
                        output.accept(SLIME_SPAWN_EGG.get());
                        output.accept(BEE_SPAWN_EGG.get());
                        output.accept(BIA_SPAWN_EGG.get());
                        output.accept(ALLIE_SPAWN_EGG.get());
                        output.accept(GOBLIN_SPAWN_EGG.get());
                        output.accept(KOBOLD_SPAWN_EGG.get());
                        output.accept(CAT_SPAWN_EGG.get());
                        output.accept(GALATH_SPAWN_EGG.get());
                        output.accept(MANGLELIE_SPAWN_EGG.get());
                        output.accept(LUCY_SPAWN_EGG.get());
                        output.accept(MIKA_SPAWN_EGG.get());
                        output.accept(MOMO_SPAWN_EGG.get());
                        // Gifts
                        output.accept(ModItems.RED_ROSE.get());
                        output.accept(ModItems.CHOCOLATE_BOX.get());
                        output.accept(ModItems.TEDDY_BEAR.get());
                        output.accept(ModItems.LOVE_LETTER.get());
                        output.accept(ModItems.DIAMOND_RING.get());
                        output.accept(ModItems.COPPER_GEAR.get());
                        output.accept(ModItems.ENCHANTED_QUILL.get());
                        output.accept(ModItems.MOONLIGHT_LILY.get());
                        output.accept(ModItems.ANCIENT_COIN.get());
                        output.accept(ModItems.GOLDEN_HONEYCOMB.get());
                        output.accept(ModItems.SILVER_BELL.get());
                        output.accept(ModItems.MYSTIC_HERB.get());
                        output.accept(ModItems.DRAGON_SCALE.get());
                        output.accept(ModItems.CRYSTAL_SLIME.get());
                        // Utility
                        output.accept(ModItems.SUMMONING_WHISTLE.get());
                        output.accept(ModItems.HEALING_CHARM.get());
                        output.accept(ModItems.BOND_BRACELET.get());
                        output.accept(ModItems.MEMORY_CRYSTAL.get());
                        // Boss Drops
                        output.accept(ModItems.GALATH_COIN.get());
                        // Guide
                        output.accept(ModItems.GUIDE_BOOK.get());
                    })
                    .build());

    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        ENTITIES.register(bus);
        ITEMS.register(bus);
        MOD_ITEMS.register(bus);
        CREATIVE_MODE_TABS.register(bus);
    }
}
