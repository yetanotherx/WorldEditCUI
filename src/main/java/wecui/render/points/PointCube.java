package wecui.render.points;

import wecui.render.LineColor;
import wecui.render.shapes.Render3DBox;

/**
 * Stores data about a cube surrounding a
 * block in the world. Used to store info
 * about the selector blocks. Keeps track
 * of color, x/y/z values, and rendering.
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class PointCube {

    protected PointContainer point;
    protected LineColor color = LineColor.CUBOIDPOINT1;

    public PointCube(PointContainer point) {
        this.point = point;
    }

    public PointCube(int x, int y, int z) {
        this.point = new PointContainer(x, y, z);
    }

    public void render() {
        double off = 0.03f;
        double x = point.getX();
        double y = point.getY();
        double z = point.getZ();

        new Render3DBox(color, new PointContainer(x - off, y - off, z - off), new PointContainer(x + 1 + off, y + 1 + off, z + 1 + off)).render();
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
