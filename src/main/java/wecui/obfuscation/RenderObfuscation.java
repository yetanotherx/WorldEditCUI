package wecui.obfuscation;

import deobf.RenderHelper;
import deobf.Tessellator;

/**
 * Singleton obfuscation class for dealing
 * with tesselator rendering. 
 * 
 * @author yetanotherx
 * @author lahwran
 * 
 * @obfuscated 1.3.1
 */
public class RenderObfuscation {

    protected Tessellator tess;

    protected RenderObfuscation() {
        tess = Tessellator.a;
    }

    public void startDrawing(int type) {
        tess.b(type);
    }

    public void addVertex(double x, double y, double z) {
        tess.a(x, y, z);
    }

    public void finishDrawing() {
        tess.a();
    }

    /**
     * TODO: Find if this is even necessary
     */
    public static void disableLighting() {
        RenderHelper.a();
    }

    public static void enableLighting() {
        RenderHelper.b();
    }

    public static RenderObfuscation getInstance() {
        return RenderObfuscationHolder.INSTANCE;
    }

    protected static class RenderObfuscationHolder {

        protected static final RenderObfuscation INSTANCE = new RenderObfuscation();
    }
}
