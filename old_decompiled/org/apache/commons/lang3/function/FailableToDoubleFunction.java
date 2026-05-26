/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToDoubleFunction<T, E extends Throwable> {
    public static final FailableToDoubleFunction NOP = t2 -> 0.0;

    public static <T, E extends Throwable> FailableToDoubleFunction<T, E> nop() {
        return NOP;
    }

    public double applyAsDouble(T var1) throws E;
}

