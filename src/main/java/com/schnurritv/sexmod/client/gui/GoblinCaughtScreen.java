package com.schnurritv.sexmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.schnurritv.sexmod.entity.goblin.GoblinEntity;
import com.schnurritv.sexmod.networking.NetworkHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * Screen shown when a player catches the goblin after being robbed.
 * Options: "Take back my stuff" or "Watch special scene"
 */
public class GoblinCaughtScreen extends Screen {
    private final GoblinEntity goblin;
    private final Player player;
    private int hoveredIndex = -1;

    private static final int COLOR_BG     = 0xE8221122;
    private static final int COLOR_PANEL  = 0xD0331133;
    private static final int COLOR_BORDER = 0xFF664466;
    private static final int COLOR_TEXT   = 0xFFDDCCDD;
    private static final int COLOR_HOVER  = 0xFF553355;
    private static final int COLOR_ACCENT = 0xFFCC6688;

    private final String[] options = {
        "📦 Take back my stuff",
        "🎬 Watch special scene",
        "❌ Never mind"
    };

    public GoblinCaughtScreen(GoblinEntity goblin, Player player) {
        super(Component.literal("Caught the Goblin!"));
        this.goblin = goblin;
        this.player = player;
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        // Dim background
        g.fill(0, 0, this.width, this.height, 0x88000000);
        super.render(g, mouseX, mouseY, partialTick);

        RenderSystem.enableBlend();

        int panelW = 260;
        int panelH = 180;
        int panelX = (this.width - panelW) / 2;
        int panelY = (this.height - panelH) / 2;

        // Panel
        g.fill(panelX - 2, panelY - 2, panelX + panelW + 2, panelY + panelH + 2, COLOR_BORDER);
        g.fill(panelX, panelY, panelX + panelW, panelY + panelH, COLOR_BG);

        // Title
        g.drawString(this.font, Component.literal("§6Caught the Goblin!").withStyle(s -> s.withBold(true)),
                     panelX + 10, panelY + 10, COLOR_ACCENT, true);

        // Subtitle
        g.drawString(this.font, Component.literal("What do you want to do?"),
                     panelX + 10, panelY + 28, COLOR_TEXT, true);

        // Divider
        g.fill(panelX + 10, panelY + 38, panelX + panelW - 10, panelY + 39, COLOR_BORDER);

        hoveredIndex = -1;
        int optionStartY = panelY + 50;
        int optionH = 30;

        for (int i = 0; i < options.length; i++) {
            int ay = optionStartY + i * (optionH + 4);
            int ax1 = panelX + 10;
            int ax2 = panelX + panelW - 10;

            boolean hovered = mouseX >= ax1 && mouseX <= ax2 && mouseY >= ay && mouseY <= ay + optionH;
            if (hovered) hoveredIndex = i;

            int bgColor = hovered ? COLOR_HOVER : COLOR_PANEL;
            g.fill(ax1, ay, ax2, ay + optionH, bgColor);

            int textColor = hovered ? 0xFFEEEEFF : COLOR_TEXT;
            g.drawString(this.font, options[i], ax1 + 10, ay + (optionH - 9) / 2, textColor, true);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (hoveredIndex >= 0) {
            switch (hoveredIndex) {
                case 0: // Take back my stuff
                    NetworkHandler.sendSceneAction(goblin.getId(), "ReturnItems");
                    break;
                case 1: // Watch special scene
                    NetworkHandler.sendGoblinAction(goblin.getId(), "scene");
                    break;
                case 2: // Never mind
                    break;
            }
            this.onClose();
        }
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
