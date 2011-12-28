package wecui.obfuscation;

import deobf.Tessellator;

/**
 * Singleton obfuscation class for dealing
 * with tesselator rendering. 
 * 
 * @author yetanotherx
 * @author lahwran
 * 
 * @obfuscated
 */
public class RenderObfuscation {

    protected Tessellator tess;
    
    protected RenderObfuscation() {
        tess = Tessellator.a;
    }
    
    public void startDrawing(int type) {
        tess.a(type);
    }

    public void addVertex(double x, double y, double z) {
        tess.a(x, y, z);
    }

    public void finishDrawing() {
        tess.a();
    }

    public static RenderObfuscation getInstance() {
        return RenderObfuscationHolder.INSTANCE;
    }

    protected static class RenderObfuscationHolder {
        protected static final RenderObfuscation INSTANCE = new RenderObfuscation();
    }
}
