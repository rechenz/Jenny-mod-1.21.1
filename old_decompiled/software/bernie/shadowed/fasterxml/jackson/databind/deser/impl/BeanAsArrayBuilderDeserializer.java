/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;

public class BeanAsArrayBuilderDeserializer
extends BeanDeserializerBase {
    private static final long serialVersionUID = 1L;
    protected final BeanDeserializerBase _delegate;
    protected final SettableBeanProperty[] _orderedProperties;
    protected final AnnotatedMethod _buildMethod;
    protected final JavaType _targetType;

    public BeanAsArrayBuilderDeserializer(BeanDeserializerBase delegate, JavaType targetType, SettableBeanProperty[] ordered, AnnotatedMethod buildMethod) {
        super(delegate);
        this._delegate = delegate;
        this._targetType = targetType;
        this._orderedProperties = ordered;
        this._buildMethod = buildMethod;
    }

    @Override
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer unwrapper) {
        return this._delegate.unwrappingDeserializer(unwrapper);
    }

    @Override
    public BeanDeserializerBase withObjectIdReader(ObjectIdReader oir) {
        return new BeanAsArrayBuilderDeserializer(this._delegate.withObjectIdReader(oir), this._targetType, this._orderedProperties, this._buildMethod);
    }

    @Override
    public BeanDeserializerBase withIgnorableProperties(Set<String> ignorableProps) {
        return new BeanAsArrayBuilderDeserializer(this._delegate.withIgnorableProperties(ignorableProps), this._targetType, this._orderedProperties, this._buildMethod);
    }

    @Override
    public BeanDeserializerBase withBeanProperties(BeanPropertyMap props) {
        return new BeanAsArrayBuilderDeserializer(this._delegate.withBeanProperties(props), this._targetType, this._orderedProperties, this._buildMethod);
    }

    @Override
    protected BeanDeserializerBase asArrayDeserializer() {
        return this;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.FALSE;
    }

    protected final Object finishBuild(DeserializationContext ctxt, Object builder) throws IOException {
        try {
            return this._buildMethod.getMember().invoke(builder, (Object[])null);
        }
        catch (Exception e10) {
            return this.wrapInstantiationProblem(e10, ctxt);
        }
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            return this.finishBuild(ctxt, this._deserializeFromNonArray(p2, ctxt));
        }
        if (!this._vanillaProcessing) {
            return this.finishBuild(ctxt, this._deserializeNonVanilla(p2, ctxt));
        }
        Object builder = this._valueInstantiator.createUsingDefault(ctxt);
        SettableBeanProperty[] props = this._orderedProperties;
        int i2 = 0;
        int propCount = props.length;
        while (true) {
            if (p2.nextToken() == JsonToken.END_ARRAY) {
                return this.finishBuild(ctxt, builder);
            }
            if (i2 == propCount) break;
            SettableBeanProperty prop = props[i2];
            if (prop != null) {
                try {
                    builder = prop.deserializeSetAndReturn(p2, ctxt, builder);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, builder, prop.getName(), ctxt);
                }
            } else {
                p2.skipChildren();
            }
            ++i2;
        }
        if (!this._ignoreAllUnknown && ctxt.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            ctxt.reportInputMismatch(this.handledType(), "Unexpected JSON values; expected at most %d properties (in JSON Array)", propCount);
        }
        while (p2.nextToken() != JsonToken.END_ARRAY) {
            p2.skipChildren();
        }
        return this.finishBuild(ctxt, builder);
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt, Object value) throws IOException {
        return this._delegate.deserialize(p2, ctxt, value);
    }

    @Override
    public Object deserializeFromObject(JsonParser p2, DeserializationContext ctxt) throws IOException {
        return this._deserializeFromNonArray(p2, ctxt);
    }

    protected Object _deserializeNonVanilla(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._nonStandardCreation) {
            return this.deserializeFromObjectUsingNonDefault(p2, ctxt);
        }
        Object builder = this._valueInstantiator.createUsingDefault(ctxt);
        if (this._injectables != null) {
            this.injectValues(ctxt, builder);
        }
        Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        SettableBeanProperty[] props = this._orderedProperties;
        int i2 = 0;
        int propCount = props.length;
        while (true) {
            if (p2.nextToken() == JsonToken.END_ARRAY) {
                return builder;
            }
            if (i2 == propCount) break;
            SettableBeanProperty prop = props[i2];
            ++i2;
            if (prop != null && (activeView == null || prop.visibleInView(activeView))) {
                try {
                    prop.deserializeSetAndReturn(p2, ctxt, builder);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, builder, prop.getName(), ctxt);
                }
                continue;
            }
            p2.skipChildren();
        }
        if (!this._ignoreAllUnknown && ctxt.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            ctxt.reportWrongTokenException(this, JsonToken.END_ARRAY, "Unexpected JSON value(s); expected at most %d properties (in JSON Array)", propCount);
        }
        while (p2.nextToken() != JsonToken.END_ARRAY) {
            p2.skipChildren();
        }
        return builder;
    }

    @Override
    protected final Object _deserializeUsingPropertyBased(JsonParser p2, DeserializationContext ctxt) throws IOException {
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, this._objectIdReader);
        SettableBeanProperty[] props = this._orderedProperties;
        int propCount = props.length;
        Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        int i2 = 0;
        Object builder = null;
        while (p2.nextToken() != JsonToken.END_ARRAY) {
            block19: {
                SettableBeanProperty prop;
                SettableBeanProperty settableBeanProperty = prop = i2 < propCount ? props[i2] : null;
                if (prop == null) {
                    p2.skipChildren();
                } else if (activeView != null && !prop.visibleInView(activeView)) {
                    p2.skipChildren();
                } else if (builder != null) {
                    try {
                        builder = prop.deserializeSetAndReturn(p2, ctxt, builder);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, builder, prop.getName(), ctxt);
                    }
                } else {
                    String propName = prop.getName();
                    SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
                    if (creatorProp != null) {
                        if (buffer.assignParameter(creatorProp, creatorProp.deserialize(p2, ctxt))) {
                            try {
                                builder = creator.build(ctxt, buffer);
                            }
                            catch (Exception e11) {
                                this.wrapAndThrow(e11, this._beanType.getRawClass(), propName, ctxt);
                                break block19;
                            }
                            if (builder.getClass() != this._beanType.getRawClass()) {
                                return ctxt.reportBadDefinition(this._beanType, String.format("Cannot support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type %s, actual type %s", this._beanType.getRawClass().getName(), builder.getClass().getName()));
                            }
                        }
                    } else if (!buffer.readIdProperty(propName)) {
                        buffer.bufferProperty(prop, prop.deserialize(p2, ctxt));
                    }
                }
            }
            ++i2;
        }
        if (builder == null) {
            try {
                builder = creator.build(ctxt, buffer);
            }
            catch (Exception e12) {
                return this.wrapInstantiationProblem(e12, ctxt);
            }
        }
        return builder;
    }

    protected Object _deserializeFromNonArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        return ctxt.handleUnexpectedToken(this.handledType(), p2.getCurrentToken(), p2, "Cannot deserialize a POJO (of type %s) from non-Array representation (token: %s): type/property designed to be serialized as JSON Array", new Object[]{this._beanType.getRawClass().getName(), p2.getCurrentToken()});
    }
}

