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
        return controller.getLocalPlugin().onVersionEvent(this.getString(0));
    }
}
