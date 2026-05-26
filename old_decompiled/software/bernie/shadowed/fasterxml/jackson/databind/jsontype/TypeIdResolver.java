/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsontype;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.databind.DatabindContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;

public interface TypeIdResolver {
    public void init(JavaType var1);

    public String idFromValue(Object var1);

    public String idFromValueAndType(Object var1, Class<?> var2);

    public String idFromBaseType();

    public JavaType typeFromId(DatabindContext var1, String var2) throws IOException;

    public String getDescForKnownTypeIds();

    public JsonTypeInfo.Id getMechanism();
}

