package wecui.event;

import wecui.util.Utilities;
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

        //split("[|]" returns String[] {""} for some reason,  instead of String[] {}.
        if (params.length == 1 && params[0].length() == 0) {
            params = new String[]{};
        }

        this.params = params;
        this.controller.getDebugger().debug("CUI Event (" + type + ") - Params: " + Utilities.join(params, ", "));

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
        this.controller.getDebugger().debug("INVALID WECUIEvent " + type + " - " + Utilities.join(params, "|") + " - Reason: " + reason);
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
