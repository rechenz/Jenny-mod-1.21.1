/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockChest
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.ModelRegistryEvent
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickItem
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b3;
import com.trolmastercard.sexmod.fa;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.j;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class hy
extends Item
implements IAnimatable {
    public static final hy b = new hy();
    private final AnimationFactory a = new AnimationFactory(this);

    public hy() {
        this.func_77637_a(CreativeTabs.field_78040_i);
        this.field_77777_bU = 1;
    }

    public static void a() {
        b.setRegistryName("sexmod", "dragon_staff");
        b.func_77655_b("dragon_staff");
        MinecraftForge.EVENT_BUS.register(hy.class);
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer entityPlayer, EnumHand enumHand) {
        return new ActionResult(EnumActionResult.FAIL, (Object)entityPlayer.func_184586_b(enumHand));
    }

    @SubscribeEvent
    public static void a(RegistryEvent.Register<Item> register) {
        register.getRegistry().register((IForgeRegistryEntry)b);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public static void a(ModelRegistryEvent modelRegistryEvent) {
        ModelLoader.setCustomModelResourceLocation((Item)b, (int)0, (ModelResourceLocation)new ModelResourceLocation("sexmod:dragon_staff"));
        b.setTileEntityItemStackRenderer(new fa());
    }

    @Override
    public void registerControllers(AnimationData animationData) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.a;
    }

    public static class a {
        @SubscribeEvent
        public void a(PlayerInteractEvent.RightClickItem rightClickItem) {
            block10: {
                World world = rightClickItem.getWorld();
                try {
                    if (!world.field_72995_K) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.hy$a.a(runtimeException);
                }
                EntityPlayer entityPlayer = rightClickItem.getEntityPlayer();
                try {
                    try {
                        if (entityPlayer.func_184586_b(EnumHand.MAIN_HAND).func_77973_b() == b || entityPlayer.func_184586_b(EnumHand.OFF_HAND).func_77973_b() == b) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.hy$a.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.hy$a.a(runtimeException);
                }
            }
            try {
                if (ff.aY.isEmpty()) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.hy$a.a(runtimeException);
            }
            this.a();
        }

        @SideOnly(value=Side.CLIENT)
        void a() {
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new j());
            ge.b.sendToServer((IMessage)new b3());
        }

        @SubscribeEvent
        public void a(PlayerInteractEvent.RightClickBlock rightClickBlock) {
            block10: {
                EntityPlayer entityPlayer = rightClickBlock.getEntityPlayer();
                try {
                    try {
                        if (entityPlayer.func_184586_b(EnumHand.MAIN_HAND).func_77973_b() == b || entityPlayer.func_184586_b(EnumHand.OFF_HAND).func_77973_b() == b) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.hy$a.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.hy$a.a(runtimeException);
                }
            }
            Block block = rightClickBlock.getWorld().func_180495_p(rightClickBlock.getPos()).func_177230_c();
            try {
                if (block instanceof BlockBed) {
                    rightClickBlock.setCancellationResult(EnumActionResult.FAIL);
                    rightClickBlock.setResult(Event.Result.DENY);
                    rightClickBlock.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.hy$a.a(runtimeException);
            }
            try {
                if (block instanceof BlockChest) {
                    rightClickBlock.setCancellationResult(EnumActionResult.FAIL);
                    rightClickBlock.setResult(Event.Result.DENY);
                    rightClickBlock.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.hy$a.a(runtimeException);
            }
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

