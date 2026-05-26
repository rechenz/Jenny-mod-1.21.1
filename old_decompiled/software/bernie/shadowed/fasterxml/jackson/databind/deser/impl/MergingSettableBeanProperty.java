/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;

public class MergingSettableBeanProperty
extends SettableBeanProperty.Delegating {
    private static final long serialVersionUID = 1L;
    protected final AnnotatedMember _accessor;

    protected MergingSettableBeanProperty(SettableBeanProperty delegate, AnnotatedMember accessor) {
        super(delegate);
        this._accessor = accessor;
    }

    protected MergingSettableBeanProperty(MergingSettableBeanProperty src, SettableBeanProperty delegate) {
        super(delegate);
        this._accessor = src._accessor;
    }

    public static MergingSettableBeanProperty construct(SettableBeanProperty delegate, AnnotatedMember accessor) {
        return new MergingSettableBeanProperty(delegate, accessor);
    }

    @Override
    protected SettableBeanProperty withDelegate(SettableBeanProperty d10) {
        return new MergingSettableBeanProperty(d10, this._accessor);
    }

    @Override
    public void deserializeAndSet(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        Object oldValue = this._accessor.getValue(instance);
        Object newValue = oldValue == null ? this.delegate.deserialize(p2, ctxt) : this.delegate.deserializeWith(p2, ctxt, oldValue);
        if (newValue != oldValue) {
            this.delegate.set(instance, newValue);
        }
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        Object oldValue = this._accessor.getValue(instance);
        Object newValue = oldValue == null ? this.delegate.deserialize(p2, ctxt) : this.delegate.deserializeWith(p2, ctxt, oldValue);
        if (newValue != oldValue && newValue != null) {
            return this.delegate.setAndReturn(instance, newValue);
        }
        return instance;
    }

    @Override
    public void set(Object instance, Object value) throws IOException {
        if (value != null) {
            this.delegate.set(instance, value);
        }
    }

    @Override
    public Object setAndReturn(Object instance, Object value) throws IOException {
        if (value != null) {
            return this.delegate.setAndReturn(instance, value);
        }
        return instance;
    }
}

