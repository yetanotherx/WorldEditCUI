package wecui.render.points;

import wecui.render.LineColor;
import wecui.render.shapes.Render3DBox;
import wecui.render.shapes.Render3DQuad;
import wecui.util.Vector3;

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

    protected Vector3 point;
    protected LineColor color = LineColor.CUBOIDPOINT1;
    protected LineColor sideColor;

    public PointCube(Vector3 point) {
        this.point = point;
    }

    public PointCube(int x, int y, int z) {
        this.point = new Vector3(x, y, z);
    }
    
    public PointCube(double x, double y, double z) {
        this.point = new Vector3(x, y, z);
    }

    public void render() {
        double off = 0.03f;
        Vector3 minVec = new Vector3(off, off, off);
        Vector3 maxVec = new Vector3(off + 1, off + 1, off + 1);

        new Render3DBox(color, point.subtract(minVec), point.add(maxVec)).render();
        
        if( this.sideColor != null ) {
            new Render3DQuad(sideColor, point.subtract(minVec), point.add(maxVec)).render();
        }
    }

    public Vector3 getPoint() {
        return point;
    }

    public void setPoint(Vector3 point) {
        this.point = point;
    }

    public LineColor getColor() {
        return color;
    }

    public void setColor(LineColor color) {
        this.color = color;
    }

    public LineColor getSideColor() {
        return sideColor;
    }

    public void setSideColor(LineColor sideColor) {
        this.sideColor = sideColor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PointCube other = (PointCube) obj;
        if (this.point != other.point && (this.point == null || !this.point.equals(other.point))) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.point != null ? this.point.hashCode() : 0);
        hash = 83 * hash + (this.color != null ? this.color.hashCode() : 0);
        return hash;
    }
}
