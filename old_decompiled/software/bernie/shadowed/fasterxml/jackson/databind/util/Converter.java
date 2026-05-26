/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;

public interface Converter<IN, OUT> {
    public OUT convert(IN var1);

    public JavaType getInputType(TypeFactory var1);

    public JavaType getOutputType(TypeFactory var1);

    public static abstract class None
    implements Converter<Object, Object> {
    }
}

