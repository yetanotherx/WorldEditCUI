package wecui.event.command;

import wecui.WorldEditCUI;
import wecui.util.ChatColor;

public class VersionCommandEvent extends CommandEventBase {

    public VersionCommandEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public String getCommand() {
        return "version";
    }

    @Override
    public void run() {
        this.controller.getObfuscation().showChatMessage(ChatColor.LIGHT_PURPLE + "WorldEditCUI version " + WorldEditCUI.VERSION);
    }
    
}
