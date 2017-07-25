package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.config.Colour;
import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventArgs;
import com.mumfrey.worldeditcui.event.CUIEventType;
import com.mumfrey.worldeditcui.render.CustomColour;
import com.mumfrey.worldeditcui.render.RenderColour;
import com.mumfrey.worldeditcui.render.region.Region;

/**
 * Called when colour event is received
 * 
 * @author Adam Mummery-Smith
 */
public class CUIEventColour extends CUIEvent
{
	public CUIEventColour(CUIEventArgs args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.COLOUR;
	}
	
	@Override
	public void prepare()
	{
		if (!this.multi)
		{
			throw new IllegalStateException("COLOUR event is not valid for non-multi selections");
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
		
		RenderColour[] defaultColours = selection.getDefaultColours();
		RenderColour[] colours = new RenderColour[defaultColours.length];

		for (int i = 0; i < defaultColours.length; i++)
		{
			String str = this.getString(i);
			if (!str.startsWith("#"))
			{
				str = "#" + str;
			}
			colours[i] = new CustomColour(Colour.parse(str, defaultColours[i].getColour()));
			
		}
		
		selection.setColours(colours);
		return null;
	}
}
