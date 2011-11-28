package cuix.event;

import cuix.fevents.Event;
import cuix.fevents.HandlerList;
import java.util.HashMap;

/**
 * Events for outgoing commands to server
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class ChatCommandEvent extends Event<ChatCommandEvent> {

    protected final String rawMessage;
    protected final String command;
    protected final String[] args;
    protected boolean handled;
    protected static final HashMap<String, HandlerList<ChatCommandEvent>> allhandlers =
            new HashMap<String, HandlerList<ChatCommandEvent>>();
    protected static final HandlerList<ChatCommandEvent> defaulthandlers =
            new HandlerList<ChatCommandEvent>();

    public ChatCommandEvent(String chat) {

        this.rawMessage = chat;

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

    public static HandlerList<ChatCommandEvent> getHandlers(String command) {
        HandlerList<ChatCommandEvent> handlers = allhandlers.get(command);
        if (handlers == null) {
            handlers = new HandlerList<ChatCommandEvent>();
            allhandlers.put(command, handlers);
        }
        return handlers;
    }

    @Override
    protected HandlerList<ChatCommandEvent> getHandlers() {
        HandlerList<ChatCommandEvent> handlers = allhandlers.get(command);
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