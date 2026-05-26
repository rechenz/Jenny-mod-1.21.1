/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.AtomicBooleanDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.ByteBufferDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StackTraceElementDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.UUIDDeserializer;

public class JdkDeserializers {
    private static final HashSet<String> _classNames;

    public static JsonDeserializer<?> find(Class<?> rawType, String clsName) {
        if (_classNames.contains(clsName)) {
            FromStringDeserializer.Std d10 = FromStringDeserializer.findDeserializer(rawType);
            if (d10 != null) {
                return d10;
            }
            if (rawType == UUID.class) {
                return new UUIDDeserializer();
            }
            if (rawType == StackTraceElement.class) {
                return new StackTraceElementDeserializer();
            }
            if (rawType == AtomicBoolean.class) {
                return new AtomicBooleanDeserializer();
            }
            if (rawType == ByteBuffer.class) {
                return new ByteBufferDeserializer();
            }
        }
        return null;
    }

    static {
        Class[] types;
        _classNames = new HashSet();
        for (Class clazz : types = new Class[]{UUID.class, AtomicBoolean.class, StackTraceElement.class, ByteBuffer.class}) {
            _classNames.add(clazz.getName());
        }
        for (Class clazz : FromStringDeserializer.types()) {
            _classNames.add(clazz.getName());
        }
    }
}

