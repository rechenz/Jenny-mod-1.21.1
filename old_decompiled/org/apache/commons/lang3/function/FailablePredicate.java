/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailablePredicate<T, E extends Throwable> {
    public static final FailablePredicate FALSE = t2 -> false;
    public static final FailablePredicate TRUE = t2 -> true;

    public static <T, E extends Throwable> FailablePredicate<T, E> falsePredicate() {
        return FALSE;
    }

    public static <T, E extends Throwable> FailablePredicate<T, E> truePredicate() {
        return TRUE;
    }

    default public FailablePredicate<T, E> and(FailablePredicate<? super T, E> other) {
        Objects.requireNonNull(other);
        return t2 -> this.test(t2) && other.test(t2);
    }

    default public FailablePredicate<T, E> negate() {
        return t2 -> !this.test(t2);
    }

    default public FailablePredicate<T, E> or(FailablePredicate<? super T, E> other) {
        Objects.requireNonNull(other);
        return t2 -> this.test(t2) || other.test(t2);
    }

    public boolean test(T var1) throws E;
}

