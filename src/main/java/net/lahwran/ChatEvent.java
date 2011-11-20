/**
 * 
 */
package net.lahwran;

import net.lahwran.fevents.Cancellable;
import net.lahwran.fevents.Event;
import net.lahwran.fevents.HandlerList;
import net.lahwran.fevents.Order;
import net.lahwran.wecui.WorldEditCUI;

/**
 * @author lahwran
 *
 */
public class ChatEvent extends Event<ChatEvent> implements Cancellable {

    public String chat;

    public ChatEvent(String chat) {
        this.chat = chat;
        WorldEditCUI.debug("chat message: " + chat);
    }

    @Override
    protected String getEventName() {
        return "ChatEvent";
    }
    public static final HandlerList<ChatEvent> handlers = new HandlerList<ChatEvent>();
    @Override
    protected HandlerList<ChatEvent> getHandlers() {
        return handlers;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
