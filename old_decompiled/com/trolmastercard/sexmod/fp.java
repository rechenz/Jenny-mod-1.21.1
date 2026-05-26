/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.em;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.controller.AnimationController;

public enum fp {
    NULL(0, false, true),
    STARTBLOWJOB(2, true, false),
    SUCKBLOWJOB(2, true, false),
    SUCKBLOWJOB_BLINK(2, true, true),
    CUMBLOWJOB(0, true, false),
    THRUSTBLOWJOB(2, true, false),
    PAYMENT(5, true, false),
    STARTDOGGY(2, false, false),
    WAITDOGGY(0, false, true),
    DOGGYSTART(0, true, false),
    DOGGYSLOW(2, true, false),
    DOGGYFAST(2, true, false),
    DOGGYCUM(2, true, false),
    STRIP(5, false, false),
    DASH(2, false, false),
    HUG(2, true, false),
    HUGIDLE(0, true, true),
    HUGSELECTED(0, true, false),
    UNDRESS(2, false, true),
    DRESS(2, false, true),
    SITDOWN(2, false, false, 60.0f, -90.0f, true),
    SITDOWNIDLE(0, false, true, 60.0f, -60.0f, true),
    COWGIRLSTART(0, true, false, 60.0f, -60.0f, false),
    COWGIRLSLOW(10, true, false, 60.0f, -60.0f, false),
    COWGIRLFAST(10, true, false, 60.0f, -60.0f, false),
    COWGIRLCUM(2, true, false, 60.0f, -60.0f, false),
    ATTACK(0, false, true),
    BOW(2, false, true),
    RIDE(0, false, true),
    SIT(0, false, true),
    THROW_PEARL(0, false, false),
    DOWNED(7, false, true),
    PAIZURI_START(0, true, false, -56.0f, -90.0f, false, true),
    PAIZURI_IDLE(0, true, false, -56.0f, -90.0f, false, true),
    PAIZURI_SLOW(0, true, true, -56.0f, -90.0f, false, true),
    PAIZURI_FAST(0, true, false, -56.0f, -90.0f, false, true),
    PAIZURI_FAST_CONTINUES(0, true, false, -56.0f, -90.0f, false, true),
    PAIZURI_CUM(0, true, false, -56.0f, -90.0f, false, true),
    MISSIONARY_START(0, true, false, 30.0f, -90.0f, true),
    MISSIONARY_SLOW(2, true, false, 30.0f, -90.0f, true),
    MISSIONARY_FAST(2, true, false, 30.0f, -90.0f, true),
    MISSIONARY_CUM(2, true, false, 30.0f, -90.0f, true),
    TALK_HORNY(5, true, false),
    TALK_IDLE(0, true, true),
    TALK_RESPONSE(2, true, false),
    ANAL_PREPARE(5, false, false),
    ANAL_WAIT(0, false, true),
    ANAL_START(0, true, false),
    ANAL_SLOW(2, true, true),
    ANAL_FAST(0, true, false),
    ANAL_CUM(2, true, false),
    KOBOLD_ANAL_START(0, true, false, false, 4.0f, -80.0f, true),
    KOBOLD_ANAL_SLOW(0, true, true, false, 4.0f, -80.0f, true),
    KOBOLD_ANAL_FAST(0, true, false, false, 4.0f, -80.0f, true),
    KOBOLD_ANAL_CUM(2, true, false, false, 4.0f, -80.0f, true),
    SUMMON(0, false, false, false, true),
    SUMMON_WAIT(0, false, true, false, true),
    HEAD_PAT(0, true, false),
    ALLIE_PREPARE_FIRST_TIME(0, false, false, 40.0f, -40.0f, false),
    DEEPTHROAT_START(0, true, false, true, 40.0f, -40.0f, false),
    DEEPTHROAT_SLOW(2, true, false, true, 40.0f, -40.0f, false),
    DEEPTHROAT_FAST(2, true, false, true, 40.0f, -40.0f, false),
    DEEPTHROAT_CUM(2, true, false, true, 40.0f, -40.0f, false),
    ALLIE_PREPARE_NORMAL(2, false, false, 40.0f, -40.0f, false),
    SUMMON_NORMAL(0, false, false),
    SUMMON_SAND(0, false, false),
    SUMMON_NORMAL_WAIT(2, false, true),
    RICH_FIRST_TIME(0, false, false),
    RICH_NORMAL(0, false, false),
    CITIZEN_START(0, true, false, 10.0f, -90.0f, false),
    CITIZEN_SLOW(0, true, false, 10.0f, -90.0f, false),
    CITIZEN_FAST(0, true, false, 10.0f, -90.0f, false),
    CITIZEN_CUM(2, true, false, 10.0f, -90.0f, false),
    FISHING_START(5, false, false),
    FISHING_IDLE(0, false, true),
    FISHING_EAT(0, false, false),
    FISHING_THROW_AWAY(0, false, false),
    TOUCH_BOOBS_INTRO(0, true, false),
    TOUCH_BOOBS_SLOW(2, true, false),
    TOUCH_BOOBS_FAST(2, true, false),
    TOUCH_BOOBS_CUM(2, true, false),
    WAIT_CAT(0, false, false, 30.0f, -90.0f, true),
    COWGIRL_SITTING_INTRO(0, true, false),
    COWGIRL_SITTING_SLOW(5, true, false),
    COWGIRL_SITTING_FAST(5, true, false),
    COWGIRL_SITTING_CUM(5, true, false),
    MINE(0, false, false),
    SLEEP(5, false, false),
    MATING_PRESS_START(0, true, false, false, -50.0f, -90.0f, false),
    MATING_PRESS_SOFT(0, true, false, -50.0f, -90.0f, false),
    MATING_PRESS_HARD(0, true, false, -50.0f, -90.0f, false),
    MATING_PRESS_CUM(2, true, false, -30.0f, -90.0f, false),
    SHOULDER_IDLE(0, false, true, false, true),
    PICK_UP(0, true, false, 10.0f, -90.0f, true, true),
    RUN(5, false, true),
    CATCH(0, true, false),
    CATCH_BJ(0, true, false),
    CATCH_BJ_IDLE(0, true, false),
    START_THROWING(0, true, true),
    THROWN(0, false, true),
    JUMP_0(0, true, false),
    JUMP_1(0, false, false),
    JUMP_2(0, false, false),
    BREEDING_INTRO_0(0, true, false),
    BREEDING_INTRO_1(0, false, false),
    BREEDING_INTRO_2(0, false, false),
    BREEDING_SLOW_0(0, true, false),
    BREEDING_1(0, false, false),
    BREEDING_SLOW_2(5, false, false),
    BREEDING_FAST_0(0, true, false),
    BREEDING_FAST_2(5, false, false),
    BREEDING_CUM_0(0, true, false),
    BREEDING_CUM_1(0, false, false),
    BREEDING_CUM_2(0, false, false),
    AWAIT_PICK_UP(0, false, true),
    VANISH(0, false, true),
    STAND_UP(0, false, false),
    NELSON_INTRO(0, true, false, 30.0f, -20.0f, false, true),
    NELSON_SLOW(0, true, false, 30.0f, -20.0f, false, true),
    NELSON_FAST(0, true, false, 30.0f, -20.0f, false, true),
    NELSON_CUM(0, true, false, 30.0f, -20.0f, false, true),
    CARRY_SLOW(0, true, false, true, true),
    CARRY_FAST(0, true, false, true, true),
    CARRY_CUM(0, true, false, true, true),
    CARRY_INTRO(0, true, false, true, true, 191, CARRY_SLOW),
    PRONE_DOGGY_INTRO(0, true, false, true, true),
    PRONE_DOGGY_SOFT(0, true, false, true, true),
    PRONE_DOGGY_HARD(0, true, false, true, true, 34, PRONE_DOGGY_SOFT),
    PRONE_DOGGY_INSERT(2, true, false, true, true, 42, PRONE_DOGGY_SOFT),
    PRONE_DOGGY_CUM(0, true, false, true, true),
    REVERSE_COWGIRL_SLOW(0, true, false, true, 30.0f, -90.0f, true),
    REVERSE_COWGIRL_FAST_START(0, true, false, true, 34, REVERSE_COWGIRL_SLOW, 30.0f, -90.0f, true),
    REVERSE_COWGIRL_FAST_CONTINUES(0, true, false, true, 39, REVERSE_COWGIRL_SLOW, 30.0f, -90.0f, true),
    REVERSE_COWGIRL_CUM(0, true, false, true, 30.0f, -90.0f, true),
    REVERSE_COWGIRL_START(0, true, false, true, 88, REVERSE_COWGIRL_SLOW, 30.0f, -90.0f, true),
    WAVE_IDLE(0, false, false, false, true),
    WAVE(0, false, false, true, false, 71, WAVE_IDLE),
    FLY(0, false, true),
    SUMMON_SKELETON(0, false, false),
    ATTACK_SWORD(0, false, false),
    KNOCK_OUT_FLY(5, false, false),
    KNOCK_OUT_GROUND(3, false, false),
    KNOCK_OUT_STAND_UP(0, false, false),
    RAPE_PREPARE(0, false, false),
    RAPE_CHARGE(0, false, false),
    RAPE_ON_GOING(0, true, false, true, 60.0f, -30.0f, false),
    RAPE_INTRO(0, true, false, false, true, 46, RAPE_ON_GOING),
    RAPE_CUM_IDLE(0, true, false, true),
    RAPE_CUM(0, true, false, true, 34, RAPE_CUM_IDLE, 60.0f, -30.0f, false),
    CORRUPT_SLOW(0, true, false, -30.0f, -90.0f, false),
    CORRUPT_FAST(0, true, false, -30.0f, -90.0f, false),
    CORRUPT_CUM(0, true, false, false, -30.0f, -90.0f, false),
    CORRUPT_INTRO(0, true, false, true, 29, CORRUPT_SLOW),
    CONTROLLED_FLIGHT(0, true, true, true, true),
    BOOST(3, true, false, true, true, 43, CONTROLLED_FLIGHT),
    GALATH_SUMMON(0, false, false, false, true, 15, NULL),
    GALATH_DE_SUMMON(0, false, false, false, true),
    GIVE_COIN(0, true, false, true, true, 140, NULL),
    MASTERBATE(0, false, false),
    HUG_MANG(0, false, false, 239, NULL),
    RIDE_MOMMY_HEAD(0, false, true),
    THREESOME_SLOW(0, true, false, false, true),
    THREESOME_FAST(0, true, false, false, true),
    THREESOME_CUM(0, true, false, false, true),
    PUSSY_LICKING(0, false, true, false),
    MASTERBATE_SITTING(0, false, true, false),
    MASTERBATE_SITTING_CUM(0, false, false, false),
    MORNING_BLOWJOB_SLOW(0, true, true, true),
    MORNING_BLOWJOB_FAST(0, true, true, true),
    MORNING_BLOWJOB_CUM(0, true, false, true);

