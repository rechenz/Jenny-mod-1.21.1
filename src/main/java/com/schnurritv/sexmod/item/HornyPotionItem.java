package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.effects.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Horny Potion (1.12.2 port) — grants a short buff and unlocks free interaction
 * with untamed kobolds (the 1.12.2 mechanic: kobolds are free to interact with
 * while the player is under this effect).
 */
public class HornyPotionItem extends Item {

    public HornyPotionItem(Properties properties) {
        super(properties.stacksTo(16)
                .food(new FoodProperties.Builder()
                        .alwaysEdible()
                        .nutrition(1)
                        .saturationModifier(0)
                        // Dedicated marker effect (audit M2) — vanilla Regen+Speed
                        // would false-positive on golden apples / beacons / other mods.
                        .effect(new MobEffectInstance(ModEffects.hornyHolder(), 600, 0), 1.0f)
                        .effect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.REGENERATION, 200, 0), 1.0f)
                        .effect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 400, 0), 1.0f)
                        .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            player.displayClientMessage(Component.literal("§dHorny mode activated! Kobolds are friendlier~"), true);
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dPotion of Horny"));
        tooltip.add(Component.literal("§7Untamed kobolds interact for FREE while under this effect"));
        tooltip.add(Component.literal("§7Regeneration + Speed buff"));
    }
}
