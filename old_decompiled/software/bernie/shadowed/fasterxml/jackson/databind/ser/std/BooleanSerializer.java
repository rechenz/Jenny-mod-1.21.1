/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContextualSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

@JacksonStdImpl
public final class BooleanSerializer
extends StdScalarSerializer<Object>
implements ContextualSerializer {
    private static final long serialVersionUID = 1L;
    protected final boolean _forPrimitive;

    public BooleanSerializer(boolean forPrimitive) {
        super(forPrimitive ? Boolean.TYPE : Boolean.class, false);
        this._forPrimitive = forPrimitive;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        JsonFormat.Shape shape;
        JsonFormat.Value format = this.findFormatOverrides(serializers, property, Boolean.class);
        if (format != null && (shape = format.getShape()).isNumeric()) {
            return new AsNumber(this._forPrimitive);
        }
        return this;
    }

    @Override
    public void serialize(Object value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        g10.writeBoolean(Boolean.TRUE.equals(value));
    }

    @Override
    public final void serializeWithType(Object value, JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        g10.writeBoolean(Boolean.TRUE.equals(value));
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return this.createSchemaNode("boolean", !this._forPrimitive);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        visitor.expectBooleanFormat(typeHint);
    }

    static final class AsNumber
    extends StdScalarSerializer<Object>
    implements ContextualSerializer {
        private static final long serialVersionUID = 1L;
        protected final boolean _forPrimitive;

        public AsNumber(boolean forPrimitive) {
            super(forPrimitive ? Boolean.TYPE : Boolean.class, false);
            this._forPrimitive = forPrimitive;
        }

        @Override
        public void serialize(Object value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            g10.writeNumber(Boolean.FALSE.equals(value) ? 0 : 1);
        }

        @Override
        public final void serializeWithType(Object value, JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
            g10.writeBoolean(Boolean.TRUE.equals(value));
        }

        @Override
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            this.visitIntFormat(visitor, typeHint, JsonParser.NumberType.INT);
        }

        @Override
        public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
            JsonFormat.Shape shape;
            JsonFormat.Value format = this.findFormatOverrides(serializers, property, Boolean.class);
            if (format != null && !(shape = format.getShape()).isNumeric()) {
                return new BooleanSerializer(this._forPrimitive);
            }
            return this;
        }
    }
}

