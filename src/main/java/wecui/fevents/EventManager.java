package wecui.fevents;

import wecui.InitializationFactory;
import wecui.WorldEditCUI;
import wecui.exception.InitializationException;

/**
 * This class doesn't actually need to exist, but it feels wrong to have this
 * part of the event call logic inside Event
 * 
 * @author lahwran
 */
public class EventManager implements InitializationFactory {

    protected WorldEditCUI controller;

    public EventManager(WorldEditCUI controller) {
        this.controller = controller;
    }
    
    /**
     * Call an event.
     * 
     * @param <TEvent> Event subclass
     * @param event Event to handle
     */
    public <TEvent extends Event<TEvent>> void callEvent(TEvent event) {
        HandlerList<TEvent> handlerlist = event.getHandlers();
        handlerlist.bake();

        Listener<TEvent>[][] handlers = handlerlist.handlers;
        int[] handlerids = handlerlist.handlerids;

        for (int arrayidx = 0; arrayidx < handlers.length; arrayidx++) {

            // if the order slot is even and the event has stopped propogating
            if (event.isCancelled() && (handlerids[arrayidx] & 1) == 0) {
                continue; // then don't call this order slot
            }
            for (int handler = 0; handler < handlers[arrayidx].length; handler++) {
                try {
                    handlers[arrayidx][handler].onEvent(event);
                } catch (Throwable t) {
                    System.err.println("Error while passing event " + event);
                    t.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize() throws InitializationException {
    }
}
