/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.KeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;

@JacksonStdImpl
public class MapEntryDeserializer
extends ContainerDeserializerBase<Map.Entry<Object, Object>>
implements ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected final KeyDeserializer _keyDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public MapEntryDeserializer(JavaType type, KeyDeserializer keyDeser, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser) {
        super(type);
        if (type.containedTypeCount() != 2) {
            throw new IllegalArgumentException("Missing generic type information for " + type);
        }
        this._keyDeserializer = keyDeser;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = valueTypeDeser;
    }

    protected MapEntryDeserializer(MapEntryDeserializer src) {
        super(src);
        this._keyDeserializer = src._keyDeserializer;
        this._valueDeserializer = src._valueDeserializer;
        this._valueTypeDeserializer = src._valueTypeDeserializer;
    }

    protected MapEntryDeserializer(MapEntryDeserializer src, KeyDeserializer keyDeser, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser) {
        super(src);
        this._keyDeserializer = keyDeser;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = valueTypeDeser;
    }

    protected MapEntryDeserializer withResolved(KeyDeserializer keyDeser, TypeDeserializer valueTypeDeser, JsonDeserializer<?> valueDeser) {
        if (this._keyDeserializer == keyDeser && this._valueDeserializer == valueDeser && this._valueTypeDeserializer == valueTypeDeser) {
            return this;
        }
        return new MapEntryDeserializer(this, keyDeser, valueDeser, valueTypeDeser);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        KeyDeserializer kd = this._keyDeserializer;
        if (kd == null) {
            kd = ctxt.findKeyDeserializer(this._containerType.containedType(0), property);
        } else if (kd instanceof ContextualKeyDeserializer) {
            kd = ((ContextualKeyDeserializer)((Object)kd)).createContextual(ctxt, property);
        }
        JsonDeserializer<Object> vd = this._valueDeserializer;
        vd = this.findConvertingContentDeserializer(ctxt, property, vd);
        JavaType contentType = this._containerType.containedType(1);
        vd = vd == null ? ctxt.findContextualValueDeserializer(contentType, property) : ctxt.handleSecondaryContextualization(vd, property, contentType);
        TypeDeserializer vtd = this._valueTypeDeserializer;
        if (vtd != null) {
            vtd = vtd.forProperty(property);
        }
        return this.withResolved(kd, vtd, vd);
    }

    @Override
    public JavaType getContentType() {
        return this._containerType.containedType(1);
    }

    @Override
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override
    public Map.Entry<Object, Object> deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        if (t2 != JsonToken.START_OBJECT && t2 != JsonToken.FIELD_NAME && t2 != JsonToken.END_OBJECT) {
            return (Map.Entry)this._deserializeFromEmpty(p2, ctxt);
        }
        if (t2 == JsonToken.START_OBJECT) {
            t2 = p2.nextToken();
        }
        if (t2 != JsonToken.FIELD_NAME) {
            if (t2 == JsonToken.END_OBJECT) {
                return (Map.Entry)ctxt.reportInputMismatch(this, "Cannot deserialize a Map.Entry out of empty JSON Object", new Object[0]);
            }
            return (Map.Entry)ctxt.handleUnexpectedToken(this.handledType(), p2);
        }
        KeyDeserializer keyDes = this._keyDeserializer;
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        String keyStr = p2.getCurrentName();
        Object key = keyDes.deserializeKey(keyStr, ctxt);
        Object value = null;
        t2 = p2.nextToken();
        try {
            value = t2 == JsonToken.VALUE_NULL ? valueDes.getNullValue(ctxt) : (typeDeser == null ? valueDes.deserialize(p2, ctxt) : valueDes.deserializeWithType(p2, ctxt, typeDeser));
        }
        catch (Exception e10) {
            this.wrapAndThrow(e10, Map.Entry.class, keyStr);
        }
        t2 = p2.nextToken();
        if (t2 != JsonToken.END_OBJECT) {
            if (t2 == JsonToken.FIELD_NAME) {
                ctxt.reportInputMismatch(this, "Problem binding JSON into Map.Entry: more than one entry in JSON (second field: '%s')", p2.getCurrentName());
            } else {
                ctxt.reportInputMismatch(this, "Problem binding JSON into Map.Entry: unexpected content after JSON Object entry: " + (Object)((Object)t2), new Object[0]);
            }
            return null;
        }
        return new AbstractMap.SimpleEntry<Object, Object>(key, value);
    }

    @Override
    public Map.Entry<Object, Object> deserialize(JsonParser p2, DeserializationContext ctxt, Map.Entry<Object, Object> result) throws IOException {
        throw new IllegalStateException("Cannot update Map.Entry values");
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(p2, ctxt);
    }
}

