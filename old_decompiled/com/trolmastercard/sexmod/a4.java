/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelSlime
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.Entity
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ay;
import com.trolmastercard.sexmod.bp;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;

public class a4
implements LayerRenderer<ay> {
    private final bp b;
    private final ModelBase a = new ModelSlime(0);

    public a4(bp bp2) {
        this.b = bp2;
    }

    public void a(ay ay2, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        try {
            if (!ay2.func_82150_aj()) {
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.func_179108_z();
                GlStateManager.func_179147_l();
                GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                this.a.func_178686_a(this.b.func_177087_b());
                this.a.func_78088_a((Entity)ay2, f10, f11, f13, f14, f15, f16);
                GlStateManager.func_179084_k();
                GlStateManager.func_179133_A();
            }
        }
        catch (RuntimeException runtimeException) {
            throw a4.a(runtimeException);
        }
    }

    public boolean func_177142_b() {
        return true;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

