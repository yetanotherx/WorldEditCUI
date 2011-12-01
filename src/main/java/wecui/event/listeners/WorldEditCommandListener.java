package wecui.event.listeners;

import wecui.fevents.Listener;
import wecui.WorldEditCUI;
import wecui.event.ChatCommandEvent;

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
    }
}
