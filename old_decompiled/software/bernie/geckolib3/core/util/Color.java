/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.util;

public final class Color {
    private final int color;
    public static final Color WHITE = new Color(-1);
    public static final Color LIGHT_GRAY = new Color(-4144960);
    public static final Color GRAY = new Color(-8355712);
    public static final Color DARK_GRAY = new Color(-12566464);
    public static final Color BLACK = new Color(-16777216);
    public static final Color RED = new Color(-65536);
    public static final Color PINK = new Color(-20561);
    public static final Color ORANGE = new Color(-14336);
    public static final Color YELLOW = new Color(-256);
    public static final Color GREEN = new Color(-16711936);
    public static final Color MAGENTA = new Color(-65281);
    public static final Color CYAN = new Color(-16711681);
    public static final Color BLUE = new Color(-16776961);

    private Color(int color) {
        this.color = color;
    }

    public static Color ofTransparent(int color) {
        return new Color(color);
    }

    public static Color ofOpaque(int color) {
        return new Color(0xFF000000 | color);
    }

    public static Color ofRGB(float r2, float g10, float b10) {
        return Color.ofRGBA(r2, g10, b10, 1.0f);
    }

    public static Color ofRGB(int r2, int g10, int b10) {
        return Color.ofRGBA(r2, g10, b10, 255);
    }

    public static Color ofRGBA(float r2, float g10, float b10, float a10) {
        return Color.ofRGBA((int)((double)(r2 * 255.0f) + 0.5), (int)((double)(g10 * 255.0f) + 0.5), (int)((double)(b10 * 255.0f) + 0.5), (int)((double)(a10 * 255.0f) + 0.5));
    }

    public static Color ofRGBA(int r2, int g10, int b10, int a10) {
        return new Color((a10 & 0xFF) << 24 | (r2 & 0xFF) << 16 | (g10 & 0xFF) << 8 | b10 & 0xFF);
    }

    public static Color ofHSB(float hue, float saturation, float brightness) {
        return Color.ofOpaque(Color.HSBtoRGB(hue, saturation, brightness));
    }

    public static int HSBtoRGB(float hue, float saturation, float brightness) {
        int r2 = 0;
        int g10 = 0;
        int b10 = 0;
        if (saturation == 0.0f) {
            g10 = b10 = (int)(brightness * 255.0f + 0.5f);
            r2 = b10;
        } else {
            float h2 = (hue - (float)Math.floor(hue)) * 6.0f;
            float f10 = h2 - (float)Math.floor(h2);
            float p2 = brightness * (1.0f - saturation);
            float q2 = brightness * (1.0f - saturation * f10);
            float t2 = brightness * (1.0f - saturation * (1.0f - f10));
            switch ((int)h2) {
                case 0: {
                    r2 = (int)(brightness * 255.0f + 0.5f);
                    g10 = (int)(t2 * 255.0f + 0.5f);
                    b10 = (int)(p2 * 255.0f + 0.5f);
                    break;
                }
                case 1: {
                    r2 = (int)(q2 * 255.0f + 0.5f);
                    g10 = (int)(brightness * 255.0f + 0.5f);
                    b10 = (int)(p2 * 255.0f + 0.5f);
                    break;
                }
                case 2: {
                    r2 = (int)(p2 * 255.0f + 0.5f);
                    g10 = (int)(brightness * 255.0f + 0.5f);
                    b10 = (int)(t2 * 255.0f + 0.5f);
                    break;
                }
                case 3: {
                    r2 = (int)(p2 * 255.0f + 0.5f);
                    g10 = (int)(q2 * 255.0f + 0.5f);
                    b10 = (int)(brightness * 255.0f + 0.5f);
                    break;
                }
                case 4: {
                    r2 = (int)(t2 * 255.0f + 0.5f);
                    g10 = (int)(p2 * 255.0f + 0.5f);
                    b10 = (int)(brightness * 255.0f + 0.5f);
                    break;
                }
                case 5: {
                    r2 = (int)(brightness * 255.0f + 0.5f);
                    g10 = (int)(p2 * 255.0f + 0.5f);
                    b10 = (int)(q2 * 255.0f + 0.5f);
                }
            }
        }
        return 0xFF000000 | r2 << 16 | g10 << 8 | b10;
    }

    public int getColor() {
        return this.color;
    }

    public int getAlpha() {
        return this.color >> 24 & 0xFF;
    }

    public int getRed() {
        return this.color >> 16 & 0xFF;
    }

    public int getGreen() {
        return this.color >> 8 & 0xFF;
    }

    public int getBlue() {
        return this.color & 0xFF;
    }

    public Color brighter(double factor) {
        int r2 = this.getRed();
        int g10 = this.getGreen();
        int b10 = this.getBlue();
        int i2 = (int)(1.0 / (1.0 - 1.0 / factor));
        if (r2 == 0 && g10 == 0 && b10 == 0) {
            return Color.ofRGBA(i2, i2, i2, this.getAlpha());
        }
        if (r2 > 0 && r2 < i2) {
            r2 = i2;
        }
        if (g10 > 0 && g10 < i2) {
            g10 = i2;
        }
        if (b10 > 0 && b10 < i2) {
            b10 = i2;
        }
        return Color.ofRGBA(Math.min((int)((double)r2 / (1.0 / factor)), 255), Math.min((int)((double)g10 / (1.0 / factor)), 255), Math.min((int)((double)b10 / (1.0 / factor)), 255), this.getAlpha());
    }

    public Color darker(double factor) {
        return Color.ofRGBA(Math.max((int)((double)this.getRed() * (1.0 / factor)), 0), Math.max((int)((double)this.getGreen() * (1.0 / factor)), 0), Math.max((int)((double)this.getBlue() * (1.0 / factor)), 0), this.getAlpha());
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        return this.color == ((Color)other).color;
    }

    public int hashCode() {
        return this.color;
    }

    public String toString() {
        return String.valueOf(this.color);
    }
}

