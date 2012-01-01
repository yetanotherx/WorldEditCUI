package wecui.event.cui;

import wecui.WorldEditCUI;
import wecui.render.region.CuboidRegion;

/**
 * Called when handshake event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIHandshakeEvent extends CUIBaseEvent {

    public CUIHandshakeEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.HANDSHAKE;
    }

    @Override
    public String run() {
        
        if (controller.getObfuscation().isMultiplayerWorld()) {
            controller.getDebugger().debug("Received handshake event, sending CUI command.");
            controller.getObfuscation().sendChat("/worldedit cui");
        }
        
        CuboidRegion cuboidRegion = new CuboidRegion(controller);
        
        //Initiates a new blank cuboid region.
        this.controller.setSelection(cuboidRegion);

        return null;
    }
}
