package deobf;
import net.lahwran.WECUIEvent;
import net.lahwran.fevents.EventManager;
import net.lahwran.wecui.WorldEditCUI;

public class spc_WorldEditCUI extends SPCPlugin {
   
    @Override
    public String getVersion() {
        return WorldEditCUI.version;
    }
    
    @Override
    public String getName() {
        return "WorldEditCUI";
    }
    
    @Override
    public void handleCUIEvent(String type, String params[]) {
        EventManager.callEvent(new WECUIEvent(type, params));
    }
}