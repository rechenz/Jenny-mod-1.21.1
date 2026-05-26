/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreProperties;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerators;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClass;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedField;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.NamedType;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.AnyGetterWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BasicSerializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.PropertyBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ResolvableSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.SerializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.Serializers;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.FilteredBeanPropertyWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.MapSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.type.ReferenceType;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Converter;

public class BeanSerializerFactory
extends BasicSerializerFactory
implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);

    protected BeanSerializerFactory(SerializerFactoryConfig config) {
        super(config);
    }

    @Override
    public SerializerFactory withConfig(SerializerFactoryConfig config) {
        if (this._factoryConfig == config) {
            return this;
        }
        if (this.getClass() != BeanSerializerFactory.class) {
            throw new IllegalStateException("Subtype of BeanSerializerFactory (" + this.getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': cannot instantiate subtype with " + "additional serializer definitions");
        }
        return new BeanSerializerFactory(config);
    }

    @Override
    protected Iterable<Serializers> customSerializers() {
        return this._factoryConfig.serializers();
    }

    @Override
    public JsonSerializer<Object> createSerializer(SerializerProvider prov, JavaType origType) throws JsonMappingException {
        boolean staticTyping;
        JavaType type;
        SerializationConfig config = prov.getConfig();
        Object beanDesc = config.introspect(origType);
        JsonSerializer<Object> ser = this.findSerializerFromAnnotation(prov, ((BeanDescription)beanDesc).getClassInfo());
        if (ser != null) {
            return ser;
        }
        AnnotationIntrospector intr = config.getAnnotationIntrospector();
        if (intr == null) {
            type = origType;
        } else {
            try {
                type = intr.refineSerializationType(config, ((BeanDescription)beanDesc).getClassInfo(), origType);
            }
            catch (JsonMappingException e10) {
                return (JsonSerializer)prov.reportBadTypeDefinition((BeanDescription)beanDesc, e10.getMessage(), new Object[0]);
            }
        }
        if (type == origType) {
            staticTyping = false;
        } else {
            staticTyping = true;
            if (!type.hasRawClass(origType.getRawClass())) {
                beanDesc = config.introspect(type);
            }
        }
        Converter<Object, Object> conv = ((BeanDescription)beanDesc).findSerializationConverter();
        if (conv == null) {
            return this._createSerializer2(prov, type, (BeanDescription)beanDesc, staticTyping);
        }
        JavaType delegateType = conv.getOutputType(prov.getTypeFactory());
        if (!delegateType.hasRawClass(type.getRawClass())) {
            beanDesc = config.introspect(delegateType);
            ser = this.findSerializerFromAnnotation(prov, ((BeanDescription)beanDesc).getClassInfo());
        }
        if (ser == null && !delegateType.isJavaLangObject()) {
            ser = this._createSerializer2(prov, delegateType, (BeanDescription)beanDesc, true);
        }
        return new StdDelegatingSerializer(conv, delegateType, ser);
    }

    protected JsonSerializer<?> _createSerializer2(SerializerProvider prov, JavaType type, BeanDescription beanDesc, boolean staticTyping) throws JsonMappingException {
        JsonSerializer<Object> ser = null;
        SerializationConfig config = prov.getConfig();
        if (type.isContainerType()) {
            if (!staticTyping) {
                staticTyping = this.usesStaticTyping(config, beanDesc, null);
            }
            if ((ser = this.buildContainerSerializer(prov, type, beanDesc, staticTyping)) != null) {
                return ser;
            }
        } else {
            if (type.isReferenceType()) {
                ser = this.findReferenceSerializer(prov, (ReferenceType)type, beanDesc, staticTyping);
            } else {
                Serializers serializers;
                Iterator<Object> i$ = this.customSerializers().iterator();
                while (i$.hasNext() && (ser = (serializers = i$.next()).findSerializer(config, type, beanDesc)) == null) {
                }
            }
            if (ser == null) {
                ser = this.findSerializerByAnnotations(prov, type, beanDesc);
            }
        }
        if (ser == null && (ser = this.findSerializerByLookup(type, config, beanDesc, staticTyping)) == null && (ser = this.findSerializerByPrimaryType(prov, type, beanDesc, staticTyping)) == null && (ser = this.findBeanSerializer(prov, type, beanDesc)) == null && (ser = this.findSerializerByAddonType(config, type, beanDesc, staticTyping)) == null) {
            ser = prov.getUnknownTypeSerializer(beanDesc.getBeanClass());
        }
        if (ser != null && this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers()) {
                ser = mod.modifySerializer(config, beanDesc, ser);
            }
        }
        return ser;
    }

    public JsonSerializer<Object> findBeanSerializer(SerializerProvider prov, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        if (!this.isPotentialBeanType(type.getRawClass()) && !type.isEnumType()) {
            return null;
        }
        return this.constructBeanSerializer(prov, beanDesc);
    }

    public TypeSerializer findPropertyTypeSerializer(JavaType baseType, SerializationConfig config, AnnotatedMember accessor) throws JsonMappingException {
        TypeSerializer typeSer;
        AnnotationIntrospector ai2 = config.getAnnotationIntrospector();
        TypeResolverBuilder<?> b10 = ai2.findPropertyTypeResolver(config, accessor, baseType);
        if (b10 == null) {
            typeSer = this.createTypeSerializer(config, baseType);
        } else {
            Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByClass(config, accessor, baseType);
            typeSer = b10.buildTypeSerializer(config, baseType, subtypes);
        }
        return typeSer;
    }

    public TypeSerializer findPropertyContentTypeSerializer(JavaType containerType, SerializationConfig config, AnnotatedMember accessor) throws JsonMappingException {
        TypeSerializer typeSer;
        JavaType contentType = containerType.getContentType();
        AnnotationIntrospector ai2 = config.getAnnotationIntrospector();
        TypeResolverBuilder<?> b10 = ai2.findPropertyContentTypeResolver(config, accessor, containerType);
        if (b10 == null) {
            typeSer = this.createTypeSerializer(config, contentType);
        } else {
            Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByClass(config, accessor, contentType);
            typeSer = b10.buildTypeSerializer(config, contentType, subtypes);
        }
        return typeSer;
    }

    protected JsonSerializer<Object> constructBeanSerializer(SerializerProvider prov, BeanDescription beanDesc) throws JsonMappingException {
        if (beanDesc.getBeanClass() == Object.class) {
            return prov.getUnknownTypeSerializer(Object.class);
        }
        SerializationConfig config = prov.getConfig();
        BeanSerializerBuilder builder = this.constructBeanSerializerBuilder(beanDesc);
        builder.setConfig(config);
        List<BeanPropertyWriter> props = this.findBeanProperties(prov, beanDesc, builder);
        props = props == null ? new ArrayList<BeanPropertyWriter>() : this.removeOverlappingTypeIds(prov, beanDesc, builder, props);
        prov.getAnnotationIntrospector().findAndAddVirtualProperties(config, beanDesc.getClassInfo(), props);
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers()) {
                props = mod.changeProperties(config, beanDesc, props);
            }
        }
        props = this.filterBeanProperties(config, beanDesc, props);
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers()) {
                props = mod.orderProperties(config, beanDesc, props);
            }
        }
        builder.setObjectIdWriter(this.constructObjectIdHandler(prov, beanDesc, props));
        builder.setProperties(props);
        builder.setFilterId(this.findFilterId(config, beanDesc));
        AnnotatedMember anyGetter = beanDesc.findAnyGetter();
        if (anyGetter != null) {
            JavaType type = anyGetter.getType();
            boolean staticTyping = config.isEnabled(MapperFeature.USE_STATIC_TYPING);
            JavaType valueType = type.getContentType();
            TypeSerializer typeSer = this.createTypeSerializer(config, valueType);
            MapSerializer anySer = this.findSerializerFromAnnotation(prov, anyGetter);
            if (anySer == null) {
                anySer = MapSerializer.construct((Set<String>)null, type, staticTyping, typeSer, null, null, null);
            }
            PropertyName name = PropertyName.construct(anyGetter.getName());
            BeanProperty.Std anyProp = new BeanProperty.Std(name, valueType, null, anyGetter, PropertyMetadata.STD_OPTIONAL);
            builder.setAnyGetter(new AnyGetterWriter(anyProp, anyGetter, anySer));
        }
        this.processViews(config, builder);
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier mod : this._factoryConfig.serializerModifiers()) {
                builder = mod.updateBuilder(config, beanDesc, builder);
            }
        }
        JsonSerializer<Object> ser = null;
        try {
            ser = builder.build();
        }
        catch (RuntimeException e10) {
            prov.reportBadTypeDefinition(beanDesc, "Failed to construct BeanSerializer for %s: (%s) %s", beanDesc.getType(), e10.getClass().getName(), e10.getMessage());
        }
        if (ser == null && beanDesc.hasKnownClassAnnotations()) {
            return builder.createDummy();
        }
        return ser;
    }

    protected ObjectIdWriter constructObjectIdHandler(SerializerProvider prov, BeanDescription beanDesc, List<BeanPropertyWriter> props) throws JsonMappingException {
        ObjectIdInfo objectIdInfo = beanDesc.getObjectIdInfo();
        if (objectIdInfo == null) {
            return null;
        }
        Class<? extends ObjectIdGenerator<?>> implClass = objectIdInfo.getGeneratorType();
        if (implClass == ObjectIdGenerators.PropertyGenerator.class) {
            String propName = objectIdInfo.getPropertyName().getSimpleName();
            BeanPropertyWriter idProp = null;
            int i2 = 0;
            int len = props.size();
            while (true) {
                if (i2 == len) {
                    throw new IllegalArgumentException("Invalid Object Id definition for " + beanDesc.getBeanClass().getName() + ": cannot find property with name '" + propName + "'");
                }
                BeanPropertyWriter prop = props.get(i2);
                if (propName.equals(prop.getName())) {
                    idProp = prop;
                    if (i2 <= 0) break;
                    props.remove(i2);
                    props.add(0, idProp);
                    break;
                }
                ++i2;
            }
            JavaType idType = idProp.getType();
            PropertyBasedObjectIdGenerator gen = new PropertyBasedObjectIdGenerator(objectIdInfo, idProp);
            return ObjectIdWriter.construct(idType, null, gen, objectIdInfo.getAlwaysAsId());
        }
        JavaType type = prov.constructType(implClass);
        JavaType idType = prov.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
        ObjectIdGenerator<?> gen = prov.objectIdGeneratorInstance(beanDesc.getClassInfo(), objectIdInfo);
        return ObjectIdWriter.construct(idType, objectIdInfo.getPropertyName(), gen, objectIdInfo.getAlwaysAsId());
    }

    protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter writer, Class<?>[] inViews) {
        return FilteredBeanPropertyWriter.constructViewBased(writer, inViews);
    }

    protected PropertyBuilder constructPropertyBuilder(SerializationConfig config, BeanDescription beanDesc) {
        return new PropertyBuilder(config, beanDesc);
    }

    protected BeanSerializerBuilder constructBeanSerializerBuilder(BeanDescription beanDesc) {
        return new BeanSerializerBuilder(beanDesc);
    }

    protected boolean isPotentialBeanType(Class<?> type) {
        return ClassUtil.canBeABeanType(type) == null && !ClassUtil.isProxyType(type);
    }

    protected List<BeanPropertyWriter> findBeanProperties(SerializerProvider prov, BeanDescription beanDesc, BeanSerializerBuilder builder) throws JsonMappingException {
        List<BeanPropertyDefinition> properties = beanDesc.findProperties();
        SerializationConfig config = prov.getConfig();
        this.removeIgnorableTypes(config, beanDesc, properties);
        if (config.isEnabled(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS)) {
            this.removeSetterlessGetters(config, beanDesc, properties);
        }
        if (properties.isEmpty()) {
            return null;
        }
        boolean staticTyping = this.usesStaticTyping(config, beanDesc, null);
        PropertyBuilder pb = this.constructPropertyBuilder(config, beanDesc);
        ArrayList<BeanPropertyWriter> result = new ArrayList<BeanPropertyWriter>(properties.size());
        for (BeanPropertyDefinition property : properties) {
            AnnotatedMember accessor = property.getAccessor();
            if (property.isTypeId()) {
                if (accessor == null) continue;
                builder.setTypeId(accessor);
                continue;
            }
            AnnotationIntrospector.ReferenceProperty refType = property.findReferenceType();
            if (refType != null && refType.isBackReference()) continue;
            if (accessor instanceof AnnotatedMethod) {
                result.add(this._constructWriter(prov, property, pb, staticTyping, (AnnotatedMethod)accessor));
                continue;
            }
            result.add(this._constructWriter(prov, property, pb, staticTyping, (AnnotatedField)accessor));
        }
        return result;
    }

    protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> props) {
        Set<String> ignored;
        JsonIgnoreProperties.Value ignorals = config.getDefaultPropertyIgnorals(beanDesc.getBeanClass(), beanDesc.getClassInfo());
        if (ignorals != null && !(ignored = ignorals.findIgnoredForSerialization()).isEmpty()) {
            Iterator<BeanPropertyWriter> it = props.iterator();
            while (it.hasNext()) {
                if (!ignored.contains(it.next().getName())) continue;
                it.remove();
            }
        }
        return props;
    }

    protected void processViews(SerializationConfig config, BeanSerializerBuilder builder) {
        List<BeanPropertyWriter> props = builder.getProperties();
        boolean includeByDefault = config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
        int propCount = props.size();
        int viewsFound = 0;
        BeanPropertyWriter[] filtered = new BeanPropertyWriter[propCount];
        for (int i2 = 0; i2 < propCount; ++i2) {
            BeanPropertyWriter bpw = props.get(i2);
            Class<?>[] views = bpw.getViews();
            if (views == null) {
                if (!includeByDefault) continue;
                filtered[i2] = bpw;
                continue;
            }
            ++viewsFound;
            filtered[i2] = this.constructFilteredBeanWriter(bpw, views);
        }
        if (includeByDefault && viewsFound == 0) {
            return;
        }
        builder.setFilteredProperties(filtered);
    }

    protected void removeIgnorableTypes(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyDefinition> properties) {
        AnnotationIntrospector intr = config.getAnnotationIntrospector();
        HashMap ignores = new HashMap();
        Iterator<BeanPropertyDefinition> it = properties.iterator();
        while (it.hasNext()) {
            BeanPropertyDefinition property = it.next();
            AnnotatedMember accessor = property.getAccessor();
            if (accessor == null) {
                it.remove();
                continue;
            }
            Class<?> type = property.getRawPrimaryType();
            Boolean result = (Boolean)ignores.get(type);
            if (result == null) {
                BeanDescription desc;
                AnnotatedClass ac2;
                result = config.getConfigOverride(type).getIsIgnoredType();
                if (result == null && (result = intr.isIgnorableType(ac2 = (desc = config.introspectClassAnnotations(type)).getClassInfo())) == null) {
                    result = Boolean.FALSE;
                }
                ignores.put(type, result);
            }
            if (!result.booleanValue()) continue;
            it.remove();
        }
    }

    protected void removeSetterlessGetters(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyDefinition> properties) {
        Iterator<BeanPropertyDefinition> it = properties.iterator();
        while (it.hasNext()) {
            BeanPropertyDefinition property = it.next();
            if (property.couldDeserialize() || property.isExplicitlyIncluded()) continue;
            it.remove();
        }
    }

    protected List<BeanPropertyWriter> removeOverlappingTypeIds(SerializerProvider prov, BeanDescription beanDesc, BeanSerializerBuilder builder, List<BeanPropertyWriter> props) {
        int end = props.size();
        block0: for (int i2 = 0; i2 < end; ++i2) {
            BeanPropertyWriter bpw = props.get(i2);
            TypeSerializer td = bpw.getTypeSerializer();
            if (td == null || td.getTypeInclusion() != JsonTypeInfo.As.EXTERNAL_PROPERTY) continue;
            String n2 = td.getPropertyName();
            PropertyName typePropName = PropertyName.construct(n2);
            for (BeanPropertyWriter w2 : props) {
                if (w2 == bpw || !w2.wouldConflictWithName(typePropName)) continue;
                bpw.assignTypeSerializer(null);
                continue block0;
            }
        }
        return props;
    }

    protected BeanPropertyWriter _constructWriter(SerializerProvider prov, BeanPropertyDefinition propDef, PropertyBuilder pb, boolean staticTyping, AnnotatedMember accessor) throws JsonMappingException {
        PropertyName name = propDef.getFullName();
        JavaType type = accessor.getType();
        BeanProperty.Std property = new BeanProperty.Std(name, type, propDef.getWrapperName(), accessor, propDef.getMetadata());
        JsonSerializer<Object> annotatedSerializer = this.findSerializerFromAnnotation(prov, accessor);
        if (annotatedSerializer instanceof ResolvableSerializer) {
            ((ResolvableSerializer)((Object)annotatedSerializer)).resolve(prov);
        }
        annotatedSerializer = prov.handlePrimaryContextualization(annotatedSerializer, property);
        TypeSerializer contentTypeSer = null;
        if (type.isContainerType() || type.isReferenceType()) {
            contentTypeSer = this.findPropertyContentTypeSerializer(type, prov.getConfig(), accessor);
        }
        TypeSerializer typeSer = this.findPropertyTypeSerializer(type, prov.getConfig(), accessor);
        return pb.buildWriter(prov, propDef, type, annotatedSerializer, typeSer, contentTypeSer, accessor, staticTyping);
    }
}

