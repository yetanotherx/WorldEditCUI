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
        CUIx_fevent_EventManager.callEvent(new CUIx_events_CUIEvent(type, params));
    }
}