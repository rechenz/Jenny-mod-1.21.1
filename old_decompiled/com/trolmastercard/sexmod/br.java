/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.ServerData
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ClientProxy;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.c8;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.g6;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gw;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.geo.raw.pojo.RawGeoModel;
import software.bernie.geckolib3.geo.raw.tree.RawGeometryTree;
import software.bernie.geckolib3.geo.render.GeoBuilder;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class br {
    public static final String a = "sexmod/custom_models";
    static final String b = "sexmod/custom_models/whitelisted_servers.txt";
    public static final String f = "sexmod_custom_models";
    static Map<String, b> c = new HashMap<String, b>();
    public static boolean d = false;
    public static boolean e = false;

    public static Map<String, b> i() {
        return c;
    }

    public static boolean f(String string) {
        boolean bl2;
        try {
            bl2 = c.get(string) != null;
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        return bl2;
    }

    public static int b(boolean bl2) {
        br.a(bl2);
        return br.c(bl2);
    }

    static void b(Level level, String string) {
        block3: {
            block2: {
                try {
                    if (!(Main.proxy instanceof ClientProxy)) break block2;
                    br.a(level, string);
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw br.a(runtimeException);
                }
            }
            Main.LOGGER.log(level, string);
        }
    }

    public static void a(boolean bl2) {
        try {
            if (bl2) {
                br.c();
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        c.clear();
    }

    public static void a() {
        ge.b.sendToServer((IMessage)new g6());
    }

    @SideOnly(value=Side.CLIENT)
    public static boolean b() {
        String string = br.g();
        try {
            if (string == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        return br.l(string);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void h(String string) {
        File file = new File(b);
        file.mkdirs();
        HashSet<Object> hashSet = new HashSet<String>();
        if (file.exists()) {
            hashSet = br.f();
        }
        hashSet.add(string);
        file.delete();
        file = new File(b);
        try {
            FileWriter fileWriter;
            block17: {
                fileWriter = new FileWriter(file);
                Throwable throwable = null;
                try {
                    for (String string2 : hashSet) {
                        fileWriter.write(string2 + "\n");
                    }
                }
                catch (Throwable throwable4) {
                    try {
                        throwable = throwable4;
                        throw throwable4;
                    }
                    catch (Throwable throwable5) {
                        block16: {
                            try {
                                if (fileWriter == null) throw throwable5;
                                if (throwable == null) break block16;
                            }
                            catch (Throwable throwable2) {
                                throw br.a(throwable2);
                            }
                            try {
                                fileWriter.close();
                                throw throwable5;
                            }
                            catch (Throwable throwable7) {
                                throwable.addSuppressed(throwable7);
                                throw throwable5;
                            }
                        }
                        fileWriter.close();
                        throw throwable5;
                    }
                }
                try {
                    if (fileWriter == null) return;
                    if (throwable == null) break block17;
                }
                catch (Throwable throwable3) {
                    throw br.a(throwable3);
                }
                try {
                    fileWriter.close();
                    return;
                }
                catch (Throwable throwable3) {
                    throwable.addSuppressed(throwable3);
                    return;
                }
            }
            fileWriter.close();
            return;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static boolean l(String string) {
        return br.f().contains(string);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static HashSet<String> f() {
        var0 = new File("sexmod/custom_models/whitelisted_servers.txt");
        try {
            var0.createNewFile();
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
        }
        var1_2 = new HashSet<String>();
        try {
            block20: {
                block21: {
                    var2_3 = new BufferedReader(new FileReader(var0));
                    var3_5 = null;
                    ** try [egrp 2[TRYBLOCK] [3, 4 : 49->73)] { 
lbl13:
                    // 1 sources

                    break block21;
lbl14:
                    // 1 sources

                    catch (Throwable var4_8) {
                        try {
                            var3_5 = var4_8;
                            throw var4_8;
                        }
lbl18:
                        // 2 sources

                        catch (Throwable var5_9) {
                            block19: {
                                try {
                                    if (var2_3 == null) throw var5_9;
                                    if (var3_5 == null) break block19;
                                }
                                catch (Exception v0) {
                                    throw br.a(v0);
                                }
                                try {
                                    var2_3.close();
                                    throw var5_9;
                                }
                                catch (Throwable var6_10) {
                                    var3_5.addSuppressed(var6_10);
                                    throw var5_9;
                                }
                            }
                            var2_3.close();
                            throw var5_9;
                        }
                    }
                }
                while (true) {
                    var4_6 = var2_3.readLine();
                    try {
                        if (var4_6 == null) break;
                        var1_2.add(var4_6);
                    }
                    catch (Exception v1) {
                        throw br.a(v1);
                    }
                }
                try {
                    if (var2_3 == null) return var1_2;
                    if (var3_5 == null) break block20;
                }
                catch (Exception v2) {
                    throw br.a(v2);
                }
                try {
                    var2_3.close();
                    return var1_2;
                }
                catch (Throwable var4_7) {
                    var3_5.addSuppressed(var4_7);
                    return var1_2;
                }
            }
            var2_3.close();
            return var1_2;
        }
        catch (IOException var2_4) {
            var2_4.printStackTrace();
            return new HashSet<String>();
        }
    }

    public static float i(String string) {
        b b10 = c.get(string);
        try {
            if (b10 == null) {
                return 0.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        return b10.f();
    }

    @SideOnly(value=Side.CLIENT)
    static void c() {
        for (Map.Entry<String, b> entry : c.entrySet()) {
            b b10 = entry.getValue();
            try {
                if (b10 == null) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw br.a(runtimeException);
            }
            ResourceLocation resourceLocation = b10.c();
            ResourceLocation resourceLocation2 = b10.k();
            try {
                if (resourceLocation != null) {
                    GeckoLibCache.getInstance().getGeoModels().remove(resourceLocation);
                }
            }
            catch (RuntimeException runtimeException) {
                throw br.a(runtimeException);
            }
            try {
                if (resourceLocation2 == null) continue;
                Minecraft.func_71410_x().field_71446_o.func_147645_c(resourceLocation2);
            }
            catch (RuntimeException runtimeException) {
                throw br.a(runtimeException);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    static void a(Level level, String string) {
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        try {
            if (entityPlayerSP == null) {
                Main.LOGGER.log(level, string);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        TextFormatting textFormatting = Level.DEBUG.equals((Object)level) ? TextFormatting.DARK_GREEN : (Level.ERROR.equals((Object)level) ? TextFormatting.RED : TextFormatting.WHITE);
        entityPlayerSP.func_145747_a((ITextComponent)new TextComponentString(textFormatting.toString() + string));
    }

    public static String h() {
        try {
            if (Main.proxy instanceof ClientProxy) {
                return br.d();
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        return f;
    }

    @SideOnly(value=Side.CLIENT)
    public static String d() {
        String string = br.g();
        try {
            if (string == null) {
                return "sexmod/custom_models/singleplayer";
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        return "sexmod/custom_models/" + string;
    }

    @SideOnly(value=Side.CLIENT)
    @Nullable
    public static String g() {
        Minecraft minecraft = Minecraft.func_71410_x();
        ServerData serverData = minecraft.func_147104_D();
        try {
            if (serverData == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        String string = serverData.field_78845_b;
        int n2 = string.indexOf(":");
        if (n2 != -1) {
            string = string.substring(0, n2);
        }
        return string;
    }

    public static int c(boolean bl2) {
        br.b(Level.INFO, "loading up custom models...");
        String string2 = br.h();
        File file2 = new File(string2);
        file2.mkdirs();
        String[] stringArray = file2.list((file, string) -> new File(file, string).isDirectory());
        try {
            if (stringArray == null) {
                br.b(Level.ERROR, String.format("Something is wrong with the custom models folder at '%s'. Check if it exists, if not - make the directory yourself because Minecraft cannot do it itself for some reason", file2.getAbsolutePath()));
                return -1;
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        br.b(Level.INFO, String.format("found %s custom model(s)", stringArray.length));
        int n2 = 0;
        for (String string3 : stringArray) {
            String string4 = br.a(string3, string2);
            try {
                if (!"".equals(string4)) {
                    br.b(Level.ERROR, string4);
                    return -1;
                }
            }
            catch (RuntimeException runtimeException) {
                throw br.a(runtimeException);
            }
            string4 = br.a(string3, string2, bl2);
            try {
                if (!"".equals(string4)) {
                    br.b(Level.ERROR, string4);
                    return -1;
                }
            }
            catch (RuntimeException runtimeException) {
                throw br.a(runtimeException);
            }
            ++n2;
        }
        br.b(Level.DEBUG, String.format("successfully registered %s custom models", n2));
        e = true;
        return 0;
    }

    public static String a(String string, String string2) {
        String string3 = String.format("%s/%s", string2, string);
        File file = new File(String.format("%s/%s.geo.json", string3, string));
        File file2 = new File(String.format("%s/%s.png", string3, string));
        File file3 = new File(String.format("%s/%s.cfg", string3, string));
        try {
            if (!file.exists()) {
                return String.format("couldn't find model File for '%s'. It should have been at '%s'. Are you sure it exists?", string, file.getAbsolutePath());
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        try {
            if (!file2.exists()) {
                return String.format("couldn't find texture File for '%s'. It should have been at '%s'. Are you sure it exists?", string, file2.getAbsolutePath());
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        try {
            if (!file3.exists()) {
                return String.format("couldn't find cfg File for '%s'. It should have been at '%s'. Are you sure it exists?", string, file3.getAbsolutePath());
            }
        }
        catch (RuntimeException runtimeException) {
            throw br.a(runtimeException);
        }
        return "";
    }

    @SideOnly(value=Side.CLIENT)
    static ResourceLocation a(String string, File file) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(file);
        return Minecraft.func_71410_x().field_71446_o.func_110578_a(string, new DynamicTexture(bufferedImage));
    }

    /*
     * Exception decompiling
     */
    @SideOnly(value=Side.CLIENT)
    static RawGeoModel a(File var0) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 14[UNCONDITIONALDOLOOP]
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

    public static String a(String string, String string2, boolean bl2) {
        try {
            if (c.get(string) != null) {
                return String.format("already registered '%s'... honestly, unsure how this could happen lol", string);
            }
        }
        catch (IOException iOException) {
            throw br.a(iOException);
        }
        String string3 = String.format("%s/%s/", string2, string);
        String string4 = string3 + string + ".cfg";
        File file = new File(string4);
        try {
            if (!file.exists()) {
                return String.format("couldn't find cfg File for '%s'. It should have been at '%s'. Are you sure it exists?", string, string4);
            }
        }
        catch (IOException iOException) {
            throw br.a(iOException);
        }
        b b10 = new b(file, string);
        try {
            if (b10.h != null) {
                return b10.h;
            }
        }
        catch (IOException iOException) {
            throw br.a(iOException);
        }
        String string5 = string3 + string + ".png";
        File file2 = new File(string5);
        try {
            if (!file2.exists()) {
                return String.format("The texture for the custom model '%s' couldn't be found at '%s' are you sure it exists?", string, string5);
            }
        }
        catch (IOException iOException) {
            throw br.a(iOException);
        }
        ResourceLocation resourceLocation = null;
        if (bl2) {
            try {
                resourceLocation = br.a(string, file2);
            }
            catch (IOException iOException) {
                return String.format("The texture for the custom model '%s' at '%s' appears to be corrupted. Try making a new one", string, string5);
            }
            catch (Exception exception) {
                return String.format("Couldn't load the texture for the custom model '%s' at '%s'. Maybe try increasing the amount of RAM of ur Minecraft client", string, file2);
            }
        }
        ResourceLocation resourceLocation2 = new ResourceLocation("sexmod", string + "Model");
        String string6 = string3 + string + ".geo.json";
        File file3 = new File(string6);
        try {
            if (!file3.exists()) {
                return String.format("The geo model for the custom model '%s' couldn't be found at '%s' are you sure it exists?", string, string6);
            }
        }
        catch (IOException iOException) {
            throw br.a(iOException);
        }
        if (bl2) {
            RawGeoModel rawGeoModel;
            try {
                rawGeoModel = br.a(file3);
            }
            catch (IOException iOException) {
                return String.format("The geo model for the custom model '%s' at '%s' appears to be corrupted. Try replacing it.", string, string6);
            }
            try {
                RawGeometryTree rawGeometryTree = RawGeometryTree.parseHierarchy(rawGeoModel, resourceLocation2);
                GeoModel geoModel = GeoBuilder.getGeoBuilder(resourceLocation2.func_110624_b()).constructGeoModel(rawGeometryTree);
                GeckoLibCache.getInstance().getGeoModels().put(resourceLocation2, geoModel);
            }
            catch (Exception exception) {
                return String.format("The geo model for the custom model '%s' at '%s' appears to be corrupted. Try replacing it.", string, string6);
            }
        }
        try {
            if (bl2) {
                b10.b(resourceLocation2);
                b10.a(resourceLocation);
            }
        }
        catch (IOException iOException) {
            throw br.a(iOException);
        }
        c.put(string, b10);
        br.b(Level.DEBUG, String.format("successfully registered custom model '%s'", string));
        return "";
    }

    public static ResourceLocation k(String string) {
        b b10;
        block4: {
            block5: {
                b10 = c.get(string);
                try {
                    try {
                        if (b10 != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw br.a(runtimeException);
                    }
                    System.out.printf("The custom model for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw br.a(runtimeException);
                }
            }
            return null;
        }
        return b10.c();
    }

    public static ResourceLocation c(String string) {
        b b10;
        block4: {
            block5: {
                b10 = c.get(string);
                try {
                    try {
                        if (b10 != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw br.a(runtimeException);
                    }
                    System.out.printf("The custom texture for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw br.a(runtimeException);
                }
            }
            return null;
        }
        return b10.k();
    }

    public static GeoModel j(String string) {
        return GeckoLibCache.getInstance().getGeoModels().get(br.k(string));
    }

    public static gw e(String string) {
        b b10;
        block4: {
            block5: {
                b10 = c.get(string);
                try {
                    try {
                        if (b10 != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw br.a(runtimeException);
                    }
                    System.out.printf("The ClothingType for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw br.a(runtimeException);
                }
            }
            return gw.HEAD;
        }
        return b10.d;
    }

    public static HashSet<fy> a(String string) {
        b b10;
        block4: {
            block5: {
                b10 = c.get(string);
                try {
                    try {
                        if (b10 != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw br.a(runtimeException);
                    }
                    System.out.printf("The HashSet<GirlType> for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw br.a(runtimeException);
                }
            }
            return null;
        }
        return b10.g;
    }

    public static HashSet<String> g(String string) {
        b b10;
        block4: {
            block5: {
                b10 = c.get(string);
                try {
                    try {
                        if (b10 != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw br.a(runtimeException);
                    }
                    System.out.printf("The HashSet<String> for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw br.a(runtimeException);
                }
            }
            return new HashSet<String>();
        }
        return b10.b;
    }

    public static String d(String string) {
        b b10;
        block4: {
            block5: {
                b10 = c.get(string);
                try {
                    try {
                        if (b10 != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw br.a(runtimeException);
                    }
                    System.out.printf("The author for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw br.a(runtimeException);
                }
            }
            return "";
        }
        return b10.k;
    }

    @Nullable
    public static b b(String string) {
        return c.get(string);
    }

    public static HashMap<gw, List<String>> a(em em2) {
        HashMap<gw, List<String>> hashMap = new HashMap<gw, List<String>>();
        for (Object object : gw.values()) {
            hashMap.put((gw)((Object)object), new ArrayList());
        }
        for (Map.Entry entry : c.entrySet()) {
            Object object;
            String string = (String)entry.getKey();
            object = (b)entry.getValue();
            gw gw2 = ((b)object).d;
            List<String> list = hashMap.get((Object)gw2);
            try {
                try {
                    if (!((b)object).g.isEmpty() && !((b)object).g.contains((Object)fy.a((Entity)em2))) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw br.a(runtimeException);
                }
            }
            catch (RuntimeException runtimeException) {
                throw br.a(runtimeException);
            }
            list.add(string);
            hashMap.put(gw2, list);
        }
        return hashMap;
    }

    public static HashMap<String, Float> e() {
        HashMap<String, Float> hashMap = new HashMap<String, Float>();
        for (Map.Entry<String, b> entry : br.i().entrySet()) {
            hashMap.put(entry.getKey(), Float.valueOf(entry.getValue().f()));
        }
        return hashMap;
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public static class b {
        gw d;
        HashSet<fy> g;
        HashSet<String> b;
        String k;
        String j;
        boolean c;
        c8 e;
        float m;
        float a;
        ResourceLocation i;
        ResourceLocation f;
        public String h;
        float l;

        /*
         * Could not resolve type clashes
         * Unable to fully structure code
         */
        public b(File var1_1, String var2_2) {
            block57: {
                block56: {
                    block55: {
                        block54: {
                            block53: {
                                block52: {
                                    block51: {
                                        block50: {
                                            block49: {
                                                super();
                                                this.g = new HashSet<E>();
                                                this.b = new HashSet<E>();
                                                this.m = 1.0f;
                                                this.a = 0.0f;
                                                this.h = null;
                                                if (var2_2.contains(" ")) ** GOTO lbl15
                                                if (var2_2.contains("#")) ** GOTO lbl15
                                                try {
                                                    block58: {
                                                        if (!var2_2.contains("$")) break block49;
                                                        break block58;
                                                        catch (FileNotFoundException v0) {
                                                            throw com.trolmastercard.sexmod.br$b.a(v0);
                                                        }
                                                    }
                                                    this.h = String.format("You cannot call your custom model '%s'. '#', '$' and spaces are illegal characters", new Object[]{var2_2});
                                                    return;
                                                }
                                                catch (FileNotFoundException v1) {
                                                    throw com.trolmastercard.sexmod.br$b.a(v1);
                                                }
                                            }
                                            try {
                                                if ("cross".equalsIgnoreCase(var2_2)) {
                                                    this.h = "You cannot call your custom model 'cross'. Im sorry, but I need that specific name for internal stuff";
                                                    return;
                                                }
                                            }
                                            catch (FileNotFoundException v2) {
                                                throw com.trolmastercard.sexmod.br$b.a(v2);
                                            }
                                            var3_3 = new Properties();
                                            try {
                                                var4_4 = new FileInputStream(var1_1);
                                            }
                                            catch (FileNotFoundException var5_5) {
                                                this.h = String.format("couldn't find cfg File for '%s'. It should have been at '%s'. Are you sure it exists?", new Object[]{var2_2, var1_1.getAbsolutePath()});
                                                return;
                                            }
                                            try {
                                                var3_3.load(var4_4);
                                            }
                                            catch (IOException var5_6) {
                                                this.h = String.format("couldn't read the cfg File for '%s' at '%s'. It appears to be corrupted. Try making a new one", new Object[]{var2_2, var1_1.getAbsolutePath()});
                                                return;
                                            }
                                            var5_7 = var3_3.getProperty("wear_type");
                                            try {
                                                if (var5_7 == null) {
                                                    this.h = String.format("The cfg File for the model '%s' at '%s' is missing the 'wear_type'. Go to the bottom of the cfg File and write 'wear_type=HEAD'. Check the cfg files of my examples to see what values for 'wear_type' are possible", new Object[]{var2_2, var1_1.getAbsolutePath()});
                                                    return;
                                                }
                                            }
                                            catch (FileNotFoundException v3) {
                                                throw com.trolmastercard.sexmod.br$b.a(v3);
                                            }
                                            try {
                                                var5_7 = var5_7.replace(" ", "");
                                                this.d = gw.valueOf(var5_7);
                                            }
                                            catch (IllegalArgumentException var6_8) {
                                                this.h = String.format("you entered '%s' into the 'wear_type' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'wear_type", new Object[]{var5_7, var2_2, var1_1.getAbsolutePath()});
                                                return;
                                            }
                                            if (!gw.CUSTOM_BONE.equals((Object)this.d)) break block50;
                                            try {
                                                block59: {
                                                    this.j = var3_3.getProperty("custom_bone");
                                                    if (!"".equals(this.j)) break block50;
                                                    break block59;
                                                    catch (FileNotFoundException v4) {
                                                        throw com.trolmastercard.sexmod.br$b.a(v4);
                                                    }
                                                }
                                                this.h = String.format("You selected CUSTOM_BONE as the 'wear_type' in the cfg file for '%s' at '%s', yet you left the 'custom_bone' field right underneath it empty. If you want ur model to be parented to a specific bone, you have to enter the name of that bone at the field 'custom_bone'.", new Object[]{var2_2, var1_1.getAbsolutePath()});
                                                return;
                                            }
                                            catch (FileNotFoundException v5) {
                                                throw com.trolmastercard.sexmod.br$b.a(v5);
                                            }
                                        }
                                        var6_9 = var3_3.getProperty("which_girls");
                                        var6_9 = var6_9.replace(" ", "");
                                        var7_10 = var6_9.split(",");
                                        for (String[] var11_17 : var7_10) {
                                            try {
                                                if ("".equals(var11_17)) {
                                                    continue;
                                                }
                                            }
                                            catch (FileNotFoundException v6) {
                                                throw com.trolmastercard.sexmod.br$b.a(v6);
                                            }
                                            try {
                                                this.g.add(fy.valueOf((String)var11_17));
                                            }
                                            catch (IllegalArgumentException var12_18) {
                                                this.h = String.format("you entered '%s' as one of the girls, you put into the 'which_girls' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'which_girls'.", new Object[]{var11_17, var2_2, var1_1.getAbsolutePath()});
                                                return;
                                            }
                                        }
                                        var8_11 = var3_3.getProperty("which_lighting");
                                        try {
                                            if (var8_11 == null) {
                                                this.h = String.format("The %s's cfg file at '%s' doesn't contain the field 'which_lighting'. Go to the bottom of the cfg file and write either 'which_lighting=DEFAULT', 'which_lighting=SEXMOD', or 'which_lighting=NONE'.", new Object[]{var2_2, var1_1.getAbsolutePath()});
                                                return;
                                            }
                                        }
                                        catch (FileNotFoundException v7) {
                                            throw com.trolmastercard.sexmod.br$b.a(v7);
                                        }
                                        var8_11 = var8_11.replace(" ", "");
                                        try {
                                            this.e = c8.valueOf((String)var8_11);
                                        }
                                        catch (IllegalArgumentException var9_13) {
                                            this.h = String.format("you entered '%s' into the 'which_lighting' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'which_lighting'.", new Object[]{var8_11, var2_2, var1_1.getAbsolutePath()});
                                        }
                                        var9_14 = var3_3.getProperty("author");
                                        if (var9_14 == null) ** GOTO lbl109
                                        try {
                                            block60: {
                                                if (!"".equals(var9_14)) break block51;
                                                break block60;
                                                catch (FileNotFoundException v8) {
                                                    throw com.trolmastercard.sexmod.br$b.a(v8);
                                                }
                                            }
                                            this.k = "anon";
                                            break block52;
                                        }
                                        catch (FileNotFoundException v9) {
                                            throw com.trolmastercard.sexmod.br$b.a(v9);
                                        }
                                    }
                                    this.k = var9_14;
                                }
                                var10_16 = var3_3.getProperty("bones_to_hide");
                                try {
                                    if (var10_16 == null || "".equals(var10_16)) break block53;
                                }
                                catch (FileNotFoundException v10) {
                                    throw com.trolmastercard.sexmod.br$b.a(v10);
                                }
                                var10_16 = var10_16.replace(" ", "");
                                var11_17 = var10_16.split(",");
                                this.b.addAll(Arrays.asList(var11_17));
                            }
                            var11_17 = var3_3.getProperty("enable_when_nude");
                            try {
                                if (var11_17 != null) break block54;
                                this.c = false;
                                break block55;
                            }
                            catch (FileNotFoundException v11) {
                                throw com.trolmastercard.sexmod.br$b.a(v11);
                            }
                        }
                        var11_17 = var11_17.replace(" ", "");
                        this.c = var11_17.equalsIgnoreCase("yes");
                    }
                    var12_19 = var3_3.getProperty("gui_size_factor");
                    try {
                        if (var12_19 == null || "".equals(var12_19)) break block56;
                    }
                    catch (FileNotFoundException v12) {
                        throw com.trolmastercard.sexmod.br$b.a(v12);
                    }
                    var12_19 = var12_19.replace(" ", "");
                    var12_19 = var12_19.replace(",", ".");
                    try {
                        this.m = Float.parseFloat(var12_19);
                    }
                    catch (NumberFormatException var13_20) {
                        this.h = String.format("you entered '%s' into the 'gui_size_factor' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'gui_size_factor'.", new Object[]{var12_19, var2_2, var1_1.getAbsolutePath()});
                    }
                }
                var13_21 = var3_3.getProperty("gui_vertical_positioning");
                try {
                    if (var13_21 == null || "".equals(var13_21)) break block57;
                }
                catch (FileNotFoundException v13) {
                    throw com.trolmastercard.sexmod.br$b.a(v13);
                }
                var13_21 = var13_21.replace(" ", "");
                var13_21 = var13_21.replace(",", ".");
                try {
                    this.a = Float.parseFloat(var13_21);
                }
                catch (NumberFormatException var14_22) {
                    this.h = String.format("you entered '%s' into the 'gui_vertical_positioning' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'gui_vertical_positioning'.", new Object[]{var13_21, var2_2, var1_1.getAbsolutePath()});
                }
            }
            var14_23 = var3_3.getProperty("version");
            var14_23 = var14_23.replace(" ", "");
            var14_23 = var14_23.replace(",", ".");
            try {
                this.l = Float.parseFloat(var14_23);
            }
            catch (NumberFormatException var15_24) {
                this.h = String.format("you entered '%s' into the 'versionString' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'versionString'.", new Object[]{var14_23, var2_2, var1_1.getAbsolutePath()});
            }
        }

        public String b() {
            return this.j;
        }

        public c8 i() {
            return this.e;
        }

        public float g() {
            return this.a;
        }

        public float d() {
            return this.m;
        }

        public gw j() {
            return this.d;
        }

        public HashSet<fy> l() {
            return this.g;
        }

        public String e() {
            return this.k;
        }

        public boolean a() {
            return this.c;
        }

        public HashSet<String> h() {
            return this.b;
        }

        public ResourceLocation k() {
            return this.i;
        }

        public void a(ResourceLocation resourceLocation) {
            this.i = resourceLocation;
        }

        public ResourceLocation c() {
            return this.f;
        }

        public void b(ResourceLocation resourceLocation) {
            this.f = resourceLocation;
        }

        public float f() {
            return this.l;
        }

        private static FileNotFoundException a(FileNotFoundException fileNotFoundException) {
            return fileNotFoundException;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static class a {
        boolean a = false;

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(ClientChatEvent clientChatEvent) {
            String string = clientChatEvent.getOriginalMessage();
            try {
                if (!"id".equals(string)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.br$a.a(runtimeException);
            }
            EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
            List list = entityPlayerSP.field_70170_p.func_72872_a(em.class, entityPlayerSP.func_174813_aQ().func_186662_g(10.0));
            em em2 = null;
            for (em em3 : list) {
                if (em2 == null) {
                    em2 = em3;
                    continue;
                }
                if (!(entityPlayerSP.func_70032_d((Entity)em3) < entityPlayerSP.func_70032_d((Entity)em2))) continue;
                em2 = em3;
            }
            try {
                if (em2 == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.br$a.a(runtimeException);
            }
            entityPlayerSP.func_146105_b((ITextComponent)new TextComponentString(em2.f().toString()), false);
            clientChatEvent.setCanceled(true);
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(FMLNetworkEvent.ClientConnectedToServerEvent clientConnectedToServerEvent) {
            Minecraft minecraft = Minecraft.func_71410_x();
            minecraft.func_152343_a(() -> br.c(true));
            this.a = false;
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(EntityJoinWorldEvent entityJoinWorldEvent) {
            try {
                if (!entityJoinWorldEvent.getEntity().equals((Object)Minecraft.func_71410_x().field_71439_g)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.br$a.a(runtimeException);
            }
            try {
                if (this.a) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.br$a.a(runtimeException);
            }
            try {
                this.a = true;
                if (br.b()) {
                    br.a();
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.br$a.a(runtimeException);
            }
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(FMLNetworkEvent.ClientDisconnectionFromServerEvent clientDisconnectionFromServerEvent) {
            Minecraft.func_71410_x().func_152344_a(() -> br.a(true));
            this.a = false;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

