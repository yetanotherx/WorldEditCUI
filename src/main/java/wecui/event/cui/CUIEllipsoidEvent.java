package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when ellipsoid event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEllipsoidEvent extends CUIBaseEvent {

    public CUIEllipsoidEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.ELLIPSOID;
    }

    @Override
    public String run() {

        int id = this.getInt(0);

        if (id == 0) {
            int x = this.getInt(1);
            int y = this.getInt(2);
            int z = this.getInt(3);
            controller.getSelection().setEllipsoidCenter(x, y, z);
        } else if (id == 1) {
            double x = this.getDouble(1);
            double y = this.getDouble(2);
            double z = this.getDouble(3);
            controller.getSelection().setEllipsoidRadii(x, y, z);
        }

        controller.getDebugger().debug("Setting center/radius");

        return null;
    }
}
