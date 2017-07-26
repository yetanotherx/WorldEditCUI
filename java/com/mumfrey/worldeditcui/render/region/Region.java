package com.mumfrey.worldeditcui.render.region;

import com.mumfrey.worldeditcui.InitialisationFactory;
import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.exceptions.InvalidSelectionTypeException;
import com.mumfrey.worldeditcui.render.RenderColour;
import com.mumfrey.worldeditcui.util.Vector3;
import net.minecraft.entity.Entity;

/**
 * Base region storage class. Provides
 * abstract methods for setting various
 * points in the region. 
 * 
 * @author yetanotherx
 * @author lahwran
 * @author Adam Mummery-Smith
 */
public abstract class Region implements InitialisationFactory
{
	protected final WorldEditCUI controller;
	protected final RenderColour[] defaultColours;
	protected RenderColour[] colours;
	
	protected Region(WorldEditCUI controller, RenderColour... colours)
	{
		this.controller = controller;
		this.colours = this.defaultColours = colours;
	}
	
	@Override
	public void initialise()
	{
	}
	
	public abstract void render(Vector3 cameraPos, float partialTicks);
	
	public RenderColour[] getDefaultColours()
	{
		return this.defaultColours;
	}
	
	public void setColours(RenderColour... colours)
	{
		if (colours.length < this.defaultColours.length)
		{
			throw new IllegalArgumentException("Invalid colour palette supplied for " + this.getType().getName() + " region");
		}
		
		this.colours = colours;
		this.updateColours();
	}
	
	protected abstract void updateColours();

	public void setGridSpacing(double spacing)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setGridSpacing");
	}
	
	public void setCuboidPoint(int id, double x, double y, double z)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setCuboidPoint");
	}
	
	public void setCuboidVertexLatch(int id, Entity entity, double traceDistance)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setCuboidVertexLatch");
	}
	
	public void setPolygonPoint(int id, int x, int z)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setPolygonPoint");
	}
	
	public void setEllipsoidCenter(int x, int y, int z)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setEllipsoidCenter");
	}
	
	public void setEllipsoidRadii(double x, double y, double z)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setEllipsoidRadii");
	}
	
	public void setMinMax(int min, int max)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setMinMax");
	}
	
	public void setCylinderCenter(int x, int y, int z)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setCylinderCenter");
	}
	
	public void setCylinderRadius(double x, double z)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "setCylinderRadius");
	}
	
	public void addPolygon(int[] vertexIds)
	{
		throw new InvalidSelectionTypeException(this.getType().getName(), "addPolygon");
	}
	
	public abstract RegionType getType();
}
