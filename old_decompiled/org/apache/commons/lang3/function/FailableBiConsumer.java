/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableBiConsumer<T, U, E extends Throwable> {
    public static final FailableBiConsumer NOP = (t2, u2) -> {};

    public static <T, U, E extends Throwable> FailableBiConsumer<T, U, E> nop() {
        return NOP;
    }

    public void accept(T var1, U var2) throws E;

    default public FailableBiConsumer<T, U, E> andThen(FailableBiConsumer<? super T, ? super U, E> after) {
        Objects.requireNonNull(after);
        return (t2, u2) -> {
            this.accept(t2, u2);
            after.accept(t2, u2);
        };
    }
}

