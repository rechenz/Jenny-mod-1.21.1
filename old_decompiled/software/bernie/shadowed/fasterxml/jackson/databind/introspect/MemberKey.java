/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class MemberKey {
    static final Class<?>[] NO_CLASSES = new Class[0];
    final String _name;
    final Class<?>[] _argTypes;

    public MemberKey(Method m2) {
        this(m2.getName(), m2.getParameterTypes());
    }

    public MemberKey(Constructor<?> ctor) {
        this("", ctor.getParameterTypes());
    }

    public MemberKey(String name, Class<?>[] argTypes) {
        this._name = name;
        this._argTypes = argTypes == null ? NO_CLASSES : argTypes;
    }

    public String toString() {
        return this._name + "(" + this._argTypes.length + "-args)";
    }

    public int hashCode() {
        return this._name.hashCode() + this._argTypes.length;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (o2.getClass() != this.getClass()) {
            return false;
        }
        MemberKey other = (MemberKey)o2;
        if (!this._name.equals(other._name)) {
            return false;
        }
        Class<?>[] otherArgs = other._argTypes;
        int len = this._argTypes.length;
        if (otherArgs.length != len) {
            return false;
        }
        for (int i2 = 0; i2 < len; ++i2) {
            Class<?> type1 = otherArgs[i2];
            Class<?> type2 = this._argTypes[i2];
            if (type1 == type2) continue;
            return false;
        }
        return true;
    }
}

