
/**
 * Base class for the two types of regions: cuboid and poly.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public abstract class CUIx_render_CuiRegion {

    /**
     * size of the region
     */
    protected int regionSize;

    /**
     * render the region lines and such
     */
    public abstract void render();

    /**
     * Set a point location and the region size. Called from the 'p' handler.
     * @param id
     * @param x
     * @param y
     * @param z
     * @param regionSize
     */
    public void setPoint(int id, int x, int y, int z, int regionSize) {};

    /**
     * Set a point location and the region size. Called from the 'p2' handler.
     * @param id
     * @param x
     * @param y
     * @param z
     * @param regionSize
     */
    public void setPoint(int id, int x, int z, int regionSize) {};

    public void setMinMax(int min, int max) {};
}