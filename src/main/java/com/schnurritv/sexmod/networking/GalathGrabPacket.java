package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.entity.galath.GalathEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.api.distmarker.Dist;

/**
 * Two-way packet for Galath combat grab mechanic:
 * - Server→Client: signals grab start/stop to open/close escape screen
 * - Client→Server: sends escape progress taps (player pressing A/D)
 */
public class GalathGrabPacket {
    private final int entityId;
    private final boolean grabActive;
    /** Taps submitted from client (only used in client→server direction) */
    private final int taps;

    // Server→Client constructor
    public GalathGrabPacket(int entityId, boolean grabActive) {
        this.entityId = entityId;
        this.grabActive = grabActive;
        this.taps = 0;
    }

    // Client→Server constructor (escape taps)
    public GalathGrabPacket(int entityId, int taps) {
        this.entityId = entityId;
        this.grabActive = false;
        this.taps = taps;
    }

    public static void encode(GalathGrabPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeBoolean(msg.grabActive);
        buf.writeInt(msg.taps);
    }

    public static GalathGrabPacket decode(FriendlyByteBuf buf) {
        int entityId = buf.readInt();
        boolean grabActive = buf.readBoolean();
        int taps = buf.readInt();
        // Reconstruct based on context: if taps > 0, it's from client
        if (taps > 0) {
            return new GalathGrabPacket(entityId, taps);
        }
        return new GalathGrabPacket(entityId, grabActive);
    }

    public static void handle(GalathGrabPacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            // If sender exists (PLAY_TO_SERVER), process escape taps
            ServerPlayer sender = ctx.getSender();
            if (sender != null) {
                // Client→Server: Process escape taps
                Entity entity = sender.level().getEntity(msg.entityId);
                if (entity instanceof GalathEntity galath) {
                    for (int i = 0; i < msg.taps; i++) {
                        galath.onEscapeTap();
                    }
                }
            } else {
                // Server→Client: Open or close the grab escape screen
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    Minecraft mc = Minecraft.getInstance();
                    if (mc.level == null) return;
                    Entity entity = mc.level.getEntity(msg.entityId);
                    if (entity instanceof GalathEntity galath) {
                        if (msg.grabActive) {
                            mc.setScreen(
                                new com.schnurritv.sexmod.client.gui.GalathGrabScreen(galath, mc.player));
                        } else {
                            if (mc.screen instanceof com.schnurritv.sexmod.client.gui.GalathGrabScreen) {
                                mc.screen.onClose();
                            }
                        }
                    }
                });
            }
        });
        ctx.setPacketHandled(true);
    }
}
