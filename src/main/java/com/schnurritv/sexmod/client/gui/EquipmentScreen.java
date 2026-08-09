package com.schnurritv.sexmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.schnurritv.sexmod.entity.SexFighterEntity;
import com.schnurritv.sexmod.networking.NetworkHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

/**
 * Equipment management screen for fighter characters.
 * <p>
 * Displays a 3×2 grid of equipment slots plus the player's inventory grid.
 * <p>
 * Interaction model (click-based, server-authoritative):
 *  - Click an inventory slot to "pick it up" (highlighted). Click again to cancel.
 *  - Click an equipment slot while holding an inventory item → server moves 1 item
 *    from that inventory slot into the equipment slot (old equipment is returned first).
 *  - Click an equipment slot while holding nothing → server moves the equipped item
 *    back into the player's inventory (or drops it if full).
 *
 * The client never mutates the local inventory directly — all transfers are
 * executed by the server via {@link com.schnurritv.sexmod.networking.EquipmentChangePacket},
 * which keeps the server-side player inventory authoritative.
 */
public class EquipmentScreen extends Screen {

    private final SexFighterEntity fighter;
    private int panelX, panelY;
    private static final int PANEL_W = 176;
    private static final int PANEL_H = 260;

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

    // Player inventory grid: 9 columns × 4 rows (27 main + 9 hotbar)
    private static final int INV_COLS = 9;
    private static final int INV_GRID_X = 8;
    private static final int INV_GRID_Y = 122;

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

    // Player inventory index currently "held" by the cursor (-1 = nothing held)
    private int heldInvIndex = -1;

    private static final int COLOR_BG       = 0xE8221122;
    private static final int COLOR_PANEL    = 0xD0331133;
    private static final int COLOR_BORDER   = 0xFF664466;
    private static final int COLOR_TEXT     = 0xFFDDCCDD;
    private static final int COLOR_ACCENT   = 0xFFCC6688;
    private static final int COLOR_SLOT_BG  = 0xFF442244;
    private static final int COLOR_SLOT_HL  = 0xFF664466;
    private static final int COLOR_HINT     = 0xFF887788;
    private static final int COLOR_INV_BG   = 0xFF332233;
    private static final int COLOR_CURSOR   = 0xFFAA88AA;

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

        // ── Render equipment slots ──
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
        String hint = "Click inventory item, then click an equip slot";
        g.drawString(this.font, hint, panelX + 8, panelY + 110, COLOR_HINT, true);

        // ── Player inventory grid (27 main + 9 hotbar) ──
        var player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null) {
            // Panel background for inventory area
            g.fill(panelX + 4, panelY + INV_GRID_Y - 4, panelX + 4 + INV_COLS * SLOT_SIZE + 2, panelY + INV_GRID_Y + 4 * SLOT_SIZE + 2, COLOR_INV_BG);

            for (int i = 0; i < 36; i++) {
                int col = i % INV_COLS;
                int row = i / INV_COLS;
                int sx = panelX + INV_GRID_X + col * SLOT_SIZE;
                int sy = panelY + INV_GRID_Y + row * SLOT_SIZE;

                boolean hovered = mouseX >= sx && mouseX <= sx + SLOT_SIZE
                               && mouseY >= sy && mouseY <= sy + SLOT_SIZE;
                int bg = hovered ? COLOR_SLOT_HL : COLOR_SLOT_BG;
                if (i == heldInvIndex) bg = COLOR_CURSOR;
                g.fill(sx, sy, sx + SLOT_SIZE, sy + SLOT_SIZE, bg);
                g.fill(sx, sy, sx + 1, sy + SLOT_SIZE, COLOR_BORDER);
                g.fill(sx, sy, sx + SLOT_SIZE, sy + 1, COLOR_BORDER);
                g.fill(sx + SLOT_SIZE - 1, sy, sx + SLOT_SIZE, sy + SLOT_SIZE, COLOR_BORDER);
                g.fill(sx, sy + SLOT_SIZE - 1, sx + SLOT_SIZE, sy + SLOT_SIZE, COLOR_BORDER);

                ItemStack stack = player.getInventory().getItem(i);
                if (!stack.isEmpty()) {
                    g.renderItem(stack, sx + 1, sy + 1);
                    g.renderItemDecorations(this.font, stack, sx + 1, sy + 1);
                }
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return false;

        // ── Player inventory click: pick up / cancel / re-pick ──
        int invIndex = inventoryIndexAt(mouseX, mouseY);
        if (invIndex >= 0) {
            if (heldInvIndex == invIndex) {
                heldInvIndex = -1; // click again to cancel
            } else {
                heldInvIndex = invIndex; // pick up (client-side marker only)
            }
            return true;
        }

        // ── Equipment slot click ──
        for (int slot = 0; slot < 6; slot++) {
            int sx = panelX + SLOT_POSITIONS[slot][0];
            int sy = panelY + SLOT_POSITIONS[slot][1];
            if (mouseX >= sx && mouseX <= sx + SLOT_SIZE && mouseY >= sy && mouseY <= sy + SLOT_SIZE) {
                if (heldInvIndex >= 0) {
                    // Equip: server moves 1 item from heldInvIndex into this slot
                    NetworkHandler.sendEquipmentChange(fighter.getId(), "equip", slot, heldInvIndex);
                    heldInvIndex = -1;
                } else {
                    // Unequip: server moves the equipped item back into the player inventory
                    NetworkHandler.sendEquipmentChange(fighter.getId(), "unequip", slot, -1);
                }
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        // All transfers are click-based and server-authoritative — nothing to do here.
        return super.mouseReleased(mouseX, mouseY, button);
    }

    /**
     * Map a screen coordinate to a player inventory index (0-35), or -1 if outside.
     * Layout: 9 cols × 4 rows starting at INV_GRID_X/INV_GRID_Y.
     * Index 0-26 = main inventory (row-major), 27-35 = hotbar.
     */
    private int inventoryIndexAt(double mouseX, double mouseY) {
        int sx0 = panelX + INV_GRID_X;
        int sy0 = panelY + INV_GRID_Y;
        for (int i = 0; i < 36; i++) {
            int col = i % INV_COLS;
            int row = i / INV_COLS;
            int sx = sx0 + col * SLOT_SIZE;
            int sy = sy0 + row * SLOT_SIZE;
            if (mouseX >= sx && mouseX <= sx + SLOT_SIZE && mouseY >= sy && mouseY <= sy + SLOT_SIZE) {
                return i;
            }
        }
        return -1;
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
