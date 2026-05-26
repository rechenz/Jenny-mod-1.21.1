/*
 * Decompiled with CFR 0.152.
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.r;

public enum gw {
    GIRL_SPECIFIC,
    HEAD(0, "customHead"),
    FOOT_L(60, "customShoeL"),
    FOOT_R(80, "customShoeR"),
    HAND_L(100, "customHandL"),
    HAND_R(120, "customHandR"),
    CUSTOM_BONE(140);

    public static final String SEPARATOR = "#";
    public int buttonIDPlus;
    public int buttonIDMinus;
    public String boneName = null;
    public int iconXPos = 0;

    private gw() {
    }

    private gw(int n3) {
        this.iconXPos = n3;
    }

    private gw(int n3, String string2) {
        this.iconXPos = n3;
        this.boneName = string2;
        ++r.i;
        this.buttonIDPlus = r.i++;
        this.buttonIDMinus = r.i;
    }

    public static int a() {
        return gw.values().length - 2;
    }
}

