import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;

public class nb extends ti {

    private boolean   d = false;
    private pf        e;
    public String     a;
    private Minecraft f;
    private mm        g;
    private boolean   h = false;
    public hc         b = new hc((wt) null);
    Random            c = new Random();

    public nb(Minecraft var1, String var2, int var3) throws UnknownHostException, IOException {
        this.f = var1;
        Socket var4 = new Socket(InetAddress.getByName(var2), var3);
        this.e = new pf(var4, "Client", this);
    }

    public void a() {
        if (!this.d) {
            this.e.b();
        }

        this.e.a();
    }

    public void a(nz var1) {
        this.f.c = new xk(this.f, this);
        this.f.I.a(jl.i, 1);
        this.g = new mm(this, var1.c, var1.d);
        this.g.B = true;
        this.f.a((fd) this.g);
        this.f.h.m = var1.d;
        this.f.a((da) (new gg(this)));
        this.f.h.aD = var1.a;
    }

    public void a(nd var1) {
        double var2 = (double) var1.b / 32.0D;
        double var4 = (double) var1.c / 32.0D;
        double var6 = (double) var1.d / 32.0D;
        hl var8 = new hl(this.g, var2, var4, var6, new iz(var1.h, var1.i, var1.l));
        var8.aP = (double) var1.e / 128.0D;
        var8.aQ = (double) var1.f / 128.0D;
        var8.aR = (double) var1.g / 128.0D;
        var8.bJ = var1.b;
        var8.bK = var1.c;
        var8.bL = var1.d;
        this.g.a(var1.a, var8);
    }

    public void a(so var1) {
        double var2 = (double) var1.b / 32.0D;
        double var4 = (double) var1.c / 32.0D;
        double var6 = (double) var1.d / 32.0D;
        Object var8 = null;
        if (var1.h == 10) {
            var8 = new yl(this.g, var2, var4, var6, 0);
        }

        if (var1.h == 11) {
            var8 = new yl(this.g, var2, var4, var6, 1);
        }

        if (var1.h == 12) {
            var8 = new yl(this.g, var2, var4, var6, 2);
        }

        if (var1.h == 90) {
            var8 = new lx(this.g, var2, var4, var6);
        }

        if (var1.h == 60) {
            var8 = new sl(this.g, var2, var4, var6);
        }

        if (var1.h == 61) {
            var8 = new by(this.g, var2, var4, var6);
        }

        if (var1.h == 63) {
            var8 = new cf(this.g, var2, var4, var6, (double) var1.e / 8000.0D, (double) var1.f / 8000.0D, (double) var1.g / 8000.0D);
            var1.i = 0;
        }

        if (var1.h == 62) {
            var8 = new vv(this.g, var2, var4, var6);
        }

        if (var1.h == 1) {
            var8 = new fz(this.g, var2, var4, var6);
        }

        if (var1.h == 50) {
            var8 = new qw(this.g, var2, var4, var6);
        }

        if (var1.h == 70) {
            var8 = new ju(this.g, var2, var4, var6, uu.F.bn);
        }

        if (var1.h == 71) {
            var8 = new ju(this.g, var2, var4, var6, uu.G.bn);
        }

        if (var8 != null) {
            ((sn) var8).bJ = var1.b;
            ((sn) var8).bK = var1.c;
            ((sn) var8).bL = var1.d;
            ((sn) var8).aS = 0.0F;
            ((sn) var8).aT = 0.0F;
            ((sn) var8).aD = var1.a;
            this.g.a(var1.a, (sn) var8);
            if (var1.i > 0) {
                if (var1.h == 60) {
                    sn var9 = this.a(var1.i);
                    if (var9 instanceof ls) {
                        ((sl) var8).c = (ls) var9;
                    }
                }

                ((sn) var8).a((double) var1.e / 8000.0D, (double) var1.f / 8000.0D, (double) var1.g / 8000.0D);
            }
        }

    }

    public void a(fa var1) {
        double var2 = (double) var1.b / 32.0D;
        double var4 = (double) var1.c / 32.0D;
        double var6 = (double) var1.d / 32.0D;
        c var8 = null;
        if (var1.e == 1) {
            var8 = new c(this.g, var2, var4, var6);
        }

        if (var8 != null) {
            var8.bJ = var1.b;
            var8.bK = var1.c;
            var8.bL = var1.d;
            var8.aS = 0.0F;
            var8.aT = 0.0F;
            var8.aD = var1.a;
            this.g.a(var8);
        }

    }

    public void a(vt var1) {
        qv var2 = new qv(this.g, var1.b, var1.c, var1.d, var1.e, var1.f);
        this.g.a(var1.a, var2);
    }

