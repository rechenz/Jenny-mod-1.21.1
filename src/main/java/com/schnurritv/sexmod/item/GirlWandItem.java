package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.util.ClientScreenHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Girl Wand / NPC Editor Wand (1.12.2 port: item.npc_editor_wand).
 *
 * Right-click an owned girl to open the NPC editor screen (affection, outfit,
 * rename, send home). Server-authoritative edits via NpcEditPacket.
 *
 * Client screen is opened through ClientScreenHelper reflection so this
 * common class never references client classes (dedicated-server safe).
 */
public class GirlWandItem extends Item {

    public GirlWandItem(Properties properties) {
        super(properties.stacksTo(1).durability(256));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.displayClientMessage(Component.literal(
                "§dGirl Wand §7— right-click a girl you own to edit her."), true);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player,
                                                  net.minecraft.world.entity.LivingEntity entity,
                                                  InteractionHand hand) {
        if (entity instanceof BaseGirlEntity girl) {
            // Client: open the editor screen (server validates ownership on each action)
            if (entity.level().isClientSide) {
                ClientScreenHelper.openNpcEditor(girl);
            } else {
                // Server-side feedback (screen opens client-side)
                String owner = girl.getAffectionData().getOwnerUUID();
                if (!owner.isEmpty() && !owner.equals(player.getStringUUID())) {
                    player.displayClientMessage(Component.literal(
                            "§cThis girl belongs to someone else!"), true);
                    return InteractionResult.FAIL;
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dGirl Wand"));
        tooltip.add(Component.literal("§7Right-click a girl you own: edit affection / outfit / name / home"));
    }

    public static boolean isGirlWand(ItemStack stack) {
        return stack.getItem() instanceof GirlWandItem;
    }
}
