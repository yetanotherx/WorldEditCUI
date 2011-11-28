package cuix.render;

/**
 * Stores data for each corner of a region
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
    public final LineInfo drawnormal;
    public final LineInfo drawhidden;

    public HighlightPosition(LineInfo drawnormal, LineInfo drawhidden) {
        this.drawhidden = drawhidden;
        this.drawnormal = drawnormal;
    }

    public void render() {
        if (!active)
            return;
        double off = 0.03f;
        RenderShapes.box(drawhidden, x-off, y-off, z-off, x+1+off, y+1+off, z+1+off);
        RenderShapes.box(drawnormal, x-off, y-off, z-off, x+1+off, y+1+off, z+1+off);
    }
}
