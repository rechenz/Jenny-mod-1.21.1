/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;

public class BeanAsArrayDeserializer
extends BeanDeserializerBase {
    private static final long serialVersionUID = 1L;
    protected final BeanDeserializerBase _delegate;
    protected final SettableBeanProperty[] _orderedProperties;

    public BeanAsArrayDeserializer(BeanDeserializerBase delegate, SettableBeanProperty[] ordered) {
        super(delegate);
        this._delegate = delegate;
        this._orderedProperties = ordered;
    }

    @Override
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer unwrapper) {
        return this._delegate.unwrappingDeserializer(unwrapper);
    }

    @Override
    public BeanDeserializerBase withObjectIdReader(ObjectIdReader oir) {
        return new BeanAsArrayDeserializer(this._delegate.withObjectIdReader(oir), this._orderedProperties);
    }

    @Override
    public BeanDeserializerBase withIgnorableProperties(Set<String> ignorableProps) {
        return new BeanAsArrayDeserializer(this._delegate.withIgnorableProperties(ignorableProps), this._orderedProperties);
    }

    @Override
    public BeanDeserializerBase withBeanProperties(BeanPropertyMap props) {
        return new BeanAsArrayDeserializer(this._delegate.withBeanProperties(props), this._orderedProperties);
    }

    @Override
    protected BeanDeserializerBase asArrayDeserializer() {
        return this;
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            return this._deserializeFromNonArray(p2, ctxt);
        }
        if (!this._vanillaProcessing) {
            return this._deserializeNonVanilla(p2, ctxt);
        }
        Object bean = this._valueInstantiator.createUsingDefault(ctxt);
        p2.setCurrentValue(bean);
        SettableBeanProperty[] props = this._orderedProperties;
        int i2 = 0;
        int propCount = props.length;
        while (true) {
            if (p2.nextToken() == JsonToken.END_ARRAY) {
                return bean;
            }
            if (i2 == propCount) break;
            SettableBeanProperty prop = props[i2];
            if (prop != null) {
                try {
                    prop.deserializeAndSet(p2, ctxt, bean);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, bean, prop.getName(), ctxt);
                }
            } else {
                p2.skipChildren();
            }
            ++i2;
        }
        if (!this._ignoreAllUnknown && ctxt.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            ctxt.reportWrongTokenException(this, JsonToken.END_ARRAY, "Unexpected JSON values; expected at most %d properties (in JSON Array)", propCount);
        }
        do {
            p2.skipChildren();
        } while (p2.nextToken() != JsonToken.END_ARRAY);
        return bean;
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt, Object bean) throws IOException {
        p2.setCurrentValue(bean);
        if (!p2.isExpectedStartArrayToken()) {
            return this._deserializeFromNonArray(p2, ctxt);
        }
        if (this._injectables != null) {
            this.injectValues(ctxt, bean);
        }
        SettableBeanProperty[] props = this._orderedProperties;
        int i2 = 0;
        int propCount = props.length;
        while (true) {
            if (p2.nextToken() == JsonToken.END_ARRAY) {
                return bean;
            }
            if (i2 == propCount) break;
            SettableBeanProperty prop = props[i2];
            if (prop != null) {
                try {
                    prop.deserializeAndSet(p2, ctxt, bean);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, bean, prop.getName(), ctxt);
                }
            } else {
                p2.skipChildren();
            }
            ++i2;
        }
        if (!this._ignoreAllUnknown && ctxt.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            ctxt.reportWrongTokenException(this, JsonToken.END_ARRAY, "Unexpected JSON values; expected at most %d properties (in JSON Array)", propCount);
        }
        do {
            p2.skipChildren();
        } while (p2.nextToken() != JsonToken.END_ARRAY);
        return bean;
    }

    @Override
    public Object deserializeFromObject(JsonParser p2, DeserializationContext ctxt) throws IOException {
        return this._deserializeFromNonArray(p2, ctxt);
    }

    protected Object _deserializeNonVanilla(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._nonStandardCreation) {
            return this.deserializeFromObjectUsingNonDefault(p2, ctxt);
        }
        Object bean = this._valueInstantiator.createUsingDefault(ctxt);
        p2.setCurrentValue(bean);
        if (this._injectables != null) {
            this.injectValues(ctxt, bean);
        }
        Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        SettableBeanProperty[] props = this._orderedProperties;
        int i2 = 0;
        int propCount = props.length;
        while (true) {
            if (p2.nextToken() == JsonToken.END_ARRAY) {
                return bean;
            }
            if (i2 == propCount) break;
            SettableBeanProperty prop = props[i2];
            ++i2;
            if (prop != null && (activeView == null || prop.visibleInView(activeView))) {
                try {
                    prop.deserializeAndSet(p2, ctxt, bean);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, bean, prop.getName(), ctxt);
                }
                continue;
            }
            p2.skipChildren();
        }
        if (!this._ignoreAllUnknown) {
            ctxt.reportWrongTokenException(this, JsonToken.END_ARRAY, "Unexpected JSON values; expected at most %d properties (in JSON Array)", propCount);
        }
        do {
            p2.skipChildren();
        } while (p2.nextToken() != JsonToken.END_ARRAY);
        return bean;
    }

    @Override
    protected final Object _deserializeUsingPropertyBased(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Class<?> activeView;
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, this._objectIdReader);
        SettableBeanProperty[] props = this._orderedProperties;
        int propCount = props.length;
        int i2 = 0;
        Object bean = null;
        Class<?> clazz = activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        while (p2.nextToken() != JsonToken.END_ARRAY) {
            block19: {
                SettableBeanProperty prop;
                SettableBeanProperty settableBeanProperty = prop = i2 < propCount ? props[i2] : null;
                if (prop == null) {
                    p2.skipChildren();
                } else if (activeView != null && !prop.visibleInView(activeView)) {
                    p2.skipChildren();
                } else if (bean != null) {
                    try {
                        prop.deserializeAndSet(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, prop.getName(), ctxt);
                    }
                } else {
                    String propName = prop.getName();
                    SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
                    if (creatorProp != null) {
                        if (buffer.assignParameter(creatorProp, creatorProp.deserialize(p2, ctxt))) {
                            try {
                                bean = creator.build(ctxt, buffer);
                            }
                            catch (Exception e11) {
                                this.wrapAndThrow(e11, this._beanType.getRawClass(), propName, ctxt);
                                break block19;
                            }
                            p2.setCurrentValue(bean);
                            if (bean.getClass() != this._beanType.getRawClass()) {
                                ctxt.reportBadDefinition(this._beanType, String.format("Cannot support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type %s, actual type %s", this._beanType.getRawClass().getName(), bean.getClass().getName()));
                            }
                        }
                    } else if (!buffer.readIdProperty(propName)) {
                        buffer.bufferProperty(prop, prop.deserialize(p2, ctxt));
                    }
                }
            }
            ++i2;
        }
        if (bean == null) {
            try {
                bean = creator.build(ctxt, buffer);
            }
            catch (Exception e12) {
                return this.wrapInstantiationProblem(e12, ctxt);
            }
        }
        return bean;
    }

    protected Object _deserializeFromNonArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        return ctxt.handleUnexpectedToken(this.handledType(), p2.getCurrentToken(), p2, "Cannot deserialize a POJO (of type %s) from non-Array representation (token: %s): type/property designed to be serialized as JSON Array", new Object[]{this._beanType.getRawClass().getName(), p2.getCurrentToken()});
    }
}

