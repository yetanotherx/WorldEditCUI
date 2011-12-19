package wecui.render;

import wecui.InitializationFactory;
import wecui.WorldEditCUI;

/**
 * Base class for the two types of regions: cuboid and poly.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public abstract class CUIRegion implements InitializationFactory {

    /**
     * Size of the region in blocks
     */
    protected int regionSize;
    protected WorldEditCUI controller;
    protected RenderShapes renderer;

    public CUIRegion(WorldEditCUI controller) {
        this.controller = controller;
        this.renderer = new RenderShapes(controller);
    }

    @Override
    public void initialize() {
    }
    
    /**
     * render the region lines and such
     */
    public abstract void render();

    /**
     * Set a point location and the region size. Called from the 'p' handler.
     * 
     * @param id
     * @param x
     * @param y
     * @param z
     * @param regionSize
     */
    public void setPoint(int id, int x, int y, int z, int regionSize) {
    }

    /**
     * Set a point location and the region size. Called from the 'p2' handler.
     * 
     * @param id
     * @param x
     * @param y
     * @param z
     * @param regionSize
     */
    public void setPoint(int id, int x, int z, int regionSize) {
    }

    /**
     * Set the minimum and maximum value of the selection
     * Currently only used by poly, as cuboid sends the
     * two new points rather than an expansion value
     * 
     * @param min
     * @param max 
     */
    public void setMinMax(int min, int max) {
    }
    
    public abstract Type getType();
    
    public static enum Type {
        POLY,
        CUBOID;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
        
    }
}