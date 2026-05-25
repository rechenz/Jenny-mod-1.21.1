package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class NetworkHandler {

    public static <MSG> void sendToServer(MSG packet) {
        INSTANCE.send(packet, PacketDistributor.SERVER.noArg());
    }
    private static final int VERSION = 2;
    public static final SimpleChannel INSTANCE = ChannelBuilder.named(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(Main.MODID, "main"))
            .networkProtocolVersion(VERSION)
            .simpleChannel();

    public static void register() {
        INSTANCE.messageBuilder(AnimationSyncPacket.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(AnimationSyncPacket::encode)
                .decoder(AnimationSyncPacket::decode)
                .consumerMainThread(AnimationSyncPacket::handle)
                .add();

        INSTANCE.messageBuilder(MovementStatePacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(MovementStatePacket::encode)
                .decoder(MovementStatePacket::decode)
                .consumerMainThread(MovementStatePacket::handle)
                .add();

        INSTANCE.messageBuilder(SceneActionPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(SceneActionPacket::encode)
                .decoder(SceneActionPacket::decode)
                .consumerMainThread(SceneActionPacket::handle)
                .add();

        INSTANCE.messageBuilder(GoblinActionPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(GoblinActionPacket::encode)
                .decoder(GoblinActionPacket::decode)
                .consumerMainThread(GoblinActionPacket::handle)
                .add();

        INSTANCE.messageBuilder(GalathGrabPacket.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(GalathGrabPacket::encode)
                .decoder(GalathGrabPacket::decode)
                .consumerMainThread(GalathGrabPacket::handle)
                .add();

        INSTANCE.messageBuilder(GalathGrabPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(GalathGrabPacket::encode)
                .decoder(GalathGrabPacket::decode)
                .consumerMainThread(GalathGrabPacket::handle)
                .add();

        INSTANCE.messageBuilder(ClothingTogglePacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ClothingTogglePacket::encode)
                .decoder(ClothingTogglePacket::decode)
                .consumerMainThread(ClothingTogglePacket::handle)
                .add();
    }

    public static void broadcastAnimationSync(SexEntity entity, SexModAnimation animation) {
        INSTANCE.send(new AnimationSyncPacket(entity.getId(), animation.name()), PacketDistributor.TRACKING_ENTITY.with(entity));
    }

    public static void sendMovementStateUpdate(int entityId, String state) {
        INSTANCE.send(new MovementStatePacket(entityId, state), PacketDistributor.SERVER.noArg());
    }

    public static void sendSceneAction(int entityId, String action) {
        INSTANCE.send(new SceneActionPacket(entityId, action), PacketDistributor.SERVER.noArg());
    }

    public static void sendGoblinAction(int entityId, String action) {
        INSTANCE.send(new GoblinActionPacket(entityId, action), PacketDistributor.SERVER.noArg());
    }

    /** Send a packet to a specific player (not tracking). */
    public static void sendToPlayer(Object packet, ServerPlayer player) {
        INSTANCE.send(packet, PacketDistributor.PLAYER.with(player));
    }
}
