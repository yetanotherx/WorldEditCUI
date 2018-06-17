package com.mumfrey.worldeditcui.event.listeners;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEventArgs;

/**
 * Listener class for incoming plugin channel messages
 * 
 * @author lahwran
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class CUIListenerChannel
{
	private WorldEditCUI controller;
	
	public CUIListenerChannel(WorldEditCUI controller)
	{
		this.controller = controller;
	}
	
	public void onMessage(String message)
	{
		String[] split = message.split("\\|", -1);
		boolean multi = split[0].startsWith("+");
		String type = split[0].substring(multi ? 1 : 0);
		String args = message.substring(type.length() + (multi ? 2 : 1));
		
		this.controller.getDebugger().debug("Received CUI event from server: " + message);
		
		try
		{
			CUIEventArgs eventArgs = new CUIEventArgs(this.controller, multi, type, args.split("\\|", -1));
			this.controller.getDispatcher().raiseEvent(eventArgs);
		}
		catch (Exception ex)
		{
//			ex.printStackTrace();
		}
	}
}
