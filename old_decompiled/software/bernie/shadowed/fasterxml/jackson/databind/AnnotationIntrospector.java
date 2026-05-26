/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.annotation.JacksonInject;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonCreator;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreProperties;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonSetter;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.core.Versioned;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JsonSerialize;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.Annotated;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClass;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.VisibilityChecker;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.NamedType;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.util.NameTransformer;

public abstract class AnnotationIntrospector
implements Versioned,
Serializable {
    public static AnnotationIntrospector nopInstance() {
        return NopAnnotationIntrospector.instance;
    }

    public static AnnotationIntrospector pair(AnnotationIntrospector a12, AnnotationIntrospector a22) {
        return new AnnotationIntrospectorPair(a12, a22);
    }

    public Collection<AnnotationIntrospector> allIntrospectors() {
        return Collections.singletonList(this);
    }

    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> result) {
        result.add(this);
        return result;
    }

    @Override
    public abstract Version version();

    public boolean isAnnotationBundle(Annotation ann) {
        return false;
    }

    public ObjectIdInfo findObjectIdInfo(Annotated ann) {
        return null;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated ann, ObjectIdInfo objectIdInfo) {
        return objectIdInfo;
    }

    public PropertyName findRootName(AnnotatedClass ac2) {
        return null;
    }

    public JsonIgnoreProperties.Value findPropertyIgnorals(Annotated ac2) {
        return JsonIgnoreProperties.Value.empty();
    }

    public Boolean isIgnorableType(AnnotatedClass ac2) {
        return null;
    }

    public Object findFilterId(Annotated ann) {
        return null;
    }

    public Object findNamingStrategy(AnnotatedClass ac2) {
        return null;
    }

    public String findClassDescription(AnnotatedClass ac2) {
        return null;
    }

    @Deprecated
    public String[] findPropertiesToIgnore(Annotated ac2, boolean forSerialization) {
        return null;
    }

    @Deprecated
    public String[] findPropertiesToIgnore(Annotated ac2) {
        return null;
    }

    @Deprecated
    public Boolean findIgnoreUnknownProperties(AnnotatedClass ac2) {
        return null;
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass ac2, VisibilityChecker<?> checker) {
        return checker;
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> config, AnnotatedClass ac2, JavaType baseType) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> config, AnnotatedMember am2, JavaType baseType) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> config, AnnotatedMember am2, JavaType containerType) {
        return null;
    }

    public List<NamedType> findSubtypes(Annotated a10) {
        return null;
    }

    public String findTypeName(AnnotatedClass ac2) {
        return null;
    }

    public Boolean isTypeId(AnnotatedMember member) {
        return null;
    }

    public ReferenceProperty findReferenceType(AnnotatedMember member) {
        return null;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember member) {
        return null;
    }

    public boolean hasIgnoreMarker(AnnotatedMember m2) {
        return false;
    }

    public JacksonInject.Value findInjectableValue(AnnotatedMember m2) {
        Object id = this.findInjectableValueId(m2);
        if (id != null) {
            return JacksonInject.Value.forId(id);
        }
        return null;
    }

    public Boolean hasRequiredMarker(AnnotatedMember m2) {
        return null;
    }

    public Class<?>[] findViews(Annotated a10) {
        return null;
    }

    public JsonFormat.Value findFormat(Annotated memberOrClass) {
        return JsonFormat.Value.empty();
    }

    public PropertyName findWrapperName(Annotated ann) {
        return null;
    }

    public String findPropertyDefaultValue(Annotated ann) {
        return null;
    }

    public String findPropertyDescription(Annotated ann) {
        return null;
    }

    public Integer findPropertyIndex(Annotated ann) {
        return null;
    }

    public String findImplicitPropertyName(AnnotatedMember member) {
        return null;
    }

    public List<PropertyName> findPropertyAliases(Annotated ann) {
        return null;
    }

    public JsonProperty.Access findPropertyAccess(Annotated ann) {
        return null;
    }

    public AnnotatedMethod resolveSetterConflict(MapperConfig<?> config, AnnotatedMethod setter1, AnnotatedMethod setter2) {
        return null;
    }

    @Deprecated
    public Object findInjectableValueId(AnnotatedMember m2) {
        return null;
    }

    public Object findSerializer(Annotated am2) {
        return null;
    }

    public Object findKeySerializer(Annotated am2) {
        return null;
    }

    public Object findContentSerializer(Annotated am2) {
        return null;
    }

    public Object findNullSerializer(Annotated am2) {
        return null;
    }

    public JsonSerialize.Typing findSerializationTyping(Annotated a10) {
        return null;
    }

    public Object findSerializationConverter(Annotated a10) {
        return null;
    }

    public Object findSerializationContentConverter(AnnotatedMember a10) {
        return null;
    }

    public JsonInclude.Value findPropertyInclusion(Annotated a10) {
        return JsonInclude.Value.empty();
    }

    @Deprecated
    public JsonInclude.Include findSerializationInclusion(Annotated a10, JsonInclude.Include defValue) {
        return defValue;
    }

    @Deprecated
    public JsonInclude.Include findSerializationInclusionForContent(Annotated a10, JsonInclude.Include defValue) {
        return defValue;
    }

    public JavaType refineSerializationType(MapperConfig<?> config, Annotated a10, JavaType baseType) throws JsonMappingException {
        return baseType;
    }

    @Deprecated
    public Class<?> findSerializationType(Annotated a10) {
        return null;
    }

    @Deprecated
    public Class<?> findSerializationKeyType(Annotated am2, JavaType baseType) {
        return null;
    }

    @Deprecated
    public Class<?> findSerializationContentType(Annotated am2, JavaType baseType) {
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass ac2) {
        return null;
    }

    public Boolean findSerializationSortAlphabetically(Annotated ann) {
        return null;
    }

    public void findAndAddVirtualProperties(MapperConfig<?> config, AnnotatedClass ac2, List<BeanPropertyWriter> properties) {
    }

    public PropertyName findNameForSerialization(Annotated a10) {
        return null;
    }

    public Boolean hasAsValue(Annotated a10) {
        if (a10 instanceof AnnotatedMethod && this.hasAsValueAnnotation((AnnotatedMethod)a10)) {
            return true;
        }
        return null;
    }

    public Boolean hasAnyGetter(Annotated a10) {
        if (a10 instanceof AnnotatedMethod && this.hasAnyGetterAnnotation((AnnotatedMethod)a10)) {
            return true;
        }
        return null;
    }

    public String[] findEnumValues(Class<?> enumType, Enum<?>[] enumValues, String[] names) {
        return names;
    }

    public Enum<?> findDefaultEnumValue(Class<Enum<?>> enumCls) {
        return null;
    }

    @Deprecated
    public String findEnumValue(Enum<?> value) {
        return value.name();
    }

    @Deprecated
    public boolean hasAsValueAnnotation(AnnotatedMethod am2) {
        return false;
    }

    @Deprecated
    public boolean hasAnyGetterAnnotation(AnnotatedMethod am2) {
        return false;
    }

    public Object findDeserializer(Annotated am2) {
        return null;
    }

    public Object findKeyDeserializer(Annotated am2) {
        return null;
    }

    public Object findContentDeserializer(Annotated am2) {
        return null;
    }

    public Object findDeserializationConverter(Annotated a10) {
        return null;
    }

    public Object findDeserializationContentConverter(AnnotatedMember a10) {
        return null;
    }

    public JavaType refineDeserializationType(MapperConfig<?> config, Annotated a10, JavaType baseType) throws JsonMappingException {
        return baseType;
    }

    @Deprecated
    public Class<?> findDeserializationType(Annotated am2, JavaType baseType) {
        return null;
    }

    @Deprecated
    public Class<?> findDeserializationKeyType(Annotated am2, JavaType baseKeyType) {
        return null;
    }

    @Deprecated
    public Class<?> findDeserializationContentType(Annotated am2, JavaType baseContentType) {
        return null;
    }

    public Object findValueInstantiator(AnnotatedClass ac2) {
        return null;
    }

    public Class<?> findPOJOBuilder(AnnotatedClass ac2) {
        return null;
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass ac2) {
        return null;
    }

    public PropertyName findNameForDeserialization(Annotated a10) {
        return null;
    }

    public Boolean hasAnySetter(Annotated a10) {
        return null;
    }

    public JsonSetter.Value findSetterInfo(Annotated a10) {
        return JsonSetter.Value.empty();
    }

    public Boolean findMergeInfo(Annotated a10) {
        return null;
    }

    public JsonCreator.Mode findCreatorAnnotation(MapperConfig<?> config, Annotated a10) {
        if (this.hasCreatorAnnotation(a10)) {
            JsonCreator.Mode mode = this.findCreatorBinding(a10);
            if (mode == null) {
                mode = JsonCreator.Mode.DEFAULT;
            }
            return mode;
        }
        return null;
    }

    @Deprecated
    public boolean hasCreatorAnnotation(Annotated a10) {
        return false;
    }

    @Deprecated
    public JsonCreator.Mode findCreatorBinding(Annotated a10) {
        return null;
    }

    @Deprecated
    public boolean hasAnySetterAnnotation(AnnotatedMethod am2) {
        return false;
    }

    protected <A extends Annotation> A _findAnnotation(Annotated annotated, Class<A> annoClass) {
        return annotated.getAnnotation(annoClass);
    }

    protected boolean _hasAnnotation(Annotated annotated, Class<? extends Annotation> annoClass) {
        return annotated.hasAnnotation(annoClass);
    }

    protected boolean _hasOneOf(Annotated annotated, Class<? extends Annotation>[] annoClasses) {
        return annotated.hasOneOf(annoClasses);
    }

    public static class ReferenceProperty {
        private final Type _type;
        private final String _name;

        public ReferenceProperty(Type t2, String n2) {
            this._type = t2;
            this._name = n2;
        }

        public static ReferenceProperty managed(String name) {
            return new ReferenceProperty(Type.MANAGED_REFERENCE, name);
        }

        public static ReferenceProperty back(String name) {
            return new ReferenceProperty(Type.BACK_REFERENCE, name);
        }

        public Type getType() {
            return this._type;
        }

        public String getName() {
            return this._name;
        }

        public boolean isManagedReference() {
            return this._type == Type.MANAGED_REFERENCE;
        }

        public boolean isBackReference() {
            return this._type == Type.BACK_REFERENCE;
        }

        public static enum Type {
            MANAGED_REFERENCE,
            BACK_REFERENCE;

        }
    }
}

