package com.schnurritv.sexmod.events;

import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.networking.SceneActionPacket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Server-side Forge event handlers for quest system integration.
 * Handles LivingDeathEvent for KILL quest tracking,
 * and server tick for DEFEND quest wave triggering.
 */
@Mod.EventBusSubscriber(modid = Main.MODID)
public class ServerForgeEvents {

    /**
     * Track mob kills for KILL quests.
     * When a mob dies within 16 blocks of any BaseGirlEntity that has an active KILL quest
     * and the killer is that girl's owner, add progress.
     * Also tracks DEFEND quest mob kills near the defending girl.
     */
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity killed = event.getEntity();
        if (killed == null || killed.level().isClientSide) return;

        // Only care about mobs killed by a player
        if (!(event.getSource().getEntity() instanceof Player killer)) return;

        // Check if any nearby BaseGirlEntity has an active KILL quest for this mob
        for (BaseGirlEntity girl : killed.level().getEntitiesOfClass(BaseGirlEntity.class,
                killed.getBoundingBox().inflate(16.0D))) {

            if (!girl.getQuestManager().hasActiveQuest()) continue;

            com.schnurritv.sexmod.relationship.QuestManager.Quest q = girl.getQuestManager().getActiveQuest();
            if (q == null) continue;

            // Verify the killer is the girl's owner
            String ownerUUID = girl.getAffectionData().getOwnerUUID();
            if (ownerUUID.isEmpty() || !ownerUUID.equals(killer.getUUID().toString())) continue;

            // Verify the killer is within range (16 blocks)
            if (killer.distanceToSqr(girl) > 256.0) continue;

            if (q.type() == com.schnurritv.sexmod.relationship.QuestManager.QuestType.KILL) {
                // Found a match — advance KILL quest
                girl.tryAdvanceKillQuest(killed, killer);
                return;
            } else if (q.type() == com.schnurritv.sexmod.relationship.QuestManager.QuestType.DEFEND) {
                // Track DEFEND mob kills
                boolean waveComplete = girl.getQuestManager().onDefendKill();
                if (waveComplete) {
                    killer.displayClientMessage(net.minecraft.network.chat.Component.literal(
                        "<" + girl.getGirlName() + "> Wave " + girl.getQuestManager().getDefendWave() +
                        "/" + q.defendWaveCount() + " cleared! Get ready for the next one!"), false);
                }
            }
        }
    }

    /**
     * Periodic server tick: check DEFEND quests and spawn next wave automatically.
     * Runs every 40 ticks (2 seconds) to avoid excessive scanning.
     */
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        tickCounter++;
        if (tickCounter % 40 != 0) return; // every 2 seconds

        // Iterate over all loaded levels via MinecraftForge.EVENT_BUS
        // Use the level list from any ServerLevel's server reference
        net.minecraft.server.MinecraftServer server = net.minecraftforge.server.ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;

        for (net.minecraft.server.level.ServerLevel level : server.getAllLevels()) {
            SceneActionPacket.tickDefendQuests(level);
        }
    }
}
