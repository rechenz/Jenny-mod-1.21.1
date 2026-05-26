/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.cy;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class o
extends AnimatedGeoModel<cy> {
    public ResourceLocation a(cy cy2) {
        try {
            if (cy2.f) {
                return new ResourceLocation("sexmod", "geo/cross.geo.json");
            }
        }
        catch (RuntimeException runtimeException) {
            throw o.a(runtimeException);
        }
        return br.k(cy2.a());
    }

    public ResourceLocation c(cy cy2) {
        try {
            if (cy2.f) {
                return new ResourceLocation("sexmod", "textures/cross.png");
            }
        }
        catch (RuntimeException runtimeException) {
            throw o.a(runtimeException);
        }
        return br.c(cy2.a());
    }

    public ResourceLocation b(cy cy2) {
        return new ResourceLocation("sexmod", "animations/slime/slime.animation.json");
    }

    public void a(cy cy2, Integer n2, @Nullable AnimationEvent animationEvent) {
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

