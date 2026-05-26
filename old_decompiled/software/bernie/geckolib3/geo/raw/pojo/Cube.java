/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.geckolib3.geo.raw.pojo.UvUnion;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class Cube {
    private Double inflate;
    private Boolean mirror;
    private double[] origin = new double[]{0.0, 0.0, 0.0};
    private double[] pivot = new double[]{0.0, 0.0, 0.0};
    private double[] rotation = new double[]{0.0, 0.0, 0.0};
    private double[] size = new double[]{1.0, 1.0, 1.0};
    private UvUnion uv;

    @JsonProperty(value="inflate")
    public Double getInflate() {
        return this.inflate;
    }

    @JsonProperty(value="inflate")
    public void setInflate(Double value) {
        this.inflate = value;
    }

    @JsonProperty(value="mirror")
    public Boolean getMirror() {
        return this.mirror;
    }

    @JsonProperty(value="mirror")
    public void setMirror(Boolean value) {
        this.mirror = value;
    }

    @JsonProperty(value="origin")
    public double[] getOrigin() {
        return this.origin;
    }

    @JsonProperty(value="origin")
    public void setOrigin(double[] value) {
        this.origin = value;
    }

    @JsonProperty(value="pivot")
    public double[] getPivot() {
        return this.pivot;
    }

    @JsonProperty(value="pivot")
    public void setPivot(double[] value) {
        this.pivot = value;
    }

    @JsonProperty(value="rotation")
    public double[] getRotation() {
        return this.rotation;
    }

    @JsonProperty(value="rotation")
    public void setRotation(double[] value) {
        this.rotation = value;
    }

    @JsonProperty(value="size")
    public double[] getSize() {
        return this.size;
    }

    @JsonProperty(value="size")
    public void setSize(double[] value) {
        this.size = value;
    }

    @JsonProperty(value="uv")
    public UvUnion getUv() {
        return this.uv;
    }

    @JsonProperty(value="uv")
    public void setUv(UvUnion value) {
        this.uv = value;
    }
}

