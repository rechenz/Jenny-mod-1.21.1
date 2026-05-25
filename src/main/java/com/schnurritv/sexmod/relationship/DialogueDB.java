package com.schnurritv.sexmod.relationship;

import java.util.*;

/**
 * Dialog database with personality-based lines for each character.
 * Each character has lines tiered by affection level.
 */
public class DialogueDB {

    private static final Random RAND = new Random();

    private static final Map<String, List<String>> DIALOGUES = new HashMap<>();
    private static boolean initialized = false;

    // ================================================================
    //  JENNY — Playful, teasing, competitive gamer energy
    // ================================================================
    static {
        put("jenny_stranger_greeting",
            "Whoa, a player! Hey, you got any good games?",
            "Huh? You looking at me?",
            "Don't mind me, just passing through this dimension.",
            "This server's spawn rate is garbage. Oh — you're a real person.");
        put("jenny_acquainted_greeting",
            "Oh, you again. Game's still running, huh?",
            "What's the quest today?",
            "You look like you need a co-op partner.");
        put("jenny_friendly_greeting",
            "Hey! Was just thinking about you. No wait — that came out wrong.",
            "So what's the plan? Dungeon? Build? Or just vibing?",
            "You know, this world's actually more fun with you around.");
        put("jenny_close_greeting",
            "There you are! Getting worried about your survival stats.",
            "Pfft, late again. I was about to start the boss fight without you.",
            "Miss me? ... What? I didn't say anything!");
        put("jenny_intimate_greeting",
            "Finally! I was getting so bored without you.",
            "I logged in just for you today. Don't let it go to your head.",
            "Hey... wanna just sit here for a bit? No quests, no mobs. Just us.");
    }

    // ================================================================
    //  ALLIE — Gentle, warm, protective healer type
    // ================================================================
    static {
        put("allie_stranger_greeting",
            "Oh, hello there. Are you lost, traveler?",
            "Welcome to my humble corner of the world.",
            "You look tired. Come rest by the fire.");
        put("allie_acquainted_greeting",
            "Back already? I'll put the kettle on.",
            "You always come by when I'm brewing something new.",
            "Take a seat. The world can wait.");
        put("allie_friendly_greeting",
            "I was hoping you'd visit today.",
            "You know, I made extra cookies. Totally planned, definitely not for you.",
            "Your aura looks brighter today. Did something good happen?");
        put("allie_close_greeting",
            "I saved you some dinner. You better eat it.",
            "You work too hard. Sit down before I make you.",
            "Sometimes I worry about you out there alone.");
        put("allie_intimate_greeting",
            "Home stopped feeling like home when you weren't here.",
            "Come here. You need a hug. No arguing.",
            "Every day you come back safe is a good day.");
    }

    // ================================================================
    //  ELLIE — Tsundere, dommy-mommy energy, secretly soft
    // ================================================================
    static {
        put("ellie_stranger_greeting",
            "Hmph. Another adventurer who thinks they can handle these lands.",
            "You look... capable. Barely.",
            "Don't just stand there. State your business.");
        put("ellie_acquainted_greeting",
            "Oh, it's you. Try not to die this time.",
            "I see you're still alive. Impressive, I suppose.",
            "Come to bother me again? ... Fine, I'll allow it.");
        put("ellie_friendly_greeting",
            "About time you showed up. I was getting bored.",
            "You better not have been fighting without me.",
            "That armor looks terrible. Let me fix it. No, I'm not being nice.");
        put("ellie_close_greeting",
            "You're late. I was... not worried. Just noting the time.",
            "Here. I enchanted your stuff. Don't read into it.",
            "If you die out there I'll resurrect you just to kill you myself.");
        put("ellie_intimate_greeting",
            "There you are. I was starting to think— never mind.",
            "Stay tonight. The inn's empty and it's raining.",
            "I don't say this often but... I'm glad it's you.");
    }

    // ================================================================
    //  BIA — Mysterious, quiet, collects strange things
    // ================================================================
    static {
        put("bia_stranger_greeting",
            "...",
            "You can see me? Interesting.",
            "Most people walk right past.");
        put("bia_acquainted_greeting",
            "You came back.",
            "Did you bring anything... shiny?",
            "I was watching the stars. Want to join?");
        put("bia_friendly_greeting",
            "I found something weird today. Show you later?",
            "You bring interesting energy. I like it.",
            "Want to see my collection?");
        put("bia_close_greeting",
            "I saved this spot for you.",
            "The moon's full tonight. Good fortune.",
            "You're the only one who doesn't find me strange. Thank you.");
        put("bia_intimate_greeting",
            "I knew you'd come tonight. I felt it.",
            "Can we just... stay here? Forever doesn't seem so bad with you.",
            "You're my favorite mystery.");
    }

