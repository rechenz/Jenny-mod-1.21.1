/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.impl;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanDescription;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.MapperConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.SettableBeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.ValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.Annotated;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationMap;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class CreatorCollector {
    protected static final int C_DEFAULT = 0;
    protected static final int C_STRING = 1;
    protected static final int C_INT = 2;
    protected static final int C_LONG = 3;
    protected static final int C_DOUBLE = 4;
    protected static final int C_BOOLEAN = 5;
    protected static final int C_DELEGATE = 6;
    protected static final int C_PROPS = 7;
    protected static final int C_ARRAY_DELEGATE = 8;
    protected static final String[] TYPE_DESCS = new String[]{"default", "from-String", "from-int", "from-long", "from-double", "from-boolean", "delegate", "property-based"};
    protected final BeanDescription _beanDesc;
    protected final boolean _canFixAccess;
    protected final boolean _forceAccess;
    protected final AnnotatedWithParams[] _creators = new AnnotatedWithParams[9];
    protected int _explicitCreators = 0;
    protected boolean _hasNonDefaultCreator = false;
    protected SettableBeanProperty[] _delegateArgs;
    protected SettableBeanProperty[] _arrayDelegateArgs;
    protected SettableBeanProperty[] _propertyBasedArgs;
    protected AnnotatedParameter _incompleteParameter;

    public CreatorCollector(BeanDescription beanDesc, MapperConfig<?> config) {
        this._beanDesc = beanDesc;
        this._canFixAccess = config.canOverrideAccessModifiers();
        this._forceAccess = config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS);
    }

    public ValueInstantiator constructValueInstantiator(DeserializationConfig config) {
        JavaType delegateType = this._computeDelegateType(this._creators[6], this._delegateArgs);
        JavaType arrayDelegateType = this._computeDelegateType(this._creators[8], this._arrayDelegateArgs);
        JavaType type = this._beanDesc.getType();
        AnnotatedWithParams defaultCtor = StdTypeConstructor.tryToOptimize(this._creators[0]);
        StdValueInstantiator inst = new StdValueInstantiator(config, type);
        inst.configureFromObjectSettings(defaultCtor, this._creators[6], delegateType, this._delegateArgs, this._creators[7], this._propertyBasedArgs);
        inst.configureFromArraySettings(this._creators[8], arrayDelegateType, this._arrayDelegateArgs);
        inst.configureFromStringCreator(this._creators[1]);
        inst.configureFromIntCreator(this._creators[2]);
        inst.configureFromLongCreator(this._creators[3]);
        inst.configureFromDoubleCreator(this._creators[4]);
        inst.configureFromBooleanCreator(this._creators[5]);
        inst.configureIncompleteParameter(this._incompleteParameter);
        return inst;
    }

    public void setDefaultCreator(AnnotatedWithParams creator) {
        this._creators[0] = this._fixAccess(creator);
    }

    public void addStringCreator(AnnotatedWithParams creator, boolean explicit) {
        this.verifyNonDup(creator, 1, explicit);
    }

    public void addIntCreator(AnnotatedWithParams creator, boolean explicit) {
        this.verifyNonDup(creator, 2, explicit);
    }

    public void addLongCreator(AnnotatedWithParams creator, boolean explicit) {
        this.verifyNonDup(creator, 3, explicit);
    }

    public void addDoubleCreator(AnnotatedWithParams creator, boolean explicit) {
        this.verifyNonDup(creator, 4, explicit);
    }

    public void addBooleanCreator(AnnotatedWithParams creator, boolean explicit) {
        this.verifyNonDup(creator, 5, explicit);
    }

    public void addDelegatingCreator(AnnotatedWithParams creator, boolean explicit, SettableBeanProperty[] injectables) {
        if (creator.getParameterType(0).isCollectionLikeType()) {
            if (this.verifyNonDup(creator, 8, explicit)) {
                this._arrayDelegateArgs = injectables;
            }
        } else if (this.verifyNonDup(creator, 6, explicit)) {
            this._delegateArgs = injectables;
        }
    }

    public void addPropertyCreator(AnnotatedWithParams creator, boolean explicit, SettableBeanProperty[] properties) {
        if (this.verifyNonDup(creator, 7, explicit)) {
            if (properties.length > 1) {
                HashMap<String, Integer> names = new HashMap<String, Integer>();
                int len = properties.length;
                for (int i2 = 0; i2 < len; ++i2) {
                    Integer old;
                    String name = properties[i2].getName();
                    if (name.length() == 0 && properties[i2].getInjectableValueId() != null || (old = names.put(name, i2)) == null) continue;
                    throw new IllegalArgumentException(String.format("Duplicate creator property \"%s\" (index %s vs %d)", name, old, i2));
                }
            }
            this._propertyBasedArgs = properties;
        }
    }

    public void addIncompeteParameter(AnnotatedParameter parameter) {
        if (this._incompleteParameter == null) {
            this._incompleteParameter = parameter;
        }
    }

    public boolean hasDefaultCreator() {
        return this._creators[0] != null;
    }

    public boolean hasDelegatingCreator() {
        return this._creators[6] != null;
    }

    public boolean hasPropertyBasedCreator() {
        return this._creators[7] != null;
    }

    private JavaType _computeDelegateType(AnnotatedWithParams creator, SettableBeanProperty[] delegateArgs) {
        if (!this._hasNonDefaultCreator || creator == null) {
            return null;
        }
        int ix = 0;
        if (delegateArgs != null) {
            int len = delegateArgs.length;
            for (int i2 = 0; i2 < len; ++i2) {
                if (delegateArgs[i2] != null) continue;
                ix = i2;
                break;
            }
        }
        return creator.getParameterType(ix);
    }

    private <T extends AnnotatedMember> T _fixAccess(T member) {
        if (member != null && this._canFixAccess) {
            ClassUtil.checkAndFixAccess((Member)((Object)member.getAnnotated()), this._forceAccess);
        }
        return member;
    }

    protected boolean verifyNonDup(AnnotatedWithParams newOne, int typeIndex, boolean explicit) {
        int mask = 1 << typeIndex;
        this._hasNonDefaultCreator = true;
        AnnotatedWithParams oldOne = this._creators[typeIndex];
        if (oldOne != null) {
            boolean verify;
            if ((this._explicitCreators & mask) != 0) {
                if (!explicit) {
                    return false;
                }
                verify = true;
            } else {
                boolean bl2 = verify = !explicit;
            }
            if (verify && oldOne.getClass() == newOne.getClass()) {
                Class<?> newType;
                Class<?> oldType = oldOne.getRawParameterType(0);
                if (oldType == (newType = newOne.getRawParameterType(0))) {
                    if (this._isEnumValueOf(newOne)) {
                        return false;
                    }
                    if (!this._isEnumValueOf(oldOne)) {
                        throw new IllegalArgumentException(String.format("Conflicting %s creators: already had %s creator %s, encountered another: %s", TYPE_DESCS[typeIndex], explicit ? "explicitly marked" : "implicitly discovered", oldOne, newOne));
                    }
                } else if (newType.isAssignableFrom(oldType)) {
                    return false;
                }
            }
        }
        if (explicit) {
            this._explicitCreators |= mask;
        }
        this._creators[typeIndex] = this._fixAccess(newOne);
        return true;
    }

    protected boolean _isEnumValueOf(AnnotatedWithParams creator) {
        return creator.getDeclaringClass().isEnum() && "valueOf".equals(creator.getName());
    }

    protected static final class StdTypeConstructor
    extends AnnotatedWithParams
    implements Serializable {
        private static final long serialVersionUID = 1L;
        public static final int TYPE_ARRAY_LIST = 1;
        public static final int TYPE_HASH_MAP = 2;
        public static final int TYPE_LINKED_HASH_MAP = 3;
        private final AnnotatedWithParams _base;
        private final int _type;

        public StdTypeConstructor(AnnotatedWithParams base, int t2) {
            super(base, null);
            this._base = base;
            this._type = t2;
        }

        public static AnnotatedWithParams tryToOptimize(AnnotatedWithParams src) {
            if (src != null) {
                Class<?> rawType = src.getDeclaringClass();
                if (rawType == List.class || rawType == ArrayList.class) {
                    return new StdTypeConstructor(src, 1);
                }
                if (rawType == LinkedHashMap.class) {
                    return new StdTypeConstructor(src, 3);
                }
                if (rawType == HashMap.class) {
                    return new StdTypeConstructor(src, 2);
                }
            }
            return src;
        }

        protected final Object _construct() {
            switch (this._type) {
                case 1: {
                    return new ArrayList();
                }
                case 3: {
                    return new LinkedHashMap();
                }
                case 2: {
                    return new HashMap();
                }
            }
            throw new IllegalStateException("Unknown type " + this._type);
        }

        @Override
        public int getParameterCount() {
            return this._base.getParameterCount();
        }

        @Override
        public Class<?> getRawParameterType(int index) {
            return this._base.getRawParameterType(index);
        }

        @Override
        public JavaType getParameterType(int index) {
            return this._base.getParameterType(index);
        }

        @Override
        @Deprecated
        public Type getGenericParameterType(int index) {
            return this._base.getGenericParameterType(index);
        }

        @Override
        public Object call() throws Exception {
            return this._construct();
        }

        @Override
        public Object call(Object[] args) throws Exception {
            return this._construct();
        }

        @Override
        public Object call1(Object arg) throws Exception {
            return this._construct();
        }

        @Override
        public Class<?> getDeclaringClass() {
            return this._base.getDeclaringClass();
        }

        @Override
        public Member getMember() {
            return this._base.getMember();
        }

        @Override
        public void setValue(Object pojo, Object value) throws UnsupportedOperationException, IllegalArgumentException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object getValue(Object pojo) throws UnsupportedOperationException, IllegalArgumentException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Annotated withAnnotations(AnnotationMap fallback) {
            throw new UnsupportedOperationException();
        }

        @Override
        public AnnotatedElement getAnnotated() {
            return this._base.getAnnotated();
        }

        @Override
        protected int getModifiers() {
            return this._base.getMember().getModifiers();
        }

        @Override
        public String getName() {
            return this._base.getName();
        }

        @Override
        public JavaType getType() {
            return this._base.getType();
        }

        @Override
        public Class<?> getRawType() {
            return this._base.getRawType();
        }

        @Override
        public boolean equals(Object o2) {
            return o2 == this;
        }

        @Override
        public int hashCode() {
            return this._base.hashCode();
        }

        @Override
        public String toString() {
            return this._base.toString();
        }
    }
}

