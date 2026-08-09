package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.entity.SexFighterEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

/**
 * Equipment transaction request (client → server).
 *
 * The server is authoritative for ALL inventory changes. The client only sends
 * a request with an action type and slot indices; the server validates and
 * executes the actual item transfer (player inventory ↔ fighter equipment).
 *
 * Actions:
 *  - "equip":   move 1 item from player inventory slot {@code srcSlot} into
 *               fighter equipment slot {@code slot}. Any item currently in the
 *               target slot is first returned to the player (or dropped).
 *  - "unequip": move the item in fighter equipment slot {@code slot} back into
 *               the player's inventory (or drop it if the inventory is full).
 */
public class EquipmentChangePacket {
    private final int entityId;
    private final int slot;     // fighter equipment slot (0-5)
    private final String action; // "equip" | "unequip"
    private final int srcSlot;  // player inventory slot (0-35), only for "equip"

    public EquipmentChangePacket(int entityId, String action, int slot, int srcSlot) {
        this.entityId = entityId;
        this.action = action;
        this.slot = slot;
        this.srcSlot = srcSlot;
    }

    public static void encode(EquipmentChangePacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.action);
        buf.writeInt(msg.slot);
        buf.writeInt(msg.srcSlot);
    }

    public static EquipmentChangePacket decode(FriendlyByteBuf buf) {
        int entityId = buf.readInt();
        String action = buf.readUtf(16);
        int slot = buf.readInt();
        int srcSlot = buf.readInt();
        return new EquipmentChangePacket(entityId, action, slot, srcSlot);
    }

    public static void handle(EquipmentChangePacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;
            Entity entity = player.level().getEntity(msg.entityId);
            if (!(entity instanceof SexFighterEntity fighter)) return;

            // Verify the player is the owner
            String ownerUUID = fighter.getEntityData().get(
                com.schnurritv.sexmod.entity.SexEntity.MASTER_UUID);
            if (!ownerUUID.isEmpty() && !ownerUUID.equals(player.getUUID().toString())) {
                return; // Not authorized
            }

            if (msg.slot < 0 || msg.slot >= 6) return;

            switch (msg.action) {
                case "equip" -> {
                    // Source slot must be a real player inventory slot (0-35)
                    if (msg.srcSlot < 0 || msg.srcSlot >= player.getInventory().getContainerSize()) return;
                    ItemStack source = player.getInventory().getItem(msg.srcSlot);
                    if (source.isEmpty()) return;

                    // If the target equipment slot already has an item, return it first
                    ItemStack current = fighter.getEquipmentSlot(msg.slot);
                    if (!current.isEmpty()) {
                        if (!player.getInventory().add(current)) {
                            player.drop(current, false);
                        }
                    }

                    // Equip 1 item from the source stack
                    ItemStack toEquip = source.copy();
                    toEquip.setCount(1);
                    fighter.setEquipmentSlot(msg.slot, toEquip);
                    player.getInventory().removeItem(msg.srcSlot, 1);
                }
                case "unequip" -> {
                    ItemStack current = fighter.getEquipmentSlot(msg.slot);
                    if (current.isEmpty()) return;

                    // Return the item to the player (or drop it if inventory is full)
                    if (!player.getInventory().add(current)) {
                        player.drop(current, false);
                    }
                    fighter.setEquipmentSlot(msg.slot, ItemStack.EMPTY);
                }
                default -> { /* unknown action — ignore */ }
            }
        });
        ctx.setPacketHandled(true);
    }
}
