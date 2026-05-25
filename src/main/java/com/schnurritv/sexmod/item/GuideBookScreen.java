package com.schnurritv.sexmod.item;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

/**
 * A simple multi-page book screen for the Guide Book.
 */
public class GuideBookScreen extends Screen {
    private final ItemStack book;
    private final String[] pages;
    private int currentPage = 0;

    private static final int COLOR_BG = 0xF0221122;
    private static final int COLOR_PAGE = 0xE0331133;
    private static final int COLOR_BORDER = 0xFF664466;
    private static final int COLOR_TEXT = 0xFFDDCCDD;
    private static final int COLOR_HEADER = 0xFFFF88CC;

    protected GuideBookScreen(ItemStack book, String[] pages) {
        super(Component.literal("Jenny Mod Guide"));
        this.book = book;
        this.pages = pages;
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        g.fill(0, 0, this.width, this.height, 0x88000000);

        int pageW = Math.min(280, this.width - 40);
        int pageH = Math.min(200, this.height - 60);
        int pageX = (this.width - pageW) / 2;
        int pageY = (this.height - pageH) / 2;

        // Book border
        g.fill(pageX - 2, pageY - 2, pageX + pageW + 2, pageY + pageH + 2, COLOR_BORDER);
        g.fill(pageX, pageY, pageX + pageW, pageY + pageH, COLOR_PAGE);

        // Title + page number
        String title = "§d§lJenny Mod Guide";
        g.drawString(this.font, title, pageX + 10, pageY + 8, COLOR_HEADER, true);

        String pageNum = "Page " + (currentPage + 1) + "/" + pages.length;
        int pnW = this.font.width(pageNum);
        g.drawString(this.font, pageNum, pageX + pageW - pnW - 10, pageY + 8, 0xFF887788, true);

        // Separator
        g.fill(pageX + 10, pageY + 22, pageX + pageW - 10, pageY + 23, COLOR_BORDER);

        // Page content — split by \n and render
        String content = pages[currentPage];
        if (content != null) {
            String[] lines = content.split("\n");
            int textY = pageY + 30;
            for (String line : lines) {
                if (textY > pageY + pageH - 20) break;
                g.drawString(this.font, line, pageX + 14, textY, COLOR_TEXT, true);
                textY += 12;
            }
        }

        // Navigation buttons
        int btnY = pageY + pageH + 10;
        int btnW = 100;
        btnW = Math.min(btnW, pageW / 2 - 10);

        if (currentPage > 0) {
            int bx1 = pageX;
            int bx2 = pageX + btnW;
            int by1 = btnY;
            int by2 = btnY + 20;
            boolean hovered = mouseX >= bx1 && mouseX <= bx2 && mouseY >= by1 && mouseY <= by2;
            g.fill(bx1, by1, bx2, by2, hovered ? 0xD0443366 : 0xC0331133);
            g.fill(bx1, by1, bx2, by1 + 1, COLOR_BORDER);
            g.drawCenteredString(this.font, "§l← Previous", bx1 + btnW / 2, by1 + 6, 0xFFEEAAEE);
        }

        if (currentPage < pages.length - 1) {
            int bx1 = pageX + pageW - btnW;
            int bx2 = pageX + pageW;
            int by1 = btnY;
            int by2 = btnY + 20;
            boolean hovered = mouseX >= bx1 && mouseX <= bx2 && mouseY >= by1 && mouseY <= by2;
            g.fill(bx1, by1, bx2, by2, hovered ? 0xD0443366 : 0xC0331133);
            g.fill(bx1, by1, bx2, by1 + 1, COLOR_BORDER);
            g.drawCenteredString(this.font, "§lNext →", bx1 + btnW / 2, by1 + 6, 0xFFEEAAEE);
        }

        // Close hint
        String closeHint = "Press ESC to close";
        g.drawCenteredString(this.font, closeHint, this.width / 2, btnY + 28, 0xFF887788);

        super.render(g, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int pageW = Math.min(280, this.width - 40);
        int pageH = Math.min(200, this.height - 60);
        int pageX = (this.width - pageW) / 2;
        int pageY = (this.height - pageH) / 2;
        int btnY = pageY + pageH + 10;
        int btnW = Math.min(100, pageW / 2 - 10);

        if (currentPage > 0) {
            int bx1 = pageX;
            int bx2 = pageX + btnW;
            int by1 = btnY;
            int by2 = btnY + 20;
            if (mouseX >= bx1 && mouseX <= bx2 && mouseY >= by1 && mouseY <= by2) {
                currentPage--;
                return true;
            }
        }

        if (currentPage < pages.length - 1) {
            int bx1 = pageX + pageW - btnW;
            int bx2 = pageX + pageW;
            int by1 = btnY;
            int by2 = btnY + 20;
            if (mouseX >= bx1 && mouseX <= bx2 && mouseY >= by1 && mouseY <= by2) {
                currentPage++;
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
