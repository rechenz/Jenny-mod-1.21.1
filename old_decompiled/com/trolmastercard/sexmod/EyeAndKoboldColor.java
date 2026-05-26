/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.TextFormatting
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ff;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;

public enum EyeAndKoboldColor {
    GREEN(69, 141, 113, 91, 167, 128, 9, TextFormatting.DARK_GREEN),
    YELLOW(241, 177, 77, 255, 226, 170, 4, TextFormatting.YELLOW),
    RED(230, 27, 57, 253, 232, 239, 14, TextFormatting.RED),
    PURPLE(196, 148, 207, 246, 188, 96, 10, TextFormatting.DARK_PURPLE),
    LIGHT_GREEN(170, 208, 47, 230, 214, 104, 5, TextFormatting.GREEN),
    OLD_BLUE(173, 138, 128, 118, 151, 180, 2, TextFormatting.LIGHT_PURPLE),
    DARK_GREY(92, 92, 110, 198, 193, 165, 7, TextFormatting.DARK_GRAY),
    BROWN(200, 145, 112, 253, 228, 198, 12, TextFormatting.GOLD),
    DARK_BLUE(65, 84, 116, 104, 137, 146, 11, TextFormatting.DARK_BLUE),
    LIGHT_BLUE(100, 163, 206, 138, 235, 242, 3, TextFormatting.DARK_AQUA),
    SILVER(136, 136, 134, 255, 255, 255, 0, TextFormatting.GRAY);

    private final Vec3i mainColor;
    private final Vec3i secondaryColor;
    private final int woolMeta;
    private final TextFormatting textColor;

    private EyeAndKoboldColor(int n3, int n4, int n5, int n6, int n7, int n8, int n9, TextFormatting textFormatting) {
        this.mainColor = new Vec3i(n3, n4, n5);
        this.secondaryColor = new Vec3i(n6, n7, n8);
        this.woolMeta = n9;
        this.textColor = textFormatting;
    }

    public static int indexOf(EyeAndKoboldColor eyeAndKoboldColor) {
        int n2 = 0;
        for (EyeAndKoboldColor eyeAndKoboldColor2 : EyeAndKoboldColor.values()) {
            try {
                if (eyeAndKoboldColor == eyeAndKoboldColor2) {
                    return n2;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw EyeAndKoboldColor.a(illegalArgumentException);
            }
            ++n2;
        }
        return n2;
    }

    public static EyeAndKoboldColor safeValueOf(String string) {
        try {
            return EyeAndKoboldColor.valueOf(string);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return ff.aJ;
        }
    }

    public static EyeAndKoboldColor safeValueOf(Vec3i vec3i) {
        for (EyeAndKoboldColor eyeAndKoboldColor : EyeAndKoboldColor.values()) {
            try {
                if (!vec3i.equals((Object)eyeAndKoboldColor.getMainColor())) continue;
                return eyeAndKoboldColor;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw EyeAndKoboldColor.a(illegalArgumentException);
            }
        }
        return ff.aJ;
    }

    public static EyeAndKoboldColor getColorByWoolId(int n2) {
        for (EyeAndKoboldColor eyeAndKoboldColor : EyeAndKoboldColor.values()) {
            try {
                if (eyeAndKoboldColor.getWoolMeta() != n2) continue;
                return eyeAndKoboldColor;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw EyeAndKoboldColor.a(illegalArgumentException);
            }
        }
        return ff.aJ;
    }

    public Vec3i getMainColor() {
        return this.mainColor;
    }

    public Vec3i getSecondaryColor() {
        return this.secondaryColor;
    }

    public int getWoolMeta() {
        return this.woolMeta;
    }

    public TextFormatting getTextColor() {
        return this.textColor;
    }

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }
}

