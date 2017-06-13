package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Draws a polygon
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render3DPolygon
{
	
	protected LineColour colour;
	protected Vector3[] vertices;
	
	public Render3DPolygon(LineColour colour, Vector3... vertices)
	{
		this.colour = colour;
		this.vertices = vertices;
	}
	
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			tempColour.prepareColour();
			for (Vector3 vertex : this.vertices)
			{
				buf.pos(vertex.getX() - cameraPos.getX(), vertex.getY() - cameraPos.getY(), vertex.getZ() - cameraPos.getZ()).endVertex();
			}
			tessellator.draw();
		}
	}
}
