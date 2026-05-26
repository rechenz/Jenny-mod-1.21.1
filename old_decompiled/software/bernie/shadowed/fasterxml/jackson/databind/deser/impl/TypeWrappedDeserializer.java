/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public final class TypeWrappedDeserializer
extends JsonDeserializer<Object>
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final TypeDeserializer _typeDeserializer;
    protected final JsonDeserializer<Object> _deserializer;

    public TypeWrappedDeserializer(TypeDeserializer typeDeser, JsonDeserializer<?> deser) {
        this._typeDeserializer = typeDeser;
        this._deserializer = deser;
    }

    @Override
    public Class<?> handledType() {
        return this._deserializer.handledType();
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return this._deserializer.supportsUpdate(config);
    }

    @Override
    public JsonDeserializer<?> getDelegatee() {
        return this._deserializer.getDelegatee();
    }

    @Override
    public Collection<Object> getKnownPropertyNames() {
        return this._deserializer.getKnownPropertyNames();
    }

    @Override
    public Object getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return this._deserializer.getNullValue(ctxt);
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return this._deserializer.getEmptyValue(ctxt);
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        return this._deserializer.deserializeWithType(p2, ctxt, this._typeDeserializer);
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt, Object intoValue) throws IOException {
        return this._deserializer.deserialize(p2, ctxt, intoValue);
    }
}

