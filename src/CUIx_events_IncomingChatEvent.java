
/**
 * Events for incoming chat messages from server
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class CUIx_events_IncomingChatEvent extends CUIx_fevent_Event<CUIx_events_IncomingChatEvent> implements CUIx_fevent_Cancellable {

    /**
     * Message content
     */
    public String message;
    /**
     * List of event handlers
     */
    public static final CUIx_fevent_HandlerList<CUIx_events_IncomingChatEvent> handlers = new CUIx_fevent_HandlerList<CUIx_events_IncomingChatEvent>();

    public CUIx_events_IncomingChatEvent(String message) {
        this.message = message;
        CUIx.debug("chat message: " + message);
    }

    @Override
    protected String getEventName() {
        return "IncomingChatEvent";
    }

    @Override
    protected CUIx_fevent_HandlerList<CUIx_events_IncomingChatEvent> getHandlers() {
        return handlers;
    }

    /**
     * Cancels the event, preventing it from being handled.
     * @param cancelled 
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
