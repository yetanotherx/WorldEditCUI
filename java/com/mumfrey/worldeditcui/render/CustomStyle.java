package com.mumfrey.worldeditcui.render;

import com.mumfrey.worldeditcui.config.Colour;

/**
 * Server-defined style for multi selections
 * 
 * @author Adam Mummery-Smith
 */
public class CustomStyle implements RenderStyle
{
	private Colour colour;
	private RenderType renderType = RenderType.ANY;
	private final LineStyle[] lines = new LineStyle[2];

	public CustomStyle(Colour colour)
	{
		this.setColour(colour);
	}
	
	@Override
	public void setRenderType(RenderType renderType)
	{
		this.renderType = renderType;
	}
	
	@Override
	public RenderType getRenderType()
	{
		return this.renderType;
	}

	@Override
	public void setColour(Colour colour)
	{
		this.colour = colour;
		this.lines[0] = new LineStyle(RenderType.HIDDEN, 3.0f, colour.red() * 0.75F, colour.green() * 0.75F, colour.blue() * 0.75F, colour.alpha() * 0.25F);
		this.lines[1] =	new LineStyle(RenderType.VISIBLE, 3.0f, colour.red(), colour.green(), colour.blue(), colour.alpha());
	}
	
	@Override
	public Colour getColour()
	{
		return this.colour;
	}

	@Override
	public LineStyle[] getLines()
	{
		return this.lines;
	}
}
