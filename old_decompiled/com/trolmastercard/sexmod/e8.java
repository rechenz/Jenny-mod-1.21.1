/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 */
package com.trolmastercard.sexmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class e8
extends ModelBase {
    private final ModelRenderer b;
    private final ModelRenderer a = new ModelRenderer((ModelBase)this, "glass");

    public e8() {
        this.a.func_78784_a(0, 0).func_78789_a(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.b = new ModelRenderer((ModelBase)this, "cube");
        this.b.func_78784_a(32, 0).func_78789_a(-4.0f, -4.0f, -4.0f, 8, 8, 8);
    }

    public void func_78088_a(Entity entity, float f10, float f11, float f12, float f13, float f14, float f15) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a((float)2.0f, (float)2.0f, (float)2.0f);
        GlStateManager.func_179114_b((float)f11, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)60.0f, (float)0.7071f, (float)0.0f, (float)0.7071f);
        this.a.func_78785_a(f15);
        GlStateManager.func_179152_a((float)0.875f, (float)0.875f, (float)0.875f);
        GlStateManager.func_179114_b((float)60.0f, (float)0.7071f, (float)0.0f, (float)0.7071f);
        GlStateManager.func_179114_b((float)f11, (float)0.0f, (float)1.0f, (float)0.0f);
        this.a.func_78785_a(f15);
        GlStateManager.func_179152_a((float)0.875f, (float)0.875f, (float)0.875f);
        GlStateManager.func_179114_b((float)60.0f, (float)0.7071f, (float)0.0f, (float)0.7071f);
        GlStateManager.func_179114_b((float)f11, (float)0.0f, (float)1.0f, (float)0.0f);
        this.b.func_78785_a(f15);
        GlStateManager.func_179121_F();
    }
}

