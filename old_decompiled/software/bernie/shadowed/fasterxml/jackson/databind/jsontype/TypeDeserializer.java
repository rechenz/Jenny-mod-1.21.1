/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsontype;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public abstract class TypeDeserializer {
    public abstract TypeDeserializer forProperty(BeanProperty var1);

    public abstract JsonTypeInfo.As getTypeInclusion();

    public abstract String getPropertyName();

    public abstract TypeIdResolver getTypeIdResolver();

    public abstract Class<?> getDefaultImpl();

    public abstract Object deserializeTypedFromObject(JsonParser var1, DeserializationContext var2) throws IOException;

    public abstract Object deserializeTypedFromArray(JsonParser var1, DeserializationContext var2) throws IOException;

    public abstract Object deserializeTypedFromScalar(JsonParser var1, DeserializationContext var2) throws IOException;

    public abstract Object deserializeTypedFromAny(JsonParser var1, DeserializationContext var2) throws IOException;

    public static Object deserializeIfNatural(JsonParser p2, DeserializationContext ctxt, JavaType baseType) throws IOException {
        return TypeDeserializer.deserializeIfNatural(p2, ctxt, baseType.getRawClass());
    }

    public static Object deserializeIfNatural(JsonParser p2, DeserializationContext ctxt, Class<?> base) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == null) {
            return null;
        }
        switch (t2) {
            case VALUE_STRING: {
                if (!base.isAssignableFrom(String.class)) break;
                return p2.getText();
            }
            case VALUE_NUMBER_INT: {
                if (!base.isAssignableFrom(Integer.class)) break;
                return p2.getIntValue();
            }
            case VALUE_NUMBER_FLOAT: {
                if (!base.isAssignableFrom(Double.class)) break;
                return p2.getDoubleValue();
            }
            case VALUE_TRUE: {
                if (!base.isAssignableFrom(Boolean.class)) break;
                return Boolean.TRUE;
            }
            case VALUE_FALSE: {
                if (!base.isAssignableFrom(Boolean.class)) break;
                return Boolean.FALSE;
            }
        }
        return null;
    }
}

