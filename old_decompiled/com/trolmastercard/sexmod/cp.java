/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.i;
import java.awt.Color;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class cp
extends GeoEntityRenderer<i> {
    public static final Color b = new Color(223, 206, 155);
    i a;

    public cp(RenderManager renderManager, AnimatedGeoModel<i> animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    public void a(GeoModel geoModel, i i2, float f10, float f11, float f12, float f13, float f14) {
        this.a = i2;
        super.render(geoModel, (Object)i2, f10, f11, f12, f13, f14);
    }

    @Override
    public void renderRecursively(BufferBuilder bufferBuilder, GeoBone geoBone, float f10, float f11, float f12, float f13) {
        String string = geoBone.getName();
        if ("shell".equals(string)) {
            f10 = (float)b.getRed() / 255.0f;
            f11 = (float)b.getGreen() / 255.0f;
            f12 = (float)b.getBlue() / 255.0f;
        }
        if ("colorSpots".equals(string)) {
            Vec3i vec3i = EyeAndKoboldColor.safeValueOf((String)this.a.func_184212_Q().func_187225_a(i.b)).getMainColor();
            f10 = (float)vec3i.func_177958_n() / 255.0f;
            f11 = (float)vec3i.func_177956_o() / 255.0f;
            f12 = (float)vec3i.func_177952_p() / 255.0f;
        }
        super.renderRecursively(bufferBuilder, geoBone, f10, f11, f12, f13);
    }
}

