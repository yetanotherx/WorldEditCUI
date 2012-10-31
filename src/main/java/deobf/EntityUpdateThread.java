package deobf;

import java.util.logging.Level;
import java.util.logging.Logger;
import wecui.obfuscation.Obfuscation;

public class EntityUpdateThread extends Thread {

    private mod_WorldEditCUI mod;

    public EntityUpdateThread(mod_WorldEditCUI mod) {
        this.mod = mod;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EntityUpdateThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if( mod.lastEntity != null ) {
                Obfuscation.setEntityPositionToPlayer(mod.controller.getMinecraft(), mod.lastEntity);
            }
        }
    }
    
    
    
}
