package com.mumfrey.worldeditcui.gui.controls;

import static com.mumfrey.liteloader.gl.GL.*;

import com.mumfrey.worldeditcui.render.ConfiguredColour;

import net.minecraft.client.Minecraft;

/**
 * Colour picker button control, spawns a style picker when clicked
 * 
 * @author Adam Mummery-Smith
 */
public class GuiColourButton extends GuiControl
{
	/**
	 * Picker active colour 
	 */
	private int colour = 0xFF000000;
	
	private ConfiguredColour lineColour;
	
	private GuiColourPicker picker;
	
	private boolean pickerClicked = false;
	
	public GuiColourButton(Minecraft minecraft, int id, int xPosition, int yPosition, int controlWidth, int controlHeight, ConfiguredColour lineColour)
	{
		super(minecraft, id, xPosition, yPosition, controlWidth, controlHeight, lineColour.getDisplayName());
		this.lineColour = lineColour;
		this.updateColour(lineColour);
	}
	
	/**
	 * @param lineColour
	 */
	public void updateColour(ConfiguredColour lineColour)
	{
		if (lineColour == this.lineColour)
		{
			this.colour = lineColour.getColourIntARGB();
		}
	}
	
	public int getColour()
	{
		return this.colour;
	}
	
	public void save()
	{
		this.lineColour.setColourIntRGBA(this.colour);
	}
	
	@Override
	public void drawControl(Minecraft minecraft, int mouseX, int mouseY, float partialTicks)
	{
		if (this.visible)
		{
			boolean mouseOver = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			int borderColour = mouseOver || this.picker != null ? 0xFFFFFFFF : 0xFFA0A0A0;
			
			drawRect(this.x, this.y, this.x + this.width, this.y + this.height, borderColour);
			
			int v = Math.min(Math.max((int)(((float)this.height / (float)this.width) * 1024F), 256), 1024);
			
			minecraft.getTextureManager().bindTexture(GuiColourPicker.COLOURPICKER_CHECKER);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(this.x + 1, this.y + 1, this.x + this.width - 1, this.y + this.height - 1, 0, 0, 1024, v);
			
			drawRect(this.x + 1, this.y + 1, this.x + this.width - 1, this.y + this.height - 1, this.colour);
			
			this.mouseDragged(minecraft, mouseX, mouseY);
			
			if (this.displayString != null && this.displayString.length() > 0)
			{
				this.drawString(minecraft.fontRenderer, this.displayString, this.x + this.width + 8, this.y + (this.height - 8) / 2, 0xFFFFFFFF);
			}
		}
	}
	
	public void drawPicker(Minecraft minecraft, int mouseX, int mouseY, float partialTicks)
	{
		if (this.visible && this.picker != null)
		{
			this.picker.drawButton(minecraft, mouseX, mouseY, partialTicks);
			
			if (this.picker.getDialogResult() == DialogResult.OK)
			{
				this.closePicker(true);
			}
			else if (this.picker.getDialogResult() == DialogResult.Cancel)
			{
				this.closePicker(false);
			}
		}
	}
	
	/**
	 * 
	 */
	public void closePicker(boolean getColour)
	{
		if (getColour)
			this.colour = this.picker.getColour();
		this.picker = null;
		this.pickerClicked = false;
	}
	
	/* (non-Javadoc)
	 * @see net.minecraft.src.GuiButton#mouseReleased(int, int)
	 */
	@Override
	public void mouseReleased(int mouseX, int mouseY)
	{
		if (this.pickerClicked && this.picker != null)
		{
			this.picker.mouseReleased(mouseX, mouseY);
			this.pickerClicked = false;
		}
	}
	
	/* (non-Javadoc)
	 * @see net.minecraft.src.GuiButton#mousePressed(net.minecraft.src.Minecraft, int, int)
	 */
	@Override
	public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY)
	{
		boolean pressed = super.mousePressed(minecraft, mouseX, mouseY);
		
		if (this.picker == null)
		{
			if (pressed)
			{
				int xPos = Math.min(this.x + this.width, GuiControl.lastScreenWidth - 233);
				int yPos = Math.min(this.y, GuiControl.lastScreenHeight - 175);
				
				this.picker = new GuiColourPicker(minecraft, 1, xPos, yPos, this.colour, "Choose colour");
				this.pickerClicked = false;
			}
			
			return pressed;
		}
		
		this.pickerClicked = this.picker.mousePressed(minecraft, mouseX, mouseY);
		
		if (pressed && !this.pickerClicked)
		{
			this.closePicker(true);
		}
		
		return this.pickerClicked;
	}
	
	public boolean keyTyped(char keyChar, int keyCode)
	{
		return (this.picker != null) ? this.picker.textBoxKeyTyped(keyChar, keyCode) : false;
	}
}
