/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class TextureMesh {
    private double[] localPivot;
    private double[] position;
    private double[] rotation;
    private double[] scale;
    private String texture;

    @JsonProperty(value="local_pivot")
    public double[] getLocalPivot() {
        return this.localPivot;
    }

    @JsonProperty(value="local_pivot")
    public void setLocalPivot(double[] value) {
        this.localPivot = value;
    }

    @JsonProperty(value="position")
    public double[] getPosition() {
        return this.position;
    }

    @JsonProperty(value="position")
    public void setPosition(double[] value) {
        this.position = value;
    }

    @JsonProperty(value="rotation")
    public double[] getRotation() {
        return this.rotation;
    }

    @JsonProperty(value="rotation")
    public void setRotation(double[] value) {
        this.rotation = value;
    }

    @JsonProperty(value="scale")
    public double[] getScale() {
        return this.scale;
    }

    @JsonProperty(value="scale")
    public void setScale(double[] value) {
        this.scale = value;
    }

    @JsonProperty(value="texture")
    public String getTexture() {
        return this.texture;
    }

    @JsonProperty(value="texture")
    public void setTexture(String value) {
        this.texture = value;
    }
}

