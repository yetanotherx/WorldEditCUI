package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.event.CUIEventArgs;
import com.mumfrey.worldeditcui.event.CUIEventType;
import com.mumfrey.worldeditcui.render.region.Region;

/**
 * Called when poly point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class CUIEventPoint2D extends CUIEventPoint3D
{
	public CUIEventPoint2D(CUIEventArgs args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.POINT2D;
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
		int x = this.getInt(1);
		int z = this.getInt(2);
		@SuppressWarnings("unused")
		int regionSize = this.getInt(3);
		selection.setPolygonPoint(id, x, z);
		
		this.controller.getDebugger().debug("Setting point2d #" + id);
		
		return null;
	}
}
