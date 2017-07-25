package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.RenderColour;
import com.mumfrey.worldeditcui.util.Observable;
import com.mumfrey.worldeditcui.util.Observer;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Base class for region renderers
 * 
 * @author Adam Mummery-Smith
 */
public abstract class RenderRegion implements Observer
{
	protected RenderColour colour;
	
	protected RenderRegion(RenderColour colour)
	{
		this.colour = colour;
	}

	public final void setColour(RenderColour colour)
	{
		this.colour = colour;
	}
	
	public abstract void render(Vector3 cameraPos);
	
	@Override
	public void notifyChanged(Observable<?> source)
	{
		
	}
}
