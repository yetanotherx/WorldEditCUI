package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.util.BoundingBox;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Draws the grid for a region between
 * two corners in a cuboid region.
 * 
 * @author yetanotherx
 */
public class Render3DGrid
{
	
	protected LineColour colour;
	protected Vector3 first;
	protected Vector3 second;
	
	public Render3DGrid(LineColour colour, BoundingBox region)
	{
		this(colour, region.getMin(), region.getMax());
	}
	
	public Render3DGrid(LineColour colour, Vector3 first, Vector3 second)
	{
		this.colour = colour;
		this.first = first;
		this.second = second;
	}
	
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		double x1 = this.first.getX() - cameraPos.getX();
		double y1 = this.first.getY() - cameraPos.getY();
		double z1 = this.first.getZ() - cameraPos.getZ();
		double x2 = this.second.getX() - cameraPos.getX();
		double y2 = this.second.getY() - cameraPos.getY();
		double z2 = this.second.getZ() - cameraPos.getZ();
		
		double cullAt = 128.0F;
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			buf.begin(GL_LINES, VF_POSITION);
			tempColour.prepareColour();
			
			double offsetSize = 1.0;
			
			for (double yoff = 0; yoff + y1 <= y2; yoff += offsetSize)
			{
				double y = y1 + yoff;
				buf.pos(x1, y, z2).endVertex();
				buf.pos(x2, y, z2).endVertex();
				buf.pos(x1, y, z1).endVertex();
				buf.pos(x2, y, z1).endVertex();
				buf.pos(x1, y, z1).endVertex();
				buf.pos(x1, y, z2).endVertex();
				buf.pos(x2, y, z1).endVertex();
				buf.pos(x2, y, z2).endVertex();
			}
			
			for (double xoff = 0; xoff + x1 <= x2; xoff += offsetSize)
			{
				double x = x1 + xoff;
//				boolean major = xoff % 10 == 0;
				if (x < -cullAt) continue;
				if (x > cullAt) break;
				buf.pos(x, y1, z1).endVertex();
				buf.pos(x, y2, z1).endVertex();
				buf.pos(x, y1, z2).endVertex();
				buf.pos(x, y2, z2).endVertex();
				buf.pos(x, y2, z1).endVertex();
				buf.pos(x, y2, z2).endVertex();
				buf.pos(x, y1, z1).endVertex();
				buf.pos(x, y1, z2).endVertex();
			}
			
			for (double zoff = 0; zoff + z1 <= z2; zoff += offsetSize)
			{
				double z = z1 + zoff;
//				boolean major = zoff % 10 == 0;
				if (z < -cullAt) continue;
				if (z > cullAt) break;
				buf.pos(x1, y1, z).endVertex();
				buf.pos(x2, y1, z).endVertex();
				buf.pos(x1, y2, z).endVertex();
				buf.pos(x2, y2, z).endVertex();
				buf.pos(x2, y1, z).endVertex();
				buf.pos(x2, y2, z).endVertex();
				buf.pos(x1, y1, z).endVertex();
				buf.pos(x1, y2, z).endVertex();
			}
			
			tessellator.draw();
		}
	}
}
