package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.entity.EntityRegistry;
import com.schnurritv.sexmod.entity.galath.GalathEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GalathCoinItem extends Item {

    public GalathCoinItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide) {
            return InteractionResultHolder.success(stack);
        }

        // Summon a tamed Galath at the player's position
        GalathEntity galath = EntityRegistry.GALATH.get().create(level);
        if (galath != null) {
            // Set position to player's location
            galath.setPos(player.getX(), player.getY(), player.getZ());
            galath.setYRot(player.getYRot());
            galath.setXRot(player.getXRot());

            // Set owner (master UUID)
            galath.getEntityData().set(com.schnurritv.sexmod.entity.SexEntity.MASTER_UUID, player.getUUID().toString());

            // Unlock the entity so it can move
            galath.getEntityData().set(com.schnurritv.sexmod.entity.SexEntity.IS_LOCKED, false);

            // Spawn in world
            level.addFreshEntity(galath);

            // Consume the coin
            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.consume(stack);
    }
}
