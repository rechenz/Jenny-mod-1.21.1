/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.utils;

public class MathUtils {
    public static int clamp(int x2, int min, int max) {
        return x2 < min ? min : (x2 > max ? max : x2);
    }

    public static float clamp(float x2, float min, float max) {
        return x2 < min ? min : (x2 > max ? max : x2);
    }

    public static double clamp(double x2, double min, double max) {
        return x2 < min ? min : (x2 > max ? max : x2);
    }

    public static int cycler(int x2, int min, int max) {
        return x2 < min ? max : (x2 > max ? min : x2);
    }

    public static float cycler(float x2, float min, float max) {
        return x2 < min ? max : (x2 > max ? min : x2);
    }

    public static double cycler(double x2, double min, double max) {
        return x2 < min ? max : (x2 > max ? min : x2);
    }
}

