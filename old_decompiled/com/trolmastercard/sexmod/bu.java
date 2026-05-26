/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelElytra
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.ei;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelElytra;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class bu
extends GeoLayerRenderer {
    private static final ResourceLocation b = new ResourceLocation("textures/entity/elytra.png");
    private final ModelElytra a = new ModelElytra();

    public bu(IGeoRenderer iGeoRenderer) {
        super(iGeoRenderer);
    }

    public void render(EntityLivingBase entityLivingBase, float f10, float f11, float f12, float f13, float f14, float f15, Color color) {
        Object object;
        ModelElytra modelElytra;
        Object object2;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        ModelElytra modelElytra2;
        UUID uUID;
        try {
            if (!(entityLivingBase instanceof e2)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw bu.a(runtimeException);
        }
        e2 e22 = (e2)entityLivingBase;
        ItemStack itemStack = (ItemStack)e22.func_184212_Q().func_187225_a(e2.T);
        EntityPlayer entityPlayer = null;
        if (e22 instanceof ei && (uUID = ((ei)e22).m()) != null) {
            entityPlayer = entityLivingBase.field_70170_p.func_152378_a(uUID);
        }
        try {
            if (itemStack.func_77973_b() != Items.field_185160_cR) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw bu.a(runtimeException);
        }
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        Minecraft.func_71410_x().func_175598_ae().field_78724_e.func_110577_a(b);
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)0.125f);
        float f22 = this.a();
        try {
            modelElytra2 = this.a;
            f21 = f10;
            f20 = f11;
            f19 = f13;
            f18 = f14;
            f17 = f15;
            f16 = f22;
            object2 = entityPlayer == null ? entityLivingBase : entityPlayer;
        }
        catch (RuntimeException runtimeException) {
            throw bu.a(runtimeException);
        }
        try {
            modelElytra2.func_78087_a(f21, f20, f19, f18, f17, f16, (Entity)object2);
            modelElytra = this.a;
            object = entityPlayer == null ? entityLivingBase : entityPlayer;
        }
        catch (RuntimeException runtimeException) {
            throw bu.a(runtimeException);
        }
        modelElytra.func_78088_a((Entity)object, f10, f11, f13, f14, f15, f22);
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }

    public float a() {
        GlStateManager.func_179091_B();
        GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
        GlStateManager.func_179109_b((float)0.0f, (float)-1.501f, (float)0.0f);
        return 0.0625f;
    }

    public void func_177141_a(EntityLivingBase entityLivingBase, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
    }

    public boolean func_177142_b() {
        return false;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

