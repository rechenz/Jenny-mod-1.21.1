/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Biomes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.fml.common.registry.EntityRegistry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.al;
import com.trolmastercard.sexmod.ay;
import com.trolmastercard.sexmod.c4;
import com.trolmastercard.sexmod.cy;
import com.trolmastercard.sexmod.e0;
import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.e5;
import com.trolmastercard.sexmod.e7;
import com.trolmastercard.sexmod.e9;
import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.ec;
import com.trolmastercard.sexmod.ed;
import com.trolmastercard.sexmod.ee;
import com.trolmastercard.sexmod.eg;
import com.trolmastercard.sexmod.el;
import com.trolmastercard.sexmod.eq;
import com.trolmastercard.sexmod.er;
import com.trolmastercard.sexmod.es;
import com.trolmastercard.sexmod.ev;
import com.trolmastercard.sexmod.ex;
import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.fn;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.fz;
import com.trolmastercard.sexmod.gi;
import com.trolmastercard.sexmod.i;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class bi {
    public static void a() {
        bi.a("jenny", ex.class, fy.JENNY.npcID, 3286592, 12655237);
        bi.a("ellie", el.class, fy.ELLIE.npcID, 0x161616, 0x980000);
        bi.a("slime", fn.class, fy.SLIME.npcID, 13167780, 8244330);
        bi.a("bia", e0.class, fy.BIA.npcID, 7488816, 7254603);
        bi.a("bee", fz.class, fy.BEE.npcID, 16701032, 4400155);
        bi.a("luna", eb.class, fy.LUNA.npcID, 7881787, 7940422);
        bi.a("allie", ev.class, fy.ALLIE.npcID);
        bi.a("kobold", ff.class, fy.KOBOLD.npcID);
        bi.a("kobold_egg", i.class, 4674237);
        bi.a("goblin", e3.class, fy.GOBLIN.npcID, 39424, 19456);
        bi.a("galath", f_.class, fy.GALATH.npcID, 0xFF0000, 0xFF0000);
        bi.a("manglelie", f8.class, fy.MANGLELIE.npcID, 0xF9F9F9, 8485574);
        bi.a("custom_model", cy.class, 6281823);
        bi.b("player_jenny", es.class, fy.JENNY.playerID);
        bi.b("player_ellie", ee.class, fy.ELLIE.playerID);
        bi.b("player_slime", ec.class, fy.SLIME.playerID);
        bi.b("player_bia", eg.class, fy.BIA.playerID);
        bi.b("player_bee", e9.class, fy.BEE.playerID);
        bi.b("player_allie", e5.class, fy.ALLIE.playerID);
        bi.b("player_kobold", e7.class, fy.KOBOLD.playerID);
        bi.b("player_goblin", eq.class, fy.GOBLIN.playerID);
        bi.b("player_luna", ed.class, fy.LUNA.playerID);
        bi.b("player_galath", er.class, fy.GALATH.playerID);
        bi.a("friendly_slime", ay.class, 5548484);
        bi.a("luna_hook", gi.class, 4768742);
        bi.a("energy_ball", c4.class, 2565153);
        bi.a("pyrocinical", al.class, 515153);
        EntityRegistry.addSpawn(fn.class, (int)10, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.CREATURE, (Biome[])new Biome[]{Biomes.field_76780_h, Biomes.field_150599_m});
        EntityRegistry.addSpawn(fz.class, (int)5, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.CREATURE, (Biome[])new Biome[]{Biomes.field_76767_f, Biomes.field_76785_t});
        EntityRegistry.addSpawn(al.class, (int)3, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.AMBIENT, (Biome[])new Biome[]{Biomes.field_76778_j});
        EntityRegistry.addSpawn(f8.class, (int)5, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.AMBIENT, (Biome[])new Biome[]{Biomes.field_76778_j});
    }

    private static void b(String string, Class<? extends Entity> clazz, int n2) {
        EntityRegistry.registerModEntity((ResourceLocation)new ResourceLocation("sexmod:" + string), clazz, (String)string, (int)n2, (Object)Main.instance, (int)100, (int)1, (boolean)false);
    }

    private static void a(String string, Class<? extends Entity> clazz, int n2, int n3, int n4) {
        EntityRegistry.registerModEntity((ResourceLocation)new ResourceLocation("sexmod:" + string), clazz, (String)string, (int)n2, (Object)Main.instance, (int)50, (int)1, (boolean)true, (int)n3, (int)n4);
    }

    private static void a(String string, Class<? extends Entity> clazz, int n2) {
        EntityRegistry.registerModEntity((ResourceLocation)new ResourceLocation("sexmod:" + string), clazz, (String)string, (int)n2, (Object)Main.instance, (int)50, (int)1, (boolean)true);
    }
}

