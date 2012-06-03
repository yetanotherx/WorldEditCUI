package deobf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.opengl.ARBBufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import wecui.util.Constants;

public class Tessellator
{
  private static boolean b = false;
  private static boolean c = false;
  private ByteBuffer d;
  private IntBuffer e;
  private FloatBuffer f;
  private ShortBuffer g;
  private int[] h;
  private int i = 0;
  private double j;
  private double k;
  private int l;
  private int m;
  private boolean n = false;
  private boolean o = false;
  private boolean p = false;
  private boolean q = false;
  private int r = 0;
  private int s = 0;
  private boolean t = false;
  private int u;
  private double v;
  private double w;
  private double x;
  private int y;
  public static final Tessellator a = new Tessellator(2097152);

  private boolean z = false;

  private boolean A = false;
  private IntBuffer B;
  private int C = 0;
  private int D = 10;
  private int E;
  
  public int opaqueAlpha = 255; //WECUI

  private Tessellator(int paramInt)
  {
    Constants.transparencyEnabled = true; //WECUI
    this.E = paramInt;

    this.d = GLAllocation.c(paramInt * 4);
    this.e = this.d.asIntBuffer();
    this.f = this.d.asFloatBuffer();
    this.g = this.d.asShortBuffer();
    this.h = new int[paramInt];

    this.A = ((c) && (GLContext.getCapabilities().GL_ARB_vertex_buffer_object));
    if (this.A) {
      this.B = GLAllocation.d(this.D);
      ARBBufferObject.glGenBuffersARB(this.B);
    }
  }

  public int a()
  {
    if (!this.z) throw new IllegalStateException("Not tesselating!");
    this.z = false;
    if (this.i > 0) {
      this.e.clear();
      this.e.put(this.h, 0, this.r);

      this.d.position(0);
      this.d.limit(this.r * 4);

      if (this.A) {
        this.C = ((this.C + 1) % this.D);
        ARBBufferObject.glBindBufferARB(34962, this.B.get(this.C));

        ARBBufferObject.glBufferDataARB(34962, this.d, 35040);
      }

      if (this.o) {
        if (this.A) {
          GL11.glTexCoordPointer(2, 5126, 32, 12L);
        } else {
          this.f.position(3);
          GL11.glTexCoordPointer(2, 32, this.f);
        }
        GL11.glEnableClientState(32888);
      }
      if (this.p) {
        es.b(es.b);

        if (this.A) {
          GL11.glTexCoordPointer(2, 5122, 32, 28L);
        } else {
          this.g.position(14);
          GL11.glTexCoordPointer(2, 32, this.g);
        }
        GL11.glEnableClientState(32888);
        es.b(es.a);
      }
      if (this.n) {
        if (this.A) {
          GL11.glColorPointer(4, 5121, 32, 20L);
        } else {
          this.d.position(20);
          GL11.glColorPointer(4, true, 32, this.d);
        }
        GL11.glEnableClientState(32886);
      }
      if (this.q) {
        if (this.A) {
          GL11.glNormalPointer(5121, 32, 24L);
        } else {
          this.d.position(24);
          GL11.glNormalPointer(32, this.d);
        }
        GL11.glEnableClientState(32885);
      }
      if (this.A) {
        GL11.glVertexPointer(3, 5126, 32, 0L);
      } else {
        this.f.position(0);
        GL11.glVertexPointer(3, 32, this.f);
      }
      GL11.glEnableClientState(32884);
      if ((this.u == 7) && (b))
        GL11.glDrawArrays(4, 0, this.i);
      else {
        GL11.glDrawArrays(this.u, 0, this.i);
      }

      GL11.glDisableClientState(32884);
      if (this.o) GL11.glDisableClientState(32888);
      if (this.p) {
        es.b(es.b);
        GL11.glDisableClientState(32888);
        es.b(es.a);
      }
      if (this.n) GL11.glDisableClientState(32886);
      if (this.q) GL11.glDisableClientState(32885);
    }

    int i1 = this.r * 4;
    d();

    return i1;
  }

  private void d() {
    this.i = 0;

    this.d.clear();
    this.r = 0;
    this.s = 0;
  }

  public void b()
  {
    a(7);
  }

  public void a(int paramInt)
  {
    if (this.z) {
      throw new IllegalStateException("Already tesselating!");
    }
    this.z = true;

    d();
    this.u = paramInt;
    this.q = false;
    this.n = false;
    this.o = false;
    this.p = false;
    this.t = false;
  }

  public void a(double paramDouble1, double paramDouble2) {
    this.o = true;
    this.j = paramDouble1;
    this.k = paramDouble2;
  }

  public void b(int paramInt) {
    this.p = true;
    this.l = paramInt;
  }

