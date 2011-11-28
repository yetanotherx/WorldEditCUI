package cuix.event;


import cuix.fevents.Event;
import cuix.fevents.HandlerList;


/**
 * Singleton event class triggered at world rendering
 * Called at each rendering tick
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class WorldRenderEvent extends Event<WorldRenderEvent> {

    /**
     * List of event handlers
     */
    public static final HandlerList<WorldRenderEvent> handlers = new HandlerList<WorldRenderEvent>();
    /**
     * Event instance (singleton)
     */
    private static final WorldRenderEvent instance = new WorldRenderEvent();
    /**
     * Rendering rick
     */
    public float partialTick;

    /**
     * Protected constructor to enforce singleton
     */
    protected WorldRenderEvent() {
    }

    @Override
    protected String getEventName() {
        return "WorldRenderEvent";
    }

    @Override
    protected HandlerList<WorldRenderEvent> getHandlers() {
        return handlers;
    }

    /**
     * Sets the rendering tick and returns the instance
     * 
     * @param partialTick
     * @return 
     */
    public static WorldRenderEvent setTickAndGetInstance(float partialTick) {
        instance.partialTick = partialTick;
        return instance;
    }
}
