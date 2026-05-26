/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleToLongFunction<E extends Throwable> {
    public static final FailableDoubleToLongFunction NOP = t2 -> 0;

    public static <E extends Throwable> FailableDoubleToLongFunction<E> nop() {
        return NOP;
    }

    public int applyAsLong(double var1) throws E;
}

