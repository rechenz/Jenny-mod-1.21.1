/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleFunction<R, E extends Throwable> {
    public static final FailableDoubleFunction NOP = t2 -> null;

    public static <R, E extends Throwable> FailableDoubleFunction<R, E> nop() {
        return NOP;
    }

    public R apply(double var1) throws E;
}

