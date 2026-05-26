/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors;

import java.util.HashMap;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonCreator;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonValue;

public enum JsonFormatTypes {
    STRING,
    NUMBER,
    INTEGER,
    BOOLEAN,
    OBJECT,
    ARRAY,
    NULL,
    ANY;

    private static final Map<String, JsonFormatTypes> _byLCName;

    @JsonValue
    public String value() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static JsonFormatTypes forValue(String s2) {
        return _byLCName.get(s2);
    }

    static {
        _byLCName = new HashMap<String, JsonFormatTypes>();
        for (JsonFormatTypes t2 : JsonFormatTypes.values()) {
            _byLCName.put(t2.name().toLowerCase(), t2);
        }
    }
}

