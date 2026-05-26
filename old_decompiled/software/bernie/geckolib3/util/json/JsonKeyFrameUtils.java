/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package software.bernie.geckolib3.util.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.ConstantValue;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.keyframe.KeyFrame;
import software.bernie.geckolib3.core.keyframe.VectorKeyFrameList;
import software.bernie.geckolib3.util.AnimationUtils;
import software.bernie.geckolib3.util.json.JsonAnimationUtils;
import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.molang.MolangException;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class JsonKeyFrameUtils {
    private static VectorKeyFrameList<KeyFrame<IValue>> convertJson(List<Map.Entry<String, JsonElement>> element, boolean isRotation, MolangParser parser) throws NumberFormatException, MolangException {
        IValue previousXValue = null;
        IValue previousYValue = null;
        IValue previousZValue = null;
        ArrayList<KeyFrame<IValue>> xKeyFrames = new ArrayList<KeyFrame<IValue>>();
        ArrayList<KeyFrame<IValue>> yKeyFrames = new ArrayList<KeyFrame<IValue>>();
        ArrayList<KeyFrame<IValue>> zKeyFrames = new ArrayList<KeyFrame<IValue>>();
        for (int i2 = 0; i2 < element.size(); ++i2) {
            KeyFrame<IValue> zKeyFrame;
            KeyFrame<IValue> yKeyFrame;
            KeyFrame<IValue> xKeyFrame;
            IValue currentZValue;
            Map.Entry<String, JsonElement> keyframe = element.get(i2);
            Map.Entry<String, JsonElement> previousKeyFrame = i2 == 0 ? null : element.get(i2 - 1);
            Double previousKeyFrameLocation = previousKeyFrame == null ? 0.0 : Double.parseDouble(previousKeyFrame.getKey());
            Double currentKeyFrameLocation = NumberUtils.isCreatable(keyframe.getKey()) ? Double.parseDouble(keyframe.getKey()) : 0.0;
            Double animationTimeDifference = currentKeyFrameLocation - previousKeyFrameLocation;
            JsonArray vectorJsonArray = JsonKeyFrameUtils.getKeyFrameVector(keyframe.getValue());
            IValue xValue = JsonKeyFrameUtils.parseExpression(parser, vectorJsonArray.get(0));
            IValue yValue = JsonKeyFrameUtils.parseExpression(parser, vectorJsonArray.get(1));
            IValue zValue = JsonKeyFrameUtils.parseExpression(parser, vectorJsonArray.get(2));
            IValue currentXValue = isRotation && xValue instanceof ConstantValue ? ConstantValue.fromDouble(Math.toRadians(-xValue.get())) : xValue;
            IValue currentYValue = isRotation && yValue instanceof ConstantValue ? ConstantValue.fromDouble(Math.toRadians(-yValue.get())) : yValue;
            IValue iValue = currentZValue = isRotation && zValue instanceof ConstantValue ? ConstantValue.fromDouble(Math.toRadians(zValue.get())) : zValue;
            if (keyframe.getValue().isJsonObject() && JsonKeyFrameUtils.hasEasingType(keyframe.getValue())) {
                EasingType easingType = JsonKeyFrameUtils.getEasingType(keyframe.getValue());
                if (JsonKeyFrameUtils.hasEasingArgs(keyframe.getValue())) {
                    List<IValue> easingArgs = JsonKeyFrameUtils.getEasingArgs(keyframe.getValue());
                    xKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentXValue : previousXValue, currentXValue, easingType, easingArgs);
                    yKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentYValue : previousYValue, currentYValue, easingType, easingArgs);
                    zKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentZValue : previousZValue, currentZValue, easingType, easingArgs);
                } else {
                    xKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentXValue : previousXValue, currentXValue, easingType);
                    yKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentYValue : previousYValue, currentYValue, easingType);
                    zKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentZValue : previousZValue, currentZValue, easingType);
                }
            } else {
                xKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentXValue : previousXValue, currentXValue);
                yKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentYValue : previousYValue, currentYValue);
                zKeyFrame = new KeyFrame<IValue>(AnimationUtils.convertSecondsToTicks(animationTimeDifference), i2 == 0 ? currentZValue : previousZValue, currentZValue);
            }
            previousXValue = currentXValue;
            previousYValue = currentYValue;
            previousZValue = currentZValue;
            xKeyFrames.add(xKeyFrame);
            yKeyFrames.add(yKeyFrame);
            zKeyFrames.add(zKeyFrame);
        }
        return new VectorKeyFrameList<KeyFrame<IValue>>(xKeyFrames, yKeyFrames, zKeyFrames);
    }

    private static JsonArray getKeyFrameVector(JsonElement element) {
        if (element.isJsonArray()) {
            return element.getAsJsonArray();
        }
        return element.getAsJsonObject().get("vector").getAsJsonArray();
    }

    private static boolean hasEasingType(JsonElement element) {
        return element.getAsJsonObject().has("easing");
    }

    private static boolean hasEasingArgs(JsonElement element) {
        return element.getAsJsonObject().has("easingArgs");
    }

    private static EasingType getEasingType(JsonElement element) {
        String easingString = element.getAsJsonObject().get("easing").getAsString();
        try {
            String uppercaseEasingString = Character.toUpperCase(easingString.charAt(0)) + easingString.substring(1);
            EasingType easing = EasingType.valueOf(uppercaseEasingString);
            return easing;
        }
        catch (Exception e10) {
            GeckoLib.LOGGER.fatal("Unknown easing type: {}", (Object)easingString);
            throw new RuntimeException(e10);
        }
    }

    private static List<IValue> getEasingArgs(JsonElement element) {
        JsonObject asJsonObject = element.getAsJsonObject();
        JsonElement easingArgs = asJsonObject.get("easingArgs");
        JsonArray asJsonArray = easingArgs.getAsJsonArray();
        return JsonAnimationUtils.convertJsonArrayToList(asJsonArray);
    }

    public static VectorKeyFrameList<KeyFrame<IValue>> convertJsonToKeyFrames(List<Map.Entry<String, JsonElement>> element, MolangParser parser) throws NumberFormatException, MolangException {
        return JsonKeyFrameUtils.convertJson(element, false, parser);
    }

    public static VectorKeyFrameList<KeyFrame<IValue>> convertJsonToRotationKeyFrames(List<Map.Entry<String, JsonElement>> element, MolangParser parser) throws NumberFormatException, MolangException {
        VectorKeyFrameList<KeyFrame<IValue>> frameList = JsonKeyFrameUtils.convertJson(element, true, parser);
        return new VectorKeyFrameList<KeyFrame<IValue>>(frameList.xKeyFrames, frameList.yKeyFrames, frameList.zKeyFrames);
    }

    public static IValue parseExpression(MolangParser parser, JsonElement element) throws MolangException {
        if (element.getAsJsonPrimitive().isString()) {
            return parser.parseJson(element);
        }
        return ConstantValue.fromDouble(element.getAsDouble());
    }
}

