/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableLongPredicate<E extends Throwable> {
    public static final FailableLongPredicate FALSE = t2 -> false;
    public static final FailableLongPredicate TRUE = t2 -> true;

    public static <E extends Throwable> FailableLongPredicate<E> falsePredicate() {
        return FALSE;
    }

    public static <E extends Throwable> FailableLongPredicate<E> truePredicate() {
        return TRUE;
    }

    default public FailableLongPredicate<E> and(FailableLongPredicate<E> other) {
        Objects.requireNonNull(other);
        return t2 -> this.test(t2) && other.test(t2);
    }

    default public FailableLongPredicate<E> negate() {
        return t2 -> !this.test(t2);
    }

    default public FailableLongPredicate<E> or(FailableLongPredicate<E> other) {
        Objects.requireNonNull(other);
        return t2 -> this.test(t2) || other.test(t2);
    }

    public boolean test(long var1) throws E;
}

