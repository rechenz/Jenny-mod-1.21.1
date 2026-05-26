/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockChest
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.hy;
import com.trolmastercard.sexmod.r;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class gm {
    static final Vec3i e = new Vec3i(255, 0, 0);
    static final Vec3i g = new Vec3i(0, 255, 0);
    static final Vec3i d = new Vec3i(0, 0, 255);
    static final ResourceLocation b = new ResourceLocation("sexmod", "textures/mark.png");
    static HashSet<BlockPos> f = new HashSet();
    static Minecraft a = Minecraft.func_71410_x();
    static TextureManager c = Minecraft.func_71410_x().func_110434_K();

    public static void a() {
        f.clear();
    }

    public static boolean a(BlockPos blockPos) {
        return f.contains(blockPos);
    }

    public static void b() {
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferBuilder = tessellator.func_178180_c();
        Vec3d vec3d = b6.a(r.k, r.j, (double)a.func_184121_ak());
        GlStateManager.func_179094_E();
        GlStateManager.func_179129_p();
        GlStateManager.func_179097_i();
        c.func_110577_a(b);
        GlStateManager.func_179137_b((double)(-vec3d.field_72450_a), (double)(-vec3d.field_72448_b), (double)(-vec3d.field_72449_c));
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        try {
            for (BlockPos blockPos : f) {
                Vec3i vec3i = gm.b(blockPos);
                gm.a(bufferBuilder, blockPos, vec3i.func_177958_n(), vec3i.func_177956_o(), vec3i.func_177952_p());
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        tessellator.func_78381_a();
        GlStateManager.func_179126_j();
        GlStateManager.func_179089_o();
        GlStateManager.func_179121_F();
    }

    static Vec3i b(BlockPos blockPos) {
        Block block = Minecraft.func_71410_x().field_71441_e.func_180495_p(blockPos).func_177230_c();
        try {
            if (block instanceof BlockBed) {
                return d;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw gm.a(concurrentModificationException);
        }
        try {
            if (block instanceof BlockChest) {
                return g;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw gm.a(concurrentModificationException);
        }
        return e;
    }

    static void a(BufferBuilder bufferBuilder, BlockPos blockPos, int n2, int n3, int n4) {
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)(blockPos.func_177956_o() + 1), (double)blockPos.func_177952_p()).func_187315_a(0.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)(blockPos.func_177956_o() + 1), (double)blockPos.func_177952_p()).func_187315_a(1.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()).func_187315_a(1.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()).func_187315_a(0.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)(blockPos.func_177956_o() + 1), (double)(1 + blockPos.func_177952_p())).func_187315_a(0.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)(blockPos.func_177956_o() + 1), (double)(1 + blockPos.func_177952_p())).func_187315_a(1.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)blockPos.func_177956_o(), (double)(1 + blockPos.func_177952_p())).func_187315_a(1.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)(1 + blockPos.func_177952_p())).func_187315_a(0.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)(blockPos.func_177956_o() + 1), (double)blockPos.func_177952_p()).func_187315_a(0.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)(blockPos.func_177956_o() + 1), (double)(1 + blockPos.func_177952_p())).func_187315_a(1.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)blockPos.func_177956_o(), (double)(1 + blockPos.func_177952_p())).func_187315_a(1.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()).func_187315_a(0.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)(blockPos.func_177956_o() + 1), (double)blockPos.func_177952_p()).func_187315_a(0.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)(blockPos.func_177956_o() + 1), (double)(1 + blockPos.func_177952_p())).func_187315_a(1.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)(1 + blockPos.func_177952_p())).func_187315_a(1.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()).func_187315_a(0.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)(1 + blockPos.func_177952_p())).func_187315_a(0.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)blockPos.func_177956_o(), (double)(1 + blockPos.func_177952_p())).func_187315_a(1.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()).func_187315_a(1.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()).func_187315_a(0.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)(blockPos.func_177956_o() + 1), (double)(1 + blockPos.func_177952_p())).func_187315_a(0.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)(blockPos.func_177956_o() + 1), (double)(1 + blockPos.func_177952_p())).func_187315_a(1.0, 1.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)(1 + blockPos.func_177958_n()), (double)(blockPos.func_177956_o() + 1), (double)blockPos.func_177952_p()).func_187315_a(1.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
        bufferBuilder.func_181662_b((double)blockPos.func_177958_n(), (double)(blockPos.func_177956_o() + 1), (double)blockPos.func_177952_p()).func_187315_a(0.0, 0.0).func_181669_b(n2, n3, n4, 255).func_181675_d();
    }

    public static void a(HashSet<BlockPos> hashSet) {
        f.addAll(hashSet);
    }

    public static void b(HashSet<BlockPos> hashSet) {
        f.removeAll(hashSet);
    }

    @SubscribeEvent
    public void a(RenderWorldLastEvent renderWorldLastEvent) {
        GlStateManager.func_179142_g();
        GL11.glDisable((int)2896);
        ItemStack itemStack = gm.a.field_71439_g.func_184586_b(EnumHand.MAIN_HAND);
        if (itemStack.func_77973_b() != hy.b) {
            itemStack = gm.a.field_71439_g.func_184586_b(EnumHand.OFF_HAND);
        }
        try {
            if (itemStack.func_77973_b() == hy.b) {
                gm.b();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw gm.a(concurrentModificationException);
        }
        GlStateManager.func_179145_e();
        GlStateManager.func_179126_j();
        GlStateManager.func_179141_d();
        GL11.glEnable((int)2896);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void a(TickEvent.ClientTickEvent clientTickEvent) {
        try {
            if (clientTickEvent.phase == TickEvent.Phase.START) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw gm.a(concurrentModificationException);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        try {
            if (entityPlayerSP == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw gm.a(concurrentModificationException);
        }
        r.k = r.j;
        r.j = entityPlayerSP.func_174791_d();
    }

    private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
        return concurrentModificationException;
    }
}

