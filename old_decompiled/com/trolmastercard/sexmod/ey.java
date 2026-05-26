/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ey {
    static final int a = 3;

    @SubscribeEvent
    public void a(BlockEvent.BreakEvent breakEvent) {
        Block block = breakEvent.getState().func_177230_c();
        try {
            if (block != Blocks.field_150324_C) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ey.a(runtimeException);
        }
        BlockPos blockPos = breakEvent.getPos();
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)(blockPos.func_177958_n() - 3), (double)(blockPos.func_177956_o() - 3), (double)(blockPos.func_177952_p() - 3), (double)(blockPos.func_177958_n() + 3), (double)(blockPos.func_177956_o() + 3), (double)(blockPos.func_177952_p() + 3));
        List list = breakEvent.getWorld().func_72872_a(em.class, axisAlignedBB);
        boolean bl2 = false;
        for (em em2 : list) {
            try {
                if (em2.field_70128_L || !((Boolean)em2.func_184212_Q().func_187225_a(em.G)).booleanValue()) continue;
            }
            catch (RuntimeException runtimeException) {
                throw ey.a(runtimeException);
            }
            bl2 = true;
            break;
        }
        try {
            if (!bl2) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ey.a(runtimeException);
        }
        breakEvent.getPlayer().func_146105_b((ITextComponent)new TextComponentString("this bed is currently used by a girl.. pls don't disturb okay? ... you are kinda mean rn"), true);
        breakEvent.setCanceled(true);
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void a(PlayerSPPushOutOfBlocksEvent playerSPPushOutOfBlocksEvent) {
        try {
            if (em.d(playerSPPushOutOfBlocksEvent.getEntityPlayer()) != null) {
                playerSPPushOutOfBlocksEvent.setCanceled(true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ey.a(runtimeException);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

