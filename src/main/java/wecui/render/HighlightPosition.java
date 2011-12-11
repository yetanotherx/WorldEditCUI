package wecui.render;

/**
 * Stores data for each corner of a region
 * 
 * TODO: Store all points in a better point controller class
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class HighlightPosition {

    public int x;
    public int y;
    public int z;
    public boolean active = false;
    protected RenderShapes renderer;
    protected final LineInfo drawnormal;
    protected final LineInfo drawhidden;

    public HighlightPosition(RenderShapes renderer, LineInfo drawnormal, LineInfo drawhidden) {
        this.renderer = renderer;
        this.drawnormal = drawnormal;
        this.drawhidden = drawhidden;
    }
    
    public void render() {
        if (!active)
            return;
        double off = 0.03f;
        renderer.drawBox(drawhidden, x-off, y-off, z-off, x+1+off, y+1+off, z+1+off);
        renderer.drawBox(drawnormal, x-off, y-off, z-off, x+1+off, y+1+off, z+1+off);
    }
}
