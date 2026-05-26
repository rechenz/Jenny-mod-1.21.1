/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.at;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class a0
extends ModelBase
implements at {
    private final ModelRenderer a;

    public a0() {
        this.field_78090_t = 16;
        this.field_78089_u = 16;
        this.a = new ModelRenderer((ModelBase)this);
        this.a.func_78793_a(-5.0f, 2.5f, 0.0f);
        this.a.field_78804_l.add(new ModelBox(this.a, 0, 0, -2.0f, -6.0f, 0.0f, 2, 6, 2, 0.0f, false));
    }

    public void func_78088_a(Entity entity, float f10, float f11, float f12, float f13, float f14, float f15) {
        this.a.func_78785_a(f15);
    }

    public void a(ModelRenderer modelRenderer, float f10, float f11, float f12) {
        modelRenderer.field_78795_f = f10;
        modelRenderer.field_78796_g = f11;
        modelRenderer.field_78808_h = f12;
    }

    @Override
    public ModelRenderer a() {
        return this.a;
    }
}