  public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
    a((int)(paramFloat1 * 255.0F), (int)(paramFloat2 * 255.0F), (int)(paramFloat3 * 255.0F));
  }

  public void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    a((int)(paramFloat1 * 255.0F), (int)(paramFloat2 * 255.0F), (int)(paramFloat3 * 255.0F), (int)(paramFloat4 * 255.0F));
  }

  public void a(int paramInt1, int paramInt2, int paramInt3) {
    a(paramInt1, paramInt2, paramInt3, this.opaqueAlpha); //WECUI
  }

  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.t) return;

    if (paramInt1 > 255) paramInt1 = 255;
    if (paramInt2 > 255) paramInt2 = 255;
    if (paramInt3 > 255) paramInt3 = 255;
    if (paramInt4 > 255) paramInt4 = 255;
    if (paramInt1 < 0) paramInt1 = 0;
    if (paramInt2 < 0) paramInt2 = 0;
    if (paramInt3 < 0) paramInt3 = 0;
    if (paramInt4 < 0) paramInt4 = 0;

    this.n = true;
    if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
      this.m = (paramInt4 << 24 | paramInt3 << 16 | paramInt2 << 8 | paramInt1);
    else
      this.m = (paramInt1 << 24 | paramInt2 << 16 | paramInt3 << 8 | paramInt4);
  }

  public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5)
  {
    a(paramDouble4, paramDouble5);
    a(paramDouble1, paramDouble2, paramDouble3);
  }

  public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
    this.s += 1;

    if ((this.u == 7) && (b) && (this.s % 4 == 0)) {
      for (int i1 = 0; i1 < 2; i1++) {
        int i2 = 8 * (3 - i1);
        if (this.o) {
          this.h[(this.r + 3)] = this.h[(this.r - i2 + 3)];
          this.h[(this.r + 4)] = this.h[(this.r - i2 + 4)];
        }
        if (this.p) {
          this.h[(this.r + 7)] = this.h[(this.r - i2 + 7)];
        }
        if (this.n) {
          this.h[(this.r + 5)] = this.h[(this.r - i2 + 5)];
        }

        this.h[(this.r + 0)] = this.h[(this.r - i2 + 0)];
        this.h[(this.r + 1)] = this.h[(this.r - i2 + 1)];
        this.h[(this.r + 2)] = this.h[(this.r - i2 + 2)];

        this.i += 1;
        this.r += 8;
      }
    }

    if (this.o) {
      this.h[(this.r + 3)] = Float.floatToRawIntBits((float)this.j);
      this.h[(this.r + 4)] = Float.floatToRawIntBits((float)this.k);
    }
    if (this.p) {
      this.h[(this.r + 7)] = this.l;
    }
    if (this.n) {
      this.h[(this.r + 5)] = this.m;
    }
    if (this.q) {
      this.h[(this.r + 6)] = this.y;
    }

    this.h[(this.r + 0)] = Float.floatToRawIntBits((float)(paramDouble1 + this.v));
    this.h[(this.r + 1)] = Float.floatToRawIntBits((float)(paramDouble2 + this.w));
    this.h[(this.r + 2)] = Float.floatToRawIntBits((float)(paramDouble3 + this.x));

    this.r += 8;

    this.i += 1;
    if ((this.i % 4 == 0) && (this.r >= this.E - 32)) {
      a();
      this.z = true;
    }
  }

  public void c(int paramInt) {
    int i1 = paramInt >> 16 & 0xFF;
    int i2 = paramInt >> 8 & 0xFF;
    int i3 = paramInt & 0xFF;
    a(i1, i2, i3);
  }

  public void a(int paramInt1, int paramInt2) {
    int i1 = paramInt1 >> 16 & 0xFF;
    int i2 = paramInt1 >> 8 & 0xFF;
    int i3 = paramInt1 & 0xFF;
    a(i1, i2, i3, paramInt2);
  }

  public void c() {
    this.t = true;
  }

  public void b(float paramFloat1, float paramFloat2, float paramFloat3) {
    this.q = true;
    int i1 = (byte)(int)(paramFloat1 * 127.0F);
    int i2 = (byte)(int)(paramFloat2 * 127.0F);
    int i3 = (byte)(int)(paramFloat3 * 127.0F);

    this.y = (i1 & 0xFF | (i2 & 0xFF) << 8 | (i3 & 0xFF) << 16);
  }

  public void b(double paramDouble1, double paramDouble2, double paramDouble3) {
    this.v = paramDouble1;
    this.w = paramDouble2;
    this.x = paramDouble3;
  }

  public void c(float paramFloat1, float paramFloat2, float paramFloat3) {
    this.v += paramFloat1;
    this.w += paramFloat2;
    this.x += paramFloat3;
  }
}
