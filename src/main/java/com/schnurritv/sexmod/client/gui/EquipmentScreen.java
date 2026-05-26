package com.schnurritv.sexmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.schnurritv.sexmod.entity.SexFighterEntity;
import com.schnurritv.sexmod.networking.NetworkHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

/**
 * Equipment management screen for fighter characters.
 * <p>
 * Displays a 3×2 grid of equipment slots:
 * ┌──────────┐
 * │ Melee  │ Bow    │
 * │ Helmet │ Chest  │
 * │ Legs   │ Boots  │
 * └──────────┘
 * <p>
 * Players can drag items from their inventory onto the slots
 * to equip their girlfriend. The change is sent to the server.
 */
public class EquipmentScreen extends Screen {

    private final SexFighterEntity fighter;
    private int panelX, panelY;
    private static final int PANEL_W = 176;
    private static final int PANEL_H = 120;

    // Slot layout: 2 columns × 3 rows
    // Slot 0 (melee)  → col 0, row 0
    // Slot 1 (bow)    → col 1, row 0
    // Slot 2 (helmet) → col 0, row 1
    // Slot 3 (chest)  → col 1, row 1
    // Slot 4 (legs)   → col 0, row 2
    // Slot 5 (boots)  → col 1, row 2
    private static final int SLOT_SIZE = 18;
    private static final int SLOT_SPACING = 22;
    private static final int COL0_X = 8;
    private static final int COL1_X = COL0_X + SLOT_SPACING;
    private static final int ROW0_Y = 30;
    private static final int ROW1_Y = ROW0_Y + SLOT_SPACING;
    private static final int ROW2_Y = ROW1_Y + SLOT_SPACING;

    private static final int[][] SLOT_POSITIONS = {
        {COL0_X, ROW0_Y}, // 0: melee
        {COL1_X, ROW0_Y}, // 1: bow
        {COL0_X, ROW1_Y}, // 2: helmet
        {COL1_X, ROW1_Y}, // 3: chestplate
        {COL0_X, ROW2_Y}, // 4: leggings
        {COL1_X, ROW2_Y}, // 5: boots
    };

    private static final String[] SLOT_LABELS = {
        "Melee", "Bow",
        "Helmet", "Chest",
        "Legs", "Boots"
    };

    private int draggedSlot = -1;
    private ItemStack draggedStack = ItemStack.EMPTY;
    private boolean inserting = false; // true = inserting from player inv, false = removing

    private static final int COLOR_BG       = 0xE8221122;
    private static final int COLOR_PANEL    = 0xD0331133;
    private static final int COLOR_BORDER   = 0xFF664466;
    private static final int COLOR_TEXT     = 0xFFDDCCDD;
    private static final int COLOR_ACCENT   = 0xFFCC6688;
    private static final int COLOR_SLOT_BG  = 0xFF442244;
    private static final int COLOR_SLOT_HL  = 0xFF664466;
    private static final int COLOR_HINT     = 0xFF887788;

    public EquipmentScreen(SexFighterEntity fighter) {
        super(Component.literal("Equipment"));
        this.fighter = fighter;
    }

