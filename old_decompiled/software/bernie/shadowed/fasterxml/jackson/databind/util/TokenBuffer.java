/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TreeMap;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerationException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonLocation;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParseException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonStreamContext;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.core.TreeNode;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.core.base.ParserMinimalBase;
import software.bernie.shadowed.fasterxml.jackson.core.json.JsonWriteContext;
import software.bernie.shadowed.fasterxml.jackson.core.util.ByteArrayBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializable;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.PackageVersion;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.RawValue;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBufferReadContext;

public class TokenBuffer
extends JsonGenerator {
    protected static final int DEFAULT_GENERATOR_FEATURES = JsonGenerator.Feature.collectDefaults();
    protected ObjectCodec _objectCodec;
    protected JsonStreamContext _parentContext;
    protected int _generatorFeatures;
    protected boolean _closed;
    protected boolean _hasNativeTypeIds;
    protected boolean _hasNativeObjectIds;
    protected boolean _mayHaveNativeIds;
    protected boolean _forceBigDecimal;
    protected Segment _first;
    protected Segment _last;
    protected int _appendAt;
    protected Object _typeId;
    protected Object _objectId;
    protected boolean _hasNativeId = false;
    protected JsonWriteContext _writeContext;

    public TokenBuffer(ObjectCodec codec, boolean hasNativeIds) {
        this._objectCodec = codec;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURES;
        this._writeContext = JsonWriteContext.createRootContext(null);
        this._first = this._last = new Segment();
        this._appendAt = 0;
        this._hasNativeTypeIds = hasNativeIds;
        this._hasNativeObjectIds = hasNativeIds;
        this._mayHaveNativeIds = this._hasNativeTypeIds | this._hasNativeObjectIds;
    }

    public TokenBuffer(JsonParser p2) {
        this(p2, null);
    }

    public TokenBuffer(JsonParser p2, DeserializationContext ctxt) {
        this._objectCodec = p2.getCodec();
        this._parentContext = p2.getParsingContext();
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURES;
        this._writeContext = JsonWriteContext.createRootContext(null);
        this._first = this._last = new Segment();
        this._appendAt = 0;
        this._hasNativeTypeIds = p2.canReadTypeId();
        this._hasNativeObjectIds = p2.canReadObjectId();
        this._mayHaveNativeIds = this._hasNativeTypeIds | this._hasNativeObjectIds;
        this._forceBigDecimal = ctxt == null ? false : ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    }

    public static TokenBuffer asCopyOfValue(JsonParser p2) throws IOException {
        TokenBuffer b10 = new TokenBuffer(p2);
        b10.copyCurrentStructure(p2);
        return b10;
    }

    public TokenBuffer overrideParentContext(JsonStreamContext ctxt) {
        this._parentContext = ctxt;
        return this;
    }

    public TokenBuffer forceUseOfBigDecimal(boolean b10) {
        this._forceBigDecimal = b10;
        return this;
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    public JsonParser asParser() {
        return this.asParser(this._objectCodec);
    }

    public JsonParser asParserOnFirstToken() throws IOException {
        JsonParser p2 = this.asParser(this._objectCodec);
        p2.nextToken();
        return p2;
    }

    public JsonParser asParser(ObjectCodec codec) {
        return new Parser(this._first, codec, this._hasNativeTypeIds, this._hasNativeObjectIds, this._parentContext);
    }

    public JsonParser asParser(JsonParser src) {
        Parser p2 = new Parser(this._first, src.getCodec(), this._hasNativeTypeIds, this._hasNativeObjectIds, this._parentContext);
        p2.setLocation(src.getTokenLocation());
        return p2;
    }

    public JsonToken firstToken() {
        return this._first.type(0);
    }

    public TokenBuffer append(TokenBuffer other) throws IOException {
        if (!this._hasNativeTypeIds) {
            this._hasNativeTypeIds = other.canWriteTypeId();
        }
        if (!this._hasNativeObjectIds) {
            this._hasNativeObjectIds = other.canWriteObjectId();
        }
        this._mayHaveNativeIds = this._hasNativeTypeIds | this._hasNativeObjectIds;
        JsonParser p2 = other.asParser();
        while (p2.nextToken() != null) {
            this.copyCurrentStructure(p2);
        }
        return this;
    }

    public void serialize(JsonGenerator gen) throws IOException {
        boolean hasIds;
        Segment segment = this._first;
        int ptr = -1;
        boolean checkIds = this._mayHaveNativeIds;
        boolean bl2 = hasIds = checkIds && segment.hasIds();
        while (true) {
            JsonToken t2;
            if (++ptr >= 16) {
                ptr = 0;
                if ((segment = segment.next()) == null) break;
                boolean bl3 = hasIds = checkIds && segment.hasIds();
            }
            if ((t2 = segment.type(ptr)) == null) break;
            if (hasIds) {
                Object id = segment.findObjectId(ptr);
                if (id != null) {
                    gen.writeObjectId(id);
                }
                if ((id = segment.findTypeId(ptr)) != null) {
                    gen.writeTypeId(id);
                }
            }
            switch (t2) {
                case START_OBJECT: {
                    gen.writeStartObject();
                    break;
                }
                case END_OBJECT: {
                    gen.writeEndObject();
                    break;
                }
                case START_ARRAY: {
                    gen.writeStartArray();
                    break;
                }
                case END_ARRAY: {
                    gen.writeEndArray();
                    break;
                }
                case FIELD_NAME: {
                    Object ob = segment.get(ptr);
                    if (ob instanceof SerializableString) {
                        gen.writeFieldName((SerializableString)ob);
                        break;
                    }
                    gen.writeFieldName((String)ob);
                    break;
                }
                case VALUE_STRING: {
                    Object ob = segment.get(ptr);
                    if (ob instanceof SerializableString) {
                        gen.writeString((SerializableString)ob);
                        break;
                    }
                    gen.writeString((String)ob);
                    break;
                }
                case VALUE_NUMBER_INT: {
                    Object n2 = segment.get(ptr);
                    if (n2 instanceof Integer) {
                        gen.writeNumber((Integer)n2);
                        break;
                    }
                    if (n2 instanceof BigInteger) {
                        gen.writeNumber((BigInteger)n2);
                        break;
                    }
                    if (n2 instanceof Long) {
                        gen.writeNumber((Long)n2);
                        break;
                    }
                    if (n2 instanceof Short) {
                        gen.writeNumber((Short)n2);
                        break;
                    }
                    gen.writeNumber(((Number)n2).intValue());
                    break;
                }
                case VALUE_NUMBER_FLOAT: {
                    Object n2 = segment.get(ptr);
                    if (n2 instanceof Double) {
                        gen.writeNumber((Double)n2);
                        break;
                    }
                    if (n2 instanceof BigDecimal) {
                        gen.writeNumber((BigDecimal)n2);
                        break;
                    }
                    if (n2 instanceof Float) {
                        gen.writeNumber(((Float)n2).floatValue());
                        break;
                    }
                    if (n2 == null) {
                        gen.writeNull();
                        break;
                    }
                    if (n2 instanceof String) {
                        gen.writeNumber((String)n2);
                        break;
                    }
                    throw new JsonGenerationException(String.format("Unrecognized value type for VALUE_NUMBER_FLOAT: %s, cannot serialize", n2.getClass().getName()), gen);
                }
                case VALUE_TRUE: {
                    gen.writeBoolean(true);
                    break;
                }
                case VALUE_FALSE: {
                    gen.writeBoolean(false);
                    break;
                }
                case VALUE_NULL: {
                    gen.writeNull();
                    break;
                }
                case VALUE_EMBEDDED_OBJECT: {
                    Object value = segment.get(ptr);
                    if (value instanceof RawValue) {
                        ((RawValue)value).serialize(gen);
                        break;
                    }
                    if (value instanceof JsonSerializable) {
                        gen.writeObject(value);
                        break;
                    }
                    gen.writeEmbeddedObject(value);
                    break;
                }
                default: {
                    throw new RuntimeException("Internal error: should never end up through this code path");
                }
            }
        }
    }

    public TokenBuffer deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2;
        if (p2.getCurrentTokenId() != JsonToken.FIELD_NAME.id()) {
            this.copyCurrentStructure(p2);
            return this;
        }
        this.writeStartObject();
        do {
            this.copyCurrentStructure(p2);
        } while ((t2 = p2.nextToken()) == JsonToken.FIELD_NAME);
        if (t2 != JsonToken.END_OBJECT) {
            ctxt.reportWrongTokenException(TokenBuffer.class, JsonToken.END_OBJECT, "Expected END_OBJECT after copying contents of a JsonParser into TokenBuffer, got " + (Object)((Object)t2), new Object[0]);
        }
        this.writeEndObject();
        return this;
    }

    public String toString() {
        int MAX_COUNT = 100;
        StringBuilder sb = new StringBuilder();
        sb.append("[TokenBuffer: ");
        JsonParser jp = this.asParser();
        int count = 0;
        boolean hasNativeIds = this._hasNativeTypeIds || this._hasNativeObjectIds;
        while (true) {
            try {
                JsonToken t2 = jp.nextToken();
                if (t2 == null) break;
                if (hasNativeIds) {
                    this._appendNativeIds(sb);
                }
                if (count < 100) {
                    if (count > 0) {
                        sb.append(", ");
                    }
                    sb.append(t2.toString());
                    if (t2 == JsonToken.FIELD_NAME) {
                        sb.append('(');
                        sb.append(jp.getCurrentName());
                        sb.append(')');
                    }
                }
            }
            catch (IOException ioe) {
                throw new IllegalStateException(ioe);
            }
            ++count;
        }
        if (count >= 100) {
            sb.append(" ... (truncated ").append(count - 100).append(" entries)");
        }
        sb.append(']');
        return sb.toString();
    }

    private final void _appendNativeIds(StringBuilder sb) {
        Object typeId;
        Object objectId = this._last.findObjectId(this._appendAt - 1);
        if (objectId != null) {
            sb.append("[objectId=").append(String.valueOf(objectId)).append(']');
        }
        if ((typeId = this._last.findTypeId(this._appendAt - 1)) != null) {
            sb.append("[typeId=").append(String.valueOf(typeId)).append(']');
        }
    }

    @Override
    public JsonGenerator enable(JsonGenerator.Feature f10) {
        this._generatorFeatures |= f10.getMask();
        return this;
    }

    @Override
    public JsonGenerator disable(JsonGenerator.Feature f10) {
        this._generatorFeatures &= ~f10.getMask();
        return this;
    }

    @Override
    public boolean isEnabled(JsonGenerator.Feature f10) {
        return (this._generatorFeatures & f10.getMask()) != 0;
    }

    @Override
    public int getFeatureMask() {
        return this._generatorFeatures;
    }

    @Override
    @Deprecated
    public JsonGenerator setFeatureMask(int mask) {
        this._generatorFeatures = mask;
        return this;
    }

    @Override
    public JsonGenerator overrideStdFeatures(int values, int mask) {
        int oldState = this.getFeatureMask();
        this._generatorFeatures = oldState & ~mask | values & mask;
        return this;
    }

    @Override
    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    @Override
    public JsonGenerator setCodec(ObjectCodec oc) {
        this._objectCodec = oc;
        return this;
    }

    @Override
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override
    public final JsonWriteContext getOutputContext() {
        return this._writeContext;
    }

    @Override
    public boolean canWriteBinaryNatively() {
        return true;
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
        this._closed = true;
    }

    @Override
    public boolean isClosed() {
        return this._closed;
    }

    @Override
    public final void writeStartArray() throws IOException {
        this._writeContext.writeValue();
        this._append(JsonToken.START_ARRAY);
        this._writeContext = this._writeContext.createChildArrayContext();
    }

    @Override
    public final void writeEndArray() throws IOException {
        this._append(JsonToken.END_ARRAY);
        JsonWriteContext c10 = this._writeContext.getParent();
        if (c10 != null) {
            this._writeContext = c10;
        }
    }

    @Override
    public final void writeStartObject() throws IOException {
        this._writeContext.writeValue();
        this._append(JsonToken.START_OBJECT);
        this._writeContext = this._writeContext.createChildObjectContext();
    }

    @Override
    public void writeStartObject(Object forValue) throws IOException {
        JsonWriteContext ctxt;
        this._writeContext.writeValue();
        this._append(JsonToken.START_OBJECT);
        this._writeContext = ctxt = this._writeContext.createChildObjectContext();
        if (forValue != null) {
            ctxt.setCurrentValue(forValue);
        }
    }

    @Override
    public final void writeEndObject() throws IOException {
        this._append(JsonToken.END_OBJECT);
        JsonWriteContext c10 = this._writeContext.getParent();
        if (c10 != null) {
            this._writeContext = c10;
        }
    }

    @Override
    public final void writeFieldName(String name) throws IOException {
        this._writeContext.writeFieldName(name);
        this._append(JsonToken.FIELD_NAME, name);
    }

    @Override
    public void writeFieldName(SerializableString name) throws IOException {
        this._writeContext.writeFieldName(name.getValue());
        this._append(JsonToken.FIELD_NAME, name);
    }

    @Override
    public void writeString(String text) throws IOException {
        if (text == null) {
            this.writeNull();
        } else {
            this._appendValue(JsonToken.VALUE_STRING, text);
        }
    }

    @Override
    public void writeString(char[] text, int offset, int len) throws IOException {
        this.writeString(new String(text, offset, len));
    }

    @Override
    public void writeString(SerializableString text) throws IOException {
        if (text == null) {
            this.writeNull();
        } else {
            this._appendValue(JsonToken.VALUE_STRING, text);
        }
    }

    @Override
    public void writeRawUTF8String(byte[] text, int offset, int length) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeUTF8String(byte[] text, int offset, int length) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeRaw(String text) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeRaw(String text, int offset, int len) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeRaw(SerializableString text) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeRaw(char[] text, int offset, int len) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeRaw(char c10) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeRawValue(String text) throws IOException {
        this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, new RawValue(text));
    }

    @Override
    public void writeRawValue(String text, int offset, int len) throws IOException {
        if (offset > 0 || len != text.length()) {
            text = text.substring(offset, offset + len);
        }
        this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, new RawValue(text));
    }

    @Override
    public void writeRawValue(char[] text, int offset, int len) throws IOException {
        this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, new String(text, offset, len));
    }

    @Override
    public void writeNumber(short i2) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_INT, i2);
    }

    @Override
    public void writeNumber(int i2) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_INT, i2);
    }

    @Override
    public void writeNumber(long l2) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_INT, l2);
    }

    @Override
    public void writeNumber(double d10) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, d10);
    }

    @Override
    public void writeNumber(float f10) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f10));
    }

    @Override
    public void writeNumber(BigDecimal dec) throws IOException {
        if (dec == null) {
            this.writeNull();
        } else {
            this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, dec);
        }
    }

    @Override
    public void writeNumber(BigInteger v2) throws IOException {
        if (v2 == null) {
            this.writeNull();
        } else {
            this._appendValue(JsonToken.VALUE_NUMBER_INT, v2);
        }
    }

    @Override
    public void writeNumber(String encodedValue) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, encodedValue);
    }

    @Override
    public void writeBoolean(boolean state) throws IOException {
        this._appendValue(state ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
    }

    @Override
    public void writeNull() throws IOException {
        this._appendValue(JsonToken.VALUE_NULL);
    }

    @Override
    public void writeObject(Object value) throws IOException {
        if (value == null) {
            this.writeNull();
            return;
        }
        Class<?> raw = value.getClass();
        if (raw == byte[].class || value instanceof RawValue) {
            this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, value);
            return;
        }
        if (this._objectCodec == null) {
            this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, value);
        } else {
            this._objectCodec.writeValue(this, value);
        }
    }

    @Override
    public void writeTree(TreeNode node) throws IOException {
        if (node == null) {
            this.writeNull();
            return;
        }
        if (this._objectCodec == null) {
            this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, node);
        } else {
            this._objectCodec.writeTree(this, node);
        }
    }

    @Override
    public void writeBinary(Base64Variant b64variant, byte[] data, int offset, int len) throws IOException {
        byte[] copy = new byte[len];
        System.arraycopy(data, offset, copy, 0, len);
        this.writeObject(copy);
    }

    @Override
    public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canWriteTypeId() {
        return this._hasNativeTypeIds;
    }

    @Override
    public boolean canWriteObjectId() {
        return this._hasNativeObjectIds;
    }

    @Override
    public void writeTypeId(Object id) {
        this._typeId = id;
        this._hasNativeId = true;
    }

    @Override
    public void writeObjectId(Object id) {
        this._objectId = id;
        this._hasNativeId = true;
    }

    @Override
    public void writeEmbeddedObject(Object object) throws IOException {
        this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, object);
    }

    @Override
    public void copyCurrentEvent(JsonParser p2) throws IOException {
        if (this._mayHaveNativeIds) {
            this._checkNativeIds(p2);
        }
        block0 : switch (p2.getCurrentToken()) {
            case START_OBJECT: {
                this.writeStartObject();
                break;
            }
            case END_OBJECT: {
                this.writeEndObject();
                break;
            }
            case START_ARRAY: {
                this.writeStartArray();
                break;
            }
            case END_ARRAY: {
                this.writeEndArray();
                break;
            }
            case FIELD_NAME: {
                this.writeFieldName(p2.getCurrentName());
                break;
            }
            case VALUE_STRING: {
                if (p2.hasTextCharacters()) {
                    this.writeString(p2.getTextCharacters(), p2.getTextOffset(), p2.getTextLength());
                    break;
                }
                this.writeString(p2.getText());
                break;
            }
            case VALUE_NUMBER_INT: {
                switch (p2.getNumberType()) {
                    case INT: {
                        this.writeNumber(p2.getIntValue());
                        break block0;
                    }
                    case BIG_INTEGER: {
                        this.writeNumber(p2.getBigIntegerValue());
                        break block0;
                    }
                }
                this.writeNumber(p2.getLongValue());
                break;
            }
            case VALUE_NUMBER_FLOAT: {
                if (this._forceBigDecimal) {
                    this.writeNumber(p2.getDecimalValue());
                    break;
                }
                switch (p2.getNumberType()) {
                    case BIG_DECIMAL: {
                        this.writeNumber(p2.getDecimalValue());
                        break block0;
                    }
                    case FLOAT: {
                        this.writeNumber(p2.getFloatValue());
                        break block0;
                    }
                }
                this.writeNumber(p2.getDoubleValue());
                break;
            }
            case VALUE_TRUE: {
                this.writeBoolean(true);
                break;
            }
            case VALUE_FALSE: {
                this.writeBoolean(false);
                break;
            }
            case VALUE_NULL: {
                this.writeNull();
                break;
            }
            case VALUE_EMBEDDED_OBJECT: {
                this.writeObject(p2.getEmbeddedObject());
                break;
            }
            default: {
                throw new RuntimeException("Internal error: should never end up through this code path");
            }
        }
    }

    @Override
    public void copyCurrentStructure(JsonParser p2) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.FIELD_NAME) {
            if (this._mayHaveNativeIds) {
                this._checkNativeIds(p2);
            }
            this.writeFieldName(p2.getCurrentName());
            t2 = p2.nextToken();
        }
        if (this._mayHaveNativeIds) {
            this._checkNativeIds(p2);
        }
        switch (t2) {
            case START_ARRAY: {
                this.writeStartArray();
                while (p2.nextToken() != JsonToken.END_ARRAY) {
                    this.copyCurrentStructure(p2);
                }
                this.writeEndArray();
                break;
            }
            case START_OBJECT: {
                this.writeStartObject();
                while (p2.nextToken() != JsonToken.END_OBJECT) {
                    this.copyCurrentStructure(p2);
                }
                this.writeEndObject();
                break;
            }
            default: {
                this.copyCurrentEvent(p2);
            }
        }
    }

    private final void _checkNativeIds(JsonParser jp) throws IOException {
        this._typeId = jp.getTypeId();
        if (this._typeId != null) {
            this._hasNativeId = true;
        }
        if ((this._objectId = jp.getObjectId()) != null) {
            this._hasNativeId = true;
        }
    }

    protected final void _append(JsonToken type) {
        Segment next;
        Segment segment = next = this._hasNativeId ? this._last.append(this._appendAt, type, this._objectId, this._typeId) : this._last.append(this._appendAt, type);
        if (next == null) {
            ++this._appendAt;
        } else {
            this._last = next;
            this._appendAt = 1;
        }
    }

    protected final void _append(JsonToken type, Object value) {
        Segment next;
        Segment segment = next = this._hasNativeId ? this._last.append(this._appendAt, type, value, this._objectId, this._typeId) : this._last.append(this._appendAt, type, value);
        if (next == null) {
            ++this._appendAt;
        } else {
            this._last = next;
            this._appendAt = 1;
        }
    }

    protected final void _appendValue(JsonToken type) {
        Segment next;
        this._writeContext.writeValue();
        Segment segment = next = this._hasNativeId ? this._last.append(this._appendAt, type, this._objectId, this._typeId) : this._last.append(this._appendAt, type);
        if (next == null) {
            ++this._appendAt;
        } else {
            this._last = next;
            this._appendAt = 1;
        }
    }

    protected final void _appendValue(JsonToken type, Object value) {
        Segment next;
        this._writeContext.writeValue();
        Segment segment = next = this._hasNativeId ? this._last.append(this._appendAt, type, value, this._objectId, this._typeId) : this._last.append(this._appendAt, type, value);
        if (next == null) {
            ++this._appendAt;
        } else {
            this._last = next;
            this._appendAt = 1;
        }
    }

    @Override
    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    protected static final class Segment {
        public static final int TOKENS_PER_SEGMENT = 16;
        private static final JsonToken[] TOKEN_TYPES_BY_INDEX = new JsonToken[16];
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object[] _tokens = new Object[16];
        protected TreeMap<Integer, Object> _nativeIds;

        public JsonToken type(int index) {
            long l2 = this._tokenTypes;
            if (index > 0) {
                l2 >>= index << 2;
            }
            int ix = (int)l2 & 0xF;
            return TOKEN_TYPES_BY_INDEX[ix];
        }

        public int rawType(int index) {
            long l2 = this._tokenTypes;
            if (index > 0) {
                l2 >>= index << 2;
            }
            int ix = (int)l2 & 0xF;
            return ix;
        }

        public Object get(int index) {
            return this._tokens[index];
        }

        public Segment next() {
            return this._next;
        }

        public boolean hasIds() {
            return this._nativeIds != null;
        }

        public Segment append(int index, JsonToken tokenType) {
            if (index < 16) {
                this.set(index, tokenType);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, tokenType);
            return this._next;
        }

        public Segment append(int index, JsonToken tokenType, Object objectId, Object typeId) {
            if (index < 16) {
                this.set(index, tokenType, objectId, typeId);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, tokenType, objectId, typeId);
            return this._next;
        }

        public Segment append(int index, JsonToken tokenType, Object value) {
            if (index < 16) {
                this.set(index, tokenType, value);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, tokenType, value);
            return this._next;
        }

        public Segment append(int index, JsonToken tokenType, Object value, Object objectId, Object typeId) {
            if (index < 16) {
                this.set(index, tokenType, value, objectId, typeId);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, tokenType, value, objectId, typeId);
            return this._next;
        }

        private void set(int index, JsonToken tokenType) {
            long typeCode = tokenType.ordinal();
            if (index > 0) {
                typeCode <<= index << 2;
            }
            this._tokenTypes |= typeCode;
        }

        private void set(int index, JsonToken tokenType, Object objectId, Object typeId) {
            long typeCode = tokenType.ordinal();
            if (index > 0) {
                typeCode <<= index << 2;
            }
            this._tokenTypes |= typeCode;
            this.assignNativeIds(index, objectId, typeId);
        }

        private void set(int index, JsonToken tokenType, Object value) {
            this._tokens[index] = value;
            long typeCode = tokenType.ordinal();
            if (index > 0) {
                typeCode <<= index << 2;
            }
            this._tokenTypes |= typeCode;
        }

        private void set(int index, JsonToken tokenType, Object value, Object objectId, Object typeId) {
            this._tokens[index] = value;
            long typeCode = tokenType.ordinal();
            if (index > 0) {
                typeCode <<= index << 2;
            }
            this._tokenTypes |= typeCode;
            this.assignNativeIds(index, objectId, typeId);
        }

        private final void assignNativeIds(int index, Object objectId, Object typeId) {
            if (this._nativeIds == null) {
                this._nativeIds = new TreeMap();
            }
            if (objectId != null) {
                this._nativeIds.put(this._objectIdIndex(index), objectId);
            }
            if (typeId != null) {
                this._nativeIds.put(this._typeIdIndex(index), typeId);
            }
        }

        private Object findObjectId(int index) {
            return this._nativeIds == null ? null : this._nativeIds.get(this._objectIdIndex(index));
        }

        private Object findTypeId(int index) {
            return this._nativeIds == null ? null : this._nativeIds.get(this._typeIdIndex(index));
        }

        private final int _typeIdIndex(int i2) {
            return i2 + i2;
        }

        private final int _objectIdIndex(int i2) {
            return i2 + i2 + 1;
        }

        static {
            JsonToken[] t2 = JsonToken.values();
            System.arraycopy(t2, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, t2.length - 1));
        }
    }

    protected static final class Parser
    extends ParserMinimalBase {
        protected ObjectCodec _codec;
        protected final boolean _hasNativeTypeIds;
        protected final boolean _hasNativeObjectIds;
        protected final boolean _hasNativeIds;
        protected Segment _segment;
        protected int _segmentPtr;
        protected TokenBufferReadContext _parsingContext;
        protected boolean _closed;
        protected transient ByteArrayBuilder _byteBuilder;
        protected JsonLocation _location = null;

        @Deprecated
        public Parser(Segment firstSeg, ObjectCodec codec, boolean hasNativeTypeIds, boolean hasNativeObjectIds) {
            this(firstSeg, codec, hasNativeTypeIds, hasNativeObjectIds, null);
        }

        public Parser(Segment firstSeg, ObjectCodec codec, boolean hasNativeTypeIds, boolean hasNativeObjectIds, JsonStreamContext parentContext) {
            super(0);
            this._segment = firstSeg;
            this._segmentPtr = -1;
            this._codec = codec;
            this._parsingContext = TokenBufferReadContext.createRootContext(parentContext);
            this._hasNativeTypeIds = hasNativeTypeIds;
            this._hasNativeObjectIds = hasNativeObjectIds;
            this._hasNativeIds = hasNativeTypeIds | hasNativeObjectIds;
        }

        public void setLocation(JsonLocation l2) {
            this._location = l2;
        }

        @Override
        public ObjectCodec getCodec() {
            return this._codec;
        }

        @Override
        public void setCodec(ObjectCodec c10) {
            this._codec = c10;
        }

        @Override
        public Version version() {
            return PackageVersion.VERSION;
        }

        public JsonToken peekNextToken() throws IOException {
            if (this._closed) {
                return null;
            }
            Segment seg = this._segment;
            int ptr = this._segmentPtr + 1;
            if (ptr >= 16) {
                ptr = 0;
                seg = seg == null ? null : seg.next();
            }
            return seg == null ? null : seg.type(ptr);
        }

        @Override
        public void close() throws IOException {
            if (!this._closed) {
                this._closed = true;
            }
        }

        @Override
        public JsonToken nextToken() throws IOException {
            if (this._closed || this._segment == null) {
                return null;
            }
            if (++this._segmentPtr >= 16) {
                this._segmentPtr = 0;
                this._segment = this._segment.next();
                if (this._segment == null) {
                    return null;
                }
            }
            this._currToken = this._segment.type(this._segmentPtr);
            if (this._currToken == JsonToken.FIELD_NAME) {
                Object ob = this._currentObject();
                String name = ob instanceof String ? (String)ob : ob.toString();
                this._parsingContext.setCurrentName(name);
            } else if (this._currToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext();
            } else if (this._currToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext();
            } else if (this._currToken == JsonToken.END_OBJECT || this._currToken == JsonToken.END_ARRAY) {
                this._parsingContext = this._parsingContext.parentOrCopy();
            }
            return this._currToken;
        }

        @Override
        public String nextFieldName() throws IOException {
            if (this._closed || this._segment == null) {
                return null;
            }
            int ptr = this._segmentPtr + 1;
            if (ptr < 16 && this._segment.type(ptr) == JsonToken.FIELD_NAME) {
                this._segmentPtr = ptr;
                Object ob = this._segment.get(ptr);
                String name = ob instanceof String ? (String)ob : ob.toString();
                this._parsingContext.setCurrentName(name);
                return name;
            }
            return this.nextToken() == JsonToken.FIELD_NAME ? this.getCurrentName() : null;
        }

        @Override
        public boolean isClosed() {
            return this._closed;
        }

        @Override
        public JsonStreamContext getParsingContext() {
            return this._parsingContext;
        }

        @Override
        public JsonLocation getTokenLocation() {
            return this.getCurrentLocation();
        }

        @Override
        public JsonLocation getCurrentLocation() {
            return this._location == null ? JsonLocation.NA : this._location;
        }

        @Override
        public String getCurrentName() {
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                JsonStreamContext parent = this._parsingContext.getParent();
                return parent.getCurrentName();
            }
            return this._parsingContext.getCurrentName();
        }

        @Override
        public void overrideCurrentName(String name) {
            JsonStreamContext ctxt = this._parsingContext;
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                ctxt = ctxt.getParent();
            }
            if (ctxt instanceof TokenBufferReadContext) {
                try {
                    ((TokenBufferReadContext)ctxt).setCurrentName(name);
                }
                catch (IOException e10) {
                    throw new RuntimeException(e10);
                }
            }
        }

        @Override
        public String getText() {
            if (this._currToken == JsonToken.VALUE_STRING || this._currToken == JsonToken.FIELD_NAME) {
                Object ob = this._currentObject();
                if (ob instanceof String) {
                    return (String)ob;
                }
                return ClassUtil.nullOrToString(ob);
            }
            if (this._currToken == null) {
                return null;
            }
            switch (this._currToken) {
                case VALUE_NUMBER_INT: 
                case VALUE_NUMBER_FLOAT: {
                    return ClassUtil.nullOrToString(this._currentObject());
                }
            }
            return this._currToken.asString();
        }

        @Override
        public char[] getTextCharacters() {
            String str = this.getText();
            return str == null ? null : str.toCharArray();
        }

        @Override
        public int getTextLength() {
            String str = this.getText();
            return str == null ? 0 : str.length();
        }

        @Override
        public int getTextOffset() {
            return 0;
        }

        @Override
        public boolean hasTextCharacters() {
            return false;
        }

        @Override
        public boolean isNaN() {
            if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
                Object value = this._currentObject();
                if (value instanceof Double) {
                    Double v2 = (Double)value;
                    return v2.isNaN() || v2.isInfinite();
                }
                if (value instanceof Float) {
                    Float v3 = (Float)value;
                    return v3.isNaN() || v3.isInfinite();
                }
            }
            return false;
        }

        @Override
        public BigInteger getBigIntegerValue() throws IOException {
            Number n2 = this.getNumberValue();
            if (n2 instanceof BigInteger) {
                return (BigInteger)n2;
            }
            if (this.getNumberType() == JsonParser.NumberType.BIG_DECIMAL) {
                return ((BigDecimal)n2).toBigInteger();
            }
            return BigInteger.valueOf(n2.longValue());
        }

        @Override
        public BigDecimal getDecimalValue() throws IOException {
            Number n2 = this.getNumberValue();
            if (n2 instanceof BigDecimal) {
                return (BigDecimal)n2;
            }
            switch (this.getNumberType()) {
                case INT: 
                case LONG: {
                    return BigDecimal.valueOf(n2.longValue());
                }
                case BIG_INTEGER: {
                    return new BigDecimal((BigInteger)n2);
                }
            }
            return BigDecimal.valueOf(n2.doubleValue());
        }

        @Override
        public double getDoubleValue() throws IOException {
            return this.getNumberValue().doubleValue();
        }

        @Override
        public float getFloatValue() throws IOException {
            return this.getNumberValue().floatValue();
        }

        @Override
        public int getIntValue() throws IOException {
            if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
                return ((Number)this._currentObject()).intValue();
            }
            return this.getNumberValue().intValue();
        }

        @Override
        public long getLongValue() throws IOException {
            return this.getNumberValue().longValue();
        }

        @Override
        public JsonParser.NumberType getNumberType() throws IOException {
            Number n2 = this.getNumberValue();
            if (n2 instanceof Integer) {
                return JsonParser.NumberType.INT;
            }
            if (n2 instanceof Long) {
                return JsonParser.NumberType.LONG;
            }
            if (n2 instanceof Double) {
                return JsonParser.NumberType.DOUBLE;
            }
            if (n2 instanceof BigDecimal) {
                return JsonParser.NumberType.BIG_DECIMAL;
            }
            if (n2 instanceof BigInteger) {
                return JsonParser.NumberType.BIG_INTEGER;
            }
            if (n2 instanceof Float) {
                return JsonParser.NumberType.FLOAT;
            }
            if (n2 instanceof Short) {
                return JsonParser.NumberType.INT;
            }
            return null;
        }

        @Override
        public final Number getNumberValue() throws IOException {
            this._checkIsNumber();
            Object value = this._currentObject();
            if (value instanceof Number) {
                return (Number)value;
            }
            if (value instanceof String) {
                String str = (String)value;
                if (str.indexOf(46) >= 0) {
                    return Double.parseDouble(str);
                }
                return Long.parseLong(str);
            }
            if (value == null) {
                return null;
            }
            throw new IllegalStateException("Internal error: entry should be a Number, but is of type " + value.getClass().getName());
        }

        @Override
        public Object getEmbeddedObject() {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return this._currentObject();
            }
            return null;
        }

        @Override
        public byte[] getBinaryValue(Base64Variant b64variant) throws IOException, JsonParseException {
            Object ob;
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT && (ob = this._currentObject()) instanceof byte[]) {
                return (byte[])ob;
            }
            if (this._currToken != JsonToken.VALUE_STRING) {
                throw this._constructError("Current token (" + (Object)((Object)this._currToken) + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), cannot access as binary");
            }
            String str = this.getText();
            if (str == null) {
                return null;
            }
            ByteArrayBuilder builder = this._byteBuilder;
            if (builder == null) {
                this._byteBuilder = builder = new ByteArrayBuilder(100);
            } else {
                this._byteBuilder.reset();
            }
            this._decodeBase64(str, builder, b64variant);
            return builder.toByteArray();
        }

        @Override
        public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException {
            byte[] data = this.getBinaryValue(b64variant);
            if (data != null) {
                out.write(data, 0, data.length);
                return data.length;
            }
            return 0;
        }

        @Override
        public boolean canReadObjectId() {
            return this._hasNativeObjectIds;
        }

        @Override
        public boolean canReadTypeId() {
            return this._hasNativeTypeIds;
        }

        @Override
        public Object getTypeId() {
            return this._segment.findTypeId(this._segmentPtr);
        }

        @Override
        public Object getObjectId() {
            return this._segment.findObjectId(this._segmentPtr);
        }

        protected final Object _currentObject() {
            return this._segment.get(this._segmentPtr);
        }

        protected final void _checkIsNumber() throws JsonParseException {
            if (this._currToken == null || !this._currToken.isNumeric()) {
                throw this._constructError("Current token (" + (Object)((Object)this._currToken) + ") not numeric, cannot use numeric value accessors");
            }
        }

        @Override
        protected void _handleEOF() throws JsonParseException {
            this._throwInternal();
        }
    }
}

