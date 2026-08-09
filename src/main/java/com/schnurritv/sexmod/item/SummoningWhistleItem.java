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

    private static final int COOLDOWN_TICKS = 40; // 2s

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            return InteractionResultHolder.success(stack);
        }

        // Cooldown (audit L5): prevent spam-calling
        if (player.getCooldowns().isOnCooldown(this)) {
            player.displayClientMessage(Component.literal("§7The whistle needs a moment to recharge..."), true);
            return InteractionResultHolder.success(stack);
        }

        BaseGirlEntity nearest = null;
        double bestDist = 64 * 64;
        String pid = player.getStringUUID();
        for (BaseGirlEntity girl : level.getEntitiesOfClass(BaseGirlEntity.class, player.getBoundingBox().inflate(64))) {
            if (!girl.isAlive()) continue;
            // Only summon girls owned by this player. Strict match (audit M3):
            // unowned girls (owner empty) are NOT pulled — they may belong to
            // another player or be freshly spawned.
            String owner = girl.getAffectionData().getOwnerUUID();
            if (!pid.equals(owner)) continue;
            double d = girl.distanceToSqr(player);
            if (d < bestDist) {
                bestDist = d;
                nearest = girl;
            }
        }
        if (nearest == null) {
            player.displayClientMessage(Component.literal("§7No girl you own is nearby to call."), true);
            return InteractionResultHolder.success(stack);
        }
        // Safe landing spot (audit L5): search upward for open air at player's position
        net.minecraft.core.BlockPos.MutableBlockPos pos = player.blockPosition().mutable();
        while (pos.getY() < level.getMaxBuildHeight() - 1
                && !(level.getBlockState(pos).isAir() && level.getBlockState(pos.above()).isAir())) {
            pos.move(0, 1, 0);
        }
        nearest.teleportTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        player.displayClientMessage(Component.literal("§d" + nearest.getGirlName() + " hurries to your side!"), true);
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dSummoning Whistle"));
        tooltip.add(Component.literal("§7Right-click: calls your closest girl"));
    }
}
