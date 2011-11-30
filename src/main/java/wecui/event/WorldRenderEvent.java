package wecui.event;

import wecui.WorldEditCUI;
import wecui.fevents.Event;
import wecui.fevents.HandlerList;

/**
 * Singleton event class triggered at world rendering
 * Called at each rendering tick
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class WorldRenderEvent extends Event<WorldRenderEvent> {

    protected WorldEditCUI controller;
    public static final HandlerList<WorldRenderEvent> handlers = new HandlerList<WorldRenderEvent>();
    public float partialTick;

    public WorldRenderEvent(WorldEditCUI controller) {
    }

    public void setPartialTick(float partialTick) {
        this.partialTick = partialTick;
    }

    @Override
    protected String getEventName() {
        return "WorldRenderEvent";
    }

    @Override
    protected HandlerList<WorldRenderEvent> getHandlers() {
        return handlers;
    }

}
