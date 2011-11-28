package cuix.render;


import cuix.obfuscation.ObfuscationHandler;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;


/**
 * Stores data for a poly-based region.
 * 
 * TODO: Move some of this to rendershapes
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class Polygon2DRegion extends CUIRegion {

    private ArrayList<HighlightPosition2D> dynamicpoints = new ArrayList<HighlightPosition2D>();
    private HighlightPosition2D[] pts;


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
        renderLines(Colors.boxnormal, off);
        renderLines(Colors.boxhidden, off);
        double half = min + (double)(min-max)/2;
        for (double height = min; height <= max+1; height++) {

            if (height == min || height == max+1) {
                renderPolygon(Colors.boxnormal, height + off);
                renderPolygon(Colors.boxhidden, height + off);
            } else {
                renderPolygon(Colors.gridnormal, height + off);
                renderPolygon(Colors.gridhidden, height + off);
            }
        }
    }

    private void renderLines(LineInfo color, double off) {
        color.prepareRender();
        ObfuscationHandler o = ObfuscationHandler.instance;

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

    private void renderPolygon(LineInfo color, double height) {
        color.prepareRender();
        ObfuscationHandler o = ObfuscationHandler.instance;

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
        HighlightPosition2D position = new HighlightPosition2D(Colors.polynormal, Colors.polyhidden);
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
        pts = dynamicpoints.toArray(new HighlightPosition2D[dynamicpoints.size()]);
    }

    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

}
