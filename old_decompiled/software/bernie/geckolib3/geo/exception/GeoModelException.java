/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.geo.exception;

import net.minecraft.util.ResourceLocation;

public class GeoModelException
extends RuntimeException {
    public GeoModelException(ResourceLocation fileLocation, String message) {
        super(fileLocation + ": " + message);
    }

    public GeoModelException(ResourceLocation fileLocation, String message, Throwable cause) {
        super(fileLocation + ": " + message, cause);
    }
}

