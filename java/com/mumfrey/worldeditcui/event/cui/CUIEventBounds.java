package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventArgs;
import com.mumfrey.worldeditcui.event.CUIEventType;
import com.mumfrey.worldeditcui.render.region.Region;

/**
 * Called when resize event is received
 * 
 * @author lahwran
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class CUIEventBounds extends CUIEvent
{
	public CUIEventBounds(CUIEventArgs args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.MINMAX;
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
		
		int min = this.getInt(0);
		int max = this.getInt(1);
		selection.setMinMax(min, max);
		this.controller.getDebugger().debug("Expanding/contracting selection.");
		
		return null;
	}
}
