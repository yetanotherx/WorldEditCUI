package com.mumfrey.worldeditcui.render.shapes;

import static com.mumfrey.liteloader.gl.GL.*;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;

import com.mumfrey.worldeditcui.render.RenderStyle;
import com.mumfrey.worldeditcui.render.LineStyle;
import com.mumfrey.worldeditcui.util.BoundingBox;
import com.mumfrey.worldeditcui.util.Observable;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Draws a rectangular prism around 2 corners
 * 
 * @author yetanotherx
 * @author lahwran
 * @author Adam Mummery-Smith
 */
public class Render3DBox extends RenderRegion
{
	private Vector3 first, second;
	
	public Render3DBox(RenderStyle style, BoundingBox region)
	{
		this(style, region.getMin(), region.getMax());
		if (region.isDynamic())
		{
			region.addObserver(this);
		}
	}
	
	public Render3DBox(RenderStyle style, Vector3 first, Vector3 second)
	{
		super(style);
		this.first = first;
		this.second = second;
	}
	
	@Override
	public void notifyChanged(Observable<?> source)
	{
		this.setPosition((BoundingBox)source);
	}
	
	public void setPosition(BoundingBox region)
	{
		this.setPosition(region.getMin(), region.getMax());
	}
	
	public void setPosition(Vector3 first, Vector3 second)
	{
		this.first = first;
		this.second = second;
	}
	
	@Override
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
		
		for (LineStyle line : this.style.getLines())
		{
			if (!line.prepare(this.style.getRenderType()))
			{
				continue;
			}
			
			// Draw bottom face
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			line.applyColour();
			buf.pos(x1, y1, z1).endVertex();
			buf.pos(x2, y1, z1).endVertex();
			buf.pos(x2, y1, z2).endVertex();
			buf.pos(x1, y1, z2).endVertex();
			tessellator.draw();
			
			// Draw top face
			buf.begin(GL_LINE_LOOP, VF_POSITION);
			line.applyColour();
			buf.pos(x1, y2, z1).endVertex();
			buf.pos(x2, y2, z1).endVertex();
			buf.pos(x2, y2, z2).endVertex();
			buf.pos(x1, y2, z2).endVertex();
			tessellator.draw();
			
			// Draw join top and bottom faces
			buf.begin(GL_LINES, VF_POSITION);
			line.applyColour();
			
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
