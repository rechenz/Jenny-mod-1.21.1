package com.schnurritv.sexmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * Healing Charm — passive aura item. While in the player's inventory it
 * slowly heals nearby girls. The tick effect is applied via
 * ModEventBusEvents.onInventoryTick.
 */
public class HealingCharmItem extends Item {

    public HealingCharmItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dHealing Charm"));
        tooltip.add(Component.literal("§7Passive: heals nearby girls slowly"));
    }
}
