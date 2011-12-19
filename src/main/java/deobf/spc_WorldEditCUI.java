package deobf;

import wecui.WorldEditCUI;
import wecui.event.CUIEvent;
import wecui.util.Utilities;

/**
 * Main SinglePlayerCommands class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class spc_WorldEditCUI extends SPCPlugin {

    protected static WorldEditCUI controller;

    public static void setController(WorldEditCUI controller) {
        spc_WorldEditCUI.controller = controller;
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
        System.out.println(type + " - " + Utilities.join(params));
        if( controller != null ) {
            controller.getEventManager().callEvent(new CUIEvent(controller, type, params));
        }
    }
}