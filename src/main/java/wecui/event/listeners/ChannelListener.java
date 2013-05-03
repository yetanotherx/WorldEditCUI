package wecui.event.listeners;

import wecui.WorldEditCUI;
import wecui.event.CUIEvent;
import wecui.event.ChannelEvent;
import wecui.fevents.Listener;

/**
 * Listener class for incoming plugin channel messages
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class ChannelListener implements Listener<ChannelEvent> {

    protected WorldEditCUI controller;

    public ChannelListener(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void onEvent(ChannelEvent event) {
        String[] split = event.getMessage().split("[|]");
        String type = split[0];
        String args = event.getMessage().substring(type.length() + 1);

        this.controller.getDebugger().debug("Received CUI event from server: " + event.getMessage());

        CUIEvent cuievent = new CUIEvent(this.controller, type, args.split("[|]"));
        this.controller.getEventManager().callEvent(cuievent);

        if (!cuievent.isHandled()) {
            cuievent.markInvalid("Invalid message type. Update WorldEditCUI to the latest version.");
        }
    }
}
