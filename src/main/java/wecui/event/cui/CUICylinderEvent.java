package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when ellipsoid event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUICylinderEvent extends CUIBaseEvent {

    public CUICylinderEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.CYLINDER;
    }

    @Override
    public String run() {

        int x = this.getInt(0);
        int y = this.getInt(1);
        int z = this.getInt(2);
        double radius = this.getDouble(3);
        
        controller.getSelection().setCylinderCenter(x, y, z);
        controller.getSelection().setCylinderRadius(radius);

        controller.getDebugger().debug("Setting center/radius");

        return null;
    }
}
