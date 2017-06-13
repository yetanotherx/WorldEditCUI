package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.util.BoundingBox;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Draws a rectangular prism around 2 corners
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render3DBox
{
	
	protected LineColour colour;
	protected Vector3 first;
	protected Vector3 second;
	
	public Render3DBox(LineColour colour, BoundingBox region)
	{
		this(colour, region.getMin(), region.getMax());
	}
	
	public Render3DBox(LineColour colour, Vector3 first, Vector3 second)
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
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			// Draw bottom face
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			tempColour.prepareColour();
			buf.pos(x1, y1, z1).endVertex();
			buf.pos(x2, y1, z1).endVertex();
			buf.pos(x2, y1, z2).endVertex();
			buf.pos(x1, y1, z2).endVertex();
			tessellator.draw();
			
			// Draw top face
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			tempColour.prepareColour();
			buf.pos(x1, y2, z1).endVertex();
			buf.pos(x2, y2, z1).endVertex();
			buf.pos(x2, y2, z2).endVertex();
			buf.pos(x1, y2, z2).endVertex();
			tessellator.draw();
			
			// Draw join top and bottom faces
			buf.begin(GL_LINES, VF_POSITION);
			tempColour.prepareColour();
			
			buf.pos(x1, y1, z1).endVertex();
			buf.pos(x1, y2, z1).endVertex();
			
			buf.pos(x2, y1, z1).endVertex();
			buf.pos(x2, y2, z1).endVertex();
			
			buf.pos(x2, y1, z2).endVertex();
			buf.pos(x2, y2, z2).endVertex();
			
			buf.pos(x1, y1, z2).endVertex();
			buf.pos(x1, y2, z2).endVertex();
			
			tessellator.draw();
		}
	}
}
