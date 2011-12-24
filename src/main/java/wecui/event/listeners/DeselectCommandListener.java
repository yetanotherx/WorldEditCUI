package wecui.event.listeners;

import wecui.fevents.Listener;
import wecui.WorldEditCUI;
import wecui.event.ChatCommandEvent;
import wecui.render.CuboidRegion;
import wecui.render.Polygon2DRegion;

/**
 * Parses incoming/outgoing chat messages, and calls a CUIEvent if it matches the WorldEdit CUI header
 * WorldEdit itself sends a message that is prepended by 4 special characters
 * Relevant: http://bit.ly/v3Me4m
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class DeselectCommandListener implements Listener<ChatCommandEvent> {

    protected WorldEditCUI controller;

    public DeselectCommandListener(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void onEvent(ChatCommandEvent event) {
        if (event.getArgs().length > 0) {
            return;
        }

        event.setCancelled(true);
        switch (controller.getSelection().getType()) {
            case POLY:
                controller.setSelection(new Polygon2DRegion(controller));
                break;
            case CUBOID:
                controller.setSelection(new CuboidRegion(controller));
                break;
        }
        controller.getObfuscation().sendChat("//sel");
    }
}
