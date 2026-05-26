/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdResolver;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DatabindContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.InjectableValues;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.KeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ContextAttributes;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializerCache;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.DeserializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.InvalidFormatException;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.MismatchedInputException;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.Annotated;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ArrayBuilders;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.LinkedNode;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ObjectBuffer;

public abstract class DeserializationContext
extends DatabindContext
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final DeserializerCache _cache;
    protected final DeserializerFactory _factory;
    protected final DeserializationConfig _config;
    protected final int _featureFlags;
    protected final Class<?> _view;
    protected transient JsonParser _parser;
    protected final InjectableValues _injectableValues;
    protected transient ArrayBuilders _arrayBuilders;
    protected transient ObjectBuffer _objectBuffer;
    protected transient DateFormat _dateFormat;
    protected transient ContextAttributes _attributes;
    protected LinkedNode<JavaType> _currentType;

    protected DeserializationContext(DeserializerFactory df2) {
        this(df2, null);
    }

    protected DeserializationContext(DeserializerFactory df2, DeserializerCache cache) {
        if (df2 == null) {
            throw new IllegalArgumentException("Cannot pass null DeserializerFactory");
        }
        this._factory = df2;
        if (cache == null) {
            cache = new DeserializerCache();
        }
        this._cache = cache;
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
        this._attributes = null;
    }

    protected DeserializationContext(DeserializationContext src, DeserializerFactory factory) {
        this._cache = src._cache;
        this._factory = factory;
        this._config = src._config;
        this._featureFlags = src._featureFlags;
        this._view = src._view;
        this._parser = src._parser;
        this._injectableValues = src._injectableValues;
        this._attributes = src._attributes;
    }

    protected DeserializationContext(DeserializationContext src, DeserializationConfig config, JsonParser p2, InjectableValues injectableValues) {
        this._cache = src._cache;
        this._factory = src._factory;
        this._config = config;
        this._featureFlags = config.getDeserializationFeatures();
        this._view = config.getActiveView();
        this._parser = p2;
        this._injectableValues = injectableValues;
        this._attributes = config.getAttributes();
    }

    protected DeserializationContext(DeserializationContext src) {
        this._cache = new DeserializerCache();
        this._factory = src._factory;
        this._config = src._config;
        this._featureFlags = src._featureFlags;
        this._view = src._view;
        this._injectableValues = null;
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    @Override
    public final Class<?> getActiveView() {
        return this._view;
    }

    @Override
    public final boolean canOverrideAccessModifiers() {
        return this._config.canOverrideAccessModifiers();
    }

    @Override
    public final boolean isEnabled(MapperFeature feature) {
        return this._config.isEnabled(feature);
    }

    @Override
    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> baseType) {
        return this._config.getDefaultPropertyFormat(baseType);
    }

    @Override
    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    @Override
    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    @Override
    public Locale getLocale() {
        return this._config.getLocale();
    }

    @Override
    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    @Override
    public Object getAttribute(Object key) {
        return this._attributes.getAttribute(key);
    }

    @Override
    public DeserializationContext setAttribute(Object key, Object value) {
        this._attributes = this._attributes.withPerCallAttribute(key, value);
        return this;
    }

    public JavaType getContextualType() {
        return this._currentType == null ? null : this._currentType.value();
    }

    public DeserializerFactory getFactory() {
        return this._factory;
    }

    public final boolean isEnabled(DeserializationFeature feat) {
        return (this._featureFlags & feat.getMask()) != 0;
    }

    public final int getDeserializationFeatures() {
        return this._featureFlags;
    }

    public final boolean hasDeserializationFeatures(int featureMask) {
        return (this._featureFlags & featureMask) == featureMask;
    }

    public final boolean hasSomeOfFeatures(int featureMask) {
        return (this._featureFlags & featureMask) != 0;
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public final Object findInjectableValue(Object valueId, BeanProperty forProperty, Object beanInstance) throws JsonMappingException {
        if (this._injectableValues == null) {
            this.reportBadDefinition(ClassUtil.classOf(valueId), String.format("No 'injectableValues' configured, cannot inject value with id [%s]", valueId));
        }
        return this._injectableValues.findInjectableValue(valueId, this, forProperty, beanInstance);
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public boolean hasValueDeserializerFor(JavaType type, AtomicReference<Throwable> cause) {
        try {
            return this._cache.hasValueDeserializerFor(this, this._factory, type);
        }
        catch (JsonMappingException e10) {
            if (cause != null) {
                cause.set(e10);
            }
        }
        catch (RuntimeException e11) {
            if (cause == null) {
                throw e11;
            }
            cause.set(e11);
        }
        return false;
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType type, BeanProperty prop) throws JsonMappingException {
        JsonDeserializer<Object> deser = this._cache.findValueDeserializer(this, this._factory, type);
        if (deser != null) {
            deser = this.handleSecondaryContextualization(deser, prop, type);
        }
        return deser;
    }

    public final JsonDeserializer<Object> findNonContextualValueDeserializer(JavaType type) throws JsonMappingException {
        return this._cache.findValueDeserializer(this, this._factory, type);
    }

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType type) throws JsonMappingException {
        JsonDeserializer<Object> deser = this._cache.findValueDeserializer(this, this._factory, type);
        if (deser == null) {
            return null;
        }
        deser = this.handleSecondaryContextualization(deser, null, type);
        TypeDeserializer typeDeser = this._factory.findTypeDeserializer(this._config, type);
        if (typeDeser != null) {
            typeDeser = typeDeser.forProperty(null);
            return new TypeWrappedDeserializer(typeDeser, deser);
        }
        return deser;
    }

    public final KeyDeserializer findKeyDeserializer(JavaType keyType, BeanProperty prop) throws JsonMappingException {
        KeyDeserializer kd = this._cache.findKeyDeserializer(this, this._factory, keyType);
        if (kd instanceof ContextualKeyDeserializer) {
            kd = ((ContextualKeyDeserializer)((Object)kd)).createContextual(this, prop);
        }
        return kd;
    }

    public abstract ReadableObjectId findObjectId(Object var1, ObjectIdGenerator<?> var2, ObjectIdResolver var3);

    public abstract void checkUnresolvedObjectId() throws UnresolvedForwardReference;

    public final JavaType constructType(Class<?> cls) {
        return cls == null ? null : this._config.constructType(cls);
    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        return this.getTypeFactory().findClass(className);
    }

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer buf = this._objectBuffer;
        if (buf == null) {
            buf = new ObjectBuffer();
        } else {
            this._objectBuffer = null;
        }
        return buf;
    }

    public final void returnObjectBuffer(ObjectBuffer buf) {
        if (this._objectBuffer == null || buf.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = buf;
        }
    }

    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated var1, Object var2) throws JsonMappingException;

    public abstract KeyDeserializer keyDeserializerInstance(Annotated var1, Object var2) throws JsonMappingException;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public JsonDeserializer<?> handlePrimaryContextualization(JsonDeserializer<?> deser, BeanProperty prop, JavaType type) throws JsonMappingException {
        if (deser instanceof ContextualDeserializer) {
            this._currentType = new LinkedNode<JavaType>(type, this._currentType);
            try {
                deser = ((ContextualDeserializer)((Object)deser)).createContextual(this, prop);
            }
            finally {
                this._currentType = this._currentType.next();
            }
        }
        return deser;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> deser, BeanProperty prop, JavaType type) throws JsonMappingException {
        if (deser instanceof ContextualDeserializer) {
            this._currentType = new LinkedNode<JavaType>(type, this._currentType);
            try {
                deser = ((ContextualDeserializer)((Object)deser)).createContextual(this, prop);
            }
            finally {
                this._currentType = this._currentType.next();
            }
        }
        return deser;
    }

    public Date parseDate(String dateStr) throws IllegalArgumentException {
        try {
            DateFormat df2 = this.getDateFormat();
            return df2.parse(dateStr);
        }
        catch (ParseException e10) {
            throw new IllegalArgumentException(String.format("Failed to parse Date value '%s': %s", dateStr, e10.getMessage()));
        }
    }

    public Calendar constructCalendar(Date d10) {
        Calendar c10 = Calendar.getInstance(this.getTimeZone());
        c10.setTime(d10);
        return c10;
    }

    public <T> T readValue(JsonParser p2, Class<T> type) throws IOException {
        return this.readValue(p2, this.getTypeFactory().constructType(type));
    }

    public <T> T readValue(JsonParser p2, JavaType type) throws IOException {
        JsonDeserializer<Object> deser = this.findRootValueDeserializer(type);
        if (deser == null) {
            this.reportBadDefinition(type, "Could not find JsonDeserializer for type " + type);
        }
        return (T)deser.deserialize(p2, this);
    }

    public <T> T readPropertyValue(JsonParser p2, BeanProperty prop, Class<T> type) throws IOException {
        return this.readPropertyValue(p2, prop, this.getTypeFactory().constructType(type));
    }

    public <T> T readPropertyValue(JsonParser p2, BeanProperty prop, JavaType type) throws IOException {
        JsonDeserializer<Object> deser = this.findContextualValueDeserializer(type, prop);
        if (deser == null) {
            return this.reportBadDefinition(type, String.format("Could not find JsonDeserializer for type %s (via property %s)", type, ClassUtil.nameOf(prop)));
        }
        return (T)deser.deserialize(p2, this);
    }

    public boolean handleUnknownProperty(JsonParser p2, JsonDeserializer<?> deser, Object instanceOrClass, String propName) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            if (!h2.value().handleUnknownProperty(this, p2, deser, instanceOrClass, propName)) continue;
            return true;
        }
        if (!this.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            p2.skipChildren();
            return true;
        }
        Collection<Object> propIds = deser == null ? null : deser.getKnownPropertyNames();
        throw UnrecognizedPropertyException.from(this._parser, instanceOrClass, propName, propIds);
    }

    public Object handleWeirdKey(Class<?> keyClass, String keyValue, String msg, Object ... msgArgs) throws IOException {
        msg = this._format(msg, msgArgs);
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            Object key = h2.value().handleWeirdKey(this, keyClass, keyValue, msg);
            if (key == DeserializationProblemHandler.NOT_HANDLED) continue;
            if (key == null || keyClass.isInstance(key)) {
                return key;
            }
            throw this.weirdStringException(keyValue, keyClass, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", keyClass, key.getClass()));
        }
        throw this.weirdKeyException(keyClass, keyValue, msg);
    }

    public Object handleWeirdStringValue(Class<?> targetClass, String value, String msg, Object ... msgArgs) throws IOException {
        msg = this._format(msg, msgArgs);
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            Object instance = h2.value().handleWeirdStringValue(this, targetClass, value, msg);
            if (instance == DeserializationProblemHandler.NOT_HANDLED) continue;
            if (instance == null || targetClass.isInstance(instance)) {
                return instance;
            }
            throw this.weirdStringException(value, targetClass, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", targetClass, instance.getClass()));
        }
        throw this.weirdStringException(value, targetClass, msg);
    }

    public Object handleWeirdNumberValue(Class<?> targetClass, Number value, String msg, Object ... msgArgs) throws IOException {
        msg = this._format(msg, msgArgs);
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            Object key = h2.value().handleWeirdNumberValue(this, targetClass, value, msg);
            if (key == DeserializationProblemHandler.NOT_HANDLED) continue;
            if (key == null || targetClass.isInstance(key)) {
                return key;
            }
            throw this.weirdNumberException(value, targetClass, this._format("DeserializationProblemHandler.handleWeirdNumberValue() for type %s returned value of type %s", targetClass, key.getClass()));
        }
        throw this.weirdNumberException(value, targetClass, msg);
    }

    public Object handleWeirdNativeValue(JavaType targetType, Object badValue, JsonParser p2) throws IOException {
        Class<?> raw = targetType.getRawClass();
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            Object goodValue = h2.value().handleWeirdNativeValue(this, targetType, badValue, p2);
            if (goodValue == DeserializationProblemHandler.NOT_HANDLED) continue;
            if (goodValue == null || raw.isInstance(goodValue)) {
                return goodValue;
            }
            throw JsonMappingException.from(p2, this._format("DeserializationProblemHandler.handleWeirdNativeValue() for type %s returned value of type %s", targetType, goodValue.getClass()));
        }
        throw this.weirdNativeValueException(badValue, raw);
    }

    public Object handleMissingInstantiator(Class<?> instClass, ValueInstantiator valueInst, JsonParser p2, String msg, Object ... msgArgs) throws IOException {
        if (p2 == null) {
            p2 = this.getParser();
        }
        msg = this._format(msg, msgArgs);
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            Object instance = h2.value().handleMissingInstantiator(this, instClass, valueInst, p2, msg);
            if (instance == DeserializationProblemHandler.NOT_HANDLED) continue;
            if (instance == null || instClass.isInstance(instance)) {
                return instance;
            }
            this.reportBadDefinition(this.constructType(instClass), String.format("DeserializationProblemHandler.handleMissingInstantiator() for type %s returned value of type %s", instClass, ClassUtil.classNameOf(instance)));
        }
        if (valueInst != null && !valueInst.canInstantiate()) {
            msg = String.format("Cannot construct instance of %s (no Creators, like default construct, exist): %s", ClassUtil.nameOf(instClass), msg);
            return this.reportBadDefinition(this.constructType(instClass), msg);
        }
        msg = String.format("Cannot construct instance of %s (although at least one Creator exists): %s", ClassUtil.nameOf(instClass), msg);
        return this.reportInputMismatch(instClass, msg, new Object[0]);
    }

    public Object handleInstantiationProblem(Class<?> instClass, Object argument, Throwable t2) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            Object instance = h2.value().handleInstantiationProblem(this, instClass, argument, t2);
            if (instance == DeserializationProblemHandler.NOT_HANDLED) continue;
            if (instance == null || instClass.isInstance(instance)) {
                return instance;
            }
            this.reportBadDefinition(this.constructType(instClass), String.format("DeserializationProblemHandler.handleInstantiationProblem() for type %s returned value of type %s", instClass, ClassUtil.classNameOf(instance)));
        }
        ClassUtil.throwIfIOE(t2);
        throw this.instantiationException(instClass, t2);
    }

    public Object handleUnexpectedToken(Class<?> instClass, JsonParser p2) throws IOException {
        return this.handleUnexpectedToken(instClass, p2.getCurrentToken(), p2, null, new Object[0]);
    }

    public Object handleUnexpectedToken(Class<?> instClass, JsonToken t2, JsonParser p2, String msg, Object ... msgArgs) throws IOException {
        msg = this._format(msg, msgArgs);
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            Object instance = h2.value().handleUnexpectedToken(this, instClass, t2, p2, msg);
            if (instance == DeserializationProblemHandler.NOT_HANDLED) continue;
            if (instance == null || instClass.isInstance(instance)) {
                return instance;
            }
            this.reportBadDefinition(this.constructType(instClass), String.format("DeserializationProblemHandler.handleUnexpectedToken() for type %s returned value of type %s", instance.getClass()));
        }
        if (msg == null) {
            msg = t2 == null ? String.format("Unexpected end-of-input when binding data into %s", this._calcName(instClass)) : String.format("Cannot deserialize instance of %s out of %s token", new Object[]{this._calcName(instClass), t2});
        }
        this.reportInputMismatch(instClass, msg, new Object[0]);
        return null;
    }

    public JavaType handleUnknownTypeId(JavaType baseType, String id, TypeIdResolver idResolver, String extraDesc) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            JavaType type = h2.value().handleUnknownTypeId(this, baseType, id, idResolver, extraDesc);
            if (type == null) continue;
            if (type.hasRawClass(Void.class)) {
                return null;
            }
            if (type.isTypeOrSubTypeOf(baseType.getRawClass())) {
                return type;
            }
            throw this.invalidTypeIdException(baseType, id, "problem handler tried to resolve into non-subtype: " + type);
        }
        if (!this.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
            return null;
        }
        throw this.invalidTypeIdException(baseType, id, extraDesc);
    }

    public JavaType handleMissingTypeId(JavaType baseType, TypeIdResolver idResolver, String extraDesc) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> h2 = this._config.getProblemHandlers(); h2 != null; h2 = h2.next()) {
            JavaType type = h2.value().handleMissingTypeId(this, baseType, idResolver, extraDesc);
            if (type == null) continue;
            if (type.hasRawClass(Void.class)) {
                return null;
            }
            if (type.isTypeOrSubTypeOf(baseType.getRawClass())) {
                return type;
            }
            throw this.invalidTypeIdException(baseType, null, "problem handler tried to resolve into non-subtype: " + type);
        }
        throw this.missingTypeIdException(baseType, extraDesc);
    }

    public void reportWrongTokenException(JsonDeserializer<?> deser, JsonToken expToken, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        throw this.wrongTokenException(this.getParser(), deser.handledType(), expToken, msg);
    }

    public void reportWrongTokenException(JavaType targetType, JsonToken expToken, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        throw this.wrongTokenException(this.getParser(), targetType, expToken, msg);
    }

    public void reportWrongTokenException(Class<?> targetType, JsonToken expToken, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        throw this.wrongTokenException(this.getParser(), targetType, expToken, msg);
    }

    public <T> T reportUnresolvedObjectId(ObjectIdReader oidReader, Object bean) throws JsonMappingException {
        String msg = String.format("No Object Id found for an instance of %s, to assign to property '%s'", ClassUtil.classNameOf(bean), oidReader.propertyName);
        return this.reportInputMismatch(oidReader.idProperty, msg, new Object[0]);
    }

    public <T> T reportInputMismatch(BeanProperty prop, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        JavaType type = prop == null ? null : prop.getType();
        throw MismatchedInputException.from(this.getParser(), type, msg);
    }

    public <T> T reportInputMismatch(JsonDeserializer<?> src, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        throw MismatchedInputException.from(this.getParser(), src.handledType(), msg);
    }

    public <T> T reportInputMismatch(Class<?> targetType, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        throw MismatchedInputException.from(this.getParser(), targetType, msg);
    }

    public <T> T reportInputMismatch(JavaType targetType, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        throw MismatchedInputException.from(this.getParser(), targetType, msg);
    }

    public <T> T reportTrailingTokens(Class<?> targetType, JsonParser p2, JsonToken trailingToken) throws JsonMappingException {
        throw MismatchedInputException.from(p2, targetType, String.format("Trailing token (of type %s) found after value (bound as %s): not allowed as per `DeserializationFeature.FAIL_ON_TRAILING_TOKENS`", new Object[]{trailingToken, ClassUtil.nameOf(targetType)}));
    }

    @Deprecated
    public void reportWrongTokenException(JsonParser p2, JsonToken expToken, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        throw this.wrongTokenException(p2, expToken, msg);
    }

    @Deprecated
    public void reportUnknownProperty(Object instanceOrClass, String fieldName, JsonDeserializer<?> deser) throws JsonMappingException {
        if (this.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            Collection<Object> propIds = deser == null ? null : deser.getKnownPropertyNames();
            throw UnrecognizedPropertyException.from(this._parser, instanceOrClass, fieldName, propIds);
        }
    }

    @Deprecated
    public void reportMissingContent(String msg, Object ... msgArgs) throws JsonMappingException {
        throw MismatchedInputException.from(this.getParser(), (JavaType)null, "No content to map due to end-of-input");
    }

    public <T> T reportBadTypeDefinition(BeanDescription bean, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        String beanDesc = ClassUtil.nameOf(bean.getBeanClass());
        msg = String.format("Invalid type definition for type %s: %s", beanDesc, msg);
        throw InvalidDefinitionException.from(this._parser, msg, bean, null);
    }

    public <T> T reportBadPropertyDefinition(BeanDescription bean, BeanPropertyDefinition prop, String msg, Object ... msgArgs) throws JsonMappingException {
        msg = this._format(msg, msgArgs);
        String propName = ClassUtil.nameOf(prop);
        String beanDesc = ClassUtil.nameOf(bean.getBeanClass());
        msg = String.format("Invalid definition for property %s (of type %s): %s", propName, beanDesc, msg);
        throw InvalidDefinitionException.from(this._parser, msg, bean, prop);
    }

    @Override
    public <T> T reportBadDefinition(JavaType type, String msg) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, msg, type);
    }

    public <T> T reportBadMerge(JsonDeserializer<?> deser) throws JsonMappingException {
        if (this.isEnabled(MapperFeature.IGNORE_MERGE_FOR_UNMERGEABLE)) {
            return null;
        }
        JavaType type = this.constructType(deser.handledType());
        String msg = String.format("Invalid configuration: values of type %s cannot be merged", type);
        throw InvalidDefinitionException.from(this.getParser(), msg, type);
    }

    public JsonMappingException wrongTokenException(JsonParser p2, JavaType targetType, JsonToken expToken, String extra) {
        String msg = String.format("Unexpected token (%s), expected %s", new Object[]{p2.getCurrentToken(), expToken});
        msg = this._colonConcat(msg, extra);
        return MismatchedInputException.from(p2, targetType, msg);
    }

    public JsonMappingException wrongTokenException(JsonParser p2, Class<?> targetType, JsonToken expToken, String extra) {
        String msg = String.format("Unexpected token (%s), expected %s", new Object[]{p2.getCurrentToken(), expToken});
        msg = this._colonConcat(msg, extra);
        return MismatchedInputException.from(p2, targetType, msg);
    }

    @Deprecated
    public JsonMappingException wrongTokenException(JsonParser p2, JsonToken expToken, String msg) {
        return this.wrongTokenException(p2, (JavaType)null, expToken, msg);
    }

    public JsonMappingException weirdKeyException(Class<?> keyClass, String keyValue, String msg) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize Map key of type %s from String %s: %s", ClassUtil.nameOf(keyClass), this._quotedString(keyValue), msg), keyValue, keyClass);
    }

    public JsonMappingException weirdStringException(String value, Class<?> instClass, String msg) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from String %s: %s", ClassUtil.nameOf(instClass), this._quotedString(value), msg), value, instClass);
    }

    public JsonMappingException weirdNumberException(Number value, Class<?> instClass, String msg) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from number %s: %s", ClassUtil.nameOf(instClass), String.valueOf(value), msg), value, instClass);
    }

    public JsonMappingException weirdNativeValueException(Object value, Class<?> instClass) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from native value (`JsonToken.VALUE_EMBEDDED_OBJECT`) of type %s: incompatible types", ClassUtil.nameOf(instClass), ClassUtil.classNameOf(value)), value, instClass);
    }

    public JsonMappingException instantiationException(Class<?> instClass, Throwable cause) {
        JavaType type = this.constructType(instClass);
        String msg = String.format("Cannot construct instance of %s, problem: %s", ClassUtil.nameOf(instClass), cause.getMessage());
        InvalidDefinitionException e10 = InvalidDefinitionException.from(this._parser, msg, type);
        e10.initCause(cause);
        return e10;
    }

    public JsonMappingException instantiationException(Class<?> instClass, String msg0) {
        JavaType type = this.constructType(instClass);
        String msg = String.format("Cannot construct instance of %s: %s", ClassUtil.nameOf(instClass), msg0);
        return InvalidDefinitionException.from(this._parser, msg, type);
    }

    @Override
    public JsonMappingException invalidTypeIdException(JavaType baseType, String typeId, String extraDesc) {
        String msg = String.format("Could not resolve type id '%s' as a subtype of %s", typeId, baseType);
        return InvalidTypeIdException.from(this._parser, this._colonConcat(msg, extraDesc), baseType, typeId);
    }

    public JsonMappingException missingTypeIdException(JavaType baseType, String extraDesc) {
        String msg = String.format("Missing type id when trying to resolve subtype of %s", baseType);
        return InvalidTypeIdException.from(this._parser, this._colonConcat(msg, extraDesc), baseType, null);
    }

    @Deprecated
    public JsonMappingException unknownTypeException(JavaType type, String id, String extraDesc) {
        String msg = String.format("Could not resolve type id '%s' into a subtype of %s", id, type);
        msg = this._colonConcat(msg, extraDesc);
        return MismatchedInputException.from(this._parser, type, msg);
    }

    @Deprecated
    public JsonMappingException endOfInputException(Class<?> instClass) {
        return MismatchedInputException.from(this._parser, instClass, "Unexpected end-of-input when trying to deserialize a " + instClass.getName());
    }

    @Deprecated
    public void reportMappingException(String msg, Object ... msgArgs) throws JsonMappingException {
        throw JsonMappingException.from(this.getParser(), this._format(msg, msgArgs));
    }

    @Deprecated
    public JsonMappingException mappingException(String message) {
        return JsonMappingException.from(this.getParser(), message);
    }

    @Deprecated
    public JsonMappingException mappingException(String msg, Object ... msgArgs) {
        return JsonMappingException.from(this.getParser(), this._format(msg, msgArgs));
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> targetClass) {
        return this.mappingException(targetClass, this._parser.getCurrentToken());
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> targetClass, JsonToken token) {
        return JsonMappingException.from(this._parser, String.format("Cannot deserialize instance of %s out of %s token", new Object[]{this._calcName(targetClass), token}));
    }

    protected DateFormat getDateFormat() {
        if (this._dateFormat != null) {
            return this._dateFormat;
        }
        DateFormat df2 = this._config.getDateFormat();
        this._dateFormat = df2 = (DateFormat)df2.clone();
        return df2;
    }
}

