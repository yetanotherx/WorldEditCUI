package com.mumfrey.worldeditcui.render.points;

import com.mumfrey.liteloader.util.EntityUtilities;
import com.mumfrey.worldeditcui.util.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

/**
 * A PointCube which tracks the specified entity location
 * 
 * @author Adam Mummery-Smith
 */
public class PointCubeTracking extends PointCube
{
	private final Entity entity;
	private final double traceDistance;
	private int lastX, lastY, lastZ;
	
	public PointCubeTracking(Entity entity, double traceDistance)
	{
		super(0, 0, 0);
		this.entity = entity;
		this.traceDistance = traceDistance;
	}
	
	@Override
	public boolean isDynamic()
	{
		return true;
	}
	
	@Override
	public Vector3 getPoint()
	{
		return this.point;
	}

	@Override
	public void updatePoint(float partialTicks)
	{
		BlockPos pos = EntityUtilities.rayTraceFromEntity(this.entity, this.traceDistance, partialTicks, false).getBlockPos();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
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
