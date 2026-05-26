/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.g6;
import com.trolmastercard.sexmod.ge;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class aw
extends CommandBase {
    public static final aw a = new aw();

    public String func_71517_b() {
        return "reloadcustommodels";
    }

    public String func_71518_a(ICommandSender iCommandSender) {
        return "/reloadcustommodels";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_184881_a(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] stringArray) throws CommandException {
        br.b(false);
        for (EntityPlayerMP entityPlayerMP : minecraftServer.func_184103_al().func_181057_v()) {
            minecraftServer.func_152344_a(() -> ge.b.sendTo((IMessage)new g6(br.e()), entityPlayerMP));
        }
    }
}

