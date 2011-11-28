package cuix.event;


import cuix.fevents.Event;
import cuix.fevents.HandlerList;
import cuix.CUIx;


/**
 * CUI communication event
 * Called when a CUIEvent is called from the server.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class CUIEvent extends Event<CUIEvent> {

    /**
     * Event type
     */
    public String type;
    /**
     * Parameters
     */
    public String[] params;
    /**
     * Whether or not this event has been successfully handled
     */
    private boolean handled = false;
    /**
     * List of event handlers
     */
    public static final HandlerList<CUIEvent> handlers = new HandlerList<CUIEvent>();

    /**
     * Initialize event
     * @param type
     * @param params 
     */
    public CUIEvent(String type, String[] params) {
        this.type = type;
        this.params = params;
        String debugmsg = "CUI event: " + type + " ";
        for (int i = 0; i < params.length; i++) {
            debugmsg += params[i] + " ";
        }
        CUIx.getInstance().getDebugger().debug(debugmsg);
    }

    @Override
    protected String getEventName() {
        return "CUIEvent";
    }

    @Override
    protected HandlerList<CUIEvent> getHandlers() {
        return handlers;
    }

    /**
     * Sets the state of the event
     * @param handled 
     */
    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    /**
     * Called upon receiving an invalid event.
     * Debugs an error message and handles the event.
     * 
     * @param reason Error message
     */
    public void markInvalid(String reason) {
        String debugmsg = "WARNING - INVALID WECUIEvent " + type;
        for (int i = 0; i < params.length; i++) {
            debugmsg += "|" + params[i];
        }
        debugmsg += " because " + reason;
        CUIx.getInstance().getDebugger().debug(debugmsg);
        setHandled(true);
    }

    /**
     * Returns the float value of a certain parameter
     * @param index Parameter to retrieve
     * @return 
     */
    public float getFloat(int index) {
        return Float.parseFloat(params[index]);
    }

    /**
     * Returns the integer value of a certain parameter
     * @param index Parameter to retrieve
     * @return 
     */
    public int getInt(int index) {
        return (int) Float.parseFloat(params[index]);
    }

    public boolean isHandled() {
        return handled;
    }

    public boolean isCancelled() {
        return isHandled();
    }
}
