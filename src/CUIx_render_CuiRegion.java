
/**
 * Base class for the two types of regions: cuboid and poly.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public abstract class CUIx_render_CuiRegion {

    /**
     * Number of blocks in the region
     */
    protected int regionSize;

    public abstract void render();

    /**
     * @param id
     * @param x
     * @param y
     * @param z
     * @param regionSize
     */
    public abstract void setPoint(int id, int x, int y, int z, int regionSize);
}
