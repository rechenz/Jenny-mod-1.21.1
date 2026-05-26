/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;

public final class ManagedReferenceProperty
extends SettableBeanProperty.Delegating {
    private static final long serialVersionUID = 1L;
    protected final String _referenceName;
    protected final boolean _isContainer;
    protected final SettableBeanProperty _backProperty;

    public ManagedReferenceProperty(SettableBeanProperty forward, String refName, SettableBeanProperty backward, boolean isContainer) {
        super(forward);
        this._referenceName = refName;
        this._backProperty = backward;
        this._isContainer = isContainer;
    }

    @Override
    protected SettableBeanProperty withDelegate(SettableBeanProperty d10) {
        throw new IllegalStateException("Should never try to reset delegate");
    }

    @Override
    public void fixAccess(DeserializationConfig config) {
        this.delegate.fixAccess(config);
        this._backProperty.fixAccess(config);
    }

    @Override
    public void deserializeAndSet(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        this.set(instance, this.delegate.deserialize(p2, ctxt));
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        return this.setAndReturn(instance, this.deserialize(p2, ctxt));
    }

    @Override
    public final void set(Object instance, Object value) throws IOException {
        this.setAndReturn(instance, value);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public Object setAndReturn(Object instance, Object value) throws IOException {
        if (value == null) return this.delegate.setAndReturn(instance, value);
        if (this._isContainer) {
            if (value instanceof Object[]) {
                for (Object ob : (Object[])value) {
                    if (ob == null) continue;
                    this._backProperty.set(ob, instance);
                }
                return this.delegate.setAndReturn(instance, value);
            } else if (value instanceof Collection) {
                for (Object ob : (Collection)value) {
                    if (ob == null) continue;
                    this._backProperty.set(ob, instance);
                }
                return this.delegate.setAndReturn(instance, value);
            } else {
                if (!(value instanceof Map)) throw new IllegalStateException("Unsupported container type (" + value.getClass().getName() + ") when resolving reference '" + this._referenceName + "'");
                for (Object ob : ((Map)value).values()) {
                    if (ob == null) continue;
                    this._backProperty.set(ob, instance);
                }
            }
            return this.delegate.setAndReturn(instance, value);
        } else {
            this._backProperty.set(value, instance);
        }
        return this.delegate.setAndReturn(instance, value);
    }
}

