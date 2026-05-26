/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

public class BuilderBasedDeserializer
extends BeanDeserializerBase {
    private static final long serialVersionUID = 1L;
    protected final AnnotatedMethod _buildMethod;
    protected final JavaType _targetType;

    public BuilderBasedDeserializer(BeanDeserializerBuilder builder, BeanDescription beanDesc, JavaType targetType, BeanPropertyMap properties, Map<String, SettableBeanProperty> backRefs, Set<String> ignorableProps, boolean ignoreAllUnknown, boolean hasViews) {
        super(builder, beanDesc, properties, backRefs, ignorableProps, ignoreAllUnknown, hasViews);
        this._targetType = targetType;
        this._buildMethod = builder.getBuildMethod();
        if (this._objectIdReader != null) {
            throw new IllegalArgumentException("Cannot use Object Id with Builder-based deserialization (type " + beanDesc.getType() + ")");
        }
    }

    @Deprecated
    public BuilderBasedDeserializer(BeanDeserializerBuilder builder, BeanDescription beanDesc, BeanPropertyMap properties, Map<String, SettableBeanProperty> backRefs, Set<String> ignorableProps, boolean ignoreAllUnknown, boolean hasViews) {
        this(builder, beanDesc, beanDesc.getType(), properties, backRefs, ignorableProps, ignoreAllUnknown, hasViews);
    }

    protected BuilderBasedDeserializer(BuilderBasedDeserializer src) {
        this(src, src._ignoreAllUnknown);
    }

    protected BuilderBasedDeserializer(BuilderBasedDeserializer src, boolean ignoreAllUnknown) {
        super((BeanDeserializerBase)src, ignoreAllUnknown);
        this._buildMethod = src._buildMethod;
        this._targetType = src._targetType;
    }

    protected BuilderBasedDeserializer(BuilderBasedDeserializer src, NameTransformer unwrapper) {
        super((BeanDeserializerBase)src, unwrapper);
        this._buildMethod = src._buildMethod;
        this._targetType = src._targetType;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer src, ObjectIdReader oir) {
        super((BeanDeserializerBase)src, oir);
        this._buildMethod = src._buildMethod;
        this._targetType = src._targetType;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer src, Set<String> ignorableProps) {
        super((BeanDeserializerBase)src, ignorableProps);
        this._buildMethod = src._buildMethod;
        this._targetType = src._targetType;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer src, BeanPropertyMap props) {
        super((BeanDeserializerBase)src, props);
        this._buildMethod = src._buildMethod;
        this._targetType = src._targetType;
    }

    @Override
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer unwrapper) {
        return new BuilderBasedDeserializer(this, unwrapper);
    }

    @Override
    public BeanDeserializerBase withObjectIdReader(ObjectIdReader oir) {
        return new BuilderBasedDeserializer(this, oir);
    }

    @Override
    public BeanDeserializerBase withIgnorableProperties(Set<String> ignorableProps) {
        return new BuilderBasedDeserializer(this, ignorableProps);
    }

    @Override
    public BeanDeserializerBase withBeanProperties(BeanPropertyMap props) {
        return new BuilderBasedDeserializer(this, props);
    }

    @Override
    protected BeanDeserializerBase asArrayDeserializer() {
        SettableBeanProperty[] props = this._beanProperties.getPropertiesInInsertionOrder();
        return new BeanAsArrayBuilderDeserializer(this, this._targetType, props, this._buildMethod);
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.FALSE;
    }

    protected final Object finishBuild(DeserializationContext ctxt, Object builder) throws IOException {
        if (null == this._buildMethod) {
            return builder;
        }
        try {
            return this._buildMethod.getMember().invoke(builder, (Object[])null);
        }
        catch (Exception e10) {
            return this.wrapInstantiationProblem(e10, ctxt);
        }
    }

    @Override
    public final Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.isExpectedStartObjectToken()) {
            JsonToken t2 = p2.nextToken();
            if (this._vanillaProcessing) {
                return this.finishBuild(ctxt, this.vanillaDeserialize(p2, ctxt, t2));
            }
            Object builder = this.deserializeFromObject(p2, ctxt);
            return this.finishBuild(ctxt, builder);
        }
        switch (p2.getCurrentTokenId()) {
            case 6: {
                return this.finishBuild(ctxt, this.deserializeFromString(p2, ctxt));
            }
            case 7: {
                return this.finishBuild(ctxt, this.deserializeFromNumber(p2, ctxt));
            }
            case 8: {
                return this.finishBuild(ctxt, this.deserializeFromDouble(p2, ctxt));
            }
            case 12: {
                return p2.getEmbeddedObject();
            }
            case 9: 
            case 10: {
                return this.finishBuild(ctxt, this.deserializeFromBoolean(p2, ctxt));
            }
            case 3: {
                return this.finishBuild(ctxt, this.deserializeFromArray(p2, ctxt));
            }
            case 2: 
            case 5: {
                return this.finishBuild(ctxt, this.deserializeFromObject(p2, ctxt));
            }
        }
        return ctxt.handleUnexpectedToken(this.handledType(), p2);
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt, Object value) throws IOException {
        Class<?> instRawType;
        JavaType valueType = this._targetType;
        Class<?> builderRawType = this.handledType();
        if (builderRawType.isAssignableFrom(instRawType = value.getClass())) {
            return ctxt.reportBadDefinition(valueType, String.format("Deserialization of %s by passing existing Builder (%s) instance not supported", valueType, builderRawType.getName()));
        }
        return ctxt.reportBadDefinition(valueType, String.format("Deserialization of %s by passing existing instance (of %s) not supported", valueType, instRawType.getName()));
    }

    private final Object vanillaDeserialize(JsonParser p2, DeserializationContext ctxt, JsonToken t2) throws IOException {
        Object bean = this._valueInstantiator.createUsingDefault(ctxt);
        while (p2.getCurrentToken() != JsonToken.END_OBJECT) {
            String propName = p2.getCurrentName();
            p2.nextToken();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            if (prop != null) {
                try {
                    bean = prop.deserializeSetAndReturn(p2, ctxt, bean);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, bean, propName, ctxt);
                }
            } else {
                this.handleUnknownVanilla(p2, ctxt, bean, propName);
            }
            p2.nextToken();
        }
        return bean;
    }

    @Override
    public Object deserializeFromObject(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Class<?> view;
        if (this._nonStandardCreation) {
            if (this._unwrappedPropertyHandler != null) {
                return this.deserializeWithUnwrapped(p2, ctxt);
            }
            if (this._externalTypeIdHandler != null) {
                return this.deserializeWithExternalTypeId(p2, ctxt);
            }
            return this.deserializeFromObjectUsingNonDefault(p2, ctxt);
        }
        Object bean = this._valueInstantiator.createUsingDefault(ctxt);
        if (this._injectables != null) {
            this.injectValues(ctxt, bean);
        }
        if (this._needViewProcesing && (view = ctxt.getActiveView()) != null) {
            return this.deserializeWithView(p2, ctxt, bean, view);
        }
        while (p2.getCurrentToken() != JsonToken.END_OBJECT) {
            String propName = p2.getCurrentName();
            p2.nextToken();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            if (prop != null) {
                try {
                    bean = prop.deserializeSetAndReturn(p2, ctxt, bean);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, bean, propName, ctxt);
                }
            } else {
                this.handleUnknownVanilla(p2, ctxt, bean, propName);
            }
            p2.nextToken();
        }
        return bean;
    }

    @Override
    protected final Object _deserializeUsingPropertyBased(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object builder;
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, this._objectIdReader);
        Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        TokenBuffer unknown = null;
        JsonToken t2 = p2.getCurrentToken();
        while (t2 == JsonToken.FIELD_NAME) {
            block22: {
                String propName = p2.getCurrentName();
                p2.nextToken();
                SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
                if (creatorProp != null) {
                    if (activeView != null && !creatorProp.visibleInView(activeView)) {
                        p2.skipChildren();
                    } else if (buffer.assignParameter(creatorProp, creatorProp.deserialize(p2, ctxt))) {
                        Object builder2;
                        p2.nextToken();
                        try {
                            builder2 = creator.build(ctxt, buffer);
                        }
                        catch (Exception e10) {
                            this.wrapAndThrow(e10, this._beanType.getRawClass(), propName, ctxt);
                            break block22;
                        }
                        if (builder2.getClass() != this._beanType.getRawClass()) {
                            return this.handlePolymorphic(p2, ctxt, builder2, unknown);
                        }
                        if (unknown != null) {
                            builder2 = this.handleUnknownProperties(ctxt, builder2, unknown);
                        }
                        return this._deserialize(p2, ctxt, builder2);
                    }
                } else if (!buffer.readIdProperty(propName)) {
                    SettableBeanProperty prop = this._beanProperties.find(propName);
                    if (prop != null) {
                        buffer.bufferProperty(prop, prop.deserialize(p2, ctxt));
                    } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                        this.handleIgnoredProperty(p2, ctxt, this.handledType(), propName);
                    } else if (this._anySetter != null) {
                        buffer.bufferAnyProperty(this._anySetter, propName, this._anySetter.deserialize(p2, ctxt));
                    } else {
                        if (unknown == null) {
                            unknown = new TokenBuffer(p2, ctxt);
                        }
                        unknown.writeFieldName(propName);
                        unknown.copyCurrentStructure(p2);
                    }
                }
            }
            t2 = p2.nextToken();
        }
        try {
            builder = creator.build(ctxt, buffer);
        }
        catch (Exception e11) {
            builder = this.wrapInstantiationProblem(e11, ctxt);
        }
        if (unknown != null) {
            if (builder.getClass() != this._beanType.getRawClass()) {
                return this.handlePolymorphic(null, ctxt, builder, unknown);
            }
            return this.handleUnknownProperties(ctxt, builder, unknown);
        }
        return builder;
    }

    protected final Object _deserialize(JsonParser p2, DeserializationContext ctxt, Object builder) throws IOException {
        Class<?> view;
        if (this._injectables != null) {
            this.injectValues(ctxt, builder);
        }
        if (this._unwrappedPropertyHandler != null) {
            if (p2.hasToken(JsonToken.START_OBJECT)) {
                p2.nextToken();
            }
            TokenBuffer tokens = new TokenBuffer(p2, ctxt);
            tokens.writeStartObject();
            return this.deserializeWithUnwrapped(p2, ctxt, builder, tokens);
        }
        if (this._externalTypeIdHandler != null) {
            return this.deserializeWithExternalTypeId(p2, ctxt, builder);
        }
        if (this._needViewProcesing && (view = ctxt.getActiveView()) != null) {
            return this.deserializeWithView(p2, ctxt, builder, view);
        }
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.START_OBJECT) {
            t2 = p2.nextToken();
        }
        while (t2 == JsonToken.FIELD_NAME) {
            String propName = p2.getCurrentName();
            p2.nextToken();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            if (prop != null) {
                try {
                    builder = prop.deserializeSetAndReturn(p2, ctxt, builder);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, builder, propName, ctxt);
                }
            } else {
                this.handleUnknownVanilla(p2, ctxt, this.handledType(), propName);
            }
            t2 = p2.nextToken();
        }
        return builder;
    }

    protected final Object deserializeWithView(JsonParser p2, DeserializationContext ctxt, Object bean, Class<?> activeView) throws IOException {
        JsonToken t2 = p2.getCurrentToken();
        while (t2 == JsonToken.FIELD_NAME) {
            String propName = p2.getCurrentName();
            p2.nextToken();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            if (prop != null) {
                if (!prop.visibleInView(activeView)) {
                    p2.skipChildren();
                } else {
                    try {
                        bean = prop.deserializeSetAndReturn(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, propName, ctxt);
                    }
                }
            } else {
                this.handleUnknownVanilla(p2, ctxt, bean, propName);
            }
            t2 = p2.nextToken();
        }
        return bean;
    }

    protected Object deserializeWithUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Class<?> activeView;
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p2, ctxt));
        }
        if (this._propertyBasedCreator != null) {
            return this.deserializeUsingPropertyBasedWithUnwrapped(p2, ctxt);
        }
        TokenBuffer tokens = new TokenBuffer(p2, ctxt);
        tokens.writeStartObject();
        Object bean = this._valueInstantiator.createUsingDefault(ctxt);
        if (this._injectables != null) {
            this.injectValues(ctxt, bean);
        }
        Class<?> clazz = activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        while (p2.getCurrentToken() != JsonToken.END_OBJECT) {
            String propName = p2.getCurrentName();
            p2.nextToken();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            if (prop != null) {
                if (activeView != null && !prop.visibleInView(activeView)) {
                    p2.skipChildren();
                } else {
                    try {
                        bean = prop.deserializeSetAndReturn(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, propName, ctxt);
                    }
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                this.handleIgnoredProperty(p2, ctxt, bean, propName);
            } else {
                tokens.writeFieldName(propName);
                tokens.copyCurrentStructure(p2);
                if (this._anySetter != null) {
                    try {
                        this._anySetter.deserializeAndSet(p2, ctxt, bean, propName);
                    }
                    catch (Exception e11) {
                        this.wrapAndThrow(e11, bean, propName, ctxt);
                    }
                }
            }
            p2.nextToken();
        }
        tokens.writeEndObject();
        return this._unwrappedPropertyHandler.processUnwrapped(p2, ctxt, bean, tokens);
    }

    protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, this._objectIdReader);
        TokenBuffer tokens = new TokenBuffer(p2, ctxt);
        tokens.writeStartObject();
        Object builder = null;
        JsonToken t2 = p2.getCurrentToken();
        while (t2 == JsonToken.FIELD_NAME) {
            block16: {
                String propName = p2.getCurrentName();
                p2.nextToken();
                SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
                if (creatorProp != null) {
                    if (buffer.assignParameter(creatorProp, creatorProp.deserialize(p2, ctxt))) {
                        t2 = p2.nextToken();
                        try {
                            builder = creator.build(ctxt, buffer);
                        }
                        catch (Exception e10) {
                            this.wrapAndThrow(e10, this._beanType.getRawClass(), propName, ctxt);
                            break block16;
                        }
                        if (builder.getClass() != this._beanType.getRawClass()) {
                            return this.handlePolymorphic(p2, ctxt, builder, tokens);
                        }
                        return this.deserializeWithUnwrapped(p2, ctxt, builder, tokens);
                    }
                } else if (!buffer.readIdProperty(propName)) {
                    SettableBeanProperty prop = this._beanProperties.find(propName);
                    if (prop != null) {
                        buffer.bufferProperty(prop, prop.deserialize(p2, ctxt));
                    } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                        this.handleIgnoredProperty(p2, ctxt, this.handledType(), propName);
                    } else {
                        tokens.writeFieldName(propName);
                        tokens.copyCurrentStructure(p2);
                        if (this._anySetter != null) {
                            buffer.bufferAnyProperty(this._anySetter, propName, this._anySetter.deserialize(p2, ctxt));
                        }
                    }
                }
            }
            t2 = p2.nextToken();
        }
        if (builder == null) {
            try {
                builder = creator.build(ctxt, buffer);
            }
            catch (Exception e11) {
                return this.wrapInstantiationProblem(e11, ctxt);
            }
        }
        return this._unwrappedPropertyHandler.processUnwrapped(p2, ctxt, builder, tokens);
    }

    protected Object deserializeWithUnwrapped(JsonParser p2, DeserializationContext ctxt, Object builder, TokenBuffer tokens) throws IOException {
        Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        JsonToken t2 = p2.getCurrentToken();
        while (t2 == JsonToken.FIELD_NAME) {
            String propName = p2.getCurrentName();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            p2.nextToken();
            if (prop != null) {
                if (activeView != null && !prop.visibleInView(activeView)) {
                    p2.skipChildren();
                } else {
                    try {
                        builder = prop.deserializeSetAndReturn(p2, ctxt, builder);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, builder, propName, ctxt);
                    }
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                this.handleIgnoredProperty(p2, ctxt, builder, propName);
            } else {
                tokens.writeFieldName(propName);
                tokens.copyCurrentStructure(p2);
                if (this._anySetter != null) {
                    this._anySetter.deserializeAndSet(p2, ctxt, builder, propName);
                }
            }
            t2 = p2.nextToken();
        }
        tokens.writeEndObject();
        return this._unwrappedPropertyHandler.processUnwrapped(p2, ctxt, builder, tokens);
    }

    protected Object deserializeWithExternalTypeId(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._propertyBasedCreator != null) {
            return this.deserializeUsingPropertyBasedWithExternalTypeId(p2, ctxt);
        }
        return this.deserializeWithExternalTypeId(p2, ctxt, this._valueInstantiator.createUsingDefault(ctxt));
    }

    protected Object deserializeWithExternalTypeId(JsonParser p2, DeserializationContext ctxt, Object bean) throws IOException {
        Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        ExternalTypeHandler ext = this._externalTypeIdHandler.start();
        JsonToken t2 = p2.getCurrentToken();
        while (t2 == JsonToken.FIELD_NAME) {
            String propName = p2.getCurrentName();
            t2 = p2.nextToken();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            if (prop != null) {
                if (t2.isScalarValue()) {
                    ext.handleTypePropertyValue(p2, ctxt, propName, bean);
                }
                if (activeView != null && !prop.visibleInView(activeView)) {
                    p2.skipChildren();
                } else {
                    try {
                        bean = prop.deserializeSetAndReturn(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, propName, ctxt);
                    }
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                this.handleIgnoredProperty(p2, ctxt, bean, propName);
            } else if (!ext.handlePropertyValue(p2, ctxt, propName, bean)) {
                if (this._anySetter != null) {
                    try {
                        this._anySetter.deserializeAndSet(p2, ctxt, bean, propName);
                    }
                    catch (Exception e11) {
                        this.wrapAndThrow(e11, bean, propName, ctxt);
                    }
                } else {
                    this.handleUnknownProperty(p2, ctxt, bean, propName);
                }
            }
            t2 = p2.nextToken();
        }
        return ext.complete(p2, ctxt, bean);
    }

    protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JavaType t2 = this._targetType;
        return ctxt.reportBadDefinition(t2, String.format("Deserialization (of %s) with Builder, External type id, @JsonCreator not yet implemented", t2));
    }
}

