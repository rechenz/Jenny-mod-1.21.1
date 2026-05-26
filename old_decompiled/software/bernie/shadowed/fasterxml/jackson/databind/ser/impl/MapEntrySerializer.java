/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContainerSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContextualSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ArrayBuilders;
import software.bernie.shadowed.fasterxml.jackson.databind.util.BeanUtil;

@JacksonStdImpl
public class MapEntrySerializer
extends ContainerSerializer<Map.Entry<?, ?>>
implements ContextualSerializer {
    public static final Object MARKER_FOR_EMPTY = JsonInclude.Include.NON_EMPTY;
    protected final BeanProperty _property;
    protected final boolean _valueTypeIsStatic;
    protected final JavaType _entryType;
    protected final JavaType _keyType;
    protected final JavaType _valueType;
    protected JsonSerializer<Object> _keySerializer;
    protected JsonSerializer<Object> _valueSerializer;
    protected final TypeSerializer _valueTypeSerializer;
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final Object _suppressableValue;
    protected final boolean _suppressNulls;

    public MapEntrySerializer(JavaType type, JavaType keyType, JavaType valueType, boolean staticTyping, TypeSerializer vts, BeanProperty property) {
        super(type);
        this._entryType = type;
        this._keyType = keyType;
        this._valueType = valueType;
        this._valueTypeIsStatic = staticTyping;
        this._valueTypeSerializer = vts;
        this._property = property;
        this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
        this._suppressableValue = null;
        this._suppressNulls = false;
    }

    @Deprecated
    protected MapEntrySerializer(MapEntrySerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> keySer, JsonSerializer<?> valueSer) {
        this(src, property, vts, keySer, valueSer, src._suppressableValue, src._suppressNulls);
    }

    protected MapEntrySerializer(MapEntrySerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> keySer, JsonSerializer<?> valueSer, Object suppressableValue, boolean suppressNulls) {
        super(Map.class, false);
        this._entryType = src._entryType;
        this._keyType = src._keyType;
        this._valueType = src._valueType;
        this._valueTypeIsStatic = src._valueTypeIsStatic;
        this._valueTypeSerializer = src._valueTypeSerializer;
        this._keySerializer = keySer;
        this._valueSerializer = valueSer;
        this._dynamicValueSerializers = src._dynamicValueSerializers;
        this._property = src._property;
        this._suppressableValue = suppressableValue;
        this._suppressNulls = suppressNulls;
    }

    @Override
    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
        return new MapEntrySerializer(this, this._property, vts, this._keySerializer, this._valueSerializer, this._suppressableValue, this._suppressNulls);
    }

    public MapEntrySerializer withResolved(BeanProperty property, JsonSerializer<?> keySerializer, JsonSerializer<?> valueSerializer, Object suppressableValue, boolean suppressNulls) {
        return new MapEntrySerializer(this, property, this._valueTypeSerializer, keySerializer, valueSerializer, suppressableValue, suppressNulls);
    }

    public MapEntrySerializer withContentInclusion(Object suppressableValue, boolean suppressNulls) {
        if (this._suppressableValue == suppressableValue && this._suppressNulls == suppressNulls) {
            return this;
        }
        return new MapEntrySerializer(this, this._property, this._valueTypeSerializer, this._keySerializer, this._valueSerializer, suppressableValue, suppressNulls);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        JsonInclude.Include incl;
        JsonInclude.Value inclV;
        AnnotatedMember propertyAcc;
        JsonSerializer<Object> ser = null;
        JsonSerializer<Object> keySer = null;
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        AnnotatedMember annotatedMember = propertyAcc = property == null ? null : property.getMember();
        if (propertyAcc != null && intr != null) {
            Object serDef = intr.findKeySerializer(propertyAcc);
            if (serDef != null) {
                keySer = provider.serializerInstance(propertyAcc, serDef);
            }
            if ((serDef = intr.findContentSerializer(propertyAcc)) != null) {
                ser = provider.serializerInstance(propertyAcc, serDef);
            }
        }
        if (ser == null) {
            ser = this._valueSerializer;
        }
        if ((ser = this.findContextualConvertingSerializer(provider, property, ser)) == null && this._valueTypeIsStatic && !this._valueType.isJavaLangObject()) {
            ser = provider.findValueSerializer(this._valueType, property);
        }
        if (keySer == null) {
            keySer = this._keySerializer;
        }
        keySer = keySer == null ? provider.findKeySerializer(this._keyType, property) : provider.handleSecondaryContextualization(keySer, property);
        Object valueToSuppress = this._suppressableValue;
        boolean suppressNulls = this._suppressNulls;
        if (property != null && (inclV = property.findPropertyInclusion(provider.getConfig(), null)) != null && (incl = inclV.getContentInclusion()) != JsonInclude.Include.USE_DEFAULTS) {
            switch (incl) {
                case NON_DEFAULT: {
                    valueToSuppress = BeanUtil.getDefaultValue(this._valueType);
                    suppressNulls = true;
                    if (valueToSuppress == null || !valueToSuppress.getClass().isArray()) break;
                    valueToSuppress = ArrayBuilders.getArrayComparator(valueToSuppress);
                    break;
                }
                case NON_ABSENT: {
                    suppressNulls = true;
                    valueToSuppress = this._valueType.isReferenceType() ? MARKER_FOR_EMPTY : null;
                    break;
                }
                case NON_EMPTY: {
                    suppressNulls = true;
                    valueToSuppress = MARKER_FOR_EMPTY;
                    break;
                }
                case CUSTOM: {
                    valueToSuppress = provider.includeFilterInstance(null, inclV.getContentFilter());
                    if (valueToSuppress == null) {
                        suppressNulls = true;
                        break;
                    }
                    suppressNulls = provider.includeFilterSuppressNulls(valueToSuppress);
                    break;
                }
                case NON_NULL: {
                    valueToSuppress = null;
                    suppressNulls = true;
                    break;
                }
                default: {
                    valueToSuppress = null;
                    suppressNulls = false;
                }
            }
        }
        MapEntrySerializer mser = this.withResolved(property, keySer, ser, valueToSuppress, suppressNulls);
        return mser;
    }

    @Override
    public JavaType getContentType() {
        return this._valueType;
    }

    @Override
    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    @Override
    public boolean hasSingleElement(Map.Entry<?, ?> value) {
        return true;
    }

    @Override
    public boolean isEmpty(SerializerProvider prov, Map.Entry<?, ?> entry) {
        Class<?> cc2;
        Object value = entry.getValue();
        if (value == null) {
            return this._suppressNulls;
        }
        if (this._suppressableValue == null) {
            return false;
        }
        JsonSerializer<Object> valueSer = this._valueSerializer;
        if (valueSer == null && (valueSer = this._dynamicValueSerializers.serializerFor((cc2 = value.getClass()).getClass())) == null) {
            try {
                valueSer = this._findAndAddDynamic(this._dynamicValueSerializers, cc2, prov);
            }
            catch (JsonMappingException e10) {
                return false;
            }
        }
        if (this._suppressableValue == MARKER_FOR_EMPTY) {
            return valueSer.isEmpty(prov, value);
        }
        return this._suppressableValue.equals(value);
    }

    @Override
    public void serialize(Map.Entry<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject(value);
        this.serializeDynamic(value, gen, provider);
        gen.writeEndObject();
    }

    @Override
    public void serializeWithType(Map.Entry<?, ?> value, JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        g10.setCurrentValue(value);
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId(value, JsonToken.START_OBJECT));
        this.serializeDynamic(value, g10, provider);
        typeSer.writeTypeSuffix(g10, typeIdDef);
    }

    protected void serializeDynamic(Map.Entry<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        JsonSerializer<Object> valueSer;
        TypeSerializer vts = this._valueTypeSerializer;
        Object keyElem = value.getKey();
        JsonSerializer<Object> keySerializer = keyElem == null ? provider.findNullKeySerializer(this._keyType, this._property) : this._keySerializer;
        Object valueElem = value.getValue();
        if (valueElem == null) {
            if (this._suppressNulls) {
                return;
            }
            valueSer = provider.getDefaultNullValueSerializer();
        } else {
            Class<?> cc2;
            valueSer = this._valueSerializer;
            if (valueSer == null && (valueSer = this._dynamicValueSerializers.serializerFor(cc2 = valueElem.getClass())) == null) {
                valueSer = this._valueType.hasGenericTypes() ? this._findAndAddDynamic(this._dynamicValueSerializers, provider.constructSpecializedType(this._valueType, cc2), provider) : this._findAndAddDynamic(this._dynamicValueSerializers, cc2, provider);
            }
            if (this._suppressableValue != null) {
                if (this._suppressableValue == MARKER_FOR_EMPTY && valueSer.isEmpty(provider, valueElem)) {
                    return;
                }
                if (this._suppressableValue.equals(valueElem)) {
                    return;
                }
            }
        }
        keySerializer.serialize(keyElem, gen, provider);
        try {
            if (vts == null) {
                valueSer.serialize(valueElem, gen, provider);
            } else {
                valueSer.serializeWithType(valueElem, gen, provider, vts);
            }
        }
        catch (Exception e10) {
            String keyDesc = "" + keyElem;
            this.wrapAndThrow(provider, (Throwable)e10, value, keyDesc);
        }
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, Class<?> type, SerializerProvider provider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
        if (map != result.map) {
            this._dynamicValueSerializers = result.map;
        }
        return result.serializer;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, JavaType type, SerializerProvider provider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
        if (map != result.map) {
            this._dynamicValueSerializers = result.map;
        }
        return result.serializer;
    }
}

