/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ObjectBuffer;

@JacksonStdImpl
public class UntypedObjectDeserializer
extends StdDeserializer<Object>
implements ResolvableDeserializer,
ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected static final Object[] NO_OBJECTS = new Object[0];
    protected JsonDeserializer<Object> _mapDeserializer;
    protected JsonDeserializer<Object> _listDeserializer;
    protected JsonDeserializer<Object> _stringDeserializer;
    protected JsonDeserializer<Object> _numberDeserializer;
    protected JavaType _listType;
    protected JavaType _mapType;
    protected final boolean _nonMerging;

    @Deprecated
    public UntypedObjectDeserializer() {
        this(null, null);
    }

    public UntypedObjectDeserializer(JavaType listType, JavaType mapType) {
        super(Object.class);
        this._listType = listType;
        this._mapType = mapType;
        this._nonMerging = false;
    }

    public UntypedObjectDeserializer(UntypedObjectDeserializer base, JsonDeserializer<?> mapDeser, JsonDeserializer<?> listDeser, JsonDeserializer<?> stringDeser, JsonDeserializer<?> numberDeser) {
        super(Object.class);
        this._mapDeserializer = mapDeser;
        this._listDeserializer = listDeser;
        this._stringDeserializer = stringDeser;
        this._numberDeserializer = numberDeser;
        this._listType = base._listType;
        this._mapType = base._mapType;
        this._nonMerging = base._nonMerging;
    }

    protected UntypedObjectDeserializer(UntypedObjectDeserializer base, boolean nonMerging) {
        super(Object.class);
        this._mapDeserializer = base._mapDeserializer;
        this._listDeserializer = base._listDeserializer;
        this._stringDeserializer = base._stringDeserializer;
        this._numberDeserializer = base._numberDeserializer;
        this._listType = base._listType;
        this._mapType = base._mapType;
        this._nonMerging = nonMerging;
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        JavaType obType = ctxt.constructType(Object.class);
        JavaType stringType = ctxt.constructType(String.class);
        TypeFactory tf = ctxt.getTypeFactory();
        this._listDeserializer = this._listType == null ? this._clearIfStdImpl(this._findCustomDeser(ctxt, tf.constructCollectionType(List.class, obType))) : this._findCustomDeser(ctxt, this._listType);
        this._mapDeserializer = this._mapType == null ? this._clearIfStdImpl(this._findCustomDeser(ctxt, tf.constructMapType(Map.class, stringType, obType))) : this._findCustomDeser(ctxt, this._mapType);
        this._stringDeserializer = this._clearIfStdImpl(this._findCustomDeser(ctxt, stringType));
        this._numberDeserializer = this._clearIfStdImpl(this._findCustomDeser(ctxt, tf.constructType((Type)((Object)Number.class))));
        JavaType unknown = TypeFactory.unknownType();
        this._mapDeserializer = ctxt.handleSecondaryContextualization(this._mapDeserializer, null, unknown);
        this._listDeserializer = ctxt.handleSecondaryContextualization(this._listDeserializer, null, unknown);
        this._stringDeserializer = ctxt.handleSecondaryContextualization(this._stringDeserializer, null, unknown);
        this._numberDeserializer = ctxt.handleSecondaryContextualization(this._numberDeserializer, null, unknown);
    }

    protected JsonDeserializer<Object> _findCustomDeser(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
        return ctxt.findNonContextualValueDeserializer(type);
    }

    protected JsonDeserializer<Object> _clearIfStdImpl(JsonDeserializer<Object> deser) {
        return ClassUtil.isJacksonStdImpl(deser) ? null : deser;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        boolean preventMerge;
        boolean bl2 = preventMerge = property == null && Boolean.FALSE.equals(ctxt.getConfig().getDefaultMergeable(Object.class));
        if (this._stringDeserializer == null && this._numberDeserializer == null && this._mapDeserializer == null && this._listDeserializer == null && this.getClass() == UntypedObjectDeserializer.class) {
            return Vanilla.instance(preventMerge);
        }
        if (preventMerge != this._nonMerging) {
            return new UntypedObjectDeserializer(this, preventMerge);
        }
        return this;
    }

    @Override
    public boolean isCachable() {
        return true;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return null;
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        switch (p2.getCurrentTokenId()) {
            case 1: 
            case 2: 
            case 5: {
                if (this._mapDeserializer != null) {
                    return this._mapDeserializer.deserialize(p2, ctxt);
                }
                return this.mapObject(p2, ctxt);
            }
            case 3: {
                if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return this.mapArrayToArray(p2, ctxt);
                }
                if (this._listDeserializer != null) {
                    return this._listDeserializer.deserialize(p2, ctxt);
                }
                return this.mapArray(p2, ctxt);
            }
            case 12: {
                return p2.getEmbeddedObject();
            }
            case 6: {
                if (this._stringDeserializer != null) {
                    return this._stringDeserializer.deserialize(p2, ctxt);
                }
                return p2.getText();
            }
            case 7: {
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(p2, ctxt);
                }
                if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                    return this._coerceIntegral(p2, ctxt);
                }
                return p2.getNumberValue();
            }
            case 8: {
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(p2, ctxt);
                }
                if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return p2.getDecimalValue();
                }
                return p2.getNumberValue();
            }
            case 9: {
                return Boolean.TRUE;
            }
            case 10: {
                return Boolean.FALSE;
            }
            case 11: {
                return null;
            }
        }
        return ctxt.handleUnexpectedToken(Object.class, p2);
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        switch (p2.getCurrentTokenId()) {
            case 1: 
            case 3: 
            case 5: {
                return typeDeserializer.deserializeTypedFromAny(p2, ctxt);
            }
            case 12: {
                return p2.getEmbeddedObject();
            }
            case 6: {
                if (this._stringDeserializer != null) {
                    return this._stringDeserializer.deserialize(p2, ctxt);
                }
                return p2.getText();
            }
            case 7: {
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(p2, ctxt);
                }
                if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                    return this._coerceIntegral(p2, ctxt);
                }
                return p2.getNumberValue();
            }
            case 8: {
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(p2, ctxt);
                }
                if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return p2.getDecimalValue();
                }
                return p2.getNumberValue();
            }
            case 9: {
                return Boolean.TRUE;
            }
            case 10: {
                return Boolean.FALSE;
            }
            case 11: {
                return null;
            }
        }
        return ctxt.handleUnexpectedToken(Object.class, p2);
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt, Object intoValue) throws IOException {
        if (this._nonMerging) {
            return this.deserialize(p2, ctxt);
        }
        switch (p2.getCurrentTokenId()) {
            case 1: 
            case 2: 
            case 5: {
                if (this._mapDeserializer != null) {
                    return this._mapDeserializer.deserialize(p2, ctxt, intoValue);
                }
                if (intoValue instanceof Map) {
                    return this.mapObject(p2, ctxt, (Map)intoValue);
                }
                return this.mapObject(p2, ctxt);
            }
            case 3: {
                if (this._listDeserializer != null) {
                    return this._listDeserializer.deserialize(p2, ctxt, intoValue);
                }
                if (intoValue instanceof Collection) {
                    return this.mapArray(p2, ctxt, (Collection)intoValue);
                }
                if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return this.mapArrayToArray(p2, ctxt);
                }
                return this.mapArray(p2, ctxt);
            }
            case 12: {
                return p2.getEmbeddedObject();
            }
            case 6: {
                if (this._stringDeserializer != null) {
                    return this._stringDeserializer.deserialize(p2, ctxt, intoValue);
                }
                return p2.getText();
            }
            case 7: {
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(p2, ctxt, intoValue);
                }
                if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                    return this._coerceIntegral(p2, ctxt);
                }
                return p2.getNumberValue();
            }
            case 8: {
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(p2, ctxt, intoValue);
                }
                if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return p2.getDecimalValue();
                }
                return p2.getNumberValue();
            }
            case 9: {
                return Boolean.TRUE;
            }
            case 10: {
                return Boolean.FALSE;
            }
            case 11: {
                return null;
            }
        }
        return this.deserialize(p2, ctxt);
    }

    protected Object mapArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.nextToken() == JsonToken.END_ARRAY) {
            return new ArrayList(2);
        }
        Object value = this.deserialize(p2, ctxt);
        if (p2.nextToken() == JsonToken.END_ARRAY) {
            ArrayList<Object> l2 = new ArrayList<Object>(2);
            l2.add(value);
            return l2;
        }
        Object value2 = this.deserialize(p2, ctxt);
        if (p2.nextToken() == JsonToken.END_ARRAY) {
            ArrayList<Object> l3 = new ArrayList<Object>(2);
            l3.add(value);
            l3.add(value2);
            return l3;
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] values = buffer.resetAndStart();
        int ptr = 0;
        values[ptr++] = value;
        values[ptr++] = value2;
        int totalSize = ptr;
        do {
            value = this.deserialize(p2, ctxt);
            ++totalSize;
            if (ptr >= values.length) {
                values = buffer.appendCompletedChunk(values);
                ptr = 0;
            }
            values[ptr++] = value;
        } while (p2.nextToken() != JsonToken.END_ARRAY);
        ArrayList<Object> result = new ArrayList<Object>(totalSize);
        buffer.completeAndClearBuffer(values, ptr, result);
        return result;
    }

    protected Object mapArray(JsonParser p2, DeserializationContext ctxt, Collection<Object> result) throws IOException {
        while (p2.nextToken() != JsonToken.END_ARRAY) {
            result.add(this.deserialize(p2, ctxt));
        }
        return result;
    }

    protected Object mapObject(JsonParser p2, DeserializationContext ctxt) throws IOException {
        String key1;
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.START_OBJECT) {
            key1 = p2.nextFieldName();
        } else if (t2 == JsonToken.FIELD_NAME) {
            key1 = p2.getCurrentName();
        } else {
            if (t2 != JsonToken.END_OBJECT) {
                return ctxt.handleUnexpectedToken(this.handledType(), p2);
            }
            key1 = null;
        }
        if (key1 == null) {
            return new LinkedHashMap(2);
        }
        p2.nextToken();
        Object value1 = this.deserialize(p2, ctxt);
        String key2 = p2.nextFieldName();
        if (key2 == null) {
            LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>(2);
            result.put(key1, value1);
            return result;
        }
        p2.nextToken();
        Object value2 = this.deserialize(p2, ctxt);
        String key = p2.nextFieldName();
        if (key == null) {
            LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>(4);
            result.put(key1, value1);
            result.put(key2, value2);
            return result;
        }
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put(key1, value1);
        result.put(key2, value2);
        do {
            p2.nextToken();
            result.put(key, this.deserialize(p2, ctxt));
        } while ((key = p2.nextFieldName()) != null);
        return result;
    }

    protected Object[] mapArrayToArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.nextToken() == JsonToken.END_ARRAY) {
            return NO_OBJECTS;
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] values = buffer.resetAndStart();
        int ptr = 0;
        do {
            Object value = this.deserialize(p2, ctxt);
            if (ptr >= values.length) {
                values = buffer.appendCompletedChunk(values);
                ptr = 0;
            }
            values[ptr++] = value;
        } while (p2.nextToken() != JsonToken.END_ARRAY);
        return buffer.completeAndClearBuffer(values, ptr);
    }

    protected Object mapObject(JsonParser p2, DeserializationContext ctxt, Map<Object, Object> m2) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.START_OBJECT) {
            t2 = p2.nextToken();
        }
        if (t2 == JsonToken.END_OBJECT) {
            return m2;
        }
        String key = p2.getCurrentName();
        do {
            p2.nextToken();
            Object old = m2.get(key);
            Object newV = old != null ? this.deserialize(p2, ctxt, old) : this.deserialize(p2, ctxt);
            if (newV == old) continue;
            m2.put(key, newV);
        } while ((key = p2.nextFieldName()) != null);
        return m2;
    }

    @JacksonStdImpl
    public static class Vanilla
    extends StdDeserializer<Object> {
        private static final long serialVersionUID = 1L;
        public static final Vanilla std = new Vanilla();
        protected final boolean _nonMerging;

        public Vanilla() {
            this(false);
        }

        protected Vanilla(boolean nonMerging) {
            super(Object.class);
            this._nonMerging = nonMerging;
        }

        public static Vanilla instance(boolean nonMerging) {
            if (nonMerging) {
                return new Vanilla(true);
            }
            return std;
        }

        @Override
        public Boolean supportsUpdate(DeserializationConfig config) {
            return this._nonMerging ? Boolean.FALSE : null;
        }

        @Override
        public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            switch (p2.getCurrentTokenId()) {
                case 1: {
                    JsonToken t2 = p2.nextToken();
                    if (t2 == JsonToken.END_OBJECT) {
                        return new LinkedHashMap(2);
                    }
                }
                case 5: {
                    return this.mapObject(p2, ctxt);
                }
                case 3: {
                    JsonToken t3 = p2.nextToken();
                    if (t3 == JsonToken.END_ARRAY) {
                        if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                            return NO_OBJECTS;
                        }
                        return new ArrayList(2);
                    }
                    if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                        return this.mapArrayToArray(p2, ctxt);
                    }
                    return this.mapArray(p2, ctxt);
                }
                case 12: {
                    return p2.getEmbeddedObject();
                }
                case 6: {
                    return p2.getText();
                }
                case 7: {
                    if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                        return this._coerceIntegral(p2, ctxt);
                    }
                    return p2.getNumberValue();
                }
                case 8: {
                    if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return p2.getDecimalValue();
                    }
                    return p2.getNumberValue();
                }
                case 9: {
                    return Boolean.TRUE;
                }
                case 10: {
                    return Boolean.FALSE;
                }
                case 2: {
                    return new LinkedHashMap(2);
                }
                case 11: {
                    return null;
                }
            }
            return ctxt.handleUnexpectedToken(Object.class, p2);
        }

        @Override
        public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
            switch (p2.getCurrentTokenId()) {
                case 1: 
                case 3: 
                case 5: {
                    return typeDeserializer.deserializeTypedFromAny(p2, ctxt);
                }
                case 6: {
                    return p2.getText();
                }
                case 7: {
                    if (ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                        return p2.getBigIntegerValue();
                    }
                    return p2.getNumberValue();
                }
                case 8: {
                    if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return p2.getDecimalValue();
                    }
                    return p2.getNumberValue();
                }
                case 9: {
                    return Boolean.TRUE;
                }
                case 10: {
                    return Boolean.FALSE;
                }
                case 12: {
                    return p2.getEmbeddedObject();
                }
                case 11: {
                    return null;
                }
            }
            return ctxt.handleUnexpectedToken(Object.class, p2);
        }

        @Override
        public Object deserialize(JsonParser p2, DeserializationContext ctxt, Object intoValue) throws IOException {
            if (this._nonMerging) {
                return this.deserialize(p2, ctxt);
            }
            switch (p2.getCurrentTokenId()) {
                case 2: 
                case 4: {
                    return intoValue;
                }
                case 1: {
                    JsonToken t2 = p2.nextToken();
                    if (t2 == JsonToken.END_OBJECT) {
                        return intoValue;
                    }
                }
                case 5: {
                    if (!(intoValue instanceof Map)) break;
                    Map m2 = (Map)intoValue;
                    String key = p2.getCurrentName();
                    do {
                        p2.nextToken();
                        Object old = m2.get(key);
                        Object newV = old != null ? this.deserialize(p2, ctxt, old) : this.deserialize(p2, ctxt);
                        if (newV == old) continue;
                        m2.put(key, newV);
                    } while ((key = p2.nextFieldName()) != null);
                    return intoValue;
                }
                case 3: {
                    JsonToken t3 = p2.nextToken();
                    if (t3 == JsonToken.END_ARRAY) {
                        return intoValue;
                    }
                    if (!(intoValue instanceof Collection)) break;
                    Collection c10 = (Collection)intoValue;
                    do {
                        c10.add(this.deserialize(p2, ctxt));
                    } while (p2.nextToken() != JsonToken.END_ARRAY);
                    return intoValue;
                }
            }
            return this.deserialize(p2, ctxt);
        }

        protected Object mapArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
            Object value = this.deserialize(p2, ctxt);
            if (p2.nextToken() == JsonToken.END_ARRAY) {
                ArrayList<Object> l2 = new ArrayList<Object>(2);
                l2.add(value);
                return l2;
            }
            Object value2 = this.deserialize(p2, ctxt);
            if (p2.nextToken() == JsonToken.END_ARRAY) {
                ArrayList<Object> l3 = new ArrayList<Object>(2);
                l3.add(value);
                l3.add(value2);
                return l3;
            }
            ObjectBuffer buffer = ctxt.leaseObjectBuffer();
            Object[] values = buffer.resetAndStart();
            int ptr = 0;
            values[ptr++] = value;
            values[ptr++] = value2;
            int totalSize = ptr;
            do {
                value = this.deserialize(p2, ctxt);
                ++totalSize;
                if (ptr >= values.length) {
                    values = buffer.appendCompletedChunk(values);
                    ptr = 0;
                }
                values[ptr++] = value;
            } while (p2.nextToken() != JsonToken.END_ARRAY);
            ArrayList<Object> result = new ArrayList<Object>(totalSize);
            buffer.completeAndClearBuffer(values, ptr, result);
            return result;
        }

        protected Object[] mapArrayToArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
            ObjectBuffer buffer = ctxt.leaseObjectBuffer();
            Object[] values = buffer.resetAndStart();
            int ptr = 0;
            do {
                Object value = this.deserialize(p2, ctxt);
                if (ptr >= values.length) {
                    values = buffer.appendCompletedChunk(values);
                    ptr = 0;
                }
                values[ptr++] = value;
            } while (p2.nextToken() != JsonToken.END_ARRAY);
            return buffer.completeAndClearBuffer(values, ptr);
        }

        protected Object mapObject(JsonParser p2, DeserializationContext ctxt) throws IOException {
            String key1 = p2.getText();
            p2.nextToken();
            Object value1 = this.deserialize(p2, ctxt);
            String key2 = p2.nextFieldName();
            if (key2 == null) {
                LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>(2);
                result.put(key1, value1);
                return result;
            }
            p2.nextToken();
            Object value2 = this.deserialize(p2, ctxt);
            String key = p2.nextFieldName();
            if (key == null) {
                LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>(4);
                result.put(key1, value1);
                result.put(key2, value2);
                return result;
            }
            LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put(key1, value1);
            result.put(key2, value2);
            do {
                p2.nextToken();
                result.put(key, this.deserialize(p2, ctxt));
            } while ((key = p2.nextFieldName()) != null);
            return result;
        }
    }
}

