
import org.lwjgl.opengl.GL11;

/**
 * Stores data for a cuboid-based region.
 * 
 * Contains, X/Y/Z values for the first and second points, the 
 * LineInfo for each potential line, and the boxes for each corner.
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIx_render_CuboidRegion extends CUIx_render_CuiRegion {

    private final CUIx_render_HighlightPosition[] points;
    
    public static CUIx_render_LineInfo firstpoint = new CUIx_render_LineInfo(3.0f, 0.2f, 0.8f, 0.2f);
    public static CUIx_render_LineInfo secondpoint = new CUIx_render_LineInfo(3.0f, 0.2f, 0.2f, 0.8f);
    public static CUIx_render_LineInfo gridnormal = new CUIx_render_LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 0.2f, GL11.GL_LESS);
    public static CUIx_render_LineInfo gridhidden = new CUIx_render_LineInfo(2.0f, 0.4F, 0.1F, 0.1F, 0.1f, GL11.GL_GEQUAL);
    public static CUIx_render_LineInfo boxnormal = new CUIx_render_LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 1.0f, GL11.GL_LESS);
    public static CUIx_render_LineInfo boxhidden = new CUIx_render_LineInfo(2.0f, 0.4F, 0.1F, 0.1F, 0.2f, GL11.GL_GEQUAL);
    
    public double x1, y1, z1, x2, y2, z2;

    public CUIx_render_CuboidRegion() {
        points = new CUIx_render_HighlightPosition[2];
        points[0] = new CUIx_render_HighlightPosition(firstpoint, 0.8f, 0.2f);
        points[1] = new CUIx_render_HighlightPosition(secondpoint, 0.8f, 0.2f);
    }

    @Override
    public void render() {
        points[0].render();
        points[1].render();
        if (points[0].active && points[1].active) {
            CUIx_render_RenderShapes.gridSurface(gridnormal, x1, y1, z1, x2, y2, z2);
            CUIx_render_RenderShapes.gridSurface(gridhidden, x1, y1, z1, x2, y2, z2);
            CUIx_render_RenderShapes.box(boxhidden, x1, y1, z1, x2, y2, z2);
            CUIx_render_RenderShapes.box(boxnormal, x1, y1, z1, x2, y2, z2);
        }
    }

    private void calcBounds() {
        double off = 0.02;
        double off1 = 1 + off;
        x1 = Double.MAX_VALUE;
        y1 = Double.MAX_VALUE;
        z1 = Double.MAX_VALUE;
        x2 = -Double.MAX_VALUE;
        y2 = -Double.MAX_VALUE;
        z2 = -Double.MAX_VALUE;

        for (int index = 0; index < points.length; index++) {
            CUIx_render_HighlightPosition pos = points[index];
            if (!pos.active) {
                continue;
            }
            if (pos.x + off1 > x2) {
                x2 = pos.x + off1;
            }

            if (pos.x - off < x1) {
                x1 = pos.x - off;
            }

            if (pos.y + off1 > y2) {
                y2 = pos.y + off1;
            }

            if (pos.y - off < y1) {
                y1 = pos.y - off;
            }

            if (pos.z + off1 > z2) {
                z2 = pos.z + off1;
            }

            if (pos.z - off < z1) {
                z1 = pos.z - off;
            }
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z, int regionSize) {
        if (id < points.length) {
            points[id].x = x;
            points[id].y = y;
            points[id].z = z;
            points[id].active = true;
            calcBounds();
        }
    }
}
