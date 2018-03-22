package com.mumfrey.worldeditcui.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;

import com.mumfrey.liteloader.client.gui.GuiCheckbox;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;
import com.mumfrey.worldeditcui.LiteModWorldEditCUI;
import com.mumfrey.worldeditcui.config.CUIConfiguration;
import com.mumfrey.worldeditcui.gui.controls.GuiColourButton;
import com.mumfrey.worldeditcui.gui.controls.GuiControl;
import com.mumfrey.worldeditcui.render.ConfiguredColour;

/**
 * @author Adam Mummery-Smith
 */
public class CUIConfigPanel extends Gui implements ConfigPanel
{

	private static final int COLOUR_OPTION_BASE_ID = 100;
	
	private static final int CONTROL_SPACING = 24;
	private static final int CONTROL_TOP = 80;
	private static final int CONTROLS_PADDING = 10;
	private static final int EXTRA_CONTROLS_SPACING = 16;
	private static final int EXTRA_CONTROLS_HEIGHT = CUIConfigPanel.EXTRA_CONTROLS_SPACING * 2;

	private Minecraft mc;
	
	private LiteModWorldEditCUI mod;
	
	private List<GuiButton> controlList = new ArrayList<GuiButton>();
	
	private List<GuiColourButton> colourButtonList = new ArrayList<GuiColourButton>();
	
	private GuiButton activeControl;
	
	private GuiCheckbox chkPromiscuous, chkAlwaysOnTop, chkClearAll;
	
	private int colourButtonsBottom;
	
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
		return this.colourButtonsBottom + CUIConfigPanel.EXTRA_CONTROLS_HEIGHT + CUIConfigPanel.CONTROLS_PADDING;
	}
	
	@Override
	public void onPanelShown(ConfigPanelHost host)
	{
		this.mod = host.getMod();
		
		this.controlList.clear();
		int nextId = 0;
		for (ConfiguredColour colour : ConfiguredColour.values())
		{
			this.controlList.add(new GuiColourButton(this.mc, nextId, 24, CUIConfigPanel.CONTROL_TOP + nextId * CUIConfigPanel.CONTROL_SPACING, 40, 20, colour));
			this.controlList.add(new GuiControl(this.mc, CUIConfigPanel.COLOUR_OPTION_BASE_ID + nextId, 234, CUIConfigPanel.CONTROL_TOP + nextId * CUIConfigPanel.CONTROL_SPACING, 60, 20, "Reset"));
			nextId++;
		}
		
		this.colourButtonsBottom = CUIConfigPanel.CONTROL_TOP + nextId * CUIConfigPanel.CONTROL_SPACING + CUIConfigPanel.EXTRA_CONTROLS_SPACING;
		this.controlList.add(this.chkPromiscuous = new GuiCheckbox(nextId, 24, 26, I18n.format("gui.options.compat.spammy")));
		this.controlList.add(this.chkAlwaysOnTop = new GuiCheckbox(nextId, 24, 42, I18n.format("gui.options.compat.ontop")));
		this.controlList.add(this.chkClearAll = new GuiCheckbox(nextId, 24, this.colourButtonsBottom + CUIConfigPanel.EXTRA_CONTROLS_SPACING, I18n.format("gui.options.extra.clearall")));
		
		for (GuiButton control : this.controlList)
		{
			if (control instanceof GuiColourButton)
			{
				this.colourButtonList.add((GuiColourButton)control);
			}
		}

		CUIConfiguration config = this.mod.getController().getConfiguration();
		this.chkPromiscuous.checked = config.isPromiscuous();
		this.chkAlwaysOnTop.checked = config.isAlwaysOnTop();
		this.chkClearAll.checked = config.isClearAllOnKey();
	
		ScaledResolution scaledresolution = new ScaledResolution(this.mc);
		GuiControl.setScreenSizeAndScale(host.getWidth(), this.getContentHeight(), scaledresolution.getScaleFactor());
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
		
		CUIConfiguration config = this.mod.getController().getConfiguration();
		config.setPromiscuous(this.chkPromiscuous.checked);
		config.setAlwaysOnTop(this.chkAlwaysOnTop.checked);
		config.setClearAllOnKey(this.chkClearAll.checked);
		config.save();
	}
	
	@Override
	public void onTick(ConfigPanelHost host)
	{
	}
	
	@Override
	public void drawPanel(ConfigPanelHost host, int mouseX, int mouseY, float partialTicks)
	{
		this.drawString(this.mc.fontRenderer, I18n.format("gui.options.compat.title"),  10, CUIConfigPanel.CONTROLS_PADDING, 0xFFFFFF55);
		this.drawString(this.mc.fontRenderer, I18n.format("gui.options.colours.title"), 10, 64, 0xFFFFFF55);
		this.drawString(this.mc.fontRenderer, I18n.format("gui.options.extra.title"), 10, this.colourButtonsBottom, 0xFFFFFF55);
		
		for (GuiButton control : this.controlList)
		{
			control.drawButton(this.mc, mouseX, mouseY, partialTicks);
		}
		
		for (GuiColourButton colourButton : this.colourButtonList)
		{
			colourButton.drawPicker(this.mc, mouseX, mouseY, partialTicks);
		}
	}
	
	@Override
	public void mousePressed(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton)
	{
		boolean makeActive = true;
		
		for (GuiButton control : this.controlList)
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
	
	private void actionPerformed(GuiButton control)
	{
		if (control instanceof GuiCheckbox)
		{
			GuiCheckbox chk = (GuiCheckbox)control;
			chk.checked = !chk.checked;
		}
		
		if (control.id >= CUIConfigPanel.COLOUR_OPTION_BASE_ID)
		{
			ConfiguredColour lineColour = ConfiguredColour.values()[control.id - CUIConfigPanel.COLOUR_OPTION_BASE_ID];
			lineColour.setDefault();
			
			for (GuiColourButton colourButton : this.colourButtonList)
			{
				colourButton.updateColour(lineColour);
			}
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
}
