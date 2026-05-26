/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class ModelProperties {
    private Boolean animationArmsDown;
    private Boolean animationArmsOutFront;
    private Boolean animationDontShowArmor;
    private Boolean animationInvertedCrouch;
    private Boolean animationNoHeadBob;
    private Boolean animationSingleArmAnimation;
    private Boolean animationSingleLegAnimation;
    private Boolean animationStationaryLegs;
    private Boolean animationStatueOfLibertyArms;
    private Boolean animationUpsideDown;
    private String identifier;
    private Boolean preserveModelPose;
    private Double textureHeight;
    private Double textureWidth;
    private Double visibleBoundsHeight;
    private double[] visibleBoundsOffset;
    private Double visibleBoundsWidth;

    @JsonProperty(value="animationArmsDown")
    public Boolean getAnimationArmsDown() {
        return this.animationArmsDown;
    }

    @JsonProperty(value="animationArmsDown")
    public void setAnimationArmsDown(Boolean value) {
        this.animationArmsDown = value;
    }

    @JsonProperty(value="animationArmsOutFront")
    public Boolean getAnimationArmsOutFront() {
        return this.animationArmsOutFront;
    }

    @JsonProperty(value="animationArmsOutFront")
    public void setAnimationArmsOutFront(Boolean value) {
        this.animationArmsOutFront = value;
    }

    @JsonProperty(value="animationDontShowArmor")
    public Boolean getAnimationDontShowArmor() {
        return this.animationDontShowArmor;
    }

    @JsonProperty(value="animationDontShowArmor")
    public void setAnimationDontShowArmor(Boolean value) {
        this.animationDontShowArmor = value;
    }

    @JsonProperty(value="animationInvertedCrouch")
    public Boolean getAnimationInvertedCrouch() {
        return this.animationInvertedCrouch;
    }

    @JsonProperty(value="animationInvertedCrouch")
    public void setAnimationInvertedCrouch(Boolean value) {
        this.animationInvertedCrouch = value;
    }

    @JsonProperty(value="animationNoHeadBob")
    public Boolean getAnimationNoHeadBob() {
        return this.animationNoHeadBob;
    }

    @JsonProperty(value="animationNoHeadBob")
    public void setAnimationNoHeadBob(Boolean value) {
        this.animationNoHeadBob = value;
    }

    @JsonProperty(value="animationSingleArmAnimation")
    public Boolean getAnimationSingleArmAnimation() {
        return this.animationSingleArmAnimation;
    }

    @JsonProperty(value="animationSingleArmAnimation")
    public void setAnimationSingleArmAnimation(Boolean value) {
        this.animationSingleArmAnimation = value;
    }

    @JsonProperty(value="animationSingleLegAnimation")
    public Boolean getAnimationSingleLegAnimation() {
        return this.animationSingleLegAnimation;
    }

    @JsonProperty(value="animationSingleLegAnimation")
    public void setAnimationSingleLegAnimation(Boolean value) {
        this.animationSingleLegAnimation = value;
    }

    @JsonProperty(value="animationStationaryLegs")
    public Boolean getAnimationStationaryLegs() {
        return this.animationStationaryLegs;
    }

    @JsonProperty(value="animationStationaryLegs")
    public void setAnimationStationaryLegs(Boolean value) {
        this.animationStationaryLegs = value;
    }

    @JsonProperty(value="animationStatueOfLibertyArms")
    public Boolean getAnimationStatueOfLibertyArms() {
        return this.animationStatueOfLibertyArms;
    }

    @JsonProperty(value="animationStatueOfLibertyArms")
    public void setAnimationStatueOfLibertyArms(Boolean value) {
        this.animationStatueOfLibertyArms = value;
    }

    @JsonProperty(value="animationUpsideDown")
    public Boolean getAnimationUpsideDown() {
        return this.animationUpsideDown;
    }

    @JsonProperty(value="animationUpsideDown")
    public void setAnimationUpsideDown(Boolean value) {
        this.animationUpsideDown = value;
    }

    @JsonProperty(value="identifier")
    public String getIdentifier() {
        return this.identifier;
    }

    @JsonProperty(value="identifier")
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    @JsonProperty(value="preserve_model_pose")
    public Boolean getPreserveModelPose() {
        return this.preserveModelPose;
    }

    @JsonProperty(value="preserve_model_pose")
    public void setPreserveModelPose(Boolean value) {
        this.preserveModelPose = value;
    }

    @JsonProperty(value="texture_height")
    public Double getTextureHeight() {
        return this.textureHeight;
    }

    @JsonProperty(value="texture_height")
    public void setTextureHeight(Double value) {
        this.textureHeight = value;
    }

    @JsonProperty(value="texture_width")
    public Double getTextureWidth() {
        return this.textureWidth;
    }

    @JsonProperty(value="texture_width")
    public void setTextureWidth(Double value) {
        this.textureWidth = value;
    }

    @JsonProperty(value="visible_bounds_height")
    public Double getVisibleBoundsHeight() {
        return this.visibleBoundsHeight;
    }

    @JsonProperty(value="visible_bounds_height")
    public void setVisibleBoundsHeight(Double value) {
        this.visibleBoundsHeight = value;
    }

    @JsonProperty(value="visible_bounds_offset")
    public double[] getVisibleBoundsOffset() {
        return this.visibleBoundsOffset;
    }

    @JsonProperty(value="visible_bounds_offset")
    public void setVisibleBoundsOffset(double[] value) {
        this.visibleBoundsOffset = value;
    }

    @JsonProperty(value="visible_bounds_width")
    public Double getVisibleBoundsWidth() {
        return this.visibleBoundsWidth;
    }

    @JsonProperty(value="visible_bounds_width")
    public void setVisibleBoundsWidth(Double value) {
        this.visibleBoundsWidth = value;
    }
}

