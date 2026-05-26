/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.Collection;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;

@JacksonStdImpl
public final class StringCollectionDeserializer
extends ContainerDeserializerBase<Collection<String>>
implements ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected final JsonDeserializer<String> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final JsonDeserializer<Object> _delegateDeserializer;

    public StringCollectionDeserializer(JavaType collectionType, JsonDeserializer<?> valueDeser, ValueInstantiator valueInstantiator) {
        this(collectionType, valueInstantiator, null, valueDeser, valueDeser, null);
    }

    protected StringCollectionDeserializer(JavaType collectionType, ValueInstantiator valueInstantiator, JsonDeserializer<?> delegateDeser, JsonDeserializer<?> valueDeser, NullValueProvider nuller, Boolean unwrapSingle) {
        super(collectionType, nuller, unwrapSingle);
        this._valueDeserializer = valueDeser;
        this._valueInstantiator = valueInstantiator;
        this._delegateDeserializer = delegateDeser;
    }

    protected StringCollectionDeserializer withResolved(JsonDeserializer<?> delegateDeser, JsonDeserializer<?> valueDeser, NullValueProvider nuller, Boolean unwrapSingle) {
        if (this._unwrapSingle == unwrapSingle && this._nullProvider == nuller && this._valueDeserializer == valueDeser && this._delegateDeserializer == delegateDeser) {
            return this;
        }
        return new StringCollectionDeserializer(this._containerType, this._valueInstantiator, delegateDeser, valueDeser, nuller, unwrapSingle);
    }

    @Override
    public boolean isCachable() {
        return this._valueDeserializer == null && this._delegateDeserializer == null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        AnnotatedWithParams delegateCreator;
        JsonDeserializer<Object> delegate = null;
        if (this._valueInstantiator != null && (delegateCreator = this._valueInstantiator.getDelegateCreator()) != null) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(ctxt.getConfig());
            delegate = this.findDeserializer(ctxt, delegateType, property);
        }
        JsonDeserializer<Object> valueDeser = this._valueDeserializer;
        JavaType valueType = this._containerType.getContentType();
        if (valueDeser == null) {
            if ((valueDeser = this.findConvertingContentDeserializer(ctxt, property, valueDeser)) == null) {
                valueDeser = ctxt.findContextualValueDeserializer(valueType, property);
            }
        } else {
            valueDeser = ctxt.handleSecondaryContextualization(valueDeser, property, valueType);
        }
        Boolean unwrapSingle = this.findFormatFeature(ctxt, property, Collection.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        NullValueProvider nuller = this.findContentNullProvider(ctxt, property, valueDeser);
        if (this.isDefaultDeserializer(valueDeser)) {
            valueDeser = null;
        }
        return this.withResolved(delegate, valueDeser, nuller, unwrapSingle);
    }

    @Override
    public JsonDeserializer<Object> getContentDeserializer() {
        JsonDeserializer<Object> deser = this._valueDeserializer;
        return deser;
    }

    @Override
    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    @Override
    public Collection<String> deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._delegateDeserializer != null) {
            return (Collection)this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p2, ctxt));
        }
        Collection result = (Collection)this._valueInstantiator.createUsingDefault(ctxt);
        return this.deserialize(p2, ctxt, result);
    }

    @Override
    public Collection<String> deserialize(JsonParser p2, DeserializationContext ctxt, Collection<String> result) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            return this.handleNonArray(p2, ctxt, result);
        }
        if (this._valueDeserializer != null) {
            return this.deserializeUsingCustom(p2, ctxt, result, this._valueDeserializer);
        }
        try {
            while (true) {
                String value;
                if ((value = p2.nextTextValue()) != null) {
                    result.add(value);
                    continue;
                }
                JsonToken t2 = p2.getCurrentToken();
                if (t2 != JsonToken.END_ARRAY) {
                    if (t2 == JsonToken.VALUE_NULL) {
                        if (this._skipNullValues) continue;
                        value = (String)this._nullProvider.getNullValue(ctxt);
                    } else {
                        value = this._parseString(p2, ctxt);
                    }
                    result.add(value);
                    continue;
                }
                break;
            }
        }
        catch (Exception e10) {
            throw JsonMappingException.wrapWithPath((Throwable)e10, result, result.size());
        }
        return result;
    }

    private Collection<String> deserializeUsingCustom(JsonParser p2, DeserializationContext ctxt, Collection<String> result, JsonDeserializer<String> deser) throws IOException {
        while (true) {
            String value;
            if (p2.nextTextValue() == null) {
                JsonToken t2 = p2.getCurrentToken();
                if (t2 == JsonToken.END_ARRAY) break;
                if (t2 == JsonToken.VALUE_NULL) {
                    if (this._skipNullValues) continue;
                    value = (String)this._nullProvider.getNullValue(ctxt);
                } else {
                    value = deser.deserialize(p2, ctxt);
                }
            } else {
                value = deser.deserialize(p2, ctxt);
            }
            result.add(value);
        }
        return result;
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(p2, ctxt);
    }

    private final Collection<String> handleNonArray(JsonParser p2, DeserializationContext ctxt, Collection<String> result) throws IOException {
        String value;
        boolean canWrap;
        boolean bl2 = canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (!canWrap) {
            return (Collection)ctxt.handleUnexpectedToken(this._containerType.getRawClass(), p2);
        }
        JsonDeserializer<String> valueDes = this._valueDeserializer;
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.VALUE_NULL) {
            if (this._skipNullValues) {
                return result;
            }
            value = (String)this._nullProvider.getNullValue(ctxt);
        } else {
            value = valueDes == null ? this._parseString(p2, ctxt) : valueDes.deserialize(p2, ctxt);
        }
        result.add(value);
        return result;
    }
}

