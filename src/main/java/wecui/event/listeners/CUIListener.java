package wecui.event.listeners;

import wecui.vendor.org.joor.Reflect;
import wecui.fevents.Listener;
import wecui.WorldEditCUI;
import wecui.event.CUIEvent;
import wecui.event.cui.CUIBaseEvent;
import wecui.event.cui.CUIEventType;
import wecui.vendor.org.joor.ReflectException;

/**
 * Listener class for CUIEvent
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIListener implements Listener<CUIEvent> {

    protected WorldEditCUI controller;

    public CUIListener(WorldEditCUI controller) {
        this.controller = controller;
    }

    public void onEvent(CUIEvent event) {

        //Get a CUIEventType enum value from the first section of the CUI message
        CUIEventType eventType = CUIEventType.getTypeFromKey(event.getType());
        if (eventType == null || eventType.getEventClass() == null) {
            event.markInvalid("Unknown CUIEvent identifier.");
        }

        try {
            CUIBaseEvent newEvent = Reflect.on(eventType.getEventClass()).create(this.controller, event.getParams()).get();

            //Run the event. If doRun returns null, the event was successful. 
            //If it returns a string, it uses that as the error message.
            String result = newEvent.doRun();
            if (result != null) {
                event.markInvalid(result);
            } else {
                event.setHandled(true);
            }
        } catch (ReflectException e) {
            return;
        }
    }
}
