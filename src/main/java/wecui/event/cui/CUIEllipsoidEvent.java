package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when ellipsoid event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEllipsoidEvent extends CUIPointEvent {

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
        int x = this.getInt(1);
        int y = this.getInt(2);
        int z = this.getInt(3);

        if (id == 0) {
            controller.getSelection().setSphereCenter(x, y, z);
        } else {
            controller.getSelection().setSphereRadius(x, y, z);
        }

        controller.getDebugger().debug("Setting center/radius");

        return null;
    }
}
