package com.schnurritv.sexmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import com.schnurritv.sexmod.entity.galath.GalathEntity;
import com.schnurritv.sexmod.networking.NetworkHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

/**
 * Escape screen shown when Galath grabs the player in combat.
 * Player must rapidly tap A and D keys to build escape progress.
 * On escape: screen closes, player breaks free.
 * On failure (timeout): Galath climaxes with heavy damage.
 */
public class GalathGrabScreen extends Screen {
    private final GalathEntity galath;
    private final Player player;
    private int escapeProgress = 0;
    private static final int MAX_ESCAPE = 60;
    private int tickCounter = 0;
    private static final int MAX_TICKS = 160; // 8 seconds
    private static final Random RAND = new Random();

    // Key state tracking for rapid tapping
    private boolean wasADown = false;
    private boolean wasDUp = false;
    // 1 = last press was A, 2 = last press was D (for alternating bonus)
    private int lastKey = 0;
    private boolean isKeyDown = false;

    // Feedback
    private String feedbackText = "";
    private int feedbackTimer = 0;

    private static final int COLOR_BG       = 0xE8221122;
    private static final int COLOR_PANEL    = 0xD0442244;
    private static final int COLOR_BORDER   = 0xFFAA4466;
    private static final int COLOR_BAR_BG   = 0xFF331133;
    private static final int COLOR_BAR_FILL = 0xFFFF4466;
    private static final int COLOR_BAR_HIGH = 0xFF44FF66;
    private static final int COLOR_TEXT      = 0xFFDDCCDD;
    private static final int COLOR_DANGER    = 0xFFFF2244;
    private static final int COLOR_WARNING   = 0xFFFFAA44;
    private static final int COLOR_ACCENT    = 0xFFCC6688;

    public GalathGrabScreen(GalathEntity galath, Player player) {
        super(Component.literal("ESCAPE!"));
        this.galath = galath;
        this.player = player;
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        // Full red-tinted overlay
        float intensity = 0.4f + 0.3f * (1.0f - (float) escapeProgress / MAX_ESCAPE);
        int alpha = Math.min(255, (int)(intensity * 255));
        g.fill(0, 0, this.width, this.height, (alpha << 24) | 0x330000);

        super.render(g, mouseX, mouseY, partialTick);
        RenderSystem.enableBlend();

        int panelW = 280;
        int panelH = 140;
        int panelX = (this.width - panelW) / 2;
        int panelY = (this.height - panelH) / 2;

        // Panel
        g.fill(panelX - 2, panelY - 2, panelX + panelW + 2, panelY + panelH + 2, COLOR_BORDER);
        g.fill(panelX, panelY, panelX + panelW, panelY + panelH, COLOR_BG);

        // Title
        String title = "⛓️ GRAPPLED BY GALATH!";
        g.drawString(this.font, Component.literal("§l" + title),
                     panelX + (panelW - this.font.width(title)) / 2,
                     panelY + 10, 0xFFFF6688, true);

        // Instruction
        String instr = "Mash A/D to escape!";
        g.drawString(this.font, Component.literal(instr),
                     panelX + (panelW - this.font.width(instr)) / 2,
                     panelY + 28, COLOR_WARNING, true);

        // Progress bar
        int barX = panelX + 20;
        int barY = panelY + 50;
        int barW = panelW - 40;
        int barH = 18;
        float progress = (float) escapeProgress / MAX_ESCAPE;

        g.fill(barX, barY, barX + barW, barY + barH, COLOR_BAR_BG);
        int fillColor = progress > 0.6f ? COLOR_BAR_HIGH : COLOR_BAR_FILL;
        int fillW = (int)(barW * progress);
        if (fillW > 0) {
            g.fill(barX, barY, barX + fillW, barY + barH, fillColor);
            g.fill(barX, barY, barX + fillW, barY + barH / 2, 0x40FFFFFF); // highlight
        }
        // Border on bar
        g.fill(barX, barY, barX + barW, barY + 1, COLOR_BORDER);
        g.fill(barX, barY + barH - 1, barX + barW, barY + barH, COLOR_BORDER);

        // Progress text
        String progText = escapeProgress + " / " + MAX_ESCAPE;
        g.drawCenteredString(this.font, progText, barX + barW / 2, barY + 4, 0xFFFFFFFF);

        // Danger indicator (time remaining)
        int remaining = (MAX_TICKS - tickCounter) / 20; // seconds
        String timeText = remaining > 0
            ? "⚠ " + remaining + "s before she climaxes!"
            : "☠ TOO LATE!";
        int timeColor = remaining > 3 ? COLOR_TEXT : COLOR_DANGER;
        g.drawCenteredString(this.font, timeText, barX + barW / 2, barY + barH + 8, timeColor);

        // Feedback text
        if (feedbackTimer > 0) {
            g.drawCenteredString(this.font, feedbackText,
                                 panelX + panelW / 2, panelY + panelH - 20, COLOR_ACCENT);
        }
    }

    @Override
    public void tick() {
        super.tick();
        tickCounter++;

        // Check for key taps (A and D)
        long window = net.minecraft.client.Minecraft.getInstance().getWindow().getWindow();
        boolean aDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS;
        boolean dDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS;
        boolean anyDown = aDown || dDown;

        if (anyDown && !isKeyDown) {
            // Key was just pressed
            int tappedKey = aDown ? 1 : 2;
            int bonus = 0;

            // Alternating bonus: +1 extra if switching between A and D
            if (lastKey != 0 && tappedKey != lastKey) {
                bonus = 1;
            }

            escapeProgress += 1 + bonus;
            lastKey = tappedKey;
            isKeyDown = true;

            // Feedback
            if (bonus > 0) {
                feedbackText = "⚡ Alternating tap! +2!";
            } else {
                feedbackText = aDown ? "← A" : "D →";
            }
            feedbackTimer = 10;

            // Clamp
            if (escapeProgress > MAX_ESCAPE) escapeProgress = MAX_ESCAPE;

            // Send tap to server in batches (every 5 taps to reduce packet spam)
            if (escapeProgress % 5 == 0 || escapeProgress >= MAX_ESCAPE) {
                NetworkHandler.INSTANCE.send(
                    new com.schnurritv.sexmod.networking.GalathGrabPacket(
                        galath.getId(), 5),
                    net.minecraftforge.network.PacketDistributor.SERVER.noArg());
            }
        } else if (!anyDown) {
            isKeyDown = false;
        }

        if (feedbackTimer > 0) feedbackTimer--;

        // Auto-close if fully escaped
        if (escapeProgress >= MAX_ESCAPE) {
            // Send final taps
            NetworkHandler.INSTANCE.send(
                new com.schnurritv.sexmod.networking.GalathGrabPacket(
                    galath.getId(), escapeProgress % 5 > 0 ? escapeProgress % 5 : 5),
                net.minecraftforge.network.PacketDistributor.SERVER.noArg());
            this.onClose();
        }

        // Auto-close if no longer grabbed (server-side released us)
        if (!galath.isAlive() || !galath.isGrabbingPlayer()) {
            this.onClose();
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Also handle space/enter as tap helpers
        if (keyCode == GLFW.GLFW_KEY_A || keyCode == GLFW.GLFW_KEY_D ||
            keyCode == GLFW.GLFW_KEY_SPACE || keyCode == GLFW.GLFW_KEY_ENTER) {
            return true; // Consume but let tick() handle the logic
        }
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return true; // Prevent movement
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return true; // Consume all clicks
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        super.onClose();
        // Re-enable player movement
        net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
        if (mc.player != null && mc.player.getAbilities().flying) {
            mc.player.getAbilities().flying = false;
        }
    }
}
