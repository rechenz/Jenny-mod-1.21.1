/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.EnumMap;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.KeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class EnumMapDeserializer
extends ContainerDeserializerBase<EnumMap<?, ?>>
implements ContextualDeserializer,
ResolvableDeserializer {
    private static final long serialVersionUID = 1L;
    protected final Class<?> _enumClass;
    protected KeyDeserializer _keyDeserializer;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected PropertyBasedCreator _propertyBasedCreator;

    public EnumMapDeserializer(JavaType mapType, ValueInstantiator valueInst, KeyDeserializer keyDeser, JsonDeserializer<?> valueDeser, TypeDeserializer vtd, NullValueProvider nuller) {
        super(mapType, nuller, null);
        this._enumClass = mapType.getKeyType().getRawClass();
        this._keyDeserializer = keyDeser;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = vtd;
        this._valueInstantiator = valueInst;
    }

    protected EnumMapDeserializer(EnumMapDeserializer base, KeyDeserializer keyDeser, JsonDeserializer<?> valueDeser, TypeDeserializer vtd, NullValueProvider nuller) {
        super(base, nuller, base._unwrapSingle);
        this._enumClass = base._enumClass;
        this._keyDeserializer = keyDeser;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = vtd;
        this._valueInstantiator = base._valueInstantiator;
        this._delegateDeserializer = base._delegateDeserializer;
        this._propertyBasedCreator = base._propertyBasedCreator;
    }

    @Deprecated
    public EnumMapDeserializer(JavaType mapType, KeyDeserializer keyDeser, JsonDeserializer<?> valueDeser, TypeDeserializer vtd) {
        this(mapType, null, keyDeser, valueDeser, vtd, null);
    }

    public EnumMapDeserializer withResolved(KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer, TypeDeserializer valueTypeDeser, NullValueProvider nuller) {
        if (keyDeserializer == this._keyDeserializer && nuller == this._nullProvider && valueDeserializer == this._valueDeserializer && valueTypeDeser == this._valueTypeDeserializer) {
            return this;
        }
        return new EnumMapDeserializer(this, keyDeserializer, valueDeserializer, valueTypeDeser, nuller);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        if (this._valueInstantiator != null) {
            if (this._valueInstantiator.canCreateUsingDelegate()) {
                JavaType delegateType = this._valueInstantiator.getDelegateType(ctxt.getConfig());
                if (delegateType == null) {
                    ctxt.reportBadDefinition(this._containerType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", this._containerType, this._valueInstantiator.getClass().getName()));
                }
                this._delegateDeserializer = this.findDeserializer(ctxt, delegateType, null);
            } else if (this._valueInstantiator.canCreateUsingArrayDelegate()) {
                JavaType delegateType = this._valueInstantiator.getArrayDelegateType(ctxt.getConfig());
                if (delegateType == null) {
                    ctxt.reportBadDefinition(this._containerType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", this._containerType, this._valueInstantiator.getClass().getName()));
                }
                this._delegateDeserializer = this.findDeserializer(ctxt, delegateType, null);
            } else if (this._valueInstantiator.canCreateFromObjectWith()) {
                SettableBeanProperty[] creatorProps = this._valueInstantiator.getFromObjectArguments(ctxt.getConfig());
                this._propertyBasedCreator = PropertyBasedCreator.construct(ctxt, this._valueInstantiator, creatorProps, ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
            }
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        KeyDeserializer keyDeser = this._keyDeserializer;
        if (keyDeser == null) {
            keyDeser = ctxt.findKeyDeserializer(this._containerType.getKeyType(), property);
        }
        JsonDeserializer<Object> valueDeser = this._valueDeserializer;
        JavaType vt = this._containerType.getContentType();
        valueDeser = valueDeser == null ? ctxt.findContextualValueDeserializer(vt, property) : ctxt.handleSecondaryContextualization(valueDeser, property, vt);
        TypeDeserializer vtd = this._valueTypeDeserializer;
        if (vtd != null) {
            vtd = vtd.forProperty(property);
        }
        return this.withResolved(keyDeser, valueDeser, vtd, this.findContentNullProvider(ctxt, property, valueDeser));
    }

    @Override
    public boolean isCachable() {
        return this._valueDeserializer == null && this._keyDeserializer == null && this._valueTypeDeserializer == null;
    }

    @Override
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return this.constructMap(ctxt);
    }

    @Override
    public EnumMap<?, ?> deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._propertyBasedCreator != null) {
            return this._deserializeUsingProperties(p2, ctxt);
        }
        if (this._delegateDeserializer != null) {
            return (EnumMap)this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p2, ctxt));
        }
        JsonToken t2 = p2.getCurrentToken();
        if (t2 != JsonToken.START_OBJECT && t2 != JsonToken.FIELD_NAME && t2 != JsonToken.END_OBJECT) {
            if (t2 == JsonToken.VALUE_STRING) {
                return (EnumMap)this._valueInstantiator.createFromString(ctxt, p2.getText());
            }
            return (EnumMap)this._deserializeFromEmpty(p2, ctxt);
        }
        EnumMap<?, ?> result = this.constructMap(ctxt);
        return this.deserialize(p2, ctxt, result);
    }

    @Override
    public EnumMap<?, ?> deserialize(JsonParser p2, DeserializationContext ctxt, EnumMap result) throws IOException {
        String keyName;
        p2.setCurrentValue(result);
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        while ((keyName = p2.nextFieldName()) != null) {
            Object value;
            Enum key = (Enum)this._keyDeserializer.deserializeKey(keyName, ctxt);
            if (key == null) {
                if (!ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    return (EnumMap)ctxt.handleWeirdStringValue(this._enumClass, keyName, "value not one of declared Enum instance names for %s", this._containerType.getKeyType());
                }
                p2.nextToken();
                p2.skipChildren();
                continue;
            }
            JsonToken t2 = p2.nextToken();
            try {
                if (t2 == JsonToken.VALUE_NULL) {
                    if (this._skipNullValues) continue;
                    value = this._nullProvider.getNullValue(ctxt);
                } else {
                    value = typeDeser == null ? valueDes.deserialize(p2, ctxt) : valueDes.deserializeWithType(p2, ctxt, typeDeser);
                }
            }
            catch (Exception e10) {
                return (EnumMap)this.wrapAndThrow(e10, result, keyName);
            }
            result.put(key, value);
        }
        return result;
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(p2, ctxt);
    }

    protected EnumMap<?, ?> constructMap(DeserializationContext ctxt) throws JsonMappingException {
        if (this._valueInstantiator == null) {
            return new EnumMap(this._enumClass);
        }
        try {
            if (!this._valueInstantiator.canCreateUsingDefault()) {
                return (EnumMap)ctxt.handleMissingInstantiator(this.handledType(), this.getValueInstantiator(), null, "no default constructor found", new Object[0]);
            }
            return (EnumMap)this._valueInstantiator.createUsingDefault(ctxt);
        }
        catch (IOException e10) {
            return (EnumMap)ClassUtil.throwAsMappingException(ctxt, e10);
        }
    }

    public EnumMap<?, ?> _deserializeUsingProperties(JsonParser p2, DeserializationContext ctxt) throws IOException {
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, null);
        String keyName = p2.isExpectedStartObjectToken() ? p2.nextFieldName() : (p2.hasToken(JsonToken.FIELD_NAME) ? p2.getCurrentName() : null);
        while (keyName != null) {
            block14: {
                JsonToken t2 = p2.nextToken();
                SettableBeanProperty prop = creator.findCreatorProperty(keyName);
                if (prop != null) {
                    if (buffer.assignParameter(prop, prop.deserialize(p2, ctxt))) {
                        EnumMap result;
                        try {
                            result = (EnumMap)creator.build(ctxt, buffer);
                        }
                        catch (Exception e10) {
                            return (EnumMap)this.wrapAndThrow(e10, this._containerType.getRawClass(), keyName);
                        }
                        return this.deserialize(p2, ctxt, result);
                    }
                } else {
                    Enum key = (Enum)this._keyDeserializer.deserializeKey(keyName, ctxt);
                    if (key == null) {
                        if (!ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                            return (EnumMap)ctxt.handleWeirdStringValue(this._enumClass, keyName, "value not one of declared Enum instance names for %s", this._containerType.getKeyType());
                        }
                        p2.nextToken();
                        p2.skipChildren();
                    } else {
                        Object value;
                        block15: {
                            try {
                                if (t2 == JsonToken.VALUE_NULL) {
                                    if (this._skipNullValues) break block14;
                                    value = this._nullProvider.getNullValue(ctxt);
                                    break block15;
                                }
                                value = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p2, ctxt) : this._valueDeserializer.deserializeWithType(p2, ctxt, this._valueTypeDeserializer);
                            }
                            catch (Exception e11) {
                                this.wrapAndThrow(e11, this._containerType.getRawClass(), keyName);
                                return null;
                            }
                        }
                        buffer.bufferMapProperty(key, value);
                    }
                }
            }
            keyName = p2.nextFieldName();
        }
        try {
            return (EnumMap)creator.build(ctxt, buffer);
        }
        catch (Exception e12) {
            this.wrapAndThrow(e12, this._containerType.getRawClass(), keyName);
            return null;
        }
    }
}

