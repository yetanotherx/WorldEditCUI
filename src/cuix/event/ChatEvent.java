package cuix.event;

import cuix.fevents.Cancellable;
import cuix.fevents.Event;
import cuix.fevents.HandlerList;

/**
 * Events for chat messages to/from server
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class ChatEvent extends Event<ChatEvent> implements Cancellable {

    public String message;
    public final Direction direction;
    public static final HandlerList<ChatEvent> handlers = new HandlerList<ChatEvent>();

    public ChatEvent(String message, Direction direction) {
        this.message = message;
        this.direction = direction;
    }

    @Override
    protected String getEventName() {
        return "IncomingChatEvent";
    }

    @Override
    protected HandlerList<ChatEvent> getHandlers() {
        return handlers;
    }

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
