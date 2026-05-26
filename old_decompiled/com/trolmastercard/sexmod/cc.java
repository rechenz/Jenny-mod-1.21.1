/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.ModelRegistryEvent
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteract
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerChangedDimensionEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.av;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ez;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gf;
import com.trolmastercard.sexmod.v;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class cc
extends Item
implements IAnimatable {
    public static final cc r = new cc();
    public static final long c = 4000L;
    public static final long g = 1000L;
    public static final long j = 3000L;
    public static final float q = 0.1f;
    public static final float p = -0.01f;
    public static final float e = 0.0015f;
    public static final float k = 2.0f;
    public static final float h = 1.5f;
    public static final float d = 0.03f;
    public static final float s = 100.0f;
    public static final float l = 0.2f;
    public static final float o = 1.5f;
    public static final String b = "sexmod:galath_coin_activation_time";
    public static final String m = "sexmod:galath_coin_deactivation_time";
    public static final String n = "sexmod:galath_coin_de_summoning_animation_time";
    public static final String f = "Defeating a succubus makes her accept the victor as her master, granting him a coin to which her soul is bound. Using the coin summons her, offering services on demand. If her master uses the coin on her or goes too far, she returns to the coin";
    private final AnimationFactory i = new AnimationFactory(this);
    AnimationController<cc> a;

    public cc() {
        this.field_77777_bU = 1;
    }

    public static void a() {
        r.setRegistryName("sexmod", "galath_coin");
        r.func_77655_b("galath_coin");
        MinecraftForge.EVENT_BUS.register(cc.class);
    }

    @SubscribeEvent
    public static void a(RegistryEvent.Register<Item> register) {
        register.getRegistry().register((IForgeRegistryEntry)r);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public static void a(ModelRegistryEvent modelRegistryEvent) {
        ModelLoader.setCustomModelResourceLocation((Item)r, (int)0, (ModelResourceLocation)new ModelResourceLocation("sexmod:galath_coin"));
        r.setTileEntityItemStackRenderer(new av());
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer entityPlayer, EnumHand enumHand) {
        NBTTagCompound nBTTagCompound = entityPlayer.getEntityData();
        ActionResult actionResult = new ActionResult(EnumActionResult.FAIL, (Object)entityPlayer.func_184586_b(enumHand));
        try {
            if (nBTTagCompound.func_74763_f(m) != 0L) {
                return actionResult;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        try {
            if (nBTTagCompound.func_74763_f(b) != 0L) {
                return actionResult;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        try {
            if (!this.a(world, entityPlayer)) {
                world.func_184134_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v, com.trolmastercard.sexmod.c.MISC_BEEW[0], SoundCategory.PLAYERS, 1.0f, 1.0f, false);
                return new ActionResult(EnumActionResult.SUCCESS, (Object)entityPlayer.func_184586_b(enumHand));
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        world.func_184134_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v, com.trolmastercard.sexmod.c.MISC_WEOWEO[1], SoundCategory.PLAYERS, 1.0f, 1.0f, false);
        nBTTagCompound.func_74772_a(b, System.currentTimeMillis());
        return new ActionResult(EnumActionResult.SUCCESS, (Object)entityPlayer.func_184586_b(enumHand));
    }

    boolean a(World world, EntityPlayer entityPlayer) {
        boolean bl2;
        block6: {
            boolean bl3;
            block8: {
                block7: {
                    try {
                        try {
                            if (world.field_72995_K) break block6;
                            if (v.c(entityPlayer.getPersistentID())) break block7;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw cc.a(concurrentModificationException);
                        }
                        bl3 = true;
                        break block8;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw cc.a(concurrentModificationException);
                    }
                }
                bl3 = false;
            }
            return bl3;
        }
        try {
            bl2 = !v.f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        return bl2;
    }

    @SubscribeEvent
    public void a(PlayerInteractEvent.EntityInteract entityInteract) {
        EntityPlayer entityPlayer = entityInteract.getEntityPlayer();
        ItemStack itemStack = entityPlayer.func_184586_b(entityInteract.getHand());
        try {
            if (!r.equals(itemStack.func_77973_b())) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        Entity entity = entityInteract.getTarget();
        try {
            if (!(entity instanceof f_)) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        f_ f_2 = (f_)entity;
        try {
            if (!entityPlayer.getPersistentID().equals(f_2.O())) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        entityPlayer.field_70170_p.func_184134_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v, com.trolmastercard.sexmod.c.MISC_WEOWEO[0], SoundCategory.PLAYERS, 1.0f, 1.0f, false);
        entityPlayer.getEntityData().func_74772_a(m, System.currentTimeMillis());
        entityInteract.setCanceled(true);
    }

    public void func_77663_a(ItemStack itemStack, World world, Entity entity, int n2, boolean bl2) {
        long l2;
        long l3;
        long l4;
        EntityPlayer entityPlayer;
        block10: {
            try {
                super.func_77663_a(itemStack, world, entity, n2, bl2);
                if (!(entity instanceof EntityPlayer)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw cc.a(concurrentModificationException);
            }
            entityPlayer = (EntityPlayer)entity;
            NBTTagCompound nBTTagCompound = entityPlayer.getEntityData();
            l4 = nBTTagCompound.func_74763_f(b);
            l3 = nBTTagCompound.func_74763_f(m);
            l2 = System.currentTimeMillis();
            try {
                try {
                    this.b(entityPlayer, nBTTagCompound, l2, l4);
                    this.a(entityPlayer, nBTTagCompound, l2, l3);
                    if (l3 == 0L || l2 <= l3 + 4000L) break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                nBTTagCompound.func_74772_a(m, 0L);
                nBTTagCompound.func_74757_a(n, false);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw cc.a(concurrentModificationException);
            }
        }
        try {
            if (!world.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        this.a(entityPlayer, l2, l4);
        this.b(entityPlayer, l2, l3);
    }

    /*
     * Loose catch block
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SideOnly(value=Side.CLIENT)
    void b(EntityPlayer entityPlayer, long l2, long l3) {
        int n2;
        em em2;
        em em32;
        block27: {
            try {
                if (l3 == 0L) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw cc.a(concurrentModificationException);
            }
            if (l2 <= l3 + 1000L) return;
            try {
                if (l2 < l3 + 3000L) break block27;
                return;
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw cc.a(concurrentModificationException);
            }
        }
        f_ f_2 = null;
        try {
            for (em em32 : em.ad()) {
                try {
                    if (em32.field_70128_L) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                try {
                    if (!em32.field_70170_p.field_72995_K) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                try {
                    if (!(em32 instanceof f_)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                try {
                    if (!entityPlayer.equals((Object)em32.z())) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                f_2 = (f_)em32;
                break;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        Vec3d vec3d = f_2.o().func_72441_c(0.0, 1.5, 0.0);
        em32 = entityPlayer.func_174791_d().func_72441_c(0.0, (double)entityPlayer.func_70047_e(), 0.0);
        try {
            em2 = em32;
            n2 = entityPlayer.func_184614_ca().func_77973_b().equals(r) ? 1 : -1;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        Vec3d vec3d2 = em2.func_178787_e(ck.a((float)n2 * 0.1f, (double)(-0.01f + entityPlayer.field_70125_A * 0.0015f), 0.0, entityPlayer.field_70761_aq));
        float f10 = (float)(l2 - l3 - 1000L) / 2000.0f;
        Vec3d vec3d3 = b6.a(vec3d, vec3d2, (double)f10);
        ez.b = 0.2f;
        Minecraft.func_71410_x().field_71452_i.func_78873_a((Particle)new ez(entityPlayer.field_70170_p, vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c));
    }

    @SideOnly(value=Side.CLIENT)
    void a(EntityPlayer entityPlayer) {
        try {
            if (!Minecraft.func_71410_x().field_71439_g.getPersistentID().equals(entityPlayer.getPersistentID())) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        v.f = true;
    }

    @SideOnly(value=Side.CLIENT)
    void a(EntityPlayer entityPlayer, long l2, long l3) {
        int n2;
        Vec3d vec3d;
        block6: {
            try {
                try {
                    if (l2 > l3 + 1000L && l2 < l3 + 3000L) break block6;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw cc.a(concurrentModificationException);
            }
        }
        Vec3d vec3d2 = entityPlayer.func_174791_d().func_72441_c(0.0, (double)entityPlayer.func_70047_e(), 0.0);
        try {
            vec3d = vec3d2;
            n2 = entityPlayer.func_184614_ca().func_77973_b().equals(r) ? 1 : -1;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        Vec3d vec3d3 = vec3d.func_178787_e(ck.a((float)n2 * 0.1f, (double)(-0.01f + entityPlayer.field_70125_A * 0.0015f), 0.0, entityPlayer.field_70761_aq));
        Vec3d vec3d4 = vec3d2.func_178787_e(entityPlayer.func_70040_Z().func_72432_b().func_186678_a(2.0));
        float f10 = (float)(l2 - l3 - 1000L) / 2000.0f;
        Vec3d vec3d5 = b6.a(vec3d3, vec3d4, (double)f10);
        ez.b = 0.2f;
        Minecraft.func_71410_x().field_71452_i.func_78873_a((Particle)new ez(entityPlayer.field_70170_p, vec3d5.field_72450_a, vec3d5.field_72448_b, vec3d5.field_72449_c));
    }

    @SubscribeEvent
    public void a(PlayerEvent.PlayerChangedDimensionEvent playerChangedDimensionEvent) {
        EntityPlayer entityPlayer = playerChangedDimensionEvent.player;
        try {
            if (entityPlayer.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        UUID uUID = v.b(entityPlayer);
        em em2 = em.a(uUID);
        try {
            if (em2 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        v.a((f_)em2);
        ge.b.sendTo((IMessage)new gf(false), (EntityPlayerMP)entityPlayer);
    }

    void b(EntityPlayer entityPlayer, NBTTagCompound nBTTagCompound, long l2, long l3) {
        try {
            if (l3 == 0L) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        try {
            if (l2 - l3 <= 4000L) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        nBTTagCompound.func_74772_a(b, 0L);
        Vec3d vec3d = entityPlayer.func_174791_d().func_72441_c(0.0, (double)entityPlayer.func_70047_e(), 0.0);
        Vec3d vec3d2 = vec3d.func_178787_e(entityPlayer.func_70040_Z().func_72432_b().func_186678_a(2.0));
        Random random = entityPlayer.func_70681_au();
        int n2 = 0;
        try {
            while ((float)n2 < 100.0f) {
                entityPlayer.field_70170_p.func_175688_a(EnumParticleTypes.DRAGON_BREATH, vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c, (double)((2.0f * random.nextFloat() - 1.0f) * 0.2f), (double)((2.0f * random.nextFloat() - 1.0f) * 0.2f), (double)((2.0f * random.nextFloat() - 1.0f) * 0.2f), new int[0]);
                ++n2;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        World world = entityPlayer.field_70170_p;
        try {
            if (world.field_72995_K) {
                this.a(entityPlayer);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        f_ f_2 = new f_(entityPlayer.field_70170_p, entityPlayer, vec3d2);
        try {
            f_2.func_70634_a(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c);
            v.a(entityPlayer, f_2);
            entityPlayer.field_70170_p.func_72838_d((Entity)f_2);
            if (v.b(entityPlayer.getPersistentID())) {
                f_2.v();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
    }

    void d(EntityPlayer entityPlayer) {
        block3: {
            block2: {
                try {
                    if (!entityPlayer.field_70170_p.field_72995_K) break block2;
                    this.b(entityPlayer);
                    break block3;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
            }
            this.c(entityPlayer);
        }
    }

    void c(EntityPlayer entityPlayer) {
        UUID uUID = v.b(entityPlayer);
        em em2 = em.a(uUID);
        try {
            if (em2 instanceof f_) {
                cc.a((f_)em2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
    }

    public static void a(f_ f_2) {
        f_2.b(fp.GALATH_DE_SUMMON);
        f_2.aC();
        f_2.a(true);
        f_2.c(f_2.func_174791_d());
        f_2.b(f_2.field_70177_z);
    }

    @SideOnly(value=Side.CLIENT)
    void b(EntityPlayer entityPlayer) {
        f_ f_2 = null;
        try {
            for (em em2 : em.ad()) {
                try {
                    if (em2.field_70128_L) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                try {
                    if (!em2.field_70170_p.field_72995_K) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                try {
                    if (!(em2 instanceof f_)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                try {
                    if (!entityPlayer.equals((Object)em2.z())) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                f_2 = (f_)em2;
                break;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        cc.a(entityPlayer, f_2);
    }

    @SideOnly(value=Side.CLIENT)
    public static void a(UUID uUID, f_ f_2) {
        Vec3d vec3d;
        World world = f_2.field_70170_p;
        try {
            vec3d = f_2.Q() ? f_2.o() : f_2.func_174791_d();
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        Vec3d vec3d2 = vec3d;
        Vec3d vec3d3 = vec3d2.func_72441_c(0.0, 1.5, 0.0);
        Random random = f_2.func_70681_au();
        int n2 = 0;
        while ((float)n2 < 100.0f) {
            Vec3d vec3d4 = new Vec3d((double)((random.nextFloat() * 2.0f - 1.0f) * 1.5f), (double)((random.nextFloat() * 2.0f - 1.0f) * 1.5f), (double)((random.nextFloat() * 2.0f - 1.0f) * 1.5f));
            Vec3d vec3d5 = vec3d3.func_178787_e(vec3d4);
            Vec3d vec3d6 = vec3d4.func_186678_a((double)-0.03f);
            world.func_175688_a(EnumParticleTypes.DRAGON_BREATH, vec3d5.field_72450_a, vec3d5.field_72448_b, vec3d5.field_72449_c, vec3d6.field_72450_a, vec3d6.field_72448_b, vec3d6.field_72449_c, new int[0]);
            ++n2;
        }
        try {
            if (Minecraft.func_71410_x().field_71439_g.getPersistentID().equals(uUID)) {
                v.f = false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
    }

    public static void a(EntityPlayer entityPlayer, f_ f_2) {
        cc.a(entityPlayer.getPersistentID(), f_2);
    }

    void a(EntityPlayer entityPlayer, NBTTagCompound nBTTagCompound, long l2, long l3) {
        World world;
        block19: {
            int n2;
            long l4;
            long l5;
            block21: {
                block20: {
                    try {
                        if (l3 == 0L) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw cc.a(concurrentModificationException);
                    }
                    long l6 = l2 - l3;
                    world = entityPlayer.field_70170_p;
                    boolean bl2 = nBTTagCompound.func_74767_n(n);
                    try {
                        try {
                            if (bl2) break block19;
                            l5 = l6;
                            l4 = 1000L;
                            if (!world.field_72995_K) break block20;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw cc.a(concurrentModificationException);
                        }
                        n2 = 0;
                        break block21;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw cc.a(concurrentModificationException);
                    }
                }
                n2 = 150;
            }
            try {
                if (l5 > l4 - (long)n2) {
                    nBTTagCompound.func_74757_a(n, true);
                    this.d(entityPlayer);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw cc.a(concurrentModificationException);
            }
        }
        try {
            if (world.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        try {
            if (l2 - l3 <= 3000L) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        UUID uUID = v.b(entityPlayer);
        em em2 = em.a(uUID);
        try {
            if (!(em2 instanceof f_)) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw cc.a(concurrentModificationException);
        }
        v.a((f_)em2);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        this.a = new AnimationController<cc>(this, "controller", 0.0f, this::a);
        animationData.addAnimationController(this.a);
    }

    @SideOnly(value=Side.CLIENT)
    protected <segs extends IAnimatable> PlayState a(AnimationEvent<segs> animationEvent) {
        block4: {
            NBTTagCompound nBTTagCompound = Minecraft.func_71410_x().field_71439_g.getEntityData();
            try {
                try {
                    if (nBTTagCompound.func_74763_f(b) != 0L || nBTTagCompound.func_74763_f(m) != 0L) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw cc.a(concurrentModificationException);
                }
                animationEvent.getController().clearAnimationCache();
                return PlayState.STOP;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw cc.a(concurrentModificationException);
            }
        }
        this.a.setAnimation(new AnimationBuilder().addAnimation("animation.galath_coin.summon", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.i;
    }

    private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
        return concurrentModificationException;
    }
}

