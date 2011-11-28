package deobf;

import cuix.CUIx;
import cuix.event.CUIEvent;
import cuix.fevents.EventManager;

/**
 * Main SinglePlayerCommands class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */

public class spc_CUIx extends SPCPlugin {
   
    @Override
    public String getVersion() {
        return CUIx.VERSION;
    }
    
    @Override
    public String getName() {
        return "CUIx";
    }
    
    @Override
    public void handleCUIEvent(String type, String params[]) {
        EventManager.callEvent(new CUIEvent(type, params));
    }
}