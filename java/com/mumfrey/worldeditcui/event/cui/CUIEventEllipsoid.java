package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventArgs;
import com.mumfrey.worldeditcui.event.CUIEventType;
import com.mumfrey.worldeditcui.render.region.Region;

/**
 * Called when ellipsoid event is received
 * 
 * @author lahwran
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class CUIEventEllipsoid extends CUIEvent
{
	public CUIEventEllipsoid(CUIEventArgs args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.ELLIPSOID;
	}
	
	@Override
	public String raise()
	{
		Region selection = this.controller.getSelection(this.multi);
		if (selection == null)
		{
			this.controller.getDebugger().debug("No active multi selection.");
			return null;
		}
		
		
		int id = this.getInt(0);
		
		if (id == 0)
		{
			int x = this.getInt(1);
			int y = this.getInt(2);
			int z = this.getInt(3);
			selection.setEllipsoidCenter(x, y, z);
		}
		else if (id == 1)
		{
			double x = this.getDouble(1);
			double y = this.getDouble(2);
			double z = this.getDouble(3);
			selection.setEllipsoidRadii(x, y, z);
		}
		
		this.controller.getDebugger().debug("Setting centre/radius");
		
		return null;
	}
}
