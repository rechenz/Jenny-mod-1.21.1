/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContainerSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;

@JacksonStdImpl
public final class IndexedListSerializer
extends AsArraySerializerBase<List<?>> {
    private static final long serialVersionUID = 1L;

    public IndexedListSerializer(JavaType elemType, boolean staticTyping, TypeSerializer vts, JsonSerializer<Object> valueSerializer) {
        super(List.class, elemType, staticTyping, vts, valueSerializer);
    }

    public IndexedListSerializer(IndexedListSerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> valueSerializer, Boolean unwrapSingle) {
        super(src, property, vts, valueSerializer, unwrapSingle);
    }

    public IndexedListSerializer withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer<?> elementSerializer, Boolean unwrapSingle) {
        return new IndexedListSerializer(this, property, vts, elementSerializer, unwrapSingle);
    }

    @Override
    public boolean isEmpty(SerializerProvider prov, List<?> value) {
        return value.isEmpty();
    }

    @Override
    public boolean hasSingleElement(List<?> value) {
        return value.size() == 1;
    }

    @Override
    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
        return new IndexedListSerializer(this, this._property, vts, this._elementSerializer, this._unwrapSingle);
    }

    @Override
    public final void serialize(List<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        int len = value.size();
        if (len == 1 && (this._unwrapSingle == null && provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || this._unwrapSingle == Boolean.TRUE)) {
            this.serializeContents(value, gen, provider);
            return;
        }
        gen.writeStartArray(len);
        this.serializeContents(value, gen, provider);
        gen.writeEndArray();
    }

    @Override
    public void serializeContents(List<?> value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        int i2;
        if (this._elementSerializer != null) {
            this.serializeContentsUsing(value, g10, provider, this._elementSerializer);
            return;
        }
        if (this._valueTypeSerializer != null) {
            this.serializeTypedContents(value, g10, provider);
            return;
        }
        int len = value.size();
        if (len == 0) {
            return;
        }
        try {
            PropertySerializerMap serializers = this._dynamicSerializers;
            for (i2 = 0; i2 < len; ++i2) {
                Object elem = value.get(i2);
                if (elem == null) {
                    provider.defaultSerializeNull(g10);
                    continue;
                }
                Class<?> cc2 = elem.getClass();
                JsonSerializer<Object> serializer = serializers.serializerFor(cc2);
                if (serializer == null) {
                    serializer = this._elementType.hasGenericTypes() ? this._findAndAddDynamic(serializers, provider.constructSpecializedType(this._elementType, cc2), provider) : this._findAndAddDynamic(serializers, cc2, provider);
                    serializers = this._dynamicSerializers;
                }
                serializer.serialize(elem, g10, provider);
            }
        }
        catch (Exception e10) {
            this.wrapAndThrow(provider, (Throwable)e10, value, i2);
        }
    }

    public void serializeContentsUsing(List<?> value, JsonGenerator jgen, SerializerProvider provider, JsonSerializer<Object> ser) throws IOException {
        int len = value.size();
        if (len == 0) {
            return;
        }
        TypeSerializer typeSer = this._valueTypeSerializer;
        for (int i2 = 0; i2 < len; ++i2) {
            Object elem = value.get(i2);
            try {
                if (elem == null) {
                    provider.defaultSerializeNull(jgen);
                    continue;
                }
                if (typeSer == null) {
                    ser.serialize(elem, jgen, provider);
                    continue;
                }
                ser.serializeWithType(elem, jgen, provider, typeSer);
                continue;
            }
            catch (Exception e10) {
                this.wrapAndThrow(provider, (Throwable)e10, value, i2);
            }
        }
    }

    public void serializeTypedContents(List<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        int i2;
        int len = value.size();
        if (len == 0) {
            return;
        }
        try {
            TypeSerializer typeSer = this._valueTypeSerializer;
            PropertySerializerMap serializers = this._dynamicSerializers;
            for (i2 = 0; i2 < len; ++i2) {
                Object elem = value.get(i2);
                if (elem == null) {
                    provider.defaultSerializeNull(jgen);
                    continue;
                }
                Class<?> cc2 = elem.getClass();
                JsonSerializer<Object> serializer = serializers.serializerFor(cc2);
                if (serializer == null) {
                    serializer = this._elementType.hasGenericTypes() ? this._findAndAddDynamic(serializers, provider.constructSpecializedType(this._elementType, cc2), provider) : this._findAndAddDynamic(serializers, cc2, provider);
                    serializers = this._dynamicSerializers;
                }
                serializer.serializeWithType(elem, jgen, provider, typeSer);
            }
        }
        catch (Exception e10) {
            this.wrapAndThrow(provider, (Throwable)e10, value, i2);
        }
    }
}

