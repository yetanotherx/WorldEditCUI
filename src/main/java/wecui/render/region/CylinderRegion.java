package wecui.render.region;

import wecui.WorldEditCUI;
import wecui.render.LineColor;
import wecui.render.points.PointCube;
import wecui.render.shapes.RenderCylinderBox;
import wecui.render.shapes.RenderCylinderCircles;
import wecui.render.shapes.RenderCylinderGrid;

/**
 * Main controller for a cylinder-type region
 * 
 * @author yetanotherx
 */
public class CylinderRegion extends BaseRegion {

    protected PointCube center;
    protected double radX = 0;
    protected double radZ = 0;
    protected int minY = 0;
    protected int maxY = 0;

    public CylinderRegion(WorldEditCUI controller) {
        super(controller);
    }

    @Override
    public void render() {
        if (center != null) {
            center.render();

            int tMin = minY;
            int tMax = maxY;

            if (minY == 0 || maxY == 0) {
                tMin = center.getPoint().getY().intValue();
                tMax = center.getPoint().getY().intValue();
            }

            new RenderCylinderCircles(LineColor.CYLINDERGRID, center, radX, radZ, tMin, tMax).render();
            new RenderCylinderGrid(LineColor.CYLINDERGRID, center, radX, radZ, tMin, tMax).render();
            new RenderCylinderBox(LineColor.CYLINDERBOX, center, radX, radZ, tMin, tMax).render();

        }
    }

    @Override
    public void setCylinderCenter(int x, int y, int z) {
        center = new PointCube(x, y, z);
        center.setColor(LineColor.CYLINDERCENTER);
    }

    @Override
    public void setCylinderRadius(double x, double z) {
        this.radX = x;
        this.radZ = z;
    }

    @Override
    public void setMinMax(int min, int max) {
        minY = min;
        maxY = max;
    }

    @Override
    public RegionType getType() {
        return RegionType.CYLINDER;
    }
}
