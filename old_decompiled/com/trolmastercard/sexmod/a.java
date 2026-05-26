/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ClientProxy;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.cy;
import com.trolmastercard.sexmod.e1;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fw;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gq;
import com.trolmastercard.sexmod.gw;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class a
extends GuiScreen {
    public static final ResourceLocation k = new ResourceLocation("sexmod", "textures/gui/clothing_icons.png");
    static final int r = 20;
    static final float j = 0.25f;
    int n = 0;
    int l = 0;
    float o = 0.0f;
    public static float b = 0.0f;
    protected static List<Integer> a = new ArrayList<Integer>();
    protected static int s = 0;
    protected static int h = 0;
    em c;
    boolean p = false;
    gq q;
    public static List<Map.Entry<gw, Map.Entry<List<String>, Integer>>> m = new ArrayList<Map.Entry<gw, Map.Entry<List<String>, Integer>>>();
    final UUID g;
    int i;
    int t;
    public boolean f = false;
    int d = 0;
    int e = 1;

    public a(@Nonnull em em2) {
        Object object;
        this.field_146297_k = Minecraft.func_71410_x();
        this.g = em2.f();
        fy fy2 = fy.a((Entity)em2);
        if (fy2 == null) {
            fy2 = fy.JENNY;
        }
        try {
            object = fy2.npcClass.getConstructor(World.class);
            this.c = (em)((Constructor)object).newInstance(this.field_146297_k.field_71441_e);
            this.c.b(true);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        this.e();
        object = em2.C();
        this.c.func_184212_Q().func_187227_b(em.b, object);
        int n2 = 0;
        for (String string : this.c.Y()) {
            Map.Entry<gw, Map.Entry<List<String>, Integer>> entry;
            block15: {
                gw gw2;
                block14: {
                    gw2 = br.e(string);
                    try {
                        if (gw.CUSTOM_BONE.equals((Object)gw2)) {
                            ++n2;
                        }
                    }
                    catch (Exception exception) {
                        throw com.trolmastercard.sexmod.a.a(exception);
                    }
                    entry = null;
                    try {
                        if (!gw.CUSTOM_BONE.equals((Object)gw2) || n2 <= 1) break block14;
                    }
                    catch (Exception exception) {
                        throw com.trolmastercard.sexmod.a.a(exception);
                    }
                    entry = com.trolmastercard.sexmod.a.b(this.c);
                    break block15;
                }
                for (Map.Entry<gw, Map.Entry<List<String>, Integer>> entry2 : m) {
                    if (!entry2.getKey().equals((Object)gw2)) continue;
                    entry = entry2;
                }
            }
            try {
                if (entry == null) {
                    continue;
                }
            }
            catch (Exception exception) {
                throw com.trolmastercard.sexmod.a.a(exception);
            }
            m.remove(entry);
            int n3 = entry.getValue().getKey().indexOf(string);
            if (n3 == -1) {
                n3 = 0;
            }
            entry.getValue().setValue(n3);
            m.add(entry);
        }
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        this.q.func_178039_p();
    }

    public static HashSet<String> b() {
        HashSet<String> hashSet = new HashSet<String>();
        for (Map.Entry<gw, Map.Entry<List<String>, Integer>> entry : m) {
            try {
                if (entry.getValue().getKey().size() == 1) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.a.a(runtimeException);
            }
            Map.Entry<List<String>, Integer> entry2 = entry.getValue();
            List<String> list = entry2.getKey();
            Integer n2 = entry2.getValue();
            hashSet.add(list.get(n2));
        }
        return hashSet;
    }

    public static Map.Entry<gw, Map.Entry<List<String>, Integer>> b(em em2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("cross");
        arrayList.addAll((Collection)br.a(em2).get((Object)gw.CUSTOM_BONE));
        return new AbstractMap.SimpleEntry<gw, Map.Entry<List<String>, Integer>>(gw.CUSTOM_BONE, new AbstractMap.SimpleEntry(arrayList, 0));
    }

    void e() {
        m.clear();
        List<Map.Entry<gw, Map.Entry<List<String>, Integer>>> list = this.c.d(this.g);
        this.i = list.size();
        m.addAll(list);
        for (gw gw2 : gw.values()) {
            try {
                if (gw2 == gw.GIRL_SPECIFIC) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.a.a(runtimeException);
            }
            ArrayList<String> object2 = new ArrayList<String>();
            object2.add("cross");
            m.add(new AbstractMap.SimpleEntry(gw2, new AbstractMap.SimpleEntry(object2, 0)));
        }
        for (Map.Entry entry : br.a(this.c).entrySet()) {
            Map.Entry<gw, Map.Entry<List<String>, Integer>> object = null;
            for (Map.Entry<gw, Map.Entry<List<String>, Integer>> entry2 : m) {
                if (!((gw)((Object)entry.getKey())).equals((Object)entry2.getKey())) continue;
                object = entry2;
            }
            try {
                if (object == null) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.a.a(runtimeException);
            }
            int n2 = m.indexOf(object);
            m.remove(object);
            object.getValue().getKey().addAll((Collection)entry.getValue());
            m.add(n2, object);
        }
    }

    public void func_73866_w_() {
        this.q = new gq(this.field_146297_k, this);
    }

    public void func_146280_a(Minecraft minecraft, int n2, int n3) {
        super.func_146280_a(minecraft, n2, n3);
        this.n = this.a(76.0f);
        this.l = this.b(89.0f);
        this.o = 90.0f;
    }

    boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
        try {
            if (n2 < n4) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            if (n2 > n6) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            if (n3 < n5) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            if (n3 > n7) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        return true;
    }

    public void func_73863_a(int n2, int n3, float f10) {
        int n4;
        int n5;
        int n6;
        int n7;
        a a10;
        try {
            super.func_73863_a(n2, n3, f10);
            if (this.p) {
                b += b6.a((float)h, (float)s, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        this.a();
        this.field_146297_k.field_71446_o.func_110577_a(k);
        int n8 = this.n - this.a(15.0f);
        int n9 = this.l - 20;
        try {
            a10 = this;
            n7 = n8;
            n6 = n9;
            n5 = 100;
            n4 = this.a(n2, n3, n8, n9, n8 + 20, n9 + 20) ? 40 : 20;
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            a10.func_73729_b(n7, n6, n5, n4, 20, 20);
            if (br.g() == null) {
                this.b(n8, n2, n3);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        this.a(this.n, this.l, this.o, (EntityLivingBase)this.c, 1.2345679f);
        this.c.func_70071_h_();
        this.q.func_148128_a(n2, n3, f10);
    }

    void b(int n2, int n3, int n4) {
        int n5;
        int n6;
        int n7;
        int n8;
        a a10;
        int n9;
        int n10;
        int n11;
        int n12;
        a a11;
        int n13;
        int n14;
        int n15;
        int n16;
        a a12;
        int n17 = this.l - 40;
        try {
            a12 = this;
            n16 = n2;
            n15 = n17;
            n14 = 120;
            n13 = this.a(n3, n4, n2, n17, n2 + 20, n17 + 20) ? 40 : 20;
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            a12.func_73729_b(n16, n15, n14, n13, 20, 20);
            a11 = this;
            n12 = n2;
            n11 = n17 -= 20;
            n10 = 20;
            n9 = this.a(n3, n4, n2, n17, n2 + 20, n17 + 20) ? 170 : 150;
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            a11.func_73729_b(n12, n11, n10, n9, 20, 20);
            a10 = this;
            n8 = n2;
            n7 = n17 -= 20;
            n6 = 0;
            n5 = this.a(n3, n4, n2, n17, n2 + 20, n17 + 20) ? 170 : 150;
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        a10.func_73729_b(n8, n7, n6, n5, 20, 20);
    }

    public boolean func_73868_f() {
        return false;
    }

    void c() {
        this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187909_gi, (float)1.0f));
        HashSet<String> hashSet = new HashSet<String>();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (Map.Entry<gw, Map.Entry<List<String>, Integer>> entry : m) {
            try {
                if (entry.getKey() == gw.GIRL_SPECIFIC) {
                    arrayList.add(entry.getValue().getValue());
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.a.a(runtimeException);
            }
            Map.Entry<List<String>, Integer> entry2 = entry.getValue();
            Integer n2 = entry2.getValue();
            try {
                if (n2 == 0) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.a.a(runtimeException);
            }
            String string = entry2.getKey().get(n2);
            hashSet.add(string);
        }
        ge.b.sendToServer((IMessage)new fw(em.a(hashSet), this.g, arrayList));
        this.field_146297_k.field_71439_g.func_71053_j();
    }

    public void a(gw gw2, boolean bl2, int n2) {
        int n3;
        int n4;
        Object object;
        block20: {
            int n5;
            block19: {
                block22: {
                    int n6;
                    ArrayList<Integer> arrayList;
                    ArrayList<Map.Entry<gw, Map.Entry<List<String>, Integer>>> arrayList2;
                    block23: {
                        block18: {
                            block21: {
                                this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187909_gi, (float)1.0f));
                                arrayList2 = new ArrayList<Map.Entry<gw, Map.Entry<List<String>, Integer>>>();
                                arrayList = new ArrayList<Integer>();
                                int n7 = 0;
                                for (Map.Entry<gw, Map.Entry<List<String>, Integer>> entry : m) {
                                    try {
                                        if (entry.getKey().equals((Object)gw2)) {
                                            arrayList2.add(entry);
                                            arrayList.add(n7);
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw com.trolmastercard.sexmod.a.a(runtimeException);
                                    }
                                    ++n7;
                                }
                                try {
                                    if (arrayList2.size() == 0) {
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.a.a(runtimeException);
                                }
                                if (arrayList2.size() != 1) break block21;
                                object = (Map.Entry)arrayList2.get(0);
                                n4 = (Integer)arrayList.get(0);
                                break block22;
                            }
                            try {
                                if (this.i != 0 && n2 <= this.i - 1 + gw.a()) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.a.a(runtimeException);
                            }
                            n6 = n2 - (this.i + gw.a());
                            break block23;
                        }
                        n6 = n2;
                    }
                    object = (Map.Entry)arrayList2.get(n6);
                    n4 = (Integer)arrayList.get(n6);
                }
                try {
                    if (object == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.a.a(runtimeException);
                }
                Map.Entry entry = (Map.Entry)object.getValue();
                n3 = (Integer)entry.getValue();
                n5 = ((List)entry.getKey()).size();
                try {
                    if (!bl2) break block19;
                    if (++n3 < n5) break block20;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.a.a(runtimeException);
                }
                n3 = 0;
                break block20;
            }
            if (--n3 < 0) {
                n3 = n5 - 1;
            }
        }
        m.set(n4, new AbstractMap.SimpleEntry(object.getKey(), new AbstractMap.SimpleEntry(((Map.Entry)object.getValue()).getKey(), n3)));
        ArrayList<Map.Entry<gw, Map.Entry<List<String>, Integer>>> arrayList = new ArrayList<Map.Entry<gw, Map.Entry<List<String>, Integer>>>();
        for (Map.Entry<gw, Map.Entry<List<String>, Integer>> entry : m) {
            try {
                if (entry.getKey() != gw.GIRL_SPECIFIC) continue;
                arrayList.add(entry);
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.a.a(runtimeException);
            }
        }
        this.c.b(arrayList);
    }

    public void a(int n2, int n3, float f10, cy cy2) {
        this.a(n2, n3, f10, cy2, 1.876945f);
    }

    public void a(cy cy2) {
        int n2;
        float f10;
        cy cy3;
        float f11;
        int n3;
        int n4;
        a a10;
        try {
            a10 = this;
            n4 = this.n;
            n3 = this.l;
            f11 = this.o;
            cy3 = cy2;
            f10 = 2.876945f;
            n2 = cy2.f ? 1 : 0;
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        a10.a(n4, n3, f11, cy3, f10, n2);
    }

    public void a(String string, int n2, int n3) {
        this.func_146279_a(string, n2, n3);
    }

    protected void func_146273_a(int n2, int n3, int n4, long l2) {
        try {
            super.func_146273_a(n2, n3, n4, l2);
            if (n4 != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            if (n2 < this.field_146294_l / 2) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        int n5 = n2 - this.t;
        a.add(n5);
        this.t = n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void func_73864_a(int n2, int n3, int n4) throws IOException {
        try {
            super.func_73864_a(n2, n3, n4);
            this.q.func_148179_a(n2, n3, n4);
            if (n4 != 0) {
                return;
            }
        }
        catch (URISyntaxException uRISyntaxException) {
            throw com.trolmastercard.sexmod.a.a(uRISyntaxException);
        }
        this.f = true;
        this.p = true;
        this.t = n2;
        int n5 = this.n - this.a(15.0f);
        int n6 = this.l - 20;
        try {
            if (this.a(n2, n3, n5, n6, n5 + 20, n6 + 20)) {
                this.c();
            }
        }
        catch (URISyntaxException uRISyntaxException) {
            throw com.trolmastercard.sexmod.a.a(uRISyntaxException);
        }
        try {
            if (br.g() != null) {
                return;
            }
        }
        catch (URISyntaxException uRISyntaxException) {
            throw com.trolmastercard.sexmod.a.a(uRISyntaxException);
        }
        n6 = this.l - 40;
        if (this.a(n2, n3, n5, n6, n5 + 20, n6 + 20)) {
            this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187909_gi, (float)1.0f));
            this.field_146297_k.field_71439_g.func_71053_j();
            int n7 = br.b(true);
            try {
                if (n7 != 0) {
                    br.d = true;
                    return;
                }
            }
            catch (URISyntaxException uRISyntaxException) {
                throw com.trolmastercard.sexmod.a.a(uRISyntaxException);
            }
            em em2 = em.b(this.g);
            try {
                if (em2 == null) return;
                com.trolmastercard.sexmod.a.a(em2);
                return;
            }
            catch (URISyntaxException uRISyntaxException) {
                throw com.trolmastercard.sexmod.a.a(uRISyntaxException);
            }
        }
        try {
            if (this.a(n2, n3, n5, n6 -= 20, n5 + 20, n6 + 20)) {
                Desktop.getDesktop().open(new File(br.d()));
                return;
            }
        }
        catch (URISyntaxException uRISyntaxException) {
            throw com.trolmastercard.sexmod.a.a(uRISyntaxException);
        }
        try {
            if (!this.a(n2, n3, n5, n6 -= 20, n5 + 20, n6 + 20)) return;
            try {
                Desktop.getDesktop().browse(new URI("http://fapcraft.org/assets/video/tutorial/girl_wand.mp4"));
                return;
            }
            catch (URISyntaxException uRISyntaxException) {
                throw new RuntimeException(uRISyntaxException);
            }
        }
        catch (IOException iOException) {
            throw com.trolmastercard.sexmod.a.a(iOException);
        }
    }

    protected void func_146286_b(int n2, int n3, int n4) {
        try {
            super.func_146286_b(n2, n3, n4);
            if (n4 == 0) {
                this.p = false;
                this.f = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        this.d = h;
    }

    int a(float f10) {
        return Math.round((float)this.field_146294_l * (f10 / 100.0f));
    }

    int b(float f10) {
        return Math.round((float)this.field_146295_m * (f10 / 100.0f));
    }

    public void func_146281_b() {
        super.func_146281_b();
        this.c.field_70170_p.func_72973_f((Entity)this.c);
        a.clear();
        m.clear();
    }

    public em d() {
        return this.c;
    }

    public void a(int n2, int n3, int n4, int n5) {
        this.field_146297_k.field_71446_o.func_110577_a(k);
        this.func_73729_b(n2, n3, n4, n5, 20, 20);
    }

    public void a(int n2, int n3, int n4) {
        this.a(n2, n3, n4, 0);
    }

    public void a(int n2, int n3, e1 e12) {
        this.a(n2, n3, e12.c, e12.b);
    }

    void a(int n2, int n3, float f10, EntityLivingBase entityLivingBase, float f11) {
        this.a(n2, n3, f10, entityLivingBase, f11, 0);
    }

    void a(int n2, int n3, float f10, EntityLivingBase entityLivingBase, float f11, int n4) {
        float f12 = entityLivingBase.field_70761_aq;
        float f13 = entityLivingBase.field_70177_z;
        float f14 = entityLivingBase.field_70125_A;
        float f15 = entityLivingBase.field_70758_at;
        float f16 = entityLivingBase.field_70759_as;
        entityLivingBase.field_70761_aq = 0.0f;
        entityLivingBase.field_70177_z = 0.0f;
        entityLivingBase.field_70125_A = 0.0f;
        entityLivingBase.field_70758_at = 0.0f;
        entityLivingBase.field_70759_as = 0.0f;
        GlStateManager.func_179142_g();
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)n2, (float)n3, (float)50.0f);
        GlStateManager.func_179152_a((float)(-f10), (float)f10, (float)f10);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GlStateManager.func_179114_b((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)n4);
        GlStateManager.func_179114_b((float)b, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)0.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
        renderManager.func_178631_a(180.0f);
        renderManager.func_178633_a(false);
        renderManager.func_188391_a((Entity)entityLivingBase, 0.0, 0.0, 0.0, 0.0f, f11, false);
        renderManager.func_178633_a(true);
        GlStateManager.func_179121_F();
        RenderHelper.func_74518_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179090_x();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        entityLivingBase.field_70761_aq = f12;
        entityLivingBase.field_70177_z = f13;
        entityLivingBase.field_70125_A = f14;
        entityLivingBase.field_70758_at = f15;
        entityLivingBase.field_70759_as = f16;
    }

    void a() {
        int n2;
        try {
            if (this.p) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        float f10 = Minecraft.func_175610_ah();
        if (f10 == 0.0f) {
            f10 = 0.1f;
        }
        try {
            if (this.d == 0) {
                b += (float)(this.e * 10) / f10;
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            b += (float)this.d / f10;
            this.d = (int)((float)this.d * (1.0f - 0.25f / f10));
            if (Math.abs(this.d) > 10) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        try {
            a a10 = this;
            n2 = this.d > 0 ? 1 : -1;
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        a10.e = n2;
        this.d = 0;
    }

    @SideOnly(value=Side.CLIENT)
    public static void a(@Nonnull em em2) {
        boolean bl2;
        Minecraft minecraft;
        block11: {
            block10: {
                minecraft = Minecraft.func_71410_x();
                try {
                    if (minecraft.field_71462_r instanceof a) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.a.a(runtimeException);
                }
                try {
                    try {
                        if (br.g() != null && !br.b()) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.a.a(runtimeException);
                    }
                    bl2 = true;
                    break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.a.a(runtimeException);
                }
            }
            bl2 = false;
        }
        boolean bl3 = bl2;
        try {
            if (!bl3) {
                minecraft.field_71439_g.func_146105_b((ITextComponent)new TextComponentString("You have to whitelist the server to use its custom models. " + TextFormatting.YELLOW + "/whitelistserver"), true);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.a.a(runtimeException);
        }
        minecraft.func_152344_a(() -> minecraft.func_147108_a((GuiScreen)new a(em2)));
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @SideOnly(value=Side.CLIENT)
    public static class b {
        @SubscribeEvent
        @SideOnly(value=Side.CLIENT)
        public void a(InputEvent.KeyInputEvent keyInputEvent) {
            block13: {
                boolean bl2;
                block15: {
                    block14: {
                        try {
                            if (!ClientProxy.keyBindings[1].func_151468_f()) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.a$b.a(runtimeException);
                        }
                        try {
                            try {
                                if (!br.d) break block13;
                                if (0 == br.b(true)) break block14;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.a$b.a(runtimeException);
                            }
                            bl2 = true;
                            break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.a$b.a(runtimeException);
                        }
                    }
                    bl2 = false;
                }
                try {
                    br.d = bl2;
                    if (br.d) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.a$b.a(runtimeException);
                }
            }
            Minecraft minecraft = Minecraft.func_71410_x();
            ei ei2 = ei.d(minecraft.field_71439_g.getPersistentID());
            try {
                if (ei2 == null) {
                    minecraft.field_71439_g.func_146105_b((ITextComponent)new TextComponentString("You have to turn into the girl you want to customize"), true);
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.a$b.a(runtimeException);
            }
            com.trolmastercard.sexmod.a.a(ei2);
        }

        @SubscribeEvent
        @SideOnly(value=Side.CLIENT)
        public void a(TickEvent.ClientTickEvent clientTickEvent) {
            h = s;
            s = 0;
            for (Integer n2 : a) {
                s += n2.intValue();
            }
            a.clear();
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

