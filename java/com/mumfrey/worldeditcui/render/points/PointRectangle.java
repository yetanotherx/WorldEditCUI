package com.mumfrey.worldeditcui.render.points;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.shapes.Render3DBox;
import com.mumfrey.worldeditcui.util.Vector2;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Stores data about a prism surrounding two
 * blocks in the world. Used to store info
 * about the selector blocks for polys. Keeps 
 * track of colour, x/y/z values, and rendering.
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class PointRectangle
{
	
	protected Vector2 point;
	protected LineColour colour = LineColour.POLYPOINT;
	
	public PointRectangle(Vector2 point)
	{
		this.point = point;
	}
	
	public PointRectangle(int x, int z)
	{
		this.point = new Vector2(x, z);
	}
	
	public void render(Vector3 cameraPos, int min, int max)
	{
		float off = 0.03f;
		Vector2 minVec = new Vector2(off, off);
		Vector2 maxVec = new Vector2(off + 1, off + 1);
		
		new Render3DBox(this.colour, this.point.subtract(minVec).toVector3(min - off), this.point.add(maxVec).toVector3(max + 1 + off)).render(cameraPos);
	}
	
	public Vector2 getPoint()
	{
		return this.point;
	}
	
	public void setPoint(Vector2 point)
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
