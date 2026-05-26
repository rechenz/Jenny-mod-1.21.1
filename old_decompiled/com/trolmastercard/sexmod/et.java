/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.IGuiHandler
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.az;
import com.trolmastercard.sexmod.bx;
import com.trolmastercard.sexmod.ca;
import com.trolmastercard.sexmod.d4;
import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.ek;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fb;
import java.io.File;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class et
implements IGuiHandler {
    File b;
    File c;
    boolean a = false;

    public et() {
    }

    public et(boolean bl2) {
        this.a();
    }

    /*
     * Loose catch block
     */
    @SideOnly(value=Side.CLIENT)
    void a() {
        block30: {
            int n2;
            block25: {
                n2 = 2;
                if (n2 != 0) break block25;
                try {
                    for (em em2 : em.ad()) {
                        block24: {
                            block28: {
                                block27: {
                                    block26: {
                                        if (em2.field_70170_p.field_72995_K) continue;
                                        if (em2.func_180425_c().func_177958_n() != 5) continue;
                                        break block26;
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw et.a(concurrentModificationException);
                                        }
                                    }
                                    if (em2.func_180425_c().func_177956_o() != 7) continue;
                                    break block27;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw et.a(concurrentModificationException);
                                    }
                                }
                                if (em2.func_180425_c().func_177952_p() != 5) continue;
                                break block28;
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw et.a(concurrentModificationException);
                                }
                            }
                            try {
                                block29: {
                                    if (!(em2 instanceof eb)) break block24;
                                    break block29;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw et.a(concurrentModificationException);
                                    }
                                }
                                new ca((eb)em2, Minecraft.func_71410_x().field_71439_g.field_71071_by, UUID.randomUUID());
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                        }
                        new d4(em2, Minecraft.func_71410_x().field_71439_g.field_71071_by, UUID.randomUUID());
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    // empty catch block
                }
            }
            if (n2 != 1) break block30;
            try {
                for (em em2 : em.ad()) {
                    block32: {
                        block31: {
                            if (em2.field_70170_p.field_72995_K) continue;
                            if (!(em2 instanceof IInventory)) continue;
                            break block31;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                        }
                        if (em2.func_180425_c().func_177958_n() != 3) continue;
                        break block32;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw et.a(concurrentModificationException);
                        }
                    }
                    try {
                        block33: {
                            if (em2.func_180425_c().func_177956_o() != 1) continue;
                            break block33;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                        }
                        if (em2.func_180425_c().func_177952_p() != 7) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw et.a(concurrentModificationException);
                    }
                    IInventory iInventory = (IInventory)em2;
                    new bx((IInventory)Minecraft.func_71410_x().field_71439_g.field_71071_by, iInventory, (EntityPlayer)Minecraft.func_71410_x().field_71439_g, UUID.randomUUID());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }
    }

    /*
     * Loose catch block
     */
    public Object getServerGuiElement(int n2, EntityPlayer entityPlayer, World world, int n3, int n4, int n5) {
        block27: {
            block23: {
                if (n2 != 0) break block23;
                try {
                    for (em em2 : em.ad()) {
                        block22: {
                            block25: {
                                block24: {
                                    if (em2.field_70170_p.field_72995_K) continue;
                                    if (em2.func_180425_c().func_177958_n() != n3) continue;
                                    break block24;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw et.a(concurrentModificationException);
                                    }
                                }
                                if (em2.func_180425_c().func_177956_o() != n4) continue;
                                break block25;
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw et.a(concurrentModificationException);
                                }
                            }
                            try {
                                block26: {
                                    if (em2.func_180425_c().func_177952_p() != n5) continue;
                                    break block26;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw et.a(concurrentModificationException);
                                    }
                                }
                                if (!(em2 instanceof eb)) break block22;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                            return new ca((eb)em2, entityPlayer.field_71071_by, UUID.randomUUID());
                        }
                        return new d4(em2, entityPlayer.field_71071_by, UUID.randomUUID());
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    // empty catch block
                }
            }
            if (n2 != 1) break block27;
            try {
                for (em em2 : em.ad()) {
                    block29: {
                        block28: {
                            if (em2.field_70170_p.field_72995_K) continue;
                            if (!(em2 instanceof IInventory)) continue;
                            break block28;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                        }
                        if (em2.func_180425_c().func_177958_n() != n3) continue;
                        break block29;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw et.a(concurrentModificationException);
                        }
                    }
                    try {
                        block30: {
                            if (em2.func_180425_c().func_177956_o() != n4) continue;
                            break block30;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                        }
                        if (em2.func_180425_c().func_177952_p() != n5) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw et.a(concurrentModificationException);
                    }
                    IInventory iInventory = (IInventory)em2;
                    return new bx((IInventory)entityPlayer.field_71071_by, iInventory, entityPlayer, UUID.randomUUID());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }
        return null;
    }

    /*
     * Loose catch block
     */
    public Object getClientGuiElement(int n2, EntityPlayer entityPlayer, World world, int n3, int n4, int n5) {
        block27: {
            block23: {
                if (n2 != 0) break block23;
                try {
                    for (em em2 : em.ad()) {
                        block22: {
                            block25: {
                                block24: {
                                    if (!em2.field_70170_p.field_72995_K) continue;
                                    if (em2.func_180425_c().func_177958_n() != n3) continue;
                                    break block24;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw et.a(concurrentModificationException);
                                    }
                                }
                                if (em2.func_180425_c().func_177956_o() != n4) continue;
                                break block25;
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw et.a(concurrentModificationException);
                                }
                            }
                            try {
                                block26: {
                                    if (em2.func_180425_c().func_177952_p() != n5) continue;
                                    break block26;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw et.a(concurrentModificationException);
                                    }
                                }
                                if (!(em2 instanceof eb)) break block22;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                            return new az((eb)em2, entityPlayer.field_71071_by, UUID.randomUUID());
                        }
                        return new fb(em2, entityPlayer.field_71071_by, UUID.randomUUID());
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    // empty catch block
                }
            }
            if (n2 != 1) break block27;
            try {
                for (em em2 : em.ad()) {
                    block29: {
                        block28: {
                            if (!em2.field_70170_p.field_72995_K) continue;
                            if (!(em2 instanceof IInventory)) continue;
                            break block28;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                        }
                        if (em2.func_180425_c().func_177958_n() != n3) continue;
                        break block29;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw et.a(concurrentModificationException);
                        }
                    }
                    try {
                        block30: {
                            if (em2.func_180425_c().func_177956_o() != n4) continue;
                            break block30;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw et.a(concurrentModificationException);
                            }
                        }
                        if (em2.func_180425_c().func_177952_p() != n5) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw et.a(concurrentModificationException);
                    }
                    return new ek(entityPlayer, em2, UUID.randomUUID());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }
        return null;
    }

    private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
        return concurrentModificationException;
    }
}

