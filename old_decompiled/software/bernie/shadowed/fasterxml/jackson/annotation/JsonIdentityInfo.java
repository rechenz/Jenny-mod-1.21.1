/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import software.bernie.shadowed.fasterxml.jackson.annotation.JacksonAnnotation;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdResolver;
import software.bernie.shadowed.fasterxml.jackson.annotation.SimpleObjectIdResolver;

@Target(value={ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(value=RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonIdentityInfo {
    public String property() default "@id";

    public Class<? extends ObjectIdGenerator<?>> generator();

    public Class<? extends ObjectIdResolver> resolver() default SimpleObjectIdResolver.class;

    public Class<?> scope() default Object.class;
}

