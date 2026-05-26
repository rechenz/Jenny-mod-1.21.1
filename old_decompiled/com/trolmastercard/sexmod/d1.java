/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.d_;
import java.util.HashSet;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d1
extends d_ {
    public d1(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d10) {
        super(renderManager, animatedGeoModel, d10);
    }

    @Override
    public HashSet<String> a() {
        HashSet hashSet = super.a();
        hashSet.add("figure");
        return hashSet;
    }
}

