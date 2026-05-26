/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

public class BeanDeserializer
extends BeanDeserializerBase
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected transient Exception _nullFromCreator;
    private volatile transient NameTransformer _currentlyTransforming;

    public BeanDeserializer(BeanDeserializerBuilder builder, BeanDescription beanDesc, BeanPropertyMap properties, Map<String, SettableBeanProperty> backRefs, HashSet<String> ignorableProps, boolean ignoreAllUnknown, boolean hasViews) {
        super(builder, beanDesc, properties, backRefs, ignorableProps, ignoreAllUnknown, hasViews);
    }

    protected BeanDeserializer(BeanDeserializerBase src) {
        super(src, src._ignoreAllUnknown);
    }

    protected BeanDeserializer(BeanDeserializerBase src, boolean ignoreAllUnknown) {
        super(src, ignoreAllUnknown);
    }

    protected BeanDeserializer(BeanDeserializerBase src, NameTransformer unwrapper) {
        super(src, unwrapper);
    }

    public BeanDeserializer(BeanDeserializerBase src, ObjectIdReader oir) {
        super(src, oir);
    }

    public BeanDeserializer(BeanDeserializerBase src, Set<String> ignorableProps) {
        super(src, ignorableProps);
    }

    public BeanDeserializer(BeanDeserializerBase src, BeanPropertyMap props) {
        super(src, props);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer transformer) {
        if (this.getClass() != BeanDeserializer.class) {
            return this;
        }
        if (this._currentlyTransforming == transformer) {
            return this;
        }
        this._currentlyTransforming = transformer;
        try {
            BeanDeserializer beanDeserializer = new BeanDeserializer((BeanDeserializerBase)this, transformer);
            return beanDeserializer;
        }
        finally {
            this._currentlyTransforming = null;
        }
    }

    @Override
    public BeanDeserializer withObjectIdReader(ObjectIdReader oir) {
        return new BeanDeserializer((BeanDeserializerBase)this, oir);
    }

    @Override
    public BeanDeserializer withIgnorableProperties(Set<String> ignorableProps) {
        return new BeanDeserializer((BeanDeserializerBase)this, ignorableProps);
    }

    @Override
    public BeanDeserializerBase withBeanProperties(BeanPropertyMap props) {
        return new BeanDeserializer((BeanDeserializerBase)this, props);
    }

    @Override
    protected BeanDeserializerBase asArrayDeserializer() {
        SettableBeanProperty[] props = this._beanProperties.getPropertiesInInsertionOrder();
        return new BeanAsArrayDeserializer((BeanDeserializerBase)this, props);
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.isExpectedStartObjectToken()) {
            if (this._vanillaProcessing) {
                return this.vanillaDeserialize(p2, ctxt, p2.nextToken());
            }
            p2.nextToken();
            if (this._objectIdReader != null) {
                return this.deserializeWithObjectId(p2, ctxt);
            }
            return this.deserializeFromObject(p2, ctxt);
        }
        return this._deserializeOther(p2, ctxt, p2.getCurrentToken());
    }

    protected final Object _deserializeOther(JsonParser p2, DeserializationContext ctxt, JsonToken t2) throws IOException {
        switch (t2) {
            case VALUE_STRING: {
                return this.deserializeFromString(p2, ctxt);
            }
            case VALUE_NUMBER_INT: {
                return this.deserializeFromNumber(p2, ctxt);
            }
            case VALUE_NUMBER_FLOAT: {
                return this.deserializeFromDouble(p2, ctxt);
            }
            case VALUE_EMBEDDED_OBJECT: {
                return this.deserializeFromEmbedded(p2, ctxt);
            }
            case VALUE_TRUE: 
            case VALUE_FALSE: {
                return this.deserializeFromBoolean(p2, ctxt);
            }
            case VALUE_NULL: {
                return this.deserializeFromNull(p2, ctxt);
            }
            case START_ARRAY: {
                return this.deserializeFromArray(p2, ctxt);
            }
            case FIELD_NAME: 
            case END_OBJECT: {
                if (this._vanillaProcessing) {
                    return this.vanillaDeserialize(p2, ctxt, t2);
                }
                if (this._objectIdReader != null) {
                    return this.deserializeWithObjectId(p2, ctxt);
                }
                return this.deserializeFromObject(p2, ctxt);
            }
        }
        return ctxt.handleUnexpectedToken(this.handledType(), p2);
    }

    @Deprecated
    protected Object _missingToken(JsonParser p2, DeserializationContext ctxt) throws IOException {
        throw ctxt.endOfInputException(this.handledType());
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt, Object bean) throws IOException {
        Class<?> view;
        String propName;
        p2.setCurrentValue(bean);
        if (this._injectables != null) {
            this.injectValues(ctxt, bean);
        }
        if (this._unwrappedPropertyHandler != null) {
            return this.deserializeWithUnwrapped(p2, ctxt, bean);
        }
        if (this._externalTypeIdHandler != null) {
            return this.deserializeWithExternalTypeId(p2, ctxt, bean);
        }
        if (p2.isExpectedStartObjectToken()) {
            propName = p2.nextFieldName();
            if (propName == null) {
                return bean;
            }
        } else if (p2.hasTokenId(5)) {
            propName = p2.getCurrentName();
        } else {
            return bean;
        }
        if (this._needViewProcesing && (view = ctxt.getActiveView()) != null) {
            return this.deserializeWithView(p2, ctxt, bean, view);
        }
        do {
            p2.nextToken();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            if (prop != null) {
                try {
                    prop.deserializeAndSet(p2, ctxt, bean);
                }
                catch (Exception e10) {
                    this.wrapAndThrow(e10, bean, propName, ctxt);
                }
                continue;
            }
            this.handleUnknownVanilla(p2, ctxt, bean, propName);
        } while ((propName = p2.nextFieldName()) != null);
        return bean;
    }

    private final Object vanillaDeserialize(JsonParser p2, DeserializationContext ctxt, JsonToken t2) throws IOException {
        Object bean = this._valueInstantiator.createUsingDefault(ctxt);
        p2.setCurrentValue(bean);
        if (p2.hasTokenId(5)) {
            String propName = p2.getCurrentName();
            do {
                p2.nextToken();
                SettableBeanProperty prop = this._beanProperties.find(propName);
                if (prop != null) {
                    try {
                        prop.deserializeAndSet(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, propName, ctxt);
                    }
                    continue;
                }
                this.handleUnknownVanilla(p2, ctxt, bean, propName);
            } while ((propName = p2.nextFieldName()) != null);
        }
        return bean;
    }

    @Override
    public Object deserializeFromObject(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Class<?> view;
        Object id;
        if (this._objectIdReader != null && this._objectIdReader.maySerializeAsObject() && p2.hasTokenId(5) && this._objectIdReader.isValidReferencePropertyName(p2.getCurrentName(), p2)) {
            return this.deserializeFromObjectId(p2, ctxt);
        }
        if (this._nonStandardCreation) {
            if (this._unwrappedPropertyHandler != null) {
                return this.deserializeWithUnwrapped(p2, ctxt);
            }
            if (this._externalTypeIdHandler != null) {
                return this.deserializeWithExternalTypeId(p2, ctxt);
            }
            Object bean = this.deserializeFromObjectUsingNonDefault(p2, ctxt);
            if (this._injectables != null) {
                this.injectValues(ctxt, bean);
            }
            return bean;
        }
        Object bean = this._valueInstantiator.createUsingDefault(ctxt);
        p2.setCurrentValue(bean);
        if (p2.canReadObjectId() && (id = p2.getObjectId()) != null) {
            this._handleTypedObjectId(p2, ctxt, bean, id);
        }
        if (this._injectables != null) {
            this.injectValues(ctxt, bean);
        }
        if (this._needViewProcesing && (view = ctxt.getActiveView()) != null) {
            return this.deserializeWithView(p2, ctxt, bean, view);
        }
        if (p2.hasTokenId(5)) {
            String propName = p2.getCurrentName();
            do {
                p2.nextToken();
                SettableBeanProperty prop = this._beanProperties.find(propName);
                if (prop != null) {
                    try {
                        prop.deserializeAndSet(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, propName, ctxt);
                    }
                    continue;
                }
                this.handleUnknownVanilla(p2, ctxt, bean, propName);
            } while ((propName = p2.nextFieldName()) != null);
        }
        return bean;
    }

    @Override
    protected Object _deserializeUsingPropertyBased(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object bean;
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, this._objectIdReader);
        TokenBuffer unknown = null;
        Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        JsonToken t2 = p2.getCurrentToken();
        ArrayList<BeanReferring> referrings = null;
        while (t2 == JsonToken.FIELD_NAME) {
            String propName = p2.getCurrentName();
            p2.nextToken();
            if (!buffer.readIdProperty(propName)) {
                SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
                if (creatorProp != null) {
                    if (activeView != null && !creatorProp.visibleInView(activeView)) {
                        p2.skipChildren();
                    } else {
                        Object value = this._deserializeWithErrorWrapping(p2, ctxt, creatorProp);
                        if (buffer.assignParameter(creatorProp, value)) {
                            Object bean2;
                            p2.nextToken();
                            try {
                                bean2 = creator.build(ctxt, buffer);
                            }
                            catch (Exception e10) {
                                bean2 = this.wrapInstantiationProblem(e10, ctxt);
                            }
                            if (bean2 == null) {
                                return ctxt.handleInstantiationProblem(this.handledType(), null, this._creatorReturnedNullException());
                            }
                            p2.setCurrentValue(bean2);
                            if (bean2.getClass() != this._beanType.getRawClass()) {
                                return this.handlePolymorphic(p2, ctxt, bean2, unknown);
                            }
                            if (unknown != null) {
                                bean2 = this.handleUnknownProperties(ctxt, bean2, unknown);
                            }
                            return this.deserialize(p2, ctxt, bean2);
                        }
                    }
                } else {
                    SettableBeanProperty prop = this._beanProperties.find(propName);
                    if (prop != null) {
                        try {
                            buffer.bufferProperty(prop, this._deserializeWithErrorWrapping(p2, ctxt, prop));
                        }
                        catch (UnresolvedForwardReference reference) {
                            BeanReferring referring = this.handleUnresolvedReference(ctxt, prop, buffer, reference);
                            if (referrings == null) {
                                referrings = new ArrayList<BeanReferring>();
                            }
                            referrings.add(referring);
                        }
                    } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                        this.handleIgnoredProperty(p2, ctxt, this.handledType(), propName);
                    } else if (this._anySetter != null) {
                        try {
                            buffer.bufferAnyProperty(this._anySetter, propName, this._anySetter.deserialize(p2, ctxt));
                        }
                        catch (Exception e11) {
                            this.wrapAndThrow(e11, this._beanType.getRawClass(), propName, ctxt);
                        }
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
            bean = creator.build(ctxt, buffer);
        }
        catch (Exception e12) {
            this.wrapInstantiationProblem(e12, ctxt);
            bean = null;
        }
        if (referrings != null) {
            for (BeanReferring referring : referrings) {
                referring.setBean(bean);
            }
        }
        if (unknown != null) {
            if (bean.getClass() != this._beanType.getRawClass()) {
                return this.handlePolymorphic(null, ctxt, bean, unknown);
            }
            return this.handleUnknownProperties(ctxt, bean, unknown);
        }
        return bean;
    }

    private BeanReferring handleUnresolvedReference(DeserializationContext ctxt, SettableBeanProperty prop, PropertyValueBuffer buffer, UnresolvedForwardReference reference) throws JsonMappingException {
        BeanReferring referring = new BeanReferring(ctxt, reference, prop.getType(), buffer, prop);
        reference.getRoid().appendReferring(referring);
        return referring;
    }

    protected final Object _deserializeWithErrorWrapping(JsonParser p2, DeserializationContext ctxt, SettableBeanProperty prop) throws IOException {
        try {
            return prop.deserialize(p2, ctxt);
        }
        catch (Exception e10) {
            this.wrapAndThrow(e10, this._beanType.getRawClass(), prop.getName(), ctxt);
            return null;
        }
    }

    protected Object deserializeFromNull(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.requiresCustomCodec()) {
            TokenBuffer tb = new TokenBuffer(p2, ctxt);
            tb.writeEndObject();
            JsonParser p22 = tb.asParser(p2);
            p22.nextToken();
            Object ob = this._vanillaProcessing ? this.vanillaDeserialize(p22, ctxt, JsonToken.END_OBJECT) : this.deserializeFromObject(p22, ctxt);
            p22.close();
            return ob;
        }
        return ctxt.handleUnexpectedToken(this.handledType(), p2);
    }

    protected final Object deserializeWithView(JsonParser p2, DeserializationContext ctxt, Object bean, Class<?> activeView) throws IOException {
        if (p2.hasTokenId(5)) {
            String propName = p2.getCurrentName();
            do {
                p2.nextToken();
                SettableBeanProperty prop = this._beanProperties.find(propName);
                if (prop != null) {
                    if (!prop.visibleInView(activeView)) {
                        p2.skipChildren();
                        continue;
                    }
                    try {
                        prop.deserializeAndSet(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, propName, ctxt);
                    }
                    continue;
                }
                this.handleUnknownVanilla(p2, ctxt, bean, propName);
            } while ((propName = p2.nextFieldName()) != null);
        }
        return bean;
    }

    protected Object deserializeWithUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
        String propName;
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p2, ctxt));
        }
        if (this._propertyBasedCreator != null) {
            return this.deserializeUsingPropertyBasedWithUnwrapped(p2, ctxt);
        }
        TokenBuffer tokens = new TokenBuffer(p2, ctxt);
        tokens.writeStartObject();
        Object bean = this._valueInstantiator.createUsingDefault(ctxt);
        p2.setCurrentValue(bean);
        if (this._injectables != null) {
            this.injectValues(ctxt, bean);
        }
        Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        String string = propName = p2.hasTokenId(5) ? p2.getCurrentName() : null;
        while (propName != null) {
            p2.nextToken();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            if (prop != null) {
                if (activeView != null && !prop.visibleInView(activeView)) {
                    p2.skipChildren();
                } else {
                    try {
                        prop.deserializeAndSet(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, propName, ctxt);
                    }
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                this.handleIgnoredProperty(p2, ctxt, bean, propName);
            } else if (this._anySetter == null) {
                tokens.writeFieldName(propName);
                tokens.copyCurrentStructure(p2);
            } else {
                TokenBuffer b22 = TokenBuffer.asCopyOfValue(p2);
                tokens.writeFieldName(propName);
                tokens.append(b22);
                try {
                    this._anySetter.deserializeAndSet(b22.asParserOnFirstToken(), ctxt, bean, propName);
                }
                catch (Exception e11) {
                    this.wrapAndThrow(e11, bean, propName, ctxt);
                }
            }
            propName = p2.nextFieldName();
        }
        tokens.writeEndObject();
        this._unwrappedPropertyHandler.processUnwrapped(p2, ctxt, bean, tokens);
        return bean;
    }

    protected Object deserializeWithUnwrapped(JsonParser p2, DeserializationContext ctxt, Object bean) throws IOException {
        Class<?> activeView;
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.START_OBJECT) {
            t2 = p2.nextToken();
        }
        TokenBuffer tokens = new TokenBuffer(p2, ctxt);
        tokens.writeStartObject();
        Class<?> clazz = activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
        while (t2 == JsonToken.FIELD_NAME) {
            String propName = p2.getCurrentName();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            p2.nextToken();
            if (prop != null) {
                if (activeView != null && !prop.visibleInView(activeView)) {
                    p2.skipChildren();
                } else {
                    try {
                        prop.deserializeAndSet(p2, ctxt, bean);
                    }
                    catch (Exception e10) {
                        this.wrapAndThrow(e10, bean, propName, ctxt);
                    }
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                this.handleIgnoredProperty(p2, ctxt, bean, propName);
            } else if (this._anySetter == null) {
                tokens.writeFieldName(propName);
                tokens.copyCurrentStructure(p2);
            } else {
                TokenBuffer b22 = TokenBuffer.asCopyOfValue(p2);
                tokens.writeFieldName(propName);
                tokens.append(b22);
                try {
                    this._anySetter.deserializeAndSet(b22.asParserOnFirstToken(), ctxt, bean, propName);
                }
                catch (Exception e11) {
                    this.wrapAndThrow(e11, bean, propName, ctxt);
                }
            }
            t2 = p2.nextToken();
        }
        tokens.writeEndObject();
        this._unwrappedPropertyHandler.processUnwrapped(p2, ctxt, bean, tokens);
        return bean;
    }

    protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object bean;
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, this._objectIdReader);
        TokenBuffer tokens = new TokenBuffer(p2, ctxt);
        tokens.writeStartObject();
        JsonToken t2 = p2.getCurrentToken();
        while (t2 == JsonToken.FIELD_NAME) {
            String propName = p2.getCurrentName();
            p2.nextToken();
            SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
            if (creatorProp != null) {
                if (buffer.assignParameter(creatorProp, this._deserializeWithErrorWrapping(p2, ctxt, creatorProp))) {
                    Object bean2;
                    t2 = p2.nextToken();
                    try {
                        bean2 = creator.build(ctxt, buffer);
                    }
                    catch (Exception e10) {
                        bean2 = this.wrapInstantiationProblem(e10, ctxt);
                    }
                    p2.setCurrentValue(bean2);
                    while (t2 == JsonToken.FIELD_NAME) {
                        p2.nextToken();
                        tokens.copyCurrentStructure(p2);
                        t2 = p2.nextToken();
                    }
                    tokens.writeEndObject();
                    if (bean2.getClass() != this._beanType.getRawClass()) {
                        ctxt.reportInputMismatch(creatorProp, "Cannot create polymorphic instances with unwrapped values", new Object[0]);
                        return null;
                    }
                    return this._unwrappedPropertyHandler.processUnwrapped(p2, ctxt, bean2, tokens);
                }
            } else if (!buffer.readIdProperty(propName)) {
                SettableBeanProperty prop = this._beanProperties.find(propName);
                if (prop != null) {
                    buffer.bufferProperty(prop, this._deserializeWithErrorWrapping(p2, ctxt, prop));
                } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                    this.handleIgnoredProperty(p2, ctxt, this.handledType(), propName);
                } else if (this._anySetter == null) {
                    tokens.writeFieldName(propName);
                    tokens.copyCurrentStructure(p2);
                } else {
                    TokenBuffer b22 = TokenBuffer.asCopyOfValue(p2);
                    tokens.writeFieldName(propName);
                    tokens.append(b22);
                    try {
                        buffer.bufferAnyProperty(this._anySetter, propName, this._anySetter.deserialize(b22.asParserOnFirstToken(), ctxt));
                    }
                    catch (Exception e11) {
                        this.wrapAndThrow(e11, this._beanType.getRawClass(), propName, ctxt);
                    }
                }
            }
            t2 = p2.nextToken();
        }
        try {
            bean = creator.build(ctxt, buffer);
        }
        catch (Exception e12) {
            this.wrapInstantiationProblem(e12, ctxt);
            return null;
        }
        return this._unwrappedPropertyHandler.processUnwrapped(p2, ctxt, bean, tokens);
    }

    protected Object deserializeWithExternalTypeId(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._propertyBasedCreator != null) {
            return this.deserializeUsingPropertyBasedWithExternalTypeId(p2, ctxt);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p2, ctxt));
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
                        prop.deserializeAndSet(p2, ctxt, bean);
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
        ExternalTypeHandler ext = this._externalTypeIdHandler.start();
        PropertyBasedCreator creator = this._propertyBasedCreator;
        PropertyValueBuffer buffer = creator.startBuilding(p2, ctxt, this._objectIdReader);
        TokenBuffer tokens = new TokenBuffer(p2, ctxt);
        tokens.writeStartObject();
        JsonToken t2 = p2.getCurrentToken();
        while (t2 == JsonToken.FIELD_NAME) {
            block17: {
                String propName = p2.getCurrentName();
                p2.nextToken();
                SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
                if (creatorProp != null) {
                    if (!ext.handlePropertyValue(p2, ctxt, propName, null) && buffer.assignParameter(creatorProp, this._deserializeWithErrorWrapping(p2, ctxt, creatorProp))) {
                        Object bean;
                        t2 = p2.nextToken();
                        try {
                            bean = creator.build(ctxt, buffer);
                        }
                        catch (Exception e10) {
                            this.wrapAndThrow(e10, this._beanType.getRawClass(), propName, ctxt);
                            break block17;
                        }
                        while (t2 == JsonToken.FIELD_NAME) {
                            p2.nextToken();
                            tokens.copyCurrentStructure(p2);
                            t2 = p2.nextToken();
                        }
                        if (bean.getClass() != this._beanType.getRawClass()) {
                            return ctxt.reportBadDefinition(this._beanType, String.format("Cannot create polymorphic instances with external type ids (%s -> %s)", this._beanType, bean.getClass()));
                        }
                        return ext.complete(p2, ctxt, bean);
                    }
                } else if (!buffer.readIdProperty(propName)) {
                    SettableBeanProperty prop = this._beanProperties.find(propName);
                    if (prop != null) {
                        buffer.bufferProperty(prop, prop.deserialize(p2, ctxt));
                    } else if (!ext.handlePropertyValue(p2, ctxt, propName, null)) {
                        if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                            this.handleIgnoredProperty(p2, ctxt, this.handledType(), propName);
                        } else if (this._anySetter != null) {
                            buffer.bufferAnyProperty(this._anySetter, propName, this._anySetter.deserialize(p2, ctxt));
                        }
                    }
                }
            }
            t2 = p2.nextToken();
        }
        try {
            return ext.complete(p2, ctxt, buffer, creator);
        }
        catch (Exception e11) {
            return this.wrapInstantiationProblem(e11, ctxt);
        }
    }

    protected Exception _creatorReturnedNullException() {
        if (this._nullFromCreator == null) {
            this._nullFromCreator = new NullPointerException("JSON Creator returned null");
        }
        return this._nullFromCreator;
    }

    static class BeanReferring
    extends ReadableObjectId.Referring {
        private final DeserializationContext _context;
        private final SettableBeanProperty _prop;
        private Object _bean;

        BeanReferring(DeserializationContext ctxt, UnresolvedForwardReference ref, JavaType valueType, PropertyValueBuffer buffer, SettableBeanProperty prop) {
            super(ref, valueType);
            this._context = ctxt;
            this._prop = prop;
        }

        public void setBean(Object bean) {
            this._bean = bean;
        }

        @Override
        public void handleResolvedForwardReference(Object id, Object value) throws IOException {
            if (this._bean == null) {
                this._context.reportInputMismatch(this._prop, "Cannot resolve ObjectId forward reference using property '%s' (of type %s): Bean not yet resolved", this._prop.getName(), this._prop.getDeclaringClass().getName());
            }
            this._prop.set(this._bean, value);
        }
    }
}

