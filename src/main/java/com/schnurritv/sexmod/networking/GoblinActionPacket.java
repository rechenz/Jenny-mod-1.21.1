package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.entity.goblin.GoblinEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class GoblinActionPacket {
    private final int entityId;
    private final String action;

    public GoblinActionPacket(int entityId, String action) {
        this.entityId = entityId;
        this.action = action;
    }

    public static void encode(GoblinActionPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.action);
    }

    public static GoblinActionPacket decode(FriendlyByteBuf buf) {
        return new GoblinActionPacket(buf.readInt(), buf.readUtf(32));
    }

    public static void handle(GoblinActionPacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null) {
                net.minecraft.world.entity.Entity entity = player.level().getEntity(msg.entityId);
                if (entity instanceof GoblinEntity goblin) {
                    switch (msg.action) {
                        case "return" -> goblin.returnStolenItems(player);
                        case "scene" -> player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal("§eThe goblin scurries off before you can act..."), false);
                    }
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
