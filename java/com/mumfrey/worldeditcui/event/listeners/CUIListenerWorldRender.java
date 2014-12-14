package com.mumfrey.worldeditcui.event.listeners;

import static com.mumfrey.liteloader.gl.GL.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;

import com.mumfrey.worldeditcui.WorldEditCUI;

/**
 * Listener for WorldRenderEvent
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIListenerWorldRender
{
	private WorldEditCUI controller;
	
	private Minecraft minecraft;
	
	public CUIListenerWorldRender(WorldEditCUI controller, Minecraft minecraft)
	{
		this.controller = controller;
		this.minecraft = minecraft;
	}
	
	public void onRender(float partialTicks)
	{
		RenderHelper.disableStandardItemLighting();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnableBlend();
		glEnableAlphaTest();
		glAlphaFunc(GL_GREATER, 0.0F);
		glDisableTexture2D();
		glDepthMask(false);
		glPushMatrix();
		
		try
		{
			EntityPlayerSP thePlayer = this.minecraft.thePlayer;
			glTranslated(-this.getPlayerXGuess(thePlayer, partialTicks), -this.getPlayerYGuess(thePlayer, partialTicks), -this.getPlayerZGuess(thePlayer, partialTicks));
			glColor3f(1.0f, 1.0f, 1.0f);
			if (this.controller.getSelection() != null)
			{
				this.controller.getSelection().render();
			}
		}
		catch (Exception e)
		{
		}
		
		glDepthFunc(GL_LEQUAL);
		glPopMatrix();
		
		glDepthMask(true);
		glEnableTexture2D();
		glDisableBlend();
		glAlphaFunc(GL_GREATER, 0.1F);

		RenderHelper.enableStandardItemLighting();
	}
	
	private double getPlayerXGuess(EntityPlayerSP thePlayer, float renderTick)
	{
		return thePlayer.prevPosX + ((thePlayer.posX - thePlayer.prevPosX) * renderTick);
	}
	
	private double getPlayerYGuess(EntityPlayerSP thePlayer, float renderTick)
	{
		return thePlayer.prevPosY + ((thePlayer.posY - thePlayer.prevPosY) * renderTick);
	}
	
	private double getPlayerZGuess(EntityPlayerSP thePlayer, float renderTick)
	{
		return thePlayer.prevPosZ + ((thePlayer.posZ - thePlayer.prevPosZ) * renderTick);
	}
}
