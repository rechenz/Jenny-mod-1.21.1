/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContainerSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.ByteArraySerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;

public class StdArraySerializers {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers = new HashMap();

    protected StdArraySerializers() {
    }

    public static JsonSerializer<?> findStandardImpl(Class<?> cls) {
        return _arraySerializers.get(cls.getName());
    }

    static {
        _arraySerializers.put(boolean[].class.getName(), new BooleanArraySerializer());
        _arraySerializers.put(byte[].class.getName(), new ByteArraySerializer());
        _arraySerializers.put(char[].class.getName(), new CharArraySerializer());
        _arraySerializers.put(short[].class.getName(), new ShortArraySerializer());
        _arraySerializers.put(int[].class.getName(), new IntArraySerializer());
        _arraySerializers.put(long[].class.getName(), new LongArraySerializer());
        _arraySerializers.put(float[].class.getName(), new FloatArraySerializer());
        _arraySerializers.put(double[].class.getName(), new DoubleArraySerializer());
    }

    @JacksonStdImpl
    public static class DoubleArraySerializer
    extends ArraySerializerBase<double[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Double.TYPE);

        public DoubleArraySerializer() {
            super(double[].class);
        }

        protected DoubleArraySerializer(DoubleArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
            super(src, prop, unwrapSingle);
        }

