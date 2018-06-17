package com.mumfrey.worldeditcui.render.shapes;

import static com.mumfrey.liteloader.gl.GL.*;

import java.util.List;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;

import com.mumfrey.worldeditcui.render.RenderStyle;
import com.mumfrey.worldeditcui.render.LineStyle;
import com.mumfrey.worldeditcui.render.points.PointRectangle;
import com.mumfrey.worldeditcui.util.Vector2;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Draws the grid for a polygon region
 * 
 * @author yetanotherx
 * @author lahwran
 * @author Adam Mummery-Smith
 */
public class Render2DGrid extends RenderRegion
{
	private List<PointRectangle> points;
	private int min, max;
	
	public Render2DGrid(RenderStyle style, List<PointRectangle> points, int min, int max)
	{
		super(style);
		this.points = points;
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		double off = 0.03;
		for (double height = this.min; height <= this.max + 1; height++)
		{
			this.drawPoly(cameraPos, height + off);
		}
	}
	
	protected void drawPoly(Vector3 cameraPos, double height)
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
			for (PointRectangle point : this.points)
			{
				if (point != null)
				{
					Vector2 pos = point.getPoint();
					double x = pos.getX() - cameraPos.getX();
					double z = pos.getY() - cameraPos.getZ();
					buf.pos(x + 0.5, height - cameraPos.getY(), z + 0.5).endVertex();
				}
			}
			tessellator.draw();
		}
	}
}
