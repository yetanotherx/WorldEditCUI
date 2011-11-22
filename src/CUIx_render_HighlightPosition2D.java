
/**
 * 2-dimensional highlighted box
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIx_render_HighlightPosition2D {

    public int x;
    public int z;
    public boolean active = false;
    public final CUIx_render_LineInfo drawnormal;
    public final CUIx_render_LineInfo drawhidden;

    public CUIx_render_HighlightPosition2D(CUIx_render_LineInfo drawnormal, CUIx_render_LineInfo drawhidden) {
        this.drawhidden = drawhidden;
        this.drawnormal = drawnormal;
    }

    public void render(int ymin, int ymax) {
        if (!active) {
            return;
        }
        double off = 0.03f;
        CUIx_render_RenderShapes.box(drawhidden, x - off, ymin - off, z - off, x + 1 + off, ymax + 1 + off, z + 1 + off);
        CUIx_render_RenderShapes.box(drawnormal, x - off, ymin - off, z - off, x + 1 + off, ymax + 1 + off, z + 1 + off);
    }
}