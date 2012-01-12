package wecui.event.listeners;

import wecui.fevents.Listener;
import wecui.WorldEditCUI;
import wecui.event.ChatCommandEvent;
import wecui.event.command.CommandEventBase;
import wecui.event.command.CommandEventType;
import wecui.vendor.org.joor.Reflect;
import wecui.vendor.org.joor.ReflectException;

/**
 * Parses incoming/outgoing chat messages, and calls a CUIEvent if it matches the WorldEdit CUI header
 * WorldEdit itself sends a message that is prepended by 4 special characters
 * Relevant: http://bit.ly/v3Me4m
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class WorldEditCommandListener implements Listener<ChatCommandEvent> {

    protected WorldEditCUI controller;

    public WorldEditCommandListener(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void onEvent(ChatCommandEvent event) {
        if (event.getArgs().length == 0) {
            return;
        }

        CommandEventType commEventType = CommandEventType.getTypeFromCommand(event.getArgs()[0]);

        if (commEventType != null) {
            try {
                CommandEventBase newEvent = Reflect.on(commEventType.getEventClass()).create(this.controller, event.getArgs()).get();
                newEvent.run();

                if (newEvent.isCancelled()) {
                    event.setCancelled(true);
                }

            } catch (ReflectException ex) {
            }

        }
    }
}
