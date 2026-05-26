/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreProperties;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerators;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdResolver;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.CreatorProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableAnyProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.InnerClassProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.MergingSettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReferenceProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ValueInjector;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.type.ClassKey;
import software.bernie.shadowed.fasterxml.jackson.databind.util.AccessPattern;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Converter;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

public abstract class BeanDeserializerBase
extends StdDeserializer<Object>
implements ContextualDeserializer,
ResolvableDeserializer,
ValueInstantiator.Gettable,
Serializable {
    private static final long serialVersionUID = 1L;
    protected static final PropertyName TEMP_PROPERTY_NAME = new PropertyName("#temporary-name");
    protected final JavaType _beanType;
    protected final JsonFormat.Shape _serializationShape;
    protected final ValueInstantiator _valueInstantiator;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected JsonDeserializer<Object> _arrayDelegateDeserializer;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected boolean _nonStandardCreation;
    protected boolean _vanillaProcessing;
    protected final BeanPropertyMap _beanProperties;
    protected final ValueInjector[] _injectables;
    protected SettableAnyProperty _anySetter;
    protected final Set<String> _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final boolean _needViewProcesing;
    protected final Map<String, SettableBeanProperty> _backRefs;
    protected transient HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;
    protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
    protected ExternalTypeHandler _externalTypeIdHandler;
    protected final ObjectIdReader _objectIdReader;

    protected BeanDeserializerBase(BeanDeserializerBuilder builder, BeanDescription beanDesc, BeanPropertyMap properties, Map<String, SettableBeanProperty> backRefs, Set<String> ignorableProps, boolean ignoreAllUnknown, boolean hasViews) {
        super(beanDesc.getType());
        this._beanType = beanDesc.getType();
        this._valueInstantiator = builder.getValueInstantiator();
        this._beanProperties = properties;
        this._backRefs = backRefs;
        this._ignorableProps = ignorableProps;
        this._ignoreAllUnknown = ignoreAllUnknown;
        this._anySetter = builder.getAnySetter();
        List<ValueInjector> injectables = builder.getInjectables();
        this._injectables = injectables == null || injectables.isEmpty() ? null : injectables.toArray(new ValueInjector[injectables.size()]);
        this._objectIdReader = builder.getObjectIdReader();
        this._nonStandardCreation = this._unwrappedPropertyHandler != null || this._valueInstantiator.canCreateUsingDelegate() || this._valueInstantiator.canCreateUsingArrayDelegate() || this._valueInstantiator.canCreateFromObjectWith() || !this._valueInstantiator.canCreateUsingDefault();
        JsonFormat.Value format = beanDesc.findExpectedFormat(null);
        this._serializationShape = format == null ? null : format.getShape();
        this._needViewProcesing = hasViews;
        this._vanillaProcessing = !this._nonStandardCreation && this._injectables == null && !this._needViewProcesing && this._objectIdReader == null;
    }

    protected BeanDeserializerBase(BeanDeserializerBase src) {
        this(src, src._ignoreAllUnknown);
    }

    protected BeanDeserializerBase(BeanDeserializerBase src, boolean ignoreAllUnknown) {
        super(src._beanType);
        this._beanType = src._beanType;
        this._valueInstantiator = src._valueInstantiator;
        this._delegateDeserializer = src._delegateDeserializer;
        this._propertyBasedCreator = src._propertyBasedCreator;
        this._beanProperties = src._beanProperties;
        this._backRefs = src._backRefs;
        this._ignorableProps = src._ignorableProps;
        this._ignoreAllUnknown = ignoreAllUnknown;
        this._anySetter = src._anySetter;
        this._injectables = src._injectables;
        this._objectIdReader = src._objectIdReader;
        this._nonStandardCreation = src._nonStandardCreation;
        this._unwrappedPropertyHandler = src._unwrappedPropertyHandler;
        this._needViewProcesing = src._needViewProcesing;
        this._serializationShape = src._serializationShape;
        this._vanillaProcessing = src._vanillaProcessing;
    }

    protected BeanDeserializerBase(BeanDeserializerBase src, NameTransformer unwrapper) {
        super(src._beanType);
        this._beanType = src._beanType;
        this._valueInstantiator = src._valueInstantiator;
        this._delegateDeserializer = src._delegateDeserializer;
        this._propertyBasedCreator = src._propertyBasedCreator;
        this._backRefs = src._backRefs;
        this._ignorableProps = src._ignorableProps;
        this._ignoreAllUnknown = unwrapper != null || src._ignoreAllUnknown;
        this._anySetter = src._anySetter;
        this._injectables = src._injectables;
        this._objectIdReader = src._objectIdReader;
        this._nonStandardCreation = src._nonStandardCreation;
        UnwrappedPropertyHandler uph = src._unwrappedPropertyHandler;
        if (unwrapper != null) {
            if (uph != null) {
                uph = uph.renameAll(unwrapper);
            }
            this._beanProperties = src._beanProperties.renameAll(unwrapper);
        } else {
            this._beanProperties = src._beanProperties;
        }
        this._unwrappedPropertyHandler = uph;
        this._needViewProcesing = src._needViewProcesing;
        this._serializationShape = src._serializationShape;
        this._vanillaProcessing = false;
    }

    public BeanDeserializerBase(BeanDeserializerBase src, ObjectIdReader oir) {
        super(src._beanType);
        this._beanType = src._beanType;
        this._valueInstantiator = src._valueInstantiator;
        this._delegateDeserializer = src._delegateDeserializer;
        this._propertyBasedCreator = src._propertyBasedCreator;
        this._backRefs = src._backRefs;
        this._ignorableProps = src._ignorableProps;
        this._ignoreAllUnknown = src._ignoreAllUnknown;
        this._anySetter = src._anySetter;
        this._injectables = src._injectables;
        this._nonStandardCreation = src._nonStandardCreation;
        this._unwrappedPropertyHandler = src._unwrappedPropertyHandler;
        this._needViewProcesing = src._needViewProcesing;
        this._serializationShape = src._serializationShape;
        this._objectIdReader = oir;
        if (oir == null) {
            this._beanProperties = src._beanProperties;
            this._vanillaProcessing = src._vanillaProcessing;
        } else {
            ObjectIdValueProperty idProp = new ObjectIdValueProperty(oir, PropertyMetadata.STD_REQUIRED);
            this._beanProperties = src._beanProperties.withProperty(idProp);
            this._vanillaProcessing = false;
        }
    }

    public BeanDeserializerBase(BeanDeserializerBase src, Set<String> ignorableProps) {
        super(src._beanType);
        this._beanType = src._beanType;
        this._valueInstantiator = src._valueInstantiator;
        this._delegateDeserializer = src._delegateDeserializer;
        this._propertyBasedCreator = src._propertyBasedCreator;
        this._backRefs = src._backRefs;
        this._ignorableProps = ignorableProps;
        this._ignoreAllUnknown = src._ignoreAllUnknown;
        this._anySetter = src._anySetter;
        this._injectables = src._injectables;
        this._nonStandardCreation = src._nonStandardCreation;
        this._unwrappedPropertyHandler = src._unwrappedPropertyHandler;
        this._needViewProcesing = src._needViewProcesing;
        this._serializationShape = src._serializationShape;
        this._vanillaProcessing = src._vanillaProcessing;
        this._objectIdReader = src._objectIdReader;
        this._beanProperties = src._beanProperties.withoutProperties(ignorableProps);
    }

    protected BeanDeserializerBase(BeanDeserializerBase src, BeanPropertyMap beanProps) {
        super(src._beanType);
        this._beanType = src._beanType;
        this._valueInstantiator = src._valueInstantiator;
        this._delegateDeserializer = src._delegateDeserializer;
        this._propertyBasedCreator = src._propertyBasedCreator;
        this._beanProperties = beanProps;
        this._backRefs = src._backRefs;
        this._ignorableProps = src._ignorableProps;
        this._ignoreAllUnknown = src._ignoreAllUnknown;
        this._anySetter = src._anySetter;
        this._injectables = src._injectables;
        this._objectIdReader = src._objectIdReader;
        this._nonStandardCreation = src._nonStandardCreation;
        this._unwrappedPropertyHandler = src._unwrappedPropertyHandler;
        this._needViewProcesing = src._needViewProcesing;
        this._serializationShape = src._serializationShape;
        this._vanillaProcessing = src._vanillaProcessing;
    }

    @Override
    public abstract JsonDeserializer<Object> unwrappingDeserializer(NameTransformer var1);

    public abstract BeanDeserializerBase withObjectIdReader(ObjectIdReader var1);

    public abstract BeanDeserializerBase withIgnorableProperties(Set<String> var1);

    public BeanDeserializerBase withBeanProperties(BeanPropertyMap props) {
        throw new UnsupportedOperationException("Class " + this.getClass().getName() + " does not override `withBeanProperties()`, needs to");
    }

    protected abstract BeanDeserializerBase asArrayDeserializer();

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        JavaType delegateType;
        ExternalTypeHandler.Builder extTypes = null;
        SettableBeanProperty[] creatorProps = this._valueInstantiator.canCreateFromObjectWith() ? this._valueInstantiator.getFromObjectArguments(ctxt.getConfig()) : null;
        UnwrappedPropertyHandler unwrapped = null;
        for (SettableBeanProperty prop : this._beanProperties) {
            if (prop.hasValueDeserializer()) continue;
            JsonDeserializer<Object> deser = this.findConvertingDeserializer(ctxt, prop);
            if (deser == null) {
                deser = ctxt.findNonContextualValueDeserializer(prop.getType());
            }
            SettableBeanProperty newProp = prop.withValueDeserializer(deser);
            this._replaceProperty(this._beanProperties, creatorProps, prop, newProp);
        }
        Iterator<SettableBeanProperty> i$ = this._beanProperties.iterator();
        while (i$.hasNext()) {
            TypeDeserializer typeDeser;
            JsonDeserializer<Object> orig;
            JsonDeserializer<Object> unwrapping;
            NameTransformer xform;
            SettableBeanProperty origProp;
            SettableBeanProperty prop = origProp = i$.next();
            JsonDeserializer<Object> deser = prop.getValueDeserializer();
            deser = ctxt.handlePrimaryContextualization(deser, prop, prop.getType());
            prop = prop.withValueDeserializer(deser);
            if (!((prop = this._resolveManagedReferenceProperty(ctxt, prop)) instanceof ManagedReferenceProperty)) {
                prop = this._resolvedObjectIdProperty(ctxt, prop);
            }
            if ((xform = this._findPropertyUnwrapper(ctxt, prop)) != null && (unwrapping = (orig = prop.getValueDeserializer()).unwrappingDeserializer(xform)) != orig && unwrapping != null) {
                prop = prop.withValueDeserializer(unwrapping);
                if (unwrapped == null) {
                    unwrapped = new UnwrappedPropertyHandler();
                }
                unwrapped.addProperty(prop);
                this._beanProperties.remove(prop);
                continue;
            }
            PropertyMetadata md = prop.getMetadata();
            prop = this._resolveMergeAndNullSettings(ctxt, prop, md);
            if ((prop = this._resolveInnerClassValuedProperty(ctxt, prop)) != origProp) {
                this._replaceProperty(this._beanProperties, creatorProps, origProp, prop);
            }
            if (!prop.hasValueTypeDeserializer() || (typeDeser = prop.getValueTypeDeserializer()).getTypeInclusion() != JsonTypeInfo.As.EXTERNAL_PROPERTY) continue;
            if (extTypes == null) {
                extTypes = ExternalTypeHandler.builder(this._beanType);
            }
            extTypes.addExternal(prop, typeDeser);
            this._beanProperties.remove(prop);
        }
        if (this._anySetter != null && !this._anySetter.hasValueDeserializer()) {
            this._anySetter = this._anySetter.withValueDeserializer(this.findDeserializer(ctxt, this._anySetter.getType(), this._anySetter.getProperty()));
        }
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            delegateType = this._valueInstantiator.getDelegateType(ctxt.getConfig());
            if (delegateType == null) {
                ctxt.reportBadDefinition(this._beanType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", this._beanType, this._valueInstantiator.getClass().getName()));
            }
            this._delegateDeserializer = this._findDelegateDeserializer(ctxt, delegateType, this._valueInstantiator.getDelegateCreator());
        }
        if (this._valueInstantiator.canCreateUsingArrayDelegate()) {
            delegateType = this._valueInstantiator.getArrayDelegateType(ctxt.getConfig());
            if (delegateType == null) {
                ctxt.reportBadDefinition(this._beanType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", this._beanType, this._valueInstantiator.getClass().getName()));
            }
            this._arrayDelegateDeserializer = this._findDelegateDeserializer(ctxt, delegateType, this._valueInstantiator.getArrayDelegateCreator());
        }
        if (creatorProps != null) {
            this._propertyBasedCreator = PropertyBasedCreator.construct(ctxt, this._valueInstantiator, creatorProps, this._beanProperties);
        }
        if (extTypes != null) {
            this._externalTypeIdHandler = extTypes.build(this._beanProperties);
            this._nonStandardCreation = true;
        }
        this._unwrappedPropertyHandler = unwrapped;
        if (unwrapped != null) {
            this._nonStandardCreation = true;
        }
        this._vanillaProcessing = this._vanillaProcessing && !this._nonStandardCreation;
    }

    protected void _replaceProperty(BeanPropertyMap props, SettableBeanProperty[] creatorProps, SettableBeanProperty origProp, SettableBeanProperty newProp) {
        props.replace(newProp);
        if (creatorProps != null) {
            int len = creatorProps.length;
            for (int i2 = 0; i2 < len; ++i2) {
                if (creatorProps[i2] != origProp) continue;
                creatorProps[i2] = newProp;
                return;
            }
        }
    }

    private JsonDeserializer<Object> _findDelegateDeserializer(DeserializationContext ctxt, JavaType delegateType, AnnotatedWithParams delegateCreator) throws JsonMappingException {
        BeanProperty.Std property = new BeanProperty.Std(TEMP_PROPERTY_NAME, delegateType, null, delegateCreator, PropertyMetadata.STD_OPTIONAL);
        TypeDeserializer td = (TypeDeserializer)delegateType.getTypeHandler();
        if (td == null) {
            td = ctxt.getConfig().findTypeDeserializer(delegateType);
        }
        JsonDeserializer<Object> dd2 = this.findDeserializer(ctxt, delegateType, property);
        if (td != null) {
            td = td.forProperty(property);
            return new TypeWrappedDeserializer(td, dd2);
        }
        return dd2;
    }

    protected JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext ctxt, SettableBeanProperty prop) throws JsonMappingException {
        Object convDef;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr != null && (convDef = intr.findDeserializationConverter(prop.getMember())) != null) {
            Converter<Object, Object> conv = ctxt.converterInstance(prop.getMember(), convDef);
            JavaType delegateType = conv.getInputType(ctxt.getTypeFactory());
            JsonDeserializer<Object> deser = ctxt.findNonContextualValueDeserializer(delegateType);
            return new StdDelegatingDeserializer<Object>(conv, delegateType, deser);
        }
        return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Set<String> ignored;
        JsonIgnoreProperties.Value ignorals;
        ObjectIdInfo objectIdInfo;
        AnnotatedMember accessor;
        ObjectIdReader oir = this._objectIdReader;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        AnnotatedMember annotatedMember = accessor = BeanDeserializerBase._neitherNull(property, intr) ? property.getMember() : null;
        if (accessor != null && (objectIdInfo = intr.findObjectIdInfo(accessor)) != null) {
            ObjectIdGenerator idGen;
            JavaType idType;
            SettableBeanProperty idProp;
            objectIdInfo = intr.findObjectReferenceInfo(accessor, objectIdInfo);
            Class<? extends ObjectIdGenerator<?>> implClass = objectIdInfo.getGeneratorType();
            ObjectIdResolver resolver = ctxt.objectIdResolverInstance(accessor, objectIdInfo);
            if (implClass == ObjectIdGenerators.PropertyGenerator.class) {
                PropertyName propName = objectIdInfo.getPropertyName();
                idProp = this.findProperty(propName);
                if (idProp == null) {
                    ctxt.reportBadDefinition(this._beanType, String.format("Invalid Object Id definition for %s: cannot find property with name '%s'", this.handledType().getName(), propName));
                }
                idType = idProp.getType();
                idGen = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
            } else {
                JavaType type = ctxt.constructType(implClass);
                idType = ctxt.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
                idProp = null;
                idGen = ctxt.objectIdGeneratorInstance(accessor, objectIdInfo);
            }
            JsonDeserializer<Object> deser = ctxt.findRootValueDeserializer(idType);
            oir = ObjectIdReader.construct(idType, objectIdInfo.getPropertyName(), idGen, deser, idProp, resolver);
        }
        BeanDeserializerBase contextual = this;
        if (oir != null && oir != this._objectIdReader) {
            contextual = contextual.withObjectIdReader(oir);
        }
        if (accessor != null && (ignorals = intr.findPropertyIgnorals(accessor)) != null && !(ignored = ignorals.findIgnoredForDeserialization()).isEmpty()) {
            Set<String> prev = contextual._ignorableProps;
            if (prev != null && !prev.isEmpty()) {
                ignored = new HashSet<String>(ignored);
                ignored.addAll(prev);
            }
            contextual = contextual.withIgnorableProperties(ignored);
        }
        JsonFormat.Value format = this.findFormatOverrides(ctxt, property, this.handledType());
        JsonFormat.Shape shape = null;
        if (format != null) {
            BeanPropertyMap propsOrig;
            BeanPropertyMap props;
            Boolean B;
            if (format.hasShape()) {
                shape = format.getShape();
            }
            if ((B = format.getFeature(JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)) != null && (props = (propsOrig = this._beanProperties).withCaseInsensitivity(B)) != propsOrig) {
                contextual = contextual.withBeanProperties(props);
            }
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        if (shape == JsonFormat.Shape.ARRAY) {
            contextual = contextual.asArrayDeserializer();
        }
        return contextual;
    }

    protected SettableBeanProperty _resolveManagedReferenceProperty(DeserializationContext ctxt, SettableBeanProperty prop) throws JsonMappingException {
        String refName = prop.getManagedReferenceName();
        if (refName == null) {
            return prop;
        }
        JsonDeserializer<Object> valueDeser = prop.getValueDeserializer();
        SettableBeanProperty backProp = valueDeser.findBackReference(refName);
        if (backProp == null) {
            ctxt.reportBadDefinition(this._beanType, String.format("Cannot handle managed/back reference '%s': no back reference property found from type %s", refName, prop.getType()));
        }
        JavaType referredType = this._beanType;
        JavaType backRefType = backProp.getType();
        boolean isContainer = prop.getType().isContainerType();
        if (!backRefType.getRawClass().isAssignableFrom(referredType.getRawClass())) {
            ctxt.reportBadDefinition(this._beanType, String.format("Cannot handle managed/back reference '%s': back reference type (%s) not compatible with managed type (%s)", refName, backRefType.getRawClass().getName(), referredType.getRawClass().getName()));
        }
        return new ManagedReferenceProperty(prop, refName, backProp, isContainer);
    }

    protected SettableBeanProperty _resolvedObjectIdProperty(DeserializationContext ctxt, SettableBeanProperty prop) throws JsonMappingException {
        ObjectIdReader objectIdReader;
        ObjectIdInfo objectIdInfo = prop.getObjectIdInfo();
        JsonDeserializer<Object> valueDeser = prop.getValueDeserializer();
        ObjectIdReader objectIdReader2 = objectIdReader = valueDeser == null ? null : valueDeser.getObjectIdReader();
        if (objectIdInfo == null && objectIdReader == null) {
            return prop;
        }
        return new ObjectIdReferenceProperty(prop, objectIdInfo);
    }

    protected NameTransformer _findPropertyUnwrapper(DeserializationContext ctxt, SettableBeanProperty prop) throws JsonMappingException {
        NameTransformer unwrapper;
        AnnotatedMember am2 = prop.getMember();
        if (am2 != null && (unwrapper = ctxt.getAnnotationIntrospector().findUnwrappingNameTransformer(am2)) != null) {
            if (prop instanceof CreatorProperty) {
                ctxt.reportBadDefinition(this.getValueType(), String.format("Cannot define Creator property \"%s\" as `@JsonUnwrapped`: combination not yet supported", prop.getName()));
            }
            return unwrapper;
        }
        return null;
    }

    protected SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationContext ctxt, SettableBeanProperty prop) {
        Class<?> valueClass;
        Class<?> enclosing;
        BeanDeserializerBase bd2;
        ValueInstantiator vi;
        JsonDeserializer<Object> deser = prop.getValueDeserializer();
        if (deser instanceof BeanDeserializerBase && !(vi = (bd2 = (BeanDeserializerBase)deser).getValueInstantiator()).canCreateUsingDefault() && (enclosing = ClassUtil.getOuterClass(valueClass = prop.getType().getRawClass())) != null && enclosing == this._beanType.getRawClass()) {
            for (Constructor<?> ctor : valueClass.getConstructors()) {
                Class<?>[] paramTypes = ctor.getParameterTypes();
                if (paramTypes.length != 1 || !enclosing.equals(paramTypes[0])) continue;
                if (ctxt.canOverrideAccessModifiers()) {
                    ClassUtil.checkAndFixAccess(ctor, ctxt.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                }
                return new InnerClassProperty(prop, ctor);
            }
        }
        return prop;
    }

    protected SettableBeanProperty _resolveMergeAndNullSettings(DeserializationContext ctxt, SettableBeanProperty prop, PropertyMetadata propMetadata) throws JsonMappingException {
        NullValueProvider nuller;
        PropertyMetadata.MergeInfo merge = propMetadata.getMergeInfo();
        if (merge != null) {
            JsonDeserializer<Object> valueDeser = prop.getValueDeserializer();
            Boolean mayMerge = valueDeser.supportsUpdate(ctxt.getConfig());
            if (mayMerge == null) {
                if (merge.fromDefaults) {
                    return prop;
                }
            } else if (!mayMerge.booleanValue()) {
                if (!merge.fromDefaults) {
                    ctxt.reportBadMerge(valueDeser);
                }
                return prop;
            }
            AnnotatedMember accessor = merge.getter;
            accessor.fixAccess(ctxt.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            if (!(prop instanceof SetterlessProperty)) {
                prop = MergingSettableBeanProperty.construct(prop, accessor);
            }
        }
        if ((nuller = this.findValueNullProvider(ctxt, prop, propMetadata)) != null) {
            prop = prop.withNullProvider(nuller);
        }
        return prop;
    }

    @Override
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.ALWAYS_NULL;
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        try {
            return this._valueInstantiator.createUsingDefault(ctxt);
        }
        catch (IOException e10) {
            return ClassUtil.throwAsMappingException(ctxt, e10);
        }
    }

    @Override
    public boolean isCachable() {
        return true;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.TRUE;
    }

    @Override
    public Class<?> handledType() {
        return this._beanType.getRawClass();
    }

    @Override
    public ObjectIdReader getObjectIdReader() {
        return this._objectIdReader;
    }

    public boolean hasProperty(String propertyName) {
        return this._beanProperties.find(propertyName) != null;
    }

    public boolean hasViews() {
        return this._needViewProcesing;
    }

    public int getPropertyCount() {
        return this._beanProperties.size();
    }

    @Override
    public Collection<Object> getKnownPropertyNames() {
        ArrayList<Object> names = new ArrayList<Object>();
        for (SettableBeanProperty prop : this._beanProperties) {
            names.add(prop.getName());
        }
        return names;
    }

    @Deprecated
    public final Class<?> getBeanClass() {
        return this._beanType.getRawClass();
    }

    @Override
    public JavaType getValueType() {
        return this._beanType;
    }

    public Iterator<SettableBeanProperty> properties() {
        if (this._beanProperties == null) {
            throw new IllegalStateException("Can only call after BeanDeserializer has been resolved");
        }
        return this._beanProperties.iterator();
    }

    public Iterator<SettableBeanProperty> creatorProperties() {
        if (this._propertyBasedCreator == null) {
            return Collections.emptyList().iterator();
        }
        return this._propertyBasedCreator.properties().iterator();
    }

    public SettableBeanProperty findProperty(PropertyName propertyName) {
        return this.findProperty(propertyName.getSimpleName());
    }

    public SettableBeanProperty findProperty(String propertyName) {
        SettableBeanProperty prop;
        SettableBeanProperty settableBeanProperty = prop = this._beanProperties == null ? null : this._beanProperties.find(propertyName);
        if (BeanDeserializerBase._neitherNull(prop, this._propertyBasedCreator)) {
            prop = this._propertyBasedCreator.findCreatorProperty(propertyName);
        }
        return prop;
    }

    public SettableBeanProperty findProperty(int propertyIndex) {
        SettableBeanProperty prop;
        SettableBeanProperty settableBeanProperty = prop = this._beanProperties == null ? null : this._beanProperties.find(propertyIndex);
        if (BeanDeserializerBase._neitherNull(prop, this._propertyBasedCreator)) {
            prop = this._propertyBasedCreator.findCreatorProperty(propertyIndex);
        }
        return prop;
    }

    @Override
    public SettableBeanProperty findBackReference(String logicalName) {
        if (this._backRefs == null) {
            return null;
        }
        return this._backRefs.get(logicalName);
    }

    @Override
    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public void replaceProperty(SettableBeanProperty original, SettableBeanProperty replacement) {
        this._beanProperties.replace(replacement);
    }

    public abstract Object deserializeFromObject(JsonParser var1, DeserializationContext var2) throws IOException;

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        if (this._objectIdReader != null) {
            Object id;
            if (p2.canReadObjectId() && (id = p2.getObjectId()) != null) {
                Object ob = typeDeserializer.deserializeTypedFromObject(p2, ctxt);
                return this._handleTypedObjectId(p2, ctxt, ob, id);
            }
            JsonToken t2 = p2.getCurrentToken();
            if (t2 != null) {
                if (t2.isScalarValue()) {
                    return this.deserializeFromObjectId(p2, ctxt);
                }
                if (t2 == JsonToken.START_OBJECT) {
                    t2 = p2.nextToken();
                }
                if (t2 == JsonToken.FIELD_NAME && this._objectIdReader.maySerializeAsObject() && this._objectIdReader.isValidReferencePropertyName(p2.getCurrentName(), p2)) {
                    return this.deserializeFromObjectId(p2, ctxt);
                }
            }
        }
        return typeDeserializer.deserializeTypedFromObject(p2, ctxt);
    }

    protected Object _handleTypedObjectId(JsonParser p2, DeserializationContext ctxt, Object pojo, Object rawId) throws IOException {
        JsonDeserializer<Object> idDeser = this._objectIdReader.getDeserializer();
        Object id = idDeser.handledType() == rawId.getClass() ? rawId : this._convertObjectId(p2, ctxt, rawId, idDeser);
        ReadableObjectId roid = ctxt.findObjectId(id, this._objectIdReader.generator, this._objectIdReader.resolver);
        roid.bindItem(pojo);
        SettableBeanProperty idProp = this._objectIdReader.idProperty;
        if (idProp != null) {
            return idProp.setAndReturn(pojo, id);
        }
        return pojo;
    }

    protected Object _convertObjectId(JsonParser p2, DeserializationContext ctxt, Object rawId, JsonDeserializer<Object> idDeser) throws IOException {
        TokenBuffer buf = new TokenBuffer(p2, ctxt);
        if (rawId instanceof String) {
            buf.writeString((String)rawId);
        } else if (rawId instanceof Long) {
            buf.writeNumber((Long)rawId);
        } else if (rawId instanceof Integer) {
            buf.writeNumber((Integer)rawId);
        } else {
            buf.writeObject(rawId);
        }
        JsonParser bufParser = buf.asParser();
        bufParser.nextToken();
        return idDeser.deserialize(bufParser, ctxt);
    }

    protected Object deserializeWithObjectId(JsonParser p2, DeserializationContext ctxt) throws IOException {
        return this.deserializeFromObject(p2, ctxt);
    }

    protected Object deserializeFromObjectId(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object id = this._objectIdReader.readObjectReference(p2, ctxt);
        ReadableObjectId roid = ctxt.findObjectId(id, this._objectIdReader.generator, this._objectIdReader.resolver);
        Object pojo = roid.resolve();
        if (pojo == null) {
            throw new UnresolvedForwardReference(p2, "Could not resolve Object Id [" + id + "] (for " + this._beanType + ").", p2.getCurrentLocation(), roid);
        }
        return pojo;
    }

    protected Object deserializeFromObjectUsingNonDefault(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonDeserializer<Object> delegateDeser = this._delegateDeserializer();
        if (delegateDeser != null) {
            return this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
        }
        if (this._propertyBasedCreator != null) {
            return this._deserializeUsingPropertyBased(p2, ctxt);
        }
        Class<?> raw = this._beanType.getRawClass();
        if (ClassUtil.isNonStaticInnerClass(raw)) {
            return ctxt.handleMissingInstantiator(raw, null, p2, "can only instantiate non-static inner class by using default, no-argument constructor", new Object[0]);
        }
        return ctxt.handleMissingInstantiator(raw, this.getValueInstantiator(), p2, "cannot deserialize from Object value (no delegate- or property-based Creator)", new Object[0]);
    }

    protected abstract Object _deserializeUsingPropertyBased(JsonParser var1, DeserializationContext var2) throws IOException;

    public Object deserializeFromNumber(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._objectIdReader != null) {
            return this.deserializeFromObjectId(p2, ctxt);
        }
        JsonDeserializer<Object> delegateDeser = this._delegateDeserializer();
        JsonParser.NumberType nt = p2.getNumberType();
        if (nt == JsonParser.NumberType.INT) {
            if (delegateDeser != null && !this._valueInstantiator.canCreateFromInt()) {
                Object bean = this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
                if (this._injectables != null) {
                    this.injectValues(ctxt, bean);
                }
                return bean;
            }
            return this._valueInstantiator.createFromInt(ctxt, p2.getIntValue());
        }
        if (nt == JsonParser.NumberType.LONG) {
            if (delegateDeser != null && !this._valueInstantiator.canCreateFromInt()) {
                Object bean = this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
                if (this._injectables != null) {
                    this.injectValues(ctxt, bean);
                }
                return bean;
            }
            return this._valueInstantiator.createFromLong(ctxt, p2.getLongValue());
        }
        if (delegateDeser != null) {
            Object bean = this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
            if (this._injectables != null) {
                this.injectValues(ctxt, bean);
            }
            return bean;
        }
        return ctxt.handleMissingInstantiator(this.handledType(), this.getValueInstantiator(), p2, "no suitable creator method found to deserialize from Number value (%s)", p2.getNumberValue());
    }

    public Object deserializeFromString(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._objectIdReader != null) {
            return this.deserializeFromObjectId(p2, ctxt);
        }
        JsonDeserializer<Object> delegateDeser = this._delegateDeserializer();
        if (delegateDeser != null && !this._valueInstantiator.canCreateFromString()) {
            Object bean = this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
            if (this._injectables != null) {
                this.injectValues(ctxt, bean);
            }
            return bean;
        }
        return this._valueInstantiator.createFromString(ctxt, p2.getText());
    }

    public Object deserializeFromDouble(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonParser.NumberType t2 = p2.getNumberType();
        if (t2 == JsonParser.NumberType.DOUBLE || t2 == JsonParser.NumberType.FLOAT) {
            JsonDeserializer<Object> delegateDeser = this._delegateDeserializer();
            if (delegateDeser != null && !this._valueInstantiator.canCreateFromDouble()) {
                Object bean = this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
                if (this._injectables != null) {
                    this.injectValues(ctxt, bean);
                }
                return bean;
            }
            return this._valueInstantiator.createFromDouble(ctxt, p2.getDoubleValue());
        }
        JsonDeserializer<Object> delegateDeser = this._delegateDeserializer();
        if (delegateDeser != null) {
            return this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
        }
        return ctxt.handleMissingInstantiator(this.handledType(), this.getValueInstantiator(), p2, "no suitable creator method found to deserialize from Number value (%s)", p2.getNumberValue());
    }

    public Object deserializeFromBoolean(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonDeserializer<Object> delegateDeser = this._delegateDeserializer();
        if (delegateDeser != null && !this._valueInstantiator.canCreateFromBoolean()) {
            Object bean = this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
            if (this._injectables != null) {
                this.injectValues(ctxt, bean);
            }
            return bean;
        }
        boolean value = p2.getCurrentToken() == JsonToken.VALUE_TRUE;
        return this._valueInstantiator.createFromBoolean(ctxt, value);
    }

    public Object deserializeFromArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonDeserializer<Object> delegateDeser = this._arrayDelegateDeserializer;
        if (delegateDeser != null || (delegateDeser = this._delegateDeserializer) != null) {
            Object bean = this._valueInstantiator.createUsingArrayDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
            if (this._injectables != null) {
                this.injectValues(ctxt, bean);
            }
            return bean;
        }
        if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            JsonToken t2 = p2.nextToken();
            if (t2 == JsonToken.END_ARRAY && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                return null;
            }
            Object value = this.deserialize(p2, ctxt);
            if (p2.nextToken() != JsonToken.END_ARRAY) {
                this.handleMissingEndArrayForSingle(p2, ctxt);
            }
            return value;
        }
        if (ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
            JsonToken t3 = p2.nextToken();
            if (t3 == JsonToken.END_ARRAY) {
                return null;
            }
            return ctxt.handleUnexpectedToken(this.handledType(), JsonToken.START_ARRAY, p2, null, new Object[0]);
        }
        return ctxt.handleUnexpectedToken(this.handledType(), p2);
    }

    public Object deserializeFromEmbedded(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (this._objectIdReader != null) {
            return this.deserializeFromObjectId(p2, ctxt);
        }
        JsonDeserializer<Object> delegateDeser = this._delegateDeserializer();
        if (delegateDeser != null && !this._valueInstantiator.canCreateFromString()) {
            Object bean = this._valueInstantiator.createUsingDelegate(ctxt, delegateDeser.deserialize(p2, ctxt));
            if (this._injectables != null) {
                this.injectValues(ctxt, bean);
            }
            return bean;
        }
        Object value = p2.getEmbeddedObject();
        if (value != null && !this._beanType.getClass().isInstance(value)) {
            value = ctxt.handleWeirdNativeValue(this._beanType, value, p2);
        }
        return value;
    }

    private final JsonDeserializer<Object> _delegateDeserializer() {
        JsonDeserializer<Object> deser = this._delegateDeserializer;
        if (deser == null) {
            deser = this._arrayDelegateDeserializer;
        }
        return deser;
    }

    protected void injectValues(DeserializationContext ctxt, Object bean) throws IOException {
        for (ValueInjector injector : this._injectables) {
            injector.inject(ctxt, bean);
        }
    }

    protected Object handleUnknownProperties(DeserializationContext ctxt, Object bean, TokenBuffer unknownTokens) throws IOException {
        unknownTokens.writeEndObject();
        JsonParser bufferParser = unknownTokens.asParser();
        while (bufferParser.nextToken() != JsonToken.END_OBJECT) {
            String propName = bufferParser.getCurrentName();
            bufferParser.nextToken();
            this.handleUnknownProperty(bufferParser, ctxt, bean, propName);
        }
        return bean;
    }

    protected void handleUnknownVanilla(JsonParser p2, DeserializationContext ctxt, Object bean, String propName) throws IOException {
        if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
            this.handleIgnoredProperty(p2, ctxt, bean, propName);
        } else if (this._anySetter != null) {
            try {
                this._anySetter.deserializeAndSet(p2, ctxt, bean, propName);
            }
            catch (Exception e10) {
                this.wrapAndThrow(e10, bean, propName, ctxt);
            }
        } else {
            this.handleUnknownProperty(p2, ctxt, bean, propName);
        }
    }

    @Override
    protected void handleUnknownProperty(JsonParser p2, DeserializationContext ctxt, Object beanOrClass, String propName) throws IOException {
        if (this._ignoreAllUnknown) {
            p2.skipChildren();
            return;
        }
        if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
            this.handleIgnoredProperty(p2, ctxt, beanOrClass, propName);
        }
        super.handleUnknownProperty(p2, ctxt, beanOrClass, propName);
    }

    protected void handleIgnoredProperty(JsonParser p2, DeserializationContext ctxt, Object beanOrClass, String propName) throws IOException {
        if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)) {
            throw IgnoredPropertyException.from(p2, beanOrClass, propName, this.getKnownPropertyNames());
        }
        p2.skipChildren();
    }

    protected Object handlePolymorphic(JsonParser p2, DeserializationContext ctxt, Object bean, TokenBuffer unknownTokens) throws IOException {
        JsonDeserializer<Object> subDeser = this._findSubclassDeserializer(ctxt, bean, unknownTokens);
        if (subDeser != null) {
            if (unknownTokens != null) {
                unknownTokens.writeEndObject();
                JsonParser p22 = unknownTokens.asParser();
                p22.nextToken();
                bean = subDeser.deserialize(p22, ctxt, bean);
            }
            if (p2 != null) {
                bean = subDeser.deserialize(p2, ctxt, bean);
            }
            return bean;
        }
        if (unknownTokens != null) {
            bean = this.handleUnknownProperties(ctxt, bean, unknownTokens);
        }
        if (p2 != null) {
            bean = this.deserialize(p2, ctxt, bean);
        }
        return bean;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext ctxt, Object bean, TokenBuffer unknownTokens) throws IOException {
        JsonDeserializer<Object> subDeser;
        BeanDeserializerBase beanDeserializerBase = this;
        synchronized (beanDeserializerBase) {
            subDeser = this._subDeserializers == null ? null : this._subDeserializers.get(new ClassKey(bean.getClass()));
        }
        if (subDeser != null) {
            return subDeser;
        }
        JavaType type = ctxt.constructType(bean.getClass());
        subDeser = ctxt.findRootValueDeserializer(type);
        if (subDeser != null) {
            BeanDeserializerBase beanDeserializerBase2 = this;
            synchronized (beanDeserializerBase2) {
                if (this._subDeserializers == null) {
                    this._subDeserializers = new HashMap();
                }
                this._subDeserializers.put(new ClassKey(bean.getClass()), subDeser);
            }
        }
        return subDeser;
    }

    public void wrapAndThrow(Throwable t2, Object bean, String fieldName, DeserializationContext ctxt) throws IOException {
        throw JsonMappingException.wrapWithPath(this.throwOrReturnThrowable(t2, ctxt), bean, fieldName);
    }

    private Throwable throwOrReturnThrowable(Throwable t2, DeserializationContext ctxt) throws IOException {
        boolean wrap;
        while (t2 instanceof InvocationTargetException && t2.getCause() != null) {
            t2 = t2.getCause();
        }
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

    protected Object wrapInstantiationProblem(Throwable t2, DeserializationContext ctxt) throws IOException {
        boolean wrap;
        while (t2 instanceof InvocationTargetException && t2.getCause() != null) {
            t2 = t2.getCause();
        }
        ClassUtil.throwIfError(t2);
        if (t2 instanceof IOException) {
            throw (IOException)t2;
        }
        boolean bl2 = wrap = ctxt == null || ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (!wrap) {
            ClassUtil.throwIfRTE(t2);
        }
        return ctxt.handleInstantiationProblem(this._beanType.getRawClass(), null, t2);
    }
}

