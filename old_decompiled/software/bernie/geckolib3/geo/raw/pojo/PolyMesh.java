/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.geckolib3.geo.raw.pojo.PolysUnion;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class PolyMesh {
    private Boolean normalizedUvs;
    private double[] normals;
    private PolysUnion polys;
    private double[] positions;
    private double[] uvs;

    @JsonProperty(value="normalized_uvs")
    public Boolean getNormalizedUvs() {
        return this.normalizedUvs;
    }

    @JsonProperty(value="normalized_uvs")
    public void setNormalizedUvs(Boolean value) {
        this.normalizedUvs = value;
    }

    @JsonProperty(value="normals")
    public double[] getNormals() {
        return this.normals;
    }

    @JsonProperty(value="normals")
    public void setNormals(double[] value) {
        this.normals = value;
    }

    @JsonProperty(value="polys")
    public PolysUnion getPolys() {
        return this.polys;
    }

    @JsonProperty(value="polys")
    public void setPolys(PolysUnion value) {
        this.polys = value;
    }

    @JsonProperty(value="positions")
    public double[] getPositions() {
        return this.positions;
    }

    @JsonProperty(value="positions")
    public void setPositions(double[] value) {
        this.positions = value;
    }

    @JsonProperty(value="uvs")
    public double[] getUvs() {
        return this.uvs;
    }

    @JsonProperty(value="uvs")
    public void setUvs(double[] value) {
        this.uvs = value;
    }
}

