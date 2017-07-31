package com.mumfrey.worldeditcui.render.shapes;

import static com.mumfrey.liteloader.gl.GL.*;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;

import com.mumfrey.worldeditcui.render.RenderStyle;
import com.mumfrey.worldeditcui.render.LineStyle;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Draws the grid lines around a cylindrical region
 * 
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class RenderCylinderGrid extends RenderRegion
{
	protected double radX = 0;
	protected double radZ = 0;
	protected int minY;
	protected int maxY;
	protected double centreX;
	protected double centreZ;
	
	public RenderCylinderGrid(RenderStyle style, PointCube centre, double radX, double radZ, int minY, int maxY)
	{
		super(style);
		this.radX = radX;
		this.radZ = radZ;
		this.minY = minY;
		this.maxY = maxY;
		this.centreX = centre.getPoint().getX() + 0.5;
		this.centreZ = centre.getPoint().getZ() + 0.5;
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		
		double xPos = this.centreX - cameraPos.getX();
		double zPos = this.centreZ - cameraPos.getZ();

		for (LineStyle line : this.style.getLines())
		{
			if (!line.prepare(this.style.getRenderType()))
			{
				continue;
			}
			
			int tmaxY = this.maxY + 1;
			int tminY = this.minY;
			int posRadiusX = (int)Math.ceil(this.radX);
			int negRadiusX = (int)-Math.ceil(this.radX);
			int posRadiusZ = (int)Math.ceil(this.radZ);
			int negRadiusZ = (int)-Math.ceil(this.radZ);
			
			for (double tempX = negRadiusX; tempX <= posRadiusX; ++tempX)
			{
				double tempZ = this.radZ * Math.cos(Math.asin(tempX / this.radX));
				buf.begin(GL_LINE_LOOP, VF_POSITION);
				line.applyColour();
				
				buf.pos(xPos + tempX, tmaxY - cameraPos.getY(), zPos + tempZ).endVertex();
				buf.pos(xPos + tempX, tmaxY - cameraPos.getY(), zPos - tempZ).endVertex();
				buf.pos(xPos + tempX, tminY - cameraPos.getY(), zPos - tempZ).endVertex();
				buf.pos(xPos + tempX, tminY - cameraPos.getY(), zPos + tempZ).endVertex();
				
				tessellator.draw();
			}
			
			for (double tempZ = negRadiusZ; tempZ <= posRadiusZ; ++tempZ)
			{
				double tempX = this.radX * Math.sin(Math.acos(tempZ / this.radZ));
				buf.begin(GL_LINE_LOOP, VF_POSITION);
				line.applyColour();
				
				buf.pos(xPos + tempX, tmaxY - cameraPos.getY(), zPos + tempZ).endVertex();
				buf.pos(xPos - tempX, tmaxY - cameraPos.getY(), zPos + tempZ).endVertex();
				buf.pos(xPos - tempX, tminY - cameraPos.getY(), zPos + tempZ).endVertex();
				buf.pos(xPos + tempX, tminY - cameraPos.getY(), zPos + tempZ).endVertex();
				
				tessellator.draw();
			}
		}
	}
}
