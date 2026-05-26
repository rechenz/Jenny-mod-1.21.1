/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.utils;

import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;

public class Interpolations {
    public static float lerp(float a10, float b10, float position) {
        return a10 + (b10 - a10) * position;
    }

    public static float lerpYaw(float a10, float b10, float position) {
        a10 = MathHelper.wrapDegrees(a10);
        b10 = MathHelper.wrapDegrees(b10);
        return Interpolations.lerp(a10, Interpolations.normalizeYaw(a10, b10), position);
    }

    public static double cubicHermite(double y0, double y1, double y2, double y3, double x2) {
        double a10 = -0.5 * y0 + 1.5 * y1 - 1.5 * y2 + 0.5 * y3;
        double b10 = y0 - 2.5 * y1 + 2.0 * y2 - 0.5 * y3;
        double c10 = -0.5 * y0 + 0.5 * y2;
        return ((a10 * x2 + b10) * x2 + c10) * x2 + y1;
    }

    public static double cubicHermiteYaw(float y0, float y1, float y2, float y3, float position) {
        y0 = MathHelper.wrapDegrees(y0);
        y1 = MathHelper.wrapDegrees(y1);
        y2 = MathHelper.wrapDegrees(y2);
        y3 = MathHelper.wrapDegrees(y3);
        y1 = Interpolations.normalizeYaw(y0, y1);
        y2 = Interpolations.normalizeYaw(y1, y2);
        y3 = Interpolations.normalizeYaw(y2, y3);
        return Interpolations.cubicHermite(y0, y1, y2, y3, position);
    }

    public static float cubic(float y0, float y1, float y2, float y3, float x2) {
        float a10 = y3 - y2 - y0 + y1;
        float b10 = y0 - y1 - a10;
        float c10 = y2 - y0;
        return ((a10 * x2 + b10) * x2 + c10) * x2 + y1;
    }

    public static float cubicYaw(float y0, float y1, float y2, float y3, float position) {
        y0 = MathHelper.wrapDegrees(y0);
        y1 = MathHelper.wrapDegrees(y1);
        y2 = MathHelper.wrapDegrees(y2);
        y3 = MathHelper.wrapDegrees(y3);
        y1 = Interpolations.normalizeYaw(y0, y1);
        y2 = Interpolations.normalizeYaw(y1, y2);
        y3 = Interpolations.normalizeYaw(y2, y3);
        return Interpolations.cubic(y0, y1, y2, y3, position);
    }

    public static float bezierX(float x1, float x2, float t2, float epsilon) {
        float x3 = t2;
        float init = Interpolations.bezier(0.0f, x1, x2, 1.0f, t2);
        float factor = Math.copySign(0.1f, t2 - init);
        while (Math.abs(t2 - init) > epsilon) {
            float oldFactor = factor;
            init = Interpolations.bezier(0.0f, x1, x2, 1.0f, x3 += factor);
            if (Math.copySign(factor, t2 - init) == oldFactor) continue;
            factor *= -0.25f;
        }
        return x3;
    }

    public static float bezierX(float x1, float x2, float t2) {
        return Interpolations.bezierX(x1, x2, t2, 5.0E-4f);
    }

    public static float bezier(float x1, float x2, float x3, float x4, float t2) {
        float t1 = Interpolations.lerp(x1, x2, t2);
        float t22 = Interpolations.lerp(x2, x3, t2);
        float t3 = Interpolations.lerp(x3, x4, t2);
        float t4 = Interpolations.lerp(t1, t22, t2);
        float t5 = Interpolations.lerp(t22, t3, t2);
        return Interpolations.lerp(t4, t5, t2);
    }

    public static float normalizeYaw(float a10, float b10) {
        float diff = a10 - b10;
        if (diff > 180.0f || diff < -180.0f) {
            diff = Math.copySign(360.0f - Math.abs(diff), diff);
            return a10 + diff;
        }
        return b10;
    }

