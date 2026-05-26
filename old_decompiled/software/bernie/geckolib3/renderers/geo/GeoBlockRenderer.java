/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDirectional
 *  net.minecraft.block.BlockHorizontal
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public abstract class GeoBlockRenderer<T extends TileEntity>
extends TileEntitySpecialRenderer<T>
implements IGeoRenderer<T> {
    private final AnimatedGeoModel<T> modelProvider;

    public GeoBlockRenderer(AnimatedGeoModel<T> modelProvider) {
        this.modelProvider = modelProvider;
    }

    public void func_192841_a(T te, double x2, double y2, double z2, float partialTicks, int destroyStage, float alpha) {
        this.render(te, x2, y2, z2, partialTicks, destroyStage);
    }

    public void render(T tile, double x2, double y2, double z2, float partialTicks, int destroyStage) {
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(tile));
        this.modelProvider.setLivingAnimations(tile, this.getUniqueID(tile));
        int light = tile.func_145831_w().func_175626_b(tile.func_174877_v(), 0);
        int lx = light % 65536;
        int ly = light / 65536;
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        OpenGlHelper.func_77475_a((int)3553, (float)lx, (float)ly);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)x2, (double)y2, (double)z2);
        GlStateManager.func_179109_b((float)0.0f, (float)0.01f, (float)0.0f);
        GlStateManager.func_179137_b((double)0.5, (double)0.0, (double)0.5);
        this.rotateBlock(this.getFacing(tile));
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation(tile));
        Color renderColor = this.getRenderColor(tile, partialTicks);
        this.render(model, tile, partialTicks, (float)renderColor.getRed() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        GlStateManager.func_179121_F();
    }

    @Override
    public AnimatedGeoModel<T> getGeoModelProvider() {
        return this.modelProvider;
    }

    protected void rotateBlock(EnumFacing facing) {
        switch (facing) {
            case SOUTH: {
                GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case WEST: {
                GlStateManager.func_179114_b((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case NORTH: {
                break;
            }
            case EAST: {
                GlStateManager.func_179114_b((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case UP: {
                GlStateManager.func_179114_b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                break;
            }
            case DOWN: {
                GlStateManager.func_179114_b((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            }
        }
    }

    private EnumFacing getFacing(T tile) {
        IBlockState blockState = tile.func_145831_w().func_180495_p(tile.func_174877_v());
        if (blockState.func_177227_a().contains(BlockHorizontal.field_185512_D)) {
            return (EnumFacing)blockState.func_177229_b((IProperty)BlockHorizontal.field_185512_D);
        }
        if (blockState.func_177227_a().contains(BlockDirectional.field_176387_N)) {
            return (EnumFacing)blockState.func_177229_b((IProperty)BlockDirectional.field_176387_N);
        }
        return EnumFacing.NORTH;
    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return this.modelProvider.getTextureLocation(instance);
    }

    static {
        AnimationController.addModelFetcher(object -> {
            TileEntity tile;
            TileEntitySpecialRenderer renderer;
            if (object instanceof TileEntity && (renderer = TileEntityRendererDispatcher.field_147556_a.func_147547_b(tile = (TileEntity)object)) instanceof GeoBlockRenderer) {
                return ((GeoBlockRenderer)renderer).getGeoModelProvider();
            }
            return null;
        });
    }
}

