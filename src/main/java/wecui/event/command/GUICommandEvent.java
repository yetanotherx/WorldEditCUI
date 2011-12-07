package wecui.event.command;

import wecui.WorldEditCUI;
import wecui.gui.WorldEditScreen;
import wecui.util.Utilities;

/**
 * Called when /we gui is called, opening the GUI window
 * TODO: Also open the window on keypress
 * 
 * @author yetanotherx
 * 
 */
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
        this.setCancelled(true);
        System.out.print(Utilities.debugMap(controller.getLocalPlugin().getPlugin().getCommands()));
        //controller.getObfuscation().showGuiScreenIfGuiChat(new WorldEditScreen());
    }
    
}
