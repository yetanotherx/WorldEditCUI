package wecui.render.region;

import wecui.WorldEditCUI;
import wecui.render.LineColor;
import wecui.render.points.PointCube;
import wecui.render.shapes.RenderCylinderBox;
import wecui.render.shapes.RenderCylinderCircles;
import wecui.render.shapes.RenderCylinderGrid;

/**
 * Main controller for a cylinder-type region
 * 
 * @author yetanotherx
 */
public class CylinderRegion extends BaseRegion
{
	
	protected PointCube center;
	protected double radX = 0;
	protected double radZ = 0;
	protected int minY = 0;
	protected int maxY = 0;
	
	public CylinderRegion(WorldEditCUI controller)
	{
		super(controller);
	}
	
	@Override
	public void render()
	{
		if (this.center != null)
		{
			this.center.render();
			
			int tMin = this.minY;
			int tMax = this.maxY;
			
			if (this.minY == 0 || this.maxY == 0)
			{
				tMin = (int)this.center.getPoint().getY();
				tMax = (int)this.center.getPoint().getY();
			}
			
			new RenderCylinderCircles(LineColor.CYLINDERGRID, this.center, this.radX, this.radZ, tMin, tMax).render();
			new RenderCylinderGrid(LineColor.CYLINDERGRID, this.center, this.radX, this.radZ, tMin, tMax).render();
			new RenderCylinderBox(LineColor.CYLINDERBOX, this.center, this.radX, this.radZ, tMin, tMax).render();
			
		}
	}
	
	@Override
	public void setCylinderCenter(int x, int y, int z)
	{
		this.center = new PointCube(x, y, z);
		this.center.setColor(LineColor.CYLINDERCENTER);
	}
	
	@Override
	public void setCylinderRadius(double x, double z)
	{
		this.radX = x;
		this.radZ = z;
	}
	
	@Override
	public void setMinMax(int min, int max)
	{
		this.minY = min;
		this.maxY = max;
	}
	
	@Override
	public RegionType getType()
	{
		return RegionType.CYLINDER;
	}
}
