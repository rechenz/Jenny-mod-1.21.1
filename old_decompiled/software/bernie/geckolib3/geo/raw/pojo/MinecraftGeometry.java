/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.geckolib3.geo.raw.pojo.Bone;
import software.bernie.geckolib3.geo.raw.pojo.ModelProperties;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class MinecraftGeometry {
    private Bone[] bones;
    private String cape;
    private ModelProperties modelProperties;

    @JsonProperty(value="bones")
    public Bone[] getBones() {
        return this.bones;
    }

    @JsonProperty(value="bones")
    public void setBones(Bone[] value) {
        this.bones = value;
    }

    @JsonProperty(value="cape")
    public String getCape() {
        return this.cape;
    }

    @JsonProperty(value="cape")
    public void setCape(String value) {
        this.cape = value;
    }

    @JsonProperty(value="description")
    public ModelProperties getProperties() {
        return this.modelProperties;
    }

    @JsonProperty(value="description")
    public void setProperties(ModelProperties value) {
        this.modelProperties = value;
    }
}