    public void a(gj var1) {
        sn var2 = this.a(var1.a);
        if (var2 != null) {
            var2.a((double) var1.b / 8000.0D, (double) var1.c / 8000.0D, (double) var1.d / 8000.0D);
        }
    }

    public void a(ux var1) {
        sn var2 = this.a(var1.a);
        if (var2 != null && var1.b() != null) {
            var2.ad().a(var1.b());
        }

    }

    public void a(mf var1) {
        double var2 = (double) var1.c / 32.0D;
        double var4 = (double) var1.d / 32.0D;
        double var6 = (double) var1.e / 32.0D;
        float var8 = (float) (var1.f * 360) / 256.0F;
        float var9 = (float) (var1.g * 360) / 256.0F;
        xz var10 = new xz(this.f.f, var1.b);
        var10.aJ = var10.bl = (double) (var10.bJ = var1.c);
        var10.aK = var10.bm = (double) (var10.bK = var1.d);
        var10.aL = var10.bn = (double) (var10.bL = var1.e);
        int var11 = var1.h;
        if (var11 == 0) {
            var10.c.a[var10.c.c] = null;
        } else {
            var10.c.a[var10.c.c] = new iz(var11, 1, 0);
        }

        var10.b(var2, var4, var6, var8, var9);
        this.g.a(var1.a, var10);
    }

    public void a(rg var1) {
        sn var2 = this.a(var1.a);
        if (var2 != null) {
            var2.bJ = var1.b;
            var2.bK = var1.c;
            var2.bL = var1.d;
            double var3 = (double) var2.bJ / 32.0D;
            double var5 = (double) var2.bK / 32.0D + 0.015625D;
            double var7 = (double) var2.bL / 32.0D;
            float var9 = (float) (var1.e * 360) / 256.0F;
            float var10 = (float) (var1.f * 360) / 256.0F;
            var2.a(var3, var5, var7, var9, var10, 3);
        }
    }

    public void a(uh var1) {
        sn var2 = this.a(var1.a);
        if (var2 != null) {
            var2.bJ += var1.b;
            var2.bK += var1.c;
            var2.bL += var1.d;
            double var3 = (double) var2.bJ / 32.0D;
            double var5 = (double) var2.bK / 32.0D;
            double var7 = (double) var2.bL / 32.0D;
            float var9 = var1.g ? (float) (var1.e * 360) / 256.0F : var2.aS;
            float var10 = var1.g ? (float) (var1.f * 360) / 256.0F : var2.aT;
            var2.a(var3, var5, var7, var9, var10, 3);
        }
    }

    public void a(rv var1) {
        this.g.c(var1.a);
    }

    public void a(ig var1) {
        dc var2 = this.f.h;
        double var3 = var2.aM;
        double var5 = var2.aN;
        double var7 = var2.aO;
        float var9 = var2.aS;
        float var10 = var2.aT;
        if (var1.h) {
            var3 = var1.a;
            var5 = var1.b;
            var7 = var1.c;
        }

        if (var1.i) {
            var9 = var1.e;
            var10 = var1.f;
        }

        var2.bo = 0.0F;
        var2.aP = var2.aQ = var2.aR = 0.0D;
        var2.b(var3, var5, var7, var9, var10);
        var1.a = var2.aM;
        var1.b = var2.aW.b;
        var1.c = var2.aO;
        var1.d = var2.aN;
        this.e.a((ki) var1);
        if (!this.h) {
            this.f.h.aJ = this.f.h.aM;
            this.f.h.aK = this.f.h.aN;
            this.f.h.aL = this.f.h.aO;
            this.h = true;
            this.f.a((da) null);
        }

    }

    public void a(se var1) {
        this.g.a(var1.a, var1.b, var1.c);
    }

    public void a(wu var1) {
        lm var2 = this.g.c(var1.a, var1.b);
        int var3 = var1.a * 16;
        int var4 = var1.b * 16;

        for (int var5 = 0; var5 < var1.f; ++var5) {
            short var6 = var1.c[var5];
            int var7 = var1.d[var5] & 255;
            byte var8 = var1.e[var5];
            int var9 = var6 >> 12 & 15;
            int var10 = var6 >> 8 & 15;
            int var11 = var6 & 255;
            var2.a(var9, var11, var10, var7, var8);
            this.g.c(var9 + var3, var11, var10 + var4, var9 + var3, var11, var10 + var4);
            this.g.b(var9 + var3, var11, var10 + var4, var9 + var3, var11, var10 + var4);
        }

    }

