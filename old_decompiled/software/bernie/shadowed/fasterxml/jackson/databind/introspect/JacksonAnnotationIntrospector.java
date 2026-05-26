/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import software.bernie.shadowed.fasterxml.jackson.annotation.JacksonInject;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonAlias;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonAnyGetter;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonAnySetter;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonAutoDetect;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonBackReference;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonClassDescription;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonCreator;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFilter;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonGetter;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIdentityInfo;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIdentityReference;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnore;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreProperties;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreType;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonManagedReference;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonMerge;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonPropertyDescription;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonPropertyOrder;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonRawValue;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonRootName;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonSetter;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonSubTypes;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeId;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeName;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonUnwrapped;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonValue;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonView;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerators;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.KeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonAppend;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonDeserialize;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonNaming;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonSerialize;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.PackageVersion;
import software.bernie.shadowed.fasterxml.jackson.databind.ext.Java7Support;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.Annotated;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClass;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.VirtualAnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.VisibilityChecker;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.NamedType;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.AttributePropertyWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.RawSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.type.MapLikeType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Converter;
import software.bernie.shadowed.fasterxml.jackson.databind.util.LRUMap;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;

public class JacksonAnnotationIntrospector
extends AnnotationIntrospector
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_SER = new Class[]{JsonSerialize.class, JsonView.class, JsonFormat.class, JsonTypeInfo.class, JsonRawValue.class, JsonUnwrapped.class, JsonBackReference.class, JsonManagedReference.class};
    private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_DESER = new Class[]{JsonDeserialize.class, JsonView.class, JsonFormat.class, JsonTypeInfo.class, JsonUnwrapped.class, JsonBackReference.class, JsonManagedReference.class, JsonMerge.class};
    private static final Java7Support _java7Helper;
    protected transient LRUMap<Class<?>, Boolean> _annotationsInside = new LRUMap(48, 48);
    protected boolean _cfgConstructorPropertiesImpliesCreator = true;

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    protected Object readResolve() {
        if (this._annotationsInside == null) {
            this._annotationsInside = new LRUMap(48, 48);
        }
        return this;
    }

    public JacksonAnnotationIntrospector setConstructorPropertiesImpliesCreator(boolean b10) {
        this._cfgConstructorPropertiesImpliesCreator = b10;
        return this;
    }

    @Override
    public boolean isAnnotationBundle(Annotation ann) {
        Class<? extends Annotation> type = ann.annotationType();
        Boolean b10 = this._annotationsInside.get(type);
        if (b10 == null) {
            b10 = type.getAnnotation(JacksonAnnotationsInside.class) != null;
            this._annotationsInside.putIfAbsent(type, b10);
        }
        return b10;
    }

    @Override
    @Deprecated
    public String findEnumValue(Enum<?> value) {
        try {
            String n2;
            JsonProperty prop;
            Field f10 = value.getClass().getField(value.name());
            if (f10 != null && (prop = f10.getAnnotation(JsonProperty.class)) != null && (n2 = prop.value()) != null && !n2.isEmpty()) {
                return n2;
            }
        }
        catch (SecurityException e10) {
        }
        catch (NoSuchFieldException noSuchFieldException) {
            // empty catch block
        }
        return value.name();
    }

    @Override
    public String[] findEnumValues(Class<?> enumType, Enum<?>[] enumValues, String[] names) {
        HashMap<String, String> expl = null;
        for (Field f10 : ClassUtil.getDeclaredFields(enumType)) {
            String n2;
            JsonProperty prop;
            if (!f10.isEnumConstant() || (prop = f10.getAnnotation(JsonProperty.class)) == null || (n2 = prop.value()).isEmpty()) continue;
            if (expl == null) {
                expl = new HashMap<String, String>();
            }
            expl.put(f10.getName(), n2);
        }
        if (expl != null) {
            int end = enumValues.length;
            for (int i2 = 0; i2 < end; ++i2) {
                String defName = enumValues[i2].name();
                String explValue = (String)expl.get(defName);
                if (explValue == null) continue;
                names[i2] = explValue;
            }
        }
        return names;
    }

    @Override
    public Enum<?> findDefaultEnumValue(Class<Enum<?>> enumCls) {
        return ClassUtil.findFirstAnnotatedEnumValue(enumCls, JsonEnumDefaultValue.class);
    }

    @Override
    public PropertyName findRootName(AnnotatedClass ac2) {
        JsonRootName ann = this._findAnnotation(ac2, JsonRootName.class);
        if (ann == null) {
            return null;
        }
        String ns = ann.namespace();
        if (ns != null && ns.length() == 0) {
            ns = null;
        }
        return PropertyName.construct(ann.value(), ns);
    }

    @Override
    public JsonIgnoreProperties.Value findPropertyIgnorals(Annotated a10) {
        JsonIgnoreProperties v2 = this._findAnnotation(a10, JsonIgnoreProperties.class);
        if (v2 == null) {
            return JsonIgnoreProperties.Value.empty();
        }
        return JsonIgnoreProperties.Value.from(v2);
    }

    @Override
    public Boolean isIgnorableType(AnnotatedClass ac2) {
        JsonIgnoreType ignore = this._findAnnotation(ac2, JsonIgnoreType.class);
        return ignore == null ? null : Boolean.valueOf(ignore.value());
    }

    @Override
    public Object findFilterId(Annotated a10) {
        String id;
        JsonFilter ann = this._findAnnotation(a10, JsonFilter.class);
        if (ann != null && (id = ann.value()).length() > 0) {
            return id;
        }
        return null;
    }

    @Override
    public Object findNamingStrategy(AnnotatedClass ac2) {
        JsonNaming ann = this._findAnnotation(ac2, JsonNaming.class);
        return ann == null ? null : ann.value();
    }

    @Override
    public String findClassDescription(AnnotatedClass ac2) {
        JsonClassDescription ann = this._findAnnotation(ac2, JsonClassDescription.class);
        return ann == null ? null : ann.value();
    }

    @Override
    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass ac2, VisibilityChecker<?> checker) {
        JsonAutoDetect ann = this._findAnnotation(ac2, JsonAutoDetect.class);
        return ann == null ? checker : checker.with(ann);
    }

    @Override
    public String findImplicitPropertyName(AnnotatedMember m2) {
        PropertyName n2 = this._findConstructorName(m2);
        return n2 == null ? null : n2.getSimpleName();
    }

    @Override
    public List<PropertyName> findPropertyAliases(Annotated m2) {
        JsonAlias ann = this._findAnnotation(m2, JsonAlias.class);
        if (ann == null) {
            return null;
        }
        String[] strs = ann.value();
        int len = strs.length;
        if (len == 0) {
            return Collections.emptyList();
        }
        ArrayList<PropertyName> result = new ArrayList<PropertyName>(len);
        for (int i2 = 0; i2 < len; ++i2) {
            result.add(PropertyName.construct(strs[i2]));
        }
        return result;
    }

    @Override
    public boolean hasIgnoreMarker(AnnotatedMember m2) {
        return this._isIgnorable(m2);
    }

    @Override
    public Boolean hasRequiredMarker(AnnotatedMember m2) {
        JsonProperty ann = this._findAnnotation(m2, JsonProperty.class);
        if (ann != null) {
            return ann.required();
        }
        return null;
    }

    @Override
    public JsonProperty.Access findPropertyAccess(Annotated m2) {
        JsonProperty ann = this._findAnnotation(m2, JsonProperty.class);
        if (ann != null) {
            return ann.access();
        }
        return null;
    }

    @Override
    public String findPropertyDescription(Annotated ann) {
        JsonPropertyDescription desc = this._findAnnotation(ann, JsonPropertyDescription.class);
        return desc == null ? null : desc.value();
    }

    @Override
    public Integer findPropertyIndex(Annotated ann) {
        int ix;
        JsonProperty prop = this._findAnnotation(ann, JsonProperty.class);
        if (prop != null && (ix = prop.index()) != -1) {
            return ix;
        }
        return null;
    }

    @Override
    public String findPropertyDefaultValue(Annotated ann) {
        JsonProperty prop = this._findAnnotation(ann, JsonProperty.class);
        if (prop == null) {
            return null;
        }
        String str = prop.defaultValue();
        return str.isEmpty() ? null : str;
    }

    @Override
    public JsonFormat.Value findFormat(Annotated ann) {
        JsonFormat f10 = this._findAnnotation(ann, JsonFormat.class);
        return f10 == null ? null : new JsonFormat.Value(f10);
    }

    @Override
    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember member) {
        JsonManagedReference ref1 = this._findAnnotation(member, JsonManagedReference.class);
        if (ref1 != null) {
            return AnnotationIntrospector.ReferenceProperty.managed(ref1.value());
        }
        JsonBackReference ref2 = this._findAnnotation(member, JsonBackReference.class);
        if (ref2 != null) {
            return AnnotationIntrospector.ReferenceProperty.back(ref2.value());
        }
        return null;
    }

    @Override
    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember member) {
        JsonUnwrapped ann = this._findAnnotation(member, JsonUnwrapped.class);
        if (ann == null || !ann.enabled()) {
            return null;
        }
        String prefix = ann.prefix();
        String suffix = ann.suffix();
        return NameTransformer.simpleTransformer(prefix, suffix);
    }

    @Override
    public JacksonInject.Value findInjectableValue(AnnotatedMember m2) {
        JacksonInject ann = this._findAnnotation(m2, JacksonInject.class);
        if (ann == null) {
            return null;
        }
        JacksonInject.Value v2 = JacksonInject.Value.from(ann);
        if (!v2.hasId()) {
            AnnotatedMethod am2;
            String id = !(m2 instanceof AnnotatedMethod) ? m2.getRawType().getName() : ((am2 = (AnnotatedMethod)m2).getParameterCount() == 0 ? m2.getRawType().getName() : am2.getRawParameterType(0).getName());
            v2 = v2.withId(id);
        }
        return v2;
    }

    @Override
    @Deprecated
    public Object findInjectableValueId(AnnotatedMember m2) {
        JacksonInject.Value v2 = this.findInjectableValue(m2);
        return v2 == null ? null : v2.getId();
    }

    @Override
    public Class<?>[] findViews(Annotated a10) {
        JsonView ann = this._findAnnotation(a10, JsonView.class);
        return ann == null ? null : ann.value();
    }

    @Override
    public AnnotatedMethod resolveSetterConflict(MapperConfig<?> config, AnnotatedMethod setter1, AnnotatedMethod setter2) {
        Class<?> cls1 = setter1.getRawParameterType(0);
        Class<?> cls2 = setter2.getRawParameterType(0);
        if (cls1.isPrimitive()) {
            if (!cls2.isPrimitive()) {
                return setter1;
            }
        } else if (cls2.isPrimitive()) {
            return setter2;
        }
        if (cls1 == String.class) {
            if (cls2 != String.class) {
                return setter1;
            }
        } else if (cls2 == String.class) {
            return setter2;
        }
        return null;
    }

    @Override
    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> config, AnnotatedClass ac2, JavaType baseType) {
        return this._findTypeResolver(config, ac2, baseType);
    }

    @Override
    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> config, AnnotatedMember am2, JavaType baseType) {
        if (baseType.isContainerType() || baseType.isReferenceType()) {
            return null;
        }
        return this._findTypeResolver(config, am2, baseType);
    }

    @Override
    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> config, AnnotatedMember am2, JavaType containerType) {
        if (containerType.getContentType() == null) {
            throw new IllegalArgumentException("Must call method with a container or reference type (got " + containerType + ")");
        }
        return this._findTypeResolver(config, am2, containerType);
    }

    @Override
    public List<NamedType> findSubtypes(Annotated a10) {
        JsonSubTypes t2 = this._findAnnotation(a10, JsonSubTypes.class);
        if (t2 == null) {
            return null;
        }
        JsonSubTypes.Type[] types = t2.value();
        ArrayList<NamedType> result = new ArrayList<NamedType>(types.length);
        for (JsonSubTypes.Type type : types) {
            result.add(new NamedType(type.value(), type.name()));
        }
        return result;
    }

    @Override
    public String findTypeName(AnnotatedClass ac2) {
        JsonTypeName tn = this._findAnnotation(ac2, JsonTypeName.class);
        return tn == null ? null : tn.value();
    }

    @Override
    public Boolean isTypeId(AnnotatedMember member) {
        return this._hasAnnotation(member, JsonTypeId.class);
    }

    @Override
    public ObjectIdInfo findObjectIdInfo(Annotated ann) {
        JsonIdentityInfo info = this._findAnnotation(ann, JsonIdentityInfo.class);
        if (info == null || info.generator() == ObjectIdGenerators.None.class) {
            return null;
        }
        PropertyName name = PropertyName.construct(info.property());
        return new ObjectIdInfo(name, info.scope(), info.generator(), info.resolver());
    }

    @Override
    public ObjectIdInfo findObjectReferenceInfo(Annotated ann, ObjectIdInfo objectIdInfo) {
        JsonIdentityReference ref = this._findAnnotation(ann, JsonIdentityReference.class);
        if (ref == null) {
            return objectIdInfo;
        }
        if (objectIdInfo == null) {
            objectIdInfo = ObjectIdInfo.empty();
        }
        return objectIdInfo.withAlwaysAsId(ref.alwaysAsId());
    }

    @Override
    public Object findSerializer(Annotated a10) {
        Class<? extends JsonSerializer> serClass;
        JsonSerialize ann = this._findAnnotation(a10, JsonSerialize.class);
        if (ann != null && (serClass = ann.using()) != JsonSerializer.None.class) {
            return serClass;
        }
        JsonRawValue annRaw = this._findAnnotation(a10, JsonRawValue.class);
        if (annRaw != null && annRaw.value()) {
            Class<?> cls = a10.getRawType();
            return new RawSerializer(cls);
        }
        return null;
    }

    @Override
    public Object findKeySerializer(Annotated a10) {
        Class<? extends JsonSerializer> serClass;
        JsonSerialize ann = this._findAnnotation(a10, JsonSerialize.class);
        if (ann != null && (serClass = ann.keyUsing()) != JsonSerializer.None.class) {
            return serClass;
        }
        return null;
    }

    @Override
    public Object findContentSerializer(Annotated a10) {
        Class<? extends JsonSerializer> serClass;
        JsonSerialize ann = this._findAnnotation(a10, JsonSerialize.class);
        if (ann != null && (serClass = ann.contentUsing()) != JsonSerializer.None.class) {
            return serClass;
        }
        return null;
    }

    @Override
    public Object findNullSerializer(Annotated a10) {
        Class<? extends JsonSerializer> serClass;
        JsonSerialize ann = this._findAnnotation(a10, JsonSerialize.class);
        if (ann != null && (serClass = ann.nullsUsing()) != JsonSerializer.None.class) {
            return serClass;
        }
        return null;
    }

    @Override
    public JsonInclude.Value findPropertyInclusion(Annotated a10) {
        JsonInclude.Value value;
        JsonInclude inc = this._findAnnotation(a10, JsonInclude.class);
        JsonInclude.Value value2 = value = inc == null ? JsonInclude.Value.empty() : JsonInclude.Value.from(inc);
        if (value.getValueInclusion() == JsonInclude.Include.USE_DEFAULTS) {
            value = this._refinePropertyInclusion(a10, value);
        }
        return value;
    }

    private JsonInclude.Value _refinePropertyInclusion(Annotated a10, JsonInclude.Value value) {
        JsonSerialize ann = this._findAnnotation(a10, JsonSerialize.class);
        if (ann != null) {
            switch (ann.include()) {
                case ALWAYS: {
                    return value.withValueInclusion(JsonInclude.Include.ALWAYS);
                }
                case NON_NULL: {
                    return value.withValueInclusion(JsonInclude.Include.NON_NULL);
                }
                case NON_DEFAULT: {
                    return value.withValueInclusion(JsonInclude.Include.NON_DEFAULT);
                }
                case NON_EMPTY: {
                    return value.withValueInclusion(JsonInclude.Include.NON_EMPTY);
                }
            }
        }
        return value;
    }

    @Override
    public JsonSerialize.Typing findSerializationTyping(Annotated a10) {
        JsonSerialize ann = this._findAnnotation(a10, JsonSerialize.class);
        return ann == null ? null : ann.typing();
    }

    @Override
    public Object findSerializationConverter(Annotated a10) {
        JsonSerialize ann = this._findAnnotation(a10, JsonSerialize.class);
        return ann == null ? null : this._classIfExplicit(ann.converter(), Converter.None.class);
    }

    @Override
    public Object findSerializationContentConverter(AnnotatedMember a10) {
        JsonSerialize ann = this._findAnnotation(a10, JsonSerialize.class);
        return ann == null ? null : this._classIfExplicit(ann.contentConverter(), Converter.None.class);
    }

    @Override
    public JavaType refineSerializationType(MapperConfig<?> config, Annotated a10, JavaType baseType) throws JsonMappingException {
        JavaType contentType;
        Class<?> currRaw;
        JsonSerialize jsonSer;
        TypeFactory tf;
        JavaType type;
        block26: {
            Class<?> serClass;
            type = baseType;
            tf = config.getTypeFactory();
            jsonSer = this._findAnnotation(a10, JsonSerialize.class);
            Class<?> clazz = serClass = jsonSer == null ? null : this._classIfExplicit(jsonSer.as());
            if (serClass != null) {
                if (type.hasRawClass(serClass)) {
                    type = type.withStaticTyping();
                } else {
                    Class<?> currRaw2 = type.getRawClass();
                    try {
                        if (serClass.isAssignableFrom(currRaw2)) {
                            type = tf.constructGeneralizedType(type, serClass);
                            break block26;
                        }
                        if (currRaw2.isAssignableFrom(serClass)) {
                            type = tf.constructSpecializedType(type, serClass);
                            break block26;
                        }
                        if (this._primitiveAndWrapper(currRaw2, serClass)) {
                            type = type.withStaticTyping();
                            break block26;
                        }
                        throw new JsonMappingException(null, String.format("Cannot refine serialization type %s into %s; types not related", type, serClass.getName()));
                    }
                    catch (IllegalArgumentException iae) {
                        throw new JsonMappingException(null, String.format("Failed to widen type %s with annotation (value %s), from '%s': %s", type, serClass.getName(), a10.getName(), iae.getMessage()), (Throwable)iae);
                    }
                }
            }
        }
        if (type.isMapLikeType()) {
            Class<?> keyClass;
            JavaType keyType = type.getKeyType();
            Class<?> clazz = keyClass = jsonSer == null ? null : this._classIfExplicit(jsonSer.keyAs());
            if (keyClass != null) {
                block27: {
                    if (keyType.hasRawClass(keyClass)) {
                        keyType = keyType.withStaticTyping();
                    } else {
                        currRaw = keyType.getRawClass();
                        try {
                            if (keyClass.isAssignableFrom(currRaw)) {
                                keyType = tf.constructGeneralizedType(keyType, keyClass);
                                break block27;
                            }
                            if (currRaw.isAssignableFrom(keyClass)) {
                                keyType = tf.constructSpecializedType(keyType, keyClass);
                                break block27;
                            }
                            if (this._primitiveAndWrapper(currRaw, keyClass)) {
                                keyType = keyType.withStaticTyping();
                                break block27;
                            }
                            throw new JsonMappingException(null, String.format("Cannot refine serialization key type %s into %s; types not related", keyType, keyClass.getName()));
                        }
                        catch (IllegalArgumentException iae) {
                            throw new JsonMappingException(null, String.format("Failed to widen key type of %s with concrete-type annotation (value %s), from '%s': %s", type, keyClass.getName(), a10.getName(), iae.getMessage()), (Throwable)iae);
                        }
                    }
                }
                type = ((MapLikeType)type).withKeyType(keyType);
            }
        }
        if ((contentType = type.getContentType()) != null) {
            Class<?> contentClass;
            Class<?> clazz = contentClass = jsonSer == null ? null : this._classIfExplicit(jsonSer.contentAs());
            if (contentClass != null) {
                block28: {
                    if (contentType.hasRawClass(contentClass)) {
                        contentType = contentType.withStaticTyping();
                    } else {
                        currRaw = contentType.getRawClass();
                        try {
                            if (contentClass.isAssignableFrom(currRaw)) {
                                contentType = tf.constructGeneralizedType(contentType, contentClass);
                                break block28;
                            }
                            if (currRaw.isAssignableFrom(contentClass)) {
                                contentType = tf.constructSpecializedType(contentType, contentClass);
                                break block28;
                            }
                            if (this._primitiveAndWrapper(currRaw, contentClass)) {
                                contentType = contentType.withStaticTyping();
                                break block28;
                            }
                            throw new JsonMappingException(null, String.format("Cannot refine serialization content type %s into %s; types not related", contentType, contentClass.getName()));
                        }
                        catch (IllegalArgumentException iae) {
                            throw new JsonMappingException(null, String.format("Internal error: failed to refine value type of %s with concrete-type annotation (value %s), from '%s': %s", type, contentClass.getName(), a10.getName(), iae.getMessage()), (Throwable)iae);
                        }
                    }
                }
                type = type.withContentType(contentType);
            }
        }
        return type;
    }

    @Override
    @Deprecated
    public Class<?> findSerializationType(Annotated am2) {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> findSerializationKeyType(Annotated am2, JavaType baseType) {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> findSerializationContentType(Annotated am2, JavaType baseType) {
        return null;
    }

    @Override
    public String[] findSerializationPropertyOrder(AnnotatedClass ac2) {
        JsonPropertyOrder order = this._findAnnotation(ac2, JsonPropertyOrder.class);
        return order == null ? null : order.value();
    }

    @Override
    public Boolean findSerializationSortAlphabetically(Annotated ann) {
        return this._findSortAlpha(ann);
    }

    private final Boolean _findSortAlpha(Annotated ann) {
        JsonPropertyOrder order = this._findAnnotation(ann, JsonPropertyOrder.class);
        if (order != null && order.alphabetic()) {
            return Boolean.TRUE;
        }
        return null;
    }

    @Override
    public void findAndAddVirtualProperties(MapperConfig<?> config, AnnotatedClass ac2, List<BeanPropertyWriter> properties) {
        JsonAppend ann = this._findAnnotation(ac2, JsonAppend.class);
        if (ann == null) {
            return;
        }
        boolean prepend = ann.prepend();
        JavaType propType = null;
        JsonAppend.Attr[] attrs = ann.attrs();
        int len = attrs.length;
        for (int i2 = 0; i2 < len; ++i2) {
            if (propType == null) {
                propType = config.constructType(Object.class);
            }
            BeanPropertyWriter bpw = this._constructVirtualProperty(attrs[i2], config, ac2, propType);
            if (prepend) {
                properties.add(i2, bpw);
                continue;
            }
            properties.add(bpw);
        }
        JsonAppend.Prop[] props = ann.props();
        int len2 = props.length;
        for (int i3 = 0; i3 < len2; ++i3) {
            BeanPropertyWriter bpw = this._constructVirtualProperty(props[i3], config, ac2);
            if (prepend) {
                properties.add(i3, bpw);
                continue;
            }
            properties.add(bpw);
        }
    }

    protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Attr attr, MapperConfig<?> config, AnnotatedClass ac2, JavaType type) {
        PropertyMetadata metadata = attr.required() ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
        String attrName = attr.value();
        PropertyName propName = this._propertyName(attr.propName(), attr.propNamespace());
        if (!propName.hasSimpleName()) {
            propName = PropertyName.construct(attrName);
        }
        VirtualAnnotatedMember member = new VirtualAnnotatedMember(ac2, ac2.getRawType(), attrName, type);
        SimpleBeanPropertyDefinition propDef = SimpleBeanPropertyDefinition.construct(config, (AnnotatedMember)member, propName, metadata, attr.include());
        return AttributePropertyWriter.construct(attrName, propDef, ac2.getAnnotations(), type);
    }

    protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Prop prop, MapperConfig<?> config, AnnotatedClass ac2) {
        VirtualBeanPropertyWriter bpw;
        PropertyMetadata metadata = prop.required() ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
        PropertyName propName = this._propertyName(prop.name(), prop.namespace());
        JavaType type = config.constructType(prop.type());
        VirtualAnnotatedMember member = new VirtualAnnotatedMember(ac2, ac2.getRawType(), propName.getSimpleName(), type);
        SimpleBeanPropertyDefinition propDef = SimpleBeanPropertyDefinition.construct(config, (AnnotatedMember)member, propName, metadata, prop.include());
        Class<? extends VirtualBeanPropertyWriter> implClass = prop.value();
        HandlerInstantiator hi = config.getHandlerInstantiator();
        VirtualBeanPropertyWriter virtualBeanPropertyWriter = bpw = hi == null ? null : hi.virtualPropertyWriterInstance(config, implClass);
        if (bpw == null) {
            bpw = ClassUtil.createInstance(implClass, config.canOverrideAccessModifiers());
        }
        return bpw.withConfig(config, ac2, propDef, type);
    }

    @Override
    public PropertyName findNameForSerialization(Annotated a10) {
        JsonGetter jg = this._findAnnotation(a10, JsonGetter.class);
        if (jg != null) {
            return PropertyName.construct(jg.value());
        }
        JsonProperty pann = this._findAnnotation(a10, JsonProperty.class);
        if (pann != null) {
            return PropertyName.construct(pann.value());
        }
        if (this._hasOneOf(a10, ANNOTATIONS_TO_INFER_SER)) {
            return PropertyName.USE_DEFAULT;
        }
        return null;
    }

    @Override
    public Boolean hasAsValue(Annotated a10) {
        JsonValue ann = this._findAnnotation(a10, JsonValue.class);
        if (ann == null) {
            return null;
        }
        return ann.value();
    }

    @Override
    public Boolean hasAnyGetter(Annotated a10) {
        JsonAnyGetter ann = this._findAnnotation(a10, JsonAnyGetter.class);
        if (ann == null) {
            return null;
        }
        return ann.enabled();
    }

    @Override
    @Deprecated
    public boolean hasAnyGetterAnnotation(AnnotatedMethod am2) {
        return this._hasAnnotation(am2, JsonAnyGetter.class);
    }

    @Override
    @Deprecated
    public boolean hasAsValueAnnotation(AnnotatedMethod am2) {
        JsonValue ann = this._findAnnotation(am2, JsonValue.class);
        return ann != null && ann.value();
    }

    @Override
    public Object findDeserializer(Annotated a10) {
        Class<? extends JsonDeserializer> deserClass;
        JsonDeserialize ann = this._findAnnotation(a10, JsonDeserialize.class);
        if (ann != null && (deserClass = ann.using()) != JsonDeserializer.None.class) {
            return deserClass;
        }
        return null;
    }

    @Override
    public Object findKeyDeserializer(Annotated a10) {
        Class<? extends KeyDeserializer> deserClass;
        JsonDeserialize ann = this._findAnnotation(a10, JsonDeserialize.class);
        if (ann != null && (deserClass = ann.keyUsing()) != KeyDeserializer.None.class) {
            return deserClass;
        }
        return null;
    }

    @Override
    public Object findContentDeserializer(Annotated a10) {
        Class<? extends JsonDeserializer> deserClass;
        JsonDeserialize ann = this._findAnnotation(a10, JsonDeserialize.class);
        if (ann != null && (deserClass = ann.contentUsing()) != JsonDeserializer.None.class) {
            return deserClass;
        }
        return null;
    }

    @Override
    public Object findDeserializationConverter(Annotated a10) {
        JsonDeserialize ann = this._findAnnotation(a10, JsonDeserialize.class);
        return ann == null ? null : this._classIfExplicit(ann.converter(), Converter.None.class);
    }

    @Override
    public Object findDeserializationContentConverter(AnnotatedMember a10) {
        JsonDeserialize ann = this._findAnnotation(a10, JsonDeserialize.class);
        return ann == null ? null : this._classIfExplicit(ann.contentConverter(), Converter.None.class);
    }

    @Override
    public JavaType refineDeserializationType(MapperConfig<?> config, Annotated a10, JavaType baseType) throws JsonMappingException {
        JavaType contentType;
        Class<?> valueClass;
        JavaType type = baseType;
        TypeFactory tf = config.getTypeFactory();
        JsonDeserialize jsonDeser = this._findAnnotation(a10, JsonDeserialize.class);
        Class<?> clazz = valueClass = jsonDeser == null ? null : this._classIfExplicit(jsonDeser.as());
        if (valueClass != null && !type.hasRawClass(valueClass) && !this._primitiveAndWrapper(type, valueClass)) {
            try {
                type = tf.constructSpecializedType(type, valueClass);
            }
            catch (IllegalArgumentException iae) {
                throw new JsonMappingException(null, String.format("Failed to narrow type %s with annotation (value %s), from '%s': %s", type, valueClass.getName(), a10.getName(), iae.getMessage()), (Throwable)iae);
            }
        }
        if (type.isMapLikeType()) {
            Class<?> keyClass;
            JavaType keyType = type.getKeyType();
            Class<?> clazz2 = keyClass = jsonDeser == null ? null : this._classIfExplicit(jsonDeser.keyAs());
            if (keyClass != null && !this._primitiveAndWrapper(keyType, keyClass)) {
                try {
                    keyType = tf.constructSpecializedType(keyType, keyClass);
                    type = ((MapLikeType)type).withKeyType(keyType);
                }
                catch (IllegalArgumentException iae) {
                    throw new JsonMappingException(null, String.format("Failed to narrow key type of %s with concrete-type annotation (value %s), from '%s': %s", type, keyClass.getName(), a10.getName(), iae.getMessage()), (Throwable)iae);
                }
            }
        }
        if ((contentType = type.getContentType()) != null) {
            Class<?> contentClass;
            Class<?> clazz3 = contentClass = jsonDeser == null ? null : this._classIfExplicit(jsonDeser.contentAs());
            if (contentClass != null && !this._primitiveAndWrapper(contentType, contentClass)) {
                try {
                    contentType = tf.constructSpecializedType(contentType, contentClass);
                    type = type.withContentType(contentType);
                }
                catch (IllegalArgumentException iae) {
                    throw new JsonMappingException(null, String.format("Failed to narrow value type of %s with concrete-type annotation (value %s), from '%s': %s", type, contentClass.getName(), a10.getName(), iae.getMessage()), (Throwable)iae);
                }
            }
        }
        return type;
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationContentType(Annotated am2, JavaType baseContentType) {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationType(Annotated am2, JavaType baseType) {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationKeyType(Annotated am2, JavaType baseKeyType) {
        return null;
    }

    @Override
    public Object findValueInstantiator(AnnotatedClass ac2) {
        JsonValueInstantiator ann = this._findAnnotation(ac2, JsonValueInstantiator.class);
        return ann == null ? null : ann.value();
    }

    @Override
    public Class<?> findPOJOBuilder(AnnotatedClass ac2) {
        JsonDeserialize ann = this._findAnnotation(ac2, JsonDeserialize.class);
        return ann == null ? null : this._classIfExplicit(ann.builder());
    }

    @Override
    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass ac2) {
        JsonPOJOBuilder ann = this._findAnnotation(ac2, JsonPOJOBuilder.class);
        return ann == null ? null : new JsonPOJOBuilder.Value(ann);
    }

    @Override
    public PropertyName findNameForDeserialization(Annotated a10) {
        JsonSetter js = this._findAnnotation(a10, JsonSetter.class);
        if (js != null) {
            return PropertyName.construct(js.value());
        }
        JsonProperty pann = this._findAnnotation(a10, JsonProperty.class);
        if (pann != null) {
            return PropertyName.construct(pann.value());
        }
        if (this._hasOneOf(a10, ANNOTATIONS_TO_INFER_DESER)) {
            return PropertyName.USE_DEFAULT;
        }
        return null;
    }

    @Override
    public Boolean hasAnySetter(Annotated a10) {
        JsonAnySetter ann = this._findAnnotation(a10, JsonAnySetter.class);
        return ann == null ? null : Boolean.valueOf(ann.enabled());
    }

    @Override
    public JsonSetter.Value findSetterInfo(Annotated a10) {
        return JsonSetter.Value.from(this._findAnnotation(a10, JsonSetter.class));
    }

    @Override
    public Boolean findMergeInfo(Annotated a10) {
        JsonMerge ann = this._findAnnotation(a10, JsonMerge.class);
        return ann == null ? null : ann.value().asBoolean();
    }

    @Override
    @Deprecated
    public boolean hasAnySetterAnnotation(AnnotatedMethod am2) {
        return this._hasAnnotation(am2, JsonAnySetter.class);
    }

    @Override
    @Deprecated
    public boolean hasCreatorAnnotation(Annotated a10) {
        Boolean b10;
        JsonCreator ann = this._findAnnotation(a10, JsonCreator.class);
        if (ann != null) {
            return ann.mode() != JsonCreator.Mode.DISABLED;
        }
        if (this._cfgConstructorPropertiesImpliesCreator && a10 instanceof AnnotatedConstructor && _java7Helper != null && (b10 = _java7Helper.hasCreatorAnnotation(a10)) != null) {
            return b10;
        }
        return false;
    }

    @Override
    @Deprecated
    public JsonCreator.Mode findCreatorBinding(Annotated a10) {
        JsonCreator ann = this._findAnnotation(a10, JsonCreator.class);
        return ann == null ? null : ann.mode();
    }

    @Override
    public JsonCreator.Mode findCreatorAnnotation(MapperConfig<?> config, Annotated a10) {
        Boolean b10;
        JsonCreator ann = this._findAnnotation(a10, JsonCreator.class);
        if (ann != null) {
            return ann.mode();
        }
        if (this._cfgConstructorPropertiesImpliesCreator && config.isEnabled(MapperFeature.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES) && a10 instanceof AnnotatedConstructor && _java7Helper != null && (b10 = _java7Helper.hasCreatorAnnotation(a10)) != null && b10.booleanValue()) {
            return JsonCreator.Mode.PROPERTIES;
        }
        return null;
    }

    protected boolean _isIgnorable(Annotated a10) {
        Boolean b10;
        JsonIgnore ann = this._findAnnotation(a10, JsonIgnore.class);
        if (ann != null) {
            return ann.value();
        }
        if (_java7Helper != null && (b10 = _java7Helper.findTransient(a10)) != null) {
            return b10;
        }
        return false;
    }

    protected Class<?> _classIfExplicit(Class<?> cls) {
        if (cls == null || ClassUtil.isBogusClass(cls)) {
            return null;
        }
        return cls;
    }

    protected Class<?> _classIfExplicit(Class<?> cls, Class<?> implicit) {
        return (cls = this._classIfExplicit(cls)) == null || cls == implicit ? null : cls;
    }

    protected PropertyName _propertyName(String localName, String namespace) {
        if (localName.isEmpty()) {
            return PropertyName.USE_DEFAULT;
        }
        if (namespace == null || namespace.isEmpty()) {
            return PropertyName.construct(localName);
        }
        return PropertyName.construct(localName, namespace);
    }

    protected PropertyName _findConstructorName(Annotated a10) {
        PropertyName name;
        AnnotatedParameter p2;
        AnnotatedWithParams ctor;
        if (a10 instanceof AnnotatedParameter && (ctor = (p2 = (AnnotatedParameter)a10).getOwner()) != null && _java7Helper != null && (name = _java7Helper.findConstructorName(p2)) != null) {
            return name;
        }
        return null;
    }

    protected TypeResolverBuilder<?> _findTypeResolver(MapperConfig<?> config, Annotated ann, JavaType baseType) {
        TypeIdResolver idRes;
        StdTypeResolverBuilder b10;
        JsonTypeInfo info = this._findAnnotation(ann, JsonTypeInfo.class);
        JsonTypeResolver resAnn = this._findAnnotation(ann, JsonTypeResolver.class);
        if (resAnn != null) {
            if (info == null) {
                return null;
            }
            b10 = config.typeResolverBuilderInstance(ann, resAnn.value());
        } else {
            if (info == null) {
                return null;
            }
            if (info.use() == JsonTypeInfo.Id.NONE) {
                return this._constructNoTypeResolverBuilder();
            }
            b10 = this._constructStdTypeResolverBuilder();
        }
        JsonTypeIdResolver idResInfo = this._findAnnotation(ann, JsonTypeIdResolver.class);
        TypeIdResolver typeIdResolver = idRes = idResInfo == null ? null : config.typeIdResolverInstance(ann, idResInfo.value());
        if (idRes != null) {
            idRes.init(baseType);
        }
        b10 = b10.init(info.use(), idRes);
        JsonTypeInfo.As inclusion = info.include();
        if (inclusion == JsonTypeInfo.As.EXTERNAL_PROPERTY && ann instanceof AnnotatedClass) {
            inclusion = JsonTypeInfo.As.PROPERTY;
        }
        b10 = b10.inclusion(inclusion);
        b10 = b10.typeProperty(info.property());
        Class<?> defaultImpl = info.defaultImpl();
        if (defaultImpl != JsonTypeInfo.None.class && !defaultImpl.isAnnotation()) {
            b10 = b10.defaultImpl(defaultImpl);
        }
        b10 = b10.typeIdVisibility(info.visible());
        return b10;
    }

    protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
        return new StdTypeResolverBuilder();
    }

    protected StdTypeResolverBuilder _constructNoTypeResolverBuilder() {
        return StdTypeResolverBuilder.noTypeInfoBuilder();
    }

    private boolean _primitiveAndWrapper(Class<?> baseType, Class<?> refinement) {
        if (baseType.isPrimitive()) {
            return baseType == ClassUtil.primitiveType(refinement);
        }
        if (refinement.isPrimitive()) {
            return refinement == ClassUtil.primitiveType(baseType);
        }
        return false;
    }

    private boolean _primitiveAndWrapper(JavaType baseType, Class<?> refinement) {
        if (baseType.isPrimitive()) {
            return baseType.hasRawClass(ClassUtil.primitiveType(refinement));
        }
        if (refinement.isPrimitive()) {
            return refinement == ClassUtil.primitiveType(baseType.getRawClass());
        }
        return false;
    }

    static {
        Java7Support x2 = null;
        try {
            x2 = Java7Support.instance();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        _java7Helper = x2;
    }
}

