package com.schnurritv.sexmod.entity.kobold;

import net.minecraft.ChatFormatting;
import net.minecraft.world.phys.Vec3;

/**
 * Tribe colors for Kobolds.
 * Each color has a main RGB, secondary RGB, wool metadata id, and text color.
 */
public enum EyeAndKoboldColor {
    GREEN(69, 141, 113, 91, 167, 128, 9, ChatFormatting.DARK_GREEN),
    YELLOW(241, 177, 77, 255, 226, 170, 4, ChatFormatting.YELLOW),
    RED(230, 27, 57, 253, 232, 239, 14, ChatFormatting.RED),
    PURPLE(196, 148, 207, 246, 188, 96, 10, ChatFormatting.DARK_PURPLE),
    LIGHT_GREEN(170, 208, 47, 230, 214, 104, 5, ChatFormatting.GREEN),
    OLD_BLUE(173, 138, 128, 118, 151, 180, 2, ChatFormatting.LIGHT_PURPLE),
    DARK_GREY(92, 92, 110, 198, 193, 165, 7, ChatFormatting.DARK_GRAY),
    BROWN(200, 145, 112, 253, 228, 198, 12, ChatFormatting.GOLD),
    DARK_BLUE(65, 84, 116, 104, 137, 146, 11, ChatFormatting.DARK_BLUE),
    LIGHT_BLUE(100, 163, 206, 138, 235, 242, 3, ChatFormatting.DARK_AQUA),
    SILVER(136, 136, 134, 255, 255, 255, 0, ChatFormatting.GRAY);

    final Vec3 mainColor;
    final Vec3 secondaryColor;
    final int woolMeta;
    final ChatFormatting textColor;

    EyeAndKoboldColor(int r1, int g1, int b1, int r2, int g2, int b2, int wool, ChatFormatting text) {
        this.mainColor = new Vec3(r1, g1, b1);
        this.secondaryColor = new Vec3(r2, g2, b2);
        this.woolMeta = wool;
        this.textColor = text;
    }

    public static int indexOf(EyeAndKoboldColor color) {
        EyeAndKoboldColor[] values = values();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == color) return i;
        }
        return 0;
    }

    public static EyeAndKoboldColor safeValueOf(String name) {
        try {
            return valueOf(name);
        } catch (IllegalArgumentException e) {
            return PURPLE;
        }
    }

    public static EyeAndKoboldColor getColorByWoolId(int meta) {
        for (EyeAndKoboldColor c : values()) {
            if (c.woolMeta == meta) return c;
        }
        return PURPLE;
    }

    public Vec3 getMainColor() { return mainColor; }
    public Vec3 getSecondaryColor() { return secondaryColor; }
    public int getWoolMeta() { return woolMeta; }
    public ChatFormatting getTextColor() { return textColor; }
}
