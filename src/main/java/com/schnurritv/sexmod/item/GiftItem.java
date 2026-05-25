package com.schnurritv.sexmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class GiftItem extends Item {
    private final String tooltipKey;
    private final int affectionValue;

    public GiftItem(Properties properties, String tooltipKey, int affectionValue) {
        super(properties);
        this.tooltipKey = tooltipKey;
        this.affectionValue = affectionValue;
    }

    public int getAffectionValue() {
        return affectionValue;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("❤ +" + affectionValue).withStyle(net.minecraft.ChatFormatting.RED));
        tooltip.add(Component.literal(tooltipKey).withStyle(net.minecraft.ChatFormatting.GRAY));
    }
}
