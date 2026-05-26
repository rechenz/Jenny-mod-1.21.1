/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ai;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.dy;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class am {
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(RenderWorldLastEvent renderWorldLastEvent) {
        Minecraft minecraft = Minecraft.func_71410_x();
        try {
            if (minecraft.field_71474_y.field_74320_O != 0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw am.a(concurrentModificationException);
        }
        UUID uUID = minecraft.field_71439_g.getPersistentID();
        em em2 = null;
        try {
            for (em em3 : em.ad()) {
                try {
                    if (em3 == null) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw am.a(concurrentModificationException);
                }
                try {
                    if (em3.field_70128_L) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw am.a(concurrentModificationException);
                }
                try {
                    if (!em3.field_70170_p.field_72995_K) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw am.a(concurrentModificationException);
                }
                try {
                    if (!(em3 instanceof ai)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw am.a(concurrentModificationException);
                }
                ai ai2 = (ai)((Object)em3);
                if (!uUID.equals(ai2.e())) continue;
                em2 = em3;
                break;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        try {
            if (em2 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw am.a(concurrentModificationException);
        }
        Render render = minecraft.func_175598_ae().func_78713_a(em2);
        try {
            if (render == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw am.a(concurrentModificationException);
        }
        float f10 = minecraft.field_71439_g.field_70177_z;
        dy.N = (float)((double)minecraft.field_71439_g.field_71158_b.field_78902_a * dy.G.field_72450_a);
        dy.N += -(f10 - dy.H) * 3.0f;
        dy.N = b6.a(dy.I, dy.N, 0.1f);
        float f11 = -minecraft.field_71439_g.field_70125_A;
        dy.x = (float)((double)minecraft.field_71439_g.field_71158_b.field_192832_b * dy.G.field_72449_c + (double)((float)minecraft.field_71439_g.field_70181_x) * dy.G.field_72448_b);
        dy.x += -(f11 - dy.t) * 3.0f;
        dy.x = b6.a(dy.E, dy.x, 0.1f);
        dy.a(em2, renderWorldLastEvent.getPartialTicks());
        dy.H = f10;
        dy.I = dy.N;
        dy.t = f11;
        dy.E = dy.x;
        GlStateManager.func_179145_e();
        GlStateManager.func_179126_j();
        GlStateManager.func_179141_d();
    }

    /*
     * Loose catch block
     */
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void b(RenderWorldLastEvent renderWorldLastEvent) {
        Minecraft minecraft = Minecraft.func_71410_x();
        try {
            if (minecraft.field_71439_g == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw am.a(concurrentModificationException);
        }
        UUID uUID = minecraft.field_71439_g.getPersistentID();
        try {
            for (em em2 : em.ad()) {
                float f10;
                double d10;
                double d11;
                double d12;
                em em3;
                RenderManager renderManager;
                block20: {
                    block19: {
                        try {
                            if (!em2.field_70170_p.field_72995_K) {
                                continue;
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw am.a(concurrentModificationException);
                        }
                        try {
                            if (em2.field_70128_L) {
                                continue;
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw am.a(concurrentModificationException);
                        }
                        try {
                            if (!(em2 instanceof ai)) {
                                continue;
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw am.a(concurrentModificationException);
                        }
                        ai ai2 = (ai)((Object)em2);
                        if (em2.y() != fp.START_THROWING) continue;
                        try {
                            block21: {
                                em2.b(true);
                                renderManager = minecraft.func_175598_ae();
                                em3 = em2;
                                d12 = 0.0;
                                d11 = 0.0;
                                d10 = 0.0;
                                if (!uUID.equals(ai2.e())) break block19;
                                break block21;
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw am.a(concurrentModificationException);
                                }
                            }
                            f10 = -420.69f;
                            break block20;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw am.a(concurrentModificationException);
                        }
                    }
                    f10 = 0.0f;
                }
                renderManager.func_188391_a((Entity)em3, d12, d11, d10, f10, minecraft.func_184121_ak(), false);
                em2.b(false);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        GlStateManager.func_179145_e();
        GlStateManager.func_179126_j();
        GlStateManager.func_179141_d();
    }

    /*
     * Loose catch block
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(RenderHandEvent renderHandEvent) {
        Minecraft minecraft = Minecraft.func_71410_x();
        UUID uUID = minecraft.field_71439_g.getPersistentID();
        try {
            for (em em2 : em.ad()) {
                block13: {
                    try {
                        if (!(em2 instanceof ai)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw am.a(concurrentModificationException);
                    }
                    fp fp2 = em2.y();
                    if (fp2 == fp.PICK_UP) break block13;
                    try {
                        if (fp2 != fp.START_THROWING) {
                            continue;
                        }
                        break block13;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw am.a(concurrentModificationException);
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw am.a(concurrentModificationException);
                    }
                }
                ai ai2 = (ai)((Object)em2);
                UUID uUID2 = ai2.e();
                try {
                    if (!uUID.equals(uUID2)) continue;
                    renderHandEvent.setCanceled(true);
                    return;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw am.a(concurrentModificationException);
                    return;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

    /*
     * Loose catch block
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(RenderPlayerEvent.Pre pre) {
        UUID uUID = pre.getEntityPlayer().getPersistentID();
        try {
            for (em em2 : em.ad()) {
                ai ai2;
                block13: {
                    try {
                        if (!(em2 instanceof ai)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw am.a(concurrentModificationException);
                    }
                    ai2 = (ai)((Object)em2);
                    fp fp2 = em2.y();
                    if (fp2 == fp.PICK_UP) break block13;
                    try {
                        if (fp2 != fp.START_THROWING) {
                            continue;
                        }
                        break block13;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw am.a(concurrentModificationException);
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw am.a(concurrentModificationException);
                    }
                }
                try {
                    if (!uUID.equals(ai2.e())) continue;
                    pre.setCanceled(true);
                    return;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw am.a(concurrentModificationException);
                    return;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

    private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
        return concurrentModificationException;
    }
}

