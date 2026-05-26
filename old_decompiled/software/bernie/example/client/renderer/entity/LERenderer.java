/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 */
package software.bernie.example.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.example.client.model.entity.LEModel;
import software.bernie.example.client.renderer.entity.layer.GeoExampleLayer;
import software.bernie.example.entity.GeoExampleEntityLayer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class LERenderer
extends GeoEntityRenderer<GeoExampleEntityLayer> {
    public LERenderer(RenderManager renderManager) {
        super(renderManager, new LEModel());
        this.addLayer(new GeoExampleLayer((IGeoRenderer)this));
        this.field_76989_e = 0.2f;
    }
}

