/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
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

import com.trolmastercard.sexmod.ax;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
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

public class b9
extends Item {
    public static final b9 a = new b9();

    public b9() {
        this.func_77637_a(CreativeTabs.field_78026_f);
        this.field_77777_bU = 1;
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer entityPlayer, EnumHand enumHand) {
        ItemStack itemStack = entityPlayer.func_184586_b(enumHand);
        Vec3d vec3d = entityPlayer.func_174824_e(0.0f);
        Vec3d vec3d2 = entityPlayer.func_70676_i(0.0f);
        Vec3d vec3d3 = vec3d.func_72441_c(vec3d2.field_72450_a * 5.0, vec3d2.field_72448_b * 5.0, vec3d2.field_72449_c * 5.0);
        RayTraceResult rayTraceResult = world.func_147447_a(vec3d, vec3d3, false, false, true);
        try {
            if (rayTraceResult == null) {
                return new ActionResult(EnumActionResult.FAIL, (Object)entityPlayer.func_184586_b(enumHand));
            }
        }
        catch (RuntimeException runtimeException) {
            throw b9.a(runtimeException);
        }
        try {
            if (rayTraceResult.field_72313_a == RayTraceResult.Type.MISS) {
                return new ActionResult(EnumActionResult.FAIL, (Object)entityPlayer.func_184586_b(enumHand));
            }
        }
        catch (RuntimeException runtimeException) {
            throw b9.a(runtimeException);
        }
        try {
            if (!entityPlayer.field_71075_bZ.field_75098_d) {
                itemStack.func_190918_g(1);
            }
        }
        catch (RuntimeException runtimeException) {
            throw b9.a(runtimeException);
        }
        try {
            if (!world.field_72995_K) {
                ax.a(world, rayTraceResult.field_72307_f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw b9.a(runtimeException);
        }
        return new ActionResult(EnumActionResult.SUCCESS, (Object)entityPlayer.func_184586_b(enumHand));
    }

    public static void a() {
        a.setRegistryName("sexmod", "tribe_egg");
        a.func_77655_b("tribe_egg");
        MinecraftForge.EVENT_BUS.register(b9.class);
    }

    @SubscribeEvent
    public static void a(RegistryEvent.Register<Item> register) {
        register.getRegistry().register((IForgeRegistryEntry)a);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public static void a(ModelRegistryEvent modelRegistryEvent) {
        ModelLoader.setCustomModelResourceLocation((Item)a, (int)0, (ModelResourceLocation)new ModelResourceLocation("sexmod:tribe_egg"));
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

