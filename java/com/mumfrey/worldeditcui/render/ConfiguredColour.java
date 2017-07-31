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
public enum ConfiguredColour // implements RenderStyle
{
	CUBOIDBOX      ("style.cuboidedge",     new Colour("#CC3333CC")),
	CUBOIDGRID     ("style.cuboidgrid",     new Colour("#CC4C4CCC")),
	CUBOIDPOINT1   ("style.cuboidpoint1",   new Colour("#33CC33CC")),
	CUBOIDPOINT2   ("style.cuboidpoint2",   new Colour("#3333CCCC")),
	POLYGRID       ("style.polygrid",       new Colour("#CC3333CC")),
	POLYBOX        ("style.polyedge",       new Colour("#CC4C4CCC")),
	POLYPOINT      ("style.polypoint",      new Colour("#33CCCCCC")),
	ELLIPSOIDGRID  ("style.ellipsoidgrid",  new Colour("#CC4C4CCC")),
	ELLIPSOIDCENTRE("style.ellipsoidpoint", new Colour("#CCCC33CC")),
	CYLINDERGRID   ("style.cylindergrid",   new Colour("#CC3333CC")),
	CYLINDERBOX    ("style.cylinderedge",   new Colour("#CC4C4CCC")),
	CYLINDERCENTRE ("style.cylinderpoint",  new Colour("#CC33CCCC"));
	
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
	
	public void setDefaultColour()
	{
		this.colour.copyFrom(this.defaultColour);
		this.updateLines();
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
