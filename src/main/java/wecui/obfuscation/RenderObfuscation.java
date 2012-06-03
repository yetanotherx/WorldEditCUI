package wecui.obfuscation;

import deobf.Block;
import deobf.RenderBlocks;
import deobf.RenderHelper;
import deobf.Tessellator;
import wecui.util.Constants;

/**
 * Singleton obfuscation class for dealing
 * with tesselator rendering. 
 * 
 * @author yetanotherx
 * @author lahwran
 * 
 * @obfuscated 1.2.5
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

    public int finishDrawing() {
        return tess.a();
    }
    
    public static void disableLighting() {
        RenderHelper.a();
    }

    public static void enableLighting() {
        RenderHelper.b();
    }

    public static RenderObfuscation getInstance() {
        return RenderObfuscationHolder.INSTANCE;
    }

    public void setOpaque(int i) {
        if( Constants.transparencyEnabled ) {
            tess.opaqueAlpha = 128;
        }
    }

    public void setTranslation(double d, double d0, double d1) {
        tess.b(d, d0, d1);
    }
    
    public boolean renderBlockByRenderType(RenderBlocks rb, Block par1Block, float par2, float par3, float par4) {
        return rb.b(par1Block, (int) par2, (int) par3, (int) par4);
    }

    public int getRenderBlockPass(Block block) {
        return block.c();
    }

    protected static class RenderObfuscationHolder {
        protected static final RenderObfuscation INSTANCE = new RenderObfuscation();
    }
}
