
import java.util.HashMap;

/**
 * Events for outgoing commands to server
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class CUIx_events_ChatCommandEvent extends CUIx_fevent_Event<CUIx_events_ChatCommandEvent> {

    /**
     * Raw message, with /command
     */
    public final String chat;
    
    /**
     * /command name
     */
    public final String command;
    
    /**
     * Arguments given
     */
    public final String[] args;
    private boolean handled;
    
    public static final HashMap<String, CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent>> allhandlers =
            new HashMap<String, CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent>>();
    public static final CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent> defaulthandlers =
            new CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent>();

    public CUIx_events_ChatCommandEvent(String chat) {
        this.chat = chat;
        int firstspace = chat.indexOf(' ');
        if (firstspace < 0) {
            this.command = chat.substring(1);
            this.args = new String[0];
        } else {
            this.command = chat.substring(1, firstspace);
            if (chat.length() - firstspace > 0) {
                this.args = chat.substring(firstspace + 1).split(" ");
            } else {
                this.args = new String[0];
            }
        }
    }

    @Override
    protected String getEventName() {
        return "ChatCommandEvent";
    }
    
    public static CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent> getHandlers(String command) {
        CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent> handlers = allhandlers.get(command);
        if (handlers == null) {
            handlers = new CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent>();
            allhandlers.put(command, handlers);
        }
        return handlers;
    }

    @Override
    protected CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent> getHandlers() {
        CUIx_fevent_HandlerList<CUIx_events_ChatCommandEvent> handlers = allhandlers.get(command);
        if (handlers == null) {
            handlers = defaulthandlers;
        }
        return handlers;
    }

    public void markHandled() {
        handled = true;
    }

    public boolean isHandled() {
        return handled;
    }

    public boolean isCancelled() {
        return isHandled();
    }
}