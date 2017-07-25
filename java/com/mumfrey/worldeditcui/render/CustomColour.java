package com.mumfrey.worldeditcui.render;

import static com.mumfrey.liteloader.gl.GL.*;

import com.mumfrey.worldeditcui.config.Colour;

/**
 * Server-defined colour for multi selections
 * 
 * @author Adam Mummery-Smith
 */
public class CustomColour implements RenderColour
{
	private Colour colour;
	private final LineInfo[] lines = new LineInfo[2];

	public CustomColour(Colour colour)
	{
		this.setColour(colour);
	}

	@Override
	public void setColour(Colour colour)
	{
		this.colour = colour;
		this.lines[0] = new LineInfo(3.0f, colour.red() * 0.75F, colour.green() * 0.75F, colour.blue() * 0.75F, colour.alpha() * 0.25F, GL_GEQUAL);
		this.lines[1] =	new LineInfo(3.0f, colour.red(), colour.green(), colour.blue(), colour.alpha(), GL_LESS);
	}
	
	@Override
	public Colour getColour()
	{
		return this.colour;
	}

	@Override
	public LineInfo[] getColours()
	{
		return this.lines;
	}
}
