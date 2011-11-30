package wecui.event.cui;

import wecui.WorldEditCUI;

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
