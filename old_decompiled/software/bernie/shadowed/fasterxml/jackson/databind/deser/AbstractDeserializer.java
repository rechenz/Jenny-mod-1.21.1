/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerators;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdResolver;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ContextualDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public class AbstractDeserializer
extends JsonDeserializer<Object>
implements ContextualDeserializer,
Serializable {
    private static final long serialVersionUID = 1L;
    protected final JavaType _baseType;
    protected final ObjectIdReader _objectIdReader;
    protected final Map<String, SettableBeanProperty> _backRefProperties;
    protected transient Map<String, SettableBeanProperty> _properties;
    protected final boolean _acceptString;
    protected final boolean _acceptBoolean;
    protected final boolean _acceptInt;
    protected final boolean _acceptDouble;

    public AbstractDeserializer(BeanDeserializerBuilder builder, BeanDescription beanDesc, Map<String, SettableBeanProperty> backRefProps, Map<String, SettableBeanProperty> props) {
        this._baseType = beanDesc.getType();
        this._objectIdReader = builder.getObjectIdReader();
        this._backRefProperties = backRefProps;
        this._properties = props;
        Class<Object> cls = this._baseType.getRawClass();
        this._acceptString = cls.isAssignableFrom(String.class);
        this._acceptBoolean = cls == Boolean.TYPE || cls.isAssignableFrom(Boolean.class);
        this._acceptInt = cls == Integer.TYPE || cls.isAssignableFrom(Integer.class);
        this._acceptDouble = cls == Double.TYPE || cls.isAssignableFrom(Double.class);
    }

    @Deprecated
    public AbstractDeserializer(BeanDeserializerBuilder builder, BeanDescription beanDesc, Map<String, SettableBeanProperty> backRefProps) {
        this(builder, beanDesc, backRefProps, null);
    }

    protected AbstractDeserializer(BeanDescription beanDesc) {
        this._baseType = beanDesc.getType();
        this._objectIdReader = null;
        this._backRefProperties = null;
        Class<Object> cls = this._baseType.getRawClass();
        this._acceptString = cls.isAssignableFrom(String.class);
        this._acceptBoolean = cls == Boolean.TYPE || cls.isAssignableFrom(Boolean.class);
        this._acceptInt = cls == Integer.TYPE || cls.isAssignableFrom(Integer.class);
        this._acceptDouble = cls == Double.TYPE || cls.isAssignableFrom(Double.class);
    }

    protected AbstractDeserializer(AbstractDeserializer base, ObjectIdReader objectIdReader, Map<String, SettableBeanProperty> props) {
        this._baseType = base._baseType;
        this._backRefProperties = base._backRefProperties;
        this._acceptString = base._acceptString;
        this._acceptBoolean = base._acceptBoolean;
        this._acceptInt = base._acceptInt;
        this._acceptDouble = base._acceptDouble;
        this._objectIdReader = objectIdReader;
        this._properties = props;
    }

    public static AbstractDeserializer constructForNonPOJO(BeanDescription beanDesc) {
        return new AbstractDeserializer(beanDesc);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        ObjectIdInfo objectIdInfo;
        AnnotatedMember accessor;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (property != null && intr != null && (accessor = property.getMember()) != null && (objectIdInfo = intr.findObjectIdInfo(accessor)) != null) {
            ObjectIdGenerator idGen;
            JavaType idType;
            SettableBeanProperty idProp = null;
            ObjectIdResolver resolver = ctxt.objectIdResolverInstance(accessor, objectIdInfo);
            Class<? extends ObjectIdGenerator<?>> implClass = (objectIdInfo = intr.findObjectReferenceInfo(accessor, objectIdInfo)).getGeneratorType();
            if (implClass == ObjectIdGenerators.PropertyGenerator.class) {
                PropertyName propName = objectIdInfo.getPropertyName();
                SettableBeanProperty settableBeanProperty = idProp = this._properties == null ? null : this._properties.get(propName.getSimpleName());
                if (idProp == null) {
                    ctxt.reportBadDefinition(this._baseType, String.format("Invalid Object Id definition for %s: cannot find property with name '%s'", this.handledType().getName(), propName));
                }
                idType = idProp.getType();
                idGen = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
            } else {
                resolver = ctxt.objectIdResolverInstance(accessor, objectIdInfo);
                JavaType type = ctxt.constructType(implClass);
                idType = ctxt.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
                idGen = ctxt.objectIdGeneratorInstance(accessor, objectIdInfo);
            }
            JsonDeserializer<Object> deser = ctxt.findRootValueDeserializer(idType);
            ObjectIdReader oir = ObjectIdReader.construct(idType, objectIdInfo.getPropertyName(), idGen, deser, idProp, resolver);
            return new AbstractDeserializer(this, oir, null);
        }
        if (this._properties == null) {
            return this;
        }
        return new AbstractDeserializer(this, this._objectIdReader, null);
    }

    @Override
    public Class<?> handledType() {
        return this._baseType.getRawClass();
    }

    @Override
    public boolean isCachable() {
        return true;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return null;
    }

    @Override
    public ObjectIdReader getObjectIdReader() {
        return this._objectIdReader;
    }

    @Override
    public SettableBeanProperty findBackReference(String logicalName) {
        return this._backRefProperties == null ? null : this._backRefProperties.get(logicalName);
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        Object result;
        JsonToken t2;
        if (this._objectIdReader != null && (t2 = p2.getCurrentToken()) != null) {
            if (t2.isScalarValue()) {
                return this._deserializeFromObjectId(p2, ctxt);
            }
            if (t2 == JsonToken.START_OBJECT) {
                t2 = p2.nextToken();
            }
            if (t2 == JsonToken.FIELD_NAME && this._objectIdReader.maySerializeAsObject() && this._objectIdReader.isValidReferencePropertyName(p2.getCurrentName(), p2)) {
                return this._deserializeFromObjectId(p2, ctxt);
            }
        }
        if ((result = this._deserializeIfNatural(p2, ctxt)) != null) {
            return result;
        }
        return typeDeserializer.deserializeTypedFromObject(p2, ctxt);
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        ValueInstantiator.Base bogus = new ValueInstantiator.Base(this._baseType);
        return ctxt.handleMissingInstantiator(this._baseType.getRawClass(), bogus, p2, "abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information", new Object[0]);
    }

    protected Object _deserializeIfNatural(JsonParser p2, DeserializationContext ctxt) throws IOException {
        switch (p2.getCurrentTokenId()) {
            case 6: {
                if (!this._acceptString) break;
                return p2.getText();
            }
            case 7: {
                if (!this._acceptInt) break;
                return p2.getIntValue();
            }
            case 8: {
                if (!this._acceptDouble) break;
                return p2.getDoubleValue();
            }
            case 9: {
                if (!this._acceptBoolean) break;
                return Boolean.TRUE;
            }
            case 10: {
                if (!this._acceptBoolean) break;
                return Boolean.FALSE;
            }
        }
        return null;
    }

    protected Object _deserializeFromObjectId(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object id = this._objectIdReader.readObjectReference(p2, ctxt);
        ReadableObjectId roid = ctxt.findObjectId(id, this._objectIdReader.generator, this._objectIdReader.resolver);
        Object pojo = roid.resolve();
        if (pojo == null) {
            throw new UnresolvedForwardReference(p2, "Could not resolve Object Id [" + id + "] -- unresolved forward-reference?", p2.getCurrentLocation(), roid);
        }
        return pojo;
    }
}

