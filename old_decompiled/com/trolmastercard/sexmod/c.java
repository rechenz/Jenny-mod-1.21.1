/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.r;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class c {
    public static final SoundEvent[] MISC_PLOB = new SoundEvent[1];
    public static final SoundEvent[] MISC_BELLJINGLE = new SoundEvent[1];
    public static final SoundEvent[] MISC_BEDRUSTLE = new SoundEvent[2];
    public static final SoundEvent[] MISC_SLAP = new SoundEvent[2];
    public static final SoundEvent[] MISC_TOUCH = new SoundEvent[2];
    public static final SoundEvent[] MISC_POUNDING = new SoundEvent[35];
    public static final SoundEvent[] MISC_SMALLINSERTS = new SoundEvent[5];
    public static final SoundEvent[] MISC_INSERTS = new SoundEvent[5];
    public static final SoundEvent[] MISC_CUMINFLATION = new SoundEvent[1];
    public static final SoundEvent[] MISC_SCREAM = new SoundEvent[2];
    public static final SoundEvent[] MISC_FART = new SoundEvent[3];
    public static final SoundEvent[] MISC_JUMP = new SoundEvent[1];
    public static final SoundEvent[] MISC_EAT = new SoundEvent[3];
    public static final SoundEvent[] MISC_SLIDE = new SoundEvent[7];
    public static final SoundEvent[] GIRLS_JENNY_AFTERSESSIONMOAN = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_JENNY_AHH = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_JENNY_BJMOAN = new SoundEvent[13];
    public static final SoundEvent[] GIRLS_JENNY_GIGGLE = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_JENNY_HAPPYOH = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_JENNY_HEAVYBREATHING = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_JENNY_HMPH = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_JENNY_HUH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_JENNY_LIGHTBREATHING = new SoundEvent[12];
    public static final SoundEvent[] GIRLS_JENNY_LIPSOUND = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_JENNY_MMM = new SoundEvent[9];
    public static final SoundEvent[] GIRLS_JENNY_MOAN = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_JENNY_SADOH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_JENNY_SIGH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ELLIE_AFTERSESSIONMOAN = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_ELLIE_AHH = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_ELLIE_BJMOAN = new SoundEvent[13];
    public static final SoundEvent[] GIRLS_ELLIE_GIGGLE = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_ELLIE_HAPPYOH = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_ELLIE_HEAVYBREATHING = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_ELLIE_HMPH = new SoundEvent[4];
    public static final SoundEvent[] GIRLS_ELLIE_HUH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ELLIE_LIGHTBREATHING = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_ELLIE_LIPSOUND = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_ELLIE_MMM = new SoundEvent[9];
    public static final SoundEvent[] GIRLS_ELLIE_MOAN = new SoundEvent[9];
    public static final SoundEvent[] GIRLS_ELLIE_SADOH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ELLIE_SIGH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ELLIE_COMETOMOMMY = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ELLIE_GOODBOY = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ELLIE_MOMMYHORNY = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_BIA_AHH = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_BIA_BJMOAN = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_BIA_BREATH = new SoundEvent[4];
    public static final SoundEvent[] GIRLS_BIA_GIGGLE = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_BIA_HEY = new SoundEvent[4];
    public static final SoundEvent[] GIRLS_BIA_HUH = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_BIA_MMM = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_LUNA_AHH = new SoundEvent[18];
    public static final SoundEvent[] GIRLS_LUNA_CUTENYA = new SoundEvent[12];
    public static final SoundEvent[] GIRLS_LUNA_HAPPYOH = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_LUNA_HMPH = new SoundEvent[6];
    public static final SoundEvent[] GIRLS_LUNA_HORNINYA = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_LUNA_HUH = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_LUNA_LIGHTBREATHING = new SoundEvent[25];
    public static final SoundEvent[] GIRLS_LUNA_MMM = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_LUNA_MOAN = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_LUNA_SADOH = new SoundEvent[7];
    public static final SoundEvent[] GIRLS_LUNA_SIGH = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_LUNA_SINGING = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_LUNA_GIGGLE = new SoundEvent[15];
    public static final SoundEvent[] GIRLS_LUNA_OUU = new SoundEvent[13];
    public static final SoundEvent[] GIRLS_LUNA_OWO = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_ALLIE_AFTERSESSIONMOAN = new SoundEvent[4];
    public static final SoundEvent[] GIRLS_ALLIE_AHH = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_ALLIE_BJMOAN = new SoundEvent[14];
    public static final SoundEvent[] GIRLS_ALLIE_GIGGLE = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_ALLIE_HAPPYOH = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_ALLIE_HEAVYBREATHING = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_ALLIE_HMPH = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_ALLIE_HUH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ALLIE_LIGHTBREATHING = new SoundEvent[11];
    public static final SoundEvent[] GIRLS_ALLIE_LIPSOUND = new SoundEvent[14];
    public static final SoundEvent[] GIRLS_ALLIE_MMM = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_ALLIE_MOAN = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_ALLIE_SADOH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ALLIE_SIGH = new SoundEvent[2];
    public static final SoundEvent[] GIRLS_ALLIE_SCAWY = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_KOBOLD_BJMOAN = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_KOBOLD_GIGGLE = new SoundEvent[4];
    public static final SoundEvent[] GIRLS_KOBOLD_HAA = new SoundEvent[7];
    public static final SoundEvent[] GIRLS_KOBOLD_HEYMASTER = new SoundEvent[6];
    public static final SoundEvent[] GIRLS_KOBOLD_INTERESTED = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_KOBOLD_LIGHTBREATHING = new SoundEvent[12];
    public static final SoundEvent[] GIRLS_KOBOLD_MASTER = new SoundEvent[6];
    public static final SoundEvent[] GIRLS_KOBOLD_MOAN = new SoundEvent[10];
    public static final SoundEvent[] GIRLS_KOBOLD_ORGASM = new SoundEvent[4];
    public static final SoundEvent[] GIRLS_KOBOLD_SAD = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_KOBOLD_YEP = new SoundEvent[7];
    public static final SoundEvent[] MISC_FLAP = new SoundEvent[4];
    public static final SoundEvent[] MISC_SHATTER = new SoundEvent[1];
    public static final SoundEvent[] MISC_WEOWEO = new SoundEvent[4];
    public static final SoundEvent[] MISC_BEEW = new SoundEvent[3];
    public static final SoundEvent[] MISC_CLAP = new SoundEvent[1];
    public static final SoundEvent[] GIRLS_GALATH_AHH = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_GALATH_BREATHING = new SoundEvent[7];
    public static final SoundEvent[] GIRLS_GALATH_DIALOG = new SoundEvent[6];
    public static final SoundEvent[] GIRLS_GALATH_GIGGLE = new SoundEvent[4];
    public static final SoundEvent[] GIRLS_GALATH_HMPH = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_GALATH_HUH = new SoundEvent[3];
    public static final SoundEvent[] GIRLS_GALATH_LIGHTCHARGE = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_GALATH_MOAN = new SoundEvent[8];
    public static final SoundEvent[] GIRLS_GALATH_STRONGCHARGE = new SoundEvent[4];
    public static final SoundEvent[] GIRLS_GALATH_UUH = new SoundEvent[7];
    public static final SoundEvent[] GIRLS_GALATH_ORGASM = new SoundEvent[5];
    public static final SoundEvent[] GIRLS_GALATH_AAA = new SoundEvent[2];
    public static final SoundEvent[] MISC_PYRO = new SoundEvent[1];
    static HashMap<SoundEvent, Integer> lastRandomSound = new HashMap();

    /*
     * Loose catch block
     */
    public static void a() {
        for (Field field : c.class.getDeclaredFields()) {
            String string;
            SoundEvent[] soundEventArray;
            block13: {
                Class<?> clazz = field.getType();
                if (!clazz.isArray()) continue;
                try {
                    if (clazz.getComponentType() != SoundEvent.class) {
                        continue;
                    }
                    break block13;
                    catch (Exception exception) {
                        throw c.a(exception);
                    }
                }
                catch (Exception exception) {
                    throw c.a(exception);
                }
            }
            try {
                soundEventArray = (SoundEvent[])field.get(null);
            }
            catch (Exception exception) {
                Main.LOGGER.error("Error registering sound: " + exception.getMessage());
                continue;
            }
            String string2 = field.getName().toLowerCase().replace("_", ".");
            String[] stringArray = string2.split("\\.");
            try {
                string = stringArray.length > 2 ? stringArray[2] : stringArray[1];
            }
            catch (Exception exception) {
                throw c.a(exception);
            }
            String string3 = string;
            try {
                for (int i2 = 0; i2 < soundEventArray.length; ++i2) {
                    soundEventArray[i2] = c.a(String.format("%s.%s%s", string2, string3, i2));
                }
            }
            catch (Exception exception) {
                throw c.a(exception);
            }
        }
    }

    public static SoundEvent a(String string) {
        ResourceLocation resourceLocation = new ResourceLocation("sexmod", string);
        SoundEvent soundEvent = new SoundEvent(resourceLocation);
        soundEvent.setRegistryName(string);
        ForgeRegistries.SOUND_EVENTS.register((IForgeRegistryEntry)soundEvent);
        return soundEvent;
    }

    public static SoundEvent a(SoundEvent[] soundEventArray) {
        int n2;
        lastRandomSound.putIfAbsent(soundEventArray[0], -69);
        int n3 = 0;
        do {
            n2 = r.f.nextInt(soundEventArray.length);
        } while (++n3 < 10 && n2 == lastRandomSound.get(soundEventArray[0]));
        lastRandomSound.replace(soundEventArray[0], n2);
        return soundEventArray[n2];
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

