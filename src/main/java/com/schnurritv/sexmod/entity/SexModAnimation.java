package com.schnurritv.sexmod.entity;

public enum SexModAnimation {
    NULL(0, true),
    IDLE(0, true),
    WALK(0, true),
    MISS_IDLE(40, true),
    MISSIONARY_START(65, true),
    MISSIONARY_SLOW(58, true),
    MISSIONARY_FAST(38, true),
    MISSIONARY_CUM(289, true),
    BLOWJOBINTRO(986, true),
    BLOWJOBSUCK(121, true),
    BLOWJOBTHRUST(35, true),
    BLOWJOBCUM(236, true),
    PAIZURI_START(173, true),
    PAIZURI_SLOW(68, true),
    PAIZURI_FAST(22, true),
    PAIZURI_CUM(230, true),
    DOGGYSTART(198, true),
    DOGGYGOONBED(130, true),
    DOGGYWAIT(129, true),
    DOGGYSLOW(46, true),
    DOGGYFAST(22, true),
    DOGGYCUM(134, true),
    // Allie-specific animations
    SUMMON(360, false),
    SUMMON_WAIT(0, false),
    RICH_FIRST_TIME(240, false),
    RICH_NORMAL(240, false);

    private final int length;
    public final boolean autoBlink;

    SexModAnimation(int length, boolean autoBlink) {
        this.length = length;
        this.autoBlink = autoBlink;
    }

    public int getLength() {
        return length;
    }
}
