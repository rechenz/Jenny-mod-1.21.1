package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.entity.EntityRegistry;
import com.schnurritv.sexmod.entity.kobold.EyeAndKoboldColor;
import com.schnurritv.sexmod.entity.kobold.KoboldEntity;
import com.schnurritv.sexmod.entity.kobold.KoboldManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

/**
 * Tribe Egg (1.12.2 port) — hatches a kobold into the player's tribe.
 *
 * Right-click to consume the egg and spawn a wild kobold that immediately
 * joins (or creates) the player's tribe. The tribe color is rolled randomly
 * for a fresh tribe, matching the 1.12.2 item.tribe_egg.
 */
public class TribeEggItem extends Item {

    public TribeEggItem(Properties properties) {
        super(properties.stacksTo(16));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            return InteractionResultHolder.success(stack);
        }

        ServerLevel serverLevel = (ServerLevel) level;
        KoboldEntity kobold = EntityRegistry.KOBOLD.get().create(serverLevel);
        if (kobold == null) {
            return InteractionResultHolder.fail(stack);
        }

        // Spawn at player position
        kobold.setPos(player.getX(), player.getY() + 0.5, player.getZ());
        kobold.setYRot(player.getYRot());

        // Find player's existing tribe, or create a new one
        UUID ownerId = player.getUUID();
        UUID existingTribe = KoboldManager.findTribeByOwner(ownerId);
        if (existingTribe != null) {
            kobold.tribeId = existingTribe;
        } else {
            kobold.tribeId = UUID.randomUUID();
            EyeAndKoboldColor color = EyeAndKoboldColor.values()[
                    kobold.getRandom().nextInt(EyeAndKoboldColor.values().length)];
            KoboldManager.createTribe(kobold.tribeId, color);
            KoboldManager.setOwner(kobold.tribeId, ownerId);
        }

        // Tame and add to tribe
        kobold.setTame(true);
        kobold.setMasterUUID(ownerId.toString());
        KoboldManager.addMember(kobold.tribeId, kobold);
        serverLevel.addFreshEntity(kobold);

        // Consume egg
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        player.displayClientMessage(Component.literal(
                "§dThe egg hatches! A kobold joins your tribe!"), true);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dTribe Egg"));
        tooltip.add(Component.literal("§7Right-click to hatch a kobold"));
        tooltip.add(Component.literal("§7It joins your tribe instantly"));
    }
}
