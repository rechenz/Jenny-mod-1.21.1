/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.example.client.model.tile;

import net.minecraft.util.ResourceLocation;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FertilizerModel
extends AnimatedGeoModel<FertilizerTileEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(FertilizerTileEntity animatable) {
        if (animatable.func_145831_w().func_72896_J()) {
            return new ResourceLocation("geckolib3", "animations/fertilizer.animation.json");
        }
        return new ResourceLocation("geckolib3", "animations/botarium.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(FertilizerTileEntity animatable) {
        if (animatable.func_145831_w().func_72896_J()) {
            return new ResourceLocation("geckolib3", "geo/fertilizer.geo.json");
        }
        return new ResourceLocation("geckolib3", "geo/botarium.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FertilizerTileEntity entity) {
        if (entity.func_145831_w().func_72896_J()) {
            return new ResourceLocation("geckolib3", "textures/block/fertilizer.png");
        }
        return new ResourceLocation("geckolib3", "textures/block/botarium.png");
    }
}

