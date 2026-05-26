/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.g2;
import javax.swing.JFrame;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class fr
extends JFrame {
    public boolean a = false;

    @SubscribeEvent
    public void a(TickEvent.ClientTickEvent clientTickEvent) {
        try {
            if (this.a) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fr.a(runtimeException);
        }
        this.a = true;
        g2.a();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

