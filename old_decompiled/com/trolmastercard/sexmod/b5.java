/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b_;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.ge;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class b5
extends GuiScreen {
    List<EntityLivingBase> a = new ArrayList<EntityLivingBase>();
    int b = 0;
    static float c = 0.0f;

    public b5(HashMap<fy, String> hashMap) {
        this.field_146297_k = Minecraft.func_71410_x();
        for (fy fy2 : fy.values()) {
            try {
                if (fy2.isNpcOnly) {
                    continue;
                }
            }
            catch (Exception exception) {
                throw b5.a(exception);
            }
            try {
                Constructor<? extends em> constructor = fy2.npcClass.getConstructor(World.class);
                em em2 = constructor.newInstance(this.field_146297_k.field_71441_e);
                em2.b(true);
                this.a.add((EntityLivingBase)em2);
                String string = hashMap.get((Object)fy2);
                if (string == null) continue;
                em2.a(em.c(string));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        this.a.add((EntityLivingBase)this.field_146297_k.field_71439_g);
    }

    public void func_73863_a(int n2, int n3, float f10) {
        super.func_73863_a(n2, n3, f10);
        this.field_146292_n.clear();
        b5.a(this.field_146294_l / 2, this.field_146295_m / 2 + 20, 30, this.a.get(this.b));
        this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 + 30, this.field_146295_m / 2 - 10, 20, 20, ">"));
        this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 - 50, this.field_146295_m / 2 - 10, 20, 20, "<"));
        this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 30, this.field_146295_m / 2 + 30, 60, 20, "pick"));
    }

    protected void func_146284_a(GuiButton guiButton) {
        block13: {
            block12: {
                try {
                    try {
                        if (!">".equals(guiButton.field_146126_j) || ++this.b < this.a.size()) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw b5.a(runtimeException);
                    }
                    this.b = 0;
                }
                catch (RuntimeException runtimeException) {
                    throw b5.a(runtimeException);
                }
            }
            try {
                try {
                    if (!"<".equals(guiButton.field_146126_j) || --this.b >= 0) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw b5.a(runtimeException);
                }
                this.b = this.a.size() - 1;
            }
            catch (RuntimeException runtimeException) {
                throw b5.a(runtimeException);
            }
        }
        if (guiButton.field_146127_k == 0) {
            ge.b.sendToServer((IMessage)new b_(fy.a((Entity)this.a.get(this.b))));
            EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
            try {
                entityPlayerSP.func_71053_j();
                entityPlayerSP.eyeHeight = entityPlayerSP.getDefaultEyeHeight();
                if (!entityPlayerSP.field_71075_bZ.field_75101_c) {
                    entityPlayerSP.field_71075_bZ.field_75101_c = entityPlayerSP.field_71075_bZ.field_75098_d;
                }
            }
            catch (RuntimeException runtimeException) {
                throw b5.a(runtimeException);
            }
        }
    }

    public boolean func_73868_f() {
        return false;
    }

    public static void a(int n2, int n3, int n4, EntityLivingBase entityLivingBase) {
        float f10 = entityLivingBase.field_70761_aq;
        float f11 = entityLivingBase.field_70177_z;
        float f12 = entityLivingBase.field_70125_A;
        float f13 = entityLivingBase.field_70758_at;
        float f14 = entityLivingBase.field_70759_as;
        try {
            if (!(entityLivingBase instanceof EntityPlayer)) {
                entityLivingBase.field_70165_t = 0.0;
                entityLivingBase.field_70163_u = 0.0;
                entityLivingBase.field_70161_v = 0.0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw b5.a(runtimeException);
        }
        entityLivingBase.field_70761_aq = 0.0f;
        entityLivingBase.field_70177_z = 0.0f;
        entityLivingBase.field_70125_A = 0.0f;
        entityLivingBase.field_70758_at = 0.0f;
        entityLivingBase.field_70759_as = 0.0f;
        float f15 = Minecraft.func_175610_ah();
        if (f15 == 0.0f) {
            f15 = 0.1f;
        }
        c += 60.0f / f15;
        GlStateManager.func_179142_g();
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)n2, (float)n3, (float)50.0f);
        GlStateManager.func_179152_a((float)(-n4), (float)n4, (float)n4);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GlStateManager.func_179114_b((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)c, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
        renderManager.func_178631_a(180.0f);
        renderManager.func_178633_a(false);
        renderManager.func_188391_a((Entity)entityLivingBase, 0.0, 0.0, 0.0, 0.0f, 1.2345679f, false);
        renderManager.func_178633_a(true);
        GlStateManager.func_179121_F();
        RenderHelper.func_74518_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179090_x();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        entityLivingBase.field_70761_aq = f10;
        entityLivingBase.field_70177_z = f11;
        entityLivingBase.field_70125_A = f12;
        entityLivingBase.field_70758_at = f13;
        entityLivingBase.field_70759_as = f14;
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

