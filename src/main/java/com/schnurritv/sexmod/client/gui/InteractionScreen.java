package com.schnurritv.sexmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.networking.NetworkHandler;
import com.schnurritv.sexmod.relationship.AffectionData;
import com.schnurritv.sexmod.relationship.DialogueDB;
import com.schnurritv.sexmod.relationship.QuestManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RPG-style dialogue screen replacing the old radial wheel.
 *
 * Layout:
 * ┌──────────────────────────────────────┐
 * │  ♥ Ellie  [Affection: 42/100 ❤]     │  ← Header (girl name + affection bar)
 * │  ┌──────────────────────────────────┐ │
 * │  │ "Hey! Was just thinking about.." │ │  ← Dialogue bubble (context-sensitive)
 * │  └──────────────────────────────────┘ │
 * │  ┌──────────────────────────────────┐ │
 * │  │ 🎁 Give Gift                     │ │  ← Action list (vertical, scrollable)
 * │  │ 👣 Follow / Stay                 │ │
 * │  │ 📋 Quest: Bring 5 Iron Ingots    │ │
 * │  │ 🔞 Missionary     [❤ 30]         │ │
 * │  │ 🔞 Doggy           [❤ 60] 🔒    │ │
 * │  └──────────────────────────────────┘ │
 * └──────────────────────────────────────┘
 */
public class InteractionScreen extends Screen {
    private final BaseGirlEntity girl;
    private int hoveredIndex = -1;
    private final int panelW, panelH;
    private int panelX, panelY;
    private static final Random RAND = new Random();

    private static final int COLOR_BG         = 0xE8221122;
    private static final int COLOR_PANEL      = 0xD0331133;
    private static final int COLOR_BORDER     = 0xFF664466;
    private static final int COLOR_TEXT       = 0xFFDDCCDD;
    private static final int COLOR_TEXT_DIM   = 0xFF887788;
    private static final int COLOR_HOVER      = 0xFF553355;
    private static final int COLOR_ACCENT     = 0xFFCC6688;
    private static final int COLOR_HEART      = 0xFFEE4466;
    private static final int COLOR_HEART_BG   = 0xFF442233;
    private static final int COLOR_SCENE      = 0xFF883355;
    private static final int COLOR_SCENE_LOCK = 0xFF553344;
    private static final int COLOR_QUEST      = 0xFF446688;

    private String dialogueText;

    public InteractionScreen(BaseGirlEntity girl) {
        super(Component.literal("Interaction"));
        this.girl = girl;
        this.panelW = 220;
        this.panelH = 260;
        // Generate a random greeting
        this.dialogueText = getDialogue();
    }

    @Override
    protected void init() {
        this.panelX = (this.width - panelW) / 2;
        this.panelY = (this.height - panelH) / 2;
    }

    private String getDialogue() {
        AffectionData data = girl.getAffectionData();
        String greeting = data.getGreeting(girl.getGirlName());
        return greeting != null ? greeting : "\"...\"";
    }

    private List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
        String girlName = girl.getGirlName();
        boolean following = "FOLLOW".equals(girl.getEntityData().get(SexEntity.MOVEMENT_STATE));
        int aff = girl.getAffection();

        // Gift
        actions.add(new Action("🎁 Give Gift", ActionType.GIFT, false));

        // Follow/Stay
        actions.add(new Action(following ? "✋ Stop Following" : "👣 Follow Me", 
                     following ? ActionType.STAY : ActionType.FOLLOW, false));

        // Quest
        boolean hasQuest = girl.getQuestManager().hasActiveQuest();
        QuestManager.Quest q = girl.getQuestManager().getActiveQuest();
        if (hasQuest && q != null) {
            actions.add(new Action("📋 Quest: " + q.description() + " (" + 
                         girl.getQuestManager().getProgress() + "/" + q.targetCount() + ")",
                         ActionType.QUEST_TURNIN, false));
        } else {
            actions.add(new Action("📋 Ask for a quest", ActionType.QUEST_START, false));
        }

        // Goblin: stolen items retrieval
        if (girl instanceof com.schnurritv.sexmod.entity.goblin.GoblinEntity goblin && goblin.getStealCount() > 0) {
            actions.add(new Action("💰 Return Stolen Items (" + goblin.getStealCount() + ")", ActionType.RETURN_ITEMS, false));
        }

        // ---- Scenes ----
        int lowThreshold = com.schnurritv.sexmod.SexModConfig.AFFECTION_SCENE_THRESHOLD_LOW.get();
        int highThreshold = com.schnurritv.sexmod.SexModConfig.AFFECTION_SCENE_THRESHOLD_HIGH.get();

