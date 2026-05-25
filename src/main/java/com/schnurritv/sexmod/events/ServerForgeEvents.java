package com.schnurritv.sexmod.events;

import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Server-side Forge event handlers for quest system integration.
 * Handles LivingDeathEvent for KILL quest tracking.
 */
@Mod.EventBusSubscriber(modid = Main.MODID)
public class ServerForgeEvents {

    /**
     * Track mob kills for KILL quests.
     * When a mob dies within 16 blocks of any BaseGirlEntity that has an active KILL quest
     * and the killer is that girl's owner, add progress.
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
            if (q == null || q.type() != com.schnurritv.sexmod.relationship.QuestManager.QuestType.KILL) continue;

            // Verify the killer is the girl's owner
            String ownerUUID = girl.getAffectionData().getOwnerUUID();
            if (ownerUUID.isEmpty() || !ownerUUID.equals(killer.getUUID().toString())) continue;

            // Verify the killer is within range (16 blocks)
            if (killer.distanceToSqr(girl) > 256.0) continue;

            // Found a match — advance quest
            girl.tryAdvanceKillQuest(killed, killer);
            return; // Only handle one girl at a time
        }
    }
}
