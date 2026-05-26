/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonAutoDetect;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonSetter;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.annotation.PropertyAccessor;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variants;
import software.bernie.shadowed.fasterxml.jackson.core.FormatSchema;
import software.bernie.shadowed.fasterxml.jackson.core.JsonEncoding;
import software.bernie.shadowed.fasterxml.jackson.core.JsonFactory;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerationException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParseException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;
import software.bernie.shadowed.fasterxml.jackson.core.PrettyPrinter;
import software.bernie.shadowed.fasterxml.jackson.core.TreeNode;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.core.Versioned;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharacterEscapes;
import software.bernie.shadowed.fasterxml.jackson.core.io.SegmentedStringWriter;
import software.bernie.shadowed.fasterxml.jackson.core.type.ResolvedType;
import software.bernie.shadowed.fasterxml.jackson.core.type.TypeReference;
import software.bernie.shadowed.fasterxml.jackson.core.util.ByteArrayBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.AbstractTypeResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.InjectableValues;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.MappingIterator;
import software.bernie.shadowed.fasterxml.jackson.databind.MappingJsonFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.Module;
import software.bernie.shadowed.fasterxml.jackson.databind.ObjectReader;
import software.bernie.shadowed.fasterxml.jackson.databind.ObjectWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyNamingStrategy;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.BaseSettings;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ConfigOverrides;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ContextAttributes;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MutableConfigOverride;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.PackageVersion;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.Deserializers;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.KeyDeserializers;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiators;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.MismatchedInputException;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ClassIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.VisibilityChecker;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonschema.JsonSchema;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.NamedType;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ArrayNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NullNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.POJONode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.TreeTraversingParser;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.FilterProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.SerializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.Serializers;
import software.bernie.shadowed.fasterxml.jackson.databind.type.SimpleType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeModifier;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.RootNameLookup;
import software.bernie.shadowed.fasterxml.jackson.databind.util.StdDateFormat;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