        boolean lowUnlocked = aff >= lowThreshold;
        boolean highUnlocked = aff >= highThreshold;

        if (lowUnlocked) {
            actions.add(new Action("♥ Missionary", ActionType.SCENE_MISSIONARY, false));
            actions.add(new Action("♥ Blowjob", ActionType.SCENE_BLOWJOB, false));
        } else {
            actions.add(new Action("♥ Missionary  [❤ " + lowThreshold + "]", ActionType.SCENE_LOCKED, true));
            actions.add(new Action("♥ Blowjob  [❤ " + lowThreshold + "]", ActionType.SCENE_LOCKED, true));
        }

        if (highUnlocked) {
            actions.add(new Action("♥ Doggy", ActionType.SCENE_DOGGY, false));
            actions.add(new Action("♥ Boobjob", ActionType.SCENE_BOOBJOB, false));
        } else {
            actions.add(new Action("♥ Doggy  [❤ " + highThreshold + "]", ActionType.SCENE_LOCKED, true));
            actions.add(new Action("♥ Boobjob  [❤ " + highThreshold + "]", ActionType.SCENE_LOCKED, true));
        }

        actions.add(new Action("■ Stop Scene", ActionType.SCENE_STOP, false));

        return actions;
    }

    private record Action(String label, ActionType type, boolean locked) {}
    private enum ActionType {
        FOLLOW, STAY, GIFT, SCENE_MISSIONARY, SCENE_DOGGY, SCENE_BLOWJOB, SCENE_BOOBJOB,
        SCENE_STOP, SCENE_LOCKED, QUEST_START, QUEST_TURNIN, RETURN_ITEMS, NONE
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        // Dim background
        g.fill(0, 0, this.width, this.height, 0x88000000);
        super.render(g, mouseX, mouseY, partialTick);

        RenderSystem.enableBlend();
        List<Action> actions = getActions();

        // ── Panel background ──
        g.fill(panelX - 2, panelY - 2, panelX + panelW + 2, panelY + panelH + 2, COLOR_BORDER);
        g.fill(panelX, panelY, panelX + panelW, panelY + panelH, COLOR_BG);

        int y = panelY + 8;

        // ── Header: Girl Name + Affection Bar ──
        String girlName = girl.getGirlName().substring(0, 1).toUpperCase() + girl.getGirlName().substring(1);
        AffectionData.AffectionLevel level = girl.getAffectionData().getLevel();
        String headerText = girlName + " · " + level.label;
        g.drawString(this.font, Component.literal(headerText).withStyle(s -> s.withBold(true)),
                     panelX + 10, y, 0xFFEEAAEE, true);

        // Affection bar
        int aff = girl.getAffection();
        int affMax = com.schnurritv.sexmod.SexModConfig.AFFECTION_MAX.get();
        int barX = panelX + 10;
        int barY = y + 14;
        int barW = panelW - 20;
        int barH = 8;
        int fillW = (int)((float)aff / affMax * barW);

        g.fill(barX, barY, barX + barW, barY + barH, COLOR_HEART_BG);
        if (fillW > 0) {
            g.fill(barX, barY, barX + fillW, barY + barH, COLOR_HEART);
            // Gradient highlight
            g.fill(barX, barY, barX + fillW, barY + barH / 2, 0x40FFFFFF);
        }
        g.drawCenteredString(this.font, "❤ " + aff + "/" + affMax, barX + barW / 2, barY + 1, 0xFFFFFFFF);

        y = barY + barH + 10;

        // ── Dialogue bubble ──
        int bubbleY = y;
        int bubbleW = panelW - 20;
        List<String> dialogueLines = wrapText(dialogueText, bubbleW - 8);
        int bubbleH = 16 * dialogueLines.size() + 16;
        g.fill(panelX + 8, bubbleY, panelX + panelW - 8, bubbleY + bubbleH, COLOR_PANEL);
        g.fill(panelX + 8, bubbleY, panelX + panelW - 8, bubbleY + 1, COLOR_ACCENT);
        for (int i = 0; i < dialogueLines.size(); i++) {
            g.drawString(this.font, dialogueLines.get(i), panelX + 14, bubbleY + 8 + i * 11, COLOR_TEXT, true);
        }

        y = bubbleY + bubbleH + 6;

        // ── Divider ──
        g.fill(panelX + 20, y, panelX + panelW - 20, y + 1, COLOR_BORDER);
        y += 6;

        // ── Action list ──
        int listStartY = y;
        int actionH = 22;
        hoveredIndex = -1;

        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            int ay = listStartY + i * actionH;
            if (ay + actionH > panelY + panelH - 10) break; // clip to panel

            int ax1 = panelX + 10;
            int ax2 = panelX + panelW - 10;
            int ay1 = ay;
            int ay2 = ay + actionH - 2;

            boolean hovered = mouseX >= ax1 && mouseX <= ax2 && mouseY >= ay1 && mouseY <= ay2;
            if (hovered) hoveredIndex = i;

            int bgColor;
            if (action.locked()) {
                bgColor = COLOR_SCENE_LOCK;
            } else if (hovered) {
                bgColor = COLOR_HOVER;
            } else {
                bgColor = switch (action.type()) {
                    case SCENE_MISSIONARY, SCENE_DOGGY, SCENE_BLOWJOB, SCENE_BOOBJOB -> COLOR_SCENE;
                    case QUEST_START, QUEST_TURNIN -> COLOR_QUEST;
                    default -> COLOR_PANEL;
                };
            }

            g.fill(ax1, ay1, ax2, ay2, bgColor);

            int textColor = action.locked() ? COLOR_TEXT_DIM
                : hovered ? 0xFFEEEEFF : COLOR_TEXT;
            String label = action.label();
            // Truncate if too long
            int maxLabelW = panelW - 30;
            if (this.font.width(label) > maxLabelW) {
                while (this.font.width(label + "...") > maxLabelW && label.length() > 3) {
                    label = label.substring(0, label.length() - 1);
                }
                label += "...";
            }
            g.drawString(this.font, label, ax1 + 6, ay1 + 5, textColor, true);
        }
    }

    private List<String> wrapText(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            lines.add("...");
            return lines;
        }
        String[] words = text.split(" ");
        StringBuilder current = new StringBuilder();
        for (String word : words) {
            if (this.font.width(current + " " + word) > maxWidth) {
                lines.add(current.toString().trim());
                current = new StringBuilder(word);
            } else {
                if (!current.isEmpty()) current.append(" ");
                current.append(word);
            }
        }
        if (!current.isEmpty()) lines.add(current.toString().trim());
        if (lines.isEmpty()) lines.add(text);
        return lines;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (hoveredIndex >= 0) {
            List<Action> actions = getActions();
            if (hoveredIndex < actions.size()) {
                executeAction(actions.get(hoveredIndex));
            }
        }
        this.onClose();
        return true;
    }

    private void executeAction(Action action) {
        var player = net.minecraft.client.Minecraft.getInstance().player;
        if (player == null) return;

        switch (action.type()) {
            case FOLLOW -> {
                NetworkHandler.sendMovementStateUpdate(girl.getId(), "FOLLOW");
            }
            case STAY -> {
                NetworkHandler.sendMovementStateUpdate(girl.getId(), "STAY");
            }
            case GIFT -> {
                // Open a gift selection hint
                player.displayClientMessage(Component.literal(
                    "§dHint: Right-click " + girl.getGirlName() + " while holding a gift item!"), false);
                player.displayClientMessage(Component.literal(
                    "§dShe likes: Diamond, Emerald, flowers, or special mod gifts."), false);
            }
            case SCENE_MISSIONARY -> NetworkHandler.sendSceneAction(girl.getId(), "Missionary");
            case SCENE_DOGGY      -> NetworkHandler.sendSceneAction(girl.getId(), "Doggy");
            case SCENE_BLOWJOB    -> NetworkHandler.sendSceneAction(girl.getId(), "Blowjob");
            case SCENE_BOOBJOB    -> NetworkHandler.sendSceneAction(girl.getId(), "Boobjob");
            case SCENE_STOP       -> NetworkHandler.sendSceneAction(girl.getId(), "Stop");
            case SCENE_LOCKED -> {
                player.displayClientMessage(Component.literal(
                    "<" + girl.getGirlName() + "> " + DialogueDB.getSceneLocked(girl.getGirlName())), false);
            }
            case QUEST_START -> {
                // Ask server to assign a quest
                NetworkHandler.sendSceneAction(girl.getId(), "QuestStart");
            }
            case QUEST_TURNIN -> {
                // Try to turn in quest items
                NetworkHandler.sendSceneAction(girl.getId(), "QuestTurnin");
            }
            case RETURN_ITEMS -> {
                // Send to server to handle
                NetworkHandler.sendSceneAction(girl.getId(), "ReturnItems");
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
