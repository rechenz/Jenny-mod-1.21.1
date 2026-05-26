/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a;
import com.trolmastercard.sexmod.ad;
import com.trolmastercard.sexmod.ah;
import com.trolmastercard.sexmod.aj;
import com.trolmastercard.sexmod.am;
import com.trolmastercard.sexmod.ap;
import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.bj;
import com.trolmastercard.sexmod.bq;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.c6;
import com.trolmastercard.sexmod.c7;
import com.trolmastercard.sexmod.cc;
import com.trolmastercard.sexmod.cn;
import com.trolmastercard.sexmod.co;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.ds;
import com.trolmastercard.sexmod.dv;
import com.trolmastercard.sexmod.dw;
import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.e_;
import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.eo;
import com.trolmastercard.sexmod.eq;
import com.trolmastercard.sexmod.ey;
import com.trolmastercard.sexmod.f4;
import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.fh;
import com.trolmastercard.sexmod.fq;
import com.trolmastercard.sexmod.fr;
import com.trolmastercard.sexmod.fu;
import com.trolmastercard.sexmod.g;
import com.trolmastercard.sexmod.g2;
import com.trolmastercard.sexmod.g3;
import com.trolmastercard.sexmod.ga;
import com.trolmastercard.sexmod.gb;
import com.trolmastercard.sexmod.gm;
import com.trolmastercard.sexmod.gp;
import com.trolmastercard.sexmod.gu;
import com.trolmastercard.sexmod.gy;
import com.trolmastercard.sexmod.hf;
import com.trolmastercard.sexmod.hj;
import com.trolmastercard.sexmod.ho;
import com.trolmastercard.sexmod.hy;
import com.trolmastercard.sexmod.l;
import com.trolmastercard.sexmod.q;
import com.trolmastercard.sexmod.v;
import com.trolmastercard.sexmod.w;
import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class bn {
    public static void a(boolean bl2) throws IOException {
        try {
            MinecraftForge.EVENT_BUS.register((Object)new ah());
            MinecraftForge.EVENT_BUS.register((Object)new eo());
            MinecraftForge.EVENT_BUS.register((Object)new q());
            MinecraftForge.EVENT_BUS.register((Object)new co());
            MinecraftForge.EVENT_BUS.register((Object)new gu());
            MinecraftForge.EVENT_BUS.register((Object)new ho.a());
            MinecraftForge.EVENT_BUS.register((Object)new g.a());
            MinecraftForge.EVENT_BUS.register((Object)ap.b);
            MinecraftForge.EVENT_BUS.register((Object)hy.b);
            MinecraftForge.EVENT_BUS.register((Object)hj.a);
            MinecraftForge.EVENT_BUS.register((Object)new gp());
            MinecraftForge.EVENT_BUS.register((Object)new fu());
            MinecraftForge.EVENT_BUS.register((Object)new eb.a());
            MinecraftForge.EVENT_BUS.register((Object)new ey());
            MinecraftForge.EVENT_BUS.register((Object)dw.a);
            MinecraftForge.EVENT_BUS.register((Object)new ff.c());
            MinecraftForge.EVENT_BUS.register((Object)new hy.a());
            MinecraftForge.EVENT_BUS.register((Object)new ax.b("tribes"));
            MinecraftForge.EVENT_BUS.register((Object)new c7());
            MinecraftForge.EVENT_BUS.register((Object)new am());
            MinecraftForge.EVENT_BUS.register((Object)new e3.c());
            MinecraftForge.EVENT_BUS.register((Object)new eq.a());
            MinecraftForge.EVENT_BUS.register((Object)new ap.a());
            MinecraftForge.EVENT_BUS.register((Object)new ad());
            MinecraftForge.EVENT_BUS.register((Object)new f_.a());
            MinecraftForge.EVENT_BUS.register((Object)new v());
            MinecraftForge.EVENT_BUS.register((Object)cc.r);
            MinecraftForge.EVENT_BUS.register((Object)aj.b);
            MinecraftForge.EVENT_BUS.register((Object)new fq());
            MinecraftForge.EVENT_BUS.register((Object)new gy());
            MinecraftForge.EVENT_BUS.register((Object)new bj());
            MinecraftForge.EVENT_BUS.register((Object)g3.b());
            MinecraftForge.EVENT_BUS.register((Object)new f8.b());
            MinecraftForge.EVENT_BUS.register((Object)new f4());
            if (bl2) {
                bn.b();
            }
        }
        catch (IOException iOException) {
            throw bn.a(iOException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    static void b() {
        block3: {
            block2: {
                try {
                    if (!bn.a()) break block2;
                    MinecraftForge.EVENT_BUS.register((Object)new fr());
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw bn.a(runtimeException);
                }
            }
            g2.a = false;
        }
        MinecraftForge.EVENT_BUS.register((Object)new ds());
        MinecraftForge.EVENT_BUS.register((Object)new fh());
        MinecraftForge.EVENT_BUS.register((Object)new d3());
        MinecraftForge.EVENT_BUS.register((Object)new l());
        MinecraftForge.EVENT_BUS.register((Object)new bq());
        MinecraftForge.EVENT_BUS.register((Object)new cn());
        MinecraftForge.EVENT_BUS.register((Object)new e_());
        MinecraftForge.EVENT_BUS.register((Object)new w());
        MinecraftForge.EVENT_BUS.register((Object)new dv.a());
        MinecraftForge.EVENT_BUS.register((Object)new gm());
        MinecraftForge.EVENT_BUS.register((Object)new c6());
        MinecraftForge.EVENT_BUS.register((Object)new a.b());
        MinecraftForge.EVENT_BUS.register((Object)new br.a());
        MinecraftForge.EVENT_BUS.register((Object)new gb());
        MinecraftForge.EVENT_BUS.register((Object)new ga());
        MinecraftForge.EVENT_BUS.register((Object)new hf());
    }

    static boolean a() {
        boolean bl2;
        File file = new File("sexmod/dontAskAgain");
        try {
            file.getParentFile().mkdirs();
            bl2 = !file.exists();
        }
        catch (RuntimeException runtimeException) {
            throw bn.a(runtimeException);
        }
        return bl2;
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

