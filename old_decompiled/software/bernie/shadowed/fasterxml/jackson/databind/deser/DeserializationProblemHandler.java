/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public abstract class DeserializationProblemHandler {
    public static final Object NOT_HANDLED = new Object();

    public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p2, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
        return false;
    }

    public Object handleWeirdKey(DeserializationContext ctxt, Class<?> rawKeyType, String keyValue, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleWeirdStringValue(DeserializationContext ctxt, Class<?> targetType, String valueToConvert, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleWeirdNumberValue(DeserializationContext ctxt, Class<?> targetType, Number valueToConvert, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleWeirdNativeValue(DeserializationContext ctxt, JavaType targetType, Object valueToConvert, JsonParser p2) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleUnexpectedToken(DeserializationContext ctxt, Class<?> targetType, JsonToken t2, JsonParser p2, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleInstantiationProblem(DeserializationContext ctxt, Class<?> instClass, Object argument, Throwable t2) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleMissingInstantiator(DeserializationContext ctxt, Class<?> instClass, ValueInstantiator valueInsta, JsonParser p2, String msg) throws IOException {
        return this.handleMissingInstantiator(ctxt, instClass, p2, msg);
    }

    public JavaType handleUnknownTypeId(DeserializationContext ctxt, JavaType baseType, String subTypeId, TypeIdResolver idResolver, String failureMsg) throws IOException {
        return null;
    }

    public JavaType handleMissingTypeId(DeserializationContext ctxt, JavaType baseType, TypeIdResolver idResolver, String failureMsg) throws IOException {
        return null;
    }

    @Deprecated
    public Object handleMissingInstantiator(DeserializationContext ctxt, Class<?> instClass, JsonParser p2, String msg) throws IOException {
        return NOT_HANDLED;
    }
}

