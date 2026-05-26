/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

class FactoryBasedEnumDeserializer
extends StdDeserializer<Object>
implements ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected final JavaType _inputType;
    protected final boolean _hasArgs;
    protected final AnnotatedMethod _factory;
    protected final JsonDeserializer<?> _deser;
    protected final ValueInstantiator _valueInstantiator;
    protected final SettableBeanProperty[] _creatorProps;
    private transient PropertyBasedCreator _propCreator;

    public FactoryBasedEnumDeserializer(Class<?> cls, AnnotatedMethod f10, JavaType paramType, ValueInstantiator valueInstantiator, SettableBeanProperty[] creatorProps) {
        super(cls);
        this._factory = f10;
        this._hasArgs = true;
        this._inputType = paramType.hasRawClass(String.class) ? null : paramType;
        this._deser = null;
        this._valueInstantiator = valueInstantiator;
        this._creatorProps = creatorProps;
    }

    public FactoryBasedEnumDeserializer(Class<?> cls, AnnotatedMethod f10) {
        super(cls);
        this._factory = f10;
        this._hasArgs = false;
        this._inputType = null;
        this._deser = null;
        this._valueInstantiator = null;
        this._creatorProps = null;
    }

    protected FactoryBasedEnumDeserializer(FactoryBasedEnumDeserializer base, JsonDeserializer<?> deser) {
        super(base._valueClass);
        this._inputType = base._inputType;
        this._factory = base._factory;
        this._hasArgs = base._hasArgs;
        this._valueInstantiator = base._valueInstantiator;
        this._creatorProps = base._creatorProps;
        this._deser = deser;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        if (this._deser == null && this._inputType != null && this._creatorProps == null) {
            return new FactoryBasedEnumDeserializer(this, ctxt.findContextualValueDeserializer(this._inputType, property));
        }
        return this;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.FALSE;
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        String value = null;
        if (this._deser != null) {
            value = (String)this._deser.deserialize(p2, ctxt);
        } else if (this._hasArgs) {
            JsonToken curr = p2.getCurrentToken();
            if (curr == JsonToken.VALUE_STRING || curr == JsonToken.FIELD_NAME) {
                value = p2.getText();
            } else {
                if (this._creatorProps != null && p2.isExpectedStartObjectToken()) {
                    if (this._propCreator == null) {
                        this._propCreator = PropertyBasedCreator.construct(ctxt, this._valueInstantiator, this._creatorProps, ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
                    }
                    p2.nextToken();
                    return this.deserializeEnumUsingPropertyBased(p2, ctxt, this._propCreator);
                }
                value = p2.getValueAsString();
            }
        } else {
            p2.skipChildren();
            try {
                return this._factory.call();
            }
            catch (Exception e10) {
                Throwable t2 = ClassUtil.throwRootCauseIfIOE(e10);
                return ctxt.handleInstantiationProblem(this._valueClass, null, t2);
            }
        }
        try {
            return this._factory.callOnWith(this._valueClass, value);
        }
        catch (Exception e11) {
            Throwable t3 = ClassUtil.throwRootCauseIfIOE(e11);
            if (ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL) && t3 instanceof IllegalArgumentException) {
                return null;
            }
            return ctxt.handleInstantiationProblem(this._valueClass, value, t3);
        }
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        if (this._deser == null) {
            return this.deserialize(p2, ctxt);
        }
        return typeDeserializer.deserializeTypedFromAny(p2, ctxt);
    }

    protected Object deserializeEnumUsingPropertyBased(JsonParser p2, DeserializationContext ctxt, PropertyBasedCreator creator) throws IOException {
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, null);
        JsonToken t2 = p2.getCurrentToken();
        while (t2 == JsonToken.FIELD_NAME) {
            String propName = p2.getCurrentName();
            p2.nextToken();
            SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
            if (creatorProp != null) {
                buffer.assignParameter(creatorProp, this._deserializeWithErrorWrapping(p2, ctxt, creatorProp));
            } else if (buffer.readIdProperty(propName)) {
                // empty if block
            }
            t2 = p2.nextToken();
        }
        return creator.build(ctxt, buffer);
    }

    protected final Object _deserializeWithErrorWrapping(JsonParser p2, DeserializationContext ctxt, SettableBeanProperty prop) throws IOException {
        try {
            return prop.deserialize(p2, ctxt);
        }
        catch (Exception e10) {
            this.wrapAndThrow(e10, this._valueClass.getClass(), prop.getName(), ctxt);
            return null;
        }
    }

    public void wrapAndThrow(Throwable t2, Object bean, String fieldName, DeserializationContext ctxt) throws IOException {
        throw JsonMappingException.wrapWithPath(this.throwOrReturnThrowable(t2, ctxt), bean, fieldName);
    }

    private Throwable throwOrReturnThrowable(Throwable t2, DeserializationContext ctxt) throws IOException {
        boolean wrap;
        t2 = ClassUtil.getRootCause(t2);
        ClassUtil.throwIfError(t2);
        boolean bl2 = wrap = ctxt == null || ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (t2 instanceof IOException) {
            if (!wrap || !(t2 instanceof JsonProcessingException)) {
                throw (IOException)t2;
            }
        } else if (!wrap) {
            ClassUtil.throwIfRTE(t2);
        }
        return t2;
    }
}

