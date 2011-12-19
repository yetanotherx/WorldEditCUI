package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when version event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIVersionEvent extends CUIBaseEvent {
    
    public CUIVersionEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }
    
    @Override
    public CUIEventType getEventType() {
        return CUIEventType.VERSION;
    }
    
    @Override
    public String run() {
        return controller.getLocalPlugin().onVersionEvent(this.getString(0));
    }
}
