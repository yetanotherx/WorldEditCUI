package wecui.event;

import wecui.WorldEditCUI;
import wecui.util.Utilities;

/**
 * CUI communication event
 * Called when a CUI event is sent from the server.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class CUIEvent
{
	private WorldEditCUI controller;
	private String type;
	private String[] params;
	
	public CUIEvent(WorldEditCUI controller, String type, String[] params)
	{
		this.controller = controller;
		this.type = type;
		
		if (params.length == 1 && params[0].length() == 0)
		{
			params = new String[] {};
		}
		
		this.params = params;
		this.controller.getDebugger().debug("CUI Event (" + type + ") - Params: " + Utilities.join(params, ", "));
	}
	
	public int getInt(int index)
	{
		return (int)Float.parseFloat(this.params[index]);
	}
	
	public String getString(int index)
	{
		return this.params[index];
	}
	
	public String[] getParams()
	{
		return this.params;
	}
	
	public String getType()
	{
		return this.type;
	}
}
