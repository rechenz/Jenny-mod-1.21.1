/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsonschema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import software.bernie.shadowed.fasterxml.jackson.annotation.JacksonAnnotation;

@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonSerializableSchema {
    public static final String NO_VALUE = "##irrelevant";

    public String id() default "";

    public String schemaType() default "any";

    @Deprecated
    public String schemaObjectPropertiesDefinition() default "##irrelevant";

    @Deprecated
    public String schemaItemDefinition() default "##irrelevant";
}

