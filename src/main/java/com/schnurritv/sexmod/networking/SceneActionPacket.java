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
                    // Handle Allie-specific actions
                    if (msg.action.startsWith("Allie_") && entity instanceof com.schnurritv.sexmod.entity.allie.AllieEntity allie) {
                        String actionKey = msg.action.substring(6); // strip "Allie_"
                        allie.handleAllieAction(actionKey, player);
                        ctx.setPacketHandled(true);
                        return;
                    }
                    switch (msg.action) {
                        case "Thrust" -> {
                            SexModAnimation current = girl.getSexModAnimation();
                            // Toggle between SLOW and FAST phases for differential.
                            // FAST→CUM is handled automatically by handleAnimationSequencing.
                            if (current == SexModAnimation.MISSIONARY_SLOW) {
                                girl.setSexModAnimation(SexModAnimation.MISSIONARY_FAST);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.MISSIONARY_CUM.name());
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.MISSIONARY_FAST) {
                                girl.setSexModAnimation(SexModAnimation.MISSIONARY_SLOW);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, "null");
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.BLOWJOBSUCK) {
                                girl.setSexModAnimation(SexModAnimation.BLOWJOBTHRUST);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.BLOWJOBCUM.name());
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.BLOWJOBTHRUST) {
                                girl.setSexModAnimation(SexModAnimation.BLOWJOBSUCK);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, "null");
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.DOGGYWAIT || current == SexModAnimation.DOGGYSTART) {
                                girl.setSexModAnimation(SexModAnimation.DOGGYSLOW);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.DOGGYFAST.name());
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.DOGGYSLOW) {
                                girl.setSexModAnimation(SexModAnimation.DOGGYFAST);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.DOGGYCUM.name());
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.DOGGYFAST) {
                                girl.setSexModAnimation(SexModAnimation.DOGGYSLOW);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, "null");
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.PAIZURI_SLOW) {
                                girl.setSexModAnimation(SexModAnimation.PAIZURI_FAST);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.PAIZURI_CUM.name());
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            } else if (current == SexModAnimation.PAIZURI_FAST) {
                                girl.setSexModAnimation(SexModAnimation.PAIZURI_SLOW);
                                girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, "null");
                                girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
                            }
                        }
                        case "Missionary" -> com.schnurritv.sexmod.scene.SceneManager.startMissionary(girl, player);
                        case "Doggy" -> com.schnurritv.sexmod.scene.SceneManager.startDoggy(girl, player);
                        case "Blowjob" -> com.schnurritv.sexmod.scene.SceneManager.startBlowjob(girl, player);
                        case "Boobjob" -> com.schnurritv.sexmod.scene.SceneManager.startBoobjob(girl, player);
                        case "Stop" -> {
                            if (girl instanceof com.schnurritv.sexmod.entity.allie.AllieEntity allie) {
                                allie.returnToSummonWait();
                            } else {
                                com.schnurritv.sexmod.scene.SceneManager.stopScene(girl);
                            }
                        }
                        case "QuestStart" -> {
                            QuestManager.Quest q = girl.getQuestManager().getAvailableQuest(girl.getGirlName());
                            if (q != null) {
                                girl.getQuestManager().startQuest(q);
                                player.displayClientMessage(Component.literal(
                                    "<" + girl.getGirlName() + "> " + q.description()), false);
                                String questTypeLabel = switch (q.type()) {
                                    case FETCH -> "§6Quest started! Bring " + q.targetCount() + "x " +
                                        net.minecraft.core.registries.BuiltInRegistries.ITEM.get(
                                            net.minecraft.resources.ResourceLocation.parse(q.targetItem())
                                        ).getDescription().getString();
                                    case KILL -> "§cQuest started! Kill " + q.targetCount() + "x " + q.targetMob();
                                    case ESCORT -> "§bQuest started! Escort " + q.girlName() + " to the " + friendlyDestName(q.escortDestination()) + "!";
                                    case DEFEND -> "§5Quest started! Survive " + q.defendWaveCount() + " waves of mobs!";
                                };
                                player.displayClientMessage(Component.literal(questTypeLabel), false);
                            } else {
                                player.displayClientMessage(Component.literal(
                                    "<" + girl.getGirlName() + "> I don't have anything for you right now."), false);
                            }
                        }
                        case "QuestTurnin" -> {
                            QuestManager activeQm = girl.getQuestManager();
                            QuestManager.Quest activeQ = activeQm.getActiveQuest();
                            if (activeQ != null) {
                                String turninMsg = switch (activeQ.type()) {
                                    case FETCH -> "Hand me the items directly! I need " +
                                        (activeQ.targetCount() - activeQm.getProgress()) + " more.";
                                    case KILL -> "Keep killing! You've taken out " +
                                        activeQm.getProgress() + "/" + activeQ.targetCount() + " so far!";
                                    case ESCORT -> "Stay close and guide me to the destination!";
                                    case DEFEND -> "Stay alert! We're on wave " + (activeQm.getDefendWave() + 1) +
                                        "/" + activeQ.defendWaveCount() + "!";
                                };
                                player.displayClientMessage(Component.literal(
                                    "<" + girl.getGirlName() + "> " + turninMsg), false);
                            } else {
                                player.displayClientMessage(Component.literal(
                                    "<" + girl.getGirlName() + "> There's no active quest. Ask me for one!"), false);
                            }
                        }
                        case "QuestAbandon" -> {
                            girl.getQuestManager().resetQuest();
                            player.displayClientMessage(Component.literal(
                                "<" + girl.getGirlName() + "> Quest abandoned. Come ask me again if you change your mind!"), false);
                            player.displayClientMessage(Component.literal(
                                "§7Quest reset."), false);
                        }
                        case "ReturnItems" -> {
                            if (girl instanceof com.schnurritv.sexmod.entity.goblin.GoblinEntity goblin) {
                                goblin.returnStolenItems(player);
                            }
                        }
                        case "HeadPat" -> {
                            if (girl instanceof com.schnurritv.sexmod.entity.cat.CatEntity cat) {
                                cat.startHeadPat(player);
                            }
                        }
                    }
                }
            }
        });
        ctx.setPacketHandled(true);
    }

    /**
     * Periodic tick for DEFEND quests: auto-spawn next wave when current wave is cleared.
     * Called from ServerForgeEvents (or a level tick hook).
     */
    public static void tickDefendQuests(net.minecraft.server.level.ServerLevel level) {
        for (var girl : level.getEntities().getAll()) {
            if (!(girl instanceof com.schnurritv.sexmod.entity.BaseGirlEntity bg)) continue;
            com.schnurritv.sexmod.relationship.QuestManager qm = bg.getQuestManager();
            if (!qm.hasActiveQuest()) continue;
            com.schnurritv.sexmod.relationship.QuestManager.Quest q = qm.getActiveQuest();
            if (q == null || q.type() != com.schnurritv.sexmod.relationship.QuestManager.QuestType.DEFEND) continue;

            int defenseWave = qm.getDefendWave();
            if (defenseWave == 0) continue; // not started yet
            if (defenseWave > q.defendWaveCount()) continue; // completed

            // If mobs remaining is 0, spawn next wave
            if (qm.getDefendMobsRemaining() <= 0) {
                String mobType = qm.startDefendWave();
                if (mobType == null) {
                    // All waves completed - quest was auto-completed
                    String ownerUUID = bg.getAffectionData().getOwnerUUID();
                    if (!ownerUUID.isEmpty()) {
                        net.minecraft.world.entity.player.Player owner = level.getPlayerByUUID(java.util.UUID.fromString(ownerUUID));
                        if (owner != null) {
                            owner.displayClientMessage(Component.literal(
                                "<" + bg.getGirlName() + "> " + com.schnurritv.sexmod.relationship.DialogueDB.getRandom("quest_complete")), false);
                            owner.displayClientMessage(Component.literal(
                                "\u00a7a\u2611 DEFEND Quest Complete! \u2764 +" + q.rewardAffection()), true);
                        }
                    }
                    int reward = q.rewardAffection();
                    bg.getAffectionData().addAffection(reward, com.schnurritv.sexmod.SexModConfig.AFFECTION_MAX.get());
                    return;
                }
                // Spawn mobs for this wave
                spawnDefendMobs(bg, mobType, qm.getDefendMobsRemaining());
                // Notify owner
                String ownerUUID = bg.getAffectionData().getOwnerUUID();
                if (!ownerUUID.isEmpty()) {
                    net.minecraft.world.entity.player.Player owner = level.getPlayerByUUID(java.util.UUID.fromString(ownerUUID));
                    if (owner != null) {
                        owner.displayClientMessage(Component.literal(
                            "<" + bg.getGirlName() + "> Wave " + defenseWave + "/" + q.defendWaveCount() + " is here! Protect me!"), false);
                    }
                }
            }
        }
    }

    private static String friendlyDestName(String dest) {
        return switch (dest) {
            case "BEACH" -> "a Beach/Ocean";
            case "DEEP_CAVE" -> "Deep Cave (Y<30)";
            case "FLOWER_FOREST" -> "Flower Forest";
            default -> dest;
        };
    }

    private static void spawnDefendMobs(net.minecraft.world.entity.Entity girl, String mobType, int count) {
        net.minecraft.server.level.ServerLevel level = (net.minecraft.server.level.ServerLevel) girl.level();
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < count; i++) {
            net.minecraft.world.entity.EntityType<?> type = switch (mobType) {
                case "ZOMBIE" -> net.minecraft.world.entity.EntityType.ZOMBIE;
                case "SKELETON" -> net.minecraft.world.entity.EntityType.SKELETON;
                case "SPIDER" -> net.minecraft.world.entity.EntityType.SPIDER;
                default -> net.minecraft.world.entity.EntityType.ZOMBIE;
            };
            double angle = rand.nextDouble() * Math.PI * 2;
            double dist = 3.0 + rand.nextDouble() * 4.0; // 3-7 blocks away
            double x = girl.getX() + Math.cos(angle) * dist;
            double z = girl.getZ() + Math.sin(angle) * dist;
            double y = girl.getY();
            // Find a valid spawn Y (walk up)
            var blockPos = net.minecraft.core.BlockPos.containing(x, y, z);
            while (!level.getBlockState(blockPos).isAir() && y < girl.getY() + 5) {
                y++;
                blockPos = net.minecraft.core.BlockPos.containing(x, y, z);
            }
            net.minecraft.world.entity.Mob mob = (net.minecraft.world.entity.Mob) type.create(level);
            if (mob != null) {
                mob.setPos(x, y, z);
                mob.targetSelector.addGoal(1,
                    new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<>(mob,
                        net.minecraft.world.entity.player.Player.class, true));
                level.addFreshEntity(mob);
            }
        }
    }
}
