// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst safe
// Source File Name:   SourceFile

import java.io.*;
import java.net.*;
import java.util.Random;
import net.minecraft.client.Minecraft;

public class mx extends td {

    private boolean d;
    private pb e;
    public String a;
    private Minecraft f;
    private mi g;
    private boolean h;
    public ha b;
    Random c;

    public mx(Minecraft minecraft, String s, int i) throws UnknownHostException, IOException {
        d = false;
        h = false;
        b = new ha(((wk) (null)));
        c = new Random();
        f = minecraft;
        Socket socket;
        socket = new Socket(InetAddress.getByName(s), i);
        e = new pb(socket, "Client", ((td) (this)));
    }

    public void a() {
        if(!d)
            e.b();

        e.a();
    }

    public void a(nv nv1) {
        f.c = ((nx) (new xb(f, this)));
        f.I.a(ji.i, 1);
        g = new mi(this, nv1.c, ((int) (nv1.d)));
        g.B = true;
        f.a(((fb) (g)));
        f.h.m = ((int) (nv1.d));
        f.a(((cy) (new ge(this))));
        f.h.aD = nv1.a;
    }

    public void a(mz mz1) {
        double d1 = (double)mz1.b / 32D;
        double d2 = (double)mz1.c / 32D;
        double d3 = (double)mz1.d / 32D;
        hj hj1 = new hj(((fb) (g)), d1, d2, d3, new iw(mz1.h, mz1.i, mz1.l));
        hj1.aP = (double)mz1.e / 128D;
        hj1.aQ = (double)mz1.f / 128D;
        hj1.aR = (double)mz1.g / 128D;
        hj1.bI = mz1.b;
        hj1.bJ = mz1.c;
        hj1.bK = mz1.d;
        g.a(mz1.a, ((si) (hj1)));
    }

    public void a(sj sj1) {
        double d1 = (double)sj1.b / 32D;
        double d2 = (double)sj1.c / 32D;
        double d3 = (double)sj1.d / 32D;
        si obj = null;

        if(sj1.h == 10)
            obj = ((si) (new yc(((fb) (g)), d1, d2, d3, 0)));

        if(sj1.h == 11)
            obj = ((si) (new yc(((fb) (g)), d1, d2, d3, 1)));

        if(sj1.h == 12)
            obj = ((si) (new yc(((fb) (g)), d1, d2, d3, 2)));

        if(sj1.h == 90)
            obj = ((si) (new lt(((fb) (g)), d1, d2, d3)));

        if(sj1.h == 60)
            obj = ((si) (new sg(((fb) (g)), d1, d2, d3)));

        if(sj1.h == 61)
            obj = ((si) (new bw(((fb) (g)), d1, d2, d3)));

        if(sj1.h == 63) {
            obj = ((si) (new cd(((fb) (g)), d1, d2, d3, (double)sj1.e / 8000D, (double)sj1.f / 8000D, (double)sj1.g / 8000D)));
            sj1.i = 0;
        }

        if(sj1.h == 62)
            obj = ((si) (new vn(((fb) (g)), d1, d2, d3)));

        if(sj1.h == 1)
            obj = ((si) (new fx(((fb) (g)), d1, d2, d3)));

        if(sj1.h == 50)
            obj = ((si) (new qr(((fb) (g)), d1, d2, d3)));

        if(sj1.h == 70)
            obj = ((si) (new jq(((fb) (g)), d1, d2, d3, un.F.bn)));

        if(sj1.h == 71)
            obj = ((si) (new jq(((fb) (g)), d1, d2, d3, un.G.bn)));

        if(obj != null) {
            obj.bI = sj1.b;
            obj.bJ = sj1.c;
            obj.bK = sj1.d;
            obj.aS = 0.0F;
            obj.aT = 0.0F;
            obj.aD = sj1.a;
            g.a(sj1.a, ((si) (obj)));

            if(sj1.i > 0) {
                if(sj1.h == 60) {
                    si si1 = a(sj1.i);

                    if(si1 instanceof lo)
                        ((sg)obj).c = (lo)si1;
                }

                ((si) (obj)).a((double)sj1.e / 8000D, (double)sj1.f / 8000D, (double)sj1.g / 8000D);
            }
        }
    }

