package wecui.event.cui;

import wecui.WorldEditCUI;
import wecui.render.CuboidRegion;
import wecui.render.Polygon2DRegion;

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

        if (this.getString(0).equals("cuboid")) {
            controller.setSelection(new CuboidRegion());
        } else if (this.getString(0).equals("polygon2d")) {
            controller.setSelection(new Polygon2DRegion());
        } else {
            return "Invalid selection type. Must be either cuboid or polygon2d.";
        }
        WorldEditCUI.getDebugger().debug("Received selection event, initalizing new region instance.");

        return null;
    }
}
