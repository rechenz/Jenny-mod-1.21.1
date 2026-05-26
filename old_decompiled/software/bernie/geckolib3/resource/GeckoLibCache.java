/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.AbstractResourcePack
 *  net.minecraft.client.resources.FileResourcePack
 *  net.minecraft.client.resources.FolderResourcePack
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.LegacyV2Adapter
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.client.FMLFolderResourcePack
 */
package software.bernie.geckolib3.resource;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.LegacyV2Adapter;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.FMLFolderResourcePack;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.file.AnimationFileLoader;
import software.bernie.geckolib3.file.GeoModelLoader;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.molang.MolangRegistrar;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class GeckoLibCache
implements IResourceManagerReloadListener {
    private static GeckoLibCache INSTANCE;
    private final AnimationFileLoader animationLoader;
    private final GeoModelLoader modelLoader;
    public final MolangParser parser = new MolangParser();
    private HashMap<ResourceLocation, AnimationFile> animations = new HashMap();
    private HashMap<ResourceLocation, GeoModel> geoModels = new HashMap();

    public HashMap<ResourceLocation, AnimationFile> getAnimations() {
        if (!GeckoLib.hasInitialized) {
            throw new RuntimeException("GeckoLib was never initialized! Please read the documentation!");
        }
        return this.animations;
    }

    public HashMap<ResourceLocation, GeoModel> getGeoModels() {
        if (!GeckoLib.hasInitialized) {
            throw new RuntimeException("GeckoLib was never initialized! Please read the documentation!");
        }
        return this.geoModels;
    }

    protected GeckoLibCache() {
        this.animationLoader = new AnimationFileLoader();
        this.modelLoader = new GeoModelLoader();
        MolangRegistrar.registerVars(this.parser);
    }

    public static GeckoLibCache getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GeckoLibCache();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void func_110549_a(IResourceManager resourceManager) {
        HashMap<ResourceLocation, AnimationFile> tempAnimations = new HashMap<ResourceLocation, AnimationFile>();
        HashMap<ResourceLocation, GeoModel> tempModels = new HashMap<ResourceLocation, GeoModel>();
        List<IResourcePack> packs = this.getPacks();
        if (packs == null) {
            return;
        }
        for (IResourcePack pack : packs) {
            for (ResourceLocation location : this.getLocations(pack, "animations", fileName -> fileName.endsWith(".json"))) {
                try {
                    tempAnimations.put(location, this.animationLoader.loadAllAnimations(this.parser, location, resourceManager));
                }
                catch (Exception e10) {
                    e10.printStackTrace();
                    GeckoLib.LOGGER.error("Error loading animation file \"" + location + "\"!", (Throwable)e10);
                }
            }
            for (ResourceLocation location : this.getLocations(pack, "geo", fileName -> fileName.endsWith(".json"))) {
                try {
                    tempModels.put(location, this.modelLoader.loadModel(resourceManager, location));
                }
                catch (Exception e11) {
                    e11.printStackTrace();
                    GeckoLib.LOGGER.error("Error loading model file \"" + location + "\"!", (Throwable)e11);
                }
            }
        }
        this.animations = tempAnimations;
        this.geoModels = tempModels;
    }

    private List<IResourcePack> getPacks() {
        try {
            Field field = FMLClientHandler.class.getDeclaredField("resourcePackList");
            field.setAccessible(true);
            return (List)field.get(FMLClientHandler.instance());
        }
        catch (Exception e10) {
            GeckoLib.LOGGER.error("Error accessing resource pack list!", (Throwable)e10);
            return null;
        }
    }

    private List<ResourceLocation> getLocations(IResourcePack pack, String folder, Predicate<String> predicate) {
        if (pack instanceof LegacyV2Adapter) {
            LegacyV2Adapter adapter = (LegacyV2Adapter)pack;
            Field packField = null;
            for (Field field : adapter.getClass().getDeclaredFields()) {
                if (field.getType() != IResourcePack.class) continue;
                packField = field;
                break;
            }
            if (packField != null) {
                packField.setAccessible(true);
                try {
                    return this.getLocations((IResourcePack)packField.get(adapter), folder, predicate);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        ArrayList<ResourceLocation> locations = new ArrayList<ResourceLocation>();
        if (pack instanceof FolderResourcePack) {
            this.handleFolderResourcePack((FolderResourcePack)pack, folder, predicate, locations);
        } else if (pack instanceof FileResourcePack) {
            this.handleZipResourcePack((FileResourcePack)pack, folder, predicate, locations);
        }
        return locations;
    }

    private void handleFolderResourcePack(FolderResourcePack folderPack, String folder, Predicate<String> predicate, List<ResourceLocation> locations) {
        Field fileField = null;
        for (Field field : AbstractResourcePack.class.getDeclaredFields()) {
            if (field.getType() != File.class) continue;
            fileField = field;
            break;
        }
        if (fileField != null) {
            fileField.setAccessible(true);
            try {
                File file = (File)fileField.get(folderPack);
                Set domains = folderPack.func_110587_b();
                if (folderPack instanceof FMLFolderResourcePack) {
                    domains.add(((FMLFolderResourcePack)folderPack).getFMLContainer().getModId());
                }
                for (String domain : domains) {
                    String prefix = "assets/" + domain + "/" + folder;
                    File pathFile = new File(file, prefix);
                    this.enumerateFiles(folderPack, pathFile, predicate, locations, domain, folder);
                }
            }
            catch (IllegalAccessException e10) {
                GeckoLib.LOGGER.error((Object)e10);
            }
        }
    }

    private void enumerateFiles(FolderResourcePack folderPack, File parent, Predicate<String> predicate, List<ResourceLocation> locations, String domain, String prefix) {
        File[] files = parent.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile() && predicate.test(file.getName())) {
                locations.add(new ResourceLocation(domain, prefix + "/" + file.getName()));
                continue;
            }
            if (!file.isDirectory()) continue;
            this.enumerateFiles(folderPack, file, predicate, locations, domain, prefix + "/" + file.getName());
        }
    }

    private void handleZipResourcePack(FileResourcePack filePack, String folder, Predicate<String> predicate, List<ResourceLocation> locations) {
        Field zipField = null;
        for (Field field : FileResourcePack.class.getDeclaredFields()) {
            if (field.getType() != ZipFile.class) continue;
            zipField = field;
            break;
        }
        if (zipField != null) {
            zipField.setAccessible(true);
            try {
                this.enumerateZipFile(filePack, folder, (ZipFile)zipField.get(filePack), predicate, locations);
            }
            catch (IllegalAccessException e10) {
                GeckoLib.LOGGER.error((Object)e10);
            }
        }
    }

    private void enumerateZipFile(FileResourcePack filePack, String folder, ZipFile file, Predicate<String> predicate, List<ResourceLocation> locations) {
        Set domains = filePack.func_110587_b();
        Enumeration<? extends ZipEntry> it = file.entries();
        while (it.hasMoreElements()) {
            String name = it.nextElement().getName();
            for (String domain : domains) {
                String assets = "assets/" + domain + "/";
                String path = assets + folder + "/";
                if (!name.startsWith(path) || !predicate.test(name)) continue;
                locations.add(new ResourceLocation(domain, name.substring(assets.length())));
            }
        }
    }
}

