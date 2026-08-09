package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.SexModConfig;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.relationship.AffectionData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
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
        // Bounds-guard the enum (audit L3): malformed ordinal must not crash the client
        Action action;
        int ordinal = buf.readInt();
        Action[] values = Action.values();
        if (ordinal < 0 || ordinal >= values.length) {
            action = Action.ADD_AFFECTION;
        } else {
            action = values[ordinal];
        }
        return new NpcEditPacket(buf.readInt(), action, buf.readUtf(64));
    }

    public static void handle(NpcEditPacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;

            // M1 (audit): must actually hold the Girl Wand to edit
            ItemStack held = player.getMainHandItem();
            if (!(held.getItem() instanceof com.schnurritv.sexmod.item.GirlWandItem)
                    && !(player.getOffhandItem().getItem() instanceof com.schnurritv.sexmod.item.GirlWandItem)) {
                return;
            }

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
                    // Simple per-player cooldown to prevent spam (audit M1)
                    long now = player.level().getGameTime();
                    if (now - lastAffectionTick.getOrDefault(player.getUUID(), -100L) < 20L) {
                        player.displayClientMessage(Component.literal("§7Too fast..."), true);
                        return;
                    }
                    lastAffectionTick.put(player.getUUID(), now);
                    data.addAffection(10, SexModConfig.AFFECTION_MAX.get());
                    girl.getEntityData().set(BaseGirlEntity.AFFECTION_VALUE, data.getAffection());
                    if (!player.isCreative()) {
                        held.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);
                    }
                    player.displayClientMessage(Component.literal(
                            "§d" + girl.getGirlName() + " +10 affection (" + girl.getAffection() + ")"), true);
                }
                case SET_CLOTHING -> {
                    int current = girl.getEntityData().get(SexEntity.CLOTHING_STATE);
                    girl.getEntityData().set(SexEntity.CLOTHING_STATE, current == 0 ? 1 : 0);
                    if (!player.isCreative()) {
                        held.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);
                    }
                    player.displayClientMessage(Component.literal(
                            "§d" + girl.getGirlName() + " outfit toggled"), true);
                }
                case RENAME -> {
                    // Strip legacy formatting codes (audit M4)
                    String name = msg.stringValue.replace("§", "").trim();
                    if (name.isEmpty() || name.length() > 32) {
                        player.displayClientMessage(Component.literal("§cName must be 1-32 chars"), true);
                        return;
                    }
                    girl.setCustomName(Component.literal(name));
                    girl.setCustomNameVisible(true);
                    if (!player.isCreative()) {
                        held.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);
                    }
                    player.displayClientMessage(Component.literal(
                            "§dRenamed to " + name), true);
                }
                case GO_HOME -> {
                    var pos = girl.getHousePos();
                    if (pos != null) {
                        // Safe landing spot (audit M2)
                        net.minecraft.core.BlockPos.MutableBlockPos landing = pos.mutable();
                        while (landing.getY() < player.level().getMaxBuildHeight() - 1
                                && !(player.level().getBlockState(landing).isAir()
                                        && player.level().getBlockState(landing.above()).isAir())) {
                            landing.move(0, 1, 0);
                        }
                        girl.teleportTo(landing.getX() + 0.5, landing.getY(), landing.getZ() + 0.5);
                        if (!player.isCreative()) {
                            held.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);
                        }
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

    /** Per-player last affection-edit game time (audit M1 anti-spam) */
    private static final java.util.Map<java.util.UUID, Long> lastAffectionTick = new java.util.HashMap<>();
}
