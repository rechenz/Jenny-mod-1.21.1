/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.d_;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ev;
import com.trolmastercard.sexmod.fp;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d8
extends d_ {
    public d8(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d10) {
        super(renderManager, animatedGeoModel, d10);
    }

    public void a(GeoModel geoModel, em em2, float f10, float f11, float f12, float f13, float f14) {
        float f15;
        float f16;
        float f17;
        ev ev2;
        block8: {
            ev2 = (ev)em2;
            try {
                try {
                    if (em2.y() != fp.NULL || em2.h()) break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw d8.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw d8.a(runtimeException);
            }
        }
        try {
            ev ev3 = ev2;
            f17 = ev2.U == 1.0f ? ev2.U : ev2.U - 0.01f;
        }
        catch (RuntimeException runtimeException) {
            throw d8.a(runtimeException);
        }
        ev3.U = f17;
        f14 = ev2.U;
        try {
            GlStateManager.func_179152_a((float)f14, (float)f14, (float)f14);
            f16 = 0.0f;
            f15 = f14 == 1.0f ? 0.0f : 3.0f - f14 * 3.0f;
        }
        catch (RuntimeException runtimeException) {
            throw d8.a(runtimeException);
        }
        GlStateManager.func_179109_b((float)f16, (float)f15, (float)0.0f);
        super.a(geoModel, em2, f10, f11, f12, f13, f14);
    }

    @Override
    protected void a(double d10, double d11, double d12) {
        try {
            if (this.j.y() == fp.NULL) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw d8.a(runtimeException);
        }
        try {
            if (this.j.h()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw d8.a(runtimeException);
        }
        try {
            if (this.j.y().hideNameTag) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw d8.a(runtimeException);
        }
        try {
            if (d8.i.func_175598_ae().field_78734_h == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw d8.a(runtimeException);
        }
        this.func_147906_a((Entity)this.j, this.j.ab(), d10, d11 + (double)this.j.i(), d12, 300);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

