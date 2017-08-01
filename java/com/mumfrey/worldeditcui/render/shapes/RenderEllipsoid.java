package com.mumfrey.worldeditcui.render.shapes;

import static com.mumfrey.liteloader.gl.GL.*;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;

import com.mumfrey.worldeditcui.render.RenderStyle;
import com.mumfrey.worldeditcui.render.LineStyle;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Draws an ellipsoid shape around a centre point.
 * 
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class RenderEllipsoid extends RenderRegion
{
	protected final static double TAU = Math.PI * 2.0;
	
	protected PointCube centre;
	protected Vector3 radii;
	
	protected double centreX, centreY, centreZ;
	
	public RenderEllipsoid(RenderStyle style, PointCube centre, Vector3 radii)
	{
		super(style);
		this.centre = centre;
		this.radii = radii;
		this.centreX = centre.getPoint().getX() + 0.5;
		this.centreY = centre.getPoint().getY() + 0.5;
		this.centreZ = centre.getPoint().getZ() + 0.5;
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		glPushMatrix();
		glTranslated(this.centreX - cameraPos.getX(), this.centreY - cameraPos.getY(), this.centreZ - cameraPos.getZ());
		
		for (LineStyle line : this.style.getLines())
		{
			if (line.prepare(this.style.getRenderType()))
			{
				this.drawXZPlane(line);
				this.drawYZPlane(line);
				this.drawXYPlane(line);
			}
		}
		
		glPopMatrix();
	}
	
	protected void drawXZPlane(LineStyle line)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();

		int yRad = (int)Math.floor(this.radii.getY());
		for (int yBlock = -yRad; yBlock < yRad; yBlock++)
		{
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			line.applyColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * TAU / 40;
				double tempX = this.radii.getX() * Math.cos(tempTheta) * Math.cos(Math.asin(yBlock / this.radii.getY()));
				double tempZ = this.radii.getZ() * Math.sin(tempTheta) * Math.cos(Math.asin(yBlock / this.radii.getY()));
				
				buf.pos(tempX, yBlock, tempZ).endVertex();
			}
			tessellator.draw();
		}
		
		buf.begin(GL_LINE_LOOP, VF_POSITION);
		line.applyColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * TAU / 40;
			double tempX = this.radii.getX() * Math.cos(tempTheta);
			double tempZ = this.radii.getZ() * Math.sin(tempTheta);
			
			buf.pos(tempX, 0.0, tempZ).endVertex();
		}
		tessellator.draw();
	}
	
	protected void drawYZPlane(LineStyle line)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();

		int xRad = (int)Math.floor(this.radii.getX());
		for (int xBlock = -xRad; xBlock < xRad; xBlock++)
		{
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			line.applyColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * TAU / 40;
				double tempY = this.radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(xBlock / this.radii.getX()));
				double tempZ = this.radii.getZ() * Math.sin(tempTheta) * Math.sin(Math.acos(xBlock / this.radii.getX()));
				
				buf.pos(xBlock, tempY, tempZ).endVertex();
			}
			tessellator.draw();
		}
		
		buf.begin(GL_LINE_LOOP, VF_POSITION);
		line.applyColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * TAU / 40;
			double tempY = this.radii.getY() * Math.cos(tempTheta);
			double tempZ = this.radii.getZ() * Math.sin(tempTheta);
			
			buf.pos(0.0, tempY, tempZ).endVertex();
		}
		tessellator.draw();
	}
	
	protected void drawXYPlane(LineStyle line)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();

		int zRad = (int)Math.floor(this.radii.getZ());
		for (int zBlock = -zRad; zBlock < zRad; zBlock++)
		{
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			line.applyColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * TAU / 40;
				double tempX = this.radii.getX() * Math.sin(tempTheta) * Math.sin(Math.acos(zBlock / this.radii.getZ()));
				double tempY = this.radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(zBlock / this.radii.getZ()));
				
				buf.pos(tempX, tempY, zBlock).endVertex();
			}
			tessellator.draw();
		}
		
		buf.begin(GL_LINE_LOOP, VF_POSITION);
		line.applyColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * TAU / 40;
			double tempX = this.radii.getX() * Math.cos(tempTheta);
			double tempY = this.radii.getY() * Math.sin(tempTheta);
			
			buf.pos(tempX, tempY, 0.0).endVertex();
		}
		tessellator.draw();
	}
}
