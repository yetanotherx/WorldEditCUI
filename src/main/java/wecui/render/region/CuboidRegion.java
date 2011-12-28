package wecui.render.region;

import wecui.WorldEditCUI;
import wecui.render.LineColor;
import wecui.render.shapes.Render3DBox;
import wecui.render.points.PointContainer;
import wecui.render.points.PointCube;
import wecui.render.shapes.Render3DGrid;

/**
 * Main controller for a cuboid-type region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class CuboidRegion extends BaseRegion {

    protected PointCube firstPoint;
    protected PointCube secondPoint;

    public CuboidRegion(WorldEditCUI controller) {
        super(controller);
    }

    @Override
    public void render() {
        if (firstPoint != null && secondPoint != null) {
            firstPoint.render();
            secondPoint.render();

            PointContainer[] bounds = this.calcBounds();
            new Render3DBox(LineColor.CUBOIDBOX, bounds[0], bounds[1]).render();
            new Render3DGrid(LineColor.CUBOIDGRID, bounds[0], bounds[1]).render();

        } else if (firstPoint != null) {
            firstPoint.render();
        } else if (secondPoint != null) {
            secondPoint.render();
        }
    }

    @Override
    public void setCuboidPoint(int id, int x, int y, int z) {
        if (id == 0) {
            firstPoint = new PointCube(x, y, z);
            firstPoint.setColor(LineColor.CUBOIDPOINT1);
        } else {
            secondPoint = new PointCube(x, y, z);
            secondPoint.setColor(LineColor.CUBOIDPOINT2);
        }
    }

    protected PointContainer[] calcBounds() {
        double off = 0.02;
        double off1 = 1 + off;

        PointContainer[] out = new PointContainer[2];
        out[0] = new PointContainer(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        out[1] = new PointContainer(-Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE);

        for (PointCube point : new PointCube[]{firstPoint, secondPoint}) {
            if (point.getPoint().getX() + off1 > out[1].getX()) {
                out[1].setX(point.getPoint().getX() + off1);
            }

            if (point.getPoint().getX() - off < out[0].getX()) {
                out[0].setX(point.getPoint().getX() - off);
            }

            if (point.getPoint().getY() + off1 > out[1].getY()) {
                out[1].setY(point.getPoint().getY() + off1);
            }

            if (point.getPoint().getY() - off < out[0].getY()) {
                out[0].setY(point.getPoint().getY() - off);
            }

            if (point.getPoint().getZ() + off1 > out[1].getZ()) {
                out[1].setZ(point.getPoint().getZ() + off1);
            }

            if (point.getPoint().getZ() - off < out[0].getZ()) {
                out[0].setZ(point.getPoint().getZ() - off);
            }
        }
        
        return out;
    }

    @Override
    public RegionType getType() {
        return RegionType.CUBOID;
    }
}
