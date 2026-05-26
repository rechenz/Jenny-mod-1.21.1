/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.util.CompactStringObjectMap;

public class EnumResolver
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final Class<Enum<?>> _enumClass;
    protected final Enum<?>[] _enums;
    protected final HashMap<String, Enum<?>> _enumsById;
    protected final Enum<?> _defaultValue;

    protected EnumResolver(Class<Enum<?>> enumClass, Enum<?>[] enums, HashMap<String, Enum<?>> map, Enum<?> defaultValue) {
        this._enumClass = enumClass;
        this._enums = enums;
        this._enumsById = map;
        this._defaultValue = defaultValue;
    }

    public static EnumResolver constructFor(Class<Enum<?>> enumCls, AnnotationIntrospector ai2) {
        Enum<?>[] enumValues = enumCls.getEnumConstants();
        if (enumValues == null) {
            throw new IllegalArgumentException("No enum constants for class " + enumCls.getName());
        }
        String[] names = ai2.findEnumValues(enumCls, enumValues, new String[enumValues.length]);
        HashMap map = new HashMap();
        int len = enumValues.length;
        for (int i2 = 0; i2 < len; ++i2) {
            String name = names[i2];
            if (name == null) {
                name = enumValues[i2].name();
            }
            map.put(name, enumValues[i2]);
        }
        Enum<?> defaultEnum = ai2.findDefaultEnumValue(enumCls);
        return new EnumResolver(enumCls, enumValues, map, defaultEnum);
    }

    @Deprecated
    public static EnumResolver constructUsingToString(Class<Enum<?>> enumCls) {
        return EnumResolver.constructUsingToString(enumCls, null);
    }

    public static EnumResolver constructUsingToString(Class<Enum<?>> enumCls, AnnotationIntrospector ai2) {
        Enum<?>[] enumValues = enumCls.getEnumConstants();
        HashMap map = new HashMap();
        int i2 = enumValues.length;
        while (--i2 >= 0) {
            Enum<?> e10 = enumValues[i2];
            map.put(e10.toString(), e10);
        }
        Enum<?> defaultEnum = ai2 == null ? null : ai2.findDefaultEnumValue(enumCls);
        return new EnumResolver(enumCls, enumValues, map, defaultEnum);
    }

    public static EnumResolver constructUsingMethod(Class<Enum<?>> enumCls, AnnotatedMember accessor, AnnotationIntrospector ai2) {
        Enum<?>[] enumValues = enumCls.getEnumConstants();
        HashMap map = new HashMap();
        int i2 = enumValues.length;
        while (--i2 >= 0) {
            Enum<?> en2 = enumValues[i2];
            try {
                Object o2 = accessor.getValue(en2);
                if (o2 == null) continue;
                map.put(o2.toString(), en2);
            }
            catch (Exception e10) {
                throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + en2 + ": " + e10.getMessage());
            }
        }
        Enum<?> defaultEnum = ai2 != null ? ai2.findDefaultEnumValue(enumCls) : null;
        return new EnumResolver(enumCls, enumValues, map, defaultEnum);
    }

    public static EnumResolver constructUnsafe(Class<?> rawEnumCls, AnnotationIntrospector ai2) {
        Class<Enum<?>> enumCls = rawEnumCls;
        return EnumResolver.constructFor(enumCls, ai2);
    }

    public static EnumResolver constructUnsafeUsingToString(Class<?> rawEnumCls, AnnotationIntrospector ai2) {
        Class<Enum<?>> enumCls = rawEnumCls;
        return EnumResolver.constructUsingToString(enumCls, ai2);
    }

    public static EnumResolver constructUnsafeUsingMethod(Class<?> rawEnumCls, AnnotatedMember accessor, AnnotationIntrospector ai2) {
        Class<Enum<?>> enumCls = rawEnumCls;
        return EnumResolver.constructUsingMethod(enumCls, accessor, ai2);
    }

    public CompactStringObjectMap constructLookup() {
        return CompactStringObjectMap.construct(this._enumsById);
    }

    public Enum<?> findEnum(String key) {
        return this._enumsById.get(key);
    }

    public Enum<?> getEnum(int index) {
        if (index < 0 || index >= this._enums.length) {
            return null;
        }
        return this._enums[index];
    }

    public Enum<?> getDefaultValue() {
        return this._defaultValue;
    }

    public Enum<?>[] getRawEnums() {
        return this._enums;
    }

    public List<Enum<?>> getEnums() {
        ArrayList enums = new ArrayList(this._enums.length);
        for (Enum<?> e10 : this._enums) {
            enums.add(e10);
        }
        return enums;
    }

    public Collection<String> getEnumIds() {
        return this._enumsById.keySet();
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}

