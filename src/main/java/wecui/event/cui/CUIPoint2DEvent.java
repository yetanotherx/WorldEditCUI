package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when poly point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIPoint2DEvent extends CUIPointEvent {

    public CUIPoint2DEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.POINT2D;
    }

    @Override
    public String run() {
        
        int id = this.getInt(0);
        int x = this.getInt(1);
        int z = this.getInt(2);
        int regionSize = this.getInt(3);
        controller.getSelection().setPolygonPoint(id, x, z);
        
        controller.getDebugger().debug("Setting point2d #" + id);
        
        return null;
    }
}
