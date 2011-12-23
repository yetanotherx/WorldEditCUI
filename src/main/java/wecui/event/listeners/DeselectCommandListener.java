package wecui.event.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import wecui.fevents.Listener;
import wecui.WorldEditCUI;
import wecui.event.ChatCommandEvent;
import wecui.event.command.CommandEventBase;
import wecui.event.command.CommandEventType;

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
        event.setCancelled(true);
        controller.getObfuscation().sendChat("//sel");
    }
}
