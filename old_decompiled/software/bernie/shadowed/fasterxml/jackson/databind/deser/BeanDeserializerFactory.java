/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreProperties;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerators;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.AbstractTypeResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.KeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.CreatorProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableAnyProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ErrorThrowingDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.FieldProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.MethodProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedField;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;

public class BeanDeserializerFactory
extends BasicDeserializerFactory
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Class<?>[] INIT_CAUSE_PARAMS = new Class[]{Throwable.class};
    protected static final Set<String> DEFAULT_NO_DESER_CLASS_NAMES;
    protected Set<String> _cfgIllegalClassNames = DEFAULT_NO_DESER_CLASS_NAMES;
    public static final BeanDeserializerFactory instance;

    public BeanDeserializerFactory(DeserializerFactoryConfig config) {
        super(config);
    }

    @Override
    public DeserializerFactory withConfig(DeserializerFactoryConfig config) {
        if (this._factoryConfig == config) {
            return this;
        }
        ClassUtil.verifyMustOverride(BeanDeserializerFactory.class, this, "withConfig");
        return new BeanDeserializerFactory(config);
    }

    @Override
    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        JavaType concreteType;
        DeserializationConfig config = ctxt.getConfig();
        JsonDeserializer<Object> custom = this._findCustomBeanDeserializer(type, config, beanDesc);
        if (custom != null) {
            return custom;
        }
        if (type.isThrowable()) {
            return this.buildThrowableDeserializer(ctxt, type, beanDesc);
        }
        if (type.isAbstract() && !type.isPrimitive() && !type.isEnumType() && (concreteType = this.materializeAbstractType(ctxt, type, beanDesc)) != null) {
            beanDesc = config.introspect(concreteType);
            return this.buildBeanDeserializer(ctxt, concreteType, beanDesc);
        }
        JsonDeserializer<Object> deser = this.findStdDeserializer(ctxt, type, beanDesc);
        if (deser != null) {
            return deser;
        }
        if (!this.isPotentialBeanType(type.getRawClass())) {
            return null;
        }
        this.checkIllegalTypes(ctxt, type, beanDesc);
        return this.buildBeanDeserializer(ctxt, type, beanDesc);
    }

    @Override
    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext ctxt, JavaType valueType, BeanDescription beanDesc, Class<?> builderClass) throws JsonMappingException {
        JavaType builderType = ctxt.constructType(builderClass);
        Object builderDesc = ctxt.getConfig().introspectForBuilder(builderType);
        return this.buildBuilderBasedDeserializer(ctxt, valueType, (BeanDescription)builderDesc);
    }

    protected JsonDeserializer<?> findStdDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> deser = this.findDefaultDeserializer(ctxt, type, beanDesc);
        if (deser != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyDeserializer(ctxt.getConfig(), beanDesc, deser);
            }
        }
        return deser;
    }

    protected JavaType materializeAbstractType(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        for (AbstractTypeResolver r2 : this._factoryConfig.abstractTypeResolvers()) {
            JavaType concrete = r2.resolveAbstractType(ctxt.getConfig(), beanDesc);
            if (concrete == null) continue;
            return concrete;
        }
        return null;
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        ValueInstantiator valueInstantiator;
        try {
            valueInstantiator = this.findValueInstantiator(ctxt, beanDesc);
        }
        catch (NoClassDefFoundError error) {
            return new ErrorThrowingDeserializer(error);
        }
        catch (IllegalArgumentException e10) {
            throw InvalidDefinitionException.from(ctxt.getParser(), e10.getMessage(), beanDesc, null);
        }
        BeanDeserializerBuilder builder = this.constructBeanDeserializerBuilder(ctxt, beanDesc);
        builder.setValueInstantiator(valueInstantiator);
        this.addBeanProps(ctxt, beanDesc, builder);
        this.addObjectIdReader(ctxt, beanDesc, builder);
        this.addBackReferenceProperties(ctxt, beanDesc, builder);
        this.addInjectables(ctxt, beanDesc, builder);
        DeserializationConfig config = ctxt.getConfig();
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                builder = mod.updateBuilder(config, beanDesc, builder);
            }
        }
        JsonDeserializer deserializer = type.isAbstract() && !valueInstantiator.canInstantiate() ? builder.buildAbstract() : builder.build();
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deserializer = mod.modifyDeserializer(config, beanDesc, deserializer);
            }
        }
        return deserializer;
    }

    protected JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext ctxt, JavaType valueType, BeanDescription builderDesc) throws JsonMappingException {
        ValueInstantiator valueInstantiator;
        try {
            valueInstantiator = this.findValueInstantiator(ctxt, builderDesc);
        }
        catch (NoClassDefFoundError error) {
            return new ErrorThrowingDeserializer(error);
        }
        catch (IllegalArgumentException e10) {
            throw InvalidDefinitionException.from(ctxt.getParser(), e10.getMessage(), builderDesc, null);
        }
        DeserializationConfig config = ctxt.getConfig();
        BeanDeserializerBuilder builder = this.constructBeanDeserializerBuilder(ctxt, builderDesc);
        builder.setValueInstantiator(valueInstantiator);
        this.addBeanProps(ctxt, builderDesc, builder);
        this.addObjectIdReader(ctxt, builderDesc, builder);
        this.addBackReferenceProperties(ctxt, builderDesc, builder);
        this.addInjectables(ctxt, builderDesc, builder);
        JsonPOJOBuilder.Value builderConfig = builderDesc.findPOJOBuilderConfig();
        String buildMethodName = builderConfig == null ? "build" : builderConfig.buildMethodName;
        AnnotatedMethod buildMethod = builderDesc.findMethod(buildMethodName, null);
        if (buildMethod != null && config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(buildMethod.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        builder.setPOJOBuilder(buildMethod, builderConfig);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                builder = mod.updateBuilder(config, builderDesc, builder);
            }
        }
        JsonDeserializer<Object> deserializer = builder.buildBuilderBased(valueType, buildMethodName);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deserializer = mod.modifyDeserializer(config, builderDesc, deserializer);
            }
        }
        return deserializer;
    }

    protected void addObjectIdReader(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
        ObjectIdGenerator gen;
        JavaType idType;
        SettableBeanProperty idProp;
        ObjectIdInfo objectIdInfo = beanDesc.getObjectIdInfo();
        if (objectIdInfo == null) {
            return;
        }
        Class<? extends ObjectIdGenerator<?>> implClass = objectIdInfo.getGeneratorType();
        ObjectIdResolver resolver = ctxt.objectIdResolverInstance(beanDesc.getClassInfo(), objectIdInfo);
        if (implClass == ObjectIdGenerators.PropertyGenerator.class) {
            PropertyName propName = objectIdInfo.getPropertyName();
            idProp = builder.findProperty(propName);
            if (idProp == null) {
                throw new IllegalArgumentException("Invalid Object Id definition for " + beanDesc.getBeanClass().getName() + ": cannot find property with name '" + propName + "'");
            }
            idType = idProp.getType();
            gen = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
        } else {
            JavaType type = ctxt.constructType(implClass);
            idType = ctxt.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
            idProp = null;
            gen = ctxt.objectIdGeneratorInstance(beanDesc.getClassInfo(), objectIdInfo);
        }
        JsonDeserializer<Object> deser = ctxt.findRootValueDeserializer(idType);
        builder.setObjectIdReader(ObjectIdReader.construct(idType, objectIdInfo.getPropertyName(), gen, deser, idProp, resolver));
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer deserializer;
        SimpleBeanPropertyDefinition propDef;
        SettableBeanProperty prop;
        DeserializationConfig config = ctxt.getConfig();
        BeanDeserializerBuilder builder = this.constructBeanDeserializerBuilder(ctxt, beanDesc);
        builder.setValueInstantiator(this.findValueInstantiator(ctxt, beanDesc));
        this.addBeanProps(ctxt, beanDesc, builder);
        AnnotatedMethod am2 = beanDesc.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (am2 != null && (prop = this.constructSettableProperty(ctxt, beanDesc, propDef = SimpleBeanPropertyDefinition.construct(ctxt.getConfig(), am2, new PropertyName("cause")), am2.getParameterType(0))) != null) {
            builder.addOrReplaceProperty(prop, true);
        }
        builder.addIgnorable("localizedMessage");
        builder.addIgnorable("suppressed");
        builder.addIgnorable("message");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                builder = mod.updateBuilder(config, beanDesc, builder);
            }
        }
        if ((deserializer = builder.build()) instanceof BeanDeserializer) {
            deserializer = new ThrowableDeserializer((BeanDeserializer)deserializer);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deserializer = mod.modifyDeserializer(config, beanDesc, deserializer);
            }
        }
        return deserializer;
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext ctxt, BeanDescription beanDesc) {
        return new BeanDeserializerBuilder(beanDesc, ctxt);
    }

    /*
     * WARNING - void declaration
     */
    protected void addBeanProps(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
        void var11_17;
        AnnotatedMember anySetter;
        Set<Object> ignored;
        boolean isConcrete = !beanDesc.getType().isAbstract();
        SettableBeanProperty[] creatorProps = isConcrete ? builder.getValueInstantiator().getFromObjectArguments(ctxt.getConfig()) : null;
        boolean hasCreatorProps = creatorProps != null;
        JsonIgnoreProperties.Value ignorals = ctxt.getConfig().getDefaultPropertyIgnorals(beanDesc.getBeanClass(), beanDesc.getClassInfo());
        if (ignorals != null) {
            boolean ignoreAny = ignorals.getIgnoreUnknown();
            builder.setIgnoreUnknownProperties(ignoreAny);
            ignored = ignorals.findIgnoredForDeserialization();
            for (String string : ignored) {
                builder.addIgnorable(string);
            }
        } else {
            ignored = Collections.emptySet();
        }
        if ((anySetter = beanDesc.findAnySetterAccessor()) != null) {
            builder.setAnySetter(this.constructAnySetter(ctxt, beanDesc, anySetter));
        } else {
            Set<String> ignored2 = beanDesc.getIgnoredPropertyNames();
            if (ignored2 != null) {
                for (String propName : ignored2) {
                    builder.addIgnorable(propName);
                }
            }
        }
        boolean useGettersAsSetters = ctxt.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && ctxt.isEnabled(MapperFeature.AUTO_DETECT_GETTERS);
        List<BeanPropertyDefinition> list = this.filterBeanProps(ctxt, beanDesc, builder, beanDesc.findProperties(), ignored);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                List<BeanPropertyDefinition> list2 = mod.updateProperties(ctxt.getConfig(), beanDesc, (List<BeanPropertyDefinition>)var11_17);
            }
        }
        for (BeanPropertyDefinition propDef : var11_17) {
            JavaType propertyType;
            SettableBeanProperty prop = null;
            if (propDef.hasSetter()) {
                AnnotatedMethod setter = propDef.getSetter();
                propertyType = setter.getParameterType(0);
                prop = this.constructSettableProperty(ctxt, beanDesc, propDef, propertyType);
            } else if (propDef.hasField()) {
                AnnotatedField field = propDef.getField();
                propertyType = field.getType();
                prop = this.constructSettableProperty(ctxt, beanDesc, propDef, propertyType);
            } else {
                AnnotatedMethod getter = propDef.getGetter();
                if (getter != null) {
                    PropertyMetadata md;
                    if (useGettersAsSetters && this._isSetterlessType(getter.getRawType())) {
                        prop = this.constructSetterlessProperty(ctxt, beanDesc, propDef);
                    } else if (!propDef.hasConstructorParameter() && (md = propDef.getMetadata()).getMergeInfo() != null) {
                        prop = this.constructSetterlessProperty(ctxt, beanDesc, propDef);
                    }
                }
            }
            if (hasCreatorProps && propDef.hasConstructorParameter()) {
                Class<?>[] views;
                String name = propDef.getName();
                CreatorProperty cprop = null;
                if (creatorProps != null) {
                    for (SettableBeanProperty cp2 : creatorProps) {
                        if (!name.equals(cp2.getName()) || !(cp2 instanceof CreatorProperty)) continue;
                        cprop = (CreatorProperty)cp2;
                        break;
                    }
                }
                if (cprop == null) {
                    ArrayList<String> n2 = new ArrayList<String>();
                    for (SettableBeanProperty cp3 : creatorProps) {
                        n2.add(cp3.getName());
                    }
                    ctxt.reportBadPropertyDefinition(beanDesc, propDef, "Could not find creator property with name '%s' (known Creator properties: %s)", name, n2);
                    continue;
                }
                if (prop != null) {
                    cprop.setFallbackSetter(prop);
                }
                if ((views = propDef.findViews()) == null) {
                    views = beanDesc.findDefaultViews();
                }
                cprop.setViews(views);
                builder.addCreatorProperty(cprop);
                continue;
            }
            if (prop == null) continue;
            Class<?>[] views = propDef.findViews();
            if (views == null) {
                views = beanDesc.findDefaultViews();
            }
            prop.setViews(views);
            builder.addProperty(prop);
        }
    }

    private boolean _isSetterlessType(Class<?> rawType) {
        return Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType);
    }

    protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder, List<BeanPropertyDefinition> propDefsIn, Set<String> ignored) throws JsonMappingException {
        ArrayList<BeanPropertyDefinition> result = new ArrayList<BeanPropertyDefinition>(Math.max(4, propDefsIn.size()));
        HashMap ignoredTypes = new HashMap();
        for (BeanPropertyDefinition property : propDefsIn) {
            Class<?> rawPropertyType;
            String name = property.getName();
            if (ignored.contains(name)) continue;
            if (!property.hasConstructorParameter() && (rawPropertyType = property.getRawPrimaryType()) != null && this.isIgnorableType(ctxt.getConfig(), property, rawPropertyType, ignoredTypes)) {
                builder.addIgnorable(name);
                continue;
            }
            result.add(property);
        }
        return result;
    }

    protected void addBackReferenceProperties(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
        List<BeanPropertyDefinition> refProps = beanDesc.findBackReferences();
        if (refProps != null) {
            for (BeanPropertyDefinition refProp : refProps) {
                String refName = refProp.findReferenceName();
                builder.addBackReferenceProperty(refName, this.constructSettableProperty(ctxt, beanDesc, refProp, refProp.getPrimaryType()));
            }
        }
    }

    @Deprecated
    protected void addReferenceProperties(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
        this.addBackReferenceProperties(ctxt, beanDesc, builder);
    }

    protected void addInjectables(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
        Map<Object, AnnotatedMember> raw = beanDesc.findInjectables();
        if (raw != null) {
            for (Map.Entry<Object, AnnotatedMember> entry : raw.entrySet()) {
                AnnotatedMember m2 = entry.getValue();
                builder.addInjectable(PropertyName.construct(m2.getName()), m2.getType(), beanDesc.getClassAnnotations(), m2, entry.getKey());
            }
        }
    }

    protected SettableAnyProperty constructAnySetter(DeserializationContext ctxt, BeanDescription beanDesc, AnnotatedMember mutator) throws JsonMappingException {
        BeanProperty.Std prop;
        JavaType valueType;
        JavaType keyType;
        if (mutator instanceof AnnotatedMethod) {
            AnnotatedMethod am2 = (AnnotatedMethod)mutator;
            keyType = am2.getParameterType(0);
            valueType = am2.getParameterType(1);
            valueType = this.resolveMemberAndTypeAnnotations(ctxt, mutator, valueType);
            prop = new BeanProperty.Std(PropertyName.construct(mutator.getName()), valueType, null, mutator, PropertyMetadata.STD_OPTIONAL);
        } else if (mutator instanceof AnnotatedField) {
            AnnotatedField af2 = (AnnotatedField)mutator;
            JavaType mapType = af2.getType();
            mapType = this.resolveMemberAndTypeAnnotations(ctxt, mutator, mapType);
            keyType = mapType.getKeyType();
            valueType = mapType.getContentType();
            prop = new BeanProperty.Std(PropertyName.construct(mutator.getName()), mapType, null, mutator, PropertyMetadata.STD_OPTIONAL);
        } else {
            return (SettableAnyProperty)ctxt.reportBadDefinition(beanDesc.getType(), String.format("Unrecognized mutator type for any setter: %s", mutator.getClass()));
        }
        KeyDeserializer keyDeser = this.findKeyDeserializerFromAnnotation(ctxt, mutator);
        if (keyDeser == null) {
            keyDeser = (KeyDeserializer)keyType.getValueHandler();
        }
        if (keyDeser == null) {
            keyDeser = ctxt.findKeyDeserializer(keyType, prop);
        } else if (keyDeser instanceof ContextualKeyDeserializer) {
            keyDeser = ((ContextualKeyDeserializer)((Object)keyDeser)).createContextual(ctxt, prop);
        }
        JsonDeserializer<Object> deser = this.findContentDeserializerFromAnnotation(ctxt, mutator);
        if (deser == null) {
            deser = (JsonDeserializer<Object>)valueType.getValueHandler();
        }
        if (deser != null) {
            deser = ctxt.handlePrimaryContextualization(deser, prop, valueType);
        }
        TypeDeserializer typeDeser = (TypeDeserializer)valueType.getTypeHandler();
        return new SettableAnyProperty(prop, mutator, valueType, keyDeser, deser, typeDeser);
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationContext ctxt, BeanDescription beanDesc, BeanPropertyDefinition propDef, JavaType propType0) throws JsonMappingException {
        ObjectIdInfo objectIdInfo;
        AnnotationIntrospector.ReferenceProperty ref;
        AnnotatedMember mutator = propDef.getNonConstructorMutator();
        if (mutator == null) {
            ctxt.reportBadPropertyDefinition(beanDesc, propDef, "No non-constructor mutator available", new Object[0]);
        }
        JavaType type = this.resolveMemberAndTypeAnnotations(ctxt, mutator, propType0);
        TypeDeserializer typeDeser = (TypeDeserializer)type.getTypeHandler();
        SettableBeanProperty prop = mutator instanceof AnnotatedMethod ? new MethodProperty(propDef, type, typeDeser, beanDesc.getClassAnnotations(), (AnnotatedMethod)mutator) : new FieldProperty(propDef, type, typeDeser, beanDesc.getClassAnnotations(), (AnnotatedField)mutator);
        JsonDeserializer<?> deser = this.findDeserializerFromAnnotation(ctxt, mutator);
        if (deser == null) {
            deser = (JsonDeserializer<?>)type.getValueHandler();
        }
        if (deser != null) {
            deser = ctxt.handlePrimaryContextualization(deser, prop, type);
            prop = prop.withValueDeserializer(deser);
        }
        if ((ref = propDef.findReferenceType()) != null && ref.isManagedReference()) {
            prop.setManagedReferenceName(ref.getName());
        }
        if ((objectIdInfo = propDef.findObjectIdInfo()) != null) {
            prop.setObjectIdInfo(objectIdInfo);
        }
        return prop;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationContext ctxt, BeanDescription beanDesc, BeanPropertyDefinition propDef) throws JsonMappingException {
        AnnotatedMethod getter = propDef.getGetter();
        JavaType type = this.resolveMemberAndTypeAnnotations(ctxt, getter, getter.getType());
        TypeDeserializer typeDeser = (TypeDeserializer)type.getTypeHandler();
        SettableBeanProperty prop = new SetterlessProperty(propDef, type, typeDeser, beanDesc.getClassAnnotations(), getter);
        JsonDeserializer<?> deser = this.findDeserializerFromAnnotation(ctxt, getter);
        if (deser == null) {
            deser = (JsonDeserializer<?>)type.getValueHandler();
        }
        if (deser != null) {
            deser = ctxt.handlePrimaryContextualization(deser, prop, type);
            prop = ((SettableBeanProperty)prop).withValueDeserializer(deser);
        }
        return prop;
    }

    protected boolean isPotentialBeanType(Class<?> type) {
        String typeStr = ClassUtil.canBeABeanType(type);
        if (typeStr != null) {
            throw new IllegalArgumentException("Cannot deserialize Class " + type.getName() + " (of type " + typeStr + ") as a Bean");
        }
        if (ClassUtil.isProxyType(type)) {
            throw new IllegalArgumentException("Cannot deserialize Proxy class " + type.getName() + " as a Bean");
        }
        typeStr = ClassUtil.isLocalType(type, true);
        if (typeStr != null) {
            throw new IllegalArgumentException("Cannot deserialize Class " + type.getName() + " (of type " + typeStr + ") as a Bean");
        }
        return true;
    }

    protected boolean isIgnorableType(DeserializationConfig config, BeanPropertyDefinition propDef, Class<?> type, Map<Class<?>, Boolean> ignoredTypes) {
        Boolean status = ignoredTypes.get(type);
        if (status != null) {
            return status;
        }
        if (type == String.class || type.isPrimitive()) {
            status = Boolean.FALSE;
        } else {
            status = config.getConfigOverride(type).getIsIgnoredType();
            if (status == null) {
                BeanDescription desc = config.introspectClassAnnotations(type);
                status = config.getAnnotationIntrospector().isIgnorableType(desc.getClassInfo());
                if (status == null) {
                    status = Boolean.FALSE;
                }
            }
        }
        ignoredTypes.put(type, status);
        return status;
    }

    protected void checkIllegalTypes(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        String full = type.getRawClass().getName();
        if (this._cfgIllegalClassNames.contains(full)) {
            ctxt.reportBadTypeDefinition(beanDesc, "Illegal type (%s) to deserialize: prevented for security reasons", full);
        }
    }

    static {
        HashSet<String> s2 = new HashSet<String>();
        s2.add("org.apache.commons.collections.functors.InvokerTransformer");
        s2.add("org.apache.commons.collections.functors.InstantiateTransformer");
        s2.add("org.apache.commons.collections4.functors.InvokerTransformer");
        s2.add("org.apache.commons.collections4.functors.InstantiateTransformer");
        s2.add("org.codehaus.groovy.runtime.ConvertedClosure");
        s2.add("org.codehaus.groovy.runtime.MethodClosure");
        s2.add("org.springframework.beans.factory.ObjectFactory");
        s2.add("com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl");
        s2.add("org.apache.xalan.xsltc.trax.TemplatesImpl");
        s2.add("com.sun.rowset.JdbcRowSetImpl");
        DEFAULT_NO_DESER_CLASS_NAMES = Collections.unmodifiableSet(s2);
        instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    }
}

