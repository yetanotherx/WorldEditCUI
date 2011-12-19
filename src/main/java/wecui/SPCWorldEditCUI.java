package wecui;

import deobf.SPCPlugin;
import wecui.event.CUIEvent;

/**
 * Main SinglePlayerCommands class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class SPCWorldEditCUI extends SPCPlugin {

    protected WorldEditCUI controller;

    public SPCWorldEditCUI(WorldEditCUI controller) {
        this.controller = controller;
    }
    
    @Override
    public String getVersion() {
        return WorldEditCUI.VERSION;
    }

    @Override
    public String getName() {
        return "WorldEditCUI";
    }

    @Override
    public void handleCUIEvent(String type, String params[]) {
        if( controller != null ) {
            controller.getEventManager().callEvent(new CUIEvent(controller, type, params));
        }
    }
}