package com.mumfrey.worldeditcui.util;

import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.render.shapes.RenderRegion;

/**
 * @author Adam Mummery-Smith
 */
public class BoundingBox extends Observable<RenderRegion> implements Observer
{
	private static final double OFF = 0.02;

	private static final Vector3 MIN_VEC = new Vector3(BoundingBox.OFF, BoundingBox.OFF, BoundingBox.OFF);
	private static final Vector3 MAX_VEC = new Vector3(BoundingBox.OFF + 1, BoundingBox.OFF + 1, BoundingBox.OFF + 1);

	private final PointCube pc1, pc2;
	private Vector3 min, max;
	
	public BoundingBox(PointCube pc1, PointCube pc2)
	{
		this.pc1 = pc1;
		this.pc2 = pc2;
		
		this.update();
		
		if (this.pc1.isDynamic())
		{
			this.pc1.addObserver(this);
		}
		
		if (this.pc2.isDynamic())
		{
			this.pc2.addObserver(this);
		}
	}

	public Vector3 getMin()
	{
		return this.min;
	}

	public Vector3 getMax()
	{
		return this.max;
	}
	
	public boolean isDynamic()
	{
		return this.pc1.isDynamic() || this.pc2.isDynamic();
	}

	@Override
	public void notifyChanged(Observable<?> source)
	{
		if (source == this.pc1 || source == this.pc2)
		{
			this.update();
			this.notifyObservers();
		}
	}
	
	private void update()
	{
		Vector3 p1 = this.pc1.getPoint();
		Vector3 p2 = this.pc2.getPoint();
		this.min = new Vector3(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()), Math.min(p1.getZ(), p2.getZ())).subtract(BoundingBox.MIN_VEC);
		this.max = new Vector3(Math.max(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()), Math.max(p1.getZ(), p2.getZ())).add(BoundingBox.MAX_VEC);
	}
}
