/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class LocatorClass {
    private Boolean ignoreInheritedScale;
    private double[] offset;
    private double[] rotation;

    @JsonProperty(value="ignore_inherited_scale")
    public Boolean getIgnoreInheritedScale() {
        return this.ignoreInheritedScale;
    }

    @JsonProperty(value="ignore_inherited_scale")
    public void setIgnoreInheritedScale(Boolean value) {
        this.ignoreInheritedScale = value;
    }

    @JsonProperty(value="offset")
    public double[] getOffset() {
        return this.offset;
    }

    @JsonProperty(value="offset")
    public void setOffset(double[] value) {
        this.offset = value;
    }

    @JsonProperty(value="rotation")
    public double[] getRotation() {
        return this.rotation;
    }

    @JsonProperty(value="rotation")
    public void setRotation(double[] value) {
        this.rotation = value;
    }
}