    @Override
    protected void init() {
        this.panelX = (this.width - PANEL_W) / 2;
        this.panelY = (this.height - PANEL_H) / 2;
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        // Dim background
        g.fill(0, 0, this.width, this.height, 0x88000000);
        super.render(g, mouseX, mouseY, partialTick);
        RenderSystem.enableBlend();

        // Panel background
        g.fill(panelX - 2, panelY - 2, panelX + PANEL_W + 2, panelY + PANEL_H + 2, COLOR_BORDER);
        g.fill(panelX, panelY, panelX + PANEL_W, panelY + PANEL_H, COLOR_BG);

        // ── Title ──
        String name = fighter.getGirlName().substring(0, 1).toUpperCase() + fighter.getGirlName().substring(1);
        g.drawString(this.font, Component.literal("§l" + name + " Equipment"),
                     panelX + 8, panelY + 8, COLOR_ACCENT, true);

        // ── Render slots ──
        for (int slot = 0; slot < 6; slot++) {
            int sx = panelX + SLOT_POSITIONS[slot][0];
            int sy = panelY + SLOT_POSITIONS[slot][1];

            // Slot background
            boolean hovered = mouseX >= sx && mouseX <= sx + SLOT_SIZE
                           && mouseY >= sy && mouseY <= sy + SLOT_SIZE;
            g.fill(sx, sy, sx + SLOT_SIZE, sy + SLOT_SIZE, hovered ? COLOR_SLOT_HL : COLOR_SLOT_BG);
            g.fill(sx, sy, sx + 1, sy + SLOT_SIZE, COLOR_BORDER);
            g.fill(sx, sy, sx + SLOT_SIZE, sy + 1, COLOR_BORDER);
            g.fill(sx + SLOT_SIZE - 1, sy, sx + SLOT_SIZE, sy + SLOT_SIZE, COLOR_BORDER);
            g.fill(sx, sy + SLOT_SIZE - 1, sx + SLOT_SIZE, sy + SLOT_SIZE, COLOR_BORDER);

            // Render item in slot
            ItemStack stack = fighter.getEquipmentSlot(slot);
            if (!stack.isEmpty()) {
                g.renderItem(stack, sx + 1, sy + 1);
            }

            // Side label
            g.drawString(this.font, SLOT_LABELS[slot], sx + SLOT_SIZE + 4, sy + 4, COLOR_TEXT, true);
        }

        // ── Instructions ──
        String hint = "Drag items from inventory onto slots to equip";
        g.drawString(this.font, hint, panelX + 8, panelY + PANEL_H - 16, COLOR_HINT, true);

        // ── Dragged item under cursor ──
        if (draggedSlot >= 0 && !draggedStack.isEmpty()) {
            g.renderItem(draggedStack, mouseX - 8, mouseY - 8);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return false;

        // Check if clicking on a slot
        for (int slot = 0; slot < 6; slot++) {
            int sx = panelX + SLOT_POSITIONS[slot][0];
            int sy = panelY + SLOT_POSITIONS[slot][1];
            if (mouseX >= sx && mouseX <= sx + SLOT_SIZE && mouseY >= sy && mouseY <= sy + SLOT_SIZE) {
                // Pick up / put down item
                ItemStack current = fighter.getEquipmentSlot(slot);
                if (current.isEmpty()) return true;
                // Remove item from slot (player gets it back via inventory)
                removeEquipment(slot);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    /**
     * Remove equipment from a slot and give it back to the player.
     */
    private void removeEquipment(int slot) {
        var player = net.minecraft.client.Minecraft.getInstance().player;
        if (player == null) return;

        ItemStack current = fighter.getEquipmentSlot(slot);
        if (current.isEmpty()) return;

        // Try to add to player inventory
        if (!player.getInventory().add(current)) {
            // Drop if inventory full
            player.drop(current, false);
        }

        // Send empty stack to server
        NetworkHandler.sendEquipmentChange(fighter.getId(), slot, ItemStack.EMPTY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        // If dragging from player inventory
        if (button == 0 && isInventorySlot(mouseX, mouseY)) {
            var mc = net.minecraft.client.Minecraft.getInstance();
            var player = mc.player;
            if (player == null) return false;

            // Find which slot was the player clicking in their own inventory
            // For simplicity, use the currently hovered inventory slot (handled by Screen)
            ItemStack held = player.containerMenu.getCarried();
            if (!held.isEmpty()) {
                // Determine target slot based on mouse position
                for (int slot = 0; slot < 6; slot++) {
                    int sx = panelX + SLOT_POSITIONS[slot][0];
                    int sy = panelY + SLOT_POSITIONS[slot][1];
                    if (mouseX >= sx && mouseX <= sx + SLOT_SIZE && mouseY >= sy && mouseY <= sy + SLOT_SIZE) {
                        // Insert the carried item into this equipment slot
                        ItemStack toEquip = held.copy();
                        toEquip.setCount(1);
                        NetworkHandler.sendEquipmentChange(fighter.getId(), slot, toEquip);
                        // Remove 1 from carried
                        held.shrink(1);
                        if (held.isEmpty()) {
                            player.containerMenu.setCarried(ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    /**
     * Check if mouse is over the player's inventory area.
     * For this simplified version, we just allow any valid slot area.
     */
    private boolean isInventorySlot(double mouseX, double mouseY) {
        return true; // Allow drop from any position - the network will validate on server
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
