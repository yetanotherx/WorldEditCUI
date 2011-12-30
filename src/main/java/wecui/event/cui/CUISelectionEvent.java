package wecui.event.cui;

import wecui.WorldEditCUI;
import wecui.render.region.BaseRegion;
import wecui.render.region.CuboidRegion;
import wecui.render.region.CylinderRegion;
import wecui.render.region.EllipsoidRegion;
import wecui.render.region.PolygonRegion;

/**
 * Called when selection event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUISelectionEvent extends CUIBaseEvent {

    public CUISelectionEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.SELECTION;
    }

    @Override
    public String run() {

        BaseRegion newRegion = null;

        if (this.getString(0).equals("cuboid")) {
            newRegion = new CuboidRegion(controller);
        } else if (this.getString(0).equals("polygon2d")) {
            newRegion = new PolygonRegion(controller);
        } else if (this.getString(0).equals("ellipsoid")) {
            newRegion = new EllipsoidRegion(controller);
        } else if (this.getString(0).equals("cylinder")) {
            newRegion = new CylinderRegion(controller);
        } else {
            return "Invalid selection type. Must be cuboid|polygon2d|ellipsoid|cylinder.";
        }

        controller.setSelection(newRegion);
        controller.getDebugger().debug("Received selection event, initalizing new region instance.");

        return null;
    }
}
