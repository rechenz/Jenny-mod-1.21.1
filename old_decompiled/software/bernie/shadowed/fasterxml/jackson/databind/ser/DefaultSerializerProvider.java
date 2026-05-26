/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.Annotated;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonschema.JsonSchema;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonschema.SchemaAware;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.SerializerFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public abstract class DefaultSerializerProvider
extends SerializerProvider
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected transient Map<Object, WritableObjectId> _seenObjectIds;
    protected transient ArrayList<ObjectIdGenerator<?>> _objectIdGenerators;
    protected transient JsonGenerator _generator;

    protected DefaultSerializerProvider() {
    }

    protected DefaultSerializerProvider(SerializerProvider src, SerializationConfig config, SerializerFactory f10) {
        super(src, config, f10);
    }

    protected DefaultSerializerProvider(DefaultSerializerProvider src) {
        super(src);
    }

    public abstract DefaultSerializerProvider createInstance(SerializationConfig var1, SerializerFactory var2);

    public DefaultSerializerProvider copy() {
        throw new IllegalStateException("DefaultSerializerProvider sub-class not overriding copy()");
    }

    @Override
    public JsonSerializer<Object> serializerInstance(Annotated annotated, Object serDef) throws JsonMappingException {
        JsonSerializer ser;
        if (serDef == null) {
            return null;
        }
        if (serDef instanceof JsonSerializer) {
            ser = (JsonSerializer)serDef;
        } else {
            HandlerInstantiator hi;
            Class serClass;
            if (!(serDef instanceof Class)) {
                this.reportBadDefinition(annotated.getType(), "AnnotationIntrospector returned serializer definition of type " + serDef.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead");
            }
            if ((serClass = (Class)serDef) == JsonSerializer.None.class || ClassUtil.isBogusClass(serClass)) {
                return null;
            }
            if (!JsonSerializer.class.isAssignableFrom(serClass)) {
                this.reportBadDefinition(annotated.getType(), "AnnotationIntrospector returned Class " + serClass.getName() + "; expected Class<JsonSerializer>");
            }
            JsonSerializer jsonSerializer = ser = (hi = this._config.getHandlerInstantiator()) == null ? null : hi.serializerInstance(this._config, annotated, serClass);
            if (ser == null) {
                ser = (JsonSerializer)ClassUtil.createInstance(serClass, this._config.canOverrideAccessModifiers());
            }
        }
        return this._handleResolvable(ser);
    }

    @Override
    public Object includeFilterInstance(BeanPropertyDefinition forProperty, Class<?> filterClass) {
        Object filter;
        if (filterClass == null) {
            return null;
        }
        HandlerInstantiator hi = this._config.getHandlerInstantiator();
        Object object = filter = hi == null ? null : hi.includeFilterInstance(this._config, forProperty, filterClass);
        if (filter == null) {
            filter = ClassUtil.createInstance(filterClass, this._config.canOverrideAccessModifiers());
        }
        return filter;
    }

    @Override
    public boolean includeFilterSuppressNulls(Object filter) throws JsonMappingException {
        if (filter == null) {
            return true;
        }
        try {
            return filter.equals(null);
        }
        catch (Throwable t2) {
            String msg = String.format("Problem determining whether filter of type '%s' should filter out `null` values: (%s) %s", filter.getClass().getName(), t2.getClass().getName(), t2.getMessage());
            this.reportBadDefinition(filter.getClass(), msg, t2);
            return false;
        }
    }

    @Override
    public WritableObjectId findObjectId(Object forPojo, ObjectIdGenerator<?> generatorType) {
        if (this._seenObjectIds == null) {
            this._seenObjectIds = this._createObjectIdMap();
        } else {
            WritableObjectId oid = this._seenObjectIds.get(forPojo);
            if (oid != null) {
                return oid;
            }
        }
        ObjectIdGenerator<?> generator = null;
        if (this._objectIdGenerators == null) {
            this._objectIdGenerators = new ArrayList(8);
        } else {
            int len = this._objectIdGenerators.size();
            for (int i2 = 0; i2 < len; ++i2) {
                ObjectIdGenerator<?> gen = this._objectIdGenerators.get(i2);
                if (!gen.canUseFor(generatorType)) continue;
                generator = gen;
                break;
            }
        }
        if (generator == null) {
            generator = generatorType.newForSerialization(this);
            this._objectIdGenerators.add(generator);
        }
        WritableObjectId oid = new WritableObjectId(generator);
        this._seenObjectIds.put(forPojo, oid);
        return oid;
    }

    protected Map<Object, WritableObjectId> _createObjectIdMap() {
        if (this.isEnabled(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID)) {
            return new HashMap<Object, WritableObjectId>();
        }
        return new IdentityHashMap<Object, WritableObjectId>();
    }

    public boolean hasSerializerFor(Class<?> cls, AtomicReference<Throwable> cause) {
        if (cls == Object.class && !this._config.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS)) {
            return true;
        }
        try {
            JsonSerializer<Object> ser = this._findExplicitUntypedSerializer(cls);
            return ser != null;
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

    @Override
    public JsonGenerator getGenerator() {
        return this._generator;
    }

    public void serializeValue(JsonGenerator gen, Object value) throws IOException {
        this._generator = gen;
        if (value == null) {
            this._serializeNull(gen);
            return;
        }
        Class<?> cls = value.getClass();
        JsonSerializer<Object> ser = this.findTypedValueSerializer(cls, true, null);
        PropertyName rootName = this._config.getFullRootName();
        if (rootName == null) {
            if (this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE)) {
                this._serialize(gen, value, ser, this._config.findRootName(cls));
                return;
            }
        } else if (!rootName.isEmpty()) {
            this._serialize(gen, value, ser, rootName);
            return;
        }
        this._serialize(gen, value, ser);
    }

    public void serializeValue(JsonGenerator gen, Object value, JavaType rootType) throws IOException {
        this._generator = gen;
        if (value == null) {
            this._serializeNull(gen);
            return;
        }
        if (!rootType.getRawClass().isAssignableFrom(value.getClass())) {
            this._reportIncompatibleRootType(value, rootType);
        }
        JsonSerializer<Object> ser = this.findTypedValueSerializer(rootType, true, null);
        PropertyName rootName = this._config.getFullRootName();
        if (rootName == null) {
            if (this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE)) {
                this._serialize(gen, value, ser, this._config.findRootName(rootType));
                return;
            }
        } else if (!rootName.isEmpty()) {
            this._serialize(gen, value, ser, rootName);
            return;
        }
        this._serialize(gen, value, ser);
    }

    public void serializeValue(JsonGenerator gen, Object value, JavaType rootType, JsonSerializer<Object> ser) throws IOException {
        PropertyName rootName;
        this._generator = gen;
        if (value == null) {
            this._serializeNull(gen);
            return;
        }
        if (rootType != null && !rootType.getRawClass().isAssignableFrom(value.getClass())) {
            this._reportIncompatibleRootType(value, rootType);
        }
        if (ser == null) {
            ser = this.findTypedValueSerializer(rootType, true, null);
        }
        if ((rootName = this._config.getFullRootName()) == null) {
            if (this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE)) {
                rootName = rootType == null ? this._config.findRootName(value.getClass()) : this._config.findRootName(rootType);
                this._serialize(gen, value, ser, rootName);
                return;
            }
        } else if (!rootName.isEmpty()) {
            this._serialize(gen, value, ser, rootName);
            return;
        }
        this._serialize(gen, value, ser);
    }

    public void serializePolymorphic(JsonGenerator gen, Object value, JavaType rootType, JsonSerializer<Object> valueSer, TypeSerializer typeSer) throws IOException {
        boolean wrap;
        PropertyName rootName;
        this._generator = gen;
        if (value == null) {
            this._serializeNull(gen);
            return;
        }
        if (rootType != null && !rootType.getRawClass().isAssignableFrom(value.getClass())) {
            this._reportIncompatibleRootType(value, rootType);
        }
        if (valueSer == null) {
            valueSer = rootType != null && rootType.isContainerType() ? this.findValueSerializer(rootType, null) : this.findValueSerializer(value.getClass(), null);
        }
        if ((rootName = this._config.getFullRootName()) == null) {
            wrap = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
            if (wrap) {
                gen.writeStartObject();
                PropertyName pname = this._config.findRootName(value.getClass());
                gen.writeFieldName(pname.simpleAsEncoded(this._config));
            }
        } else if (rootName.isEmpty()) {
            wrap = false;
        } else {
            wrap = true;
            gen.writeStartObject();
            gen.writeFieldName(rootName.getSimpleName());
        }
        try {
            valueSer.serializeWithType(value, gen, this, typeSer);
            if (wrap) {
                gen.writeEndObject();
            }
        }
        catch (Exception e10) {
            throw this._wrapAsIOE(gen, e10);
        }
    }

    private final void _serialize(JsonGenerator gen, Object value, JsonSerializer<Object> ser, PropertyName rootName) throws IOException {
        try {
            gen.writeStartObject();
            gen.writeFieldName(rootName.simpleAsEncoded(this._config));
            ser.serialize(value, gen, this);
            gen.writeEndObject();
        }
        catch (Exception e10) {
            throw this._wrapAsIOE(gen, e10);
        }
    }

    private final void _serialize(JsonGenerator gen, Object value, JsonSerializer<Object> ser) throws IOException {
        try {
            ser.serialize(value, gen, this);
        }
        catch (Exception e10) {
            throw this._wrapAsIOE(gen, e10);
        }
    }

    protected void _serializeNull(JsonGenerator gen) throws IOException {
        JsonSerializer<Object> ser = this.getDefaultNullValueSerializer();
        try {
            ser.serialize(null, gen, this);
        }
        catch (Exception e10) {
            throw this._wrapAsIOE(gen, e10);
        }
    }

    private IOException _wrapAsIOE(JsonGenerator g10, Exception e10) {
        if (e10 instanceof IOException) {
            return (IOException)e10;
        }
        String msg = e10.getMessage();
        if (msg == null) {
            msg = "[no message for " + e10.getClass().getName() + "]";
        }
        return new JsonMappingException((Closeable)g10, msg, (Throwable)e10);
    }

    public int cachedSerializersCount() {
        return this._serializerCache.size();
    }

    public void flushCachedSerializers() {
        this._serializerCache.flush();
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper visitor) throws JsonMappingException {
        if (javaType == null) {
            throw new IllegalArgumentException("A class must be provided");
        }
        visitor.setProvider(this);
        this.findValueSerializer(javaType, null).acceptJsonFormatVisitor(visitor, javaType);
    }

    @Deprecated
    public JsonSchema generateJsonSchema(Class<?> type) throws JsonMappingException {
        JsonNode schemaNode;
        JsonSerializer<Object> ser = this.findValueSerializer(type, null);
        JsonNode jsonNode = schemaNode = ser instanceof SchemaAware ? ((SchemaAware)((Object)ser)).getSchema(this, null) : JsonSchema.getDefaultSchemaNode();
        if (!(schemaNode instanceof ObjectNode)) {
            throw new IllegalArgumentException("Class " + type.getName() + " would not be serialized as a JSON object and therefore has no schema");
        }
        return new JsonSchema((ObjectNode)schemaNode);
    }

    public static final class Impl
    extends DefaultSerializerProvider {
        private static final long serialVersionUID = 1L;

        public Impl() {
        }

        public Impl(Impl src) {
            super(src);
        }

        protected Impl(SerializerProvider src, SerializationConfig config, SerializerFactory f10) {
            super(src, config, f10);
        }

        @Override
        public DefaultSerializerProvider copy() {
            if (this.getClass() != Impl.class) {
                return super.copy();
            }
            return new Impl(this);
        }

        @Override
        public Impl createInstance(SerializationConfig config, SerializerFactory jsf) {
            return new Impl(this, config, jsf);
        }
    }
}

