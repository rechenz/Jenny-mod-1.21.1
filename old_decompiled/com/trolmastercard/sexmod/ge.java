/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a1;
import com.trolmastercard.sexmod.a6;
import com.trolmastercard.sexmod.a8;
import com.trolmastercard.sexmod.ab;
import com.trolmastercard.sexmod.ac;
import com.trolmastercard.sexmod.aq;
import com.trolmastercard.sexmod.au;
import com.trolmastercard.sexmod.b0;
import com.trolmastercard.sexmod.b1;
import com.trolmastercard.sexmod.b3;
import com.trolmastercard.sexmod.b_;
import com.trolmastercard.sexmod.bd;
import com.trolmastercard.sexmod.bg;
import com.trolmastercard.sexmod.bk;
import com.trolmastercard.sexmod.bo;
import com.trolmastercard.sexmod.bv;
import com.trolmastercard.sexmod.bw;
import com.trolmastercard.sexmod.cd;
import com.trolmastercard.sexmod.ct;
import com.trolmastercard.sexmod.cu;
import com.trolmastercard.sexmod.cz;
import com.trolmastercard.sexmod.dc;
import com.trolmastercard.sexmod.dq;
import com.trolmastercard.sexmod.e6;
import com.trolmastercard.sexmod.ej;
import com.trolmastercard.sexmod.en;
import com.trolmastercard.sexmod.eu;
import com.trolmastercard.sexmod.f3;
import com.trolmastercard.sexmod.fc;
import com.trolmastercard.sexmod.fj;
import com.trolmastercard.sexmod.fw;
import com.trolmastercard.sexmod.g4;
import com.trolmastercard.sexmod.g6;
import com.trolmastercard.sexmod.g9;
import com.trolmastercard.sexmod.g_;
import com.trolmastercard.sexmod.gd;
import com.trolmastercard.sexmod.gf;
import com.trolmastercard.sexmod.gg;
import com.trolmastercard.sexmod.gh;
import com.trolmastercard.sexmod.gk;
import com.trolmastercard.sexmod.gz;
import com.trolmastercard.sexmod.h6;
import com.trolmastercard.sexmod.n;
import com.trolmastercard.sexmod.s;
import com.trolmastercard.sexmod.t;
import com.trolmastercard.sexmod.z;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ge {
    public static SimpleNetworkWrapper b;
    private static int a;

    private static int b() {
        return a++;
    }

    public static void a() {
        b = NetworkRegistry.INSTANCE.newSimpleChannel("sexmodchannel");
        b.registerMessage(gh.a.class, gh.class, ge.b(), Side.CLIENT);
        b.registerMessage(gh.a.class, gh.class, ge.b(), Side.SERVER);
        b.registerMessage(gz.a.class, gz.class, ge.b(), Side.CLIENT);
        b.registerMessage(a8.a.class, a8.class, ge.b(), Side.SERVER);
        b.registerMessage(ac.a.class, ac.class, ge.b(), Side.SERVER);
        b.registerMessage(b0.a.class, b0.class, ge.b(), Side.SERVER);
        b.registerMessage(dc.a.class, dc.class, ge.b(), Side.SERVER);
        b.registerMessage(a1.a.class, a1.class, ge.b(), Side.CLIENT);
        b.registerMessage(a1.a.class, a1.class, ge.b(), Side.SERVER);
        b.registerMessage(s.a.class, s.class, ge.b(), Side.SERVER);
        b.registerMessage(n.a.class, n.class, ge.b(), Side.SERVER);
        b.registerMessage(bo.a.class, bo.class, ge.b(), Side.SERVER);
        b.registerMessage(gg.a.class, gg.class, ge.b(), Side.SERVER);
        b.registerMessage(a6.a.class, a6.class, ge.b(), Side.SERVER);
        b.registerMessage(b1.a.class, b1.class, ge.b(), Side.SERVER);
        b.registerMessage(t.a.class, t.class, ge.b(), Side.SERVER);
        b.registerMessage(bg.a.class, bg.class, ge.b(), Side.SERVER);
        b.registerMessage(cz.a.class, cz.class, ge.b(), Side.SERVER);
        b.registerMessage(bw.a.class, bw.class, ge.b(), Side.SERVER);
        b.registerMessage(b_.a.class, b_.class, ge.b(), Side.SERVER);
        b.registerMessage(g4.a.class, g4.class, ge.b(), Side.SERVER);
        b.registerMessage(g4.a.class, g4.class, ge.b(), Side.CLIENT);
        b.registerMessage(eu.a.class, eu.class, ge.b(), Side.SERVER);
        b.registerMessage(f3.a.class, f3.class, ge.b(), Side.SERVER);
        b.registerMessage(ej.a.class, ej.class, ge.b(), Side.SERVER);
        b.registerMessage(gk.a.class, gk.class, ge.b(), Side.SERVER);
        b.registerMessage(dq.a.class, dq.class, ge.b(), Side.SERVER);
        b.registerMessage(g9.a.class, g9.class, ge.b(), Side.SERVER);
        b.registerMessage(b3.a.class, b3.class, ge.b(), Side.SERVER);
        b.registerMessage(b3.a.class, b3.class, ge.b(), Side.CLIENT);
        b.registerMessage(fj.a.class, fj.class, ge.b(), Side.SERVER);
        b.registerMessage(fc.a.class, fc.class, ge.b(), Side.SERVER);
        b.registerMessage(h6.a.class, h6.class, ge.b(), Side.CLIENT);
        b.registerMessage(h6.a.class, h6.class, ge.b(), Side.SERVER);
        b.registerMessage(au.a.class, au.class, ge.b(), Side.SERVER);
        b.registerMessage(en.a.class, en.class, ge.b(), Side.CLIENT);
        b.registerMessage(z.a.class, z.class, ge.b(), Side.SERVER);
        b.registerMessage(e6.a.class, e6.class, ge.b(), Side.SERVER);
        b.registerMessage(bd.a.class, bd.class, ge.b(), Side.CLIENT);
        b.registerMessage(gd.a.class, gd.class, ge.b(), Side.CLIENT);
        b.registerMessage(fw.a.class, fw.class, ge.b(), Side.SERVER);
        b.registerMessage(g_.a.class, g_.class, ge.b(), Side.SERVER);
        b.registerMessage(ct.a.class, ct.class, ge.b(), Side.SERVER);
        b.registerMessage(g6.a.class, g6.class, ge.b(), Side.SERVER);
        b.registerMessage(g6.a.class, g6.class, ge.b(), Side.CLIENT);
        b.registerMessage(cu.a.class, cu.class, ge.b(), Side.SERVER);
        b.registerMessage(cu.a.class, cu.class, ge.b(), Side.CLIENT);
        b.registerMessage(bv.a.class, bv.class, ge.b(), Side.CLIENT);
        b.registerMessage(cd.a.class, cd.class, ge.b(), Side.SERVER);
        b.registerMessage(gf.a.class, gf.class, ge.b(), Side.CLIENT);
        b.registerMessage(bk.a.class, bk.class, ge.b(), Side.SERVER);
        b.registerMessage(ab.a.class, ab.class, ge.b(), Side.CLIENT);
        b.registerMessage(aq.a.class, aq.class, ge.b(), Side.CLIENT);
    }

    static {
        a = 0;
    }
}

