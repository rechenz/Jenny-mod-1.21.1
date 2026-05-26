/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethodMap;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationCollector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ClassIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.CollectorBase;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.MemberKey;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class AnnotatedMethodCollector
extends CollectorBase {
    private final ClassIntrospector.MixInResolver _mixInResolver;

    AnnotatedMethodCollector(AnnotationIntrospector intr, ClassIntrospector.MixInResolver mixins) {
        super(intr);
        this._mixInResolver = intr == null ? null : mixins;
    }

    public static AnnotatedMethodMap collectMethods(AnnotationIntrospector intr, TypeResolutionContext tc, ClassIntrospector.MixInResolver mixins, TypeFactory types, JavaType type, List<JavaType> superTypes, Class<?> primaryMixIn) {
        return new AnnotatedMethodCollector(intr, mixins).collect(types, tc, type, superTypes, primaryMixIn);
    }

    AnnotatedMethodMap collect(TypeFactory typeFactory, TypeResolutionContext tc, JavaType mainType, List<JavaType> superTypes, Class<?> primaryMixIn) {
        LinkedHashMap<MemberKey, MethodBuilder> methods = new LinkedHashMap<MemberKey, MethodBuilder>();
        this._addMemberMethods(tc, mainType.getRawClass(), methods, primaryMixIn);
        for (JavaType type : superTypes) {
            Class<?> mixin = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(type.getRawClass());
            this._addMemberMethods(new TypeResolutionContext.Basic(typeFactory, type.getBindings()), type.getRawClass(), methods, mixin);
        }
        if (methods.isEmpty()) {
            return new AnnotatedMethodMap();
        }
        LinkedHashMap<MemberKey, AnnotatedMethod> actual = new LinkedHashMap<MemberKey, AnnotatedMethod>(methods.size());
        for (Map.Entry entry : methods.entrySet()) {
            AnnotatedMethod am2 = ((MethodBuilder)entry.getValue()).build();
            if (am2 == null) continue;
            actual.put((MemberKey)entry.getKey(), am2);
        }
        return new AnnotatedMethodMap(actual);
    }

    private void _addMemberMethods(TypeResolutionContext tc, Class<?> cls, Map<MemberKey, MethodBuilder> methods, Class<?> mixInCls) {
        if (mixInCls != null) {
            this._addMethodMixIns(tc, cls, methods, mixInCls);
        }
        if (cls == null) {
            return;
        }
        for (Method m2 : ClassUtil.getClassMethods(cls)) {
            Method old;
            if (!this._isIncludableMemberMethod(m2)) continue;
            MemberKey key = new MemberKey(m2);
            MethodBuilder b10 = methods.get(key);
            if (b10 == null) {
                AnnotationCollector c10 = this._intr == null ? AnnotationCollector.emptyCollector() : this.collectAnnotations(m2.getDeclaredAnnotations());
                methods.put(key, new MethodBuilder(tc, m2, c10));
                continue;
            }
            if (this._intr != null) {
                b10.annotations = this.collectDefaultAnnotations(b10.annotations, m2.getDeclaredAnnotations());
            }
            if ((old = b10.method) == null) {
                b10.method = m2;
                continue;
            }
            if (!Modifier.isAbstract(old.getModifiers()) || Modifier.isAbstract(m2.getModifiers())) continue;
            b10.method = m2;
        }
    }

    protected void _addMethodMixIns(TypeResolutionContext tc, Class<?> targetClass, Map<MemberKey, MethodBuilder> methods, Class<?> mixInCls) {
        if (this._intr == null) {
            return;
        }
        for (Class<?> mixin : ClassUtil.findRawSuperTypes(mixInCls, targetClass, true)) {
            for (Method m2 : ClassUtil.getDeclaredMethods(mixin)) {
                if (!this._isIncludableMemberMethod(m2)) continue;
                MemberKey key = new MemberKey(m2);
                MethodBuilder b10 = methods.get(key);
                Annotation[] anns = m2.getDeclaredAnnotations();
                if (b10 == null) {
                    methods.put(key, new MethodBuilder(tc, null, this.collectAnnotations(anns)));
                    continue;
                }
                b10.annotations = this.collectDefaultAnnotations(b10.annotations, anns);
            }
        }
    }

    private boolean _isIncludableMemberMethod(Method m2) {
        if (Modifier.isStatic(m2.getModifiers()) || m2.isSynthetic() || m2.isBridge()) {
            return false;
        }
        int pcount = m2.getParameterTypes().length;
        return pcount <= 2;
    }

    private static final class MethodBuilder {
        public final TypeResolutionContext typeContext;
        public Method method;
        public AnnotationCollector annotations;

        public MethodBuilder(TypeResolutionContext tc, Method m2, AnnotationCollector ann) {
            this.typeContext = tc;
            this.method = m2;
            this.annotations = ann;
        }

        public AnnotatedMethod build() {
            if (this.method == null) {
                return null;
            }
            return new AnnotatedMethod(this.typeContext, this.method, this.annotations.asAnnotationMap(), null);
        }
    }
}

