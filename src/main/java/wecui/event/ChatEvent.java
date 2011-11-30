package wecui.event;

import wecui.WorldEditCUI;
import wecui.fevents.Cancellable;
import wecui.fevents.Event;
import wecui.fevents.HandlerList;

/**
 * Events for chat messages to/from server
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class ChatEvent extends Event<ChatEvent> implements Cancellable {

    protected WorldEditCUI controller;
    protected String message;
    protected final Direction direction;
    public static final HandlerList<ChatEvent> handlers = new HandlerList<ChatEvent>();

    public ChatEvent(WorldEditCUI controller, String message, Direction direction) {
        this.controller = controller;
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

    public Direction getDirection() {
        return direction;
    }

    public String getMessage() {
        return message;
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
