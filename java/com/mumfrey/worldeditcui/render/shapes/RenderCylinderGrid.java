package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.render.points.PointCube;

import net.minecraft.client.renderer.Tessellator;
import static org.lwjgl.opengl.GL11.*;

/**
 * Draws the grid lines around a cylindrical region
 * 
 * @author yetanotherx
 */
public class RenderCylinderGrid
{
	
	protected LineColour colour;
	protected double radX = 0;
	protected double radZ = 0;
	protected int minY;
	protected int maxY;
	protected double centerX;
	protected double centerZ;
	
	public RenderCylinderGrid(LineColour colour, PointCube center, double radX, double radZ, int minY, int maxY)
	{
		this.colour = colour;
		this.radX = radX;
		this.radZ = radZ;
		this.minY = minY;
		this.maxY = maxY;
		this.centerX = center.getPoint().getX() + 0.5;
		this.centerZ = center.getPoint().getZ() + 0.5;
	}
	
	public void render()
	{
		Tessellator tess = Tessellator.instance;
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			int tmaxY = this.maxY + 1;
			int tminY = this.minY;
			int posRadiusX = (int)Math.ceil(this.radX);
			int negRadiusX = (int)-Math.ceil(this.radX);
			int posRadiusZ = (int)Math.ceil(this.radZ);
			int negRadiusZ = (int)-Math.ceil(this.radZ);
			
			for (double tempX = negRadiusX; tempX <= posRadiusX; ++tempX)
			{
				double tempZ = this.radZ * Math.cos(Math.asin(tempX / this.radX));
				tess.startDrawing(GL_LINE_LOOP);
				tempColour.prepareColour();
				
				tess.addVertex(this.centerX + tempX, tmaxY, this.centerZ + tempZ);
				tess.addVertex(this.centerX + tempX, tmaxY, this.centerZ - tempZ);
				tess.addVertex(this.centerX + tempX, tminY, this.centerZ - tempZ);
				tess.addVertex(this.centerX + tempX, tminY, this.centerZ + tempZ);
				
				tess.draw();
			}
			
			for (double tempZ = negRadiusZ; tempZ <= posRadiusZ; ++tempZ)
			{
				double tempX = this.radX * Math.sin(Math.acos(tempZ / this.radZ));
				tess.startDrawing(GL_LINE_LOOP);
				tempColour.prepareColour();
				
				tess.addVertex(this.centerX + tempX, tmaxY, this.centerZ + tempZ);
				tess.addVertex(this.centerX - tempX, tmaxY, this.centerZ + tempZ);
				tess.addVertex(this.centerX - tempX, tminY, this.centerZ + tempZ);
				tess.addVertex(this.centerX + tempX, tminY, this.centerZ + tempZ);
				
				tess.draw();
			}
		}
	}
}
