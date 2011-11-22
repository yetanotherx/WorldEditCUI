
/**
 * Singleton event class triggered at world rendering
 * Called at each rendering tick
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIx_events_WorldRenderEvent extends CUIx_fevent_Event<CUIx_events_WorldRenderEvent> {

    /**
     * List of event handlers
     */
    public static final CUIx_fevent_HandlerList<CUIx_events_WorldRenderEvent> handlers = new CUIx_fevent_HandlerList<CUIx_events_WorldRenderEvent>();
    /**
     * Event instance (singleton)
     */
    private static final CUIx_events_WorldRenderEvent instance = new CUIx_events_WorldRenderEvent();
    /**
     * Rendering rick
     */
    public float partialTick;

    /**
     * Protected constructor to enforce singleton
     */
    protected CUIx_events_WorldRenderEvent() {
    }

    @Override
    protected String getEventName() {
        return "WorldRenderEvent";
    }

    @Override
    protected CUIx_fevent_HandlerList<CUIx_events_WorldRenderEvent> getHandlers() {
        return handlers;
    }

    /**
     * Sets the rendering tick and returns the instance
     * 
     * @param partialTick
     * @return 
     */
    public static CUIx_events_WorldRenderEvent setTickAndGetInstance(float partialTick) {
        instance.partialTick = partialTick;
        return instance;
    }
}
