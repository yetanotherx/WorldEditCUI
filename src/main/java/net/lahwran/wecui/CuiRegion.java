/**
 * 
 */
package net.lahwran.wecui;

/**
 * @author lahwran
 *
 */
public abstract class CuiRegion {

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
