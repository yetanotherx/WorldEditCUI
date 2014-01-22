package com.mumfrey.worldeditcui.render.region;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.render.shapes.Render3DBox;
import com.mumfrey.worldeditcui.render.shapes.Render3DGrid;
import com.mumfrey.worldeditcui.util.Vector3;
import com.mumfrey.worldeditcui.util.Vector3m;

/**
 * Main controller for a cuboid-type region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class CuboidRegion extends BaseRegion
{
	
	protected PointCube firstPoint;
	protected PointCube secondPoint;
	
	public CuboidRegion(WorldEditCUI controller)
	{
		super(controller);
	}
	
	@Override
	public void render()
	{
		if (this.firstPoint != null && this.secondPoint != null)
		{
			
			Vector3[] bounds = this.calcBounds();
			new Render3DGrid(LineColour.CUBOIDGRID, bounds[0], bounds[1]).render();
			new Render3DBox(LineColour.CUBOIDBOX, bounds[0], bounds[1]).render();
			
			this.firstPoint.render();
			this.secondPoint.render();
			
		}
		else if (this.firstPoint != null)
		{
			this.firstPoint.render();
		}
		else if (this.secondPoint != null)
		{
			this.secondPoint.render();
		}
	}
	
	@Override
	public void setCuboidPoint(int id, double x, double y, double z)
	{
		if (id == 0)
		{
			this.firstPoint = new PointCube(x, y, z);
			this.firstPoint.setColour(LineColour.CUBOIDPOINT1);
		}
		else if (id == 1)
		{
			this.secondPoint = new PointCube(x, y, z);
			this.secondPoint.setColour(LineColour.CUBOIDPOINT2);
		}
	}
	
	/**
	 * Calculates the boundary points of the actual box. 
	 * I have no idea what I'm doing.
	 * @return 
	 */
	protected Vector3m[] calcBounds()
	{
		float off = 0.02f;
		float off1 = 1 + off;
		
		Vector3m[] out = new Vector3m[2];
		out[0] = new Vector3m(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		out[1] = new Vector3m(-Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE);
		
		for (PointCube point : new PointCube[] { this.firstPoint, this.secondPoint })
		{
			if (point.getPoint().getX() + off1 > out[1].getX())
			{
				out[1].setX(point.getPoint().getX() + off1);
			}
			
			if (point.getPoint().getX() - off < out[0].getX())
			{
				out[0].setX(point.getPoint().getX() - off);
			}
			
			if (point.getPoint().getY() + off1 > out[1].getY())
			{
				out[1].setY(point.getPoint().getY() + off1);
			}
			
			if (point.getPoint().getY() - off < out[0].getY())
			{
				out[0].setY(point.getPoint().getY() - off);
			}
			
			if (point.getPoint().getZ() + off1 > out[1].getZ())
			{
				out[1].setZ(point.getPoint().getZ() + off1);
			}
			
			if (point.getPoint().getZ() - off < out[0].getZ())
			{
				out[0].setZ(point.getPoint().getZ() - off);
			}
		}
		
		return out;
	}
	
	@Override
	public RegionType getType()
	{
		return RegionType.CUBOID;
	}
}
