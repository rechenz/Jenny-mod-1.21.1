package com.schnurritv.sexmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Dragon Staff (1.12.2 port) — the Kobold taming wand.
 *
 * Right-click a kobold with this staff to tame it (creates/joins a tribe).
 * This replaces the ad-hoc vanilla stick in KoboldEntity.mobInteract with a
 * proper mod item, matching the original 1.12.2 item.dragon_staff.
 */
public class DragonStaffItem extends Item {

    public DragonStaffItem(Properties properties) {
        super(properties.stacksTo(1).durability(128));
    }

    @Override
    public InteractionResult useOn(net.minecraft.world.item.context.UseOnContext context) {
        // Taming is handled in KoboldEntity.mobInteract when the staff is held.
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dDragon Staff"));
        tooltip.add(Component.literal("§7Right-click a Kobold to tame it"));
        tooltip.add(Component.literal("§7Creates or joins a tribe"));
    }

    public static boolean isDragonStaff(ItemStack stack) {
        return stack.getItem() instanceof DragonStaffItem;
    }
}
