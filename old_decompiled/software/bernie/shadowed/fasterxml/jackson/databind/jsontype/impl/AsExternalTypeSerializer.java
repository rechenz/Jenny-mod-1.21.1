/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.TypeSerializerBase;

public class AsExternalTypeSerializer
extends TypeSerializerBase {
    protected final String _typePropertyName;

    public AsExternalTypeSerializer(TypeIdResolver idRes, BeanProperty property, String propName) {
        super(idRes, property);
        this._typePropertyName = propName;
    }

    @Override
    public AsExternalTypeSerializer forProperty(BeanProperty prop) {
        return this._property == prop ? this : new AsExternalTypeSerializer(this._idResolver, prop, this._typePropertyName);
    }

    @Override
    public String getPropertyName() {
        return this._typePropertyName;
    }

    @Override
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.EXTERNAL_PROPERTY;
    }

    protected final void _writeScalarPrefix(Object value, JsonGenerator g10) throws IOException {
    }

    protected final void _writeObjectPrefix(Object value, JsonGenerator g10) throws IOException {
        g10.writeStartObject();
    }

    protected final void _writeArrayPrefix(Object value, JsonGenerator g10) throws IOException {
        g10.writeStartArray();
    }

    protected final void _writeScalarSuffix(Object value, JsonGenerator g10, String typeId) throws IOException {
        if (typeId != null) {
            g10.writeStringField(this._typePropertyName, typeId);
        }
    }

    protected final void _writeObjectSuffix(Object value, JsonGenerator g10, String typeId) throws IOException {
        g10.writeEndObject();
        if (typeId != null) {
            g10.writeStringField(this._typePropertyName, typeId);
        }
    }

    protected final void _writeArraySuffix(Object value, JsonGenerator g10, String typeId) throws IOException {
        g10.writeEndArray();
        if (typeId != null) {
            g10.writeStringField(this._typePropertyName, typeId);
        }
    }
}

