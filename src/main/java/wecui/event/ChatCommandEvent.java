package wecui.event;

import java.util.HashMap;

import wecui.WorldEditCUI;
import wecui.fevents.Cancellable;
import wecui.fevents.Event;
import wecui.fevents.HandlerList;

/**
 * Events for outgoing commands to server. 
 * Handlers can be registered to a certain command.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class ChatCommandEvent extends Event<ChatCommandEvent> implements Cancellable {

    protected WorldEditCUI controller;
    protected String rawMessage;
    protected String command;
    protected String[] args;
    protected boolean handled;
    protected static final HashMap<String, HandlerList<ChatCommandEvent>> allhandlers =
            new HashMap<String, HandlerList<ChatCommandEvent>>();
    protected static final HandlerList<ChatCommandEvent> defaulthandlers =
            new HandlerList<ChatCommandEvent>();

    public ChatCommandEvent(WorldEditCUI controller, String chat) {
        this.controller = controller;
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
        HandlerList<ChatCommandEvent> handlers = allhandlers.get(this.command);
        if (handlers == null) {
            handlers = defaulthandlers;
        }
        return handlers;
    }

    public void markHandled() {
        this.handled = true;
    }

    public boolean isHandled() {
        return this.handled;
    }

    public String[] getArgs() {
        return this.args;
    }

    public String getCommand() {
        return this.command;
    }

    public String getRawMessage() {
        return this.rawMessage;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
	public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}