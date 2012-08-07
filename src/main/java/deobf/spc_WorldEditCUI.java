package deobf;

import wecui.WorldEditCUI;
import wecui.event.CUIEvent;

/**
 * Main SinglePlayerCommands class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class spc_WorldEditCUI extends SPCPlugin {

    protected WorldEditCUI controller;

    public spc_WorldEditCUI(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public String getVersion() {
        return WorldEditCUI.getVersion();
    }

    @Override
    public String getName() {
        return "WorldEditCUI";
    }

    @Override
    public void handleCUIEvent(String type, String params[]) {
        if (controller != null) {
            controller.getEventManager().callEvent(new CUIEvent(controller, type, params));
        }
    }
}