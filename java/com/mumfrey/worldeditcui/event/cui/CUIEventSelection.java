package com.mumfrey.worldeditcui.event.cui;

import java.util.UUID;

import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventArgs;
import com.mumfrey.worldeditcui.event.CUIEventType;
import com.mumfrey.worldeditcui.render.region.Region;

/**
 * Called when selection event is received
 * 
 * @author lahwran
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class CUIEventSelection extends CUIEvent
{
	public CUIEventSelection(CUIEventArgs args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.SELECTION;
	}
	
	@Override
	public String raise()
	{
		String key = this.getString(0);
		Region selection = this.controller.getSelectionProvider().createSelection(key);
		
		if (selection != null)
		{
			selection.initialise();
		}
		
		UUID id = null;
		if (this.multi)
		{
			if (selection == null && this.params.length < 2)
			{
				this.controller.getDebugger().debug("Received clear selection event.");
				this.controller.clearRegions();
				return null;
			}
			
			id = UUID.fromString(this.getString(1));
		}
		
		this.controller.setSelection(id, selection);
		this.controller.getDebugger().debug("Received selection event, initalizing new region instance.");
		
		return null;
	}
}
