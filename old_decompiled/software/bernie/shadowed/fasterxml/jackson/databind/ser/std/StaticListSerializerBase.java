/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContextualSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class StaticListSerializerBase<T extends Collection<?>>
extends StdSerializer<T>
implements ContextualSerializer {
    protected final Boolean _unwrapSingle;

    protected StaticListSerializerBase(Class<?> cls) {
        super(cls, false);
        this._unwrapSingle = null;
    }

    protected StaticListSerializerBase(StaticListSerializerBase<?> src, Boolean unwrapSingle) {
        super(src);
        this._unwrapSingle = unwrapSingle;
    }

    public abstract JsonSerializer<?> _withResolved(BeanProperty var1, Boolean var2);

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        JsonFormat.Value format;
        JsonSerializer<Object> ser = null;
        Boolean unwrapSingle = null;
        if (property != null) {
            Object serDef;
            AnnotationIntrospector intr = serializers.getAnnotationIntrospector();
            AnnotatedMember m2 = property.getMember();
            if (m2 != null && (serDef = intr.findContentSerializer(m2)) != null) {
                ser = serializers.serializerInstance(m2, serDef);
            }
        }
        if ((format = this.findFormatOverrides(serializers, property, this.handledType())) != null) {
            unwrapSingle = format.getFeature(JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        }
        if ((ser = this.findContextualConvertingSerializer(serializers, property, ser)) == null) {
            ser = serializers.findValueSerializer(String.class, property);
        }
        if (this.isDefaultSerializer(ser)) {
            if (unwrapSingle == this._unwrapSingle) {
                return this;
            }
            return this._withResolved(property, unwrapSingle);
        }
        return new CollectionSerializer(serializers.constructType((Type)((Object)String.class)), true, null, ser);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, T value) {
        return value == null || value.size() == 0;
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return this.createSchemaNode("array", true).set("items", this.contentSchema());
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        this.acceptContentVisitor(visitor.expectArrayFormat(typeHint));
    }

    protected abstract JsonNode contentSchema();

    protected abstract void acceptContentVisitor(JsonArrayFormatVisitor var1) throws JsonMappingException;

    @Override
    public abstract void serializeWithType(T var1, JsonGenerator var2, SerializerProvider var3, TypeSerializer var4) throws IOException;
}

