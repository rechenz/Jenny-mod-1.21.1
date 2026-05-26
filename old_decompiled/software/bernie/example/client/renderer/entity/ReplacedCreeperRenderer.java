/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.util.math.MathHelper
 */
package software.bernie.example.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.math.MathHelper;
import software.bernie.example.client.model.entity.ReplacedCreeperModel;
import software.bernie.example.entity.ReplacedCreeperEntity;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;

public class ReplacedCreeperRenderer
extends GeoReplacedEntityRenderer<ReplacedCreeperEntity> {
    public ReplacedCreeperRenderer(RenderManager renderManager) {
        super(renderManager, new ReplacedCreeperModel(), new ReplacedCreeperEntity());
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entitylivingbaseIn, float partialTickTime) {
        EntityCreeper creeper = (EntityCreeper)entitylivingbaseIn;
        float f10 = creeper.func_70831_j(partialTickTime);
        float f12 = 1.0f + MathHelper.func_76126_a((float)(f10 * 100.0f)) * f10 * 0.01f;
        f10 = MathHelper.func_76131_a((float)f10, (float)0.0f, (float)1.0f);
        f10 *= f10;
        f10 *= f10;
        float f22 = (1.0f + f10 * 0.4f) * f12;
        float f32 = (1.0f + f10 * 0.1f) / f12;
        GlStateManager.func_179152_a((float)f22, (float)f32, (float)f22);
    }
}

