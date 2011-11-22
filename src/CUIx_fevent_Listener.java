
/**
 * @author lahwran
 * @param <TEvent> Event type
 */
public interface CUIx_fevent_Listener<TEvent extends CUIx_fevent_Event<TEvent>> {

    /**
     * Handle an event
     * @param event Event to handle
     */
    public void onEvent(TEvent event);
}
