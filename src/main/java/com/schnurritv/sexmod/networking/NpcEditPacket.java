package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.SexModConfig;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.relationship.AffectionData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.network.CustomPayloadEvent;

/**
 * Girl Wand (NPC editor) actions — PLAY_TO_SERVER, server-authoritative.
 *
 * Actions: ADD_AFFECTION (+10), SET_CLOTHING (force toggle, bypassing the
 * affection threshold — editor privilege), RENAME (set custom name), GO_HOME
 * (teleport the girl to her house).
 *
 * Server always validates that the sender owns the girl (or the girl is
 * unowned) before applying anything (audit: no cross-player editing).
 */
public class NpcEditPacket {

    public enum Action { ADD_AFFECTION, SET_CLOTHING, RENAME, GO_HOME }

    private final int entityId;
    private final Action action;
    private final String stringValue;

    public NpcEditPacket(int entityId, Action action, String stringValue) {
        this.entityId = entityId;
        this.action = action;
        this.stringValue = stringValue == null ? "" : stringValue;
    }

    public static void encode(NpcEditPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeEnum(msg.action);
        buf.writeUtf(msg.stringValue, 64);
    }

    public static NpcEditPacket decode(FriendlyByteBuf buf) {
        return new NpcEditPacket(buf.readInt(), buf.readEnum(Action.class), buf.readUtf(64));
    }

    public static void handle(NpcEditPacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;
            Entity entity = player.level().getEntity(msg.entityId);
            if (!(entity instanceof BaseGirlEntity girl)) return;

            // Owner check: only the girl's owner (or unowned girls) can be edited
            AffectionData data = girl.getAffectionData();
            String owner = data.getOwnerUUID();
            if (!owner.isEmpty() && !owner.equals(player.getStringUUID())) {
                player.displayClientMessage(Component.literal("§cYou don't own this girl!"), true);
                return;
            }

            switch (msg.action) {
                case ADD_AFFECTION -> {
                    data.addAffection(10, SexModConfig.AFFECTION_MAX.get());
                    girl.getEntityData().set(BaseGirlEntity.AFFECTION_VALUE, data.getAffection());
                    player.displayClientMessage(Component.literal(
                            "§d" + girl.getGirlName() + " +10 affection (" + girl.getAffection() + ")"), true);
                }
                case SET_CLOTHING -> {
                    int current = girl.getEntityData().get(SexEntity.CLOTHING_STATE);
                    girl.getEntityData().set(SexEntity.CLOTHING_STATE, current == 0 ? 1 : 0);
                    player.displayClientMessage(Component.literal(
                            "§d" + girl.getGirlName() + " outfit toggled"), true);
                }
                case RENAME -> {
                    String name = msg.stringValue.trim();
                    if (name.isEmpty() || name.length() > 32) {
                        player.displayClientMessage(Component.literal("§cName must be 1-32 chars"), true);
                        return;
                    }
                    girl.setCustomName(Component.literal(name));
                    girl.setCustomNameVisible(true);
                    player.displayClientMessage(Component.literal(
                            "§dRenamed to " + name), true);
                }
                case GO_HOME -> {
                    var pos = girl.getHousePos();
                    if (pos != null) {
                        girl.teleportTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                        player.displayClientMessage(Component.literal(
                                "§d" + girl.getGirlName() + " sent home"), true);
                    } else {
                        player.displayClientMessage(Component.literal(
                                "§7" + girl.getGirlName() + " has no home yet."), true);
                    }
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
