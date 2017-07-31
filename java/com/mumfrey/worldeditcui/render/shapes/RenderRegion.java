package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.RenderStyle;
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
	protected RenderStyle style;
	
	protected RenderRegion(RenderStyle style)
	{
		this.style = style;
	}

	public final void setStyle(RenderStyle style)
	{
		this.style = style;
	}
	
	public abstract void render(Vector3 cameraPos);
	
	@Override
	public void notifyChanged(Observable<?> source)
	{
		
	}
}
