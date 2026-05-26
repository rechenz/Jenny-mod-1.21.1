/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableLongUnaryOperator<E extends Throwable> {
    public static final FailableLongUnaryOperator NOP = t2 -> 0L;

    public static <E extends Throwable> FailableLongUnaryOperator<E> identity() {
        return t2 -> t2;
    }

    public static <E extends Throwable> FailableLongUnaryOperator<E> nop() {
        return NOP;
    }

    default public FailableLongUnaryOperator<E> andThen(FailableLongUnaryOperator<E> after) {
        Objects.requireNonNull(after);
        return t2 -> after.applyAsLong(this.applyAsLong(t2));
    }

    public long applyAsLong(long var1) throws E;

    default public FailableLongUnaryOperator<E> compose(FailableLongUnaryOperator<E> before) {
        Objects.requireNonNull(before);
        return v2 -> this.applyAsLong(before.applyAsLong(v2));
    }
}

