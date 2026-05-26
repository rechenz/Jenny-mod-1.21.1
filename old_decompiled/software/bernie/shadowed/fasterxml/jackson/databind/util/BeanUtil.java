/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class BeanUtil {
    public static String okNameForGetter(AnnotatedMethod am2, boolean stdNaming) {
        String name = am2.getName();
        String str = BeanUtil.okNameForIsGetter(am2, name, stdNaming);
        if (str == null) {
            str = BeanUtil.okNameForRegularGetter(am2, name, stdNaming);
        }
        return str;
    }

    public static String okNameForRegularGetter(AnnotatedMethod am2, String name, boolean stdNaming) {
        if (name.startsWith("get")) {
            if ("getCallbacks".equals(name) ? BeanUtil.isCglibGetCallbacks(am2) : "getMetaClass".equals(name) && BeanUtil.isGroovyMetaClassGetter(am2)) {
                return null;
            }
            return stdNaming ? BeanUtil.stdManglePropertyName(name, 3) : BeanUtil.legacyManglePropertyName(name, 3);
        }
        return null;
    }

    public static String okNameForIsGetter(AnnotatedMethod am2, String name, boolean stdNaming) {
        Class<?> rt;
        if (name.startsWith("is") && ((rt = am2.getRawType()) == Boolean.class || rt == Boolean.TYPE)) {
            return stdNaming ? BeanUtil.stdManglePropertyName(name, 2) : BeanUtil.legacyManglePropertyName(name, 2);
        }
        return null;
    }

    @Deprecated
    public static String okNameForSetter(AnnotatedMethod am2, boolean stdNaming) {
        String name = BeanUtil.okNameForMutator(am2, "set", stdNaming);
        if (!(name == null || "metaClass".equals(name) && BeanUtil.isGroovyMetaClassSetter(am2))) {
            return name;
        }
        return null;
    }

    public static String okNameForMutator(AnnotatedMethod am2, String prefix, boolean stdNaming) {
        String name = am2.getName();
        if (name.startsWith(prefix)) {
            return stdNaming ? BeanUtil.stdManglePropertyName(name, prefix.length()) : BeanUtil.legacyManglePropertyName(name, prefix.length());
        }
        return null;
    }

    public static Object getDefaultValue(JavaType type) {
        Class<?> cls = type.getRawClass();
        Class<?> prim = ClassUtil.primitiveType(cls);
        if (prim != null) {
            return ClassUtil.defaultValue(prim);
        }
        if (type.isContainerType() || type.isReferenceType()) {
            return JsonInclude.Include.NON_EMPTY;
        }
        if (cls == String.class) {
            return "";
        }
        if (type.isTypeOrSubTypeOf(Date.class)) {
            return new Date(0L);
        }
        if (type.isTypeOrSubTypeOf(Calendar.class)) {
            GregorianCalendar c10 = new GregorianCalendar();
            c10.setTimeInMillis(0L);
            return c10;
        }
        return null;
    }

    protected static boolean isCglibGetCallbacks(AnnotatedMethod am2) {
        Class<?> compType;
        String pkgName;
        Class<?> rt = am2.getRawType();
        if (rt.isArray() && (pkgName = ClassUtil.getPackageName(compType = rt.getComponentType())) != null && pkgName.contains(".cglib")) {
            return pkgName.startsWith("net.sf.cglib") || pkgName.startsWith("org.hibernate.repackage.cglib") || pkgName.startsWith("org.springframework.cglib");
        }
        return false;
    }

    protected static boolean isGroovyMetaClassSetter(AnnotatedMethod am2) {
        Class<?> argType = am2.getRawParameterType(0);
        String pkgName = ClassUtil.getPackageName(argType);
        return pkgName != null && pkgName.startsWith("groovy.lang");
    }

    protected static boolean isGroovyMetaClassGetter(AnnotatedMethod am2) {
        String pkgName = ClassUtil.getPackageName(am2.getRawType());
        return pkgName != null && pkgName.startsWith("groovy.lang");
    }

    protected static String legacyManglePropertyName(String basename, int offset) {
        char d10;
        int end = basename.length();
        if (end == offset) {
            return null;
        }
        char c10 = basename.charAt(offset);
        if (c10 == (d10 = Character.toLowerCase(c10))) {
            return basename.substring(offset);
        }
        StringBuilder sb = new StringBuilder(end - offset);
        sb.append(d10);
        for (int i2 = offset + 1; i2 < end; ++i2) {
            c10 = basename.charAt(i2);
            if (c10 == (d10 = Character.toLowerCase(c10))) {
                sb.append(basename, i2, end);
                break;
            }
            sb.append(d10);
        }
        return sb.toString();
    }

    protected static String stdManglePropertyName(String basename, int offset) {
        char c12;
        int end = basename.length();
        if (end == offset) {
            return null;
        }
        char c02 = basename.charAt(offset);
        if (c02 == (c12 = Character.toLowerCase(c02))) {
            return basename.substring(offset);
        }
        if (offset + 1 < end && Character.isUpperCase(basename.charAt(offset + 1))) {
            return basename.substring(offset);
        }
        StringBuilder sb = new StringBuilder(end - offset);
        sb.append(c12);
        sb.append(basename, offset + 1, end);
        return sb.toString();
    }
}

