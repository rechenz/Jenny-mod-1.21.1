/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import java.util.Map;
import software.bernie.geckolib3.geo.raw.pojo.Cube;
import software.bernie.geckolib3.geo.raw.pojo.LocatorValue;
import software.bernie.geckolib3.geo.raw.pojo.PolyMesh;
import software.bernie.geckolib3.geo.raw.pojo.TextureMesh;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class Bone {
    private double[] bindPoseRotation;
    private Cube[] cubes;
    private Boolean debug;
    private Double inflate;
    private Map<String, LocatorValue> locators;
    private Boolean mirror;
    private String name;
    private Boolean neverRender;
    private String parent;
    private double[] pivot = new double[]{0.0, 0.0, 0.0};
    private PolyMesh polyMesh;
    private Long renderGroupID;
    private Boolean reset;
    private double[] rotation = new double[]{0.0, 0.0, 0.0};
    private TextureMesh[] textureMeshes;

    @JsonProperty(value="bind_pose_rotation")
    public double[] getBindPoseRotation() {
        return this.bindPoseRotation;
    }

    @JsonProperty(value="bind_pose_rotation")
    public void setBindPoseRotation(double[] value) {
        this.bindPoseRotation = value;
    }

    @JsonProperty(value="cubes")
    public Cube[] getCubes() {
        return this.cubes;
    }

    @JsonProperty(value="cubes")
    public void setCubes(Cube[] value) {
        this.cubes = value;
    }

    @JsonProperty(value="debug")
    public Boolean getDebug() {
        return this.debug;
    }

    @JsonProperty(value="debug")
    public void setDebug(Boolean value) {
        this.debug = value;
    }

    @JsonProperty(value="inflate")
    public Double getInflate() {
        return this.inflate;
    }

    @JsonProperty(value="inflate")
    public void setInflate(Double value) {
        this.inflate = value;
    }

    @JsonProperty(value="locators")
    public Map<String, LocatorValue> getLocators() {
        return this.locators;
    }

    @JsonProperty(value="locators")
    public void setLocators(Map<String, LocatorValue> value) {
        this.locators = value;
    }

    @JsonProperty(value="mirror")
    public Boolean getMirror() {
        return this.mirror;
    }

    @JsonProperty(value="mirror")
    public void setMirror(Boolean value) {
        this.mirror = value;
    }

    @JsonProperty(value="name")
    public String getName() {
        return this.name;
    }

    @JsonProperty(value="name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty(value="neverRender")
    public Boolean getNeverRender() {
        return this.neverRender;
    }

    @JsonProperty(value="neverRender")
    public void setNeverRender(Boolean value) {
        this.neverRender = value;
    }

    @JsonProperty(value="parent")
    public String getParent() {
        return this.parent;
    }

    @JsonProperty(value="parent")
    public void setParent(String value) {
        this.parent = value;
    }

    @JsonProperty(value="pivot")
    public double[] getPivot() {
        return this.pivot;
    }

    @JsonProperty(value="pivot")
    public void setPivot(double[] value) {
        this.pivot = value;
    }

    @JsonProperty(value="poly_mesh")
    public PolyMesh getPolyMesh() {
        return this.polyMesh;
    }

    @JsonProperty(value="poly_mesh")
    public void setPolyMesh(PolyMesh value) {
        this.polyMesh = value;
    }

    @JsonProperty(value="render_group_id")
    public Long getRenderGroupID() {
        return this.renderGroupID;
    }

    @JsonProperty(value="render_group_id")
    public void setRenderGroupID(Long value) {
        this.renderGroupID = value;
    }

    @JsonProperty(value="reset")
    public Boolean getReset() {
        return this.reset;
    }

    @JsonProperty(value="reset")
    public void setReset(Boolean value) {
        this.reset = value;
    }

    @JsonProperty(value="rotation")
    public double[] getRotation() {
        return this.rotation;
    }

    @JsonProperty(value="rotation")
    public void setRotation(double[] value) {
        this.rotation = value;
    }

    @JsonProperty(value="texture_meshes")
    public TextureMesh[] getTextureMeshes() {
        return this.textureMeshes;
    }

    @JsonProperty(value="texture_meshes")
    public void setTextureMeshes(TextureMesh[] value) {
        this.textureMeshes = value;
    }
}

