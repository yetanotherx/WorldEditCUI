package com.mumfrey.worldeditcui.render.shapes;

import static com.mumfrey.liteloader.gl.GL.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.chunk.Chunk;

import com.mumfrey.worldeditcui.render.LineStyle;
import com.mumfrey.worldeditcui.render.RenderStyle;
import com.mumfrey.worldeditcui.util.Vector3;

public class RenderChunkBoundary extends RenderRegion
{
	private final Minecraft mc;
	private Render3DGrid grid;
	
	public RenderChunkBoundary(RenderStyle boundaryStyle, RenderStyle gridStyle, Minecraft minecraft)
	{
		super(boundaryStyle);

		this.mc = minecraft;
		
		this.grid = new Render3DGrid(gridStyle, Vector3.ZERO, Vector3.ZERO);
		this.grid.setSpacing(4.0);
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		double yMax = this.mc.world != null ? this.mc.world.getHeight() : 256.0;
		double yMin = 0.0;
		
		long xBlock = MathHelper.floor(cameraPos.getX());
		long zBlock = MathHelper.floor(cameraPos.getZ());
		
		int xChunk = (int)(xBlock >> 4);
		int zChunk = (int)(zBlock >> 4);
		
		double xBase = 0 - (xBlock - (xChunk * 16)) - (cameraPos.getX() - xBlock);
		double zBase = (0 - (zBlock - (zChunk * 16)) - (cameraPos.getZ() - zBlock)) + 16;
		
		this.grid.setPosition(new Vector3(xBase, yMin, zBase - 16), new Vector3(xBase + 16, yMax, zBase));
		
		glPushMatrix();
		glTranslated(0.0, -cameraPos.getY(), 0.0);

		this.grid.render(Vector3.ZERO);

		this.renderChunkBorder(yMin, yMax, xBase, zBase);
		
		if (this.mc.world != null)
		{
			this.renderChunkBoundary(xChunk, zChunk, xBase, zBase);
		}

		glPopMatrix();
	}

	private void renderChunkBorder(double yMin, double yMax, double xBase, double zBase)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();

		int spacing = 16;
		
		for (LineStyle line : this.style.getLines())
		{
			if (line.prepare(this.style.getRenderType()))
			{
				buf.begin(GL_LINES, VF_POSITION);
				line.applyColour();
				
				for (int x = -16; x <= 32; x += spacing)
				{
					for (int z = -16; z <= 32; z += spacing)
					{
						buf.pos(xBase + x, yMin, zBase - z).endVertex();
						buf.pos(xBase + x, yMax, zBase - z).endVertex();
					}
				}
				
				for (double y = yMin; y <= yMax; y += yMax)
				{
					buf.pos(xBase, y, zBase).endVertex();
					buf.pos(xBase, y, zBase - 16).endVertex();
					buf.pos(xBase, y, zBase - 16).endVertex();
					buf.pos(xBase + 16, y, zBase - 16).endVertex();
					buf.pos(xBase + 16, y, zBase - 16).endVertex();
					buf.pos(xBase + 16, y, zBase).endVertex();
					buf.pos(xBase + 16, y, zBase).endVertex();
					buf.pos(xBase, y, zBase).endVertex();
				}

				tessellator.draw();
			}
		}
	}

	private void renderChunkBoundary(int xChunk, int zChunk, double xBase, double zBase)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();

		Chunk chunk = this.mc.world.getChunkFromChunkCoords(xChunk, zChunk);

		for (LineStyle line : this.style.getLines())
		{
			if (line.prepare(this.style.getRenderType()))
			{
				buf.begin(GL_LINES, VF_POSITION);
				line.applyColour();

				int[][] lastHeight = { { -1, -1 }, { -1, -1 } };
				for (int i = 0, height = 0; i < 16; i++)
				{
					for (int j = 0; j < 2; j++)
					{
						for (int axis = 0; axis < 2; axis++)
						{
							height = axis == 0 ? chunk.getHeightValue(j * 15, i) : chunk.getHeightValue(i, j * 15);
							double xPos = axis == 0 ? xBase + (j * 16) : xBase + i;
							double zPos = axis == 0 ? zBase - 16 + i : zBase - 16 + (j * 16);
							if (lastHeight[axis][j] > -1 && height != lastHeight[axis][j])
							{
								buf.pos(xPos, lastHeight[axis][j], zPos).endVertex();
								buf.pos(xPos, height, zPos).endVertex();
							}
							buf.pos(xPos, height, zPos).endVertex();
							buf.pos(xPos + axis, height, zPos + (1 - axis)).endVertex();
							lastHeight[axis][j] = height;
						}
					}
				}
				
				tessellator.draw();
			}
		}
	}
	
}
