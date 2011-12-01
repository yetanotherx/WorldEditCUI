package wecui.event.command;

import wecui.WorldEditCUI;
import wecui.fevents.Cancellable;

public abstract class CommandEventBase implements Cancellable {

    protected WorldEditCUI controller;
    protected String[] args;
    protected boolean cancelled = false;

    public CommandEventBase(WorldEditCUI controller, String[] args) {
        this.controller = controller;
        this.args = args;
    }

    public abstract String getCommand();

    public abstract void run();

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
}
