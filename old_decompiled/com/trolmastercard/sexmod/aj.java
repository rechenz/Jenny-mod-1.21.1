/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraftforge.common.MinecraftForge
 */
package com.trolmastercard.sexmod;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class aj
extends Item
implements IAnimatable {
    public static final aj b = new aj();
    private final AnimationFactory a = new AnimationFactory(this);

    public static void a() {
        b.setRegistryName("sexmod", "winchester");
        b.func_77655_b("winchester");
        MinecraftForge.EVENT_BUS.register(aj.class);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.a;
    }
}

