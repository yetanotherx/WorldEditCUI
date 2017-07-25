package com.mumfrey.worldeditcui.render.region;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.render.ConfiguredColour;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.render.points.PointCubeTracking;
import com.mumfrey.worldeditcui.render.shapes.Render3DBox;
import com.mumfrey.worldeditcui.render.shapes.Render3DGrid;
import com.mumfrey.worldeditcui.util.BoundingBox;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Main controller for a cuboid-type region
 * 
 * @author yetanotherx
 * @author lahwran
 * @author Adam Mummery-Smith
 */
public class CuboidRegion extends Region
{
	private PointCube[] points = new PointCube[2];
	
	private Render3DGrid grid;
	private Render3DBox box;
	
	private double spacing = 1.0;
	
	public CuboidRegion(WorldEditCUI controller)
	{
		super(controller, ConfiguredColour.CUBOIDBOX, ConfiguredColour.CUBOIDGRID, ConfiguredColour.CUBOIDPOINT1, ConfiguredColour.CUBOIDPOINT2);
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		if (this.points[0] != null && this.points[1] != null)
		{
			this.points[0].updatePoint();
			this.points[1].updatePoint();
			
			this.grid.render(cameraPos);
			this.box.render(cameraPos);
			
			this.points[0].render(cameraPos);
			this.points[1].render(cameraPos);
		}
		else if (this.points[0] != null)
		{
		    this.points[0].updatePoint();
			this.points[0].render(cameraPos);
		}
		else if (this.points[1] != null)
		{
		    this.points[1].updatePoint();
			this.points[1].render(cameraPos);
		}
	}
	
	@Override
	public void setGridSpacing(double spacing)
	{
		this.spacing = spacing;
		if (this.grid != null)
		{
			this.grid.setSpacing(spacing);
		}
	}
	
	@Override
	public void setCuboidPoint(int id, double x, double y, double z)
	{
		if (id < 2)
		{
			this.points[id] = new PointCube(x, y, z).setColour(this.colours[2]);
		}
		
		this.updateBounds();
	}
	
	@Override
	public void setCuboidVertexLatch(int id)
	{
		if (id < 2)
		{
			Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
			this.points[id] = new PointCubeTracking(renderViewEntity).setColour(this.colours[2]);
		}
		
		this.updateBounds();
	}

	private void updateBounds()
	{
		if (this.points[0] != null && this.points[1] != null)
		{
			BoundingBox bounds = new BoundingBox(this.points[0], this.points[1]);
			this.grid = new Render3DGrid(this.colours[1], bounds).setSpacing(this.spacing);
			this.box = new Render3DBox(this.colours[0], bounds);
		}
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
		
		if (this.points[0] != null)
		{
			this.points[0].setColour(this.colours[2]);
		}
		
		if (this.points[1] != null)
		{
			this.points[1].setColour(this.colours[3]);
		}
	}
	
	@Override
	public RegionType getType()
	{
		return RegionType.CUBOID;
	}
}
