/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.NullValueProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;

public class ObjectIdReferenceProperty
extends SettableBeanProperty {
    private static final long serialVersionUID = 1L;
    private final SettableBeanProperty _forward;

    public ObjectIdReferenceProperty(SettableBeanProperty forward, ObjectIdInfo objectIdInfo) {
        super(forward);
        this._forward = forward;
        this._objectIdInfo = objectIdInfo;
    }

    public ObjectIdReferenceProperty(ObjectIdReferenceProperty src, JsonDeserializer<?> deser, NullValueProvider nva) {
        super(src, deser, nva);
        this._forward = src._forward;
        this._objectIdInfo = src._objectIdInfo;
    }

    public ObjectIdReferenceProperty(ObjectIdReferenceProperty src, PropertyName newName) {
        super(src, newName);
        this._forward = src._forward;
        this._objectIdInfo = src._objectIdInfo;
    }

    @Override
    public SettableBeanProperty withName(PropertyName newName) {
        return new ObjectIdReferenceProperty(this, newName);
    }

    @Override
    public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> deser) {
        if (this._valueDeserializer == deser) {
            return this;
        }
        return new ObjectIdReferenceProperty(this, deser, this._nullProvider);
    }

    @Override
    public SettableBeanProperty withNullProvider(NullValueProvider nva) {
        return new ObjectIdReferenceProperty(this, this._valueDeserializer, nva);
    }

    @Override
    public void fixAccess(DeserializationConfig config) {
        if (this._forward != null) {
            this._forward.fixAccess(config);
        }
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> acls) {
        return this._forward.getAnnotation(acls);
    }

    @Override
    public AnnotatedMember getMember() {
        return this._forward.getMember();
    }

    @Override
    public int getCreatorIndex() {
        return this._forward.getCreatorIndex();
    }

    @Override
    public void deserializeAndSet(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        this.deserializeSetAndReturn(p2, ctxt, instance);
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        try {
            return this.setAndReturn(instance, this.deserialize(p2, ctxt));
        }
        catch (UnresolvedForwardReference reference) {
            boolean usingIdentityInfo;
            boolean bl2 = usingIdentityInfo = this._objectIdInfo != null || this._valueDeserializer.getObjectIdReader() != null;
            if (!usingIdentityInfo) {
                throw JsonMappingException.from(p2, "Unresolved forward reference but no identity info.", (Throwable)reference);
            }
            reference.getRoid().appendReferring(new PropertyReferring(this, reference, this._type.getRawClass(), instance));
            return null;
        }
    }

    @Override
    public void set(Object instance, Object value) throws IOException {
        this._forward.set(instance, value);
    }

    @Override
    public Object setAndReturn(Object instance, Object value) throws IOException {
        return this._forward.setAndReturn(instance, value);
    }

    public static final class PropertyReferring
    extends ReadableObjectId.Referring {
        private final ObjectIdReferenceProperty _parent;
        public final Object _pojo;

        public PropertyReferring(ObjectIdReferenceProperty parent, UnresolvedForwardReference ref, Class<?> type, Object ob) {
            super(ref, type);
            this._parent = parent;
            this._pojo = ob;
        }

        @Override
        public void handleResolvedForwardReference(Object id, Object value) throws IOException {
            if (!this.hasId(id)) {
                throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + id + "] that wasn't previously seen as unresolved.");
            }
            this._parent.set(this._pojo, value);
        }
    }
}

