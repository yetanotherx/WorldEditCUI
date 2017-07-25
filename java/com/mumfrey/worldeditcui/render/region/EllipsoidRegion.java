package com.mumfrey.worldeditcui.render.region;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.render.ConfiguredColour;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.render.shapes.RenderEllipsoid;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Main controller for a ellipsoid-type region
 * 
 * @author yetanotherx
 * @author lahwran
 * @author Adam Mummery-Smith
 */
public class EllipsoidRegion extends Region
{
	private PointCube centre;
	private Vector3 radii;
	
	private RenderEllipsoid ellipsoid;
	
	public EllipsoidRegion(WorldEditCUI controller)
	{
		super(controller, ConfiguredColour.ELLIPSOIDGRID, ConfiguredColour.ELLIPSOIDCENTRE);
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		if (this.centre != null && this.radii != null)
		{
			this.centre.render(cameraPos);
			this.ellipsoid.render(cameraPos);
		}
		else if (this.centre != null)
		{
			this.centre.render(cameraPos);
		}
	}
	
	@Override
	public void setEllipsoidCenter(int x, int y, int z)
	{
		this.centre = new PointCube(x, y, z);
		this.centre.setColour(this.colours[1]);
		this.update();
	}
	
	@Override
	public void setEllipsoidRadii(double x, double y, double z)
	{
		this.radii = new Vector3(x, y, z);
		this.update();
	}

	private void update()
	{
		if (this.centre != null && this.radii != null)
		{
			this.ellipsoid = new RenderEllipsoid(this.colours[0], this.centre, this.radii);
		}
	}
	
	@Override
	protected void updateColours()
	{
		if (this.ellipsoid != null)
		{
			this.ellipsoid.setColour(this.colours[0]);
		}
		
		if (this.centre != null)
		{
			this.centre.setColour(this.colours[1]);
		}
	}

	@Override
	public RegionType getType()
	{
		return RegionType.ELLIPSOID;
	}
	
}
