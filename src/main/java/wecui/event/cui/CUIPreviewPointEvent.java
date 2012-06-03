package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when preview points event is received
 * 
 * @author yetanotherx
 */
public class CUIPreviewPointEvent extends CUIBaseEvent {

    public CUIPreviewPointEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.PREVIEW;
    }

    @Override
    public String run() {
        controller.getDebugger().debug("Setting preview points.");
        
        int x = this.getInt(0);
        int y = this.getInt(1);
        int z = this.getInt(2);
        int id = this.getInt(3);
        int data = 0;
        
        if( this.length() > 4 ) {
            data = this.getInt(4);
        }
        
        if( x == -1 && y == -1 && z == -1 ) {
            controller.getPreview().clear();
            return null;
        }
        controller.getPreview().setPoint(x, y, z, id, data);
        
        return null;
    }
}
