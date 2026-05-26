/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.util.Iterator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContainerSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;

@JacksonStdImpl
public class IteratorSerializer
extends AsArraySerializerBase<Iterator<?>> {
    public IteratorSerializer(JavaType elemType, boolean staticTyping, TypeSerializer vts) {
        super(Iterator.class, elemType, staticTyping, vts, null);
    }

    public IteratorSerializer(IteratorSerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> valueSerializer, Boolean unwrapSingle) {
        super(src, property, vts, valueSerializer, unwrapSingle);
    }

    @Override
    public boolean isEmpty(SerializerProvider prov, Iterator<?> value) {
        return !value.hasNext();
    }

    @Override
    public boolean hasSingleElement(Iterator<?> value) {
        return false;
    }

    @Override
    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
        return new IteratorSerializer(this, this._property, vts, this._elementSerializer, this._unwrapSingle);
    }

    public IteratorSerializer withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer<?> elementSerializer, Boolean unwrapSingle) {
        return new IteratorSerializer(this, property, vts, elementSerializer, unwrapSingle);
    }

    @Override
    public final void serialize(Iterator<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        this.serializeContents(value, gen, provider);
        gen.writeEndArray();
    }

    @Override
    public void serializeContents(Iterator<?> value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        if (!value.hasNext()) {
            return;
        }
        JsonSerializer serializer = this._elementSerializer;
        if (serializer == null) {
            this._serializeDynamicContents(value, g10, provider);
            return;
        }
        TypeSerializer typeSer = this._valueTypeSerializer;
        do {
            Object elem;
            if ((elem = value.next()) == null) {
                provider.defaultSerializeNull(g10);
                continue;
            }
            if (typeSer == null) {
                serializer.serialize(elem, g10, provider);
                continue;
            }
            serializer.serializeWithType(elem, g10, provider, typeSer);
        } while (value.hasNext());
    }

    protected void _serializeDynamicContents(Iterator<?> value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        JsonSerializer<Object> serializer = this._elementSerializer;
        TypeSerializer typeSer = this._valueTypeSerializer;
        PropertySerializerMap serializers = this._dynamicSerializers;
        do {
            Object elem;
            if ((elem = value.next()) == null) {
                provider.defaultSerializeNull(g10);
                continue;
            }
            Class<?> cc2 = elem.getClass();
            serializers.serializerFor(cc2);
            if (serializer == null) {
                serializer = this._elementType.hasGenericTypes() ? this._findAndAddDynamic(serializers, provider.constructSpecializedType(this._elementType, cc2), provider) : this._findAndAddDynamic(serializers, cc2, provider);
                serializers = this._dynamicSerializers;
            }
            if (typeSer == null) {
                serializer.serialize(elem, g10, provider);
                continue;
            }
            serializer.serializeWithType(elem, g10, provider, typeSer);
        } while (value.hasNext());
    }
}

