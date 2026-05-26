/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ei;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class bq {
    @SubscribeEvent
    public void a(GuiOpenEvent guiOpenEvent) {
        block4: {
            try {
                try {
                    if (!(guiOpenEvent.getGui() instanceof GuiMainMenu) && !(guiOpenEvent.getGui() instanceof GuiMultiplayer)) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw bq.a(runtimeException);
                }
                ei.Z.clear();
                ei.al.clear();
            }
            catch (RuntimeException runtimeException) {
                throw bq.a(runtimeException);
            }
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

