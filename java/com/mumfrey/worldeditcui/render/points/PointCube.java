package com.mumfrey.worldeditcui.render.points;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.shapes.Render3DBox;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Stores data about a cube surrounding a
 * block in the world. Used to store info
 * about the selector blocks. Keeps track
 * of colour, x/y/z values, and rendering.
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class PointCube
{
	
	protected Vector3 point;
	protected LineColour colour = LineColour.CUBOIDPOINT1;
	
	public PointCube(Vector3 point)
	{
		this.point = point;
	}
	
	public PointCube(int x, int y, int z)
	{
		this.point = new Vector3(x, y, z);
	}
	
	public PointCube(double x, double y, double z)
	{
		this.point = new Vector3(x, y, z);
	}
	
	public void render()
	{
		double off = 0.03f;
		Vector3 minVec = new Vector3(off, off, off);
		Vector3 maxVec = new Vector3(off + 1, off + 1, off + 1);
		
		new Render3DBox(this.colour, this.point.subtract(minVec), this.point.add(maxVec)).render();
	}
	
	public Vector3 getPoint()
	{
		return this.point;
	}
	
	public void setPoint(Vector3 point)
	{
		this.point = point;
	}
	
	public LineColour getColour()
	{
		return this.colour;
	}
	
	public void setColour(LineColour colour)
	{
		this.colour = colour;
	}
}
