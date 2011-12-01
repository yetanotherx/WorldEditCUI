package wecui.event;

import wecui.WorldEditCUI;
import wecui.fevents.Event;
import wecui.fevents.HandlerList;

/**
 * Event class triggered at world rendering, called at each rendering tick.
 * Only one instance of this class should exist, to save processing time.
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class WorldRenderEvent extends Event<WorldRenderEvent> {

    protected WorldEditCUI controller;
    protected float partialTick;
    public static final HandlerList<WorldRenderEvent> handlers = new HandlerList<WorldRenderEvent>();

    public WorldRenderEvent(WorldEditCUI controller) {
    }

    public void setPartialTick(float partialTick) {
        this.partialTick = partialTick;
    }

    public float getPartialTick() {
        return partialTick;
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
