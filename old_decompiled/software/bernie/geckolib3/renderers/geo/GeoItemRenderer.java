/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import java.util.Collections;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public abstract class GeoItemRenderer<T extends Item>
extends TileEntityItemStackRenderer
implements IGeoRenderer<T> {
    protected AnimatedGeoModel<T> modelProvider;
    protected ItemStack currentItemStack;

    public GeoItemRenderer(AnimatedGeoModel<T> modelProvider) {
        this.modelProvider = modelProvider;
    }

    public void setModel(AnimatedGeoModel<T> model) {
        this.modelProvider = model;
    }

    @Override
    public AnimatedGeoModel<T> getGeoModelProvider() {
        return this.modelProvider;
    }

    public void func_192838_a(ItemStack itemStack, float partialTicks) {
        this.render(itemStack.func_77973_b(), itemStack);
    }

    public void render(T animatable, ItemStack itemStack) {
        this.currentItemStack = itemStack;
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(animatable));
        AnimationEvent<IAnimatable> itemEvent = new AnimationEvent<IAnimatable>((IAnimatable)animatable, 0.0f, 0.0f, Minecraft.func_71410_x().func_184121_ak(), false, Collections.singletonList(itemStack));
        this.modelProvider.setLivingAnimations((IAnimatable)animatable, this.getUniqueID(animatable), itemEvent);
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)0.0f, (float)0.01f, (float)0.0f);
        GlStateManager.func_179137_b((double)0.5, (double)0.5, (double)0.5);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation(animatable));
        Color renderColor = this.getRenderColor(animatable, 0.0f);
        this.render(model, animatable, 0.0f, (float)renderColor.getRed() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        GlStateManager.func_179121_F();
    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return this.modelProvider.getTextureLocation(instance);
    }

    @Override
    public Integer getUniqueID(T animatable) {
        return Objects.hash(this.currentItemStack.func_77973_b(), this.currentItemStack.func_190916_E(), this.currentItemStack.func_77942_o() ? this.currentItemStack.func_77978_p().toString() : Integer.valueOf(1));
    }

    static {
        AnimationController.addModelFetcher(object -> {
            Item item;
            TileEntityItemStackRenderer renderer;
            if (object instanceof Item && (renderer = (item = (Item)object).getTileEntityItemStackRenderer()) instanceof GeoItemRenderer) {
                return ((GeoItemRenderer)renderer).getGeoModelProvider();
            }
            return null;
        });
    }
}

