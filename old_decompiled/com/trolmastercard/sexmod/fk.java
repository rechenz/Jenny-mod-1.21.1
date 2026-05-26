/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.client.registry.RenderingRegistry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ag;
import com.trolmastercard.sexmod.al;
import com.trolmastercard.sexmod.ay;
import com.trolmastercard.sexmod.b;
import com.trolmastercard.sexmod.bp;
import com.trolmastercard.sexmod.c0;
import com.trolmastercard.sexmod.c2;
import com.trolmastercard.sexmod.c4;
import com.trolmastercard.sexmod.c5;
import com.trolmastercard.sexmod.c9;
import com.trolmastercard.sexmod.cb;
import com.trolmastercard.sexmod.ce;
import com.trolmastercard.sexmod.cg;
import com.trolmastercard.sexmod.ci;
import com.trolmastercard.sexmod.cl;
import com.trolmastercard.sexmod.cp;
import com.trolmastercard.sexmod.cr;
import com.trolmastercard.sexmod.cx;
import com.trolmastercard.sexmod.cy;
import com.trolmastercard.sexmod.d0;
import com.trolmastercard.sexmod.d1;
import com.trolmastercard.sexmod.d2;
import com.trolmastercard.sexmod.d5;
import com.trolmastercard.sexmod.d8;
import com.trolmastercard.sexmod.da;
import com.trolmastercard.sexmod.db;
import com.trolmastercard.sexmod.de;
import com.trolmastercard.sexmod.dg;
import com.trolmastercard.sexmod.dh;
import com.trolmastercard.sexmod.di;
import com.trolmastercard.sexmod.dj;
import com.trolmastercard.sexmod.dk;
import com.trolmastercard.sexmod.dl;
import com.trolmastercard.sexmod.dn;
import com.trolmastercard.sexmod.dp;
import com.trolmastercard.sexmod.dt;
import com.trolmastercard.sexmod.du;
import com.trolmastercard.sexmod.dv;
import com.trolmastercard.sexmod.dx;
import com.trolmastercard.sexmod.dy;
import com.trolmastercard.sexmod.e;
import com.trolmastercard.sexmod.e0;
import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.e5;
import com.trolmastercard.sexmod.e7;
import com.trolmastercard.sexmod.e9;
import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.ec;
import com.trolmastercard.sexmod.ed;
import com.trolmastercard.sexmod.ee;
import com.trolmastercard.sexmod.eg;
import com.trolmastercard.sexmod.el;
import com.trolmastercard.sexmod.eq;
import com.trolmastercard.sexmod.er;
import com.trolmastercard.sexmod.es;
import com.trolmastercard.sexmod.ev;
import com.trolmastercard.sexmod.ex;
import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.fi;
import com.trolmastercard.sexmod.fn;
import com.trolmastercard.sexmod.fz;
import com.trolmastercard.sexmod.gi;
import com.trolmastercard.sexmod.i;
import com.trolmastercard.sexmod.k;
import com.trolmastercard.sexmod.o;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class fk {
    public static void a() {
        RenderingRegistry.registerEntityRenderingHandler(ff.class, renderManager -> new dj(renderManager, (AnimatedGeoModel)new c9(), -0.4));
        RenderingRegistry.registerEntityRenderingHandler(ex.class, renderManager -> new du(renderManager, new c5(), -0.15));
        RenderingRegistry.registerEntityRenderingHandler(el.class, renderManager -> new dk(renderManager, new cx(), 0.05));
        RenderingRegistry.registerEntityRenderingHandler(fn.class, renderManager -> new d1(renderManager, (AnimatedGeoModel)new cr(), -0.2));
        RenderingRegistry.registerEntityRenderingHandler(e0.class, renderManager -> new dt(renderManager, (AnimatedGeoModel)new cl(), -0.4));
        RenderingRegistry.registerEntityRenderingHandler(ev.class, renderManager -> new d8(renderManager, (AnimatedGeoModel)new c2(), -0.4));
        RenderingRegistry.registerEntityRenderingHandler(fz.class, renderManager -> new dn(renderManager, new c0(), -0.4));
        RenderingRegistry.registerEntityRenderingHandler(ay.class, bp::new);
        RenderingRegistry.registerEntityRenderingHandler(eb.class, renderManager -> new dp(renderManager, (AnimatedGeoModel)new cg(), -0.4));
        RenderingRegistry.registerEntityRenderingHandler(e3.class, renderManager -> new dy(renderManager, (AnimatedGeoModel)new ci(), -0.6));
        RenderingRegistry.registerEntityRenderingHandler(f_.class, renderManager -> new da(renderManager, (AnimatedGeoModel)new cb(), -0.05));
        RenderingRegistry.registerEntityRenderingHandler(i.class, renderManager -> new cp(renderManager, new k()));
        RenderingRegistry.registerEntityRenderingHandler(f8.class, renderManager -> new dh(renderManager, (AnimatedGeoModel)new ce(), -0.05));
        RenderingRegistry.registerEntityRenderingHandler(eg.class, renderManager -> new d0(renderManager, (AnimatedGeoModel)new cl()));
        RenderingRegistry.registerEntityRenderingHandler(es.class, renderManager -> new db(renderManager, (AnimatedGeoModel)new c5()));
        RenderingRegistry.registerEntityRenderingHandler(ee.class, renderManager -> new dl(renderManager, (AnimatedGeoModel)new cx()));
        RenderingRegistry.registerEntityRenderingHandler(ec.class, renderManager -> new d5(renderManager, (AnimatedGeoModel)new cr()));
        RenderingRegistry.registerEntityRenderingHandler(e5.class, renderManager -> new dv(renderManager, (AnimatedGeoModel)new c2()));
        RenderingRegistry.registerEntityRenderingHandler(e9.class, renderManager -> new d2(renderManager, (AnimatedGeoModel)new c0()));
        RenderingRegistry.registerEntityRenderingHandler(ed.class, renderManager -> new di(renderManager, (AnimatedGeoModel)new cg()));
        RenderingRegistry.registerEntityRenderingHandler(e7.class, renderManager -> new de(renderManager, (AnimatedGeoModel)new c9()));
        RenderingRegistry.registerEntityRenderingHandler(eq.class, renderManager -> new dg(renderManager, (AnimatedGeoModel)new ci()));
        RenderingRegistry.registerEntityRenderingHandler(er.class, renderManager -> new dx(renderManager, (AnimatedGeoModel)new cb()));
        RenderingRegistry.registerEntityRenderingHandler(gi.class, fi::new);
        RenderingRegistry.registerEntityRenderingHandler(cy.class, renderManager -> new b(renderManager, new o()));
        RenderingRegistry.registerEntityRenderingHandler(c4.class, ag::new);
        RenderingRegistry.registerEntityRenderingHandler(al.class, e::new);
    }
}

