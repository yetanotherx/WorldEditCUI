package wecui.render.region;

import wecui.WorldEditCUI;
import wecui.render.LineColor;
import wecui.render.points.PointCube;
import wecui.render.shapes.RenderEllipsoid;
import wecui.util.Vector3;

/**
 * Main controller for a ellipsoid-type region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class EllipsoidRegion extends BaseRegion {

    protected PointCube center;
    protected Vector3 radii;
    
    public EllipsoidRegion(WorldEditCUI controller) {
        super(controller);
    }

    @Override
    public void render() {
        if( this.center != null && this.radii != null ) {
            this.center.render();
            
            new RenderEllipsoid(LineColor.ELLIPSOIDGRID, this.center, this.radii).render();
            
        }
        else if( this.center != null ) {
            this.center.render();
        }
    }

    @Override
    public void setEllipsoidCenter(int x, int y, int z) {
        this.center = new PointCube(x, y, z);
        this.center.setColor(LineColor.ELLIPSOIDCENTER);
    }

    @Override
    public void setEllipsoidRadii(double x, double y, double z) {
        this.radii = new Vector3(x, y, z);
    }

    @Override
    public RegionType getType() {
        return RegionType.ELLIPSOID;
    }
    
}
