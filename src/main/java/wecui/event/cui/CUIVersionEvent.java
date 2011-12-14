package wecui.event.cui;

import com.sk89q.worldedit.WorldEdit;
import java.lang.reflect.Constructor;
import wecui.WorldEditCUI;
import wecui.plugin.CUIPlayer;
import wecui.plugin.CUIWEConfiguration;
import wecui.plugin.LocalPlugin;
import wecui.plugin.CUIServerInterface;
import wecui.plugin.CUIWorld;

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
        if (controller.getLocalPlugin().isInitialized()) {
            return null;
        }
        
        controller.getLocalPlugin().setInitialized(true);
        
        //Check if the WorldEdit class exists.
        try {
            Class.forName("com.sk89q.worldedit.WorldEdit");
        } catch (Exception e) {
            controller.getLocalPlugin().setEnabled(false);
            String error = "WorldEdit not found! Certain features will not work as expected!";
            controller.getObfuscation().showChatMessage(error);
            return error;
        }
        
        String plugin = this.getString(0);
        String local = WorldEdit.getVersion();
        
        controller.getDebugger().debug("Server version - " + plugin + " | Local version - " + local);
        
        if (!local.equals(plugin)) {
            controller.getLocalPlugin().setEnabled(false);
            String error = "Server and local versions of WorldEdit do not match!";
            controller.getObfuscation().showChatMessage(error);
            return error;
        }
        
        if( !WorldEditCUI.WEVERSIONS.contains(local) ) {
            controller.getLocalPlugin().setEnabled(false);
            String error = "WorldEdit version is not compatible with WorldEditCUI! Certain features will not work!";
            controller.getObfuscation().showChatMessage(error);
            return error;
        }
        
        try {
            
            //Initializes plugin class
            LocalPlugin lp = controller.getLocalPlugin();
            lp.setConfiguration(new CUIWEConfiguration(controller));
            lp.setServerInterface(new CUIServerInterface(controller));
            lp.setWorld(new CUIWorld(controller));
            
            Class<?> clazz = lp.getClazz();
            Constructor[] consts = clazz.getDeclaredConstructors();
            
            lp.setPlugin((WorldEdit) consts[0].newInstance(lp.getServerInterface(), lp.getConfiguration()));
        
            lp.setSession(lp.getPlugin().getSession(new CUIPlayer(lp.getServerInterface(), controller)));
            
            //Set localPlugin if SPC already loaded it.
            controller.getLocalPlugin().setEnabled(true);
            return null;
        } catch (Exception ex) {
            controller.getLocalPlugin().setEnabled(false);
            controller.getObfuscation().showChatMessage("Internal WorldEditCUI exception! See console for errors.");
            ex.printStackTrace(System.err);
            return ex.getMessage();
        }
        
    }
}
