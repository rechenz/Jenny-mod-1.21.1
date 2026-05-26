/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContainerSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;

public class CollectionSerializer
extends AsArraySerializerBase<Collection<?>> {
    private static final long serialVersionUID = 1L;

    public CollectionSerializer(JavaType elemType, boolean staticTyping, TypeSerializer vts, JsonSerializer<Object> valueSerializer) {
        super(Collection.class, elemType, staticTyping, vts, valueSerializer);
    }

    @Deprecated
    public CollectionSerializer(JavaType elemType, boolean staticTyping, TypeSerializer vts, BeanProperty property, JsonSerializer<Object> valueSerializer) {
        this(elemType, staticTyping, vts, valueSerializer);
    }

    public CollectionSerializer(CollectionSerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> valueSerializer, Boolean unwrapSingle) {
        super(src, property, vts, valueSerializer, unwrapSingle);
    }

    @Override
    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
        return new CollectionSerializer(this, this._property, vts, this._elementSerializer, this._unwrapSingle);
    }

    public CollectionSerializer withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer<?> elementSerializer, Boolean unwrapSingle) {
        return new CollectionSerializer(this, property, vts, elementSerializer, unwrapSingle);
    }

    @Override
    public boolean isEmpty(SerializerProvider prov, Collection<?> value) {
        return value.isEmpty();
    }

    @Override
    public boolean hasSingleElement(Collection<?> value) {
        return value.size() == 1;
    }

    @Override
    public final void serialize(Collection<?> value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        int len = value.size();
        if (len == 1 && (this._unwrapSingle == null && provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || this._unwrapSingle == Boolean.TRUE)) {
            this.serializeContents(value, g10, provider);
            return;
        }
        g10.writeStartArray(len);
        this.serializeContents(value, g10, provider);
        g10.writeEndArray();
    }

    @Override
    public void serializeContents(Collection<?> value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        g10.setCurrentValue(value);
        if (this._elementSerializer != null) {
            this.serializeContentsUsing(value, g10, provider, this._elementSerializer);
            return;
        }
        Iterator<?> it = value.iterator();
        if (!it.hasNext()) {
            return;
        }
        PropertySerializerMap serializers = this._dynamicSerializers;
        TypeSerializer typeSer = this._valueTypeSerializer;
        int i2 = 0;
        try {
            do {
                Object elem;
                if ((elem = it.next()) == null) {
                    provider.defaultSerializeNull(g10);
                } else {
                    Class<?> cc2 = elem.getClass();
                    JsonSerializer<Object> serializer = serializers.serializerFor(cc2);
                    if (serializer == null) {
                        serializer = this._elementType.hasGenericTypes() ? this._findAndAddDynamic(serializers, provider.constructSpecializedType(this._elementType, cc2), provider) : this._findAndAddDynamic(serializers, cc2, provider);
                        serializers = this._dynamicSerializers;
                    }
                    if (typeSer == null) {
                        serializer.serialize(elem, g10, provider);
                    } else {
                        serializer.serializeWithType(elem, g10, provider, typeSer);
                    }
                }
                ++i2;
            } while (it.hasNext());
        }
        catch (Exception e10) {
            this.wrapAndThrow(provider, (Throwable)e10, value, i2);
        }
    }

    public void serializeContentsUsing(Collection<?> value, JsonGenerator g10, SerializerProvider provider, JsonSerializer<Object> ser) throws IOException {
        Iterator<?> it = value.iterator();
        if (it.hasNext()) {
            TypeSerializer typeSer = this._valueTypeSerializer;
            int i2 = 0;
            do {
                Object elem = it.next();
                try {
                    if (elem == null) {
                        provider.defaultSerializeNull(g10);
                    } else if (typeSer == null) {
                        ser.serialize(elem, g10, provider);
                    } else {
                        ser.serializeWithType(elem, g10, provider, typeSer);
                    }
                    ++i2;
                }
                catch (Exception e10) {
                    this.wrapAndThrow(provider, (Throwable)e10, value, i2);
                }
            } while (it.hasNext());
        }
    }
}

