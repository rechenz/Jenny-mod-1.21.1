package com.schnurritv.sexmod.networking;

import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.relationship.QuestManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class SceneActionPacket {
    private final int entityId;
    private final String action;

    public SceneActionPacket(int entityId, String action) {
        this.entityId = entityId;
        this.action = action;
    }

    public static void encode(SceneActionPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.action);
    }

    public static SceneActionPacket decode(FriendlyByteBuf buf) {
        return new SceneActionPacket(buf.readInt(), buf.readUtf(32));
    }

    public static void handle(SceneActionPacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null) {
                net.minecraft.world.entity.Entity entity = player.level().getEntity(msg.entityId);
                if (entity instanceof com.schnurritv.sexmod.entity.BaseGirlEntity girl) {
                    switch (msg.action) {
                        case "Thrust" -> {
                            SexModAnimation current = girl.getSexModAnimation();
                            if (current == SexModAnimation.MISSIONARY_SLOW) {
                                girl.setSexModAnimation(SexModAnimation.MISSIONARY_FAST);
                            } else if (current == SexModAnimation.MISSIONARY_FAST) {
                                girl.setSexModAnimation(SexModAnimation.MISSIONARY_SLOW);
                            } else if (current == SexModAnimation.BLOWJOBSUCK) {
                                girl.setSexModAnimation(SexModAnimation.BLOWJOBTHRUST);
                            } else if (current == SexModAnimation.BLOWJOBTHRUST) {
                                girl.setSexModAnimation(SexModAnimation.BLOWJOBSUCK);
                            } else if (current == SexModAnimation.DOGGYWAIT || current == SexModAnimation.DOGGYSTART) {
                                girl.setSexModAnimation(SexModAnimation.DOGGYSLOW);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, "null");
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.DOGGYSLOW) {
                                girl.setSexModAnimation(SexModAnimation.DOGGYFAST);
                            } else if (current == SexModAnimation.DOGGYFAST) {
                                girl.setSexModAnimation(SexModAnimation.DOGGYSLOW);
                            } else if (current == SexModAnimation.PAIZURI_SLOW) {
                                girl.setSexModAnimation(SexModAnimation.PAIZURI_FAST);
                            } else if (current == SexModAnimation.PAIZURI_FAST) {
                                girl.setSexModAnimation(SexModAnimation.PAIZURI_SLOW);
                            }
                        }
                        case "Missionary" -> com.schnurritv.sexmod.scene.SceneManager.startMissionary(girl, player);
                        case "Doggy" -> com.schnurritv.sexmod.scene.SceneManager.startDoggy(girl, player);
                        case "Blowjob" -> com.schnurritv.sexmod.scene.SceneManager.startBlowjob(girl, player);
                        case "Boobjob" -> com.schnurritv.sexmod.scene.SceneManager.startBoobjob(girl, player);
                        case "Stop" -> com.schnurritv.sexmod.scene.SceneManager.stopScene(girl);
                        case "QuestStart" -> {
                            QuestManager.Quest q = girl.getQuestManager().getAvailableQuest(girl.getGirlName());
                            if (q != null) {
                                girl.getQuestManager().startQuest(q);
                                player.displayClientMessage(Component.literal(
                                    "<" + girl.getGirlName() + "> " + q.description()), false);
                                player.displayClientMessage(Component.literal(
                                    "§6Quest started! Deliver " + q.targetCount() + "x " + 
                                    net.minecraft.core.registries.BuiltInRegistries.ITEM.get(
                                        net.minecraft.resources.ResourceLocation.parse(q.targetItem())
                                    ).getDescription().getString()), false);
                            } else {
                                player.displayClientMessage(Component.literal(
                                    "<" + girl.getGirlName() + "> I don't have anything for you right now."), false);
                            }
                        }
                        case "QuestTurnin" -> {
                            QuestManager.Quest activeQ = girl.getQuestManager().getActiveQuest();
                            if (activeQ != null) {
                                player.displayClientMessage(Component.literal(
                                    "<" + girl.getGirlName() + "> Hand me the items directly! I need " +
                                    (activeQ.targetCount() - girl.getQuestManager().getProgress()) + " more."), false);
                            } else {
                                player.displayClientMessage(Component.literal(
                                    "<" + girl.getGirlName() + "> There's no active quest. Ask me for one!"), false);
                            }
                        }
                        case "ReturnItems" -> {
                            if (girl instanceof com.schnurritv.sexmod.entity.goblin.GoblinEntity goblin) {
                                goblin.returnStolenItems(player);
                            }
                        }
                    }
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
