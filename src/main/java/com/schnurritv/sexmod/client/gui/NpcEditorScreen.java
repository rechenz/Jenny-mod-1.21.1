package com.schnurritv.sexmod.client.gui;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.networking.NetworkHandler;
import com.schnurritv.sexmod.networking.NpcEditPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * NPC Editor screen — opened by the Girl Wand on an owned girl.
 *
 * Server-authoritative actions (via NpcEditPacket):
 *   ♥ +10 affection   👗 toggle outfit   🏠 send home   ✏ rename (input box)
 */
public class NpcEditorScreen extends Screen {

    private final BaseGirlEntity girl;
    private EditBox nameBox;

    private static final int COLOR_BG     = 0xE8221122;
    private static final int COLOR_PANEL  = 0xD0331133;
    private static final int COLOR_BORDER = 0xFF664466;
    private static final int COLOR_TEXT   = 0xFFDDCCDD;

    public NpcEditorScreen(BaseGirlEntity girl) {
        super(Component.literal("NPC Editor"));
        this.girl = girl;
    }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int cy = this.height / 2;
        int bw = 120;

        this.addRenderableWidget(Button.builder(Component.literal("♥ +10 Affection"),
                b -> NetworkHandler.sendToServer(new NpcEditPacket(girl.getId(),
                        NpcEditPacket.Action.ADD_AFFECTION, "")))
                .bounds(cx - bw / 2, cy - 70, bw, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("👗 Toggle Outfit"),
                b -> NetworkHandler.sendToServer(new NpcEditPacket(girl.getId(),
                        NpcEditPacket.Action.SET_CLOTHING, "")))
                .bounds(cx - bw / 2, cy - 44, bw, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("🏠 Send Home"),
                b -> NetworkHandler.sendToServer(new NpcEditPacket(girl.getId(),
                        NpcEditPacket.Action.GO_HOME, "")))
                .bounds(cx - bw / 2, cy - 18, bw, 20).build());

        this.nameBox = new EditBox(this.font, cx - bw / 2, cy + 12, bw, 20,
                Component.literal("New name"));
        this.nameBox.setMaxLength(32);
        this.addRenderableWidget(this.nameBox);

        this.addRenderableWidget(Button.builder(Component.literal("✏ Rename"),
                b -> NetworkHandler.sendToServer(new NpcEditPacket(girl.getId(),
                        NpcEditPacket.Action.RENAME, nameBox.getValue())))
                .bounds(cx - bw / 2, cy + 38, bw, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("Close"),
                b -> this.onClose())
                .bounds(cx - bw / 2, cy + 66, bw, 20).build());
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(g, mouseX, mouseY, partialTick);
        int cx = this.width / 2;
        int cy = this.height / 2;
        g.fill(cx - 90, cy - 100, cx + 90, cy + 100, COLOR_BORDER);
        g.fill(cx - 88, cy - 98, cx + 88, cy + 98, COLOR_PANEL);
        g.drawCenteredString(this.font, "§dNPC Editor — " + girl.getGirlName(), cx, cy - 90, COLOR_TEXT);
        g.drawCenteredString(this.font, "♥ Affection: " + girl.getAffection(), cx, cy - 76, COLOR_TEXT);
        super.render(g, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
