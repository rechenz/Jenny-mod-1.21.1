/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.example.client.model.tile;

import net.minecraft.util.ResourceLocation;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BotariumModel
extends AnimatedGeoModel<BotariumTileEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(BotariumTileEntity entity) {
        return new ResourceLocation("geckolib3", "animations/botarium.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(BotariumTileEntity animatable) {
        return new ResourceLocation("geckolib3", "geo/botarium.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BotariumTileEntity entity) {
        return new ResourceLocation("geckolib3", "textures/block/botarium.png");
    }
}

