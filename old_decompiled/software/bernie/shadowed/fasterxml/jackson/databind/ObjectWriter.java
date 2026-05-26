/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.FormatFeature;
import software.bernie.shadowed.fasterxml.jackson.core.FormatSchema;
import software.bernie.shadowed.fasterxml.jackson.core.JsonEncoding;
import software.bernie.shadowed.fasterxml.jackson.core.JsonFactory;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerationException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.PrettyPrinter;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.core.Versioned;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharacterEscapes;
import software.bernie.shadowed.fasterxml.jackson.core.io.SegmentedStringWriter;
import software.bernie.shadowed.fasterxml.jackson.core.io.SerializedString;
import software.bernie.shadowed.fasterxml.jackson.core.type.TypeReference;
import software.bernie.shadowed.fasterxml.jackson.core.util.ByteArrayBuilder;
import software.bernie.shadowed.fasterxml.jackson.core.util.Instantiatable;
import software.bernie.shadowed.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.ObjectMapper;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.SequenceWriter;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ContextAttributes;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.PackageVersion;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.FilterProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.SerializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class ObjectWriter
implements Versioned,
Serializable {
    private static final long serialVersionUID = 1L;
    protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
    protected final SerializationConfig _config;
    protected final DefaultSerializerProvider _serializerProvider;
    protected final SerializerFactory _serializerFactory;
    protected final JsonFactory _generatorFactory;
    protected final GeneratorSettings _generatorSettings;
    protected final Prefetch _prefetch;

    protected ObjectWriter(ObjectMapper mapper, SerializationConfig config, JavaType rootType, PrettyPrinter pp) {
        this._config = config;
        this._serializerProvider = mapper._serializerProvider;
        this._serializerFactory = mapper._serializerFactory;
        this._generatorFactory = mapper._jsonFactory;
        GeneratorSettings generatorSettings = this._generatorSettings = pp == null ? GeneratorSettings.empty : new GeneratorSettings(pp, null, null, null);
        if (rootType == null || rootType.hasRawClass(Object.class)) {
            this._prefetch = Prefetch.empty;
        } else {
            rootType = rootType.withStaticTyping();
            this._prefetch = Prefetch.empty.forRootType(this, rootType);
        }
    }

    protected ObjectWriter(ObjectMapper mapper, SerializationConfig config) {
        this._config = config;
        this._serializerProvider = mapper._serializerProvider;
        this._serializerFactory = mapper._serializerFactory;
        this._generatorFactory = mapper._jsonFactory;
        this._generatorSettings = GeneratorSettings.empty;
        this._prefetch = Prefetch.empty;
    }

    protected ObjectWriter(ObjectMapper mapper, SerializationConfig config, FormatSchema s2) {
        this._config = config;
        this._serializerProvider = mapper._serializerProvider;
        this._serializerFactory = mapper._serializerFactory;
        this._generatorFactory = mapper._jsonFactory;
        this._generatorSettings = s2 == null ? GeneratorSettings.empty : new GeneratorSettings(null, s2, null, null);
        this._prefetch = Prefetch.empty;
    }

    protected ObjectWriter(ObjectWriter base, SerializationConfig config, GeneratorSettings genSettings, Prefetch prefetch) {
        this._config = config;
        this._serializerProvider = base._serializerProvider;
        this._serializerFactory = base._serializerFactory;
        this._generatorFactory = base._generatorFactory;
        this._generatorSettings = genSettings;
        this._prefetch = prefetch;
    }

    protected ObjectWriter(ObjectWriter base, SerializationConfig config) {
        this._config = config;
        this._serializerProvider = base._serializerProvider;
        this._serializerFactory = base._serializerFactory;
        this._generatorFactory = base._generatorFactory;
        this._generatorSettings = base._generatorSettings;
        this._prefetch = base._prefetch;
    }

    protected ObjectWriter(ObjectWriter base, JsonFactory f10) {
        this._config = (SerializationConfig)base._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, f10.requiresPropertyOrdering());
        this._serializerProvider = base._serializerProvider;
        this._serializerFactory = base._serializerFactory;
        this._generatorFactory = f10;
        this._generatorSettings = base._generatorSettings;
        this._prefetch = base._prefetch;
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    protected ObjectWriter _new(ObjectWriter base, JsonFactory f10) {
        return new ObjectWriter(base, f10);
    }

    protected ObjectWriter _new(ObjectWriter base, SerializationConfig config) {
        if (config == this._config) {
            return this;
        }
        return new ObjectWriter(base, config);
    }

    protected ObjectWriter _new(GeneratorSettings genSettings, Prefetch prefetch) {
        if (this._generatorSettings == genSettings && this._prefetch == prefetch) {
            return this;
        }
        return new ObjectWriter(this, this._config, genSettings, prefetch);
    }

    protected SequenceWriter _newSequenceWriter(boolean wrapInArray, JsonGenerator gen, boolean managedInput) throws IOException {
        this._configureGenerator(gen);
        return new SequenceWriter(this._serializerProvider(), gen, managedInput, this._prefetch).init(wrapInArray);
    }

    public ObjectWriter with(SerializationFeature feature) {
        return this._new(this, this._config.with(feature));
    }

    public ObjectWriter with(SerializationFeature first, SerializationFeature ... other) {
        return this._new(this, this._config.with(first, other));
    }

    public ObjectWriter withFeatures(SerializationFeature ... features) {
        return this._new(this, this._config.withFeatures(features));
    }

    public ObjectWriter without(SerializationFeature feature) {
        return this._new(this, this._config.without(feature));
    }

    public ObjectWriter without(SerializationFeature first, SerializationFeature ... other) {
        return this._new(this, this._config.without(first, other));
    }

    public ObjectWriter withoutFeatures(SerializationFeature ... features) {
        return this._new(this, this._config.withoutFeatures(features));
    }

    public ObjectWriter with(JsonGenerator.Feature feature) {
        return this._new(this, this._config.with(feature));
    }

    public ObjectWriter withFeatures(JsonGenerator.Feature ... features) {
        return this._new(this, this._config.withFeatures(features));
    }

    public ObjectWriter without(JsonGenerator.Feature feature) {
        return this._new(this, this._config.without(feature));
    }

    public ObjectWriter withoutFeatures(JsonGenerator.Feature ... features) {
        return this._new(this, this._config.withoutFeatures(features));
    }

    public ObjectWriter with(FormatFeature feature) {
        return this._new(this, this._config.with(feature));
    }

    public ObjectWriter withFeatures(FormatFeature ... features) {
        return this._new(this, this._config.withFeatures(features));
    }

    public ObjectWriter without(FormatFeature feature) {
        return this._new(this, this._config.without(feature));
    }

    public ObjectWriter withoutFeatures(FormatFeature ... features) {
        return this._new(this, this._config.withoutFeatures(features));
    }

    public ObjectWriter forType(JavaType rootType) {
        return this._new(this._generatorSettings, this._prefetch.forRootType(this, rootType));
    }

    public ObjectWriter forType(Class<?> rootType) {
        if (rootType == Object.class) {
            return this.forType((JavaType)null);
        }
        return this.forType(this._config.constructType(rootType));
    }

    public ObjectWriter forType(TypeReference<?> rootType) {
        return this.forType(this._config.getTypeFactory().constructType(rootType.getType()));
    }

    @Deprecated
    public ObjectWriter withType(JavaType rootType) {
        return this.forType(rootType);
    }

    @Deprecated
    public ObjectWriter withType(Class<?> rootType) {
        return this.forType(rootType);
    }

    @Deprecated
    public ObjectWriter withType(TypeReference<?> rootType) {
        return this.forType(rootType);
    }

    public ObjectWriter with(DateFormat df2) {
        return this._new(this, this._config.with(df2));
    }

    public ObjectWriter withDefaultPrettyPrinter() {
        return this.with(this._config.getDefaultPrettyPrinter());
    }

    public ObjectWriter with(FilterProvider filterProvider) {
        if (filterProvider == this._config.getFilterProvider()) {
            return this;
        }
        return this._new(this, this._config.withFilters(filterProvider));
    }

    public ObjectWriter with(PrettyPrinter pp) {
        return this._new(this._generatorSettings.with(pp), this._prefetch);
    }

    public ObjectWriter withRootName(String rootName) {
        return this._new(this, (SerializationConfig)this._config.withRootName(rootName));
    }

    public ObjectWriter withRootName(PropertyName rootName) {
        return this._new(this, this._config.withRootName(rootName));
    }

    public ObjectWriter withoutRootName() {
        return this._new(this, this._config.withRootName(PropertyName.NO_NAME));
    }

    public ObjectWriter with(FormatSchema schema) {
        this._verifySchemaType(schema);
        return this._new(this._generatorSettings.with(schema), this._prefetch);
    }

    @Deprecated
    public ObjectWriter withSchema(FormatSchema schema) {
        return this.with(schema);
    }

    public ObjectWriter withView(Class<?> view) {
        return this._new(this, (SerializationConfig)this._config.withView((Class)view));
    }

    public ObjectWriter with(Locale l2) {
        return this._new(this, (SerializationConfig)this._config.with(l2));
    }

    public ObjectWriter with(TimeZone tz) {
        return this._new(this, (SerializationConfig)this._config.with(tz));
    }

    public ObjectWriter with(Base64Variant b64variant) {
        return this._new(this, (SerializationConfig)this._config.with(b64variant));
    }

    public ObjectWriter with(CharacterEscapes escapes) {
        return this._new(this._generatorSettings.with(escapes), this._prefetch);
    }

    public ObjectWriter with(JsonFactory f10) {
        return f10 == this._generatorFactory ? this : this._new(this, f10);
    }

    public ObjectWriter with(ContextAttributes attrs) {
        return this._new(this, this._config.with(attrs));
    }

    public ObjectWriter withAttributes(Map<?, ?> attrs) {
        return this._new(this, (SerializationConfig)this._config.withAttributes(attrs));
    }

    public ObjectWriter withAttribute(Object key, Object value) {
        return this._new(this, (SerializationConfig)this._config.withAttribute(key, value));
    }

    public ObjectWriter withoutAttribute(Object key) {
        return this._new(this, (SerializationConfig)this._config.withoutAttribute(key));
    }

    public ObjectWriter withRootValueSeparator(String sep) {
        return this._new(this._generatorSettings.withRootValueSeparator(sep), this._prefetch);
    }

    public ObjectWriter withRootValueSeparator(SerializableString sep) {
        return this._new(this._generatorSettings.withRootValueSeparator(sep), this._prefetch);
    }

    public SequenceWriter writeValues(File out) throws IOException {
        return this._newSequenceWriter(false, this._generatorFactory.createGenerator(out, JsonEncoding.UTF8), true);
    }

    public SequenceWriter writeValues(JsonGenerator gen) throws IOException {
        this._configureGenerator(gen);
        return this._newSequenceWriter(false, gen, false);
    }

    public SequenceWriter writeValues(Writer out) throws IOException {
        return this._newSequenceWriter(false, this._generatorFactory.createGenerator(out), true);
    }

    public SequenceWriter writeValues(OutputStream out) throws IOException {
        return this._newSequenceWriter(false, this._generatorFactory.createGenerator(out, JsonEncoding.UTF8), true);
    }

    public SequenceWriter writeValues(DataOutput out) throws IOException {
        return this._newSequenceWriter(false, this._generatorFactory.createGenerator(out), true);
    }

    public SequenceWriter writeValuesAsArray(File out) throws IOException {
        return this._newSequenceWriter(true, this._generatorFactory.createGenerator(out, JsonEncoding.UTF8), true);
    }

    public SequenceWriter writeValuesAsArray(JsonGenerator gen) throws IOException {
        return this._newSequenceWriter(true, gen, false);
    }

    public SequenceWriter writeValuesAsArray(Writer out) throws IOException {
        return this._newSequenceWriter(true, this._generatorFactory.createGenerator(out), true);
    }

    public SequenceWriter writeValuesAsArray(OutputStream out) throws IOException {
        return this._newSequenceWriter(true, this._generatorFactory.createGenerator(out, JsonEncoding.UTF8), true);
    }

    public SequenceWriter writeValuesAsArray(DataOutput out) throws IOException {
        return this._newSequenceWriter(true, this._generatorFactory.createGenerator(out), true);
    }

    public boolean isEnabled(SerializationFeature f10) {
        return this._config.isEnabled(f10);
    }

    public boolean isEnabled(MapperFeature f10) {
        return this._config.isEnabled(f10);
    }

    @Deprecated
    public boolean isEnabled(JsonParser.Feature f10) {
        return this._generatorFactory.isEnabled(f10);
    }

    public boolean isEnabled(JsonGenerator.Feature f10) {
        return this._generatorFactory.isEnabled(f10);
    }

    public SerializationConfig getConfig() {
        return this._config;
    }

    public JsonFactory getFactory() {
        return this._generatorFactory;
    }

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public boolean hasPrefetchedSerializer() {
        return this._prefetch.hasSerializer();
    }

    public ContextAttributes getAttributes() {
        return this._config.getAttributes();
    }

    public void writeValue(JsonGenerator gen, Object value) throws IOException {
        this._configureGenerator(gen);
        if (this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && value instanceof Closeable) {
            Closeable toClose = (Closeable)value;
            try {
                this._prefetch.serialize(gen, value, this._serializerProvider());
                if (this._config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                    gen.flush();
                }
            }
            catch (Exception e10) {
                ClassUtil.closeOnFailAndThrowAsIOE(null, toClose, e10);
                return;
            }
            toClose.close();
        } else {
            this._prefetch.serialize(gen, value, this._serializerProvider());
            if (this._config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                gen.flush();
            }
        }
    }

    public void writeValue(File resultFile, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        this._configAndWriteValue(this._generatorFactory.createGenerator(resultFile, JsonEncoding.UTF8), value);
    }

    public void writeValue(OutputStream out, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        this._configAndWriteValue(this._generatorFactory.createGenerator(out, JsonEncoding.UTF8), value);
    }

    public void writeValue(Writer w2, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        this._configAndWriteValue(this._generatorFactory.createGenerator(w2), value);
    }

    public void writeValue(DataOutput out, Object value) throws IOException {
        this._configAndWriteValue(this._generatorFactory.createGenerator(out), value);
    }

    public String writeValueAsString(Object value) throws JsonProcessingException {
        SegmentedStringWriter sw = new SegmentedStringWriter(this._generatorFactory._getBufferRecycler());
        try {
            this._configAndWriteValue(this._generatorFactory.createGenerator(sw), value);
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
        ByteArrayBuilder bb2 = new ByteArrayBuilder(this._generatorFactory._getBufferRecycler());
        try {
            this._configAndWriteValue(this._generatorFactory.createGenerator(bb2, JsonEncoding.UTF8), value);
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

    public void acceptJsonFormatVisitor(JavaType type, JsonFormatVisitorWrapper visitor) throws JsonMappingException {
        if (type == null) {
            throw new IllegalArgumentException("type must be provided");
        }
        this._serializerProvider().acceptJsonFormatVisitor(type, visitor);
    }

    public void acceptJsonFormatVisitor(Class<?> rawType, JsonFormatVisitorWrapper visitor) throws JsonMappingException {
        this.acceptJsonFormatVisitor(this._config.constructType(rawType), visitor);
    }

    public boolean canSerialize(Class<?> type) {
        return this._serializerProvider().hasSerializerFor(type, null);
    }

    public boolean canSerialize(Class<?> type, AtomicReference<Throwable> cause) {
        return this._serializerProvider().hasSerializerFor(type, cause);
    }

    protected DefaultSerializerProvider _serializerProvider() {
        return this._serializerProvider.createInstance(this._config, this._serializerFactory);
    }

    protected void _verifySchemaType(FormatSchema schema) {
        if (schema != null && !this._generatorFactory.canUseSchema(schema)) {
            throw new IllegalArgumentException("Cannot use FormatSchema of type " + schema.getClass().getName() + " for format " + this._generatorFactory.getFormatName());
        }
    }

    protected final void _configAndWriteValue(JsonGenerator gen, Object value) throws IOException {
        this._configureGenerator(gen);
        if (this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && value instanceof Closeable) {
            this._writeCloseable(gen, value);
            return;
        }
        try {
            this._prefetch.serialize(gen, value, this._serializerProvider());
        }
        catch (Exception e10) {
            ClassUtil.closeOnFailAndThrowAsIOE(gen, e10);
            return;
        }
        gen.close();
    }

    private final void _writeCloseable(JsonGenerator gen, Object value) throws IOException {
        Closeable toClose = (Closeable)value;
        try {
            this._prefetch.serialize(gen, value, this._serializerProvider());
            Closeable tmpToClose = toClose;
            toClose = null;
            tmpToClose.close();
        }
        catch (Exception e10) {
            ClassUtil.closeOnFailAndThrowAsIOE(gen, toClose, e10);
            return;
        }
        gen.close();
    }

    protected final void _configureGenerator(JsonGenerator gen) {
        this._config.initialize(gen);
        this._generatorSettings.initialize(gen);
    }

    public static final class Prefetch
    implements Serializable {
        private static final long serialVersionUID = 1L;
        public static final Prefetch empty = new Prefetch(null, null, null);
        private final JavaType rootType;
        private final JsonSerializer<Object> valueSerializer;
        private final TypeSerializer typeSerializer;

        private Prefetch(JavaType rootT, JsonSerializer<Object> ser, TypeSerializer typeSer) {
            this.rootType = rootT;
            this.valueSerializer = ser;
            this.typeSerializer = typeSer;
        }

        public Prefetch forRootType(ObjectWriter parent, JavaType newType) {
            boolean noType;
            boolean bl2 = noType = newType == null || newType.isJavaLangObject();
            if (noType) {
                if (this.rootType == null || this.valueSerializer == null) {
                    return this;
                }
                return new Prefetch(null, null, this.typeSerializer);
            }
            if (newType.equals(this.rootType)) {
                return this;
            }
            if (parent.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH)) {
                DefaultSerializerProvider prov = parent._serializerProvider();
                try {
                    JsonSerializer<Object> ser = prov.findTypedValueSerializer(newType, true, null);
                    if (ser instanceof TypeWrappedSerializer) {
                        return new Prefetch(newType, null, ((TypeWrappedSerializer)ser).typeSerializer());
                    }
                    return new Prefetch(newType, ser, null);
                }
                catch (JsonProcessingException e10) {
                    // empty catch block
                }
            }
            return new Prefetch(newType, null, this.typeSerializer);
        }

        public final JsonSerializer<Object> getValueSerializer() {
            return this.valueSerializer;
        }

        public final TypeSerializer getTypeSerializer() {
            return this.typeSerializer;
        }

        public boolean hasSerializer() {
            return this.valueSerializer != null || this.typeSerializer != null;
        }

        public void serialize(JsonGenerator gen, Object value, DefaultSerializerProvider prov) throws IOException {
            if (this.typeSerializer != null) {
                prov.serializePolymorphic(gen, value, this.rootType, this.valueSerializer, this.typeSerializer);
            } else if (this.valueSerializer != null) {
                prov.serializeValue(gen, value, this.rootType, this.valueSerializer);
            } else if (this.rootType != null) {
                prov.serializeValue(gen, value, this.rootType);
            } else {
                prov.serializeValue(gen, value);
            }
        }
    }

    public static final class GeneratorSettings
    implements Serializable {
        private static final long serialVersionUID = 1L;
        public static final GeneratorSettings empty = new GeneratorSettings(null, null, null, null);
        public final PrettyPrinter prettyPrinter;
        public final FormatSchema schema;
        public final CharacterEscapes characterEscapes;
        public final SerializableString rootValueSeparator;

        public GeneratorSettings(PrettyPrinter pp, FormatSchema sch, CharacterEscapes esc, SerializableString rootSep) {
            this.prettyPrinter = pp;
            this.schema = sch;
            this.characterEscapes = esc;
            this.rootValueSeparator = rootSep;
        }

        public GeneratorSettings with(PrettyPrinter pp) {
            if (pp == null) {
                pp = NULL_PRETTY_PRINTER;
            }
            return pp == this.prettyPrinter ? this : new GeneratorSettings(pp, this.schema, this.characterEscapes, this.rootValueSeparator);
        }

        public GeneratorSettings with(FormatSchema sch) {
            return this.schema == sch ? this : new GeneratorSettings(this.prettyPrinter, sch, this.characterEscapes, this.rootValueSeparator);
        }

        public GeneratorSettings with(CharacterEscapes esc) {
            return this.characterEscapes == esc ? this : new GeneratorSettings(this.prettyPrinter, this.schema, esc, this.rootValueSeparator);
        }

        public GeneratorSettings withRootValueSeparator(String sep) {
            if (sep == null) {
                if (this.rootValueSeparator == null) {
                    return this;
                }
                return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, null);
            }
            if (sep.equals(this._rootValueSeparatorAsString())) {
                return this;
            }
            return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, new SerializedString(sep));
        }

        public GeneratorSettings withRootValueSeparator(SerializableString sep) {
            if (sep == null) {
                if (this.rootValueSeparator == null) {
                    return this;
                }
                return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, null);
            }
            if (sep.equals(this.rootValueSeparator)) {
                return this;
            }
            return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, sep);
        }

        private final String _rootValueSeparatorAsString() {
            return this.rootValueSeparator == null ? null : this.rootValueSeparator.getValue();
        }

        public void initialize(JsonGenerator gen) {
            PrettyPrinter pp = this.prettyPrinter;
            if (this.prettyPrinter != null) {
                if (pp == NULL_PRETTY_PRINTER) {
                    gen.setPrettyPrinter(null);
                } else {
                    if (pp instanceof Instantiatable) {
                        pp = (PrettyPrinter)((Instantiatable)((Object)pp)).createInstance();
                    }
                    gen.setPrettyPrinter(pp);
                }
            }
            if (this.characterEscapes != null) {
                gen.setCharacterEscapes(this.characterEscapes);
            }
            if (this.schema != null) {
                gen.setSchema(this.schema);
            }
            if (this.rootValueSeparator != null) {
                gen.setRootValueSeparator(this.rootValueSeparator);
            }
        }
    }
}

