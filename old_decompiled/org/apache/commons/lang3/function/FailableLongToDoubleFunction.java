/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongToDoubleFunction<E extends Throwable> {
    public static final FailableLongToDoubleFunction NOP = t2 -> 0.0;

    public static <E extends Throwable> FailableLongToDoubleFunction<E> nop() {
        return NOP;
    }

    public double applyAsDouble(long var1) throws E;
}

