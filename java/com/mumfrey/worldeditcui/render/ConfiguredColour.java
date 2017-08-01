package com.mumfrey.worldeditcui.render;

import net.minecraft.client.resources.I18n;

import com.mumfrey.worldeditcui.config.Colour;
import com.mumfrey.worldeditcui.render.RenderStyle.RenderType;

/**
 * Stores style data for each type of line.
 * 
 * Each line has a normal line, and a hidden line.
 * The normal line has an alpha value of 0.8f, and
 * the hidden line has an alpha value of 0.2f. They
 * both have a thickness of 3.0f.
 * 
 * @author yetanotherx
 * @author lahwran
 * @author Adam Mummery-Smith
 */
public enum ConfiguredColour
{
	CUBOIDBOX      ("colour.cuboidedge",     new Colour("#CC3333CC")),
	CUBOIDGRID     ("colour.cuboidgrid",     new Colour("#CC4C4CCC")),
	CUBOIDPOINT1   ("colour.cuboidpoint1",   new Colour("#33CC33CC")),
	CUBOIDPOINT2   ("colour.cuboidpoint2",   new Colour("#3333CCCC")),
	POLYGRID       ("colour.polygrid",       new Colour("#CC3333CC")),
	POLYBOX        ("colour.polyedge",       new Colour("#CC4C4CCC")),
	POLYPOINT      ("colour.polypoint",      new Colour("#33CCCCCC")),
	ELLIPSOIDGRID  ("colour.ellipsoidgrid",  new Colour("#CC4C4CCC")),
	ELLIPSOIDCENTRE("colour.ellipsoidpoint", new Colour("#CCCC33CC")),
	CYLINDERGRID   ("colour.cylindergrid",   new Colour("#CC3333CC")),
	CYLINDERBOX    ("colour.cylinderedge",   new Colour("#CC4C4CCC")),
	CYLINDERCENTRE ("colour.cylinderpoint",  new Colour("#CC33CCCC")),
	CHUNKBOUNDARY  ("colour.chunkboundary",  new Colour("#33CC33CC")),
	CHUNKGRID      ("colour.chunkgrid",      new Colour("#4CCCAA99"));
	
	class Style implements RenderStyle
	{
		private RenderType renderType = RenderType.ANY;
		
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
		}

		@Override
		public Colour getColour()
		{
			return ConfiguredColour.this.getColour();
		}

		@Override
		public LineStyle[] getLines()
		{
			return ConfiguredColour.this.getLines();
		}
	}
	
	private String displayName;
	private Colour defaultColour, colour;
	private LineStyle normal, hidden;
	private LineStyle[] lines;
	
	private ConfiguredColour(String displayName, Colour colour)
	{
		this.displayName = displayName;
		this.colour = colour;
		this.defaultColour = new Colour().copyFrom(colour);
		this.updateLines();
	}
	
	public String getDisplayName()
	{
		return I18n.format(this.displayName);
	}
	
	public RenderStyle style()
	{
		return new Style();
	}
	
	public void setColour(Colour colour)
	{
		this.colour = colour;
		this.updateLines();
	}

	public Colour getColour()
	{
		return this.colour;
	}
	
	public LineStyle getHidden()
	{
		return this.hidden;
	}
	
	public LineStyle getNormal()
	{
		return this.normal;
	}
	
	public LineStyle[] getLines()
	{
		return this.lines;
	}
	
	public void setDefault()
	{
		this.colour.copyFrom(this.defaultColour);
		this.updateLines();
	}
	
	public Colour getDefault()
	{
		return this.defaultColour;
	}
	
	public void setColourIntRGBA(int argb)
	{
		int rgba = ((argb << 8) & 0xFFFFFF00) | (((argb & 0xFF000000) >> 24) & 0xFF);
		this.colour.setHex(Integer.toHexString(rgba));
		this.updateLines();
	}
	
	public int getColourIntARGB()
	{
		return this.colour.getIntARGB();
	}

	private void updateLines()
	{
		this.normal = new LineStyle(RenderType.VISIBLE, 3.0f, this.colour.red(), this.colour.green(), this.colour.blue(), this.colour.alpha());
		this.hidden = new LineStyle(RenderType.HIDDEN, 3.0f, this.colour.red() * 0.75F, this.colour.green() * 0.75F, this.colour.blue() * 0.75F, this.colour.alpha() * 0.25F);
		this.lines = new LineStyle[] { this.hidden, this.normal };
	}
}
