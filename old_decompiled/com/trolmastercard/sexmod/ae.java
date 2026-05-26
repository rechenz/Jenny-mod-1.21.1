/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonSyntaxException
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.client.shader.ShaderGroup
 *  net.minecraft.client.shader.ShaderLinkHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 */
package com.trolmastercard.sexmod;

import com.google.gson.JsonSyntaxException;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.em;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ae {
    public static ShaderGroup b;
    static final ResourceLocation a;
    static Framebuffer c;

    public static void a() {
        Minecraft minecraft = Minecraft.func_71410_x();
        try {
            if (!OpenGlHelper.field_148824_g) {
                Main.LOGGER.warn("Shaders not supported");
                return;
            }
        }
        catch (IOException iOException) {
            throw ae.a(iOException);
        }
        try {
            if (ShaderLinkHelper.func_148074_b() == null) {
                ShaderLinkHelper.func_148076_a();
            }
        }
        catch (IOException iOException) {
            throw ae.a(iOException);
        }
        try {
            b = new ShaderGroup(minecraft.func_110434_K(), minecraft.func_110442_L(), minecraft.func_147110_a(), a);
            b.func_148026_a(minecraft.field_71443_c, minecraft.field_71440_d);
            c = b.func_177066_a("final");
            ClientRegistry.registerEntityShader(em.class, (ResourceLocation)a);
            System.out.println("succ registered the outline shader :)");
        }
        catch (IOException iOException) {
            Main.LOGGER.warn("Failed to load shader: {}", (Object)a, (Object)iOException);
        }
        catch (JsonSyntaxException jsonSyntaxException) {
            Main.LOGGER.warn("Failed to load shader: {}", (Object)a, (Object)jsonSyntaxException);
        }
    }

    static {
        a = new ResourceLocation("sexmod", "shaders/post/outline.json");
    }

    private static IOException a(IOException iOException) {
        return iOException;
    }
}