public class ObjectMapper
extends ObjectCodec
implements Versioned,
Serializable {
    private static final long serialVersionUID = 2L;
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
    protected static final BaseSettings DEFAULT_BASE = new BaseSettings(null, DEFAULT_ANNOTATION_INTROSPECTOR, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), null, Base64Variants.getDefaultVariant());
    protected final JsonFactory _jsonFactory;
    protected TypeFactory _typeFactory;
    protected InjectableValues _injectableValues;
    protected SubtypeResolver _subtypeResolver;
    protected final ConfigOverrides _configOverrides;
    protected SimpleMixInResolver _mixIns;
    protected SerializationConfig _serializationConfig;
    protected DefaultSerializerProvider _serializerProvider;
    protected SerializerFactory _serializerFactory;
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected Set<Object> _registeredModuleTypes;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers = new ConcurrentHashMap(64, 0.6f, 2);

    public ObjectMapper() {
        this(null, null, null);
    }

    public ObjectMapper(JsonFactory jf) {
        this(jf, null, null);
    }

    protected ObjectMapper(ObjectMapper src) {
        this._jsonFactory = src._jsonFactory.copy();
        this._jsonFactory.setCodec(this);
        this._subtypeResolver = src._subtypeResolver;
        this._typeFactory = src._typeFactory;
        this._injectableValues = src._injectableValues;
        this._configOverrides = src._configOverrides.copy();
        this._mixIns = src._mixIns.copy();
        RootNameLookup rootNames = new RootNameLookup();
        this._serializationConfig = new SerializationConfig(src._serializationConfig, this._mixIns, rootNames, this._configOverrides);
        this._deserializationConfig = new DeserializationConfig(src._deserializationConfig, this._mixIns, rootNames, this._configOverrides);
        this._serializerProvider = src._serializerProvider.copy();
        this._deserializationContext = src._deserializationContext.copy();
        this._serializerFactory = src._serializerFactory;
        Set<Object> reg = src._registeredModuleTypes;
        this._registeredModuleTypes = reg == null ? null : new LinkedHashSet<Object>(reg);
    }

    public ObjectMapper(JsonFactory jf, DefaultSerializerProvider sp, DefaultDeserializationContext dc2) {
        SimpleMixInResolver mixins;
        if (jf == null) {
            this._jsonFactory = new MappingJsonFactory(this);
        } else {
            this._jsonFactory = jf;
            if (jf.getCodec() == null) {
                this._jsonFactory.setCodec(this);
            }
        }
        this._subtypeResolver = new StdSubtypeResolver();
        RootNameLookup rootNames = new RootNameLookup();
        this._typeFactory = TypeFactory.defaultInstance();
        this._mixIns = mixins = new SimpleMixInResolver(null);
        BaseSettings base = DEFAULT_BASE.withClassIntrospector(this.defaultClassIntrospector());
        this._configOverrides = new ConfigOverrides();
        this._serializationConfig = new SerializationConfig(base, this._subtypeResolver, mixins, rootNames, this._configOverrides);
        this._deserializationConfig = new DeserializationConfig(base, this._subtypeResolver, mixins, rootNames, this._configOverrides);
        boolean needOrder = this._jsonFactory.requiresPropertyOrdering();
        if (needOrder ^ this._serializationConfig.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)) {
            this.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, needOrder);
        }
        this._serializerProvider = sp == null ? new DefaultSerializerProvider.Impl() : sp;
        this._deserializationContext = dc2 == null ? new DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance) : dc2;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    protected ClassIntrospector defaultClassIntrospector() {
        return new BasicClassIntrospector();
    }

    public ObjectMapper copy() {
        this._checkInvalidCopy(ObjectMapper.class);
        return new ObjectMapper(this);
    }

    protected void _checkInvalidCopy(Class<?> exp) {
        if (this.getClass() != exp) {
            throw new IllegalStateException("Failed copy(): " + this.getClass().getName() + " (version: " + this.version() + ") does not override copy(); it has to");
        }
    }

    protected ObjectReader _newReader(DeserializationConfig config) {
        return new ObjectReader(this, config);
    }

    protected ObjectReader _newReader(DeserializationConfig config, JavaType valueType, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues) {
        return new ObjectReader(this, config, valueType, valueToUpdate, schema, injectableValues);
    }

    protected ObjectWriter _newWriter(SerializationConfig config) {
        return new ObjectWriter(this, config);
    }

    protected ObjectWriter _newWriter(SerializationConfig config, FormatSchema schema) {
        return new ObjectWriter(this, config, schema);
    }

    protected ObjectWriter _newWriter(SerializationConfig config, JavaType rootType, PrettyPrinter pp) {
        return new ObjectWriter(this, config, rootType, pp);
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    public ObjectMapper registerModule(Module module) {
        String name;
        Object typeId;
        if (this.isEnabled(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS) && (typeId = module.getTypeId()) != null) {
            if (this._registeredModuleTypes == null) {
                this._registeredModuleTypes = new LinkedHashSet<Object>();
            }
            if (!this._registeredModuleTypes.add(typeId)) {
                return this;
            }
        }
        if ((name = module.getModuleName()) == null) {
            throw new IllegalArgumentException("Module without defined name");
        }
        Version version = module.version();
        if (version == null) {
            throw new IllegalArgumentException("Module without defined version");
        }
        module.setupModule(new Module.SetupContext(){

            @Override
            public Version getMapperVersion() {
                return ObjectMapper.this.version();
            }

            @Override
            public <C extends ObjectCodec> C getOwner() {
                return (C)ObjectMapper.this;
            }

            @Override
            public TypeFactory getTypeFactory() {
                return ObjectMapper.this._typeFactory;
            }

            @Override
            public boolean isEnabled(MapperFeature f10) {
                return ObjectMapper.this.isEnabled(f10);
            }

            @Override
            public boolean isEnabled(DeserializationFeature f10) {
                return ObjectMapper.this.isEnabled(f10);
            }

            @Override
            public boolean isEnabled(SerializationFeature f10) {
                return ObjectMapper.this.isEnabled(f10);
            }

            @Override
            public boolean isEnabled(JsonFactory.Feature f10) {
                return ObjectMapper.this.isEnabled(f10);
            }

            @Override
            public boolean isEnabled(JsonParser.Feature f10) {
                return ObjectMapper.this.isEnabled(f10);
            }

            @Override
            public boolean isEnabled(JsonGenerator.Feature f10) {
                return ObjectMapper.this.isEnabled(f10);
            }

            @Override
            public MutableConfigOverride configOverride(Class<?> type) {
                return ObjectMapper.this.configOverride(type);
            }

            @Override
            public void addDeserializers(Deserializers d10) {
                DeserializerFactory df2 = ObjectMapper.this._deserializationContext._factory.withAdditionalDeserializers(d10);
                ObjectMapper.this._deserializationContext = ObjectMapper.this._deserializationContext.with(df2);
            }

            @Override
            public void addKeyDeserializers(KeyDeserializers d10) {
                DeserializerFactory df2 = ObjectMapper.this._deserializationContext._factory.withAdditionalKeyDeserializers(d10);
                ObjectMapper.this._deserializationContext = ObjectMapper.this._deserializationContext.with(df2);
            }

            @Override
            public void addBeanDeserializerModifier(BeanDeserializerModifier modifier) {
                DeserializerFactory df2 = ObjectMapper.this._deserializationContext._factory.withDeserializerModifier(modifier);
                ObjectMapper.this._deserializationContext = ObjectMapper.this._deserializationContext.with(df2);
            }

            @Override
            public void addSerializers(Serializers s2) {
                ObjectMapper.this._serializerFactory = ObjectMapper.this._serializerFactory.withAdditionalSerializers(s2);
            }

            @Override
            public void addKeySerializers(Serializers s2) {
                ObjectMapper.this._serializerFactory = ObjectMapper.this._serializerFactory.withAdditionalKeySerializers(s2);
            }

            @Override
            public void addBeanSerializerModifier(BeanSerializerModifier modifier) {
                ObjectMapper.this._serializerFactory = ObjectMapper.this._serializerFactory.withSerializerModifier(modifier);
            }

            @Override
            public void addAbstractTypeResolver(AbstractTypeResolver resolver) {
                DeserializerFactory df2 = ObjectMapper.this._deserializationContext._factory.withAbstractTypeResolver(resolver);
                ObjectMapper.this._deserializationContext = ObjectMapper.this._deserializationContext.with(df2);
            }

            @Override
            public void addTypeModifier(TypeModifier modifier) {
                TypeFactory f10 = ObjectMapper.this._typeFactory;
                f10 = f10.withModifier(modifier);
                ObjectMapper.this.setTypeFactory(f10);
            }

            @Override
            public void addValueInstantiators(ValueInstantiators instantiators) {
                DeserializerFactory df2 = ObjectMapper.this._deserializationContext._factory.withValueInstantiators(instantiators);
                ObjectMapper.this._deserializationContext = ObjectMapper.this._deserializationContext.with(df2);
            }

            @Override
            public void setClassIntrospector(ClassIntrospector ci2) {
                ObjectMapper.this._deserializationConfig = (DeserializationConfig)ObjectMapper.this._deserializationConfig.with(ci2);
                ObjectMapper.this._serializationConfig = (SerializationConfig)ObjectMapper.this._serializationConfig.with(ci2);
            }

            @Override
            public void insertAnnotationIntrospector(AnnotationIntrospector ai2) {
                ObjectMapper.this._deserializationConfig = (DeserializationConfig)ObjectMapper.this._deserializationConfig.withInsertedAnnotationIntrospector(ai2);
                ObjectMapper.this._serializationConfig = (SerializationConfig)ObjectMapper.this._serializationConfig.withInsertedAnnotationIntrospector(ai2);
            }

            @Override
            public void appendAnnotationIntrospector(AnnotationIntrospector ai2) {
                ObjectMapper.this._deserializationConfig = (DeserializationConfig)ObjectMapper.this._deserializationConfig.withAppendedAnnotationIntrospector(ai2);
                ObjectMapper.this._serializationConfig = (SerializationConfig)ObjectMapper.this._serializationConfig.withAppendedAnnotationIntrospector(ai2);
            }

            @Override
            public void registerSubtypes(Class<?> ... subtypes) {
                ObjectMapper.this.registerSubtypes(subtypes);
            }

            @Override
            public void registerSubtypes(NamedType ... subtypes) {
                ObjectMapper.this.registerSubtypes(subtypes);
            }

            @Override
            public void registerSubtypes(Collection<Class<?>> subtypes) {
                ObjectMapper.this.registerSubtypes(subtypes);
            }

            @Override
            public void setMixInAnnotations(Class<?> target, Class<?> mixinSource) {
                ObjectMapper.this.addMixIn(target, mixinSource);
            }

            @Override
            public void addDeserializationProblemHandler(DeserializationProblemHandler handler) {
                ObjectMapper.this.addHandler(handler);
            }

            @Override
            public void setNamingStrategy(PropertyNamingStrategy naming) {
                ObjectMapper.this.setPropertyNamingStrategy(naming);
            }
        });
        return this;
    }

    public ObjectMapper registerModules(Module ... modules) {
        for (Module module : modules) {
            this.registerModule(module);
        }
        return this;
    }

    public ObjectMapper registerModules(Iterable<Module> modules) {
        for (Module module : modules) {
            this.registerModule(module);
        }
        return this;
    }

    public static List<Module> findModules() {
        return ObjectMapper.findModules(null);
    }

    public static List<Module> findModules(ClassLoader classLoader) {
        ArrayList<Module> modules = new ArrayList<Module>();
        ServiceLoader<Module> loader = ObjectMapper.secureGetServiceLoader(Module.class, classLoader);
        for (Module module : loader) {
            modules.add(module);
        }
        return modules;
    }

    private static <T> ServiceLoader<T> secureGetServiceLoader(final Class<T> clazz, final ClassLoader classLoader) {
        SecurityManager sm = System.getSecurityManager();
        if (sm == null) {
            return classLoader == null ? ServiceLoader.load(clazz) : ServiceLoader.load(clazz, classLoader);
        }
        return (ServiceLoader)AccessController.doPrivileged(new PrivilegedAction<ServiceLoader<T>>(){

            @Override
            public ServiceLoader<T> run() {
                return classLoader == null ? ServiceLoader.load(clazz) : ServiceLoader.load(clazz, classLoader);
            }
        });
    }

    public ObjectMapper findAndRegisterModules() {
        return this.registerModules(ObjectMapper.findModules());
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public DeserializationContext getDeserializationContext() {
        return this._deserializationContext;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory f10) {
        this._serializerFactory = f10;
        return this;
    }

    public SerializerFactory getSerializerFactory() {
        return this._serializerFactory;
    }

    public ObjectMapper setSerializerProvider(DefaultSerializerProvider p2) {
        this._serializerProvider = p2;
        return this;
    }

    public SerializerProvider getSerializerProvider() {
        return this._serializerProvider;
    }

    public SerializerProvider getSerializerProviderInstance() {
        return this._serializerProvider(this._serializationConfig);
    }

    public ObjectMapper setMixIns(Map<Class<?>, Class<?>> sourceMixins) {
        this._mixIns.setLocalDefinitions(sourceMixins);
        return this;
    }

    public ObjectMapper addMixIn(Class<?> target, Class<?> mixinSource) {
        this._mixIns.addLocalDefinition(target, mixinSource);
        return this;
    }

    public ObjectMapper setMixInResolver(ClassIntrospector.MixInResolver resolver) {
        SimpleMixInResolver r2 = this._mixIns.withOverrides(resolver);
        if (r2 != this._mixIns) {
            this._mixIns = r2;
            this._deserializationConfig = new DeserializationConfig(this._deserializationConfig, r2);
            this._serializationConfig = new SerializationConfig(this._serializationConfig, r2);
        }
        return this;
    }

    public Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixIns.findMixInClassFor(cls);
    }

    public int mixInCount() {
        return this._mixIns.localSize();
    }

    @Deprecated
    public void setMixInAnnotations(Map<Class<?>, Class<?>> sourceMixins) {
        this.setMixIns(sourceMixins);
    }

    @Deprecated
    public final void addMixInAnnotations(Class<?> target, Class<?> mixinSource) {
        this.addMixIn(target, mixinSource);
    }

    public VisibilityChecker<?> getVisibilityChecker() {
        return this._serializationConfig.getDefaultVisibilityChecker();
    }

    public ObjectMapper setVisibility(VisibilityChecker<?> vc) {
        this._configOverrides.setDefaultVisibility(vc);
        return this;
    }

    public ObjectMapper setVisibility(PropertyAccessor forMethod, JsonAutoDetect.Visibility visibility) {
        VisibilityChecker<?> vc = this._configOverrides.getDefaultVisibility();
        vc = vc.withVisibility(forMethod, visibility);
        this._configOverrides.setDefaultVisibility(vc);
        return this;
    }

    public SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public ObjectMapper setSubtypeResolver(SubtypeResolver str) {
        this._subtypeResolver = str;
        this._deserializationConfig = this._deserializationConfig.with(str);
        this._serializationConfig = this._serializationConfig.with(str);
        return this;
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector ai2) {
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(ai2);
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(ai2);
        return this;
    }

    public ObjectMapper setAnnotationIntrospectors(AnnotationIntrospector serializerAI, AnnotationIntrospector deserializerAI) {
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(serializerAI);
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(deserializerAI);
        return this;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy s2) {
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(s2);
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(s2);
        return this;
    }

    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._serializationConfig.getPropertyNamingStrategy();
    }

    public ObjectMapper setDefaultPrettyPrinter(PrettyPrinter pp) {
        this._serializationConfig = this._serializationConfig.withDefaultPrettyPrinter(pp);
        return this;
    }

    @Deprecated
    public void setVisibilityChecker(VisibilityChecker<?> vc) {
        this.setVisibility(vc);
    }

    public ObjectMapper setSerializationInclusion(JsonInclude.Include incl) {
        this.setPropertyInclusion(JsonInclude.Value.construct(incl, incl));
        return this;
    }

    @Deprecated
    public ObjectMapper setPropertyInclusion(JsonInclude.Value incl) {
        return this.setDefaultPropertyInclusion(incl);
    }

    public ObjectMapper setDefaultPropertyInclusion(JsonInclude.Value incl) {
        this._configOverrides.setDefaultInclusion(incl);
        return this;
    }

    public ObjectMapper setDefaultPropertyInclusion(JsonInclude.Include incl) {
        this._configOverrides.setDefaultInclusion(JsonInclude.Value.construct(incl, incl));
        return this;
    }

    public ObjectMapper setDefaultSetterInfo(JsonSetter.Value v2) {
        this._configOverrides.setDefaultSetterInfo(v2);
        return this;
    }

    public ObjectMapper setDefaultVisibility(JsonAutoDetect.Value vis) {
        this._configOverrides.setDefaultVisibility(VisibilityChecker.Std.construct(vis));
        return this;
    }

    public ObjectMapper setDefaultMergeable(Boolean b10) {
        this._configOverrides.setDefaultMergeable(b10);
        return this;
    }

    public ObjectMapper enableDefaultTyping() {
        return this.enableDefaultTyping(DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping dti) {
        return this.enableDefaultTyping(dti, JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping applicability, JsonTypeInfo.As includeAs) {
        if (includeAs == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
            throw new IllegalArgumentException("Cannot use includeAs of " + (Object)((Object)includeAs));
        }
        DefaultTypeResolverBuilder typer = new DefaultTypeResolverBuilder(applicability);
        typer = typer.init(JsonTypeInfo.Id.CLASS, null);
        typer = typer.inclusion(includeAs);
        return this.setDefaultTyping(typer);
    }

    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping applicability, String propertyName) {
        DefaultTypeResolverBuilder typer = new DefaultTypeResolverBuilder(applicability);
        typer = typer.init(JsonTypeInfo.Id.CLASS, null);
        typer = typer.inclusion(JsonTypeInfo.As.PROPERTY);
        typer = typer.typeProperty(propertyName);
        return this.setDefaultTyping(typer);
    }

    public ObjectMapper disableDefaultTyping() {
        return this.setDefaultTyping(null);
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder<?> typer) {
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(typer);
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(typer);
        return this;
    }

    public void registerSubtypes(Class<?> ... classes) {
        this.getSubtypeResolver().registerSubtypes(classes);
    }

    public void registerSubtypes(NamedType ... types) {
        this.getSubtypeResolver().registerSubtypes(types);
    }

    public void registerSubtypes(Collection<Class<?>> subtypes) {
        this.getSubtypeResolver().registerSubtypes(subtypes);
    }

    public MutableConfigOverride configOverride(Class<?> type) {
        return this._configOverrides.findOrCreateOverride(type);
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public ObjectMapper setTypeFactory(TypeFactory f10) {
        this._typeFactory = f10;
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(f10);
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(f10);
        return this;
    }

    public JavaType constructType(Type t2) {
        return this._typeFactory.constructType(t2);
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory f10) {
        this._deserializationConfig = this._deserializationConfig.with(f10);
        return this;
    }

    public ObjectMapper addHandler(DeserializationProblemHandler h2) {
        this._deserializationConfig = this._deserializationConfig.withHandler(h2);
        return this;
    }

    public ObjectMapper clearProblemHandlers() {
        this._deserializationConfig = this._deserializationConfig.withNoProblemHandlers();
        return this;
    }

    public ObjectMapper setConfig(DeserializationConfig config) {
        this._deserializationConfig = config;
        return this;
    }

    @Deprecated
    public void setFilters(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
    }

    public ObjectMapper setFilterProvider(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
        return this;
    }

    public ObjectMapper setBase64Variant(Base64Variant v2) {
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(v2);
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(v2);
        return this;
    }

    public ObjectMapper setConfig(SerializationConfig config) {
        this._serializationConfig = config;
        return this;
    }

    @Override
    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    @Override
    @Deprecated
    public JsonFactory getJsonFactory() {
        return this.getFactory();
    }

    public ObjectMapper setDateFormat(DateFormat dateFormat) {
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(dateFormat);
        this._serializationConfig = this._serializationConfig.with(dateFormat);
        return this;
    }

    public DateFormat getDateFormat() {
        return this._serializationConfig.getDateFormat();
    }

    public Object setHandlerInstantiator(HandlerInstantiator hi) {
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(hi);
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(hi);
        return this;
    }

    public ObjectMapper setInjectableValues(InjectableValues injectableValues) {
        this._injectableValues = injectableValues;
        return this;
    }

    public InjectableValues getInjectableValues() {
        return this._injectableValues;
    }

    public ObjectMapper setLocale(Locale l2) {
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(l2);
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(l2);
        return this;
    }

    public ObjectMapper setTimeZone(TimeZone tz) {
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(tz);
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(tz);
        return this;
    }

    public boolean isEnabled(MapperFeature f10) {
        return this._serializationConfig.isEnabled(f10);
    }

    public ObjectMapper configure(MapperFeature f10, boolean state) {
        this._serializationConfig = state ? (SerializationConfig)this._serializationConfig.with(new MapperFeature[]{f10}) : (SerializationConfig)this._serializationConfig.without(new MapperFeature[]{f10});
        this._deserializationConfig = state ? (DeserializationConfig)this._deserializationConfig.with(new MapperFeature[]{f10}) : (DeserializationConfig)this._deserializationConfig.without(new MapperFeature[]{f10});
        return this;
    }

    public ObjectMapper enable(MapperFeature ... f10) {
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.with(f10);
        this._serializationConfig = (SerializationConfig)this._serializationConfig.with(f10);
        return this;
    }

    public ObjectMapper disable(MapperFeature ... f10) {
        this._deserializationConfig = (DeserializationConfig)this._deserializationConfig.without(f10);
        this._serializationConfig = (SerializationConfig)this._serializationConfig.without(f10);
        return this;
    }

    public boolean isEnabled(SerializationFeature f10) {
        return this._serializationConfig.isEnabled(f10);
    }

    public ObjectMapper configure(SerializationFeature f10, boolean state) {
        this._serializationConfig = state ? this._serializationConfig.with(f10) : this._serializationConfig.without(f10);
        return this;
    }

    public ObjectMapper enable(SerializationFeature f10) {
        this._serializationConfig = this._serializationConfig.with(f10);
        return this;
    }

    public ObjectMapper enable(SerializationFeature first, SerializationFeature ... f10) {
        this._serializationConfig = this._serializationConfig.with(first, f10);
        return this;
    }

    public ObjectMapper disable(SerializationFeature f10) {
        this._serializationConfig = this._serializationConfig.without(f10);
        return this;
    }

    public ObjectMapper disable(SerializationFeature first, SerializationFeature ... f10) {
        this._serializationConfig = this._serializationConfig.without(first, f10);
        return this;
    }

    public boolean isEnabled(DeserializationFeature f10) {
        return this._deserializationConfig.isEnabled(f10);
    }

    public ObjectMapper configure(DeserializationFeature f10, boolean state) {
        this._deserializationConfig = state ? this._deserializationConfig.with(f10) : this._deserializationConfig.without(f10);
        return this;
    }

    public ObjectMapper enable(DeserializationFeature feature) {
        this._deserializationConfig = this._deserializationConfig.with(feature);
        return this;
    }

    public ObjectMapper enable(DeserializationFeature first, DeserializationFeature ... f10) {
        this._deserializationConfig = this._deserializationConfig.with(first, f10);
        return this;
    }

    public ObjectMapper disable(DeserializationFeature feature) {
        this._deserializationConfig = this._deserializationConfig.without(feature);
        return this;
    }

    public ObjectMapper disable(DeserializationFeature first, DeserializationFeature ... f10) {
        this._deserializationConfig = this._deserializationConfig.without(first, f10);
        return this;
    }

    public boolean isEnabled(JsonParser.Feature f10) {
        return this._deserializationConfig.isEnabled(f10, this._jsonFactory);
    }

    public ObjectMapper configure(JsonParser.Feature f10, boolean state) {
        this._jsonFactory.configure(f10, state);
        return this;
    }

    public ObjectMapper enable(JsonParser.Feature ... features) {
        for (JsonParser.Feature f10 : features) {
            this._jsonFactory.enable(f10);
        }
        return this;
    }

    public ObjectMapper disable(JsonParser.Feature ... features) {
        for (JsonParser.Feature f10 : features) {
            this._jsonFactory.disable(f10);
        }
        return this;
    }

    public boolean isEnabled(JsonGenerator.Feature f10) {
        return this._serializationConfig.isEnabled(f10, this._jsonFactory);
    }

    public ObjectMapper configure(JsonGenerator.Feature f10, boolean state) {
        this._jsonFactory.configure(f10, state);
        return this;
    }

    public ObjectMapper enable(JsonGenerator.Feature ... features) {
        for (JsonGenerator.Feature f10 : features) {
            this._jsonFactory.enable(f10);
        }
        return this;
    }

    public ObjectMapper disable(JsonGenerator.Feature ... features) {
        for (JsonGenerator.Feature f10 : features) {
            this._jsonFactory.disable(f10);
        }
        return this;
    }

    public boolean isEnabled(JsonFactory.Feature f10) {
        return this._jsonFactory.isEnabled(f10);
    }

    @Override
    public <T> T readValue(JsonParser p2, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readValue(this.getDeserializationConfig(), p2, this._typeFactory.constructType(valueType));
    }

    @Override
    public <T> T readValue(JsonParser p2, TypeReference<?> valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readValue(this.getDeserializationConfig(), p2, this._typeFactory.constructType(valueTypeRef));
    }

    @Override
    public final <T> T readValue(JsonParser p2, ResolvedType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readValue(this.getDeserializationConfig(), p2, (JavaType)valueType);
    }

    public <T> T readValue(JsonParser p2, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readValue(this.getDeserializationConfig(), p2, valueType);
    }

    @Override
    public <T extends TreeNode> T readTree(JsonParser p2) throws IOException, JsonProcessingException {
        DeserializationConfig cfg = this.getDeserializationConfig();
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == null && (t2 = p2.nextToken()) == null) {
            return null;
        }
        JsonNode n2 = (JsonNode)this._readValue(cfg, p2, JSON_NODE_TYPE);
        if (n2 == null) {
            n2 = this.getNodeFactory().nullNode();
        }
        JsonNode result = n2;
        return (T)result;
    }

    public <T> MappingIterator<T> readValues(JsonParser p2, ResolvedType valueType) throws IOException, JsonProcessingException {
        return this.readValues(p2, (JavaType)valueType);
    }

    public <T> MappingIterator<T> readValues(JsonParser p2, JavaType valueType) throws IOException, JsonProcessingException {
        DeserializationConfig config = this.getDeserializationConfig();
        DefaultDeserializationContext ctxt = this.createDeserializationContext(p2, config);
        JsonDeserializer<Object> deser = this._findRootDeserializer(ctxt, valueType);
        return new MappingIterator(valueType, p2, ctxt, deser, false, null);
    }

    public <T> MappingIterator<T> readValues(JsonParser p2, Class<T> valueType) throws IOException, JsonProcessingException {
        return this.readValues(p2, this._typeFactory.constructType(valueType));
    }

    public <T> MappingIterator<T> readValues(JsonParser p2, TypeReference<?> valueTypeRef) throws IOException, JsonProcessingException {
        return this.readValues(p2, this._typeFactory.constructType(valueTypeRef));
    }

    public JsonNode readTree(InputStream in) throws IOException {
        return this._readTreeAndClose(this._jsonFactory.createParser(in));
    }

    public JsonNode readTree(Reader r2) throws IOException {
        return this._readTreeAndClose(this._jsonFactory.createParser(r2));
    }

    public JsonNode readTree(String content) throws IOException {
        return this._readTreeAndClose(this._jsonFactory.createParser(content));
    }

    public JsonNode readTree(byte[] content) throws IOException {
        return this._readTreeAndClose(this._jsonFactory.createParser(content));
    }

    public JsonNode readTree(File file) throws IOException, JsonProcessingException {
        return this._readTreeAndClose(this._jsonFactory.createParser(file));
    }

    public JsonNode readTree(URL source) throws IOException {
        return this._readTreeAndClose(this._jsonFactory.createParser(source));
    }

    @Override
    public void writeValue(JsonGenerator g10, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        SerializationConfig config = this.getSerializationConfig();
        if (config.isEnabled(SerializationFeature.INDENT_OUTPUT) && g10.getPrettyPrinter() == null) {
            g10.setPrettyPrinter(config.constructDefaultPrettyPrinter());
        }
        if (config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && value instanceof Closeable) {
            this._writeCloseableValue(g10, value, config);
        } else {
            this._serializerProvider(config).serializeValue(g10, value);
            if (config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                g10.flush();
            }
        }
    }

    @Override
    public void writeTree(JsonGenerator jgen, TreeNode rootNode) throws IOException, JsonProcessingException {
        SerializationConfig config = this.getSerializationConfig();
        this._serializerProvider(config).serializeValue(jgen, rootNode);
        if (config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jgen.flush();
        }
    }

    public void writeTree(JsonGenerator jgen, JsonNode rootNode) throws IOException, JsonProcessingException {
        SerializationConfig config = this.getSerializationConfig();
        this._serializerProvider(config).serializeValue(jgen, rootNode);
        if (config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jgen.flush();
        }
    }

    @Override
    public ObjectNode createObjectNode() {
        return this._deserializationConfig.getNodeFactory().objectNode();
    }

    @Override
    public ArrayNode createArrayNode() {
        return this._deserializationConfig.getNodeFactory().arrayNode();
    }

    @Override
    public JsonParser treeAsTokens(TreeNode n2) {
        return new TreeTraversingParser((JsonNode)n2, this);
    }

    @Override
    public <T> T treeToValue(TreeNode n2, Class<T> valueType) throws JsonProcessingException {
        try {
            Object ob;
            if (valueType != Object.class && valueType.isAssignableFrom(n2.getClass())) {
                return (T)n2;
            }
            if (n2.asToken() == JsonToken.VALUE_EMBEDDED_OBJECT && n2 instanceof POJONode && ((ob = ((POJONode)n2).getPojo()) == null || valueType.isInstance(ob))) {
                return (T)ob;
            }
            return this.readValue(this.treeAsTokens(n2), valueType);
        }
        catch (JsonProcessingException e10) {
            throw e10;
        }
        catch (IOException e11) {
            throw new IllegalArgumentException(e11.getMessage(), e11);
        }
    }

    public <T extends JsonNode> T valueToTree(Object fromValue) throws IllegalArgumentException {
        JsonNode result;
        if (fromValue == null) {
            return null;
        }
        TokenBuffer buf = new TokenBuffer(this, false);
        if (this.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            buf = buf.forceUseOfBigDecimal(true);
        }
        try {
            this.writeValue(buf, fromValue);
            JsonParser p2 = buf.asParser();
            result = (JsonNode)this.readTree(p2);
            p2.close();
        }
        catch (IOException e10) {
            throw new IllegalArgumentException(e10.getMessage(), e10);
        }
        return (T)result;
    }

    public boolean canSerialize(Class<?> type) {
        return this._serializerProvider(this.getSerializationConfig()).hasSerializerFor(type, null);
    }

    public boolean canSerialize(Class<?> type, AtomicReference<Throwable> cause) {
        return this._serializerProvider(this.getSerializationConfig()).hasSerializerFor(type, cause);
    }

    public boolean canDeserialize(JavaType type) {
        return this.createDeserializationContext(null, this.getDeserializationConfig()).hasValueDeserializerFor(type, null);
    }

    public boolean canDeserialize(JavaType type, AtomicReference<Throwable> cause) {
        return this.createDeserializationContext(null, this.getDeserializationConfig()).hasValueDeserializerFor(type, cause);
    }

    public <T> T readValue(File src, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueType));
    }

    public <T> T readValue(File src, TypeReference valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueTypeRef));
    }

    public <T> T readValue(File src, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), valueType);
    }

    public <T> T readValue(URL src, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueType));
    }

    public <T> T readValue(URL src, TypeReference valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueTypeRef));
    }

    public <T> T readValue(URL src, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), valueType);
    }

    public <T> T readValue(String content, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(content), this._typeFactory.constructType(valueType));
    }

    public <T> T readValue(String content, TypeReference valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(content), this._typeFactory.constructType(valueTypeRef));
    }

    public <T> T readValue(String content, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(content), valueType);
    }

    public <T> T readValue(Reader src, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueType));
    }

    public <T> T readValue(Reader src, TypeReference valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueTypeRef));
    }

    public <T> T readValue(Reader src, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), valueType);
    }

    public <T> T readValue(InputStream src, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueType));
    }

    public <T> T readValue(InputStream src, TypeReference valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueTypeRef));
    }

    public <T> T readValue(InputStream src, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), valueType);
    }

    public <T> T readValue(byte[] src, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueType));
    }

    public <T> T readValue(byte[] src, int offset, int len, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src, offset, len), this._typeFactory.constructType(valueType));
    }

    public <T> T readValue(byte[] src, TypeReference valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueTypeRef));
    }

    public <T> T readValue(byte[] src, int offset, int len, TypeReference valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src, offset, len), this._typeFactory.constructType(valueTypeRef));
    }

    public <T> T readValue(byte[] src, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), valueType);
    }

    public <T> T readValue(byte[] src, int offset, int len, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src, offset, len), valueType);
    }

    public <T> T readValue(DataInput src, Class<T> valueType) throws IOException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), this._typeFactory.constructType(valueType));
    }

    public <T> T readValue(DataInput src, JavaType valueType) throws IOException {
        return (T)this._readMapAndClose(this._jsonFactory.createParser(src), valueType);
    }

    public void writeValue(File resultFile, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        this._configAndWriteValue(this._jsonFactory.createGenerator(resultFile, JsonEncoding.UTF8), value);
    }

    public void writeValue(OutputStream out, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        this._configAndWriteValue(this._jsonFactory.createGenerator(out, JsonEncoding.UTF8), value);
    }

    public void writeValue(DataOutput out, Object value) throws IOException {
        this._configAndWriteValue(this._jsonFactory.createGenerator(out, JsonEncoding.UTF8), value);
    }

    public void writeValue(Writer w2, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        this._configAndWriteValue(this._jsonFactory.createGenerator(w2), value);
    }

    public String writeValueAsString(Object value) throws JsonProcessingException {
        SegmentedStringWriter sw = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        try {
            this._configAndWriteValue(this._jsonFactory.createGenerator(sw), value);
        }
        catch (JsonProcessingException e10) {
            throw e10;
        }
        catch (IOException e11) {
            throw JsonMappingException.fromUnexpectedIOE(e11);
        }
        return sw.getAndClear();
    }

    public byte[] writeValueAsBytes(Object value) throws JsonProcessingException {
        ByteArrayBuilder bb2 = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
        try {
            this._configAndWriteValue(this._jsonFactory.createGenerator(bb2, JsonEncoding.UTF8), value);
        }
        catch (JsonProcessingException e10) {
            throw e10;
        }
        catch (IOException e11) {
            throw JsonMappingException.fromUnexpectedIOE(e11);
        }
        byte[] result = bb2.toByteArray();
        bb2.release();
        return result;
    }

    public ObjectWriter writer() {
        return this._newWriter(this.getSerializationConfig());
    }

    public ObjectWriter writer(SerializationFeature feature) {
        return this._newWriter(this.getSerializationConfig().with(feature));
    }

    public ObjectWriter writer(SerializationFeature first, SerializationFeature ... other) {
        return this._newWriter(this.getSerializationConfig().with(first, other));
    }

    public ObjectWriter writer(DateFormat df2) {
        return this._newWriter(this.getSerializationConfig().with(df2));
    }

    public ObjectWriter writerWithView(Class<?> serializationView) {
        return this._newWriter((SerializationConfig)this.getSerializationConfig().withView((Class)serializationView));
    }

    public ObjectWriter writerFor(Class<?> rootType) {
        return this._newWriter(this.getSerializationConfig(), rootType == null ? null : this._typeFactory.constructType(rootType), null);
    }

    public ObjectWriter writerFor(TypeReference<?> rootType) {
        return this._newWriter(this.getSerializationConfig(), rootType == null ? null : this._typeFactory.constructType(rootType), null);
    }

    public ObjectWriter writerFor(JavaType rootType) {
        return this._newWriter(this.getSerializationConfig(), rootType, null);
    }

    public ObjectWriter writer(PrettyPrinter pp) {
        if (pp == null) {
            pp = ObjectWriter.NULL_PRETTY_PRINTER;
        }
        return this._newWriter(this.getSerializationConfig(), null, pp);
    }

    public ObjectWriter writerWithDefaultPrettyPrinter() {
        SerializationConfig config = this.getSerializationConfig();
        return this._newWriter(config, null, config.getDefaultPrettyPrinter());
    }

    public ObjectWriter writer(FilterProvider filterProvider) {
        return this._newWriter(this.getSerializationConfig().withFilters(filterProvider));
    }

    public ObjectWriter writer(FormatSchema schema) {
        this._verifySchemaType(schema);
        return this._newWriter(this.getSerializationConfig(), schema);
    }

    public ObjectWriter writer(Base64Variant defaultBase64) {
        return this._newWriter((SerializationConfig)this.getSerializationConfig().with(defaultBase64));
    }

    public ObjectWriter writer(CharacterEscapes escapes) {
        return this._newWriter(this.getSerializationConfig()).with(escapes);
    }

    public ObjectWriter writer(ContextAttributes attrs) {
        return this._newWriter(this.getSerializationConfig().with(attrs));
    }

    @Deprecated
    public ObjectWriter writerWithType(Class<?> rootType) {
        return this._newWriter(this.getSerializationConfig(), rootType == null ? null : this._typeFactory.constructType(rootType), null);
    }

    @Deprecated
    public ObjectWriter writerWithType(TypeReference<?> rootType) {
        return this._newWriter(this.getSerializationConfig(), rootType == null ? null : this._typeFactory.constructType(rootType), null);
    }

    @Deprecated
    public ObjectWriter writerWithType(JavaType rootType) {
        return this._newWriter(this.getSerializationConfig(), rootType, null);
    }

    public ObjectReader reader() {
        return this._newReader(this.getDeserializationConfig()).with(this._injectableValues);
    }

    public ObjectReader reader(DeserializationFeature feature) {
        return this._newReader(this.getDeserializationConfig().with(feature));
    }

    public ObjectReader reader(DeserializationFeature first, DeserializationFeature ... other) {
        return this._newReader(this.getDeserializationConfig().with(first, other));
    }

    public ObjectReader readerForUpdating(Object valueToUpdate) {
        JavaType t2 = this._typeFactory.constructType(valueToUpdate.getClass());
        return this._newReader(this.getDeserializationConfig(), t2, valueToUpdate, null, this._injectableValues);
    }

    public ObjectReader readerFor(JavaType type) {
        return this._newReader(this.getDeserializationConfig(), type, null, null, this._injectableValues);
    }

    public ObjectReader readerFor(Class<?> type) {
        return this._newReader(this.getDeserializationConfig(), this._typeFactory.constructType(type), null, null, this._injectableValues);
    }

    public ObjectReader readerFor(TypeReference<?> type) {
        return this._newReader(this.getDeserializationConfig(), this._typeFactory.constructType(type), null, null, this._injectableValues);
    }

    public ObjectReader reader(JsonNodeFactory f10) {
        return this._newReader(this.getDeserializationConfig()).with(f10);
    }

    public ObjectReader reader(FormatSchema schema) {
        this._verifySchemaType(schema);
        return this._newReader(this.getDeserializationConfig(), null, null, schema, this._injectableValues);
    }

    public ObjectReader reader(InjectableValues injectableValues) {
        return this._newReader(this.getDeserializationConfig(), null, null, null, injectableValues);
    }

    public ObjectReader readerWithView(Class<?> view) {
        return this._newReader((DeserializationConfig)this.getDeserializationConfig().withView((Class)view));
    }

    public ObjectReader reader(Base64Variant defaultBase64) {
        return this._newReader((DeserializationConfig)this.getDeserializationConfig().with(defaultBase64));
    }

    public ObjectReader reader(ContextAttributes attrs) {
        return this._newReader(this.getDeserializationConfig().with(attrs));
    }

    @Deprecated
    public ObjectReader reader(JavaType type) {
        return this._newReader(this.getDeserializationConfig(), type, null, null, this._injectableValues);
    }

    @Deprecated
    public ObjectReader reader(Class<?> type) {
        return this._newReader(this.getDeserializationConfig(), this._typeFactory.constructType(type), null, null, this._injectableValues);
    }

    @Deprecated
    public ObjectReader reader(TypeReference<?> type) {
        return this._newReader(this.getDeserializationConfig(), this._typeFactory.constructType(type), null, null, this._injectableValues);
    }

    public <T> T convertValue(Object fromValue, Class<T> toValueType) throws IllegalArgumentException {
        return (T)this._convert(fromValue, this._typeFactory.constructType(toValueType));
    }

    public <T> T convertValue(Object fromValue, TypeReference<?> toValueTypeRef) throws IllegalArgumentException {
        return (T)this._convert(fromValue, this._typeFactory.constructType(toValueTypeRef));
    }

    public <T> T convertValue(Object fromValue, JavaType toValueType) throws IllegalArgumentException {
        return (T)this._convert(fromValue, toValueType);
    }

    protected Object _convert(Object fromValue, JavaType toValueType) throws IllegalArgumentException {
        Class<?> targetType;
        if (fromValue != null && (targetType = toValueType.getRawClass()) != Object.class && !toValueType.hasGenericTypes() && targetType.isAssignableFrom(fromValue.getClass())) {
            return fromValue;
        }
        TokenBuffer buf = new TokenBuffer(this, false);
        if (this.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            buf = buf.forceUseOfBigDecimal(true);
        }
        try {
            Object result;
            SerializationConfig config = this.getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE);
            this._serializerProvider(config).serializeValue(buf, fromValue);
            JsonParser p2 = buf.asParser();
            DeserializationConfig deserConfig = this.getDeserializationConfig();
            JsonToken t2 = this._initForReading(p2, toValueType);
            if (t2 == JsonToken.VALUE_NULL) {
                DefaultDeserializationContext ctxt = this.createDeserializationContext(p2, deserConfig);
                result = this._findRootDeserializer(ctxt, toValueType).getNullValue(ctxt);
            } else if (t2 == JsonToken.END_ARRAY || t2 == JsonToken.END_OBJECT) {
                result = null;
            } else {
                DefaultDeserializationContext ctxt = this.createDeserializationContext(p2, deserConfig);
                JsonDeserializer<Object> deser = this._findRootDeserializer(ctxt, toValueType);
                result = deser.deserialize(p2, ctxt);
            }
            p2.close();
            return result;
        }
        catch (IOException e10) {
            throw new IllegalArgumentException(e10.getMessage(), e10);
        }
    }

    public <T> T updateValue(T valueToUpdate, Object overrides) throws JsonMappingException {
        T result = valueToUpdate;
        if (valueToUpdate != null && overrides != null) {
            TokenBuffer buf = new TokenBuffer(this, false);
            if (this.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                buf = buf.forceUseOfBigDecimal(true);
            }
            try {
                SerializationConfig config = this.getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE);
                this._serializerProvider(config).serializeValue(buf, overrides);
                JsonParser p2 = buf.asParser();
                result = this.readerForUpdating(valueToUpdate).readValue(p2);
                p2.close();
            }
            catch (IOException e10) {
                if (e10 instanceof JsonMappingException) {
                    throw (JsonMappingException)e10;
                }
                throw JsonMappingException.fromUnexpectedIOE(e10);
            }
        }
        return result;
    }

    @Deprecated
    public JsonSchema generateJsonSchema(Class<?> t2) throws JsonMappingException {
        return this._serializerProvider(this.getSerializationConfig()).generateJsonSchema(t2);
    }

    public void acceptJsonFormatVisitor(Class<?> type, JsonFormatVisitorWrapper visitor) throws JsonMappingException {
        this.acceptJsonFormatVisitor(this._typeFactory.constructType(type), visitor);
    }

    public void acceptJsonFormatVisitor(JavaType type, JsonFormatVisitorWrapper visitor) throws JsonMappingException {
        if (type == null) {
            throw new IllegalArgumentException("type must be provided");
        }
        this._serializerProvider(this.getSerializationConfig()).acceptJsonFormatVisitor(type, visitor);
    }

    protected DefaultSerializerProvider _serializerProvider(SerializationConfig config) {
        return this._serializerProvider.createInstance(config, this._serializerFactory);
    }

    protected final void _configAndWriteValue(JsonGenerator g10, Object value) throws IOException {
        SerializationConfig cfg = this.getSerializationConfig();
        cfg.initialize(g10);
        if (cfg.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && value instanceof Closeable) {
            this._configAndWriteCloseable(g10, value, cfg);
            return;
        }
        try {
            this._serializerProvider(cfg).serializeValue(g10, value);
        }
        catch (Exception e10) {
            ClassUtil.closeOnFailAndThrowAsIOE(g10, e10);
            return;
        }
        g10.close();
    }

    private final void _configAndWriteCloseable(JsonGenerator g10, Object value, SerializationConfig cfg) throws IOException {
        Closeable toClose = (Closeable)value;
        try {
            this._serializerProvider(cfg).serializeValue(g10, value);
            Closeable tmpToClose = toClose;
            toClose = null;
            tmpToClose.close();
        }
        catch (Exception e10) {
            ClassUtil.closeOnFailAndThrowAsIOE(g10, toClose, e10);
            return;
        }
        g10.close();
    }

    private final void _writeCloseableValue(JsonGenerator g10, Object value, SerializationConfig cfg) throws IOException {
        Closeable toClose = (Closeable)value;
        try {
            this._serializerProvider(cfg).serializeValue(g10, value);
            if (cfg.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                g10.flush();
            }
        }
        catch (Exception e10) {
            ClassUtil.closeOnFailAndThrowAsIOE(null, toClose, e10);
            return;
        }
        toClose.close();
    }

    protected Object _readValue(DeserializationConfig cfg, JsonParser p2, JavaType valueType) throws IOException {
        Object result;
        JsonToken t2 = this._initForReading(p2, valueType);
        DefaultDeserializationContext ctxt = this.createDeserializationContext(p2, cfg);
        if (t2 == JsonToken.VALUE_NULL) {
            result = this._findRootDeserializer(ctxt, valueType).getNullValue(ctxt);
        } else if (t2 == JsonToken.END_ARRAY || t2 == JsonToken.END_OBJECT) {
            result = null;
        } else {
            JsonDeserializer<Object> deser = this._findRootDeserializer(ctxt, valueType);
            result = cfg.useRootWrapping() ? this._unwrapAndDeserialize(p2, ctxt, cfg, valueType, deser) : deser.deserialize(p2, ctxt);
        }
        p2.clearCurrentToken();
        if (cfg.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(p2, ctxt, valueType);
        }
        return result;
    }

    protected Object _readMapAndClose(JsonParser p0, JavaType valueType) throws IOException {
        try (JsonParser p2 = p0;){
            Object result;
            JsonToken t2 = this._initForReading(p2, valueType);
            DeserializationConfig cfg = this.getDeserializationConfig();
            DefaultDeserializationContext ctxt = this.createDeserializationContext(p2, cfg);
            if (t2 == JsonToken.VALUE_NULL) {
                result = this._findRootDeserializer(ctxt, valueType).getNullValue(ctxt);
            } else if (t2 == JsonToken.END_ARRAY || t2 == JsonToken.END_OBJECT) {
                result = null;
            } else {
                JsonDeserializer<Object> deser = this._findRootDeserializer(ctxt, valueType);
                result = cfg.useRootWrapping() ? this._unwrapAndDeserialize(p2, ctxt, cfg, valueType, deser) : deser.deserialize(p2, ctxt);
                ((DeserializationContext)ctxt).checkUnresolvedObjectId();
            }
            if (cfg.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                this._verifyNoTrailingTokens(p2, ctxt, valueType);
            }
            Object object = result;
            return object;
        }
    }

    protected JsonNode _readTreeAndClose(JsonParser p0) throws IOException {
        try (JsonParser p2 = p0;){
            Object result;
            JavaType valueType = JSON_NODE_TYPE;
            DeserializationConfig cfg = this.getDeserializationConfig();
            cfg.initialize(p2);
            JsonToken t2 = p2.getCurrentToken();
            if (t2 == null && (t2 = p2.nextToken()) == null) {
                JsonNode jsonNode = null;
                return jsonNode;
            }
            if (t2 == JsonToken.VALUE_NULL) {
                NullNode nullNode = cfg.getNodeFactory().nullNode();
                return nullNode;
            }
            DefaultDeserializationContext ctxt = this.createDeserializationContext(p2, cfg);
            JsonDeserializer<Object> deser = this._findRootDeserializer(ctxt, valueType);
            if (cfg.useRootWrapping()) {
                result = this._unwrapAndDeserialize(p2, ctxt, cfg, valueType, deser);
            } else {
                result = deser.deserialize(p2, ctxt);
                if (cfg.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                    this._verifyNoTrailingTokens(p2, ctxt, valueType);
                }
            }
            JsonNode jsonNode = (JsonNode)result;
            return jsonNode;
        }
    }

    protected Object _unwrapAndDeserialize(JsonParser p2, DeserializationContext ctxt, DeserializationConfig config, JavaType rootType, JsonDeserializer<Object> deser) throws IOException {
        String actualName;
        PropertyName expRootName = config.findRootName(rootType);
        String expSimpleName = expRootName.getSimpleName();
        if (p2.getCurrentToken() != JsonToken.START_OBJECT) {
            ctxt.reportWrongTokenException(rootType, JsonToken.START_OBJECT, "Current token not START_OBJECT (needed to unwrap root name '%s'), but %s", new Object[]{expSimpleName, p2.getCurrentToken()});
        }
        if (p2.nextToken() != JsonToken.FIELD_NAME) {
            ctxt.reportWrongTokenException(rootType, JsonToken.FIELD_NAME, "Current token not FIELD_NAME (to contain expected root name '" + expSimpleName + "'), but " + (Object)((Object)p2.getCurrentToken()), new Object[0]);
        }
        if (!expSimpleName.equals(actualName = p2.getCurrentName())) {
            ctxt.reportInputMismatch(rootType, "Root name '%s' does not match expected ('%s') for type %s", actualName, expSimpleName);
        }
        p2.nextToken();
        Object result = deser.deserialize(p2, ctxt);
        if (p2.nextToken() != JsonToken.END_OBJECT) {
            ctxt.reportWrongTokenException(rootType, JsonToken.END_OBJECT, "Current token not END_OBJECT (to match wrapper object with root name '%s'), but %s", new Object[]{expSimpleName, p2.getCurrentToken()});
        }
        if (config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(p2, ctxt, rootType);
        }
        return result;
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser p2, DeserializationConfig cfg) {
        return this._deserializationContext.createInstance(cfg, p2, this._injectableValues);
    }

    protected JsonToken _initForReading(JsonParser p2, JavaType targetType) throws IOException {
        this._deserializationConfig.initialize(p2);
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == null && (t2 = p2.nextToken()) == null) {
            throw MismatchedInputException.from(p2, targetType, "No content to map due to end-of-input");
        }
        return t2;
    }

    @Deprecated
    protected JsonToken _initForReading(JsonParser p2) throws IOException {
        return this._initForReading(p2, null);
    }

    protected final void _verifyNoTrailingTokens(JsonParser p2, DeserializationContext ctxt, JavaType bindType) throws IOException {
        JsonToken t2 = p2.nextToken();
        if (t2 != null) {
            Class<?> bt2 = ClassUtil.rawClass(bindType);
            ctxt.reportTrailingTokens(bt2, p2, t2);
        }
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext ctxt, JavaType valueType) throws JsonMappingException {
        JsonDeserializer<Object> deser = this._rootDeserializers.get(valueType);
        if (deser != null) {
            return deser;
        }
        deser = ctxt.findRootValueDeserializer(valueType);
        if (deser == null) {
            return (JsonDeserializer)ctxt.reportBadDefinition(valueType, "Cannot find a deserializer for type " + valueType);
        }
        this._rootDeserializers.put(valueType, deser);
        return deser;
    }

    protected void _verifySchemaType(FormatSchema schema) {
        if (schema != null && !this._jsonFactory.canUseSchema(schema)) {
            throw new IllegalArgumentException("Cannot use FormatSchema of type " + schema.getClass().getName() + " for format " + this._jsonFactory.getFormatName());
        }
    }

    public static class DefaultTypeResolverBuilder
    extends StdTypeResolverBuilder
    implements Serializable {
        private static final long serialVersionUID = 1L;
        protected final DefaultTyping _appliesFor;

        public DefaultTypeResolverBuilder(DefaultTyping t2) {
            this._appliesFor = t2;
        }

        @Override
        public TypeDeserializer buildTypeDeserializer(DeserializationConfig config, JavaType baseType, Collection<NamedType> subtypes) {
            return this.useForType(baseType) ? super.buildTypeDeserializer(config, baseType, subtypes) : null;
        }

        @Override
        public TypeSerializer buildTypeSerializer(SerializationConfig config, JavaType baseType, Collection<NamedType> subtypes) {
            return this.useForType(baseType) ? super.buildTypeSerializer(config, baseType, subtypes) : null;
        }

        public boolean useForType(JavaType t2) {
            if (t2.isPrimitive()) {
                return false;
            }
            switch (this._appliesFor) {
                case NON_CONCRETE_AND_ARRAYS: {
                    while (t2.isArrayType()) {
                        t2 = t2.getContentType();
                    }
                }
                case OBJECT_AND_NON_CONCRETE: {
                    while (t2.isReferenceType()) {
                        t2 = t2.getReferencedType();
                    }
                    return t2.isJavaLangObject() || !t2.isConcrete() && !TreeNode.class.isAssignableFrom(t2.getRawClass());
                }
                case NON_FINAL: {
                    while (t2.isArrayType()) {
                        t2 = t2.getContentType();
                    }
                    while (t2.isReferenceType()) {
                        t2 = t2.getReferencedType();
                    }
                    return !t2.isFinal() && !TreeNode.class.isAssignableFrom(t2.getRawClass());
                }
            }
            return t2.isJavaLangObject();
        }
    }

    public static enum DefaultTyping {
        JAVA_LANG_OBJECT,
        OBJECT_AND_NON_CONCRETE,
        NON_CONCRETE_AND_ARRAYS,
        NON_FINAL;

    }
}

