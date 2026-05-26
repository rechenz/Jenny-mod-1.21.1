/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainerCreative
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayer$SleepResult
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent
 *  net.minecraftforge.event.entity.living.LivingDamageEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteract
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.event.entity.player.PlayerSleepInBedEvent
 *  net.minecraftforge.event.world.GetCollisionBoxesEvent
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.e5;
import com.trolmastercard.sexmod.e9;
import com.trolmastercard.sexmod.ec;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class fu {
    static final int a = 284453;

    @SubscribeEvent
    public void a(PlayerSleepInBedEvent playerSleepInBedEvent) {
        EntityPlayer entityPlayer = playerSleepInBedEvent.getEntityPlayer();
        ei ei2 = ei.g(entityPlayer);
        try {
            if (ei2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (!entityPlayer.func_70093_af()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        playerSleepInBedEvent.setResult(EntityPlayer.SleepResult.OTHER_PROBLEM);
    }

    @SubscribeEvent
    public void a(GetCollisionBoxesEvent getCollisionBoxesEvent) {
    }

    @SubscribeEvent
    public void a(PlayerInteractEvent.RightClickBlock rightClickBlock) {
        ei ei2 = ei.d(rightClickBlock.getEntityPlayer().getPersistentID());
        BlockPos blockPos = rightClickBlock.getPos();
        World world = rightClickBlock.getEntityPlayer().field_70170_p;
        EntityPlayer entityPlayer = rightClickBlock.getEntityPlayer();
        try {
            if (ei2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (!ei2.v()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (!cj.a(world, blockPos, rightClickBlock.getHitVec(), rightClickBlock.getFace(), entityPlayer)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (((Boolean)ei2.func_184212_Q().func_187225_a(em.G)).booleanValue()) {
                rightClickBlock.setCanceled(true);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (!entityPlayer.func_70093_af()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        try {
            if (world.func_180495_p(blockPos.func_177978_c()).func_177230_c() == Blocks.field_150350_a) {
                arrayList.add(blockPos.func_177978_c());
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (world.func_180495_p(blockPos.func_177974_f()).func_177230_c() == Blocks.field_150350_a) {
                arrayList.add(blockPos.func_177974_f());
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (world.func_180495_p(blockPos.func_177968_d()).func_177230_c() == Blocks.field_150350_a) {
                arrayList.add(blockPos.func_177968_d());
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (world.func_180495_p(blockPos.func_177976_e()).func_177230_c() == Blocks.field_150350_a) {
                arrayList.add(blockPos.func_177976_e());
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        BlockPos blockPos2 = null;
        for (BlockPos blockPos3 : arrayList) {
            double d10;
            if (blockPos2 == null) {
                blockPos2 = blockPos3;
                continue;
            }
            Vec3d vec3d = entityPlayer.func_174791_d();
            double d11 = this.a(blockPos3.func_177958_n(), blockPos3.func_177956_o(), blockPos3.func_177952_p(), vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
            if (!(d11 < (d10 = this.a(blockPos2.func_177958_n(), blockPos2.func_177956_o(), blockPos2.func_177952_p(), vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c)))) continue;
            blockPos2 = blockPos3;
        }
        try {
            if (blockPos2 == null) {
                entityPlayer.func_145747_a((ITextComponent)new TextComponentString("Bed is obscured"));
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            entityPlayer.func_70107_b((double)blockPos2.func_177958_n() + 0.5, (double)blockPos2.func_177956_o(), (double)blockPos2.func_177952_p() + 0.5);
            if (blockPos.func_177978_c().equals((Object)blockPos2)) {
                entityPlayer.field_70177_z = 0.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (blockPos.func_177974_f().equals((Object)blockPos2)) {
                entityPlayer.field_70177_z = 90.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (blockPos.func_177968_d().equals((Object)blockPos2)) {
                entityPlayer.field_70177_z = 180.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (blockPos.func_177976_e().equals((Object)blockPos2)) {
                entityPlayer.field_70177_z = -90.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (rightClickBlock.getWorld().field_72995_K) {
                d3.a(false);
                ei2.H();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        ei2.c(new Vec3d((double)blockPos2.func_177958_n() + 0.5, (double)((float)blockPos2.func_177956_o() + 0.0f), (double)blockPos2.func_177952_p() + 0.5));
        ei2.b(entityPlayer.field_70177_z);
        ei2.func_184212_Q().func_187227_b(em.G, (Object)true);
        ei2.u();
    }

    double a(double d10, double d11, double d12, double d13, double d14, double d15) {
        double d16 = d10 - d13;
        double d17 = d11 - d14;
        double d18 = d12 - d15;
        return Math.sqrt(d16 * d16 + d17 * d17 + d18 * d18);
    }

    @SubscribeEvent
    public void a(PlayerEvent.PlayerRespawnEvent playerRespawnEvent) {
        EntityPlayer entityPlayer = playerRespawnEvent.player;
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        ei ei2 = ei.a(entityPlayer.getPersistentID());
        try {
            if (ei2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        Vec3d vec3d = entityPlayer.func_174791_d();
        ei2.field_71093_bK = entityPlayer.field_71093_bK;
        ei2.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        ei2.func_70619_bc();
        System.out.println(entityPlayer.field_70170_p.func_175697_a(ei2.func_180425_c(), 2));
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void b(PlayerInteractEvent.EntityInteract entityInteract) {
        try {
            if (!(entityInteract.getTarget() instanceof EntityPlayer)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (entityInteract.getEntityPlayer().func_70093_af()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (!entityInteract.getEntityPlayer().getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        ei ei2 = ei.d(entityPlayerSP.getPersistentID());
        EntityPlayer entityPlayer = (EntityPlayer)entityInteract.getTarget();
        ei ei3 = ei.g(entityPlayer);
        try {
            if (ei3 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (ei2 != null) {
                entityPlayerSP.func_146105_b((ITextComponent)new TextComponentString("no lesbo yet owo"), true);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (!ei3.l()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (ei3.p()) {
                ei3.b((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(PlayerInteractEvent.EntityInteract entityInteract) {
        try {
            if (!(entityInteract.getTarget() instanceof EntityPlayer)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (!entityInteract.getEntityPlayer().getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        ei ei2 = ei.d(entityPlayerSP.getPersistentID());
        try {
            if (ei2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        EntityPlayer entityPlayer = (EntityPlayer)entityInteract.getTarget();
        ei ei3 = ei.d(entityPlayer.getPersistentID());
        try {
            if (ei3 != null) {
                entityPlayer.func_146105_b((ITextComponent)new TextComponentString("no lesbo yet owo"), true);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (ei2.p()) {
                ei2.ab = false;
                ei2.b(entityPlayer);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
    }

    @SubscribeEvent
    public void b(PlayerInteractEvent.RightClickBlock rightClickBlock) {
        block28: {
            EntityPlayer entityPlayer = rightClickBlock.getEntityPlayer();
            ei ei2 = ei.g(entityPlayer);
            try {
                if (ei2 == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            try {
                if (!(ei2 instanceof ec)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            try {
                if (!entityPlayer.func_70093_af()) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            try {
                if (!entityPlayer.func_184614_ca().equals(ItemStack.field_190927_a)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            try {
                if (((Boolean)ei2.func_184212_Q().func_187225_a(em.G)).booleanValue()) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            try {
                if (entityPlayer.field_70125_A < 20.0f) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            Vec3d vec3d = rightClickBlock.getHitVec();
            try {
                if (vec3d == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            Vec3d vec3d2 = new Vec3d(vec3d.field_72450_a, Math.floor(vec3d.field_72448_b) + 0.0, vec3d.field_72449_c);
            try {
                if (vec3d.func_72438_d(entityPlayer.func_174791_d()) > 3.0) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            try {
                try {
                    entityPlayer.func_70107_b(vec3d2.field_72450_a, Math.floor(vec3d.field_72448_b), vec3d2.field_72449_c);
                    ei2.c(vec3d2);
                    ei2.b(entityPlayer.field_70177_z);
                    ei2.func_184212_Q().func_187227_b(em.G, (Object)true);
                    ei2.func_184212_Q().func_187227_b(em.D, (Object)0);
                    ei2.b(fp.STARTDOGGY);
                    if (!rightClickBlock.getWorld().field_72995_K || !Minecraft.func_71410_x().field_71439_g.getPersistentID().equals(entityPlayer.getPersistentID())) break block28;
                }
                catch (RuntimeException runtimeException) {
                    throw fu.a(runtimeException);
                }
                d3.a(false);
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
        }
    }

    @SubscribeEvent
    public void a(LivingHurtEvent livingHurtEvent) {
        block13: {
            try {
                if (!(livingHurtEvent.getEntityLiving() instanceof EntityPlayer)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            try {
                if (livingHurtEvent.getSource() != DamageSource.field_76379_h) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            EntityPlayer entityPlayer = (EntityPlayer)livingHurtEvent.getEntityLiving();
            ei ei2 = ei.g(entityPlayer);
            try {
                if (ei2 == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
            try {
                try {
                    if (!(ei2 instanceof e5) && !(ei2 instanceof e9)) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw fu.a(runtimeException);
                }
                livingHurtEvent.setCanceled(true);
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(GuiScreenEvent.InitGuiEvent initGuiEvent) {
        String string;
        GuiScreen guiScreen;
        block15: {
            guiScreen = initGuiEvent.getGui();
            try {
                try {
                    if (guiScreen instanceof GuiInventory || guiScreen instanceof GuiContainerCreative) break block15;
                }
                catch (RuntimeException runtimeException) {
                    throw fu.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
        }
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        try {
            if (entityPlayerSP == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        ei ei2 = ei.g((EntityPlayer)entityPlayerSP);
        try {
            if (ei2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (ei2.A()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        List list = initGuiEvent.getButtonList();
        try {
            string = ei2.ah() == 0 ? "action.names.dressup" : "action.names.strip";
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        String string2 = I18n.func_135052_a((String)string, (Object[])new Object[0]);
        list.add(new GuiButton(284453, (int)((double)guiScreen.field_146294_l * 0.5 - 35.0), (int)((double)guiScreen.field_146295_m * 0.87), 70, 20, string2));
        initGuiEvent.setButtonList(list);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(GuiScreenEvent.ActionPerformedEvent actionPerformedEvent) {
        block19: {
            GuiScreen guiScreen = actionPerformedEvent.getGui();
            try {
                try {
                    if (guiScreen instanceof GuiInventory || guiScreen instanceof GuiContainerCreative) break block19;
                }
                catch (RuntimeException runtimeException) {
                    throw fu.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw fu.a(runtimeException);
            }
        }
        try {
            if (actionPerformedEvent.getButton().field_146127_k != 284453) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        Minecraft minecraft = Minecraft.func_71410_x();
        ei ei2 = ei.d(minecraft.field_71439_g.getPersistentID());
        try {
            if (ei2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (ei2.A()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (ei2.ae() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (ei2.y() != fp.NULL) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        minecraft.field_71474_y.field_74320_O = 2;
        minecraft.field_71460_t.func_175066_a(null);
        ei2.b(fp.STRIP);
        d3.a(false);
        minecraft.field_71439_g.func_71053_j();
    }

    @SubscribeEvent
    public void a(LivingDamageEvent livingDamageEvent) {
        try {
            if (livingDamageEvent.getSource() != DamageSource.field_76379_h) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        EntityLivingBase entityLivingBase = livingDamageEvent.getEntityLiving();
        try {
            if (!(entityLivingBase instanceof EntityPlayer)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        ei ei2 = ei.d(entityLivingBase.getPersistentID());
        try {
            if (ei2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
        try {
            if (ei2 instanceof ec) {
                livingDamageEvent.setResult(Event.Result.DENY);
                livingDamageEvent.setAmount(0.0f);
                livingDamageEvent.setCanceled(true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fu.a(runtimeException);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