    public static float envelope(float x2, float duration, float fades) {
        return Interpolations.envelope(x2, 0.0f, fades, duration - fades, duration);
    }

    public static float envelope(float x2, float lowIn, float lowOut, float highIn, float highOut) {
        if (x2 < lowIn || x2 > highOut) {
            return 0.0f;
        }
        if (x2 < lowOut) {
            return (x2 - lowIn) / (lowOut - lowIn);
        }
        if (x2 > highIn) {
            return 1.0f - (x2 - highIn) / (highOut - highIn);
        }
        return 1.0f;
    }

    public static double lerp(double a10, double b10, double position) {
        return a10 + (b10 - a10) * position;
    }

    public static double lerpYaw(double a10, double b10, double position) {
        a10 = MathHelper.wrapDegrees(a10);
        b10 = MathHelper.wrapDegrees(b10);
        return Interpolations.lerp(a10, Interpolations.normalizeYaw(a10, b10), position);
    }

    public static double cubic(double y0, double y1, double y2, double y3, double x2) {
        double a10 = y3 - y2 - y0 + y1;
        double b10 = y0 - y1 - a10;
        double c10 = y2 - y0;
        return ((a10 * x2 + b10) * x2 + c10) * x2 + y1;
    }

    public static double cubicYaw(double y0, double y1, double y2, double y3, double position) {
        y0 = MathHelper.wrapDegrees(y0);
        y1 = MathHelper.wrapDegrees(y1);
        y2 = MathHelper.wrapDegrees(y2);
        y3 = MathHelper.wrapDegrees(y3);
        y1 = Interpolations.normalizeYaw(y0, y1);
        y2 = Interpolations.normalizeYaw(y1, y2);
        y3 = Interpolations.normalizeYaw(y2, y3);
        return Interpolations.cubic(y0, y1, y2, y3, position);
    }

    public static double bezierX(double x1, double x2, double t2, double epsilon) {
        double x3 = t2;
        double init = Interpolations.bezier(0.0, x1, x2, 1.0, t2);
        double factor = Math.copySign((double)0.1f, t2 - init);
        while (Math.abs(t2 - init) > epsilon) {
            double oldFactor = factor;
            init = Interpolations.bezier(0.0, x1, x2, 1.0, x3 += factor);
            if (Math.copySign(factor, t2 - init) == oldFactor) continue;
            factor *= -0.25;
        }
        return x3;
    }

    public static double bezierX(double x1, double x2, float t2) {
        return Interpolations.bezierX(x1, x2, (double)t2, (double)5.0E-4f);
    }

    public static double bezier(double x1, double x2, double x3, double x4, double t2) {
        double t1 = Interpolations.lerp(x1, x2, t2);
        double t22 = Interpolations.lerp(x2, x3, t2);
        double t3 = Interpolations.lerp(x3, x4, t2);
        double t4 = Interpolations.lerp(t1, t22, t2);
        double t5 = Interpolations.lerp(t22, t3, t2);
        return Interpolations.lerp(t4, t5, t2);
    }

    public static double normalizeYaw(double a10, double b10) {
        double diff = a10 - b10;
        if (diff > 180.0 || diff < -180.0) {
            diff = Math.copySign(360.0 - Math.abs(diff), diff);
            return a10 + diff;
        }
        return b10;
    }

    public static double envelope(double x2, double duration, double fades) {
        return Interpolations.envelope(x2, 0.0, fades, duration - fades, duration);
    }

    public static double envelope(double x2, double lowIn, double lowOut, double highIn, double highOut) {
        if (x2 < lowIn || x2 > highOut) {
            return 0.0;
        }
        if (x2 < lowOut) {
            return (x2 - lowIn) / (lowOut - lowIn);
        }
        if (x2 > highIn) {
            return 1.0 - (x2 - highIn) / (highOut - highIn);
        }
        return 1.0;
    }
}

