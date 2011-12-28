package wecui.render.region;

import wecui.WorldEditCUI;
import wecui.render.LineColor;
import wecui.render.RenderShapes;
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
            center.render();
            
            RenderShapes.drawEllipsoidAroundPoint(LineColor.ELLIPSOIDGRID, center, radii);
            
        }
        else if( center != null ) {
            center.render();
        }
    }

    @Override
    public void setSphereCenter(int x, int y, int z) {
        center = new PointCube(x, y, z);
        center.setColor(LineColor.ELLIPSOIDCENTER);
    }

    @Override
    public void setSphereRadius(int x, int y, int z) {
        radii = new PointContainer(x, y, z);
    }

    @Override
    public RegionType getType() {
        return RegionType.ELLIPSOID;
    }
    
}
