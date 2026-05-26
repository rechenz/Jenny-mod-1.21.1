/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableFunction<T, R, E extends Throwable> {
    public static final FailableFunction NOP = t2 -> null;

    public static <T, E extends Throwable> FailableFunction<T, T, E> identity() {
        return t2 -> t2;
    }

    public static <T, R, E extends Throwable> FailableFunction<T, R, E> nop() {
        return NOP;
    }

    default public <V> FailableFunction<T, V, E> andThen(FailableFunction<? super R, ? extends V, E> after) {
        Objects.requireNonNull(after);
        return t2 -> after.apply((R)this.apply(t2));
    }

    public R apply(T var1) throws E;

    default public <V> FailableFunction<V, R, E> compose(FailableFunction<? super V, ? extends T, E> before) {
        Objects.requireNonNull(before);
        return v2 -> this.apply(before.apply((Object)v2));
    }
}

