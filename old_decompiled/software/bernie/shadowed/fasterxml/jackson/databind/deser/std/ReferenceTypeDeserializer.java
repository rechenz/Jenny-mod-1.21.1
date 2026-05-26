/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.AccessPattern;

public abstract class ReferenceTypeDeserializer<T>
extends StdDeserializer<T>
implements ContextualDeserializer {
    private static final long serialVersionUID = 2L;
    protected final JavaType _fullType;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;

    public ReferenceTypeDeserializer(JavaType fullType, ValueInstantiator vi, TypeDeserializer typeDeser, JsonDeserializer<?> deser) {
        super(fullType);
        this._valueInstantiator = vi;
        this._fullType = fullType;
        this._valueDeserializer = deser;
        this._valueTypeDeserializer = typeDeser;
    }

    @Deprecated
    public ReferenceTypeDeserializer(JavaType fullType, TypeDeserializer typeDeser, JsonDeserializer<?> deser) {
        this(fullType, null, typeDeser, deser);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<Object> deser = this._valueDeserializer;
        deser = deser == null ? ctxt.findContextualValueDeserializer(this._fullType.getReferencedType(), property) : ctxt.handleSecondaryContextualization(deser, property, this._fullType.getReferencedType());
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        if (typeDeser != null) {
            typeDeser = typeDeser.forProperty(property);
        }
        if (deser == this._valueDeserializer && typeDeser == this._valueTypeDeserializer) {
            return this;
        }
        return this.withResolved(typeDeser, deser);
    }

    @Override
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    protected abstract ReferenceTypeDeserializer<T> withResolved(TypeDeserializer var1, JsonDeserializer<?> var2);

    @Override
    public abstract T getNullValue(DeserializationContext var1);

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) {
        return this.getNullValue(ctxt);
    }

    public abstract T referenceValue(Object var1);

    public abstract T updateReference(T var1, Object var2);

    public abstract Object getReferenced(T var1);

    @Override
    public JavaType getValueType() {
        return this._fullType;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return this._valueDeserializer == null ? null : this._valueDeserializer.supportsUpdate(config);
    }

    @Override
    public T deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._valueInstantiator != null) {
            Object value = this._valueInstantiator.createUsingDefault(ctxt);
            return (T)this.deserialize(p2, ctxt, value);
        }
        Object contents = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p2, ctxt) : this._valueDeserializer.deserializeWithType(p2, ctxt, this._valueTypeDeserializer);
        return this.referenceValue(contents);
    }

    @Override
    public T deserialize(JsonParser p2, DeserializationContext ctxt, T reference) throws IOException {
        Object contents;
        Boolean B = this._valueDeserializer.supportsUpdate(ctxt.getConfig());
        if (B.equals(Boolean.FALSE) || this._valueTypeDeserializer != null) {
            contents = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p2, ctxt) : this._valueDeserializer.deserializeWithType(p2, ctxt, this._valueTypeDeserializer);
        } else {
            contents = this.getReferenced(reference);
            if (contents == null) {
                contents = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p2, ctxt) : this._valueDeserializer.deserializeWithType(p2, ctxt, this._valueTypeDeserializer);
                return this.referenceValue(contents);
            }
            contents = this._valueDeserializer.deserialize(p2, ctxt, contents);
        }
        return this.updateReference(reference, contents);
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.VALUE_NULL) {
            return this.getNullValue(ctxt);
        }
        if (this._valueTypeDeserializer == null) {
            return this.deserialize(p2, ctxt);
        }
        return this.referenceValue(this._valueTypeDeserializer.deserializeTypedFromAny(p2, ctxt));
    }
}

