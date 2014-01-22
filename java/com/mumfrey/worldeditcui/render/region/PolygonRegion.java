package com.mumfrey.worldeditcui.render.region;

import java.util.ArrayList;
import java.util.List;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.points.PointRectangle;
import com.mumfrey.worldeditcui.render.shapes.Render2DBox;
import com.mumfrey.worldeditcui.render.shapes.Render2DGrid;

/**
 * Main controller for a polygon-type region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class PolygonRegion extends BaseRegion
{
	
	protected List<PointRectangle> points = new ArrayList<PointRectangle>();
	protected int min;
	protected int max;
	
	public PolygonRegion(WorldEditCUI controller)
	{
		super(controller);
	}
	
	@Override
	public void render()
	{
		if (this.points == null)
		{
			return;
		}
		
		for (PointRectangle point : this.points)
		{
			point.render(this.min, this.max);
		}
		
		new Render2DBox(LineColour.POLYBOX, this.points, this.min, this.max).render();
		new Render2DGrid(LineColour.POLYGRID, this.points, this.min, this.max).render();
		
	}
	
	@Override
	public void setMinMax(int min, int max)
	{
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void setPolygonPoint(int id, int x, int z)
	{
		PointRectangle point = new PointRectangle(x, z);
		point.setColour(LineColour.POLYPOINT);
		
		if (id < this.points.size())
		{
			this.points.set(id, point);
		}
		else
		{
			for (int i = 0; i < id - this.points.size(); i++)
			{
				this.points.add(null);
			}
			this.points.add(point);
		}
	}
	
	@Override
	public RegionType getType()
	{
		return RegionType.POLYGON;
	}
}
