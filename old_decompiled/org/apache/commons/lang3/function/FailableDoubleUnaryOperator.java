/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableDoubleUnaryOperator<E extends Throwable> {
    public static final FailableDoubleUnaryOperator NOP = t2 -> 0.0;

    public static <E extends Throwable> FailableDoubleUnaryOperator<E> identity() {
        return t2 -> t2;
    }

    public static <E extends Throwable> FailableDoubleUnaryOperator<E> nop() {
        return NOP;
    }

    default public FailableDoubleUnaryOperator<E> andThen(FailableDoubleUnaryOperator<E> after) {
        Objects.requireNonNull(after);
        return t2 -> after.applyAsDouble(this.applyAsDouble(t2));
    }

    public double applyAsDouble(double var1) throws E;

    default public FailableDoubleUnaryOperator<E> compose(FailableDoubleUnaryOperator<E> before) {
        Objects.requireNonNull(before);
        return v2 -> this.applyAsDouble(before.applyAsDouble(v2));
    }
}

