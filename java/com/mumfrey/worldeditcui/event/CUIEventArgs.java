package com.mumfrey.worldeditcui.event;

import com.google.common.base.Joiner;
import com.mumfrey.worldeditcui.WorldEditCUI;

/**
 * CUI communication event
 * Called when a CUI event is sent from the server.
 * 
 * @author lahwran
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public final class CUIEventArgs
{
	private WorldEditCUI controller;
	private boolean multi;
	private String type;
	private String[] params;
	
	public CUIEventArgs(WorldEditCUI controller, boolean multi, String type, String[] params)
	{
		this.controller = controller;
		this.multi = multi;
		this.type = type;
		
		if (params.length == 1 && params[0].length() == 0)
		{
			params = new String[] {};
		}
		
		this.params = params;
		this.controller.getDebugger().debug("CUI Event (" + type + ") - Params: " + Joiner.on(", ").join(params));
	}
	
	public WorldEditCUI getController()
	{
		return this.controller;
	}
	
	public String[] getParams()
	{
		return this.params;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public boolean isMulti()
	{
		return this.multi;
	}
}
