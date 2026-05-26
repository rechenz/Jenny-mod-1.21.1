/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.type;

import java.io.Serializable;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class TypeBindings
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String[] NO_STRINGS = new String[0];
    private static final JavaType[] NO_TYPES = new JavaType[0];
    private static final TypeBindings EMPTY = new TypeBindings(NO_STRINGS, NO_TYPES, null);
    private final String[] _names;
    private final JavaType[] _types;
    private final String[] _unboundVariables;
    private final int _hashCode;

    private TypeBindings(String[] names, JavaType[] types, String[] uvars) {
        this._names = names == null ? NO_STRINGS : names;
        JavaType[] javaTypeArray = this._types = types == null ? NO_TYPES : types;
        if (this._names.length != this._types.length) {
            throw new IllegalArgumentException("Mismatching names (" + this._names.length + "), types (" + this._types.length + ")");
        }
        int h2 = 1;
        int len = this._types.length;
        for (int i2 = 0; i2 < len; ++i2) {
            h2 += this._types[i2].hashCode();
        }
        this._unboundVariables = uvars;
        this._hashCode = h2;
    }

    public static TypeBindings emptyBindings() {
        return EMPTY;
    }

    protected Object readResolve() {
        if (this._names == null || this._names.length == 0) {
            return EMPTY;
        }
        return this;
    }

    public static TypeBindings create(Class<?> erasedType, List<JavaType> typeList) {
        JavaType[] types = typeList == null || typeList.isEmpty() ? NO_TYPES : typeList.toArray(new JavaType[typeList.size()]);
        return TypeBindings.create(erasedType, types);
    }

    public static TypeBindings create(Class<?> erasedType, JavaType[] types) {
        String[] names;
        if (types == null) {
            types = NO_TYPES;
        } else {
            switch (types.length) {
                case 1: {
                    return TypeBindings.create(erasedType, types[0]);
                }
                case 2: {
                    return TypeBindings.create(erasedType, types[0], types[1]);
                }
            }
        }
        TypeVariable<Class<?>>[] vars = erasedType.getTypeParameters();
        if (vars == null || vars.length == 0) {
            names = NO_STRINGS;
        } else {
            int len = vars.length;
            names = new String[len];
            for (int i2 = 0; i2 < len; ++i2) {
                names[i2] = vars[i2].getName();
            }
        }
        if (names.length != types.length) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with " + types.length + " type parameter" + (types.length == 1 ? "" : "s") + ": class expects " + names.length);
        }
        return new TypeBindings(names, types, null);
    }

    public static TypeBindings create(Class<?> erasedType, JavaType typeArg1) {
        int varLen;
        TypeVariable<?>[] vars = TypeParamStash.paramsFor1(erasedType);
        int n2 = varLen = vars == null ? 0 : vars.length;
        if (varLen != 1) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with 1 type parameter: class expects " + varLen);
        }
        return new TypeBindings(new String[]{vars[0].getName()}, new JavaType[]{typeArg1}, null);
    }

    public static TypeBindings create(Class<?> erasedType, JavaType typeArg1, JavaType typeArg2) {
        int varLen;
        TypeVariable<?>[] vars = TypeParamStash.paramsFor2(erasedType);
        int n2 = varLen = vars == null ? 0 : vars.length;
        if (varLen != 2) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with 2 type parameters: class expects " + varLen);
        }
        return new TypeBindings(new String[]{vars[0].getName(), vars[1].getName()}, new JavaType[]{typeArg1, typeArg2}, null);
    }

    public static TypeBindings createIfNeeded(Class<?> erasedType, JavaType typeArg1) {
        int varLen;
        TypeVariable<Class<?>>[] vars = erasedType.getTypeParameters();
        int n2 = varLen = vars == null ? 0 : vars.length;
        if (varLen == 0) {
            return EMPTY;
        }
        if (varLen != 1) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with 1 type parameter: class expects " + varLen);
        }
        return new TypeBindings(new String[]{vars[0].getName()}, new JavaType[]{typeArg1}, null);
    }

    public static TypeBindings createIfNeeded(Class<?> erasedType, JavaType[] types) {
        TypeVariable<Class<?>>[] vars = erasedType.getTypeParameters();
        if (vars == null || vars.length == 0) {
            return EMPTY;
        }
        if (types == null) {
            types = NO_TYPES;
        }
        int len = vars.length;
        String[] names = new String[len];
        for (int i2 = 0; i2 < len; ++i2) {
            names[i2] = vars[i2].getName();
        }
        if (names.length != types.length) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with " + types.length + " type parameter" + (types.length == 1 ? "" : "s") + ": class expects " + names.length);
        }
        return new TypeBindings(names, types, null);
    }

    public TypeBindings withUnboundVariable(String name) {
        int len = this._unboundVariables == null ? 0 : this._unboundVariables.length;
        String[] names = len == 0 ? new String[1] : Arrays.copyOf(this._unboundVariables, len + 1);
        names[len] = name;
        return new TypeBindings(this._names, this._types, names);
    }

    public JavaType findBoundType(String name) {
        int len = this._names.length;
        for (int i2 = 0; i2 < len; ++i2) {
            ResolvedRecursiveType rrt;
            JavaType t2;
            if (!name.equals(this._names[i2])) continue;
            JavaType t3 = this._types[i2];
            if (t3 instanceof ResolvedRecursiveType && (t2 = (rrt = (ResolvedRecursiveType)t3).getSelfReferencedType()) != null) {
                t3 = t2;
            }
            return t3;
        }
        return null;
    }

    public boolean isEmpty() {
        return this._types.length == 0;
    }

    public int size() {
        return this._types.length;
    }

    public String getBoundName(int index) {
        if (index < 0 || index >= this._names.length) {
            return null;
        }
        return this._names[index];
    }

    public JavaType getBoundType(int index) {
        if (index < 0 || index >= this._types.length) {
            return null;
        }
        return this._types[index];
    }

    public List<JavaType> getTypeParameters() {
        if (this._types.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.asList(this._types);
    }

    public boolean hasUnbound(String name) {
        if (this._unboundVariables != null) {
            int i2 = this._unboundVariables.length;
            while (--i2 >= 0) {
                if (!name.equals(this._unboundVariables[i2])) continue;
                return true;
            }
        }
        return false;
    }

    public Object asKey(Class<?> rawBase) {
        return new AsKey(rawBase, this._types, this._hashCode);
    }

    public String toString() {
        if (this._types.length == 0) {
            return "<>";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        int len = this._types.length;
        for (int i2 = 0; i2 < len; ++i2) {
            if (i2 > 0) {
                sb.append(',');
            }
            String sig = this._types[i2].getGenericSignature();
            sb.append(sig);
        }
        sb.append('>');
        return sb.toString();
    }

    public int hashCode() {
        return this._hashCode;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!ClassUtil.hasClass(o2, this.getClass())) {
            return false;
        }
        int len = this._types.length;
        TypeBindings other = (TypeBindings)o2;
        if (len != other.size()) {
            return false;
        }
        JavaType[] otherTypes = other._types;
        for (int i2 = 0; i2 < len; ++i2) {
            if (otherTypes[i2].equals(this._types[i2])) continue;
            return false;
        }
        return true;
    }

    protected JavaType[] typeParameterArray() {
        return this._types;
    }

    static final class AsKey {
        private final Class<?> _raw;
        private final JavaType[] _params;
        private final int _hash;

        public AsKey(Class<?> raw, JavaType[] params, int hash) {
            this._raw = raw;
            this._params = params;
            this._hash = hash;
        }

        public int hashCode() {
            return this._hash;
        }

        public boolean equals(Object o2) {
            JavaType[] otherParams;
            int len;
            if (o2 == this) {
                return true;
            }
            if (o2 == null) {
                return false;
            }
            if (o2.getClass() != this.getClass()) {
                return false;
            }
            AsKey other = (AsKey)o2;
            if (this._hash == other._hash && this._raw == other._raw && (len = this._params.length) == (otherParams = other._params).length) {
                for (int i2 = 0; i2 < len; ++i2) {
                    if (this._params[i2].equals(otherParams[i2])) continue;
                    return false;
                }
                return true;
            }
            return false;
        }

        public String toString() {
            return this._raw.getName() + "<>";
        }
    }

    static class TypeParamStash {
        private static final TypeVariable<?>[] VARS_ABSTRACT_LIST = AbstractList.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_COLLECTION = Collection.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_ITERABLE = Iterable.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_LIST = List.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_ARRAY_LIST = ArrayList.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_MAP = Map.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_HASH_MAP = HashMap.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_LINKED_HASH_MAP = LinkedHashMap.class.getTypeParameters();

        TypeParamStash() {
        }

        public static TypeVariable<?>[] paramsFor1(Class<?> erasedType) {
            if (erasedType == Collection.class) {
                return VARS_COLLECTION;
            }
            if (erasedType == List.class) {
                return VARS_LIST;
            }
            if (erasedType == ArrayList.class) {
                return VARS_ARRAY_LIST;
            }
            if (erasedType == AbstractList.class) {
                return VARS_ABSTRACT_LIST;
            }
            if (erasedType == Iterable.class) {
                return VARS_ITERABLE;
            }
            return erasedType.getTypeParameters();
        }

        public static TypeVariable<?>[] paramsFor2(Class<?> erasedType) {
            if (erasedType == Map.class) {
                return VARS_MAP;
            }
            if (erasedType == HashMap.class) {
                return VARS_HASH_MAP;
            }
            if (erasedType == LinkedHashMap.class) {
                return VARS_LINKED_HASH_MAP;
            }
            return erasedType.getTypeParameters();
        }
    }
}

