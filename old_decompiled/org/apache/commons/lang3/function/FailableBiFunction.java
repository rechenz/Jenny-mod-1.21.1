/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;
import org.apache.commons.lang3.function.FailableFunction;

@FunctionalInterface
public interface FailableBiFunction<T, U, R, E extends Throwable> {
    public static final FailableBiFunction NOP = (t2, u2) -> null;

    public static <T, U, R, E extends Throwable> FailableBiFunction<T, U, R, E> nop() {
        return NOP;
    }

    default public <V> FailableBiFunction<T, U, V, E> andThen(FailableFunction<? super R, ? extends V, E> after) {
        Objects.requireNonNull(after);
        return (t2, u2) -> after.apply((R)this.apply(t2, u2));
    }

    public R apply(T var1, U var2) throws E;
}

