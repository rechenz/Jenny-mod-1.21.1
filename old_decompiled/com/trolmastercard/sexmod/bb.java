/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityMultiPart
 *  net.minecraft.entity.MultiPartEntityPart
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.world.World;

public class bb
extends MultiPartEntityPart {
    public boolean a = false;

    public bb(World world) {
        super(null, "", 0.0f, 0.0f);
    }

    public bb(IEntityMultiPart iEntityMultiPart, String string, float f10, float f11) {
        super(iEntityMultiPart, string, f10, f11);
    }

    public boolean func_70067_L() {
        return this.a;
    }
}

