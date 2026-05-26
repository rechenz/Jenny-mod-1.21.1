/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.MemberKey;

public final class AnnotatedMethodMap
implements Iterable<AnnotatedMethod> {
    protected Map<MemberKey, AnnotatedMethod> _methods;

    public AnnotatedMethodMap() {
    }

    public AnnotatedMethodMap(Map<MemberKey, AnnotatedMethod> m2) {
        this._methods = m2;
    }

    public int size() {
        return this._methods == null ? 0 : this._methods.size();
    }

    public AnnotatedMethod find(String name, Class<?>[] paramTypes) {
        if (this._methods == null) {
            return null;
        }
        return this._methods.get(new MemberKey(name, paramTypes));
    }

    public AnnotatedMethod find(Method m2) {
        if (this._methods == null) {
            return null;
        }
        return this._methods.get(new MemberKey(m2));
    }

    @Override
    public Iterator<AnnotatedMethod> iterator() {
        if (this._methods == null) {
            return Collections.emptyIterator();
        }
        return this._methods.values().iterator();
    }
}

