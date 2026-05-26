/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ArrayNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContextualSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.EnumValues;

@JacksonStdImpl
public class EnumSerializer
extends StdScalarSerializer<Enum<?>>
implements ContextualSerializer {
    private static final long serialVersionUID = 1L;
    protected final EnumValues _values;
    protected final Boolean _serializeAsIndex;

    public EnumSerializer(EnumValues v2, Boolean serializeAsIndex) {
        super(v2.getEnumClass(), false);
        this._values = v2;
        this._serializeAsIndex = serializeAsIndex;
    }

    public static EnumSerializer construct(Class<?> enumClass, SerializationConfig config, BeanDescription beanDesc, JsonFormat.Value format) {
        EnumValues v2 = EnumValues.constructFromName(config, enumClass);
        Boolean serializeAsIndex = EnumSerializer._isShapeWrittenUsingIndex(enumClass, format, true, null);
        return new EnumSerializer(v2, serializeAsIndex);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        Class type;
        Boolean serializeAsIndex;
        JsonFormat.Value format = this.findFormatOverrides(serializers, property, this.handledType());
        if (format != null && (serializeAsIndex = EnumSerializer._isShapeWrittenUsingIndex(type = this.handledType(), format, false, this._serializeAsIndex)) != this._serializeAsIndex) {
            return new EnumSerializer(this._values, serializeAsIndex);
        }
        return this;
    }

    public EnumValues getEnumValues() {
        return this._values;
    }

    @Override
    public final void serialize(Enum<?> en2, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (this._serializeAsIndex(serializers)) {
            gen.writeNumber(en2.ordinal());
            return;
        }
        if (serializers.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
            gen.writeString(en2.toString());
            return;
        }
        gen.writeString(this._values.serializedValueFor(en2));
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        JavaType type;
        if (this._serializeAsIndex(provider)) {
            return this.createSchemaNode("integer", true);
        }
        ObjectNode objectNode = this.createSchemaNode("string", true);
        if (typeHint != null && (type = provider.constructType(typeHint)).isEnumType()) {
            ArrayNode enumNode = objectNode.putArray("enum");
            for (SerializableString value : this._values.values()) {
                enumNode.add(value.getValue());
            }
        }
        return objectNode;
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        SerializerProvider serializers = visitor.getProvider();
        if (this._serializeAsIndex(serializers)) {
            this.visitIntFormat(visitor, typeHint, JsonParser.NumberType.INT);
            return;
        }
        JsonStringFormatVisitor stringVisitor = visitor.expectStringFormat(typeHint);
        if (stringVisitor != null) {
            LinkedHashSet<String> enums = new LinkedHashSet<String>();
            if (serializers != null && serializers.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
                for (Enum<?> e10 : this._values.enums()) {
                    enums.add(e10.toString());
                }
            } else {
                for (SerializableString value : this._values.values()) {
                    enums.add(value.getValue());
                }
            }
            stringVisitor.enumTypes(enums);
        }
    }

    protected final boolean _serializeAsIndex(SerializerProvider serializers) {
        if (this._serializeAsIndex != null) {
            return this._serializeAsIndex;
        }
        return serializers.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    protected static Boolean _isShapeWrittenUsingIndex(Class<?> enumClass, JsonFormat.Value format, boolean fromClass, Boolean defaultValue) {
        JsonFormat.Shape shape;
        JsonFormat.Shape shape2 = shape = format == null ? null : format.getShape();
        if (shape == null) {
            return defaultValue;
        }
        if (shape == JsonFormat.Shape.ANY || shape == JsonFormat.Shape.SCALAR) {
            return defaultValue;
        }
        if (shape == JsonFormat.Shape.STRING || shape == JsonFormat.Shape.NATURAL) {
            return Boolean.FALSE;
        }
        if (shape.isNumeric() || shape == JsonFormat.Shape.ARRAY) {
            return Boolean.TRUE;
        }
        throw new IllegalArgumentException(String.format("Unsupported serialization shape (%s) for Enum %s, not supported as %s annotation", new Object[]{shape, enumClass.getName(), fromClass ? "class" : "property"}));
    }
}

