/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonAutoDetect;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonIgnoreProperties;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonInclude;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonSetter;
import software.bernie.shadowed.fasterxml.jackson.databind.cfg.ConfigOverride;

public class MutableConfigOverride
extends ConfigOverride
implements Serializable {
    private static final long serialVersionUID = 1L;

    public MutableConfigOverride() {
    }

    protected MutableConfigOverride(MutableConfigOverride src) {
        super(src);
    }

    public MutableConfigOverride copy() {
        return new MutableConfigOverride(this);
    }

    public MutableConfigOverride setFormat(JsonFormat.Value v2) {
        this._format = v2;
        return this;
    }

    public MutableConfigOverride setInclude(JsonInclude.Value v2) {
        this._include = v2;
        return this;
    }

    public MutableConfigOverride setIncludeAsProperty(JsonInclude.Value v2) {
        this._includeAsProperty = v2;
        return this;
    }

    public MutableConfigOverride setIgnorals(JsonIgnoreProperties.Value v2) {
        this._ignorals = v2;
        return this;
    }

    public MutableConfigOverride setIsIgnoredType(Boolean v2) {
        this._isIgnoredType = v2;
        return this;
    }

    public MutableConfigOverride setSetterInfo(JsonSetter.Value v2) {
        this._setterInfo = v2;
        return this;
    }

    public MutableConfigOverride setVisibility(JsonAutoDetect.Value v2) {
        this._visibility = v2;
        return this;
    }

    public MutableConfigOverride setMergeable(Boolean v2) {
        this._mergeable = v2;
        return this;
    }
}

