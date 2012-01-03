package wecui.render.region;

import wecui.WorldEditCUI;
import wecui.render.LineColor;
import wecui.render.shapes.RenderEllipsoid;
import wecui.render.points.PointContainer;
import wecui.render.points.PointCube;

/**
 * Main controller for a ellipsoid-type region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class EllipsoidRegion extends BaseRegion {

    protected PointCube center;
    protected PointContainer radii;
    
    public EllipsoidRegion(WorldEditCUI controller) {
        super(controller);
    }

    @Override
    public void render() {
        if( center != null && radii != null ) {
            if( radii.getX() != 0 && radii.getY() != 0 || radii.getZ() != 0 ) {
                center.render();
            }
            
            new RenderEllipsoid(LineColor.ELLIPSOIDGRID, center, radii).render();
            
        }
        else if( center != null ) {
            center.render();
        }
    }

    @Override
    public void setEllipsoidCenter(int x, int y, int z) {
        center = new PointCube(x, y, z);
        center.setColor(LineColor.ELLIPSOIDCENTER);
    }

    @Override
    public void setEllipsoidRadii(double x, double y, double z) {
        radii = new PointContainer(x, y, z);
    }

    @Override
    public RegionType getType() {
        return RegionType.ELLIPSOID;
    }
    
}
