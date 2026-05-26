/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.introspect;

import java.util.Iterator;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.databind.AnnotationIntrospector;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyMetadata;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedField;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;
import software.bernie.shadowed.fasterxml.jackson.databind.util.Named;

public abstract class BeanPropertyDefinition
implements Named {
    protected static final JsonInclude.Value EMPTY_INCLUDE = JsonInclude.Value.empty();

    public abstract BeanPropertyDefinition withName(PropertyName var1);

    public abstract BeanPropertyDefinition withSimpleName(String var1);

    @Override
    public abstract String getName();

    public abstract PropertyName getFullName();

    public boolean hasName(PropertyName name) {
        return this.getFullName().equals(name);
    }

    public abstract String getInternalName();

    public abstract PropertyName getWrapperName();

    public abstract boolean isExplicitlyIncluded();

    public boolean isExplicitlyNamed() {
        return this.isExplicitlyIncluded();
    }

    public abstract JavaType getPrimaryType();

    public abstract Class<?> getRawPrimaryType();

    public abstract PropertyMetadata getMetadata();

    public boolean isRequired() {
        return this.getMetadata().isRequired();
    }

    public boolean couldDeserialize() {
        return this.getMutator() != null;
    }

    public boolean couldSerialize() {
        return this.getAccessor() != null;
    }

    public abstract boolean hasGetter();

    public abstract boolean hasSetter();

    public abstract boolean hasField();

    public abstract boolean hasConstructorParameter();

    public abstract AnnotatedMethod getGetter();

    public abstract AnnotatedMethod getSetter();

    public abstract AnnotatedField getField();

    public abstract AnnotatedParameter getConstructorParameter();

    public Iterator<AnnotatedParameter> getConstructorParameters() {
        return ClassUtil.emptyIterator();
    }

    public AnnotatedMember getAccessor() {
        AnnotatedMember m2 = this.getGetter();
        if (m2 == null) {
            m2 = this.getField();
        }
        return m2;
    }

    public AnnotatedMember getMutator() {
        AnnotatedMember acc = this.getConstructorParameter();
        if (acc == null && (acc = this.getSetter()) == null) {
            acc = this.getField();
        }
        return acc;
    }

    public AnnotatedMember getNonConstructorMutator() {
        AnnotatedMember m2 = this.getSetter();
        if (m2 == null) {
            m2 = this.getField();
        }
        return m2;
    }

    public abstract AnnotatedMember getPrimaryMember();

    public Class<?>[] findViews() {
        return null;
    }

    public AnnotationIntrospector.ReferenceProperty findReferenceType() {
        return null;
    }

    public String findReferenceName() {
        AnnotationIntrospector.ReferenceProperty ref = this.findReferenceType();
        return ref == null ? null : ref.getName();
    }

    public boolean isTypeId() {
        return false;
    }

    public ObjectIdInfo findObjectIdInfo() {
        return null;
    }

    public abstract JsonInclude.Value findInclusion();
}

