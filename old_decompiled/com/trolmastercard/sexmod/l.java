/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class l {
    Vec3d b = null;
    Vec3d a = null;

    /*
     * Loose catch block
     */
    @SubscribeEvent
    public void a(RenderPlayerEvent.Pre pre) {
        try {
            for (em em2 : em.ad()) {
                block10: {
                    if (em2.field_70128_L || em2.ae() == null) continue;
                    try {
                        if (em2.y() == fp.NULL) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw l.a(concurrentModificationException);
                    }
                    EntityPlayer entityPlayer = pre.getEntityPlayer();
                    if (!em2.y().hasPlayer) continue;
                    try {
                        block11: {
                            if (em2.ae().equals(entityPlayer.getPersistentID())) break block10;
                            break block11;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw l.a(concurrentModificationException);
                            }
                        }
                        if (!em2.ae().equals(entityPlayer.func_110124_au())) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw l.a(concurrentModificationException);
                    }
                }
                pre.setCanceled(true);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

    /*
     * Loose catch block
     */
    @SubscribeEvent
    public void a(RenderHandEvent renderHandEvent) {
        EntityPlayerSP entityPlayerSP;
        block14: {
            Minecraft minecraft = Minecraft.func_71410_x();
            entityPlayerSP = minecraft.field_71439_g;
            ei ei2 = ei.g((EntityPlayer)entityPlayerSP);
            if (ei2 == null) break block14;
            try {
                block16: {
                    if (!ei2.Q()) break block14;
                    break block16;
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw l.a(concurrentModificationException);
                    }
                }
                renderHandEvent.setCanceled(true);
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw l.a(concurrentModificationException);
            }
        }
        try {
            for (em em2 : em.ad()) {
                block15: {
                    UUID uUID = em2.ae();
                    fp fp2 = em2.y();
                    if (em2.field_70128_L || uUID == null) continue;
                    try {
                        if (fp2 == null) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw l.a(concurrentModificationException);
                    }
                    if (!fp2.hasPlayer) continue;
                    try {
                        block17: {
                            if (uUID.equals(entityPlayerSP.func_110124_au())) break block15;
                            break block17;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw l.a(concurrentModificationException);
                            }
                        }
                        if (!uUID.equals(entityPlayerSP.getPersistentID())) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw l.a(concurrentModificationException);
                    }
                }
                renderHandEvent.setCanceled(true);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(TickEvent.RenderTickEvent renderTickEvent) {
        Vec3d vec3d;
        Minecraft minecraft;
        block21: {
            block22: {
                minecraft = Minecraft.func_71410_x();
                try {
                    if (minecraft.field_71439_g == null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw l.a(concurrentModificationException);
                }
                try {
                    try {
                        if (renderTickEvent.phase != TickEvent.Phase.END) break block21;
                        if (this.b == null) break block22;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw l.a(concurrentModificationException);
                    }
                    minecraft.field_71439_g.func_70107_b(this.b.field_72450_a, this.b.field_72448_b, this.b.field_72449_c);
                    minecraft.field_71439_g.field_70142_S = this.a.field_72450_a;
                    minecraft.field_71439_g.field_70137_T = this.a.field_72448_b;
                    minecraft.field_71439_g.field_70136_U = this.a.field_72449_c;
                    this.b = null;
                    this.a = null;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw l.a(concurrentModificationException);
                }
            }
            return;
        }
        try {
            if (minecraft.field_71474_y.field_74320_O != 0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw l.a(concurrentModificationException);
        }
        em em2 = em.a(minecraft.field_71439_g.getPersistentID(), false);
        try {
            if (em2 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw l.a(concurrentModificationException);
        }
        try {
            if (!em2.y().useBoyCam) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw l.a(concurrentModificationException);
        }
        try {
            if (em2.m()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw l.a(concurrentModificationException);
        }
        try {
            this.b = minecraft.field_71439_g.func_174791_d();
            this.a = new Vec3d(minecraft.field_71439_g.field_70142_S, minecraft.field_71439_g.field_70137_T, minecraft.field_71439_g.field_70136_U);
            vec3d = em2.Q() ? em2.b("boyCam").func_178787_e(em2.o()) : em2.b("boyCam").func_178787_e(b6.a(new Vec3d(em2.field_70142_S, em2.field_70137_T, em2.field_70136_U), em2.func_174791_d(), (double)renderTickEvent.renderTickTime));
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw l.a(concurrentModificationException);
        }
        Vec3d vec3d2 = vec3d;
        minecraft.field_71439_g.field_70165_t = vec3d2.field_72450_a;
        minecraft.field_71439_g.field_70163_u = vec3d2.field_72448_b - (double)minecraft.field_71439_g.func_70047_e();
        minecraft.field_71439_g.field_70161_v = vec3d2.field_72449_c;
        minecraft.field_71439_g.field_70142_S = vec3d2.field_72450_a;
        minecraft.field_71439_g.field_70137_T = vec3d2.field_72448_b - (double)minecraft.field_71439_g.func_70047_e();
        minecraft.field_71439_g.field_70136_U = vec3d2.field_72449_c;
    }

    private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
        return concurrentModificationException;
    }
}

