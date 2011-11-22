
import org.lwjgl.opengl.GL11;

/**
 * Stores data for each corner of a region
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class CUIx_render_HighlightPosition {

    public int x;
    public int y;
    public int z;
    public boolean active = false;
    public final CUIx_render_LineInfo drawnormal;
    public final CUIx_render_LineInfo drawhidden;

    public CUIx_render_HighlightPosition(CUIx_render_LineInfo color, float alphanormal, float alphahidden) {
        this.drawhidden = new CUIx_render_LineInfo(color);
        drawhidden.alpha = alphahidden;
        drawhidden.depthfunc = GL11.GL_GEQUAL;
        this.drawnormal = new CUIx_render_LineInfo(color);
        drawnormal.alpha = alphanormal;
        drawnormal.depthfunc = GL11.GL_LESS;
    }

    public void render() {
        if (!active)
            return;
        double off = 0.03f;
        CUIx_render_RenderShapes.box(drawhidden, x-off, y-off, z-off, x+1+off, y+1+off, z+1+off);
        CUIx_render_RenderShapes.box(drawnormal, x-off, y-off, z-off, x+1+off, y+1+off, z+1+off);
    }
}
