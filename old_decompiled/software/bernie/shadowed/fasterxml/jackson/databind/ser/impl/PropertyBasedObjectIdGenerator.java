/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerators;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.BeanPropertyWriter;

public class PropertyBasedObjectIdGenerator
extends ObjectIdGenerators.PropertyGenerator {
    private static final long serialVersionUID = 1L;
    protected final BeanPropertyWriter _property;

    public PropertyBasedObjectIdGenerator(ObjectIdInfo oid, BeanPropertyWriter prop) {
        this(oid.getScope(), prop);
    }

    protected PropertyBasedObjectIdGenerator(Class<?> scope, BeanPropertyWriter prop) {
        super(scope);
        this._property = prop;
    }

    @Override
    public boolean canUseFor(ObjectIdGenerator<?> gen) {
        PropertyBasedObjectIdGenerator other;
        if (gen.getClass() == this.getClass() && (other = (PropertyBasedObjectIdGenerator)gen).getScope() == this._scope) {
            return other._property == this._property;
        }
        return false;
    }

    @Override
    public Object generateId(Object forPojo) {
        try {
            return this._property.get(forPojo);
        }
        catch (RuntimeException e10) {
            throw e10;
        }
        catch (Exception e11) {
            throw new IllegalStateException("Problem accessing property '" + this._property.getName() + "': " + e11.getMessage(), e11);
        }
    }

    @Override
    public ObjectIdGenerator<Object> forScope(Class<?> scope) {
        return scope == this._scope ? this : new PropertyBasedObjectIdGenerator(scope, this._property);
    }

    @Override
    public ObjectIdGenerator<Object> newForSerialization(Object context) {
        return this;
    }

    @Override
    public ObjectIdGenerator.IdKey key(Object key) {
        if (key == null) {
            return null;
        }
        return new ObjectIdGenerator.IdKey(this.getClass(), this._scope, key);
    }
}

