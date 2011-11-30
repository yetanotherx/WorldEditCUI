package wecui.render;


/**
 * 2-dimensional highlighted box
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class HighlightPosition2D {

    public int x;
    public int z;
    public boolean active = false;
    protected RenderShapes renderer;
    protected final LineInfo drawnormal;
    protected final LineInfo drawhidden;

    public HighlightPosition2D(RenderShapes renderer, LineInfo drawnormal, LineInfo drawhidden) {
        this.renderer = renderer;
        this.drawnormal = drawnormal;
        this.drawhidden = drawhidden;
    }

    public void render(int ymin, int ymax) {
        if (!active) {
            return;
        }
        double off = 0.03f;
        renderer.drawBox(drawhidden, x - off, ymin - off, z - off, x + 1 + off, ymax + 1 + off, z + 1 + off);
        renderer.drawBox(drawnormal, x - off, ymin - off, z - off, x + 1 + off, ymax + 1 + off, z + 1 + off);
    }
}