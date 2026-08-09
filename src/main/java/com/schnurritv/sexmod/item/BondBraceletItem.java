package com.schnurritv.sexmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * Bond Bracelet — passive item. While in the player's inventory it slows
 * affection decay for nearby girls. Applied via ModEventBusEvents.
 */
public class BondBraceletItem extends Item {

    public BondBraceletItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dBond Bracelet"));
        tooltip.add(Component.literal("§7Passive: slows affection decay"));
    }
}
