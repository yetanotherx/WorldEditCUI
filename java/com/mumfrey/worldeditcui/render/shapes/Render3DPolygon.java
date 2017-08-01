package com.mumfrey.worldeditcui.render.shapes;

import static com.mumfrey.liteloader.gl.GL.*;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;

import com.mumfrey.worldeditcui.render.RenderStyle;
import com.mumfrey.worldeditcui.render.LineStyle;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Draws a polygon
 * 
 * @author yetanotherx
 * @author lahwran
 * @author Adam Mummery-Smith
 */
public class Render3DPolygon extends RenderRegion
{
	private Vector3[] vertices;
	
	public Render3DPolygon(RenderStyle style, Vector3... vertices)
	{
		super(style);
		this.vertices = vertices;
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		
		for (LineStyle line : this.style.getLines())
		{
			if (!line.prepare(this.style.getRenderType()))
			{
				continue;
			}
			
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			line.applyColour();
			for (Vector3 vertex : this.vertices)
			{
				buf.pos(vertex.getX() - cameraPos.getX(), vertex.getY() - cameraPos.getY(), vertex.getZ() - cameraPos.getZ()).endVertex();
			}
			tessellator.draw();
		}
	}
}
