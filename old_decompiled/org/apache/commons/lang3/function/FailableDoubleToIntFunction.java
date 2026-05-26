/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleToIntFunction<E extends Throwable> {
    public static final FailableDoubleToIntFunction NOP = t2 -> 0;

    public static <E extends Throwable> FailableDoubleToIntFunction<E> nop() {
        return NOP;
    }

    public int applyAsInt(double var1) throws E;
}