    public final int transitionTick;
    public final boolean hasPlayer;
    public final boolean autoBlink;
    public final float maxGirlPitch;
    public final float minGirlPitch;
    public final boolean flipGirlYaw;
    public int length;
    public int[] ticksPlaying = new int[]{0, 0};
    public fp followUp = null;
    public boolean useBoyCam;
    public boolean hideNameTag;

    private fp(int n3, boolean bl2, boolean bl3) {
        this.transitionTick = n3;
        this.hasPlayer = bl2;
        this.autoBlink = bl3;
        this.maxGirlPitch = 30.0f;
        this.minGirlPitch = -90.0f;
        this.flipGirlYaw = false;
        this.useBoyCam = false;
        this.hideNameTag = false;
    }

    private fp(int n3, boolean bl2, boolean bl3, boolean bl4) {
        this(n3, bl2, bl3);
        this.useBoyCam = bl4;
    }

    private fp(int n3, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        this(n3, bl2, bl3);
        this.useBoyCam = bl4;
        this.hideNameTag = bl5;
    }

    private fp(int n3, boolean bl2, boolean bl3, float f10, float f11, boolean bl4) {
        this.transitionTick = n3;
        this.hasPlayer = bl2;
        this.autoBlink = bl3;
        this.maxGirlPitch = f10;
        this.minGirlPitch = f11;
        this.flipGirlYaw = bl4;
        this.useBoyCam = false;
        this.hideNameTag = false;
    }

