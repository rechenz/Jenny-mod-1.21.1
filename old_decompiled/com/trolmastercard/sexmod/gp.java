/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.IItemPropertyGetter
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.ModelRegistryEvent
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.gi;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class gp
extends ItemFishingRod {
    public static final gp a = new gp();

    public gp() {
        this.func_77656_e(64);
        this.func_77625_d(1);
        this.func_185043_a(new ResourceLocation("cast"), new IItemPropertyGetter(){

            @SideOnly(value=Side.CLIENT)
            public float func_185085_a(ItemStack itemStack, @Nullable World world, @Nullable EntityLivingBase entityLivingBase) {
                float f10;
                try {
                    if (entityLivingBase == null) {
                        return 0.0f;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gp$a.a(runtimeException);
                }
                try {
                    if (!(entityLivingBase instanceof eb)) {
                        return 0.0f;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gp$a.a(runtimeException);
                }
                try {
                    f10 = (Boolean)entityLivingBase.func_184212_Q().func_187225_a(eb.af) != false ? 1.0f : 0.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.gp$a.a(runtimeException);
                }
                return f10;
            }

            private static RuntimeException a(RuntimeException runtimeException) {
                return runtimeException;
            }
        });
    }

    public static void a() {
        a.setRegistryName("sexmod", "luna_rod");
        a.func_77655_b("luna_rod");
        MinecraftForge.EVENT_BUS.register(gp.class);
    }

    @SubscribeEvent
    public static void a(RegistryEvent.Register<Item> register) {
        register.getRegistry().register((IForgeRegistryEntry)a);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public static void a(ModelRegistryEvent modelRegistryEvent) {
        ModelLoader.setCustomModelResourceLocation((Item)a, (int)0, (ModelResourceLocation)new ModelResourceLocation("fishing_rod"));
    }

    public ActionResult<ItemStack> a(World world, eb eb2, EnumHand enumHand) {
        ItemStack itemStack = eb2.func_184586_b(enumHand);
        if (eb2.av != null) {
            int n2 = eb2.av.c();
            itemStack.func_77972_a(n2, (EntityLivingBase)eb2);
            eb2.func_184609_a(enumHand);
            world.func_184148_a((EntityPlayer)null, eb2.field_70165_t, eb2.field_70163_u, eb2.field_70161_v, SoundEvents.field_193780_J, SoundCategory.NEUTRAL, 1.0f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        } else {
            world.func_184148_a((EntityPlayer)null, eb2.field_70165_t, eb2.field_70163_u, eb2.field_70161_v, SoundEvents.field_187612_G, SoundCategory.NEUTRAL, 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
            if (!world.field_72995_K) {
                gi.b = eb2;
                double d10 = eb2.func_174791_d().func_72438_d(new Vec3d((double)eb2.ai.func_177958_n(), (double)eb2.ai.func_177956_o(), (double)eb2.ai.func_177952_p()));
                gi gi2 = new gi(world, eb2, d10 * eb.ap);
                int n3 = EnchantmentHelper.func_191528_c((ItemStack)itemStack);
                try {
                    if (n3 > 0) {
                        gi2.b(n3);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw gp.a(runtimeException);
                }
                int n4 = EnchantmentHelper.func_191529_b((ItemStack)itemStack);
                try {
                    if (n4 > 0) {
                        gi2.a(n4);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw gp.a(runtimeException);
                }
                world.func_72838_d((Entity)gi2);
            }
            eb2.func_184609_a(enumHand);
        }
        return new ActionResult(EnumActionResult.SUCCESS, (Object)itemStack);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

