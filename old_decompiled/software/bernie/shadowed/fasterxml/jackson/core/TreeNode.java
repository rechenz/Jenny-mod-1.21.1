/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core;

import java.util.Iterator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonPointer;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;

public interface TreeNode {
    public JsonToken asToken();

    public JsonParser.NumberType numberType();

    public int size();

    public boolean isValueNode();

    public boolean isContainerNode();

    public boolean isMissingNode();

    public boolean isArray();

    public boolean isObject();

    public TreeNode get(String var1);

    public TreeNode get(int var1);

    public TreeNode path(String var1);

    public TreeNode path(int var1);

    public Iterator<String> fieldNames();

    public TreeNode at(JsonPointer var1);

    public TreeNode at(String var1) throws IllegalArgumentException;

    public JsonParser traverse();

    public JsonParser traverse(ObjectCodec var1);
}

