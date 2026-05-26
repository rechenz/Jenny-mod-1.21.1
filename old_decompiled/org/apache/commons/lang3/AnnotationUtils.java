/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnnotationUtils {
    private static final ToStringStyle TO_STRING_STYLE = new ToStringStyle(){
        private static final long serialVersionUID = 1L;
        {
            this.setDefaultFullDetail(true);
            this.setArrayContentDetail(true);
            this.setUseClassName(true);
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
            this.setContentStart("(");
            this.setContentEnd(")");
            this.setFieldSeparator(", ");
            this.setArrayStart("[");
            this.setArrayEnd("]");
        }

        @Override
        protected String getShortClassName(Class<?> cls) {
            for (Class<?> iface : ClassUtils.getAllInterfaces(cls)) {
                if (!Annotation.class.isAssignableFrom(iface)) continue;
                return "@" + iface.getName();
            }
            return "";
        }

        @Override
        protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (value instanceof Annotation) {
                value = AnnotationUtils.toString((Annotation)value);
            }
            super.appendDetail(buffer, fieldName, value);
        }
    };

    public static boolean equals(Annotation a12, Annotation a22) {
        if (a12 == a22) {
            return true;
        }
        if (a12 == null || a22 == null) {
            return false;
        }
        Class<? extends Annotation> type1 = a12.annotationType();
        Class<? extends Annotation> type2 = a22.annotationType();
        Validate.notNull(type1, "Annotation %s with null annotationType()", a12);
        Validate.notNull(type2, "Annotation %s with null annotationType()", a22);
        if (!type1.equals(type2)) {
            return false;
        }
        try {
            for (Method m2 : type1.getDeclaredMethods()) {
                if (m2.getParameterTypes().length != 0 || !AnnotationUtils.isValidAnnotationMemberType(m2.getReturnType())) continue;
                Object v1 = m2.invoke((Object)a12, new Object[0]);
                Object v2 = m2.invoke((Object)a22, new Object[0]);
                if (AnnotationUtils.memberEquals(m2.getReturnType(), v1, v2)) continue;
                return false;
            }
        }
        catch (IllegalAccessException | InvocationTargetException ex2) {
            return false;
        }
        return true;
    }

    public static int hashCode(Annotation a10) {
        int result = 0;
        Class<? extends Annotation> type = a10.annotationType();
        for (Method m2 : type.getDeclaredMethods()) {
            try {
                Object value = m2.invoke((Object)a10, new Object[0]);
                if (value == null) {
                    throw new IllegalStateException(String.format("Annotation method %s returned null", m2));
                }
                result += AnnotationUtils.hashMember(m2.getName(), value);
            }
            catch (RuntimeException ex2) {
                throw ex2;
            }
            catch (Exception ex3) {
                throw new RuntimeException(ex3);
            }
        }
        return result;
    }

    public static String toString(Annotation a10) {
        ToStringBuilder builder = new ToStringBuilder(a10, TO_STRING_STYLE);
        for (Method m2 : a10.annotationType().getDeclaredMethods()) {
            if (m2.getParameterTypes().length > 0) continue;
            try {
                builder.append(m2.getName(), m2.invoke((Object)a10, new Object[0]));
            }
            catch (RuntimeException ex2) {
                throw ex2;
            }
            catch (Exception ex3) {
                throw new RuntimeException(ex3);
            }
        }
        return builder.build();
    }

    public static boolean isValidAnnotationMemberType(Class<?> type) {
        if (type == null) {
            return false;
        }
        if (type.isArray()) {
            type = type.getComponentType();
        }
        return type.isPrimitive() || type.isEnum() || type.isAnnotation() || String.class.equals(type) || Class.class.equals(type);
    }

    private static int hashMember(String name, Object value) {
        int part1 = name.hashCode() * 127;
        if (value.getClass().isArray()) {
            return part1 ^ AnnotationUtils.arrayMemberHash(value.getClass().getComponentType(), value);
        }
        if (value instanceof Annotation) {
            return part1 ^ AnnotationUtils.hashCode((Annotation)value);
        }
        return part1 ^ value.hashCode();
    }

    private static boolean memberEquals(Class<?> type, Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (type.isArray()) {
            return AnnotationUtils.arrayMemberEquals(type.getComponentType(), o1, o2);
        }
        if (type.isAnnotation()) {
            return AnnotationUtils.equals((Annotation)o1, (Annotation)o2);
        }
        return o1.equals(o2);
    }

    private static boolean arrayMemberEquals(Class<?> componentType, Object o1, Object o2) {
        if (componentType.isAnnotation()) {
            return AnnotationUtils.annotationArrayMemberEquals((Annotation[])o1, (Annotation[])o2);
        }
        if (componentType.equals(Byte.TYPE)) {
            return Arrays.equals((byte[])o1, (byte[])o2);
        }
        if (componentType.equals(Short.TYPE)) {
            return Arrays.equals((short[])o1, (short[])o2);
        }
        if (componentType.equals(Integer.TYPE)) {
            return Arrays.equals((int[])o1, (int[])o2);
        }
        if (componentType.equals(Character.TYPE)) {
            return Arrays.equals((char[])o1, (char[])o2);
        }
        if (componentType.equals(Long.TYPE)) {
            return Arrays.equals((long[])o1, (long[])o2);
        }
        if (componentType.equals(Float.TYPE)) {
            return Arrays.equals((float[])o1, (float[])o2);
        }
        if (componentType.equals(Double.TYPE)) {
            return Arrays.equals((double[])o1, (double[])o2);
        }
        if (componentType.equals(Boolean.TYPE)) {
            return Arrays.equals((boolean[])o1, (boolean[])o2);
        }
        return Arrays.equals((Object[])o1, (Object[])o2);
    }

    private static boolean annotationArrayMemberEquals(Annotation[] a12, Annotation[] a22) {
        if (a12.length != a22.length) {
            return false;
        }
        for (int i2 = 0; i2 < a12.length; ++i2) {
            if (AnnotationUtils.equals(a12[i2], a22[i2])) continue;
            return false;
        }
        return true;
    }

    private static int arrayMemberHash(Class<?> componentType, Object o2) {
        if (componentType.equals(Byte.TYPE)) {
            return Arrays.hashCode((byte[])o2);
        }
        if (componentType.equals(Short.TYPE)) {
            return Arrays.hashCode((short[])o2);
        }
        if (componentType.equals(Integer.TYPE)) {
            return Arrays.hashCode((int[])o2);
        }
        if (componentType.equals(Character.TYPE)) {
            return Arrays.hashCode((char[])o2);
        }
        if (componentType.equals(Long.TYPE)) {
            return Arrays.hashCode((long[])o2);
        }
        if (componentType.equals(Float.TYPE)) {
            return Arrays.hashCode((float[])o2);
        }
        if (componentType.equals(Double.TYPE)) {
            return Arrays.hashCode((double[])o2);
        }
        if (componentType.equals(Boolean.TYPE)) {
            return Arrays.hashCode((boolean[])o2);
        }
        return Arrays.hashCode((Object[])o2);
    }
}

