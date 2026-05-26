/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ap;
import com.trolmastercard.sexmod.y;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class a9
extends AnimatedGeoModel<ap> {
    ResourceLocation a = null;

    public ResourceLocation b(ap ap2) {
        return new ResourceLocation("sexmod", "geo/allie/lamp.geo.json");
    }

    public ResourceLocation c(ap ap2) {
        try {
            if (this.a != null) {
                return this.a;
            }
        }
        catch (IOException iOException) {
            throw a9.a(iOException);
        }
        try {
            Minecraft minecraft = Minecraft.func_71410_x();
            BufferedImage bufferedImage = y.a(minecraft.field_71439_g.getPersistentID());
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(new Color(185, 254, 255));
            graphics.fillRect(0, 0, 2, 2);
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(2, 0, 1, 2);
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(3, 0, 1, 2);
            this.a = minecraft.field_71446_o.func_110578_a("alliesLamp", new DynamicTexture(bufferedImage));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            this.a = new ResourceLocation("sexmod", "textures/entity/allie/lamp.png");
        }
        return this.a;
    }

    public ResourceLocation a(ap ap2) {
        return new ResourceLocation("sexmod", "animations/allie/lamp.animation.json");
    }

    private static IOException a(IOException iOException) {
        return iOException;
    }
}