    public void a(ef var1) {
        this.g.c(var1.a, var1.b, var1.c, var1.a + var1.d - 1, var1.b + var1.e - 1, var1.c + var1.f - 1);
        this.g.a(var1.a, var1.b, var1.c, var1.d, var1.e, var1.f, var1.g);
    }

    public void a(tv var1) {
        this.g.f(var1.a, var1.b, var1.c, var1.d, var1.e);
    }

    public void a(yr var1) {
        this.e.a("disconnect.kicked", new Object[0]);
        this.d = true;
        this.f.a((fd) null);
        this.f.a((da) (new ex("disconnect.disconnected", "disconnect.genericReason", new Object[] { var1.a })));
    }

    public void a(String var1, Object[] var2) {
        if (!this.d) {
            this.d = true;
            this.f.a((fd) null);
            this.f.a((da) (new ex("disconnect.lost", var1, var2)));
        }
    }

    public void a(ki var1) {
        if (!this.d) {
            this.e.a(var1);
            this.e.c();
        }
    }

    public void b(ki var1) {
        if (!this.d) {
            this.e.a(var1);
        }
    }

    public void a(di var1) {
        sn var2 = this.a(var1.a);
        Object var3 = (ls) this.a(var1.b);
        if (var3 == null) {
            var3 = this.f.h;
        }

        if (var2 != null) {
            this.g.a(var2, "random.pop", 0.2F, ((this.c.nextFloat() - this.c.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            this.f.j.a((xw) (new em(this.f.f, var2, (sn) var3, -0.5F)));
            this.g.c(var1.a);
        }

    }

    public void a(pe var1) {
        if (!ChatHook.runhooks(var1.a)) // WorldEditCui
            this.f.v.a(var1.a);
    }

    public void a(nm var1) {
        sn var2 = this.a(var1.a);
        if (var2 != null) {
            gs var3;
            if (var1.b == 1) {
                var3 = (gs) var2;
                var3.J();
            } else if (var1.b == 2) {
                var2.h();
            } else if (var1.b == 3) {
                var3 = (gs) var2;
                var3.a(false, false, false);
            } else if (var1.b == 4) {
                var3 = (gs) var2;
                var3.v();
            }

        }
    }

    public void a(jz var1) {
        sn var2 = this.a(var1.a);
        if (var2 != null) {
            if (var1.e == 0) {
                gs var3 = (gs) var2;
                var3.b(var1.b, var1.c, var1.d);
            }

        }
    }

    public void a(mp var1) {
        if (var1.a.equals("-")) {
            this.b(new nz(this.f.k.b, 14));
        } else {
            try {
                URL var2 = new URL("http://www.minecraft.net/game/joinserver.jsp?user=" + this.f.k.b + "&sessionId=" + this.f.k.c + "&serverId=" + var1.a);
                BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.openStream()));
                String var4 = var3.readLine();
                var3.close();
                if (var4.equalsIgnoreCase("ok")) {
                    this.b(new nz(this.f.k.b, 14));
                } else {
                    this.e.a("disconnect.loginFailedInfo", new Object[] { var4 });
                }
            } catch (Exception var5) {
                var5.printStackTrace();
                this.e.a("disconnect.genericReason", new Object[] { "Internal client error: " + var5.toString() });
            }
        }

    }

    public void b() {
        this.d = true;
        this.e.a();
        this.e.a("disconnect.closed", new Object[0]);
    }

    @SuppressWarnings("rawtypes")
    public void a(jm var1) {
        double var2 = (double) var1.c / 32.0D;
        double var4 = (double) var1.d / 32.0D;
        double var6 = (double) var1.e / 32.0D;
        float var8 = (float) (var1.f * 360) / 256.0F;
        float var9 = (float) (var1.g * 360) / 256.0F;
        ls var10 = (ls) jc.a(var1.b, this.f.f);
        var10.bJ = var1.c;
        var10.bK = var1.d;
        var10.bL = var1.e;
        var10.aD = var1.a;
        var10.b(var2, var4, var6, var8, var9);
        var10.V = true;
        this.g.a(var1.a, var10);
        List var11 = var1.b();
        if (var11 != null) {
            var10.ad().a(var11);
        }

    }

    public void a(hg var1) {
        this.f.f.a(var1.a);
    }

    public void a(rc var1) {
        this.f.h.a(new br(var1.a, var1.b, var1.c));
        this.f.f.x().a(var1.a, var1.b, var1.c);
    }

    public void a(ns var1) {
        Object var2 = this.a(var1.a);
        sn var3 = this.a(var1.b);
        if (var1.a == this.f.h.aD) {
            var2 = this.f.h;
        }

        if (var2 != null) {
            ((sn) var2).i(var3);
        }
    }

    public void a(jf var1) {
        sn var2 = this.a(var1.a);
        if (var2 != null) {
            var2.a(var1.b);
        }

    }

    private sn a(int var1) {
        return (sn) (var1 == this.f.h.aD ? this.f.h : this.g.b(var1));
    }

    public void a(eu var1) {
        this.f.h.d_(var1.a);
    }

    public void a(ox var1) {
        if (var1.a != this.f.h.m) {
            this.h = false;
            this.g = new mm(this, this.g.x().b(), var1.a);
            this.g.B = true;
            this.f.a((fd) this.g);
            this.f.h.m = var1.a;
            this.f.a((da) (new gg(this)));
        }

        this.f.a(true, var1.a);
    }

    public void a(rm var1) {
        qx var2 = new qx(this.f.f, (sn) null, var1.a, var1.b, var1.c, var1.d);
        var2.g = var1.e;
        var2.a(true);
    }

    public void a(iw var1) {
        if (var1.b == 0) {
            qo var2 = new qo(var1.c, var1.d);
            this.f.h.a((lw) var2);
            this.f.h.e.f = var1.a;
        } else if (var1.b == 2) {
            sk var3 = new sk();
            this.f.h.a(var3);
            this.f.h.e.f = var1.a;
        } else if (var1.b == 3) {
            az var4 = new az();
            this.f.h.a(var4);
            this.f.h.e.f = var1.a;
        } else if (var1.b == 1) {
            dc var5 = this.f.h;
            this.f.h.a(in.b(var5.aM), in.b(var5.aN), in.b(var5.aO));
            this.f.h.e.f = var1.a;
        }

    }

    public void a(hq var1) {
        if (var1.a == -1) {
            this.f.h.c.b(var1.c);
        } else if (var1.a == 0 && var1.b >= 36 && var1.b < 45) {
            iz var2 = this.f.h.d.b(var1.b).a();
            if (var1.c != null && (var2 == null || var2.a < var1.c.a)) {
                var1.c.b = 5;
            }

            this.f.h.d.a(var1.b, var1.c);
        } else if (var1.a == this.f.h.e.f) {
            this.f.h.e.a(var1.b, var1.c);
        }

    }

    public void a(oj var1) {
        dw var2 = null;
        if (var1.a == 0) {
            var2 = this.f.h.d;
        } else if (var1.a == this.f.h.e.f) {
            var2 = this.f.h.e;
        }

        if (var2 != null) {
            if (var1.c) {
                var2.a(var1.b);
            } else {
                var2.b(var1.b);
                this.b(new oj(var1.a, var1.b, true));
            }
        }

    }

    public void a(kb var1) {
        if (var1.a == 0) {
            this.f.h.d.a(var1.b);
        } else if (var1.a == this.f.h.e.f) {
            this.f.h.e.a(var1.b);
        }

    }

    public void a(ui var1) {
        if (this.f.f.i(var1.a, var1.b, var1.c)) {
            ow var2 = this.f.f.b(var1.a, var1.b, var1.c);
            if (var2 instanceof yk) {
                yk var3 = (yk) var2;

                for (int var4 = 0; var4 < 4; ++var4) {
                    var3.a[var4] = var1.d[var4];
                }

                var3.y_();
            }
        }

    }

    public void a(mv var1) {
        this.c(var1);
        if (this.f.h.e != null && this.f.h.e.f == var1.a) {
            this.f.h.e.a(var1.b, var1.c);
        }

    }

    public void a(s var1) {
        sn var2 = this.a(var1.a);
        if (var2 != null) {
            var2.c(var1.b, var1.c, var1.d);
        }

    }

    public void a(mn var1) {
        this.f.h.r();
    }

    public void a(vw var1) {
        this.f.f.d(var1.a, var1.b, var1.c, var1.d, var1.e);
    }

    public void a(ca var1) {
        int var2 = var1.b;
        if (var2 >= 0 && var2 < ca.a.length && ca.a[var2] != null) {
            this.f.h.b(ca.a[var2]);
        }

        if (var2 == 1) {
            this.g.x().b(true);
            this.g.h(1.0F);
        } else if (var2 == 2) {
            this.g.x().b(false);
            this.g.h(0.0F);
        }

    }

    public void a(ai var1) {
        if (var1.a == gm.bb.bf) {
            wr.a(var1.b, this.f.f).a(var1.c);
        } else {
            System.out.println("Unknown itemid: " + var1.b);
        }

    }

    public void a(fn var1) {
        this.f.f.e(var1.a, var1.c, var1.d, var1.e, var1.b);
    }

    public void a(of var1) {
        ((tk) this.f.h).b(jl.a(var1.a), var1.b);
    }

    public boolean c() {
        return false;
    }
}
