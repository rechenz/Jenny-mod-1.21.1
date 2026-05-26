/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.dm;
import java.util.HashSet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d0
extends dm {
    public d0(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    @Override
    protected void c() {
        GlStateManager.func_179137_b((double)0.0, (double)-1.0, (double)-0.05);
        GlStateManager.func_179152_a((float)0.65f, (float)0.65f, (float)0.65f);
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
            throw d0.a(runtimeException);
        }
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
                        throw d0.a(runtimeException);
                    }
                    GlStateManager.func_179137_b((double)0.0, (double)-0.1, (double)0.05);
                    GlStateManager.func_179114_b((float)40.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw d0.a(runtimeException);
                }
            }
            try {
                try {
                    if (!bl2 || bl3) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw d0.a(runtimeException);
                }
                GlStateManager.func_179137_b((double)-0.025, (double)-0.1, (double)0.0);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw d0.a(runtimeException);
            }
        }
    }

    @Override
    public HashSet<String> a() {
        return new HashSet<String>(){
            {
                this.add("boobs");
                this.add("booty");
                this.add("vagina");
                this.add("fuckhole");
                this.add("leaf7");
                this.add("leaf8");
            }
        };
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

