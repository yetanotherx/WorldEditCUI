/**
 * 
 */
package net.lahwran.wecui.rendering;

import org.lwjgl.opengl.GL11;

/**
 * @author lahwran
 *
 */
public class HighlightPosition {

    public int x;
    public int y;
    public int z;
    public boolean active = false;
    public final LineInfo drawnormal;
    public final LineInfo drawhidden;

    public HighlightPosition(LineInfo color, float alphanormal, float alphahidden) {
        this.drawhidden = new LineInfo(color);
        drawhidden.alpha = alphahidden;
        drawhidden.depthfunc = GL11.GL_GEQUAL;
        this.drawnormal = new LineInfo(color);
        drawnormal.alpha = alphanormal;
        drawnormal.depthfunc = GL11.GL_LESS;
    }

    public void render() {
        if (!active)
            return;
        double off = 0.03f;
        RenderShapes.box(drawhidden, x-off, y-off, z-off, x+1+off, y+1+off, z+1+off);
        RenderShapes.box(drawnormal, x-off, y-off, z-off, x+1+off, y+1+off, z+1+off);
    }
}
