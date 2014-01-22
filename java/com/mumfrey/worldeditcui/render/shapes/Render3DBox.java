package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import static org.lwjgl.opengl.GL11.*;

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
	
	public Render3DBox(LineColour colour, Vector3 first, Vector3 second)
	{
		this.colour = colour;
		this.first = first;
		this.second = second;
	}
	
	public void render()
	{
		Tessellator tess = Tessellator.instance;
		double x1 = this.first.getX();
		double y1 = this.first.getY();
		double z1 = this.first.getZ();
		double x2 = this.second.getX();
		double y2 = this.second.getY();
		double z2 = this.second.getZ();
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			// Draw bottom face
			tess.startDrawing(GL_LINE_LOOP);
			tempColour.prepareColour();
			tess.addVertex(x1, y1, z1);
			tess.addVertex(x2, y1, z1);
			tess.addVertex(x2, y1, z2);
			tess.addVertex(x1, y1, z2);
			tess.draw();
			
			// Draw top face
			tess.startDrawing(GL_LINE_LOOP);
			tempColour.prepareColour();
			tess.addVertex(x1, y2, z1);
			tess.addVertex(x2, y2, z1);
			tess.addVertex(x2, y2, z2);
			tess.addVertex(x1, y2, z2);
			tess.draw();
			
			// Draw join top and bottom faces
			tess.startDrawing(GL_LINES);
			tempColour.prepareColour();
			
			tess.addVertex(x1, y1, z1);
			tess.addVertex(x1, y2, z1);
			
			tess.addVertex(x2, y1, z1);
			tess.addVertex(x2, y2, z1);
			
			tess.addVertex(x2, y1, z2);
			tess.addVertex(x2, y2, z2);
			
			tess.addVertex(x1, y1, z2);
			tess.addVertex(x1, y2, z2);
			
			tess.draw();
		}
	}
}
