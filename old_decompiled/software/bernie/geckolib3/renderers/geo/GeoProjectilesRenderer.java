/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.util.AnimationUtils;

public class GeoProjectilesRenderer<T extends Entity>
extends Render<T>
implements IGeoRenderer<T> {
    private final AnimatedGeoModel<T> modelProvider;

    public GeoProjectilesRenderer(RenderManager renderManager, AnimatedGeoModel<T> modelProvider) {
        super(renderManager);
        this.modelProvider = modelProvider;
    }

    public void func_76986_a(T entity, double x2, double y2, double z2, float entityYaw, float partialTicks) {
        GlStateManager.func_179094_E();
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(entity));
        GlStateManager.func_179114_b((float)(((Entity)entity).field_70126_B + (((Entity)entity).field_70177_z - ((Entity)entity).field_70126_B) * partialTicks - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(((Entity)entity).field_70127_C + (((Entity)entity).field_70125_A - ((Entity)entity).field_70127_C) * partialTicks), (float)0.0f, (float)0.0f, (float)1.0f);
        float lastLimbDistance = 0.0f;
        float limbSwing = 0.0f;
        EntityModelData entityModelData = new EntityModelData();
        AnimationEvent<IAnimatable> predicate = new AnimationEvent<IAnimatable>((IAnimatable)entity, limbSwing, lastLimbDistance, partialTicks, !(lastLimbDistance > -0.15f) || !(lastLimbDistance < 0.15f), Collections.singletonList(entityModelData));
        if (this.modelProvider instanceof IAnimatableModel) {
            this.modelProvider.setLivingAnimations(entity, this.getUniqueID(entity), (AnimationEvent)predicate);
        }
        GlStateManager.func_179094_E();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation(entity));
        Color renderColor = this.getRenderColor(entity, partialTicks);
        if (!entity.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g)) {
            this.render(model, entity, partialTicks, (float)renderColor.getRed() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179121_F();
    }

    @Override
    public GeoModelProvider<T> getGeoModelProvider() {
        return this.modelProvider;
    }

    public ResourceLocation func_110775_a(T instance) {
        return this.getTextureLocation(instance);
    }

    @Override
    public Integer getUniqueID(T animatable) {
        return animatable.func_110124_au().hashCode();
    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return this.modelProvider.getTextureLocation(instance);
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

