package com.schnurritv.sexmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.entity.kobold.KoboldEggEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * Renderer for KoboldEggEntity.
 * Uses GeckoLib to render the egg geo model with proper color spots.
 */
public class KoboldEggRenderer extends GeoEntityRenderer<KoboldEggEntity> {

    // Hardcoded path references since KoboldEggEntity doesn't use the standard SexEntity path convention
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(Main.MODID, "geo/kobold/koboldegg.geo.json");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/kobold/koboldegg.png");
    private static final ResourceLocation ANIM = ResourceLocation.fromNamespaceAndPath(Main.MODID, "animations/kobold/egg.animation.json");

    public KoboldEggRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeoModel<>() {
            @Override
            public ResourceLocation getModelResource(KoboldEggEntity animatable) {
                return GEO;
            }

            @Override
            public ResourceLocation getTextureResource(KoboldEggEntity animatable) {
                return TEXTURE;
            }

            @Override
            public ResourceLocation getAnimationResource(KoboldEggEntity animatable) {
                return ANIM;
            }
        });
    }

    @Override
    public RenderType getRenderType(KoboldEggEntity animatable, ResourceLocation texture,
                                     MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutoutNoCull(texture);
    }

    @Override
    public void preRender(PoseStack poseStack, KoboldEggEntity animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer,
                          boolean isReversed, float partialTick, int packedLight,
                          int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer,
                isReversed, partialTick, packedLight, packedOverlay, colour);
    }
}
