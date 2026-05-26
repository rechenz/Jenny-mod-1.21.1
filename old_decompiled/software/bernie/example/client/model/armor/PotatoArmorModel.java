/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.example.client.model.armor;

import net.minecraft.util.ResourceLocation;
import software.bernie.example.item.PotatoArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PotatoArmorModel
extends AnimatedGeoModel<PotatoArmorItem> {
    @Override
    public ResourceLocation getModelLocation(PotatoArmorItem object) {
        return new ResourceLocation("geckolib3", "geo/potato_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PotatoArmorItem object) {
        return new ResourceLocation("geckolib3", "textures/item/potato_armor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PotatoArmorItem animatable) {
        return new ResourceLocation("geckolib3", "animations/potato_armor.animation.json");
    }
}

