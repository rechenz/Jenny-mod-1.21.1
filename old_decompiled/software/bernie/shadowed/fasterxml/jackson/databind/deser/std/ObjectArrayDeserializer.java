/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.lang.reflect.Array;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.AccessPattern;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ObjectBuffer;

@JacksonStdImpl
public class ObjectArrayDeserializer
extends ContainerDeserializerBase<Object[]>
implements ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected static final Object[] NO_OBJECTS = new Object[0];
    protected final boolean _untyped;
    protected final Class<?> _elementClass;
    protected JsonDeserializer<Object> _elementDeserializer;
    protected final TypeDeserializer _elementTypeDeserializer;

    public ObjectArrayDeserializer(JavaType arrayType, JsonDeserializer<Object> elemDeser, TypeDeserializer elemTypeDeser) {
        super(arrayType, null, null);
        this._elementClass = arrayType.getContentType().getRawClass();
        this._untyped = this._elementClass == Object.class;
        this._elementDeserializer = elemDeser;
        this._elementTypeDeserializer = elemTypeDeser;
    }

    protected ObjectArrayDeserializer(ObjectArrayDeserializer base, JsonDeserializer<Object> elemDeser, TypeDeserializer elemTypeDeser, NullValueProvider nuller, Boolean unwrapSingle) {
        super(base, nuller, unwrapSingle);
        this._elementClass = base._elementClass;
        this._untyped = base._untyped;
        this._elementDeserializer = elemDeser;
        this._elementTypeDeserializer = elemTypeDeser;
    }

    public ObjectArrayDeserializer withDeserializer(TypeDeserializer elemTypeDeser, JsonDeserializer<?> elemDeser) {
        return this.withResolved(elemTypeDeser, elemDeser, this._nullProvider, this._unwrapSingle);
    }

    public ObjectArrayDeserializer withResolved(TypeDeserializer elemTypeDeser, JsonDeserializer<?> elemDeser, NullValueProvider nuller, Boolean unwrapSingle) {
        if (unwrapSingle == this._unwrapSingle && nuller == this._nullProvider && elemDeser == this._elementDeserializer && elemTypeDeser == this._elementTypeDeserializer) {
            return this;
        }
        return new ObjectArrayDeserializer(this, elemDeser, elemTypeDeser, nuller, unwrapSingle);
    }

    @Override
    public boolean isCachable() {
        return this._elementDeserializer == null && this._elementTypeDeserializer == null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<Object> valueDeser = this._elementDeserializer;
        Boolean unwrapSingle = this.findFormatFeature(ctxt, property, this._containerType.getRawClass(), JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        valueDeser = this.findConvertingContentDeserializer(ctxt, property, valueDeser);
        JavaType vt = this._containerType.getContentType();
        valueDeser = valueDeser == null ? ctxt.findContextualValueDeserializer(vt, property) : ctxt.handleSecondaryContextualization(valueDeser, property, vt);
        TypeDeserializer elemTypeDeser = this._elementTypeDeserializer;
        if (elemTypeDeser != null) {
            elemTypeDeser = elemTypeDeser.forProperty(property);
        }
        NullValueProvider nuller = this.findContentNullProvider(ctxt, property, valueDeser);
        return this.withResolved(elemTypeDeser, valueDeser, nuller, unwrapSingle);
    }

    @Override
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._elementDeserializer;
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return NO_OBJECTS;
    }

    @Override
    public Object[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            return this.handleNonArray(p2, ctxt);
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] chunk = buffer.resetAndStart();
        int ix = 0;
        TypeDeserializer typeDeser = this._elementTypeDeserializer;
        try {
            JsonToken t2;
            while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                Object value;
                if (t2 == JsonToken.VALUE_NULL) {
                    if (this._skipNullValues) continue;
                    value = this._nullProvider.getNullValue(ctxt);
                } else {
                    value = typeDeser == null ? this._elementDeserializer.deserialize(p2, ctxt) : this._elementDeserializer.deserializeWithType(p2, ctxt, typeDeser);
                }
                if (ix >= chunk.length) {
                    chunk = buffer.appendCompletedChunk(chunk);
                    ix = 0;
                }
                chunk[ix++] = value;
            }
        }
        catch (Exception e10) {
            throw JsonMappingException.wrapWithPath((Throwable)e10, (Object)chunk, buffer.bufferedSize() + ix);
        }
        Object[] result = this._untyped ? buffer.completeAndClearBuffer(chunk, ix) : buffer.completeAndClearBuffer(chunk, ix, this._elementClass);
        ctxt.returnObjectBuffer(buffer);
        return result;
    }

    public Object[] deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return (Object[])typeDeserializer.deserializeTypedFromArray(p2, ctxt);
    }

    @Override
    public Object[] deserialize(JsonParser p2, DeserializationContext ctxt, Object[] intoValue) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            Object[] arr = this.handleNonArray(p2, ctxt);
            if (arr == null) {
                return intoValue;
            }
            int offset = intoValue.length;
            Object[] result = new Object[offset + arr.length];
            System.arraycopy(intoValue, 0, result, 0, offset);
            System.arraycopy(arr, 0, result, offset, arr.length);
            return result;
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        int ix = intoValue.length;
        Object[] chunk = buffer.resetAndStart(intoValue, ix);
        TypeDeserializer typeDeser = this._elementTypeDeserializer;
        try {
            JsonToken t2;
            while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                Object value;
                if (t2 == JsonToken.VALUE_NULL) {
                    if (this._skipNullValues) continue;
                    value = this._nullProvider.getNullValue(ctxt);
                } else {
                    value = typeDeser == null ? this._elementDeserializer.deserialize(p2, ctxt) : this._elementDeserializer.deserializeWithType(p2, ctxt, typeDeser);
                }
                if (ix >= chunk.length) {
                    chunk = buffer.appendCompletedChunk(chunk);
                    ix = 0;
                }
                chunk[ix++] = value;
            }
        }
        catch (Exception e10) {
            throw JsonMappingException.wrapWithPath((Throwable)e10, (Object)chunk, buffer.bufferedSize() + ix);
        }
        Object[] result = this._untyped ? buffer.completeAndClearBuffer(chunk, ix) : buffer.completeAndClearBuffer(chunk, ix, this._elementClass);
        ctxt.returnObjectBuffer(buffer);
        return result;
    }

    protected Byte[] deserializeFromBase64(JsonParser p2, DeserializationContext ctxt) throws IOException {
        byte[] b10 = p2.getBinaryValue(ctxt.getBase64Variant());
        Byte[] result = new Byte[b10.length];
        int len = b10.length;
        for (int i2 = 0; i2 < len; ++i2) {
            result[i2] = b10[i2];
        }
        return result;
    }

    protected Object[] handleNonArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object value;
        boolean canWrap;
        String str;
        if (p2.hasToken(JsonToken.VALUE_STRING) && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (str = p2.getText()).length() == 0) {
            return null;
        }
        boolean bl2 = canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (!canWrap) {
            JsonToken t2 = p2.getCurrentToken();
            if (t2 == JsonToken.VALUE_STRING && this._elementClass == Byte.class) {
                return this.deserializeFromBase64(p2, ctxt);
            }
            return (Object[])ctxt.handleUnexpectedToken(this._containerType.getRawClass(), p2);
        }
        JsonToken t3 = p2.getCurrentToken();
        if (t3 == JsonToken.VALUE_NULL) {
            if (this._skipNullValues) {
                return NO_OBJECTS;
            }
            value = this._nullProvider.getNullValue(ctxt);
        } else {
            value = this._elementTypeDeserializer == null ? this._elementDeserializer.deserialize(p2, ctxt) : this._elementDeserializer.deserializeWithType(p2, ctxt, this._elementTypeDeserializer);
        }
        Object[] result = this._untyped ? new Object[1] : (Object[])Array.newInstance(this._elementClass, 1);
        result[0] = value;
        return result;
    }
}

