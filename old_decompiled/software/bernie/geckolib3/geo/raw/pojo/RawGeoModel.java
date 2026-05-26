/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import software.bernie.geckolib3.geo.raw.pojo.FormatVersion;
import software.bernie.geckolib3.geo.raw.pojo.MinecraftGeometry;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonProperty;

public class RawGeoModel {
    private FormatVersion formatVersion;
    private MinecraftGeometry[] minecraftGeometry;

    @JsonProperty(value="format_version")
    public FormatVersion getFormatVersion() {
        return this.formatVersion;
    }

    @JsonProperty(value="format_version")
    public void setFormatVersion(FormatVersion value) {
        this.formatVersion = value;
    }

    @JsonProperty(value="minecraft:geometry")
    public MinecraftGeometry[] getMinecraftGeometry() {
        return this.minecraftGeometry;
    }

    @JsonProperty(value="minecraft:geometry")
    public void setMinecraftGeometry(MinecraftGeometry[] value) {
        this.minecraftGeometry = value;
    }
}

