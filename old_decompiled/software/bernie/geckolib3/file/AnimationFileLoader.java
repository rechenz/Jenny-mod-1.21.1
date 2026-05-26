/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.util.JsonException
 *  net.minecraft.util.JsonUtils
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 */
package software.bernie.geckolib3.file;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.util.json.JsonAnimationUtils;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class AnimationFileLoader {
    public AnimationFile loadAllAnimations(MolangParser parser, ResourceLocation location, IResourceManager manager) {
        AnimationFile animationFile = new AnimationFile();
        JsonObject jsonRepresentation = this.loadFile(location, manager);
        Set<Map.Entry<String, JsonElement>> entrySet = JsonAnimationUtils.getAnimations(jsonRepresentation);
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String animationName = entry.getKey();
            try {
                Animation animation = JsonAnimationUtils.deserializeJsonToAnimation(JsonAnimationUtils.getAnimation(jsonRepresentation, animationName), parser);
                animationFile.putAnimation(animationName, animation);
            }
            catch (JsonException e10) {
                GeckoLib.LOGGER.error("Could not load animation: {}", (Object)animationName, (Object)e10);
                throw new RuntimeException(e10);
            }
        }
        return animationFile;
    }

    private JsonObject loadFile(ResourceLocation location, IResourceManager manager) {
        String content = AnimationFileLoader.getResourceAsString(location, manager);
        Gson GSON = new Gson();
        return (JsonObject)JsonUtils.func_193839_a((Gson)GSON, (Reader)new StringReader(content), JsonObject.class);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getResourceAsString(ResourceLocation location, IResourceManager manager) {
        try (InputStream inputStream = manager.func_110536_a(location).func_110527_b();){
            String string = IOUtils.toString((InputStream)inputStream, (Charset)Charset.defaultCharset());
            return string;
        }
        catch (Exception e10) {
            String message = "Couldn't load " + location;
            GeckoLib.LOGGER.error(message, (Throwable)e10);
            throw new RuntimeException(new FileNotFoundException(location.toString()));
        }
    }
}