        @Override
        public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
            return new DoubleArraySerializer(this, prop, unwrapSingle);
        }

        @Override
        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
            return this;
        }

        @Override
        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        @Override
        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        @Override
        public boolean isEmpty(SerializerProvider prov, double[] value) {
            return value.length == 0;
        }

        @Override
        public boolean hasSingleElement(double[] value) {
            return value.length == 1;
        }

        @Override
        public final void serialize(double[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            if (len == 1 && this._shouldUnwrapSingle(provider)) {
                this.serializeContents(value, g10, provider);
                return;
            }
            g10.setCurrentValue(value);
            g10.writeArray(value, 0, value.length);
        }

        @Override
        public void serializeContents(double[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            for (int i2 = 0; i2 < len; ++i2) {
                g10.writeNumber(value[i2]);
            }
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            return this.createSchemaNode("array", true).set("items", this.createSchemaNode("number"));
        }

        @Override
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.NUMBER);
        }
    }

    @JacksonStdImpl
    public static class FloatArraySerializer
    extends TypedPrimitiveArraySerializer<float[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Float.TYPE);

        public FloatArraySerializer() {
            super(float[].class);
        }

        public FloatArraySerializer(FloatArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
            super(src, prop, unwrapSingle);
        }

        @Override
        public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
            return new FloatArraySerializer(this, prop, unwrapSingle);
        }

        @Override
        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        @Override
        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        @Override
        public boolean isEmpty(SerializerProvider prov, float[] value) {
            return value.length == 0;
        }

        @Override
        public boolean hasSingleElement(float[] value) {
            return value.length == 1;
        }

        @Override
        public final void serialize(float[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            if (len == 1 && this._shouldUnwrapSingle(provider)) {
                this.serializeContents(value, g10, provider);
                return;
            }
            g10.writeStartArray(len);
            g10.setCurrentValue(value);
            this.serializeContents(value, g10, provider);
            g10.writeEndArray();
        }

        @Override
        public void serializeContents(float[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            for (int i2 = 0; i2 < len; ++i2) {
                g10.writeNumber(value[i2]);
            }
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            return this.createSchemaNode("array", true).set("items", this.createSchemaNode("number"));
        }

        @Override
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.NUMBER);
        }
    }

    @JacksonStdImpl
    public static class LongArraySerializer
    extends TypedPrimitiveArraySerializer<long[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Long.TYPE);

        public LongArraySerializer() {
            super(long[].class);
        }

        public LongArraySerializer(LongArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
            super(src, prop, unwrapSingle);
        }

        @Override
        public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
            return new LongArraySerializer(this, prop, unwrapSingle);
        }

        @Override
        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        @Override
        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        @Override
        public boolean isEmpty(SerializerProvider prov, long[] value) {
            return value.length == 0;
        }

        @Override
        public boolean hasSingleElement(long[] value) {
            return value.length == 1;
        }

        @Override
        public final void serialize(long[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            if (len == 1 && this._shouldUnwrapSingle(provider)) {
                this.serializeContents(value, g10, provider);
                return;
            }
            g10.setCurrentValue(value);
            g10.writeArray(value, 0, value.length);
        }

        @Override
        public void serializeContents(long[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            for (int i2 = 0; i2 < len; ++i2) {
                g10.writeNumber(value[i2]);
            }
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            return this.createSchemaNode("array", true).set("items", this.createSchemaNode("number", true));
        }

        @Override
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.NUMBER);
        }
    }

    @JacksonStdImpl
    public static class IntArraySerializer
    extends ArraySerializerBase<int[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Integer.TYPE);

        public IntArraySerializer() {
            super(int[].class);
        }

        protected IntArraySerializer(IntArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
            super(src, prop, unwrapSingle);
        }

        @Override
        public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
            return new IntArraySerializer(this, prop, unwrapSingle);
        }

        @Override
        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
            return this;
        }

        @Override
        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        @Override
        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        @Override
        public boolean isEmpty(SerializerProvider prov, int[] value) {
            return value.length == 0;
        }

        @Override
        public boolean hasSingleElement(int[] value) {
            return value.length == 1;
        }

        @Override
        public final void serialize(int[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            if (len == 1 && this._shouldUnwrapSingle(provider)) {
                this.serializeContents(value, g10, provider);
                return;
            }
            g10.setCurrentValue(value);
            g10.writeArray(value, 0, value.length);
        }

        @Override
        public void serializeContents(int[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            for (int i2 = 0; i2 < len; ++i2) {
                g10.writeNumber(value[i2]);
            }
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            return this.createSchemaNode("array", true).set("items", this.createSchemaNode("integer"));
        }

        @Override
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.INTEGER);
        }
    }

    @JacksonStdImpl
    public static class CharArraySerializer
    extends StdSerializer<char[]> {
        public CharArraySerializer() {
            super(char[].class);
        }

        @Override
        public boolean isEmpty(SerializerProvider prov, char[] value) {
            return value.length == 0;
        }

        @Override
        public void serialize(char[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            if (provider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                g10.writeStartArray(value.length);
                g10.setCurrentValue(value);
                this._writeArrayContents(g10, value);
                g10.writeEndArray();
            } else {
                g10.writeString(value, 0, value.length);
            }
        }

        @Override
        public void serializeWithType(char[] value, JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
            WritableTypeId typeIdDef;
            boolean asArray = provider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS);
            if (asArray) {
                typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId(value, JsonToken.START_ARRAY));
                this._writeArrayContents(g10, value);
            } else {
                typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId(value, JsonToken.VALUE_STRING));
                g10.writeString(value, 0, value.length);
            }
            typeSer.writeTypeSuffix(g10, typeIdDef);
        }

        private final void _writeArrayContents(JsonGenerator g10, char[] value) throws IOException {
            int len = value.length;
            for (int i2 = 0; i2 < len; ++i2) {
                g10.writeString(value, i2, 1);
            }
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            ObjectNode o2 = this.createSchemaNode("array", true);
            ObjectNode itemSchema = this.createSchemaNode("string");
            itemSchema.put("type", "string");
            return o2.set("items", itemSchema);
        }

        @Override
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.STRING);
        }
    }

    @JacksonStdImpl
    public static class ShortArraySerializer
    extends TypedPrimitiveArraySerializer<short[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);

        public ShortArraySerializer() {
            super(short[].class);
        }

        public ShortArraySerializer(ShortArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
            super(src, prop, unwrapSingle);
        }

        @Override
        public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
            return new ShortArraySerializer(this, prop, unwrapSingle);
        }

        @Override
        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        @Override
        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        @Override
        public boolean isEmpty(SerializerProvider prov, short[] value) {
            return value.length == 0;
        }

        @Override
        public boolean hasSingleElement(short[] value) {
            return value.length == 1;
        }

        @Override
        public final void serialize(short[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            if (len == 1 && this._shouldUnwrapSingle(provider)) {
                this.serializeContents(value, g10, provider);
                return;
            }
            g10.writeStartArray(len);
            g10.setCurrentValue(value);
            this.serializeContents(value, g10, provider);
            g10.writeEndArray();
        }

        @Override
        public void serializeContents(short[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            for (int i2 = 0; i2 < len; ++i2) {
                g10.writeNumber((int)value[i2]);
            }
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            ObjectNode o2 = this.createSchemaNode("array", true);
            return o2.set("items", this.createSchemaNode("integer"));
        }

        @Override
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.INTEGER);
        }
    }

    @JacksonStdImpl
    public static class BooleanArraySerializer
    extends ArraySerializerBase<boolean[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Boolean.class);

        public BooleanArraySerializer() {
            super(boolean[].class);
        }

        protected BooleanArraySerializer(BooleanArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
            super(src, prop, unwrapSingle);
        }

        @Override
        public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
            return new BooleanArraySerializer(this, prop, unwrapSingle);
        }

        @Override
        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
            return this;
        }

        @Override
        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        @Override
        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        @Override
        public boolean isEmpty(SerializerProvider prov, boolean[] value) {
            return value.length == 0;
        }

        @Override
        public boolean hasSingleElement(boolean[] value) {
            return value.length == 1;
        }

        @Override
        public final void serialize(boolean[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            if (len == 1 && this._shouldUnwrapSingle(provider)) {
                this.serializeContents(value, g10, provider);
                return;
            }
            g10.writeStartArray(len);
            g10.setCurrentValue(value);
            this.serializeContents(value, g10, provider);
            g10.writeEndArray();
        }

        @Override
        public void serializeContents(boolean[] value, JsonGenerator g10, SerializerProvider provider) throws IOException {
            int len = value.length;
            for (int i2 = 0; i2 < len; ++i2) {
                g10.writeBoolean(value[i2]);
            }
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            ObjectNode o2 = this.createSchemaNode("array", true);
            o2.set("items", this.createSchemaNode("boolean"));
            return o2;
        }

        @Override
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.BOOLEAN);
        }
    }

    protected static abstract class TypedPrimitiveArraySerializer<T>
    extends ArraySerializerBase<T> {
        protected TypedPrimitiveArraySerializer(Class<T> cls) {
            super(cls);
        }

        protected TypedPrimitiveArraySerializer(TypedPrimitiveArraySerializer<T> src, BeanProperty prop, Boolean unwrapSingle) {
            super(src, prop, unwrapSingle);
        }

        @Override
        public final ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
            return this;
        }
    }
}

