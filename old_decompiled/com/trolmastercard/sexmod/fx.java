/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.IClientCommand
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fw;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.ge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class fx
extends CommandBase
implements IClientCommand {
    public static final fx a = new fx();

    public boolean allowUsageWithoutPrefix(ICommandSender iCommandSender, String string) {
        return false;
    }

    public String func_71517_b() {
        return "setmodelcode";
    }

    public String func_71518_a(ICommandSender iCommandSender) {
        return "/setmodelcode";
    }

    public boolean func_184882_a(MinecraftServer minecraftServer, ICommandSender iCommandSender) {
        return true;
    }

    public void func_184881_a(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] stringArray) throws CommandException {
        String[] stringArray2;
        Minecraft minecraft = Minecraft.func_71410_x();
        EntityPlayerSP entityPlayerSP = minecraft.field_71439_g;
        String string = "";
        String string2 = "";
        if (stringArray.length > 0) {
            stringArray2 = stringArray[0].split("\\$");
            string = stringArray2[0];
            if (stringArray2.length > 1) {
                string2 = stringArray2[1];
            }
        }
        stringArray2 = Minecraft.func_71410_x().field_71476_x;
        em em2 = this.a((RayTraceResult)stringArray2);
        try {
            if (em2 == null) {
                entityPlayerSP.func_146105_b((ITextComponent)new TextComponentString("You gotta transform into the girl you want to apply the model-code to"), true);
                return;
            }
        }
        catch (CommandException commandException) {
            throw fx.a((Exception)((Object)commandException));
        }
        try {
            if ("".equals(string2)) {
                ge.b.sendToServer((IMessage)new fw(string, em2.f()));
                entityPlayerSP.func_146105_b((ITextComponent)new TextComponentString(this.a(em2)), true);
                return;
            }
        }
        catch (CommandException commandException) {
            throw fx.a((Exception)((Object)commandException));
        }
        ge.b.sendToServer((IMessage)new fw(string, em2.f(), em.c(string2)));
        entityPlayerSP.func_146105_b((ITextComponent)new TextComponentString(this.a(em2)), true);
    }

    String a(em em2) {
        try {
            if (em2 instanceof ei) {
                return TextFormatting.YELLOW + "applied model code to your player-" + be.b(fy.a((Entity)em2).toString());
            }
        }
        catch (RuntimeException runtimeException) {
            throw fx.a(runtimeException);
        }
        return TextFormatting.YELLOW + "applied model code to this " + em2.c();
    }

    @SideOnly(value=Side.CLIENT)
    em a(RayTraceResult rayTraceResult) {
        try {
            if (rayTraceResult == null) {
                return ei.g((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fx.a(runtimeException);
        }
        try {
            if (em.a(rayTraceResult.field_72308_g)) {
                return (em)rayTraceResult.field_72308_g;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fx.a(runtimeException);
        }
        return ei.g((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

