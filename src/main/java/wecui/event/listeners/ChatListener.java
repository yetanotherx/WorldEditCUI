package wecui.event.listeners;

import wecui.fevents.Listener;
import wecui.WorldEditCUI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wecui.Updater;
import wecui.event.CUIEvent;
import wecui.event.ChatEvent;

/**
 * Parses incoming/outgoing chat messages, and calls a CUIEvent if it matches the WorldEdit CUI header
 * WorldEdit itself sends a message that is prepended by 4 special characters
 * Relevant: http://bit.ly/v3Me4m
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class ChatListener implements Listener<ChatEvent> {

    public final static Pattern commandpattern = Pattern.compile("\u00a75\u00a76\u00a74\u00a75([^|]*)\\|?(.*)");
    protected WorldEditCUI controller;

    public ChatListener(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void onEvent(ChatEvent event) {
        if (event.getDirection() == ChatEvent.Direction.INCOMING) {
            Matcher matcher = commandpattern.matcher(event.getMessage());

            //Checks if the incoming message contains the 8-byte CUI header (4 color codes)
            //If so, parses the message and creates a new CUIEvent
            if (matcher.find()) {
                String type = matcher.group(1);
                String args = matcher.group(2);
                this.controller.getDebugger().debug("Received CUI event from server: " + event.getMessage());

                CUIEvent cuievent = new CUIEvent(this.controller, type, args.split("[|]"));
                this.controller.getEventManager().callEvent(cuievent);

                if (!cuievent.isHandled()) {
                    cuievent.markInvalid("Invalid message type. Update WorldEditCUI to the latest version.");
                }

                event.setCancelled(cuievent.isHandled());
            }
            
            //Check for new WE version. If this is the message, send the protocol version.
            if (event.getMessage().contains("\u00a74\u00a75\u00a73\u00a74")) {
                controller.getObfuscation().sendChat("u00a74u00a75u00a73u00a74v|" + WorldEditCUI.protocolVersion);
                controller.getLocalPlugin().onVersionEvent("");
                new Updater(controller).start();
            }
        }
    }
}
