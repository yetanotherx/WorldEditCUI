package com.mumfrey.worldeditcui.render.points;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import com.mumfrey.worldeditcui.util.Vector3;

/**
 * A PointCube which tracks the specified entity location
 * 
 * @author Adam Mummery-Smith
 */
public class PointCubeTracking extends PointCube
{
	private final Entity entity;
	private int lastX, lastY, lastZ;
	
	public PointCubeTracking(Entity entity)
	{
		super(0, 0, 0);
		this.entity = entity;
	}
	
	@Override
	public boolean isDynamic()
	{
		return true;
	}
	
	@Override
	public Vector3 getPoint()
	{
        this.updatePoint();
		return this.point;
	}

	@Override
	public void updatePoint()
	{
		int x = MathHelper.floor(this.entity.posX);
        int y = MathHelper.floor(this.entity.posY + this.entity.getEyeHeight());
        int z = MathHelper.floor(this.entity.posZ);
        
        if (this.lastX != x || this.lastY != y || this.lastZ != z)
        {
        	this.lastX = x;
        	this.lastY = y;
        	this.lastZ = z;
        	this.point = new Vector3(x, y, z);
        	this.box.setPosition(this.point.subtract(PointCube.MIN_VEC), this.point.add(PointCube.MAX_VEC));
        	this.notifyObservers();
        }
	}
}
