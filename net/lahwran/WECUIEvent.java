/**
 * 
 */
package net.lahwran;

import net.lahwran.fevents.Event;
import net.lahwran.fevents.HandlerList;
import net.lahwran.wecui.WorldEditCUI;

/**
 * @author lahwran
 *
 */
public class WECUIEvent extends Event<WECUIEvent> {

    public String type;
    public String[] params;
    private boolean handled = false;

    public WECUIEvent(String type, String[] params) {
        this.type = type;
        this.params = params;
        String debugmsg = "CUI event: " + type + " ";
        for (int i = 0; i < params.length; i++) {
            debugmsg += params[i] + " ";
        }
        WorldEditCUI.debug(debugmsg);
    }

    public void markHandled() {
        handled = true;
    }

    public void markInvalid(String reason) {
        String debugmsg = "WARNING - INVALID WECUIEvent "+type;
        for (int i = 0; i < params.length; i++) {
            debugmsg += "|"+params[i];
        }
        debugmsg += " because "+reason;
        WorldEditCUI.debug(debugmsg);
        markHandled();
    }

    public float getFloat(int index) {
        return Float.parseFloat(params[index]);
    }

    public int getInt(int index) {
        return (int)Float.parseFloat(params[index]);
    }

    public boolean isHandled() {
        return handled;
    }

    public boolean isCancelled() {
        return isHandled();
    }

    @Override
    protected String getEventName() {
        return "Worldedit CUI Communication event";
    }

    public static final HandlerList<WECUIEvent> handlers = new HandlerList<WECUIEvent>();
    @Override
    protected HandlerList<WECUIEvent> getHandlers() {
        return handlers;
    }

}
