package deobf;

import wecui.WorldEditCUI;
import wecui.event.CUIEvent;
import wecui.fevents.EventManager;

/**
 * Main SinglePlayerCommands class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class spc_WorldEditCUI extends SPCPlugin {

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
        EventManager.callEvent(new CUIEvent(type, params));
    }
}