    public void a(ey ey1) {
        double d1 = (double)ey1.b / 32D;
        double d2 = (double)ey1.c / 32D;
        double d3 = (double)ey1.d / 32D;
        c c1 = null;

        if(ey1.e == 1)
            c1 = new c(((fb) (g)), d1, d2, d3);

        if(c1 != null) {
            c1.bI = ey1.b;
            c1.bJ = ey1.c;
            c1.bK = ey1.d;
            c1.aS = 0.0F;
            c1.aT = 0.0F;
            c1.aD = ey1.a;
            g.a(((si) (c1)));
        }
    }

    public void a(vl vl1) {
        qq qq1 = new qq(((fb) (g)), vl1.b, vl1.c, vl1.d, vl1.e, vl1.f);
        g.a(vl1.a, ((si) (qq1)));
    }

    public void a(gh gh1) {
        si si1 = a(gh1.a);

        if(si1 == null) {
            return;
        } else {
            si1.a((double)gh1.b / 8000D, (double)gh1.c / 8000D, (double)gh1.d / 8000D);
            return;
        }
    }

    public void a(uq uq1) {
        si si1 = a(uq1.a);

        if(si1 != null && uq1.b() != null)
            si1.ad().a(uq1.b());
    }

    public void a(me me1) {
        double d1 = (double)me1.c / 32D;
        double d2 = (double)me1.d / 32D;
        double d3 = (double)me1.e / 32D;
        float f1 = (float)(me1.f * 360) / 256F;
        float f2 = (float)(me1.g * 360) / 256F;
        xq xq1 = new xq(f.f, me1.b);
        xq1.aJ = xq1.bl = xq1.bI = me1.c;
        xq1.aK = xq1.bm = xq1.bJ = me1.d;
        xq1.aL = xq1.bn = xq1.bK = me1.e;
        int i = me1.h;

        if(i == 0)
            xq1.c.a[xq1.c.c] = null;
        else
            xq1.c.a[xq1.c.c] = new iw(i, 1, 0);

        xq1.b(d1, d2, d3, f1, f2);
        g.a(me1.a, ((si) (xq1)));
    }

    public void a(rb rb1) {
        si si1 = a(rb1.a);

        if(si1 == null) {
            return;
        } else {
            si1.bI = rb1.b;
            si1.bJ = rb1.c;
            si1.bK = rb1.d;
            double d1 = (double)si1.bI / 32D;
            double d2 = (double)si1.bJ / 32D + 0.015625D;
            double d3 = (double)si1.bK / 32D;
            float f1 = (float)(rb1.e * 360) / 256F;
            float f2 = (float)(rb1.f * 360) / 256F;
            si1.a(d1, d2, d3, f1, f2, 3);
            return;
        }
    }

    public void a(uc uc1) {
        si si1 = a(uc1.a);

        if(si1 == null) {
            return;
        } else {
            si1.bI += ((int) (uc1.b));
            si1.bJ += ((int) (uc1.c));
            si1.bK += ((int) (uc1.d));
            double d1 = (double)si1.bI / 32D;
            double d2 = (double)si1.bJ / 32D;
            double d3 = (double)si1.bK / 32D;
            float f1 = uc1.g ? (float)(uc1.e * 360) / 256F : si1.aS;
            float f2 = uc1.g ? (float)(uc1.f * 360) / 256F : si1.aT;
            si1.a(d1, d2, d3, f1, f2, 3);
            return;
        }
    }

    public void a(rq rq1) {
        g.c(rq1.a);
    }

