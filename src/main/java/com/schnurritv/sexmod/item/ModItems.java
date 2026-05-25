package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.Main;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Custom items for the mod — gifts, special equipment, and utility items.
 * Each gift item has an associated affection boost value.
 */
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    // ── Gifts (universal, liked by most girls) ──
    public static final RegistryObject<Item> RED_ROSE        = register("red_rose", "A beautiful red rose. Most girls love flowers.");
    public static final RegistryObject<Item> CHOCOLATE_BOX   = register("chocolate_box", "A box of fine chocolates. Sweet and romantic.");
    public static final RegistryObject<Item> TEDDY_BEAR      = register("teddy_bear", "A soft plush teddy bear. Comforting and cute.");
    public static final RegistryObject<Item> LOVE_LETTER     = register("love_letter", "A handwritten love letter. Very personal.");
    public static final RegistryObject<Item> DIAMOND_RING    = register("diamond_ring", "A sparkling diamond ring. The ultimate gift.");

    // ── Character-specific favorites ──
    public static final RegistryObject<Item> COPPER_GEAR     = register("copper_gear", "A finely crafted copper gear. Jenny loves tinkering.");
    public static final RegistryObject<Item> ENCHANTED_QUILL = register("enchanted_quill", "An enchanted quill that writes by itself. Ellie's favorite.");
    public static final RegistryObject<Item> MOONLIGHT_LILY  = register("moonlight_lily", "A lily that glows under moonlight. Allie adores these.");
    public static final RegistryObject<Item> ANCIENT_COIN    = register("ancient_coin", "A coin from a forgotten civilization. Bia collects these.");
    public static final RegistryObject<Item> GOLDEN_HONEYCOMB = register("golden_honeycomb", "Honeycomb infused with gold. Bee's treasure.");
    public static final RegistryObject<Item> SILVER_BELL     = register("silver_bell", "A tiny silver bell with a sweet chime. Cat loves playing with it.");
    public static final RegistryObject<Item> MYSTIC_HERB     = register("mystic_herb", "A rare herb from deep swamps. Goblin shamans value this.");
    public static final RegistryObject<Item> DRAGON_SCALE    = register("dragon_scale", "A gleaming dragon scale. Kobolds revere these.");
    public static final RegistryObject<Item> CRYSTAL_SLIME   = register("crystal_slime", "A crystallized slime ball. Slime girls find these fascinating.");

    // ── Utility items ──
    public static final RegistryObject<Item> SUMMONING_WHISTLE = register("summoning_whistle", "A magical whistle. Calls your closest follower to your side.");
    public static final RegistryObject<Item> HEALING_CHARM     = register("healing_charm", "A charm that slowly heals nearby girls. Passive aura effect.");
    public static final RegistryObject<Item> BOND_BRACELET     = register("bond_bracelet", "Wearing this strengthens the bond with your companion. Slows affection decay.");
    public static final RegistryObject<Item> MEMORY_CRYSTAL    = register("memory_crystal", "Stores a girl's home position. Right-click to set, use to recall.");
    public static final RegistryObject<Item> GUIDE_BOOK        = ITEMS.register("guide_book",
        () -> new GuideBookItem(new Item.Properties()));

    // ── Boss Drops ──
    public static final RegistryObject<Item> GALATH_COIN = ITEMS.register("galath_coin",
        () -> new GalathCoinItem(new Item.Properties()));

    // ── Helper ──
    private static RegistryObject<Item> register(String name, String tooltip) {
        return ITEMS.register(name, () -> new GiftItem(new Item.Properties(), tooltip, getAffectionValue(name)));
    }

    /** Affection boost value for each gift item */
    public static int getAffectionValue(String itemName) {
        return switch (itemName) {
            case "red_rose"         -> 5;
            case "chocolate_box"    -> 8;
            case "teddy_bear"       -> 10;
            case "love_letter"      -> 15;
            case "diamond_ring"     -> 25;
            case "copper_gear", "enchanted_quill", "moonlight_lily",
                 "ancient_coin", "golden_honeycomb", "silver_bell",
                 "mystic_herb", "dragon_scale", "crystal_slime" -> 20;
            case "summoning_whistle" -> 0;
            case "healing_charm"     -> 0;
            case "bond_bracelet"     -> 0;
            case "memory_crystal"    -> 0;
            default -> 0;
        };
    }
}
