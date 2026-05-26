/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.d9;
import com.trolmastercard.sexmod.dj;
import com.trolmastercard.sexmod.e4;
import com.trolmastercard.sexmod.e7;
import com.trolmastercard.sexmod.ff;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class de
extends d9 {
    public de(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    @Override
    protected Vec3i a(String string) {
        block10: {
            EntityDataManager entityDataManager = this.j.func_184212_Q();
            EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.valueOf((String)entityDataManager.func_187225_a(ff.N));
            BlockPos blockPos = (BlockPos)entityDataManager.func_187225_a(ff.K);
            try {
                if (dj.t.contains(string)) {
                    return eyeAndKoboldColor.getMainColor();
                }
            }
            catch (RuntimeException runtimeException) {
                throw de.a(runtimeException);
            }
            try {
                if (dj.u.contains(string)) {
                    return eyeAndKoboldColor.getSecondaryColor();
                }
            }
            catch (RuntimeException runtimeException) {
                throw de.a(runtimeException);
            }
            try {
                try {
                    if (!"irisR".equals(string) && !"irisL".equals(string)) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw de.a(runtimeException);
                }
                return blockPos;
            }
            catch (RuntimeException runtimeException) {
                throw de.a(runtimeException);
            }
        }
        return z;
    }

    @Override
    protected Vector4f a(String string, float f10, float f11, float f12) {
        if ("mouth".equals(string)) {
            String[] stringArray = e4.a(this.j);
            int n2 = Integer.parseInt(stringArray[7]);
            try {
                if (n2 == 1) {
                    return new Vector4f(f10, f11, f12, -0.078125f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw de.a(runtimeException);
            }
        }
        return super.a(string, f10, f11, f12);
    }

    @Override
    protected void d() {
        float f10 = 0.25f - ((Float)this.j.func_184212_Q().func_187225_a(e7.aA)).floatValue();
        GlStateManager.func_179152_a((float)(1.0f - f10), (float)(1.0f - f10), (float)(1.0f - f10));
    }

    @Override
    protected void b() {
        float f10 = 0.25f - ((Float)this.j.func_184212_Q().func_187225_a(e7.aA)).floatValue();
        double d10 = 1.0 / (1.0 - (double)f10);
        GlStateManager.func_179139_a((double)d10, (double)d10, (double)d10);
    }

    @Override
    protected void c() {
        GlStateManager.func_179137_b((double)0.0, (double)-0.8f, (double)0.05);
        GlStateManager.func_179139_a((double)0.5, (double)0.5, (double)0.5);
    }

    @Override
    protected void a(boolean bl2, ItemStack itemStack) {
        float f10;
        block9: {
            block10: {
                try {
                    try {
                        super.a(bl2, itemStack);
                        if (itemStack.func_77973_b().func_77661_b(itemStack) != EnumAction.BOW) break block9;
                        if (bl2) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw de.a(runtimeException);
                    }
                    GlStateManager.func_179114_b((float)170.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                }
                catch (RuntimeException runtimeException) {
                    throw de.a(runtimeException);
                }
            }
            try {
                if (bl2) {
                    GlStateManager.func_179109_b((float)0.1f, (float)0.0f, (float)0.0f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw de.a(runtimeException);
            }
            return;
        }
        try {
            f10 = bl2 ? 80.0f : 180.0f;
        }
        catch (RuntimeException runtimeException) {
            throw de.a(runtimeException);
        }
        GlStateManager.func_179114_b((float)f10, (float)1.0f, (float)0.0f, (float)0.0f);
    }

    @Override
    protected void a(boolean bl2, boolean bl3) {
        block8: {
            block9: {
                block6: {
                    block7: {
                        try {
                            try {
                                super.a(bl2, bl3);
                                if (!bl2) break block6;
                                if (!bl3) break block7;
                            }
                            catch (RuntimeException runtimeException) {
                                throw de.a(runtimeException);
                            }
                            GlStateManager.func_179137_b((double)0.06, (double)0.0, (double)-0.13);
                            GlStateManager.func_179114_b((float)60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                            GlStateManager.func_179114_b((float)38.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                            GlStateManager.func_179114_b((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                            break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw de.a(runtimeException);
                        }
                    }
                    GlStateManager.func_179114_b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179137_b((double)0.0, (double)-0.3f, (double)-0.13);
                    break block8;
                }
                try {
                    if (!bl3) break block9;
                    GlStateManager.func_179114_b((float)150.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.func_179137_b((double)0.0, (double)-0.35, (double)0.0);
                    break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw de.a(runtimeException);
                }
            }
            GlStateManager.func_179137_b((double)0.0, (double)-0.1, (double)-0.083f);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

