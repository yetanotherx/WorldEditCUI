package wecui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.src.EntityClientPlayerMP;
import wecui.util.ChatColor;

public class Updater extends Thread {

    protected WorldEditCUI controller;
    protected final int updaterVersion = 1;
    
    protected final int currentRevision = 100;

    public Updater(WorldEditCUI controller) {
        this.controller = controller;
    }

    /**
     * Checks the most recent version of updates.yml on GitHub, and parses the
     * YAML to find supported versions. If the current version isn't supported,
     * show an error to the user.
     */
    @Override
    public void run() {
        if (this.controller.configuration.ignoreUpdates()) {
            return;
        }

        InputStream is = null;

        try {
            URL url = new URL(this.controller.getConfiguration().getUpdateFile());
            url.openConnection();
            is = url.openStream();

			StringBuilder readString = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String readLine;
			while ((readLine = reader.readLine()) != null)
			{
				readString.append(readLine).append("\n");
			}
			
			reader.close();

			String[] versions = readString.toString().split("\n");
			int latestVersion = 0;
            
			for (String version : versions)
			{
				if (version.matches("^" + WorldEditCUI.MCVERSION + "=\\d+$"))
				{
					latestVersion = Integer.parseInt(version.substring(version.indexOf("=") + 1));
				}
			}
            
            if (latestVersion > this.currentRevision) {
                this.showChatMessage(ChatColor.RED + "Your WorldEditCUI version is out of date.");
            }

        } catch (Exception e) {
            this.controller.getDebugger().info("Error in fetching update file!", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }
	
	/**
	 * Displays a chat message on the screen, if the player is currently playing
	 * 
	 * @param chat
	 */
	public void showChatMessage(String chat)
	{
		EntityClientPlayerMP thePlayer = this.controller.getMinecraft().thePlayer;
		if (thePlayer != null)
		{
			thePlayer.addChatMessage(chat);
		}
	}
}
