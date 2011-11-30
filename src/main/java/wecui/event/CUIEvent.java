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

    protected WorldEditCUI controller;
    protected String type;
    protected String[] params;
    protected boolean handled = false;
    public static final HandlerList<CUIEvent> handlers = new HandlerList<CUIEvent>();

    public CUIEvent(WorldEditCUI controller, String type, String[] params) {
        this.controller = controller;
        this.type = type;
        
        if (params.length == 1 && params[0].length() == 0) {
            params = new String[]{};
        }
        
        this.params = params;
        String debugmsg = "CUI event: " + type + " ";
        for (int i = 0; i < params.length; i++) {
            debugmsg += params[i] + " ";
        }
        this.controller.getDebugger().debug(debugmsg);
        
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
        this.controller.getDebugger().debug(debugmsg);
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

    public String[] getParams() {
        return params;
    }

    public String getType() {
        return type;
    }
    
}
