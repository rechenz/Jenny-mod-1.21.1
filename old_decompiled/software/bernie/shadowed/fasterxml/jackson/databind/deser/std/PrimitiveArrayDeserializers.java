/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.Nulls;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variants;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParseException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.InvalidNullException;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.AccessPattern;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ArrayBuilders;

public abstract class PrimitiveArrayDeserializers<T>
extends StdDeserializer<T>
implements ContextualDeserializer {
    protected final Boolean _unwrapSingle;
    private transient Object _emptyValue;
    protected final NullValueProvider _nuller;

    protected PrimitiveArrayDeserializers(Class<T> cls) {
        super(cls);
        this._unwrapSingle = null;
        this._nuller = null;
    }

    protected PrimitiveArrayDeserializers(PrimitiveArrayDeserializers<?> base, NullValueProvider nuller, Boolean unwrapSingle) {
        super(base._valueClass);
        this._unwrapSingle = unwrapSingle;
        this._nuller = nuller;
    }

    public static JsonDeserializer<?> forType(Class<?> rawType) {
        if (rawType == Integer.TYPE) {
            return IntDeser.instance;
        }
        if (rawType == Long.TYPE) {
            return LongDeser.instance;
        }
        if (rawType == Byte.TYPE) {
            return new ByteDeser();
        }
        if (rawType == Short.TYPE) {
            return new ShortDeser();
        }
        if (rawType == Float.TYPE) {
            return new FloatDeser();
        }
        if (rawType == Double.TYPE) {
            return new DoubleDeser();
        }
        if (rawType == Boolean.TYPE) {
            return new BooleanDeser();
        }
        if (rawType == Character.TYPE) {
            return new CharDeser();
        }
        throw new IllegalStateException();
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Boolean unwrapSingle = this.findFormatFeature(ctxt, property, this._valueClass, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        NullValueProvider nuller = null;
        Nulls nullStyle = this.findContentNullStyle(ctxt, property);
        if (nullStyle == Nulls.SKIP) {
            nuller = NullsConstantProvider.skipper();
        } else if (nullStyle == Nulls.FAIL) {
            nuller = property == null ? NullsFailProvider.constructForRootValue(ctxt.constructType(this._valueClass)) : NullsFailProvider.constructForProperty(property);
        }
        if (unwrapSingle == this._unwrapSingle && nuller == this._nuller) {
            return this;
        }
        return this.withResolved(nuller, unwrapSingle);
    }

    protected abstract T _concat(T var1, T var2);

    protected abstract T handleSingleElementUnwrapped(JsonParser var1, DeserializationContext var2) throws IOException;

    protected abstract PrimitiveArrayDeserializers<?> withResolved(NullValueProvider var1, Boolean var2);

    protected abstract T _constructEmpty();

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.TRUE;
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        Object empty = this._emptyValue;
        if (empty == null) {
            this._emptyValue = empty = this._constructEmpty();
        }
        return empty;
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(p2, ctxt);
    }

    @Override
    public T deserialize(JsonParser p2, DeserializationContext ctxt, T existing) throws IOException {
        Object newValue = this.deserialize(p2, ctxt);
        if (existing == null) {
            return newValue;
        }
        int len = Array.getLength(existing);
        if (len == 0) {
            return newValue;
        }
        return this._concat(existing, newValue);
    }

    protected T handleNonArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        boolean canWrap;
        if (p2.hasToken(JsonToken.VALUE_STRING) && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && p2.getText().length() == 0) {
            return null;
        }
        boolean bl2 = canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (canWrap) {
            return this.handleSingleElementUnwrapped(p2, ctxt);
        }
        return (T)ctxt.handleUnexpectedToken(this._valueClass, p2);
    }

    protected void _failOnNull(DeserializationContext ctxt) throws IOException {
        throw InvalidNullException.from(ctxt, null, ctxt.constructType(this._valueClass));
    }

    @JacksonStdImpl
    static final class DoubleDeser
    extends PrimitiveArrayDeserializers<double[]> {
        private static final long serialVersionUID = 1L;

        public DoubleDeser() {
            super(double[].class);
        }

        protected DoubleDeser(DoubleDeser base, NullValueProvider nuller, Boolean unwrapSingle) {
            super(base, nuller, unwrapSingle);
        }

        @Override
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nuller, Boolean unwrapSingle) {
            return new DoubleDeser(this, nuller, unwrapSingle);
        }

        @Override
        protected double[] _constructEmpty() {
            return new double[0];
        }

        @Override
        public double[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (!p2.isExpectedStartArrayToken()) {
                return (double[])this.handleNonArray(p2, ctxt);
            }
            ArrayBuilders.DoubleBuilder builder = ctxt.getArrayBuilders().getDoubleBuilder();
            double[] chunk = (double[])builder.resetAndStart();
            int ix = 0;
            try {
                JsonToken t2;
                while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                    if (t2 == JsonToken.VALUE_NULL && this._nuller != null) {
                        this._nuller.getNullValue(ctxt);
                        continue;
                    }
                    double value = this._parseDoublePrimitive(p2, ctxt);
                    if (ix >= chunk.length) {
                        chunk = builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    chunk[ix++] = value;
                }
            }
            catch (Exception e10) {
                throw JsonMappingException.wrapWithPath((Throwable)e10, (Object)chunk, builder.bufferedSize() + ix);
            }
            return builder.completeAndClearBuffer(chunk, ix);
        }

        @Override
        protected double[] handleSingleElementUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
            return new double[]{this._parseDoublePrimitive(p2, ctxt)};
        }

        @Override
        protected double[] _concat(double[] oldValue, double[] newValue) {
            int len1 = oldValue.length;
            int len2 = newValue.length;
            double[] result = Arrays.copyOf(oldValue, len1 + len2);
            System.arraycopy(newValue, 0, result, len1, len2);
            return result;
        }
    }

    @JacksonStdImpl
    static final class FloatDeser
    extends PrimitiveArrayDeserializers<float[]> {
        private static final long serialVersionUID = 1L;

        public FloatDeser() {
            super(float[].class);
        }

        protected FloatDeser(FloatDeser base, NullValueProvider nuller, Boolean unwrapSingle) {
            super(base, nuller, unwrapSingle);
        }

        @Override
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nuller, Boolean unwrapSingle) {
            return new FloatDeser(this, nuller, unwrapSingle);
        }

        @Override
        protected float[] _constructEmpty() {
            return new float[0];
        }

        @Override
        public float[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (!p2.isExpectedStartArrayToken()) {
                return (float[])this.handleNonArray(p2, ctxt);
            }
            ArrayBuilders.FloatBuilder builder = ctxt.getArrayBuilders().getFloatBuilder();
            float[] chunk = (float[])builder.resetAndStart();
            int ix = 0;
            try {
                JsonToken t2;
                while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                    if (t2 == JsonToken.VALUE_NULL && this._nuller != null) {
                        this._nuller.getNullValue(ctxt);
                        continue;
                    }
                    float value = this._parseFloatPrimitive(p2, ctxt);
                    if (ix >= chunk.length) {
                        chunk = builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    chunk[ix++] = value;
                }
            }
            catch (Exception e10) {
                throw JsonMappingException.wrapWithPath((Throwable)e10, (Object)chunk, builder.bufferedSize() + ix);
            }
            return builder.completeAndClearBuffer(chunk, ix);
        }

        @Override
        protected float[] handleSingleElementUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
            return new float[]{this._parseFloatPrimitive(p2, ctxt)};
        }

        @Override
        protected float[] _concat(float[] oldValue, float[] newValue) {
            int len1 = oldValue.length;
            int len2 = newValue.length;
            float[] result = Arrays.copyOf(oldValue, len1 + len2);
            System.arraycopy(newValue, 0, result, len1, len2);
            return result;
        }
    }

    @JacksonStdImpl
    static final class LongDeser
    extends PrimitiveArrayDeserializers<long[]> {
        private static final long serialVersionUID = 1L;
        public static final LongDeser instance = new LongDeser();

        public LongDeser() {
            super(long[].class);
        }

        protected LongDeser(LongDeser base, NullValueProvider nuller, Boolean unwrapSingle) {
            super(base, nuller, unwrapSingle);
        }

        @Override
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nuller, Boolean unwrapSingle) {
            return new LongDeser(this, nuller, unwrapSingle);
        }

        @Override
        protected long[] _constructEmpty() {
            return new long[0];
        }

        @Override
        public long[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (!p2.isExpectedStartArrayToken()) {
                return (long[])this.handleNonArray(p2, ctxt);
            }
            ArrayBuilders.LongBuilder builder = ctxt.getArrayBuilders().getLongBuilder();
            long[] chunk = (long[])builder.resetAndStart();
            int ix = 0;
            try {
                JsonToken t2;
                while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                    long value;
                    if (t2 == JsonToken.VALUE_NUMBER_INT) {
                        value = p2.getLongValue();
                    } else if (t2 == JsonToken.VALUE_NULL) {
                        if (this._nuller != null) {
                            this._nuller.getNullValue(ctxt);
                            continue;
                        }
                        this._verifyNullForPrimitive(ctxt);
                        value = 0L;
                    } else {
                        value = this._parseLongPrimitive(p2, ctxt);
                    }
                    if (ix >= chunk.length) {
                        chunk = builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    chunk[ix++] = value;
                }
            }
            catch (Exception e10) {
                throw JsonMappingException.wrapWithPath((Throwable)e10, (Object)chunk, builder.bufferedSize() + ix);
            }
            return builder.completeAndClearBuffer(chunk, ix);
        }

        @Override
        protected long[] handleSingleElementUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
            return new long[]{this._parseLongPrimitive(p2, ctxt)};
        }

        @Override
        protected long[] _concat(long[] oldValue, long[] newValue) {
            int len1 = oldValue.length;
            int len2 = newValue.length;
            long[] result = Arrays.copyOf(oldValue, len1 + len2);
            System.arraycopy(newValue, 0, result, len1, len2);
            return result;
        }
    }

    @JacksonStdImpl
    static final class IntDeser
    extends PrimitiveArrayDeserializers<int[]> {
        private static final long serialVersionUID = 1L;
        public static final IntDeser instance = new IntDeser();

        public IntDeser() {
            super(int[].class);
        }

        protected IntDeser(IntDeser base, NullValueProvider nuller, Boolean unwrapSingle) {
            super(base, nuller, unwrapSingle);
        }

        @Override
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nuller, Boolean unwrapSingle) {
            return new IntDeser(this, nuller, unwrapSingle);
        }

        @Override
        protected int[] _constructEmpty() {
            return new int[0];
        }

        @Override
        public int[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (!p2.isExpectedStartArrayToken()) {
                return (int[])this.handleNonArray(p2, ctxt);
            }
            ArrayBuilders.IntBuilder builder = ctxt.getArrayBuilders().getIntBuilder();
            int[] chunk = (int[])builder.resetAndStart();
            int ix = 0;
            try {
                JsonToken t2;
                while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                    int value;
                    if (t2 == JsonToken.VALUE_NUMBER_INT) {
                        value = p2.getIntValue();
                    } else if (t2 == JsonToken.VALUE_NULL) {
                        if (this._nuller != null) {
                            this._nuller.getNullValue(ctxt);
                            continue;
                        }
                        this._verifyNullForPrimitive(ctxt);
                        value = 0;
                    } else {
                        value = this._parseIntPrimitive(p2, ctxt);
                    }
                    if (ix >= chunk.length) {
                        chunk = builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    chunk[ix++] = value;
                }
            }
            catch (Exception e10) {
                throw JsonMappingException.wrapWithPath((Throwable)e10, (Object)chunk, builder.bufferedSize() + ix);
            }
            return builder.completeAndClearBuffer(chunk, ix);
        }

        @Override
        protected int[] handleSingleElementUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
            return new int[]{this._parseIntPrimitive(p2, ctxt)};
        }

        @Override
        protected int[] _concat(int[] oldValue, int[] newValue) {
            int len1 = oldValue.length;
            int len2 = newValue.length;
            int[] result = Arrays.copyOf(oldValue, len1 + len2);
            System.arraycopy(newValue, 0, result, len1, len2);
            return result;
        }
    }

    @JacksonStdImpl
    static final class ShortDeser
    extends PrimitiveArrayDeserializers<short[]> {
        private static final long serialVersionUID = 1L;

        public ShortDeser() {
            super(short[].class);
        }

        protected ShortDeser(ShortDeser base, NullValueProvider nuller, Boolean unwrapSingle) {
            super(base, nuller, unwrapSingle);
        }

        @Override
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nuller, Boolean unwrapSingle) {
            return new ShortDeser(this, nuller, unwrapSingle);
        }

        @Override
        protected short[] _constructEmpty() {
            return new short[0];
        }

        @Override
        public short[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (!p2.isExpectedStartArrayToken()) {
                return (short[])this.handleNonArray(p2, ctxt);
            }
            ArrayBuilders.ShortBuilder builder = ctxt.getArrayBuilders().getShortBuilder();
            short[] chunk = (short[])builder.resetAndStart();
            int ix = 0;
            try {
                JsonToken t2;
                while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                    short value;
                    if (t2 == JsonToken.VALUE_NULL) {
                        if (this._nuller != null) {
                            this._nuller.getNullValue(ctxt);
                            continue;
                        }
                        this._verifyNullForPrimitive(ctxt);
                        value = 0;
                    } else {
                        value = this._parseShortPrimitive(p2, ctxt);
                    }
                    if (ix >= chunk.length) {
                        chunk = builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    chunk[ix++] = value;
                }
            }
            catch (Exception e10) {
                throw JsonMappingException.wrapWithPath((Throwable)e10, (Object)chunk, builder.bufferedSize() + ix);
            }
            return builder.completeAndClearBuffer(chunk, ix);
        }

        @Override
        protected short[] handleSingleElementUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
            return new short[]{this._parseShortPrimitive(p2, ctxt)};
        }

        @Override
        protected short[] _concat(short[] oldValue, short[] newValue) {
            int len1 = oldValue.length;
            int len2 = newValue.length;
            short[] result = Arrays.copyOf(oldValue, len1 + len2);
            System.arraycopy(newValue, 0, result, len1, len2);
            return result;
        }
    }

    @JacksonStdImpl
    static final class ByteDeser
    extends PrimitiveArrayDeserializers<byte[]> {
        private static final long serialVersionUID = 1L;

        public ByteDeser() {
            super(byte[].class);
        }

        protected ByteDeser(ByteDeser base, NullValueProvider nuller, Boolean unwrapSingle) {
            super(base, nuller, unwrapSingle);
        }

        @Override
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nuller, Boolean unwrapSingle) {
            return new ByteDeser(this, nuller, unwrapSingle);
        }

        @Override
        protected byte[] _constructEmpty() {
            return new byte[0];
        }

        @Override
        public byte[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            JsonToken t2;
            block16: {
                t2 = p2.getCurrentToken();
                if (t2 == JsonToken.VALUE_STRING) {
                    try {
                        return p2.getBinaryValue(ctxt.getBase64Variant());
                    }
                    catch (JsonParseException e10) {
                        String msg = e10.getOriginalMessage();
                        if (!msg.contains("base64")) break block16;
                        return (byte[])ctxt.handleWeirdStringValue(byte[].class, p2.getText(), msg, new Object[0]);
                    }
                }
            }
            if (t2 == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object ob = p2.getEmbeddedObject();
                if (ob == null) {
                    return null;
                }
                if (ob instanceof byte[]) {
                    return (byte[])ob;
                }
            }
            if (!p2.isExpectedStartArrayToken()) {
                return (byte[])this.handleNonArray(p2, ctxt);
            }
            ArrayBuilders.ByteBuilder builder = ctxt.getArrayBuilders().getByteBuilder();
            byte[] chunk = (byte[])builder.resetAndStart();
            int ix = 0;
            try {
                while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                    byte value;
                    if (t2 == JsonToken.VALUE_NUMBER_INT || t2 == JsonToken.VALUE_NUMBER_FLOAT) {
                        value = p2.getByteValue();
                    } else if (t2 == JsonToken.VALUE_NULL) {
                        if (this._nuller != null) {
                            this._nuller.getNullValue(ctxt);
                            continue;
                        }
                        this._verifyNullForPrimitive(ctxt);
                        value = 0;
                    } else {
                        value = this._parseBytePrimitive(p2, ctxt);
                    }
                    if (ix >= chunk.length) {
                        chunk = builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    chunk[ix++] = value;
                }
            }
            catch (Exception e11) {
                throw JsonMappingException.wrapWithPath((Throwable)e11, (Object)chunk, builder.bufferedSize() + ix);
            }
            return builder.completeAndClearBuffer(chunk, ix);
        }

        @Override
        protected byte[] handleSingleElementUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
            byte value;
            JsonToken t2 = p2.getCurrentToken();
            if (t2 == JsonToken.VALUE_NUMBER_INT || t2 == JsonToken.VALUE_NUMBER_FLOAT) {
                value = p2.getByteValue();
            } else {
                if (t2 == JsonToken.VALUE_NULL) {
                    if (this._nuller != null) {
                        this._nuller.getNullValue(ctxt);
                        return (byte[])this.getEmptyValue(ctxt);
                    }
                    this._verifyNullForPrimitive(ctxt);
                    return null;
                }
                Number n2 = (Number)ctxt.handleUnexpectedToken(this._valueClass.getComponentType(), p2);
                value = n2.byteValue();
            }
            return new byte[]{value};
        }

        @Override
        protected byte[] _concat(byte[] oldValue, byte[] newValue) {
            int len1 = oldValue.length;
            int len2 = newValue.length;
            byte[] result = Arrays.copyOf(oldValue, len1 + len2);
            System.arraycopy(newValue, 0, result, len1, len2);
            return result;
        }
    }

    @JacksonStdImpl
    static final class BooleanDeser
    extends PrimitiveArrayDeserializers<boolean[]> {
        private static final long serialVersionUID = 1L;

        public BooleanDeser() {
            super(boolean[].class);
        }

        protected BooleanDeser(BooleanDeser base, NullValueProvider nuller, Boolean unwrapSingle) {
            super(base, nuller, unwrapSingle);
        }

        @Override
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nuller, Boolean unwrapSingle) {
            return new BooleanDeser(this, nuller, unwrapSingle);
        }

        @Override
        protected boolean[] _constructEmpty() {
            return new boolean[0];
        }

        @Override
        public boolean[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (!p2.isExpectedStartArrayToken()) {
                return (boolean[])this.handleNonArray(p2, ctxt);
            }
            ArrayBuilders.BooleanBuilder builder = ctxt.getArrayBuilders().getBooleanBuilder();
            boolean[] chunk = (boolean[])builder.resetAndStart();
            int ix = 0;
            try {
                JsonToken t2;
                while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                    boolean value;
                    if (t2 == JsonToken.VALUE_TRUE) {
                        value = true;
                    } else if (t2 == JsonToken.VALUE_FALSE) {
                        value = false;
                    } else if (t2 == JsonToken.VALUE_NULL) {
                        if (this._nuller != null) {
                            this._nuller.getNullValue(ctxt);
                            continue;
                        }
                        this._verifyNullForPrimitive(ctxt);
                        value = false;
                    } else {
                        value = this._parseBooleanPrimitive(p2, ctxt);
                    }
                    if (ix >= chunk.length) {
                        chunk = builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    chunk[ix++] = value;
                }
            }
            catch (Exception e10) {
                throw JsonMappingException.wrapWithPath((Throwable)e10, (Object)chunk, builder.bufferedSize() + ix);
            }
            return builder.completeAndClearBuffer(chunk, ix);
        }

        @Override
        protected boolean[] handleSingleElementUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
            return new boolean[]{this._parseBooleanPrimitive(p2, ctxt)};
        }

        @Override
        protected boolean[] _concat(boolean[] oldValue, boolean[] newValue) {
            int len1 = oldValue.length;
            int len2 = newValue.length;
            boolean[] result = Arrays.copyOf(oldValue, len1 + len2);
            System.arraycopy(newValue, 0, result, len1, len2);
            return result;
        }
    }

    @JacksonStdImpl
    static final class CharDeser
    extends PrimitiveArrayDeserializers<char[]> {
        private static final long serialVersionUID = 1L;

        public CharDeser() {
            super(char[].class);
        }

        protected CharDeser(CharDeser base, NullValueProvider nuller, Boolean unwrapSingle) {
            super(base, nuller, unwrapSingle);
        }

        @Override
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nuller, Boolean unwrapSingle) {
            return this;
        }

        @Override
        protected char[] _constructEmpty() {
            return new char[0];
        }

        @Override
        public char[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            JsonToken t2 = p2.getCurrentToken();
            if (t2 == JsonToken.VALUE_STRING) {
                char[] buffer = p2.getTextCharacters();
                int offset = p2.getTextOffset();
                int len = p2.getTextLength();
                char[] result = new char[len];
                System.arraycopy(buffer, offset, result, 0, len);
                return result;
            }
            if (p2.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                    String str;
                    if (t2 == JsonToken.VALUE_STRING) {
                        str = p2.getText();
                    } else if (t2 == JsonToken.VALUE_NULL) {
                        if (this._nuller != null) {
                            this._nuller.getNullValue(ctxt);
                            continue;
                        }
                        this._verifyNullForPrimitive(ctxt);
                        str = "\u0000";
                    } else {
                        CharSequence cs2 = (CharSequence)ctxt.handleUnexpectedToken(Character.TYPE, p2);
                        str = cs2.toString();
                    }
                    if (str.length() != 1) {
                        ctxt.reportInputMismatch(this, "Cannot convert a JSON String of length %d into a char element of char array", str.length());
                    }
                    sb.append(str.charAt(0));
                }
                return sb.toString().toCharArray();
            }
            if (t2 == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object ob = p2.getEmbeddedObject();
                if (ob == null) {
                    return null;
                }
                if (ob instanceof char[]) {
                    return (char[])ob;
                }
                if (ob instanceof String) {
                    return ((String)ob).toCharArray();
                }
                if (ob instanceof byte[]) {
                    return Base64Variants.getDefaultVariant().encode((byte[])ob, false).toCharArray();
                }
            }
            return (char[])ctxt.handleUnexpectedToken(this._valueClass, p2);
        }

        @Override
        protected char[] handleSingleElementUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
            return (char[])ctxt.handleUnexpectedToken(this._valueClass, p2);
        }

        @Override
        protected char[] _concat(char[] oldValue, char[] newValue) {
            int len1 = oldValue.length;
            int len2 = newValue.length;
            char[] result = Arrays.copyOf(oldValue, len1 + len2);
            System.arraycopy(newValue, 0, result, len1, len2);
            return result;
        }
    }
}

