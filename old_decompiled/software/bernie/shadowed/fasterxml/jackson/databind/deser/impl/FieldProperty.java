/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedField;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Annotations;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public final class FieldProperty
extends SettableBeanProperty {
    private static final long serialVersionUID = 1L;
    protected final AnnotatedField _annotated;
    protected final transient Field _field;
    protected final boolean _skipNulls;

    public FieldProperty(BeanPropertyDefinition propDef, JavaType type, TypeDeserializer typeDeser, Annotations contextAnnotations, AnnotatedField field) {
        super(propDef, type, typeDeser, contextAnnotations);
        this._annotated = field;
        this._field = field.getAnnotated();
        this._skipNulls = NullsConstantProvider.isSkipper(this._nullProvider);
    }

    protected FieldProperty(FieldProperty src, JsonDeserializer<?> deser, NullValueProvider nva) {
        super(src, deser, nva);
        this._annotated = src._annotated;
        this._field = src._field;
        this._skipNulls = NullsConstantProvider.isSkipper(nva);
    }

    protected FieldProperty(FieldProperty src, PropertyName newName) {
        super(src, newName);
        this._annotated = src._annotated;
        this._field = src._field;
        this._skipNulls = src._skipNulls;
    }

    protected FieldProperty(FieldProperty src) {
        super(src);
        this._annotated = src._annotated;
        Field f10 = this._annotated.getAnnotated();
        if (f10 == null) {
            throw new IllegalArgumentException("Missing field (broken JDK (de)serialization?)");
        }
        this._field = f10;
        this._skipNulls = src._skipNulls;
    }

    @Override
    public SettableBeanProperty withName(PropertyName newName) {
        return new FieldProperty(this, newName);
    }

    @Override
    public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> deser) {
        if (this._valueDeserializer == deser) {
            return this;
        }
        return new FieldProperty(this, deser, this._nullProvider);
    }

    @Override
    public SettableBeanProperty withNullProvider(NullValueProvider nva) {
        return new FieldProperty(this, this._valueDeserializer, nva);
    }

    @Override
    public void fixAccess(DeserializationConfig config) {
        ClassUtil.checkAndFixAccess(this._field, config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> acls) {
        return this._annotated == null ? null : (A)this._annotated.getAnnotation(acls);
    }

    @Override
    public AnnotatedMember getMember() {
        return this._annotated;
    }

    @Override
    public void deserializeAndSet(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        Object value;
        if (p2.hasToken(JsonToken.VALUE_NULL)) {
            if (this._skipNulls) {
                return;
            }
            value = this._nullProvider.getNullValue(ctxt);
        } else {
            value = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p2, ctxt) : this._valueDeserializer.deserializeWithType(p2, ctxt, this._valueTypeDeserializer);
        }
        try {
            this._field.set(instance, value);
        }
        catch (Exception e10) {
            this._throwAsIOE(p2, e10, value);
        }
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        Object value;
        if (p2.hasToken(JsonToken.VALUE_NULL)) {
            if (this._skipNulls) {
                return instance;
            }
            value = this._nullProvider.getNullValue(ctxt);
        } else {
            value = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p2, ctxt) : this._valueDeserializer.deserializeWithType(p2, ctxt, this._valueTypeDeserializer);
        }
        try {
            this._field.set(instance, value);
        }
        catch (Exception e10) {
            this._throwAsIOE(p2, e10, value);
        }
        return instance;
    }

    @Override
    public void set(Object instance, Object value) throws IOException {
        try {
            this._field.set(instance, value);
        }
        catch (Exception e10) {
            this._throwAsIOE(e10, value);
        }
    }

    @Override
    public Object setAndReturn(Object instance, Object value) throws IOException {
        try {
            this._field.set(instance, value);
        }
        catch (Exception e10) {
            this._throwAsIOE(e10, value);
        }
        return instance;
    }

    Object readResolve() {
        return new FieldProperty(this);
    }
}

