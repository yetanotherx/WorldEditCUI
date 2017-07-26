package com.mumfrey.worldeditcui.render.region;

import java.util.ArrayList;
import java.util.List;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.render.ConfiguredColour;
import com.mumfrey.worldeditcui.render.points.PointRectangle;
import com.mumfrey.worldeditcui.render.shapes.Render2DBox;
import com.mumfrey.worldeditcui.render.shapes.Render2DGrid;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Main controller for a polygon-type region
 * 
 * @author yetanotherx
 * @author lahwran
 * @author Adam Mummery-Smith
 */
public class PolygonRegion extends Region
{
	private final List<PointRectangle> points = new ArrayList<PointRectangle>();
	private int min, max;
	
	private Render2DBox box;
	private Render2DGrid grid;
	
	public PolygonRegion(WorldEditCUI controller)
	{
		super(controller, ConfiguredColour.POLYBOX, ConfiguredColour.POLYGRID, ConfiguredColour.POLYPOINT);
	}
	
	@Override
	public void render(Vector3 cameraPos, float partialTicks)
	{
		if (this.points.size() < 1)
		{
			return;
		}
		
		for (PointRectangle point : this.points)
		{
			if (point != null)
			{
				point.render(cameraPos);
			}
		}
		
		this.box.render(cameraPos);
		this.grid.render(cameraPos);
	}
	
	@Override
	public void setMinMax(int min, int max)
	{
		this.min = min;
		this.max = max;
		this.update();
	}
	
	@Override
	public void setPolygonPoint(int id, int x, int z)
	{
		PointRectangle point = new PointRectangle(x, z);
		point.setColour(this.colours[0]);
		point.setMinMax(this.min, this.max);
		
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
		this.update();
	}
	
	private void update()
	{
		if (this.points.size() <= 0)
		{
			return;
		}
		
		for (PointRectangle point : this.points)
		{
			if (point != null)
			{
				point.setMinMax(this.min, this.max);
			}
		}
		
		this.box = new Render2DBox(this.colours[0], this.points, this.min, this.max);
		this.grid = new Render2DGrid(this.colours[1], this.points, this.min, this.max);
	}
	
	@Override
	protected void updateColours()
	{
		if (this.box != null) 
		{
			this.box.setColour(this.colours[0]);
		}
		
		if (this.grid != null)
		{
			this.grid.setColour(this.colours[1]);
		}
		
		for (PointRectangle point : this.points)
		{
			point.setColour(this.colours[0]);
		}
	}

	@Override
	public RegionType getType()
	{
		return RegionType.POLYGON;
	}
}

