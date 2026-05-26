/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.FormatFeature;
import software.bernie.shadowed.fasterxml.jackson.core.FormatSchema;
import software.bernie.shadowed.fasterxml.jackson.core.JsonFactory;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParseException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonPointer;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;
import software.bernie.shadowed.fasterxml.jackson.core.TreeNode;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.core.Versioned;
import software.bernie.shadowed.fasterxml.jackson.core.filter.FilteringParserDelegate;
import software.bernie.shadowed.fasterxml.jackson.core.filter.JsonPointerBasedFilter;
import software.bernie.shadowed.fasterxml.jackson.core.filter.TokenFilter;
import software.bernie.shadowed.fasterxml.jackson.core.type.ResolvedType;
import software.bernie.shadowed.fasterxml.jackson.core.type.TypeReference;
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
import software.bernie.shadowed.fasterxml.jackson.databind.ObjectMapper;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ContextAttributes;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.PackageVersion;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DataFormatReaders;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.node.TreeTraversingParser;
import software.bernie.shadowed.fasterxml.jackson.databind.type.SimpleType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class ObjectReader
extends ObjectCodec
implements Versioned,
Serializable {
    private static final long serialVersionUID = 2L;
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final JsonFactory _parserFactory;
    protected final boolean _unwrapRoot;
    private final TokenFilter _filter;
    protected final JavaType _valueType;
    protected final JsonDeserializer<Object> _rootDeserializer;
    protected final Object _valueToUpdate;
    protected final FormatSchema _schema;
    protected final InjectableValues _injectableValues;
    protected final DataFormatReaders _dataFormatReaders;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;

    protected ObjectReader(ObjectMapper mapper, DeserializationConfig config) {
        this(mapper, config, null, null, null, null);
    }

    protected ObjectReader(ObjectMapper mapper, DeserializationConfig config, JavaType valueType, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues) {
        this._config = config;
        this._context = mapper._deserializationContext;
        this._rootDeserializers = mapper._rootDeserializers;
        this._parserFactory = mapper._jsonFactory;
        this._valueType = valueType;
        this._valueToUpdate = valueToUpdate;
        this._schema = schema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = config.useRootWrapping();
        this._rootDeserializer = this._prefetchRootDeserializer(valueType);
        this._dataFormatReaders = null;
        this._filter = null;
    }

    protected ObjectReader(ObjectReader base, DeserializationConfig config, JavaType valueType, JsonDeserializer<Object> rootDeser, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
        this._config = config;
        this._context = base._context;
        this._rootDeserializers = base._rootDeserializers;
        this._parserFactory = base._parserFactory;
        this._valueType = valueType;
        this._rootDeserializer = rootDeser;
        this._valueToUpdate = valueToUpdate;
        this._schema = schema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = config.useRootWrapping();
        this._dataFormatReaders = dataFormatReaders;
        this._filter = base._filter;
    }

    protected ObjectReader(ObjectReader base, DeserializationConfig config) {
        this._config = config;
        this._context = base._context;
        this._rootDeserializers = base._rootDeserializers;
        this._parserFactory = base._parserFactory;
        this._valueType = base._valueType;
        this._rootDeserializer = base._rootDeserializer;
        this._valueToUpdate = base._valueToUpdate;
        this._schema = base._schema;
        this._injectableValues = base._injectableValues;
        this._unwrapRoot = config.useRootWrapping();
        this._dataFormatReaders = base._dataFormatReaders;
        this._filter = base._filter;
    }

    protected ObjectReader(ObjectReader base, JsonFactory f10) {
        this._config = (DeserializationConfig)base._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, f10.requiresPropertyOrdering());
        this._context = base._context;
        this._rootDeserializers = base._rootDeserializers;
        this._parserFactory = f10;
        this._valueType = base._valueType;
        this._rootDeserializer = base._rootDeserializer;
        this._valueToUpdate = base._valueToUpdate;
        this._schema = base._schema;
        this._injectableValues = base._injectableValues;
        this._unwrapRoot = base._unwrapRoot;
        this._dataFormatReaders = base._dataFormatReaders;
        this._filter = base._filter;
    }

    protected ObjectReader(ObjectReader base, TokenFilter filter) {
        this._config = base._config;
        this._context = base._context;
        this._rootDeserializers = base._rootDeserializers;
        this._parserFactory = base._parserFactory;
        this._valueType = base._valueType;
        this._rootDeserializer = base._rootDeserializer;
        this._valueToUpdate = base._valueToUpdate;
        this._schema = base._schema;
        this._injectableValues = base._injectableValues;
        this._unwrapRoot = base._unwrapRoot;
        this._dataFormatReaders = base._dataFormatReaders;
        this._filter = filter;
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    protected ObjectReader _new(ObjectReader base, JsonFactory f10) {
        return new ObjectReader(base, f10);
    }

    protected ObjectReader _new(ObjectReader base, DeserializationConfig config) {
        return new ObjectReader(base, config);
    }

    protected ObjectReader _new(ObjectReader base, DeserializationConfig config, JavaType valueType, JsonDeserializer<Object> rootDeser, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
        return new ObjectReader(base, config, valueType, rootDeser, valueToUpdate, schema, injectableValues, dataFormatReaders);
    }

    protected <T> MappingIterator<T> _newIterator(JsonParser p2, DeserializationContext ctxt, JsonDeserializer<?> deser, boolean parserManaged) {
        return new MappingIterator(this._valueType, p2, ctxt, deser, parserManaged, this._valueToUpdate);
    }

    protected JsonToken _initForReading(DeserializationContext ctxt, JsonParser p2) throws IOException {
        if (this._schema != null) {
            p2.setSchema(this._schema);
        }
        this._config.initialize(p2);
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == null && (t2 = p2.nextToken()) == null) {
            ctxt.reportInputMismatch(this._valueType, "No content to map due to end-of-input", new Object[0]);
        }
        return t2;
    }

    protected void _initForMultiRead(DeserializationContext ctxt, JsonParser p2) throws IOException {
        if (this._schema != null) {
            p2.setSchema(this._schema);
        }
        this._config.initialize(p2);
    }

    public ObjectReader with(DeserializationFeature feature) {
        return this._with(this._config.with(feature));
    }

    public ObjectReader with(DeserializationFeature first, DeserializationFeature ... other) {
        return this._with(this._config.with(first, other));
    }

    public ObjectReader withFeatures(DeserializationFeature ... features) {
        return this._with(this._config.withFeatures(features));
    }

    public ObjectReader without(DeserializationFeature feature) {
        return this._with(this._config.without(feature));
    }

    public ObjectReader without(DeserializationFeature first, DeserializationFeature ... other) {
        return this._with(this._config.without(first, other));
    }

    public ObjectReader withoutFeatures(DeserializationFeature ... features) {
        return this._with(this._config.withoutFeatures(features));
    }

    public ObjectReader with(JsonParser.Feature feature) {
        return this._with(this._config.with(feature));
    }

    public ObjectReader withFeatures(JsonParser.Feature ... features) {
        return this._with(this._config.withFeatures(features));
    }

    public ObjectReader without(JsonParser.Feature feature) {
        return this._with(this._config.without(feature));
    }

    public ObjectReader withoutFeatures(JsonParser.Feature ... features) {
        return this._with(this._config.withoutFeatures(features));
    }

    public ObjectReader with(FormatFeature feature) {
        return this._with(this._config.with(feature));
    }

    public ObjectReader withFeatures(FormatFeature ... features) {
        return this._with(this._config.withFeatures(features));
    }

    public ObjectReader without(FormatFeature feature) {
        return this._with(this._config.without(feature));
    }

    public ObjectReader withoutFeatures(FormatFeature ... features) {
        return this._with(this._config.withoutFeatures(features));
    }

    public ObjectReader at(String value) {
        return new ObjectReader(this, new JsonPointerBasedFilter(value));
    }

    public ObjectReader at(JsonPointer pointer) {
        return new ObjectReader(this, new JsonPointerBasedFilter(pointer));
    }

    public ObjectReader with(DeserializationConfig config) {
        return this._with(config);
    }

    public ObjectReader with(InjectableValues injectableValues) {
        if (this._injectableValues == injectableValues) {
            return this;
        }
        return this._new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, injectableValues, this._dataFormatReaders);
    }

    public ObjectReader with(JsonNodeFactory f10) {
        return this._with(this._config.with(f10));
    }

    public ObjectReader with(JsonFactory f10) {
        if (f10 == this._parserFactory) {
            return this;
        }
        ObjectReader r2 = this._new(this, f10);
        if (f10.getCodec() == null) {
            f10.setCodec(r2);
        }
        return r2;
    }

    public ObjectReader withRootName(String rootName) {
        return this._with((DeserializationConfig)this._config.withRootName(rootName));
    }

    public ObjectReader withRootName(PropertyName rootName) {
        return this._with(this._config.withRootName(rootName));
    }

    public ObjectReader withoutRootName() {
        return this._with(this._config.withRootName(PropertyName.NO_NAME));
    }

    public ObjectReader with(FormatSchema schema) {
        if (this._schema == schema) {
            return this;
        }
        this._verifySchemaType(schema);
        return this._new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, schema, this._injectableValues, this._dataFormatReaders);
    }

    public ObjectReader forType(JavaType valueType) {
        if (valueType != null && valueType.equals(this._valueType)) {
            return this;
        }
        JsonDeserializer<Object> rootDeser = this._prefetchRootDeserializer(valueType);
        DataFormatReaders det = this._dataFormatReaders;
        if (det != null) {
            det = det.withType(valueType);
        }
        return this._new(this, this._config, valueType, rootDeser, this._valueToUpdate, this._schema, this._injectableValues, det);
    }

    public ObjectReader forType(Class<?> valueType) {
        return this.forType(this._config.constructType(valueType));
    }

    public ObjectReader forType(TypeReference<?> valueTypeRef) {
        return this.forType(this._config.getTypeFactory().constructType(valueTypeRef.getType()));
    }

    @Deprecated
    public ObjectReader withType(JavaType valueType) {
        return this.forType(valueType);
    }

    @Deprecated
    public ObjectReader withType(Class<?> valueType) {
        return this.forType(this._config.constructType(valueType));
    }

    @Deprecated
    public ObjectReader withType(Type valueType) {
        return this.forType(this._config.getTypeFactory().constructType(valueType));
    }

    @Deprecated
    public ObjectReader withType(TypeReference<?> valueTypeRef) {
        return this.forType(this._config.getTypeFactory().constructType(valueTypeRef.getType()));
    }

    public ObjectReader withValueToUpdate(Object value) {
        if (value == this._valueToUpdate) {
            return this;
        }
        if (value == null) {
            return this._new(this, this._config, this._valueType, this._rootDeserializer, null, this._schema, this._injectableValues, this._dataFormatReaders);
        }
        JavaType t2 = this._valueType == null ? this._config.constructType(value.getClass()) : this._valueType;
        return this._new(this, this._config, t2, this._rootDeserializer, value, this._schema, this._injectableValues, this._dataFormatReaders);
    }

    public ObjectReader withView(Class<?> activeView) {
        return this._with((DeserializationConfig)this._config.withView((Class)activeView));
    }

    public ObjectReader with(Locale l2) {
        return this._with((DeserializationConfig)this._config.with(l2));
    }

    public ObjectReader with(TimeZone tz) {
        return this._with((DeserializationConfig)this._config.with(tz));
    }

    public ObjectReader withHandler(DeserializationProblemHandler h2) {
        return this._with(this._config.withHandler(h2));
    }

    public ObjectReader with(Base64Variant defaultBase64) {
        return this._with((DeserializationConfig)this._config.with(defaultBase64));
    }

    public ObjectReader withFormatDetection(ObjectReader ... readers) {
        return this.withFormatDetection(new DataFormatReaders(readers));
    }

    public ObjectReader withFormatDetection(DataFormatReaders readers) {
        return this._new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, readers);
    }

    public ObjectReader with(ContextAttributes attrs) {
        return this._with(this._config.with(attrs));
    }

    public ObjectReader withAttributes(Map<?, ?> attrs) {
        return this._with((DeserializationConfig)this._config.withAttributes(attrs));
    }

    public ObjectReader withAttribute(Object key, Object value) {
        return this._with((DeserializationConfig)this._config.withAttribute(key, value));
    }

    public ObjectReader withoutAttribute(Object key) {
        return this._with((DeserializationConfig)this._config.withoutAttribute(key));
    }

    protected ObjectReader _with(DeserializationConfig newConfig) {
        if (newConfig == this._config) {
            return this;
        }
        ObjectReader r2 = this._new(this, newConfig);
        if (this._dataFormatReaders != null) {
            r2 = r2.withFormatDetection(this._dataFormatReaders.with(newConfig));
        }
        return r2;
    }

    public boolean isEnabled(DeserializationFeature f10) {
        return this._config.isEnabled(f10);
    }

    public boolean isEnabled(MapperFeature f10) {
        return this._config.isEnabled(f10);
    }

    public boolean isEnabled(JsonParser.Feature f10) {
        return this._parserFactory.isEnabled(f10);
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    @Override
    public JsonFactory getFactory() {
        return this._parserFactory;
    }

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public ContextAttributes getAttributes() {
        return this._config.getAttributes();
    }

    public InjectableValues getInjectableValues() {
        return this._injectableValues;
    }

    public <T> T readValue(JsonParser p2) throws IOException {
        return (T)this._bind(p2, this._valueToUpdate);
    }

    @Override
    public <T> T readValue(JsonParser p2, Class<T> valueType) throws IOException {
        return this.forType(valueType).readValue(p2);
    }

    @Override
    public <T> T readValue(JsonParser p2, TypeReference<?> valueTypeRef) throws IOException {
        return this.forType(valueTypeRef).readValue(p2);
    }

    @Override
    public <T> T readValue(JsonParser p2, ResolvedType valueType) throws IOException {
        return this.forType((JavaType)valueType).readValue(p2);
    }

    public <T> T readValue(JsonParser p2, JavaType valueType) throws IOException {
        return this.forType(valueType).readValue(p2);
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p2, Class<T> valueType) throws IOException {
        return this.forType(valueType).readValues(p2);
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p2, TypeReference<?> valueTypeRef) throws IOException {
        return this.forType(valueTypeRef).readValues(p2);
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p2, ResolvedType valueType) throws IOException {
        return this.readValues(p2, (JavaType)valueType);
    }

    public <T> Iterator<T> readValues(JsonParser p2, JavaType valueType) throws IOException {
        return this.forType(valueType).readValues(p2);
    }

    @Override
    public JsonNode createArrayNode() {
        return this._config.getNodeFactory().arrayNode();
    }

    @Override
    public JsonNode createObjectNode() {
        return this._config.getNodeFactory().objectNode();
    }

    @Override
    public JsonParser treeAsTokens(TreeNode n2) {
        return new TreeTraversingParser((JsonNode)n2, this);
    }

    @Override
    public <T extends TreeNode> T readTree(JsonParser p2) throws IOException {
        return (T)this._bindAsTree(p2);
    }

    @Override
    public void writeTree(JsonGenerator g10, TreeNode rootNode) {
        throw new UnsupportedOperationException();
    }

    public <T> T readValue(InputStream src) throws IOException {
        if (this._dataFormatReaders != null) {
            return (T)this._detectBindAndClose(this._dataFormatReaders.findFormat(src), false);
        }
        return (T)this._bindAndClose(this._considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(Reader src) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(src);
        }
        return (T)this._bindAndClose(this._considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(String src) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(src);
        }
        return (T)this._bindAndClose(this._considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(byte[] src) throws IOException {
        if (this._dataFormatReaders != null) {
            return (T)this._detectBindAndClose(src, 0, src.length);
        }
        return (T)this._bindAndClose(this._considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(byte[] src, int offset, int length) throws IOException {
        if (this._dataFormatReaders != null) {
            return (T)this._detectBindAndClose(src, offset, length);
        }
        return (T)this._bindAndClose(this._considerFilter(this._parserFactory.createParser(src, offset, length), false));
    }

    public <T> T readValue(File src) throws IOException {
        if (this._dataFormatReaders != null) {
            return (T)this._detectBindAndClose(this._dataFormatReaders.findFormat(this._inputStream(src)), true);
        }
        return (T)this._bindAndClose(this._considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(URL src) throws IOException {
        if (this._dataFormatReaders != null) {
            return (T)this._detectBindAndClose(this._dataFormatReaders.findFormat(this._inputStream(src)), true);
        }
        return (T)this._bindAndClose(this._considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(JsonNode src) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(src);
        }
        return (T)this._bindAndClose(this._considerFilter(this.treeAsTokens(src), false));
    }

    public <T> T readValue(DataInput src) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(src);
        }
        return (T)this._bindAndClose(this._considerFilter(this._parserFactory.createParser(src), false));
    }

    public JsonNode readTree(InputStream in) throws IOException {
        if (this._dataFormatReaders != null) {
            return this._detectBindAndCloseAsTree(in);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this._parserFactory.createParser(in), false));
    }

    public JsonNode readTree(Reader r2) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(r2);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this._parserFactory.createParser(r2), false));
    }

    public JsonNode readTree(String json) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(json);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this._parserFactory.createParser(json), false));
    }

    public JsonNode readTree(DataInput src) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(src);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> MappingIterator<T> readValues(JsonParser p2) throws IOException {
        DefaultDeserializationContext ctxt = this.createDeserializationContext(p2);
        return this._newIterator(p2, ctxt, this._findRootDeserializer(ctxt), false);
    }

    public <T> MappingIterator<T> readValues(InputStream src) throws IOException {
        if (this._dataFormatReaders != null) {
            return this._detectBindAndReadValues(this._dataFormatReaders.findFormat(src), false);
        }
        return this._bindAndReadValues(this._considerFilter(this._parserFactory.createParser(src), true));
    }

    public <T> MappingIterator<T> readValues(Reader src) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(src);
        }
        JsonParser p2 = this._considerFilter(this._parserFactory.createParser(src), true);
        DefaultDeserializationContext ctxt = this.createDeserializationContext(p2);
        this._initForMultiRead(ctxt, p2);
        p2.nextToken();
        return this._newIterator(p2, ctxt, this._findRootDeserializer(ctxt), true);
    }

    public <T> MappingIterator<T> readValues(String json) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(json);
        }
        JsonParser p2 = this._considerFilter(this._parserFactory.createParser(json), true);
        DefaultDeserializationContext ctxt = this.createDeserializationContext(p2);
        this._initForMultiRead(ctxt, p2);
        p2.nextToken();
        return this._newIterator(p2, ctxt, this._findRootDeserializer(ctxt), true);
    }

    public <T> MappingIterator<T> readValues(byte[] src, int offset, int length) throws IOException {
        if (this._dataFormatReaders != null) {
            return this._detectBindAndReadValues(this._dataFormatReaders.findFormat(src, offset, length), false);
        }
        return this._bindAndReadValues(this._considerFilter(this._parserFactory.createParser(src, offset, length), true));
    }

    public final <T> MappingIterator<T> readValues(byte[] src) throws IOException {
        return this.readValues(src, 0, src.length);
    }

    public <T> MappingIterator<T> readValues(File src) throws IOException {
        if (this._dataFormatReaders != null) {
            return this._detectBindAndReadValues(this._dataFormatReaders.findFormat(this._inputStream(src)), false);
        }
        return this._bindAndReadValues(this._considerFilter(this._parserFactory.createParser(src), true));
    }

    public <T> MappingIterator<T> readValues(URL src) throws IOException {
        if (this._dataFormatReaders != null) {
            return this._detectBindAndReadValues(this._dataFormatReaders.findFormat(this._inputStream(src)), true);
        }
        return this._bindAndReadValues(this._considerFilter(this._parserFactory.createParser(src), true));
    }

    public <T> MappingIterator<T> readValues(DataInput src) throws IOException {
        if (this._dataFormatReaders != null) {
            this._reportUndetectableSource(src);
        }
        return this._bindAndReadValues(this._considerFilter(this._parserFactory.createParser(src), true));
    }

    @Override
    public <T> T treeToValue(TreeNode n2, Class<T> valueType) throws JsonProcessingException {
        try {
            return this.readValue(this.treeAsTokens(n2), valueType);
        }
        catch (JsonProcessingException e10) {
            throw e10;
        }
        catch (IOException e11) {
            throw JsonMappingException.fromUnexpectedIOE(e11);
        }
    }

    @Override
    public void writeValue(JsonGenerator gen, Object value) throws IOException {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    protected Object _bind(JsonParser p2, Object valueToUpdate) throws IOException {
        Object result;
        DefaultDeserializationContext ctxt = this.createDeserializationContext(p2);
        JsonToken t2 = this._initForReading(ctxt, p2);
        if (t2 == JsonToken.VALUE_NULL) {
            result = valueToUpdate == null ? this._findRootDeserializer(ctxt).getNullValue(ctxt) : valueToUpdate;
        } else if (t2 == JsonToken.END_ARRAY || t2 == JsonToken.END_OBJECT) {
            result = valueToUpdate;
        } else {
            JsonDeserializer<Object> deser = this._findRootDeserializer(ctxt);
            result = this._unwrapRoot ? this._unwrapAndDeserialize(p2, ctxt, this._valueType, deser) : (valueToUpdate == null ? deser.deserialize(p2, ctxt) : deser.deserialize(p2, ctxt, valueToUpdate));
        }
        p2.clearCurrentToken();
        if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(p2, ctxt, this._valueType);
        }
        return result;
    }

    protected Object _bindAndClose(JsonParser p0) throws IOException {
        try (JsonParser p2 = p0;){
            Object result;
            DefaultDeserializationContext ctxt = this.createDeserializationContext(p2);
            JsonToken t2 = this._initForReading(ctxt, p2);
            if (t2 == JsonToken.VALUE_NULL) {
                result = this._valueToUpdate == null ? this._findRootDeserializer(ctxt).getNullValue(ctxt) : this._valueToUpdate;
            } else if (t2 == JsonToken.END_ARRAY || t2 == JsonToken.END_OBJECT) {
                result = this._valueToUpdate;
            } else {
                JsonDeserializer<Object> deser = this._findRootDeserializer(ctxt);
                if (this._unwrapRoot) {
                    result = this._unwrapAndDeserialize(p2, ctxt, this._valueType, deser);
                } else if (this._valueToUpdate == null) {
                    result = deser.deserialize(p2, ctxt);
                } else {
                    deser.deserialize(p2, ctxt, this._valueToUpdate);
                    result = this._valueToUpdate;
                }
            }
            if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                this._verifyNoTrailingTokens(p2, ctxt, this._valueType);
            }
            Object object = result;
            return object;
        }
    }

    protected final JsonNode _bindAndCloseAsTree(JsonParser p0) throws IOException {
        try (JsonParser p2 = p0;){
            JsonNode jsonNode = this._bindAsTree(p2);
            return jsonNode;
        }
    }

    protected final JsonNode _bindAsTree(JsonParser p2) throws IOException {
        Object result;
        JsonToken t2;
        this._config.initialize(p2);
        if (this._schema != null) {
            p2.setSchema(this._schema);
        }
        if ((t2 = p2.getCurrentToken()) == null && (t2 = p2.nextToken()) == null) {
            return null;
        }
        DefaultDeserializationContext ctxt = this.createDeserializationContext(p2);
        if (t2 == JsonToken.VALUE_NULL) {
            return ctxt.getNodeFactory().nullNode();
        }
        JsonDeserializer<Object> deser = this._findTreeDeserializer(ctxt);
        if (this._unwrapRoot) {
            result = this._unwrapAndDeserialize(p2, ctxt, JSON_NODE_TYPE, deser);
        } else {
            result = deser.deserialize(p2, ctxt);
            if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                this._verifyNoTrailingTokens(p2, ctxt, JSON_NODE_TYPE);
            }
        }
        return (JsonNode)result;
    }

    protected <T> MappingIterator<T> _bindAndReadValues(JsonParser p2) throws IOException {
        DefaultDeserializationContext ctxt = this.createDeserializationContext(p2);
        this._initForMultiRead(ctxt, p2);
        p2.nextToken();
        return this._newIterator(p2, ctxt, this._findRootDeserializer(ctxt), true);
    }

    protected Object _unwrapAndDeserialize(JsonParser p2, DeserializationContext ctxt, JavaType rootType, JsonDeserializer<Object> deser) throws IOException {
        Object result;
        String actualName;
        PropertyName expRootName = this._config.findRootName(rootType);
        String expSimpleName = expRootName.getSimpleName();
        if (p2.getCurrentToken() != JsonToken.START_OBJECT) {
            ctxt.reportWrongTokenException(rootType, JsonToken.START_OBJECT, "Current token not START_OBJECT (needed to unwrap root name '%s'), but %s", new Object[]{expSimpleName, p2.getCurrentToken()});
        }
        if (p2.nextToken() != JsonToken.FIELD_NAME) {
            ctxt.reportWrongTokenException(rootType, JsonToken.FIELD_NAME, "Current token not FIELD_NAME (to contain expected root name '%s'), but %s", new Object[]{expSimpleName, p2.getCurrentToken()});
        }
        if (!expSimpleName.equals(actualName = p2.getCurrentName())) {
            ctxt.reportInputMismatch(rootType, "Root name '%s' does not match expected ('%s') for type %s", actualName, expSimpleName, rootType);
        }
        p2.nextToken();
        if (this._valueToUpdate == null) {
            result = deser.deserialize(p2, ctxt);
        } else {
            deser.deserialize(p2, ctxt, this._valueToUpdate);
            result = this._valueToUpdate;
        }
        if (p2.nextToken() != JsonToken.END_OBJECT) {
            ctxt.reportWrongTokenException(rootType, JsonToken.END_OBJECT, "Current token not END_OBJECT (to match wrapper object with root name '%s'), but %s", new Object[]{expSimpleName, p2.getCurrentToken()});
        }
        if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(p2, ctxt, this._valueType);
        }
        return result;
    }

    protected JsonParser _considerFilter(JsonParser p2, boolean multiValue) {
        return this._filter == null || FilteringParserDelegate.class.isInstance(p2) ? p2 : new FilteringParserDelegate(p2, this._filter, false, multiValue);
    }

    protected final void _verifyNoTrailingTokens(JsonParser p2, DeserializationContext ctxt, JavaType bindType) throws IOException {
        JsonToken t2 = p2.nextToken();
        if (t2 != null) {
            Class<?> bt2 = ClassUtil.rawClass(bindType);
            if (bt2 == null && this._valueToUpdate != null) {
                bt2 = this._valueToUpdate.getClass();
            }
            ctxt.reportTrailingTokens(bt2, p2, t2);
        }
    }

    protected Object _detectBindAndClose(byte[] src, int offset, int length) throws IOException {
        DataFormatReaders.Match match = this._dataFormatReaders.findFormat(src, offset, length);
        if (!match.hasMatch()) {
            this._reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser p2 = match.createParserWithMatch();
        return match.getReader()._bindAndClose(p2);
    }

    protected Object _detectBindAndClose(DataFormatReaders.Match match, boolean forceClosing) throws IOException {
        if (!match.hasMatch()) {
            this._reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser p2 = match.createParserWithMatch();
        if (forceClosing) {
            p2.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndClose(p2);
    }

    protected <T> MappingIterator<T> _detectBindAndReadValues(DataFormatReaders.Match match, boolean forceClosing) throws IOException {
        if (!match.hasMatch()) {
            this._reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser p2 = match.createParserWithMatch();
        if (forceClosing) {
            p2.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndReadValues(p2);
    }

    protected JsonNode _detectBindAndCloseAsTree(InputStream in) throws IOException {
        DataFormatReaders.Match match = this._dataFormatReaders.findFormat(in);
        if (!match.hasMatch()) {
            this._reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser p2 = match.createParserWithMatch();
        p2.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return match.getReader()._bindAndCloseAsTree(p2);
    }

    protected void _reportUnkownFormat(DataFormatReaders detector, DataFormatReaders.Match match) throws JsonProcessingException {
        throw new JsonParseException(null, "Cannot detect format from input, does not look like any of detectable formats " + detector.toString());
    }

    protected void _verifySchemaType(FormatSchema schema) {
        if (schema != null && !this._parserFactory.canUseSchema(schema)) {
            throw new IllegalArgumentException("Cannot use FormatSchema of type " + schema.getClass().getName() + " for format " + this._parserFactory.getFormatName());
        }
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser p2) {
        return this._context.createInstance(this._config, p2, this._injectableValues);
    }

    protected InputStream _inputStream(URL src) throws IOException {
        return src.openStream();
    }

    protected InputStream _inputStream(File f10) throws IOException {
        return new FileInputStream(f10);
    }

    protected void _reportUndetectableSource(Object src) throws JsonProcessingException {
        throw new JsonParseException(null, "Cannot use source of type " + src.getClass().getName() + " with format auto-detection: must be byte- not char-based");
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext ctxt) throws JsonMappingException {
        JsonDeserializer<Object> deser;
        if (this._rootDeserializer != null) {
            return this._rootDeserializer;
        }
        JavaType t2 = this._valueType;
        if (t2 == null) {
            ctxt.reportBadDefinition((JavaType)null, "No value type configured for ObjectReader");
        }
        if ((deser = this._rootDeserializers.get(t2)) != null) {
            return deser;
        }
        deser = ctxt.findRootValueDeserializer(t2);
        if (deser == null) {
            ctxt.reportBadDefinition(t2, "Cannot find a deserializer for type " + t2);
        }
        this._rootDeserializers.put(t2, deser);
        return deser;
    }

    protected JsonDeserializer<Object> _findTreeDeserializer(DeserializationContext ctxt) throws JsonMappingException {
        JsonDeserializer<Object> deser = this._rootDeserializers.get(JSON_NODE_TYPE);
        if (deser == null) {
            deser = ctxt.findRootValueDeserializer(JSON_NODE_TYPE);
            if (deser == null) {
                ctxt.reportBadDefinition(JSON_NODE_TYPE, "Cannot find a deserializer for type " + JSON_NODE_TYPE);
            }
            this._rootDeserializers.put(JSON_NODE_TYPE, deser);
        }
        return deser;
    }

    protected JsonDeserializer<Object> _prefetchRootDeserializer(JavaType valueType) {
        if (valueType == null || !this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
            return null;
        }
        JsonDeserializer<Object> deser = this._rootDeserializers.get(valueType);
        if (deser == null) {
            try {
                DefaultDeserializationContext ctxt = this.createDeserializationContext(null);
                deser = ctxt.findRootValueDeserializer(valueType);
                if (deser != null) {
                    this._rootDeserializers.put(valueType, deser);
                }
                return deser;
            }
            catch (JsonProcessingException jsonProcessingException) {
                // empty catch block
            }
        }
        return deser;
    }
}

