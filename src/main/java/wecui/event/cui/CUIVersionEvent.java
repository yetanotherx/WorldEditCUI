package wecui.event.cui;

import com.sk89q.worldedit.WorldEdit;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wecui.WorldEditCUI;
import wecui.plugin.CUIWEConfiguration;
import wecui.plugin.LocalPlugin;
import wecui.plugin.CUIServerInterface;

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
        //If for some reason, the local plugin is already disabled, let's not continue.
        if (!controller.getLocalPlugin().isEnabled()) {
            return "WorldEdit not found! Ignoring message!";
        }
        
        //Check if the WorldEdit class exists.
        try {
            Class.forName("com.sk89q.worldedit.WorldEdit");
        } catch (Exception e) {
            controller.getLocalPlugin().setEnabled(false);
            return "WorldEdit not found! Certain features will not work as expected!";
        }
        
        String plugin = this.getString(0);
        String local = WorldEdit.getVersion();
        
        controller.getDebugger().debug("Server version - " + plugin + " | Local version - " + local);
        
        if (!local.equals(plugin)) {
            controller.getLocalPlugin().setEnabled(false);
            return "Server and local versions of WorldEdit do not match!";
        }
        
        if( !WorldEditCUI.WEVERSIONS.contains(local) ) {
            controller.getLocalPlugin().setEnabled(false);
            return "WorldEdit version is not compatible with WorldEditCUI! Certain features will not work!";
        }
        
        try {
            
            //Initializes plugin class
            LocalPlugin lp = controller.getLocalPlugin();
            lp.setConfiguration(new CUIWEConfiguration(controller));
            lp.setServerInterface(new CUIServerInterface(controller));
            
            Class<?> clazz = lp.getClazz();
            Constructor[] consts = clazz.getDeclaredConstructors();
            
            lp.setPlugin((WorldEdit) consts[0].newInstance(lp.getServerInterface(), lp.getConfiguration()));
        
            //Set localPlugin if SPC already loaded it.
            controller.getLocalPlugin().setEnabled(true);
            return null;
        } catch (Exception ex) {
            controller.getLocalPlugin().setEnabled(false);
            return ex.getMessage();
        }
        
    }
}
