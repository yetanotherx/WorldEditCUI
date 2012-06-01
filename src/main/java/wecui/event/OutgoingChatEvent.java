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
public class OutgoingChatEvent extends Event<OutgoingChatEvent> implements Cancellable {

    protected WorldEditCUI controller;
    protected String message;
    public static final HandlerList<OutgoingChatEvent> handlers = new HandlerList<OutgoingChatEvent>();

    public OutgoingChatEvent(WorldEditCUI controller, String message) {
        this.controller = controller;
        this.message = message;
    }

    @Override
    protected String getEventName() {
        return "OutgoingChatEvent";
    }

    @Override
    protected HandlerList<OutgoingChatEvent> getHandlers() {
        return handlers;
    }

    public String getMessage() {
        return message;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
