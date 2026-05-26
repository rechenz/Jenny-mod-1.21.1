/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.ModelRegistryEvent
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteract
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickBlock
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickEmpty
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class hj
extends Item {
    public static final hj a = new hj();

    public hj() {
        this.func_77637_a(CreativeTabs.field_78040_i);
        this.field_77777_bU = 1;
    }

    public void func_77663_a(ItemStack itemStack, World world, Entity entity, int n2, boolean bl2) {
        try {
            if (world.field_72995_K) {
                this.a(entity, itemStack);
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        super.func_77663_a(itemStack, world, entity, n2, bl2);
    }

    @SideOnly(value=Side.CLIENT)
    void a(Entity entity, ItemStack itemStack) {
        int n2;
        ItemStack itemStack2;
        block13: {
            block12: {
                block11: {
                    try {
                        if (!(entity instanceof EntityPlayer)) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                    EntityPlayer entityPlayer = (EntityPlayer)entity;
                    try {
                        try {
                            if (itemStack.equals(entityPlayer.func_184614_ca()) || itemStack.equals(entityPlayer.func_184592_cb())) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw hj.a(runtimeException);
                        }
                        itemStack.func_77964_b(0);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                }
                RayTraceResult rayTraceResult = Minecraft.func_71410_x().field_71476_x;
                try {
                    try {
                        itemStack2 = itemStack;
                        if (rayTraceResult == null || !em.a(rayTraceResult.field_72308_g)) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                    n2 = 1;
                    break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw hj.a(runtimeException);
                }
            }
            n2 = 0;
        }
        itemStack2.func_77964_b(n2);
    }

    @SubscribeEvent
    public void a(PlayerInteractEvent.EntityInteract entityInteract) {
        Entity entity;
        block23: {
            boolean bl2;
            block25: {
                block24: {
                    entity = entityInteract.getTarget();
                    try {
                        if (!(entity instanceof em)) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                    try {
                        if (!em.a(entity)) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                    EntityPlayer entityPlayer = entityInteract.getEntityPlayer();
                    try {
                        if (entityPlayer == null) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                    ItemStack itemStack = entityPlayer.func_184614_ca();
                    if (itemStack.func_77973_b() != a) {
                        itemStack = entityPlayer.func_184592_cb();
                    }
                    try {
                        if (itemStack.func_77973_b() != a) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                    try {
                        entityInteract.setCanceled(true);
                        if (!entityInteract.getWorld().field_72995_K) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                    try {
                        try {
                            if (!br.d) break block23;
                            if (0 == br.b(true)) break block24;
                        }
                        catch (RuntimeException runtimeException) {
                            throw hj.a(runtimeException);
                        }
                        bl2 = true;
                        break block25;
                    }
                    catch (RuntimeException runtimeException) {
                        throw hj.a(runtimeException);
                    }
                }
                bl2 = false;
            }
            try {
                br.d = bl2;
                if (br.d) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw hj.a(runtimeException);
            }
        }
        com.trolmastercard.sexmod.a.a(((em)entity).E());
    }

    @SubscribeEvent
    public void a(AttackEntityEvent attackEntityEvent) {
        Entity entity = attackEntityEvent.getTarget();
        try {
            if (entity == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        try {
            if (!(entity instanceof em)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        EntityPlayer entityPlayer = attackEntityEvent.getEntityPlayer();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        ItemStack itemStack = entityPlayer.func_184614_ca();
        if (itemStack.func_77973_b() != a) {
            itemStack = entityPlayer.func_184592_cb();
        }
        try {
            if (itemStack.func_77973_b() != a) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        try {
            attackEntityEvent.setCanceled(true);
            if (!entityPlayer.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        em em2 = (em)entity;
        String string = em2.C();
        String string2 = em.c(em.h(em2.f()));
        entityPlayer.func_145747_a((ITextComponent)new TextComponentString(String.format("%s's model-code: %s%s$%s", em2.c(), TextFormatting.YELLOW, string, string2)));
        entityPlayer.func_145747_a((ITextComponent)new TextComponentString(TextFormatting.ITALIC + "copied to clipboard"));
        be.a(String.format("%s$%s", string, string2));
    }

    @SubscribeEvent
    public void a(PlayerInteractEvent.LeftClickBlock leftClickBlock) {
        try {
            if (this.a(leftClickBlock.getEntityPlayer(), leftClickBlock.getWorld())) {
                leftClickBlock.setCanceled(true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
    }

    @SubscribeEvent
    public void a(PlayerInteractEvent.LeftClickEmpty leftClickEmpty) {
        this.a(leftClickEmpty.getEntityPlayer(), leftClickEmpty.getWorld());
    }

    boolean a(EntityPlayer entityPlayer, World world) {
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        ItemStack itemStack = entityPlayer.func_184614_ca();
        if (itemStack.func_77973_b() != a) {
            itemStack = entityPlayer.func_184592_cb();
        }
        try {
            if (itemStack.func_77973_b() != a) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        try {
            if (!world.field_72995_K) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        ei ei2 = ei.d(entityPlayer.getPersistentID());
        try {
            if (ei2 == null) {
                entityPlayer.func_146105_b((ITextComponent)new TextComponentString("you gotta turn into the girl, you want to copy the model-code off"), true);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw hj.a(runtimeException);
        }
        String string = ei2.C();
        String string2 = em.c(em.h(ei2.f()));
        entityPlayer.func_145747_a((ITextComponent)new TextComponentString(String.format("%s's model-code: %s%s$%s", be.b(fy.a((Entity)ei2).toString()), TextFormatting.YELLOW, string, string2)));
        entityPlayer.func_145747_a((ITextComponent)new TextComponentString(TextFormatting.ITALIC + "copied to clipboard"));
        be.a(String.format("%s$%s", string, string2));
        return true;
    }

    public static void a() {
        a.setRegistryName("sexmod", "npc_editor_wand");
        a.func_77655_b("npc_editor_wand");
        MinecraftForge.EVENT_BUS.register(hj.class);
    }

    @SubscribeEvent
    public static void a(RegistryEvent.Register<Item> register) {
        register.getRegistry().register((IForgeRegistryEntry)a);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public static void a(ModelRegistryEvent modelRegistryEvent) {
        ModelLoader.setCustomModelResourceLocation((Item)a, (int)0, (ModelResourceLocation)new ModelResourceLocation("sexmod:npc_editor_wand"));
        ModelLoader.setCustomModelResourceLocation((Item)a, (int)1, (ModelResourceLocation)new ModelResourceLocation("sexmod:npc_editor_wand_active"));
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

