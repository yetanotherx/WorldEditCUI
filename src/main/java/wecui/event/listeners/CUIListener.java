package wecui.event.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import wecui.fevents.Listener;
import wecui.WorldEditCUI;
import wecui.event.CUIEvent;
import wecui.event.cui.CUIBaseEvent;
import wecui.event.cui.CUIEventType;

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
        try {
            
            //Get a CUIEventType enum value from the first section of the CUI message
            CUIEventType eventType = CUIEventType.getTypeFromKey(event.getType());
            if (eventType == null) {
                event.markInvalid("Unknown CUIEvent identifier.");
            }

            Constructor[] constructors = eventType.getEventClass().getDeclaredConstructors();
            if (constructors == null || constructors.length == 0) {
                return;
            }
            CUIBaseEvent newEvent = (CUIBaseEvent) constructors[0].newInstance(this.controller, event.getParams());

            //Run the event. If doRun returns null, the event was successful. 
            //If it returns a string, it uses that as the error message.
            String result = newEvent.doRun();
            if (result != null) {
                event.markInvalid(result);
            } else {
                event.setHandled(true);
            }

        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (IllegalArgumentException ex) {
        } catch (InvocationTargetException ex) {
        }
    }
}
