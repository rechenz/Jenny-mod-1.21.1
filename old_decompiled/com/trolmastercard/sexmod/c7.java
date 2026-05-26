/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.block.model.ModelBakery
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.ModelRegistryEvent
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.hn;
import com.trolmastercard.sexmod.i;
import java.util.UUID;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class c7
extends Item
implements IAnimatable {
    private final AnimationFactory b = new AnimationFactory(this);
    public static c7 a = new c7();

    public c7() {
        this.func_77625_d(1);
    }

    public static void a() {
        a.setRegistryName("sexmod", "kobold_egg_item");
        a.func_77655_b("kobold_egg_item");
        MinecraftForge.EVENT_BUS.register(c7.class);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.b;
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public static void a(ModelRegistryEvent modelRegistryEvent) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation("sexmod:kobold_egg_item");
        ModelLoader.setCustomMeshDefinition((Item)a, itemStack -> modelResourceLocation);
        ModelBakery.registerItemVariants((Item)a, (ResourceLocation[])new ResourceLocation[]{modelResourceLocation});
        a.setTileEntityItemStackRenderer(new hn());
    }

    @SubscribeEvent
    public static void a(RegistryEvent.Register<Item> register) {
        register.getRegistry().register((IForgeRegistryEntry)a);
    }

    @SubscribeEvent
    public static void a(PlayerInteractEvent.RightClickBlock rightClickBlock) {
        World world = rightClickBlock.getWorld();
        ItemStack itemStack = rightClickBlock.getItemStack();
        Vec3d vec3d = rightClickBlock.getHitVec();
        try {
            if (world.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c7.a(runtimeException);
        }
        try {
            if (itemStack.func_77973_b() != a) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw c7.a(runtimeException);
        }
        i i2 = new i(world);
        i2.func_70107_b(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        i2.func_184212_Q().func_187227_b(i.b, (Object)EyeAndKoboldColor.getColorByWoolId(itemStack.func_77960_j()).toString());
        NBTTagCompound nBTTagCompound = itemStack.func_77978_p();
        try {
            if (nBTTagCompound != null) {
                i2.f = UUID.fromString(nBTTagCompound.func_74779_i("tribeID"));
            }
        }
        catch (RuntimeException runtimeException) {
            throw c7.a(runtimeException);
        }
        world.func_72838_d((Entity)i2);
        itemStack.func_190918_g(1);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

