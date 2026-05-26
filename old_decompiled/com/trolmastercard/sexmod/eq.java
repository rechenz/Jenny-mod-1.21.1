/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteract
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ai;
import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.by;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.dg;
import com.trolmastercard.sexmod.dy;
import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.e4;
import com.trolmastercard.sexmod.eh;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.ew;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.fv;
import com.trolmastercard.sexmod.g5;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import com.trolmastercard.sexmod.m;
import com.trolmastercard.sexmod.s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class eq
extends ew
implements ai {
    public static final float aI = 2.0f;
    public static final DataParameter<String> ax = EntityDataManager.func_187226_a(eq.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(122);
    public static final DataParameter<Boolean> aA = EntityDataManager.func_187226_a(eq.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(126);
    int aJ = 0;
    int az = -1;
    int aG = 0;
    fp aw = fp.NULL;
    int aE = -1;
    boolean aC = false;
    boolean aB = true;
    boolean ay = true;
    boolean aF = false;
    boolean aH = false;
    String aD = "";

    public eq(World world) {
        super(world);
    }

    public eq(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float i() {
        return 0.9f;
    }

    @Override
    public at a(int n2) {
        return new fv();
    }

    @Override
    public String c(int n2) {
        return "textures/entity/kobold/hand.png";
    }

    @Override
    public Vec3i b(int n2) {
        String[] stringArray = eq.a(this);
        try {
            if (stringArray.length < 8) {
                return super.b(n2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        return by.values()[Integer.parseInt(stringArray[7])].a();
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        eh eh2 = eh.values()[this.func_70681_au().nextInt(eh.values().length)];
        this.m.func_187214_a(au, (Object)new BlockPos(eh2.a()));
        this.m.func_187214_a(as, (Object)e3.ax.name());
        this.m.func_187214_a(aA, (Object)false);
        this.m.func_187214_a(ax, (Object)"");
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("anal".equals(string)) {
                this.b(uUID);
                this.b(fp.NELSON_INTRO);
                this.a(this.ah(), fp.NELSON_INTRO);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            if ("paizuri".equals(string)) {
                this.b(uUID);
                this.b(fp.PAIZURI_START);
                this.a(this.ah(), fp.PAIZURI_START);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean b(EntityPlayer entityPlayer) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(this, entityPlayer, new String[]{"anal", "paizuri"}, null, false));
        return true;
    }

    @Override
    public EntityPlayer c(EntityPlayer entityPlayer) {
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return entityPlayer;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        EntityPlayer entityPlayer2 = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer2 == null) {
                return entityPlayer;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        return entityPlayer2;
    }

    @Override
    public boolean d() {
        boolean bl2;
        block5: {
            block4: {
                try {
                    try {
                        if (this.e() != null && Minecraft.func_71410_x().field_71439_g.getPersistentID().equals(this.m())) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eq.a(runtimeException);
                    }
                    bl2 = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw eq.a(runtimeException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    @Override
    public boolean z() {
        boolean bl2;
        UUID uUID = this.e();
        try {
            bl2 = uUID == null;
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        return bl2;
    }

    @Override
    public Vec3d c(Vec3d vec3d, float f10) {
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        Vec3d vec3d2 = entityPlayer.func_174791_d();
        Vec3d vec3d3 = new Vec3d(entityPlayer.field_70142_S, entityPlayer.field_70137_T, entityPlayer.field_70136_U);
        return b6.a(vec3d3, vec3d2, (double)f10);
    }

    void c(EntityPlayer entityPlayer) {
        try {
            if (this.y() != fp.NULL) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            if (this.e() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            if (e3.d(entityPlayer.getPersistentID())) {
                entityPlayer.func_146105_b((ITextComponent)new TextComponentString("you are already carrying a Goblin"), true);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        this.a(entityPlayer.getPersistentID());
        this.b(fp.PICK_UP);
        this.b(45);
        EntityPlayer entityPlayer2 = this.k();
        try {
            if (entityPlayer2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            entityPlayer2.func_189654_d(true);
            entityPlayer2.field_70145_X = true;
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer2);
    }

    @Override
    protected String a(StringBuilder stringBuilder) {
        e4.a(stringBuilder, 3);
        e4.a(stringBuilder, 2);
        e4.a(stringBuilder, 2);
        e4.a(stringBuilder, 7);
        e4.a(stringBuilder, 7);
        e4.a(stringBuilder, 5);
        e4.a(stringBuilder, g5.values().length - 1);
        e4.a(stringBuilder, by.values().length - 1);
        e4.a(stringBuilder, eh.values().length - 1);
        e4.c(stringBuilder, 0);
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> D() {
        return new ArrayList<Integer>(){
            {
                this.add(4);
                this.add(3);
                this.add(3);
                this.add(16);
                this.add(16);
                this.add(6);
                this.add(g5.values().length);
                this.add(by.values().length);
                this.add(eh.values().length);
            }
        };
    }

    @Override
    public List<Integer> u() {
        return Collections.singletonList(2);
    }

    @Override
    protected void a() {
        dg.e();
        dy.c();
    }

    public float func_70047_e() {
        return 0.75f;
    }

    @Override
    public boolean o() {
        boolean bl2;
        block5: {
            block4: {
                try {
                    try {
                        if (!this.Q() && this.e() == null) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eq.a(runtimeException);
                    }
                    bl2 = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw eq.a(runtimeException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    @Override
    public boolean a(fp fp2, EntityPlayer entityPlayer) {
        float f10;
        float f11;
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        EntityPlayer entityPlayer2 = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer2 == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        float f12 = entityPlayer.field_70177_z;
        try {
            f11 = fp2 == fp.PICK_UP ? 180.0f : 0.0f;
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        float f13 = f11;
        float f14 = entityPlayer2.field_70177_z - 90.0f + f13;
        float f15 = entityPlayer2.field_70177_z + 90.0f + f13;
        try {
            if (f12 < f14) {
                entityPlayer.field_70177_z = f14;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            if (f12 > f15) {
                entityPlayer.field_70177_z = f15;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        float f16 = entityPlayer.field_70125_A;
        try {
            f10 = fp2 == fp.PICK_UP ? 0.0f : 37.5f;
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        float f17 = f10;
        try {
            if (f16 > f17) {
                entityPlayer.field_70125_A = f17;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        return true;
    }

    @Override
    public Vec3d b(Vec3d vec3d, float f10) {
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        float f11 = b6.a(entityPlayer.field_70760_ar, entityPlayer.field_70761_aq, f10);
        Vec3d vec3d2 = vec3d;
        float f12 = 135.0f;
        fp fp2 = this.y();
        if (fp2 == fp.PICK_UP) {
            vec3d2 = new Vec3d(vec3d.field_72450_a, vec3d.field_72448_b, -vec3d.field_72449_c);
            f12 += 40.0f;
        } else if (fp2 != fp.START_THROWING) {
            vec3d2 = vec3d2.func_178786_a(0.0, 2.0, 0.0);
        }
        vec3d2 = ck.a(vec3d2, f11 + f12);
        return vec3d2;
    }

    @SideOnly(value=Side.CLIENT)
    void f() {
        block7: {
            EntityPlayer entityPlayer = this.k();
            try {
                if (entityPlayer == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eq.a(runtimeException);
            }
            try {
                try {
                    if (this.y() != fp.START_THROWING) break block7;
                    entityPlayer.field_70128_L = false;
                    if (this.field_70170_p.field_72996_f.contains(entityPlayer)) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw eq.a(runtimeException);
                }
                this.field_70170_p.func_72838_d((Entity)entityPlayer);
            }
            catch (RuntimeException runtimeException) {
                throw eq.a(runtimeException);
            }
        }
    }

    @Override
    public void func_70071_h_() {
        try {
            e3.e(this);
            this.d();
            this.j();
            super.func_70071_h_();
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        this.f();
        fp fp2 = this.y();
        this.d(fp2);
        this.c(fp2);
        this.aw = fp2;
    }

    @Override
    public boolean E() {
        boolean bl2;
        try {
            bl2 = this.e() != null;
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        return bl2;
    }

    void j() {
        block16: {
            fp fp2 = this.y();
            try {
                if (fp2 == fp.THROWN) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eq.a(runtimeException);
            }
            try {
                try {
                    if (fp2 != fp.START_THROWING || this.a() <= 15) break block16;
                }
                catch (RuntimeException runtimeException) {
                    throw eq.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw eq.a(runtimeException);
            }
        }
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        EntityPlayer entityPlayer2 = this.k();
        try {
            if (entityPlayer2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        entityPlayer2.field_70145_X = true;
        entityPlayer2.func_189654_d(true);
        entityPlayer2.func_70107_b(entityPlayer.field_70165_t, entityPlayer.field_70163_u + 2.0, entityPlayer.field_70161_v);
    }

    void d() {
        eq eq2 = this;
        int n2 = eq2.a();
        try {
            if (n2 == -1) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        eq2.c(++n2);
        EntityPlayer entityPlayer = this.k();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        if (n2 == 15) {
            float f10;
            float f11;
            block17: {
                Vec3d vec3d = e3.b(this);
                f11 = e3.d(this);
                f10 = e3.c(this);
                try {
                    try {
                        if (!this.field_70170_p.field_72995_K || !this.f()) break block17;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eq.a(runtimeException);
                    }
                    d3.a(true);
                }
                catch (RuntimeException runtimeException) {
                    throw eq.a(runtimeException);
                }
            }
            Vec3d vec3d = e3.a(new Vec3d(0.0, 0.0, 1.5), f11, f10);
            try {
                entityPlayer.field_70159_w = vec3d.field_72450_a;
                entityPlayer.field_70181_x = vec3d.field_72448_b;
                entityPlayer.field_70179_y = vec3d.field_72449_c;
                if (!this.field_70170_p.field_72995_K) {
                    this.b(f10);
                }
            }
            catch (RuntimeException runtimeException) {
                throw eq.a(runtimeException);
            }
        }
        try {
            entityPlayer.field_70145_X = false;
            entityPlayer.func_189654_d(false);
            if (n2 == 39) {
                this.c(-1);
                this.b(fp.THROWN);
                this.e((UUID)null);
                this.a((UUID)null);
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
    }

    @Override
    public void func_70619_bc() {
        super.func_70619_bc();
        e3.a(this);
        this.o();
        this.e();
    }

    void e() {
        try {
            if (this.y() != fp.STAND_UP) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            if (++this.aJ < 37) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        this.aJ = 0;
        this.b(fp.NULL);
    }

    void o() {
        try {
            if (this.y() != fp.THROWN) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.k();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            if (!entityPlayer.field_70122_E) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        int n2 = this.d() + 1;
        try {
            this.a(n2);
            if (n2 < 30) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        this.a(0);
        this.b(fp.STAND_UP);
    }

    @Override
    @Nullable
    public UUID e() {
        String string = (String)this.m.func_187225_a(ax);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (Exception exception) {
            throw eq.a(exception);
        }
        try {
            return UUID.fromString((String)this.m.func_187225_a(ax));
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void a(UUID uUID) {
        try {
            if (uUID == null) {
                this.m.func_187227_b(ax, (Object)"");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        this.m.func_187227_b(ax, (Object)uUID.toString());
    }

    public EntityPlayer r() {
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        return this.field_70170_p.func_152378_a(uUID);
    }

    @Override
    public void c(int n2) {
        this.az = n2;
    }

    @Override
    public int a() {
        return this.az;
    }

    @Override
    public void a(int n2) {
        this.aG = n2;
    }

    @Override
    public int d() {
        return this.aG;
    }

    @Override
    public void a(fp fp2) {
        this.aw = fp2;
    }

    @Override
    public fp b() {
        return this.aw;
    }

    @Override
    public void b(int n2) {
        this.aE = n2;
    }

    @Override
    public int c() {
        return this.aE;
    }

    @Override
    public void g() {
        try {
            super.g();
            this.m.func_187227_b(aA, (Object)false);
            if (this.e() == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        this.a((UUID)null);
        EntityPlayer entityPlayer = this.k();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        ge.b.sendTo((IMessage)new gz(true), (EntityPlayerMP)entityPlayer);
    }

    @SideOnly(value=Side.CLIENT)
    void c(fp fp2) {
        block4: {
            try {
                try {
                    if (fp2 != fp.NELSON_FAST || this.aw == fp.NELSON_FAST) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw eq.a(runtimeException);
                }
                this.aF = false;
            }
            catch (RuntimeException runtimeException) {
                throw eq.a(runtimeException);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    void d(fp fp2) {
        Minecraft minecraft = Minecraft.func_71410_x();
        try {
            if (!minecraft.field_71439_g.getPersistentID().equals(this.ae())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            if (minecraft.field_71474_y.field_74320_O != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        try {
            switch (fp2) {
                case NELSON_CUM: 
                case NELSON_FAST: 
                case NELSON_INTRO: 
                case NELSON_SLOW: {
                    minecraft.field_71474_y.field_74320_O = 2;
                    break;
                }
                default: {
                    return;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
    }

    @Override
    public void a(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int n2 : list) {
            e4.c(stringBuilder, n2);
        }
        e4.c(stringBuilder, 1);
        this.m.func_187227_b(at, (Object)stringBuilder.toString());
    }

    /*
     * Exception decompiling
     */
    @Override
    @Nullable
    protected fp c(fp var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 1[SWITCH]
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

    @Override
    public void b(fp fp2) {
        block41: {
            fp fp3;
            block40: {
                block39: {
                    block37: {
                        block35: {
                            block33: {
                                fp3 = this.y();
                                try {
                                    block34: {
                                        try {
                                            try {
                                                if (fp3 != fp.PAIZURI_CUM) break block33;
                                                if (fp2 == fp.PAIZURI_SLOW) break block34;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw eq.a(runtimeException);
                                            }
                                            if (fp2 != fp.PAIZURI_FAST) break block33;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw eq.a(runtimeException);
                                        }
                                    }
                                    return;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw eq.a(runtimeException);
                                }
                            }
                            try {
                                block36: {
                                    try {
                                        try {
                                            if (fp3 != fp.NELSON_CUM) break block35;
                                            if (fp2 == fp.NELSON_SLOW) break block36;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw eq.a(runtimeException);
                                        }
                                        if (fp2 != fp.NELSON_FAST) break block35;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw eq.a(runtimeException);
                                    }
                                }
                                return;
                            }
                            catch (RuntimeException runtimeException) {
                                throw eq.a(runtimeException);
                            }
                        }
                        try {
                            block38: {
                                try {
                                    try {
                                        if (fp3 != fp.BREEDING_CUM_0) break block37;
                                        if (fp2 == fp.BREEDING_SLOW_0) break block38;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw eq.a(runtimeException);
                                    }
                                    if (fp2 != fp.BREEDING_FAST_0) break block37;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw eq.a(runtimeException);
                                }
                            }
                            return;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eq.a(runtimeException);
                        }
                    }
                    try {
                        try {
                            if (fp2 != fp.PAIZURI_START || this.field_70170_p.field_72995_K) break block39;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eq.a(runtimeException);
                        }
                        this.m();
                    }
                    catch (RuntimeException runtimeException) {
                        throw eq.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (fp2 != fp.NELSON_INTRO || this.field_70170_p.field_72995_K) break block40;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eq.a(runtimeException);
                    }
                    this.q();
                }
                catch (RuntimeException runtimeException) {
                    throw eq.a(runtimeException);
                }
            }
            try {
                if (fp2 == fp.NELSON_CUM) {
                    this.m.func_187227_b(aA, (Object)true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw eq.a(runtimeException);
            }
            try {
                try {
                    if (fp3 != fp.NELSON_CUM || fp2 == fp.NELSON_CUM) break block41;
                }
                catch (RuntimeException runtimeException) {
                    throw eq.a(runtimeException);
                }
                this.m.func_187227_b(aA, (Object)false);
            }
            catch (RuntimeException runtimeException) {
                throw eq.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    void q() {
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        this.b(entityPlayer.field_70177_z);
        this.field_70145_X = true;
        this.func_189654_d(true);
        entityPlayer.func_189654_d(true);
        entityPlayer.field_70145_X = true;
        entityPlayer.func_70634_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v - 1.0);
    }

    void m() {
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        this.b(entityPlayer.field_70177_z + 180.0f);
        this.field_70145_X = true;
        this.func_189654_d(true);
        entityPlayer.func_189654_d(true);
        entityPlayer.field_70145_X = true;
        entityPlayer.func_70634_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u - 0.5, entityPlayer.field_70161_v - (double)0.6f);
        entityPlayer.field_70125_A = 70.0f;
        entityPlayer.field_70127_C = 70.0f;
    }

    @Override
    public boolean l() {
        boolean bl2;
        try {
            bl2 = this.e() == null;
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        return bl2;
    }

    @Override
    public void b(EntityPlayer entityPlayer) {
        try {
            if (!entityPlayer.getPersistentID().equals(this.e())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        s.a.a(this);
        this.a(false);
        this.b(fp.NULL);
        this.a((UUID)null);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected fp a(fp var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 1[SWITCH]
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
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 23[SWITCH]
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

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.C == null) {
                this.p();
            }
        }
        catch (RuntimeException runtimeException) {
            throw eq.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 33[SWITCH]
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        };
        this.C.registerSoundListener(iSoundListener);
        this.E.transitionLengthTicks = 2.0;
        animationData.addAnimationController(this.C);
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static class a {
        HashSet<EntityPlayer> a = new HashSet();

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(RenderHandEvent renderHandEvent) {
            ei ei2 = ei.g((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
            try {
                if (ei2 == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            try {
                if (!(ei2 instanceof ai)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            try {
                if (((ai)((Object)ei2)).e() != null) {
                    renderHandEvent.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
        }

        @SubscribeEvent
        public void a(TickEvent.PlayerTickEvent playerTickEvent) {
            EntityPlayer entityPlayer = playerTickEvent.player;
            try {
                if (entityPlayer == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            this.a(entityPlayer);
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(TickEvent.RenderTickEvent renderTickEvent) {
            try {
                if (renderTickEvent.phase == TickEvent.Phase.END) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
            try {
                if (entityPlayerSP == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            this.a((EntityPlayer)entityPlayerSP);
        }

        void a(EntityPlayer entityPlayer) {
            ei ei2;
            block16: {
                ei2 = ei.g(entityPlayer);
                try {
                    if (!(ei2 instanceof eq)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                }
                fp fp2 = ei2.y();
                try {
                    if (fp2 == fp.THROWN) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                }
                try {
                    try {
                        if (fp2 != fp.START_THROWING || ((ai)((Object)ei2)).a() <= 15) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                }
            }
            UUID uUID = ((eq)ei2).e();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            EntityPlayer entityPlayer2 = entityPlayer.field_70170_p.func_152378_a(uUID);
            try {
                if (entityPlayer2 == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            entityPlayer.field_70145_X = true;
            entityPlayer.func_189654_d(true);
            ei2.field_70145_X = true;
            ei2.func_189654_d(true);
            entityPlayer.func_70107_b(entityPlayer2.field_70165_t, entityPlayer2.field_70163_u + 2.0, entityPlayer2.field_70161_v);
            entityPlayer.field_70142_S = entityPlayer2.field_70142_S;
            entityPlayer.field_70137_T = entityPlayer2.field_70137_T + 2.0;
            entityPlayer.field_70136_U = entityPlayer2.field_70136_U;
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(RenderWorldLastEvent renderWorldLastEvent) {
            Minecraft minecraft = Minecraft.func_71410_x();
            RenderManager renderManager = minecraft.func_175598_ae();
            EntityPlayerSP entityPlayerSP = minecraft.field_71439_g;
            try {
                if (minecraft.field_71439_g == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            Vec3d vec3d = entityPlayerSP.func_174791_d();
            for (EntityPlayer entityPlayer : this.a) {
                Vec3d vec3d2 = entityPlayer.func_174791_d();
                Vec3d vec3d3 = vec3d2.func_178788_d(vec3d);
                renderManager.func_188391_a((Entity)entityPlayer, vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c, 69.0f, renderWorldLastEvent.getPartialTicks(), true);
            }
            GlStateManager.func_179145_e();
            GlStateManager.func_179126_j();
            GlStateManager.func_179141_d();
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void b(TickEvent.RenderTickEvent renderTickEvent) {
            block3: {
                block2: {
                    try {
                        if (renderTickEvent.phase != TickEvent.Phase.START) break block2;
                        this.b();
                        break block3;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                    }
                }
                this.a();
            }
        }

        @SideOnly(value=Side.CLIENT)
        void a() {
            for (EntityPlayer entityPlayer : this.a) {
                entityPlayer.field_70128_L = true;
            }
        }

        @SideOnly(value=Side.CLIENT)
        void b() {
            this.a.clear();
            Minecraft minecraft = Minecraft.func_71410_x();
            EntityPlayerSP entityPlayerSP = minecraft.field_71439_g;
            try {
                if (minecraft.field_71441_e == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            for (EntityPlayer entityPlayer : minecraft.field_71441_e.field_73010_i) {
                block17: {
                    try {
                        if (entityPlayer == entityPlayerSP) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                    }
                    ei ei2 = ei.g(entityPlayer);
                    try {
                        if (!(ei2 instanceof eq)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                    }
                    eq eq2 = (eq)ei2;
                    try {
                        if (eq2.e() == null) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                    }
                    fp fp2 = eq2.y();
                    try {
                        try {
                            if (fp2 != fp.THROWN && fp2 != fp.START_THROWING) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
                    }
                }
                this.a.add(entityPlayer);
                entityPlayer.field_70128_L = false;
            }
        }

        @SubscribeEvent
        public void a(PlayerInteractEvent.EntityInteract entityInteract) {
            EntityPlayer entityPlayer = entityInteract.getEntityPlayer();
            try {
                if (!entityPlayer.func_70093_af()) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            try {
                if (!(entityInteract.getTarget() instanceof EntityPlayer)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            ei ei2 = ei.d(entityInteract.getTarget().getPersistentID());
            try {
                if (!(ei2 instanceof eq)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            ei ei3 = ei.d(entityPlayer.getPersistentID());
            try {
                if (ei3 != null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.eq$a.a(runtimeException);
            }
            ((eq)ei2).c(entityInteract.getEntityPlayer());
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

