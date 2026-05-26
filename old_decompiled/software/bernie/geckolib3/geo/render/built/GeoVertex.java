/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector3f
 */
package software.bernie.geckolib3.geo.render.built;

import javax.vecmath.Vector3f;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class GeoVertex {
    public final Vector3f position;
    public float textureU;
    public float textureV;

    public GeoVertex(float x2, float y2, float z2) {
        this.position = new Vector3f(x2, y2, z2);
    }

    public GeoVertex(double x2, double y2, double z2) {
        this.position = new Vector3f((float)x2, (float)y2, (float)z2);
    }

    public GeoVertex setTextureUV(float texU, float texV) {
        return new GeoVertex(this.position, texU, texV);
    }

    public GeoVertex setTextureUV(double[] array) {
        Validate.validIndex(ArrayUtils.toObject(array), 1);
        return new GeoVertex(this.position, (float)array[0], (float)array[1]);
    }

    public GeoVertex(Vector3f posIn, float texU, float texV) {
        this.position = posIn;
        this.textureU = texU;
        this.textureV = texV;
    }
}

