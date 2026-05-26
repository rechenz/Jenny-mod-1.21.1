/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.em;
import java.util.ConcurrentModificationException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class gn
extends CommandBase {
    public static final gn a = new gn();

    public String func_71517_b() {
        return "locatenearestgoblinlair";
    }

    public String func_71518_a(ICommandSender iCommandSender) {
        return "/locatenearestgoblinlair";
    }

    /*
     * Loose catch block
     */
    public void func_184881_a(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] stringArray) throws CommandException {
        block19: {
            String string;
            StringBuilder stringBuilder;
            TextComponentString textComponentString;
            TextComponentString textComponentString2;
            ICommandSender iCommandSender2;
            block21: {
                block20: {
                    Entity entity;
                    block22: {
                        entity = iCommandSender.func_174793_f();
                        if (entity == null) break block19;
                        if (entity.field_71093_bK == 0) break block19;
                        break block22;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw gn.a(concurrentModificationException);
                        }
                    }
                    try {
                        block23: {
                            TextComponentString textComponentString3;
                            iCommandSender2 = iCommandSender;
                            textComponentString2 = textComponentString3;
                            textComponentString = textComponentString3;
                            stringBuilder = new StringBuilder().append(TextFormatting.YELLOW).append("goblin lairs don't exist in the ");
                            if (entity.field_71093_bK != -1) break block20;
                            break block23;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw gn.a(concurrentModificationException);
                            }
                        }
                        string = TextFormatting.RED + "Nether";
                        break block21;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw gn.a(concurrentModificationException);
                    }
                }
                string = TextFormatting.DARK_PURPLE + "End";
            }
            textComponentString2(stringBuilder.append(string).toString());
            iCommandSender2.func_145747_a((ITextComponent)textComponentString);
            return;
        }
        e3 e32 = null;
        try {
            for (em em2 : em.ad()) {
                try {
                    if (!(em2 instanceof e3)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw gn.a(concurrentModificationException);
                }
                e3 e33 = (e3)em2;
                try {
                    if (!e33.aX) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw gn.a(concurrentModificationException);
                }
                if (e32 == null) {
                    e32 = e33;
                    continue;
                }
                if (!(e33.func_174818_b(iCommandSender.func_180425_c()) < e32.func_174818_b(iCommandSender.func_180425_c()))) continue;
                e32 = e33;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        try {
            if (e32 == null) {
                iCommandSender.func_145747_a((ITextComponent)new TextComponentString(TextFormatting.RED + "No nearby goblin lair found uwu"));
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw gn.a(concurrentModificationException);
        }
        BlockPos blockPos = e32.func_180425_c();
        iCommandSender.func_145747_a((ITextComponent)new TextComponentString(String.format("%sgoblin lair found at %s%s %s%s %s%s", TextFormatting.YELLOW, TextFormatting.RED, blockPos.func_177958_n(), TextFormatting.GREEN, blockPos.func_177956_o(), TextFormatting.BLUE, blockPos.func_177952_p())));
    }

    private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
        return concurrentModificationException;
    }
}

