/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Enchantments
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraftforge.event.entity.living.LivingDamageEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.r;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class gu {
    public gu() {
        a.a(EntityEquipmentSlot.HEAD, ItemArmor.ArmorMaterial.LEATHER, 1, 0);
        a.a(EntityEquipmentSlot.HEAD, ItemArmor.ArmorMaterial.GOLD, 2, 0);
        a.a(EntityEquipmentSlot.HEAD, ItemArmor.ArmorMaterial.CHAIN, 2, 0);
        a.a(EntityEquipmentSlot.HEAD, ItemArmor.ArmorMaterial.IRON, 2, 0);
        a.a(EntityEquipmentSlot.HEAD, ItemArmor.ArmorMaterial.DIAMOND, 3, 3);
        a.a(EntityEquipmentSlot.CHEST, ItemArmor.ArmorMaterial.LEATHER, 3, 0);
        a.a(EntityEquipmentSlot.CHEST, ItemArmor.ArmorMaterial.GOLD, 5, 0);
        a.a(EntityEquipmentSlot.CHEST, ItemArmor.ArmorMaterial.CHAIN, 5, 0);
        a.a(EntityEquipmentSlot.CHEST, ItemArmor.ArmorMaterial.IRON, 6, 0);
        a.a(EntityEquipmentSlot.CHEST, ItemArmor.ArmorMaterial.DIAMOND, 8, 3);
        a.a(EntityEquipmentSlot.LEGS, ItemArmor.ArmorMaterial.LEATHER, 2, 0);
        a.a(EntityEquipmentSlot.LEGS, ItemArmor.ArmorMaterial.GOLD, 3, 0);
        a.a(EntityEquipmentSlot.LEGS, ItemArmor.ArmorMaterial.CHAIN, 4, 0);
        a.a(EntityEquipmentSlot.LEGS, ItemArmor.ArmorMaterial.IRON, 5, 0);
        a.a(EntityEquipmentSlot.LEGS, ItemArmor.ArmorMaterial.DIAMOND, 6, 3);
        a.a(EntityEquipmentSlot.FEET, ItemArmor.ArmorMaterial.LEATHER, 1, 0);
        a.a(EntityEquipmentSlot.FEET, ItemArmor.ArmorMaterial.GOLD, 1, 0);
        a.a(EntityEquipmentSlot.FEET, ItemArmor.ArmorMaterial.CHAIN, 1, 0);
        a.a(EntityEquipmentSlot.FEET, ItemArmor.ArmorMaterial.IRON, 2, 0);
        a.a(EntityEquipmentSlot.FEET, ItemArmor.ArmorMaterial.DIAMOND, 3, 3);
    }

    @SubscribeEvent
    public void a(LivingDamageEvent livingDamageEvent) {
        float f10;
        block22: {
            try {
                if (!(livingDamageEvent.getEntity() instanceof e2)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw gu.a(runtimeException);
            }
            e2 e22 = (e2)livingDamageEvent.getEntity();
            DamageSource damageSource = new DamageSource[]{e22.Q.getStackInSlot(2), e22.Q.getStackInSlot(3), e22.Q.getStackInSlot(4), e22.Q.getStackInSlot(5)};
            ArrayList<ItemArmor> arrayList = new ArrayList<ItemArmor>();
            ArrayList<ItemStack> arrayList2 = new ArrayList<ItemStack>();
            for (Object object : damageSource) {
                try {
                    if (!(object.func_77973_b() instanceof ItemArmor)) continue;
                    arrayList.add((ItemArmor)object.func_77973_b());
                    arrayList2.add((ItemStack)object);
                }
                catch (RuntimeException runtimeException) {
                    throw gu.a(runtimeException);
                }
            }
            try {
                if (arrayList.size() == 0) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw gu.a(runtimeException);
            }
            DamageSource damageSource2 = livingDamageEvent.getSource();
            int n2 = 0;
            int n3 = 0;
            if (!damageSource2.func_76363_c()) {
                for (ItemArmor itemArmor : arrayList) {
                    n2 += a.a(itemArmor.field_77881_a, itemArmor.func_82812_d());
                    n3 += a.b(itemArmor.field_77881_a, itemArmor.func_82812_d());
                }
            }
            f10 = livingDamageEvent.getAmount();
            f10 *= 1.0f - Math.min(20.0f, Math.max((float)n2 / 5.0f, (float)n2 - 4.0f * f10 / ((float)n3 + 8.0f))) / 25.0f;
            float f11 = 0.0f;
            float f12 = f10;
            for (ItemStack itemStack : arrayList2) {
                int n4;
                float f13;
                float f14;
                int n5 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_180310_c, (ItemStack)itemStack);
                f10 -= (float)n5 * 0.04f * f10;
                int n6 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_92091_k, (ItemStack)itemStack);
                try {
                    f14 = f11;
                    f13 = r.f.nextFloat() < 0.15f * (float)n6 ? r.f.nextFloat() * 4.0f + 1.0f : 0.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw gu.a(runtimeException);
                }
                f11 = f14 + f13;
                f11 = Math.min(4.0f, f11);
                if (damageSource2.func_76347_k()) {
                    n4 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_77329_d, (ItemStack)itemStack);
                    f10 -= (float)n4 * 0.08f * f10;
                }
                if (damageSource2.func_94541_c()) {
                    n4 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_185297_d, (ItemStack)itemStack);
                    f10 -= (float)n4 * 0.08f * f10;
                }
                if (damageSource2.field_76373_n.equals("fall")) {
                    n4 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_180309_e, (ItemStack)itemStack);
                    f10 -= (float)n4 * 0.12f * f10;
                }
                if (!damageSource2.func_76352_a()) continue;
                n4 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_180308_g, (ItemStack)itemStack);
                f10 -= (float)n4 * 0.08f * f10;
            }
            try {
                if (!(f11 > 0.0f) || !(damageSource2 instanceof EntityDamageSource)) break block22;
            }
            catch (RuntimeException runtimeException) {
                throw gu.a(runtimeException);
            }
            EntityDamageSource entityDamageSource = (EntityDamageSource)damageSource2;
            try {
                if (entityDamageSource.func_76346_g() != null) {
                    entityDamageSource.func_76346_g().func_70097_a(DamageSource.func_92087_a((Entity)e22), f11);
                }
            }
            catch (RuntimeException runtimeException) {
                throw gu.a(runtimeException);
            }
        }
        livingDamageEvent.setAmount(f10);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    static class a {
        public static HashMap<String, Integer[]> a = new HashMap();

        a() {
        }

        public static int a(EntityEquipmentSlot entityEquipmentSlot, ItemArmor.ArmorMaterial armorMaterial) {
            try {
                return a.get(entityEquipmentSlot.toString() + armorMaterial.toString())[0];
            }
            catch (NullPointerException nullPointerException) {
                return 3;
            }
        }

        public static int b(EntityEquipmentSlot entityEquipmentSlot, ItemArmor.ArmorMaterial armorMaterial) {
            try {
                return a.get(entityEquipmentSlot.toString() + armorMaterial.toString())[1];
            }
            catch (NullPointerException nullPointerException) {
                return 0;
            }
        }

        public static void a(EntityEquipmentSlot entityEquipmentSlot, ItemArmor.ArmorMaterial armorMaterial, int n2, int n3) {
            a.put(entityEquipmentSlot.toString() + armorMaterial.toString(), new Integer[]{n2, n3});
        }
    }
}