    private fp(int n3, boolean bl2, boolean bl3, float f10, float f11, boolean bl4, boolean bl5) {
        this.transitionTick = n3;
        this.hasPlayer = bl2;
        this.autoBlink = bl3;
        this.maxGirlPitch = f10;
        this.minGirlPitch = f11;
        this.flipGirlYaw = bl4;
        this.useBoyCam = false;
        this.hideNameTag = bl5;
    }

    private fp(int n3, boolean bl2, boolean bl3, boolean bl4, float f10, float f11, boolean bl5) {
        this.transitionTick = n3;
        this.hasPlayer = bl2;
        this.autoBlink = bl3;
        this.maxGirlPitch = f10;
        this.minGirlPitch = f11;
        this.flipGirlYaw = bl5;
        this.hideNameTag = false;
        this.useBoyCam = bl4;
    }

    private fp(int n3, boolean bl2, boolean bl3, int n4, fp fp2) {
        this(n3, bl2, bl3);
        this.length = n4;
        this.followUp = fp2;
    }

    private fp(int n3, boolean bl2, boolean bl3, boolean bl4, int n4, fp fp2) {
        this(n3, bl2, bl3);
        this.length = n4;
        this.followUp = fp2;
        this.useBoyCam = bl4;
    }

    private fp(int n3, boolean bl2, boolean bl3, boolean bl4, int n4, fp fp2, float f10, float f11, boolean bl5) {
        this.transitionTick = n3;
        this.hasPlayer = bl2;
        this.autoBlink = bl3;
        this.length = n4;
        this.followUp = fp2;
        this.useBoyCam = bl4;
        this.minGirlPitch = f10;
        this.maxGirlPitch = f11;
        this.flipGirlYaw = bl5;
    }

