package com.mumfrey.worldeditcui.gui.controls;

import static org.lwjgl.opengl.GL11.*;

import com.mumfrey.worldeditcui.render.LineColour;

import net.minecraft.client.Minecraft;

/**
 * Colour picker button control, spawns a colour picker when clicked
 * 
 * @author Adam Mummery-Smith
 */
public class GuiColourButton extends GuiControl
{
	/**
	 * Picker active colour 
	 */
	private int colour = 0xFF000000;
	
	private LineColour lineColour;
	
	private GuiColourPicker picker;
	
	private boolean pickerClicked = false;
	
	public GuiColourButton(Minecraft minecraft, int id, int xPosition, int yPosition, int controlWidth, int controlHeight, LineColour lineColour)
	{
		super(minecraft, id, xPosition, yPosition, controlWidth, controlHeight, lineColour.getDisplayName());
		this.lineColour = lineColour;
		this.updateColour(lineColour);
	}
	
	/**
	 * @param lineColour
	 */
	public void updateColour(LineColour lineColour)
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
	public void drawControl(Minecraft minecraft, int mouseX, int mouseY)
	{
		if (this.field_146125_m)
		{
			boolean mouseOver = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
			int borderColour = mouseOver || this.picker != null ? 0xFFFFFFFF : 0xFFA0A0A0;
			
			drawRect(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, borderColour);
			
			int v = Math.min(Math.max((int)(((float)this.field_146121_g / (float)this.field_146120_f) * 1024F), 256), 1024);
			
			minecraft.getTextureManager().bindTexture(GuiColourPicker.COLOURPICKER_CHECKER);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(this.field_146128_h + 1, this.field_146129_i + 1, this.field_146128_h + this.field_146120_f - 1, this.field_146129_i + this.field_146121_g - 1, 0, 0, 1024, v);
			
			drawRect(this.field_146128_h + 1, this.field_146129_i + 1, this.field_146128_h + this.field_146120_f - 1, this.field_146129_i + this.field_146121_g - 1, this.colour);
			
			this.mouseDragged(minecraft, mouseX, mouseY);
			
			if (this.displayString != null && this.displayString.length() > 0)
			{
				this.drawString(minecraft.fontRenderer, this.displayString, this.field_146128_h + this.field_146120_f + 8, this.field_146129_i + (this.field_146121_g - 8) / 2, 0xFFFFFFFF);
			}
		}
	}
	
	public void drawPicker(Minecraft minecraft, int mouseX, int mouseY)
	{
		if (this.field_146125_m && this.picker != null)
		{
			this.picker.drawButton(minecraft, mouseX, mouseY);
			
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
				int xPos = Math.min(this.field_146128_h + this.field_146120_f, GuiControl.lastScreenWidth - 233);
				int yPos = Math.min(this.field_146129_i, GuiControl.lastScreenHeight - 175);
				
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