    public void a(id id1) {
        da da1 = f.h;
        double d1 = ((gq) (da1)).aM;
        double d2 = ((gq) (da1)).aN;
        double d3 = ((gq) (da1)).aO;
        float f1 = ((gq) (da1)).aS;
        float f2 = ((gq) (da1)).aT;

        if(id1.h) {
            d1 = id1.a;
            d2 = id1.b;
            d3 = id1.c;
        }

        if(id1.i) {
            f1 = id1.e;
            f2 = id1.f;
        }

        da1.bo = 0.0F;
        da1.aP = da1.aQ = da1.aR = 0.0D;
        ((gq) (da1)).b(d1, d2, d3, f1, f2);
        id1.a = ((gq) (da1)).aM;
        id1.b = ((gq) (da1)).aW.b;
        id1.c = ((gq) (da1)).aO;
        id1.d = ((gq) (da1)).aN;
        e.a(((ke) (id1)));

        if(!h) {
            f.h.aJ = f.h.aM;
            f.h.aK = f.h.aN;
            f.h.aL = f.h.aO;
            h = true;
            f.a(((cy) (null)));
        }
    }

    public void a(rz rz1) {
        g.a(rz1.a, rz1.b, rz1.c);
    }

    public void a(wl wl1) {
        li li1 = g.c(wl1.a, wl1.b);
        int i = wl1.a * 16;
        int j = wl1.b * 16;

        for(int k = 0; k < wl1.f; k++) {
            short word0 = wl1.c[k];
            int l = wl1.d[k] & 0xff;
            byte byte0 = wl1.e[k];
            int i1 = word0 >> 12 & 0xf;
            int j1 = word0 >> 8 & 0xf;
            int k1 = word0 & 0xff;
            li1.a(i1, k1, j1, l, ((int) (byte0)));
            g.c(i1 + i, k1, j1 + j, i1 + i, k1, j1 + j);
            g.b(i1 + i, k1, j1 + j, i1 + i, k1, j1 + j);
        }
    }

    public void a(ed ed1) {
        g.c(ed1.a, ed1.b, ed1.c, (ed1.a + ed1.d) - 1, (ed1.b + ed1.e) - 1, (ed1.c + ed1.f) - 1);
        g.a(ed1.a, ed1.b, ed1.c, ed1.d, ed1.e, ed1.f, ed1.g);
    }

    public void a(tq tq1) {
        g.f(tq1.a, tq1.b, tq1.c, tq1.d, tq1.e);
    }

    public void a(yi yi1) {
        e.a("disconnect.kicked", new Object[0]);
        d = true;
        f.a(((fb) (null)));
        f.a(((cy) (new ev("disconnect.disconnected", "disconnect.genericReason", new Object[] {
                              yi1.a
                          }))));
    }

    public void a(String s, Object aobj[]) {
        if(d) {
            return;
        } else {
            d = true;
            f.a(((fb) (null)));
            f.a(((cy) (new ev("disconnect.lost", s, aobj))));
            return;
        }
    }

    public void a(ke ke) {
        if(d) {
            return;
        } else {
            e.a(ke);
            e.c();
            return;
        }
    }

    public void b(ke ke) {
        if(d) {
            return;
        } else {
            e.a(ke);
            return;
        }
    }

