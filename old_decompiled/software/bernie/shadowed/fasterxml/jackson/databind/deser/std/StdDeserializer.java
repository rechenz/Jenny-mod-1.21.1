/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.Nulls;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParseException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.io.NumberInput;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.KeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.AccessPattern;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Converter;

public abstract class StdDeserializer<T>
extends JsonDeserializer<T>
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask();
    protected static final int F_MASK_ACCEPT_ARRAYS = DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS.getMask() | DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.getMask();
    protected final Class<?> _valueClass;

    protected StdDeserializer(Class<?> vc) {
        this._valueClass = vc;
    }

    protected StdDeserializer(JavaType valueType) {
        this._valueClass = valueType.getRawClass();
    }

    protected StdDeserializer(StdDeserializer<?> src) {
        this._valueClass = src._valueClass;
    }

    @Override
    public Class<?> handledType() {
        return this._valueClass;
    }

    @Deprecated
    public final Class<?> getValueClass() {
        return this._valueClass;
    }

    public JavaType getValueType() {
        return null;
    }

    protected boolean isDefaultDeserializer(JsonDeserializer<?> deserializer) {
        return ClassUtil.isJacksonStdImpl(deserializer);
    }

    protected boolean isDefaultKeyDeserializer(KeyDeserializer keyDeser) {
        return ClassUtil.isJacksonStdImpl(keyDeser);
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(p2, ctxt);
    }

    protected final boolean _parseBooleanPrimitive(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (t2 == JsonToken.VALUE_FALSE) {
            return false;
        }
        if (t2 == JsonToken.VALUE_NULL) {
            this._verifyNullForPrimitive(ctxt);
            return false;
        }
        if (t2 == JsonToken.VALUE_NUMBER_INT) {
            return this._parseBooleanFromInt(p2, ctxt);
        }
        if (t2 == JsonToken.VALUE_STRING) {
            String text = p2.getText().trim();
            if ("true".equals(text) || "True".equals(text)) {
                return true;
            }
            if ("false".equals(text) || "False".equals(text)) {
                return false;
            }
            if (this._isEmptyOrTextualNull(text)) {
                this._verifyNullForPrimitiveCoercion(ctxt, text);
                return false;
            }
            Boolean b10 = (Boolean)ctxt.handleWeirdStringValue(this._valueClass, text, "only \"true\" or \"false\" recognized", new Object[0]);
            return Boolean.TRUE.equals(b10);
        }
        if (t2 == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            p2.nextToken();
            boolean parsed = this._parseBooleanPrimitive(p2, ctxt);
            this._verifyEndArrayForSingle(p2, ctxt);
            return parsed;
        }
        return (Boolean)ctxt.handleUnexpectedToken(this._valueClass, p2);
    }

    protected boolean _parseBooleanFromInt(JsonParser p2, DeserializationContext ctxt) throws IOException {
        this._verifyNumberForScalarCoercion(ctxt, p2);
        return !"0".equals(p2.getText());
    }

    protected final byte _parseBytePrimitive(JsonParser p2, DeserializationContext ctxt) throws IOException {
        int value = this._parseIntPrimitive(p2, ctxt);
        if (this._byteOverflow(value)) {
            Number v2 = (Number)ctxt.handleWeirdStringValue(this._valueClass, String.valueOf(value), "overflow, value cannot be represented as 8-bit value", new Object[0]);
            return this._nonNullNumber(v2).byteValue();
        }
        return (byte)value;
    }

    protected final short _parseShortPrimitive(JsonParser p2, DeserializationContext ctxt) throws IOException {
        int value = this._parseIntPrimitive(p2, ctxt);
        if (this._shortOverflow(value)) {
            Number v2 = (Number)ctxt.handleWeirdStringValue(this._valueClass, String.valueOf(value), "overflow, value cannot be represented as 16-bit value", new Object[0]);
            return this._nonNullNumber(v2).shortValue();
        }
        return (short)value;
    }

    protected final int _parseIntPrimitive(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            return p2.getIntValue();
        }
        switch (p2.getCurrentTokenId()) {
            case 6: {
                String text = p2.getText().trim();
                if (this._isEmptyOrTextualNull(text)) {
                    this._verifyNullForPrimitiveCoercion(ctxt, text);
                    return 0;
                }
                return this._parseIntPrimitive(ctxt, text);
            }
            case 8: {
                if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    this._failDoubleToIntCoercion(p2, ctxt, "int");
                }
                return p2.getValueAsInt();
            }
            case 11: {
                this._verifyNullForPrimitive(ctxt);
                return 0;
            }
            case 3: {
                if (!ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) break;
                p2.nextToken();
                int parsed = this._parseIntPrimitive(p2, ctxt);
                this._verifyEndArrayForSingle(p2, ctxt);
                return parsed;
            }
        }
        return ((Number)ctxt.handleUnexpectedToken(this._valueClass, p2)).intValue();
    }

    protected final int _parseIntPrimitive(DeserializationContext ctxt, String text) throws IOException {
        try {
            if (text.length() > 9) {
                long l2 = Long.parseLong(text);
                if (this._intOverflow(l2)) {
                    Number v2 = (Number)ctxt.handleWeirdStringValue(this._valueClass, text, "Overflow: numeric value (%s) out of range of int (%d -%d)", text, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    return this._nonNullNumber(v2).intValue();
                }
                return (int)l2;
            }
            return NumberInput.parseInt(text);
        }
        catch (IllegalArgumentException iae) {
            Number v3 = (Number)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid int value", new Object[0]);
            return this._nonNullNumber(v3).intValue();
        }
    }

    protected final long _parseLongPrimitive(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            return p2.getLongValue();
        }
        switch (p2.getCurrentTokenId()) {
            case 6: {
                String text = p2.getText().trim();
                if (this._isEmptyOrTextualNull(text)) {
                    this._verifyNullForPrimitiveCoercion(ctxt, text);
                    return 0L;
                }
                return this._parseLongPrimitive(ctxt, text);
            }
            case 8: {
                if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    this._failDoubleToIntCoercion(p2, ctxt, "long");
                }
                return p2.getValueAsLong();
            }
            case 11: {
                this._verifyNullForPrimitive(ctxt);
                return 0L;
            }
            case 3: {
                if (!ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) break;
                p2.nextToken();
                long parsed = this._parseLongPrimitive(p2, ctxt);
                this._verifyEndArrayForSingle(p2, ctxt);
                return parsed;
            }
        }
        return ((Number)ctxt.handleUnexpectedToken(this._valueClass, p2)).longValue();
    }

    protected final long _parseLongPrimitive(DeserializationContext ctxt, String text) throws IOException {
        try {
            return NumberInput.parseLong(text);
        }
        catch (IllegalArgumentException iae) {
            Number v2 = (Number)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid long value", new Object[0]);
            return this._nonNullNumber(v2).longValue();
        }
    }

    protected final float _parseFloatPrimitive(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
            return p2.getFloatValue();
        }
        switch (p2.getCurrentTokenId()) {
            case 6: {
                String text = p2.getText().trim();
                if (this._isEmptyOrTextualNull(text)) {
                    this._verifyNullForPrimitiveCoercion(ctxt, text);
                    return 0.0f;
                }
                return this._parseFloatPrimitive(ctxt, text);
            }
            case 7: {
                return p2.getFloatValue();
            }
            case 11: {
                this._verifyNullForPrimitive(ctxt);
                return 0.0f;
            }
            case 3: {
                if (!ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) break;
                p2.nextToken();
                float parsed = this._parseFloatPrimitive(p2, ctxt);
                this._verifyEndArrayForSingle(p2, ctxt);
                return parsed;
            }
        }
        return ((Number)ctxt.handleUnexpectedToken(this._valueClass, p2)).floatValue();
    }

    protected final float _parseFloatPrimitive(DeserializationContext ctxt, String text) throws IOException {
        switch (text.charAt(0)) {
            case 'I': {
                if (!this._isPosInf(text)) break;
                return Float.POSITIVE_INFINITY;
            }
            case 'N': {
                if (!this._isNaN(text)) break;
                return Float.NaN;
            }
            case '-': {
                if (!this._isNegInf(text)) break;
                return Float.NEGATIVE_INFINITY;
            }
        }
        try {
            return Float.parseFloat(text);
        }
        catch (IllegalArgumentException iae) {
            Number v2 = (Number)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid float value", new Object[0]);
            return this._nonNullNumber(v2).floatValue();
        }
    }

    protected final double _parseDoublePrimitive(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
            return p2.getDoubleValue();
        }
        switch (p2.getCurrentTokenId()) {
            case 6: {
                String text = p2.getText().trim();
                if (this._isEmptyOrTextualNull(text)) {
                    this._verifyNullForPrimitiveCoercion(ctxt, text);
                    return 0.0;
                }
                return this._parseDoublePrimitive(ctxt, text);
            }
            case 7: {
                return p2.getDoubleValue();
            }
            case 11: {
                this._verifyNullForPrimitive(ctxt);
                return 0.0;
            }
            case 3: {
                if (!ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) break;
                p2.nextToken();
                double parsed = this._parseDoublePrimitive(p2, ctxt);
                this._verifyEndArrayForSingle(p2, ctxt);
                return parsed;
            }
        }
        return ((Number)ctxt.handleUnexpectedToken(this._valueClass, p2)).doubleValue();
    }

    protected final double _parseDoublePrimitive(DeserializationContext ctxt, String text) throws IOException {
        switch (text.charAt(0)) {
            case 'I': {
                if (!this._isPosInf(text)) break;
                return Double.POSITIVE_INFINITY;
            }
            case 'N': {
                if (!this._isNaN(text)) break;
                return Double.NaN;
            }
            case '-': {
                if (!this._isNegInf(text)) break;
                return Double.NEGATIVE_INFINITY;
            }
        }
        try {
            return StdDeserializer.parseDouble(text);
        }
        catch (IllegalArgumentException iae) {
            Number v2 = (Number)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid double value (as String to convert)", new Object[0]);
            return this._nonNullNumber(v2).doubleValue();
        }
    }

    protected Date _parseDate(JsonParser p2, DeserializationContext ctxt) throws IOException {
        switch (p2.getCurrentTokenId()) {
            case 6: {
                return this._parseDate(p2.getText().trim(), ctxt);
            }
            case 7: {
                long ts;
                try {
                    ts = p2.getLongValue();
                }
                catch (JsonParseException e10) {
                    Number v2 = (Number)ctxt.handleWeirdNumberValue(this._valueClass, p2.getNumberValue(), "not a valid 64-bit long for creating `java.util.Date`", new Object[0]);
                    ts = v2.longValue();
                }
                return new Date(ts);
            }
            case 11: {
                return (Date)this.getNullValue(ctxt);
            }
            case 3: {
                return this._parseDateFromArray(p2, ctxt);
            }
        }
        return (Date)ctxt.handleUnexpectedToken(this._valueClass, p2);
    }

    protected Date _parseDateFromArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2;
        if (ctxt.hasSomeOfFeatures(F_MASK_ACCEPT_ARRAYS)) {
            t2 = p2.nextToken();
            if (t2 == JsonToken.END_ARRAY && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                return (Date)this.getNullValue(ctxt);
            }
            if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                Date parsed = this._parseDate(p2, ctxt);
                this._verifyEndArrayForSingle(p2, ctxt);
                return parsed;
            }
        } else {
            t2 = p2.getCurrentToken();
        }
        return (Date)ctxt.handleUnexpectedToken(this._valueClass, t2, p2, null, new Object[0]);
    }

    protected Date _parseDate(String value, DeserializationContext ctxt) throws IOException {
        try {
            if (this._isEmptyOrTextualNull(value)) {
                return (Date)this.getNullValue(ctxt);
            }
            return ctxt.parseDate(value);
        }
        catch (IllegalArgumentException iae) {
            return (Date)ctxt.handleWeirdStringValue(this._valueClass, value, "not a valid representation (error: %s)", iae.getMessage());
        }
    }

    protected static final double parseDouble(String numStr) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(numStr)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(numStr);
    }

    protected final String _parseString(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.VALUE_STRING) {
            return p2.getText();
        }
        String value = p2.getValueAsString();
        if (value != null) {
            return value;
        }
        return (String)ctxt.handleUnexpectedToken(String.class, p2);
    }

    protected T _deserializeFromEmpty(JsonParser p2, DeserializationContext ctxt) throws IOException {
        String str;
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.START_ARRAY) {
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                t2 = p2.nextToken();
                if (t2 == JsonToken.END_ARRAY) {
                    return null;
                }
                return (T)ctxt.handleUnexpectedToken(this.handledType(), p2);
            }
        } else if (t2 == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (str = p2.getText().trim()).isEmpty()) {
            return null;
        }
        return (T)ctxt.handleUnexpectedToken(this.handledType(), p2);
    }

    protected boolean _hasTextualNull(String value) {
        return "null".equals(value);
    }

    protected boolean _isEmptyOrTextualNull(String value) {
        return value.isEmpty() || "null".equals(value);
    }

    protected final boolean _isNegInf(String text) {
        return "-Infinity".equals(text) || "-INF".equals(text);
    }

    protected final boolean _isPosInf(String text) {
        return "Infinity".equals(text) || "INF".equals(text);
    }

    protected final boolean _isNaN(String text) {
        return "NaN".equals(text);
    }

    protected T _deserializeFromArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2;
        if (ctxt.hasSomeOfFeatures(F_MASK_ACCEPT_ARRAYS)) {
            t2 = p2.nextToken();
            if (t2 == JsonToken.END_ARRAY && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                return this.getNullValue(ctxt);
            }
            if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                Object parsed = this.deserialize(p2, ctxt);
                if (p2.nextToken() != JsonToken.END_ARRAY) {
                    this.handleMissingEndArrayForSingle(p2, ctxt);
                }
                return parsed;
            }
        } else {
            t2 = p2.getCurrentToken();
        }
        Object result = ctxt.handleUnexpectedToken(this._valueClass, t2, p2, null, new Object[0]);
        return (T)result;
    }

    protected T _deserializeWrappedValue(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.START_ARRAY)) {
            String msg = String.format("Cannot deserialize instance of %s out of %s token: nested Arrays not allowed with %s", new Object[]{ClassUtil.nameOf(this._valueClass), JsonToken.START_ARRAY, "DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS"});
            Object result = ctxt.handleUnexpectedToken(this._valueClass, p2.getCurrentToken(), p2, msg, new Object[0]);
            return (T)result;
        }
        return this.deserialize(p2, ctxt);
    }

    protected void _failDoubleToIntCoercion(JsonParser p2, DeserializationContext ctxt, String type) throws IOException {
        ctxt.reportInputMismatch(this.handledType(), "Cannot coerce a floating-point value ('%s') into %s (enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow)", p2.getValueAsString(), type);
    }

    protected Object _coerceIntegral(JsonParser p2, DeserializationContext ctxt) throws IOException {
        int feats = ctxt.getDeserializationFeatures();
        if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(feats)) {
            return p2.getBigIntegerValue();
        }
        if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(feats)) {
            return p2.getLongValue();
        }
        return p2.getBigIntegerValue();
    }

    protected Object _coerceNullToken(DeserializationContext ctxt, boolean isPrimitive) throws JsonMappingException {
        if (isPrimitive) {
            this._verifyNullForPrimitive(ctxt);
        }
        return this.getNullValue(ctxt);
    }

    protected Object _coerceTextualNull(DeserializationContext ctxt, boolean isPrimitive) throws JsonMappingException {
        boolean enable;
        Enum feat;
        if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            feat = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            enable = true;
        } else if (isPrimitive && ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            feat = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
            enable = false;
        } else {
            return this.getNullValue(ctxt);
        }
        this._reportFailedNullCoerce(ctxt, enable, feat, "String \"null\"");
        return null;
    }

    protected Object _coerceEmptyString(DeserializationContext ctxt, boolean isPrimitive) throws JsonMappingException {
        boolean enable;
        Enum feat;
        if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            feat = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            enable = true;
        } else if (isPrimitive && ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            feat = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
            enable = false;
        } else {
            return this.getNullValue(ctxt);
        }
        this._reportFailedNullCoerce(ctxt, enable, feat, "empty String (\"\")");
        return null;
    }

    protected final void _verifyNullForPrimitive(DeserializationContext ctxt) throws JsonMappingException {
        if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            ctxt.reportInputMismatch(this, "Cannot coerce `null` %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", this._coercedTypeDesc());
        }
    }

    protected final void _verifyNullForPrimitiveCoercion(DeserializationContext ctxt, String str) throws JsonMappingException {
        boolean enable;
        Enum feat;
        if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            feat = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            enable = true;
        } else if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            feat = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
            enable = false;
        } else {
            return;
        }
        String strDesc = str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str);
        this._reportFailedNullCoerce(ctxt, enable, feat, strDesc);
    }

    protected final void _verifyNullForScalarCoercion(DeserializationContext ctxt, String str) throws JsonMappingException {
        if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            String strDesc = str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str);
            this._reportFailedNullCoerce(ctxt, true, MapperFeature.ALLOW_COERCION_OF_SCALARS, strDesc);
        }
    }

    protected void _verifyStringForScalarCoercion(DeserializationContext ctxt, String str) throws JsonMappingException {
        MapperFeature feat = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!ctxt.isEnabled(feat)) {
            ctxt.reportInputMismatch(this, "Cannot coerce String \"%s\" %s (enable `%s.%s` to allow)", str, this._coercedTypeDesc(), feat.getClass().getSimpleName(), feat.name());
        }
    }

    protected void _verifyNumberForScalarCoercion(DeserializationContext ctxt, JsonParser p2) throws IOException {
        MapperFeature feat = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!ctxt.isEnabled(feat)) {
            String valueDesc = p2.getText();
            ctxt.reportInputMismatch(this, "Cannot coerce Number (%s) %s (enable `%s.%s` to allow)", valueDesc, this._coercedTypeDesc(), feat.getClass().getSimpleName(), feat.name());
        }
    }

    protected void _reportFailedNullCoerce(DeserializationContext ctxt, boolean state, Enum<?> feature, String inputDesc) throws JsonMappingException {
        String enableDesc = state ? "enable" : "disable";
        ctxt.reportInputMismatch(this, "Cannot coerce %s to Null value %s (%s `%s.%s` to allow)", inputDesc, this._coercedTypeDesc(), enableDesc, feature.getClass().getSimpleName(), feature.name());
    }

    protected String _coercedTypeDesc() {
        String typeDesc;
        boolean structured;
        JavaType t2 = this.getValueType();
        if (t2 != null && !t2.isPrimitive()) {
            structured = t2.isContainerType() || t2.isReferenceType();
            typeDesc = "'" + t2.toString() + "'";
        } else {
            Class<?> cls = this.handledType();
            structured = cls.isArray() || Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
            typeDesc = ClassUtil.nameOf(cls);
        }
        if (structured) {
            return "as content of type " + typeDesc;
        }
        return "for type " + typeDesc;
    }

    protected JsonDeserializer<Object> findDeserializer(DeserializationContext ctxt, JavaType type, BeanProperty property) throws JsonMappingException {
        return ctxt.findContextualValueDeserializer(type, property);
    }

    protected final boolean _isIntNumber(String text) {
        int len = text.length();
        if (len > 0) {
            int i2;
            char c10 = text.charAt(0);
            int n2 = i2 = c10 == '-' || c10 == '+' ? 1 : 0;
            while (i2 < len) {
                char ch2 = text.charAt(i2);
                if (ch2 > '9' || ch2 < '0') {
                    return false;
                }
                ++i2;
            }
            return true;
        }
        return false;
    }

    protected JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext ctxt, BeanProperty prop, JsonDeserializer<?> existingDeserializer) throws JsonMappingException {
        Object convDef;
        AnnotatedMember member;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (StdDeserializer._neitherNull(intr, prop) && (member = prop.getMember()) != null && (convDef = intr.findDeserializationContentConverter(member)) != null) {
            Converter<Object, Object> conv = ctxt.converterInstance(prop.getMember(), convDef);
            JavaType delegateType = conv.getInputType(ctxt.getTypeFactory());
            if (existingDeserializer == null) {
                existingDeserializer = ctxt.findContextualValueDeserializer(delegateType, prop);
            }
            return new StdDelegatingDeserializer<Object>(conv, delegateType, existingDeserializer);
        }
        return existingDeserializer;
    }

    protected JsonFormat.Value findFormatOverrides(DeserializationContext ctxt, BeanProperty prop, Class<?> typeForDefaults) {
        if (prop != null) {
            return prop.findPropertyFormat(ctxt.getConfig(), typeForDefaults);
        }
        return ctxt.getDefaultPropertyFormat(typeForDefaults);
    }

    protected Boolean findFormatFeature(DeserializationContext ctxt, BeanProperty prop, Class<?> typeForDefaults, JsonFormat.Feature feat) {
        JsonFormat.Value format = this.findFormatOverrides(ctxt, prop, typeForDefaults);
        if (format != null) {
            return format.getFeature(feat);
        }
        return null;
    }

    protected final NullValueProvider findValueNullProvider(DeserializationContext ctxt, SettableBeanProperty prop, PropertyMetadata propMetadata) throws JsonMappingException {
        if (prop != null) {
            return this._findNullProvider(ctxt, prop, propMetadata.getValueNulls(), prop.getValueDeserializer());
        }
        return null;
    }

    protected NullValueProvider findContentNullProvider(DeserializationContext ctxt, BeanProperty prop, JsonDeserializer<?> valueDeser) throws JsonMappingException {
        Nulls nulls = this.findContentNullStyle(ctxt, prop);
        if (nulls == Nulls.SKIP) {
            return NullsConstantProvider.skipper();
        }
        NullValueProvider prov = this._findNullProvider(ctxt, prop, nulls, valueDeser);
        if (prov != null) {
            return prov;
        }
        return valueDeser;
    }

    protected Nulls findContentNullStyle(DeserializationContext ctxt, BeanProperty prop) throws JsonMappingException {
        if (prop != null) {
            return prop.getMetadata().getContentNulls();
        }
        return null;
    }

    protected final NullValueProvider _findNullProvider(DeserializationContext ctxt, BeanProperty prop, Nulls nulls, JsonDeserializer<?> valueDeser) throws JsonMappingException {
        if (nulls == Nulls.FAIL) {
            if (prop == null) {
                return NullsFailProvider.constructForRootValue(ctxt.constructType(valueDeser.handledType()));
            }
            return NullsFailProvider.constructForProperty(prop);
        }
        if (nulls == Nulls.AS_EMPTY) {
            AccessPattern access;
            ValueInstantiator vi;
            if (valueDeser == null) {
                return null;
            }
            if (valueDeser instanceof BeanDeserializerBase && !(vi = ((BeanDeserializerBase)valueDeser).getValueInstantiator()).canCreateUsingDefault()) {
                JavaType type = prop.getType();
                ctxt.reportBadDefinition(type, String.format("Cannot create empty instance of %s, no default Creator", type));
            }
            if ((access = valueDeser.getEmptyAccessPattern()) == AccessPattern.ALWAYS_NULL) {
                return NullsConstantProvider.nuller();
            }
            if (access == AccessPattern.CONSTANT) {
                return NullsConstantProvider.forValue(valueDeser.getEmptyValue(ctxt));
            }
            return new NullsAsEmptyProvider(valueDeser);
        }
        if (nulls == Nulls.SKIP) {
            return NullsConstantProvider.skipper();
        }
        return null;
    }

    protected void handleUnknownProperty(JsonParser p2, DeserializationContext ctxt, Object instanceOrClass, String propName) throws IOException {
        if (instanceOrClass == null) {
            instanceOrClass = this.handledType();
        }
        if (ctxt.handleUnknownProperty(p2, this, instanceOrClass, propName)) {
            return;
        }
        p2.skipChildren();
    }

    protected void handleMissingEndArrayForSingle(JsonParser p2, DeserializationContext ctxt) throws IOException {
        ctxt.reportWrongTokenException(this, JsonToken.END_ARRAY, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", this.handledType().getName());
    }

    protected void _verifyEndArrayForSingle(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2 = p2.nextToken();
        if (t2 != JsonToken.END_ARRAY) {
            this.handleMissingEndArrayForSingle(p2, ctxt);
        }
    }

    protected static final boolean _neitherNull(Object a10, Object b10) {
        return a10 != null && b10 != null;
    }

    protected final boolean _byteOverflow(int value) {
        return value < -128 || value > 255;
    }

    protected final boolean _shortOverflow(int value) {
        return value < Short.MIN_VALUE || value > Short.MAX_VALUE;
    }

    protected final boolean _intOverflow(long value) {
        return value < Integer.MIN_VALUE || value > Integer.MAX_VALUE;
    }

    protected Number _nonNullNumber(Number n2) {
        if (n2 == null) {
            n2 = 0;
        }
        return n2;
    }
}

