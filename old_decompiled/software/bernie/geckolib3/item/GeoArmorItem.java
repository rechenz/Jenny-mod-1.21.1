/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package software.bernie.geckolib3.item;

import javax.annotation.Nullable;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public abstract class GeoArmorItem
extends ItemArmor {
    public GeoArmorItem(ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot slot) {
        super(materialIn, renderIndexIn, slot);
    }

    @Nullable
    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        Class<?> clazz = ((Object)((Object)this)).getClass();
        GeoArmorRenderer renderer = GeoArmorRenderer.getRenderer(clazz);
        renderer.setCurrentItem(entityLiving, itemStack, armorSlot);
        renderer.applyEntityStats(_default).applySlot(armorSlot);
        return renderer;
    }

    @Nullable
    @SideOnly(value=Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        Class<?> clazz = ((Object)((Object)this)).getClass();
        GeoArmorRenderer renderer = GeoArmorRenderer.getRenderer(clazz);
        return renderer.getTextureLocation((ItemArmor)stack.func_77973_b()).toString();
    }
}

