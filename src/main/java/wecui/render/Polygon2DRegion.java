package wecui.render;

import java.util.ArrayList;
import wecui.WorldEditCUI;

/**
 * Stores data for a poly-based region.
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

    public Polygon2DRegion(WorldEditCUI controller) {
        super(controller);
    }

    @Override
    public void render() {
        if (pts == null) {
            return;
        }
        for (int point = 0; point < pts.length; point++) {
            if (pts[point] != null) {
                pts[point].render(min, max);

            }
        }
        double off = 0.03;
        renderer.draw2DLines(Colors.boxnormal, off, pts, min, max);
        renderer.draw2DLines(Colors.boxhidden, off, pts, min, max);


        double half = min + (double) (min - max) / 2;
        for (double height = min; height <= max + 1; height++) {

            if (height == min || height == max + 1) {
                renderer.draw2DPolygon(Colors.boxnormal, height + off, pts);
                renderer.draw2DPolygon(Colors.boxhidden, height + off, pts);
            } else {
                renderer.draw2DPolygon(Colors.gridnormal, height + off, pts);
                renderer.draw2DPolygon(Colors.gridhidden, height + off, pts);
            }
        }
    }

    @Override
    public void setPoint(int id, int x, int z, int regionSize) {
        HighlightPosition2D position = new HighlightPosition2D(renderer, Colors.polynormal, Colors.polyhidden);
        position.x = x;
        position.z = z;
        position.active = true;
        this.regionSize = regionSize;

        if (id < dynamicpoints.size()) {
            dynamicpoints.set(id, position);
        } else {
            for (int i = 0; i < id - dynamicpoints.size(); i++) {
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
