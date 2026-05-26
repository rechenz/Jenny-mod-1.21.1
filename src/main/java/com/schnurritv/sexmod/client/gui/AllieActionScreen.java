package com.schnurritv.sexmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.schnurritv.sexmod.entity.allie.AllieEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.networking.NetworkHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

/**
 * Simplified action screen for Allie.
 * Shows the 3 wish/action options as defined in the old ev.java.
 */
public class AllieActionScreen extends Screen {
    private final AllieEntity girl;
    private int hoveredIndex = -1;
    private final int panelW = 200;
    private final int panelH = 160;
    private int panelX, panelY;

    private static final int COLOR_BG       = 0xE8221122;
    private static final int COLOR_PANEL    = 0xD0331133;
    private static final int COLOR_BORDER   = 0xFF664466;
    private static final int COLOR_TEXT     = 0xFFDDCCDD;
    private static final int COLOR_HOVER    = 0xFF553355;
    private static final int COLOR_ACCENT   = 0xFFCC6688;

    private record Action(String label, String actionKey) {}

    private final List<Action> actions = List.of(
        new Action("💰 Make me Rich!", "makemerichallie"),
        new Action("💋 Deep Throat", "deepthroat"),
        new Action("🔥 Reverse Cowgirl", "reverse_cowgirl")
    );

    public AllieActionScreen(AllieEntity girl) {
        super(Component.literal("Allie Actions"));
        this.girl = girl;
    }

    @Override
    protected void init() {
        this.panelX = (this.width - panelW) / 2;
        this.panelY = (this.height - panelH) / 2;
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        // Dim background
        g.fill(0, 0, this.width, this.height, 0x88000000);
        super.render(g, mouseX, mouseY, partialTick);
        RenderSystem.enableBlend();

        // Panel
        g.fill(panelX - 2, panelY - 2, panelX + panelW + 2, panelY + panelH + 2, COLOR_BORDER);
        g.fill(panelX, panelY, panelX + panelW, panelY + panelH, COLOR_BG);

        int y = panelY + 8;

        // Header
        g.drawString(this.font, Component.literal("§lAllie · What'll it be?"),
                     panelX + 10, y, 0xFFEEAAEE, true);
        y += 16;

        // Current state indicator
        SexModAnimation currentAnim = girl.getSexModAnimation();
        String stateText = switch (currentAnim) {
            case SUMMON_WAIT -> "§7I'm ready for anything~";
            default -> "§7...";
        };
        g.drawString(this.font, stateText, panelX + 10, y, 0xFF887788, true);
        y += 20;

        // Divider
        g.fill(panelX + 20, y, panelX + panelW - 20, y + 1, COLOR_BORDER);
        y += 8;

        // Action buttons
        hoveredIndex = -1;
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            int ax1 = panelX + 10;
            int ax2 = panelX + panelW - 10;
            int ay1 = y;
            int ay2 = y + 24;

            boolean hovered = mouseX >= ax1 && mouseX <= ax2 && mouseY >= ay1 && mouseY <= ay2;
            if (hovered) hoveredIndex = i;

            int bgColor = hovered ? COLOR_HOVER : COLOR_PANEL;
            g.fill(ax1, ay1, ax2, ay2, bgColor);

            // Accent bar on the left
            g.fill(ax1, ay1, ax1 + 3, ay2, COLOR_ACCENT);

            g.drawString(this.font, action.label(), ax1 + 10, ay1 + 7, COLOR_TEXT, true);
            y += 26;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (hoveredIndex >= 0 && hoveredIndex < actions.size()) {
            executeAction(actions.get(hoveredIndex));
        }
        this.onClose();
        return true;
    }

    private void executeAction(Action action) {
        // Send the action choice to server via network
        NetworkHandler.sendSceneAction(girl.getId(), "Allie_" + action.actionKey());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
