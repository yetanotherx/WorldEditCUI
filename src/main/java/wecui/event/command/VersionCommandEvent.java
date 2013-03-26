package wecui.event.command;

import net.minecraft.src.EntityClientPlayerMP;
import wecui.WorldEditCUI;
import wecui.util.ChatColor;

/**
 * Called when /we version is called. Inserts a WECUI version message.
 * 
 * @author yetanotherx
 * 
 */
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
        EntityClientPlayerMP thePlayer = this.controller.getMinecraft().thePlayer;
		if (thePlayer != null)
		{
			thePlayer.addChatMessage(ChatColor.LIGHT_PURPLE + "WorldEditCUI version " + WorldEditCUI.getVersion());
		}
    }
}
