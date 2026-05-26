/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityHanging
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EnumPlayerModelParts
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 */
package software.bernie.geckolib3.renderers.geo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.renderers.geo.RenderHurtColor;
import software.bernie.geckolib3.util.AnimationUtils;
import software.bernie.shadowed.eliotlash.mclib.utils.Interpolations;

public abstract class GeoEntityRenderer<T extends EntityLivingBase>
extends Render<T>
implements IGeoRenderer<T> {
    protected final AnimatedGeoModel<T> modelProvider;
    protected final List<GeoLayerRenderer<T>> layerRenderers = Lists.newArrayList();

    public GeoEntityRenderer(RenderManager renderManager, AnimatedGeoModel<T> modelProvider) {
        super(renderManager);
        this.modelProvider = modelProvider;
    }

    public void doRender(T entity, double x2, double y2, double z2, float entityYaw, float partialTicks) {
        Entity leashHolder;
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)x2, (double)y2, (double)z2);
        boolean shouldSit = entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit();
        EntityModelData entityModelData = new EntityModelData();
        entityModelData.isSitting = shouldSit;
        entityModelData.isChild = entity.func_70631_g_();
        float f10 = Interpolations.lerpYaw(((EntityLivingBase)entity).field_70760_ar, ((EntityLivingBase)entity).field_70761_aq, partialTicks);
        float f12 = Interpolations.lerpYaw(((EntityLivingBase)entity).field_70758_at, ((EntityLivingBase)entity).field_70759_as, partialTicks);
        float netHeadYaw = f12 - f10;
        if (shouldSit && entity.func_184187_bx() instanceof EntityLivingBase) {
            EntityLivingBase livingentity = (EntityLivingBase)entity.func_184187_bx();
            f10 = Interpolations.lerpYaw(livingentity.field_70760_ar, livingentity.field_70761_aq, partialTicks);
            netHeadYaw = f12 - f10;
            float f32 = MathHelper.func_76142_g((float)netHeadYaw);
            if (f32 < -85.0f) {
                f32 = -85.0f;
            }
            if (f32 >= 85.0f) {
                f32 = 85.0f;
            }
            f10 = f12 - f32;
            if (f32 * f32 > 2500.0f) {
                f10 += f32 * 0.2f;
            }
            netHeadYaw = f12 - f10;
        }
        float headPitch = Interpolations.lerp(((EntityLivingBase)entity).field_70127_C, ((EntityLivingBase)entity).field_70125_A, partialTicks);
        float f72 = this.handleRotationFloat(entity, partialTicks);
        this.applyRotations(entity, f72, f10, partialTicks);
        float limbSwingAmount = 0.0f;
        float limbSwing = 0.0f;
        if (!shouldSit && entity.func_70089_S()) {
            limbSwingAmount = Interpolations.lerp(((EntityLivingBase)entity).field_184618_aE, ((EntityLivingBase)entity).field_70721_aZ, partialTicks);
            limbSwing = ((EntityLivingBase)entity).field_184619_aG - ((EntityLivingBase)entity).field_70721_aZ * (1.0f - partialTicks);
            if (entity.func_70631_g_()) {
                limbSwing *= 3.0f;
            }
            if (limbSwingAmount > 1.0f) {
                limbSwingAmount = 1.0f;
            }
        }
        entityModelData.headPitch = -headPitch;
        entityModelData.netHeadYaw = -netHeadYaw;
        AnimationEvent<IAnimatable> predicate = new AnimationEvent<IAnimatable>((IAnimatable)entity, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15f) || !(limbSwingAmount < 0.15f), Collections.singletonList(entityModelData));
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(entity));
        if (this.modelProvider instanceof IAnimatableModel) {
            this.modelProvider.setLivingAnimations(entity, this.getUniqueID(entity), (AnimationEvent)predicate);
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)0.0f, (float)0.01f, (float)0.0f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getEntityTexture(entity));
        Color renderColor = this.getRenderColor(entity, partialTicks);
        boolean flag = this.setDoRenderBrightness(entity, partialTicks);
        if (!entity.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g)) {
            this.render(model, entity, partialTicks, (float)renderColor.getRed() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        }
        if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).func_175149_v()) {
            for (GeoLayerRenderer<T> layerRenderer : this.layerRenderers) {
                layerRenderer.render(entity, limbSwing, limbSwingAmount, partialTicks, limbSwing, netHeadYaw, headPitch, renderColor);
            }
        }
        if (entity instanceof EntityLiving && (leashHolder = ((EntityLiving)entity).func_110166_bE()) != null) {
            this.renderLeash((EntityLiving)entity, x2, y2, z2, entityYaw, partialTicks);
        }
        if (flag) {
            RenderHurtColor.unset();
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179121_F();
    }

    public ResourceLocation getEntityTexture(T entity) {
        return this.getTextureLocation(entity);
    }

    @Override
    public GeoModelProvider getGeoModelProvider() {
        return this.modelProvider;
    }

    protected void applyRotations(T entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        String s2;
        if (!entityLiving.func_70608_bn()) {
            GlStateManager.func_179114_b((float)(180.0f - rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (((EntityLivingBase)entityLiving).field_70725_aQ > 0) {
            float f10 = ((float)((EntityLivingBase)entityLiving).field_70725_aQ + partialTicks - 1.0f) / 20.0f * 1.6f;
            if ((f10 = MathHelper.func_76129_c((float)f10)) > 1.0f) {
                f10 = 1.0f;
            }
            GlStateManager.func_179114_b((float)(f10 * this.getDeathMaxRotation(entityLiving)), (float)0.0f, (float)0.0f, (float)1.0f);
        } else if ((entityLiving.func_145818_k_() || entityLiving instanceof EntityPlayer) && ("Dinnerbone".equals(s2 = TextFormatting.func_110646_a((String)entityLiving.func_70005_c_())) || "Grumm".equals(s2)) && (!(entityLiving instanceof EntityPlayer) || ((EntityPlayer)entityLiving).func_175148_a(EnumPlayerModelParts.CAPE))) {
            GlStateManager.func_179137_b((double)0.0, (double)(((EntityLivingBase)entityLiving).field_70131_O + 0.1f), (double)0.0);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
    }

    protected boolean isVisible(T livingEntityIn) {
        return !livingEntityIn.func_82150_aj();
    }

    private static float getFacingAngle(EnumFacing facingIn) {
        switch (facingIn) {
            case SOUTH: {
                return 90.0f;
            }
            case WEST: {
                return 0.0f;
            }
            case NORTH: {
                return 270.0f;
            }
            case EAST: {
                return 180.0f;
            }
        }
        return 0.0f;
    }

    @Override
    public Integer getUniqueID(T animatable) {
        return animatable.func_110124_au().hashCode();
    }

    protected float getDeathMaxRotation(T entityLivingBaseIn) {
        return 90.0f;
    }

    protected float getSwingProgress(T livingBase, float partialTickTime) {
        return livingBase.func_70678_g(partialTickTime);
    }

    protected float handleRotationFloat(T livingBase, float partialTicks) {
        return (float)((EntityLivingBase)livingBase).field_70173_aa + partialTicks;
    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return this.modelProvider.getTextureLocation(instance);
    }

    public final boolean addLayer(GeoLayerRenderer<T> layer) {
        return this.layerRenderers.add(layer);
    }

    protected boolean setDoRenderBrightness(T entityLivingBaseIn, float partialTicks) {
        return RenderHurtColor.set(entityLivingBaseIn, partialTicks);
    }

    protected void renderLeash(EntityLiving entityLivingIn, double x2, double y2, double z2, float entityYaw, float partialTicks) {
        Entity entity = entityLivingIn.func_110166_bE();
        if (entity != null) {
            y2 -= (1.6 - (double)entityLivingIn.field_70131_O) * 0.5;
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            double d02 = this.interpolateValue(entity.field_70126_B, entity.field_70177_z, partialTicks * 0.5f) * 0.01745329238474369;
            double d12 = this.interpolateValue(entity.field_70127_C, entity.field_70125_A, partialTicks * 0.5f) * 0.01745329238474369;
            double d22 = Math.cos(d02);
            double d32 = Math.sin(d02);
            double d42 = Math.sin(d12);
            if (entity instanceof EntityHanging) {
                d22 = 0.0;
                d32 = 0.0;
                d42 = -1.0;
            }
            double d52 = Math.cos(d12);
            double d62 = this.interpolateValue(entity.field_70169_q, entity.field_70165_t, partialTicks) - d22 * 0.7 - d32 * 0.5 * d52;
            double d72 = this.interpolateValue(entity.field_70167_r + (double)entity.func_70047_e() * 0.7, entity.field_70163_u + (double)entity.func_70047_e() * 0.7, partialTicks) - d42 * 0.5 - 0.25;
            double d82 = this.interpolateValue(entity.field_70166_s, entity.field_70161_v, partialTicks) - d32 * 0.7 + d22 * 0.5 * d52;
            double d92 = this.interpolateValue(entityLivingIn.field_70760_ar, entityLivingIn.field_70761_aq, partialTicks) * 0.01745329238474369 + 1.5707963267948966;
            d22 = Math.cos(d92) * (double)entityLivingIn.field_70130_N * 0.4;
            d32 = Math.sin(d92) * (double)entityLivingIn.field_70130_N * 0.4;
            double d10 = this.interpolateValue(entityLivingIn.field_70169_q, entityLivingIn.field_70165_t, partialTicks) + d22;
            double d11 = this.interpolateValue(entityLivingIn.field_70167_r, entityLivingIn.field_70163_u, partialTicks);
            double d122 = this.interpolateValue(entityLivingIn.field_70166_s, entityLivingIn.field_70161_v, partialTicks) + d32;
            x2 += d22;
            z2 += d32;
            double d13 = (float)(d62 - d10);
            double d14 = (float)(d72 - d11);
            double d15 = (float)(d82 - d122);
            GlStateManager.func_179090_x();
            GlStateManager.func_179140_f();
            GlStateManager.func_179129_p();
            bufferbuilder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
            for (int j2 = 0; j2 <= 24; ++j2) {
                float f10 = 0.5f;
                float f12 = 0.4f;
                float f22 = 0.3f;
                if (j2 % 2 == 0) {
                    f10 *= 0.7f;
                    f12 *= 0.7f;
                    f22 *= 0.7f;
                }
                float f32 = (float)j2 / 24.0f;
                bufferbuilder.func_181662_b(x2 + d13 * (double)f32 + 0.0, y2 + d14 * (double)(f32 * f32 + f32) * 0.5 + (double)((24.0f - (float)j2) / 18.0f + 0.125f), z2 + d15 * (double)f32).func_181666_a(f10, f12, f22, 1.0f).func_181675_d();
                bufferbuilder.func_181662_b(x2 + d13 * (double)f32 + 0.025, y2 + d14 * (double)(f32 * f32 + f32) * 0.5 + (double)((24.0f - (float)j2) / 18.0f + 0.125f) + 0.025, z2 + d15 * (double)f32).func_181666_a(f10, f12, f22, 1.0f).func_181675_d();
            }
            tessellator.func_78381_a();
            bufferbuilder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
            for (int k2 = 0; k2 <= 24; ++k2) {
                float f42 = 0.5f;
                float f52 = 0.4f;
                float f62 = 0.3f;
                if (k2 % 2 == 0) {
                    f42 *= 0.7f;
                    f52 *= 0.7f;
                    f62 *= 0.7f;
                }
                float f72 = (float)k2 / 24.0f;
                bufferbuilder.func_181662_b(x2 + d13 * (double)f72 + 0.0, y2 + d14 * (double)(f72 * f72 + f72) * 0.5 + (double)((24.0f - (float)k2) / 18.0f + 0.125f) + 0.025, z2 + d15 * (double)f72).func_181666_a(f42, f52, f62, 1.0f).func_181675_d();
                bufferbuilder.func_181662_b(x2 + d13 * (double)f72 + 0.025, y2 + d14 * (double)(f72 * f72 + f72) * 0.5 + (double)((24.0f - (float)k2) / 18.0f + 0.125f), z2 + d15 * (double)f72 + 0.025).func_181666_a(f42, f52, f62, 1.0f).func_181675_d();
            }
            tessellator.func_78381_a();
            GlStateManager.func_179145_e();
            GlStateManager.func_179098_w();
            GlStateManager.func_179089_o();
        }
    }

    private double interpolateValue(double start, double end, double pct) {
        return start + (end - start) * pct;
    }

    static {
        AnimationController.addModelFetcher(object -> {
            if (object instanceof Entity) {
                return (IAnimatableModel)((Object)AnimationUtils.getGeoModelForEntity((Entity)object));
            }
            return null;
        });
    }
}

