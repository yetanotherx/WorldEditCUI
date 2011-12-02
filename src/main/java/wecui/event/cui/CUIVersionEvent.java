package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when version event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIVersionEvent extends CUIBaseEvent {

    public CUIVersionEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.VERSION;
    }

    @Override
    public String run() {
        /*WorldEditCUI.pluginVersion = this.getString(0);
        
        WorldEditCUI.getDebugger().debug("WorldEdit version - " + WorldEditCUI.pluginVersion);
        */
        //TODO: Check for version compatibility here. Only do once interface for the local copy exists.
        return null;
    }
    
}
