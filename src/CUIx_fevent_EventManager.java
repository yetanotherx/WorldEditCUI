/**
 * 
 */


/**
 * This class doesn't actually need to exist, but it feels wrong to have this
 * part of the event call logic inside Event
 * @author lahwran
 */
public class CUIx_fevent_EventManager {
    /**
     * Call an event.
     * 
     * @param <TEvent> Event subclass
     * @param event Event to handle
     */
    public static <TEvent extends CUIx_fevent_Event<TEvent>> void callEvent(TEvent event) {
        CUIx_fevent_HandlerList<TEvent> handlerlist = event.getHandlers();
        handlerlist.bake();

        CUIx_fevent_Listener<TEvent>[][] handlers = handlerlist.handlers;
        int[] handlerids = handlerlist.handlerids;

        for (int arrayidx=0; arrayidx<handlers.length; arrayidx++) {

            // if the order slot is even and the event has stopped propogating
            if (event.isCancelled() && (handlerids[arrayidx] & 1) == 0)
                continue; // then don't call this order slot

            for (int handler = 0; handler < handlers[arrayidx].length; handler++) {
                try {
                    handlers[arrayidx][handler].onEvent(event);
                } catch (Throwable t) {
                    System.err.println("Error while passing event "+event);
                    t.printStackTrace();
                }
            }
        }
    }
}
