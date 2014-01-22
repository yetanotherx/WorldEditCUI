package com.mumfrey.worldeditcui.event.listeners;

import static org.lwjgl.opengl.GL11.*;

import com.mumfrey.worldeditcui.WorldEditCUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;

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
		glEnable(GL_BLEND);
		glEnable(GL_ALPHA_TEST);
		glAlphaFunc(GL_GREATER, 0.0F);
		glDisable(GL_TEXTURE_2D);
		glDepthMask(false);
		glPushMatrix();
		
		try
		{
			EntityClientPlayerMP thePlayer = this.minecraft.thePlayer;
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
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		glAlphaFunc(GL_GREATER, 0.1F);

		RenderHelper.enableStandardItemLighting();
	}
	
	private double getPlayerXGuess(EntityClientPlayerMP thePlayer, float renderTick)
	{
		return thePlayer.prevPosX + ((thePlayer.posX - thePlayer.prevPosX) * renderTick);
	}
	
	private double getPlayerYGuess(EntityClientPlayerMP thePlayer, float renderTick)
	{
		return thePlayer.prevPosY + ((thePlayer.posY - thePlayer.prevPosY) * renderTick);
	}
	
	private double getPlayerZGuess(EntityClientPlayerMP thePlayer, float renderTick)
	{
		return thePlayer.prevPosZ + ((thePlayer.posZ - thePlayer.prevPosZ) * renderTick);
	}
}
