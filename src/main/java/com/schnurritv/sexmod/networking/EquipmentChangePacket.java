package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.entity.SexFighterEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class EquipmentChangePacket {
    private final int entityId;
    private final int slot;
    private final ItemStack stack;

    public EquipmentChangePacket(int entityId, int slot, ItemStack stack) {
        this.entityId = entityId;
        this.slot = slot;
        this.stack = stack;
    }

    public static void encode(EquipmentChangePacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeInt(msg.slot);
        buf.writeJsonWithCodec(ItemStack.CODEC, msg.stack);
    }

    public static EquipmentChangePacket decode(FriendlyByteBuf buf) {
        int entityId = buf.readInt();
        int slot = buf.readInt();
        ItemStack stack = buf.readJsonWithCodec(ItemStack.CODEC);
        return new EquipmentChangePacket(entityId, slot, stack);
    }

    public static void handle(EquipmentChangePacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;
            Entity entity = player.level().getEntity(msg.entityId);
            if (entity instanceof SexFighterEntity fighter) {
                // Verify the player is the owner
                String ownerUUID = fighter.getEntityData().get(
                    com.schnurritv.sexmod.entity.SexEntity.MASTER_UUID);
                if (!ownerUUID.isEmpty() && !ownerUUID.equals(player.getUUID().toString())) {
                    return; // Not authorized
                }
                fighter.setEquipmentSlot(msg.slot, msg.stack);
            }
        });
        ctx.setPacketHandled(true);
    }
}
