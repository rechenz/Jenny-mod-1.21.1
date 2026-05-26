/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors;

import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

public interface JsonValueFormatVisitor {
    public void format(JsonValueFormat var1);

    public void enumTypes(Set<String> var1);

    public static class Base
    implements JsonValueFormatVisitor {
        @Override
        public void format(JsonValueFormat format) {
        }

        @Override
        public void enumTypes(Set<String> enums) {
        }
    }
}

