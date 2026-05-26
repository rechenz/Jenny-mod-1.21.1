/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl;

import java.io.IOException;
import java.io.Serializable;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.util.JsonParserSequence;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

public class AsArrayTypeDeserializer
extends TypeDeserializerBase
implements Serializable {
    private static final long serialVersionUID = 1L;

    public AsArrayTypeDeserializer(JavaType bt2, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
        super(bt2, idRes, typePropertyName, typeIdVisible, defaultImpl);
    }

    public AsArrayTypeDeserializer(AsArrayTypeDeserializer src, BeanProperty property) {
        super(src, property);
    }

    @Override
    public TypeDeserializer forProperty(BeanProperty prop) {
        return prop == this._property ? this : new AsArrayTypeDeserializer(this, prop);
    }

    @Override
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.WRAPPER_ARRAY;
    }

    @Override
    public Object deserializeTypedFromArray(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return this._deserialize(jp, ctxt);
    }

    @Override
    public Object deserializeTypedFromObject(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return this._deserialize(jp, ctxt);
    }

    @Override
    public Object deserializeTypedFromScalar(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return this._deserialize(jp, ctxt);
    }

    @Override
    public Object deserializeTypedFromAny(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return this._deserialize(jp, ctxt);
    }

    protected Object _deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object typeId;
        if (p2.canReadTypeId() && (typeId = p2.getTypeId()) != null) {
            return this._deserializeWithNativeTypeId(p2, ctxt, typeId);
        }
        boolean hadStartArray = p2.isExpectedStartArrayToken();
        String typeId2 = this._locateTypeId(p2, ctxt);
        JsonDeserializer<Object> deser = this._findDeserializer(ctxt, typeId2);
        if (this._typeIdVisible && !this._usesExternalId() && p2.getCurrentToken() == JsonToken.START_OBJECT) {
            TokenBuffer tb = new TokenBuffer(null, false);
            tb.writeStartObject();
            tb.writeFieldName(this._typePropertyName);
            tb.writeString(typeId2);
            p2.clearCurrentToken();
            p2 = JsonParserSequence.createFlattened(false, tb.asParser(p2), p2);
            p2.nextToken();
        }
        Object value = deser.deserialize(p2, ctxt);
        if (hadStartArray && p2.nextToken() != JsonToken.END_ARRAY) {
            ctxt.reportWrongTokenException(this.baseType(), JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value", new Object[0]);
        }
        return value;
    }

    protected String _locateTypeId(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            if (this._defaultImpl != null) {
                return this._idResolver.idFromBaseType();
            }
            ctxt.reportWrongTokenException(this.baseType(), JsonToken.START_ARRAY, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + this.baseTypeName(), new Object[0]);
            return null;
        }
        JsonToken t2 = p2.nextToken();
        if (t2 == JsonToken.VALUE_STRING) {
            String result = p2.getText();
            p2.nextToken();
            return result;
        }
        if (this._defaultImpl != null) {
            return this._idResolver.idFromBaseType();
        }
        ctxt.reportWrongTokenException(this.baseType(), JsonToken.VALUE_STRING, "need JSON String that contains type id (for subtype of %s)", this.baseTypeName());
        return null;
    }

    protected boolean _usesExternalId() {
        return false;
    }
}

