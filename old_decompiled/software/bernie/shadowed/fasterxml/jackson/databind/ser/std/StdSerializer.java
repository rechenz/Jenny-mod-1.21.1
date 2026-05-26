/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.IdentityHashMap;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonschema.SchemaAware;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.FilterProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.PropertyFilter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Converter;

public abstract class StdSerializer<T>
extends JsonSerializer<T>
implements JsonFormatVisitable,
SchemaAware,
Serializable {
    private static final long serialVersionUID = 1L;
    private static final Object KEY_CONTENT_CONVERTER_LOCK = new Object();
    protected final Class<T> _handledType;

    protected StdSerializer(Class<T> t2) {
        this._handledType = t2;
    }

    protected StdSerializer(JavaType type) {
        this._handledType = type.getRawClass();
    }

    protected StdSerializer(Class<?> t2, boolean dummy) {
        this._handledType = t2;
    }

    protected StdSerializer(StdSerializer<?> src) {
        this._handledType = src._handledType;
    }

    @Override
    public Class<T> handledType() {
        return this._handledType;
    }

    @Override
    public abstract void serialize(T var1, JsonGenerator var2, SerializerProvider var3) throws IOException;

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        visitor.expectAnyFormat(typeHint);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        return this.createSchemaNode("string");
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint, boolean isOptional) throws JsonMappingException {
        ObjectNode schema = (ObjectNode)this.getSchema(provider, typeHint);
        if (!isOptional) {
            schema.put("required", !isOptional);
        }
        return schema;
    }

    protected ObjectNode createSchemaNode(String type) {
        ObjectNode schema = JsonNodeFactory.instance.objectNode();
        schema.put("type", type);
        return schema;
    }

    protected ObjectNode createSchemaNode(String type, boolean isOptional) {
        ObjectNode schema = this.createSchemaNode(type);
        if (!isOptional) {
            schema.put("required", !isOptional);
        }
        return schema;
    }

    protected void visitStringFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        visitor.expectStringFormat(typeHint);
    }

    protected void visitStringFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonValueFormat format) throws JsonMappingException {
        JsonStringFormatVisitor v2 = visitor.expectStringFormat(typeHint);
        if (v2 != null) {
            v2.format(format);
        }
    }

    protected void visitIntFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonParser.NumberType numberType) throws JsonMappingException {
        JsonIntegerFormatVisitor v2 = visitor.expectIntegerFormat(typeHint);
        if (StdSerializer._neitherNull(v2, (Object)numberType)) {
            v2.numberType(numberType);
        }
    }

    protected void visitIntFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonParser.NumberType numberType, JsonValueFormat format) throws JsonMappingException {
        JsonIntegerFormatVisitor v2 = visitor.expectIntegerFormat(typeHint);
        if (v2 != null) {
            if (numberType != null) {
                v2.numberType(numberType);
            }
            if (format != null) {
                v2.format(format);
            }
        }
    }

    protected void visitFloatFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonParser.NumberType numberType) throws JsonMappingException {
        JsonNumberFormatVisitor v2 = visitor.expectNumberFormat(typeHint);
        if (v2 != null) {
            v2.numberType(numberType);
        }
    }

    protected void visitArrayFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonSerializer<?> itemSerializer, JavaType itemType) throws JsonMappingException {
        JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
        if (StdSerializer._neitherNull(v2, itemSerializer)) {
            v2.itemsFormat(itemSerializer, itemType);
        }
    }

    protected void visitArrayFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonFormatTypes itemType) throws JsonMappingException {
        JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
        if (v2 != null) {
            v2.itemsFormat(itemType);
        }
    }

    public void wrapAndThrow(SerializerProvider provider, Throwable t2, Object bean, String fieldName) throws IOException {
        boolean wrap;
        while (t2 instanceof InvocationTargetException && t2.getCause() != null) {
            t2 = t2.getCause();
        }
        ClassUtil.throwIfError(t2);
        boolean bl2 = wrap = provider == null || provider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS);
        if (t2 instanceof IOException) {
            if (!wrap || !(t2 instanceof JsonMappingException)) {
                throw (IOException)t2;
            }
        } else if (!wrap) {
            ClassUtil.throwIfRTE(t2);
        }
        throw JsonMappingException.wrapWithPath(t2, bean, fieldName);
    }

    public void wrapAndThrow(SerializerProvider provider, Throwable t2, Object bean, int index) throws IOException {
        boolean wrap;
        while (t2 instanceof InvocationTargetException && t2.getCause() != null) {
            t2 = t2.getCause();
        }
        ClassUtil.throwIfError(t2);
        boolean bl2 = wrap = provider == null || provider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS);
        if (t2 instanceof IOException) {
            if (!wrap || !(t2 instanceof JsonMappingException)) {
                throw (IOException)t2;
            }
        } else if (!wrap) {
            ClassUtil.throwIfRTE(t2);
        }
        throw JsonMappingException.wrapWithPath(t2, bean, index);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected JsonSerializer<?> findContextualConvertingSerializer(SerializerProvider provider, BeanProperty property, JsonSerializer<?> existingSerializer) throws JsonMappingException {
        IdentityHashMap<BeanProperty, Boolean> conversions = (IdentityHashMap<BeanProperty, Boolean>)provider.getAttribute(KEY_CONTENT_CONVERTER_LOCK);
        if (conversions != null) {
            Object lock = conversions.get(property);
            if (lock != null) {
                return existingSerializer;
            }
        } else {
            conversions = new IdentityHashMap<BeanProperty, Boolean>();
            provider.setAttribute(KEY_CONTENT_CONVERTER_LOCK, conversions);
        }
        conversions.put(property, Boolean.TRUE);
        try {
            JsonSerializer<?> ser = this.findConvertingContentSerializer(provider, property, existingSerializer);
            if (ser != null) {
                JsonSerializer<?> jsonSerializer = provider.handleSecondaryContextualization(ser, property);
                return jsonSerializer;
            }
        }
        finally {
            conversions.remove(property);
        }
        return existingSerializer;
    }

    @Deprecated
    protected JsonSerializer<?> findConvertingContentSerializer(SerializerProvider provider, BeanProperty prop, JsonSerializer<?> existingSerializer) throws JsonMappingException {
        Object convDef;
        AnnotatedMember m2;
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        if (StdSerializer._neitherNull(intr, prop) && (m2 = prop.getMember()) != null && (convDef = intr.findSerializationContentConverter(m2)) != null) {
            Converter<Object, Object> conv = provider.converterInstance(prop.getMember(), convDef);
            JavaType delegateType = conv.getOutputType(provider.getTypeFactory());
            if (existingSerializer == null && !delegateType.isJavaLangObject()) {
                existingSerializer = provider.findValueSerializer(delegateType);
            }
            return new StdDelegatingSerializer(conv, delegateType, existingSerializer);
        }
        return existingSerializer;
    }

    protected PropertyFilter findPropertyFilter(SerializerProvider provider, Object filterId, Object valueToFilter) throws JsonMappingException {
        FilterProvider filters = provider.getFilterProvider();
        if (filters == null) {
            provider.reportBadDefinition(this.handledType(), "Cannot resolve PropertyFilter with id '" + filterId + "'; no FilterProvider configured");
        }
        return filters.findPropertyFilter(filterId, valueToFilter);
    }

    protected JsonFormat.Value findFormatOverrides(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults) {
        if (prop != null) {
            return prop.findPropertyFormat(provider.getConfig(), typeForDefaults);
        }
        return provider.getDefaultPropertyFormat(typeForDefaults);
    }

    protected Boolean findFormatFeature(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults, JsonFormat.Feature feat) {
        JsonFormat.Value format = this.findFormatOverrides(provider, prop, typeForDefaults);
        if (format != null) {
            return format.getFeature(feat);
        }
        return null;
    }

    protected JsonInclude.Value findIncludeOverrides(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults) {
        if (prop != null) {
            return prop.findPropertyInclusion(provider.getConfig(), typeForDefaults);
        }
        return provider.getDefaultPropertyInclusion(typeForDefaults);
    }

    protected JsonSerializer<?> findAnnotatedContentSerializer(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            Object serDef;
            AnnotatedMember m2 = property.getMember();
            AnnotationIntrospector intr = serializers.getAnnotationIntrospector();
            if (m2 != null && (serDef = intr.findContentSerializer(m2)) != null) {
                return serializers.serializerInstance(m2, serDef);
            }
        }
        return null;
    }

    protected boolean isDefaultSerializer(JsonSerializer<?> serializer) {
        return ClassUtil.isJacksonStdImpl(serializer);
    }

    protected static final boolean _neitherNull(Object a10, Object b10) {
        return a10 != null && b10 != null;
    }

    protected static final boolean _nonEmpty(Collection<?> c10) {
        return c10 != null && !c10.isEmpty();
    }
}

