/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.FailingDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Annotations;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ViewMatcher;

public abstract class SettableBeanProperty
extends ConcreteBeanPropertyBase
implements Serializable {
    protected static final JsonDeserializer<Object> MISSING_VALUE_DESERIALIZER = new FailingDeserializer("No _valueDeserializer assigned");
    protected final PropertyName _propName;
    protected final JavaType _type;
    protected final PropertyName _wrapperName;
    protected final transient Annotations _contextAnnotations;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected final NullValueProvider _nullProvider;
    protected String _managedReferenceName;
    protected ObjectIdInfo _objectIdInfo;
    protected ViewMatcher _viewMatcher;
    protected int _propertyIndex = -1;

    protected SettableBeanProperty(BeanPropertyDefinition propDef, JavaType type, TypeDeserializer typeDeser, Annotations contextAnnotations) {
        this(propDef.getFullName(), type, propDef.getWrapperName(), typeDeser, contextAnnotations, propDef.getMetadata());
    }

    protected SettableBeanProperty(PropertyName propName, JavaType type, PropertyName wrapper, TypeDeserializer typeDeser, Annotations contextAnnotations, PropertyMetadata metadata) {
        super(metadata);
        this._propName = propName == null ? PropertyName.NO_NAME : propName.internSimpleName();
        this._type = type;
        this._wrapperName = wrapper;
        this._contextAnnotations = contextAnnotations;
        this._viewMatcher = null;
        if (typeDeser != null) {
            typeDeser = typeDeser.forProperty(this);
        }
        this._valueTypeDeserializer = typeDeser;
        this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
        this._nullProvider = MISSING_VALUE_DESERIALIZER;
    }

    protected SettableBeanProperty(PropertyName propName, JavaType type, PropertyMetadata metadata, JsonDeserializer<Object> valueDeser) {
        super(metadata);
        this._propName = propName == null ? PropertyName.NO_NAME : propName.internSimpleName();
        this._type = type;
        this._wrapperName = null;
        this._contextAnnotations = null;
        this._viewMatcher = null;
        this._valueTypeDeserializer = null;
        this._valueDeserializer = valueDeser;
        this._nullProvider = valueDeser;
    }

    protected SettableBeanProperty(SettableBeanProperty src) {
        super(src);
        this._propName = src._propName;
        this._type = src._type;
        this._wrapperName = src._wrapperName;
        this._contextAnnotations = src._contextAnnotations;
        this._valueDeserializer = src._valueDeserializer;
        this._valueTypeDeserializer = src._valueTypeDeserializer;
        this._managedReferenceName = src._managedReferenceName;
        this._propertyIndex = src._propertyIndex;
        this._viewMatcher = src._viewMatcher;
        this._nullProvider = src._nullProvider;
    }

    protected SettableBeanProperty(SettableBeanProperty src, JsonDeserializer<?> deser, NullValueProvider nuller) {
        super(src);
        this._propName = src._propName;
        this._type = src._type;
        this._wrapperName = src._wrapperName;
        this._contextAnnotations = src._contextAnnotations;
        this._valueTypeDeserializer = src._valueTypeDeserializer;
        this._managedReferenceName = src._managedReferenceName;
        this._propertyIndex = src._propertyIndex;
        this._valueDeserializer = deser == null ? MISSING_VALUE_DESERIALIZER : deser;
        this._viewMatcher = src._viewMatcher;
        if (nuller == MISSING_VALUE_DESERIALIZER) {
            nuller = this._valueDeserializer;
        }
        this._nullProvider = nuller;
    }

    protected SettableBeanProperty(SettableBeanProperty src, PropertyName newName) {
        super(src);
        this._propName = newName;
        this._type = src._type;
        this._wrapperName = src._wrapperName;
        this._contextAnnotations = src._contextAnnotations;
        this._valueDeserializer = src._valueDeserializer;
        this._valueTypeDeserializer = src._valueTypeDeserializer;
        this._managedReferenceName = src._managedReferenceName;
        this._propertyIndex = src._propertyIndex;
        this._viewMatcher = src._viewMatcher;
        this._nullProvider = src._nullProvider;
    }

    public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<?> var1);

    public abstract SettableBeanProperty withName(PropertyName var1);

    public SettableBeanProperty withSimpleName(String simpleName) {
        PropertyName n2 = this._propName == null ? new PropertyName(simpleName) : this._propName.withSimpleName(simpleName);
        return n2 == this._propName ? this : this.withName(n2);
    }

    public abstract SettableBeanProperty withNullProvider(NullValueProvider var1);

    public void setManagedReferenceName(String n2) {
        this._managedReferenceName = n2;
    }

    public void setObjectIdInfo(ObjectIdInfo objectIdInfo) {
        this._objectIdInfo = objectIdInfo;
    }

    public void setViews(Class<?>[] views) {
        this._viewMatcher = views == null ? null : ViewMatcher.construct(views);
    }

    public void assignIndex(int index) {
        if (this._propertyIndex != -1) {
            throw new IllegalStateException("Property '" + this.getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + index);
        }
        this._propertyIndex = index;
    }

    public void fixAccess(DeserializationConfig config) {
    }

    @Override
    public final String getName() {
        return this._propName.getSimpleName();
    }

    @Override
    public PropertyName getFullName() {
        return this._propName;
    }

    @Override
    public JavaType getType() {
        return this._type;
    }

    @Override
    public PropertyName getWrapperName() {
        return this._wrapperName;
    }

    @Override
    public abstract AnnotatedMember getMember();

    @Override
    public abstract <A extends Annotation> A getAnnotation(Class<A> var1);

    @Override
    public <A extends Annotation> A getContextAnnotation(Class<A> acls) {
        return this._contextAnnotations.get(acls);
    }

    @Override
    public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
        if (this.isRequired()) {
            objectVisitor.property(this);
        } else {
            objectVisitor.optionalProperty(this);
        }
    }

    protected Class<?> getDeclaringClass() {
        return this.getMember().getDeclaringClass();
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    public ObjectIdInfo getObjectIdInfo() {
        return this._objectIdInfo;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null && this._valueDeserializer != MISSING_VALUE_DESERIALIZER;
    }

    public boolean hasValueTypeDeserializer() {
        return this._valueTypeDeserializer != null;
    }

    public JsonDeserializer<Object> getValueDeserializer() {
        JsonDeserializer<Object> deser = this._valueDeserializer;
        if (deser == MISSING_VALUE_DESERIALIZER) {
            return null;
        }
        return deser;
    }

    public TypeDeserializer getValueTypeDeserializer() {
        return this._valueTypeDeserializer;
    }

    public NullValueProvider getNullValueProvider() {
        return this._nullProvider;
    }

    public boolean visibleInView(Class<?> activeView) {
        return this._viewMatcher == null || this._viewMatcher.isVisibleForView(activeView);
    }

    public boolean hasViews() {
        return this._viewMatcher != null;
    }

    public int getPropertyIndex() {
        return this._propertyIndex;
    }

    public int getCreatorIndex() {
        throw new IllegalStateException(String.format("Internal error: no creator index for property '%s' (of type %s)", this.getName(), this.getClass().getName()));
    }

    public Object getInjectableValueId() {
        return null;
    }

    public abstract void deserializeAndSet(JsonParser var1, DeserializationContext var2, Object var3) throws IOException;

    public abstract Object deserializeSetAndReturn(JsonParser var1, DeserializationContext var2, Object var3) throws IOException;

    public abstract void set(Object var1, Object var2) throws IOException;

    public abstract Object setAndReturn(Object var1, Object var2) throws IOException;

    public final Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.VALUE_NULL)) {
            return this._nullProvider.getNullValue(ctxt);
        }
        if (this._valueTypeDeserializer != null) {
            return this._valueDeserializer.deserializeWithType(p2, ctxt, this._valueTypeDeserializer);
        }
        return this._valueDeserializer.deserialize(p2, ctxt);
    }

    public final Object deserializeWith(JsonParser p2, DeserializationContext ctxt, Object toUpdate) throws IOException {
        if (p2.hasToken(JsonToken.VALUE_NULL)) {
            if (NullsConstantProvider.isSkipper(this._nullProvider)) {
                return toUpdate;
            }
            return this._nullProvider.getNullValue(ctxt);
        }
        if (this._valueTypeDeserializer != null) {
            ctxt.reportBadDefinition(this.getType(), String.format("Cannot merge polymorphic property '%s'", this.getName()));
        }
        return this._valueDeserializer.deserialize(p2, ctxt, toUpdate);
    }

    protected void _throwAsIOE(JsonParser p2, Exception e10, Object value) throws IOException {
        if (e10 instanceof IllegalArgumentException) {
            String actType = ClassUtil.classNameOf(value);
            StringBuilder msg = new StringBuilder("Problem deserializing property '").append(this.getName()).append("' (expected type: ").append(this.getType()).append("; actual type: ").append(actType).append(")");
            String origMsg = e10.getMessage();
            if (origMsg != null) {
                msg.append(", problem: ").append(origMsg);
            } else {
                msg.append(" (no error message provided)");
            }
            throw JsonMappingException.from(p2, msg.toString(), (Throwable)e10);
        }
        this._throwAsIOE(p2, e10);
    }

    protected IOException _throwAsIOE(JsonParser p2, Exception e10) throws IOException {
        ClassUtil.throwIfIOE(e10);
        ClassUtil.throwIfRTE(e10);
        Throwable th = ClassUtil.getRootCause(e10);
        throw JsonMappingException.from(p2, th.getMessage(), th);
    }

    @Deprecated
    protected IOException _throwAsIOE(Exception e10) throws IOException {
        return this._throwAsIOE((JsonParser)null, e10);
    }

    protected void _throwAsIOE(Exception e10, Object value) throws IOException {
        this._throwAsIOE(null, e10, value);
    }

    public String toString() {
        return "[property '" + this.getName() + "']";
    }

    public static abstract class Delegating
    extends SettableBeanProperty {
        protected final SettableBeanProperty delegate;

        protected Delegating(SettableBeanProperty d10) {
            super(d10);
            this.delegate = d10;
        }

        protected abstract SettableBeanProperty withDelegate(SettableBeanProperty var1);

        protected SettableBeanProperty _with(SettableBeanProperty newDelegate) {
            if (newDelegate == this.delegate) {
                return this;
            }
            return this.withDelegate(newDelegate);
        }

        @Override
        public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> deser) {
            return this._with(this.delegate.withValueDeserializer(deser));
        }

        @Override
        public SettableBeanProperty withName(PropertyName newName) {
            return this._with(this.delegate.withName(newName));
        }

        @Override
        public SettableBeanProperty withNullProvider(NullValueProvider nva) {
            return this._with(this.delegate.withNullProvider(nva));
        }

        @Override
        public void assignIndex(int index) {
            this.delegate.assignIndex(index);
        }

        @Override
        public void fixAccess(DeserializationConfig config) {
            this.delegate.fixAccess(config);
        }

        @Override
        protected Class<?> getDeclaringClass() {
            return this.delegate.getDeclaringClass();
        }

        @Override
        public String getManagedReferenceName() {
            return this.delegate.getManagedReferenceName();
        }

        @Override
        public ObjectIdInfo getObjectIdInfo() {
            return this.delegate.getObjectIdInfo();
        }

        @Override
        public boolean hasValueDeserializer() {
            return this.delegate.hasValueDeserializer();
        }

        @Override
        public boolean hasValueTypeDeserializer() {
            return this.delegate.hasValueTypeDeserializer();
        }

        @Override
        public JsonDeserializer<Object> getValueDeserializer() {
            return this.delegate.getValueDeserializer();
        }

        @Override
        public TypeDeserializer getValueTypeDeserializer() {
            return this.delegate.getValueTypeDeserializer();
        }

        @Override
        public boolean visibleInView(Class<?> activeView) {
            return this.delegate.visibleInView(activeView);
        }

        @Override
        public boolean hasViews() {
            return this.delegate.hasViews();
        }

        @Override
        public int getPropertyIndex() {
            return this.delegate.getPropertyIndex();
        }

        @Override
        public int getCreatorIndex() {
            return this.delegate.getCreatorIndex();
        }

        @Override
        public Object getInjectableValueId() {
            return this.delegate.getInjectableValueId();
        }

        @Override
        public AnnotatedMember getMember() {
            return this.delegate.getMember();
        }

        @Override
        public <A extends Annotation> A getAnnotation(Class<A> acls) {
            return this.delegate.getAnnotation(acls);
        }

        public SettableBeanProperty getDelegate() {
            return this.delegate;
        }

        @Override
        public void deserializeAndSet(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
            this.delegate.deserializeAndSet(p2, ctxt, instance);
        }

        @Override
        public Object deserializeSetAndReturn(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
            return this.delegate.deserializeSetAndReturn(p2, ctxt, instance);
        }

        @Override
        public void set(Object instance, Object value) throws IOException {
            this.delegate.set(instance, value);
        }

        @Override
        public Object setAndReturn(Object instance, Object value) throws IOException {
            return this.delegate.setAndReturn(instance, value);
        }
    }
}

