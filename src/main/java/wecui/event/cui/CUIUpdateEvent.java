package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when update event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIUpdateEvent extends CUIBaseEvent {

    public CUIUpdateEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.UPDATE;
    }

    @Override
    public String run() {
        return null;
    }
}
