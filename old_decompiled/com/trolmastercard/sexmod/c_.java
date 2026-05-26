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

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class c_
extends ModelBase {
    private final ModelRenderer a;
    private final ModelRenderer d;
    private final ModelRenderer e;
    private final ModelRenderer c;
    private final ModelRenderer b;

    public c_() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.a = new ModelRenderer((ModelBase)this);
        this.a.func_78793_a(0.0f, 0.0f, 0.0f);
        this.a.field_78804_l.add(new ModelBox(this.a, 0, 16, -3.0f, 17.0f, -3.0f, 6, 6, 6, 0.0f, true));
        this.d = new ModelRenderer((ModelBase)this);
        this.d.func_78793_a(0.0f, 0.0f, 0.0f);
        this.d.field_78804_l.add(new ModelBox(this.d, 32, 0, 1.3f, 18.0f, -3.5f, 2, 2, 2, 0.0f, true));
        this.e = new ModelRenderer((ModelBase)this);
        this.e.func_78793_a(0.0f, 0.0f, 0.0f);
        this.e.field_78804_l.add(new ModelBox(this.e, 32, 4, -3.3f, 18.0f, -3.5f, 2, 2, 2, 0.0f, true));
        this.c = new ModelRenderer((ModelBase)this);
        this.c.func_78793_a(0.0f, 0.0f, 0.0f);
        this.c.field_78804_l.add(new ModelBox(this.c, 32, 8, -1.0f, 21.0f, -3.5f, 1, 1, 1, 0.0f, true));
        this.b = new ModelRenderer((ModelBase)this);
        this.b.func_78793_a(-0.5f, 0.0f, 0.1f);
        ModelRenderer modelRenderer = new ModelRenderer((ModelBase)this);
        modelRenderer.func_78793_a(2.0f, 20.7406f, 4.0504f);
        this.b.func_78792_a(modelRenderer);
        this.a(modelRenderer, 1.0908f, 0.0f, 0.0f);
        modelRenderer.field_78804_l.add(new ModelBox(modelRenderer, 10, 11, -2.5f, 0.0f, 0.0f, 2, 2, 1, 0.0f, false));
        ModelRenderer modelRenderer2 = new ModelRenderer((ModelBase)this);
        modelRenderer2.func_78793_a(2.0f, 19.9214f, 3.4768f);
        this.b.func_78792_a(modelRenderer2);
        this.a(modelRenderer2, 0.6109f, 0.0f, 0.0f);
        modelRenderer2.field_78804_l.add(new ModelBox(modelRenderer2, 10, 11, -3.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, false));
        ModelRenderer modelRenderer3 = new ModelRenderer((ModelBase)this);
        modelRenderer3.func_78793_a(2.0f, 19.0074f, 3.0643f);
        this.b.func_78792_a(modelRenderer3);
        this.a(modelRenderer3, 0.3491f, 0.0f, 0.0f);
        modelRenderer3.field_78804_l.add(new ModelBox(modelRenderer3, 10, 11, -4.0f, 0.0f, 0.075f, 5, 1, 1, 0.0f, false));
        ModelRenderer modelRenderer4 = new ModelRenderer((ModelBase)this);
        modelRenderer4.func_78793_a(0.0f, 17.925f, 3.5f);
        this.b.func_78792_a(modelRenderer4);
        this.a(modelRenderer4, 0.1309f, 0.0f, 0.0f);
        modelRenderer4.field_78804_l.add(new ModelBox(modelRenderer4, 10, 11, -3.0f, -1.0f, -0.5f, 7, 2, 1, 0.0f, false));
    }

    public void func_78088_a(Entity entity, float f10, float f11, float f12, float f13, float f14, float f15) {
        this.a.func_78785_a(f15);
        this.d.func_78785_a(f15);
        this.e.func_78785_a(f15);
        this.c.func_78785_a(f15);
        this.b.func_78785_a(f15);
    }

    public void a(ModelRenderer modelRenderer, float f10, float f11, float f12) {
        modelRenderer.field_78795_f = f10;
        modelRenderer.field_78796_g = f11;
        modelRenderer.field_78808_h = f12;
    }
}

