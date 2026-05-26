/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.DimensionType
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.cj;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class al
extends EntityLiving {
    public static final long b = 60000L;
    public static final float g = 3.0f;
    static final float c = 30.0f;
    static final int h = 175;
    static final int i = 10;
    BlockPos f = null;
    int d = 0;
    boolean e = false;
    public int a = -1;

    public al(World world) {
        super(world);
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        this.a();
    }

    void a() {
        block27: {
            block26: {
                block23: {
                    int n2;
                    int n3;
                    int n4;
                    block25: {
                        block24: {
                            block21: {
                                try {
                                    if (this.e) {
                                        this.func_70661_as().func_75499_g();
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw al.a(runtimeException);
                                }
                                EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 15.0);
                                try {
                                    try {
                                        if (entityPlayer == null || !(entityPlayer.func_70032_d((Entity)this) < 3.0f)) break block21;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw al.a(runtimeException);
                                    }
                                    this.func_70661_as().func_75499_g();
                                    return;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw al.a(runtimeException);
                                }
                            }
                            try {
                                try {
                                    block22: {
                                        try {
                                            try {
                                                if (this.f == null || this.func_70011_f(this.f.func_177958_n(), this.f.func_177956_o(), this.f.func_177952_p()) > this.c()) break block22;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw al.a(runtimeException);
                                            }
                                            if (this.d <= 175) break block23;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw al.a(runtimeException);
                                        }
                                    }
                                    if (!this.func_70681_au().nextBoolean()) break block24;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw al.a(runtimeException);
                                }
                                n4 = 1;
                                break block25;
                            }
                            catch (RuntimeException runtimeException) {
                                throw al.a(runtimeException);
                            }
                        }
                        n4 = -1;
                    }
                    int n5 = n4 * this.func_70681_au().nextInt(10);
                    try {
                        n3 = this.func_70681_au().nextBoolean() ? 1 : -1;
                    }
                    catch (RuntimeException runtimeException) {
                        throw al.a(runtimeException);
                    }
                    int n6 = n3 * this.func_70681_au().nextInt(10);
                    try {
                        n2 = this.field_70170_p.field_73011_w.func_186058_p() == DimensionType.NETHER ? (int)Math.ceil(this.field_70163_u) : cj.a(this.field_70170_p, this.func_180425_c().func_177958_n() + n5, this.func_180425_c().func_177952_p() + n6);
                    }
                    catch (RuntimeException runtimeException) {
                        throw al.a(runtimeException);
                    }
                    int n7 = n2;
                    this.f = new BlockPos(this.func_180425_c().func_177958_n() + n5, n7, this.func_180425_c().func_177952_p() + n6);
                    this.d = 0;
                }
                try {
                    if (!(Math.sqrt(this.f.func_177951_i((Vec3i)this.func_180425_c())) > 2.0)) break block26;
                    this.func_70661_as().func_75492_a((double)this.f.func_177958_n(), (double)this.f.func_177956_o(), (double)this.f.func_177952_p(), (double)0.35f);
                    this.d();
                    break block27;
                }
                catch (RuntimeException runtimeException) {
                    throw al.a(runtimeException);
                }
            }
            ++this.d;
        }
    }

    protected void d() {
        int n2;
        Path path;
        block12: {
            block11: {
                path = this.func_70661_as().func_75505_d();
                try {
                    if (path == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw al.a(runtimeException);
                }
                try {
                    try {
                        if (!this.field_70122_E && !this.func_70090_H()) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw al.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw al.a(runtimeException);
                }
            }
            n2 = path.func_75873_e();
            int n3 = path.func_75874_d();
            try {
                try {
                    if (n3 != n2 && n3 - 1 != n2) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw al.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw al.a(runtimeException);
            }
        }
        PathPoint pathPoint = path.func_75877_a(n2);
        PathPoint pathPoint2 = path.func_75877_a(n2 + 1);
        Vec3d vec3d = new Vec3d((double)(pathPoint2.field_75839_a - pathPoint.field_75839_a), (double)(pathPoint2.field_75837_b - pathPoint.field_75837_b), (double)(pathPoint2.field_75838_c - pathPoint.field_75838_c));
        this.field_70159_w = vec3d.field_72450_a / 7.0;
        this.field_70179_y = vec3d.field_72449_c / 7.0;
    }

    public boolean func_70097_a(DamageSource damageSource, float f10) {
        try {
            if (damageSource == DamageSource.field_76380_i) {
                this.field_70170_p.func_72900_e((Entity)this);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw al.a(runtimeException);
        }
        try {
            if (!(damageSource.func_76346_g() instanceof EntityPlayer)) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw al.a(runtimeException);
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                this.b();
            }
        }
        catch (RuntimeException runtimeException) {
            throw al.a(runtimeException);
        }
        this.e = true;
        be.a(6250, () -> this.field_70170_p.func_72900_e((Entity)this));
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    void b() {
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        this.a = entityPlayerSP.field_70173_aa;
        entityPlayerSP.func_184185_a(com.trolmastercard.sexmod.c.MISC_WEOWEO[3], 1.0f, 1.0f);
    }

    double c() {
        return Math.sqrt(1800.0);
    }

    public boolean func_70601_bi() {
        block4: {
            try {
                try {
                    if (this.func_70681_au().nextInt(100) >= 1 || this.func_70681_au().nextInt(100) >= 10) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw al.a(runtimeException);
                }
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw al.a(runtimeException);
            }
        }
        this.field_70170_p.func_72900_e((Entity)this);
        return false;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

