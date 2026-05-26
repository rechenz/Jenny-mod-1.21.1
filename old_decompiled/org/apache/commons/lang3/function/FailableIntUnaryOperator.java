/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableIntUnaryOperator<E extends Throwable> {
    public static final FailableIntUnaryOperator NOP = t2 -> 0;

    public static <E extends Throwable> FailableIntUnaryOperator<E> identity() {
        return t2 -> t2;
    }

    public static <E extends Throwable> FailableIntUnaryOperator<E> nop() {
        return NOP;
    }

    default public FailableIntUnaryOperator<E> andThen(FailableIntUnaryOperator<E> after) {
        Objects.requireNonNull(after);
        return t2 -> after.applyAsInt(this.applyAsInt(t2));
    }

    public int applyAsInt(int var1) throws E;

    default public FailableIntUnaryOperator<E> compose(FailableIntUnaryOperator<E> before) {
        Objects.requireNonNull(before);
        return v2 -> this.applyAsInt(before.applyAsInt(v2));
    }
}