    // ================================================================
    //  BEE — Bubbly, hyperactive, loves honey
    // ================================================================
    static {
        put("bee_stranger_greeting",
            "BZZZT! Oh sorry, force of habit. Hi!",
            "Ooh a visitor! Do you like flowers??",
            "Hellohellohello! What brings you— wait, do you smell like honey?");
        put("bee_acquainted_greeting",
            "Bzz! It's you! Got any snacks?",
            "I pollinated so many flowers today you won't believe it!",
            "Hi again! You're the one who doesn't run away from the buzzing!");
        put("bee_friendly_greeting",
            "You came back! I saved you some honeycomb!",
            "Let's go exploring! I found a meadow with the BEST flowers!",
            "Bzz bzz! That means 'I'm happy to see you' in bee!");
        put("bee_close_greeting",
            "You're my favorite non-bee person. Don't tell the hive!",
            "I made you a flower crown! ... It might have pollen, sorry.",
            "Every day with you is the sweetest day!");
        put("bee_intimate_greeting",
            "Buzz buzz buzz... that's my heart doing the happy dance!",
            "Can I stay with you forever? I'll make honey every day!",
            "You're sweeter than a whole field of poppies!");
    }

    // ================================================================
    //  CAT — Lazy, sarcastic, secretly affectionate
    // ================================================================
    static {
        put("cat_stranger_greeting",
            "Nya... who're you?",
            "This is my sunbeam. Find your own.",
            "You may pet me. Once. Maybe.");
        put("cat_acquainted_greeting",
            "Oh, you again. Fine, you can sit there.",
            "I wasn't waiting for you. I was just... sitting here.",
            "Don't touch my tail.");
        put("cat_friendly_greeting",
            "Nya~ You brought fish? ... You didn't. Disappointing.",
            "I GUESS I'm happy to see you. Whatever.",
            "If you scratch behind my ears I might purr. Might.");
        put("cat_close_greeting",
            "Purrr... I mean, hello. Normally.",
            "I saved the sunny spot for you. Only because you're warm.",
            "Okay fine. I do like when you visit. Don't tell anyone.");
        put("cat_intimate_greeting",
            "Purrrrr... get over here. Petting time.",
            "I'll protect you. No monster messes with what's mine.",
            "You're the only human I'd share my bed with. Nya.");
    }

    // ================================================================
    //  GOBLIN — Gruff, pragmatic, surprisingly loyal
    // ================================================================
    static {
        put("goblin_stranger_greeting",
            "You lost or stupid?",
            "Don't touch my stuff.",
            "Adventurer. Great. Another one.",
            "What do YOU want?");
        put("goblin_acquainted_greeting",
            "You again. Not dead yet, huh?",
            "Got any useful loot this time?",
            "Try not to step in the traps.");
        put("goblin_friendly_greeting",
            "I saved you some stew. It's not poisoned.",
            "You fight okay. For a surface dweller.",
            "Watch my back and I'll watch yours. Deal?");
        put("goblin_close_greeting",
            "Finally. Got a new gadget to show you.",
            "You're the only one I trust in my camp. Don't betray that.",
            "Here, take this. Found it on a dead adventurer. ...What?");
        put("goblin_intimate_greeting",
            "Camp's warmer when you're here.",
            "If anyone hurts you, I'll bury them.",
            "I never thought I'd say this to a surface-walker... stay.");
    }

    // ================================================================
    //  KOBOLD — Innocent, excitable, loves shinies
    // ================================================================
    static {
        put("kobold_stranger_greeting",
            "Yip! A big person! Don't hurt!",
            "Shiny? You have shiny?",
            "Kobold is scared but also curious!",
            "What big person want from little cave?");
        put("kobold_acquainted_greeting",
            "Yip yip! Big person returns!",
            "Kobold found a rock! Very shiny! Want to see?",
            "You not mean like others. Kobold trusts.");
        put("kobold_friendly_greeting",
            "Kobold dug a tunnel just for you! Not very straight but is tunnel!",
            "You want to see hoard? It's not big but it's MINE!",
            "Kobold thinks big person is actually good person!");
        put("kobold_close_greeting",
            "Yip yip yip! Best day is day you visit!",
            "Kobold saved you a shiny! Polished it and everything!",
            "Pack says you're weird but good weird. Pack likes you.");
        put("kobold_intimate_greeting",
            "Kobold's hoard is your hoard now!",
            "You make cave feel like home. Kobold never had home before.",
            "If you leave Kobold for too long, Kobold will come find you!");
    }