    public void a(dg dg1) {
        si si1 = a(dg1.a);
        Object obj = ((Object) ((lo)a(dg1.b)));

        if(obj == null)
            obj = ((Object) (f.h));

        if(si1 != null) {
            g.a(si1, "random.pop", 0.2F, ((c.nextFloat() - c.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            f.j.a(((xn) (new ek(f.f, si1, ((si) (obj)), -0.5F))));
            g.c(dg1.a);
        }
    }

    public void a(pa pa1) {
        if(!ChatHook.runhooks(pa1.a))
            f.v.a(pa1.a);
    }

    public void a(ni ni1) {
        si si1 = a(ni1.a);

        if(si1 == null)
            return;

        if(ni1.b == 1) {
            gq gq1 = (gq)si1;
            gq1.J();
        } else if(ni1.b == 2)
            si1.h();
        else if(ni1.b == 3) {
            gq gq2 = (gq)si1;
            gq2.a(false, false, false);
        } else if(ni1.b == 4) {
            gq gq3 = (gq)si1;
            gq3.v();
        }
    }

    public void a(jv jv1) {
        si si1 = a(jv1.a);

        if(si1 == null)
            return;

        if(jv1.e == 0) {
            gq gq1 = (gq)si1;
            gq1.b(jv1.b, jv1.c, jv1.d);
        }
    }

    public void a(ml ml1) {
        if(ml1.a.equals("-"))
            b(((ke) (new nv(f.k.b, 13))));
        else
            try {
                URL url = new URL((new StringBuilder()).append("http://www.minecraft.net/game/joinserver.jsp?user=").append(f.k.b).append("&sessionId=").append(f.k.c).append("&serverId=").append(ml1.a).toString());
                BufferedReader bufferedreader = new BufferedReader(((java.io.Reader) (new InputStreamReader(url.openStream()))));
                String s = bufferedreader.readLine();
                bufferedreader.close();

                if(s.equalsIgnoreCase("ok"))
                    b(((ke) (new nv(f.k.b, 13))));
                else
                    e.a("disconnect.loginFailedInfo", new Object[] {
                            s
                        });
            } catch(Exception exception) {
                exception.printStackTrace();
                e.a("disconnect.genericReason", new Object[] {
                        (new StringBuilder()).append("Internal client error: ").append(exception.toString()).toString()
                    });
            }
    }

    public void b() {
        d = true;
        e.a();
        e.a("disconnect.closed", new Object[0]);
    }

    public void a(jj jj1) {
        double d1 = (double)jj1.c / 32D;
        double d2 = (double)jj1.d / 32D;
        double d3 = (double)jj1.e / 32D;
        float f1 = (float)(jj1.f * 360) / 256F;
        float f2 = (float)(jj1.g * 360) / 256F;
        lo lo1 = (lo)iz.a(((int) (jj1.b)), f.f);
        lo1.bI = jj1.c;
        lo1.bJ = jj1.d;
        lo1.bK = jj1.e;
        lo1.aD = jj1.a;
        lo1.b(d1, d2, d3, f1, f2);
        lo1.V = true;
        g.a(jj1.a, ((si) (lo1)));
        java.util.List list = jj1.b();

        if(list != null)
            lo1.ad().a(list);
    }

    public void a(he he1) {
        f.f.a(he1.a);
    }

    public void a(qx qx1) {
        f.h.a(new bp(qx1.a, qx1.b, qx1.c));
        f.f.x().a(qx1.a, qx1.b, qx1.c);
    }

    public void a(no no1) {
        Object obj = ((Object) (a(no1.a)));
        si si1 = a(no1.b);

        if(no1.a == f.h.aD)
            obj = ((Object) (f.h));

        if(obj == null) {
            return;
        } else {
            ((si) (obj)).i(si1);
            return;
        }
    }

    public void a(jc jc1) {
        si si1 = a(jc1.a);

        if(si1 != null)
            si1.a(jc1.b);
    }

    private si a(int i) {
        if(i == f.h.aD)
            return ((si) (f.h));
        else
            return g.b(i);
    }

    public void a(es es1) {
        f.h.b_(es1.a);
    }

    public void a(ot ot1) {
        if(ot1.a != f.h.m) {
            h = false;
            g = new mi(this, g.x().b(), ((int) (ot1.a)));
            g.B = true;
            f.a(((fb) (g)));
            f.h.m = ((int) (ot1.a));
            f.a(((cy) (new ge(this))));
        }

        f.a(true, ((int) (ot1.a)));
    }

    public void a(rh rh1) {
        qs qs1 = new qs(f.f, ((si) (null)), rh1.a, rh1.b, rh1.c, rh1.d);
        qs1.g = rh1.e;
        qs1.a(true);
    }

    public void a(it it1) {
        if(it1.b == 0) {
            qk qk1 = new qk(it1.c, it1.d);
            f.h.a(((ls) (qk1)));
            f.h.e.f = it1.a;
        } else if(it1.b == 2) {
            sf sf1 = new sf();
            f.h.a(sf1);
            f.h.e.f = it1.a;
        } else if(it1.b == 3) {
            ay ay1 = new ay();
            f.h.a(ay1);
            f.h.e.f = it1.a;
        } else if(it1.b == 1) {
            da da1 = f.h;
            f.h.a(ik.b(((gq) (da1)).aM), ik.b(((gq) (da1)).aN), ik.b(((gq) (da1)).aO));
            f.h.e.f = it1.a;
        }
    }

    public void a(ho ho1) {
        if(ho1.a == -1)
            f.h.c.b(ho1.c);
        else if(ho1.a == 0 && ho1.b >= 36 && ho1.b < 45) {
            iw iw1 = f.h.d.b(ho1.b).a();

            if(ho1.c != null && (iw1 == null || iw1.a < ho1.c.a))
                ho1.c.b = 5;

            f.h.d.a(ho1.b, ho1.c);
        } else if(ho1.a == f.h.e.f)
            f.h.e.a(ho1.b, ho1.c);
    }

    public void a(of of1) {
        du du1 = null;

        if(of1.a == 0)
            du1 = f.h.d;
        else if(of1.a == f.h.e.f)
            du1 = f.h.e;

        if(du1 != null)
            if(of1.c) {
                du1.a(of1.b);
            } else {
                du1.b(of1.b);
                b(((ke) (new of(of1.a, of1.b, true))));
            }
    }

    public void a(jx jx1) {
        if(jx1.a == 0)
            f.h.d.a(jx1.b);
        else if(jx1.a == f.h.e.f)
            f.h.e.a(jx1.b);
    }

    public void a(ud ud1) {
        if(f.f.i(ud1.a, ud1.b, ud1.c)) {
            os os = f.f.b(ud1.a, ud1.b, ud1.c);

            if(os instanceof yb) {
                yb yb1 = (yb)os;

                for(int i = 0; i < 4; i++)
                    yb1.a[i] = ud1.d[i];

                yb1.y_();
            }
        }
    }

    public void a(mr mr1) {
        c(((ke) (mr1)));

        if(f.h.e != null && f.h.e.f == mr1.a)
            f.h.e.a(mr1.b, mr1.c);
    }

    public void a(r r1) {
        si si1 = a(r1.a);

        if(si1 != null)
            si1.c(r1.b, r1.c, r1.d);
    }

    public void a(mj mj) {
        f.h.r();
    }

    public void a(vo vo1) {
        f.f.d(vo1.a, vo1.b, vo1.c, vo1.d, vo1.e);
    }

    public void a(by by1) {
        int i = by1.b;

        if(i >= 0 && i < by.a.length && by.a[i] != null)
            f.h.b(by.a[i]);

        if(i == 1) {
            g.x().b(true);
            g.h(1.0F);
        } else if(i == 2) {
            g.x().b(false);
            g.h(0.0F);
        }
    }

    public void a(ah ah1) {
        if(ah1.a == gk.bb.be)
            wi.a(ah1.b, f.f).a(ah1.c);
        else
            System.out.println((new StringBuilder()).append("Unknown itemid: ").append(((int) (ah1.b))).toString());
    }

    public void a(fl fl1) {
        f.f.e(fl1.a, fl1.c, fl1.d, fl1.e, fl1.b);
    }

    public void a(ob ob1) {
        ((tf)f.h).b(ji.a(ob1.a), ob1.b);
    }

    public boolean c() {
        return false;
    }
}
