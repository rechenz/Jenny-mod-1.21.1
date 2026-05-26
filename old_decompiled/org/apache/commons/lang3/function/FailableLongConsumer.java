/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableLongConsumer<E extends Throwable> {
    public static final FailableLongConsumer NOP = t2 -> {};

    public static <E extends Throwable> FailableLongConsumer<E> nop() {
        return NOP;
    }

    public void accept(long var1) throws E;

    default public FailableLongConsumer<E> andThen(FailableLongConsumer<E> after) {
        Objects.requireNonNull(after);
        return t2 -> {
            this.accept(t2);
            after.accept(t2);
        };
    }
}

