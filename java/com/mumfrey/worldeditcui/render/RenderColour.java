package com.mumfrey.worldeditcui.render;

import com.mumfrey.worldeditcui.config.Colour;

/**
 * Render colour adapter, can be one of the built-in {@link ConfiguredColour}s or a
 * user-defined colour from a custom payload
 * 
 * @author Adam Mummery-Smith
 */
public interface RenderColour
{
	public abstract void setColour(Colour colour);

	public abstract Colour getColour();
	
	public abstract LineInfo[] getColours();
}