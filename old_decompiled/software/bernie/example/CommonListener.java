/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  net.minecraftforge.client.event.ModelRegistryEvent
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.registry.EntityEntry
 *  net.minecraftforge.fml.common.registry.EntityEntryBuilder
 *  net.minecraftforge.fml.common.registry.GameRegistry
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package software.bernie.example;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import software.bernie.example.GeckoLibMod;
import software.bernie.example.block.BotariumBlock;
import software.bernie.example.block.FertilizerBlock;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.example.client.renderer.item.JackInTheBoxRenderer;
import software.bernie.example.entity.BikeEntity;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.example.entity.GeoExampleEntityLayer;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.example.item.PotatoArmorItem;
import software.bernie.example.registry.BlockRegistry;
import software.bernie.example.registry.ItemRegistry;
import software.bernie.example.registry.SoundRegistry;

public class CommonListener {
    private static IForgeRegistry<Item> itemRegistry;
    private static IForgeRegistry<Block> blockRegistry;

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        blockRegistry = event.getRegistry();
        BlockRegistry.BOTARIUM_BLOCK = new BotariumBlock();
        BlockRegistry.FERTILIZER_BLOCK = new FertilizerBlock();
        BlockRegistry.BOTARIUM_BLOCK.func_149647_a(GeckoLibMod.getGeckolibItemGroup());
        BlockRegistry.FERTILIZER_BLOCK.func_149647_a(GeckoLibMod.getGeckolibItemGroup());
        CommonListener.registerBlock((Block)BlockRegistry.BOTARIUM_BLOCK, "botariumblock");
        CommonListener.registerBlock((Block)BlockRegistry.FERTILIZER_BLOCK, "fertilizerblock");
    }

    @SubscribeEvent
    public void onRegisterEntities(RegistryEvent.Register<EntityEntry> event) {
        int id = 0;
        event.getRegistry().register((IForgeRegistryEntry)EntityEntryBuilder.create().entity(BikeEntity.class).name("Bike").id(new ResourceLocation("geckolib3", "bike"), id++).tracker(160, 2, false).build());
        event.getRegistry().register((IForgeRegistryEntry)EntityEntryBuilder.create().entity(GeoExampleEntity.class).name("Example").id(new ResourceLocation("geckolib3", "example"), id++).tracker(160, 2, false).build());
        event.getRegistry().register((IForgeRegistryEntry)EntityEntryBuilder.create().entity(GeoExampleEntityLayer.class).name("ExampleLayer").id(new ResourceLocation("geckolib3", "examplelayer"), id++).tracker(160, 2, false).build());
        GameRegistry.registerTileEntity(BotariumTileEntity.class, (ResourceLocation)new ResourceLocation("geckolib3", "botariumtile"));
        GameRegistry.registerTileEntity(FertilizerTileEntity.class, (ResourceLocation)new ResourceLocation("geckolib3", "fertilizertile"));
    }

    @SubscribeEvent
    public void onRegisterItems(RegistryEvent.Register<Item> event) {
        itemRegistry = event.getRegistry();
        ItemRegistry.JACK_IN_THE_BOX = CommonListener.registerItem(new JackInTheBoxItem(), "jackintheboxitem");
        ItemRegistry.POTATO_HEAD = CommonListener.registerItem(new PotatoArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.HEAD), "potato_head");
        ItemRegistry.POTATO_CHEST = CommonListener.registerItem(new PotatoArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.CHEST), "potato_chest");
        ItemRegistry.POTATO_LEGGINGS = CommonListener.registerItem(new PotatoArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.LEGS), "potato_leggings");
        ItemRegistry.POTATO_BOOTS = CommonListener.registerItem(new PotatoArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.FEET), "potato_boots");
        ItemRegistry.BOTARIUM = CommonListener.registerItem(new ItemBlock((Block)BlockRegistry.BOTARIUM_BLOCK), "botarium");
        ItemRegistry.FERTILIZER = CommonListener.registerItem(new ItemBlock((Block)BlockRegistry.FERTILIZER_BLOCK), "fertilizer");
    }

    public static <T extends Item> T registerItem(T item, String name) {
        CommonListener.registerItem(item, new ResourceLocation("geckolib3", name));
        return item;
    }

    public static <T extends Item> T registerItem(T item, ResourceLocation name) {
        itemRegistry.register((IForgeRegistryEntry)((Item)item.setRegistryName(name)).func_77655_b(name.toString().replace(":", ".")));
        return item;
    }

    public static void registerBlock(Block block, String name) {
        CommonListener.registerBlock(block, new ResourceLocation("geckolib3", name));
    }

    public static void registerBlock(Block block, ResourceLocation name) {
        blockRegistry.register((IForgeRegistryEntry)((Block)block.setRegistryName(name)).func_149663_c(name.toString().replace(":", ".")));
    }

    @SubscribeEvent
    public void onRegisterSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        ResourceLocation location = new ResourceLocation("geckolib3", "jack_music");
        SoundRegistry.JACK_MUSIC = (SoundEvent)new SoundEvent(location).setRegistryName(location);
        event.getRegistry().register((IForgeRegistryEntry)SoundRegistry.JACK_MUSIC);
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void onModelRegistry(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation((Item)ItemRegistry.JACK_IN_THE_BOX, (int)0, (ModelResourceLocation)new ModelResourceLocation("geckolib3:jackintheboxitem", "inventory"));
        ModelLoader.setCustomModelResourceLocation((Item)ItemRegistry.BOTARIUM, (int)0, (ModelResourceLocation)new ModelResourceLocation("geckolib3:botarium", "inventory"));
        ModelLoader.setCustomModelResourceLocation((Item)ItemRegistry.FERTILIZER, (int)0, (ModelResourceLocation)new ModelResourceLocation("geckolib3:fertilizer", "inventory"));
        ModelLoader.setCustomModelResourceLocation((Item)ItemRegistry.POTATO_HEAD, (int)0, (ModelResourceLocation)new ModelResourceLocation("geckolib3:potato_head", "inventory"));
        ModelLoader.setCustomModelResourceLocation((Item)ItemRegistry.POTATO_CHEST, (int)0, (ModelResourceLocation)new ModelResourceLocation("geckolib3:potato_chest", "inventory"));
        ModelLoader.setCustomModelResourceLocation((Item)ItemRegistry.POTATO_LEGGINGS, (int)0, (ModelResourceLocation)new ModelResourceLocation("geckolib3:potato_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation((Item)ItemRegistry.POTATO_BOOTS, (int)0, (ModelResourceLocation)new ModelResourceLocation("geckolib3:potato_boots", "inventory"));
        ItemRegistry.JACK_IN_THE_BOX.setTileEntityItemStackRenderer(new JackInTheBoxRenderer());
    }
}

