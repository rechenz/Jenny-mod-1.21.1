/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.item.ItemMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.MapData
 *  net.minecraftforge.client.event.RenderSpecificHandEvent
 *  net.minecraftforge.fml.common.ObfuscationReflectionHelper
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ad;
import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.ei;
import java.io.PrintWriter;
import java.io.StringWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class cn {
    Minecraft f;
    float g = 2.0f;
    boolean c = false;
    private static final ResourceLocation e = new ResourceLocation("textures/map/map_background.png");
    at d;
    ResourceLocation h;
    Vec3i b;
    float a = 0.0f;

    @SubscribeEvent
    public void a(RenderSpecificHandEvent renderSpecificHandEvent) {
        block26: {
            float f10;
            block23: {
                block27: {
                    ItemStack itemStack;
                    ItemRenderer itemRenderer;
                    float f11;
                    float f12;
                    block25: {
                        ei.C();
                        ei ei2 = ei.d(Minecraft.func_71410_x().field_71439_g.getPersistentID());
                        try {
                            if (ei2 == null) {
                                return;
                            }
                        }
                        catch (Exception exception) {
                            throw cn.a(exception);
                        }
                        int n2 = ei2.ah();
                        try {
                            this.d = ei2.a(n2);
                            this.h = new ResourceLocation("sexmod", ei2.c(n2));
                            this.b = ei2.b(n2);
                            if (this.d == null) {
                                System.out.println("HAND IS NULL uwu did you forget to assign this girl a hand owo?");
                                return;
                            }
                        }
                        catch (Exception exception) {
                            throw cn.a(exception);
                        }
                        this.f = Minecraft.func_71410_x();
                        f12 = 0.0f;
                        f11 = 0.0f;
                        try {
                            itemRenderer = this.f.func_175597_ag();
                            if (ad.a()) {
                                f12 = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, (Object)itemRenderer, (String)"prevEquippedProgressMainHand")).floatValue();
                                f11 = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, (Object)itemRenderer, (String)"equippedProgressMainHand")).floatValue();
                            } else {
                                f12 = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, (Object)itemRenderer, (String)"field_187470_g")).floatValue();
                                f11 = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, (Object)itemRenderer, (String)"field_187469_f")).floatValue();
                            }
                            this.g = 2.0f - (f12 + (f11 - f12) * renderSpecificHandEvent.getPartialTicks());
                        }
                        catch (Exception exception) {
                            System.out.println("couldnt do the reflection thingy");
                            StringWriter stringWriter = new StringWriter();
                            exception.printStackTrace(new PrintWriter(stringWriter));
                            Minecraft.func_71410_x().field_71439_g.func_71165_d(stringWriter.toString());
                        }
                        itemRenderer = this.f.field_71439_g;
                        f10 = itemRenderer.func_70678_g(renderSpecificHandEvent.getPartialTicks());
                        itemStack = this.f.field_71439_g.func_184614_ca();
                        try {
                            block24: {
                                try {
                                    try {
                                        GlStateManager.func_179124_c((float)((float)this.b.func_177958_n() / 255.0f), (float)((float)this.b.func_177956_o() / 255.0f), (float)((float)this.b.func_177952_p() / 255.0f));
                                        if (renderSpecificHandEvent.getHand() != EnumHand.MAIN_HAND) break block23;
                                        if (itemStack.func_190926_b()) break block24;
                                    }
                                    catch (Exception exception) {
                                        throw cn.a(exception);
                                    }
                                    if (!(itemStack.func_77973_b() instanceof ItemMap)) break block25;
                                }
                                catch (Exception exception) {
                                    throw cn.a(exception);
                                }
                            }
                            renderSpecificHandEvent.setCanceled(true);
                            this.a(itemStack, renderSpecificHandEvent.getPartialTicks(), (AbstractClientPlayer)itemRenderer, this.g, f10);
                            this.c = true;
                            break block26;
                        }
                        catch (Exception exception) {
                            throw cn.a(exception);
                        }
                    }
                    try {
                        try {
                            if (!(f11 < f12)) break block27;
                            if (!this.c) break block26;
                        }
                        catch (Exception exception) {
                            throw cn.a(exception);
                        }
                        renderSpecificHandEvent.setCanceled(true);
                        this.a(itemStack, renderSpecificHandEvent.getPartialTicks(), (AbstractClientPlayer)itemRenderer, this.g, f10);
                        break block26;
                    }
                    catch (Exception exception) {
                        throw cn.a(exception);
                    }
                }
                this.c = false;
                break block26;
            }
            try {
                if (this.f.field_71439_g.func_184592_cb().func_77973_b() instanceof ItemMap) {
                    renderSpecificHandEvent.setCanceled(true);
                    this.a(EnumHandSide.LEFT, this.g - 1.0f, f10, this.f.field_71439_g.func_184592_cb());
                }
            }
            catch (Exception exception) {
                throw cn.a(exception);
            }
        }
        GlStateManager.func_179117_G();
    }

    void a(ItemStack itemStack, float f10, AbstractClientPlayer abstractClientPlayer, float f11, float f12) {
        block6: {
            block4: {
                block5: {
                    try {
                        try {
                            if (!(itemStack.func_77973_b() instanceof ItemMap)) break block4;
                            if (!abstractClientPlayer.func_184592_cb().func_190926_b()) break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw cn.a(runtimeException);
                        }
                        this.a(itemStack, abstractClientPlayer, f12, f10);
                        break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cn.a(runtimeException);
                    }
                }
                this.a(EnumHandSide.RIGHT, f11 - 1.0f, f12, itemStack);
                break block6;
            }
            this.a(f12, f10);
        }
    }

    void a(EnumHandSide enumHandSide, float f10, float f11, ItemStack itemStack) {
        float f12;
        block6: {
            block8: {
                block7: {
                    float f13;
                    try {
                        f13 = enumHandSide == EnumHandSide.RIGHT ? 1.0f : -1.0f;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cn.a(runtimeException);
                    }
                    f12 = f13;
                    try {
                        try {
                            GlStateManager.func_179109_b((float)(f12 * 0.125f), (float)-0.125f, (float)0.0f);
                            if (this.f.field_71439_g.func_82150_aj()) break block6;
                            GlStateManager.func_179094_E();
                            GlStateManager.func_179114_b((float)(f12 * 10.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                            this.a(f10, f11, enumHandSide);
                            GlStateManager.func_179109_b((float)-0.5f, (float)-1.1f, (float)0.0f);
                            if (enumHandSide != EnumHandSide.RIGHT) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw cn.a(runtimeException);
                        }
                        GlStateManager.func_179109_b((float)0.48f, (float)0.15f, (float)0.0f);
                        break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cn.a(runtimeException);
                    }
                }
                GlStateManager.func_179109_b((float)0.44f, (float)1.3f, (float)1.0f);
            }
            Minecraft.func_71410_x().func_110434_K().func_110577_a(this.h);
            this.d.a().func_78785_a(0.175f);
            GlStateManager.func_179121_F();
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)(f12 * 0.51f), (float)(-0.08f + f10 * -1.2f), (float)-0.75f);
        float f14 = MathHelper.func_76129_c((float)f11);
        float f15 = MathHelper.func_76126_a((float)(f14 * (float)Math.PI));
        float f16 = -0.5f * f15;
        float f17 = 0.4f * MathHelper.func_76126_a((float)(f14 * ((float)Math.PI * 2)));
        float f18 = -0.3f * MathHelper.func_76126_a((float)(f11 * (float)Math.PI));
        GlStateManager.func_179109_b((float)(f12 * f16), (float)(f17 - 0.3f * f15), (float)f18);
        GlStateManager.func_179114_b((float)(f15 * -45.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(f12 * f15 * -30.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        this.a(itemStack);
        GlStateManager.func_179121_F();
    }

    void a(ItemStack itemStack, AbstractClientPlayer abstractClientPlayer, float f10, float f11) {
        float f12 = abstractClientPlayer.field_70127_C + (abstractClientPlayer.field_70125_A - abstractClientPlayer.field_70127_C) * f11;
        float f13 = MathHelper.func_76129_c((float)f10);
        float f14 = -0.2f * MathHelper.func_76126_a((float)(f10 * (float)Math.PI));
        float f15 = -0.4f * MathHelper.func_76126_a((float)(f13 * (float)Math.PI));
        GlStateManager.func_179109_b((float)0.0f, (float)(-f14 / 2.0f), (float)f15);
        float f16 = this.a(f12);
        GlStateManager.func_179109_b((float)0.0f, (float)(0.04f + (this.g - 1.0f) * -1.2f + f16 * -0.5f), (float)-0.72f);
        GlStateManager.func_179114_b((float)(f16 * -85.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179129_p();
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.a(EnumHandSide.RIGHT);
        this.a(EnumHandSide.LEFT);
        GlStateManager.func_179121_F();
        GlStateManager.func_179089_o();
        float f17 = MathHelper.func_76126_a((float)(f13 * (float)Math.PI));
        GlStateManager.func_179114_b((float)(f17 * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)2.0f, (float)2.0f, (float)2.0f);
        this.a(itemStack);
        GlStateManager.func_179145_e();
    }

    void a(ItemStack itemStack) {
        GlStateManager.func_179117_G();
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179152_a((float)0.38f, (float)0.38f, (float)0.38f);
        GlStateManager.func_179140_f();
        this.f.func_110434_K().func_110577_a(e);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferBuilder = tessellator.func_178180_c();
        GlStateManager.func_179109_b((float)-0.5f, (float)-0.5f, (float)0.0f);
        GlStateManager.func_179152_a((float)0.0078125f, (float)0.0078125f, (float)0.0078125f);
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferBuilder.func_181662_b(-7.0, 135.0, 0.0).func_187315_a(0.0, 1.0).func_181675_d();
        bufferBuilder.func_181662_b(135.0, 135.0, 0.0).func_187315_a(1.0, 1.0).func_181675_d();
        bufferBuilder.func_181662_b(135.0, -7.0, 0.0).func_187315_a(1.0, 0.0).func_181675_d();
        bufferBuilder.func_181662_b(-7.0, -7.0, 0.0).func_187315_a(0.0, 0.0).func_181675_d();
        tessellator.func_78381_a();
        MapData mapData = ((ItemMap)itemStack.func_77973_b()).func_77873_a(itemStack, (World)this.f.field_71441_e);
        try {
            if (mapData != null) {
                this.f.field_71460_t.func_147701_i().func_148250_a(mapData, false);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cn.a(runtimeException);
        }
        GlStateManager.func_179124_c((float)((float)this.b.func_177958_n() / 255.0f), (float)((float)this.b.func_177956_o() / 255.0f), (float)((float)this.b.func_177952_p() / 255.0f));
    }

    private void a(EnumHandSide enumHandSide) {
        block5: {
            block4: {
                float f10;
                try {
                    GlStateManager.func_179094_E();
                    f10 = enumHandSide == EnumHandSide.RIGHT ? 1.0f : -1.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw cn.a(runtimeException);
                }
                float f11 = f10;
                try {
                    GlStateManager.func_179114_b((float)92.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179114_b((float)(f11 * -41.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.func_179109_b((float)(f11 * 0.3f), (float)-1.1f, (float)0.45f);
                    if (enumHandSide != EnumHandSide.RIGHT) break block4;
                    GlStateManager.func_179109_b((float)0.63f, (float)0.36f, (float)0.0f);
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw cn.a(runtimeException);
                }
            }
            GlStateManager.func_179109_b((float)1.6f, (float)0.35f, (float)0.0f);
        }
        Minecraft.func_71410_x().func_110434_K().func_110577_a(this.h);
        this.d.a().func_78785_a(0.175f);
        GlStateManager.func_179121_F();
    }

    private float a(float f10) {
        float f11 = 1.0f - f10 / 45.0f + 0.1f;
        f11 = MathHelper.func_76131_a((float)f11, (float)0.0f, (float)1.0f);
        f11 = -MathHelper.func_76134_b((float)(f11 * (float)Math.PI)) * 0.5f + 0.5f;
        return f11;
    }

    void a(float f10, float f11) {
        GlStateManager.func_179129_p();
        GlStateManager.func_179094_E();
        this.a(this.g, f10, EnumHandSide.RIGHT);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(this.h);
        this.d.a().func_78785_a(0.175f);
        GlStateManager.func_179084_k();
        GlStateManager.func_179089_o();
        GlStateManager.func_179121_F();
    }

    private void a(float f10, float f11, EnumHandSide enumHandSide) {
        float f12;
        boolean bl2;
        try {
            bl2 = enumHandSide != EnumHandSide.LEFT;
        }
        catch (RuntimeException runtimeException) {
            throw cn.a(runtimeException);
        }
        boolean bl3 = bl2;
        try {
            f12 = bl3 ? 1.0f : -1.0f;
        }
        catch (RuntimeException runtimeException) {
            throw cn.a(runtimeException);
        }
        float f13 = f12;
        float f14 = MathHelper.func_76129_c((float)f11);
        float f15 = -0.3f * MathHelper.func_76126_a((float)(f14 * (float)Math.PI));
        float f16 = 0.4f * MathHelper.func_76126_a((float)(f14 * ((float)Math.PI * 2)));
        float f17 = -0.4f * MathHelper.func_76126_a((float)(f11 * (float)Math.PI));
        GlStateManager.func_179109_b((float)(f13 * (f15 + 0.64000005f)), (float)(f16 + -0.6f + f10 * -0.6f), (float)(f17 + -0.71999997f));
        GlStateManager.func_179114_b((float)(f13 * 45.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        float f18 = MathHelper.func_76126_a((float)(f11 * f11 * (float)Math.PI));
        float f19 = MathHelper.func_76126_a((float)(f14 * (float)Math.PI));
        GlStateManager.func_179114_b((float)(f13 * f19 * 70.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(f13 * f18 * -20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179109_b((float)(f13 * -1.0f), (float)3.6f, (float)3.5f);
        GlStateManager.func_179114_b((float)(f13 * 120.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)200.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(f13 * -135.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179109_b((float)(f13 * 5.6f), (float)0.0f, (float)0.0f);
        GlStateManager.func_179109_b((float)0.5f, (float)1.1f, (float)0.0f);
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

