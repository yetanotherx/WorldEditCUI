/**
 * 
 */
package net.lahwran;

import net.lahwran.fevents.Event;
import net.lahwran.fevents.HandlerList;

/**
 * @author lahwran
 *
 */
public class WorldRenderEvent extends Event<WorldRenderEvent> {

    private static final WorldRenderEvent singleton = new WorldRenderEvent();

    public float partialTick;

    public static WorldRenderEvent update(float partialTick) {
        singleton.partialTick = partialTick;
        return singleton;
    }

    protected WorldRenderEvent() {
        // makes it protected
    }

    @Override
    protected String getEventName() {
        return "Worldedit CUI Communication event";
    }

    public static final HandlerList<WorldRenderEvent> handlers = new HandlerList<WorldRenderEvent>();
    @Override
    protected HandlerList<WorldRenderEvent> getHandlers() {
        return handlers;
    }
}
