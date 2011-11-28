package cuix.render;

/**
 * Stores data for a cuboid-based region.
 * 
 * Contains, X/Y/Z values for the first and second points and the boxes for each corner.
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CuboidRegion extends CUIRegion {

    protected HighlightPosition[] points;
    
    public double x1, y1, z1, x2, y2, z2;

    public CuboidRegion() {
        points = new HighlightPosition[2];
        points[0] = new HighlightPosition(Colors.firstnormal, Colors.firsthidden);
        points[1] = new HighlightPosition(Colors.secondnormal, Colors.secondhidden);
    }

    @Override
    public void render() {
        points[0].render();
        points[1].render();
        if (points[0].active && points[1].active) {
            RenderShapes.gridSurface(Colors.gridnormal, x1, y1, z1, x2, y2, z2);
            RenderShapes.gridSurface(Colors.gridhidden, x1, y1, z1, x2, y2, z2);
            RenderShapes.box(Colors.boxhidden, x1, y1, z1, x2, y2, z2);
            RenderShapes.box(Colors.boxnormal, x1, y1, z1, x2, y2, z2);
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
            HighlightPosition pos = points[index];
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
