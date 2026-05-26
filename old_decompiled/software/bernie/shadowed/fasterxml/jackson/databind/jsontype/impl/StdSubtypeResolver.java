/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsontype.impl;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClass;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedClassResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.NamedType;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.SubtypeResolver;

public class StdSubtypeResolver
extends SubtypeResolver
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected LinkedHashSet<NamedType> _registeredSubtypes;

    @Override
    public void registerSubtypes(NamedType ... types) {
        if (this._registeredSubtypes == null) {
            this._registeredSubtypes = new LinkedHashSet();
        }
        for (NamedType type : types) {
            this._registeredSubtypes.add(type);
        }
    }

    @Override
    public void registerSubtypes(Class<?> ... classes) {
        NamedType[] types = new NamedType[classes.length];
        int len = classes.length;
        for (int i2 = 0; i2 < len; ++i2) {
            types[i2] = new NamedType(classes[i2]);
        }
        this.registerSubtypes(types);
    }

    @Override
    public void registerSubtypes(Collection<Class<?>> subtypes) {
        int len = subtypes.size();
        NamedType[] types = new NamedType[len];
        int i2 = 0;
        for (Class<?> subtype : subtypes) {
            types[i2++] = new NamedType(subtype);
        }
        this.registerSubtypes(types);
    }

    @Override
    public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> config, AnnotatedMember property, JavaType baseType) {
        List<NamedType> st;
        AnnotationIntrospector ai2 = config.getAnnotationIntrospector();
        Class<?> rawBase = baseType == null ? property.getRawType() : baseType.getRawClass();
        HashMap<NamedType, NamedType> collected = new HashMap<NamedType, NamedType>();
        if (this._registeredSubtypes != null) {
            for (NamedType subtype : this._registeredSubtypes) {
                if (!rawBase.isAssignableFrom(subtype.getType())) continue;
                AnnotatedClass curr = AnnotatedClassResolver.resolveWithoutSuperTypes(config, subtype.getType());
                this._collectAndResolve(curr, subtype, config, ai2, collected);
            }
        }
        if (property != null && (st = ai2.findSubtypes(property)) != null) {
            for (NamedType nt : st) {
                AnnotatedClass ac2 = AnnotatedClassResolver.resolveWithoutSuperTypes(config, nt.getType());
                this._collectAndResolve(ac2, nt, config, ai2, collected);
            }
        }
        NamedType rootType = new NamedType(rawBase, null);
        AnnotatedClass ac3 = AnnotatedClassResolver.resolveWithoutSuperTypes(config, rawBase);
        this._collectAndResolve(ac3, rootType, config, ai2, collected);
        return new ArrayList<NamedType>(collected.values());
    }

    @Override
    public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> config, AnnotatedClass type) {
        AnnotationIntrospector ai2 = config.getAnnotationIntrospector();
        HashMap<NamedType, NamedType> subtypes = new HashMap<NamedType, NamedType>();
        if (this._registeredSubtypes != null) {
            Class<?> rawBase = type.getRawType();
            for (NamedType subtype : this._registeredSubtypes) {
                if (!rawBase.isAssignableFrom(subtype.getType())) continue;
                AnnotatedClass curr = AnnotatedClassResolver.resolveWithoutSuperTypes(config, subtype.getType());
                this._collectAndResolve(curr, subtype, config, ai2, subtypes);
            }
        }
        NamedType rootType = new NamedType(type.getRawType(), null);
        this._collectAndResolve(type, rootType, config, ai2, subtypes);
        return new ArrayList<NamedType>(subtypes.values());
    }

    @Override
    public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> config, AnnotatedMember property, JavaType baseType) {
        List<NamedType> st;
        AnnotationIntrospector ai2 = config.getAnnotationIntrospector();
        Class<?> rawBase = baseType.getRawClass();
        HashSet typesHandled = new HashSet();
        LinkedHashMap<String, NamedType> byName = new LinkedHashMap<String, NamedType>();
        NamedType rootType = new NamedType(rawBase, null);
        AnnotatedClass ac2 = AnnotatedClassResolver.resolveWithoutSuperTypes(config, rawBase);
        this._collectAndResolveByTypeId(ac2, rootType, config, typesHandled, byName);
        if (property != null && (st = ai2.findSubtypes(property)) != null) {
            for (NamedType nt : st) {
                ac2 = AnnotatedClassResolver.resolveWithoutSuperTypes(config, nt.getType());
                this._collectAndResolveByTypeId(ac2, nt, config, typesHandled, byName);
            }
        }
        if (this._registeredSubtypes != null) {
            for (NamedType subtype : this._registeredSubtypes) {
                if (!rawBase.isAssignableFrom(subtype.getType())) continue;
                AnnotatedClass curr = AnnotatedClassResolver.resolveWithoutSuperTypes(config, subtype.getType());
                this._collectAndResolveByTypeId(curr, subtype, config, typesHandled, byName);
            }
        }
        return this._combineNamedAndUnnamed(rawBase, typesHandled, byName);
    }

    @Override
    public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> config, AnnotatedClass baseType) {
        Class<?> rawBase = baseType.getRawType();
        HashSet typesHandled = new HashSet();
        LinkedHashMap<String, NamedType> byName = new LinkedHashMap<String, NamedType>();
        NamedType rootType = new NamedType(rawBase, null);
        this._collectAndResolveByTypeId(baseType, rootType, config, typesHandled, byName);
        if (this._registeredSubtypes != null) {
            for (NamedType subtype : this._registeredSubtypes) {
                if (!rawBase.isAssignableFrom(subtype.getType())) continue;
                AnnotatedClass curr = AnnotatedClassResolver.resolveWithoutSuperTypes(config, subtype.getType());
                this._collectAndResolveByTypeId(curr, subtype, config, typesHandled, byName);
            }
        }
        return this._combineNamedAndUnnamed(rawBase, typesHandled, byName);
    }

    protected void _collectAndResolve(AnnotatedClass annotatedType, NamedType namedType, MapperConfig<?> config, AnnotationIntrospector ai2, HashMap<NamedType, NamedType> collectedSubtypes) {
        String name;
        if (!namedType.hasName() && (name = ai2.findTypeName(annotatedType)) != null) {
            namedType = new NamedType(namedType.getType(), name);
        }
        if (collectedSubtypes.containsKey(namedType)) {
            NamedType prev;
            if (namedType.hasName() && !(prev = collectedSubtypes.get(namedType)).hasName()) {
                collectedSubtypes.put(namedType, namedType);
            }
            return;
        }
        collectedSubtypes.put(namedType, namedType);
        List<NamedType> st = ai2.findSubtypes(annotatedType);
        if (st != null && !st.isEmpty()) {
            for (NamedType subtype : st) {
                AnnotatedClass subtypeClass = AnnotatedClassResolver.resolveWithoutSuperTypes(config, subtype.getType());
                this._collectAndResolve(subtypeClass, subtype, config, ai2, collectedSubtypes);
            }
        }
    }

    protected void _collectAndResolveByTypeId(AnnotatedClass annotatedType, NamedType namedType, MapperConfig<?> config, Set<Class<?>> typesHandled, Map<String, NamedType> byName) {
        List<NamedType> st;
        String name;
        AnnotationIntrospector ai2 = config.getAnnotationIntrospector();
        if (!namedType.hasName() && (name = ai2.findTypeName(annotatedType)) != null) {
            namedType = new NamedType(namedType.getType(), name);
        }
        if (namedType.hasName()) {
            byName.put(namedType.getName(), namedType);
        }
        if (typesHandled.add(namedType.getType()) && (st = ai2.findSubtypes(annotatedType)) != null && !st.isEmpty()) {
            for (NamedType subtype : st) {
                AnnotatedClass subtypeClass = AnnotatedClassResolver.resolveWithoutSuperTypes(config, subtype.getType());
                this._collectAndResolveByTypeId(subtypeClass, subtype, config, typesHandled, byName);
            }
        }
    }

    protected Collection<NamedType> _combineNamedAndUnnamed(Class<?> rawBase, Set<Class<?>> typesHandled, Map<String, NamedType> byName) {
        ArrayList<NamedType> result = new ArrayList<NamedType>(byName.values());
        for (NamedType namedType : byName.values()) {
            typesHandled.remove(namedType.getType());
        }
        for (Class clazz : typesHandled) {
            if (clazz == rawBase && Modifier.isAbstract(clazz.getModifiers())) continue;
            result.add(new NamedType(clazz));
        }
        return result;
    }
}

