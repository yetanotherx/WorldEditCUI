package wecui.render.region;

import wecui.WorldEditCUI;
import wecui.render.LineColor;
import wecui.render.points.PointCube;
import wecui.render.shapes.RenderCylinder;

/**
 * Main controller for a cylinder-type region
 * 
 * @author yetanotherx
 */
public class CylinderRegion extends BaseRegion {

    protected PointCube center;
    protected double radius = 0;
    protected int minY = 0;
    protected int maxY = 0;
    
    public CylinderRegion(WorldEditCUI controller) {
        super(controller);
    }

    @Override
    public void render() {
        if( center != null && radius != 0 ) {
            center.render();
            
            if( minY == 0 || maxY == 0 ) {
                new RenderCylinder(LineColor.CYLINDERGRID, center, radius, center.getPoint().getY().intValue(), center.getPoint().getY().intValue()).render();
            }
            else {
                new RenderCylinder(LineColor.CYLINDERGRID, center, radius, minY, maxY).render();
            }
            
        }
        else if( center != null ) {
            center.render();
        }
    }

    @Override
    public void setCylinderCenter(int x, int y, int z) {
        center = new PointCube(x, y, z);
        center.setColor(LineColor.CYLINDERCENTER);
    }

    @Override
    public void setCylinderRadius(double radius) {
        this.radius = radius;
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
