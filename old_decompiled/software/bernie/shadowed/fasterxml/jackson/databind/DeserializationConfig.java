/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.io.Serializable;
import java.util.Collection;
import software.bernie.shadowed.fasterxml.jackson.core.FormatFeature;
import software.bernie.shadowed.fasterxml.jackson.core.JsonFactory;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.BaseSettings;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ConfigOverrides;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ContextAttributes;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfigBase;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClass;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.NamedType;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.LinkedNode;
import software.bernie.shadowed.fasterxml.jackson.databind.util.RootNameLookup;

public final class DeserializationConfig
extends MapperConfigBase<DeserializationFeature, DeserializationConfig>
implements Serializable {
    private static final long serialVersionUID = 2L;
    protected final LinkedNode<DeserializationProblemHandler> _problemHandlers;
    protected final JsonNodeFactory _nodeFactory;
    protected final int _deserFeatures;
    protected final int _parserFeatures;
    protected final int _parserFeaturesToChange;
    protected final int _formatReadFeatures;
    protected final int _formatReadFeaturesToChange;

    public DeserializationConfig(BaseSettings base, SubtypeResolver str, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides) {
        super(base, str, mixins, rootNames, configOverrides);
        this._deserFeatures = DeserializationConfig.collectFeatureDefaults(DeserializationFeature.class);
        this._nodeFactory = JsonNodeFactory.instance;
        this._problemHandlers = null;
        this._parserFeatures = 0;
        this._parserFeaturesToChange = 0;
        this._formatReadFeatures = 0;
        this._formatReadFeaturesToChange = 0;
    }

    protected DeserializationConfig(DeserializationConfig src, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides) {
        super(src, mixins, rootNames, configOverrides);
        this._deserFeatures = src._deserFeatures;
        this._problemHandlers = src._problemHandlers;
        this._nodeFactory = src._nodeFactory;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig src, int mapperFeatures, int deserFeatures, int parserFeatures, int parserFeatureMask, int formatFeatures, int formatFeatureMask) {
        super(src, mapperFeatures);
        this._deserFeatures = deserFeatures;
        this._nodeFactory = src._nodeFactory;
        this._problemHandlers = src._problemHandlers;
        this._parserFeatures = parserFeatures;
        this._parserFeaturesToChange = parserFeatureMask;
        this._formatReadFeatures = formatFeatures;
        this._formatReadFeaturesToChange = formatFeatureMask;
    }

    private DeserializationConfig(DeserializationConfig src, SubtypeResolver str) {
        super(src, str);
        this._deserFeatures = src._deserFeatures;
        this._nodeFactory = src._nodeFactory;
        this._problemHandlers = src._problemHandlers;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig src, BaseSettings base) {
        super(src, base);
        this._deserFeatures = src._deserFeatures;
        this._nodeFactory = src._nodeFactory;
        this._problemHandlers = src._problemHandlers;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig src, JsonNodeFactory f10) {
        super(src);
        this._deserFeatures = src._deserFeatures;
        this._problemHandlers = src._problemHandlers;
        this._nodeFactory = f10;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig src, LinkedNode<DeserializationProblemHandler> problemHandlers) {
        super(src);
        this._deserFeatures = src._deserFeatures;
        this._problemHandlers = problemHandlers;
        this._nodeFactory = src._nodeFactory;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig src, PropertyName rootName) {
        super(src, rootName);
        this._deserFeatures = src._deserFeatures;
        this._problemHandlers = src._problemHandlers;
        this._nodeFactory = src._nodeFactory;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig src, Class<?> view) {
        super(src, view);
        this._deserFeatures = src._deserFeatures;
        this._problemHandlers = src._problemHandlers;
        this._nodeFactory = src._nodeFactory;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    protected DeserializationConfig(DeserializationConfig src, ContextAttributes attrs) {
        super(src, attrs);
        this._deserFeatures = src._deserFeatures;
        this._problemHandlers = src._problemHandlers;
        this._nodeFactory = src._nodeFactory;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    protected DeserializationConfig(DeserializationConfig src, SimpleMixInResolver mixins) {
        super(src, mixins);
        this._deserFeatures = src._deserFeatures;
        this._problemHandlers = src._problemHandlers;
        this._nodeFactory = src._nodeFactory;
        this._parserFeatures = src._parserFeatures;
        this._parserFeaturesToChange = src._parserFeaturesToChange;
        this._formatReadFeatures = src._formatReadFeatures;
        this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
    }

    protected BaseSettings getBaseSettings() {
        return this._base;
    }

    @Override
    protected final DeserializationConfig _withBase(BaseSettings newBase) {
        return this._base == newBase ? this : new DeserializationConfig(this, newBase);
    }

    @Override
    protected final DeserializationConfig _withMapperFeatures(int mapperFeatures) {
        return new DeserializationConfig(this, mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    @Override
    public DeserializationConfig with(SubtypeResolver str) {
        return this._subtypeResolver == str ? this : new DeserializationConfig(this, str);
    }

    @Override
    public DeserializationConfig withRootName(PropertyName rootName) {
        if (rootName == null ? this._rootName == null : rootName.equals(this._rootName)) {
            return this;
        }
        return new DeserializationConfig(this, rootName);
    }

    @Override
    public DeserializationConfig withView(Class<?> view) {
        return this._view == view ? this : new DeserializationConfig(this, view);
    }

    @Override
    public DeserializationConfig with(ContextAttributes attrs) {
        return attrs == this._attributes ? this : new DeserializationConfig(this, attrs);
    }

    public DeserializationConfig with(DeserializationFeature feature) {
        int newDeserFeatures = this._deserFeatures | feature.getMask();
        return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(DeserializationFeature first, DeserializationFeature ... features) {
        int newDeserFeatures = this._deserFeatures | first.getMask();
        for (DeserializationFeature f10 : features) {
            newDeserFeatures |= f10.getMask();
        }
        return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withFeatures(DeserializationFeature ... features) {
        int newDeserFeatures = this._deserFeatures;
        for (DeserializationFeature f10 : features) {
            newDeserFeatures |= f10.getMask();
        }
        return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig without(DeserializationFeature feature) {
        int newDeserFeatures = this._deserFeatures & ~feature.getMask();
        return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig without(DeserializationFeature first, DeserializationFeature ... features) {
        int newDeserFeatures = this._deserFeatures & ~first.getMask();
        for (DeserializationFeature f10 : features) {
            newDeserFeatures &= ~f10.getMask();
        }
        return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withoutFeatures(DeserializationFeature ... features) {
        int newDeserFeatures = this._deserFeatures;
        for (DeserializationFeature f10 : features) {
            newDeserFeatures &= ~f10.getMask();
        }
        return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(JsonParser.Feature feature) {
        int newSet = this._parserFeatures | feature.getMask();
        int newMask = this._parserFeaturesToChange | feature.getMask();
        return this._parserFeatures == newSet && this._parserFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, newSet, newMask, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withFeatures(JsonParser.Feature ... features) {
        int newSet = this._parserFeatures;
        int newMask = this._parserFeaturesToChange;
        for (JsonParser.Feature f10 : features) {
            int mask = f10.getMask();
            newSet |= mask;
            newMask |= mask;
        }
        return this._parserFeatures == newSet && this._parserFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, newSet, newMask, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig without(JsonParser.Feature feature) {
        int newSet = this._parserFeatures & ~feature.getMask();
        int newMask = this._parserFeaturesToChange | feature.getMask();
        return this._parserFeatures == newSet && this._parserFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, newSet, newMask, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withoutFeatures(JsonParser.Feature ... features) {
        int newSet = this._parserFeatures;
        int newMask = this._parserFeaturesToChange;
        for (JsonParser.Feature f10 : features) {
            int mask = f10.getMask();
            newSet &= ~mask;
            newMask |= mask;
        }
        return this._parserFeatures == newSet && this._parserFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, newSet, newMask, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(FormatFeature feature) {
        int newSet = this._formatReadFeatures | feature.getMask();
        int newMask = this._formatReadFeaturesToChange | feature.getMask();
        return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, newSet, newMask);
    }

    public DeserializationConfig withFeatures(FormatFeature ... features) {
        int newSet = this._formatReadFeatures;
        int newMask = this._formatReadFeaturesToChange;
        for (FormatFeature f10 : features) {
            int mask = f10.getMask();
            newSet |= mask;
            newMask |= mask;
        }
        return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, newSet, newMask);
    }

    public DeserializationConfig without(FormatFeature feature) {
        int newSet = this._formatReadFeatures & ~feature.getMask();
        int newMask = this._formatReadFeaturesToChange | feature.getMask();
        return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, newSet, newMask);
    }

    public DeserializationConfig withoutFeatures(FormatFeature ... features) {
        int newSet = this._formatReadFeatures;
        int newMask = this._formatReadFeaturesToChange;
        for (FormatFeature f10 : features) {
            int mask = f10.getMask();
            newSet &= ~mask;
            newMask |= mask;
        }
        return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, newSet, newMask);
    }

    public DeserializationConfig with(JsonNodeFactory f10) {
        if (this._nodeFactory == f10) {
            return this;
        }
        return new DeserializationConfig(this, f10);
    }

    public DeserializationConfig withHandler(DeserializationProblemHandler h2) {
        if (LinkedNode.contains(this._problemHandlers, h2)) {
            return this;
        }
        return new DeserializationConfig(this, new LinkedNode<DeserializationProblemHandler>(h2, this._problemHandlers));
    }

    public DeserializationConfig withNoProblemHandlers() {
        if (this._problemHandlers == null) {
            return this;
        }
        return new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>)null);
    }

    public void initialize(JsonParser p2) {
        if (this._parserFeaturesToChange != 0) {
            p2.overrideStdFeatures(this._parserFeatures, this._parserFeaturesToChange);
        }
        if (this._formatReadFeaturesToChange != 0) {
            p2.overrideFormatFeatures(this._formatReadFeatures, this._formatReadFeaturesToChange);
        }
    }

    @Override
    public boolean useRootWrapping() {
        if (this._rootName != null) {
            return !this._rootName.isEmpty();
        }
        return this.isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
    }

    public final boolean isEnabled(DeserializationFeature f10) {
        return (this._deserFeatures & f10.getMask()) != 0;
    }

    public final boolean isEnabled(JsonParser.Feature f10, JsonFactory factory) {
        int mask = f10.getMask();
        if ((this._parserFeaturesToChange & mask) != 0) {
            return (this._parserFeatures & f10.getMask()) != 0;
        }
        return factory.isEnabled(f10);
    }

    public final boolean hasDeserializationFeatures(int featureMask) {
        return (this._deserFeatures & featureMask) == featureMask;
    }

    public final boolean hasSomeOfFeatures(int featureMask) {
        return (this._deserFeatures & featureMask) != 0;
    }

    public final int getDeserializationFeatures() {
        return this._deserFeatures;
    }

    public final boolean requiresFullValue() {
        return DeserializationFeature.FAIL_ON_TRAILING_TOKENS.enabledIn(this._deserFeatures);
    }

    public LinkedNode<DeserializationProblemHandler> getProblemHandlers() {
        return this._problemHandlers;
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._nodeFactory;
    }

    public <T extends BeanDescription> T introspect(JavaType type) {
        return (T)this.getClassIntrospector().forDeserialization(this, type, this);
    }

    public <T extends BeanDescription> T introspectForCreation(JavaType type) {
        return (T)this.getClassIntrospector().forCreation(this, type, this);
    }

    public <T extends BeanDescription> T introspectForBuilder(JavaType type) {
        return (T)this.getClassIntrospector().forDeserializationWithBuilder(this, type, this);
    }

    public TypeDeserializer findTypeDeserializer(JavaType baseType) throws JsonMappingException {
        BeanDescription bean = this.introspectClassAnnotations(baseType.getRawClass());
        AnnotatedClass ac2 = bean.getClassInfo();
        TypeResolverBuilder<?> b10 = this.getAnnotationIntrospector().findTypeResolver(this, ac2, baseType);
        Collection<NamedType> subtypes = null;
        if (b10 == null) {
            b10 = this.getDefaultTyper(baseType);
            if (b10 == null) {
                return null;
            }
        } else {
            subtypes = this.getSubtypeResolver().collectAndResolveSubtypesByTypeId(this, ac2);
        }
        return b10.buildTypeDeserializer(this, baseType, subtypes);
    }
}

