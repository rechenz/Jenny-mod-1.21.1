/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.example.client.model.item;

import net.minecraft.util.ResourceLocation;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JackInTheBoxModel
extends AnimatedGeoModel<JackInTheBoxItem> {
    @Override
    public ResourceLocation getModelLocation(JackInTheBoxItem object) {
        return new ResourceLocation("geckolib3", "geo/jack.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(JackInTheBoxItem object) {
        return new ResourceLocation("geckolib3", "textures/item/jack.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(JackInTheBoxItem animatable) {
        return new ResourceLocation("geckolib3", "animations/jackinthebox.animation.json");
    }
}

