package wecui.event.cui;

import wecui.WorldEditCUI;
import wecui.render.CuboidRegion;

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
            WorldEditCUI.getDebugger().debug("Received handshake event, sending CUI command.");
            controller.getObfuscation().sendChat("/worldedit cui");
        }

        this.controller.setSelection(new CuboidRegion());

        return null;
    }
}
