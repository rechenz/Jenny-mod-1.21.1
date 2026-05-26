/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.network.EnumPacketDirection
 *  net.minecraft.network.NetworkManager
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.x;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;

public class f5
extends NetHandlerPlayClient {
    public f5(Minecraft minecraft) {
        super(minecraft, minecraft.field_71462_r, (NetworkManager)new x(EnumPacketDirection.CLIENTBOUND), minecraft.func_110432_I().func_148256_e());
    }
}

