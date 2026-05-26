/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ap;
import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.ee;
import com.trolmastercard.sexmod.eg;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gf;
import com.trolmastercard.sexmod.gz;
import com.trolmastercard.sexmod.h6;
import com.trolmastercard.sexmod.s;
import com.trolmastercard.sexmod.v;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class q {
    static final UUID b = UUID.fromString("b91e6484-8911-4def-ab04-9fa3452fca5f");
    static final UUID a = UUID.fromString("adf20149-2adc-4a9d-9af5-8e9aeda019d6");

    @SubscribeEvent
    public void a(PlayerEvent.PlayerLoggedInEvent playerLoggedInEvent) {
        Object object2;
        EntityPlayerMP entityPlayerMP;
        block19: {
            entityPlayerMP = playerLoggedInEvent.player.field_70170_p.func_73046_m().func_184103_al().func_177451_a(playerLoggedInEvent.player.getPersistentID());
            try {
                try {
                    entityPlayerMP.func_82142_c(false);
                    entityPlayerMP.func_189654_d(false);
                    entityPlayerMP.field_70145_X = false;
                    if (entityPlayerMP.field_71075_bZ.field_75098_d || !entityPlayerMP.field_71075_bZ.field_75100_b) break block19;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw q.a(concurrentModificationException);
                }
                entityPlayerMP.field_71075_bZ.field_75100_b = false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw q.a(concurrentModificationException);
            }
        }
        ge.b.sendTo((IMessage)new gz(true), entityPlayerMP);
        ge.b.sendTo((IMessage)new gf(v.c(entityPlayerMP.getPersistentID())), entityPlayerMP);
        for (Object object2 : entityPlayerMP.field_71071_by.field_70462_a) {
            try {
                try {
                    if (object2.func_77973_b() != ap.b || !object2.func_77942_o()) continue;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw q.a(concurrentModificationException);
                }
                object2.func_77978_p().func_186854_a("user", UUID.randomUUID());
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw q.a(concurrentModificationException);
            }
        }
        UUID uUID = ax.a(entityPlayerMP.getPersistentID());
        if (uUID != null) {
            object2 = ax.d(uUID);
            ge.b.sendTo((IMessage)new h6((HashSet<BlockPos>)object2, true), entityPlayerMP);
        }
        ei.C();
        object2 = ei.d(playerLoggedInEvent.player.getPersistentID());
        World world = FMLCommonHandler.instance().getMinecraftServerInstance().func_130014_f_();
        try {
            this.a(world, (EntityPlayer)entityPlayerMP, (ei)object2);
            if (object2 != null) {
                ((em)object2).a(false);
                ((ei)object2).b(fp.NULL);
                s.a.a((em)object2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw q.a(concurrentModificationException);
        }
        UUID uUID2 = playerLoggedInEvent.player.getPersistentID();
        try {
            if (uUID2.equals(b)) {
                this.a(world, (EntityPlayer)entityPlayerMP, uUID2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw q.a(concurrentModificationException);
        }
        try {
            if (uUID2.equals(a)) {
                this.b(world, (EntityPlayer)entityPlayerMP, uUID2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw q.a(concurrentModificationException);
        }
        f_.c((EntityPlayer)entityPlayerMP);
    }

    void a(World world, EntityPlayer entityPlayer, UUID uUID) {
        eg eg2 = new eg(world, uUID);
        eg2.func_189654_d(true);
        eg2.field_70145_X = true;
        eg2.field_70159_w = 0.0;
        eg2.field_70181_x = 0.0;
        eg2.field_70179_y = 0.0;
        eg2.func_70107_b(entityPlayer.field_70165_t, entityPlayer.field_70163_u + 69.0, entityPlayer.field_70161_v);
        world.func_72838_d((Entity)eg2);
        eg2.B();
    }

    void b(World world, EntityPlayer entityPlayer, UUID uUID) {
        ee ee2 = new ee(world, uUID);
        ee2.func_189654_d(true);
        ee2.field_70145_X = true;
        ee2.field_70159_w = 0.0;
        ee2.field_70181_x = 0.0;
        ee2.field_70179_y = 0.0;
        ee2.func_70107_b(entityPlayer.field_70165_t, entityPlayer.field_70163_u + 69.0, entityPlayer.field_70161_v);
        world.func_72838_d((Entity)ee2);
        ee2.B();
    }

    void a(World world, EntityPlayer entityPlayer, ei ei3) {
        Predicate<ei> predicate = ei2 -> true;
        List list = world.func_175644_a(ei.class, predicate::test);
        for (ei ei4 : list) {
            try {
                if (!ei4.m().equals(entityPlayer.getPersistentID())) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw q.a(concurrentModificationException);
            }
            try {
                try {
                    if (ei3 != null && ei4.func_145782_y() == ei3.func_145782_y()) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw q.a(concurrentModificationException);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw q.a(concurrentModificationException);
            }
            world.func_72900_e((Entity)ei4);
        }
    }

    /*
     * Unable to fully structure code
     */
    @SubscribeEvent
    public void a(PlayerEvent.PlayerLoggedOutEvent var1_1) {
        var2_2 = var1_1.player;
        try {
            for (em var4_5 : em.ad()) {
                block17: {
                    try {
                        if (var4_5 instanceof ei) {
                            ((ei)var4_5).b(var2_2);
                        }
                    }
                    catch (ConcurrentModificationException v0) {
                        throw q.a(v0);
                    }
                    try {
                        if (var4_5.ae() == null) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException v1) {
                        throw q.a(v1);
                    }
                    if (var4_5.ae().equals(var2_2.getPersistentID())) ** GOTO lbl23
                    try {
                        block18: {
                            if (!var4_5.ae().equals(var2_2.func_110124_au())) break block17;
                            break block18;
                            catch (ConcurrentModificationException v2) {
                                throw q.a(v2);
                            }
                        }
                        s.a.a(var4_5);
                        var4_5.a(false);
                        var4_5.b(fp.NULL);
                    }
                    catch (ConcurrentModificationException v3) {
                        throw q.a(v3);
                    }
                }
                if (!(var4_5 instanceof ei)) continue;
                try {
                    block19: {
                        if (!((ei)var4_5).m().equals(var2_2.getPersistentID())) continue;
                        break block19;
                        catch (ConcurrentModificationException v4) {
                            throw q.a(v4);
                        }
                    }
                    if (var4_5.ae() == null) continue;
                }
                catch (ConcurrentModificationException v5) {
                    throw q.a(v5);
                }
                var5_6 = (EntityPlayerMP)var1_1.player.field_70170_p.func_152378_a(var4_5.ae());
                ge.b.sendTo((IMessage)new gz(true), var5_6);
                s.a.a(var5_6);
                var2_2.func_82142_c(false);
                var4_5.e((UUID)null);
            }
        }
        catch (ConcurrentModificationException var3_4) {
            // empty catch block
        }
    }

    private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
        return concurrentModificationException;
    }
}

