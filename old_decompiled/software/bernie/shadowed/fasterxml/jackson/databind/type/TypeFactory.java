/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.type;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import software.bernie.shadowed.fasterxml.jackson.core.type.TypeReference;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.ArrayType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.ClassStack;
import software.bernie.shadowed.fasterxml.jackson.databind.type.CollectionLikeType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.CollectionType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.MapLikeType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.MapType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.ReferenceType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.SimpleType;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeBindings;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeModifier;
import software.bernie.shadowed.fasterxml.jackson.databind.type.TypeParser;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ArrayBuilders;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.LRUMap;

public final class TypeFactory
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    protected static final TypeBindings EMPTY_BINDINGS = TypeBindings.emptyBindings();
    private static final Class<?> CLS_STRING = String.class;
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final Class<?> CLS_COMPARABLE = Comparable.class;
    private static final Class<?> CLS_CLASS = Class.class;
    private static final Class<?> CLS_ENUM = Enum.class;
    private static final Class<?> CLS_BOOL = Boolean.TYPE;
    private static final Class<?> CLS_INT = Integer.TYPE;
    private static final Class<?> CLS_LONG = Long.TYPE;
    protected static final SimpleType CORE_TYPE_BOOL = new SimpleType(CLS_BOOL);
    protected static final SimpleType CORE_TYPE_INT = new SimpleType(CLS_INT);
    protected static final SimpleType CORE_TYPE_LONG = new SimpleType(CLS_LONG);
    protected static final SimpleType CORE_TYPE_STRING = new SimpleType(CLS_STRING);
    protected static final SimpleType CORE_TYPE_OBJECT = new SimpleType(CLS_OBJECT);
    protected static final SimpleType CORE_TYPE_COMPARABLE = new SimpleType(CLS_COMPARABLE);
    protected static final SimpleType CORE_TYPE_ENUM = new SimpleType(CLS_ENUM);
    protected static final SimpleType CORE_TYPE_CLASS = new SimpleType(CLS_CLASS);
    protected final LRUMap<Object, JavaType> _typeCache;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final ClassLoader _classLoader;

    private TypeFactory() {
        this(null);
    }

    protected TypeFactory(LRUMap<Object, JavaType> typeCache) {
        if (typeCache == null) {
            typeCache = new LRUMap(16, 200);
        }
        this._typeCache = typeCache;
        this._parser = new TypeParser(this);
        this._modifiers = null;
        this._classLoader = null;
    }

    protected TypeFactory(LRUMap<Object, JavaType> typeCache, TypeParser p2, TypeModifier[] mods, ClassLoader classLoader) {
        if (typeCache == null) {
            typeCache = new LRUMap(16, 200);
        }
        this._typeCache = typeCache;
        this._parser = p2.withFactory(this);
        this._modifiers = mods;
        this._classLoader = classLoader;
    }

    public TypeFactory withModifier(TypeModifier mod) {
        TypeModifier[] mods;
        LRUMap<Object, JavaType> typeCache = this._typeCache;
        if (mod == null) {
            mods = null;
            typeCache = null;
        } else {
            mods = this._modifiers == null ? new TypeModifier[]{mod} : ArrayBuilders.insertInListNoDup(this._modifiers, mod);
        }
        return new TypeFactory(typeCache, this._parser, mods, this._classLoader);
    }

    public TypeFactory withClassLoader(ClassLoader classLoader) {
        return new TypeFactory(this._typeCache, this._parser, this._modifiers, classLoader);
    }

    public TypeFactory withCache(LRUMap<Object, JavaType> cache) {
        return new TypeFactory(cache, this._parser, this._modifiers, this._classLoader);
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public void clearCache() {
        this._typeCache.clear();
    }

    public ClassLoader getClassLoader() {
        return this._classLoader;
    }

    public static JavaType unknownType() {
        return TypeFactory.defaultInstance()._unknownType();
    }

    public static Class<?> rawClass(Type t2) {
        if (t2 instanceof Class) {
            return (Class)t2;
        }
        return TypeFactory.defaultInstance().constructType(t2).getRawClass();
    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        Class<?> prim;
        if (className.indexOf(46) < 0 && (prim = this._findPrimitive(className)) != null) {
            return prim;
        }
        Throwable prob = null;
        ClassLoader loader = this.getClassLoader();
        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }
        if (loader != null) {
            try {
                return this.classForName(className, true, loader);
            }
            catch (Exception e10) {
                prob = ClassUtil.getRootCause(e10);
            }
        }
        try {
            return this.classForName(className);
        }
        catch (Exception e11) {
            if (prob == null) {
                prob = ClassUtil.getRootCause(e11);
            }
            ClassUtil.throwIfRTE(prob);
            throw new ClassNotFoundException(prob.getMessage(), prob);
        }
    }

    protected Class<?> classForName(String name, boolean initialize, ClassLoader loader) throws ClassNotFoundException {
        return Class.forName(name, true, loader);
    }

    protected Class<?> classForName(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }

    protected Class<?> _findPrimitive(String className) {
        if ("int".equals(className)) {
            return Integer.TYPE;
        }
        if ("long".equals(className)) {
            return Long.TYPE;
        }
        if ("float".equals(className)) {
            return Float.TYPE;
        }
        if ("double".equals(className)) {
            return Double.TYPE;
        }
        if ("boolean".equals(className)) {
            return Boolean.TYPE;
        }
        if ("byte".equals(className)) {
            return Byte.TYPE;
        }
        if ("char".equals(className)) {
            return Character.TYPE;
        }
        if ("short".equals(className)) {
            return Short.TYPE;
        }
        if ("void".equals(className)) {
            return Void.TYPE;
        }
        return null;
    }

    /*
     * Unable to fully structure code
     */
    public JavaType constructSpecializedType(JavaType baseType, Class<?> subclass) {
        block9: {
            block11: {
                block10: {
                    block8: {
                        rawBase = baseType.getRawClass();
                        if (rawBase == subclass) {
                            return baseType;
                        }
                        if (rawBase != Object.class) break block8;
                        newType = this._fromClass(null, subclass, TypeBindings.emptyBindings());
                        break block9;
                    }
                    if (!rawBase.isAssignableFrom(subclass)) {
                        throw new IllegalArgumentException(String.format("Class %s not subtype of %s", new Object[]{subclass.getName(), baseType}));
                    }
                    if (!baseType.getBindings().isEmpty()) break block10;
                    newType = this._fromClass(null, subclass, TypeBindings.emptyBindings());
                    break block9;
                }
                if (!baseType.isContainerType()) ** GOTO lbl26
                if (!baseType.isMapLikeType()) break block11;
                if (subclass != HashMap.class && subclass != LinkedHashMap.class && subclass != EnumMap.class && subclass != TreeMap.class) ** GOTO lbl26
                newType = this._fromClass(null, subclass, TypeBindings.create(subclass, baseType.getKeyType(), baseType.getContentType()));
                break block9;
            }
            if (!baseType.isCollectionLikeType()) ** GOTO lbl26
            if (subclass == ArrayList.class || subclass == LinkedList.class || subclass == HashSet.class || subclass == TreeSet.class) {
                newType = this._fromClass(null, subclass, TypeBindings.create(subclass, baseType.getContentType()));
            } else {
                if (rawBase == EnumSet.class) {
                    return baseType;
                }
lbl26:
                // 5 sources

                if ((typeParamCount = subclass.getTypeParameters().length) == 0) {
                    newType = this._fromClass(null, subclass, TypeBindings.emptyBindings());
                } else {
                    tb = this._bindingsForSubtype(baseType, typeParamCount, subclass);
                    newType = baseType.isInterface() != false ? baseType.refine(subclass, tb, null, new JavaType[]{baseType}) : baseType.refine(subclass, tb, baseType, TypeFactory.NO_TYPES);
                    if (newType == null) {
                        newType = this._fromClass(null, subclass, tb);
                    }
                }
            }
        }
        newType = newType.withHandlersFrom(baseType);
        return newType;
    }

    private TypeBindings _bindingsForSubtype(JavaType baseType, int typeParamCount, Class<?> subclass) {
        int baseCount = baseType.containedTypeCount();
        if (baseCount == typeParamCount) {
            if (typeParamCount == 1) {
                return TypeBindings.create(subclass, baseType.containedType(0));
            }
            if (typeParamCount == 2) {
                return TypeBindings.create(subclass, baseType.containedType(0), baseType.containedType(1));
            }
            ArrayList<JavaType> types = new ArrayList<JavaType>(baseCount);
            for (int i2 = 0; i2 < baseCount; ++i2) {
                types.add(baseType.containedType(i2));
            }
            return TypeBindings.create(subclass, types);
        }
        return TypeBindings.emptyBindings();
    }

    public JavaType constructGeneralizedType(JavaType baseType, Class<?> superClass) {
        Class<?> rawBase = baseType.getRawClass();
        if (rawBase == superClass) {
            return baseType;
        }
        JavaType superType = baseType.findSuperType(superClass);
        if (superType == null) {
            if (!superClass.isAssignableFrom(rawBase)) {
                throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", superClass.getName(), baseType));
            }
            throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", superClass.getName(), baseType));
        }
        return superType;
    }

    public JavaType constructFromCanonical(String canonical) throws IllegalArgumentException {
        return this._parser.parse(canonical);
    }

    public JavaType[] findTypeParameters(JavaType type, Class<?> expType) {
        JavaType match = type.findSuperType(expType);
        if (match == null) {
            return NO_TYPES;
        }
        return match.getBindings().typeParameterArray();
    }

    @Deprecated
    public JavaType[] findTypeParameters(Class<?> clz, Class<?> expType, TypeBindings bindings) {
        return this.findTypeParameters(this.constructType(clz, bindings), expType);
    }

    @Deprecated
    public JavaType[] findTypeParameters(Class<?> clz, Class<?> expType) {
        return this.findTypeParameters(this.constructType(clz), expType);
    }

    public JavaType moreSpecificType(JavaType type1, JavaType type2) {
        Class<?> raw2;
        if (type1 == null) {
            return type2;
        }
        if (type2 == null) {
            return type1;
        }
        Class<?> raw1 = type1.getRawClass();
        if (raw1 == (raw2 = type2.getRawClass())) {
            return type1;
        }
        if (raw1.isAssignableFrom(raw2)) {
            return type2;
        }
        return type1;
    }

    public JavaType constructType(Type type) {
        return this._fromAny(null, type, EMPTY_BINDINGS);
    }

    public JavaType constructType(Type type, TypeBindings bindings) {
        return this._fromAny(null, type, bindings);
    }

    public JavaType constructType(TypeReference<?> typeRef) {
        return this._fromAny(null, typeRef.getType(), EMPTY_BINDINGS);
    }

    @Deprecated
    public JavaType constructType(Type type, Class<?> contextClass) {
        JavaType contextType = contextClass == null ? null : this.constructType(contextClass);
        return this.constructType(type, contextType);
    }

    @Deprecated
    public JavaType constructType(Type type, JavaType contextType) {
        TypeBindings bindings;
        if (contextType == null) {
            bindings = TypeBindings.emptyBindings();
        } else {
            bindings = contextType.getBindings();
            if (type.getClass() != Class.class) {
                while (bindings.isEmpty() && (contextType = contextType.getSuperClass()) != null) {
                    bindings = contextType.getBindings();
                }
            }
        }
        return this._fromAny(null, type, bindings);
    }

    public ArrayType constructArrayType(Class<?> elementType) {
        return ArrayType.construct(this._fromAny(null, elementType, null), null);
    }

    public ArrayType constructArrayType(JavaType elementType) {
        return ArrayType.construct(elementType, null);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return this.constructCollectionType(collectionClass, this._fromClass(null, elementClass, EMPTY_BINDINGS));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> collectionClass, JavaType elementType) {
        JavaType t2;
        JavaType realET;
        TypeBindings bindings = TypeBindings.createIfNeeded(collectionClass, elementType);
        CollectionType result = (CollectionType)this._fromClass(null, collectionClass, bindings);
        if (bindings.isEmpty() && elementType != null && !(realET = (t2 = result.findSuperType(Collection.class)).getContentType()).equals(elementType)) {
            throw new IllegalArgumentException(String.format("Non-generic Collection class %s did not resolve to something with element type %s but %s ", ClassUtil.nameOf(collectionClass), elementType, realET));
        }
        return result;
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> collectionClass, Class<?> elementClass) {
        return this.constructCollectionLikeType(collectionClass, this._fromClass(null, elementClass, EMPTY_BINDINGS));
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> collectionClass, JavaType elementType) {
        JavaType type = this._fromClass(null, collectionClass, TypeBindings.createIfNeeded(collectionClass, elementType));
        if (type instanceof CollectionLikeType) {
            return (CollectionLikeType)type;
        }
        return CollectionLikeType.upgradeFrom(type, elementType);
    }

    public MapType constructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        JavaType kt;
        JavaType vt;
        if (mapClass == Properties.class) {
            kt = vt = CORE_TYPE_STRING;
        } else {
            kt = this._fromClass(null, keyClass, EMPTY_BINDINGS);
            vt = this._fromClass(null, valueClass, EMPTY_BINDINGS);
        }
        return this.constructMapType(mapClass, kt, vt);
    }

    public MapType constructMapType(Class<? extends Map> mapClass, JavaType keyType, JavaType valueType) {
        TypeBindings bindings = TypeBindings.createIfNeeded(mapClass, new JavaType[]{keyType, valueType});
        MapType result = (MapType)this._fromClass(null, mapClass, bindings);
        if (bindings.isEmpty()) {
            JavaType t2 = result.findSuperType(Map.class);
            JavaType realKT = t2.getKeyType();
            if (!realKT.equals(keyType)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with key type %s but %s ", ClassUtil.nameOf(mapClass), keyType, realKT));
            }
            JavaType realVT = t2.getContentType();
            if (!realVT.equals(valueType)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with value type %s but %s ", ClassUtil.nameOf(mapClass), valueType, realVT));
            }
        }
        return result;
    }

    public MapLikeType constructMapLikeType(Class<?> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return this.constructMapLikeType(mapClass, this._fromClass(null, keyClass, EMPTY_BINDINGS), this._fromClass(null, valueClass, EMPTY_BINDINGS));
    }

    public MapLikeType constructMapLikeType(Class<?> mapClass, JavaType keyType, JavaType valueType) {
        JavaType type = this._fromClass(null, mapClass, TypeBindings.createIfNeeded(mapClass, new JavaType[]{keyType, valueType}));
        if (type instanceof MapLikeType) {
            return (MapLikeType)type;
        }
        return MapLikeType.upgradeFrom(type, keyType, valueType);
    }

    public JavaType constructSimpleType(Class<?> rawType, JavaType[] parameterTypes) {
        return this._fromClass(null, rawType, TypeBindings.create(rawType, parameterTypes));
    }

    @Deprecated
    public JavaType constructSimpleType(Class<?> rawType, Class<?> parameterTarget, JavaType[] parameterTypes) {
        return this.constructSimpleType(rawType, parameterTypes);
    }

    public JavaType constructReferenceType(Class<?> rawType, JavaType referredType) {
        return ReferenceType.construct(rawType, null, null, null, referredType);
    }

    @Deprecated
    public JavaType uncheckedSimpleType(Class<?> cls) {
        return this._constructSimple(cls, EMPTY_BINDINGS, null, null);
    }

    public JavaType constructParametricType(Class<?> parametrized, Class<?> ... parameterClasses) {
        int len = parameterClasses.length;
        JavaType[] pt = new JavaType[len];
        for (int i2 = 0; i2 < len; ++i2) {
            pt[i2] = this._fromClass(null, parameterClasses[i2], null);
        }
        return this.constructParametricType(parametrized, pt);
    }

    public JavaType constructParametricType(Class<?> rawType, JavaType ... parameterTypes) {
        return this._fromClass(null, rawType, TypeBindings.create(rawType, parameterTypes));
    }

    @Deprecated
    public JavaType constructParametrizedType(Class<?> parametrized, Class<?> parametersFor, JavaType ... parameterTypes) {
        return this.constructParametricType(parametrized, parameterTypes);
    }

    @Deprecated
    public JavaType constructParametrizedType(Class<?> parametrized, Class<?> parametersFor, Class<?> ... parameterClasses) {
        return this.constructParametricType(parametrized, parameterClasses);
    }

    public CollectionType constructRawCollectionType(Class<? extends Collection> collectionClass) {
        return this.constructCollectionType(collectionClass, TypeFactory.unknownType());
    }

    public CollectionLikeType constructRawCollectionLikeType(Class<?> collectionClass) {
        return this.constructCollectionLikeType(collectionClass, TypeFactory.unknownType());
    }

    public MapType constructRawMapType(Class<? extends Map> mapClass) {
        return this.constructMapType(mapClass, TypeFactory.unknownType(), TypeFactory.unknownType());
    }

    public MapLikeType constructRawMapLikeType(Class<?> mapClass) {
        return this.constructMapLikeType(mapClass, TypeFactory.unknownType(), TypeFactory.unknownType());
    }

    private JavaType _mapType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType kt;
        JavaType vt;
        if (rawClass == Properties.class) {
            kt = vt = CORE_TYPE_STRING;
        } else {
            List<JavaType> typeParams = bindings.getTypeParameters();
            switch (typeParams.size()) {
                case 0: {
                    kt = vt = this._unknownType();
                    break;
                }
                case 2: {
                    kt = typeParams.get(0);
                    vt = typeParams.get(1);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Strange Map type " + rawClass.getName() + ": cannot determine type parameters");
                }
            }
        }
        return MapType.construct(rawClass, bindings, superClass, superInterfaces, kt, vt);
    }

    private JavaType _collectionType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType ct2;
        List<JavaType> typeParams = bindings.getTypeParameters();
        if (typeParams.isEmpty()) {
            ct2 = this._unknownType();
        } else if (typeParams.size() == 1) {
            ct2 = typeParams.get(0);
        } else {
            throw new IllegalArgumentException("Strange Collection type " + rawClass.getName() + ": cannot determine type parameters");
        }
        return CollectionType.construct(rawClass, bindings, superClass, superInterfaces, ct2);
    }

    private JavaType _referenceType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType ct2;
        List<JavaType> typeParams = bindings.getTypeParameters();
        if (typeParams.isEmpty()) {
            ct2 = this._unknownType();
        } else if (typeParams.size() == 1) {
            ct2 = typeParams.get(0);
        } else {
            throw new IllegalArgumentException("Strange Reference type " + rawClass.getName() + ": cannot determine type parameters");
        }
        return ReferenceType.construct(rawClass, bindings, superClass, superInterfaces, ct2);
    }

    protected JavaType _constructSimple(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType result;
        if (bindings.isEmpty() && (result = this._findWellKnownSimple(raw)) != null) {
            return result;
        }
        return this._newSimpleType(raw, bindings, superClass, superInterfaces);
    }

    protected JavaType _newSimpleType(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return new SimpleType(raw, bindings, superClass, superInterfaces);
    }

    protected JavaType _unknownType() {
        return CORE_TYPE_OBJECT;
    }

    protected JavaType _findWellKnownSimple(Class<?> clz) {
        if (clz.isPrimitive()) {
            if (clz == CLS_BOOL) {
                return CORE_TYPE_BOOL;
            }
            if (clz == CLS_INT) {
                return CORE_TYPE_INT;
            }
            if (clz == CLS_LONG) {
                return CORE_TYPE_LONG;
            }
        } else {
            if (clz == CLS_STRING) {
                return CORE_TYPE_STRING;
            }
            if (clz == CLS_OBJECT) {
                return CORE_TYPE_OBJECT;
            }
        }
        return null;
    }

    protected JavaType _fromAny(ClassStack context, Type type, TypeBindings bindings) {
        JavaType resultType;
        if (type instanceof Class) {
            resultType = this._fromClass(context, (Class)type, EMPTY_BINDINGS);
        } else if (type instanceof ParameterizedType) {
            resultType = this._fromParamType(context, (ParameterizedType)type, bindings);
        } else {
            if (type instanceof JavaType) {
                return (JavaType)type;
            }
            if (type instanceof GenericArrayType) {
                resultType = this._fromArrayType(context, (GenericArrayType)type, bindings);
            } else if (type instanceof TypeVariable) {
                resultType = this._fromVariable(context, (TypeVariable)type, bindings);
            } else if (type instanceof WildcardType) {
                resultType = this._fromWildcard(context, (WildcardType)type, bindings);
            } else {
                throw new IllegalArgumentException("Unrecognized Type: " + (type == null ? "[null]" : type.toString()));
            }
        }
        if (this._modifiers != null) {
            TypeBindings b10 = resultType.getBindings();
            if (b10 == null) {
                b10 = EMPTY_BINDINGS;
            }
            for (TypeModifier mod : this._modifiers) {
                JavaType t2 = mod.modifyType(resultType, type, b10, this);
                if (t2 == null) {
                    throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", mod, mod.getClass().getName(), resultType));
                }
                resultType = t2;
            }
        }
        return resultType;
    }

    protected JavaType _fromClass(ClassStack context, Class<?> rawType, TypeBindings bindings) {
        JavaType result = this._findWellKnownSimple(rawType);
        if (result != null) {
            return result;
        }
        Object key = bindings == null || bindings.isEmpty() ? rawType : bindings.asKey(rawType);
        result = this._typeCache.get(key);
        if (result != null) {
            return result;
        }
        if (context == null) {
            context = new ClassStack(rawType);
        } else {
            ClassStack prev = context.find(rawType);
            if (prev != null) {
                ResolvedRecursiveType selfRef = new ResolvedRecursiveType(rawType, EMPTY_BINDINGS);
                prev.addSelfReference(selfRef);
                return selfRef;
            }
            context = context.child(rawType);
        }
        if (rawType.isArray()) {
            result = ArrayType.construct(this._fromAny(context, rawType.getComponentType(), bindings), bindings);
        } else {
            JavaType[] superInterfaces;
            JavaType superClass;
            if (rawType.isInterface()) {
                superClass = null;
                superInterfaces = this._resolveSuperInterfaces(context, rawType, bindings);
            } else {
                superClass = this._resolveSuperClass(context, rawType, bindings);
                superInterfaces = this._resolveSuperInterfaces(context, rawType, bindings);
            }
            if (rawType == Properties.class) {
                result = MapType.construct(rawType, bindings, superClass, superInterfaces, CORE_TYPE_STRING, CORE_TYPE_STRING);
            } else if (superClass != null) {
                result = superClass.refine(rawType, bindings, superClass, superInterfaces);
            }
            if (result == null && (result = this._fromWellKnownClass(context, rawType, bindings, superClass, superInterfaces)) == null && (result = this._fromWellKnownInterface(context, rawType, bindings, superClass, superInterfaces)) == null) {
                result = this._newSimpleType(rawType, bindings, superClass, superInterfaces);
            }
        }
        context.resolveSelfReferences(result);
        if (!result.hasHandlers()) {
            this._typeCache.putIfAbsent(key, result);
        }
        return result;
    }

    protected JavaType _resolveSuperClass(ClassStack context, Class<?> rawType, TypeBindings parentBindings) {
        Type parent = ClassUtil.getGenericSuperclass(rawType);
        if (parent == null) {
            return null;
        }
        return this._fromAny(context, parent, parentBindings);
    }

    protected JavaType[] _resolveSuperInterfaces(ClassStack context, Class<?> rawType, TypeBindings parentBindings) {
        Type[] types = ClassUtil.getGenericInterfaces(rawType);
        if (types == null || types.length == 0) {
            return NO_TYPES;
        }
        int len = types.length;
        JavaType[] resolved = new JavaType[len];
        for (int i2 = 0; i2 < len; ++i2) {
            Type type = types[i2];
            resolved[i2] = this._fromAny(context, type, parentBindings);
        }
        return resolved;
    }

    protected JavaType _fromWellKnownClass(ClassStack context, Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        if (bindings == null) {
            bindings = TypeBindings.emptyBindings();
        }
        if (rawType == Map.class) {
            return this._mapType(rawType, bindings, superClass, superInterfaces);
        }
        if (rawType == Collection.class) {
            return this._collectionType(rawType, bindings, superClass, superInterfaces);
        }
        if (rawType == AtomicReference.class) {
            return this._referenceType(rawType, bindings, superClass, superInterfaces);
        }
        return null;
    }

    protected JavaType _fromWellKnownInterface(ClassStack context, Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        int intCount = superInterfaces.length;
        for (int i2 = 0; i2 < intCount; ++i2) {
            JavaType result = superInterfaces[i2].refine(rawType, bindings, superClass, superInterfaces);
            if (result == null) continue;
            return result;
        }
        return null;
    }

    protected JavaType _fromParamType(ClassStack context, ParameterizedType ptype, TypeBindings parentBindings) {
        TypeBindings newBindings;
        int paramCount;
        Class rawType = (Class)ptype.getRawType();
        if (rawType == CLS_ENUM) {
            return CORE_TYPE_ENUM;
        }
        if (rawType == CLS_COMPARABLE) {
            return CORE_TYPE_COMPARABLE;
        }
        if (rawType == CLS_CLASS) {
            return CORE_TYPE_CLASS;
        }
        Type[] args = ptype.getActualTypeArguments();
        int n2 = paramCount = args == null ? 0 : args.length;
        if (paramCount == 0) {
            newBindings = EMPTY_BINDINGS;
        } else {
            JavaType[] pt = new JavaType[paramCount];
            for (int i2 = 0; i2 < paramCount; ++i2) {
                pt[i2] = this._fromAny(context, args[i2], parentBindings);
            }
            newBindings = TypeBindings.create(rawType, pt);
        }
        return this._fromClass(context, rawType, newBindings);
    }

    protected JavaType _fromArrayType(ClassStack context, GenericArrayType type, TypeBindings bindings) {
        JavaType elementType = this._fromAny(context, type.getGenericComponentType(), bindings);
        return ArrayType.construct(elementType, bindings);
    }

    protected JavaType _fromVariable(ClassStack context, TypeVariable<?> var, TypeBindings bindings) {
        String name = var.getName();
        JavaType type = bindings.findBoundType(name);
        if (type != null) {
            return type;
        }
        if (bindings.hasUnbound(name)) {
            return CORE_TYPE_OBJECT;
        }
        bindings = bindings.withUnboundVariable(name);
        Type[] bounds = var.getBounds();
        return this._fromAny(context, bounds[0], bindings);
    }

    protected JavaType _fromWildcard(ClassStack context, WildcardType type, TypeBindings bindings) {
        return this._fromAny(context, type.getUpperBounds()[0], bindings);
    }
}

