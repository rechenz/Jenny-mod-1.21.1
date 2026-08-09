package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

/**
 * Memory Crystal (home-return system).
 *
 * Right-click: bind to the nearest girl — stores her home position.
 * Shift + right-click: teleport to the bound girl's home.
 *
 * This is the 1.12.2 "go home" mechanic: girls return to their houses at
 * night; the crystal lets the player follow them.
 */
public class MemoryCrystalItem extends Item {

    private static final String TAG_OWNER = "BoundOwner";

    public MemoryCrystalItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            return InteractionResultHolder.success(stack);
        }

        if (player.isShiftKeyDown()) {
            // Recall: teleport to bound girl's home
            var customData = stack.getOrDefault(net.minecraft.core.component.DataComponents.CUSTOM_DATA,
                    net.minecraft.world.item.component.CustomData.EMPTY);
            String ownerUUID = customData.copyTag().getString(TAG_OWNER);
            if (ownerUUID.isEmpty()) {
                player.displayClientMessage(Component.literal("§7Crystal is empty. Right-click near a girl to bind."), true);
                return InteractionResultHolder.success(stack);
            }
            // Direct UUID lookup via LevelEntityGetter (audit L4: avoid full-entity scan)
            Entity bound = ((ServerLevel) level).getEntities().get(java.util.UUID.fromString(ownerUUID));
            if (bound instanceof BaseGirlEntity girl) {
                if (girl.getHousePos() != null) {
                    var pos = girl.getHousePos();
                    // Safe landing spot (audit L4): search upward for open air at the house
                    net.minecraft.core.BlockPos.MutableBlockPos landing = pos.mutable();
                    while (landing.getY() < level.getMaxBuildHeight() - 1
                            && !(level.getBlockState(landing).isAir()
                                    && level.getBlockState(landing.above()).isAir())) {
                        landing.move(0, 1, 0);
                    }
                    player.teleportTo(landing.getX() + 0.5, landing.getY(), landing.getZ() + 0.5);
                    player.displayClientMessage(Component.literal("§dYou recall the path home..."), true);
                } else {
                    player.displayClientMessage(Component.literal("§7" + girl.getGirlName() + " has no home yet."), true);
                }
            } else {
                player.displayClientMessage(Component.literal("§7Your bound girl is too far away or gone."), true);
            }
            return InteractionResultHolder.success(stack);
        }

        // Bind: find nearest girl within 16 blocks (audit L5: only bind
        // girls you own — or unowned girls — so nobody can stalk others' homes)
        BaseGirlEntity nearest = null;
        double bestDist = 16 * 16;
        String pid = player.getStringUUID();
        for (BaseGirlEntity girl : level.getEntitiesOfClass(BaseGirlEntity.class, player.getBoundingBox().inflate(16))) {
            if (!girl.isAlive()) continue;
            String owner = girl.getAffectionData().getOwnerUUID();
            if (!owner.isEmpty() && !owner.equals(pid)) continue;
            double d = girl.distanceToSqr(player);
            if (d < bestDist) {
                bestDist = d;
                nearest = girl;
            }
        }
        if (nearest == null) {
            player.displayClientMessage(Component.literal("§7No girl nearby to bind to."), true);
            return InteractionResultHolder.success(stack);
        }
        final BaseGirlEntity boundGirl = nearest;
        stack.update(net.minecraft.core.component.DataComponents.CUSTOM_DATA,
                net.minecraft.world.item.component.CustomData.EMPTY,
                custom -> custom.update(tag -> tag.putString(TAG_OWNER, boundGirl.getStringUUID())));        player.displayClientMessage(Component.literal("§dCrystal bound to " + nearest.getGirlName()
                + " (home: " + (nearest.getHousePos() != null ? nearest.getHousePos().toShortString() : "none") + ")"), true);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dMemory Crystal"));
        tooltip.add(Component.literal("§7Right-click: bind to nearest girl"));
        tooltip.add(Component.literal("§7Shift+right-click: teleport to her home"));
    }
}
