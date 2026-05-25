package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.SexModConfig;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ClothingTogglePacket {
    private final int entityId;
    private final int state;

    public ClothingTogglePacket(int entityId, int state) {
        this.entityId = entityId;
        this.state = state;
    }

    public static void encode(ClothingTogglePacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeInt(msg.state);
    }

    public static ClothingTogglePacket decode(FriendlyByteBuf buf) {
        return new ClothingTogglePacket(buf.readInt(), buf.readInt());
    }

    public static void handle(ClothingTogglePacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;
            Entity entity = player.level().getEntity(msg.entityId);
            if (entity instanceof BaseGirlEntity girl) {
                // Only allow if affection is high enough
                int aff = girl.getAffection();
                int threshold = SexModConfig.CLOTHING_AFFECTION_THRESHOLD.get();
                if (aff >= threshold) {
                    girl.getEntityData().set(SexEntity.CLOTHING_STATE, msg.state);
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
