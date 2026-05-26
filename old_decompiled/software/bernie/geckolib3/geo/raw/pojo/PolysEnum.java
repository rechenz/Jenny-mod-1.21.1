/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonCreator;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonValue;

public enum PolysEnum {
    QUAD_LIST,
    TRI_LIST;


    @JsonValue
    public String toValue() {
        switch (this) {
            case QUAD_LIST: {
                return "quad_list";
            }
            case TRI_LIST: {
                return "tri_list";
            }
        }
        return null;
    }

    @JsonCreator
    public static PolysEnum forValue(String value) throws IOException {
        if (value.equals("quad_list")) {
            return QUAD_LIST;
        }
        if (value.equals("tri_list")) {
            return TRI_LIST;
        }
        throw new IOException("Cannot deserialize PolysEnum");
    }
}