    // ================================================================
    //  SLIME — Curious, innocent, fascinated by solid things
    // ================================================================
    static {
        put("slime_stranger_greeting",
            "Blub? A solid person! How do you stay together?",
            "Ooh! You're all... not squishy! That's AMAZING!",
            "Hello! I'm not going to absorb you, promise!",
            "What's it like having bones??");
        put("slime_acquainted_greeting",
            "Blub blub! It's the solid friend!",
            "I collected some pebbles today! They're like us but hard!",
            "Want to squish some things together?");
        put("slime_friendly_greeting",
            "I bounced all the way here to see you! Well, rolled. Same thing!",
            "You're my favorite solid! Don't tell the rocks.",
            "Can I hold your hand? I'll be careful not to dissolve it!");
        put("slime_close_greeting",
            "Bluuuub~ You're here! I was about to ooze out looking for you!",
            "I made you something! It's... it's dissolving. Oh no.",
            "You're the reason I stay solid. Figuratively!");
        put("slime_intimate_greeting",
            "Blub blub blub... that means I love you in slime language!",
            "I'd give up my nucleus for you! ...That's a big deal!",
            "You make me feel solid inside. Is that weird?");
    }

    // ================================================================
    //  Gift reactions (by item)
    // ================================================================
    static {
        put("jenny_gift_love",    "Wait, a Copper Gear?! For me? ... This is EPIC. You really know me.");
        put("jenny_gift_like",    "Oh sweet! A gift? Let me check the stats... approved!");
        put("jenny_gift_neutral", "Uh, thanks? I'll... add this to my inventory I guess.");
        put("jenny_gift_dislike", "What am I supposed to do with this? Salvage materials?");

        put("allie_gift_love",    "Moonlight Lily... you went all the way to the swamp at night? For me?");
        put("allie_gift_like",    "Oh, you're too sweet. Let me put these in a vase.");
        put("allie_gift_neutral", "Thank you dear. It's the thought that counts.");
        put("allie_gift_dislike", "Oh. Um. I'll just... put this somewhere. Thank you.");

        put("ellie_gift_love",    "An Enchanted Quill?! Do you have any idea how rare these are?! ...Thank you. Genuinely.");
        put("ellie_gift_like",    "Hmph. You DO have taste. I'll add this to my collection.");
        put("ellie_gift_neutral", "A present? For me? ... Well, I suppose I can't refuse.");
        put("ellie_gift_dislike", "Is this a joke? This is the opposite of thoughtful.");
    }

    // ================================================================
    //  Misc
    // ================================================================
    static {
        put("gift_limit_reached", "You've already given me enough today. Save some for tomorrow!");
        put("gift_first_ever",    "Wait... is this... for ME? Nobody's ever given me anything before!");
        put("scene_locked",       "I... I don't think we're that close yet. Maybe someday.");
        put("follow_start",      "Alright, lead the way!");
        put("follow_stop",       "I'll wait here then.");
    }

    static {
        initialized = true;
    }

    // ── API ──

    private static void put(String key, String... lines) {
        DIALOGUES.put(key, new ArrayList<>(Arrays.asList(lines)));
    }

    /**
     * Get a random line for a key, with optional format args.
     * Example: getRandom("jenny_intimate_greeting") → one random greeting
     */
    public static String getRandom(String key, Object... args) {
        String formattedKey = String.format(key, args);
        List<String> lines = DIALOGUES.getOrDefault(formattedKey, DIALOGUES.get(key));
        if (lines == null || lines.isEmpty()) return "...";
        return lines.get(RAND.nextInt(lines.size()));
    }

    /**
     * Get gift reaction based on affection gain tier.
     * love: 15+ gain, like: 8-14, neutral: 1-7, dislike: ≤0
     */
    public static String getGiftReaction(String girlName, int affectionGain) {
        String key;
        if (affectionGain >= 15) key = "%s_gift_love";
        else if (affectionGain >= 8) key = "%s_gift_like";
        else if (affectionGain > 0) key = "%s_gift_neutral";
        else key = "%s_gift_dislike";
        return getRandom(key, girlName);
    }

    /** Get scene locked message for a girl */
    public static String getSceneLocked(String girlName) {
        return getRandom("scene_locked");
    }
}
