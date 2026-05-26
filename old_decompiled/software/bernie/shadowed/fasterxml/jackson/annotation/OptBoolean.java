/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.annotation;

public enum OptBoolean {
    TRUE,
    FALSE,
    DEFAULT;


    public Boolean asBoolean() {
        if (this == DEFAULT) {
            return null;
        }
        return this == TRUE ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean asPrimitive() {
        return this == TRUE;
    }

    public static OptBoolean fromBoolean(Boolean b10) {
        if (b10 == null) {
            return DEFAULT;
        }
        return b10 != false ? TRUE : FALSE;
    }

    public static boolean equals(Boolean b12, Boolean b22) {
        if (b12 == null) {
            return b22 == null;
        }
        return b12.equals(b22);
    }
}

