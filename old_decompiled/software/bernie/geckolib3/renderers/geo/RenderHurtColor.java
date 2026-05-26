/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHurtColor
extends RenderLivingBase<EntityLivingBase> {
    private static RenderHurtColor instance;

    public static RenderHurtColor getInstance() {
        if (instance == null) {
            instance = new RenderHurtColor(Minecraft.func_71410_x().func_175598_ae(), null, 0.0f);
        }
        return instance;
    }

    public static boolean set(EntityLivingBase entity, float partialTicks) {
        return RenderHurtColor.getInstance().func_177092_a(entity, partialTicks, true);
    }

    public static void unset() {
        RenderHurtColor.getInstance().func_177091_f();
    }

    public RenderHurtColor(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    protected ResourceLocation getEntityTexture(EntityLivingBase entity) {
        return null;
    }
}

