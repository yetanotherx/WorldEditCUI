package wecui.event;

import wecui.WorldEditCUI;
import wecui.fevents.Event;
import wecui.fevents.HandlerList;

/**
 * Events for chat messages to/from server
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class ChannelEvent extends Event<ChannelEvent> {

    protected WorldEditCUI controller;
    protected String message;
    public static final HandlerList<ChannelEvent> handlers = new HandlerList<ChannelEvent>();

    public ChannelEvent(WorldEditCUI controller, String message) {
        this.controller = controller;
        this.message = message;
    }

    @Override
    protected String getEventName() {
        return "ChannelEvent";
    }

    @Override
    protected HandlerList<ChannelEvent> getHandlers() {
        return handlers;
    }

    public String getMessage() {
        return this.message;
    }
}
