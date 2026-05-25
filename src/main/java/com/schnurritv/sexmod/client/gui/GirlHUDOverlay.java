package com.schnurritv.sexmod.client.gui;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.schnurritv.sexmod.Main;

/**
 * HUD overlay showing nearby followed girl's affection + quest status.
 * Hooks into CustomizeGuiOverlayEvent.Chat (renders after chat overlay).
 */
@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GirlHUDOverlay {

    @SubscribeEvent
    public static void onRenderChat(CustomizeGuiOverlayEvent.Chat event) {
        var mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        BaseGirlEntity nearest = null;
        double nearestDist = 256;
        String playerUUID = mc.player.getUUID().toString();

        for (var entity : mc.level.entitiesForRendering()) {
            if (entity instanceof BaseGirlEntity girl) {
                String owner = girl.getEntityData().get(SexEntity.MASTER_UUID);
                if (playerUUID.equals(owner)) {
                    double dist = entity.distanceToSqr(mc.player);
                    if (dist < nearestDist) {
                        nearestDist = dist;
                        nearest = girl;
                    }
                }
            }
        }

        if (nearest == null) return;

        GuiGraphics g = event.getGuiGraphics();
        int screenW = mc.getWindow().getGuiScaledWidth();
        int screenH = mc.getWindow().getGuiScaledHeight();
        int x = 10;
        int y = screenH - 55;
        int w = 160;
        int h = 40;

        // Background
        g.fill(x, y, x + w, y + h, 0xC0221122);
        g.fill(x, y, x + w, y + 1, 0xFF664466);

        // Girl name
        String name = nearest.getGirlName().substring(0, 1).toUpperCase() + nearest.getGirlName().substring(1);
        g.drawString(mc.font, name, x + 6, y + 4, 0xFFEEAAEE, true);

        // Affection bar
        int aff = nearest.getAffection();
        int affMax = com.schnurritv.sexmod.SexModConfig.AFFECTION_MAX.get();
        int barW = w - 20;
        int fillW = Math.max(1, (int)((float)aff / affMax * barW));
        g.fill(x + 8, y + 16, x + 8 + barW, y + 22, 0xFF442233);
        g.fill(x + 8, y + 16, x + 8 + fillW, y + 22, 0xFFEE4466);

        // Numbers
        g.drawString(mc.font, "❤ " + aff + "/" + affMax, x + 10, y + 24, 0xFFFF8888, true);

        // Quest progress if active
        var qm = nearest.getQuestManager();
        if (qm.hasActiveQuest()) {
            var q = qm.getActiveQuest();
            if (q != null) {
                String qStr = "📋 " + qm.getProgress() + "/" + q.targetCount();
                g.drawString(mc.font, qStr, x + 6, y + 32, 0xFF88AAFF, true);
            }
        }
    }
}
