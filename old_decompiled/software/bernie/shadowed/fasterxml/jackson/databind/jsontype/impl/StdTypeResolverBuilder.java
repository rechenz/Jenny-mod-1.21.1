/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl;

import java.util.Collection;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.NoClass;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.NamedType;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsExistingPropertyTypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsExternalTypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsExternalTypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsWrapperTypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.AsWrapperTypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.TypeNameIdResolver;

public class StdTypeResolverBuilder
implements TypeResolverBuilder<StdTypeResolverBuilder> {
    protected JsonTypeInfo.Id _idType;
    protected JsonTypeInfo.As _includeAs;
    protected String _typeProperty;
    protected boolean _typeIdVisible = false;
    protected Class<?> _defaultImpl;
    protected TypeIdResolver _customIdResolver;

    public StdTypeResolverBuilder() {
    }

    protected StdTypeResolverBuilder(JsonTypeInfo.Id idType, JsonTypeInfo.As idAs, String propName) {
        this._idType = idType;
        this._includeAs = idAs;
        this._typeProperty = propName;
    }

    public static StdTypeResolverBuilder noTypeInfoBuilder() {
        return new StdTypeResolverBuilder().init(JsonTypeInfo.Id.NONE, null);
    }

    @Override
    public StdTypeResolverBuilder init(JsonTypeInfo.Id idType, TypeIdResolver idRes) {
        if (idType == null) {
            throw new IllegalArgumentException("idType cannot be null");
        }
        this._idType = idType;
        this._customIdResolver = idRes;
        this._typeProperty = idType.getDefaultPropertyName();
        return this;
    }

    @Override
    public TypeSerializer buildTypeSerializer(SerializationConfig config, JavaType baseType, Collection<NamedType> subtypes) {
        if (this._idType == JsonTypeInfo.Id.NONE) {
            return null;
        }
        if (baseType.isPrimitive()) {
            return null;
        }
        TypeIdResolver idRes = this.idResolver(config, baseType, subtypes, true, false);
        switch (this._includeAs) {
            case WRAPPER_ARRAY: {
                return new AsArrayTypeSerializer(idRes, null);
            }
            case PROPERTY: {
                return new AsPropertyTypeSerializer(idRes, null, this._typeProperty);
            }
            case WRAPPER_OBJECT: {
                return new AsWrapperTypeSerializer(idRes, null);
            }
            case EXTERNAL_PROPERTY: {
                return new AsExternalTypeSerializer(idRes, null, this._typeProperty);
            }
            case EXISTING_PROPERTY: {
                return new AsExistingPropertyTypeSerializer(idRes, null, this._typeProperty);
            }
        }
        throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + (Object)((Object)this._includeAs));
    }

    @Override
    public TypeDeserializer buildTypeDeserializer(DeserializationConfig config, JavaType baseType, Collection<NamedType> subtypes) {
        if (this._idType == JsonTypeInfo.Id.NONE) {
            return null;
        }
        if (baseType.isPrimitive()) {
            return null;
        }
        TypeIdResolver idRes = this.idResolver(config, baseType, subtypes, false, true);
        JavaType defaultImpl = this._defaultImpl == null ? null : (this._defaultImpl == Void.class || this._defaultImpl == NoClass.class ? config.getTypeFactory().constructType(this._defaultImpl) : config.getTypeFactory().constructSpecializedType(baseType, this._defaultImpl));
        switch (this._includeAs) {
            case WRAPPER_ARRAY: {
                return new AsArrayTypeDeserializer(baseType, idRes, this._typeProperty, this._typeIdVisible, defaultImpl);
            }
            case PROPERTY: 
            case EXISTING_PROPERTY: {
                return new AsPropertyTypeDeserializer(baseType, idRes, this._typeProperty, this._typeIdVisible, defaultImpl, this._includeAs);
            }
            case WRAPPER_OBJECT: {
                return new AsWrapperTypeDeserializer(baseType, idRes, this._typeProperty, this._typeIdVisible, defaultImpl);
            }
            case EXTERNAL_PROPERTY: {
                return new AsExternalTypeDeserializer(baseType, idRes, this._typeProperty, this._typeIdVisible, defaultImpl);
            }
        }
        throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + (Object)((Object)this._includeAs));
    }

    @Override
    public StdTypeResolverBuilder inclusion(JsonTypeInfo.As includeAs) {
        if (includeAs == null) {
            throw new IllegalArgumentException("includeAs cannot be null");
        }
        this._includeAs = includeAs;
        return this;
    }

    @Override
    public StdTypeResolverBuilder typeProperty(String typeIdPropName) {
        if (typeIdPropName == null || typeIdPropName.length() == 0) {
            typeIdPropName = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = typeIdPropName;
        return this;
    }

    @Override
    public StdTypeResolverBuilder defaultImpl(Class<?> defaultImpl) {
        this._defaultImpl = defaultImpl;
        return this;
    }

    @Override
    public StdTypeResolverBuilder typeIdVisibility(boolean isVisible) {
        this._typeIdVisible = isVisible;
        return this;
    }

    @Override
    public Class<?> getDefaultImpl() {
        return this._defaultImpl;
    }

    public String getTypeProperty() {
        return this._typeProperty;
    }

    public boolean isTypeIdVisible() {
        return this._typeIdVisible;
    }

    protected TypeIdResolver idResolver(MapperConfig<?> config, JavaType baseType, Collection<NamedType> subtypes, boolean forSer, boolean forDeser) {
        if (this._customIdResolver != null) {
            return this._customIdResolver;
        }
        if (this._idType == null) {
            throw new IllegalStateException("Cannot build, 'init()' not yet called");
        }
        switch (this._idType) {
            case CLASS: {
                return new ClassNameIdResolver(baseType, config.getTypeFactory());
            }
            case MINIMAL_CLASS: {
                return new MinimalClassNameIdResolver(baseType, config.getTypeFactory());
            }
            case NAME: {
                return TypeNameIdResolver.construct(config, baseType, subtypes, forSer, forDeser);
            }
            case NONE: {
                return null;
            }
        }
        throw new IllegalStateException("Do not know how to construct standard type id resolver for idType: " + (Object)((Object)this._idType));
    }
}

