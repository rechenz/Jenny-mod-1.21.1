/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core;

import java.io.IOException;
import java.util.Iterator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonFactory;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.TreeCodec;
import software.bernie.shadowed.fasterxml.jackson.core.TreeNode;
import software.bernie.shadowed.fasterxml.jackson.core.Version;
import software.bernie.shadowed.fasterxml.jackson.core.Versioned;
import software.bernie.shadowed.fasterxml.jackson.core.type.ResolvedType;
import software.bernie.shadowed.fasterxml.jackson.core.type.TypeReference;

public abstract class ObjectCodec
extends TreeCodec
implements Versioned {
    protected ObjectCodec() {
    }

    @Override
    public abstract Version version();

    public abstract <T> T readValue(JsonParser var1, Class<T> var2) throws IOException;

    public abstract <T> T readValue(JsonParser var1, TypeReference<?> var2) throws IOException;

    public abstract <T> T readValue(JsonParser var1, ResolvedType var2) throws IOException;

    public abstract <T> Iterator<T> readValues(JsonParser var1, Class<T> var2) throws IOException;

    public abstract <T> Iterator<T> readValues(JsonParser var1, TypeReference<?> var2) throws IOException;

    public abstract <T> Iterator<T> readValues(JsonParser var1, ResolvedType var2) throws IOException;

    public abstract void writeValue(JsonGenerator var1, Object var2) throws IOException;

    @Override
    public abstract <T extends TreeNode> T readTree(JsonParser var1) throws IOException;

    @Override
    public abstract void writeTree(JsonGenerator var1, TreeNode var2) throws IOException;

    @Override
    public abstract TreeNode createObjectNode();

    @Override
    public abstract TreeNode createArrayNode();

    @Override
    public abstract JsonParser treeAsTokens(TreeNode var1);

    public abstract <T> T treeToValue(TreeNode var1, Class<T> var2) throws JsonProcessingException;

    @Deprecated
    public JsonFactory getJsonFactory() {
        return this.getFactory();
    }

    public JsonFactory getFactory() {
        return this.getJsonFactory();
    }
}

