/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.math;

import org.apache.commons.lang3.Validate;

public class IEEE754rUtils {
    public static double min(double ... array) {
        Validate.notNull(array, "The Array must not be null", new Object[0]);
        Validate.isTrue(array.length != 0, "Array cannot be empty.", new Object[0]);
        double min = array[0];
        for (int i2 = 1; i2 < array.length; ++i2) {
            min = IEEE754rUtils.min(array[i2], min);
        }
        return min;
    }

    public static float min(float ... array) {
        Validate.notNull(array, "The Array must not be null", new Object[0]);
        Validate.isTrue(array.length != 0, "Array cannot be empty.", new Object[0]);
        float min = array[0];
        for (int i2 = 1; i2 < array.length; ++i2) {
            min = IEEE754rUtils.min(array[i2], min);
        }
        return min;
    }

    public static double min(double a10, double b10, double c10) {
        return IEEE754rUtils.min(IEEE754rUtils.min(a10, b10), c10);
    }

    public static double min(double a10, double b10) {
        if (Double.isNaN(a10)) {
            return b10;
        }
        if (Double.isNaN(b10)) {
            return a10;
        }
        return Math.min(a10, b10);
    }

    public static float min(float a10, float b10, float c10) {
        return IEEE754rUtils.min(IEEE754rUtils.min(a10, b10), c10);
    }

    public static float min(float a10, float b10) {
        if (Float.isNaN(a10)) {
            return b10;
        }
        if (Float.isNaN(b10)) {
            return a10;
        }
        return Math.min(a10, b10);
    }

    public static double max(double ... array) {
        Validate.notNull(array, "The Array must not be null", new Object[0]);
        Validate.isTrue(array.length != 0, "Array cannot be empty.", new Object[0]);
        double max = array[0];
        for (int j2 = 1; j2 < array.length; ++j2) {
            max = IEEE754rUtils.max(array[j2], max);
        }
        return max;
    }

    public static float max(float ... array) {
        Validate.notNull(array, "The Array must not be null", new Object[0]);
        Validate.isTrue(array.length != 0, "Array cannot be empty.", new Object[0]);
        float max = array[0];
        for (int j2 = 1; j2 < array.length; ++j2) {
            max = IEEE754rUtils.max(array[j2], max);
        }
        return max;
    }

    public static double max(double a10, double b10, double c10) {
        return IEEE754rUtils.max(IEEE754rUtils.max(a10, b10), c10);
    }

    public static double max(double a10, double b10) {
        if (Double.isNaN(a10)) {
            return b10;
        }
        if (Double.isNaN(b10)) {
            return a10;
        }
        return Math.max(a10, b10);
    }

    public static float max(float a10, float b10, float c10) {
        return IEEE754rUtils.max(IEEE754rUtils.max(a10, b10), c10);
    }

    public static float max(float a10, float b10) {
        if (Float.isNaN(a10)) {
            return b10;
        }
        if (Float.isNaN(b10)) {
            return a10;
        }
        return Math.max(a10, b10);
    }
}

