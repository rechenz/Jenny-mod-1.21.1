/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variants;
import software.bernie.shadowed.fasterxml.jackson.core.FormatSchema;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerationException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonStreamContext;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;
import software.bernie.shadowed.fasterxml.jackson.core.PrettyPrinter;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.core.TreeNode;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.core.Versioned;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharacterEscapes;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.core.util.VersionUtil;

public abstract class JsonGenerator
implements Closeable,
Flushable,
Versioned {
    protected PrettyPrinter _cfgPrettyPrinter;

    protected JsonGenerator() {
    }

    public abstract JsonGenerator setCodec(ObjectCodec var1);

    public abstract ObjectCodec getCodec();

    @Override
    public abstract Version version();

    public abstract JsonGenerator enable(Feature var1);

    public abstract JsonGenerator disable(Feature var1);

    public final JsonGenerator configure(Feature f10, boolean state) {
        if (state) {
            this.enable(f10);
        } else {
            this.disable(f10);
        }
        return this;
    }

    public abstract boolean isEnabled(Feature var1);

    public abstract int getFeatureMask();

    @Deprecated
    public abstract JsonGenerator setFeatureMask(int var1);

    public JsonGenerator overrideStdFeatures(int values, int mask) {
        int oldState = this.getFeatureMask();
        int newState = oldState & ~mask | values & mask;
        return this.setFeatureMask(newState);
    }

    public int getFormatFeatures() {
        return 0;
    }

    public JsonGenerator overrideFormatFeatures(int values, int mask) {
        throw new IllegalArgumentException("No FormatFeatures defined for generator of type " + this.getClass().getName());
    }

    public void setSchema(FormatSchema schema) {
        throw new UnsupportedOperationException("Generator of type " + this.getClass().getName() + " does not support schema of type '" + schema.getSchemaType() + "'");
    }

    public FormatSchema getSchema() {
        return null;
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter pp) {
        this._cfgPrettyPrinter = pp;
        return this;
    }

    public PrettyPrinter getPrettyPrinter() {
        return this._cfgPrettyPrinter;
    }

    public abstract JsonGenerator useDefaultPrettyPrinter();

    public JsonGenerator setHighestNonEscapedChar(int charCode) {
        return this;
    }

    public int getHighestEscapedChar() {
        return 0;
    }

    public CharacterEscapes getCharacterEscapes() {
        return null;
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes esc) {
        return this;
    }

    public JsonGenerator setRootValueSeparator(SerializableString sep) {
        throw new UnsupportedOperationException();
    }

    public Object getOutputTarget() {
        return null;
    }

    public int getOutputBuffered() {
        return -1;
    }

    public Object getCurrentValue() {
        JsonStreamContext ctxt = this.getOutputContext();
        return ctxt == null ? null : ctxt.getCurrentValue();
    }

    public void setCurrentValue(Object v2) {
        JsonStreamContext ctxt = this.getOutputContext();
        if (ctxt != null) {
            ctxt.setCurrentValue(v2);
        }
    }

    public boolean canUseSchema(FormatSchema schema) {
        return false;
    }

    public boolean canWriteObjectId() {
        return false;
    }

    public boolean canWriteTypeId() {
        return false;
    }

    public boolean canWriteBinaryNatively() {
        return false;
    }

    public boolean canOmitFields() {
        return true;
    }

    public boolean canWriteFormattedNumbers() {
        return false;
    }

    public abstract void writeStartArray() throws IOException;

    public void writeStartArray(int size) throws IOException {
        this.writeStartArray();
    }

    public abstract void writeEndArray() throws IOException;

    public abstract void writeStartObject() throws IOException;

    public void writeStartObject(Object forValue) throws IOException {
        this.writeStartObject();
        this.setCurrentValue(forValue);
    }

    public abstract void writeEndObject() throws IOException;

    public abstract void writeFieldName(String var1) throws IOException;

    public abstract void writeFieldName(SerializableString var1) throws IOException;

    public void writeFieldId(long id) throws IOException {
        this.writeFieldName(Long.toString(id));
    }

    public void writeArray(int[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(array.length, offset, length);
        this.writeStartArray();
        int end = offset + length;
        for (int i2 = offset; i2 < end; ++i2) {
            this.writeNumber(array[i2]);
        }
        this.writeEndArray();
    }

    public void writeArray(long[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(array.length, offset, length);
        this.writeStartArray();
        int end = offset + length;
        for (int i2 = offset; i2 < end; ++i2) {
            this.writeNumber(array[i2]);
        }
        this.writeEndArray();
    }

    public void writeArray(double[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(array.length, offset, length);
        this.writeStartArray();
        int end = offset + length;
        for (int i2 = offset; i2 < end; ++i2) {
            this.writeNumber(array[i2]);
        }
        this.writeEndArray();
    }

    public abstract void writeString(String var1) throws IOException;

    public void writeString(Reader reader, int len) throws IOException {
        this._reportUnsupportedOperation();
    }

    public abstract void writeString(char[] var1, int var2, int var3) throws IOException;

    public abstract void writeString(SerializableString var1) throws IOException;

    public abstract void writeRawUTF8String(byte[] var1, int var2, int var3) throws IOException;

    public abstract void writeUTF8String(byte[] var1, int var2, int var3) throws IOException;

    public abstract void writeRaw(String var1) throws IOException;

    public abstract void writeRaw(String var1, int var2, int var3) throws IOException;

    public abstract void writeRaw(char[] var1, int var2, int var3) throws IOException;

    public abstract void writeRaw(char var1) throws IOException;

    public void writeRaw(SerializableString raw) throws IOException {
        this.writeRaw(raw.getValue());
    }

    public abstract void writeRawValue(String var1) throws IOException;

    public abstract void writeRawValue(String var1, int var2, int var3) throws IOException;

    public abstract void writeRawValue(char[] var1, int var2, int var3) throws IOException;

    public void writeRawValue(SerializableString raw) throws IOException {
        this.writeRawValue(raw.getValue());
    }

    public abstract void writeBinary(Base64Variant var1, byte[] var2, int var3, int var4) throws IOException;

    public void writeBinary(byte[] data, int offset, int len) throws IOException {
        this.writeBinary(Base64Variants.getDefaultVariant(), data, offset, len);
    }

    public void writeBinary(byte[] data) throws IOException {
        this.writeBinary(Base64Variants.getDefaultVariant(), data, 0, data.length);
    }

    public int writeBinary(InputStream data, int dataLength) throws IOException {
        return this.writeBinary(Base64Variants.getDefaultVariant(), data, dataLength);
    }

    public abstract int writeBinary(Base64Variant var1, InputStream var2, int var3) throws IOException;

    public void writeNumber(short v2) throws IOException {
        this.writeNumber((int)v2);
    }

    public abstract void writeNumber(int var1) throws IOException;

    public abstract void writeNumber(long var1) throws IOException;

    public abstract void writeNumber(BigInteger var1) throws IOException;

    public abstract void writeNumber(double var1) throws IOException;

    public abstract void writeNumber(float var1) throws IOException;

    public abstract void writeNumber(BigDecimal var1) throws IOException;

    public abstract void writeNumber(String var1) throws IOException;

    public abstract void writeBoolean(boolean var1) throws IOException;

    public abstract void writeNull() throws IOException;

    public void writeEmbeddedObject(Object object) throws IOException {
        if (object == null) {
            this.writeNull();
            return;
        }
        if (object instanceof byte[]) {
            this.writeBinary((byte[])object);
            return;
        }
        throw new JsonGenerationException("No native support for writing embedded objects of type " + object.getClass().getName(), this);
    }

    public void writeObjectId(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }

    public void writeObjectRef(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }

    public void writeTypeId(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Type Ids", this);
    }

    public WritableTypeId writeTypePrefix(WritableTypeId typeIdDef) throws IOException {
        Object id = typeIdDef.id;
        JsonToken valueShape = typeIdDef.valueShape;
        if (this.canWriteTypeId()) {
            typeIdDef.wrapperWritten = false;
            this.writeTypeId(id);
        } else {
            String idStr = id instanceof String ? (String)id : String.valueOf(id);
            typeIdDef.wrapperWritten = true;
            WritableTypeId.Inclusion incl = typeIdDef.include;
            if (valueShape != JsonToken.START_OBJECT && incl.requiresObjectContext()) {
                typeIdDef.include = incl = WritableTypeId.Inclusion.WRAPPER_ARRAY;
            }
            switch (incl) {
                case PARENT_PROPERTY: {
                    break;
                }
                case PAYLOAD_PROPERTY: {
                    break;
                }
                case METADATA_PROPERTY: {
                    this.writeStartObject(typeIdDef.forValue);
                    this.writeStringField(typeIdDef.asProperty, idStr);
                    return typeIdDef;
                }
                case WRAPPER_OBJECT: {
                    this.writeStartObject();
                    this.writeFieldName(idStr);
                    break;
                }
                default: {
                    this.writeStartArray();
                    this.writeString(idStr);
                }
            }
        }
        if (valueShape == JsonToken.START_OBJECT) {
            this.writeStartObject(typeIdDef.forValue);
        } else if (valueShape == JsonToken.START_ARRAY) {
            this.writeStartArray();
        }
        return typeIdDef;
    }

    public WritableTypeId writeTypeSuffix(WritableTypeId typeIdDef) throws IOException {
        JsonToken valueShape = typeIdDef.valueShape;
        if (valueShape == JsonToken.START_OBJECT) {
            this.writeEndObject();
        } else if (valueShape == JsonToken.START_ARRAY) {
            this.writeEndArray();
        }
        if (typeIdDef.wrapperWritten) {
            switch (typeIdDef.include) {
                case WRAPPER_ARRAY: {
                    this.writeEndArray();
                    break;
                }
                case PARENT_PROPERTY: {
                    Object id = typeIdDef.id;
                    String idStr = id instanceof String ? (String)id : String.valueOf(id);
                    this.writeStringField(typeIdDef.asProperty, idStr);
                    break;
                }
                case PAYLOAD_PROPERTY: 
                case METADATA_PROPERTY: {
                    break;
                }
                default: {
                    this.writeEndObject();
                }
            }
        }
        return typeIdDef;
    }

    public abstract void writeObject(Object var1) throws IOException;

    public abstract void writeTree(TreeNode var1) throws IOException;

    public void writeStringField(String fieldName, String value) throws IOException {
        this.writeFieldName(fieldName);
        this.writeString(value);
    }

    public final void writeBooleanField(String fieldName, boolean value) throws IOException {
        this.writeFieldName(fieldName);
        this.writeBoolean(value);
    }

    public final void writeNullField(String fieldName) throws IOException {
        this.writeFieldName(fieldName);
        this.writeNull();
    }

    public final void writeNumberField(String fieldName, int value) throws IOException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeNumberField(String fieldName, long value) throws IOException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeNumberField(String fieldName, double value) throws IOException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeNumberField(String fieldName, float value) throws IOException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeNumberField(String fieldName, BigDecimal value) throws IOException {
        this.writeFieldName(fieldName);
        this.writeNumber(value);
    }

    public final void writeBinaryField(String fieldName, byte[] data) throws IOException {
        this.writeFieldName(fieldName);
        this.writeBinary(data);
    }

    public final void writeArrayFieldStart(String fieldName) throws IOException {
        this.writeFieldName(fieldName);
        this.writeStartArray();
    }

    public final void writeObjectFieldStart(String fieldName) throws IOException {
        this.writeFieldName(fieldName);
        this.writeStartObject();
    }

    public final void writeObjectField(String fieldName, Object pojo) throws IOException {
        this.writeFieldName(fieldName);
        this.writeObject(pojo);
    }

    public void writeOmittedField(String fieldName) throws IOException {
    }

    public void copyCurrentEvent(JsonParser p2) throws IOException {
        JsonToken t2 = p2.currentToken();
        if (t2 == null) {
            this._reportError("No current event to copy");
        }
        switch (t2.id()) {
            case -1: {
                this._reportError("No current event to copy");
                break;
            }
            case 1: {
                this.writeStartObject();
                break;
            }
            case 2: {
                this.writeEndObject();
                break;
            }
            case 3: {
                this.writeStartArray();
                break;
            }
            case 4: {
                this.writeEndArray();
                break;
            }
            case 5: {
                this.writeFieldName(p2.getCurrentName());
                break;
            }
            case 6: {
                if (p2.hasTextCharacters()) {
                    this.writeString(p2.getTextCharacters(), p2.getTextOffset(), p2.getTextLength());
                    break;
                }
                this.writeString(p2.getText());
                break;
            }
            case 7: {
                JsonParser.NumberType n2 = p2.getNumberType();
                if (n2 == JsonParser.NumberType.INT) {
                    this.writeNumber(p2.getIntValue());
                    break;
                }
                if (n2 == JsonParser.NumberType.BIG_INTEGER) {
                    this.writeNumber(p2.getBigIntegerValue());
                    break;
                }
                this.writeNumber(p2.getLongValue());
                break;
            }
            case 8: {
                JsonParser.NumberType n3 = p2.getNumberType();
                if (n3 == JsonParser.NumberType.BIG_DECIMAL) {
                    this.writeNumber(p2.getDecimalValue());
                    break;
                }
                if (n3 == JsonParser.NumberType.FLOAT) {
                    this.writeNumber(p2.getFloatValue());
                    break;
                }
                this.writeNumber(p2.getDoubleValue());
                break;
            }
            case 9: {
                this.writeBoolean(true);
                break;
            }
            case 10: {
                this.writeBoolean(false);
                break;
            }
            case 11: {
                this.writeNull();
                break;
            }
            case 12: {
                this.writeObject(p2.getEmbeddedObject());
                break;
            }
            default: {
                this._throwInternal();
            }
        }
    }

    public void copyCurrentStructure(JsonParser p2) throws IOException {
        int id;
        JsonToken t2 = p2.currentToken();
        if (t2 == null) {
            this._reportError("No current event to copy");
        }
        if ((id = t2.id()) == 5) {
            this.writeFieldName(p2.getCurrentName());
            t2 = p2.nextToken();
            id = t2.id();
        }
        switch (id) {
            case 1: {
                this.writeStartObject();
                while (p2.nextToken() != JsonToken.END_OBJECT) {
                    this.copyCurrentStructure(p2);
                }
                this.writeEndObject();
                break;
            }
            case 3: {
                this.writeStartArray();
                while (p2.nextToken() != JsonToken.END_ARRAY) {
                    this.copyCurrentStructure(p2);
                }
                this.writeEndArray();
                break;
            }
            default: {
                this.copyCurrentEvent(p2);
            }
        }
    }

    public abstract JsonStreamContext getOutputContext();

    @Override
    public abstract void flush() throws IOException;

    public abstract boolean isClosed();

    @Override
    public abstract void close() throws IOException;

    protected void _reportError(String msg) throws JsonGenerationException {
        throw new JsonGenerationException(msg, this);
    }

    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by generator of type " + this.getClass().getName());
    }

    protected final void _verifyOffsets(int arrayLength, int offset, int length) {
        if (offset < 0 || offset + length > arrayLength) {
            throw new IllegalArgumentException(String.format("invalid argument(s) (offset=%d, length=%d) for input array of %d element", offset, length, arrayLength));
        }
    }

    protected void _writeSimpleObject(Object value) throws IOException {
        if (value == null) {
            this.writeNull();
            return;
        }
        if (value instanceof String) {
            this.writeString((String)value);
            return;
        }
        if (value instanceof Number) {
            Number n2 = (Number)value;
            if (n2 instanceof Integer) {
                this.writeNumber(n2.intValue());
                return;
            }
            if (n2 instanceof Long) {
                this.writeNumber(n2.longValue());
                return;
            }
            if (n2 instanceof Double) {
                this.writeNumber(n2.doubleValue());
                return;
            }
            if (n2 instanceof Float) {
                this.writeNumber(n2.floatValue());
                return;
            }
            if (n2 instanceof Short) {
                this.writeNumber(n2.shortValue());
                return;
            }
            if (n2 instanceof Byte) {
                this.writeNumber(n2.byteValue());
                return;
            }
            if (n2 instanceof BigInteger) {
                this.writeNumber((BigInteger)n2);
                return;
            }
            if (n2 instanceof BigDecimal) {
                this.writeNumber((BigDecimal)n2);
                return;
            }
            if (n2 instanceof AtomicInteger) {
                this.writeNumber(((AtomicInteger)n2).get());
                return;
            }
            if (n2 instanceof AtomicLong) {
                this.writeNumber(((AtomicLong)n2).get());
                return;
            }
        } else {
            if (value instanceof byte[]) {
                this.writeBinary((byte[])value);
                return;
            }
            if (value instanceof Boolean) {
                this.writeBoolean((Boolean)value);
                return;
            }
            if (value instanceof AtomicBoolean) {
                this.writeBoolean(((AtomicBoolean)value).get());
                return;
            }
        }
        throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + value.getClass().getName() + ")");
    }

    public static enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        FLUSH_PASSED_TO_STREAM(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        WRITE_BIGDECIMAL_AS_PLAIN(false),
        ESCAPE_NON_ASCII(false),
        STRICT_DUPLICATE_DETECTION(false),
        IGNORE_UNKNOWN(false);

        private final boolean _defaultState;
        private final int _mask;

        public static int collectDefaults() {
            int flags = 0;
            for (Feature f10 : Feature.values()) {
                if (!f10.enabledByDefault()) continue;
                flags |= f10.getMask();
            }
            return flags;
        }

        private Feature(boolean defaultState) {
            this._defaultState = defaultState;
            this._mask = 1 << this.ordinal();
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int flags) {
            return (flags & this._mask) != 0;
        }

        public int getMask() {
            return this._mask;
        }
    }
}

