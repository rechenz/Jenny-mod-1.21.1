/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ar;
import com.trolmastercard.sexmod.b8;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ep;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ga {
    static final ResourceLocation b = new ResourceLocation("sexmod", "textures/cummy.png");
    static Minecraft c = Minecraft.func_71410_x();
    static List<ep> a = new ArrayList<ep>();

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(RenderWorldLastEvent renderWorldLastEvent) {
        ga.c.field_71446_o.func_110577_a(b);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferBuilder = tessellator.func_178180_c();
        float f10 = renderWorldLastEvent.getPartialTicks();
        try {
            GlStateManager.func_179140_f();
            GlStateManager.func_179141_d();
            if (ga.c.field_71439_g == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ga.a(runtimeException);
        }
        for (ep ep2 : a) {
            ep2.a(c, tessellator, bufferBuilder, f10);
        }
        GlStateManager.func_179126_j();
        GlStateManager.func_179145_e();
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(TickEvent.ClientTickEvent clientTickEvent) {
        try {
            if (clientTickEvent.phase == TickEvent.Phase.END) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ga.a(runtimeException);
        }
        for (ep ep2 : a) {
            ep2.a();
        }
    }

    public static void a(ep ep2) {
        a.add(ep2);
    }

    public static void a(int n2, ar ar2, b8 b82, em em2, float f10, float f11) {
        a.add(new ep(n2, ar2, b82, em2, f10, f11));
    }

    public static void a(@Nonnull em em2) {
        ArrayList<ep> arrayList = new ArrayList<ep>();
        for (ep ep2 : a) {
            try {
                if (!ep2.e.f().equals(em2.f())) continue;
                arrayList.add(ep2);
            }
            catch (RuntimeException runtimeException) {
                throw ga.a(runtimeException);
            }
        }
        a.removeAll(arrayList);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

