/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityEndGateway
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ho
extends EntityEnderPearl {
    public ho(World world) {
        super(world);
    }

    public ho(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
    }

    protected void func_70184_a(RayTraceResult rayTraceResult) {
        block17: {
            block18: {
                BlockPos blockPos;
                TileEntity tileEntity;
                EntityLivingBase entityLivingBase = this.func_85052_h();
                if (rayTraceResult.field_72313_a == RayTraceResult.Type.BLOCK && (tileEntity = this.field_70170_p.func_175625_s(blockPos = rayTraceResult.func_178782_a())) instanceof TileEntityEndGateway) {
                    TileEntityEndGateway tileEntityEndGateway;
                    block15: {
                        block16: {
                            tileEntityEndGateway = (TileEntityEndGateway)tileEntity;
                            try {
                                try {
                                    if (entityLivingBase == null) break block15;
                                    if (!(entityLivingBase instanceof EntityPlayerMP)) break block16;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ho.a(runtimeException);
                                }
                                CriteriaTriggers.field_192124_d.func_192193_a((EntityPlayerMP)entityLivingBase, this.field_70170_p.func_180495_p(blockPos));
                            }
                            catch (RuntimeException runtimeException) {
                                throw ho.a(runtimeException);
                            }
                        }
                        tileEntityEndGateway.func_184306_a((Entity)entityLivingBase);
                        this.func_70106_y();
                        return;
                    }
                    tileEntityEndGateway.func_184306_a((Entity)this);
                    return;
                }
                try {
                    for (int i2 = 0; i2 < 32; ++i2) {
                        this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t, this.field_70163_u + this.field_70146_Z.nextDouble() * 2.0, this.field_70161_v, this.field_70146_Z.nextGaussian(), 0.0, this.field_70146_Z.nextGaussian(), new int[0]);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ho.a(runtimeException);
                }
                try {
                    if (this.field_70170_p.field_72995_K) break block17;
                    if (entityLivingBase == null) break block18;
                }
                catch (RuntimeException runtimeException) {
                    throw ho.a(runtimeException);
                }
                em em2 = (em)entityLivingBase;
                if (em2.l.func_72438_d(this.func_174791_d()) < 5.0) {
                    block19: {
                        tileEntity = new EnderTeleportEvent(entityLivingBase, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5.0f);
                        try {
                            try {
                                if (MinecraftForge.EVENT_BUS.post((Event)tileEntity)) break block18;
                                if (!entityLivingBase.func_184218_aH()) break block19;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ho.a(runtimeException);
                            }
                            entityLivingBase.func_184210_p();
                        }
                        catch (RuntimeException runtimeException) {
                            throw ho.a(runtimeException);
                        }
                    }
                    entityLivingBase.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    entityLivingBase.field_70143_R = 0.0f;
                }
            }
            this.func_70106_y();
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a {
        @SubscribeEvent
        public void a(EnderTeleportEvent enderTeleportEvent) {
            if (enderTeleportEvent.getEntityLiving() instanceof em) {
                em em2 = (em)enderTeleportEvent.getEntityLiving();
                em2.q = null;
                em2.b(fp.NULL);
                em2.func_184212_Q().func_187227_b(em.G, (Object)false);
                em2.x();
            }
        }
    }
}

