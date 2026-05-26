/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.geo.raw.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.geo.raw.pojo.Bone;
import software.bernie.geckolib3.geo.raw.pojo.MinecraftGeometry;
import software.bernie.geckolib3.geo.raw.pojo.ModelProperties;
import software.bernie.geckolib3.geo.raw.pojo.RawGeoModel;
import software.bernie.geckolib3.geo.raw.tree.RawBoneGroup;

public class RawGeometryTree {
    public HashMap<String, RawBoneGroup> topLevelBones = new HashMap();
    public ModelProperties properties;

    public static RawGeometryTree parseHierarchy(RawGeoModel model, ResourceLocation location) {
        RawGeometryTree hierarchy = new RawGeometryTree();
        MinecraftGeometry geometry = model.getMinecraftGeometry()[0];
        hierarchy.properties = geometry.getProperties();
        ArrayList<Bone> bones = new ArrayList<Bone>(Arrays.asList(geometry.getBones()));
        int index = bones.size() - 1;
        int loopsWithoutChange = 0;
        while (true) {
            if (++loopsWithoutChange > 10000) {
                GeckoLib.LOGGER.warn("Some bones in " + location.toString() + " do not have existing parents: ");
                GeckoLib.LOGGER.warn(bones.stream().map(x2 -> x2.getName()).collect(Collectors.joining(", ")));
                break;
            }
            Bone bone = (Bone)bones.get(index);
            if (!RawGeometryTree.hasParent(bone)) {
                hierarchy.topLevelBones.put(bone.getName(), new RawBoneGroup(bone));
                bones.remove(bone);
                loopsWithoutChange = 0;
            } else {
                RawBoneGroup groupFromHierarchy = RawGeometryTree.getGroupFromHierarchy(hierarchy, bone.getParent());
                if (groupFromHierarchy != null) {
                    groupFromHierarchy.children.put(bone.getName(), new RawBoneGroup(bone));
                    bones.remove(bone);
                    loopsWithoutChange = 0;
                }
            }
            if (index == 0) {
                index = bones.size() - 1;
                if (index != -1) continue;
                break;
            }
            --index;
        }
        return hierarchy;
    }

    public static boolean hasParent(Bone bone) {
        return bone.getParent() != null;
    }

    public static RawBoneGroup getGroupFromHierarchy(RawGeometryTree hierarchy, String bone) {
        HashMap<String, RawBoneGroup> flatList = new HashMap<String, RawBoneGroup>();
        for (RawBoneGroup group : hierarchy.topLevelBones.values()) {
            flatList.put(group.selfBone.getName(), group);
            RawGeometryTree.traverse(flatList, group);
        }
        return (RawBoneGroup)flatList.get(bone);
    }

    public static void traverse(HashMap<String, RawBoneGroup> flatList, RawBoneGroup group) {
        for (RawBoneGroup child : group.children.values()) {
            flatList.put(child.selfBone.getName(), child);
            RawGeometryTree.traverse(flatList, child);
        }
    }
}

