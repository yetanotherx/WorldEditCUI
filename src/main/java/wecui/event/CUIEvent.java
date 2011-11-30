package wecui.event;

import wecui.fevents.Event;
import wecui.fevents.HandlerList;
import wecui.WorldEditCUI;

/**
 * CUI communication event
 * Called when a CUI event is sent from the server.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class CUIEvent extends Event<CUIEvent> {

    public String type;
    public String[] params;
    private boolean handled = false;
    public static final HandlerList<CUIEvent> handlers = new HandlerList<CUIEvent>();

    public CUIEvent(String type, String[] params) {
        this.type = type;
        this.params = params;
        String debugmsg = "CUI event: " + type + " ";
        for (int i = 0; i < params.length; i++) {
            debugmsg += params[i] + " ";
        }
        WorldEditCUI.getDebugger().debug(debugmsg);
    }

    @Override
    protected String getEventName() {
        return "CUIEvent";
    }

    @Override
    protected HandlerList<CUIEvent> getHandlers() {
        return handlers;
    }

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
        WorldEditCUI.getDebugger().debug(debugmsg);
        setHandled(true);
    }

    public int getInt(int index) {
        return (int) Float.parseFloat(params[index]);
    }
    
    public String getString(int index) {
        return params[index];
    }

    public boolean isHandled() {
        return handled;
    }

    public boolean isCancelled() {
        return isHandled();
    }
}
