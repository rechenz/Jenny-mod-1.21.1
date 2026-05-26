/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;

public abstract class TypeSerializerBase
extends TypeSerializer {
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    protected TypeSerializerBase(TypeIdResolver idRes, BeanProperty property) {
        this._idResolver = idRes;
        this._property = property;
    }

    @Override
    public abstract JsonTypeInfo.As getTypeInclusion();

    @Override
    public String getPropertyName() {
        return null;
    }

    @Override
    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    @Override
    public WritableTypeId writeTypePrefix(JsonGenerator g10, WritableTypeId idMetadata) throws IOException {
        this._generateTypeId(idMetadata);
        return g10.writeTypePrefix(idMetadata);
    }

    @Override
    public WritableTypeId writeTypeSuffix(JsonGenerator g10, WritableTypeId idMetadata) throws IOException {
        return g10.writeTypeSuffix(idMetadata);
    }

    protected void _generateTypeId(WritableTypeId idMetadata) {
        Object id = idMetadata.id;
        if (id == null) {
            Object value = idMetadata.forValue;
            Class<?> typeForId = idMetadata.forValueType;
            id = typeForId == null ? this.idFromValue(value) : this.idFromValueAndType(value, typeForId);
            idMetadata.id = id;
        }
    }

    protected String idFromValue(Object value) {
        String id = this._idResolver.idFromValue(value);
        if (id == null) {
            this.handleMissingId(value);
        }
        return id;
    }

    protected String idFromValueAndType(Object value, Class<?> type) {
        String id = this._idResolver.idFromValueAndType(value, type);
        if (id == null) {
            this.handleMissingId(value);
        }
        return id;
    }

    protected void handleMissingId(Object value) {
    }
}

