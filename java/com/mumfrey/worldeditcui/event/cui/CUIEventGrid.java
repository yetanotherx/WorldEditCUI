package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventArgs;
import com.mumfrey.worldeditcui.event.CUIEventType;
import com.mumfrey.worldeditcui.render.RenderStyle.RenderType;
import com.mumfrey.worldeditcui.render.region.Region;

/**
 * Called when grid spacing event is received
 * 
 * @author Adam Mummery-Smith
 */
public class CUIEventGrid extends CUIEvent
{
	public CUIEventGrid(CUIEventArgs args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.GRID;
	}
	
	@Override
	public void prepare()
	{
		if (!this.multi)
		{
			throw new IllegalStateException("GRID event is not valid for non-multi selections");
		}
		
		super.prepare();
	}
	
	@Override
	public String raise()
	{
		Region selection = this.controller.getSelection(true);
		if (selection == null)
		{
			this.controller.getDebugger().debug("No active multi selection.");
			return null;
		}
		
		selection.setGridSpacing(this.getDouble(0));
		
		RenderType renderType = RenderType.ANY;
		if (this.params.length > 1 && "cull".equalsIgnoreCase(this.getString(1)))
		{
			renderType = RenderType.VISIBLE;
		}
		
		selection.setRenderType(renderType);
		return null;
	}
}
