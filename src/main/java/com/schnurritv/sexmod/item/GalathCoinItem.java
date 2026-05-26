package com.schnurritv.sexmod.item;

import com.schnurritv.sexmod.entity.EntityRegistry;
import com.schnurritv.sexmod.entity.galath.GalathEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GalathCoinItem extends Item {

    private static final int DESPAWN_DELAY = 40; // 2 seconds

    public GalathCoinItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Check if there's already a Galath owned by this player in the world
            GalathEntity existing = findOwnedGalath(level, player);

            if (existing != null) {
                // Desummon: set despawn timer, send her back to the coin
                existing.getEntityData().set(SexEntity.IS_LOCKED, false);
                existing.getEntityData().set(SexEntity.MASTER_UUID, "");
                existing.despawnTimer = DESPAWN_DELAY;
                player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal("§5Galath retreats back into her coin!"), true);
            } else {
                // Summon a new Galath at the player's position
                GalathEntity galath = EntityRegistry.GALATH.get().create(level);
                if (galath != null) {
                    galath.setPos(player.getX(), player.getY(), player.getZ());
                    galath.setYRot(player.getYRot());
                    galath.setXRot(player.getXRot());
                    galath.getEntityData().set(SexEntity.MASTER_UUID, player.getUUID().toString());
                    galath.getEntityData().set(SexEntity.IS_LOCKED, false);
                    galath.setTarget(null);
                    galath.despawnTimer = -1;
                    level.addFreshEntity(galath);
                    player.displayClientMessage(
                        net.minecraft.network.chat.Component.literal("§5Your soul binds to Galath! She arises before you."), true);
                }
            }
        }

        // Always play the animation on client
        return InteractionResultHolder.success(stack);
    }

    /** Find a Galath owned by this player */
    public static GalathEntity findOwnedGalath(Level level, Player player) {
        String playerUUID = player.getUUID().toString();
        // Iterate all loaded entities of GalathEntity type
        for (GalathEntity galath : level.getEntitiesOfClass(GalathEntity.class,
                net.minecraft.world.phys.AABB.ofSize(player.position(), 9999, 9999, 9999))) {
            if (galath.isAlive()) {
                String master = galath.getEntityData().get(SexEntity.MASTER_UUID);
                if (playerUUID.equals(master)) {
                    return galath;
                }
            }
        }
        return null;
    }
}
