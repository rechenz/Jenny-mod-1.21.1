package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.entity.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.UUID;

/**
 * Allies Lamp — the summoning item for Allie (ported from 1.12.2).
 *
 * Right-click to summon Allie at the player's position. She spawns in her
 * SUMMON animation chain (8-line dialogue + portal particles) and then waits
 * for the player to make a wish.
 *
 * Faithful to the original: Allie has a phobia of sand — the lamp refuses to
 * summon her if the player stands on sand (allie.dialogue.phobia).
 */
public class AlliesLampItem extends Item {

    private static final int COOLDOWN_TICKS = 40; // 2s between summons

    public AlliesLampItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            return InteractionResultHolder.success(stack);
        }

        // Sand phobia: check blocks under the player (a few blocks down in case of stairs)
        BlockPos foot = player.blockPosition();
        boolean onSand = false;
        for (int dy = 0; dy >= -3; dy--) {
            var state = level.getBlockState(foot.offset(0, dy, 0));
            if (state.is(Blocks.SAND) || state.is(Blocks.RED_SAND) || state.is(Blocks.SANDSTONE)
                    || state.is(Blocks.RED_SANDSTONE)) {
                onSand = true;
                break;
            }
        }
        if (onSand) {
            player.displayClientMessage(Component.literal(
                    "<Allie> §cOOOH HECK NO!!1 ... she has a phobia about sand. Move away from it."), true);
            return InteractionResultHolder.success(stack);
        }

        // Check cooldown
        if (player.getCooldowns().isOnCooldown(this)) {
            player.displayClientMessage(Component.literal("§7The lamp is still recharging..."), true);
            return InteractionResultHolder.success(stack);
        }

        // Check if player already has an Allie
        UUID pid = player.getUUID();
        for (var allie : level.getEntitiesOfClass(com.schnurritv.sexmod.entity.allie.AllieEntity.class,
                player.getBoundingBox().inflate(64))) {
            String owner = allie.getEntityData().get(
                    com.schnurritv.sexmod.entity.allie.AllieEntity.SUMMON_OWNER);
            if (pid.toString().equals(owner)) {
                player.displayClientMessage(Component.literal("<Allie> §dI'm already here, mortal~"), true);
                return InteractionResultHolder.success(stack);
            }
        }

        // Summon Allie
        var allie = EntityRegistry.ALLIE.get().create(level);
        if (allie != null) {
            allie.setPos(player.getX(), player.getY() + 0.5, player.getZ());
            allie.setYRot(player.getYRot());
            allie.getEntityData().set(com.schnurritv.sexmod.entity.allie.AllieEntity.SUMMON_OWNER,
                    pid.toString());
            level.addFreshEntity(allie);
            player.displayClientMessage(Component.literal("§dThe lamp shimmers... Allie appears!"), true);
            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§dRub to summon Allie"));
        tooltip.add(Component.literal("§7She grants 3 wishes... and more"));
        tooltip.add(Component.literal("§7⚠ She hates sand!"));
    }
}
