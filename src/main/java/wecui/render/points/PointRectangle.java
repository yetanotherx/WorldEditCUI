package wecui.render.points;

import wecui.render.LineColor;
import wecui.render.RenderShapes;

/**
 * Stores data about a prism surrounding two
 * blocks in the world. Used to store info
 * about the selector blocks for polys. Keeps 
 * track of color, x/y/z values, and rendering.
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class PointRectangle {

    protected PointContainer point;
    protected LineColor color = LineColor.POLYPOINT;

    public PointRectangle(PointContainer point) {
        this.point = point;
    }

    public PointRectangle(int x, int z) {
        this.point = new PointContainer(x, 0, z);
    }

    public void render(int min, int max) {
        double off = 0.03f;
        double x = point.getX();
        double z = point.getZ();

        RenderShapes.drawBox(color.getHidden(), x - off, min - off, z - off, x + 1 + off, max + 1 + off, z + 1 + off);
        RenderShapes.drawBox(color.getNormal(), x - off, min - off, z - off, x + 1 + off, max + 1 + off, z + 1 + off);
    }

    public PointContainer getPoint() {
        return point;
    }

    public void setPoint(PointContainer point) {
        this.point = point;
    }

    public LineColor getColor() {
        return color;
    }

    public void setColor(LineColor color) {
        this.color = color;
    }
}
