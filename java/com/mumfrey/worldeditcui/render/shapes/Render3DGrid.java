package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
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
	
	public Render3DGrid(LineColour colour, Vector3 first, Vector3 second)
	{
		this.colour = colour;
		this.first = first;
		this.second = second;
	}
	
	public void render()
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		double x1 = this.first.getX();
		double y1 = this.first.getY();
		double z1 = this.first.getZ();
		double x2 = this.second.getX();
		double y2 = this.second.getY();
		double z2 = this.second.getZ();
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			worldRenderer.startDrawing(GL_LINES);
			tempColour.prepareColour();
			
			double x, y, z;
			double offsetSize = 1.0;
			
			// Zmax XY plane, y axis
			z = z2;
			y = y1;
			int msize = 150;
			if ((y2 - y / offsetSize) < msize)
			{
				for (double yoff = 0; yoff + y <= y2; yoff += offsetSize)
				{
					worldRenderer.addVertex(x1, y + yoff, z);
					worldRenderer.addVertex(x2, y + yoff, z);
				}
			}
			
			// Zmin XY plane, y axis
			z = z1;
			if ((y2 - y / offsetSize) < msize)
			{
				for (double yoff = 0; yoff + y <= y2; yoff += offsetSize)
				{
					worldRenderer.addVertex(x1, y + yoff, z);
					worldRenderer.addVertex(x2, y + yoff, z);
				}
			}
			
			// Xmin YZ plane, y axis
			x = x1;
			if ((y2 - y / offsetSize) < msize)
			{
				for (double yoff = 0; yoff + y <= y2; yoff += offsetSize)
				{
					worldRenderer.addVertex(x, y + yoff, z1);
					worldRenderer.addVertex(x, y + yoff, z2);
				}
			}
			
			// Xmax YZ plane, y axis
			x = x2;
			if ((y2 - y / offsetSize) < msize)
			{
				for (double yoff = 0; yoff + y <= y2; yoff += offsetSize)
				{
					worldRenderer.addVertex(x, y + yoff, z1);
					worldRenderer.addVertex(x, y + yoff, z2);
				}
			}
			
			// Zmin XY plane, x axis
			x = x1;
			z = z1;
			if ((x2 - x / offsetSize) < msize)
			{
				for (double xoff = 0; xoff + x <= x2; xoff += offsetSize)
				{
					worldRenderer.addVertex(x + xoff, y1, z);
					worldRenderer.addVertex(x + xoff, y2, z);
				}
			}
			// Zmax XY plane, x axis
			z = z2;
			if ((x2 - x / offsetSize) < msize)
			{
				for (double xoff = 0; xoff + x <= x2; xoff += offsetSize)
				{
					worldRenderer.addVertex(x + xoff, y1, z);
					worldRenderer.addVertex(x + xoff, y2, z);
				}
			}
			// Ymin XZ plane, x axis
			y = y2;
			if ((x2 - x / offsetSize) < msize)
			{
				for (double xoff = 0; xoff + x <= x2; xoff += offsetSize)
				{
					worldRenderer.addVertex(x + xoff, y, z1);
					worldRenderer.addVertex(x + xoff, y, z2);
				}
			}
			// Ymax XZ plane, x axis
			y = y1;
			if ((x2 - x / offsetSize) < msize)
			{
				for (double xoff = 0; xoff + x <= x2; xoff += offsetSize)
				{
					worldRenderer.addVertex(x + xoff, y, z1);
					worldRenderer.addVertex(x + xoff, y, z2);
				}
			}
			
			// Ymin XZ plane, z axis
			z = z1;
			y = y1;
			if ((z2 - z / offsetSize) < msize)
			{
				for (double zoff = 0; zoff + z <= z2; zoff += offsetSize)
				{
					worldRenderer.addVertex(x1, y, z + zoff);
					worldRenderer.addVertex(x2, y, z + zoff);
				}
			}
			// Ymax XZ plane, z axis
			y = y2;
			if ((z2 - z / offsetSize) < msize)
			{
				for (double zoff = 0; zoff + z <= z2; zoff += offsetSize)
				{
					worldRenderer.addVertex(x1, y, z + zoff);
					worldRenderer.addVertex(x2, y, z + zoff);
				}
			}
			// Xmin YZ plane, z axis
			x = x2;
			if ((z2 - z / offsetSize) < msize)
			{
				for (double zoff = 0; zoff + z <= z2; zoff += offsetSize)
				{
					worldRenderer.addVertex(x, y1, z + zoff);
					worldRenderer.addVertex(x, y2, z + zoff);
				}
			}
			// Xmax YZ plane, z axis
			x = x1;
			if ((z2 - z / offsetSize) < msize)
			{
				for (double zoff = 0; zoff + z <= z2; zoff += offsetSize)
				{
					worldRenderer.addVertex(x, y1, z + zoff);
					worldRenderer.addVertex(x, y2, z + zoff);
				}
			}
			
			tessellator.draw();
		}
	}
}
