/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  net.minecraft.client.util.JsonException
 */
package software.bernie.geckolib3.util.json;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.util.JsonException;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.keyframe.BoneAnimation;
import software.bernie.geckolib3.core.keyframe.EventKeyFrame;
import software.bernie.geckolib3.core.keyframe.ParticleEventKeyFrame;
import software.bernie.geckolib3.core.keyframe.VectorKeyFrameList;
import software.bernie.geckolib3.util.AnimationUtils;
import software.bernie.geckolib3.util.json.JsonKeyFrameUtils;
import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class JsonAnimationUtils {
    public static Set<Map.Entry<String, JsonElement>> getAnimations(JsonObject json) {
        return JsonAnimationUtils.getObjectListAsArray(json.getAsJsonObject("animations"));
    }

    public static List<Map.Entry<String, JsonElement>> getBones(JsonObject json) {
        JsonObject bones = json.getAsJsonObject("bones");
        return bones == null ? new ArrayList<Map.Entry<String, JsonElement>>() : new ArrayList<Map.Entry<String, JsonElement>>(JsonAnimationUtils.getObjectListAsArray(bones));
    }

    public static Set<Map.Entry<String, JsonElement>> getRotationKeyFrames(JsonObject json) {
        JsonElement rotationObject = json.get("rotation");
        if (rotationObject.isJsonArray()) {
            return ImmutableSet.of(new AbstractMap.SimpleEntry<String, JsonArray>("0", rotationObject.getAsJsonArray()));
        }
        if (rotationObject.isJsonPrimitive()) {
            JsonPrimitive primitive = rotationObject.getAsJsonPrimitive();
            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(Arrays.asList(primitive, primitive, primitive));
            return ImmutableSet.of(new AbstractMap.SimpleEntry<String, JsonElement>("0", jsonElement));
        }
        return JsonAnimationUtils.getObjectListAsArray(rotationObject.getAsJsonObject());
    }

    public static Set<Map.Entry<String, JsonElement>> getPositionKeyFrames(JsonObject json) {
        JsonElement positionObject = json.get("position");
        if (positionObject.isJsonArray()) {
            return ImmutableSet.of(new AbstractMap.SimpleEntry<String, JsonArray>("0", positionObject.getAsJsonArray()));
        }
        if (positionObject.isJsonPrimitive()) {
            JsonPrimitive primitive = positionObject.getAsJsonPrimitive();
            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(Arrays.asList(primitive, primitive, primitive));
            return ImmutableSet.of(new AbstractMap.SimpleEntry<String, JsonElement>("0", jsonElement));
        }
        return JsonAnimationUtils.getObjectListAsArray(positionObject.getAsJsonObject());
    }

    public static Set<Map.Entry<String, JsonElement>> getScaleKeyFrames(JsonObject json) {
        JsonElement scaleObject = json.get("scale");
        if (scaleObject.isJsonArray()) {
            return ImmutableSet.of(new AbstractMap.SimpleEntry<String, JsonArray>("0", scaleObject.getAsJsonArray()));
        }
        if (scaleObject.isJsonPrimitive()) {
            JsonPrimitive primitive = scaleObject.getAsJsonPrimitive();
            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(Arrays.asList(primitive, primitive, primitive));
            return ImmutableSet.of(new AbstractMap.SimpleEntry<String, JsonElement>("0", jsonElement));
        }
        return JsonAnimationUtils.getObjectListAsArray(scaleObject.getAsJsonObject());
    }

    public static ArrayList<Map.Entry<String, JsonElement>> getSoundEffectFrames(JsonObject json) {
        JsonObject sound_effects = json.getAsJsonObject("sound_effects");
        return sound_effects == null ? new ArrayList<Map.Entry<String, JsonElement>>() : new ArrayList<Map.Entry<String, JsonElement>>(JsonAnimationUtils.getObjectListAsArray(sound_effects));
    }

    public static ArrayList<Map.Entry<String, JsonElement>> getParticleEffectFrames(JsonObject json) {
        JsonObject particle_effects = json.getAsJsonObject("particle_effects");
        return particle_effects == null ? new ArrayList<Map.Entry<String, JsonElement>>() : new ArrayList<Map.Entry<String, JsonElement>>(JsonAnimationUtils.getObjectListAsArray(particle_effects));
    }

    public static ArrayList<Map.Entry<String, JsonElement>> getCustomInstructionKeyFrames(JsonObject json) {
        JsonObject custom_instructions = json.getAsJsonObject("timeline");
        return custom_instructions == null ? new ArrayList<Map.Entry<String, JsonElement>>() : new ArrayList<Map.Entry<String, JsonElement>>(JsonAnimationUtils.getObjectListAsArray(custom_instructions));
    }

    private static JsonElement getObjectByKey(Set<Map.Entry<String, JsonElement>> json, String key) throws JsonException {
        return (JsonElement)json.stream().filter(x2 -> ((String)x2.getKey()).equals(key)).findFirst().orElseThrow(() -> new JsonException("Could not find key: " + key)).getValue();
    }

    public static Map.Entry<String, JsonElement> getAnimation(JsonObject animationFile, String animationName) throws JsonException {
        return new AbstractMap.SimpleEntry<String, JsonElement>(animationName, JsonAnimationUtils.getObjectByKey(JsonAnimationUtils.getAnimations(animationFile), animationName));
    }

    public static Set<Map.Entry<String, JsonElement>> getObjectListAsArray(JsonObject json) {
        return json.entrySet();
    }

    public static Animation deserializeJsonToAnimation(Map.Entry<String, JsonElement> element, MolangParser parser) throws ClassCastException, IllegalStateException {
        ArrayList<Map.Entry<String, JsonElement>> arrayList;
        ArrayList<Map.Entry<String, JsonElement>> particleKeyFrames;
        Animation animation = new Animation();
        JsonObject animationJsonObject = element.getValue().getAsJsonObject();
        animation.animationName = element.getKey();
        JsonElement animation_length = animationJsonObject.get("animation_length");
        animation.animationLength = animation_length == null ? null : Double.valueOf(AnimationUtils.convertSecondsToTicks(animation_length.getAsDouble()));
        animation.boneAnimations = new ArrayList<BoneAnimation>();
        JsonElement loop = animationJsonObject.get("loop");
        animation.loop = loop != null && loop.getAsString() == "true" ? ILoopType.EDefaultLoopTypes.LOOP : ILoopType.EDefaultLoopTypes.PLAY_ONCE;
        ArrayList<Map.Entry<String, JsonElement>> soundEffectFrames = JsonAnimationUtils.getSoundEffectFrames(animationJsonObject);
        if (soundEffectFrames != null) {
            for (Map.Entry<String, JsonElement> entry : soundEffectFrames) {
                animation.soundKeyFrames.add(new EventKeyFrame<String>(Double.parseDouble(entry.getKey()) * 20.0, entry.getValue().getAsJsonObject().get("effect").getAsString()));
            }
        }
        if ((particleKeyFrames = JsonAnimationUtils.getParticleEffectFrames(animationJsonObject)) != null) {
            for (Map.Entry<String, JsonElement> entry : particleKeyFrames) {
                JsonObject jsonObject = entry.getValue().getAsJsonObject();
                JsonElement effect = jsonObject.get("effect");
                JsonElement locator = jsonObject.get("locator");
                JsonElement pre_effect_script = jsonObject.get("pre_effect_script");
                animation.particleKeyFrames.add(new ParticleEventKeyFrame(Double.parseDouble(entry.getKey()) * 20.0, effect == null ? "" : effect.getAsString(), locator == null ? "" : locator.getAsString(), pre_effect_script == null ? "" : pre_effect_script.getAsString()));
            }
        }
        if ((arrayList = JsonAnimationUtils.getCustomInstructionKeyFrames(animationJsonObject)) != null) {
            for (Map.Entry<String, JsonElement> entry : arrayList) {
                animation.customInstructionKeyframes.add(new EventKeyFrame<String>(Double.parseDouble(entry.getKey()) * 20.0, entry.getValue() instanceof JsonArray ? JsonAnimationUtils.convertJsonArrayToList(entry.getValue().getAsJsonArray()).toString() : entry.getValue().getAsString()));
            }
        }
        List<Map.Entry<String, JsonElement>> list = JsonAnimationUtils.getBones(animationJsonObject);
        for (Map.Entry<String, JsonElement> bone : list) {
            BoneAnimation boneAnimation = new BoneAnimation();
            boneAnimation.boneName = bone.getKey();
            JsonObject boneJsonObj = bone.getValue().getAsJsonObject();
            try {
                Set<Map.Entry<String, JsonElement>> scaleKeyFramesJson = JsonAnimationUtils.getScaleKeyFrames(boneJsonObj);
                boneAnimation.scaleKeyFrames = JsonKeyFrameUtils.convertJsonToKeyFrames(new ArrayList<Map.Entry<String, JsonElement>>(scaleKeyFramesJson), parser);
            }
            catch (Exception e10) {
                boneAnimation.scaleKeyFrames = new VectorKeyFrameList();
            }
            try {
                Set<Map.Entry<String, JsonElement>> positionKeyFramesJson = JsonAnimationUtils.getPositionKeyFrames(boneJsonObj);
                boneAnimation.positionKeyFrames = JsonKeyFrameUtils.convertJsonToKeyFrames(new ArrayList<Map.Entry<String, JsonElement>>(positionKeyFramesJson), parser);
            }
            catch (Exception e11) {
                boneAnimation.positionKeyFrames = new VectorKeyFrameList();
            }
            try {
                Set<Map.Entry<String, JsonElement>> rotationKeyFramesJson = JsonAnimationUtils.getRotationKeyFrames(boneJsonObj);
                boneAnimation.rotationKeyFrames = JsonKeyFrameUtils.convertJsonToRotationKeyFrames(new ArrayList<Map.Entry<String, JsonElement>>(rotationKeyFramesJson), parser);
            }
            catch (Exception e12) {
                boneAnimation.rotationKeyFrames = new VectorKeyFrameList();
            }
            animation.boneAnimations.add(boneAnimation);
        }
        if (animation.animationLength == null) {
            animation.animationLength = JsonAnimationUtils.calculateLength(animation.boneAnimations);
        }
        return animation;
    }

    private static double calculateLength(List<BoneAnimation> boneAnimations) {
        double longestLength = 0.0;
        for (BoneAnimation animation : boneAnimations) {
            double xKeyframeTime = animation.rotationKeyFrames.getLastKeyframeTime();
            double yKeyframeTime = animation.positionKeyFrames.getLastKeyframeTime();
            double zKeyframeTime = animation.scaleKeyFrames.getLastKeyframeTime();
            longestLength = JsonAnimationUtils.maxAll(longestLength, xKeyframeTime, yKeyframeTime, zKeyframeTime);
        }
        return longestLength == 0.0 ? Double.MAX_VALUE : longestLength;
    }

    static List<IValue> convertJsonArrayToList(JsonArray array) {
        return (List)new Gson().fromJson((JsonElement)array, ArrayList.class);
    }

    public static double maxAll(double ... values) {
        double max = 0.0;
        for (double value : values) {
            max = Math.max(value, max);
        }
        return max;
    }
}

