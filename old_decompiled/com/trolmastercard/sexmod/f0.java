/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a9;
import com.trolmastercard.sexmod.ap;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class f0
extends GeoItemRenderer<ap> {
    Minecraft a = Minecraft.func_71410_x();
    static ResourceLocation b = null;

    public f0() {
        super(new a9());
    }

    ResourceLocation a() {
        if (b == null) {
            try {
                URL uRL = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + Minecraft.func_71410_x().field_71439_g.getPersistentID().toString().replace("-", ""));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRL.openStream()));
                String string = bufferedReader.lines().collect(Collectors.joining());
                int n2 = string.indexOf("\"value\" : ");
                int n3 = n2 + 11;
                StringBuilder stringBuilder = new StringBuilder();
                int n4 = 0;
                try {
                    while (string.charAt(n3 + n4) != '\"') {
                        stringBuilder.append(string.charAt(n3 + n4));
                        ++n4;
                    }
                }
                catch (Exception exception) {
                    throw f0.a(exception);
                }
                String string2 = new String(Base64.getDecoder().decode(stringBuilder.toString()));
                int n5 = string2.indexOf("\"url\" : ");
                int n6 = n5 + 9;
                StringBuilder stringBuilder2 = new StringBuilder();
                int n7 = 0;
                try {
                    while (string2.charAt(n6 + n7) != '\"') {
                        stringBuilder2.append(string2.charAt(n6 + n7));
                        ++n7;
                    }
                }
                catch (Exception exception) {
                    throw f0.a(exception);
                }
                URL uRL2 = new URL(stringBuilder2.toString());
                BufferedImage bufferedImage = ImageIO.read(uRL2);
                BufferedImage bufferedImage2 = ImageIO.read(this.a.func_110442_L().func_110536_a(new a9().c(new ap())).func_110527_b());
                for (int i2 = 0; i2 < bufferedImage2.getWidth(); ++i2) {
                    for (int i3 = 0; i3 < bufferedImage2.getHeight(); ++i3) {
                        int n8 = bufferedImage.getRGB(i2, i3);
                        try {
                            if (n8 == 0) continue;
                            bufferedImage2.setRGB(i2, i3, n8);
                            continue;
                        }
                        catch (Exception exception) {
                            throw f0.a(exception);
                        }
                    }
                }
                b = Minecraft.func_71410_x().func_175598_ae().field_78724_e.func_110578_a("lamptex", new DynamicTexture(bufferedImage2));
            }
            catch (Exception exception) {
                b = new a9().c(new ap());
            }
        }
        return b;
    }

    public void a(GeoModel geoModel, ap ap2, float f10, float f11, float f12, float f13, float f14) {
        GlStateManager.func_179129_p();
        GlStateManager.func_179091_B();
        this.renderEarly(ap2, f10, f11, f12, f13, f14);
        this.renderLate(ap2, f10, f11, f12, f13, f14);
        BufferBuilder bufferBuilder = Tessellator.func_178181_a().func_178180_c();
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
        for (GeoBone geoBone : geoModel.topLevelBones) {
            this.a(bufferBuilder, ap2, geoBone, f11, f12, f13, f14);
        }
        Tessellator.func_178181_a().func_78381_a();
        this.renderAfter(ap2, f10, f11, f12, f13, f14);
        GlStateManager.func_179101_C();
        GlStateManager.func_179089_o();
    }

    public void a(BufferBuilder bufferBuilder, ap ap2, GeoBone geoBone, float f10, float f11, float f12, float f13) {
        try {
            MATRIX_STACK.push();
            MATRIX_STACK.translate(geoBone);
            MATRIX_STACK.moveToPivot(geoBone);
            MATRIX_STACK.rotate(geoBone);
            MATRIX_STACK.scale(geoBone);
            MATRIX_STACK.moveBackFromPivot(geoBone);
            this.a.field_71446_o.func_110577_a(this.a());
            if (this.a(geoBone.getName())) {
                this.b(bufferBuilder, ap2, geoBone, f10, f11, f12, f13);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f0.a(runtimeException);
        }
        MATRIX_STACK.pop();
    }

    boolean a(String string) {
        boolean bl2;
        block10: {
            block9: {
                block8: {
                    try {
                        try {
                            if (string.equals("leftArm") || string.equals("rightArm")) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw f0.a(runtimeException);
                        }
                        return true;
                    }
                    catch (RuntimeException runtimeException) {
                        throw f0.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (!this.a.field_71439_g.getEntityData().func_74767_n("sexmodAllieInUse") || this.a.field_71474_y.field_74320_O != 0) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw f0.a(runtimeException);
                    }
                    bl2 = true;
                    break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw f0.a(runtimeException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    void b(BufferBuilder bufferBuilder, ap ap2, GeoBone geoBone, float f10, float f11, float f12, float f13) {
        if (!geoBone.isHidden) {
            for (GeoCube object : geoBone.childCubes) {
                MATRIX_STACK.push();
                GlStateManager.func_179094_E();
                this.renderCube(bufferBuilder, object, f10, f11, f12, f13);
                GlStateManager.func_179121_F();
                MATRIX_STACK.pop();
            }
            for (GeoBone geoBone2 : geoBone.childBones) {
                this.a(bufferBuilder, ap2, geoBone2, f10, f11, f12, f13);
            }
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

