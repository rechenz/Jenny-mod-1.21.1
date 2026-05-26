/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class FaceUv {
    private String materialInstance;
    private double[] uv;
    private double[] uvSize;

    @JsonProperty(value="material_instance")
    public String getMaterialInstance() {
        return this.materialInstance;
    }

    @JsonProperty(value="material_instance")
    public void setMaterialInstance(String value) {
        this.materialInstance = value;
    }

    @JsonProperty(value="uv")
    public double[] getUv() {
        return this.uv;
    }

    @JsonProperty(value="uv")
    public void setUv(double[] value) {
        this.uv = value;
    }

    @JsonProperty(value="uv_size")
    public double[] getUvSize() {
        return this.uvSize;
    }

    @JsonProperty(value="uv_size")
    public void setUvSize(double[] value) {
        this.uvSize = value;
    }
}

