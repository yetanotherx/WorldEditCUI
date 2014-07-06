package com.mumfrey.worldeditcui.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;

import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;
import com.mumfrey.worldeditcui.LiteModWorldEditCUI;
import com.mumfrey.worldeditcui.gui.controls.GuiColourButton;
import com.mumfrey.worldeditcui.gui.controls.GuiControl;
import com.mumfrey.worldeditcui.render.LineColour;

public class CUIConfigPanel extends Gui implements ConfigPanel
{
	private static final int CONTROL_SPACING = 24;

	private Minecraft mc;
	
	private LiteModWorldEditCUI mod;
	
	private List<GuiControl> controlList = new ArrayList<GuiControl>();
	
	private List<GuiColourButton> colourButtonList = new ArrayList<GuiColourButton>();
	
	private GuiControl activeControl;
	
	public CUIConfigPanel()
	{
		this.mc = Minecraft.getMinecraft();
	}
	
	@Override
	public String getPanelTitle()
	{
		return I18n.format("wecui.options.title");
	}
	
	@Override
	public int getContentHeight()
	{
		return LineColour.values().length * CUIConfigPanel.CONTROL_SPACING;
	}
	
	@Override
	public void onPanelShown(ConfigPanelHost host)
	{
		this.mod = host.getMod();
		
		ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		GuiControl.setScreenSizeAndScale(host.getWidth(), this.getContentHeight(), scaledresolution.getScaleFactor());
		
		this.controlList.clear();
		int nextId = 0;
		
		for (LineColour colour : LineColour.values())
		{
			this.controlList.add(new GuiColourButton(this.mc, nextId, 10, nextId * CUIConfigPanel.CONTROL_SPACING, 40, 20, colour));
			this.controlList.add(new GuiControl(this.mc, 100 + nextId, 220, nextId * CUIConfigPanel.CONTROL_SPACING, 60, 20, "Reset"));
			nextId++;
		}
		
		for (GuiControl control : this.controlList)
		{
			if (control instanceof GuiColourButton)
				this.colourButtonList.add((GuiColourButton)control);
		}
	}
	
	@Override
	public void onPanelResize(ConfigPanelHost host)
	{
	}
	
	@Override
	public void onPanelHidden()
	{
		for (GuiColourButton colourButton : this.colourButtonList)
		{
			colourButton.save();
		}
		
		this.mod.getController().getConfiguration().save();
	}
	
	@Override
	public void onTick(ConfigPanelHost host)
	{
	}
	
	@Override
	public void drawPanel(ConfigPanelHost host, int mouseX, int mouseY, float partialTicks)
	{
		for (GuiControl control : this.controlList)
		{
			control.drawButton(this.mc, mouseX, mouseY);
		}
		
		for (GuiColourButton colourButton : this.colourButtonList)
		{
			colourButton.drawPicker(this.mc, mouseX, mouseY);
		}
	}
	
	@Override
	public void mousePressed(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton)
	{
		boolean makeActive = true;
		
		for (GuiControl control : this.controlList)
		{
			if (control.mousePressed(this.mc, mouseX, mouseY))
			{
				if (makeActive)
				{
					makeActive = false;
					this.activeControl = control;
					this.actionPerformed(control);
				}
			}
		}
	}
	
	private void actionPerformed(GuiControl control)
	{
		if (control.id >= 100)
		{
			LineColour lineColour = LineColour.values()[control.id - 100];
			lineColour.setDefaultColour();
			
			for (GuiColourButton colourButton : this.colourButtonList)
				colourButton.updateColour(lineColour);
		}
	}
	
	@Override
	public void mouseReleased(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton)
	{
		if (this.activeControl != null)
		{
			this.activeControl.mouseReleased(mouseX, mouseY);
			this.activeControl = null;
		}
	}
	
	@Override
	public void mouseMoved(ConfigPanelHost host, int mouseX, int mouseY)
	{
	}
	
	@Override
	public void keyPressed(ConfigPanelHost host, char keyChar, int keyCode)
	{
		if (keyCode == Keyboard.KEY_ESCAPE)
		{
			host.close();
			return;
		}
		
		for (GuiColourButton colourButton : this.colourButtonList)
		{
			colourButton.keyTyped(keyChar, keyCode);
		}
	}
	
	/**
	 * Enable OpenGL clipping planes (uses planes 2, 3, 4 and 5)
	 */
	protected final void enableClipping()
	{
		glEnable(GL_CLIP_PLANE2);
		glEnable(GL_CLIP_PLANE3);
		glEnable(GL_CLIP_PLANE4);
		glEnable(GL_CLIP_PLANE5);
	}
	
	/**
	 * Disable OpenGL clipping planes (uses planes 2, 3, 4 and 5)
	 */
	protected final void disableClipping()
	{
		glDisable(GL_CLIP_PLANE5);
		glDisable(GL_CLIP_PLANE4);
		glDisable(GL_CLIP_PLANE3);
		glDisable(GL_CLIP_PLANE2);
	}
}
