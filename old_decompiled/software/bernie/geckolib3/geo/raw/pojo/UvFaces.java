/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.geckolib3.geo.raw.pojo.FaceUv;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class UvFaces {
    private FaceUv down;
    private FaceUv east;
    private FaceUv north;
    private FaceUv south;
    private FaceUv up;
    private FaceUv west;

    @JsonProperty(value="down")
    public FaceUv getDown() {
        return this.down;
    }

    @JsonProperty(value="down")
    public void setDown(FaceUv value) {
        this.down = value;
    }

    @JsonProperty(value="east")
    public FaceUv getEast() {
        return this.east;
    }

    @JsonProperty(value="east")
    public void setEast(FaceUv value) {
        this.east = value;
    }

    @JsonProperty(value="north")
    public FaceUv getNorth() {
        return this.north;
    }

    @JsonProperty(value="north")
    public void setNorth(FaceUv value) {
        this.north = value;
    }

    @JsonProperty(value="south")
    public FaceUv getSouth() {
        return this.south;
    }

    @JsonProperty(value="south")
    public void setSouth(FaceUv value) {
        this.south = value;
    }

    @JsonProperty(value="up")
    public FaceUv getUp() {
        return this.up;
    }

    @JsonProperty(value="up")
    public void setUp(FaceUv value) {
        this.up = value;
    }

    @JsonProperty(value="west")
    public FaceUv getWest() {
        return this.west;
    }

    @JsonProperty(value="west")
    public void setWest(FaceUv value) {
        this.west = value;
    }
}

