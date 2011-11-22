
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;


/**
 * Stores data for a poly-based region.
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIx_render_Polygon2DRegion extends CUIx_render_CuiRegion {

    private ArrayList<CUIx_render_HighlightPosition2D> dynamicpoints = new ArrayList<CUIx_render_HighlightPosition2D>();
    private CUIx_render_HighlightPosition2D[] pts;


    private int min;
    private int max;

    @Override
    public void render() {
        if (pts == null)
            return;
        for (int point = 0; point < pts.length; point++) {
            if (pts[point] != null) {
                pts[point].render(min, max);
                
            }
        }
        double off = 0.03;
        renderLines(CUIx_render_Colors.boxnormal, off);
        renderLines(CUIx_render_Colors.boxhidden, off);
        double half = min + (double)(min-max)/2;
        for (double height = min; height <= max+1; height++) {

            if (height == min || height == max+1) {
                renderPolygon(CUIx_render_Colors.boxnormal, height + off);
                renderPolygon(CUIx_render_Colors.boxhidden, height + off);
            } else {
                renderPolygon(CUIx_render_Colors.gridnormal, height + off);
                renderPolygon(CUIx_render_Colors.gridhidden, height + off);
            }
        }
    }

    private void renderLines(CUIx_render_LineInfo color, double off) {
        color.prepareRender();
        CUIx_obf_Handler o = CUIx_obf_Handler.instance;

        o.draw_begin(GL11.GL_LINES);
        color.prepareColor();
        for (int i = 0; i < pts.length; i++) {
            if (pts[i] != null) {
                o.addVertex(pts[i].x+0.5, min+off, pts[i].z+0.5);
                o.addVertex(pts[i].x+0.5, max+1+off, pts[i].z+0.5);
            }
        }
        o.draw();
    }

    private void renderPolygon(CUIx_render_LineInfo color, double height) {
        color.prepareRender();
        CUIx_obf_Handler o = CUIx_obf_Handler.instance;

        o.draw_begin(GL11.GL_LINE_LOOP);
        color.prepareColor();
        for (int i = 0; i < pts.length; i++) {
            if (pts[i] != null) {
                o.addVertex(pts[i].x+0.5, height, pts[i].z+0.5);
            }
        }
        o.draw();
    }

    @Override
    public void setPoint(int id, int x, int z, int regionSize) {
        CUIx_render_HighlightPosition2D position = new CUIx_render_HighlightPosition2D(CUIx_render_Colors.polynormal, CUIx_render_Colors.polyhidden);
        position.x = x;
        position.z = z;
        position.active = true;
        this.regionSize = regionSize;

        if (id < dynamicpoints.size()) {
            dynamicpoints.set(id, position);
        } else {
            for (int i=0; i < id - dynamicpoints.size(); i++) {
                dynamicpoints.add(null);
            }
            dynamicpoints.add(position);
        }
        pts = dynamicpoints.toArray(new CUIx_render_HighlightPosition2D[dynamicpoints.size()]);
    }

    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

}
