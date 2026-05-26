/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import java.lang.annotation.Annotation;

public interface Annotations {
    public <A extends Annotation> A get(Class<A> var1);

    public boolean has(Class<?> var1);

    public boolean hasOneOf(Class<? extends Annotation>[] var1);

    public int size();
}

