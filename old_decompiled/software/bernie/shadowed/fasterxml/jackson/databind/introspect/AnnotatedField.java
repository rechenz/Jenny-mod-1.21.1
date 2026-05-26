/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotationMap;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public final class AnnotatedField
extends AnnotatedMember
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final transient Field _field;
    protected Serialization _serialization;

    public AnnotatedField(TypeResolutionContext contextClass, Field field, AnnotationMap annMap) {
        super(contextClass, annMap);
        this._field = field;
    }

    @Override
    public AnnotatedField withAnnotations(AnnotationMap ann) {
        return new AnnotatedField(this._typeContext, this._field, ann);
    }

    protected AnnotatedField(Serialization ser) {
        super(null, null);
        this._field = null;
        this._serialization = ser;
    }

    @Override
    public Field getAnnotated() {
        return this._field;
    }

    @Override
    public int getModifiers() {
        return this._field.getModifiers();
    }

    @Override
    public String getName() {
        return this._field.getName();
    }

    @Override
    public Class<?> getRawType() {
        return this._field.getType();
    }

    @Override
    @Deprecated
    public Type getGenericType() {
        return this._field.getGenericType();
    }

    @Override
    public JavaType getType() {
        return this._typeContext.resolveType(this._field.getGenericType());
    }

    @Override
    public Class<?> getDeclaringClass() {
        return this._field.getDeclaringClass();
    }

    @Override
    public Member getMember() {
        return this._field;
    }

    @Override
    public void setValue(Object pojo, Object value) throws IllegalArgumentException {
        try {
            this._field.set(pojo, value);
        }
        catch (IllegalAccessException e10) {
            throw new IllegalArgumentException("Failed to setValue() for field " + this.getFullName() + ": " + e10.getMessage(), e10);
        }
    }

    @Override
    public Object getValue(Object pojo) throws IllegalArgumentException {
        try {
            return this._field.get(pojo);
        }
        catch (IllegalAccessException e10) {
            throw new IllegalArgumentException("Failed to getValue() for field " + this.getFullName() + ": " + e10.getMessage(), e10);
        }
    }

    public int getAnnotationCount() {
        return this._annotations.size();
    }

    public boolean isTransient() {
        return Modifier.isTransient(this.getModifiers());
    }

    @Override
    public int hashCode() {
        return this._field.getName().hashCode();
    }

    @Override
    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        return ClassUtil.hasClass(o2, this.getClass()) && ((AnnotatedField)o2)._field == this._field;
    }

    @Override
    public String toString() {
        return "[field " + this.getFullName() + "]";
    }

    Object writeReplace() {
        return new AnnotatedField(new Serialization(this._field));
    }

    Object readResolve() {
        Class<?> clazz = this._serialization.clazz;
        try {
            Field f10 = clazz.getDeclaredField(this._serialization.name);
            if (!f10.isAccessible()) {
                ClassUtil.checkAndFixAccess(f10, false);
            }
            return new AnnotatedField(null, f10, null);
        }
        catch (Exception e10) {
            throw new IllegalArgumentException("Could not find method '" + this._serialization.name + "' from Class '" + clazz.getName());
        }
    }

    private static final class Serialization
    implements Serializable {
        private static final long serialVersionUID = 1L;
        protected Class<?> clazz;
        protected String name;

        public Serialization(Field f10) {
            this.clazz = f10.getDeclaringClass();
            this.name = f10.getName();
        }
    }
}

