/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  javax.vecmath.Matrix4f
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityHanging
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL20
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ClientProxy;
import com.trolmastercard.sexmod.a;
import com.trolmastercard.sexmod.b;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.c3;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.cv;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f7;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.gj;
import com.trolmastercard.sexmod.gx;
import com.trolmastercard.sexmod.p;
import com.trolmastercard.sexmod.y;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.RenderHurtColor;
import software.bernie.geckolib3.util.MatrixStack;
import software.bernie.shadowed.eliotlash.mclib.utils.Interpolations;

public abstract class d_<T extends em>
extends GeoEntityRenderer<T>
implements c3 {
    protected static final ResourceLocation e = new ResourceLocation("sexmod", "textures/line.png");
    static final float m = 1.5f;
    protected double c;
    protected T j;
    protected static Minecraft i;
    protected static HashMap<UUID, ResourceLocation> l;
    Color f = new Color(245, 199, 165);
    Color o = new Color(245, 157, 169);
    boolean h = false;
    protected HashSet<String> p = new HashSet();
    Integer k = null;
    Integer b = null;
    Integer d = null;
    float a = 0.0f;
    public static BufferBuilder n;
    Matrix4f g = null;
    protected GeoBone q = null;

    public d_(RenderManager renderManager, AnimatedGeoModel<T> animatedGeoModel, double d10) {
        super(renderManager, animatedGeoModel);
        this.c = d10;
        i = Minecraft.func_71410_x();
        this.field_76989_e = 0.2f;
    }

    protected ResourceLocation d(T t2) throws IOException {
        ResourceLocation resourceLocation;
        block9: {
            block8: {
                try {
                    if (!(((em)t2).field_70170_p instanceof gj) && ((em)t2).ae() != null) break block8;
                }
                catch (IOException iOException) {
                    throw d_.b(iOException);
                }
                resourceLocation = l.get(i.func_110432_I().func_148256_e().getId());
                try {
                    if (resourceLocation == null) {
                        return this.a(i.func_110432_I().func_148256_e().getId(), ((em)t2).field_70170_p);
                    }
                    break block9;
                }
                catch (IOException iOException) {
                    throw d_.b(iOException);
                }
            }
            resourceLocation = l.get(((em)t2).ae());
            try {
                if (resourceLocation == null) {
                    return this.a(((em)t2).ae(), ((em)t2).field_70170_p);
                }
            }
            catch (IOException iOException) {
                throw d_.b(iOException);
            }
        }
        return resourceLocation;
    }

    protected ResourceLocation a(UUID uUID, World world) throws IOException {
        BufferedImage bufferedImage;
        try {
            bufferedImage = y.a(uUID);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(this.f);
            graphics.fillRect(0, 0, 4, 3);
            graphics.setColor(this.o);
            graphics.fillRect(4, 0, 3, 3);
        }
        catch (Exception exception) {
            try {
                if (!this.h) {
                    this.h = true;
                }
            }
            catch (Exception exception2) {
                throw d_.b(exception2);
            }
            bufferedImage = ImageIO.read(i.func_110442_L().func_110536_a(new ResourceLocation("sexmod", "textures/player/steve.png")).func_110527_b());
        }
        l.put(uUID, this.field_76990_c.field_78724_e.func_110578_a("player" + uUID, new DynamicTexture(bufferedImage)));
        return l.get(uUID);
    }

    protected static float a(em em2, float f10) {
        float f11;
        try {
            f11 = em2.Q() ? em2.I().floatValue() : b6.a(em2.field_70760_ar, em2.field_70761_aq, f10);
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        return f11;
    }

    protected void d() {
    }

    protected void b() {
    }

    float a(World world, Vec3d vec3d, float f10, float f11) {
        RayTraceResult rayTraceResult = this.a(vec3d, vec3d.func_178787_e(ck.a(new Vec3d(0.0, 0.0, -4.0), f10, f11)), world);
        try {
            if (rayTraceResult == null) {
                return 4.0f;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        Vec3d vec3d2 = rayTraceResult.field_72307_f;
        try {
            if (vec3d2 == null) {
                return 4.0f;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        return (float)vec3d.func_72438_d(vec3d2);
    }

    boolean a(T t2, EntityPlayer entityPlayer) {
        try {
            if (t2 instanceof ei) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        World world = ((em)t2).field_70170_p;
        Vec3d vec3d = t2.func_174791_d();
        float f10 = ((em)t2).field_70130_N * 1.5f;
        float f11 = ((em)t2).field_70131_O * 1.5f;
        Vec3d vec3d2 = entityPlayer.func_174791_d().func_72441_c(0.0, (double)entityPlayer.func_70047_e(), 0.0);
        int n2 = d_.i.field_71474_y.field_74320_O;
        try {
            if (n2 != 0) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        if (n2 > 0) {
            float f12 = entityPlayer.field_70177_z;
            float f13 = entityPlayer.field_70125_A;
            if (n2 == 2) {
                f13 += 180.0f;
            }
            float f14 = 4.0f;
            Vec3d vec3d3 = vec3d2.func_72441_c((double)(MathHelper.func_76126_a((float)(f12 * ((float)Math.PI / 180))) * MathHelper.func_76134_b((float)(f13 * ((float)Math.PI / 180))) * f14), (double)(MathHelper.func_76126_a((float)(f13 * ((float)Math.PI / 180))) * f14), (double)(-MathHelper.func_76134_b((float)(f12 * ((float)Math.PI / 180))) * MathHelper.func_76134_b((float)(f13 * ((float)Math.PI / 180))) * f14));
            BlockPos blockPos = new BlockPos(vec3d3);
            boolean bl2 = world.func_175623_d(blockPos);
            if (!bl2) {
                vec3d2 = vec3d3;
            } else if (world.func_175623_d(blockPos.func_177982_a(0, 1, 0))) {
                vec3d2 = new Vec3d(vec3d3.field_72450_a, (double)(blockPos.func_177956_o() + 1), vec3d3.field_72449_c);
            }
        }
        Vec3d[] vec3dArray = new Vec3d[]{vec3d.func_72441_c((double)(-f10 / 2.0f), 0.0, (double)(-f10 / 2.0f)), vec3d.func_72441_c((double)(-f10 / 2.0f), 0.0, (double)(f10 / 2.0f)), vec3d.func_72441_c((double)(f10 / 2.0f), 0.0, (double)(-f10 / 2.0f)), vec3d.func_72441_c((double)(f10 / 2.0f), 0.0, (double)(f10 / 2.0f)), vec3d.func_72441_c((double)(-f10 / 2.0f), (double)f11, (double)(-f10 / 2.0f)), vec3d.func_72441_c((double)(-f10 / 2.0f), (double)f11, (double)(f10 / 2.0f)), vec3d.func_72441_c((double)(f10 / 2.0f), (double)f11, (double)(-f10 / 2.0f)), vec3d.func_72441_c((double)(f10 / 2.0f), (double)f11, (double)(f10 / 2.0f))};
        for (Vec3d vec3d3 : vec3dArray) {
            RayTraceResult rayTraceResult = this.a(vec3d2, vec3d3, world);
            try {
                if (rayTraceResult == null) {
                    return true;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            IBlockState iBlockState = world.func_180495_p(rayTraceResult.func_178782_a());
            try {
                if (iBlockState.func_185895_e()) {
                    return true;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            try {
                if (iBlockState.func_177230_c().func_180664_k() == BlockRenderLayer.SOLID) continue;
                return true;
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
        }
        return false;
    }

    HashSet<String> a(Boolean bl2, boolean bl3) {
        try {
            if (ClientProxy.IS_PRELOADING) {
                return new HashSet<String>();
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        HashSet<String> hashSet = bl2 != false ? com.trolmastercard.sexmod.a.b() : ((em)this.j).Y();
        HashSet<String> hashSet2 = new HashSet<String>();
        for (String string : hashSet) {
            br.b b10 = br.b(string);
            try {
                if (b10 == null) {
                    continue;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            try {
                try {
                    if (!b10.a() && bl3) {
                        continue;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            hashSet2.addAll(b10.h());
        }
        return hashSet2;
    }

    public void a(GeoModel geoModel, T t2, float f10, float f11, float f12, float f13, float f14) {
        boolean bl2;
        Boolean bl3;
        d_ d_2;
        block10: {
            try {
                try {
                    try {
                        try {
                            if (d_.i.field_71439_g == null || ((em)t2).h()) break block10;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        if (!((em)t2).d()) break block10;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                    if (this.a(t2, (EntityPlayer)d_.i.field_71439_g)) break block10;
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
        }
        GlStateManager.func_179091_B();
        this.a(t2, f10, f11, f12, f13, f14);
        this.renderLate(t2, f10, f11, f12, f13, f14);
        BufferBuilder bufferBuilder = Tessellator.func_178181_a().func_178180_c();
        try {
            bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
            this.func_110776_a(Objects.requireNonNull(this.getEntityTexture(this.j)));
            this.p.clear();
            d_ d_3 = this;
            d_2 = this;
            bl3 = ((em)t2).h();
            bl2 = ((em)t2).ah() == 0;
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        d_3.p = d_2.a(bl3, bl2);
        this.d();
        gx.a(((em)t2).b().getModelRendererList(), this.a(), this);
        gx.a(t2, f10);
        this.a(geoModel, bufferBuilder, t2, f11, f12, f13, f14, f10);
        this.renderAfter(t2, f10, f11, f12, f13, f14);
        GlStateManager.func_179101_C();
        GlStateManager.func_179089_o();
        GL20.glUseProgram((int)0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void a(GeoModel geoModel, BufferBuilder bufferBuilder, T t2, float f10, float f11, float f12, float f13, float f14) {
        block6: {
            GeoBone geoBone = null;
            for (GeoBone geoBone2 : geoModel.topLevelBones) {
                if (geoBone2.getName().equals("steve")) {
                    geoBone = geoBone2;
                    continue;
                }
                this.renderRecursively(bufferBuilder, geoBone2, f10, f11, f12, f13);
            }
            try {
                Tessellator.func_178181_a().func_78381_a();
                this.b();
                if (geoBone == null) break block6;
                bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
                try {
                    Minecraft.func_71410_x().field_71446_o.func_110577_a(this.d(this.j));
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            this.renderRecursively(bufferBuilder, geoBone, f10, f11, f12, ((em)this.j).v());
            Tessellator.func_178181_a().func_78381_a();
        }
    }

    String a(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(string));
            while (true) {
                String string2 = bufferedReader.readLine();
                try {
                    if (string2 == null) break;
                    stringBuilder.append(string2).append("//\n");
                }
                catch (IOException iOException) {
                    throw d_.b(iOException);
                }
            }
            bufferedReader.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        return stringBuilder.toString();
    }

    protected void a(double d10, double d11, double d12) {
        try {
            if (((em)this.j).h()) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        try {
            if (((em)this.j).y().hideNameTag) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        try {
            if (d_.i.func_175598_ae().field_78734_h == null) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        this.func_147906_a((Entity)this.j, ((em)this.j).ab(), d10, d11 + (double)((em)this.j).i(), d12, 300);
    }

    Vec3d a(EntityPlayer entityPlayer, float f10) {
        EntityLiving entityLiving = (EntityLiving)entityPlayer.func_184187_bx();
        EntityPlayerSP entityPlayerSP = d_.i.field_71439_g;
        Vec3d vec3d = entityLiving.func_70040_Z();
        Vec3d vec3d2 = b6.a(new Vec3d(entityPlayer.field_70142_S, entityPlayer.field_70137_T, entityPlayer.field_70136_U), entityPlayer.func_174791_d(), (double)f10);
        Vec3d vec3d3 = b6.a(new Vec3d(entityPlayerSP.field_70142_S, entityPlayerSP.field_70137_T, entityPlayerSP.field_70136_U), entityPlayerSP.func_174791_d(), (double)f10);
        vec3d3 = vec3d2.func_178788_d(vec3d3);
        ((em)this.j).field_70761_aq = entityLiving.field_70761_aq;
        return new Vec3d(vec3d3.field_72450_a + vec3d.field_72450_a * -0.5, vec3d3.field_72448_b + (double)0.15f, vec3d3.field_72449_c + vec3d.field_72449_c * -0.5);
    }

    protected Vec3d a(T t2, float f10, Vec3d vec3d) {
        return vec3d;
    }

    Vec3d a(T t2, float f10, double d10, double d11, double d12) {
        float f11;
        Vec3d vec3d;
        block28: {
            block27: {
                block26: {
                    block24: {
                        vec3d = new Vec3d(d10, d11, d12);
                        try {
                            if (((em)t2).field_70170_p instanceof gj) {
                                return vec3d;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        try {
                            block25: {
                                try {
                                    try {
                                        if (!((em)t2).t()) break block24;
                                        if (!(t2 instanceof ei)) break block25;
                                    }
                                    catch (IllegalStateException illegalStateException) {
                                        throw d_.b(illegalStateException);
                                    }
                                    if (d_.i.field_71474_y.field_74320_O == 0) break block24;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw d_.b(illegalStateException);
                                }
                            }
                            this.a(d10, d11, d12);
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                    }
                    EntityPlayer entityPlayer = ((em)t2).z();
                    try {
                        try {
                            try {
                                try {
                                    if (entityPlayer == null || !entityPlayer.func_184218_aH()) break block26;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw d_.b(illegalStateException);
                                }
                                if (!(entityPlayer.func_184187_bx() instanceof EntityHorse)) break block26;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d_.b(illegalStateException);
                            }
                            if (!((EntityHorse)entityPlayer.func_184187_bx()).func_110257_ck()) break block26;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        return this.a(entityPlayer, f10);
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                }
                try {
                    if (!((em)t2).Q()) {
                        return vec3d;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
                try {
                    try {
                        if (!(t2 instanceof ei) || !((ei)t2).f()) break block27;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                    if (d_.i.field_71474_y.field_74320_O != 0) break block28;
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
            }
            Vec3d vec3d2 = b6.a(new Vec3d(d_.i.field_71439_g.field_70142_S, d_.i.field_71439_g.field_70137_T, d_.i.field_71439_g.field_70136_U), d_.i.field_71439_g.func_174791_d(), (double)f10);
            vec3d = ((em)t2).o().func_178788_d(vec3d2);
        }
        ((em)t2).field_70177_z = f11 = ((em)t2).I().floatValue();
        ((em)t2).field_70760_ar = f11;
        ((em)t2).field_70761_aq = f11;
        ((em)t2).field_70758_at = f11;
        ((em)t2).field_70759_as = f11;
        return vec3d;
    }

    protected void b(T t2) {
    }

    public void a(T t2, double d10, double d11, double d12, float f10, float f11) {
        boolean bl2;
        float f12;
        float f13;
        float f14;
        T t3;
        AnimationEvent<T> animationEvent;
        AnimationEvent<T> animationEvent2;
        float f15;
        float f16;
        float f17;
        float f18;
        EntityModelData entityModelData;
        block33: {
            block32: {
                block31: {
                    float f19;
                    float f20;
                    boolean bl3;
                    block30: {
                        boolean bl4;
                        block29: {
                            block28: {
                                this.j = t2;
                                Vec3d vec3d = this.a(t2, f11, d10, d11, d12);
                                vec3d = this.a(t2, f11, vec3d);
                                d10 = vec3d.field_72450_a;
                                d11 = vec3d.field_72448_b;
                                d12 = vec3d.field_72449_c;
                                try {
                                    this.b(t2);
                                    if (t2.func_110167_bD()) {
                                        this.a((em)t2, d10, d11 + this.c, d12, f11);
                                    }
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw d_.b(illegalStateException);
                                }
                                try {
                                    try {
                                        GlStateManager.func_179094_E();
                                        GlStateManager.func_179137_b((double)d10, (double)d11, (double)d12);
                                        GL11.glDisable((int)2896);
                                        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
                                        GlStateManager.func_179108_z();
                                        GlStateManager.func_179147_l();
                                        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                                        if (t2.func_184187_bx() == null || !t2.func_184187_bx().shouldRiderSit()) break block28;
                                    }
                                    catch (IllegalStateException illegalStateException) {
                                        throw d_.b(illegalStateException);
                                    }
                                    bl4 = true;
                                    break block29;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw d_.b(illegalStateException);
                                }
                            }
                            bl4 = false;
                        }
                        bl3 = bl4;
                        entityModelData = new EntityModelData();
                        entityModelData.isSitting = bl3;
                        entityModelData.isChild = t2.func_70631_g_();
                        f20 = Interpolations.lerpYaw(((em)t2).field_70760_ar, ((em)t2).field_70761_aq, f11);
                        float f21 = Interpolations.lerpYaw(((em)t2).field_70758_at, ((em)t2).field_70759_as, f11);
                        f18 = f21 - f20;
                        try {
                            if (!bl3 || !(t2.func_184187_bx() instanceof EntityLivingBase)) break block30;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        EntityLivingBase entityLivingBase = (EntityLivingBase)t2.func_184187_bx();
                        f20 = Interpolations.lerpYaw(entityLivingBase.field_70760_ar, entityLivingBase.field_70761_aq, f11);
                        f18 = f21 - f20;
                        f19 = MathHelper.func_76142_g((float)f18);
                        if (f19 < -85.0f) {
                            f19 = -85.0f;
                        }
                        if (f19 >= 85.0f) {
                            f19 = 85.0f;
                        }
                        f20 = f21 - f19;
                        if (f19 * f19 > 2500.0f) {
                            f20 += f19 * 0.2f;
                        }
                        f18 = f21 - f20;
                    }
                    f17 = Interpolations.lerp(((em)t2).field_70127_C, ((em)t2).field_70125_A, f11);
                    f19 = this.handleRotationFloat(t2, f11);
                    this.b(t2, f19, f20, f11);
                    f16 = 0.0f;
                    f15 = 0.0f;
                    try {
                        if (bl3 || !t2.func_70089_S()) break block31;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                    f16 = Interpolations.lerp(((em)t2).field_184618_aE, ((em)t2).field_70721_aZ, f11);
                    f15 = ((em)t2).field_184619_aG - ((em)t2).field_70721_aZ * (1.0f - f11);
                    if (t2.func_70631_g_()) {
                        f15 *= 3.0f;
                    }
                    if (f16 > 1.0f) {
                        f16 = 1.0f;
                    }
                }
                try {
                    AnimationEvent<T> animationEvent3;
                    entityModelData.headPitch = -f17;
                    entityModelData.netHeadYaw = -f18;
                    animationEvent2 = animationEvent3;
                    animationEvent = animationEvent3;
                    t3 = t2;
                    f14 = f15;
                    f13 = f16;
                    f12 = f11;
                    if (f16 > -0.15f && f16 < 0.15f) break block32;
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
                bl2 = true;
                break block33;
            }
            bl2 = false;
        }
        animationEvent2(t3, f14, f13, f12, bl2, Collections.singletonList(entityModelData));
        AnimationEvent<T> animationEvent4 = animationEvent;
        GeoModelProvider geoModelProvider = super.getGeoModelProvider();
        ResourceLocation resourceLocation = geoModelProvider.getModelLocation(t2);
        GeoModel geoModel = geoModelProvider.getModel(resourceLocation);
        try {
            if (geoModelProvider instanceof IAnimatableModel) {
                ((IAnimatableModel)((Object)geoModelProvider)).setLivingAnimations(t2, t2.func_110124_au().hashCode(), animationEvent4);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)0.0f, (float)0.01f, (float)0.0f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getEntityTexture(t2));
        software.bernie.geckolib3.core.util.Color color = this.getRenderColor(t2, f11);
        boolean bl5 = this.setDoRenderBrightness(t2, f11);
        try {
            this.a(geoModel, t2, f11, (float)color.getRed() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getAlpha() / 255.0f);
            if (bl5) {
                RenderHurtColor.unset();
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        for (GeoLayerRenderer geoLayerRenderer : this.layerRenderers) {
            geoLayerRenderer.render(t2, f15, f16, f11, f15, f18, f17, color);
        }
        GL11.glEnable((int)2896);
        GlStateManager.func_179084_k();
        GlStateManager.func_179133_A();
        GlStateManager.func_179121_F();
        GlStateManager.func_179121_F();
        this.a(t2);
        com.trolmastercard.sexmod.b.a(t2, f11);
        f7 f72 = this.e(t2);
        try {
            if (f72 != null) {
                this.a((em)t2, f11, f72);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
    }

    void a(T t2) {
        ArrayList<String> arrayList = new ArrayList<String>(cv.e);
        arrayList.addAll(((em)t2).p);
        for (String string : arrayList) {
            boolean bl2;
            String string2;
            T t3;
            try {
                t3 = t2;
                string2 = string;
                bl2 = !((em)t2).h();
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            MatrixStack matrixStack = ((em)t3).a(string2, bl2);
            Matrix4f matrix4f = matrixStack.getModelMatrix();
            Vec3d vec3d = new Vec3d((double)(-matrix4f.m03), (double)matrix4f.m13, (double)(-matrix4f.m23));
            ((em)t2).a(string, vec3d);
        }
    }

    @Nullable
    protected f7 e(T t2) {
        return null;
    }

    public Entity c(em em2) {
        return em2;
    }

    void a(em em2, float f10, f7 f72) {
        Vec3d vec3d;
        EntityPlayerSP entityPlayerSP = d_.i.field_71439_g;
        f72 = new f7(f72.a / 255.0f, f72.c / 255.0f, f72.b / 255.0f);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferBuilder = tessellator.func_178180_c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)0.0, (double)0.01, (double)0.0);
        Entity entity = this.c(em2);
        try {
            vec3d = em2.Q() ? em2.o() : b6.a(new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U), entity.func_174791_d(), (double)f10);
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        Vec3d vec3d2 = vec3d;
        Vec3d vec3d3 = b6.a(new Vec3d(entityPlayerSP.field_70142_S, entityPlayerSP.field_70137_T, entityPlayerSP.field_70136_U), entityPlayerSP.func_174791_d(), (double)f10);
        Vec3d vec3d4 = vec3d2.func_178788_d(vec3d3);
        GlStateManager.func_179137_b((double)vec3d4.field_72450_a, (double)vec3d4.field_72448_b, (double)vec3d4.field_72449_c);
        i.func_110434_K().func_110577_a(e);
        float f11 = d_.a(em2, f10, 1.0f, 5.0f);
        this.b(tessellator, bufferBuilder, em2, f72, f11);
        GlStateManager.func_179121_F();
    }

    protected static float a(em em2, float f10, float f11, float f12) {
        Vec3d vec3d;
        EntityPlayerSP entityPlayerSP = d_.i.field_71439_g;
        Entity entity = ((d_)i.func_175598_ae().func_78713_a((Entity)em2)).c(em2);
        try {
            vec3d = em2.Q() ? em2.o() : b6.a(new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U), entity.func_174791_d(), (double)f10);
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        Vec3d vec3d2 = vec3d;
        Vec3d vec3d3 = b6.a(new Vec3d(entityPlayerSP.field_70142_S, entityPlayerSP.field_70137_T, entityPlayerSP.field_70136_U), entityPlayerSP.func_174791_d(), (double)f10);
        Vec3d vec3d4 = ActiveRenderInfo.getCameraPosition().func_178787_e(vec3d3);
        float f13 = (float)vec3d4.func_72438_d(vec3d2);
        float f14 = Math.abs(f13) / 5.0f;
        return b6.a(f12, f11, be.b(f14, 0.0f, 1.0f));
    }

    protected void b(Tessellator tessellator, BufferBuilder bufferBuilder, em em2, f7 f72, float f10) {
    }

    protected static void a(BufferBuilder bufferBuilder, Tessellator tessellator, em em2, String string, String string2, float f10, float f11, float f12, float f13) {
        bufferBuilder.func_181668_a(1, DefaultVertexFormats.field_181709_i);
        GlStateManager.func_187441_d((float)f13);
        Vec3d vec3d = em2.b(string);
        Vec3d vec3d2 = em2.b(string2);
        bufferBuilder.func_181662_b(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c).func_187315_a(0.0, 0.0).func_181666_a(f10, f11, f12, 1.0f).func_181675_d();
        bufferBuilder.func_181662_b(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c).func_187315_a(0.0, 0.0).func_181666_a(f10, f11, f12, 1.0f).func_181675_d();
        tessellator.func_78381_a();
    }

    protected static void a(Tessellator tessellator, BufferBuilder bufferBuilder, em em2, f7 f72, float f10) {
        d_.a(bufferBuilder, tessellator, em2, "braStringMidStartR", "braStringMidMid1R", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidMid1R", "braStringMidMid2R", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidMid2R", "braStringMidMid3R", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidMid3R", "braStringMidEndR", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidEndR", "braStringBackR", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringBackR", "braStringRightEndR", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringRightEndR", "braStringRightStartR", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringRightR", "braStringRightL", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidStartL", "braStringMidMid1L", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidMid1L", "braStringMidMid2L", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidMid2L", "braStringMidMid3L", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidMid3L", "braStringMidEndL", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringMidEndL", "braStringBackL", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringBackL", "braStringLeftEndL", f72.a, f72.c, f72.b, f10);
        d_.a(bufferBuilder, tessellator, em2, "braStringLeftEndL", "braStringLeftStartL", f72.a, f72.c, f72.b, f10);
    }

    protected void b(T t2, float f10, float f11, float f12) {
        block14: {
            try {
                super.applyRotations(t2, f10, f11, f12);
                if (!(t2 instanceof ei)) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            UUID uUID = ((ei)t2).m();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            EntityPlayer entityPlayer = ((em)t2).field_70170_p.func_152378_a(uUID);
            try {
                if (entityPlayer == null) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            try {
                if (!entityPlayer.func_184613_cA()) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            float f13 = (float)entityPlayer.func_184599_cB() + f12;
            float f14 = MathHelper.func_76131_a((float)(f13 * f13 / 100.0f), (float)0.0f, (float)1.0f);
            GlStateManager.func_179114_b((float)(f14 * (-90.0f - entityPlayer.field_70125_A)), (float)1.0f, (float)0.0f, (float)0.0f);
            Vec3d vec3d = entityPlayer.func_70676_i(f12);
            double d10 = entityPlayer.field_70159_w * entityPlayer.field_70159_w + entityPlayer.field_70179_y * entityPlayer.field_70179_y;
            double d11 = vec3d.field_72450_a * vec3d.field_72450_a + vec3d.field_72449_c * vec3d.field_72449_c;
            try {
                if (!(d10 > 0.0) || !(d11 > 0.0)) break block14;
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
            double d12 = (entityPlayer.field_70159_w * vec3d.field_72450_a + entityPlayer.field_70179_y * vec3d.field_72449_c) / (Math.sqrt(d10) * Math.sqrt(d11));
            double d13 = entityPlayer.field_70159_w * vec3d.field_72449_c - entityPlayer.field_70179_y * vec3d.field_72450_a;
            GlStateManager.func_179114_b((float)((float)(Math.signum(d13) * Math.acos(d12)) * 180.0f / (float)Math.PI), (float)0.0f, (float)1.0f, (float)0.0f);
        }
    }

    protected void a(BufferBuilder bufferBuilder, String string, GeoBone geoBone) {
    }

    protected void a(em em2, double d10, double d11, double d12, float f10) {
        float f11;
        float f12;
        float f13;
        float f14;
        int n2;
        Entity entity = em2.func_110166_bE();
        d11 -= (1.6 - (double)em2.field_70131_O) * 0.5;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferBuilder = tessellator.func_178180_c();
        double d13 = (double)b6.a(entity.field_70126_B, entity.field_70177_z, f10 * 0.5f) * 0.01745329238474369;
        double d14 = (double)b6.a(entity.field_70127_C, entity.field_70125_A, f10 * 0.5f) * 0.01745329238474369;
        double d15 = Math.cos(d13);
        double d16 = Math.sin(d13);
        double d17 = Math.sin(d14);
        if (entity instanceof EntityHanging) {
            d15 = 0.0;
            d16 = 0.0;
            d17 = -1.0;
        }
        double d18 = Math.cos(d14);
        double d19 = b6.b(entity.field_70169_q, entity.field_70165_t, (double)f10) - d15 * 0.7 - d16 * 0.5 * d18;
        double d20 = b6.b(entity.field_70167_r + (double)entity.func_70047_e() * 0.7, entity.field_70163_u + (double)entity.func_70047_e() * 0.7, (double)f10) - d17 * 0.5 - 0.25;
        double d21 = b6.b(entity.field_70166_s, entity.field_70161_v, (double)f10) - d16 * 0.7 + d15 * 0.5 * d18;
        double d22 = (double)b6.a(em2.field_70760_ar, em2.field_70761_aq, f10) * 0.01745329238474369 + 1.5707963267948966;
        d15 = Math.cos(d22) * (double)em2.field_70130_N * 0.4;
        d16 = Math.sin(d22) * (double)em2.field_70130_N * 0.4;
        double d23 = b6.b(em2.field_70169_q, em2.field_70165_t, (double)f10) + d15;
        double d24 = b6.b(em2.field_70167_r, em2.field_70163_u, (double)f10);
        double d25 = b6.b(em2.field_70166_s, em2.field_70161_v, (double)f10) + d16;
        d10 += d15;
        d12 += d16;
        double d26 = (float)(d19 - d23);
        double d27 = (float)(d20 - d24);
        double d28 = (float)(d21 - d25);
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        bufferBuilder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        for (n2 = 0; n2 <= 24; ++n2) {
            f14 = 0.5f;
            f13 = 0.4f;
            f12 = 0.3f;
            if (n2 % 2 == 0) {
                f14 *= 0.7f;
                f13 *= 0.7f;
                f12 *= 0.7f;
            }
            f11 = (float)n2 / 24.0f;
            bufferBuilder.func_181662_b(d10 + d26 * (double)f11 + 0.0, d11 + d27 * (double)(f11 * f11 + f11) * 0.5 + (double)((24.0f - (float)n2) / 18.0f + 0.125f), d12 + d28 * (double)f11).func_181666_a(f14, f13, f12, 1.0f).func_181675_d();
            bufferBuilder.func_181662_b(d10 + d26 * (double)f11 + 0.025, d11 + d27 * (double)(f11 * f11 + f11) * 0.5 + (double)((24.0f - (float)n2) / 18.0f + 0.125f) + 0.025, d12 + d28 * (double)f11).func_181666_a(f14, f13, f12, 1.0f).func_181675_d();
        }
        tessellator.func_78381_a();
        bufferBuilder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        for (n2 = 0; n2 <= 24; ++n2) {
            f14 = 0.5f;
            f13 = 0.4f;
            f12 = 0.3f;
            if (n2 % 2 == 0) {
                f14 *= 0.7f;
                f13 *= 0.7f;
                f12 *= 0.7f;
            }
            f11 = (float)n2 / 24.0f;
            bufferBuilder.func_181662_b(d10 + d26 * (double)f11 + 0.0, d11 + d27 * (double)(f11 * f11 + f11) * 0.5 + (double)((24.0f - (float)n2) / 18.0f + 0.125f) + 0.025, d12 + d28 * (double)f11).func_181666_a(f14, f13, f12, 1.0f).func_181675_d();
            bufferBuilder.func_181662_b(d10 + d26 * (double)f11 + 0.025, d11 + d27 * (double)(f11 * f11 + f11) * 0.5 + (double)((24.0f - (float)n2) / 18.0f + 0.125f), d12 + d28 * (double)f11 + 0.025).func_181666_a(f14, f13, f12, 1.0f).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179145_e();
        GlStateManager.func_179098_w();
        GlStateManager.func_179089_o();
    }

    /*
     * Could not resolve type clashes
     * Loose catch block
     */
    @Override
    public void renderRecursively(BufferBuilder bufferBuilder, GeoBone geoBone, float f10, float f11, float f12, float f13) {
        String string;
        block35: {
            block34: {
                block33: {
                    block32: {
                        block31: {
                            try {
                                if (((em)this.j).field_70170_p instanceof gj) {
                                    return;
                                }
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d_.b(illegalStateException);
                            }
                            string = geoBone.getName();
                            if (!string.equals("weapon")) break block31;
                            try {
                                block36: {
                                    if (!(this.j instanceof e2)) break block31;
                                    break block36;
                                    catch (IllegalStateException illegalStateException) {
                                        throw d_.b(illegalStateException);
                                    }
                                }
                                this.a(bufferBuilder, geoBone);
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d_.b(illegalStateException);
                            }
                        }
                        if (!string.equals("itemRenderer")) break block32;
                        try {
                            block37: {
                                if (((em)this.j).y() != fp.PAYMENT) break block32;
                                break block37;
                                catch (IllegalStateException illegalStateException) {
                                    throw d_.b(illegalStateException);
                                }
                            }
                            this.b(bufferBuilder, geoBone);
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                    }
                    if (string.equals("ballL")) break block33;
                    try {
                        block38: {
                            if (string.equals("ballR")) break block33;
                            break block38;
                            catch (IllegalStateException illegalStateException) {
                                throw d_.b(illegalStateException);
                            }
                        }
                        if (!string.equals("cock")) break block34;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                }
                f13 = 1.0f;
            }
            n = bufferBuilder;
            this.a(bufferBuilder, string, geoBone);
            MATRIX_STACK.push();
            MATRIX_STACK.translate(geoBone);
            MATRIX_STACK.moveToPivot(geoBone);
            MATRIX_STACK.rotate(geoBone);
            MATRIX_STACK.scale(geoBone);
            MATRIX_STACK.moveBackFromPivot(geoBone);
            if (!"Head2".equals(string)) break block35;
            try {
                block39: {
                    if (this.c()) break block35;
                    break block39;
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                }
                MATRIX_STACK.pop();
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw d_.b(illegalStateException);
            }
        }
        try {
            if (!this.b(string)) {
                MATRIX_STACK.pop();
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        if (!geoBone.isHidden) {
            Vector4f vector4f = this.a(string, f10, f11, f12);
            f10 = vector4f.x;
            f11 = vector4f.y;
            f12 = vector4f.z;
            double d10 = vector4f.w;
            if (!this.p.contains(string)) {
                for (Object object : geoBone.childCubes) {
                    MATRIX_STACK.push();
                    this.q = geoBone;
                    this.a(bufferBuilder, (GeoCube)object, f10, f11, f12, f13, d10);
                    MATRIX_STACK.pop();
                }
            }
            for (Object object : geoBone.childBones) {
                try {
                    if (d10 == 0.0) {
                        this.renderRecursively(bufferBuilder, (GeoBone)object, f10, f11, f12, f13);
                        continue;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
                this.a(bufferBuilder, (GeoBone)object, f10, f11, f12, f13, d10);
            }
        }
        try {
            MATRIX_STACK.pop();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
    }

    protected Vector4f a(float f10, float f11, float f12) {
        return new Vector4f(f10, f11, f12, 0.0f);
    }

    boolean b(String string) {
        try {
            if (!string.startsWith("armor")) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        return this.j instanceof e2;
    }

    protected Vector4f a(String string, float f10, float f11, float f12) {
        try {
            if (!string.startsWith("armor")) {
                return this.a(f10, f11, f12);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        try {
            if (!(this.j instanceof e2)) {
                return this.a(f10, f11, f12);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        try {
            if ((Integer)((em)this.j).m.func_187225_a(em.D) == 0) {
                return this.a(f10, f11, f12);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        GeoModelProvider geoModelProvider = this.getGeoModelProvider();
        try {
            if (!(geoModelProvider instanceof cv)) {
                return this.a(f10, f11, f12);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        cv cv2 = (cv)geoModelProvider;
        ItemStack itemStack = cv2.a((em)this.j, string);
        try {
            if (!(itemStack.func_77973_b() instanceof ItemArmor)) {
                return this.a(f10, f11, f12);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        ItemArmor itemArmor = (ItemArmor)itemStack.func_77973_b();
        ItemArmor.ArmorMaterial armorMaterial = itemArmor.func_82812_d();
        float f13 = 0.0f;
        switch (armorMaterial) {
            case GOLD: {
                f13 = 1.0f;
                break;
            }
            case CHAIN: 
            case IRON: {
                f13 = 2.0f;
                break;
            }
            case LEATHER: {
                f13 = 4.0f;
                int n2 = itemArmor.func_82814_b(itemStack);
                float f14 = (float)(n2 >> 16 & 0xFF) / 255.0f;
                float f15 = (float)(n2 >> 8 & 0xFF) / 255.0f;
                float f16 = (float)(n2 & 0xFF) / 255.0f;
                f10 *= f14;
                f11 *= f15;
                f12 *= f16;
            }
        }
        return new Vector4f(f10, f11, f12, 72.0f * f13 / 4096.0f);
    }

    public void a(T t2, float f10, float f11, float f12, float f13, float f14) {
        this.g = (Matrix4f)MATRIX_STACK.getModelMatrix().clone();
    }

    public void a(BufferBuilder bufferBuilder, GeoBone geoBone, float f10, float f11, float f12, float f13, double d10) {
        block16: {
            block17: {
                String string;
                block15: {
                    block14: {
                        try {
                            if (((em)this.j).field_70170_p instanceof gj) {
                                return;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        string = geoBone.getName();
                        try {
                            if (string.equals("weapon")) {
                                this.a(bufferBuilder, geoBone);
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        try {
                            try {
                                if (string.equals("ballL") || string.equals("ballR")) break block14;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d_.b(illegalStateException);
                            }
                            if (!string.equals("cock")) break block15;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                    }
                    f13 = 1.0f;
                }
                try {
                    this.a(bufferBuilder, geoBone.getName(), geoBone);
                    MATRIX_STACK.push();
                    MATRIX_STACK.translate(geoBone);
                    MATRIX_STACK.moveToPivot(geoBone);
                    MATRIX_STACK.rotate(geoBone);
                    MATRIX_STACK.scale(geoBone);
                    MATRIX_STACK.moveBackFromPivot(geoBone);
                    if (geoBone.isHidden) break block16;
                    if (this.p.contains(string)) break block17;
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
                for (GeoCube object : geoBone.childCubes) {
                    MATRIX_STACK.push();
                    GlStateManager.func_179094_E();
                    this.q = geoBone;
                    this.a(bufferBuilder, object, f10, f11, f12, f13, d10);
                    GlStateManager.func_179121_F();
                    MATRIX_STACK.pop();
                }
            }
            for (GeoBone geoBone2 : geoBone.childBones) {
                this.a(bufferBuilder, geoBone2, f10, f11, f12, f13, d10);
            }
        }
        MATRIX_STACK.pop();
    }

    protected boolean c() {
        boolean bl2;
        try {
            if (!((em)this.j).n()) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        try {
            bl2 = d_.i.field_71474_y.field_74320_O != 0;
        }
        catch (IllegalStateException illegalStateException) {
            throw d_.b(illegalStateException);
        }
        return bl2;
    }

    public void a(BufferBuilder bufferBuilder, GeoCube geoCube, float f10, float f11, float f12, float f13, double d10) {
        MATRIX_STACK.moveToPivot(geoCube);
        MATRIX_STACK.rotate(geoCube);
        MATRIX_STACK.moveBackFromPivot(geoCube);
        for (GeoQuad geoQuad : geoCube.quads) {
            Vector3f vector3f;
            block25: {
                block24: {
                    block23: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.func_177958_n(), (float)geoQuad.normal.func_177956_o(), (float)geoQuad.normal.func_177952_p());
                        try {
                            try {
                                try {
                                    MATRIX_STACK.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block23;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw d_.b(illegalStateException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block23;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d_.b(illegalStateException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block24;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d_.b(illegalStateException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block24;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block25;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block25;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
            }
            Vec3d vec3d = gx.a(this, this.q, new Vec3d((double)f10, (double)f11, (double)f12), vector3f);
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.func_181662_b((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).func_187315_a((double)geoVertex.textureU + d10, (double)geoVertex.textureV).func_181666_a((float)vec3d.field_72450_a, (float)vec3d.field_72448_b, (float)vec3d.field_72449_c, f13).func_181663_c(vector3f.getX(), vector3f.getY(), vector3f.getZ()).func_181675_d();
            }
        }
    }

    /*
     * Exception decompiling
     */
    protected ItemStack a() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Exception decompiling
     */
    protected void b(BufferBuilder var1_1, GeoBone var2_2) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 3[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    protected ItemStack a(@Nullable ItemStack itemStack) {
        return itemStack;
    }

    protected void a(BufferBuilder bufferBuilder, GeoBone geoBone) {
        ItemStack itemStack;
        block27: {
            e2 e22;
            block28: {
                block26: {
                    block25: {
                        try {
                            if (this.j == null) {
                                return;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        try {
                            if (!(this.j instanceof e2)) {
                                return;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        EntityDataManager entityDataManager = this.j.func_184212_Q();
                        e22 = (e2)this.j;
                        int n2 = (Integer)entityDataManager.func_187225_a(e2.M);
                        try {
                            if (e22.y() != fp.BOW) {
                                this.a = 0.0f;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        itemStack = null;
                        if (n2 == 1) {
                            itemStack = (ItemStack)entityDataManager.func_187225_a(e2.L);
                        } else if (n2 == 2) {
                            itemStack = (ItemStack)entityDataManager.func_187225_a(e2.R);
                        }
                        itemStack = this.a(itemStack);
                        try {
                            if (itemStack == null) {
                                return;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                        try {
                            try {
                                if (!itemStack.func_77973_b().equals(Items.field_151031_f) || e22.y() != fp.BOW) break block25;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d_.b(illegalStateException);
                            }
                            this.a += 0.015f;
                            e22.d(Math.round(-this.a * 20.0f + (float)itemStack.func_77988_m()));
                            e22.a(itemStack);
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d_.b(illegalStateException);
                        }
                    }
                    try {
                        GlStateManager.func_179094_E();
                        Tessellator.func_178181_a().func_78381_a();
                        com.trolmastercard.sexmod.p.a(MATRIX_STACK, geoBone);
                        GL11.glEnable((int)2896);
                        if (!(itemStack.func_77973_b() instanceof ItemBow)) break block26;
                        GL11.glRotatef((float)e22.K, (float)1.0f, (float)0.0f, (float)0.0f);
                        break block27;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                }
                try {
                    try {
                        if (e22.y() != fp.ATTACK || e22.S != 0) break block28;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d_.b(illegalStateException);
                    }
                    GlStateManager.func_179137_b((double)e22.V.field_72450_a, (double)e22.V.field_72448_b, (double)e22.V.field_72449_c);
                    GL11.glRotatef((float)e22.O, (float)1.0f, (float)0.0f, (float)0.0f);
                    break block27;
                }
                catch (IllegalStateException illegalStateException) {
                    throw d_.b(illegalStateException);
                }
            }
            GL11.glRotatef((float)e22.P, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        Minecraft.func_71410_x().func_175597_ag().func_178099_a(this.j, itemStack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
        this.func_110776_a(Objects.requireNonNull(this.getEntityTexture(this.j)));
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
        GL11.glDisable((int)2896);
        GlStateManager.func_179121_F();
    }

    /*
     * Exception decompiling
     */
    RayTraceResult a(Vec3d var1_1, Vec3d var2_2, World var3_3) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [54[UNCONDITIONALDOLOOP]], but top level block is 25[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    static {
        l = new HashMap();
    }

    private static Exception b(Exception exception) {
        return exception;
    }
}

