/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClass;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationCollector;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationMap;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.CollectorBase;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.MemberKey;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

final class AnnotatedCreatorCollector
extends CollectorBase {
    private final TypeResolutionContext _typeContext;
    private AnnotatedConstructor _defaultConstructor;

    AnnotatedCreatorCollector(AnnotationIntrospector intr, TypeResolutionContext tc) {
        super(intr);
        this._typeContext = tc;
    }

    public static AnnotatedClass.Creators collectCreators(AnnotationIntrospector intr, TypeResolutionContext tc, JavaType type, Class<?> primaryMixIn) {
        return new AnnotatedCreatorCollector(intr, tc).collect(type, primaryMixIn);
    }

    AnnotatedClass.Creators collect(JavaType type, Class<?> primaryMixIn) {
        List<AnnotatedConstructor> constructors = this._findPotentialConstructors(type, primaryMixIn);
        List<AnnotatedMethod> factories = this._findPotentialFactories(type, primaryMixIn);
        if (this._intr != null) {
            if (this._defaultConstructor != null && this._intr.hasIgnoreMarker(this._defaultConstructor)) {
                this._defaultConstructor = null;
            }
            int i2 = constructors.size();
            while (--i2 >= 0) {
                if (!this._intr.hasIgnoreMarker(constructors.get(i2))) continue;
                constructors.remove(i2);
            }
            i2 = factories.size();
            while (--i2 >= 0) {
                if (!this._intr.hasIgnoreMarker(factories.get(i2))) continue;
                factories.remove(i2);
            }
        }
        return new AnnotatedClass.Creators(this._defaultConstructor, constructors, factories);
    }

    private List<AnnotatedConstructor> _findPotentialConstructors(JavaType type, Class<?> primaryMixIn) {
        int ctorCount;
        List<AnnotatedConstructor> result;
        ClassUtil.Ctor defaultCtor = null;
        ArrayList<ClassUtil.Ctor> ctors = null;
        if (!type.isEnumType()) {
            ClassUtil.Ctor[] declaredCtors;
            for (ClassUtil.Ctor ctor : declaredCtors = ClassUtil.getConstructors(type.getRawClass())) {
                if (!AnnotatedCreatorCollector.isIncludableConstructor(ctor.getConstructor())) continue;
                if (ctor.getParamCount() == 0) {
                    defaultCtor = ctor;
                    continue;
                }
                if (ctors == null) {
                    ctors = new ArrayList<ClassUtil.Ctor>();
                }
                ctors.add(ctor);
            }
        }
        if (ctors == null) {
            result = Collections.emptyList();
            if (defaultCtor == null) {
                return result;
            }
            ctorCount = 0;
        } else {
            ctorCount = ctors.size();
            result = new ArrayList<AnnotatedConstructor>(ctorCount);
            for (int i2 = 0; i2 < ctorCount; ++i2) {
                result.add(null);
            }
        }
        if (primaryMixIn != null) {
            MemberKey[] ctorKeys = null;
            block2: for (ClassUtil.Ctor mixinCtor : ClassUtil.getConstructors(primaryMixIn)) {
                if (mixinCtor.getParamCount() == 0) {
                    if (defaultCtor == null) continue;
                    this._defaultConstructor = this.constructDefaultConstructor(defaultCtor, mixinCtor);
                    defaultCtor = null;
                    continue;
                }
                if (ctors == null) continue;
                if (ctorKeys == null) {
                    ctorKeys = new MemberKey[ctorCount];
                    for (int i3 = 0; i3 < ctorCount; ++i3) {
                        ctorKeys[i3] = new MemberKey(((ClassUtil.Ctor)ctors.get(i3)).getConstructor());
                    }
                }
                MemberKey key = new MemberKey(mixinCtor.getConstructor());
                for (int i4 = 0; i4 < ctorCount; ++i4) {
                    if (!key.equals(ctorKeys[i4])) continue;
                    result.set(i4, this.constructNonDefaultConstructor((ClassUtil.Ctor)ctors.get(i4), mixinCtor));
                    continue block2;
                }
            }
        }
        if (defaultCtor != null) {
            this._defaultConstructor = this.constructDefaultConstructor(defaultCtor, null);
        }
        for (int i5 = 0; i5 < ctorCount; ++i5) {
            AnnotatedConstructor ctor = result.get(i5);
            if (ctor != null) continue;
            result.set(i5, this.constructNonDefaultConstructor((ClassUtil.Ctor)ctors.get(i5), null));
        }
        return result;
    }

    private List<AnnotatedMethod> _findPotentialFactories(JavaType type, Class<?> primaryMixIn) {
        ArrayList<Method> candidates = null;
        for (Method m2 : ClassUtil.getClassMethods(type.getRawClass())) {
            if (!Modifier.isStatic(m2.getModifiers())) continue;
            if (candidates == null) {
                candidates = new ArrayList<Method>();
            }
            candidates.add(m2);
        }
        if (candidates == null) {
            return Collections.emptyList();
        }
        int factoryCount = candidates.size();
        ArrayList<AnnotatedMethod> result = new ArrayList<AnnotatedMethod>(factoryCount);
        for (int i2 = 0; i2 < factoryCount; ++i2) {
            result.add(null);
        }
        if (primaryMixIn != null) {
            MemberKey[] methodKeys = null;
            block2: for (Method mixinFactory : ClassUtil.getDeclaredMethods(primaryMixIn)) {
                if (!Modifier.isStatic(mixinFactory.getModifiers())) continue;
                if (methodKeys == null) {
                    methodKeys = new MemberKey[factoryCount];
                    for (int i3 = 0; i3 < factoryCount; ++i3) {
                        methodKeys[i3] = new MemberKey((Method)candidates.get(i3));
                    }
                }
                MemberKey key = new MemberKey(mixinFactory);
                for (int i4 = 0; i4 < factoryCount; ++i4) {
                    if (!key.equals(methodKeys[i4])) continue;
                    result.set(i4, this.constructFactoryCreator((Method)candidates.get(i4), mixinFactory));
                    continue block2;
                }
            }
        }
        for (int i5 = 0; i5 < factoryCount; ++i5) {
            AnnotatedMethod factory = (AnnotatedMethod)result.get(i5);
            if (factory != null) continue;
            result.set(i5, this.constructFactoryCreator((Method)candidates.get(i5), null));
        }
        return result;
    }

    protected AnnotatedConstructor constructDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor mixin) {
        if (this._intr == null) {
            return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), AnnotatedCreatorCollector._emptyAnnotationMap(), NO_ANNOTATION_MAPS);
        }
        return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), this.collectAnnotations(ctor, mixin), this.collectAnnotations(ctor.getConstructor().getParameterAnnotations(), mixin == null ? (Annotation[][])null : mixin.getConstructor().getParameterAnnotations()));
    }

    protected AnnotatedConstructor constructNonDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor mixin) {
        AnnotationMap[] resolvedAnnotations;
        int paramCount = ctor.getParamCount();
        if (this._intr == null) {
            return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), AnnotatedCreatorCollector._emptyAnnotationMap(), AnnotatedCreatorCollector._emptyAnnotationMaps(paramCount));
        }
        if (paramCount == 0) {
            return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), this.collectAnnotations(ctor, mixin), NO_ANNOTATION_MAPS);
        }
        Annotation[][] paramAnns = ctor.getParameterAnnotations();
        if (paramCount != paramAnns.length) {
            resolvedAnnotations = null;
            Class<?> dc2 = ctor.getDeclaringClass();
            if (dc2.isEnum() && paramCount == paramAnns.length + 2) {
                Annotation[][] old = paramAnns;
                paramAnns = new Annotation[old.length + 2][];
                System.arraycopy(old, 0, paramAnns, 2, old.length);
                resolvedAnnotations = this.collectAnnotations(paramAnns, (Annotation[][])null);
            } else if (dc2.isMemberClass() && paramCount == paramAnns.length + 1) {
                Annotation[][] old = paramAnns;
                paramAnns = new Annotation[old.length + 1][];
                System.arraycopy(old, 0, paramAnns, 1, old.length);
                paramAnns[0] = NO_ANNOTATIONS;
                resolvedAnnotations = this.collectAnnotations(paramAnns, (Annotation[][])null);
            }
            if (resolvedAnnotations == null) {
                throw new IllegalStateException(String.format("Internal error: constructor for %s has mismatch: %d parameters; %d sets of annotations", ctor.getDeclaringClass().getName(), paramCount, paramAnns.length));
            }
        } else {
            resolvedAnnotations = this.collectAnnotations(paramAnns, mixin == null ? (Annotation[][])null : mixin.getParameterAnnotations());
        }
        return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), this.collectAnnotations(ctor, mixin), resolvedAnnotations);
    }

    protected AnnotatedMethod constructFactoryCreator(Method m2, Method mixin) {
        int paramCount = m2.getParameterTypes().length;
        if (this._intr == null) {
            return new AnnotatedMethod(this._typeContext, m2, AnnotatedCreatorCollector._emptyAnnotationMap(), AnnotatedCreatorCollector._emptyAnnotationMaps(paramCount));
        }
        if (paramCount == 0) {
            return new AnnotatedMethod(this._typeContext, m2, this.collectAnnotations(m2, mixin), NO_ANNOTATION_MAPS);
        }
        return new AnnotatedMethod(this._typeContext, m2, this.collectAnnotations(m2, mixin), this.collectAnnotations(m2.getParameterAnnotations(), mixin == null ? (Annotation[][])null : mixin.getParameterAnnotations()));
    }

    private AnnotationMap[] collectAnnotations(Annotation[][] mainAnns, Annotation[][] mixinAnns) {
        int count = mainAnns.length;
        AnnotationMap[] result = new AnnotationMap[count];
        for (int i2 = 0; i2 < count; ++i2) {
            AnnotationCollector c10 = this.collectAnnotations(AnnotationCollector.emptyCollector(), mainAnns[i2]);
            if (mixinAnns != null) {
                c10 = this.collectAnnotations(c10, mixinAnns[i2]);
            }
            result[i2] = c10.asAnnotationMap();
        }
        return result;
    }

    private AnnotationMap collectAnnotations(ClassUtil.Ctor main, ClassUtil.Ctor mixin) {
        AnnotationCollector c10 = this.collectAnnotations(main.getConstructor().getDeclaredAnnotations());
        if (mixin != null) {
            c10 = this.collectAnnotations(c10, mixin.getConstructor().getDeclaredAnnotations());
        }
        return c10.asAnnotationMap();
    }

    private final AnnotationMap collectAnnotations(AnnotatedElement main, AnnotatedElement mixin) {
        AnnotationCollector c10 = this.collectAnnotations(main.getDeclaredAnnotations());
        if (mixin != null) {
            c10 = this.collectAnnotations(c10, mixin.getDeclaredAnnotations());
        }
        return c10.asAnnotationMap();
    }

    private static boolean isIncludableConstructor(Constructor<?> c10) {
        return !c10.isSynthetic();
    }
}

