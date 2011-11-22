
/**
 * Events for chat messages to/from server
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class CUIx_events_ChatEvent extends CUIx_fevent_Event<CUIx_events_ChatEvent> implements CUIx_fevent_Cancellable {

    /**
     * Message content
     */
    public String message;
    /**
     * Direction of chat
     */
    public final Direction direction;
    /**
     * List of event handlers
     */
    public static final CUIx_fevent_HandlerList<CUIx_events_ChatEvent> handlers = new CUIx_fevent_HandlerList<CUIx_events_ChatEvent>();

    public CUIx_events_ChatEvent(String message, Direction direction) {
        this.message = message;
        this.direction = direction;
    }

    @Override
    protected String getEventName() {
        return "IncomingChatEvent";
    }

    @Override
    protected CUIx_fevent_HandlerList<CUIx_events_ChatEvent> getHandlers() {
        return handlers;
    }

    /**
     * Cancels the event, preventing it from being handled.
     * @param cancelled 
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public static enum Direction {
        INCOMING,
        OUTGOING;
        public String toString() {
            return name().toLowerCase();
        }
    }
}
