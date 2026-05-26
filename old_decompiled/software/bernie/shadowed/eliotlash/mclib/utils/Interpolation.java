/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.utils;

import software.bernie.shadowed.eliotlash.mclib.utils.Interpolations;

public enum Interpolation {
    LINEAR("linear"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            return Interpolations.lerp(a10, b10, x2);
        }
    }
    ,
    QUAD_IN("quad_in"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            return a10 + (b10 - a10) * x2 * x2;
        }
    }
    ,
    QUAD_OUT("quad_out"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            return a10 - (b10 - a10) * x2 * (x2 - 2.0f);
        }
    }
    ,
    QUAD_INOUT("quad_inout"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            if ((x2 *= 2.0f) < 1.0f) {
                return a10 + (b10 - a10) / 2.0f * x2 * x2;
            }
            return a10 - (b10 - a10) / 2.0f * ((x2 -= 1.0f) * (x2 - 2.0f) - 1.0f);
        }
    }
    ,
    CUBIC_IN("cubic_in"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            return a10 + (b10 - a10) * x2 * x2 * x2;
        }
    }
    ,
    CUBIC_OUT("cubic_out"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            return a10 + (b10 - a10) * ((x2 -= 1.0f) * x2 * x2 + 1.0f);
        }
    }
    ,
    CUBIC_INOUT("cubic_inout"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            if ((x2 *= 2.0f) < 1.0f) {
                return a10 + (b10 - a10) / 2.0f * x2 * x2 * x2;
            }
            return a10 + (b10 - a10) / 2.0f * ((x2 -= 2.0f) * x2 * x2 + 2.0f);
        }
    }
    ,
    EXP_IN("exp_in"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            return a10 + (b10 - a10) * (float)Math.pow(2.0, 10.0f * (x2 - 1.0f));
        }
    }
    ,
    EXP_OUT("exp_out"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            return a10 + (b10 - a10) * (float)(-Math.pow(2.0, -10.0f * x2) + 1.0);
        }
    }
    ,
    EXP_INOUT("exp_inout"){

        @Override
        public float interpolate(float a10, float b10, float x2) {
            if (x2 == 0.0f) {
                return a10;
            }
            if (x2 == 1.0f) {
                return b10;
            }
            if ((x2 *= 2.0f) < 1.0f) {
                return a10 + (b10 - a10) / 2.0f * (float)Math.pow(2.0, 10.0f * (x2 - 1.0f));
            }
            return a10 + (b10 - a10) / 2.0f * (float)(-Math.pow(2.0, -10.0f * (x2 -= 1.0f)) + 2.0);
        }
    };

    public final String key;

    private Interpolation(String key) {
        this.key = key;
    }

    public abstract float interpolate(float var1, float var2, float var3);

    public String getName() {
        return "mclib.interpolations." + this.key;
    }
}