    private fp(int n3, boolean bl2, boolean bl3, boolean bl4, boolean bl5, int n4, fp fp2) {
        this(n3, bl2, bl3);
        this.length = n4;
        this.followUp = fp2;
        this.useBoyCam = bl5;
        this.hideNameTag = bl4;
    }

    public static boolean a(fp fp2, fp ... fpArray) {
        for (fp fp3 : fpArray) {
            try {
                if (fp2 != fp3) continue;
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw fp.a(runtimeException);
            }
        }
        return false;
    }

    public static boolean a(em em2, fp ... fpArray) {
        return fp.a(em2.y(), fpArray);
    }

    public static double a(AnimationController animationController) {
        try {
            if (animationController == null) {
                return 0.0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fp.a(runtimeException);
        }
        Animation animation = animationController.getCurrentAnimation();
        try {
            if (animation == null) {
                return 0.0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fp.a(runtimeException);
        }
        return animation.animationLength;
    }

    @SideOnly(value=Side.CLIENT)
    public static float a(em em2) {
        return (float)fp.a(em2.C);
    }

    @SideOnly(value=Side.CLIENT)
    public static float c(em em2, float f10) {
        return (float)(em2.getFactory().getOrCreateAnimationData((Integer)Integer.valueOf((int)em2.func_110124_au().hashCode())).tick + (double)f10 - em2.C.tickOffset);
    }

    @SideOnly(value=Side.CLIENT)
    public static float a(em em2, float f10) {
        return fp.c(em2, f10) / 20.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public static float d(em em2, float f10) {
        float f11 = fp.a(em2);
        try {
            if (f11 <= 0.0f) {
                return 0.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fp.a(runtimeException);
        }
        return be.b(fp.c(em2, f10) / f11, 0.0f, 1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public static boolean b(em em2, float f10) {
        boolean bl2;
        try {
            bl2 = fp.d(em2, f10) == 1.0f;
        }
        catch (RuntimeException runtimeException) {
            throw fp.a(runtimeException);
        }
        return bl2;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

