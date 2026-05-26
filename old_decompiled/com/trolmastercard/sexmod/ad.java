/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.bs;
import com.trolmastercard.sexmod.de;
import com.trolmastercard.sexmod.dg;
import com.trolmastercard.sexmod.dj;
import com.trolmastercard.sexmod.dy;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.v;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ad {
    static final int a = 60;
    public static float[] b;

    public ad() {
        if (ad.a()) {
            b = new float[60];
        }
    }

    public static boolean a() {
        return (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(ClientChatEvent clientChatEvent) {
        try {
            if (!ad.a()) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ad.a(nullPointerException);
        }
        try {
            if (!"resetcolor".equalsIgnoreCase(clientChatEvent.getMessage())) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ad.a(nullPointerException);
        }
        dj.c();
        de.e();
        dy.c();
        dg.e();
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void c(ClientChatEvent clientChatEvent) {
        float f10;
        int n2;
        try {
            if (!ad.a()) {
                return;
            }
        }
        catch (Exception exception) {
            throw ad.a(exception);
        }
        String string = clientChatEvent.getOriginalMessage();
        String[] stringArray = string.split(" ");
        try {
            if (stringArray.length != 3) {
                return;
            }
        }
        catch (Exception exception) {
            throw ad.a(exception);
        }
        try {
            if (!"set".equalsIgnoreCase(stringArray[0])) {
                return;
            }
        }
        catch (Exception exception) {
            throw ad.a(exception);
        }
        try {
            n2 = Integer.parseInt(stringArray[1]);
            f10 = Float.parseFloat(stringArray[2]);
            if (b.length - 1 < n2) {
                return;
            }
        }
        catch (Exception exception) {
            return;
        }
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(String.format("%sSet dev float N.%s from %s to %s", TextFormatting.GRAY, n2, Float.valueOf(b[n2]), Float.valueOf(f10))));
        ad.b[n2] = f10;
        clientChatEvent.setCanceled(true);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void b(ClientChatEvent clientChatEvent) {
        int n2;
        try {
            if (!ad.a()) {
                return;
            }
        }
        catch (Exception exception) {
            throw ad.a(exception);
        }
        String string = clientChatEvent.getOriginalMessage();
        String[] stringArray = string.split(" ");
        try {
            if (stringArray.length != 2) {
                return;
            }
        }
        catch (Exception exception) {
            throw ad.a(exception);
        }
        try {
            if (!"get".equalsIgnoreCase(stringArray[0])) {
                return;
            }
        }
        catch (Exception exception) {
            throw ad.a(exception);
        }
        try {
            n2 = Integer.parseInt(stringArray[1]);
            if (b.length - 1 < n2) {
                return;
            }
        }
        catch (Exception exception) {
            return;
        }
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(String.format("%sdev float N.%s is %s", TextFormatting.YELLOW, n2, Float.valueOf(b[n2]))));
        clientChatEvent.setCanceled(true);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(LivingHurtEvent livingHurtEvent) {
        boolean bl2;
        try {
            if (!ad.a()) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ad.a(nullPointerException);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        EntityLivingBase entityLivingBase = livingHurtEvent.getEntityLiving();
        try {
            if (!(entityLivingBase instanceof ff)) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ad.a(nullPointerException);
        }
        ff ff2 = (ff)entityLivingBase;
        UUID uUID = ax.a(entityPlayerSP.getPersistentID());
        Collection<bs> collection = ax.p(uUID);
        for (bs object : collection) {
            this.a("task: " + object.d().name());
            this.a("workers involved: ");
            for (ff ff3 : object.c()) {
                this.a(ff3.c() + " " + ff3.f());
            }
        }
        this.a("tribe contains my exact reference: " + ax.n(uUID).contains(ff2));
        this.a("tribe contains my ID: ");
        boolean bl22 = false;
        for (ff ff4 : ax.n(uUID)) {
            if (!ff4.f().equals(ff2.f())) continue;
            bl22 = true;
        }
        boolean bl3 = false;
        for (Map.Entry<UUID, BlockPos> entry : ax.a(uUID, entityPlayerSP.field_70170_p).entrySet()) {
            if (!entry.getKey().equals(ff2.f())) continue;
            bl2 = true;
        }
        this.a("loaded : " + bl22);
        this.a("saved : " + bl2);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void d(ClientChatEvent clientChatEvent) {
        String[] stringArray;
        try {
            if (!ad.a()) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ad.a(nullPointerException);
        }
        String string = clientChatEvent.getOriginalMessage().toLowerCase();
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        try {
            if ("time".equals(string)) {
                entityPlayerSP.func_145747_a((ITextComponent)new TextComponentString(String.valueOf(entityPlayerSP.field_70170_p.func_82737_E())));
            }
        }
        catch (NullPointerException nullPointerException) {
            throw ad.a(nullPointerException);
        }
        if ("girls".equals(string)) {
            stringArray = entityPlayerSP.field_70170_p.func_175644_a(em.class, em2 -> true);
            entityPlayerSP.func_145747_a((ITextComponent)new TextComponentString(String.valueOf(stringArray.size())));
            for (em object : stringArray) {
                System.out.printf("%s at %s %s %s\n", object, object.field_70165_t, object.field_70163_u, object.field_70161_v);
            }
        }
        if ("kobs".equals(string)) {
            stringArray = ax.a(entityPlayerSP.getPersistentID());
            int n2 = ax.h((UUID)stringArray);
            List<ff> list = ax.n((UUID)stringArray);
            for (Object object : list) {
                String string2;
                ad ad2;
                try {
                    this.a(String.format("alive member %s at %s world.isremote? %s isdead %s girlID %s entityID %s", ((ff)object).c(), object.func_180425_c(), ((ff)object).field_70170_p.field_72995_K, ((ff)object).field_70128_L, ((em)object).f(), object.func_145782_y()));
                    ad2 = this;
                    string2 = entityPlayerSP.field_70170_p.func_72872_a(ff.class, new AxisAlignedBB(object.func_180425_c())).isEmpty() ? "couldn't be located" : "appears to actually exist";
                }
                catch (NullPointerException nullPointerException) {
                    throw ad.a(nullPointerException);
                }
                ad2.a(string2);
            }
            HashMap<UUID, BlockPos> nullPointerException = ax.a((UUID)stringArray, entityPlayerSP.field_70170_p);
            for (Map.Entry entry : nullPointerException.entrySet()) {
                this.a(String.format("saved pos of %s at %s", ((UUID)entry.getKey()).toString(), ((BlockPos)entry.getValue()).toString()));
            }
            this.a("total amount members: " + n2);
        }
        if (string.startsWith("setcumtime ")) {
            long l2;
            stringArray = string.split(" ");
            try {
                l2 = Long.parseLong(stringArray[1]);
            }
            catch (NullPointerException nullPointerException) {
                System.out.println("long: " + stringArray[1]);
                nullPointerException.printStackTrace();
                return;
            }
            v.a(entityPlayerSP.getPersistentID(), l2);
            entityPlayerSP.func_145747_a((ITextComponent)new TextComponentString("set to: " + l2));
        }
    }

    @SideOnly(value=Side.CLIENT)
    void a(String string) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(string));
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

