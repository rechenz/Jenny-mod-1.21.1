/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.IClientCommand
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f_;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;

public class a_
extends CommandBase
implements IClientCommand {
    static final String d = "sexmod/futa";
    static final int a = 10;
    static final float c = 0.025f;
    public static boolean e = true;
    public static final a_ b = new a_();

    public a_() {
        String string = "";
        try {
            string = new BufferedReader(new FileReader(d)).readLine().toLowerCase();
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            if ("".equals(string)) {
                return;
            }
        }
        catch (Exception exception) {
            throw a_.a(exception);
        }
        try {
            if ("true".equals(string)) {
                e = true;
            }
        }
        catch (Exception exception) {
            throw a_.a(exception);
        }
        try {
            if ("false".equals(string)) {
                e = false;
            }
        }
        catch (Exception exception) {
            throw a_.a(exception);
        }
    }

    public String func_71517_b() {
        return "futa";
    }

    public String func_71518_a(ICommandSender iCommandSender) {
        return "/futa <true|false>";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void func_184881_a(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] stringArray) throws CommandException {
        String string;
        block25: {
            block26: {
                block24: {
                    try {
                        if (stringArray.length < 1) {
                            this.a(iCommandSender);
                            return;
                        }
                    }
                    catch (IOException iOException) {
                        throw a_.a(iOException);
                    }
                    string = stringArray[0].toLowerCase();
                    try {
                        if (!"true".equals(string)) break block24;
                        e = true;
                        break block25;
                    }
                    catch (IOException iOException) {
                        throw a_.a(iOException);
                    }
                }
                try {
                    if (!"false".equals(string)) break block26;
                    e = false;
                    break block25;
                }
                catch (IOException iOException) {
                    throw a_.a(iOException);
                }
            }
            this.a(iCommandSender);
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(d);
            fileWriter.write(string);
            fileWriter.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        try {
            for (em em2 : em.ad()) {
                try {
                    if (em2.field_70128_L) {
                        continue;
                    }
                }
                catch (IOException iOException) {
                    throw a_.a(iOException);
                }
                try {
                    if (!em2.field_70170_p.field_72995_K) {
                        continue;
                    }
                }
                catch (IOException iOException) {
                    throw a_.a(iOException);
                }
                try {
                    if (!(em2 instanceof f_)) {
                        continue;
                    }
                }
                catch (IOException iOException) {
                    throw a_.a(iOException);
                }
                Vec3d vec3d = em2.b("cockParticles").func_178787_e(em2.func_174791_d());
                Random random = em2.func_70681_au();
                try {
                    for (int i2 = 0; i2 < 10; ++i2) {
                        em2.field_70170_p.func_175688_a(EnumParticleTypes.DRAGON_BREATH, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, (double)(random.nextFloat() * 0.025f * (float)be.a()), (double)(random.nextFloat() * 0.025f * (float)be.a()), (double)(random.nextFloat() * 0.025f * (float)be.a()), new int[0]);
                    }
                }
                catch (IOException iOException) {
                    throw a_.a(iOException);
                    return;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

    void a(ICommandSender iCommandSender) {
        iCommandSender.func_145747_a((ITextComponent)new TextComponentString(String.format("%sYou can either do %s/futa true %sor %s/futa false", TextFormatting.YELLOW, TextFormatting.GRAY, TextFormatting.YELLOW, TextFormatting.GRAY)));
    }

    public boolean allowUsageWithoutPrefix(ICommandSender iCommandSender, String string) {
        return false;
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

