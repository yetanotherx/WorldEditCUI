package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Draws an ellipsoid shape around a center point.
 * 
 * @author yetanotherx
 */
public class RenderEllipsoid
{
	
	protected LineColour colour;
	protected PointCube center;
	protected Vector3 radii;
	protected final static double twoPi = Math.PI * 2;
	protected double centerX;
	protected double centerY;
	protected double centerZ;
	
	public RenderEllipsoid(LineColour colour, PointCube center, Vector3 radii)
	{
		this.colour = colour;
		this.center = center;
		this.radii = radii;
		this.centerX = center.getPoint().getX() + 0.5;
		this.centerY = center.getPoint().getY() + 0.5;
		this.centerZ = center.getPoint().getZ() + 0.5;
	}
	
	public void render()
	{
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			this.drawXZPlane(tempColour);
			this.drawYZPlane(tempColour);
			this.drawXYPlane(tempColour);
		}
	}
	
	protected void drawXZPlane(LineInfo colour)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();

		int yRad = (int)Math.floor(this.radii.getY());
		for (int yBlock = -yRad; yBlock < yRad; yBlock++)
		{
			worldRenderer.startDrawing(GL_LINE_LOOP);
			colour.prepareColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * twoPi / 40;
				double tempX = this.radii.getX() * Math.cos(tempTheta) * Math.cos(Math.asin(yBlock / this.radii.getY()));
				double tempZ = this.radii.getZ() * Math.sin(tempTheta) * Math.cos(Math.asin(yBlock / this.radii.getY()));
				
				worldRenderer.addVertex(this.centerX + tempX, this.centerY + yBlock, this.centerZ + tempZ);
			}
			tessellator.draw();
		}
		
		worldRenderer.startDrawing(GL_LINE_LOOP);
		colour.prepareColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * twoPi / 40;
			double tempX = this.radii.getX() * Math.cos(tempTheta);
			double tempZ = this.radii.getZ() * Math.sin(tempTheta);
			
			worldRenderer.addVertex(this.centerX + tempX, this.centerY, this.centerZ + tempZ);
		}
		tessellator.draw();
	}
	
	protected void drawYZPlane(LineInfo colour)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();

		int xRad = (int)Math.floor(this.radii.getX());
		for (int xBlock = -xRad; xBlock < xRad; xBlock++)
		{
			worldRenderer.startDrawing(GL_LINE_LOOP);
			colour.prepareColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * twoPi / 40;
				double tempY = this.radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(xBlock / this.radii.getX()));
				double tempZ = this.radii.getZ() * Math.sin(tempTheta) * Math.sin(Math.acos(xBlock / this.radii.getX()));
				
				worldRenderer.addVertex(this.centerX + xBlock, this.centerY + tempY, this.centerZ + tempZ);
			}
			tessellator.draw();
		}
		
		worldRenderer.startDrawing(GL_LINE_LOOP);
		colour.prepareColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * twoPi / 40;
			double tempY = this.radii.getY() * Math.cos(tempTheta);
			double tempZ = this.radii.getZ() * Math.sin(tempTheta);
			
			worldRenderer.addVertex(this.centerX, this.centerY + tempY, this.centerZ + tempZ);
		}
		tessellator.draw();
	}
	
	protected void drawXYPlane(LineInfo colour)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();

		int zRad = (int)Math.floor(this.radii.getZ());
		for (int zBlock = -zRad; zBlock < zRad; zBlock++)
		{
			worldRenderer.startDrawing(GL_LINE_LOOP);
			colour.prepareColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * twoPi / 40;
				double tempX = this.radii.getX() * Math.sin(tempTheta) * Math.sin(Math.acos(zBlock / this.radii.getZ()));
				double tempY = this.radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(zBlock / this.radii.getZ()));
				
				worldRenderer.addVertex(this.centerX + tempX, this.centerY + tempY, this.centerZ + zBlock);
			}
			tessellator.draw();
		}
		
		worldRenderer.startDrawing(GL_LINE_LOOP);
		colour.prepareColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * twoPi / 40;
			double tempX = this.radii.getX() * Math.cos(tempTheta);
			double tempY = this.radii.getY() * Math.sin(tempTheta);
			
			worldRenderer.addVertex(this.centerX + tempX, this.centerY + tempY, this.centerZ);
		}
		tessellator.draw();
	}
}
