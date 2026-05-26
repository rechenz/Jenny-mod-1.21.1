/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public final class EnumValues
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Class<Enum<?>> _enumClass;
    private final Enum<?>[] _values;
    private final SerializableString[] _textual;
    private transient EnumMap<?, SerializableString> _asMap;

    private EnumValues(Class<Enum<?>> enumClass, SerializableString[] textual) {
        this._enumClass = enumClass;
        this._values = enumClass.getEnumConstants();
        this._textual = textual;
    }

    public static EnumValues construct(SerializationConfig config, Class<Enum<?>> enumClass) {
        if (config.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
            return EnumValues.constructFromToString(config, enumClass);
        }
        return EnumValues.constructFromName(config, enumClass);
    }

    public static EnumValues constructFromName(MapperConfig<?> config, Class<Enum<?>> enumClass) {
        Class<Enum<?>> enumCls = ClassUtil.findEnumType(enumClass);
        Enum<?>[] enumValues = enumCls.getEnumConstants();
        if (enumValues == null) {
            throw new IllegalArgumentException("Cannot determine enum constants for Class " + enumClass.getName());
        }
        String[] names = config.getAnnotationIntrospector().findEnumValues(enumCls, enumValues, new String[enumValues.length]);
        SerializableString[] textual = new SerializableString[enumValues.length];
        int len = enumValues.length;
        for (int i2 = 0; i2 < len; ++i2) {
            Enum<?> en2 = enumValues[i2];
            String name = names[i2];
            if (name == null) {
                name = en2.name();
            }
            textual[en2.ordinal()] = config.compileString(name);
        }
        return new EnumValues(enumClass, textual);
    }

    public static EnumValues constructFromToString(MapperConfig<?> config, Class<Enum<?>> enumClass) {
        Class<Enum<?>> cls = ClassUtil.findEnumType(enumClass);
        Enum<?>[] values = cls.getEnumConstants();
        if (values != null) {
            SerializableString[] textual = new SerializableString[values.length];
            for (Enum<?> en2 : values) {
                textual[en2.ordinal()] = config.compileString(en2.toString());
            }
            return new EnumValues(enumClass, textual);
        }
        throw new IllegalArgumentException("Cannot determine enum constants for Class " + enumClass.getName());
    }

    public SerializableString serializedValueFor(Enum<?> key) {
        return this._textual[key.ordinal()];
    }

    public Collection<SerializableString> values() {
        return Arrays.asList(this._textual);
    }

    public List<Enum<?>> enums() {
        return Arrays.asList(this._values);
    }

    public EnumMap<?, SerializableString> internalMap() {
        EnumMap<Object, SerializableString> result = this._asMap;
        if (result == null) {
            LinkedHashMap map = new LinkedHashMap();
            for (Enum<?> en2 : this._values) {
                map.put(en2, this._textual[en2.ordinal()]);
            }
            result = new EnumMap(map);
        }
        return result;
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }
}

