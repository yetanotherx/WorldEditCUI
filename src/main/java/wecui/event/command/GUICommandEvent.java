package wecui.event.command;

import wecui.WorldEditCUI;
import wecui.gui.WorldEditScreen;

public class GUICommandEvent extends CommandEventBase {

    public GUICommandEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public String getCommand() {
        return "gui";
    }

    @Override
    public void run() {
        controller.getObfuscation().showGuiScreenIfGuiChat(new WorldEditScreen());
    }
    
}
