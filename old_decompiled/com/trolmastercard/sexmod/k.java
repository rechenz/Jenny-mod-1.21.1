/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.i;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class k
extends AnimatedGeoModel<i> {
    public ResourceLocation b(i i2) {
        return new ResourceLocation("sexmod", "geo/kobold/koboldegg.geo.json");
    }

    public ResourceLocation c(i i2) {
        return new ResourceLocation("sexmod", "textures/entity/kobold/koboldegg.png");
    }

    public ResourceLocation a(i i2) {
        return new ResourceLocation("sexmod", "animations/kobold/egg.animation.json");
    }
}

