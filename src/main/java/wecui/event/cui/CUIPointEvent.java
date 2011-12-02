package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIPointEvent extends CUIBaseEvent {

    public CUIPointEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.POINT;
    }

    @Override
    public String run() {
        int id = this.getInt(0);
        int x = this.getInt(1);
        int y = this.getInt(2);
        int z = this.getInt(3);
        int regionSize = this.getInt(4);
        controller.getSelection().setPoint(id, x, y, z, regionSize);

        controller.getDebugger().debug("Setting point #" + id);

        return null;
    }
}
