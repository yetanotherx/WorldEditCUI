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
    public final LineInfo drawnormal;
    public final LineInfo drawhidden;

    public HighlightPosition2D(LineInfo drawnormal, LineInfo drawhidden) {
        this.drawhidden = drawhidden;
        this.drawnormal = drawnormal;
    }

    public void render(int ymin, int ymax) {
        if (!active) {
            return;
        }
        double off = 0.03f;
        RenderShapes.box(drawhidden, x - off, ymin - off, z - off, x + 1 + off, ymax + 1 + off, z + 1 + off);
        RenderShapes.box(drawnormal, x - off, ymin - off, z - off, x + 1 + off, ymax + 1 + off, z + 1 + off);
    }
}