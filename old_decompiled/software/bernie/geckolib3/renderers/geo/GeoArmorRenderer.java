/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.util.GeoUtils;

public abstract class GeoArmorRenderer<T extends ItemArmor>
extends ModelBiped
implements IGeoRenderer<T> {
    private static Map<Class<? extends ItemArmor>, GeoArmorRenderer> renderers = new ConcurrentHashMap<Class<? extends ItemArmor>, GeoArmorRenderer>();
    private T currentArmorItem;
    private EntityLivingBase entityLiving;
    private ItemStack itemStack;
    private EntityEquipmentSlot armorSlot;
    public String headBone = "armorHead";
    public String bodyBone = "armorBody";
    public String rightArmBone = "armorRightArm";
    public String leftArmBone = "armorLeftArm";
    public String rightLegBone = "armorRightLeg";
    public String leftLegBone = "armorLeftLeg";
    public String rightBootBone = "armorRightBoot";
    public String leftBootBone = "armorLeftBoot";
    private final AnimatedGeoModel<T> modelProvider;

    public static void registerArmorRenderer(Class<? extends ItemArmor> itemClass, GeoArmorRenderer renderer) {
        renderers.put(itemClass, renderer);
    }

    public static GeoArmorRenderer getRenderer(Class<? extends ItemArmor> item) {
        return renderers.get(item);
    }

    public GeoArmorRenderer(AnimatedGeoModel<T> modelProvider) {
        super(1.0f);
        this.modelProvider = modelProvider;
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.render(ageInTicks);
    }

    public void render(float partialTicks) {
        GlStateManager.func_179137_b((double)0.0, (double)1.501f, (double)0.0);
        GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(this.currentArmorItem));
        AnimationEvent<IAnimatable> itemEvent = new AnimationEvent<IAnimatable>((IAnimatable)this.currentArmorItem, 0.0f, 0.0f, 0.0f, false, Arrays.asList(this.itemStack, this.entityLiving, this.armorSlot));
        this.modelProvider.setLivingAnimations((IAnimatable)this.currentArmorItem, this.getUniqueID(this.currentArmorItem), itemEvent);
        this.fitToBiped();
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)0.0f, (float)0.01f, (float)0.0f);
        IBone rightArmBone = this.modelProvider.getBone(this.rightArmBone);
        IBone leftArmBone = this.modelProvider.getBone(this.leftArmBone);
        if (this.field_78095_p > 0.0f) {
            rightArmBone.setScaleZ(1.25f);
            rightArmBone.setScaleX(1.25f);
            leftArmBone.setScaleZ(1.3f);
            leftArmBone.setScaleX(1.05f);
        }
        if (this.field_78117_n) {
            IBone headBone = this.modelProvider.getBone(this.headBone);
            IBone bodyBone = this.modelProvider.getBone(this.bodyBone);
            IBone rightLegBone = this.modelProvider.getBone(this.rightLegBone);
            IBone leftLegBone = this.modelProvider.getBone(this.leftLegBone);
            IBone rightBootBone = this.modelProvider.getBone(this.rightBootBone);
            IBone leftBootBone = this.modelProvider.getBone(this.leftBootBone);
            try {
                headBone.setPositionY(headBone.getPositionY() - 1.0f);
                bodyBone.setPositionZ(bodyBone.getPositionX() - 0.4f);
                bodyBone.setPositionY(bodyBone.getPositionX() - 3.5f);
                rightArmBone.setPositionY(bodyBone.getPositionX() - 3.0f);
                rightArmBone.setPositionX(bodyBone.getPositionX() + 0.35f);
                leftArmBone.setPositionY(bodyBone.getPositionX() - 3.0f);
                leftArmBone.setPositionX(bodyBone.getPositionX() - 0.35f);
                rightLegBone.setPositionZ(bodyBone.getPositionX() + 4.0f);
                leftLegBone.setPositionZ(bodyBone.getPositionX() + 4.0f);
                rightBootBone.setPositionZ(bodyBone.getPositionX() + 4.0f);
                leftBootBone.setPositionZ(bodyBone.getPositionX() + 4.0f);
            }
            catch (Exception e10) {
                throw new RuntimeException("Could not find an armor bone.", e10);
            }
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation(this.currentArmorItem));
        Color renderColor = this.getRenderColor(this.currentArmorItem, partialTicks);
        this.render(model, this.currentArmorItem, partialTicks, (float)renderColor.getRed() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        GlStateManager.func_179121_F();
        GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
        GlStateManager.func_179137_b((double)0.0, (double)-1.501f, (double)0.0);
    }

    private void fitToBiped() {
        IBone headBone = this.modelProvider.getBone(this.headBone);
        IBone bodyBone = this.modelProvider.getBone(this.bodyBone);
        IBone rightArmBone = this.modelProvider.getBone(this.rightArmBone);
        IBone leftArmBone = this.modelProvider.getBone(this.leftArmBone);
        IBone rightLegBone = this.modelProvider.getBone(this.rightLegBone);
        IBone leftLegBone = this.modelProvider.getBone(this.leftLegBone);
        IBone rightBootBone = this.modelProvider.getBone(this.rightBootBone);
        IBone leftBootBone = this.modelProvider.getBone(this.leftBootBone);
        try {
            if (!(this.entityLiving instanceof EntityArmorStand)) {
                GeoUtils.copyRotations(this.field_78116_c, headBone);
                GeoUtils.copyRotations(this.field_78115_e, bodyBone);
                GeoUtils.copyRotations(this.field_178723_h, rightArmBone);
                GeoUtils.copyRotations(this.field_178724_i, leftArmBone);
                GeoUtils.copyRotations(this.field_178721_j, rightLegBone);
                GeoUtils.copyRotations(this.field_178722_k, leftLegBone);
                GeoUtils.copyRotations(this.field_178721_j, rightBootBone);
                GeoUtils.copyRotations(this.field_178722_k, leftBootBone);
            }
        }
        catch (Exception e10) {
            throw new RuntimeException("Could not find an armor bone.", e10);
        }
    }

    @Override
    public AnimatedGeoModel<T> getGeoModelProvider() {
        return this.modelProvider;
    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return this.modelProvider.getTextureLocation(instance);
    }

    public void setCurrentItem(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot) {
        this.entityLiving = entityLiving;
        this.itemStack = itemStack;
        this.armorSlot = armorSlot;
        this.currentArmorItem = (ItemArmor)itemStack.func_77973_b();
    }

    public final GeoArmorRenderer applyEntityStats(ModelBiped defaultArmor) {
        this.field_78091_s = defaultArmor.field_78091_s;
        this.field_78117_n = defaultArmor.field_78117_n;
        this.field_78093_q = defaultArmor.field_78093_q;
        this.field_187076_m = defaultArmor.field_187076_m;
        this.field_187075_l = defaultArmor.field_187075_l;
        return this;
    }

    public GeoArmorRenderer applySlot(EntityEquipmentSlot slot) {
        this.modelProvider.getModel(this.modelProvider.getModelLocation(this.currentArmorItem));
        IBone headBone = this.modelProvider.getBone(this.headBone);
        IBone bodyBone = this.modelProvider.getBone(this.bodyBone);
        IBone rightArmBone = this.modelProvider.getBone(this.rightArmBone);
        IBone leftArmBone = this.modelProvider.getBone(this.leftArmBone);
        IBone rightLegBone = this.modelProvider.getBone(this.rightLegBone);
        IBone leftLegBone = this.modelProvider.getBone(this.leftLegBone);
        IBone rightBootBone = this.modelProvider.getBone(this.rightBootBone);
        IBone leftBootBone = this.modelProvider.getBone(this.leftBootBone);
        try {
            headBone.setHidden(true);
            bodyBone.setHidden(true);
            rightArmBone.setHidden(true);
            leftArmBone.setHidden(true);
            rightLegBone.setHidden(true);
            leftLegBone.setHidden(true);
            rightBootBone.setHidden(true);
            leftBootBone.setHidden(true);
            switch (slot) {
                case HEAD: {
                    headBone.setHidden(false);
                    break;
                }
                case CHEST: {
                    bodyBone.setHidden(false);
                    rightArmBone.setHidden(false);
                    leftArmBone.setHidden(false);
                    break;
                }
                case LEGS: {
                    rightLegBone.setHidden(false);
                    leftLegBone.setHidden(false);
                    break;
                }
                case FEET: {
                    rightBootBone.setHidden(false);
                    leftBootBone.setHidden(false);
                }
            }
        }
        catch (Exception e10) {
            throw new RuntimeException("Could not find an armor bone.", e10);
        }
        return this;
    }

    @Override
    public Integer getUniqueID(T animatable) {
        return Objects.hash(this.armorSlot, this.itemStack.func_77973_b(), this.itemStack.func_190916_E(), this.itemStack.func_77942_o() ? this.itemStack.func_77978_p().toString() : Integer.valueOf(1), this.entityLiving.func_110124_au().toString());
    }

    static {
        AnimationController.addModelFetcher(object -> {
            if (object instanceof ItemArmor) {
                GeoArmorRenderer renderer = renderers.get(object.getClass());
                return renderer == null ? null : renderer.getGeoModelProvider();
            }
            return null;
        });
    }
}

