/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.dm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class db
extends dm {
    public db(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    @Override
    protected void c() {
        GlStateManager.func_179109_b((float)0.0f, (float)-1.25f, (float)0.0f);
        GlStateManager.func_179152_a((float)0.8f, (float)0.8f, (float)0.8f);
    }

    @Override
    protected void a(boolean bl2, boolean bl3) {
        block9: {
            block8: {
                try {
                    try {
                        super.a(bl2, bl3);
                        if (bl2 || bl3) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw db.a(runtimeException);
                    }
                    GlStateManager.func_179137_b((double)0.0, (double)-0.1, (double)0.05);
                    GlStateManager.func_179114_b((float)40.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw db.a(runtimeException);
                }
            }
            try {
                try {
                    if (!bl2 || bl3) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw db.a(runtimeException);
                }
                GlStateManager.func_179137_b((double)-0.025, (double)-0.1, (double)0.0);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw db.a(runtimeException);
            }
        }
    }

    @Override
    protected void a(boolean bl2) {
        try {
            super.a(bl2);
            if (bl2) {
                GlStateManager.func_179137_b((double)0.15, (double)0.0, (double)0.0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw db.a(runtimeException);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

