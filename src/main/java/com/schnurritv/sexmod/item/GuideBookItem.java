package com.schnurritv.sexmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

/**
 * In-game guide book explaining mod mechanics.
 * Right-click to open a multi-page info screen.
 */
public class GuideBookItem extends Item {

    private static final String[] PAGES = {
        // Page 1: Welcome
        "§d§lWelcome to Jenny Mod!\n\n" +
        "This mod adds 9 unique companion girls to your Minecraft world. " +
        "Each has her own personality, abilities, and preferences.\n\n" +
        "§7→ Right-click a girl to interact\n" +
        "§7→ Give gifts to raise affection\n" +
        "§7→ Complete quests for rewards\n" +
        "§7→ Unlock scenes as you bond",

        // Page 2: Characters
        "§d§lMeet the Girls\n\n" +
        "§6Jenny§r — Gamer, loves Copper Gears\n" +
        "§cEllie§r — Warrior, loves Enchanted Quills\n" +
        "§aAllie§r — Healer, loves Moonlight Lilies\n" +
        "§5Bia§r — Mystic, loves Ancient Coins\n" +
        "§eBee§r — Bubbly bee, loves Golden Honeycomb\n" +
        "§bCat§r — Lazy cat, loves Silver Bells\n" +
        "§2Goblin§r — Scavenger, loves Mystic Herbs\n" +
        "§6Kobold§r — Cave dweller, loves Dragon Scales\n" +
        "§aSlime§r — Curious, loves Crystal Slime",

        // Page 3: Gifts & Affection
        "§d§lGifts & Affection\n\n" +
        "Give gifts by right-clicking a girl while holding an item. " +
        "Gifts raise affection (❤), which unlocks:\n\n" +
        "§7❤ 0-9 §8Stranger\n" +
        "§7❤ 10-29 §8Acquainted\n" +
        "§7❤ 30-59 §8Friendly\n" +
        "§7❤ 60-79 §8Close\n" +
        "§7❤ 80-100 §8Intimate\n\n" +
        "Favorite gifts give +5 bonus affection!\n" +
        "Affection decays if you don't visit for days.",

        // Page 4: Scenes
        "§d§lScenes\n\n" +
        "As affection grows, you unlock scenes:\n\n" +
        "§7❤ 30+ §rMissionary & Blowjob\n" +
        "§7❤ 60+ §rDoggy & Boobjob\n\n" +
        "During scenes:\n" +
        "§7→ Click Thrust to toggle speed\n" +
        "§7→ Click Stop to end\n" +
        "§7→ Camera auto-adjusts\n\n" +
        "§cScenes require a bed nearby!",

        // Page 5: Quests
        "§d§lQuest System\n\n" +
        "Each girl can give you fetch quests!\n\n" +
        "§7→ Open interaction menu\n" +
        "§7→ Click 'Ask for a quest'\n" +
        "§7→ Gather the requested items\n" +
        "§7→ Hand them directly to complete\n\n" +
        "Rewards include affection bonuses " +
        "and special items!\n\n" +
        "Each quest is unique per character.",

        // Page 6: Character Abilities
        "§d§lCharacter Abilities\n\n" +
        "§cEllie§r — Attacks hostile mobs near owner\n" +
        "§aAllie§r — Heals nearby players (8-block radius)\n" +
        "§5Bia§r — Grants Night Vision to owner\n" +
        "§eBee§r — Slow Falling + Speed buffs\n" +
        "§bCat§r — Invisibility when owner crouches\n" +
        "§2Goblin§r — Auto-pickup nearby items\n" +
        "§6Kobold§r — Haste II for owner\n" +
        "§aSlime§r — Jump Boost II for owner\n" +
        "§6Jenny§r — Idle chatter (emotional support!)",

        // Page 7: Jealousy & Tips
        "§d§lJealousy & Tips\n\n" +
        "Girls get jealous if you bond with others! " +
        "Having multiple girls nearby with high affection " +
        "causes periodic affection loss.\n\n" +
        "§7Tips:\n" +
        "§7→ Favorite gifts give +5 extra ❤\n" +
        "§7→ First gift ever gives +10 bonus\n" +
        "§7→ Visit daily to prevent decay\n" +
        "§7→ Check the HUD for quest progress\n" +
        "§7→ Creative players don't consume gifts",

        // Page 8: Crafting & Items
        "§d§lSpecial Items\n\n" +
        "§7Summoning Whistle§r — Summons nearest girl\n" +
        "§7Healing Charm§r — Quest reward from Allie\n" +
        "§7Bond Bracelet§r — Prevents affection decay\n" +
        "§7Memory Crystal§r — Bia's memory keepsake\n\n" +
        "Most items are found as quest rewards. " +
        "Check your crafting book for recipes!\n\n" +
        "§7Craft: 1 Book + 1 Rose → Guide Book"
    };

    public GuideBookItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            net.minecraft.client.Minecraft.getInstance().setScreen(
                new GuideBookScreen(stack, PAGES));
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§7Right-click to read"));
        tooltip.add(Component.literal("§d" + PAGES.length + " pages of knowledge"));
    }
}
