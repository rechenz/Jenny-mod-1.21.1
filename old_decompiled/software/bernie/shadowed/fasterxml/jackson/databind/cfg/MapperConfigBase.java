/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonAutoDetect;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreProperties;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonSetter;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyNamingStrategy;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.BaseSettings;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ConfigFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ConfigOverride;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ConfigOverrides;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ContextAttributes;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClass;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ClassIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.VisibilityChecker;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.RootNameLookup;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>>
extends MapperConfig<T>
implements Serializable {
    protected static final ConfigOverride EMPTY_OVERRIDE = ConfigOverride.empty();
    private static final int DEFAULT_MAPPER_FEATURES = MapperConfigBase.collectFeatureDefaults(MapperFeature.class);
    private static final int AUTO_DETECT_MASK = MapperFeature.AUTO_DETECT_FIELDS.getMask() | MapperFeature.AUTO_DETECT_GETTERS.getMask() | MapperFeature.AUTO_DETECT_IS_GETTERS.getMask() | MapperFeature.AUTO_DETECT_SETTERS.getMask() | MapperFeature.AUTO_DETECT_CREATORS.getMask();
    protected final SimpleMixInResolver _mixIns;
    protected final SubtypeResolver _subtypeResolver;
    protected final PropertyName _rootName;
    protected final Class<?> _view;
    protected final ContextAttributes _attributes;
    protected final RootNameLookup _rootNames;
    protected final ConfigOverrides _configOverrides;

    protected MapperConfigBase(BaseSettings base, SubtypeResolver str, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides) {
        super(base, DEFAULT_MAPPER_FEATURES);
        this._mixIns = mixins;
        this._subtypeResolver = str;
        this._rootNames = rootNames;
        this._rootName = null;
        this._view = null;
        this._attributes = ContextAttributes.getEmpty();
        this._configOverrides = configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides) {
        super(src);
        this._mixIns = mixins;
        this._subtypeResolver = src._subtypeResolver;
        this._rootNames = rootNames;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
        this._configOverrides = configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src) {
        super(src);
        this._mixIns = src._mixIns;
        this._subtypeResolver = src._subtypeResolver;
        this._rootNames = src._rootNames;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
        this._configOverrides = src._configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, BaseSettings base) {
        super(src, base);
        this._mixIns = src._mixIns;
        this._subtypeResolver = src._subtypeResolver;
        this._rootNames = src._rootNames;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
        this._configOverrides = src._configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, int mapperFeatures) {
        super(src, mapperFeatures);
        this._mixIns = src._mixIns;
        this._subtypeResolver = src._subtypeResolver;
        this._rootNames = src._rootNames;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
        this._configOverrides = src._configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, SubtypeResolver str) {
        super(src);
        this._mixIns = src._mixIns;
        this._subtypeResolver = str;
        this._rootNames = src._rootNames;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
        this._configOverrides = src._configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, PropertyName rootName) {
        super(src);
        this._mixIns = src._mixIns;
        this._subtypeResolver = src._subtypeResolver;
        this._rootNames = src._rootNames;
        this._rootName = rootName;
        this._view = src._view;
        this._attributes = src._attributes;
        this._configOverrides = src._configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, Class<?> view) {
        super(src);
        this._mixIns = src._mixIns;
        this._subtypeResolver = src._subtypeResolver;
        this._rootNames = src._rootNames;
        this._rootName = src._rootName;
        this._view = view;
        this._attributes = src._attributes;
        this._configOverrides = src._configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, SimpleMixInResolver mixins) {
        super(src);
        this._mixIns = mixins;
        this._subtypeResolver = src._subtypeResolver;
        this._rootNames = src._rootNames;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
        this._configOverrides = src._configOverrides;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, ContextAttributes attr) {
        super(src);
        this._mixIns = src._mixIns;
        this._subtypeResolver = src._subtypeResolver;
        this._rootNames = src._rootNames;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = attr;
        this._configOverrides = src._configOverrides;
    }

    protected abstract T _withBase(BaseSettings var1);

    protected abstract T _withMapperFeatures(int var1);

    @Override
    public final T with(MapperFeature ... features) {
        int newMapperFlags = this._mapperFeatures;
        for (MapperFeature f10 : features) {
            newMapperFlags |= f10.getMask();
        }
        if (newMapperFlags == this._mapperFeatures) {
            return (T)this;
        }
        return this._withMapperFeatures(newMapperFlags);
    }

    @Override
    public final T without(MapperFeature ... features) {
        int newMapperFlags = this._mapperFeatures;
        for (MapperFeature f10 : features) {
            newMapperFlags &= ~f10.getMask();
        }
        if (newMapperFlags == this._mapperFeatures) {
            return (T)this;
        }
        return this._withMapperFeatures(newMapperFlags);
    }

    @Override
    public final T with(MapperFeature feature, boolean state) {
        int newMapperFlags = state ? this._mapperFeatures | feature.getMask() : this._mapperFeatures & ~feature.getMask();
        if (newMapperFlags == this._mapperFeatures) {
            return (T)this;
        }
        return this._withMapperFeatures(newMapperFlags);
    }

    public final T with(AnnotationIntrospector ai2) {
        return this._withBase(this._base.withAnnotationIntrospector(ai2));
    }

    public final T withAppendedAnnotationIntrospector(AnnotationIntrospector ai2) {
        return this._withBase(this._base.withAppendedAnnotationIntrospector(ai2));
    }

    public final T withInsertedAnnotationIntrospector(AnnotationIntrospector ai2) {
        return this._withBase(this._base.withInsertedAnnotationIntrospector(ai2));
    }

    public final T with(ClassIntrospector ci2) {
        return this._withBase(this._base.withClassIntrospector(ci2));
    }

    public abstract T with(ContextAttributes var1);

    public T withAttributes(Map<?, ?> attributes) {
        return this.with(this.getAttributes().withSharedAttributes(attributes));
    }

    public T withAttribute(Object key, Object value) {
        return this.with(this.getAttributes().withSharedAttribute(key, value));
    }

    public T withoutAttribute(Object key) {
        return this.with(this.getAttributes().withoutSharedAttribute(key));
    }

    public final T with(TypeFactory tf) {
        return this._withBase(this._base.withTypeFactory(tf));
    }

    public final T with(TypeResolverBuilder<?> trb) {
        return this._withBase(this._base.withTypeResolverBuilder(trb));
    }

    public final T with(PropertyNamingStrategy pns) {
        return this._withBase(this._base.withPropertyNamingStrategy(pns));
    }

    public final T with(HandlerInstantiator hi) {
        return this._withBase(this._base.withHandlerInstantiator(hi));
    }

    public final T with(Base64Variant base64) {
        return this._withBase(this._base.with(base64));
    }

    public T with(DateFormat df2) {
        return this._withBase(this._base.withDateFormat(df2));
    }

    public final T with(Locale l2) {
        return this._withBase(this._base.with(l2));
    }

    public final T with(TimeZone tz) {
        return this._withBase(this._base.with(tz));
    }

    public abstract T withRootName(PropertyName var1);

    public T withRootName(String rootName) {
        if (rootName == null) {
            return this.withRootName((PropertyName)null);
        }
        return this.withRootName(PropertyName.construct(rootName));
    }

    public abstract T with(SubtypeResolver var1);

    public abstract T withView(Class<?> var1);

    @Override
    public final SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    @Deprecated
    public final String getRootName() {
        return this._rootName == null ? null : this._rootName.getSimpleName();
    }

    public final PropertyName getFullRootName() {
        return this._rootName;
    }

    @Override
    public final Class<?> getActiveView() {
        return this._view;
    }

    @Override
    public final ContextAttributes getAttributes() {
        return this._attributes;
    }

    @Override
    public final ConfigOverride getConfigOverride(Class<?> type) {
        ConfigOverride override = this._configOverrides.findOverride(type);
        return override == null ? EMPTY_OVERRIDE : override;
    }

    @Override
    public final ConfigOverride findConfigOverride(Class<?> type) {
        return this._configOverrides.findOverride(type);
    }

    @Override
    public final JsonInclude.Value getDefaultPropertyInclusion() {
        return this._configOverrides.getDefaultInclusion();
    }

    @Override
    public final JsonInclude.Value getDefaultPropertyInclusion(Class<?> baseType) {
        JsonInclude.Value v2 = this.getConfigOverride(baseType).getInclude();
        JsonInclude.Value def = this.getDefaultPropertyInclusion();
        if (def == null) {
            return v2;
        }
        return def.withOverrides(v2);
    }

    @Override
    public final JsonInclude.Value getDefaultInclusion(Class<?> baseType, Class<?> propertyType) {
        JsonInclude.Value v2 = this.getConfigOverride(propertyType).getIncludeAsProperty();
        JsonInclude.Value def = this.getDefaultPropertyInclusion(baseType);
        if (def == null) {
            return v2;
        }
        return def.withOverrides(v2);
    }

    @Override
    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> type) {
        JsonFormat.Value v2;
        ConfigOverride overrides = this._configOverrides.findOverride(type);
        if (overrides != null && (v2 = overrides.getFormat()) != null) {
            return v2;
        }
        return EMPTY_FORMAT;
    }

    @Override
    public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> type) {
        JsonIgnoreProperties.Value v2;
        ConfigOverride overrides = this._configOverrides.findOverride(type);
        if (overrides != null && (v2 = overrides.getIgnorals()) != null) {
            return v2;
        }
        return null;
    }

    @Override
    public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> baseType, AnnotatedClass actualClass) {
        AnnotationIntrospector intr = this.getAnnotationIntrospector();
        JsonIgnoreProperties.Value base = intr == null ? null : intr.findPropertyIgnorals(actualClass);
        JsonIgnoreProperties.Value overrides = this.getDefaultPropertyIgnorals(baseType);
        return JsonIgnoreProperties.Value.merge(base, overrides);
    }

    @Override
    public final VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker<?> vchecker = this._configOverrides.getDefaultVisibility();
        if ((this._mapperFeatures & AUTO_DETECT_MASK) != 0) {
            if (!this.isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
                vchecker = vchecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
            }
            if (!this.isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
                vchecker = vchecker.withGetterVisibility(JsonAutoDetect.Visibility.NONE);
            }
            if (!this.isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
                vchecker = vchecker.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
            }
            if (!this.isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
                vchecker = vchecker.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
            }
            if (!this.isEnabled(MapperFeature.AUTO_DETECT_CREATORS)) {
                vchecker = vchecker.withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
            }
        }
        return vchecker;
    }

    @Override
    public final VisibilityChecker<?> getDefaultVisibilityChecker(Class<?> baseType, AnnotatedClass actualClass) {
        ConfigOverride overrides;
        VisibilityChecker<?> vc = this.getDefaultVisibilityChecker();
        AnnotationIntrospector intr = this.getAnnotationIntrospector();
        if (intr != null) {
            vc = intr.findAutoDetectVisibility(actualClass, vc);
        }
        if ((overrides = this._configOverrides.findOverride(baseType)) != null) {
            vc = vc.withOverrides(overrides.getVisibility());
        }
        return vc;
    }

    @Override
    public final JsonSetter.Value getDefaultSetterInfo() {
        return this._configOverrides.getDefaultSetterInfo();
    }

    @Override
    public Boolean getDefaultMergeable() {
        return this._configOverrides.getDefaultMergeable();
    }

    @Override
    public Boolean getDefaultMergeable(Class<?> baseType) {
        Boolean b10;
        ConfigOverride cfg = this._configOverrides.findOverride(baseType);
        if (cfg != null && (b10 = cfg.getMergeable()) != null) {
            return b10;
        }
        return this._configOverrides.getDefaultMergeable();
    }

    @Override
    public PropertyName findRootName(JavaType rootType) {
        if (this._rootName != null) {
            return this._rootName;
        }
        return this._rootNames.findRootName(rootType, this);
    }

    @Override
    public PropertyName findRootName(Class<?> rawRootType) {
        if (this._rootName != null) {
            return this._rootName;
        }
        return this._rootNames.findRootName(rawRootType, this);
    }

    @Override
    public final Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixIns.findMixInClassFor(cls);
    }

    @Override
    public ClassIntrospector.MixInResolver copy() {
        throw new UnsupportedOperationException();
    }

    public final int mixInCount() {
        return this._mixIns.localSize();
    }
}

