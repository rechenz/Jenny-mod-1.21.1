package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
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
 * Summoning Whistle — calls the player's closest tamed girl to their side.
 *
 * Teleports the nearest girl (with matching owner) within 64 blocks to the
 * player. Faithful to the 1.12.2 follower-whistle concept.
 */
public class SummoningWhistleItem extends Item {

    public SummoningWhistleItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            return InteractionResultHolder.success(stack);
        }

        BaseGirlEntity nearest = null;
        double bestDist = 64 * 64;
        String pid = player.getStringUUID();
        for (BaseGirlEntity girl : level.getEntitiesOfClass(BaseGirlEntity.class, player.getBoundingBox().inflate(64))) {
            if (!girl.isAlive()) continue;
            // Only summon girls owned by this player
            String owner = girl.getAffectionData().getOwnerUUID();
            if (owner != null && !owner.isEmpty() && !owner.equals(pid)) continue;
            double d = girl.distanceToSqr(player);
            if (d < bestDist) {
                bestDist = d;
                nearest = girl;
            }
        }
        if (nearest == null) {
            player.displayClientMessage(Component.literal("§7No girl nearby to call."), true);
            return InteractionResultHolder.success(stack);
        }
        nearest.teleportTo(player.getX() + 1, player.getY(), player.getZ() + 1);
        player.displayClientMessage(Component.literal("§d" + nearest.getGirlName() + " hurries to your side!"), true);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dSummoning Whistle"));
        tooltip.add(Component.literal("§7Right-click: calls your closest girl"));
    }
}
