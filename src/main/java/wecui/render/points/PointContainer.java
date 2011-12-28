package wecui.render.points;

/**
 * Stores the coordinates of a point in the world.
 * 
 * @author yetanotherx
 */
public class PointContainer {

    protected Double x;
    protected Double y;
    protected Double z;

    public PointContainer(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public PointContainer(Integer x, Integer y, Integer z) {
        this.x = x.doubleValue();
        this.y = y.doubleValue();
        this.z = z.doubleValue();
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

}
